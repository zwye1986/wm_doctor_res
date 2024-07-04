package com.pinde.res.biz.gzdh;

import com.pinde.sci.model.mo.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IGzdhSpeBiz {

    List<Map<String, String>> schDoctorSchProcessSpe(Map<String, String> schArrangeResultMap);

    //根据角色和deptFlow获取user
    List<SysUser> teacherRoleCheckUser(String deptFlow, String role, String teaName, String userFlow);

    List<ResDoctorSchProcess> searchProcessByDoctorNew(Map<String, Object> paramMap);

    List<SysUser> searchSysUserByuserFlows(List<String> userFlows);

    List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows);

    Map<String, String> parseRecContent(String content);

    ResScore getScoreByProcess(String processFlow);

    ResDoctorSchProcess read(String processFlow);

    SchDept readSchDept(String schDeptFlow);

    int schProcessStudentDistinctQuery(String deptFlow, String userFlow, String isOpen);

    void auditDate(String userFlow, String dataFlow);

    ResDoctorSchProcess searchByResultFlow(String resultFlow);

    ResRec queryResRec(String processFlow, String operUserFlow, String recTypeId);

    int schDoctorSchProcessSpeCount(Map<String, String> schArrangeResultMap);

    int findNeedAuditAbsences(SysUser userinfo);

    List<Map<String,String>> getSpeAbsenceList(Map<String, String> schArrangeResultMap);

    SchDoctorAbsence readAbsence(String absenceFlow);

    int saveAuditAbsence(SchDoctorAbsence absence) throws ParseException;

    int saveAuditRepealAbsence(SchDoctorAbsence absence) throws ParseException;

    Map<String,Object> absenceCountDetail(String startDate, String endDate, String flow);

    int absenceCount(String startDate, String endDate, String flow);

    List<Map<String,String>> schDoctorSchProcessSpeUserList(Map<String, String> schArrangeResultMap);

    List<Map<String,String>> teacherRoleCheckSpeUser(String cfgTeacher, String teaName, String userFlow, String deptFlow, String orderBy);

    Map<String,String> readDocTypeMap(String userFlow, String sessionNumber);

    Map<String,String> readDocEducationMap(String userFlow, String sessionNumber);

    Map<String,String> readSessionNumberDocMap(String userFlow);

    List<Map<String,String>> readTeacherStatistics(String userFlow, String roleFlow);

    List<Map<String,String>> teacherList(Map<String, String> schArrangeResultMap);

}