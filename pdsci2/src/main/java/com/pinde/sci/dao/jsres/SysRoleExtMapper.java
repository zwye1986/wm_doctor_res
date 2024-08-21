package com.pinde.sci.dao.jsres;

import com.pinde.sci.model.mo.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysRoleExtMapper {
	/**
	 * @Department：研发部
	 * @Date 2020/11/18
	 */
	List<SysUserRole> getByUserFlow(Map<String, Object> paramMap);
}
