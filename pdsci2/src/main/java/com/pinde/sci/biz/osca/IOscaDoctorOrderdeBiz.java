package com.pinde.sci.biz.osca;


import com.pinde.core.model.*;
import com.pinde.sci.model.mo.ResScore;
import com.pinde.sci.model.osca.OscaSkillsAssessmentExt;

import java.util.List;
import java.util.Map;

public interface IOscaDoctorOrderdeBiz {

    List<OscaSkillsAssessmentExt> skillsAssessmentList(Map<String, Object> map);

    int insertDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment);

    int updateDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment);

    List<OscaDoctorAssessment> selectDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment);

    int countDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment);


    List<OscaSubjectStation> searchSubjectsForDoctor(String clinicalFlow);

    /**
     * 查询考核信息
     * @param map
     * @return
     */
    List<OscaSkillsAssessmentExt> selectDoctorAssessmentInfo(Map<String, String> map);

    /**
     * 查询准考证信息
     * @param map
     * @return
     */
    List<OscaSkillsAssessmentExt> selectTicketInfo(Map<String, String> map);

    /**
     * 根据clinicalFlow查询考核信息
     * @param clinicalFlow
     * @return
     */
    OscaSkillsAssessment selectSkillsAssessmentByClinicalFlow(String clinicalFlow);

    OscaDoctorAssessment selectDoctorAssessmentByRecordFlow(String recordFlow);

    /**
     * 查询一年内参加结业考核次数
     * @param map
     * @return
     */
    List<OscaSkillsAssessmentExt> selectAssessmentIsNotLocalOneYear(Map<String, String> map);

    /**
     *我的预约信息查询
     * @param map
     * @return
     */
    List<OscaSkillsAssessmentExt> selectDoctorAssessmentList(Map<String, String> map);

    /**
     * 检查考核时间不重复
     * @param map
     * @return
     */
    int countOrderedTime(Map<String, String> map);

    /**
     * 获取学员结业年份
     * @param doctorFlow
     * @return
     */
    List<com.pinde.core.model.ResDoctorRecruit> selectDoctorGraduationYear(String doctorFlow);

    List<ResScore> selectResScore(String doctorFlow,String year);

    /**
     * 查询一阶段对应的住培专业
     * @param map
     * @return
     */
    List<String> searchTrainingSpeList(Map<String,Object> map);

    /**
     * 通过flow查询考核信息的考核时间
     * @param clinicalFlow
     * @return
     */
    List<OscaSkillsAssessmentTime> searchCheckTime(String clinicalFlow);
    //查询学员所有考站
    List<OscaSubjectStation> getStations(Map<String,Object> paramMap);
}
