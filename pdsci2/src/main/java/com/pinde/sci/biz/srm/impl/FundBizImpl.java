package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IFundBiz;
import com.pinde.sci.biz.srm.IProjMineBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.srm.ProjFundExtMapper;
import com.pinde.sci.dao.srm.UserSurplusInfoMapper;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.srm.BalanceFundCollectForm;
import com.pinde.sci.form.srm.FundDetailCalculateForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class FundBizImpl implements IFundBiz {

    @Autowired
    private SrmProjFundInfoMapper fundInfoMapper;
    @Autowired
    private PubProjMapper pubProjMapper;
    @Autowired
    private SrmProjFundDetailMapper fundDetailMapper;
    @Autowired
    private SrmFundSchemeMapper fundSchemeMapper;
    @Autowired
    private SrmFundItemMapper fundItemMapper;
    @Autowired
    private SrmFundProcessMapper fundProcessMapper;
    @Autowired
    private IProjMineBiz projMineBiz;
    @Autowired
    private SrmFundSchemeDetailMapper fundSchemeDetailMapper;
    @Autowired
    private ProjFundExtMapper fundExtMapper;
    @Autowired
    private UserSurplusInfoMapper surplusInfoMapper;
    @Override
    public List<FundInfoExt> getList(PubProj proj) {
        //proj.setProjStageId(ProjStageEnum.Schedule.getId());
        //proj.setProjStatusId(ProjScheduleStatusEnum.FirstAudit.getId());

        List<PubProj> proList = this.projMineBiz.searchProjListForBudget(proj);
        List<String> proFlows = new ArrayList<String>();
        for (PubProj p : proList) {
            proFlows.add(p.getProjFlow());
        }
        SrmProjFundInfoExample fundExample = new SrmProjFundInfoExample();
        List<SrmProjFundInfo> funds = new ArrayList<SrmProjFundInfo>();
        if (!proFlows.isEmpty()) {
            fundExample.createCriteria().andProjFlowIn(proFlows).andBudgetStatusIdEqualTo(AchStatusEnum.FirstAudit.getId());
            funds = this.fundInfoMapper.selectByExample(fundExample);
        }
        List<FundInfoExt> fundExts = new ArrayList<FundInfoExt>();
        FundInfoExt fundInfoExt = null;
        for (SrmProjFundInfo f : funds) {
            for (PubProj p : proList) {
                if (f.getProjFlow().equals(p.getProjFlow())) {
                    fundInfoExt = new FundInfoExt();
                    fundInfoExt.setFund(f);
                    fundInfoExt.setProject(p);
                    fundExts.add(fundInfoExt);
                    continue;
                }
            }
        }
        return fundExts;
    }

    @Override
    public int editProcess(SrmFundProcess srmFundProcess) {
        if (StringUtil.isBlank(srmFundProcess.getFundProcessFlow())) {
            srmFundProcess.setFundProcessFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(srmFundProcess, true);
            return this.fundProcessMapper.insert(srmFundProcess);
        } else {
            GeneralMethod.setRecordInfo(srmFundProcess, false);
            return this.fundProcessMapper.updateByPrimaryKeySelective(srmFundProcess);
        }
    }

    @Override
    public int editFundDetail(SrmProjFundDetail srmProjFundDetail) {
        if (StringUtil.isBlank(srmProjFundDetail.getFundDetailFlow())) {
            srmProjFundDetail.setFundDetailFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(srmProjFundDetail, true);
            return this.fundDetailMapper.insert(srmProjFundDetail);
        } else {
            GeneralMethod.setRecordInfo(srmProjFundDetail, false);
            return this.fundDetailMapper.updateByPrimaryKeySelective(srmProjFundDetail);
        }
    }

    @Override
    public SrmProjFundDetail readSrmProjFundDetail(String detailFlow) {
        return fundDetailMapper.selectByPrimaryKey(detailFlow);
    }

    @Override
    public int editFundDetail(SrmProjFundDetail srmProjFundDetail, SrmFundProcess pro) {
        if (srmProjFundDetail != null) {
            editFundDetail(srmProjFundDetail);

            if (StringUtil.isNotBlank(srmProjFundDetail.getFundDetailFlow())) {
                srmProjFundDetail = readSrmProjFundDetail(srmProjFundDetail.getFundDetailFlow());

                if (srmProjFundDetail != null && pro != null) {
                    pro.setFundFlow(srmProjFundDetail.getFundFlow());
                    pro.setFundDetailFlow(srmProjFundDetail.getFundDetailFlow());
                    pro.setOperateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                    pro.setOperateUserName(GlobalContext.getCurrentUser().getUserName());
                    pro.setOperateTime(DateUtil.getCurrDateTime());
                    pro.setOperStatusId(srmProjFundDetail.getOperStatusId());
                    pro.setOperStatusName(srmProjFundDetail.getOperStatusName());
                    GeneralMethod.setRecordInfo(pro, true);
                    editProcess(pro);
                }
            }
            return GlobalConstant.ONE_LINE;
        }
        return GlobalConstant.ZERO_LINE;
    }


    @Override
    public List<SrmProjFundDetail> getDetails(String fundFlow) {
        if (StringUtil.isNotBlank(fundFlow)) {
            SrmProjFundDetailExample example = new SrmProjFundDetailExample();
            example.createCriteria().andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.Income.getId()).andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
            example.setOrderByClause("create_time asc");
            return this.fundDetailMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<SrmProjFundDetail> paymentDetails(SrmProjFundDetail detail) {
        SrmProjFundDetailExample example = new SrmProjFundDetailExample();
        SrmProjFundDetailExample.Criteria criteria = example.createCriteria().andOperStatusIdEqualTo(AchStatusEnum.SecondAudit.getId());
        SrmProjFundDetailExample.Criteria criteria2 = example.createCriteria().andOperStatusIdEqualTo(AchStatusEnum.SecondAudit.getId());
        criteria.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        criteria2.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(detail.getFundFlow())){
            criteria.andFundFlowEqualTo(detail.getFundFlow());
            criteria2.andFundFlowEqualTo(detail.getFundFlow());
        }
        if(StringUtil.isNotBlank(detail.getFundTypeId())){
            criteria.andFundTypeIdEqualTo(detail.getFundTypeId());
            criteria2.andFundTypeIdEqualTo(detail.getFundTypeId());
        }
        if(StringUtil.isNotBlank(detail.getRealityTypeId())){
            criteria.andRealityTypeIdEqualTo(detail.getRealityTypeId());
            criteria2.andRealityTypeIdEqualTo(detail.getRealityTypeId());
        }
        if(StringUtil.isNotBlank(detail.getItemFlow())){
            criteria.andItemFlowEqualTo(detail.getItemFlow());
        }
        if(StringUtil.isNotBlank(detail.getItemId())){
            criteria.andItemIdEqualTo(detail.getItemId());
            criteria2.andItemPidEqualTo(detail.getItemId());
            example.or(criteria2);
        }
        example.setOrderByClause("create_time asc");
        return this.fundDetailMapper.selectByExample(example);
    }

    @Override
    public List<SrmProjFundDetail> getFeeDetail(String fundFlow) {
        if (StringUtil.isNotBlank(fundFlow)) {
            SrmProjFundDetailExample example = new SrmProjFundDetailExample();
            example.createCriteria().andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.ManageFee.getId()).andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
            example.setOrderByClause("create_time asc");
            return this.fundDetailMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<SrmFundProcess> getSrmFundProcess(String fundProcessFlow) {
        if (StringUtil.isNotBlank(fundProcessFlow)) {
            SrmFundProcessExample example = new SrmFundProcessExample();
            example.createCriteria().andFundFlowEqualTo(fundProcessFlow).andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
            example.setOrderByClause("create_time asc");
            return this.fundProcessMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public void saveDetail(SrmProjFundDetail detail) {
        SysUser currUser = GlobalContext.getCurrentUser();

        if (detail != null) {
            detail.setFundOperator(currUser.getUserName());
            if(detail.getFundDetailFlow() == null) {
                detail.setFundDetailFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(detail, true);
                /*if(StringUtil.isNotBlank(detail.getRealityTypeId())) {
                    detail.setRealityTypeName();
                }*/
                if(StringUtil.isNotBlank(detail.getFundSourceId())){
                    detail.setFundSourceName(DictTypeEnum.ProjFundAccountsType.getDictNameById(detail.getFundSourceId()));
                }
                if (StringUtil.isNotBlank(detail.getFundTypeId())) {
                    detail.setFundTypeName(ProjFundTypeEnum.getNameById(detail.getFundTypeId()));
                }

                if (StringUtil.isBlank(detail.getOperStatusId())) {
                    detail.setOperStatusId(AchStatusEnum.Apply.getId());
                    detail.setOperStatusName(AchStatusEnum.Apply.getName());
                } else {
                    detail.setOperStatusName(AchStatusEnum.getNameById(detail.getOperStatusId()));
                }
                if(StringUtil.isNotBlank(detail.getProvideDateTime())){
                    detail.setProvideDateTime(DateUtil.getDateTime(detail.getProvideDateTime()));
                }
                this.fundDetailMapper.insertSelective(detail);

			 /*插入过程*/
                SrmFundProcess pro = new SrmFundProcess();
                pro.setFundProcessFlow(PkUtil.getUUID());
                pro.setFundFlow(detail.getFundFlow());
                pro.setFundDetailFlow(detail.getFundDetailFlow());
                pro.setOperateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                pro.setOperateUserName(GlobalContext.getCurrentUser().getUserName());
                pro.setOperateTime(DateUtil.getCurrDateTime());
                if (StringUtil.isBlank(pro.getOperStatusId())) {
                    pro.setOperStatusId(AchStatusEnum.Apply.getId());
                    pro.setOperStatusName(AchStatusEnum.Apply.getName());
                }
                GeneralMethod.setRecordInfo(pro, true);
                this.fundProcessMapper.insertSelective(pro);
            }
            if (detail.getOperStatusId().equals(AchStatusEnum.SecondAudit.getId())) {
                //计算下拨和配套的总值 更新到fundInfo中
                BigDecimal realityGoveAmount = new BigDecimal("0.0000");
                BigDecimal realityOrgAmount = new BigDecimal("0.0000");
                SrmProjFundDetailExample example = new SrmProjFundDetailExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andFundFlowEqualTo(detail.getFundFlow()).andOperStatusIdEqualTo(AchStatusEnum.SecondAudit.getId());
                List<SrmProjFundDetail> fundDetailList = this.fundDetailMapper.selectByExample(example);
                for (SrmProjFundDetail fundDetail : fundDetailList) {
                    if(fundDetail.getFundTypeId().equals(ProjFundTypeEnum.Income.getId())||fundDetail.getFundTypeId().equals(ProjFundTypeEnum.ManageFee.getId())) {

                        if (ProjFundAccountsTypeEnum.Allocate.getId().equals(fundDetail.getRealityTypeId())) {//更新实际下拨总额
                            realityGoveAmount = realityGoveAmount.add(fundDetail.getMoney());
                        }
                        if (ProjFundAccountsTypeEnum.Matching.getId().equals(fundDetail.getRealityTypeId())) {//更新实际配套总额
                            realityOrgAmount = realityOrgAmount.add(fundDetail.getMoney());
                        }
                    }
                }
                SrmProjFundInfo fund = this.getFund(detail.getFundFlow());
                if (fund != null) {
                    GeneralMethod.setRecordInfo(fund, false);
//				 if(ProjFundAccountsTypeEnum.Allocate.getId().equals(detail.getRealityTypeId())){//更新实际下拨总额
//					 fund.setRealityGoveAmount(fund.getRealityGoveAmount().add(detail.getMoney()));
//				 }else if(ProjFundAccountsTypeEnum.Matching.getId().equals(detail.getRealityTypeId())){//更新实际配套总额
//					 fund.setRealityOrgAmount(fund.getRealityOrgAmount().add(detail.getMoney()));
//				 }
                    fund.setRealityGoveAmount(realityGoveAmount);
                    fund.setRealityOrgAmount(realityOrgAmount);
                    fund.setRealityAmount(fund.getRealityGoveAmount().add(fund.getRealityOrgAmount()));//更新实际到账总额
                    fund.setRealityBalance(fund.getRealityBalance().add(detail.getMoney()));//更新到账余额('余额+(-管理费)'|'余额+到账经费')
                    this.fundInfoMapper.updateByPrimaryKeySelective(fund);
                }
            }
        }


    }

    @Override
    public SrmProjFundInfo getFund(String fundFlow) {
        return this.fundInfoMapper.selectByPrimaryKey(fundFlow);
    }

    @Override
    public FundSum getFundSum(List<PubProjExt> list) {
        if (list != null) {
            BigDecimal amountFundSum = new BigDecimal("0");//预算经费总计
            BigDecimal goveFundSum = new BigDecimal("0");//下拨总计
            BigDecimal orgFundSum = new BigDecimal("0");//配套总计
            BigDecimal realityAmountSum = new BigDecimal("0");//到账总计
            BigDecimal yetPaymentAmountSum = new BigDecimal("0");//支出总计
            BigDecimal realityBalanceSum = new BigDecimal("0");//剩余经费总计
            for (PubProjExt pubProjExt : list) {
                SrmProjFundInfo fundInfo = pubProjExt.getProjFundInfo();
                if (fundInfo.getBudgetAmount() != null) {
                    amountFundSum = amountFundSum.add(fundInfo.getBudgetAmount());
                }
                if (fundInfo.getRealityGoveAmount() != null) {
//                    goveFundSum = goveFundSum.add(fundInfo.getRealityGoveAmount());
                    goveFundSum = goveFundSum.add(fundInfo.getBudgetGove());
                }
                if (fundInfo.getRealityOrgAmount() != null) {
//                    orgFundSum = orgFundSum.add(fundInfo.getRealityOrgAmount());
                    orgFundSum = orgFundSum.add(fundInfo.getBudgetOrg());
                }
                if (fundInfo.getRealityAmount() != null) {
                    realityAmountSum = realityAmountSum.add(fundInfo.getRealityAmount());
                }
                if (fundInfo.getYetPaymentAmount() != null) {
                    yetPaymentAmountSum = yetPaymentAmountSum.add(fundInfo.getYetPaymentAmount());
                }
                if (fundInfo.getRealityBalance() != null) {
                    realityBalanceSum = realityBalanceSum.add(fundInfo.getRealityBalance());
                }
            }
            return new FundSum(amountFundSum, goveFundSum, orgFundSum, realityAmountSum, yetPaymentAmountSum, realityBalanceSum);
        }
        return null;
    }

    @Override
    public FundInfoExt getFundExt(String fundFlow) {
        if (StringUtil.isNotBlank(fundFlow)) {
            SrmProjFundInfo fund = this.getFund(fundFlow);
            PubProj proj = this.pubProjMapper.selectByPrimaryKey(fund.getProjFlow());
            FundInfoExt fundExt = new FundInfoExt();
            fundExt.setFund(fund);
            fundExt.setProject(proj);
            SrmProjFundDetailExample example = new SrmProjFundDetailExample();
            if(InitConfig.getSysCfg("srm_local_type").equals("Y")){
                example.createCriteria().andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.Reimburse.getId()).andOperStatusIdEqualTo(AchStatusEnum.FifthAudit.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                example.setOrderByClause(" create_time asc");
            }else{
                example.createCriteria().andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.Reimburse.getId()).andOperStatusIdEqualTo(AchStatusEnum.SecondAudit.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                example.setOrderByClause(" create_time asc");
            }
            List<SrmProjFundDetail> alaimList = this.fundDetailMapper.selectByExample(example);//报销明细
            List<FundDetailExt> alaimExtList = new ArrayList<FundDetailExt>();
            FundDetailExt ae = null;
            for (SrmProjFundDetail a : alaimList) {
                ae = new FundDetailExt();
                ae.setFundDetail(a);
//                SrmFundSchemeDetail item = this.fundSchemeDetailMapper.selectByPrimaryKey(a.getItemFlow());
//                ae.setItem(item);
                alaimExtList.add(ae);
            }
            fundExt.setAlaimList(alaimExtList);
            SrmProjFundDetailExample example2 = new SrmProjFundDetailExample();
            example2.createCriteria().andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.Budget.getId());
            List<SrmProjFundDetail> budgetList = this.fundDetailMapper.selectByExample(example2);//预算明细
            fundExt.setBudgetList(budgetList);
            SrmFundScheme scheme = this.fundSchemeMapper.selectByPrimaryKey(fund.getSchemeFlow());
            fundExt.setFundScheme(scheme);
            return fundExt;
        }
        return null;
    }

    @Override
    public void delDetailByFundDetailFlow(String fundDetailFlow) {
        SrmProjFundDetail fundDetail = this.fundDetailMapper.selectByPrimaryKey(fundDetailFlow);
        fundDetail.setRecordStatus(GlobalConstant.FLAG_N);
        BigDecimal money = fundDetail.getMoney();
        String realityTypeId = fundDetail.getRealityTypeId();
        String fundFlow = fundDetail.getFundFlow();

        /*如果操作id为财务审核通过更新经费信息*/
        if (fundDetail.getOperStatusId().equals(AchStatusEnum.SecondAudit.getId())) {
            SrmProjFundInfo fundInfo = this.fundInfoMapper.selectByPrimaryKey(fundFlow);
            fundInfo.setRealityAmount(fundInfo.getRealityAmount().subtract(money));
           // fundInfo.setRealityBalance(fundInfo.getRealityBalance().subtract(money));
            fundInfo.setRealityBalance(fundInfo.getRealityAmount().subtract(fundInfo.getYetPaymentAmount()));
            if (ProjFundAccountsTypeEnum.Allocate.getId().equals(realityTypeId) ) {//更新实际下拨总额
                //更改实际到账中的下拨金额
                fundInfo.setRealityGoveAmount(fundInfo.getRealityGoveAmount().subtract(money));

            } else if (ProjFundAccountsTypeEnum.Matching.getId().equals(realityTypeId) ) {//更新实际配套总额
                //更改实际到账中的配套金额
                fundInfo.setRealityOrgAmount(fundInfo.getRealityOrgAmount().subtract(money));
            }
            GeneralMethod.setRecordInfo(fundInfo, false);
            this.fundInfoMapper.updateByPrimaryKeySelective(fundInfo);
        }

        GeneralMethod.setRecordInfo(fundDetail, false);
        this.fundDetailMapper.updateByPrimaryKeySelective(fundDetail);


    }

    @Override
    public List<FundDetailCalculateForm> calculateFundDetail(String fundFlow,String startTime,String endTime) {
        if (StringUtil.isNotBlank(fundFlow)) {
           // SrmProjFundInfo fund = this.getFund(fundFlow);

            SrmProjFundDetailExample example = new SrmProjFundDetailExample();
            SrmProjFundDetailExample.Criteria criteria = example.createCriteria();
            criteria.andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.Reimburse.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            if(InitConfig.getSysCfg("srm_local_type").equals("Y")){
                criteria.andOperStatusIdEqualTo(AchStatusEnum.FifthAudit.getId());
            }else{
                criteria.andOperStatusIdEqualTo(AchStatusEnum.SecondAudit.getId());
            }
            if(StringUtil.isNotBlank(startTime)){
                criteria.andProvideDateTimeGreaterThanOrEqualTo(DateUtil.getDateTime(startTime));
            }
            if(StringUtil.isNotBlank(endTime)){
                criteria.andProvideDateTimeLessThanOrEqualTo(DateUtil.getDateTime(endTime));
            }
            example.setOrderByClause(" create_time asc");
            List<SrmProjFundDetail> alaimList = this.fundDetailMapper.selectByExample(example);//报销明细

//            SrmProjFundDetailExample example2 = new SrmProjFundDetailExample();
//            example2.createCriteria().andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.Budget.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//            List<SrmProjFundDetail> budgetList = this.fundDetailMapper.selectByExample(example2);//预算明细


            /*SrmProjFundDetailExample example1 = new SrmProjFundDetailExample();
            example1.createCriteria().andFundFlowEqualTo(fundFlow).andFundTypeIdEqualTo(ProjFundTypeEnum.ManageFee.getId()).andOperStatusIdEqualTo(AchStatusEnum.SecondAudit.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            example1.setOrderByClause(" create_time asc");
            List<SrmProjFundDetail> feeDetailList = fundDetailMapper.selectByExample(example1);*/

            Map<String,Object> map = new HashMap<>();//预算明细
            map.put("fundFlow",fundFlow);
            map.put("fundTypeId",ProjFundTypeEnum.Budget.getId());
            List<FundDetailAndSchemeExt> budgetList = fundExtMapper.selectFundDetailExtList(map);

            List<FundDetailCalculateForm>  calculateFormList = new ArrayList<>();
            for(SrmProjFundDetail budgetDetail:budgetList){
                boolean isAdd = false;
                if(budgetDetail.getMoney() != null && budgetDetail.getMoney().compareTo(new BigDecimal(0))==1){//预算不为空 且 大于0
                    isAdd = true;
                }
                //该项已报销总计
                BigDecimal yetPaymentAmount = new BigDecimal("0");
                //该项下拨已报销
                BigDecimal yetPaymentAllocate = new BigDecimal("0");
                //该项配套已报销
                BigDecimal yetPaymentMatching = new BigDecimal("0");
                FundDetailCalculateForm calculateForm = new FundDetailCalculateForm();
                for (SrmProjFundDetail a : alaimList) {
                    if(budgetDetail.getItemFlow().equals(a.getItemFlow())){
                        isAdd = true;
                        if(null == a.getRealmoney()){
                            a.setRealmoney(new BigDecimal("0"));
                        }
                        yetPaymentAmount = yetPaymentAmount.add(a.getRealmoney());
                        //下拨已报销
                        if(ProjFundAccountsTypeEnum.Allocate.getId().equals(a.getRealityTypeId())){
                            yetPaymentAllocate = yetPaymentAllocate.add(a.getRealmoney());
                        }
                        //配套已报销
                        if(ProjFundAccountsTypeEnum.Matching.getId().equals(a.getRealityTypeId())){
                            yetPaymentMatching = yetPaymentMatching.add(a.getRealmoney());
                        }
                    }
                    //当前报销父项
                    if (StringUtil.isNotBlank(a.getItemPid()) && StringUtil.isNotBlank(budgetDetail.getItemId()) && (StringUtil.isBlank(budgetDetail.getItemPid()) || "0".equals(budgetDetail.getItemPid())) && budgetDetail.getItemId().equals(a.getItemPid())){
                        isAdd = true;
                        if(null == a.getRealmoney()){
                            a.setRealmoney(new BigDecimal("0"));
                        }
                        yetPaymentAmount = yetPaymentAmount.add(a.getRealmoney());
                        //下拨已报销
                        if(ProjFundAccountsTypeEnum.Allocate.getId().equals(a.getRealityTypeId())){
                            yetPaymentAllocate = yetPaymentAllocate.add(a.getRealmoney());
                        }
                        //配套已报销
                        if(ProjFundAccountsTypeEnum.Matching.getId().equals(a.getRealityTypeId())){
                            yetPaymentMatching = yetPaymentMatching.add(a.getRealmoney());
                        }
                    }

                }
                /*for (SrmProjFundDetail a : feeDetailList) {//管理费
                    if("guanlifei".equals(budgetDetail.getItemId())){
                        if(null == a.getMoney()){
                            a.setMoney(new BigDecimal("0"));
                        }
                        yetPaymentAmount = yetPaymentAmount.subtract(a.getMoney());//管理费是负数 所以用减法
                        //下拨已报销
                        if(ProjFundAccountsTypeEnum.Allocate.getId().equals(a.getRealityTypeId())){
                            yetPaymentAllocate = yetPaymentAllocate.subtract(a.getMoney());
                        }
                        //配套已报销
                        if(ProjFundAccountsTypeEnum.Matching.getId().equals(a.getRealityTypeId())){
                            yetPaymentMatching = yetPaymentMatching.subtract(a.getMoney());
                        }
                    }
                }*/
                if(isAdd) {
                    calculateForm.setFundDetailFlow(budgetDetail.getFundDetailFlow());
                    calculateForm.setItemFlow(budgetDetail.getItemFlow());
                    calculateForm.setMoney(budgetDetail.getMoney());
                    calculateForm.setProvideDateTime(budgetDetail.getProvideDateTime());
                    calculateForm.setRemark(budgetDetail.getRemark());
                    calculateForm.setItemId(budgetDetail.getItemId());
                    calculateForm.setItemName(budgetDetail.getItemName());
                    calculateForm.setItemPid(budgetDetail.getItemPid());
                    calculateForm.setAllocateMoney(budgetDetail.getAllocateMoney());
                    calculateForm.setMatchingMoney(budgetDetail.getMatchingMoney());

                    calculateForm.setYetPaymentAmount(yetPaymentAmount);
                    calculateForm.setYetPaymentAllocate(yetPaymentAllocate);
                    calculateForm.setYetPaymentMatching(yetPaymentMatching);
                    calculateForm.setBalanceAmount((null == budgetDetail.getMoney()) ? new BigDecimal("0") : budgetDetail.getMoney().subtract(yetPaymentAmount));
                    calculateForm.setBalanceAllocate((null == budgetDetail.getAllocateMoney()) ? new BigDecimal("0") : budgetDetail.getAllocateMoney().subtract(yetPaymentAllocate));
                    calculateForm.setBalanceMatching((null == budgetDetail.getMatchingMoney()) ? new BigDecimal("0") : budgetDetail.getMatchingMoney().subtract(yetPaymentMatching));
                    calculateFormList.add(calculateForm);
                }
            }
            return calculateFormList;
        }
        return null;
    }

    @Override
    public List<UserSurplusInfo> surplusInfoList(Map<String, String> map) {
        return surplusInfoMapper.surplusInfoList(map);
    }

    @Override
    public List<PubProjExt> surplusProjInfoList(PubProjExt pubProjExt) {

        return surplusInfoMapper.surplusProjInfoList(pubProjExt);
    }

    @Override
    public int importIncomesFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);

            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize()));
            return parseExcel(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }


    private int parseExcel(Workbook wb){
        SysUser currentUser = GlobalContext.getCurrentUser();
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }
            for(int i = 1;i <= row_num; i++){
                SrmProjFundInfo fundInfo = null;
                Row r =  sheet.getRow(i);
                SrmProjFundDetail fundDetail = new SrmProjFundDetail();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
                        if (cell.getCellType() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            if ((double) Math.round(cell.getNumericCellValue()) - cell.getNumericCellValue() == 0.0D) {
                                value = String.valueOf((long) cell.getNumericCellValue());
                            }
                            else {
                                value = String.valueOf(cell.getNumericCellValue());
                            }
                            if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
                                Date theDate = cell.getDateCellValue();
                                SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                value = dff.format(theDate);
                            }
                        }
                    }
					/* 课题编号	到账类型	到账金额（万元）	到账时间	到账单位 */

                    if("课题编号".equals(colnames.get(j))) {
                        if(StringUtil.isNotBlank(value)){
                            Map<String,Object> map = new HashMap<>();
                            map.put("projNo",value);
                            map.put("applyOrgFlow",currentUser.getOrgFlow());
                            List<SrmProjFundInfo> fundInfoList = fundExtMapper.selectFundInfoList(map);
                            if(fundInfoList != null && fundInfoList.size() > 0){
                                fundInfo = fundInfoList.get(0);
                                fundDetail.setFundFlow(fundInfo.getFundFlow());
                            }
                        }
                    }else if("到账类型".equals(colnames.get(j))){
                        fundDetail.setFundSourceName(value);
                        if(StringUtil.isBlank(fundDetail.getFundSourceId())){
                            fundDetail.setFundSourceId(getDictId(value, DictTypeEnum.ProjFundAccountsType.getId()));
                        }
                        List<SysDict> dictList = DictTypeEnum.ProjFundAccountsType.getSysDictList();
                        String fundAccountsType = "";//经费到账类型（字典中维护）
                        if (dictList != null && dictList.size() > 0) {
                            for (SysDict dict : dictList) {
                                if (dict.getDictId().equals(fundDetail.getFundSourceId())) {
                                    fundAccountsType = dict.getDictDesc();//字典描述（下拨、配套）
                                    continue;
                                }
                            }
                            if("下拨".equals(fundAccountsType)) {
                                fundDetail.setRealityTypeId(ProjFundAccountsTypeEnum.Allocate.getId());
                                fundDetail.setRealityTypeName(ProjFundAccountsTypeEnum.Allocate.getName());
                            }else if("配套".equals(fundAccountsType)){
                                fundDetail.setRealityTypeId(ProjFundAccountsTypeEnum.Matching.getId());
                                fundDetail.setRealityTypeName(ProjFundAccountsTypeEnum.Matching.getName());
                            }
                        }
                    }else if("到账金额（万元）".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)){
                            BigDecimal momey = new BigDecimal(value);
                            fundDetail.setMoney(momey);
                        }
                    }else if("到账时间".equals(colnames.get(j))){
                        if(StringUtil.isNotBlank(value)){
                            if(value.length() == 10){
                                value+=" 00:00:00";
                            }
                            fundDetail.setProvideDateTime(DateUtil.getDateTime(value));
                        }
                    }else if("到账单位".equals(colnames.get(j))){
                        fundDetail.setProvideOrg(value);
                    }
                }
                if(StringUtil.isBlank(fundDetail.getFundFlow())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，请检查课题编号！");
                }
                if(StringUtil.isBlank(fundDetail.getRealityTypeId())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，请检查到账类型！");
                }
                if(null == fundDetail.getMoney()){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，请检查到账金额！");
                }
                if(StringUtil.isBlank(fundDetail.getProvideDateTime())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，请检查到账时间！");
                }
                fundDetail.setFundTypeId(ProjFundTypeEnum.Income.getId());
                fundDetail.setFundTypeName(ProjFundTypeEnum.Income.getName());
                fundDetail.setOperStatusId(AchStatusEnum.SecondAudit.getId());
                fundDetail.setOperStatusName(AchStatusEnum.SecondAudit.getName());
                saveDetail(fundDetail);
                //执行保存
                /*if(StringUtil.isNotBlank(sysUser.getUserFlow())){
                    SysUser exitSysUser = readSysUser(sysUser.getUserFlow());
                    if(exitSysUser != null){
                        updateUser(sysUser);
                    }else{
                        addUser(sysUser);
                    }
                }else{
                    sysUser.setUserFlow(PkUtil.getUUID());
                    addUser(sysUser);
                }*/
                count ++;
            }
        }
        return count;
    }
    /**
     * 根据字典名称获取Id
     * @param dictName
     * @param dictTypeId
     * @return
     */
    public String getDictId(String dictName,String dictTypeId){
        Map<String,String> dictNameMap=InitConfig.getDictNameMap(dictTypeId);
        return dictNameMap.get(dictName);
    }

    @Override
    public List<PersonalFundInfoExt> personalFundList(Map<String, String> map) {
        return surplusInfoMapper.personalFundList(map);
    }

    @Override
    public BalanceFundCollectForm getFundCollectForm(List<String> fundFlowList) {

        SrmProjFundDetailExample example = new SrmProjFundDetailExample();
        example.createCriteria().andFundFlowIn(fundFlowList).andFundTypeIdEqualTo(ProjFundTypeEnum.Income.getId()).andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andOperStatusIdEqualTo(AchStatusEnum.SecondAudit.getId());
        example.setOrderByClause("create_time asc");
        //所有到账
        List<SrmProjFundDetail> incomeDetailList = fundDetailMapper.selectByExample(example);

        SrmProjFundDetailExample example2 = new SrmProjFundDetailExample();
        example2.createCriteria().andFundFlowIn(fundFlowList).andFundTypeIdEqualTo(ProjFundTypeEnum.Reimburse.getId()).andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andOperStatusIdEqualTo(AchStatusEnum.SecondAudit.getId());
        example2.setOrderByClause("create_time asc");
        //所有报销
        List<SrmProjFundDetail> reimburseDetailList = fundDetailMapper.selectByExample(example2);

        List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("ProjFundAccountsType");

        Map<String,BigDecimal> balanceTypeFundMap = new HashMap<>();
        /*
            剩余经费（行政） 来源为除国库下拨、院基金会的
            剩余经费（国库）来源为国库下拨
            剩余经费（院基金会）来源为院基金会
         */
        for(SysDict dict : dictList){
            BigDecimal incomeMoney = new BigDecimal(0);
            BigDecimal reimburseMoney = new BigDecimal(0);
            for(SrmProjFundDetail detail : incomeDetailList){
                if(dict.getDictId().equals(detail.getFundSourceId())){
                    incomeMoney = incomeMoney.add(detail.getMoney());
                }
            }
            for(SrmProjFundDetail detail : reimburseDetailList){
                if(dict.getDictId().equals(detail.getFundSourceId())){
                    reimburseMoney = reimburseMoney.add(detail.getRealmoney());
                }
            }
            balanceTypeFundMap.put(dict.getDictId(),(incomeMoney.subtract(reimburseMoney)).multiply(new BigDecimal(10000)));
        }
        BalanceFundCollectForm fundCollectForm = new BalanceFundCollectForm();
        fundCollectForm.setBalanceFundMapBySourceId(balanceTypeFundMap);
        return fundCollectForm;
    }

    public static void main(String[] agrs){
        System.out.println(DateUtil.getDateTime("2016-08-07"));
    }
}
