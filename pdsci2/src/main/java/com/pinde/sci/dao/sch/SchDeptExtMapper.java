package com.pinde.sci.dao.sch;


import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SchDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchDeptExtMapper {
	List<SchDept> searchTeachDept(@Param(value="teacherUserFlow")String teacherUserFlow);
	
	/**
	 * 获取医师的轮转计划内科室
	 * @param userFlow
	 * @return
	 */
	List<SchDept> searchrotationDept(@Param(value="userFlow")String userFlow);
	
	/**
	 * 按条件筛选医师们的轮转科室范围
	 * @param doctor
	 * @return
	 */
	List<SchDept> countDeptArea(@Param(value="doctor")ResDoctor doctor);
	
	/**
	 * 获取该用户的所有轮转科室
	 * @param userFlow
	 * @return
	 */
	List<SchDept> userSchDept(@Param(value="userFlow")String userFlow);
	
	/**
	 * 获取该用户是否存在对外开放的科室
	 * @param paramMap
	 * @return
	 */
	int getExternalNum(Map<String,Object> paramMap);
	/**
	 * 根据orgFlow查询已关联的轮转科室集合
	 * @param paramMap
	 * @return
	 */
	List<SchDept> searchSchDeptHadRelated(Map<String,String> paramMap);

	List<SchDept> readSchDeptByOrgAndStandardId(@Param("orgFlow") String orgFlow, @Param("standardDeptId") String standardDeptId);

	List<SchDept> searchRelByStandard(@Param("orgFlow") String orgFlow, @Param("standardDeptId") String standardDeptId);
}
