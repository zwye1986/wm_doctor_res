package com.pinde.sci.biz.osca.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaDoctorScoreBiz;
import com.pinde.sci.dao.base.OscaDoctorAssessmentMapper;
import com.pinde.sci.dao.base.OscaSkillsAssessmentMapper;
import com.pinde.sci.dao.base.OscaSubjectStationMapper;
import com.pinde.sci.dao.osca.OscaDoctorAssessmentExtMapper;
import com.pinde.sci.dao.osca.OscaDoctorScoreInfoMapper;
import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.core.common.enums.osca.SignStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaDoctorScoreBizImpl implements IOscaDoctorScoreBiz{
    @Autowired
    private OscaSkillsAssessmentMapper oscaSkillsAssessmentMapper;
    @Autowired
    private OscaDoctorAssessmentMapper oscaDoctorAssessmentMapper;
    @Autowired
    private OscaDoctorAssessmentExtMapper oscaDoctorAssessmentExtMapper;
    @Autowired
    private OscaDoctorScoreInfoMapper oscaDoctorScoreInfoMapper;
    @Autowired
    private OscaSubjectStationMapper oscaSubjectStationMapper;
    @Override
    public List<OscaDoctorAssessment> queryDoctorList(String clinicalFlow) {
        OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
        OscaDoctorAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andClinicalFlowEqualTo(clinicalFlow)
                .andAuditStatusIdEqualTo(AuditStatusEnum.Passed.getId())
                .andSiginStatusIdEqualTo(SignStatusEnum.SignIn.getId());
//        if(StringUtil.isNotBlank(clinicalFlow)){
//            example.createCriteria().andClinicalFlowEqualTo(clinicalFlow);
//        }
        return oscaDoctorAssessmentMapper.selectByExample(example);
    }

    @Override
    public List<OscaSkillsAssessment> selectskillsAssessmentList(OscaSkillsAssessment oscaSkillsAssessment) {
        OscaSkillsAssessmentExample example=new OscaSkillsAssessmentExample();
        OscaSkillsAssessmentExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getOrgName())){
            criteria.andOrgNameLike(oscaSkillsAssessment.getOrgName());
        }
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getClinicalYear())){
            criteria.andClinicalYearEqualTo(oscaSkillsAssessment.getClinicalYear());
        }
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getSpeId())){
            criteria.andSpeIdEqualTo(oscaSkillsAssessment.getSpeId());
        }
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getOrgFlow())){
            criteria.andOrgFlowEqualTo(oscaSkillsAssessment.getOrgFlow());
        }
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getSubjectFlow())){
            criteria.andSubjectFlowEqualTo(oscaSkillsAssessment.getSubjectFlow());
        }
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getActionTypeId())){
            criteria.andActionTypeIdEqualTo(oscaSkillsAssessment.getActionTypeId());
        }
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getIsLocal())){
            criteria.andIsLocalEqualTo(oscaSkillsAssessment.getIsLocal());
        }
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getIsReleased())){
            criteria.andIsReleasedEqualTo(oscaSkillsAssessment.getIsReleased());
        }
        if(StringUtil.isNotBlank(oscaSkillsAssessment.getIsGradeReleased())){
            criteria.andIsGradeReleasedNotEqualTo(oscaSkillsAssessment.getIsGradeReleased());
        }
        return oscaSkillsAssessmentMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> queryGradeList(Map<String, Object> map) {
        return oscaDoctorScoreInfoMapper.selectGradeList(map);
    }

    @Override
    public List<OscaSubjectStation> queryStationList(String subjectFlow) {
        OscaSubjectStationExample example = new OscaSubjectStationExample();
        example.createCriteria().andSubjectFlowEqualTo(subjectFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL");
        return oscaSubjectStationMapper.selectByExample(example);
    }

    @Override
    public OscaSkillsAssessment queryDataByFlow(String clinicalFlow) {
        return oscaSkillsAssessmentMapper.selectByPrimaryKey(clinicalFlow);
    }

    @Override
    public String getPassPercent(Map<String,Object> paramMap1,Map<String,Object> paramMap2) {
        int amuountPass = oscaDoctorAssessmentExtMapper.getStudentNumber(paramMap1);
        int amuountAll = oscaDoctorAssessmentExtMapper.getStudentNumber(paramMap2);
        if(amuountAll==0){
            return "";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format((double)amuountPass/amuountAll*100);
    }
    @Override
    public List<Map<String, Object>> queryAllGradeList(Map<String, Object> map) {
        return oscaDoctorScoreInfoMapper.selectAllGradeList(map);
    }

    @Override
    public List<Map<String, Object>> getSingleGrade(Map<String, Object> paramMap) {
        return oscaDoctorScoreInfoMapper.getSingleGrade(paramMap);
    }
}
