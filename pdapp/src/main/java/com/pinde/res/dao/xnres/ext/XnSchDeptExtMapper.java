package com.pinde.res.dao.xnres.ext;

import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface XnSchDeptExtMapper {
	/**
	 * 根据角色和轮转科室获取用户信息
	 * @param paramMap
	 * @return
     */
	List<SysUser> getUserListBySchDept(Map<String,Object> paramMap);
}
