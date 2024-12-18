package com.pinde.res.biz.osca;

import com.pinde.core.model.OscaSkillDocTeaScore;
import com.pinde.core.model.OscaSkillsAssessment;
import com.pinde.core.model.SysDict;
import com.pinde.res.model.osca.mo.*;

import java.util.List;
import java.util.Map;

public interface IOscaAdminAppBiz {


	List<OscaSkillsAssessment> getSelectDateSkills(String orgFlow, String selectDate);

	List<OscaDoctorSkillsExt> getSelectDateDocSkills(String orgFlow, String selectDate, String speId);

	List<OscaSkillsRoomExt> getSelectDateSkillRooms(String orgFlow, String selectDate);

	List<OscaSkillsPartnerExt> getSelectDateSkillPartners(String orgFlow, String selectDate);

	List<OscaDoctorSkillsExt> getSelectDateDocSignIns(String orgFlow, String selectDate,String speId);

	List<OscaSkillsTimeExt> getSelectDateSkillTimes(String orgFlow, String selectDate, String speId);

	List<OscaDoctorSkillsExt> getSelectDateAudited(String orgFlow, String selectDate, String speId);

	List<Map<String,String>> getTeaList(Map<String, Object> paramMap);

	List<Map<String,String>> getNoSiginList(Map<String, Object> paramMap);

	List<OscaDoctorExamBean> getOscaDoctorExamList(Map<String, Object> paramMap);

	Map<String,String> getSelectDateMaxExamTime(String orgFlow, String selectDate, String speId);

	Double getDoctorExamScore(String clinicalFlow, String doctorFlow);

	List<OscaSkillDocTeaScore> getOscaSkillDocTeaScores(String doctorFlow, String clinicalFlow, String stationFlow);

	List<SysDict> getOrgRoomList(Map<String, Object> paramMap);

	List<Map<String,String>> getRoomInfoByRoomFlow(Map<String, Object> paramMap);

	List<Map<String,String>> getRoomPartnerExam(Map<String, Object> paramMap);

	Integer getRoomPartnerNotExam(Map<String, Object> paramMap);

	List<Map<String,Object>> getCalendarInfo(String orgFlow, String month);

	List<Map<String,Object>> getMonthInfo(String orgFlow, String year);

	Map<String,Object> getTypeInfoByMonth(String orgFlow, String month);

	Map<String, Object> getTypeInfoByYear(String orgFlow, String year);

    List<SysDict> getOrgRooms(String orgFlow);
}