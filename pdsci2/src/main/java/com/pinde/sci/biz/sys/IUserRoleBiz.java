package com.pinde.sci.biz.sys;

import com.pinde.core.model.SysRole;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.SysUserRole;

import java.util.List;

public interface IUserRoleBiz {

	List<SysUserRole> getByUserFlow(String userFlow);

	List<SysUserRole> getByOrgFlow(String orgFlow, String wsid);

	List<SysUserRole> getByUserFlow(List<String> userFlows, String wsid);

	void saveAllot(String userFlow, String orgFlow, String workStationId, String[] roleFlows);

	List<SysUserRole> searchUserRole(SysUserRole userRole);

	int saveSysUserRole(SysUserRole userRole);


	int saveSysUserRole(String userFlow, String roleFlow, String wsId);

	/**
	 * 根据用户和角色获取当前用户
	 * @param userFlow
	 * @param roleFlow
	 * @return
	 */
	SysUserRole readUserRole(String userFlow, String roleFlow);

	int addSysUserRole(SysUserRole userRole);

	/**
	 * 查询某用户某工作站下所有角色
	 */
	List<SysUserRole> getByUserFlowAndWsid(String userFlow,String wsid);

	/**
	 * 根据主键读取一条sysRole
	 */
	SysRole read(String roleFlow);

	/**
	 * 用户是否存在权限
	 * @return
     */
	boolean userExistRole(SysUser sysUser,SysRole sysRole);

    SysRole getByRoleName(String roleName);

	void batchUpdateUserRoles(String userFlow, String wsId, List<String> userRoles, List<String> roleRanges);
}
