package com.pinde.res.dao.hbres.ext;

import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SchDeptRel;
import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchDeptExtMapper {
	/**
	 * 根据角色和轮转科室获取用户信息
	 * @param paramMap
	 * @return
     */
	List<SysUser> getUserListBySchDept(Map<String,Object> paramMap);
	/*=====================产品学员APP优化（首页） begin===================*/
	List<Map<String, Object>> searchSchRotationDept(Map<String,Object> paramMap);
	/*=====================产品学员APP优化（首页） end===================*/
	List<SchDept> searchrotationDept(@Param(value="userFlow")String userFlow);

	List<SchDeptRel> searchRelByStandard(@Param("orgFlow") String orgFlow, @Param("standardDeptId") String standardDeptId, @Param("deptName") String deptName);
}
