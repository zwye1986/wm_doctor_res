package com.pinde.res.dao.njmu.ext;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DataListMapper {

	public List<Map<String, Object>> mrDataList(
			@Param(value = "userFlow") String userFlow,
			@Param(value = "deptFlow") String deptFlow,
			@Param(value = "cataFlow") String cataFlow,
			@Param(value = "startRow") int startRow,
			@Param(value = "endRow") int endRow);

	public List<Map<String, Object>> diseaseDataList(
			@Param(value = "userFlow") String userFlow,
			@Param(value = "deptFlow") String deptFlow,
			@Param(value = "cataFlow") String cataFlow,
			@Param(value = "startRow") int startRow,
			@Param(value = "endRow") int endRow);

	public List<Map<String, Object>> skillDataList(
			@Param(value = "userFlow") String userFlow,
			@Param(value = "deptFlow") String deptFlow,
			@Param(value = "cataFlow") String cataFlow,
			@Param(value = "startRow") int startRow,
			@Param(value = "endRow") int endRow);

	public List<Map<String, Object>> operationDataList(
			@Param(value = "userFlow") String userFlow,
			@Param(value = "deptFlow") String deptFlow,
			@Param(value = "cataFlow") String cataFlow,
			@Param(value = "startRow") int startRow,
			@Param(value = "endRow") int endRow);

	public List<Map<String, Object>> activityDataList(
			@Param(value = "userFlow") String userFlow,
			@Param(value = "deptFlow") String deptFlow,
			@Param(value = "cataFlow") String cataFlow,
			@Param(value = "startRow") int startRow,
			@Param(value = "endRow") int endRow);

}
