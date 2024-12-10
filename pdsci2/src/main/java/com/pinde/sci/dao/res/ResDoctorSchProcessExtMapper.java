package com.pinde.sci.dao.res;

import com.pinde.core.model.SysUser;
import com.pinde.sci.form.jsres.TeacherWorkForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.SchProcessExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResDoctorSchProcessExtMapper {
	List<Map<String,Object>> countProcessByUser(@Param(value="userFlows")List<String> userFlows);
	
	List<ResDoctorSchProcess> searchProcessByDoctor(@Param(value="doctor")ResDoctor doctor, @Param(value="process")ResDoctorSchProcess process, @Param(value="roleFlagMap")Map<String,String> roleFlagMap);

	List<ResDoctorSchProcess> searchProcessByDoctorNew(@Param(value = "isOpen")String isOpen, @Param(value="doctor")ResDoctor doctor, @Param(value="process")ResDoctorSchProcess process, @Param(value="roleFlagMap")Map<String,String> roleFlagMap,@Param(value = "basicPractice")String basicPractice);

	List<ResDoctorSchProcess> searchProcessByMp(Map<String,Object> roleFlagMap);

	List<ResDoctor> searchProcessByDoctorNew2(@Param(value = "flag") String flag, @Param(value = "isOpen") String isOpen, @Param(value = "doctor") ResDoctor doctor, @Param(value = "process") ResDoctorSchProcess process, @Param(value = "roleFlagMap") Map<String, String> roleFlagMap, @Param("doctorTypeIdList") List<String> doctorTypeIdList);

	List<String> searchTeachDept(@Param(value="userFlow")String userFlow);

	List<SchProcessExt> searchProcessAndResultByDoctorFlow(@Param(value="doctorFlow") String doctorFlow,@Param(value = "deptFlow") String deptFlow);
	
	/**schProcessComingStudentSunQuery
	 * 筛去没有开始轮转的医师
	 * @param doctorFlows
	 * @return
	 */
	List<String> searchRotationDoctor(@Param(value="doctorFlows")List<String> doctorFlows);
	
	/**
	 * 查询该用户所在部门下的所有轮转中学员
	 * @param userFlow
	 * @param isCurrentFlag
	 * @return
	 */
	List<ResDoctorSchProcess> searchCurrentProcessByUserFlow(@Param(value = "isOpen")String isOpen,@Param(value="userFlow")String userFlow,@Param(value="isCurrentFlag")String isCurrentFlag);

	//获取当前带教老师所有轮转中学员（青浦）
	List<Map<String,Object>> teacherGetStudents(Map<String,Object> paramMap);
	//获取当前科秘老师所有轮转中学员（青浦）
	List<Map<String,Object>> headGetStudents(Map<String,Object> paramMap);

	List<ResDoctorSchProcess> searchProcessByUserFlow(Map<String,Object> paramMap);

	/**
	 * 查询该用户关联专业下的所有轮转中学员
	 * @param userFlow
	 * @param isCurrentFlag
	 * @return
	 */
	List<ResDoctorSchProcess> searchProcessByUserSpe(@Param(value="userFlow")String userFlow,@Param(value="isCurrentFlag")String isCurrentFlag);
	
	List<Map<String,String>> schDoctorSchProcessQuery(Map<String, String> map);
	
	
	int resSchDoctorSchProcessDistinctQuery(@Param(value="teacherUserFlow")String teacherUserFlow);
	int resSchDoctorSchProcessTeacherQuery(@Param(value="teacherUserFlow")String teacherUserFlow);

	int schDoctorSchProcessDistinctQuery(@Param(value="teacherUserFlow")String teacherUserFlow);
	int schDoctorSchProcessDistinctQueryByDate(@Param(value="teacherUserFlow")String teacherUserFlow,@Param(value="startDate")String startDate,@Param(value="endDate")String endDate);
	int schDoctorSchProcessTeacherQuery(@Param(value="teacherUserFlow")String teacherUserFlow);
	int schDoctorSchProcessWaitingExamineQuery(@Param(value="teacherUserFlow")String teacherUserFlow);

	int jsresSchDoctorSchProcessDistinctQuery(@Param(value="teacherUserFlow")String teacherUserFlow);
	int jsresSchDoctorSchProcessDistinctQueryByDate(@Param(value = "teacherUserFlow") String teacherUserFlow, @Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate, @Param("typeList") List<String> typeList);
	List<SysUser> jsresTeacherSchDocByDate(@Param(value = "teacherUserFlow") String teacherUserFlow, @Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate, @Param("typeList") List<String> typeList);
	int jsresSchDoctorSchProcessTeacherQuery(@Param(value="teacherUserFlow")String teacherUserFlow);
	int jsresSchDoctorSchProcessWaitingExamineQuery(@Param(value="teacherUserFlow")String teacherUserFlow);
	List<Map<String, String>> jsresSchDoctorSchProcessHead(Map<String, Object> map);
	List<Map<String, String>> temporaryOutAuditSearch(Map<String, Object> map);
	List<Map<String, String>> temporaryOutSearch(Map<String, Object> map);
	int temporaryOutAudit(ResDoctorSchProcess proces);
	List<Map<String,String>> jsresSchDoctorSchProcessQuery(Map<String, Object> map);


	/**
	 * 按部门统计学员
	 * @param deptFlow
	 * @param userFlow
	 * @return
	 */
	int schProcessStudentDistinctQuery(@Param(value="deptFlow")String deptFlow,@Param(value="userFlow")String userFlow);

	List<Map<String,String>>  schProcessStudentDistinctQuery2(Map<String, Object> map);

	int schProcessStudentQuery(@Param(value="deptFlow")String deptFlow,@Param(value="userFlow")String userFlow);
	int schProcessComingStudentQuery(@Param(value="deptFlow")String deptFlow,@Param(value="userFlow")String userFlow);
	
	/**
	 * 根据当前用户查询关联dept并获取ResDoctorSchProcess
	 * @param 
	 * @return
	 */
	List<Map<String, String>> schDoctorSchProcessHead(Map<String, String> map);
	
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

	List<Map<String,Object>> schDoctorQuery(@Param(value="teacherUserFlow")String teacherUserFlow, @Param(value="schDeptFlow")String schDeptFlow, @Param(value="operEndDate")String operEndDate,@Param(value="operStartDate") String operStartDate,@Param(value="doctorTypeIds")List<String> doctorTypeIds);

	List<Map<String,Object>> chTeacherProcessFlowRec(@Param(value="teacherUserFlow")String teacherUserFlow, @Param(value="schDeptFlow")String schDeptFlow, @Param(value="operEndDate")String operEndDate,@Param(value="operStartDate") String operStartDate,@Param(value="doctorTypeIds")List<String> doctorTypeIds);

	List<Map<String,Object>> newDocWorkingSearch(Map<String, Object> parMp);

	List<Map<String,Object>> newDocWorkingDetail(Map<String, Object> parMp);

	List<Map<String,Object>> searchDocCycleList(Map<String, Object> paramMap);

	List<SchArrangeResult> searchCycleArrangeResults(Map<String, Object> paramMap);

	/**
	 * 本月计划出入科学员信息
	 * @param paramMap
	 * @return
     */
	List<SchProcessExt> searchPlanAccessDoc(Map<String, Object> paramMap);

	/**
	 * 上月已入科学员信息
	 * @param paramMap
	 * @return
     */
	List<SchProcessExt> searchAlreadyInDoc(Map<String, Object> paramMap);

	/**
	 * 上月出科学员以及未出科学员信息
	 * @param paramMap
	 * @return
     */
	List<SchProcessExt> searchInAndOutDoc(Map<String, Object> paramMap);

	//查询专业基地学员出科情况
	List<Map<String,String>> searchDocoutReport(Map<String,Object> paramMap);

	/****************************高******校******管******理******员******角******色************************************************/

	List<Map<String,Object>> docWorkingSearchForUni(Map<String, Object> paraMp);

	List<Map<String,Object>> orgTeaAuditInfoForUni(Map<String, Object> paraMp);

	List<Map<String,Object>> exportCkScore(Map<String, Object> paramMap);

	List<Map<String,Object>> workList(Map<String, Object> parMp);

	List<Map<String,Object>> workDetail(Map<String, Object> parMp);

	int getTeaDeptAuditNum(Map<String, Object> map);

	int getTeaDeptNotAuditNum(Map<String, Object> map);

	Map<String,Object> deptWorkingDetail(Map<String, Object> map);

	Map<String,Object> doctorDeptAvgWorkDetail(@Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	List<Map<String,Object>> jsresSchDoctorSchProcessEval(Map<String, Object> param);

	List<Map<String,Object>> findOneProcessEvals(Map<String, Object> param);

	//查询学员评价所需数据
	List<Map<String,Object>> queryProcessEvalData(@Param("processFlow") String processFlow);

	List<ResDoctorSchProcess> searchDoctorProcess(@Param("isOpen") String isOpen, @Param("doctorName")String doctorName,
												  @Param("doctorProcess") ResDoctorSchProcess doctorProcess);
	List<ResDoctorSchProcess> searchDoctorProcess2(Map<String,Object> map);

	//根据条件查询某月轮转学员列表
	List<Map<String,Object>> searchProcessByMonth(Map<String,Object> paramMap);

	//按时间维度的考勤查询
	List<Map<String,Object>> searchProcessByTime(Map<String,Object> paramMap);

	//按轮转科室维度的考勤查询
	List<Map<String,Object>> searchProcessByDept(Map<String,Object> paramMap);

	//查询带教工作量
	List<Map<String,String>> jsresSearchTeacherWorkInfo(Map<String,Object> paramMap);
	List<TeachingActivityInfo> queryActivityList(Map<String,String> paramMap);

	List<ResDoctorSchProcess> searchByStandadDeptIdAndGroupFLow(Map<String, String> paramsMap);

	List<Map<String,Object>> getTeaAuditNumMap(Map<String, Object> map);

	List<Map<String,Object>> getTeaNotAuditNumMap(Map<String, Object> map);

	List<Map<String,Object>> getTeaAllDocNumMap(Map<String, Object> map);

	List<Map<String,Object>> getTeaCurrDocNumMap(Map<String, Object> map);

	List<Map<String,Object>> getTeaSchDocNumMap(Map<String, Object> map);

	List<Map<String,Object>> getTeaLectureNumMap(Map<String, Object> map);

	List<Map<String,Object>> readHeadOrTeaStudents(Map<String, Object> paramMap);

	int delProcessByResultFlow(@Param("resultFlow") String resultFlow);

	List<SchArrangeResult> searchResultsByUserFlow(Map<String, Object> paramMap);

	int checkProcessEval(@Param("processFlow") String processFlow);

	List<ResDoctorSchProcess> selectProcessByDoctorNew(Map<String, Object> roleFlagMap);

    List<Map<String, String>> jsresSearchTeacherWorkInfoNew(Map<String, Object> paramMap);

    /**
     * @Department：研发部
     * @Description 高校角色带教工作量查询
     * @Author fengxf
     * @Date 2020/10/12
     */
	List<Map<String, String>> searchUniversityTeacherWorkInfoList(TeacherWorkForm teacherWorkForm);

	/**
	 * @param parMp
	 * @Department：研发部
	 * @Description 责任导师查询学员工作量
	 * @Author Zjie
	 * @Date 0014, 2021年1月14日
	 */
	List<Map<String, Object>> docWorkingSearchNew(Map<String, Object> parMp);

	/**
	 * @param paramMap
	 * @Department：研发部
	 * @Description 医师轮转培训查询
	 * @Author Zjie
	 * @Date 0015, 2021年1月14日
	 */
	List<Map<String, Object>> searchDocCycleListNew(Map<String, Object> paramMap);


	List<ResDoctorSchProcess> listByDoctorFlow(@Param("doctorFlow")String doctorFlow);
}
