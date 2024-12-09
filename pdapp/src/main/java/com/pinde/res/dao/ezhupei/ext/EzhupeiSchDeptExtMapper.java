package com.pinde.res.dao.ezhupei.ext;

import com.pinde.core.model.SchDept;
import com.pinde.core.model.SchDeptRel;
import com.pinde.core.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EzhupeiSchDeptExtMapper {
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
