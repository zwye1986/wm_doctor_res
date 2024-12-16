package com.pinde.sci.biz.osca;

import com.alibaba.fastjson.JSONArray;
import com.pinde.core.model.*;
import com.pinde.sci.model.mo.PubFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IOscaProvincialBiz {
    //根据条件查询预约信息
    List<OscaSkillsAssessment> searchSkillsAssessment(OscaSkillsAssessment skillsAssessment, List<SysDict> dictList);
    //查询某场考核学员通过审核报名人数
    int queryDoctorNum(String clinicalFlow);
    //查询某场考核学员信息列表
    List<Map<String,Object>> searchDoctorList(String clinicalFlow);
    //根据条件查询学员成绩
    List<Map<String,Object>> searchDoctorScoreList(Map<String,Object> paramMap);
    //查询某学员某次考试所有站点得分
    List<OscaSkillRoomDoc> queryDocScores(String doctorFlow, String clinicalFlow);
    //按次序查询某学员某次考试所有站点得分
    List<Map<String,Object>> queryDocScoresByOrder(String doctorFlow,String clinicalFlow);
    //查找所有考核方案
    List<OscaSubjectMain> searchSubjects(OscaSubjectMain subjectMain);
    //查找一个考核方案
    OscaSubjectMain readSubject(String subjectFlow);
    //保存一个考核方案
    int edit(OscaSubjectMain subjectMain);
    //保存一个考核方案(添加考核方案及关联信息专用)
    int edit2(OscaSubjectMain subjectMain);
    //编辑考站以及关联评分表
    int editStation(OscaSubjectStation subjectStation, List<Map<String, Object>> fromFlows, List<String> fileFlows);
    //编辑考站评分表关联信息
    int editSubjectStationFrom(OscaSubjectStationFrom subjectStationFrom);
    //根据方案主表flow查找对应考站
    List<OscaSubjectStation> searchSubjectStationByMain(String subjectStationFlow);
    //根据站点flow查询对应评分表
    List<OscaSubjectStationFrom> searchFromByStaion(String stationFlow,String orgFlow);
    //编辑考站
    int editStationOnly(OscaSubjectStation subjectStation);
    //保存省级方案医院自定义表单
    int saveHospitalForms(String stationFlow, String stationName, List<Map<String, Object>> fromFlowMaps, List<String> fileFlows);

    List<PubFile> findStationFiles(String stationFlow, String orgFlow);

    Map<String,String> uploadFile(MultipartFile checkFile, String fileAddress);

    List<Map<String,String>> getSubjectParts(String subjectFlow);

    OscaSubjectPartScore getSubjectPartScore(String subjectFlow, String partFlow);

    OscaSubjectStationScore getSubjectStationScore(String subjectFlow, String stationFlow);

    int savePassedInfo(OscaSubjectMain m, String allScore, JSONArray stationScoreList, JSONArray partScoreList);

    List<OscaSubjectPartScore> getOscaSubjectPartScores(String subjectFlow);

    List<OscaSubjectStationScore> getOscaSubjectStationScores(String subjectFlow);

    List<PubFile> findClinicalStationFiles(String stationFlow, String clinicalFlow, String orgFlow);

    int saveClinicalFiles(String stationFlow, String clinicalFlow, String orgFlow, List<String> fileFlows);
}
