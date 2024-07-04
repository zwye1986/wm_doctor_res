package com.pinde.res.dao.stdp.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StdpResDoctorExtMapper {
	/**
	 * 获取该教师的所有学员
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>> getDocListByTeacher(Map<String,Object> paramMap);

	List<Map<String,String>> schDoctorSchProcessQuery(Map<String, Object> schArrangeResultMap);

	List<Map<String,String>> zseySchDoctorSchProcessQuery(Map<String, Object> schArrangeResultMap);

	List<Map<String,String>> schDoctorSchProcessInfoQuery(Map<String, Object> schArrangeResultMap);

	List<Map<String,String>> zseySchDoctorSchProcessInfoQuery(Map<String, Object> schArrangeResultMap);

	List<Map<String,Object>> findDataNoAudit(Map<String, Object> param);

	List<Map<String, Object>> getDocCapDatas(@Param("id")  String id,@Param("processFlow") String processFlow);

	Map<String,String> getPowerMap(@Param("docFlow") String docFlow);

	List<Map<String,String>> teacherDocUserList(Map<String, Object> schArrangeResultMap);

	int getCountDocListByTeacher(Map<String, Object> paramMap);
}
