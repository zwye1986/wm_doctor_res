package com.pinde.res.biz.eval;

import com.pinde.core.model.SysUser;
import com.pinde.core.model.SysUserRole;

import java.util.List;

public interface IEvalAppBiz {
	/**
	 * 读取一条用户信息
	 * @param userFlow
	 * @return
     */
	SysUser readSysUser(String userFlow);

	/**
	 * 获取该用户的所有角色
	 * @param userFlow
	 * @return
	 */
	List<SysUserRole> getSysUserRole(String userFlow);

	/**
	 * 获取配置
	 * @param code
	 * @return
	 */
	String getCfgByCode(String code);


}
  