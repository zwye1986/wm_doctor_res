package com.pinde.sci.biz.jsres;

import com.pinde.sci.form.jsres.JsresDoctorInfoExt;
import com.pinde.sci.model.jsres.JsRecruitDocInfoExt;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.JsresRecruitDocInfo;
import com.pinde.sci.model.mo.JsresRecruitInfo;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResRecruitHistory;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJsResRecruitDoctorInfoBiz {

    List<Map<String,Object>> searchRecruitDoctorInfos(Map<String, Object> param);

    List<Map<String,Object>> zltjCityDoctorType(Map<String, Object> param);

    List<Map<String,Object>> zltjCitySpeType(Map<String, Object> param);

    List<Map<String,Object>> zltjOrgDoctorType(Map<String, Object> param);

    List<Map<String,Object>> orgListByOrgFlow(List<String> jointOrgFlowList);

    List<Map<String,Object>> zltjOrgSpeType(Map<String, Object> param);

    List<Map<String,Object>> zltjOrgLocalList(Map<String, Object> param);

    JsresRecruitDocInfo readRecruit(String recruitFlow);

    JsresRecruitInfo getRecruitInfo( String recruitFlow);

    List<Map<String,Object>> cityOrgListByOrgFlow(List<String> jointOrgFlowList);

    List<Map<String,Object>> zltjOrgCityDoctorType(Map<String, Object> param);

    JsresRecruitInfo getRecruitInfoBysessionNumber(String userFlow, String sessionNumber);

    List<JsRecruitDocInfoExt> searchRecruitDoctorInfosByResume(Map<String, Object> param);

    /**
     * @Author shengl
     * @Description // 查询录取学员数据
     * @Date  2020-08-28
     * @Param [orgFlow]
    **/
    List<Map<String,Object>> getRecruitSpeInfo(String orgFlow,String sessionNumber,String flag);
    List<Map<String,Object>> getRecruitSpeInfoNew(Map<String,Object> map);
    List<Map<String,Object>> getRecruitSpeInfoNewAcc(Map<String,Object> map);
    /**
     * @Author lim
     * @Description // 查询学员培训记录
     * @Date  2020-09-25
     * @Param [doctorFlow]
     **/
    List<ResDoctorRecruit> searchRecruitList(String doctorFlow);

    List<JsresDoctorInfoExt> searchRecruitDoctorInfosByResume2(Map<String, Object> param);

    List<JsresDoctorInfoExt> searchRecruitDoctorInfosByResume3(Map<String, Object> param);

    List<ResDoctorRecruit> searchRecruitListNew(String doctorFlow);

    List<JsResDoctorRecruitExt> searchRecruitExtList(Map<String, Object> param);

    List<Map<String,Object>> zltjOrgLocalListNew(Map<String, Object> param);

    List<Map<String, Object>> getOrgRecruitSpeInfo(Map<String, Object> params);

    List<ResRecruitHistory> getRecruitHistoryInfo(String photoTime, String orgFlow, String speId);

    List<String> getHistoryPhotoTimeList();

    List<ResRecruitHistory> getRecruitHistoryBySessionNumber(String sessionNumber);

    void saveRecruitHistory(ResRecruitHistory history);

    void updateRecruitHistory(ResRecruitHistory history);
}
