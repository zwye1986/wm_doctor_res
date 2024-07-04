package com.pinde.res.biz.osca;

import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.sci.model.mo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IOscaAppBiz {



	public SysUser findByUserCode(String userCode);


	public ResDoctor readResDoctor(String userFlow);

	/**
	 * 通过flow读取用户信息
	 * @param userFlow
	 * @return
	 */
	SysUser readSysUser(String userFlow);


	String getCfgCode(String code);


	SysOrg getOrg(String orgFlow);

	OscaSkillsAssessment getOscaSkillsAssessmentByFlow(String clinicalFlow);

	OscaDoctorAssessment getOscaDoctorAssessmentByFlow(String recordFlow);

	List<OscaSubjectStation> getOscaSubjectStations(String subjectFlow);

	OscaSubjectMain getOscaSubjectMain(String subjectFlow);

	List<OscaSubjectStation> getTeaDocStation(Map<String, String> paramMap);

	List<OscaSkillRoomTea> getTeaRooms(Map<String, String> param);

	OscaSkillRoomDoc getOscaSkillRoomDocByDoc(Map<String, String> param);

	List<OscaSkillDocScore> getDocScoreByParam(Map<String, String> param);

	OscaSkillRoom getOscaskillRoomByFlow(String examRoomFlow);

	OscaTeaScanDoc getOscaTeaScanDoc(Map<String, String> param);

	int editTeaScanDoc(OscaTeaScanDoc b, SysUser user);

	OscaSubjectStation getOscaSubjectStationByFlow(String stationFlow);


	int editOscaSkillRoomDoc(OscaSkillRoomDoc docStation, SysUser user);

	List<OscaSubjectStationFrom> getFromsByStationFlow(String stationFlow);

	OscaSkillDocScore getOscaSkillDocScoreByFlow(String scoreFlow);

	int editOscaSkillDocScore(OscaSkillDocScore score, SysUser tea);

	OscaFrom getFromByFlow(String fromFlow);

	Map<String,Object> parseFromXml(String content);

	String getScoreXml(HttpServletRequest request);

	void editOscaSkillScoreDeatil(Map<String, Map<String, Object>> dateMap, SysUser tea, OscaFrom from, OscaSkillDocScore score);

	OscaSkillScoreDeatil getScoreDetail(String key, String scoreFlow);

	int editOneOscaSkillScoreDeatil(OscaSkillScoreDeatil deatil, SysUser user);


    Map<String, Object> parseFromAllItemXml(String content);


    Map<String, Object> parseGuDingFromXml(String fromContent);

	List<OscaSkillScoreDeatil> getScoreDeatilsByScoreFlow(String scoreFlow);

	Map<String,Object> getDocStationAllScore(Map<String, String> param);

	Map<String,Object> getDocStationAllSaveScore(Map<String, String> param);

	List<OscaTeaScanDoc> getScanDocList(Map<String, Object> paramMap);

	List<OscaSkillDocScore> getTeaDocScores(String userFlow, List<String> list);

	OscaDoctorAssessment getOscaDoctorAssessment(String clinicalFlow, String doctorFlow);

	List<OscaSkillRoomDoc> getOscaSkillRoomDocs(String clinicalFlow, String doctorFlow);

	int editOscaDoctorAssessment(OscaDoctorAssessment doctorAssessment, SysUser user);

	List<OscaSubjectStationFrom> getFromsByOrgFlow(String stationFlow, String orgFlow);

	List<FromTitle> parseFromXmlForList(String content);

	OscaDoctorAssessment getOscaDoctorAssessmentByTickNumber(String tickNumber);

	List<OscaSubjectStation> getTeaSubDocStation(Map<String, String> p);

	OscaSkillDocTeaScore getOscaSkillRoomTeaScoreByDoc(Map<String, String> param);

	void scoreBatchSubmit(String userFlow, String []flows);

	void updateTeaScanDocStatus(String doctorFlow, String clinicalFlow, SysUser tea);

	void updateDoctorAssessment(String clinicalFlow, String doctorFlow, SysUser tea);

	void updateDoctorAssessmentSavePass(String clinicalFlow, String doctorFlow, SysUser tea);

	void scoreBatchSubmit(String []flow,String userFlow,String doctorFlow);

	List<PubFile> findStationFiles(String stationFlow, String orgFlow);

	List<OscaSubjectStationFrom> getStationExamFroms(Map<String, String> param);

	OscaFrom getFromByScoreFlow(String recordFlow);

	OscaSkillDocScore getNoFromScoreByParam(Map<String, String> param);

	OscaSkillDocScore getNotRequiredFromScoreByParam(Map<String, String> param);

	List<Map<String,Object>> notExamStudentList(Map<String, Object> paramMap);

	void updateDocSaveStatus(String doctorFlow, SysUser tea);

	int editOscaSkillRoomTeaScore(OscaSkillDocTeaScore teaScore, SysUser tea);

	OscaExamDifferScore readDiffierScoreByOrgFlow(String orgFlow);

	List<OscaSubjectPartScore> getOscaSubjectPartScores(String subjectFlow);

	List<OscaSubjectStationScore> getOscaSubjectStationScores(String subjectFlow);

	OscaSkillDocScore getRequiredFromScoreByParam(Map<String, String> param);

	List<SysUserRole> getSysUserRole(String userFlow, String wsId);

	SysUser findByUserEmail(String userEmail);

	SysUser findByIdNo(String idNo);

	SysUser findByUserPhone(String userPhone);

	int saveOsceRegistUser(SysUser registerUser);

	int edit(SysUser user, SysUser saveuser);

	void editDoctor(ResDoctor doctor, SysUser saveuser);

	List<SysOrg> queryAllSysOrg(SysOrg searchOrg);

	List<SysDict> getDictListByDictId(String id);

	int completeOsceRegistUser(SysUser sysUser,ResDoctor doctor);

	OscaDoctorAssessment getAuditOscaDocInfo(String userFlow);

	List<InxInfo> findNotice(InxInfo info);

	InxInfo readInxInfo(String infoFlow);

    List<PubFile> findClinicalStationFiles(String stationFlow, String clinicalFlow, String orgFlow);

	List<OscaSkillRoom> findClinicalStationRooms(String stationFlow, String clinicalFlow, String orgFlow);

	PubFile readFile(String fileFlow);

	OscaRoomFile getRoomFile(String roomFlow, String orgFlow);

    OscaSkillDocStation getDocSkillStation(String stationFlow, String userFlow, String clinicalFlow);
	List<OscaSkillDocStation> getDocSkillStations(  String userFlow, String clinicalFlow);

	void saveDocSkillStations(List<OscaSkillDocStation> docStations, SysUser user);

	void saveDocStation(OscaSkillDocStation docStation, SysUser saveuser);

    int saveRoomFile(OscaRoomFile roomFile, SysUser user);
}