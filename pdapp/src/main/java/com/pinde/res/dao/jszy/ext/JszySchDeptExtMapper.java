package com.pinde.res.dao.jszy.ext;

import com.pinde.sci.model.mo.SchDeptRel;
import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JszySchDeptExtMapper {
	/**
	 * 根据角色和轮转科室获取用户信息
	 * @param paramMap
	 * @return
     */
	List<SysUser> getUserListBySchDept(Map<String,Object> paramMap);

	List<SchDeptRel> searchRelByStandard(@Param("orgFlow") String orgFlow, @Param("standardDeptId") String standardDeptId, @Param("deptName") String deptName);
}
