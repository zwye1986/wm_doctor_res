package com.pinde.res.dao.jszy.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JszyResDoctorExtMapper {
	/**
	 * 获取该教师的所有学员
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>> getDocListByTeacher(Map<String,Object> paramMap);

	List<Map<String,String>> schDoctorSchProcessQuery(Map<String, Object> schArrangeResultMap);

	List<Map<String,String>> schDoctorSchProcessInfoQuery(Map<String, Object> schArrangeResultMap);

	List<Map<String,Object>> findDataNoAudit(Map<String, Object> param);

	List<Map<String,Object>> getDocCapDatas(@Param(value ="id") String id,@Param(value ="processFlow")  String processFlow);
}
