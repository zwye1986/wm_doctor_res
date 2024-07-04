package com.pinde.res.dao.hzyy.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HzyyKzrMapper {

	List<Map<String,Object>> getStudentList(Map<String, Object> param);

	List<Map<String,Object>> getDeptList(Map<String, Object> param);

	List<Map<String,Object>> getDeptEvalInfo(@Param("HosID") String HosID,@Param("hosSecID") String hosSecID, @Param("year") String year);

	List<Map<String,Object>> getTeacherList(Map<String, Object> param);

	List<Map<String,Object>> getTeacherEvalInfo(@Param("hosID") String hosID, @Param("teaFlow") String teaFlow, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
