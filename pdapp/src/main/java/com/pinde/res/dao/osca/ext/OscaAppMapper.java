package com.pinde.res.dao.osca.ext;

import com.pinde.res.model.osca.mo.*;
import com.pinde.sci.model.mo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OscaAppMapper {

	List<OscaSubjectStation> getTeaDocStation(Map<String, String> paramMap);
	List<OscaSubjectStation> getTeaSubDocStation(Map<String, String> paramMap);

	List<OscaSkillRoomTea> getTeaRooms(Map<String, String> param);

	List<OscaSkillRoomDoc> getOscaSkillRoomDocByDoc(Map<String, String> param);

	List<OscaSkillDocScore>  getDocScoreByParam(Map<String, String> param);

	List<OscaTeaScanDoc> getOscaTeaScanDoc(Map<String, String> param);

	List<OscaTeaScanDoc> getScanDocList(Map<String, Object> paramMap);

	List<OscaSkillDocScore> getTeaDocScores(@Param("userFlow") String userFlow,@Param("list") List<String> list);

	List<PubFile> findStationFiles(@Param("stationFlow") String stationFlow, @Param("orgFlow") String orgFlow);

	List<OscaSubjectStationFrom> getStationExamFroms(Map<String, String> param);

	OscaFrom getFromByScoreFlow(@Param("recordFlow") String recordFlow);

	List<OscaSkillDocScore> getNoFromScoreByParam(Map<String, String> param);

	List<OscaSkillDocScore> getNotRequiredFromScoreByParam(Map<String, String> param);

	List<Map<String,Object>> notExamStudentList(Map<String, Object> paramMap);

	List<OscaSkillsAssessment> getSelectDateSkills(@Param("orgFlow") String orgFlow, @Param("selectDate") String selectDate);

	List<OscaDoctorSkillsExt> getSelectDateDocSkills(@Param("orgFlow") String orgFlow, @Param("selectDate") String selectDate, @Param("speId") String speId);

	List<OscaSkillsRoomExt> getSelectDateSkillRooms(@Param("orgFlow") String orgFlow, @Param("selectDate") String selectDate);

	List<OscaSkillsPartnerExt> getSelectDateSkillPartners(@Param("orgFlow") String orgFlow, @Param("selectDate") String selectDate);

	List<OscaDoctorSkillsExt> getSelectDateDocSignIns(@Param("orgFlow") String orgFlow, @Param("selectDate") String selectDate, @Param("speId") String speId);

	List<OscaSkillsTimeExt> getSelectDateSkillTimes(@Param("orgFlow") String orgFlow, @Param("selectDate") String selectDate, @Param("speId") String speId);

	List<OscaDoctorSkillsExt> getSelectDateAudited(@Param("orgFlow") String orgFlow, @Param("selectDate") String selectDate, @Param("speId") String speId);

	List<Map<String,String>> getTeaList(Map<String, Object> paramMap);

	List<Map<String,String>> getNoSiginList(Map<String, Object> paramMap);

	List<OscaDoctorExamBean> getOscaDoctorExamList(Map<String, Object> paramMap);

	Map<String,String> getSelectDateMaxExamTime(@Param("orgFlow") String orgFlow, @Param("selectDate") String selectDate, @Param("speId") String speId);

	Double getDoctorExamScore(@Param("clinicalFlow") String clinicalFlow, @Param("doctorFlow") String doctorFlow);

	List<OscaSkillDocTeaScore> getOscaSkillRoomTeaScoreByDoc(Map<String, String> param);

	List<OscaSkillDocTeaScore> getOscaSkillDocTeaScores(@Param("doctorFlow") String doctorFlow, @Param("clinicalFlow") String clinicalFlow, @Param("stationFlow") String stationFlow);

	List<SysDict> getOrgRoomList(Map<String, Object> paramMap);

	List<Map<String,String>> getRoomInfoByRoomFlow(Map<String, Object> paramMap);

	List<Map<String,String>> getRoomPartnerExam(Map<String, Object> paramMap);

	Integer getRoomPartnerNotExam(Map<String, Object> paramMap);

	List<OscaSkillDocScore> getRequiredFromScoreByParam(Map<String, String> param);

	List<Map<String,Object>> getCalendarInfo(@Param("orgFlow") String orgFlow, @Param("month") String month);

	List<Map<String,Object>> getMonthInfo(@Param("orgFlow") String orgFlow, @Param("year") String year);

	List<Map<String,Object>> getTypeInfoByMonth(@Param("orgFlow") String orgFlow, @Param("month") String month);
	List<Map<String,Object>> getTypeInfoByYear(@Param("orgFlow") String orgFlow,@Param("year") String year);

    List<PubFile> findClinicalStationFiles(@Param("stationFlow") String stationFlow, @Param("clinicalFlow") String clinicalFlow, @Param("orgFlow") String orgFlow);

	List<SysDict> getOrgRooms(@Param("orgFlow") String orgFlow);
}
