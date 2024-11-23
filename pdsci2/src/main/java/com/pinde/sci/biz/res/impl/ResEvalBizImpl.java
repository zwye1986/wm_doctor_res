package com.pinde.sci.biz.res.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResEvalBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResBaseevalDetailMapper;
import com.pinde.sci.dao.base.ResBaseevalFormCfgMapper;
import com.pinde.sci.dao.base.ResBaseevalFormMapper;
import com.pinde.sci.dao.base.ResBaseevalMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
//@Transactional(rollbackFor=Exception.class)
public class ResEvalBizImpl implements IResEvalBiz {
    @Autowired
    private ResBaseevalFormMapper baseevalFormMapper;
    @Autowired
    private ResBaseevalFormCfgMapper baseevalFormCfgMapper;
    @Autowired
    private ResBaseevalMapper baseevalMapper;
    @Autowired
    private ResBaseevalDetailMapper baseevalDetailMapper;

    @Override
    public ResBaseevalForm readForm(String recordFlow) {
        return baseevalFormMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<ResBaseevalForm> search(ResBaseevalForm resBaseevalForm) {
        ResBaseevalFormExample example = new ResBaseevalFormExample();
        ResBaseevalFormExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return baseevalFormMapper.selectByExample(example);
    }

    @Override
    public List<ResBaseevalFormCfg> searchCfg(ResBaseevalFormCfg resBaseevalFormCfg) {
        ResBaseevalFormCfgExample example = new ResBaseevalFormCfgExample();
        ResBaseevalFormCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(resBaseevalFormCfg.getTrainingSpeId())){
            criteria.andTrainingSpeIdEqualTo(resBaseevalFormCfg.getTrainingSpeId());
        }
        if(StringUtil.isNotBlank(resBaseevalFormCfg.getTrainingSpeName())){
            criteria.andTrainingSpeNameEqualTo(resBaseevalFormCfg.getTrainingSpeName());
        }
        if(StringUtil.isNotBlank(resBaseevalFormCfg.getOrgFlow())){
            criteria.andOrgFlowEqualTo(resBaseevalFormCfg.getOrgFlow());
        }
        return baseevalFormCfgMapper.selectByExample(example);
    }

    @Override
    public int editEvalCfg(ResBaseevalFormCfg baseevalFormCfg) {
        if(StringUtil.isNotBlank(baseevalFormCfg.getRecordFlow())){
            GeneralMethod.setRecordInfo(baseevalFormCfg,false);
            return baseevalFormCfgMapper.updateByPrimaryKeySelective(baseevalFormCfg);
        }else {
            baseevalFormCfg.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(baseevalFormCfg,true);
            return baseevalFormCfgMapper.insert(baseevalFormCfg);
        }
    }

