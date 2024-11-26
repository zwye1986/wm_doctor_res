package com.pinde.sci.biz.res.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IFundsBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResFundsExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
//@Transactional(rollbackFor=Exception.class)
public class FundsBizImpl implements IFundsBiz {
    @Autowired
    private ResProvinceFundMapper resProvinceFundMapper;
    @Autowired
    private ResProvinceFundDetailMapper resProvinceFundDetailMapper;
    @Autowired
    private ResBaseFundMapper resBaseFundMapper;
    @Autowired
    private ResBaseFundDetailMapper resBaseFundDetailMapper;
    @Autowired
    private ResSyntheticalFundMapper resSyntheticalFundMapper;
    @Autowired
    private ResSyntheticalFundDetailMapper resSyntheticalFundDetailMapper;
    @Autowired
    private ResFundsExtMapper resFundsExtMapper;
    @Autowired
    ResFundsExtMapper resFundsMapper;

    /**
     * 经费收支
     * @param recordFlow
     * @return
     */
    @Override
    public ResProvinceFund readResProvinceFund(String recordFlow) {
        return resProvinceFundMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int editResProvinceFund(ResProvinceFund resProvinceFund) {
        String recordFlow = resProvinceFund.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(resProvinceFund,false);
            return resProvinceFundMapper.updateByPrimaryKeySelective(resProvinceFund);
        }else {
            recordFlow = PkUtil.getUUID();
            resProvinceFund.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(resProvinceFund,true);
            return resProvinceFundMapper.insertSelective(resProvinceFund);
        }
    }

    @Override
    public List<ResProvinceFund> searchResProvinceFund(String startDate, String endDate) {
        ResProvinceFundExample example = new ResProvinceFundExample();
        ResProvinceFundExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(startDate)){
            criteria.andReportDateGreaterThanOrEqualTo(startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.andReportDateLessThanOrEqualTo(endDate);
        }
        example.setOrderByClause("REPORT_DATE DESC, RECORD_FLOW");
        return resProvinceFundMapper.selectByExample(example);
    }

