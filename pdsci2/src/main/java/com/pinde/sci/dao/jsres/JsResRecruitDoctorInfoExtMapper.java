package com.pinde.sci.dao.jsres;

import com.pinde.sci.form.jsres.JsresDoctorInfoExt;
import com.pinde.core.model.JsRecruitDocInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsResRecruitDoctorInfoExtMapper {
	List<Map<String,Object>> searchRecruitDoctorInfos(Map<String, Object> param);

	List<Map<String,Object>> zltjCityDoctorType(Map<String, Object> param);

	List<Map<String,Object>> zltjCitySpeType(Map<String, Object> param);

	List<Map<String,Object>> zltjOrgDoctorType(Map<String, Object> param);

	List<Map<String,Object>> orgListByOrgFlow(@Param("jointOrgFlowList") List<String> jointOrgFlowList);

	List<Map<String,Object>> zltjOrgSpeType(Map<String, Object> param);

	List<Map<String,Object>> zltjOrgLocalList(Map<String, Object> param);

	List<Map<String,Object>> cityOrgListByOrgFlow(@Param("jointOrgFlowList") List<String> jointOrgFlowList);

	List<Map<String,Object>> zltjOrgCityDoctorType(Map<String, Object> param);

	List<JsRecruitDocInfoExt> searchRecruitDoctorInfosByResume(Map<String, Object> param);

	/**
	 * @Author shengl
	 * @Description // 查询录取学员数据
	 * @Date  2020-08-28
	 * @Param [orgFlow]
     * @return java.util.List<com.pinde.core.model.JsresRecruitDocInfo>
	**/
    List<Map<String,Object>> getRecruitSpeInfo(@Param("orgFlow") String orgFlow,@Param("sessionNumber") String sessionNumber,@Param("flag") String flag);

	List<Map<String,Object>> getRecruitSpeInfoNew(Map<String,Object> map);

	List<Map<String,Object>> getRecruitSpeInfoNewAcc(Map<String,Object> map);

    List<JsresDoctorInfoExt> searchRecruitDoctorInfosByResume2(Map<String, Object> param);

	List<JsresDoctorInfoExt> searchRecruitDoctorInfosByResume3(Map<String, Object> param);

	List<Map<String,Object>> zltjOrgLocalListNew(Map<String, Object> param);

	List<Map<String, Object>> getOrgRecruitSpeInfo(Map<String, Object> params);

    List<String> getHistoryPhotoTimeList();
}
