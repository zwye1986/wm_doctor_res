package com.pinde.sci.dao.jszy;

import com.pinde.core.model.JszyDoctorInfoExt;
import com.pinde.core.model.JszyResDoctorRecruitExt;

import java.util.List;
import java.util.Map;

public interface JszyResDoctorRecruitExtMapper {
	List<JszyResDoctorRecruitExt> searchJsDoctorRecruitExtList(Map<String, Object> paramMap);
	List<JszyResDoctorRecruitExt> searchJsDoctorScoreExtList(Map<String, Object> paramMap);
	List<JszyResDoctorRecruitExt> searchDoctorSkillAndTheoryScoreExts(Map<String, Object> paramMap);
	List<JszyResDoctorRecruitExt> searchJsDoctorSkillScoreExtList(Map<String, Object> paramMap);
	List<JszyResDoctorRecruitExt> searchJsDoctorPublicScoreExtList(Map<String, Object> paramMap);

	int searchBasePassCount(Map<String, Object> paramMap);
	
	List<JszyDoctorInfoExt> searchDoctorInfoExts(Map<String, Object> paramMap);
	
	List<JszyDoctorInfoExt> searchDoctorInfo(Map<String, Object> paramMap);
	
	List<JszyResDoctorRecruitExt> searchDoctorRecruit(Map<String, Object> paramMap);
	
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
	
	List<JszyDoctorInfoExt> statisticNoAppUser(Map<String, Object> paramMap);
	List<Map<String,Object>> doctorScoreQuery(Map<String, Object> doctorRecruitMap);


	Map doctorCounMap(Map paramMap);

	List<Map<String,Object>> getCurrDocDetails(Map<String, Object> paramMap);

	List<Map<String,Object>> statisticJointCountByOrg(Map<String, Object> paramMap);

	List<Map<String,Object>> zlxytj(Map<String,Object> param);

	List<Map<String,Object>> zlxytj2(Map<String, Object> param);

	List<Map<String,Object>> zlxytjJoint(Map<String, Object> param);

	List<Map<String,Object>> zlxytj3(Map<String, Object> param);

	List<JszyResDoctorRecruitExt> searchDoctorCertificateList(Map<String, Object> doctorRecruitMap);

	List<Map<String,Object>> zlxytj4(Map<String, Object> param);

    List<Map<String,Object>> zlxytj5(Map<String, Object> param);

    List<Map<String,Object>> zpxytj(Map<String, Object> param);

    List<Map<String,Object>> zpxytjChanges(Map<String, Object> param);
    List<Map<String,Object>> zpxytjChangesDetail(Map<String, Object> param);
}
