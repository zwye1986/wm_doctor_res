package com.pinde.res.biz.gzdh;

import com.pinde.sci.model.mo.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IGzdhKzrBiz {

    List<Map<String, String>> schDoctorSchProcessHead(Map<String, String> schArrangeResultMap);

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

    List<Map<String,String>> schDoctorSchProcessHeadUserList(Map<String, String> schArrangeResultMap);

    int schDoctorSchProcessHeadCount(Map<String, String> schArrangeResultMap);

    int findNeedAuditAbsences(SysUser userinfo);

    List<Map<String,String>> getAbsenceList(Map<String, String> schArrangeResultMap);

    SchDoctorAbsence readAbsence(String absenceFlow);

    int saveAuditAbsence(SchDoctorAbsence absence) throws ParseException;

    int saveAuditRepealAbsence(SchDoctorAbsence absence) throws ParseException;

    Map<String,Object> absenceCountDetail(String startDate, String endDate, String flow);

    int absenceCount(String startDate, String endDate, String flow);

    List<Map<String,Object>> getCycleStudents(String userFlow, String stuName, String sessionNumber, String speId, String schDeptFlow);

    List<Map<String,Object>> docSchDeptList(String userFlow, String doctorFlow);

    List<SchDept> readSchDeptList(String userFlow);
}