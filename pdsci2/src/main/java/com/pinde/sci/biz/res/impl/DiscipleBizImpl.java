package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.sci.dao.ResDiscipleNoteInfoMapper;
import com.pinde.core.common.sci.dao.ResStudentDiscipleTeacherMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IDiscipleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.res.DiscipleDoctorExtMapper;
import com.pinde.sci.model.res.ResDoctorDiscioleExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-12.
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class DiscipleBizImpl implements IDiscipleBiz {
    @Autowired
    private ResStudentDiscipleTeacherMapper teacherMapper;
    @Autowired
    private ResDiscipleNoteInfoMapper noteInfoMapper;
    @Autowired
    private DiscipleDoctorExtMapper doctorExtMapper;
    @Override
    public void saveStudentDiscipleTeacher(ResStudentDiscipleTeacher studentDiscipleTeacher) {
        if(StringUtil.isNotBlank(studentDiscipleTeacher.getRecordFlow())){
            GeneralMethod.setRecordInfo(studentDiscipleTeacher, false);
            teacherMapper.updateByPrimaryKeySelective(studentDiscipleTeacher);
        }else{
            studentDiscipleTeacher.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(studentDiscipleTeacher, true);
            teacherMapper.insertSelective(studentDiscipleTeacher);
        }
    }

    @Override
    public boolean searchStudentDiscipleTeacher(String discipleFlow,String doctorFlow) {
        ResStudentDiscipleTeacherExample example=new ResStudentDiscipleTeacherExample();
        ResStudentDiscipleTeacherExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andTeacherFlowEqualTo(discipleFlow).andDoctorFlowEqualTo(doctorFlow);
        List<ResStudentDiscipleTeacher> studentDiscipleTeachers = teacherMapper.selectByExample(example);
        return studentDiscipleTeachers != null && studentDiscipleTeachers.size() > 0;
    }

    @Override
    public List<ResDiscipleNoteInfo> findResDiscipleNoteInfo(ResDiscipleNoteInfo discipleNoteInfo, List<String> auditStatusList) {
        ResDiscipleNoteInfoExample example = new ResDiscipleNoteInfoExample();
        ResDiscipleNoteInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(discipleNoteInfo.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(discipleNoteInfo.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(discipleNoteInfo.getTeacherFlow())){
            criteria.andTeacherFlowEqualTo(discipleNoteInfo.getTeacherFlow());
        }
        if(StringUtil.isNotBlank(discipleNoteInfo.getStudyTimeId())){
            criteria.andStudyTimeIdEqualTo(discipleNoteInfo.getStudyTimeId());
        }
        if(StringUtil.isNotBlank(discipleNoteInfo.getNoteTypeId())){
            criteria.andNoteTypeIdEqualTo(discipleNoteInfo.getNoteTypeId());
        }
        if(StringUtil.isNotBlank(discipleNoteInfo.getAuditStatusId())){
            criteria.andAuditStatusIdEqualTo(discipleNoteInfo.getAuditStatusId());
        }
        if(StringUtil.isNotBlank(discipleNoteInfo.getStudyStartDate())){
            criteria.andStudyStartDateEqualTo(discipleNoteInfo.getStudyStartDate());
        }
        if(null != auditStatusList && auditStatusList.size()>0){
            criteria.andAuditStatusIdIn(auditStatusList);
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return noteInfoMapper.selectByExample(example);
    }

    @Override
    public ResDiscipleNoteInfoWithBLOBs findResDiscipleNoteInfoWithBLOBs(String recordFlow) {
        return noteInfoMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int updateResDiscipleNoteInfoWithBLOBs(ResDiscipleNoteInfoWithBLOBs discipleNoteInfo) {
        if(StringUtil.isNotBlank(discipleNoteInfo.getRecordFlow())){
            GeneralMethod.setRecordInfo(discipleNoteInfo, false);
            return noteInfoMapper.updateByPrimaryKeySelective(discipleNoteInfo);
        }else {
            discipleNoteInfo.setStudentSignTime(DateUtil.getCurrDateTime());
            discipleNoteInfo.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(discipleNoteInfo, true);
            return noteInfoMapper.insertSelective(discipleNoteInfo);
        }
    }

    @Override
    public int delResDiscipleNoteInfo(String recordFlow) {
        ResDiscipleNoteInfo resDiscipleNoteInfo = new ResDiscipleNoteInfo();
        resDiscipleNoteInfo.setRecordFlow(recordFlow);
        resDiscipleNoteInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
        return noteInfoMapper.updateByPrimaryKey(resDiscipleNoteInfo);
    }

    @Override
    public List<ResDoctorDiscioleExt> searchAuditDoctorList(Map<String, Object> map) {

        return doctorExtMapper.auditDoctorList(map);
    }

    @Override
    public int agreeRecordBatch(Map<String, String> paramMap) {
        return doctorExtMapper.agreeRecordBatch(paramMap);
    }

    public  int oneKeyAuditByOrg(String orgFlow ,String userFlow){
        int record = 0;
        //-- 跟师记录表
        record += doctorExtMapper.oneKeyAuditDiscipleRecordInfoByOrg( orgFlow,  userFlow);
        //-- 跟师学习笔记表
        record += doctorExtMapper.oneKeyAuditDiscipleNoteInfoByOrg( orgFlow,  userFlow);
        //-- 跟师医案表
        record += doctorExtMapper.oneKeyAuditTypicalClassByOrg( orgFlow,  userFlow);
        //-- 年度考核表
//        record += doctorExtMapper.oneKeyAuditAnnualAssessmentByOrg( orgFlow,  userFlow);
        return record ;
    }
}
