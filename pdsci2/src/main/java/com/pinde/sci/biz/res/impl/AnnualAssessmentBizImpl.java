package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.enums.DiscipleStatusEnum;
import com.pinde.core.common.sci.dao.ResAnnualAssessmentMapper;
import com.pinde.core.model.ResAnnualAssessment;
import com.pinde.core.model.ResAnnualAssessmentExample;
import com.pinde.core.model.ResAnnualAssessmentWithBLOBs;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IAnnualAssessmentBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.res.AnnualAssessmentExtMapper;
import com.pinde.sci.model.res.AnnualAssessmentExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-19.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class AnnualAssessmentBizImpl implements IAnnualAssessmentBiz {

    @Autowired
    private AnnualAssessmentExtMapper assessmentExtMapper;
    @Autowired
    private ResAnnualAssessmentMapper assessmentMapper;

    @Override
    public AnnualAssessmentExt initAnnualAssessmentExt(Map<String,Object> paramMap) {
        String recordFlow = String.valueOf(paramMap.get("recordFlow")) ;
        String roleScope = String.valueOf(paramMap.get("roleScope")) ;
        if(StringUtil.isNotEmpty(recordFlow)&&!"doctor".equals(roleScope)){
            return assessmentExtMapper.initAssessmentExtByRecordFlow(paramMap);
        }
        return assessmentExtMapper.initAnnualAssessmentExt(paramMap);
    }

    @Override
    public List<ResAnnualAssessment> findAnnualAssessmentList(ResAnnualAssessment assessment,List<String> statusIdList) {
        ResAnnualAssessmentExample example = new ResAnnualAssessmentExample();
        ResAnnualAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(assessment.getDoctorFlow())) {
            criteria.andDoctorFlowEqualTo(assessment.getDoctorFlow());
        }
        if (StringUtil.isNotBlank(assessment.getTeacherFlow())) {
            criteria.andTeacherFlowEqualTo(assessment.getTeacherFlow());
        }
        if(StringUtil.isNotBlank(assessment.getAuditStatusId())){
            criteria.andAuditStatusIdEqualTo(assessment.getAuditStatusId());
        }
        if(statusIdList != null && statusIdList.size()>0){
            criteria.andAuditStatusIdIn(statusIdList);
        }
        if(StringUtil.isNotBlank(assessment.getRecordYear())){
            criteria.andRecordYearEqualTo(assessment.getRecordYear());
        }
        example.setOrderByClause("DOCTOR_NAME,CREATE_TIME DESC");
        return assessmentMapper.selectByExample(example);
    }

    @Override
    public int editAnnualAssessment(ResAnnualAssessmentWithBLOBs assessmentWithBLOBs) {

        if (StringUtil.isNotBlank(assessmentWithBLOBs.getRecordFlow())) {
            GeneralMethod.setRecordInfo(assessmentWithBLOBs, false);
            return assessmentMapper.updateByPrimaryKeySelective(assessmentWithBLOBs);
        } else {
            assessmentWithBLOBs.setCompleleSignTime(DateUtil.getCurrDateTime());
            assessmentWithBLOBs.setExperienceSignTime(DateUtil.getCurrDateTime());
            assessmentWithBLOBs.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(assessmentWithBLOBs, true);
            return assessmentMapper.insertSelective(assessmentWithBLOBs);
        }
    }

    @Override
    public int delAnnualAssessment(String recordFlow) {
        ResAnnualAssessmentWithBLOBs assessment = new ResAnnualAssessmentWithBLOBs();
        assessment.setRecordFlow(recordFlow);
        assessment.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
        return assessmentMapper.updateByPrimaryKeySelective(assessment);
    }

    /**
     * 年度考核一键审核
     * @param teacherFlow
     * @return
     */
    public int  oneKeyAudit(String teacherFlow){
        ResAnnualAssessmentExample example = new ResAnnualAssessmentExample();
        ResAnnualAssessmentWithBLOBs assessmentWithBLOBs = new ResAnnualAssessmentWithBLOBs();
        assessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.DiscipleAudit.getId());
        assessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.DiscipleAudit.getName());
        SysUser user= GlobalContext.getCurrentUser();
        assessmentWithBLOBs.setAuditUserFlow(user.getUserFlow());
        assessmentWithBLOBs.setAuditUserName(user.getUserName());
        assessmentWithBLOBs.setAuditTime(DateUtil.getCurrDateTime());
        GeneralMethod.setRecordInfo(assessmentWithBLOBs,false);
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andTeacherFlowEqualTo(teacherFlow).andAuditStatusIdEqualTo(DiscipleStatusEnum.Submit.getId());

        return  assessmentMapper.updateByExampleSelective(assessmentWithBLOBs,example);

    }
    public List<ResAnnualAssessment> findAnnualAssessmentByDocFlow(String doctorFlow){
        ResAnnualAssessmentExample example = new ResAnnualAssessmentExample();
        example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        return assessmentMapper.selectByExample(example);
    }
}
