package com.pinde.res.biz.osca;

import com.pinde.sci.model.mo.OscaDoctorAssessment;
import com.pinde.sci.model.mo.OscaSkillsAssessment;
import com.pinde.sci.model.mo.OscaSubjectStation;

import java.util.List;
import java.util.Map;


public interface IOscaDoctorScoreBiz {
    //查询考核信息下的审核通过的学员
    List<OscaDoctorAssessment> queryDoctorList(String clinicalFlow);

    //按条件查询考核信息(得到clinicalFlowList)
    List<OscaSkillsAssessment> selectskillsAssessmentList(OscaSkillsAssessment oscaSkillsAssessment);

    //查询学员每站成绩
    List<Map<String,Object>> queryGradeList(Map<String, Object> map);

    //查询方案下设置的考站
    List<OscaSubjectStation> queryStationList(String subjectFlow);

    //考核信息单条记录查询
    OscaSkillsAssessment queryDataByFlow(String clinicalFlow);

    //查询基地全部专业考核总合格率
    String getPassPercent(Map<String, Object> paramMap1, Map<String, Object> paramMap2);

    //查询所有学员成绩
    List<Map<String,Object>> queryAllGradeList(Map<String, Object> map);

    //查询单个学员成绩
    List<Map<String,Object>> getSingleGrade(Map<String, Object> paramMap);
}
