package com.pinde.res.dao.njmu.ext;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CategoryProgressMapper {
	
	public List<Map<String, Object>> mrProgress(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow,@Param(value="startRow") int startRow,@Param(value="endRow") int endRow);
	
	public List<Map<String, Object>> diseaseProgress(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow,@Param(value="startRow") int startRow,@Param(value="endRow") int endRow);
	
	public List<Map<String, Object>> skillProgress(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow,@Param(value="startRow") int startRow,@Param(value="endRow") int endRow);

	public List<Map<String, Object>> operationProgress(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow,@Param(value="startRow") int startRow,@Param(value="endRow") int endRow);

	public List<Map<String, Object>> activityProgress(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow,@Param(value="startRow") int startRow,@Param(value="endRow") int endRow);
	
	public List<Map<String, Object>> summaryProgress(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow,@Param(value="startRow") int startRow,@Param(value="endRow") int endRow);


}
