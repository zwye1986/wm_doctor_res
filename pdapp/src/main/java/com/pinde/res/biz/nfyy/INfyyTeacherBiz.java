package com.pinde.res.biz.nfyy;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pinde.res.model.nfyy.mo.Activity;

public interface INfyyTeacherBiz {
	
	List<Map<String,Object>> getDoctorList(String userFlow , String schStatusId , String doctorName , String doctorType , int pageIndex , int pageSize);
	
	Map<String,Object> readDoctorDeptStatus(String userFlow , String doctorFlow);
	
	Map<String,Object> readDoctorInfo(String userFlow , String doctorFlow);
	
	void enterDeptConfirm(String userFlow , String doctorFlow);
	
	Map<String,Object> readEvalInfo(String userFlow , String doctorFlow);
	
	void saveEvalInfo(String userFlow , String doctorFlow,String eval);

	List<Map<String, Object>> getActives(String userFlow, String doctorFlow, Integer pageIndex, Integer pageSize);

	Map<String,Object> readActivity(String dataFlow, String doctorFlow);

	Map<String, Object> readOutSecBrief(String doctorFlow);

	void saveOutSecBrief(String userFlow, String doctorFlow, HttpServletRequest request);

	Map<String, Object> readCycleOutSectionRecord(String userFlow, String doctorFlow);

	Map<String, Object> readExamInfo(String doctorFlow,String examInfoType);
	
	List<Map<String, Object>> getMarks(String doctorFlow,String examInfoType);

	Map<String, Object> readOutDops(String doctorFlow);

	Map<String, Object> readOutMiniCex(String doctorFlow);

	void saveOutDops(String userFlow, String doctorFlow, HttpServletRequest request);

	void saveOutMiniCex(String userFlow, String doctorFlow, HttpServletRequest request);

	List<Map<String, Object>> getWaitEvalOutSecBriefList(String userFlow);

	Map<String,Object> getWaitArrangeActivityCount(String userFlow);

	List<Map<String, Object>> getNeedConfirmActives(String userFlow, String doctorFlow, Integer pageIndex,Integer pageSize);

	List<Map<String, Object>> getNeedConfirmActiveUsers(String dataFlow);

	void confirmActiveUsers(String dataFlow, HttpServletRequest request);

	Float readTheoryScore(String doctorFlow);

}
