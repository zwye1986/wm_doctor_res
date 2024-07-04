package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IJswjwTeacherBiz {

    /**
     * 根据教师查询一系列用户，且去重复
     * @param teacherUserFlow
     * @param deptFlow
     * @return
     */
    int schDoctorSchProcessDistinctQuery(String teacherUserFlow, String deptFlow);

    int schDoctorSchProcessTeacherQuery(String teacherUserFlow, String deptFlow);

    /**
     * 查询所有待审核数据
     * @param teacherUserFlow
     * @return
     */
    int schDoctorSchProcessWaitingExamineQuery(String teacherUserFlow);

    /**
     * 所有带教过的学员
     * @param map
     * @return
     */
    List<Map<String,String>> schDoctorSchProcessQuery(Map<String, String> map);

    /**
     * 学员填写的数据
     * @param processFlow
     * @param doctorFlow
     * @return
     */
    List<ResRec> searchRecByProcess(String processFlow,String doctorFlow);

    /**
     * 排班
     * @param resultFlow
     * @return
     */
    SchArrangeResult readSchArrangeResult(String resultFlow);

    /**
     * 组
     * @param groupFlow
     * @return
     */
    SchRotationGroup readSchRotationGroup(String groupFlow);
    /**
     * 通过groupFlow同standardDeptId查询
     * @param groupFlow,standardDeptId
     * @return
     */
    SchRotationDept searchGroupFlowAndStandardDeptIdQuery(String groupFlow, String standardDeptId);

    /**
     *
     * @param processFlow
     * @param docFlow
     * @return
     */
    List<ResRec> searchRecByProcessAndRecType(String processFlow, String docFlow,String recType,String biaoJi);

    Map<String,Object> parseRecContent(String recContent);

    List<ResRec> searchRecByProcessWithBLOBs(List<String> recTypes, String processFlow, String userFlow);

    ResRec searResRecZhuZhi(String formFileName, String recFlow, String operUserFlow, String roleFlag, String processFlow, String recordFlow,String userFlow, HttpServletRequest req);

    List<Map<String,String>> schDoctorSchProcessInfoQuery(Map<String, String> schArrangeResultMap);

    ResScore readScoreByProcessFlow(String processFlow);

    List<Map<String,String>> schDoctorSchProcessEvalList(Map<String, String> schArrangeResultMap);

    List<Map<String,Object>> findProcessEvals(Map<String, Object> params);

    List<Map<String,String>> schDoctorSchProcessUserList(Map<String, String> schArrangeResultMap);

    List<TeachingActivityInfo> searchJoinActivityByProcessFlow(String processFlow);

    List<TeachingActivityInfo> searchJoinActivityByProcessFlowNotScore(String processFlow);

    List<Map<String,Object>> findSkillNoAudit(Map<String, Object> param);

    List<Map<String,String>> schDoctorSchProcessDayAttend(Map<String, String> schArrangeResultMap);

    List<Map<String,Object>> dayAttendList(Map<String, Object> param);

    int dayAttendListCount(Map<String, Object> param);
}
  