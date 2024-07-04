package com.pinde.res.dao.gzzy.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GzzySecretaryMapper {

	Map<String,Object> InProcessCount(Map<String, Object> param);

	List<String> getHosSecId(@Param("userFlow") String userFlow);

	List<Map<String, Object>> OutProcessCount(@Param("userFlow") String userFlow);

	List<Map<String,Object>> getInProcessStuList(Map<String, Object> param);

	List<Map<String,Object>> getOutProcessStuList(Map<String, Object> param);

	List<Map<String,Object>> getTeachPlanList(Map<String, Object> param);

	List<Map<String,Object>> getInProcessFileList(Map<String, Object> param);

	Map<String,Object> getCycleDetail(@Param("ucsid") String ucsid);

	List<Map<String,Object>> getTeacherList(Map<String, Object> param);

	List<Map<String,Object>> getHeadList(Map<String, Object> param);

	Map<String,Object> getAfterSummData(@Param("ucsid") String ucsid);

	List<Map<String,Object>> activitsDate(@Param("ucsid") String ucsid);

	Map<String,Object> cycleData(@Param("cySecID") String cySecID, @Param("userID") String userID);

	List<Map<String,Object>> getDayEvalItems();

	List<Map<String,String>> getDopsStuRoles();

	List<Map<String,Object>> getDayEvalResults(@Param("ucsid") String ucsid, @Param("type") String type);

	Map<String,Object> getDopsData(@Param("ucsid") String ucsid);

	Map<String,Object> getStuInitData(@Param("ucsid") String ucsid);

	String getSecName(@Param("ucsid") String ucsid);

	List<Map<String,Object>> getmbs(@Param("type") String type);

	List<Map<String,Object>> getmbEvals(@Param("type") String type, @Param("examItemID") Object examItemID);

	List<Map<String,Object>> getMbevalResults(@Param("type") String type, @Param("ucsid") String ucsid);

	Map<String,Object> getMiniData(@Param("ucsid") String ucsid);

	String getIsAssess(@Param("ucsid") String ucsid);

	String getDopsScore(@Param("ucsid") String ucsid);

	String getMiniScore(@Param("ucsid") String ucsid);

	String getFzMiniScore(@Param("ucsid") String ucsid);

	List<Map<String,Object>> getZhEval(@Param("ucsid") String ucsid, @Param("type") String type);

	Map<String,Object> getAuditData(@Param("ucsid") String ucsid);

	List<Map<String,Object>> getLeaveList(Map<String, Object> param);

	List<Map<String,String>> readUserInfo(@Param("userFlow") String userFlow);

	Map<String,Object> inProcessFileDetail(@Param("readSecDocumentId") String readSecDocumentId);

	List<Map<String,Object>> zhuPeiInfoList(Map<String, Object> param);

	Map<String,Object> zhuPeiFileDetail(@Param("cIFlow") String cIFlow);

	Map<String,String> deptOrgCost(@Param("userFlow") String userFlow, @Param("year") String year);

	Map<String,Object> getOutInManageDetail(@Param("ucsid") String ucsid);

	List<Map<String,Object>> getFillInfos(@Param("ucsid") String ucsid);

	List<Map<String, Object>> InProcessCount2(Map<String, Object> param);

	void updateDayEvalItem(Map<String, String> m);

	void updateDayEvalType(@Param("examInfoDF") String examInfoDF, @Param("examInfoId") String examInfoId, @Param("TecAppraise") String TecAppraise);

	void updateAfterEvaluation1(Map<String, String> param);

	void updateAfterSummary1(Map<String, String> param);

	List<Map<String,Object>> internshipList(Map<String, Object> param);

	Map<String,Object> getAppraisalData(@Param("ucsid") String ucsid);

	Map<String,Object> getDayEvalData(@Param("ucsid") String ucsid);

	void updateSecAppraise(Map<String, Object> param);

	Map<String,Object> getTeachPlanDetail(@Param("caDisID") String caDisID);

	List<Map<String,Object>> activityStuList(Map<String, Object> param);

	List<Map<String,Object>> getActivityList(Map<String, Object> param);

	List<Map<String,Object>> getActivtyDicts();

	List<Map<String,Object>> getThisTeachers(Map<String, Object> param);

	List<Map<String,Object>> getNotThisTeachers(Map<String, Object> param);

	List<Map<String,Object>> getDoctors(Map<String, Object> param);

	int updateTeachPlan(Map<String, String> param);

	int addTeachPlan(Map<String, String> param);

	Map<String,String> docHosSecID(@Param("tecID") String tecID);

	Integer getCycleNum(@Param("ucsid") String ucsid);

	void insertInProcess(Map<String, Object> param);

	void updateInProcess(Map<String, Object> param);

	Integer getInProcessFileNum(@Param("ucsid") String ucsid);

	void insertInProcessFile(Map<String, Object> param);

	int delTeachPlan(@Param("caDisID") String caDisID);

	Integer hasTea(@Param("rUserID") String rUserID, @Param("rHosCySecID") String rHosCySecID, @Param("ucsid") String ucsid);

	void addTeaInfo(Map<String, Object> param);

	void addTeaRecord(Map<String, Object> param);

	void updateTeaInfo(Map<String, Object> param);

	Integer hasHead(@Param("ucsid") String ucsid);

	void updateHeadInfo(Map<String, Object> param);

	void addHeadInfo(Map<String, Object> param);

	Map<String,Object> getSpeDeptInfo(@Param("rCySecID") String rCySecID);

	Map<String,Object> getExamTestInfo(Map<String, Object> speDeptInfo);

	Integer hasExam(@Param("ucsid") String ucsid);

	void addExamInfo(Map<String, Object> param);

	Map<String,Object> getActivitydetail(@Param("caDisID") String caDisID);

	int saveActivityEval(Map<String, String> param);

    List<Map<String, String>> selectDocCycTimes(@Param("rUserID") String rUserID,@Param("UCSID") String UCSID);

    List<Map<String, Object>> getMiniItemScore(@Param("ucsid") String ucsid, @Param("type") String type);
}
