package com.pinde.res.dao.osca.ext;

import com.pinde.res.model.osca.mo.OscaSkillsAssessmentExt;
import com.pinde.sci.model.mo.OscaSkillsAssessment;
import com.pinde.sci.model.mo.OscaSubjectStation;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OscaSkillsAssessmentExtMapper {
    List<OscaSkillsAssessmentExt> searchAllSkillsAssessments(Map<String, Object> map);

    /**
     * 查询考核信息
     * @param map
     * @return
     */
    List<OscaSkillsAssessmentExt> searchDoctorAssessment(Map<String, String> map);

    /**
     * 查询准考证信息
     * @param map
     * @return
     */
    List<OscaSkillsAssessmentExt> searchTicketInfo(Map<String, String> map);

    /**
     * 查询一年内参加结业考核次数
     * @param map
     * @return
     */
    List<OscaSkillsAssessmentExt> searchAssessmentIsNotLocalOneYear(Map<String, String> map);

    /**
     * 我的预约信息查询
     * @param map
     * @return
     */
    List<OscaSkillsAssessmentExt> searchDoctorAssessmentList(Map<String, String> map);

    int countOrderedTime(Map<String, String> map);

    List<ResDoctorRecruit> searchDoctorGraduationYear(String doctorFlow);

    //查询某市的考核信息
    List<OscaSkillsAssessment> searchCitySkillsAssessment(Map<String, Object> paramMap);
    //查询一阶段对应的住培专业
    List<String> searchTrainingSpeList(Map<String, Object> map);

    Map<String,String> getSkillsAssessmentOrder(@Param("clinicalYear") String clinicalYear);
    //查询所有考站
    List<OscaSubjectStation> getStations(Map<String, Object> paramMap);
}
