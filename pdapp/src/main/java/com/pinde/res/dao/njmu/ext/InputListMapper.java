package com.pinde.res.dao.njmu.ext;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface InputListMapper {

	List<Map<String, Object>> diseaseDiagNameList(@Param(value="userFlow") String userFlow, @Param(value="deptFlow") String deptFlow);

	List<Map<String, Object>> operationOperNameList(@Param(value="userFlow") String userFlow, @Param(value="deptFlow") String deptFlow);

	List<Map<String, Object>> skillOperNameList(@Param(value="userFlow") String userFlow, @Param(value="deptFlow") String deptFlow);

}
