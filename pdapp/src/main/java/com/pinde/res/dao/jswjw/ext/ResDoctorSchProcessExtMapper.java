package com.pinde.res.dao.jswjw.ext;

import com.pinde.res.model.jswjw.mo.JsResDoctorOrgHistoryExt;
import com.pinde.sci.model.mo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResDoctorSchProcessExtMapper {
	List<Map<String,Object>> countProcessByUser(@Param(value = "userFlows") List<String> userFlows);
	
	List<ResDoctorSchProcess> searchProcessByDoctor(@Param(value = "doctor") ResDoctor doctor, @Param(value = "process") ResDoctorSchProcess process, @Param(value = "roleFlagMap") Map<String, String> roleFlagMap);
	
	List<String> searchTeachDept(@Param(value = "userFlow") String userFlow);

	/**schProcessComingStudentSunQuery
	 * 筛去没有开始轮转的医师
	 * @param doctorFlows
	 * @return
	 */
	List<String> searchRotationDoctor(@Param(value = "doctorFlows") List<String> doctorFlows);

	/**
	 * 查询该用户所在部门下的所有轮转中学员
	 * @param userFlow
	 * @param isCurrentFlag
	 * @return
	 */
	List<ResDoctorSchProcess> searchCurrentProcessByUserFlow(@Param(value = "userFlow") String userFlow, @Param(value = "isCurrentFlag") String isCurrentFlag);

	/**
	 * 查询该用户关联专业下的所有轮转中学员
	 * @param userFlow
	 * @param isCurrentFlag
	 * @return
	 */
	List<ResDoctorSchProcess> searchProcessByUserSpe(@Param(value = "userFlow") String userFlow, @Param(value = "isCurrentFlag") String isCurrentFlag);

	List<Map<String,String>> schDoctorSchProcessQuery(Map<String, String> map);
	

	int schDoctorSchProcessDistinctQuery(@Param("teacherUserFlow") String teacherUserFlow, @Param("deptFlow") String deptFlow);
	int schDoctorSchProcessTeacherQuery(@Param(value = "teacherUserFlow") String teacherUserFlow, @Param("deptFlow") String deptFlow);
	int schDoctorSchProcessWaitingExamineQuery(@Param("teacherUserFlow") String teacherUserFlow);

	/**
	 * 按部门统计学员
	 * @param deptFlow
	 * @param userFlow
	 * @param isSch
	 * @return
	 */
	int schProcessStudentDistinctQuery(@Param(value = "deptFlow") String deptFlow, @Param(value = "userFlow") String userFlow, @Param("isSch") String isSch);
	int schProcessStudentQuery(@Param(value = "deptFlow") String deptFlow, @Param(value = "userFlow") String userFlow);
	int schProcessComingStudentQuery(@Param(value = "deptFlow") String deptFlow, @Param(value = "userFlow") String userFlow);

	/**
	 * 根据当前用户查询关联dept并获取ResDoctorSchProcess
	 * @param
	 * @return
	 */
	List<Map<String, String>> schDoctorSchProcessHead(Map<String, String> map);
	List<Map<String, String>> deptTeacherUsers(Map<String, String> map);

	List<Map<String, String>> processRecShMap(Map<String, String> map);
	List<Map<String, String>> processRecRecTeacherMap(Map<String, String> map);
	/**
	 * 根据orgFlow查询将出科学员
	 * @param
	 * @return
	 */
	List<Map<String, Object>> searTrainingQuery(Map<String, Object> map);

	List<Map<String, Object>> docWorkingSearch(Map<String, Object> paraMp);
	List<Map<String, Object>> docWorkingDetail(Map<String, Object> paraMp);

	List<Map<String,Object>> searchTeacherWorkload(Map<String, Object> map);

	List<Map<String,Object>> schDoctorQuery(@Param(value = "teacherUserFlow") String teacherUserFlow, @Param(value = "schDeptFlow") String schDeptFlow);

	List<Map<String,Object>> chTeacherProcessFlowRec(@Param(value = "teacherUserFlow") String teacherUserFlow, @Param(value = "schDeptFlow") String schDeptFlow, @Param(value = "operEndDate") String operEndDate, @Param(value = "operStartDate") String operStartDate);

	List<Map<String,Object>> newDocWorkingSearch(Map<String, Object> parMp);

	List<Map<String,Object>> newDocWorkingDetail(Map<String, Object> parMp);

	List<Map<String,Object>> searchDocCycleList(Map<String, Object> paramMap);

	List<SchArrangeResult> searchCycleArrangeResults(Map<String, Object> paramMap);

	List<Map<String,String>> schDoctorSchProcessInfoQuery(Map<String, String> schArrangeResultMap);
	List<Map<String,String>> schDoctorSchProcessEvalList(Map<String, String> schArrangeResultMap);

	List<Map<String,Object>> findProcessEvals(Map<String, Object> params);

	List<Map<String,String>> schDeptDoctorSchProcess(Map<String, String> schArrangeResultMap);

	List<JsResDoctorOrgHistoryExt> getSpeChangeList(Map<String,Object> param);

	List<Map<String,Object>> getOrgSpeDocNum(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber);

	List<Map<String, String>> hbresDoctorSchProcessHead(Map<String, String> map);

	List<ResDoctorSchProcess> searchProcessByDoctorNew(Map<String, Object> paramMap);

	int hbresSchProcessStudent(@Param(value = "deptFlow") String deptFlow, @Param(value = "userFlow") String userFlow, @Param("isOpen") String isOpen);

	List<Map<String,String>> schDoctorSchProcessHeadUserList(Map<String, String> schArrangeResultMap);

	int schDoctorSchProcessHeadCount(Map<String, String> schArrangeResultMap);

	int findNeedAuditAbsences(@Param("user") SysUser user);

	List<Map<String,String>> getAbsenceList(Map<String, String> schArrangeResultMap);

	List<Map<String,Object>> absenceCountDetail(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("flow") String flow);
	int absenceCount(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("flow") String flow);

	List<Map<String,String>> zseyDoctorSchProcessHead(Map<String, String> schArrangeResultMap);

	int findNeedSpeAuditAbsences(@Param("user") SysUser user);

	List<Map<String,String>> schDoctorSchProcessSpeUserList(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> getSpeAbsenceList(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> schDoctorSchProcessSpe(Map<String, String> schArrangeResultMap);

	int schDoctorSchProcessSpeCount(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> readDocTypeMap(@Param("userFlow") String userFlow, @Param("sessionNumber") String sessionNumber);

	List<Map<String,String>> readDocEducationMap(@Param("userFlow") String userFlow, @Param("sessionNumber") String sessionNumber);
	List<Map<String,String>> readSessionNumberDocMap(@Param("userFlow") String userFlow);

	List<Map<String,String>> readTeacherStatistics(@Param("userFlow") String userFlow, @Param("roleFlow") String roleFlow);

	List<Map<String,String>> teacherList(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> schDoctorSchProcessUserList(Map<String, String> params);

	int deptUsersDocCount(Map<String, String> param);

	int getOrgDocCount(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("statusId") String statusId);

	List<Map<String,String>> getOrgDocList(Map<String, Object> param);

	List<Map<String,Object>> getCycleStudents(Map<String, Object> map);
	List<Map<String,Object>> docSchDeptList(Map<String, Object> map);

	List<SchDept> readSchDeptList(@Param("userFlow") String userFlow);

	List<TeachingActivityInfo> searchJoinActivityByProcessFlow(@Param("processFlow") String processFlow);

	List<TeachingActivityInfo> searchJoinActivityByProcessFlowNotScore(@Param("processFlow") String processFlow);

	List<Map<String,Object>> findSkillNoAudit(Map<String, Object> param);

	List<Map<String,String>> schDoctorSchProcessDayAttend(Map<String, String> schArrangeResultMap);

	List<Map<String,Object>> dayAttendList(Map<String, Object> param);

	int jsresHeadCount(Map<String, String> param);

	List<Map<String,String>> jsresHeadUserList(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> deptTeacherDocList(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> attendList(Map<String, String> schArrangeResultMap);

	int deptUsersCount(Map<String, String> param);

	List<Map<String,String>> afterUserList(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> temporaryOutAuditList(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> temporaryOutList(Map<String, String> schArrangeResultMap);

	List<Map<String,String>> afterAuditList(Map<String, String> schArrangeResultMap);

	int checkProcessEval(@Param("processFlow") String processFlow);

    int dayAttendListCount(Map<String, Object> param);

	Map<String,Object> doctorDeptAvgWorkDetail(@Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	List<ResDoctorSchProcess> selectProcessByDoctorNew(Map<String, Object> roleFlagMap);

	List<String> findDoctorNameByRecTime(Map<String, Object> param);

    List<Map<String, String>> temporaryOutSearch(Map<String, Object> map);

    List<Map<String, String>> schProcessStudentDistinctQuery2(Map<String, Object> map);

	List<String> studentList(String userFlow,String isNow);
}
