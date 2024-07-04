package com.pinde.res.dao.nfyy.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {

	List<Map<String, Object>> getDoctorListEntering(@Param("userFlow")String userFlow, @Param("doctorName")String doctorName,
			String doctorType, @Param("start")int start , @Param("end")int end);

	List<Map<String, Object>> getDoctorListNotEntered(@Param("userFlow")String userFlow, @Param("doctorName")String doctorName,
			String doctorType, @Param("start")int start , @Param("end")int end);

	List<Map<String, Object>> getDoctorListExited(@Param("userFlow")String userFlow, @Param("doctorName")String doctorName,
			String doctorType, @Param("start")int start , @Param("end")int end);

	Map<String,Object> readDoctorDeptStatus(@Param("userFlow")String userFlow, @Param("doctorFlow")String doctorFlow);
	
	Map<String,Object> readDoctorInfo(@Param("userFlow")String userFlow, @Param("doctorFlow")String doctorFlow);
	
	int enterDeptConfirm(@Param("userFlow")String userFlow, @Param("doctorFlow")String doctorFlow);
	
	Map<String,Object> readEvalInfo(@Param("userFlow")String userFlow, @Param("doctorFlow")String doctorFlow);
	
	int saveEvalInfo(@Param("userFlow")String userFlow, @Param("doctorFlow")String doctorFlow, @Param("eval")String eval);

	List<Map<String, Object>> getActives(@Param("userFlow")String userFlow, @Param("doctorFlow")String doctorFlow,
			@Param("start")int start , @Param("end")int end);

	Map<String, Object> readActivity(@Param("dataFlow")String dataFlow, @Param("doctorFlow")String doctorFlow);
	
	Map<String, Object> readOutSecBrief(@Param("doctorFlow")String doctorFlow);

	void saveOutSecBrief(Map<String,Object> params);

	Map<String, Object> readCycleOutSectionRecord(@Param("userFlow")String userFlow, @Param("doctorFlow")String doctorFlow);

	void addCycleOutSectionRecord(Map<String, Object> params);

	void modCycleOutSectionRecord(Map<String, Object> params);

	Map<String, Object> readExamInfo(@Param("doctorFlow")String doctorFlow,@Param("examInfoType")String examInfoType);
	
	List<Map<String, Object>> getMarks(@Param("doctorFlow")String doctorFlow,@Param("examInfoType")String examInfoType);
	
	void addExamInfo(Map<String, Object> params);
	
	void addMark(Map<String, Object> params);
	
	void modExamInfo(Map<String, Object> params);
	
	void modMark(Map<String, Object> params);
	
	Map<String, Object> readOutDops(@Param("doctorFlow")String doctorFlow);
	
	void addOutDops(Map<String, Object> params);

	void modOutDops(Map<String, Object> params);
	
	Map<String, Object> readOutMiniCex(@Param("doctorFlow")String doctorFlow);
	
	void addOutMiniCex(Map<String, Object> params);

	void modOutMiniCex(Map<String, Object> params);

	List<Map<String, Object>> getWaitEvalOutSecBriefList(String userFlow);
	
	Map<String,Object> getWaitArrangeActivityCount(String userFlow);
	
	List<Map<String, Object>> getNeedConfirmActives(@Param("userFlow")String userFlow, @Param("doctorFlow")String doctorFlow,
			@Param("start")int start , @Param("end")int end);

	List<Map<String, Object>> getNeedConfirmActiveUsers(@Param("dataFlow")String dataFlow);

	void confirmActiveUsers(Map<String, Object> params);

	Float readTheoryScore(String doctorFlow);

	List<Map<String,String>> getDataAudit(Map<String, String> searchMap);

	List<Map<String,String>> getMrDatas(Map<String, Object> searchMap);

	Map<String,String> getMrFinishMap(Map<String, Object> searchMap);

	List<Map<String,String>> getDiseaseDatas(Map<String, Object> searchMap);

	Map<String,String> getDiseaseFinishMap(Map<String, Object> searchMap);

	List<Map<String,String>> getSkillDatas(Map<String, Object> searchMap);

	Map<String,String> getSkillFinishMap(Map<String, Object> searchMap);

	List<Map<String,String>> getOperationDatas(Map<String, Object> searchMap);

	Map<String,String> getOperationFinishMap(Map<String, Object> searchMap);

	List<Map<String,String>> getDiscipleDatas(Map<String, Object> searchMap);

	Map<String,String> getDiscipleFinishMap(Map<String, Object> searchMap);

	Map<String,String> getMrData(@Param("dataFlow") String dataFlow);

	Map<String,String> getDiseaseData(@Param("dataFlow") String dataFlow);

	Map<String,String> getSkillData(@Param("dataFlow") String dataFlow);

	Map<String,String> getOperationData(@Param("dataFlow") String dataFlow);

	Map<String,String> getDiscipleData(@Param("dataFlow") String dataFlow);

	List<Map<String,String>> getAuditMrDatas(Map<String, Object> searchMap);

	List<Map<String,String>> getAuditDiseaseDatas(Map<String, Object> searchMap);

	List<Map<String,String>> getAuditSkillDatas(Map<String, Object> searchMap);

	List<Map<String,String>> getAuditOperationDatas(Map<String, Object> searchMap);

	List<Map<String,String>> getAuditDiscipleDatas(Map<String, Object> searchMap);

	int auditMrData(@Param("dataFlow") String dataFlow, @Param("isPass") String isPass);

	int auditDiseaseData(@Param("dataFlow") String dataFlow, @Param("isPass") String isPass);

	int auditSkillData(@Param("dataFlow") String dataFlow, @Param("isPass") String isPass);

	int auditOperationData(@Param("dataFlow") String dataFlow, @Param("isPass") String isPass);

	int auditDiscipleData(@Param("dataFlow") String dataFlow, @Param("isPass") String isPass);

	Map<String,String> getOutSkill(@Param("ucsid") String ucsid);

	Map<String,String> getCycleInfo(@Param("ucsid") String ucsid);

	List<Map<String,String>> getSkillList(@Param("skillName") String skillName, @Param("start") int start, @Param("end") int end);

	List<Map<String,String>> getSkillItems(@Param("skillFlow") String skillFlow, @Param("isMain") String isMain);

	List<Map<String,String>> getSkillItemScores(@Param("skillId") String skillId);

	void updateOutSkill(@Param("skill_patientName") String skill_patientName, @Param("skill_no") String skill_no, @Param("skill_score") String skill_score, @Param("skill_time") String skill_time, @Param("skill_id") String skill_id);

	void updateItemScore(@Param("skill_id") String skill_id, @Param("tItemFlow") String tItemFlow, @Param("score") String score);

	void insertItemScore(@Param("skillId") String skillId, @Param("tItemFlow") String tItemFlow, @Param("score") String score);

	void insertOutSkill(@Param("skill_patientName") String skill_patientName,@Param("skill_name") String skill_name, @Param("skill_no") String skill_no, @Param("skill_score") String skill_score, @Param("skill_time") String skill_time, @Param("doctorFlow") String doctorFlow, @Param("skillFlow") String skillFlow, @Param("skillId") String skillId);

	Map<String,String> getSkill(@Param("skillFlow") String skillFlow);

	Map<String,String> getOutPatient(@Param("ucsid") String ucsid);

	List<Map<String,String>> getPatientItemScores(@Param("patient_id") String patient_id);

	void updateOutPatient(@Param("patient_name") String patient_name, @Param("patient_no") String patient_no, @Param("patient_score") String patient_score, @Param("patient_remark") String patient_remark, @Param("patient_id") String patient_id);

	void updatePatientItemScore(@Param("patient_id") String patient_id, @Param("index") String index, @Param("s") String s);

	void insertOutPatient(@Param("patient_name") String patient_name, @Param("patient_no") String patient_no, @Param("patient_score") String patient_score, @Param("patient_remark") String patient_remark, @Param("doctorFlow") String doctorFlow, @Param("userFlow") String userFlow, @Param("currDateTime2") String currDateTime2, @Param("patientTypeId") String patientTypeId, @Param("patientTypeName") String patientTypeName, @Param("patientId") String patientId);

	void insertPatientItemScore(@Param("patientId") String patientId, @Param("index") String index, @Param("s") String s);

	Map<String,String> getOutSecBrief(@Param("ucsid") String ucsid);

	int updateOutSecBrief(@Param("ucsid") String ucsid, @Param("theoryScore") String theoryScore);
}
