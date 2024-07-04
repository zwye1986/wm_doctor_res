package com.pinde.res.biz.gzzy;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IGzzyTeacherBiz {
	
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

	Map<String,Object> readBaseInfo(String doctorFlow, String studentFlow);

	Map<String,Object> readDailyInfo(String doctorFlow);

	BigDecimal readShijia(Map<String, Object> baseInfo);

	BigDecimal readBingjia(Map<String, Object> baseInfo);

	Integer readQueQing(Map<String, Object> baseInfo);

	String readKzrName(String doctorFlow);

	List<Map<String,String>> isFzDept(String doctorFlow);

	Map<String,Object> readOutMiniCexFz(String doctorFlow);

	void saveOutMiniCexFz(String userFlow, String doctorFlow, HttpServletRequest request);
}
