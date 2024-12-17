package com.pinde.sci.biz.res;

import com.pinde.core.model.*;
import com.pinde.sci.form.jsres.TeacherWorkForm;
import com.pinde.core.model.SchArrangeResult;
import com.pinde.sci.model.res.SchProcessExt;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IResDoctorProcessBiz {
	/**
	 * 保存或修改
	 * @param process
	 * @return
	 */
	int edit(ResDoctorSchProcess process);
	/**
	 * 按轮转计划流水号查找
	 * @param resultFlow
	 * @return
	 */
	ResDoctorSchProcess searchByResultFlow(String resultFlow);

	List<ResDoctorSchProcess> searchByResultFlowPartitionList(List<List<String>> resultFlowListList);

	List<ResDoctorSchProcess> readResDoctorSchProcessByExample(ResDoctorSchProcessExample example);
	/**
	 * 根据主键查找
	 * @param processFlow
	 * @return
	 */
	ResDoctorSchProcess read(String processFlow);

	List<ResDoctorSchProcess> searchDoctorProcess(String isOpen,String doctorName,ResDoctorSchProcess doctorProcess);
	List<ResDoctorSchProcess> searchDoctorProcess(Map<String,Object> map);
	List<ResDoctorSchProcess> searchByResultFlows(List<String> schResultFlows);
	ResDoctorSchProcess searchProcessByRec(String doctorFlow, String schDeptFlow);
	List<ResDoctorSchProcess> searchProcessByDoctor(String doctorFlow);
	List<ResDoctorSchProcess> searchProcessByDoctor(ResDoctorSchProcess resDoctorSchProcess);
	List<ResDoctorSchProcess> searchProcessByDoctor(ResDoctor doctor,
                                                    ResDoctorSchProcess process, Map<String, String> roleFlagMap);

	List<ResDoctorSchProcess> searchProcessByDoctorNew(String isOpen,ResDoctor doctor,
					ResDoctorSchProcess process,Map<String,String> roleFlagMap,String basicPractice);
	List<ResDoctorSchProcess> searchProcessByDoctorNew(Map<String,Object> map);

	List<ResDoctor> searchProcessByDoctorNew2(String flag, String isOpen, ResDoctor doctor,
														ResDoctorSchProcess process, Map<String, String> roleFlagMap, List<String> doctorTypeIdList);

	List<ResDoctorSchProcess> searchProcessByOrg(String orgFlow);
	
	/**
	 * 查询用户当前轮转科室
	 * @param userFlow
	 * @return
	 */
	ResDoctorSchProcess searchCurrDept(SysUser sysUser);
	
	/**
	 * 根据带教老师flow查询带教老师所带过的科室
	 * @param userFlow
	 * @return
	 */
	List<String> searchTeachDept(String userFlow);


	List<SchProcessExt> searchProcessAndResultByDoctorFlow(String doctorFlow, String deptFlow);
	/**
	 * 筛去没有开始轮转的医师
	 * @param doctorFlows
	 * @return
	 */
	List<String> searchRotationDoctor(List<String> doctorFlows);
	
	/**
	 * 查询该用户所在部门下的所有轮转中学员
	 * @param userFlow
	 * @param isCurrentFlag
	 * @return
	 */
	List<ResDoctorSchProcess> searchCurrentProcessByUserFlow(String isOpen,String userFlow,
			String isCurrentFlag);

	/**
	 * 查询该用户所在部门下的所有轮转中学员
	 * @return
	 */
	List<ResDoctorSchProcess> searchProcessByUserFlow(Map<String,Object> paramMap);
	
	/**
	 * 查询该用户关联专业下的所有轮转中学员
	 * @param userFlow
	 * @param isCurrentFlag
	 * @return
	 */
	List<ResDoctorSchProcess> searchProcessByUserSpe(String userFlow,
			String isCurrentFlag);
	ResDoctorSchProcess searchProcess(String userFlow,String orgFlow, String groupFlow,String deptId);
	
	/**
	 * 根据用户查询用户的轮转信息
	 * @param userFlows
	 * @return
	 */
	List<ResDoctorSchProcess> searchCurrProcessByUserFlows(
			List<String> userFlows);
	
	List<Map<String,String>> schDoctorSchProcessQuery(Map<String, String> map);

	/**
	 * 根据教师查询一系列用户，且去重复
	 * @param teacherUserFlow
	 * @return
	 */
	int resSchDoctorSchProcessDistinctQuery(String teacherUserFlow);
	int resSchDoctorSchProcessTeacherQuery(String teacherUserFlow);

	int schDoctorSchProcessDistinctQuery(String teacherUserFlow);
	int schDoctorSchProcessDistinctQueryByDate(String teacherUserFlow,String startDate,String endDate);

	int schDoctorSchProcessTeacherQuery(String teacherUserFlow);
	int schDoctorSchProcessWaitingExamineQuery(String teacherUserFlow);

	int jsresSchDoctorSchProcessDistinctQuery(String teacherUserFlow);
	int jsresSchDoctorSchProcessDistinctQueryByDate(String teacherUserFlow, String startDate, String endDate, List<String> typeList);
	int jsresSchDoctorSchProcessTeacherQuery(String teacherUserFlow);
	int jsresSchDoctorSchProcessWaitingExamineQuery(String teacherUserFlow);
	List<Map<String, String>> jsresSchDoctorSchProcessHead(Map<String, Object> map);
	List<Map<String, String>> temporaryOutAuditSearch(Map<String, Object> map);
	List<Map<String, String>> temporaryOutSearch(Map<String, Object> map);
	int temporaryOutAudit(ResDoctorSchProcess proces);
	List<Map<String,String>> jsresSchDoctorSchProcessQuery(Map<String, Object> map);
	/**
	 * 按部门统计学员
	 * @param userFlow
	 * @param isCurrentFlag
	 * @return
	 */
	int schProcessStudentDistinctQuery(String deptFlow,String userFlow);

	List<Map<String,String>> schProcessStudentDistinctQuery2(Map<String, Object> map);

	int schProcessStudentQuery(String deptFlow,String userFlow);
	int schProcessComingStudentQuery(String deptFlow,String userFlow);
	/**
	 * 根据当前用户查询关联dept并获取ResDoctorSchProcess
	 * @param 
	 * @return
	 */
	List<Map<String, String>> schDoctorSchProcessHead(Map<String, String> map);
	List<Map<String, String>> processRecShMap(Map<String, String> map);
	List<Map<String, String>> processRecRecTeacherMap(Map<String, String> map);
	List<Map<String, Object>> searTrainingQuery(String orgFlow,String datel);
	/**
	 * 根据轮转计划流水号查询所有记录
	 */
	List<ResDoctorSchProcess> searchBySchResultFlow(String schResultFlow);
	/**
	 * 根据轮转部门流水号查询记录
	 */
	List<ResDoctorSchProcess> searchByDeptFlow(String deptFlow);

	List<Map<String,Object>> searchTeacherWorkload(Map<String, Object> map);

	List<Map<String,Object>> schDoctorQuery(String teacherUserFlow, String schDeptFlow,String operEndDate,String operStartDate,List<String> doctorTypeIds);

	List<Map<String,Object>> chTeacherProcessFlowRec(String teacherUserFlow,String schDeptFlow,String operEndDate,String operStartDate,List<String> doctorTypeIds);
	List<Map<String,Object>> docWorkingSearch(Map<String, Object> parMp);


	List<Map<String,Object>> workList(Map<String, Object> parMp);

	List<Map<String,Object>> workDetail(Map<String, Object> parMp);

	int getTeaDeptAuditNum(Map<String, Object> map);

	int getTeaDeptNotAuditNum(Map<String, Object> map);

	void exportInfo(HttpServletResponse response, List<SchArrangeResult> arrResultList, Map<String, ResDoctorSchProcess> processMap, Map<String, String> finishPerMap) throws Exception;

	List<Map<String,Object>> jsresSchDoctorSchProcessEval(Map<String, Object> param);

	List<Map<String,Object>> findOneProcessEvals(Map<String, Object> params);
	//查询所有学员评价
	List<ResDoctorSchProcessEval> queryEvalListByFlow();
	//查询所有学员评价
	List<ResDoctorSchProcessEval> queryEvalListByProcessFlow(List<String> processFlows);
	//保存学员评价数据
	int saveForm(Map<String,Object> param);
	//查询学员评价
	ResDoctorSchProcessEvalWithBLOBs readProcessEvalByFlow(String recordFlow);

	int saveProcess(ResDoctorSchProcess process);
	//专业基地学员出科情况查询
	List<Map<String,String>> searchDocoutReport(Map<String,Object> paramMap);

	List<SysUser> jsresTeacherSchDocByDate(String userFlow, String startDate, String endDate, List<String> typeList);

	List<Map<String,String>> jsresSearchTeacherWorkInfo(String startDate, String endDate, List<String> typeList,SysUser sysUser,String orgFlow,String roleFlow,String orderItem,String sortName);
	//教学活动
	List<TeachingActivityInfo> searchActivityList(TeachingActivityInfo activityInfo);

	List<ResDoctorSchProcess> searchByStandadDeptIdAndGroupFLow(Map<String, String> paramsMap);

	Map<String,Object> getTeaAuditNumMap(Map<String, Object> map);

	Map<String,Object> getTeaNotAuditNumMap(Map<String, Object> map);

	Map<String,Object> getTeaAllDocNumMap(Map<String, Object> map);

	Map<String,Object> getTeaCurrDocNumMap(Map<String, Object> map);

	Map<String,Object> getTeaSchDocNumMap(Map<String, Object> map);

	Map<String,Object> getTeaLectureNumMap(Map<String, Object> map);

	List<Map<String,Object>> readHeadOrTeaStudents(Map<String, Object> paramMap);

	void delProcessByResultFlow(String resultFlow);

	List<SchArrangeResult> searchResultsByUserFlow(Map<String, Object> paramMap);

	int checkProcessEval(String processFlow);

    void saveChooseTea(String teacherUserName, String teacherUserFlow, String resultFlow);

	List<ResDoctorSchProcess> selectProcessByDoctorNew(Map<String, Object> param);

    List<Map<String, String>> jsresSearchTeacherWorkInfoNew(String startDate, String endDate, List<String> typeList,SysUser sysUser,String orgFlow,String roleFlow,String orderItem,String sortName,String doctorStartDate,String doctorEndDate);

    /**
     * @Department：研发部
     * @Description 高校带教工作量查询
     * @Author fengxf
     * @Date 2020/10/12
     */
	List<Map<String,String>> searchUniversityTeacherWorkInfoList(TeacherWorkForm teacherWorkForm);

	ResDoctorSchProcessEvalWithBLOBs getDoctorProcessEval(String processFlow, String startTime,String endTime);
}
