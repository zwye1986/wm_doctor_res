package com.pinde.res.dao.nydyjs.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NydyjsAppMapper {
	
	Map<String,String> readUser(@Param("userFlow") String userFlow);

	int getNotAuditNum(String userFlow);

	int getIsCurrentNum(String userFlow);

	int getIsSchNum(String userFlow);

	List<Map<String,String>> getStudentList(Map<String, Object> schArrangeResultMap);

	List<Map<String,String>> getDataStudentList(Map<String, Object> schArrangeResultMap);

	List<Map<String,String>> appealStudentList(Map<String, Object> schArrangeResultMap);

	List<Map<String,String>> afterSummeryStudentList(Map<String, Object> schArrangeResultMap);

	List<Map<String,String>> afterEvaStudentList(Map<String, Object> schArrangeResultMap);

	int getAppealNotAuditNum(String userFlow);

	int getAfterSummNotAuditNum(String userFlow);

	int getAfterEvaNotAuditNum(String userFlow);

	List<Map<String,String>> getCaseRegistryList(Map<String, Object> param);

	Map<String,String> getCaseRegistryProcess(Map<String, Object> param);

	Map<String,String> getDiseaseProcess(Map<String, Object> param);

	Map<String,String> getSkillProcess(Map<String, Object> param);

	Map<String,String> getPossSkillProcess(Map<String, Object> param);

	List<Map<String,String>> getDiseaseRegistryList(Map<String, Object> param);

	int insertIntoReviewInfo(Map<String, Object> param);

	int insertIntoReviewInfoItem(Map<String, Object> param);

	int updateRecBigHistory(Map<String, Object> param);

	int updateRecCaseClass(Map<String, Object> param);

	int updateRecOperateSkill(Map<String, Object> param);

	int updateRecPOSSkill(Map<String, Object> param);

	List<Map<String,String>> getOperateSkillList(Map<String, Object> param);

	List<Map<String,String>> getPossSkillList(Map<String, Object> param);

	List<Map<String,String>> getActivityList(Map<String, Object> param);

	int insertCaseDiscussCheck(Map<String, Object> param);

	int updateCaseDiscuss(Map<String, Object> param);

	List<Map<String,String>> appealDataList(Map<String, Object> param);

	int updateExplan(Map<String, Object> param);

	Map<String,String> getAfterSummDetail(Map<String, Object> param);

	int updateOutSecBrief(Map<String, Object> param);

	int deleteWFWORK(Map<String, Object> param);

	int getDiseaseRegistryNum(String explanId);

	int getOperateSkillNum(String explanId);

	int getPossSkillNum(String explanId);

	int getCaRequireNum(String cySecId);

	int getCaRequireFinishNum(Map<String, Object> param);

	int getDisRequireNum(String cySecId);

	int getDisRequireFinishNum(Map<String, Object> param);

	int getOpRequireFinishNum(Map<String, Object> param);

	int getOpRequireNum(String cySecId);

	int getOpsRequireNum(String cySecId);

	int getOpsRequireFinishNum(Map<String, Object> param);

	Map<String, Object> getDayLogCount(Map<String, Object> param);

	Map<String, Object> getCycDayCount(Map<String, Object> param);

	int getActivityNum(Map<String, Object> param);

	Map<String,Object> getScoreMap(Map<String, Object> param);

	int getCheckStatus(Map<String, Object> param);

	int updateAfterEva(Map<String, Object> param);

	int insertAfterEva(Map<String, Object> param);

	Map<String,Object> getTheroyScore(String afterEvaId);

	int deleteWfwork(String workId);

	int insertWFWORK(Map<String, Object> param2);

	List<Map<String,Object>> getDayTimeList(Map<String, Object> param);

	int havaAppealNum(Map<String, Object> paramMap);

	int getMonths(Map<String, Object> param);
}
