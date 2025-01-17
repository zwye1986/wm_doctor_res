package com.pinde.sci.dao.res;

import com.pinde.core.model.ResDoctorDiscioleExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-20.
 */
public interface DiscipleDoctorExtMapper {

    List<ResDoctorDiscioleExt> auditDoctorList(Map<String,Object> map);

    int agreeRecordBatch(Map<String, String> paramMap);

    int agreeTypicalCasesBatch(Map<String, String> paramMap);

    //-- 跟师记录表
    int oneKeyAuditDiscipleRecordInfoByOrg(@Param("orgFlow") String orgFlow,@Param("userFlow") String userFlow);
    //-- 跟师学习笔记表
    int oneKeyAuditDiscipleNoteInfoByOrg(@Param("orgFlow") String orgFlow,@Param("userFlow") String userFlow);
    //-- 跟师医案表
    int oneKeyAuditTypicalClassByOrg(@Param("orgFlow") String orgFlow,@Param("userFlow") String userFlow);
    //-- 年度考核表
    int oneKeyAuditAnnualAssessmentByOrg(@Param("orgFlow") String orgFlow, @Param("userFlow") String userFlow);

}
