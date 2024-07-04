package com.pinde.res.dao.bengyify.ext;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BengyifyTeacherMapper {

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

	List<Map<String,Object>> getActivityList(@Param("userFlow") String userFlow, @Param("status") String status, @Param("start") int start, @Param("end") int end);

	Map<String,Object> activityDetail(@Param("caDisID") String caDisID);

	Map<String,String> getAttachIdMap();

	int uploadFile(Map<String, String> param);

	int updateActivity(Map<String, String> param);

	Map<String,String> getDirMap();
}
