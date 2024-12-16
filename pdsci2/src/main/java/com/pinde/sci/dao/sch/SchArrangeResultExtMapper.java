package com.pinde.sci.dao.sch;


import com.pinde.core.model.JsresDoctorDeptDetail;
import com.pinde.core.model.SysDept;
import com.pinde.sci.form.sch.SchGradeFrom;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResOutOfficeLock;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.res.GeneralIdNameModel;
import com.pinde.sci.model.res.SchArrangeResultExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchArrangeResultExtMapper {



	List<SchGradeFrom> searchGradeByDoctorFlow(@Param(value = "schDeptFlow") String schDeptFlow,@Param(value = "userFlow") String userFlow,@Param(value = "processFlow") String processFlow,@Param(value = "doctorFlow") String doctorFlow,@Param(value = "schStartDate") String schStartDate,
                                               @Param(value = "schEndDate") String schEndDate,@Param(value = "resultFlow") String resultFlow);

	List<SchGradeFrom> searchGradeByOrgFlow(@Param(value = "doctorName") String doctorName,@Param(value = "processFlow") String processFlow,@Param(value = "orgFlow") String orgFlow,@Param(value = "schStartDate") String schStartDate,@Param(value = "schEndDate") String schEndDate);


	/**
	 * 医师轮转培训查询
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> searchDocCycleList(Map<String,Object> paramMap);

	/**
	 * 医师轮转培训查询
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> searchDocCycleBaseList(Map<String,Object> paramMap);

	/**
	 * 医师培训成绩查询
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> searchDocResultsList(Map<String,Object> paramMap);
	/**
	 * 医师培训成绩查询 江苏西医优化采用此方法
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>> searchDocResultsListNew(Map<String,Object> paramMap);

	List<ResOutOfficeLock> searchDocErrorResultsList(Map<String,Object> paramMap);

	List<Map<String,Object>> countResultByUser(@Param(value="userFlows")List<String> userFlows);
	
	Map<String,String> countDateArea(@Param(value="doctor")ResDoctor doctor);
	
	List<Map<String,Object>> countMonthNum(@Param(value="month")String month,@Param(value="doctor")ResDoctor doctor);
	
	List<SchArrangeResult> searchInMonthResult(@Param(value="schDeptFlow")String schDeptFlow,@Param(value="month")String month,@Param(value="doctor")ResDoctor doctor);
	
	List<SchArrangeResult> willInDoctor(@Param(value="orgFlow")String orgFlow,@Param(value="userFlow")String userFlow);

	List<SchArrangeResult> willInDoctor2(Map<String,Object> map);

	List<GeneralIdNameModel> preWarningBefore(Map<String, String> param);

	List<GeneralIdNameModel> preWarningIng(Map<String, String> param);

	List<GeneralIdNameModel> preWarningAfter(Map<String, String> param);

	int countRotationUse(@Param(value="rotationFlow")String rotationFlow);

	/**
	 * 为该医师的计划按标准科室排序
	 * @param doctorFlow
	 * @return
	 */
	int sortResult(@Param(value="doctorFlow")String doctorFlow);
	
	/**
	 * 按条件查找排班
	 * @param doctor
	 * @return
	 */
	List<SchArrangeResult> searchResultByDoctor(@Param(value="doctor")ResDoctor doctor);
	
	/**
	 * 查询过程或计划数据
	 * @param paramMap
	 * @return
	 */
	List<SchArrangeResultExt> getResults(Map<String,Object> paramMap);


	/**
	 * 根据开始和结束时间、doctorFlow查询多个学员轮转科室
	 */
	List<SchArrangeResult> searchCycleArrangeResults (Map<String,Object> paramMap);

	/****************************高******校******管******理******员******角******色************************************************/

	List<Map<String,Object>> searchDocCycleListForUni(Map<String, Object> paramMap);

	List<Map<String,Object>> searchDocResultsListForUni(Map<String, Object> paramMap);

	List<SchArrangeResult> searchCycleArrangeResultsForUni(Map<String, Object> paramMap);

	List<SchArrangeResult> searchCycleArrangeResultsForUniTwo(Map<String, Object> paramMap);

	int deleteResultNotInRotation(Map<String, String> param);

	List<SchArrangeResult> getResultBySchRotationDept(Map<String, String> param);

	List<Map<String,Object>> preWarningBeforeList(Map<String, String> param);

	List<Map<String,Object>> preWarningIngList(Map<String, String> param);

	List<Map<String,Object>> preWarningAfterList(Map<String, String> param);


	List<Map<String,Object>> querySchArrangeResultByDoctor(@Param("doctorFlow") String doctorFlow);

	void updateResultHaveAfter(@Param("haveAfterPic") String haveAfterPic, @Param("schRotationDeptFlow") String schRotationDeptFlow, @Param("operUserFlow") String operUserFlow);

	List<Map<String,String>> searchDocResultsByMonth(Map<String, Object> paramMap);

	List<SchArrangeResult> searchSchArrangeResultByDoctorAndDate(Map<String, String> paramMap);

	List<SchArrangeResult> searchSchArrangeResultByMap(Map<String, String> paramMap);

	List<JsresDoctorDeptDetail> deptDoctorAllWorkDetailByNow(@Param("recruitFlow") String recruitFlow, @Param("doctorFlow") String doctorFlow, @Param("applyYear") String applyYear);

	List<SchArrangeResult> searchArrangeResultByDateAndOrgByMap(Map<String, String> param);

	List<SysDept> findDeptsByDoctor(@Param("doctorFlow") String doctorFlow);

	List<SchArrangeResult> searchArrangeResultByDateAndOrgByMapNew(Map<String, Object> param);


	/**
	 * @Department：研发部
	 * @Description 查询学员轮转月数
	 * @Author fengxf
	 * @Date 2021/4/13
	 */
	float getDoctorSchMonthSumInfo(@Param("rotationFlow") String rotationFlow, @Param("doctorFlow") String doctorFlow);


	List<Map<String,Object>> schedulingSearchDeptList(@Param("orgFlow")String orgFlow,@Param("firstDayOfMonth")String firstDayOfMonth,@Param("lastDayOfMonth")String lastDayOfMonth,@Param("deptFlow")String deptFlow);

	List<Map<String,Object>> schedulingSearchDeptList2(@Param("orgFlow")String orgFlow,@Param("firstDayOfMonth")String firstDayOfMonth,@Param("lastDayOfMonth")String lastDayOfMonth,@Param("deptFlow")String deptFlow);

	List<Map<String,Object>> schedulingSearchDeptUserList(@Param("orgFlow")String orgFlow,@Param("firstDayOfMonth")String firstDayOfMoth,@Param("lastDayOfMonth")String lastDayOfMonth,
														  @Param("deptFlow")String deptFlow,@Param("trainingTypeId")String trainingTypeId);

	List<Map<String,Object>> schedulingSearchDoctorList(Map<String, Object> paramMap);

	List<Map<String,Object>> schedulingSearchAuditList(Map<String, Object> paramMap);

	List<SchArrangeResult> checkResultDateList(Map<String,Object> paramMap);

	int updateSchArrangeResultToDel(@Param("resultFlows") List<String> resultFlows);

	int updateResDoctorSchProcessToDel(@Param("resultFlows") List<String> resultFlows);


	List<SchArrangeResult> getAllByDoctorFlow(@Param("doctorFlow")String doctorFlow,
											  @Param("delFlag")Boolean delFlag);

	List<SchArrangeResult> selectDoctorSchDate(@Param("doctorFlowList") List<String> doctorFlowList, @Param("schStartDate") String schStartDate, @Param("schEndDate") String schEndDate);
}