package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.mo.SysOrg;

import java.util.List;
import java.util.Map;

public interface IJswjwChargeBiz {

    List<Map<String,Object>> searchDoctorRecruitList(Map<String, String> param);

    List<SysOrg> searchOrgList(String orgCityId);

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

    List<SysDict> searchSpeList(String dictTypeId);
}