    @Override
    public List<ResBaseeval> searchBaseeval(ResBaseeval baseeval) {
        ResBaseevalExample example = new ResBaseevalExample();
        ResBaseevalExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(baseeval.getFormFlow())){
            criteria.andFormFlowEqualTo(baseeval.getFormFlow());
        }
        if(StringUtil.isNotBlank(baseeval.getSpeId())){
            criteria.andSpeIdEqualTo(baseeval.getSpeId());
        }
        if(StringUtil.isNotBlank(baseeval.getSpeName())){
            criteria.andSpeNameEqualTo(baseeval.getSpeName());
        }
        return baseevalMapper.selectByExample(example);
    }

    @Override
    public int editBaseeval(ResBaseeval baseeval) {
        if(StringUtil.isNotBlank(baseeval.getRecordFlow())){
            GeneralMethod.setRecordInfo(baseeval,false);
            return baseevalMapper.updateByPrimaryKeySelective(baseeval);
        }else {
            baseeval.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(baseeval,true);
            return baseevalMapper.insertSelective(baseeval);
        }
    }

    @Override
    public int editBaseevalDetail(ResBaseevalDetail baseevalDetail) {
        if(StringUtil.isNotBlank(baseevalDetail.getRecordFlow())){
            GeneralMethod.setRecordInfo(baseevalDetail,false);
            return baseevalDetailMapper.updateByPrimaryKeySelective(baseevalDetail);
        }else {
            baseevalDetail.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(baseevalDetail,true);
            return baseevalDetailMapper.insertSelective(baseevalDetail);
        }
    }

    @Override
    public int editBaseevalTings(String jsonData,String role) {
        Map<String,Object> dataMap = JSON.parseObject(jsonData,Map.class);
        Map<String,Object> evalMap = (Map<String,Object>)dataMap.get("eval");
        String formFlow = (String)evalMap.get("formFlow");
        String recordFlow = (String)evalMap.get("recordFlow");
        ResBaseeval saveEval = new ResBaseeval();
        SysUser user = GlobalContext.getCurrentUser();
        String speId="";
        String speName="";
        if("manager".equals(role)){
            speId="基地";
        }else{
            speId = user.getResTrainingSpeId();
            speName =user.getResTrainingSpeName();
        }
        saveEval.setFormFlow(formFlow);
        saveEval.setRecordFlow(recordFlow);
        saveEval.setSpeId(speId);
        saveEval.setSpeName(speName);
        if(StringUtil.isNotBlank(saveEval.getRecordFlow())){
            GeneralMethod.setRecordInfo(saveEval,false);
            baseevalMapper.updateByPrimaryKeySelective(saveEval);
        }else {
            recordFlow = PkUtil.getUUID();
            saveEval.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(saveEval,true);
            baseevalMapper.insertSelective(saveEval);
        }

        List<Map<String,Object>> scoresMapList = (List<Map<String,Object>>)dataMap.get("scores");
        if(scoresMapList!=null&&scoresMapList.size()>0){
            for(Map<String,Object> map:scoresMapList){
                String orderNumber = (String)map.get("orderNumber");
                String score = (String)map.get("score");
                String deductMarks = (String)map.get("deductMarks");
                String filePath = (String)map.get("filePath");
                ResBaseevalDetail saveDetail = new ResBaseevalDetail();
				saveDetail.setBaseevalFlow(recordFlow);
                saveDetail.setOrderNumber(orderNumber);
                saveDetail.setScore(score);
                saveDetail.setDeductMarks(deductMarks);
                saveDetail.setFilePath(filePath);

                ResBaseevalDetailExample example = new ResBaseevalDetailExample();
                ResBaseevalDetailExample.Criteria criteria = example.createCriteria().
                        andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andBaseevalFlowEqualTo(recordFlow);
                if(StringUtil.isNotBlank(orderNumber)){
                    criteria.andOrderNumberEqualTo(orderNumber);
                }
                List<ResBaseevalDetail> baseevalDetailList = baseevalDetailMapper.selectByExample(example);
                if(baseevalDetailList!=null&&baseevalDetailList.size()>0){
                    String flow = baseevalDetailList.get(0).getRecordFlow();
                    saveDetail.setRecordFlow(flow);
                }
                editBaseevalDetail(saveDetail);
            }
        }
        return 1;
    }

    @Override
    public List<ResBaseevalDetail> searchBaseevalDetail(ResBaseevalDetail baseevalDetail) {
        ResBaseevalDetailExample example = new ResBaseevalDetailExample();
        ResBaseevalDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(baseevalDetail.getBaseevalFlow())){
            criteria.andBaseevalFlowEqualTo(baseevalDetail.getBaseevalFlow());
        }
        return baseevalDetailMapper.selectByExample(example);
    }

    @Override
    public int editEvalCfgs(ResBaseevalFormCfg baseevalFormCfg, String jsonData) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(baseevalFormCfg.getTrainingSpeId())){
            baseevalFormCfg.setTrainingSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(baseevalFormCfg.getTrainingSpeId()));
        }
        Map<String,Object> dataMap = JSON.parseObject(jsonData);
        List<String> newFormFlows = (List<String>)dataMap.get("formFlows");

        List<ResBaseevalFormCfg> oldCfgs = searchCfg(baseevalFormCfg);
        List<String> oldFormFlows = new ArrayList<>();
        if(oldCfgs!=null&&oldCfgs.size()>0){
            for(ResBaseevalFormCfg oldCfg:oldCfgs){
                String oldFormFLow = oldCfg.getFormFlow();
                oldFormFlows.add(oldFormFLow);
                if(!newFormFlows.contains(oldFormFLow)){
                    ResBaseevalFormCfg delCfg = new ResBaseevalFormCfg();
                    delCfg.setRecordFlow(oldCfg.getRecordFlow());
                    delCfg.setRecordStatus("N");
                    editEvalCfg(delCfg);
                }
            }
        }

        if(newFormFlows!=null&&newFormFlows.size()>0){
            for(String formFLow:newFormFlows){
                if(!oldFormFlows.contains(formFLow)){
                    ResBaseevalFormCfg saveCfg = new ResBaseevalFormCfg();
                    ResBaseevalForm form = readForm(formFLow);
                    saveCfg.setFormName(form.getFormName());
                    saveCfg.setFormFlow(formFLow);
                    saveCfg.setOrgFlow(currentUser.getOrgFlow());
                    saveCfg.setTrainingSpeId(baseevalFormCfg.getTrainingSpeId());
                    saveCfg.setTrainingSpeName(baseevalFormCfg.getTrainingSpeName());
                    saveCfg.setRecordFlow(baseevalFormCfg.getRecordFlow());
                    editEvalCfg(saveCfg);
                }
            }
        }
        return 1;
    }
}
