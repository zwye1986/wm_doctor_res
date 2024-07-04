package com.pinde.res.dao.jswjw.ext;

import java.util.List;
import java.util.Map;

import com.pinde.sci.model.mo.SysDept;
import org.apache.ibatis.annotations.Param;

public interface SysDeptExtMapper {
	/**
	 * 获取本机构部门及对本机构公开的部门
	 * @param paramMap
	 * @return
	 */
	List<SysDept> getExtDept(Map<String,Object> paramMap);
	
	/**
	 * 获取本机构部门及对本机构公开的部门
	 * @param paramMap
	 * @return
	 */
	List<SysDept> getBaseAndExtDept(Map<String,Object> paramMap);

	/**
	 * 获取科主任多个科室
	 * @param userFlow
	 * @param deptFlow
     * @return
     */
	List<SysDept> getHeadDeptList(@Param("userFlow") String userFlow, @Param("deptFlow") String deptFlow);

	/**
	 * 江苏西医基地下存在异常出科学员的科室信息
	 * @param orgFlow
	 * @return
     */
	List<SysDept> getErrorSchDepts(@Param("orgFlow") String orgFlow);

	List<SysDept> getSpeDeptList(@Param("userFlow") String userFlow);
}
