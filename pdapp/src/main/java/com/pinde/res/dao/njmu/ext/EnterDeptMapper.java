package com.pinde.res.dao.njmu.ext;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EnterDeptMapper {
	
	public Map<String, Object> pre(@Param(value="deptFlow") String deptFlow);
	
	public void addExam(@Param(value="deptFlow") String deptFlow);

}
