package com.pinde.res.biz.hzyy;

import com.pinde.res.ctrl.upload.FileForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IHzyyTeacherBiz {

	String isNeedPatient(String ucsid);

	List<Map<String,Object>> getDoctorList(String userFlow, String schStatusId, String doctorName, String doctorType, int pageIndex, int pageSize);
	
	Map<String,Object> readDoctorDeptStatus(String userFlow, String doctorFlow);
	
	Map<String,Object> readDoctorInfo(String userFlow, String doctorFlow);
	
	void enterDeptConfirm(String userFlow, String doctorFlow);
	
	Map<String,Object> readEvalInfo(String userFlow, String doctorFlow);
	
	void saveEvalInfo(String userFlow, String doctorFlow, String eval);

	List<Map<String, Object>> getActives(String userFlow, String doctorFlow, Integer pageIndex, Integer pageSize);

	Map<String,Object> readActivity(String dataFlow, String doctorFlow);

	Map<String, Object> readOutSecBrief(String doctorFlow);

	void saveOutSecBrief(String userFlow, String doctorFlow, HttpServletRequest request);

	Map<String, Object> readCycleOutSectionRecord(String userFlow, String doctorFlow);

	Map<String, Object> readExamInfo(String doctorFlow, String examInfoType);
	
	List<Map<String, Object>> getMarks(String doctorFlow, String examInfoType);

	Map<String, Object> readOutDops(String doctorFlow);

	Map<String, Object> readOutMiniCex(String doctorFlow);

	void saveOutDops(String userFlow, String doctorFlow, HttpServletRequest request);

	void saveOutMiniCex(String userFlow, String doctorFlow, HttpServletRequest request);

	List<Map<String, Object>> getWaitEvalOutSecBriefList(String userFlow);

	Map<String,Object> getWaitArrangeActivityCount(String userFlow);

	List<Map<String, Object>> getNeedConfirmActives(String userFlow, String doctorFlow, Integer pageIndex, Integer pageSize);

	List<Map<String, Object>> getNeedConfirmActiveUsers(String dataFlow);

	void confirmActiveUsers(String dataFlow, HttpServletRequest request);

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

	Map<String,String> getMrData(String dataFlow);

	Map<String,String> getDiseaseData(String dataFlow);

	Map<String,String> getSkillData(String dataFlow);

	Map<String,String> getOperationData(String dataFlow);

	Map<String,String> getDiscipleData(String dataFlow);

	List<Map<String,String>> getAuditMrDatas(Map<String, Object> searchMap);

	List<Map<String,String>> getAuditDiseaseDatas(Map<String, Object> searchMap);

	List<Map<String,String>> getAuditSkillDatas(Map<String, Object> searchMap);

	List<Map<String,String>> getAuditOperationDatas(Map<String, Object> searchMap);

	List<Map<String,String>> getAuditDiscipleDatas(Map<String, Object> searchMap);

	int batchAuditData(List<Map<String,String>> datas, String dataTypeId, String isPass);

	int auditData(String dataFlow, String dataTypeId, String isPass);

	Map<String,String> getOutSkill(String doctorFlow);

	List<Map<String,String>> getSkillList(String skillName, Integer pageIndex, Integer pageSize);

	Map<String,String> getCycleInfo(String doctorFlow);

	List<Map<String,String>> getSkillItems(String skillFlow, String y);

	Map<String,String> getSkillScorelMap(String skillId);

	void updateSkillScore(String skill_id, String doctorFlow, String skillFlow, List<Map<String, String>> items, Map<String, List<Map<String, String>>> itemsMap, HttpServletRequest request);

	void insertSkillScore(String doctorFlow, String skillId, String skillFlow, Map<String, String> skill, List<Map<String,String>> items, Map<String, List<Map<String,String>>> itemsMap, HttpServletRequest request);

	Map<String,String> getSkill(String skillFlow);

	Map<String,String> getOutPatient(String doctorFlow);

	Map<String,String> getPatientScorelMap(String patient_id);

	void updatePatientScore(String patient_id, String doctorFlow, HttpServletRequest request);

	void insertPatientScore(String doctorFlow, String patientId, String patientTypeId, HttpServletRequest request, String userFlow);

	Map<String,String> getOutSecBrief(String doctorFlow);

	int updateOutSecBrief(String doctorFlow, String theoryScore);

	Map<String,String> readAfterEva(String doctorFlow);

	Map<String,String> getCycleOutInfo(String doctorFlow);

	int getJxhd(String doctorFlow);

	int updateAfterEvaluation(String id, String doctorFlow, Map<String, String> tea, HttpServletRequest request);

	int insertAfterEvaluation(String doctorFlow, Map<String, String> tea, HttpServletRequest request);

	List<Map<String,String>> getFileList(String skill_id);

	int delFile(String fileFlow);

	Map<String,String> readFile(String fileFlow);

	int saveFile(FileForm form, Map<String, Object> param);

	String getNewFileFlow();


	List<Map<String,String>> readPatientList();

	List<Map<String,String>> readPatientDeptList(String dicItemID, String ucsid);
}
