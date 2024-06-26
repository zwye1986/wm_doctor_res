package com.pinde.sci.dao.sys;

import com.pinde.sci.model.mo.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysDeptExtMapper {
	/**
	 * 根据rec和process的情况获取部门
	 * @param map
	 * @return
	 */
	List<SysDept> getDeptByRec(Map<String,Object> map);
	List<SysDept> getDeptByRecForUni(Map<String,Object> map);

	List<Map<String,String>> searchDeptByUnion(@Param("dept") SysDept dept, @Param("isUnion") String isUnion);

	//查询科主任所属科室
	List<Map<String,Object>> queryDeptListByFlow(@Param("userFlow") String userFlow);

	List<Map<String,String>> searchDeptByExt(Map<String, Object> paramMap);

	List<Map<String, Object>> searchDeptByDoctor(@Param("userFlow") String userFlow, @Param("orgFlow") String orgFlow);

	List<SysDept> searchDeptBySpe(@Param("resTrainingSpeId") String resTrainingSpeId, @Param("orgFlow") String orgFlow);

	/**
	 * 获取本机构部门及对本机构公开的部门
	 * @param paramMap
	 * @return
	 */
	List<SysDept> getBaseAndExtDept(Map<String,Object> paramMap);

	String selectDeptByParam(@Param("orgFlow") String orgFlow, @Param("deptName") String deptName);

	List<String> selectDeptByParamList(@Param("orgFlow") String orgFlow, @Param("deptName") String deptName);

}


