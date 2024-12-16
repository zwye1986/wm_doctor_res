package com.pinde.sci.biz.jszy.impl;

import com.pinde.core.model.JsresRecruitDocInfo;
import com.pinde.core.model.JsresRecruitDocInfoExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyDoctorReductionBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.JsresRecruitDocInfoMapper;
import com.pinde.sci.dao.base.ResDoctorMapper;
import com.pinde.sci.dao.base.ResDoctorRecruitMapper;
import com.pinde.sci.dao.base.ResDoctorReductionMapper;
import com.pinde.sci.dao.jszy.JszyResReductionExtMapper;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorReduction;
import com.pinde.sci.model.mo.ResDoctorReductionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/11/28.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class JszyDoctorReductionBizImpl implements IJszyDoctorReductionBiz {

    @Autowired
    private ResDoctorReductionMapper reductionMapper;
    @Autowired
    private JszyResReductionExtMapper resReductionExtMapper;
    @Autowired
    private ResDoctorRecruitMapper recruitMapper;
    @Autowired
    private ResDoctorMapper resDoctorMapper;
    @Autowired
    private JsresRecruitDocInfoMapper   jsresRecruitDocInfoMapper;

    @Override
    public ResDoctorReduction findReductionByRecruitFlow(String recruitFlow) {
        ResDoctorReductionExample example = new ResDoctorReductionExample();
        ResDoctorReductionExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecruitFlowEqualTo(recruitFlow);
        List<ResDoctorReduction> reductionList = reductionMapper.selectByExample(example);
        if (reductionList != null && reductionList.size() > 0) {
            return reductionList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<ResDoctorReduction> findReductionByMap(Map<String, Object> paramMap) {
        ResDoctorReductionExample example = new ResDoctorReductionExample();
        ResDoctorReductionExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorReduction> reductionList = reductionMapper.selectByExample(example);
        return reductionList;
    }

    @Override
    public int edit(ResDoctorReduction resDoctorReduction) {
        if (StringUtil.isNotBlank(resDoctorReduction.getRecordFlow())) {
            GeneralMethod.setRecordInfo(resDoctorReduction, false);
            return reductionMapper.updateByPrimaryKeySelective(resDoctorReduction);
        } else {
            resDoctorReduction.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(resDoctorReduction, true);
            return reductionMapper.insertSelective(resDoctorReduction);
        }
    }

    @Override
    public ResDoctorReduction findReductionByPK(String recordFlow) {
        return reductionMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<Map<String, String>> findReductionExtByMap(Map<String, Object> paramMap) {
        return resReductionExtMapper.findReductionExtByMap(paramMap);
    }

    @Override
    public List<Map<String, String>> findReductionExtByMapAcc(Map<String, Object> paramMap) {
        return resReductionExtMapper.findReductionExtByMapAcc(paramMap);
    }

    @Override
    public int updateRecruitAndDoctorInfo(ResDoctorReduction reduction) {
        if (reduction != null) {
            com.pinde.core.model.ResDoctorRecruit recruit = recruitMapper.selectByPrimaryKey(reduction.getRecruitFlow());
            String afterReduceTrainYear = reduction.getAfterReduceTrainYear();
            if (recruit != null) {
                String sessionNumber = recruit.getSessionNumber();
                Integer graduationYear = Integer.parseInt(sessionNumber) + Integer.parseInt(afterReduceTrainYear);
                recruit.setTrainYear(afterReduceTrainYear);
                recruit.setGraduationYear(graduationYear + "");
                recruitMapper.updateByPrimaryKey(recruit);
                JsresRecruitDocInfoExample example = new JsresRecruitDocInfoExample();
                JsresRecruitDocInfoExample.Criteria criteria = example.createCriteria();
                criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
                criteria.andSessionNumberEqualTo(recruit.getSessionNumber());
                List<JsresRecruitDocInfo> jsresRecruitDocInfos = jsresRecruitDocInfoMapper.selectByExample(example);
                if(jsresRecruitDocInfos !=null && jsresRecruitDocInfos.size()>0) {
                    JsresRecruitDocInfo docInfo = jsresRecruitDocInfos.get(0);
                    docInfo.setTrainYear(afterReduceTrainYear);
                    docInfo.setGraduationYear(graduationYear + "");
                    jsresRecruitDocInfoMapper.updateByPrimaryKey(docInfo);
                }
            }
            ResDoctor resDoctor = resDoctorMapper.selectByPrimaryKey(reduction.getDoctorFlow());
            if (resDoctor != null) {
                resDoctor.setTrainingYears(afterReduceTrainYear);
                resDoctorMapper.updateByPrimaryKey(resDoctor);
            }
        }
        return 1;
    }

    @Override
    public ResDoctorReduction findReductionByRecruitFlowandStatusId(String recruitFlow, String auditStatusId) {
        ResDoctorReductionExample example = new ResDoctorReductionExample();
        ResDoctorReductionExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(recruitFlow)){
            criteria.andRecruitFlowEqualTo(recruitFlow);
        }
        if (StringUtil.isNotBlank(auditStatusId)){
            criteria.andAuditStatusIdEqualTo(auditStatusId);
        }
        List<ResDoctorReduction> reductionList = reductionMapper.selectByExample(example);
        if (reductionList != null && reductionList.size() > 0) {
            return reductionList.get(0);
        } else {
            return null;
        }
    }

}
