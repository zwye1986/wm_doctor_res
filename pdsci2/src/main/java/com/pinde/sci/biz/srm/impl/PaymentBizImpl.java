package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IPaymentBiz;
import com.pinde.sci.biz.srm.IProjMineBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.srm.ProjFundExtMapper;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.ProjFundAccountsTypeEnum;
import com.pinde.sci.enums.srm.ProjFundTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.FundInfoExt;
import com.pinde.sci.model.srm.ProjFundDetailExt;
import com.pinde.sci.model.srm.ProjFundFormExt;
import com.pinde.sci.model.srm.PubProjExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentBizImpl implements IPaymentBiz {

    @Autowired
    private SrmProjFundInfoMapper fundInfoMapper;
    @Autowired
    private SrmProjFundDetailMapper fundDetailMapper;
    @Autowired
    private SrmFundSchemeDetailMapper schemeDetailMapper;
    @Autowired
    private SrmFundProcessMapper processMapper;
    @Autowired
    private ProjFundExtMapper projFundExtMapper;
    @Autowired
    private IProjMineBiz projMineBiz;
    @Autowired
    private PubFileMapper pubFileMapper;
    @Autowired
    private SrmProjFundFormMapper fundFormMapper;

    @Override
    @Deprecated
    public List<FundInfoExt> queryPaymentList(PubProj proj) {
        SysUser currUser = GlobalContext.getCurrentUser();
        proj.setApplyUserFlow(currUser.getUserFlow());
        List<PubProj> proList = this.projMineBiz.searchProjListForBudget(proj);
        Map<String, PubProj> projMap = new HashMap<String, PubProj>();
        for (PubProj p : proList) {
            projMap.put(p.getProjFlow(), p);
        }
        SrmProjFundInfoExample fundExample = new SrmProjFundInfoExample();
        List<SrmProjFundInfo> funds = new ArrayList<SrmProjFundInfo>();
        if (!projMap.isEmpty()) {
            fundExample.createCriteria().andProjFlowIn(new ArrayList<String>(projMap.keySet())).andBudgetStatusIdEqualTo(AchStatusEnum.FirstAudit.getId());
            funds = this.fundInfoMapper.selectByExample(fundExample);
        }
        List<FundInfoExt> fundInfoExtList = new ArrayList<FundInfoExt>();
        for (SrmProjFundInfo fundInfo : funds) {
            PubProj projForFundInfo = projMap.get(fundInfo.getProjFlow());
            FundInfoExt fundInfoExt = new FundInfoExt();
            fundInfoExt.setFund(fundInfo);
            fundInfoExt.setProject(projForFundInfo);
            fundInfoExtList.add(fundInfoExt);
        }
        return fundInfoExtList;

    }

    @Override
    public List<SrmProjFundDetail> getDetailByFundFlow(String fundFlow) {
        SrmProjFundDetailExample example = new SrmProjFundDetailExample();
        example.createCriteria().andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.Reimburse.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("CREATE_TIME");
        return fundDetailMapper.selectByExample(example);
    }

    @Override
    public void saveDetailList(String fundFlow, List<SrmProjFundDetail> detailList, SrmFundProcess fundProcess) {
        for (SrmProjFundDetail detail : detailList) {
            if (detail != null) {
                //修改报销明细
                if (StringUtil.isNotBlank(detail.getFundDetailFlow())) {
                    SrmProjFundDetail findDetail = this.getDetailByDetailFlow(detail.getFundDetailFlow());
                    if (findDetail == null || !AchStatusEnum.FirstAudit.getId().equals(findDetail.getOperStatusId())) {
                        GeneralMethod.setRecordInfo(detail, false);
                        detail.setFundFlow(fundFlow);
                        fundDetailMapper.updateByPrimaryKeySelective(detail);
                    }
                } else {
                    GeneralMethod.setRecordInfo(detail, true);
                    detail.setFundDetailFlow(PkUtil.getUUID());
                    //设项目经费流水号
                    detail.setFundFlow(fundFlow);
                    fundDetailMapper.insert(detail);
                    //操作过程
                    fundProcess.setFundProcessFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(fundProcess, true);
                    fundProcess.setOperateTime(DateUtil.getCurrDateTime());
                    processMapper.insert(fundProcess);
                }
            }
        }
    }

    @Override
    public void reimburse(SrmProjFundDetail fundDetail, PubFile file) {
        String fundFlow = fundDetail.getFundFlow();

        fundDetail.setMoney(fundDetail.getMoney().divide(new BigDecimal(10000)));
        //经费类型-->报销
        fundDetail.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
        fundDetail.setFundTypeName(ProjFundTypeEnum.Reimburse.getName());
        //操作-->提交
        fundDetail.setOperStatusId(AchStatusEnum.Submit.getId());
        fundDetail.setOperStatusName(AchStatusEnum.Submit.getName());
        //如果是徐州中心医院
        if (InitConfig.getSysCfg("srm_local_type").equals("Y")) {
            fundDetail.setOperStatusId(AchStatusEnum.Apply.getId());
            fundDetail.setOperStatusName(AchStatusEnum.Apply.getName());
            SrmProjFundForm fundForm = new SrmProjFundForm();
            fundForm.setOperStatusId(AchStatusEnum.Apply.getId());
            fundForm.setOperStatusName(AchStatusEnum.Apply.getName());
            fundForm.setFundOperator(fundDetail.getFundOperator());
            fundForm.setFundFlow(fundDetail.getFundFlow());
            BigDecimal allocateMoney = new BigDecimal("0");
            BigDecimal matchingMoney = new BigDecimal("0");
            if (fundDetail.getRealityTypeId().equals(ProjFundAccountsTypeEnum.Allocate.getId())) {
                allocateMoney = fundDetail.getMoney();
            }
            if (fundDetail.getRealityTypeId().equals(ProjFundAccountsTypeEnum.Matching.getId())) {
                matchingMoney = fundDetail.getMoney();
            }
            if (StringUtil.isNotBlank(fundDetail.getFundFormFlow())) {
                SrmProjFundForm fundFormTemp = fundFormMapper.selectByPrimaryKey(fundDetail.getFundFormFlow());
                fundForm.setMoney(fundFormTemp.getMoney().add(fundDetail.getMoney()));
                fundForm.setOrgFund(fundFormTemp.getOrgFund().add(matchingMoney));
                fundForm.setGoveFund(fundFormTemp.getGoveFund().add(allocateMoney));
                GeneralMethod.setRecordInfo(fundForm, false);
                fundForm.setFundFormFlow(fundDetail.getFundFormFlow());
                fundFormMapper.updateByPrimaryKeySelective(fundForm);
            } else {
                fundForm.setMoney(fundDetail.getMoney());
                fundForm.setOrgFund(matchingMoney);
                fundForm.setGoveFund(allocateMoney);
                GeneralMethod.setRecordInfo(fundForm, true);
                fundForm.setFundFormFlow(PkUtil.getUUID());
                fundFormMapper.insert(fundForm);
                fundDetail.setFundFormFlow(fundForm.getFundFormFlow());
            }

        }
        //到账类型名称
        fundDetail.setRealityTypeName(ProjFundAccountsTypeEnum.getNameById(fundDetail.getRealityTypeId()));
        String[] operStatus = {"FirstAudit", "SecondAudit", "ThirdAudit", "FourthAudit", "FifthAudit"};
        List<String> operStatusList = new ArrayList<>();
        operStatusList.toArray(operStatus);
        if (StringUtil.isNotBlank(fundDetail.getFundDetailFlow())) {
            SrmProjFundDetail findDetail = this.getDetailByDetailFlow(fundDetail.getFundDetailFlow());
            if (findDetail == null || (!operStatusList.contains(findDetail.getOperStatusId()))) {
                GeneralMethod.setRecordInfo(fundDetail, false);
                fundDetail.setFundFlow(fundFlow);
                fundDetailMapper.updateByPrimaryKeySelective(fundDetail);
            }
        } else {
            GeneralMethod.setRecordInfo(fundDetail, true);
            fundDetail.setFundDetailFlow(PkUtil.getUUID());
            //设项目经费流水号
            fundDetail.setFundFlow(fundFlow);
            fundDetailMapper.insert(fundDetail);

        }

        //处理文件对象
        if (file != null) {
            file.setProductFlow(fundDetail.getFundDetailFlow());
            if (StringUtil.isNotBlank(file.getFileFlow())) {
                GeneralMethod.setRecordInfo(file, false);
                pubFileMapper.updateByPrimaryKeySelective(file);
            } else {
                file.setFileFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(file, true);
                pubFileMapper.insert(file);
            }
        }
        if (!InitConfig.getSysCfg("srm_local_type").equals("Y")) {
            //封装过程对象
            SrmFundProcess fundProcess = new SrmFundProcess();
            fundProcess.setFundFlow(fundFlow);
            fundProcess.setFundDetailFlow(fundDetail.getFundDetailFlow());
            fundProcess.setOperStatusId(AchStatusEnum.Submit.getId());
            fundProcess.setOperStatusName(AchStatusEnum.Submit.getName());

            //操作过程
            fundProcess.setFundProcessFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(fundProcess, true);
            SysUser currUser = GlobalContext.getCurrentUser();
            fundProcess.setOperateUserFlow(currUser.getUserFlow());
            fundProcess.setOperateUserName(currUser.getUserName());
            fundProcess.setOperateTime(DateUtil.getCurrDateTime());
            processMapper.insert(fundProcess);
        }
    }

    @Override
    public List<SrmFundSchemeDetail> getSchemeDetailBySchemeFlow(String schemeFlow) {
        SrmFundSchemeDetailExample example = new SrmFundSchemeDetailExample();
        example.createCriteria().andSchemeFlowEqualTo(schemeFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

        return schemeDetailMapper.selectByExample(example);
    }


    @Override
    public List<PubProjExt> queryProjFundInfoList(PubProjExt projExt) {
        return this.projFundExtMapper.selectReimburseInfoExtList(projExt);
    }

    @Override
    public SrmProjFundInfo getFundInfoByFundFlow(String fundFlow) {
        return fundInfoMapper.selectByPrimaryKey(fundFlow);
    }

    @Override
    public SrmProjFundDetail getDetailByDetailFlow(String fundDetailFlow) {
        return fundDetailMapper.selectByPrimaryKey(fundDetailFlow);
    }

    @Override
    public void updateDetailStatus(SrmProjFundDetail fundDetail, SrmFundProcess fundProcess) {
        if (AchStatusEnum.SecondAudit.getId().equals(fundDetail.getOperStatusId()) || AchStatusEnum.FifthAudit.getId().equals(fundDetail.getOperStatusId())) {
            //报销时间
            fundDetail.setProvideDateTime(DateUtil.getCurrDateTime());
        }
        if (StringUtil.isNotBlank(fundDetail.getFundDetailFlow())) {
            GeneralMethod.setRecordInfo(fundDetail, false);
            fundDetailMapper.updateByPrimaryKeySelective(fundDetail);
        }else{
            fundDetail.setFundDetailFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(fundDetail, true);
            fundDetailMapper.insert(fundDetail);
        }

        fundProcess.setFundDetailFlow(fundDetail.getFundDetailFlow());
            processMapper.insert(fundProcess);
            if (AchStatusEnum.SecondAudit.getId().equals(fundDetail.getOperStatusId()) || AchStatusEnum.FifthAudit.getId().equals(fundDetail.getOperStatusId())) {
                //更新项目经费表
                SrmProjFundInfo fundInfo = fundInfoMapper.selectByPrimaryKey(fundDetail.getFundFlow());
                //已报销金额=原来已报销的+现在报销的
                if (fundDetail.getRealmoney() != null && !fundDetail.getRealmoney().equals("")) {
                    BigDecimal yetPayment = fundInfo.getYetPaymentAmount().add(fundDetail.getRealmoney());
                    fundInfo.setYetPaymentAmount(yetPayment);
                    //下拨已报销
                    if(ProjFundAccountsTypeEnum.Allocate.getId().equals(fundDetail.getRealityTypeId())){
                        fundInfo.setYetPaymentGove(fundInfo.getYetPaymentGove().add(fundDetail.getRealmoney()));
                    }
                    //配套已报销
                    if(ProjFundAccountsTypeEnum.Matching.getId().equals(fundDetail.getRealityTypeId())){
                        fundInfo.setYetPaymentOrg(fundInfo.getYetPaymentOrg().add(fundDetail.getRealmoney()));
                    }
                    //到账余额=实际到账-已报销
                    fundInfo.setRealityBalance(fundInfo.getRealityAmount().subtract(yetPayment));
                }
                this.fundInfoMapper.updateByPrimaryKeySelective(fundInfo);
            }

    }

    @Override
    public SrmProjFundDetail searchBudgetDetail(String fundFlow, String itemFlow) {
        SrmProjFundDetailExample example = new SrmProjFundDetailExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).
                andFundFlowEqualTo(fundFlow).
                andItemFlowEqualTo(itemFlow).
                andFundTypeIdEqualTo(ProjFundTypeEnum.Budget.getId());
        List<SrmProjFundDetail> resultList = this.fundDetailMapper.selectByExample(example);
        if (resultList.size()>0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public int searchFundDetailNoApproveCount(SrmProjFundDetail fundDetail) {

        SrmProjFundDetailExample example = new SrmProjFundDetailExample();
        com.pinde.sci.model.mo.SrmProjFundDetailExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(fundDetail.getFundFlow())) {
            criteria.andFundFlowEqualTo(fundDetail.getFundFlow());
        }
        if (StringUtil.isNotBlank(fundDetail.getFundTypeId())) {
            criteria.andFundTypeIdEqualTo(fundDetail.getFundTypeId());
        }
        if (StringUtil.isNotBlank(fundDetail.getOperStatusId())) {
            criteria.andOperStatusIdEqualTo(fundDetail.getOperStatusId());
        }
        return this.fundDetailMapper.countByExample(example);
    }

    @Override
    public List<SrmProjFundForm> getFormByFundFlow(String fundFlow) {
        SrmProjFundFormExample example = new SrmProjFundFormExample();
        example.createCriteria().andFundFlowEqualTo(fundFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("CREATE_TIME");
        return fundFormMapper.selectByExample(example);
    }

    /**
     * 分组查询经费详情
     *
     * @param fundDetailExt
     * @return
     */
    @Override
    public List<SrmProjFundDetail> getDetailByGroup(String formFlow, String fundFlow) {
        SrmProjFundDetailExample example = new SrmProjFundDetailExample();
        example.createCriteria().andFundTypeIdEqualTo(ProjFundTypeEnum.Reimburse.getId()).andFundFlowEqualTo(fundFlow).andFundFormFlowEqualTo(formFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("CREATE_TIME");
        return fundDetailMapper.selectByExample(example);
    }

    @Override
    public SrmProjFundForm getFormByFormFlow(String formFlow) {
        return fundFormMapper.selectByPrimaryKey(formFlow);
    }

    @Override
    public void submitReimburseForm(SrmProjFundForm fundForm) {
        if (StringUtil.isNotBlank(fundForm.getFundFormFlow())) {
            GeneralMethod.setRecordInfo(fundForm, false);
            fundFormMapper.updateByPrimaryKeySelective(fundForm);
        } else {
            GeneralMethod.setRecordInfo(fundForm, true);
            fundForm.setFundFormFlow(PkUtil.getUUID());
            fundFormMapper.insert(fundForm);
        }

        SrmFundProcess fundProcess = new SrmFundProcess();
        fundProcess.setFundFlow(fundForm.getFundFlow());
        //此处添加经费报销表单流水号
        fundProcess.setFundFlow(fundForm.getFundFlow());
        fundProcess.setFundDetailFlow(fundForm.getFundFormFlow());
        fundProcess.setOperStatusId(AchStatusEnum.Submit.getId());
        fundProcess.setOperStatusName(AchStatusEnum.Submit.getName());

        //操作过程
        fundProcess.setFundProcessFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(fundProcess, true);
        SysUser currUser = GlobalContext.getCurrentUser();
        fundProcess.setOperateUserFlow(currUser.getUserFlow());
        fundProcess.setOperateUserName(currUser.getUserName());
        fundProcess.setOperateTime(DateUtil.getCurrDateTime());
        processMapper.insert(fundProcess);
    }

    @Override
    public List<SrmProjFundDetail> getReimburseDetail(SrmProjFundDetail fundDetail,List<String> operStatusIds ) {
        SrmProjFundDetailExample example = new SrmProjFundDetailExample();
        SrmProjFundDetailExample.Criteria criteria = example.createCriteria();
        criteria.andFundTypeIdEqualTo(ProjFundTypeEnum.Reimburse.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(null != operStatusIds && operStatusIds.size() > 0){
            criteria.andOperStatusIdIn(operStatusIds);
        }
        example.setOrderByClause("CREATE_TIME");
        return fundDetailMapper.selectByExample(example);
    }

    @Override
    public List<ProjFundFormExt> queryFundFormAuditList(ProjFundFormExt fundFormExt) {
        SysUser currUser = GlobalContext.getCurrentUser();
        fundFormExt.setRecordStatus(GlobalConstant.FLAG_Y);
        PubProj proj = fundFormExt.getProj();
        if (proj == null) {
            //是否增加所在单位
            proj = new PubProj();
            proj.setApplyOrgFlow(currUser.getOrgFlow());
            fundFormExt.setProj(proj);
        } else {
            proj.setApplyOrgFlow(currUser.getOrgFlow());
        }
        return this.projFundExtMapper.selectProjFundFormList(fundFormExt);
    }

    @Override
    public ProjFundFormExt selectFundFormExtByFormFlow(String fundFormFlow) {
        return projFundExtMapper.selectProjFundFormExt(fundFormFlow);
    }

    @Override
    public void updateFormStatus(List<SrmProjFundDetail> fundDetailList, SrmProjFundForm fundForm, SrmFundProcess process) {
        if (StringUtil.isNotBlank(fundForm.getFundFormFlow())) {
            BigDecimal realPayment = new BigDecimal("0");
            //配套
            BigDecimal realOrgPayment = new BigDecimal("0");
            //下拨
            BigDecimal realGovePayment = new BigDecimal("0");
            String time = DateUtil.getCurrDateTime();
            for (SrmProjFundDetail fundDetail : fundDetailList) {
                if (AchStatusEnum.FifthAudit.getId().equals(fundDetail.getOperStatusId())) {
                    fundDetail.setProvideDateTime(time);
                    //已报销金额=原来已报销的+现在报销的
                    if (fundDetail.getRealmoney() != null && !fundDetail.getRealmoney().equals("")) {
                        realPayment = realPayment.add(fundDetail.getRealmoney());
                        if (fundDetail.getRealityTypeId().equals(ProjFundAccountsTypeEnum.Allocate.getId())) {
                            realGovePayment = realGovePayment.add(fundDetail.getRealmoney());
                        }
                        if (fundDetail.getRealityTypeId().equals(ProjFundAccountsTypeEnum.Matching.getId())) {
                            realOrgPayment = realOrgPayment.add(fundDetail.getRealmoney());
                        }
                    }
                }
                fundDetailMapper.updateByPrimaryKeySelective(fundDetail);
            }
            GeneralMethod.setRecordInfo(fundForm, false);
            if (AchStatusEnum.FifthAudit.getId().equals(fundForm.getOperStatusId())) {
                //更新项目经费表
                SrmProjFundInfo fundInfo = fundInfoMapper.selectByPrimaryKey(fundForm.getFundFlow());
                BigDecimal yetPayment = fundInfo.getYetPaymentAmount().add(realPayment);
                fundInfo.setYetPaymentAmount(yetPayment);
                //到账余额=实际到账-已报销
                fundInfo.setRealityBalance(fundInfo.getRealityAmount().subtract(yetPayment));
                this.fundInfoMapper.updateByPrimaryKeySelective(fundInfo);
                fundForm.setRealmoney(realPayment);
                fundForm.setRealityGoveAmount(realGovePayment);
                fundForm.setRealityOrgAmount(realOrgPayment);
                //报销时间
                fundForm.setProvideDateTime(DateUtil.getCurrDateTime());
            }
            fundFormMapper.updateByPrimaryKeySelective(fundForm);
            if (StringUtil.isNotBlank(fundForm.getFundFormFlow())) {
                process.setFundDetailFlow(fundForm.getFundFormFlow());
            }
            processMapper.insert(process);

        }
    }

    @Override
    public List<ProjFundDetailExt> selectProjFundDetailList(ProjFundDetailExt projFundDetailExt) {
        return projFundExtMapper.selectProjFundDetailList(projFundDetailExt);
    }

    @Override
    public int deleteReimburse(String detailFlow) {
        SrmProjFundDetail fundDetail = fundDetailMapper.selectByPrimaryKey(detailFlow);
        if(fundDetail != null){
            SrmProjFundInfo fundInfo = fundInfoMapper.selectByPrimaryKey(fundDetail.getFundFlow());
            if(fundInfo != null){
                fundDetail.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                fundInfo.setYetPaymentAmount(fundInfo.getYetPaymentAmount().subtract(fundDetail.getRealmoney()));
                fundInfo.setRealityBalance(fundInfo.getRealityBalance().add(fundDetail.getRealmoney()));
                if(ProjFundAccountsTypeEnum.Matching.getId().equals(fundDetail.getRealityTypeId())){
                    fundInfo.setYetPaymentOrg(fundInfo.getYetPaymentOrg().subtract(fundDetail.getRealmoney()));
                }else if(ProjFundAccountsTypeEnum.Allocate.getId().equals(fundDetail.getRealityTypeId())){
                    fundInfo.setYetPaymentGove(fundInfo.getYetPaymentGove().subtract(fundDetail.getRealmoney()));
                }
                int i = fundInfoMapper.updateByPrimaryKeySelective(fundInfo);
                int j = fundDetailMapper.updateByPrimaryKeySelective(fundDetail);
                if(i == 1 && j == 1){
                    return 1;
                }else{
                    throw new RuntimeException("删除失败！");
                }
            }
        }
        return 0;
    }
}
