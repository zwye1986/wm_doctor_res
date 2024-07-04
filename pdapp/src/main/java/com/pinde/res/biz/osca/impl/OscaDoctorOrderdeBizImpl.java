package com.pinde.res.biz.osca.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.osca.IOscaDoctorOrderdeBiz;
import com.pinde.res.dao.jswjw.ext.OscaSubjectMainExtMapper;
import com.pinde.res.dao.osca.ext.OscaSkillsAssessmentExtMapper;
import com.pinde.res.enums.osca.AuditStatusEnum;
import com.pinde.res.model.osca.mo.OscaSkillsAssessmentExt;
import com.pinde.sci.dao.base.OscaDoctorAssessmentMapper;
import com.pinde.sci.dao.base.OscaSkillsAssessmentMapper;
import com.pinde.sci.dao.base.OscaSkillsAssessmentTimeMapper;
import com.pinde.sci.dao.base.ResScoreMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OscaDoctorOrderdeBizImpl implements IOscaDoctorOrderdeBiz {
    @Autowired
    private OscaSkillsAssessmentExtMapper oscaSkillsAssessmentExtMapper;
    @Autowired
    private OscaDoctorAssessmentMapper oscaDoctorAssessmentMapper;
    @Autowired
    private OscaSkillsAssessmentMapper oscaSkillsAssessmentMapper;
    @Autowired
    private ResScoreMapper resScoreMapper;
    @Autowired
    private OscaSkillsAssessmentTimeMapper timeMapper;
    @Autowired
    private OscaSubjectMainExtMapper subjectMainExtMapper;

    @Override
    public List<OscaSkillsAssessmentExt> skillsAssessmentList(Map<String, Object> map) {
        return oscaSkillsAssessmentExtMapper.searchAllSkillsAssessments(map);
    }

    @Override
    public int insertDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment) {
        return oscaDoctorAssessmentMapper.insertSelective(oscaDoctorAssessment);
    }

    @Override
    public int updateDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment) {
        OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
        OscaDoctorAssessmentExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(oscaDoctorAssessment.getRecordFlow())){
            criteria.andRecordFlowEqualTo(oscaDoctorAssessment.getRecordFlow());
        }
        if(StringUtil.isNotBlank(oscaDoctorAssessment.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(oscaDoctorAssessment.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(oscaDoctorAssessment.getClinicalFlow())){
            criteria.andClinicalFlowEqualTo(oscaDoctorAssessment.getClinicalFlow());
        }
        return oscaDoctorAssessmentMapper.updateByExample(oscaDoctorAssessment,example);
    }

    @Override
    public List<OscaDoctorAssessment> selectDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment) {
        OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
        OscaDoctorAssessmentExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(oscaDoctorAssessment.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(oscaDoctorAssessment.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(oscaDoctorAssessment.getClinicalFlow())){
            criteria.andClinicalFlowEqualTo(oscaDoctorAssessment.getClinicalFlow());
        }
        if(StringUtil.isNotBlank(oscaDoctorAssessment.getRecordFlow())){
            criteria.andRecordFlowEqualTo(oscaDoctorAssessment.getRecordFlow());
        }
        if(StringUtil.isNotBlank(oscaDoctorAssessment.getAuditStatusId())){
            criteria.andAuditStatusIdEqualTo(oscaDoctorAssessment.getAuditStatusId());
        }
        return oscaDoctorAssessmentMapper.selectByExample(example);
    }

    @Override
    public List<OscaSubjectStation> searchSubjectsForDoctor(String clinicalFlow) {
        return subjectMainExtMapper.searchSubjectsForDoctor(clinicalFlow);
    }

    @Override
    public int countDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment) {
        List<String> args = new ArrayList<>();
        args.add(AuditStatusEnum.Passing.getId());
        args.add(AuditStatusEnum.Passed.getId());
        OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
        OscaDoctorAssessmentExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andAuditStatusIdIn(args);
        if(StringUtil.isNotBlank(oscaDoctorAssessment.getClinicalFlow())){
            criteria.andClinicalFlowEqualTo(oscaDoctorAssessment.getClinicalFlow());
        }
        return oscaDoctorAssessmentMapper.countByExample(example);
    }

    @Override
    public List<OscaSkillsAssessmentExt> selectDoctorAssessmentInfo(Map<String, String> map) {
        return oscaSkillsAssessmentExtMapper.searchDoctorAssessment(map);
    }

    @Override
    public List<OscaSkillsAssessmentExt> selectTicketInfo(Map<String, String> map) {
        return oscaSkillsAssessmentExtMapper.searchTicketInfo(map);
    }

    @Override
    public OscaSkillsAssessment selectSkillsAssessmentByClinicalFlow(String clinicalFlow) {
        return oscaSkillsAssessmentMapper.selectByPrimaryKey(clinicalFlow);
    }

    @Override
    public OscaDoctorAssessment selectDoctorAssessmentByRecordFlow(String recordFlow) {
        return oscaDoctorAssessmentMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<OscaSkillsAssessmentExt> selectAssessmentIsNotLocalOneYear(Map<String, String> map) {
        return oscaSkillsAssessmentExtMapper.searchAssessmentIsNotLocalOneYear(map);
    }

    @Override
    public List<OscaSkillsAssessmentExt> selectDoctorAssessmentList(Map<String, String> map) {
        return oscaSkillsAssessmentExtMapper.searchDoctorAssessmentList(map);
    }

    @Override
    public int countOrderedTime(Map<String, String> map) {
        return oscaSkillsAssessmentExtMapper.countOrderedTime(map);
    }

    @Override
    public List<ResDoctorRecruit> selectDoctorGraduationYear(String doctorFlow) {
        return oscaSkillsAssessmentExtMapper.searchDoctorGraduationYear(doctorFlow);
    }

    @Override
    public List<ResScore> selectResScore(String doctorFlow, String year) {
        ResScoreExample example=new ResScoreExample();
        ResScoreExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andScoreTypeIdEqualTo("TheoryScore");
        if(StringUtil.isNotBlank(doctorFlow)){
            criteria.andDoctorFlowEqualTo(doctorFlow);
        }
        if(StringUtil.isNotBlank(year)){
            criteria.andScorePhaseIdEqualTo(year);
        }
        return resScoreMapper.selectByExample(example);
    }

    @Override
    public List<String> searchTrainingSpeList(Map<String, Object> map) {
        return oscaSkillsAssessmentExtMapper.searchTrainingSpeList(map);
    }

    @Override
    public List<OscaSkillsAssessmentTime> searchCheckTime(String clinicalFlow) {
        OscaSkillsAssessmentTimeExample example=new OscaSkillsAssessmentTimeExample();
        OscaSkillsAssessmentTimeExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(clinicalFlow)){
            criteria.andClinicalFlowEqualTo(clinicalFlow);
        }
        return timeMapper.selectByExample(example);
    }

    @Override
    public List<OscaSubjectStation> getStations(Map<String, Object> paramMap) {
        return oscaSkillsAssessmentExtMapper.getStations(paramMap);
    }
}
