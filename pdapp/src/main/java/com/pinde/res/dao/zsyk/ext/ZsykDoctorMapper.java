package com.pinde.res.dao.zsyk.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZsykDoctorMapper {

	List<Map<String, Object>> selectBigHistorys(@Param("userFlow") String userFlow, @Param("schDeptFlow") String schDeptFlow, @Param("start") int start, @Param("end") int end);

	Map<String, Object> selectBigHistory(@Param("dataFlow") String dataFlow);

	void insertBigHistory(Map<String, String> param);

	void updateBigHistory(Map<String, String> param);

	void deleteBigHistory(@Param("dataFlow") String dataFlow);



	List<Map<String, Object>> selectOutPatients(@Param("userFlow") String userFlow, @Param("schDeptFlow") String schDeptFlow, @Param("start") int start, @Param("end") int end);

	Map<String, Object> selectOutPatient(@Param("dataFlow") String dataFlow);

	void insertOutPatient(Map<String, String> param);

	void updateOutPatient(Map<String, String> param);

	void deleteOutPatient(@Param("dataFlow") String dataFlow);


	List<Map<String, Object>> selectCaseClassCatas(@Param("userFlow") String userFlow, @Param("schDeptFlow") String schDeptFlow, @Param("start") int start, @Param("end") int end);

	String selectCaseClassCataName(@Param("cataFlow") String cataFlow);

	List<Map<String, Object>> selectCaseClasses(@Param("userFlow") String userFlow, @Param("schDeptFlow") String schDeptFlow, @Param("cataFlow") String cataFlow, @Param("start") int start, @Param("end") int end);

	Map<String, Object> selectCaseClass(@Param("dataFlow") String dataFlow);

	void insertCaseClass(Map<String, String> param);

	void updateCaseClass(Map<String, String> param);

	void deleteCaseClass(@Param("dataFlow") String dataFlow);



	List<Map<String, Object>> selectOperateSkillCatas(@Param("userFlow") String userFlow, @Param("schDeptFlow") String schDeptFlow, @Param("start") int start, @Param("end") int end);

	String selectOperateSkillCataName(@Param("cataFlow") String cataFlow);

	List<Map<String, Object>> selectOperateSkills(@Param("userFlow") String userFlow, @Param("schDeptFlow") String schDeptFlow, @Param("cataFlow") String cataFlow, @Param("start") int start, @Param("end") int end);

	Map<String, Object> selectOperateSkill(@Param("dataFlow") String dataFlow);

	void insertOperateSkill(Map<String, String> param);

	void updateOperateSkill(Map<String, String> param);

	void deleteOperateSkill(@Param("dataFlow") String dataFlow);



	List<Map<String, Object>> selectPOSSkillCatas(@Param("userFlow") String userFlow, @Param("schDeptFlow") String schDeptFlow, @Param("start") int start, @Param("end") int end);

	String selectPOSSkillCataName(@Param("cataFlow") String cataFlow);

	List<Map<String, Object>> selectPOSSkills(@Param("userFlow") String userFlow, @Param("schDeptFlow") String schDeptFlow, @Param("cataFlow") String cataFlow, @Param("start") int start, @Param("end") int end);

	Map<String, Object> selectPOSSkill(@Param("dataFlow") String dataFlow);

	void insertPOSSkill(Map<String, String> param);

	void updatePOSSkill(Map<String, String> param);

	void deletePOSSkill(@Param("dataFlow") String dataFlow);



	Map<String, Object> selectOutSecBrief(@Param("schDeptFlow") String schDeptFlow);

	void insertOutSecBrief(Map<String, String> param);

	void updateOutSecBrief(Map<String, String> param);
	
}
