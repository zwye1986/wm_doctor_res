package com.pinde.sci.biz.sch;

import com.pinde.sci.excelListens.model.SchedulingDataModel;
import com.pinde.sci.form.sch.SchArrangeResultForm;
import com.pinde.sci.form.sch.SelectDept;
import com.pinde.sci.model.jsres.ArrangTdVo;
import com.pinde.sci.model.mo.*;
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

	Map<String,Object> updateImportData(List<Map<String, ArrangTdVo>> data);

	Map<String,Object> saveDbImportArrang(List<Map<String, ArrangTdVo>> data) throws ParseException;

	Map<String,Object> submitImportData(List<Map<String, ArrangTdVo>> data);
	List<SchArrangeResult> searchSchArrangeResult();

	int countArrangeResultBySchDeptFlow(String nextNextMonth, String schDeptFlow, String sessionNumber) throws ParseException;
	
	SchArrangeResult readSchArrangeResult(String resultFlow);

	List<SchArrangeResult> readSchArrangeResultByExample(SchArrangeResultExample example);
	
	int saveSchArrangeResult(SchArrangeResult result);

	int saveProcessAndResult(ResDoctorSchProcess process,SchArrangeResult result);

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

	List<SchArrangeResult> searchArrangeResultByDate(String schStartDate,
			String doctorFlow);

	List<SchArrangeResult> searchArrangeResultByDate(String schStartDate,
			String schEndDate, String doctorName);

	List<SchArrangeResult> searchArrangeResultByDateAndDoctorFlow(String startDate,
			String endDate, String docFlow);

	List<SchArrangeResult> searchArrangeResultByDateAndDoctorFlows(String startDate,
																  String endDate, List<ResDoctor> doctorList,String schDeptFlow);

	List<Map<String,Object>> searchArrangeResultNotInDates(String startDate,String endDate, String docFlow);

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
	 * 为自由排班(无方案医师)的医师生成排班数据
	 * @param doctorFlow
	 * @param orgFlow
	 * @return
	 */
	int createFreeRosteringResult(ResDoctor doctor);

	/**
	 * 根据医师找出正在轮转和未轮转的计划
	 * @param doctorFlows
	 * @return
	 */
	List<SchArrangeResult> cutAfterResult(List<String> doctorFlows);

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
	
	
	/*int roundRobinStudents(String doctorFlow,String currDate);*/
	List<SchArrangeResult> schArrangeResultQuery(Map<String, Object> map);

	/**
	 * 检查是否存在重叠时间
	 * @param paramMap
	 * @return
	 */
	int checkResultDate(Map<String, Object> paramMap);

	int checkScholarDate(Map<String, Object> paramMap);

	/**
	 * 保存轮转计划和轮转进度
	 * @param result
	 * @param process
	 * @return
	 */
	int editCustomResult(SchArrangeResult result, ResDoctorSchProcess process) throws ParseException;
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

	Map<String,Map<String, BigDecimal>> getScoreByDoctorIds(List<String> doctorFlowList);

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

	List<Map<String,Object>> docWorkingSearchForUni(Map<String, Object> parMp);

	List<Map<String,Object>> orgTeaAuditInfoForUni(Map<String, Object> parMp);

	List<Map<String,Object>> exportCkScore(Map<String, Object> paramMap);

	Map<String,Object> deptWorkingDetail(Map<String, Object> map);

	List<SchArrangeResult> searchSchArrangeResultByDoctorAndRotationFlow(String doctorFlow, String rotationFlow);
	List<SchArrangeResult> searchSchArrangeResultIsBByDoctorAndRotationFlow(String doctorFlow, String rotationFlow);

	List<SchArrangeResult> searchSchArrangeResultByDoctorAndRotationFlowAndStandardId(String doctorFlow, String rotationFlow,String standardDeptId);

	JsresDoctorDeptDetail deptDoctorWorkDetail(String recordFlow, String rotationFlow, String doctorFlow);

	Map<String,Object> doctorDeptAvgWorkDetail(String recruitFlow, String applyYear);

	List<JsresDoctorDeptDetail> deptDoctorAllWorkDetail(String rotationFlow, String doctorFlow, String applyYear);

	void initDocResult(ResDoctor doctor, List<SchRotationDept> schRotationDepts);


	int synchronizeProcessByResults(List<SchArrangeResult> resultList);

	int checkSelectResult(String doctorFlow, String startDate, String endDate);

	int getAllSchMonth(String doctorFlow);

	int checkSelectStandardDept(String doctorFlow, String standardDeptId, String groupFlow);

	String saveDoctorSelectDept(List<SelectDept> selectDepts) throws ParseException;


	List<Map<String,Object>> doctorSelectDeptCount(String doctorFlow);

	int countArrangeResultByGroupFlow(String doctorFlow, String groupFlow);

	int countResultByDoctorAndDate(String d, String doctorFlow) throws ParseException;

	/**
	 * 老板要求，学员轮转，轮转科室的排列按照最上方为轮转中，然后是待入科，最下面是已出科
	 * @param userFlow
	 * @return
     */
	List<SchArrangeResult> searchSchArrangeResultByDoctor4Sort(String userFlow);

	SchArrangeResult getResultBySchRotationDept(Map<String, String> param);

	List<SchArrangeResult> searchSchArrangeResultBySpeAndDoc(Map<String, Object> paramMap);

	int editSchArrangeResult(SchArrangeResult result1);

	List<SchArrangeResult> searchResultByRotationDepts(List<SchRotationDept> depts, String doctorFlow);

	void updateResultHaveAfter(String haveAfterPic, String schRotationDeptFlow, String operUserFlow);

	List<Map<String,String>> searchDocResultsByMonth(Map<String, Object> paramMap);

	List<SchArrangeResult> searchSchArrangeResultByDoctorAndDate(Map<String, String> paramMap);

	List<SchArrangeResult> searchSchArrangeResultByMap(Map<String, String> pMap);

	List<JsresDoctorDeptDetail> deptDoctorAllWorkDetailByNow(String recruitFlow, String doctorFlow, String applyYear);

	List<SchArrangeResult> searchArrangeResultByDateAndOrgByMap(Map<String, String> param);

	List<SysDept> findDeptsByDoctor(String doctorFlow);

	List<HbresDoctorDeptDetail> hbresDoctorSchResults(List<String> doctorFlow);

	List<HbresDoctorDeptDetail> hbresDoctorDeptDetails(String doctorFlow, String applyYear);

	Map<String,String> imgUpload(String resultFlow, MultipartFile checkFile, String fileType);

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

	Map<String,Object>  importSchedulingAuditExcel(MultipartFile file,String rotationFlow,String trainingTypeId) throws IOException, Exception;

	Map<String,String> importSchedulingAuditExcelCache(MultipartFile file) throws IOException, InvalidFormatException;
	Map<String, Object> checkRowData(List<SchedulingDataModel> data);
	Map<String,Object> submitPbImport(List<SchedulingDataModel> data) throws Exception;

	List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate,
										   String subDeptFlow,String rotationFlow);

	public List<SchArrangeResult> searchSchArrangeResult(String userFlow, String deptFlow);

	SysUser searchTeacherList(String sysDeptFlow, String roleId,String userName);

	List<SysDept> searchSysDeptList(String orgFlow, String searchStr);


	void expertSchTemp(HttpServletRequest request, HttpServletResponse response, String rotationFlow) throws IOException;

}
