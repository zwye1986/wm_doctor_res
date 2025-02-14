package com.pinde.res.dao.stdp.ext;

import com.pinde.core.model.TeachingActivityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeachingActivityInfoExtMapper {

	List<Map<String,Object>> findActivityList(Map<String, String> param);

	List<Map<String,Object>> findActivityList2(Map<String, String> param);


	Map<String,Object> readActivity(@Param("activityFlow") String activityFlow);

	List<Map<String,Object>> readActivityResults(@Param("activityFlow") String activityFlow, @Param("searchStr") String searchStr);

	List<Map<String,Object>> readActivityTargetEvals(@Param("activityFlow") String activityFlow);

	List<Map<String,Object>> readActivityResults2(@Param("activityFlow") String activityFlow);

	int checkTime(@Param("activity") TeachingActivityInfo activity);

	List<Map<String,String>> getDeptActivityStatisticsMap(@Param("deptFlow") String deptFlow, @Param("startTime") String startTime, @Param("endTime") String endTime);



	int checkJoin2(@Param("activityFlow") String activityFlow, @Param("userFlow") String userFlow);



	List<Map<String,String>> getDoctorActivityStatisticsMap(@Param("doctorFlow") String doctorFlow, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<Map<String,Object>> findActivityTypeList(Map<String, String> param);

	List<TeachingActivityInfo> checkJoinList(@Param("activityFlow") String activityFlow, @Param("userFlow") String userFlow);

	List<Map<String, Object>> getActivityListByRole(Map<String, Object> param);

	List<Map<String,Object>> searchDeptByDoctor(@Param("userFlow") String userFlow, @Param("orgFlow") String orgFlow);

	List<Map<String,Object>> readActivityResultsByType(@Param("activityFlow") String activityFlow, @Param("searchStr") String searchStr, @Param("typeId") String typeId);

	List<Map<String,Object>> getDeptCountActivityStatisticsList(@Param("orgFlow") String orgFlow,@Param("deptFlow") String deptFlow, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("notNull") String notNull);
}
