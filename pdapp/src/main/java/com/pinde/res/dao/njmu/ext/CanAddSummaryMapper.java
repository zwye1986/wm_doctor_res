package com.pinde.res.dao.njmu.ext;

import org.apache.ibatis.annotations.Param;

public interface CanAddSummaryMapper {
	
	public int evaluateTeacher(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow);
	
	public int evaluateDept(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow);
	
	public int afterDeptExam(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow);

}
