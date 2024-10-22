package com.pinde.sci.dao.jsres;

import com.pinde.sci.model.jsres.JsDoctorInfoExt;
import com.pinde.sci.model.jsres.JsGraduateExt;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsResDoctorRecruitExtMapper {
	List<JsResDoctorRecruitExt> searchJsDoctorRecruitExtList(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchJsDoctorRecruitExtList4(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchJsDoctorRecruitExtList2(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchJsDoctorRecruitExtBaseList(Map<String, Object> paramMap);
	//查询招录学员分组排名信息
	List<JsResDoctorRecruitExt> searchJsDoctorRecruitExtList3(Map<String, Object> paramMap);

	List<JsResDoctorRecruitExt> searchJsDoctorScoreExtList(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchDoctorSkillAndTheoryScoreExts(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchDoctorSkillAndTheoryScoreExts1(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchJsDoctorSkillScoreExtList(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchJsDoctorTheoryScoreExtList(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchJsDoctorPublicScoreExtList(Map<String, Object> paramMap);

	Integer selectCountByDoctFlow(String doctorFlow);

	int searchBasePassCount(Map<String, Object> paramMap);
	
	List<JsDoctorInfoExt> searchDoctorInfoExts(Map<String, Object> paramMap);

	List<JsDoctorInfoExt> searchDoctorInfoExts2(Map<String, Object> paramMap);

	List<JsDoctorInfoExt> searchDoctorInfoBaseExts(Map<String, Object> paramMap);

	List<JsDoctorInfoExt> searchDoctorInfo(Map<String, Object> paramMap);
	
	List<JsResDoctorRecruitExt> searchDoctorRecruit(Map<String, Object> paramMap);
	
	int searchTrainInfoCount(Map<String, Object> paramMap);
	
	int doctorCount(Map<String, Object> paramMap);
	
	int joingorgCount(Map<String, Object> paramMap);
	
	List<Map<String,Object>> statisticDocCouByType(Map<String, Object> paramMap);
	
	List<Map<String,Object>> statisticJointCount(Map<String, Object> paramMap);
	
	List<Map<String,Object>> searchJointOrgList(Map<String, Object> paramMap);
	
	List<Map<String,Object>> statisticDocCountByOrg(Map<String, Object> paramMap);
	
	List<Map<String,Object>> statisticAppCountByOrg(Map<String, Object> paramMap);
	
	List<Map<String,Object>> statisticRealAppCount(Map<String, Object> paramMap);
	
	List<Map<String,Object>> statisticDocCountByOrgForTime(Map<String, Object> paramMap);
	
	List<JsDoctorInfoExt> statisticNoAppUser(Map<String, Object> paramMap);

	List<JsDoctorInfoExt> statisticNoAppUserByJoint(Map<String, Object> paramMap);

List<Map<String,Object>> doctorScoreQuery(Map<String, Object> doctorRecruitMap);

	List<String> searchDoctorNum(@Param("doctorTypeId")List<String> doctorTypeId,@Param("doctorStatusId")String doctorStatusId,@Param("catSpeId")String catSpeId);

	List<String> searchDoctorNum2(@Param("doctorTypeId")List<String> doctorTypeId,@Param("catSpeId")String catSpeId);

	String searchDoctorNumYear(@Param("doctorStatusId")String doctorStatusId,@Param("catSpeId")String catSpeId);

	String searchDoctorNumYear2(@Param("catSpeId")String catSpeId);

	List<Map<String,Object>> searchDoctorTrainingNum(@Param("sessionNumber")String sessionNumber,@Param("statisticsType")String statisticsType,@Param("catSpeId")String catSpeId);

	List<Map<String,Object>> searchDoctorTrainingNumWithNotJoin(@Param("sessionNumber")String sessionNumber,@Param("statisticsType")String statisticsType,@Param("catSpeId")String catSpeId);

	List<Map<String,Object>> searchSpeDoctorTrainingNum(@Param("sessionNumber")String sessionNumber,@Param("statisticsType")String statisticsType,@Param("catSpeId")String catSpeId);

	List<Map<String,Object>> searchDoctorRecruitNum(@Param("sessionNumber")String sessionNumber,@Param("catSpeId")String catSpeId);

	List<Map<String,Object>> searchDoctorRecruitNumWithNotJoin(@Param("sessionNumber")String sessionNumber,@Param("catSpeId")String catSpeId);

	List<Map<String,Object>> searchSpeDoctorRecruitNum(@Param("sessionNumber")String sessionNumber,@Param("catSpeId")String catSpeId);

	Map doctorCounMap(Map<String, Object> paramMap);

	List<Map<String,Object>> getCurrDocDetails(Map<String, Object> paramMap);

	Map<String,Object> sumCountAudit(Map<String,Object> paramMap);

	Map<String,Object> sumCountAuditRes(Map<String,Object> paramMap);

	List<Map<String,Object>> statisticJointCountByOrg(Map<String, Object> paramMap);

	/****************************高******校******管******理******员******角******色************************************************/

	List<JsResDoctorRecruitExt> searchJsDoctorRecruitExtListForUni(Map<String, Object> doctorRecruitMap);

	List<Map<String,Object>> doctorScoreQueryForUni(Map<String, Object> param);

	List<JsResDoctorRecruitExt> searchDoctorSkillScoreForUni(Map<String, Object> param);
	List<JsResDoctorRecruitExt> searchDoctorSkillScoreForUni1(Map<String, Object> param);

	List<JsDoctorInfoExt> searchDoctorInfoExtsForUni(Map<String, Object> paramMap);

	Map doctorCountMapForUni(Map paramMap);

	List<Map<String,Object>> getCurrDocDetailsForUni(Map<String, Object> paramMap);

	List<Map<String,String>> searchOrgNotUseAppDoc(Map<String, Object> param);
	List<Map<String,String>> findSchArrengResultByDoctorAndYearMonth(Map<String, Object> param);

	List<JsResDoctorRecruitExt> searchDoctorManualForUni(Map<String, Object> paramMap);

	Map<String,Object> getDocProcessArea(@Param("doctorFlow") String doctorFlow, @Param("rotationFlow") String rotationFlow);

	List<JsGraduateExt> graduateExtList(Map<String,Object> parMap);

	List<JsResDoctorRecruitExt> searchDoctorCertificateList(Map<String, Object> doctorRecruitMap);

	List<JsResDoctorRecruitExt> searchDoctorCertificateList2(Map<String, Object> doctorRecruitMap);

	Map<String,Object> getOrgNumByCityId( @Param(value="orgFlow")String orgFlow,@Param(value="orgCityId") String orgCityId,@Param(value="year") String year,@Param(value="yearbefore") String yearbefore);

	Map<String,Object> getQuanKe(@Param(value="orgCityId") String orgCityId,@Param(value="year") String year,@Param(value="yearbefore") String yearbefore);

	Map<String,Object> getFeiQuanKe(@Param(value="orgCityId") String orgCityId,@Param(value="year") String year,@Param(value="yearbefore") String yearbefore);

	Map<String,Object> getOrgCodeAndNum(@Param(value="orgFlow") String orgFlow,@Param(value="year") String year);

	List<Map<String,Object>> zlxytj(@Param("sessionNumber") String sessionNumber);
	List<Map<String,Object>> zlxytjold(@Param("sessionNumber") String sessionNumber);

	List<Map<String,Object>> zlxytj2(Map<String, Object> param);
	List<Map<String,Object>> zlxytj2old(Map<String, Object> param);

	List<Map<String,Object>> zlxytjJoint(Map<String, Object> param);
	List<Map<String,Object>> zlxytjJointold(Map<String, Object> param);
	List<JsDoctorInfoExt> searchJsDoctorRecruitExtList1(Map<String, Object> paramMap);

	List<JsResDoctorRecruitExt> searchJsDoctorRecruitExtListNew(Map<String, Object> paramMap);

	List<JsResDoctorRecruitExt> searchResDoctorRecruitExtList(Map<String, Object> doctorRecruitMap);

	List<JsResDoctorRecruitExt> searchResDoctorRecruitExtList2(Map<String, Object> doctorRecruitMap);

	List<JsDoctorInfoExt> resDoctorInfoExtListNew(Map<String, Object> doctorRecruitMap);

	List<JsResDoctorRecruitExt> searchNotCertificateList(Map<String, Object> doctorRecruitMap);

	Map<String,String> searchCertificateNo(@Param("recruitFlow") String recruitFlow,@Param("orgFlow") String orgFlow,@Param("graduationYear") String graduationYear);

	List<JsResDoctorRecruitExt> searchRecruitExtList(Map<String, Object> param);

	List<JsResDoctorRecruitExt> searchJsDoctorRecruitExtList3New(Map<String, Object> doctorRecruitMap);
	//住院医师 在培 学员数据填写量查询（绩效-学员填写量）
    List<Map<String, Object>> searchDoctorData(Map<String, Object> param);

    List<Map<String, Object>> searchDoctorDataNew(Map<String, Object> param);

    List<Map<String, Object>> searchDoctorDataNew2(Map<String, Object> param);

    int autoAddData();

    int delData();
	// 基地下带教老师
	List<Map<String, Object>> searchTeacherUserList(Map<String, Object> param);
	//带教数据审核量查询（绩效-带教审核量）
	List<Map<String, Object>> searchTeacherAuditList(Map<String, Object> param);

	void jsresDoctorWorkPro();

	List<Map<String, Object>> countTrainingDoctor(Map<String, Object> param);
}
