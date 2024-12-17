package com.pinde.sci.dao.jsres;

import com.pinde.core.model.*;
import com.pinde.core.model.ResSupervisioSubject;
import com.pinde.core.model.ResSupervisioSubjectUser;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JsresSupervisioSubjectExtMapper {
    List<Map<String,String>> searchManegeSupervisioList(Map<String,Object> param);

    List<SysUser> selectUserBysubjectActivitiFlows(@Param("subjectActivitiFlows") String subjectActivitiFlows);

    List<ResSupervisioSubject> selectSubjectActiveListByParam(Map<String, Object> param);

    List<Map<String,String>> searchBaseExpertSupervisio(Map<String,Object> param);

    List<ResSupervisioSubjectUser> searchSubjectAndUserLevedId(@Param("subjectFlow") String subjectFlow,@Param("userLevedId") String userLevedId);

    List<ResSupervisioSubjectRecords> selectRecordBySubjectFlowAndRoleFlag(@Param("subjectFlow") String subjectFlow, @Param("RoleFlag") String RoleFlag);

    //查询基地自己创建的项目
    List<ResSupervisioSubject> selectBaseSubjectList(Map<String,Object> param);

    List<ResSupervisioSubject> selectLocalSubjectListByParam(Map<String, Object> param);

    String searchSubjectNameNum( @Param("subjectName")  String subjectName);

    String searchSubExpertNum(@Param("subjectFlow") String subjectFlow);

    //管理专家和专业专家信息不分开
	List<ResSupervisioSubject> selectSubjectList(Map<String, Object> param);

	//管理专家和专业专家信息分开
	List<ResSupervisioSubject> selectSubjectListInfo(Map<String, Object> param);

    List<ResSupervisioSubject> selectGrobalSubjectListInfo(Map<String,Object> param);

    int delSubjectUserBySubjectFlow(@Param("subjectFlow") String subjectFlow);

    List<String> selectSupervisioUserFlowListByFlow(@Param("subjectFlow") String subjectFlow);

    List<ResSupervisioSubject> selectSubjectListByParam(Map<String, Object> param);

    List<Map<String,Object>> searchSpeAssignBySpeIdAndYear(@Param("speId") String speId, @Param("currYear") String currYear);

    List<Map<String,String>> searchEvaluationIndicators(ArrayList<String> list);

    List<ResSupervisioSubjectUser> selectSupervisioUserListByFlowAndUserLevelID(@Param("subjectFlow") String subjectFlow);

    int updateSubjectFeedback(Map<String, Object> param);
    //计算同一批次专业专家提交专业表单的质量控制分数
    Map<String, Object> selectAvgScoreBySubjectActivitiFlows(@Param("subjectActivitiFlows") String subjectActivitiFlows);


    List<ResOrgSpeAssign> searchSpeAll();

    List<ResHospSupervSubject> selectHospSuperList(HashMap<String, Object> map);

    List<ResHospSupervSubject> hospitalLeaderScoreList(Map<String, Object> map);

    List<SysDept> selectDeptByOrgFlow(@Param("orgFlow") String orgFlow);

    List<ResHospScoreTable> chooseTable(@Param("speId") String speId, @Param("inspectionType") String inspectionType);
}
