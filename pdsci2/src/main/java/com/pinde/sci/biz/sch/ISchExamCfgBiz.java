package com.pinde.sci.biz.sch;

import com.pinde.core.model.*;

import java.util.List;
import java.util.Map;


public interface ISchExamCfgBiz {

	String generateExam(SchExamArrangement schExamArrangement, String trainingSpeId, String sessionNumber, String accessToken);

	void generateDoctorExam(SchExamArrangement schExamArrangement, String paperFlow, String trainingSpeId, String sessionNumber, String accessToken);

	boolean deleteExam(String paperFlow, String accessToken);

	List<SchExamArrangement> searchList(SchExamArrangement schExamArrangement);

	int updateCfg(SchExamArrangement schExamArrangement);

	SchExamArrangement readByFlow(String arrangeFlow);

	List<SchExamStandardDept> readStandardDeptsByFlow(String arrangeFlow);

	/**
	 * 保存或修改考核配置
	 * @param schExamArrangement 考核配置
	 * @param standardDeptId 考试范围
     * @return
     */
	int updateArrangement(SchExamArrangement schExamArrangement, String[] standardDeptId,String isProduct);

	int findDocExamCount(String userFlow, String arrangeFlow);

	int save(SchExamDoctorArrangement result);


	int update(SchExamDoctorArrangement result);

	Map<String,Object> getSuiJiConfig(SchExamArrangement ment,String userFlow);

	Map<String,Object> getGuDingConfig(SchExamArrangement ment);

	/**
	 * 根据arrangeFlow和doctorFlow查询医师考试的记录
	 * @param paramMap
	 * @return
     */
	List<Map<String,String>> searchExamLogByItems(Map<String, String> paramMap);

	void deleteSchExamStandardDeptByDeptId(String deptFlow);

	int checkHaveExam(String arrangeFlow);

	List<SchArrangeResult> getSuiJiConfigs(SchExamArrangement ment, String userFlow);

	List<SchArrangeResult> getGuDingConfigs(SchExamArrangement ment, String userFlow);

	int checkExists(SchExamArrangement schExamArrangement);

	int checkExists(SchExamArrangement schExamArrangement, List<String> speIds, List<String> sessinNumbers);

	int updateArrangements(SchExamArrangement schExamArrangement, String[] standardDeptId, List<String> speIds, List<String> sessinNumbers, String paperFlow);

	SchExamDoctorArrangement readExamResult(String processFlow);

	int saveGraduationExam(ResDoctorGraduationExam result);
}