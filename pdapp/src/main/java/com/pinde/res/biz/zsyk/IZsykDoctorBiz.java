package com.pinde.res.biz.zsyk;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IZsykDoctorBiz {

	List<Map<String,Object>> getBigHistorys(String userFlow, String schDeptFlow, int pageIndex, int pageSize);

	Map<String, Object> readBigHistory(String userFlow, String schDeptFlow, String dataFlow);

	void addBigHistory(String userFlow, String schDeptFlow, HttpServletRequest request);

	void modBigHistory(String userFlow, String schDeptFlow, String dataFlow, HttpServletRequest request);

	void delBigHistory(String userFlow, String schDeptFlow, String dataFlow);


	List<Map<String,Object>> getOutPatients(String userFlow, String schDeptFlow, int pageIndex, int pageSize);

	Map<String, Object> readOutPatient(String userFlow, String schDeptFlow, String dataFlow);

	void addOutPatient(String userFlow, String schDeptFlow, HttpServletRequest request);

	void modOutPatient(String userFlow, String schDeptFlow, String dataFlow, HttpServletRequest request);

	void delOutPatient(String userFlow, String schDeptFlow, String dataFlow);



	List<Map<String,Object>> getCaseClassCatas(String userFlow, String schDeptFlow, int i, int maxValue);

	String readCaseClassCataName(String cataFlow);

	List<Map<String, Object>> getCaseClasses(String userFlow, String schDeptFlow, String cataFlow, Integer pageIndex, Integer pageSize);

	Map<String, Object> readCaseClass(String userFlow, String schDeptFlow, String dataFlow);

	void addCaseClass(String userFlow, String schDeptFlow, String cataFlow, HttpServletRequest request);

	void modCaseClass(String userFlow, String schDeptFlow, String cataFlow, String dataFlow,
                      HttpServletRequest request);
	void delCaseClass(String userFlow, String schDeptFlow, String dataFlow);


	List<Map<String, Object>> getOperateSkillCatas(String userFlow, String schDeptFlow, Integer pageIndex, Integer pageSize);

	String readOperateSkillCataName(String cataFlow);

	List<Map<String, Object>> getOperateSkills(String userFlow, String schDeptFlow, String cataFlow, Integer pageIndex, Integer pageSize);

	Map<String, Object> readOperateSkill(String userFlow, String schDeptFlow, String dataFlow);

	void addOperateSkill(String userFlow, String schDeptFlow, String cataFlow, HttpServletRequest request);

	void modOperateSkill(String userFlow, String schDeptFlow, String cataFlow, String dataFlow, HttpServletRequest request);

	void delOperateSkill(String userFlow, String schDeptFlow, String dataFlow);


	List<Map<String, Object>> getPOSSkillCatas(String userFlow, String schDeptFlow, Integer pageIndex, Integer pageSize);

	String readPOSSkillCataName(String cataFlow);

	List<Map<String, Object>> getPOSSkills(String userFlow, String schDeptFlow, String cataFlow, Integer pageIndex, Integer pageSize);
	
	Map<String, Object> readPOSSkill(String userFlow, String schDeptFlow, String dataFlow);

	void addPOSSkill(String userFlow, String schDeptFlow, String cataFlow, HttpServletRequest request);

	void modPOSSkill(String userFlow, String schDeptFlow, String cataFlow, String dataFlow, HttpServletRequest request);

	void delPOSSkill(String userFlow, String schDeptFlow, String dataFlow);
	
	

	Map<String, Object> readOutSecBrief(String schDeptFlow);

	void addOutSecBrief(String userFlow, String schDeptFlow, HttpServletRequest request);

	void modOutSecBrief(String userFlow, String schDeptFlow, HttpServletRequest request);

}
