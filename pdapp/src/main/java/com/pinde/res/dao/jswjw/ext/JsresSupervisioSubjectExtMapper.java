package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.ResSupervisioSubjectUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsresSupervisioSubjectExtMapper {

    int delSubjectUserBySubjectFlow(@Param("subjectFlow") String subjectFlow);

    List<String> selectSupervisioUserFlowListByFlow(@Param("subjectFlow") String subjectFlow);

    List<Map<String,Object>> selectSubjectListByParam(Map<String, Object> param);

    List<Map<String,Object>> selectSubjectListByParamNew(Map<String, Object> param);

    List<Map<String,Object>> searchSpeAssignBySpeIdAndYear(@Param("speId") String speId, @Param("currYear") String currYear);

    List<ResSupervisioSubjectUser> selectSupervisioUserListByFlowAndUserLevelID(@Param("subjectFlow") String subjectFlow);

    int updateSubjectFeedback(Map<String, Object> param);
    //计算同一批次专业专家提交专业表单的质量控制分数
    Map<String, Object> selectAvgScoreBySubjectActivitiFlows(@Param("subjectActivitiFlows") String subjectActivitiFlows);
}
