package com.pinde.res.biz.jswjw;

import com.pinde.core.model.SysUser;

import java.util.List;
import java.util.Map;

public interface IJswjwKzrBiz {

    List<Map<String,String>> schDoctorSchProcessHead(Map<String, String> schArrangeResultMap);
    /**
     * 根据角色和deptFlow获取user
     *
     * @param deptFlow
     * @return
     */
    List<SysUser> teacherRoleCheckUser(String deptFlow, String role, String teaName, String userFlow);

    List<Map<String,String>> deptUsers(Map<String, String> param);

    List<Map<String,String>> deptTeacherUsers(Map<String, String> param);

    int deptUsersDocCount(Map<String, String> param);

    List<SysUser> readDeptTeachAndHead(String deptFlow, String teacherRoleFlow, String headRoleFlow);

    int schDoctorSchProcessHeadCount(Map<String, String> param);

    List<Map<String,String>> schDoctorSchProcessHeadUserList(Map<String, String> schArrangeResultMap);

    List<Map<String,String>> deptTeacherDocList(Map<String, String> param);

    List<Map<String,String>> attendList(Map<String, String> schArrangeResultMap);

    int deptUsersCount(Map<String, String> param);

    List<Map<String,String>> afterUserList(Map<String, String> schArrangeResultMap);

    List<Map<String,String>> temporaryOutAuditList(Map<String, String> schArrangeResultMap);

    List<Map<String,String>> temporaryOutList(Map<String, String> schArrangeResultMap);

    List<Map<String,String>> afterAuditList(Map<String, String> schArrangeResultMap);
}
  