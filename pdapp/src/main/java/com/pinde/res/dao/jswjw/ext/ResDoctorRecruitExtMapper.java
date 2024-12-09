package com.pinde.res.dao.jswjw.ext;


import com.pinde.core.model.ResDoctorRecruit;

import java.util.List;
import java.util.Map;

public interface ResDoctorRecruitExtMapper {

	List<Map<String,Object>> searchDoctorRecruitList(Map<String, String> param);

	List<Map<String,Object>> searchDoctorRecruitTrainingList(Map<String, String> param);

	List<Map<String,Object>> searchDoctorReductionList(Map<String, String> param);

	List<Map<String,Object>> searchSpeChangeList(Map<String, String> param);

	List<Map<String,Object>> searchDelayList(Map<String, String> param);

	List<Map<String,Object>> searchReturnList(Map<String, String> param);

	List<Map<String,Object>> searchBlackList(Map<String, String> param);

	List<Map<String,Object>> searchInDeptList(Map<String, String> param);

	List<Map<String,Object>> searchOutDeptList(Map<String, String> param);

	List<Map<String,Object>> searchActivityList(Map<String, String> param);

	List<Map<String,Object>> searchGradutionList(Map<String, String> param);

	List<Map<String,Object>> searchSignList(Map<String, String> param);

	List<Map<String,Object>> searchTheoryScoreList(Map<String, String> param);

	List<Map<String,Object>> searchSkillScoreList(Map<String, String> param);

	List<Map<String,Object>> searchDoctorRecruitAllList(Map<String, String> param);

	List<Map<String,Object>> searchDoctorRecruitTrainingAllList(Map<String, String> param);

	List<Map<String,Object>> searchDoctorReductionAllList(Map<String, String> param);

	List<Map<String,Object>> searchSpeChangeAllList(Map<String, String> param);

	List<Map<String,Object>> searchDelayAllList(Map<String, String> param);

	List<Map<String,Object>> searchReturnAllList(Map<String, String> param);

	List<Map<String,Object>> searchBlackAllList(Map<String, String> param);

	List<Map<String,Object>> searchInDeptAllList(Map<String, String> param);

	List<Map<String,Object>> searchOutDeptAllList(Map<String, String> param);

	List<Map<String,Object>> searchAssignInfoListNew(Map<String, Object> paramMap);

	List<Map<String,String>> searchAssignOrgSpeListNew(Map<String, String> param);

	List<ResDoctorRecruit> getDoctorRecruitInfo(String doctorFlow);

    List<Map<String, Object>> searchDoctorDataNew2(Map<String, Object> param);

    List<Map<String, Object>> searchTeacherUserList(Map<String, Object> param);

	List<Map<String, Object>> searchTeacherAuditList(Map<String, Object> param);

    List<Map<String, Object>> queryExamSignUpList(Map<String, Object> param);
}
