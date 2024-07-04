package com.pinde.res.biz.nfykdx;

import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface INfykdxKzrBiz {

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
}