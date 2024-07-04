package com.pinde.res.biz.hzyy;

import java.util.List;
import java.util.Map;

public interface IHzyyKzrBiz {

	List<Map<String,Object>> getStudentList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	List<Map<String,Object>> getDeptList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	List<Map<String,Object>> getDeptEvalInfo(String HosID,String hosSecID, String year);

	List<Map<String,Object>> getTeacherList( Map<String, Object> param, Integer pageIndex, Integer pageSize);

	List<Map<String,Object>> getTeacherEvalInfo(String hosID, String teaFlow, String startTime, String endTime);
}