    @Override
    public int editResProvinceFundDetail(ResProvinceFundDetail resProvinceFundDetail) {
        String recordFlow = resProvinceFundDetail.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(resProvinceFundDetail,false);
            return resProvinceFundDetailMapper.updateByPrimaryKeySelective(resProvinceFundDetail);
        }else {
            recordFlow = PkUtil.getUUID();
            resProvinceFundDetail.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(resProvinceFundDetail,true);
            return resProvinceFundDetailMapper.insertSelective(resProvinceFundDetail);
        }
    }

    @Override
    public List<ResProvinceFundDetail> searchResProvinceFundDetail(ResProvinceFundDetail resProvinceFundDetail) {
        ResProvinceFundDetailExample example = new ResProvinceFundDetailExample();
        ResProvinceFundDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(resProvinceFundDetail!=null){
            if(StringUtil.isNotBlank(resProvinceFundDetail.getMainFlow())){
                criteria.andMainFlowEqualTo(resProvinceFundDetail.getMainFlow());
            }
        }
        return resProvinceFundDetailMapper.selectByExample(example);
    }

    @Override
    public List<ResProvinceFundDetail> searchResProvinceFundDetailMoney(Map<String, Object> paramMap) {
        return resFundsExtMapper.searchResProvinceFundDetailMoney(paramMap);
    }

    @Override
    public int editResProvinceFundDetailBatch(String jsonData) {
        Map<String,Object> dataMap = JSON.parseObject(jsonData,Map.class);
        String mainFlow = (String)dataMap.get("mainFlow");
        List<Map<String,Object>> mapList = (List<Map<String,Object>>)dataMap.get("data");
        if(mapList!=null&&mapList.size()>0){
            for(Map<String,Object> map:mapList){
                String recordFlow = (String)map.get("recordFlow");
                String sourcesOfFundsId = (String)map.get("sourcesOfFundsId");
                String projectOfFundsId = (String)map.get("projectOfFundsId");
                String value = (String)map.get("value");
                ResProvinceFundDetail save = new ResProvinceFundDetail();
                save.setMainFlow(mainFlow);
                save.setRecordFlow(recordFlow);
                save.setSourcesOfFundsId(sourcesOfFundsId);
                save.setSourcesOfFundsName(DictTypeEnum.SourcesOfFunds.getDictNameById(sourcesOfFundsId));
                save.setProjectOfFundsId(projectOfFundsId);
                save.setProjectOfFundsName(DictTypeEnum.ProjectOfFunds.getDictNameById(projectOfFundsId));
                save.setAmountOfMoney(value);
                editResProvinceFundDetail(save);
            }
        }
        return 1;
    }

    /**
     * 基地
     * @param recordFlow
     * @return
     */
    @Override
    public ResBaseFund readResBaseFund(String recordFlow) {
        return resBaseFundMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int addResBaseFund(ResBaseFund resBaseFund) {
        GeneralMethod.setRecordInfo(resBaseFund,true);
        return resBaseFundMapper.insertSelective(resBaseFund);
    }

    @Override
    public int editResBaseFund(ResBaseFund resBaseFund) {
        String recordFlow = resBaseFund.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(resBaseFund,false);
            return resBaseFundMapper.updateByPrimaryKeySelective(resBaseFund);
        }else {
            recordFlow = PkUtil.getUUID();
            resBaseFund.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(resBaseFund,true);
            return resBaseFundMapper.insertSelective(resBaseFund);
        }
    }

    @Override
    public List<ResBaseFund> searchResBaseFund(String startDate, String endDate, ResBaseFund resBaseFund) {
        ResBaseFundExample example = new ResBaseFundExample();
        ResBaseFundExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(startDate)){
            criteria.andReportDateGreaterThanOrEqualTo(startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.andReportDateLessThanOrEqualTo(endDate);
        }
        String orgName = resBaseFund.getOrgName();
        if(StringUtil.isNotBlank(orgName)){
            criteria.andOrgNameEqualTo(orgName);
        }
        String orgFlow = resBaseFund.getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        String auditStatusId = resBaseFund.getAuditStatusId();
        if(StringUtil.isNotBlank(auditStatusId)){
            criteria.andAuditStatusIdEqualTo(auditStatusId);
        }
        example.setOrderByClause("REPORT_DATE DESC, RECORD_FLOW");
        return resBaseFundMapper.selectByExample(example);
    }
    @Override
    public int addResBaseFundDetail(ResBaseFundDetail resBaseFundDetail) {
        GeneralMethod.setRecordInfo(resBaseFundDetail,true);
        return resBaseFundDetailMapper.insertSelective(resBaseFundDetail);
    }
    @Override
    public int editResBaseFundDetail(ResBaseFundDetail resBaseFundDetail) {
        String recordFlow = resBaseFundDetail.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(resBaseFundDetail,false);
            return resBaseFundDetailMapper.updateByPrimaryKeySelective(resBaseFundDetail);
        }else {
            recordFlow = PkUtil.getUUID();
            resBaseFundDetail.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(resBaseFundDetail,true);
            return resBaseFundDetailMapper.insertSelective(resBaseFundDetail);
        }
    }

    @Override
    public List<ResBaseFundDetail> searchResBaseFundDetail(ResBaseFundDetail resBaseFundDetail) {

        ResBaseFundDetailExample example = new ResBaseFundDetailExample();
        ResBaseFundDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        String mainFlow = resBaseFundDetail.getMainFlow();
        if(StringUtil.isNotBlank(mainFlow)){
            criteria.andMainFlowEqualTo(mainFlow);
        }
        String projectOfBasefundsId = resBaseFundDetail.getProjectOfBasefundsId();
        if(StringUtil.isNotBlank(projectOfBasefundsId)){
            criteria.andProjectOfBasefundsIdEqualTo(projectOfBasefundsId);
        }
        String projectOfBasefundsName = resBaseFundDetail.getProjectOfBasefundsName();
        if(StringUtil.isNotBlank(projectOfBasefundsName)){
            criteria.andProjectOfBasefundsIdEqualTo(projectOfBasefundsName);
        }
        return resBaseFundDetailMapper.selectByExample(example);
    }

    @Override
    public List<ResBaseFundDetail> resBaseFundList(HashMap<String, Object> paramMap) {
        return resFundsMapper.resBaseFundList(paramMap);
    }


    @Override
    public int editResSyntheticalFund(ResSyntheticalFund resSyntheticalFund) {
        String recordFlow = resSyntheticalFund.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(resSyntheticalFund,false);
            return resSyntheticalFundMapper.updateByPrimaryKeySelective(resSyntheticalFund);
        }else {
            recordFlow = PkUtil.getUUID();
            resSyntheticalFund.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(resSyntheticalFund,true);
            return resSyntheticalFundMapper.insertSelective(resSyntheticalFund);
        }
    }

    @Override
    public ResSyntheticalFund readResSyntheticalFund(String recordFlow) {
        return resSyntheticalFundMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<ResSyntheticalFund> searchResSyntheticalFund(String startDate, String endDate, ResSyntheticalFund resSyntheticalFund) {
        ResSyntheticalFundExample example = new ResSyntheticalFundExample();
        ResSyntheticalFundExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(startDate)){
            criteria.andReportDateGreaterThanOrEqualTo(startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.andReportDateLessThanOrEqualTo(endDate);
        }
        String orgFlow = resSyntheticalFund.getOrgFlow();
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        String orgName = resSyntheticalFund.getOrgName();
        if(StringUtil.isNotBlank(orgName)){
            criteria.andOrgNameEqualTo(orgName);
        }
        example.setOrderByClause("REPORT_DATE DESC, RECORD_FLOW");
        return resSyntheticalFundMapper.selectByExample(example);
    }

    @Override
    public int editResSyntheticalFundDetail(ResSyntheticalFundDetail resSyntheticalFundDetail) {
        String recordFlow = resSyntheticalFundDetail.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(resSyntheticalFundDetail,false);
            return resSyntheticalFundDetailMapper.updateByPrimaryKeySelective(resSyntheticalFundDetail);
        }else {
            recordFlow = PkUtil.getUUID();
            resSyntheticalFundDetail.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(resSyntheticalFundDetail,true);
            return resSyntheticalFundDetailMapper.insertSelective(resSyntheticalFundDetail);
        }
    }

    @Override
    public List<ResSyntheticalFundDetail> searchResSyntheticalFundDetail(ResSyntheticalFundDetail resSyntheticalFundDetail) {
        ResSyntheticalFundDetailExample example = new ResSyntheticalFundDetailExample();
        ResSyntheticalFundDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

        String recordFlow = resSyntheticalFundDetail.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            criteria.andRecordFlowEqualTo(recordFlow);
        }
        String mainFlow = resSyntheticalFundDetail.getMainFlow();
        if(StringUtil.isNotBlank(mainFlow)){
            criteria.andMainFlowEqualTo(mainFlow);
        }
        String projectOfSyntheticalId = resSyntheticalFundDetail.getProjectOfSyntheticalId();
        if(StringUtil.isNotBlank(projectOfSyntheticalId)){
            criteria.andProjectOfSyntheticalIdEqualTo(projectOfSyntheticalId);
        }
        return resSyntheticalFundDetailMapper.selectByExample(example);
    }

    @Override
    public int addResSyntheticalFund(ResSyntheticalFund resSyntheticalFund) {
        GeneralMethod.setRecordInfo(resSyntheticalFund,true);
        return resSyntheticalFundMapper.insertSelective(resSyntheticalFund);
    }

    @Override
    public List<ResSyntheticalFundDetail> resResSyntheticalFundDetailList(HashMap<String, Object> paramMap) {
        return resFundsExtMapper.resResSyntheticalFundDetailList(paramMap);
    }

    @Override
    public List<SysOrg> searchOrgs(Map<String, Object> paramMap) {
        return resFundsMapper.searchOrgs(paramMap);
    }

    @Override
    public List<ResProvinceFundDetail> queryFundDetaiList() {
        return resFundsMapper.queryFundDetaiList();
    }


}
