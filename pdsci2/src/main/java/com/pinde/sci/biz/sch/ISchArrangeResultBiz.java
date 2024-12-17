package com.pinde.sci.biz.sch;

import com.pinde.core.model.*;
import com.pinde.sci.excelListens.model.SchedulingDataModel;
import com.pinde.sci.form.sch.SchArrangeResultForm;
import com.pinde.sci.model.jsres.ArrangTdVo;
import com.pinde.core.model.ResOutOfficeLock;
import com.pinde.core.model.SchArrangeResult;
import com.pinde.core.model.SchArrangeResultExample;
import com.pinde.core.model.SchRotationDept;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface ISchArrangeResultBiz {

	Map<String,Object> saveDbImportArrang(List<Map<String, ArrangTdVo>> data) throws ParseException;

	Map<String,Object> submitImportData(List<Map<String, ArrangTdVo>> data);

	SchArrangeResult readSchArrangeResult(String resultFlow);

	List<SchArrangeResult> readSchArrangeResultByExample(SchArrangeResultExample example);
	
	int saveSchArrangeResult(SchArrangeResult result);

    int saveProcessAndResult(ResDoctorSchProcess process, SchArrangeResult result);

	int saveInDept(SchArrangeResult arrangeResult,ResDoctorSchProcess process) throws ParseException;

	int save(SchArrangeResult result);

	int update(SchArrangeResult result);

	List<SchArrangeResult> searchSchArrangeResultByDoctor(String doctorFlow);
	List<Map<String,Object>> querySchArrangeResultByDoctor(String doctorFlow);

	List<SchArrangeResult> searchProcessByItems(Map<String,Object> paramMap);

	int saveSchArrangeResultForm(SchArrangeResultForm resultForm);

	/**
	 * 删除该医师排班
	 * @param doctorFlow
	 * @return
	 */
	int delArrangeResult(String doctorFlow);
	
	/**
	 * 删除该医师排班且重置状态
	 * @param doctorFlow
	 * @param reStatus
	 * @return
	 */
	int delArrangeResult(String doctorFlow,boolean reStatus);

	List<SchArrangeResult> searchArrangeResultByDoctorFlows(
			List<String> doctorFlows);

	List<SchArrangeResult> searchArrangeResultByResultFlow(
			List<String> resultFlows);

	List<Map<String,Object>> countResultByUser(List<String> userFlows);

	List<SchArrangeResult> searchArrangeResultByDateAndOrg(String schStartDate,
			String schEndDate, String orgFlow);
	List<SchArrangeResult> searchArrangeResultByDateAndOrg1(Map<String,Object> map);



	int saveResultByDoctor(ResDoctor doctor);

	int saveSchArrangeResults(List<SchArrangeResult> resultList);

	Map<String, String> countDateArea(ResDoctor doctor);

	List<Map<String, Object>> countMonthNum(String month, ResDoctor doctor);

	List<SchArrangeResult> searchInMonthResult(String schDeptFlow,
			String month, ResDoctor doctor);

	List<SchArrangeResult> willInDoctor(String orgFlow,String userFlow);

	List<SchArrangeResult> willInDoctor(Map<String,Object> map);

	int countRotationUse(String rotationFlow);

	/**
	 * 编辑该医师的排班计划
	 * @param doctorFlow
	 * @param addSchDeptFlows
	 * @param delResultFlows
	 * @return
	 */
	int editFreeRostering(String doctorFlow, String[] addSchDeptFlows,
			String[] delResultFlows);
	/**
	 * 编辑该医师的排班计划
	 * @param doctorFlow
	 * @return
	 */
	int editFreeRostering(String doctorFlow, String groupFlow, String standardDeptId,String standardDeptName, String schDeptFlow);
	/**
	 * 为组保存排班
	 * @param resultList
	 * @param groupId
	 * @param operUser
	 * @return
	 */
	int saveGroupResult(List<SchArrangeResult> resultList, String groupId,
			SysUser operUser);

	/**
	 * 按条件查找排班
	 * @param doctor
	 * @return
	 */
	List<SchArrangeResult> searchResultByDoctor(ResDoctor doctor);
	/**
	 * 按GroupFlow和StandardDeptId查
	 * @param doctor
	 * @return
	 */
	List<SchArrangeResult> searchResultByGroupFlow(String groupFlow, String standardDeptId,String doctorFlow);

	/**
	 * 删除一条result
	 */
	int delResultByResultFlow(String resultFlow);

	int delInDept(String resultFlows);
	/**
	 * 医师轮转培训查询
	 */
	List<Map<String,Object>> searchDocCycleList(Map<String,Object> paramMap);

	/**
	 * 医师轮转培训查询 主基地实际在协同基地培训的学员
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>> searchDocCycleBaseList(Map<String,Object> paramMap);

	/**
	 * 医师轮转成绩查询
     */
	List<Map<String,Object>> searchDocResultsList(Map<String,Object> paramMap);
	/**
	 * 医师轮转成绩查询江苏西医优化采用此方法
	 */
	List<Map<String,Object>> searchDocResultsListNew(Map<String,Object> paramMap);

	Map<String,Map<String, BigDecimal>> getScoreByDoctorIds(List<String> doctorFlowList, String schStartDate, String schEndDate);

	List<ResOutOfficeLock> searchDocErrorResultsList(Map<String,Object> paramMap) throws ParseException;

	List<ResDoctorSchProcess> searchDocErrorResultInfo(String recordFlow,String orgFlow) throws ParseException;

	int saveAuditLockInfo(ResOutOfficeLock outOfficeLock);

	List<SchArrangeResult> searchCycleArrangeResults(Map<String,Object> paramMap);

	List<Map<String, Object>> docWorkingSearch(Map<String, Object> paraMp);
	List<Map<String, Object>> docWorkingDetail(Map<String, Object> paraMp);

	List<Map<String,Object>> newDocWorkingDetail(Map<String, Object> parMp);

	List<Map<String,Object>> searchNewDocCycleList(Map<String, Object> paramMap);

	List<SchArrangeResult> searchNewCycleArrangeResults(Map<String, Object> paramMap);

	/****************************高******校******管******理******员******角******色************************************************/

	List<Map<String,Object>> searchDocCycleListForUni(Map<String, Object> paramMap);

	List<Map<String,Object>> searchDocResultsListForUni(Map<String, Object> paramMap);

	List<SchArrangeResult> searchCycleArrangeResultsForUni(Map<String, Object> paramMap);

	List<SchArrangeResult> searchCycleArrangeResultsForUniTwo(Map<String, Object> paramMap);

	List<Map<String,Object>> orgTeaAuditInfoForUni(Map<String, Object> parMp);

	List<Map<String,Object>> exportCkScore(Map<String, Object> paramMap);

	List<SchArrangeResult> searchSchArrangeResultByDoctorAndRotationFlow(String doctorFlow, String rotationFlow);

	JsresDoctorDeptDetail deptDoctorWorkDetail(String recordFlow, String rotationFlow, String doctorFlow);

	Map<String,Object> doctorDeptAvgWorkDetail(String recruitFlow, String applyYear);

	List<JsresDoctorDeptDetail> deptDoctorAllWorkDetail(String rotationFlow, String doctorFlow, String applyYear);

	void initDocResult(ResDoctor doctor, List<SchRotationDept> schRotationDepts);


	int synchronizeProcessByResults(List<SchArrangeResult> resultList);

	SchArrangeResult getResultBySchRotationDept(Map<String, String> param);

	int editSchArrangeResult(SchArrangeResult result1);

	List<SchArrangeResult> searchResultByRotationDepts(List<SchRotationDept> depts, String doctorFlow);

	void updateResultHaveAfter(String haveAfterPic, String schRotationDeptFlow, String operUserFlow);

	List<Map<String,String>> searchDocResultsByMonth(Map<String, Object> paramMap);

	List<SchArrangeResult> searchSchArrangeResultByDoctorAndDate(Map<String, String> paramMap);

	List<SchArrangeResult> searchSchArrangeResultByMap(Map<String, String> pMap);

	List<JsresDoctorDeptDetail> deptDoctorAllWorkDetailByNow(String recruitFlow, String doctorFlow, String applyYear);

	List<SchArrangeResult> searchArrangeResultByDateAndOrgByMap(Map<String, String> param);

	List<SysDept> findDeptsByDoctor(String doctorFlow);

	/**
	 * @Department：研发部
	 * @Description 责任导师查询学员工作量
	 * @Author Zjie
	 * @Date 0014, 2021年1月14日
	 */
	List<Map<String, Object>> docWorkingSearchNew(Map<String, Object> parMp);

	/**
	 * @Department：研发部
	 * @Description 医师轮转培训查询
	 * @Author Zjie
	 * @Date 0015, 2021年1月14日
	 */
	List<Map<String, Object>> searchDocCycleListNew(Map<String, Object> paramMap);

	/**
	 * @Department：研发部
	 * @Description 查询学员轮转月数
	 * @Author fengxf
	 * @Date 2021/4/13
	 */
	float getDoctorSchMonthSumInfo(String rotationFlow, String doctorFlow);

	List<Map<String,Object>> schedulingSearchDeptList(String orgFlow,String firstDayOfMoth,String lastDayOfMoth,String deptFlow);
	List<Map<String,Object>> schedulingSearchDeptList2(String orgFlow,String firstDayOfMoth,String lastDayOfMoth,String deptFlow);

	List<Map<String,Object>> schedulingSearchDeptUserList(String orgFlow,String firstDayOfMoth,String lastDayOfMoth,String deptFlow,String trainingTypeId);

	List<Map<String,Object>> schedulingSearchDoctorList(Map<String, Object> paramMap);

	List<Map<String,Object>> schedulingSearchAuditList(Map<String, Object> paramMap);

    Map<String, Object> importSchedulingAuditExcel(MultipartFile file, String rotationFlow, String trainingTypeId) throws Exception;

	Map<String,String> importSchedulingAuditExcelCache(MultipartFile file) throws IOException, InvalidFormatException;
	Map<String, Object> checkRowData(List<SchedulingDataModel> data);
	Map<String,Object> submitPbImport(List<SchedulingDataModel> data) throws Exception;

	List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate,
										   String subDeptFlow,String rotationFlow);

    List<SchArrangeResult> searchSchArrangeResult(String userFlow, String deptFlow);

	List<SysDept> searchSysDeptList(String orgFlow, String searchStr);


	void expertSchTemp(HttpServletRequest request, HttpServletResponse response, String rotationFlow) throws IOException;

}
