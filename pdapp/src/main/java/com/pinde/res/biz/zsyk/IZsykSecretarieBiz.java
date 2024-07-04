package com.pinde.res.biz.zsyk;

import java.util.List;
import java.util.Map;

public interface IZsykSecretarieBiz {

	Map<String,Object> InProcessCount(Map<String, Object> param);

	List<String> getHosSecId(String userFlow);

	Map<String,Object> OutProcessCount(String userFlow);

	List<Map<String,Object>> getInProcessStuList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	Map<String,Object> getCycleDetail(String ucsid);

	List<Map<String,Object>> getTeacherList(Map<String, Object> param);

	List<Map<String,Object>> getHeadList(Map<String, Object> param);

	List<Map<String,Object>> getOutProcessStuList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	Map<String,Object> getAfterSummData(String ucsid);

	Map<String,Object> activitsDate(String ucsid);

	Map<String,Object> cycleData(String cySecID, String userID);

	List<Map<String,Object>> getDayEvalItems();


	List<Map<String,Object>> getDayEvalResults(String ucsid, String type);

	List<Map<String,String>> getDopsStuRoles();

	String getSecName(String ucsid);

	List<Map<String,Object>> getmbs(String type);

	List<Map<String,Object>> getmbEvals(String type, Object examItemID);

	Map<String,Object> getDopsData(String ucsid);

	Map<String,Object> getStuInitData(String ucsid);

	List<Map<String,Object>> getMbEvalResults(String type, String ucsid);

	Map<String,Object> getMiniData(String ucsid);

	String getIsAssess(String ucsid);

	String getDopsScore(String ucsid);

	String getMiniScore(String ucsid);

	List<Map<String,Object>> getZhEval(String ucsid, String type);

	Map<String,Object> getAuditData(String ucsid);

	List<Map<String,Object>> getTeachPlanList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	List<Map<String,Object>> getLeaveList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	Map<String,String> readUserInfo(String userFlow);

	List<Map<String,Object>> getInProcessFileList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	Map<String,Object> inProcessFileDetail(String readSecDocumentId);

	List<Map<String,Object>> zhuPeiInfoList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	Map<String,Object> zhuPeiFileDetail(String cIFlow);

	Map<String,String> deptOrgCost(String userFlow, String year);

	Map<String,Object> getOutInManageDetail(String ucsid);

	List<Map<String, Object>> getFillInfos(String ucsid);

	Map<String,Object> InProcessCount2(Map<String, Object> param);

	String saveDayEval(List<Map<String, String>> items, String examInfoDf);

	String saveAfterEvaluation1(Map<String, String> param);

	List<Map<String,Object>> internshipList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	Map<String,Object> getAppraisalData(String ucsid);

	String saveAppraisal(Map<String, Object> param);

	Map<String,Object> getTeachPlanDetail(String caDisID);

	List<Map<String,Object>> activityStuList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	List<Map<String,Object>> getActivtyDicts();

	List<Map<String,Object>> getThisTeachers(String userFlow, Integer pageIndex, Integer pageSize, String tecID);

	List<Map<String,Object>> getNotThisTeachers(String userFlow, Integer pageIndex, Integer pageSize, String tecID);

	List<Map<String,Object>> getDoctors(String userFlow, Integer pageIndex, Integer pageSize);

	int updateTeachPlan(Map<String, String> param);

	int addTeachPlan(Map<String, String> param);

	Map<String,String> docHosSecID(String tecID);

	String saveInProcess(Map<String, Object> param);

	int delTeachPlan(String caDisID);

	Map<String,Object> getDayEvalData(String ucsid);

	List<Map<String,Object>> getActivityList(Map<String, Object> param, Integer pageIndex, Integer pageSize);

	Map<String,Object> getActivitydetail(String caDisID);

	int saveActivityEval(Map<String, String> param);

}
