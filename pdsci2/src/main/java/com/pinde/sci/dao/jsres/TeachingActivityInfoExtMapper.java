package com.pinde.sci.dao.jsres;

import com.pinde.sci.model.mo.TeachingActivityEval;
import com.pinde.sci.model.mo.TeachingActivityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeachingActivityInfoExtMapper {

	List<Map<String,Object>> findActivityList(Map<String, String> param);

	List<Map<String,Object>> activityStatisticsList(Map<String, String> param);

	List<Map<String,Object>> activityStatisticsExportList(Map<String, String> param);

	List<Map<String,Object>> queryJoin(@Param("activityFlow") String activityFlow,@Param("orgName") String orgName);

	List<Map<String,Object>> supervisioFindActivityList(Map<String, Object> param);

	List<Map<String,Object>> getTeacherActivityStatistics(Map<String, Object> param);

	List<Map<String,Object>> getTeacherActivityStatistics2(Map<String, Object> param);

	Map<String,Object> readActivity(@Param("activityFlow") String activityFlow);

	List<Map<String,Object>> readActivityResults(@Param("activityFlow") String activityFlow);

	List<Map<String,Object>> readActivityTargetEvals(@Param("activityFlow") String activityFlow);

	List<TeachingActivityEval> readTeachingEvals(@Param("activityFlow") String activityFlow);

	int checkTime(@Param("activity") TeachingActivityInfo activity);

	List<Map<String,String>> getDeptActivityStatisticsMap(@Param("deptFlow") String deptFlow, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<Map<String,Object>> getDeptCountActivityStatisticsList(@Param("orgFlow") String orgFlow,@Param("deptFlow") String deptFlow, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("notNull") String notNull);

	List<Map<String,String>> getTeacherActivityStatisticsMap(@Param("deptFlow") String deptFlow, @Param("teacherFlow") String teacherFlow, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<Map<String,String>> getTeacherActivityStatisticsReport(Map<String, Object> param);

	String getRealitSpeaker(@Param("userFlow")String userFlow,@Param("deptFlow")String deptFlow,@Param("orgFlow")String orgFlow);

	String getRealitSpeaker2(@Param("userFlow")String userFlow,@Param("deptFlow")String deptFlow,@Param("orgFlow")String orgFlow ,@Param("startTime") String startTime, @Param("endTime") String endTime);

	int checkJoin(@Param("activityFlow") String activityFlow, @Param("userFlow") String userFlow);

	List<Map<String,Object>> readActivityRegists(@Param("activityFlow") String activityFlow);

	List<Map<String,Object>> getDoctorActivityStatistics(Map<String, Object> parMp);

	List<Map<String,String>> getDoctorActivityStatisticsMap(@Param("doctorFlow") String doctorFlow, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("isDept") String isDept, @Param("userFlow") String userFlow);

	List<Map<String,Object>> getResDoctorActivityStatistics(Map<String, Object> parMp);

	List<TeachingActivityInfo> checkJoinList(@Param("activityFlow") String activityFlow, @Param("userFlow") String userFlow);

	List<Map<String,Object>> findActivityList2(Map<String, String> param);

	List<Map<String,Object>> findActivityList3(Map<String, String> param);

	List<Map<String, Object>> getActivitys(Map<String, Object> param);

	List<Map<String,Object>> getDeptActivityCountMap(Map<String, Object> param);

	List<Map<String, Object>> readActivityResults2(Map<String, Object> param2);

	List<Map<String,String>> getDeptActivityMap(@Param("trainingSpeId") String trainingSpeId, @Param("startTime") String startTime,@Param("endTime") String endTime);

	List<Map<String,String>> searchSpeDept(@Param("orgFlow") String orgFlow,@Param("trainingSpeId") String trainingSpeId);

	List<Map<String, Object>> getActivityCountMap(Map<String, Object> param);

	List<Map<String, Object>> readResults(Map<String, Object> param);

	void activityBat();

	int delFileByProductFlow(@Param("productType") String productType,@Param("productFlow") String productFlow,@Param("fileFlows") List<String> fileFlows);

	int delFileByProductFlow2(@Param("productType") String productType,@Param("productFlow") String productFlow,@Param("fileFlows") List<String> fileFlows,@Param("fileUpType") String fileUpType);
}
