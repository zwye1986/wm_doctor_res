package com.pinde.sci.dao.sys;

import com.pinde.sci.form.sys.SysUserForm;
import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserExtMapper {
	List<SysUser> selectIrbUserByForm(SysUserForm form);

	List<SysUser> selectResUserByForm(SysUserForm form);
	
	List<SysUser> searchUserListByOrgFlow(Map<String, Object> paramMap);

	/**
	 * 查询指定机构的某一角色人员(已激活的用户)
	 * @param paramMap
	 * @return
	 */
	List<SysUser> selectUserByOrgFlowAndRoleFlow(Map<String, Object> paramMap);
	List<SysUser> selectTeacherUserByOrgFlowAndRoleFlow(Map<String, Object> paramMap);
	List<SysUser> selectHeadUserByOrgFlowAndRoleFlow(Map<String, Object> paramMap);

//	List<SysUser> selectUserByRoleFlow(Map<String, Object> paramMap);
	
	List<SysUser> searchUserByRoleAndOrgFlows(@Param(value="roleFlow")String roleFlow,@Param(value="orgFlows")List<String> orgFlows);
	/**
	 * 查询拥有某个菜单功能的用户
	 * @param paramMap
	 * @return
	 */
	List<SysUser> selectUserByMenuId(Map<String, Object> paramMap);

	List<SysUser> searchResManageUser(Map<String, Object> map);
	
	/**
	 * 根据科室查出所有属于该科室的人员(包括多部门)
	 * @param deptFlow
	 * @return
	 */
	List<SysUser> searchUserByDeptAndRole(@Param(value="deptFlow")String deptFlow,@Param(value="roleFlow")String roleFlow);
	
	/**
	 * 查询当前带教或科主任的待出科人员
	 * @param process
	 * @param user
	 * @return
	 */
//	List<SysUser> searchAfterAuditUser(@Param(value="process")ResDoctorSchProcess process,@Param(value="user")SysUser user);
	
	/**
	 * 锁定整月未登记数据的学员
	 * @param month
	 * @return
	 */
	int lockHaveNotRegOverMonth(@Param(value="month")String month);
	
	List<SysUser> teacherRoleCheckUser(Map<String, String> map);
	
	
	/**
	 * 根据rec和process的情况获取用户
	 * @param map
	 * @return
	 */
	List<SysUser> getUserByRec(Map<String,Object> map);

	List<SysUser> getUserByRecForUni(Map<String, Object> paramMap);

	List<SysUser> searchResManageUserNotSelf(Map<String, Object> map);

	//根据各项条件以及角色ID查询用户
	List<SysUser> searchUserWithRole(Map<String, Object> map);

	List<SysUser> searchUserWithRoleByJx(Map<String, Object> map);
	List<SysUser> searchUserWithRoleByJx2(Map<String, Object> map);

	List<Map<String,Object>> getGateUserList(Map<String, Object> params);

	Map<String,Object> getUserRoles(@Param("userFlow") String userFlow);

	void deleteGateRole(@Param("user") SysUser user, @Param("roleFlows") String[] roleFlows);

	List<SysUser> readDeptTeachAndHead(@Param("deptFlow") String deptFlow, @Param("roles") List<String> roles);
	//定期提醒更新密码
	void updateTipPassword();
	//南医大学生角色学籍信息
	List<Map<String,Object>> queryEduUserList(@Param("stuRole") String stuRole);

	List<SysUser> queryTeaList(@Param("examTeaRole") String examTeaRole, @Param("orgFlow") String orgFlow);

	List<SysUser> searchOtherUserByDeptAndRole(@Param("orgFlow") String orgFlow, @Param("deptFlow") String deptFlow, @Param("roleFlow") String roleFlow);

	List<Map<String,Object>> getPortalUserList(Map<String, Object> params);

	void deletePortalRole(@Param("user") SysUser user, @Param("roleFlows") String[] roleFlows);

	List<SysUser> readOrgTeas(@Param("orgFlow") String orgFlow, @Param("roles") List<String> roles);

	List<SysUser> readUserBySpe(@Param("deptFlow") String deptFlow, @Param("roleFlow") String roleFlow, @Param("resTrainingSpeId") String resTrainingSpeId, @Param("orgFlow") String orgFlow);

	List<SysUser> findByNotBlackIdNo(@Param("idNo") String idNo);

    List<SysUser> searchRecruitManagers(Map<String, Object> map);

    int updateTeaSubmit(List<String> userFlowList);

    int updateCheckAll(Map<String, Object> param);

	int updateTeaNotSubmit(List<String> userFlowList);

	List<SysUser> searchUserList(Map<String,Object> paramMap);
}
