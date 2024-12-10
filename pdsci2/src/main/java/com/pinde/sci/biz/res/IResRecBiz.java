package com.pinde.sci.biz.res;

import com.pinde.core.model.SysUser;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorSchProcessExt;
import com.pinde.sci.model.res.ResRecExt;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 */
public interface IResRecBiz {
	/**
	 * 根据主键查找
	 * @param recFlow
	 * @return
	 */
	ResRec readResRec(String recFlow);
	/**
	 * 新增或修改
	 * @param rec
	 * @return
	 */
	int edit(ResRec rec);

	List<ResRec> searchByRec(String recTypeId, String schDeptFlow,
			String operUserFlow);
	Map<String, Object> parseGradeXml(String recContent);

//	/**
//	 * 查询各类别未审核的表单数目
//	 * @param userFlow 当前登录用户
//	 * @param roleFlag teacher: 带教老师,head：科主任
//	 * @return Map key:类别id,value:数目
//	 */
//	Map<String,BigDecimal> searchAuditCount(String userFlow,String roleFlag);

	//	int saveRecContentForm(String recFlow,String recTypeId,String roleFlag, String processFlow, Object form);
//	List<ResRec> searchByRec(List<String> recTypeIds, String schDeptFlow,
//			String operUserFlow);
	int editAppeal(ResAppeal appeal);
	List<ResAppeal> searchAppeal(String recTypeId, String schDeptFlow,
			String operUserFlow,String processFlow);

	//	List<ResAppeal> searchAppeal(ResAppeal appeal);
	List<ResAppeal> searchAppeal(ResAppeal appeal, String starTime,
			String endTime);
	List<ResAppeal> searResAppeal(String processFlow,String recTypeId,String userFlow,String itemId);
	int searResRecWan(String processFlow,String recTypeId,String itemId);

	//	ResAppeal readAppeal(String appealFlow);
	List<ResRec> searchByRec(String recTypeId, List<String> schDeptFlows,
			String operUserFlow);
	List<ResAppeal> searchAppeal(List<String> schDeptFlows, ResAppeal appeal);
	List<ResRecExt> searchRegistryList(String userFlow, String roleFlag,
			ResRec rec,ResDoctorSchProcess process);
	List<ResRec> searchResRec(List<String> schDeptFlows, ResRec rec);
	List<ResRecExt> searchScoreList(String userFlow, String roleFlag,
			String sessionNumber, String recTypeId, String isCurrentFlag);
	List<ResRecExt> searchTeacherAudit(String schDeptFlow,
			String isCurrentFlag, String userFlow);
	Map<String, Object> parseRecContent(String content);
	List<ResRec> searchByRecWithBLOBs(String recTypeId, String schDeptFlow,
			String operUserFlow);
	Map<String,List<Map<String, String>>> parseRecContentAppraise(String content);
	List<ResRec> searchFinishRec(List<String> recTypeIds, String operUserFlow);
	Map<String, String> getFinishPer(List<SchArrangeResult> arrResultList);

    String getRecContent(String recContent, String formName, List<Element> list,
						 HttpServletRequest req);
	int editAndOut(ResRec rec, ResDoctorSchProcess process);
	List<ResRec> searchByRecWithBLOBs(List<String> recTypeIds,
			String schDeptFlow, String operUserFlow);
	List<ResDoctorSchProcessExt> searchProcessExt(
			ResDoctorSchProcessExt processExt);
	List<ResRec> searchRecByUserAndSchdept(List<String> userFlows,
			List<String> processFlows, String recTypeId, String itemName);
	List<ResRec> searchByRecWithBLOBs(String recTypeId, String schDeptFlow,
			String operUserFlow, String itemName);

	//	List<ResRecExt> searchRecExtByRecExt(ResRecExt recExt);
	List<Map<String,Object>> countProcessByUser(List<String> userFlows);
	Map<String, String> getFinishPer(List<SchArrangeResult> arrResultList,
			String doctorFlow);


	Map<String, Map<String,String>> getFinishPer2List(Map<String, List<SchArrangeResult>> arrResultListMap, List<ResDoctor> resDoctorList, Map<String, SysUser> userFlowToEntityMap,
									 Map<String, SchArrangeResult> arrangeResultMap, Map<String, SchRotationGroup> rotationGroupFlowToEntityMap, Map<String, SchRotationDept> keyToRotationDepeMap);

	List<ResRec> searchFinishRec2(Map<String, List<String>> medicineToDoctorMap, Map<String, List<String>> medicinToRecTypeMap);

	List<SchDoctorDept> searchReductionDept2(List<ResDoctor> resDoctorList);

	Map<String, String> getStandardDeptFinishPer(List<SchRotationDept> depts,
			String doctorFlow);
	String getFormPath(String recTypeId, String currVer, String recForm, String type,String medicineTypeId);
	List<Map<String, Object>> countRecWithDoc(List<String> userFlows,
			List<String> processFlows);
	List<Map<String, Object>> appealCountWithUser(List<String> userFlows,
			List<String> processFlows);
	List<Map<String, Object>> countRecWithDoc(List<String> userFlows,
			List<String> processFlows, String isAudit);
	List<ResRec> searchByRecForAudit(String processFlow, String recTypeId);
	List<Map<String, Object>> appealCountWithUser(List<String> userFlows,
			List<String> processFlows, String roleFlag);
	List<ResAppeal> searchAppealForAudit(String processFlow, String recTypeId);


    List<Map<String, Object>> searchAppealCount(String schDeptFlow,
			String operUserFlow);
	int oneKeyAudit(String recTypeId, String schDeptFlow, String operUserFlow);

	int oneKeyAuditAll( String operUserFlow);

    int oneKeyAuditByOrg(String orgFlow,String userFlow);

	/**
	 * 解析form去展示
	 * @param recForm
	 * @param recType
	 * @param recVer
	 * @param recContent
	 * @return
	 */
	List<Map<String, String>> parseViewValue(String recForm, String recType,
			String recVer, String recContent);
	
	List<Map<String, Object>> searchTeacherAuditCount(String headUserFlow,
			String isAudit);

	//	List<ResRec> searchByUserFlows(String recTypeId, String schDeptFlow,
//			List<String> operUserFlows);
	List<ResRec> searchRecInfo(ResRec resRec,List<String> operUserFlows);
	
	List<ResRec> searchInfo(ResRec resRec,List<String> operUserFlows,List<String> orgFlowList);
	List<Map<String, Object>> searchDoctorNotAuditCount(String schDeptFlow,
			 String teacherUserFlow, String isAudit,List<String> recTypeIds);
	List<Map<String, Object>> searchNotAuditAppealCount(String schDeptFlow,
			String teacherUserFlow, String isAudit);

	//	List<ResRec> searchByUserFlowsWithBLOBs(String recTypeId,
//			String schDeptFlow, List<String> operUserFlows);
	Map<String, Object> parseContent(String content);
	ResRec searchRecWithBLOBs(String recTypeId, String operUserFlow);
	
	/**
	 * 根据类型和操作人查找包含大字段内容的记录
	 * @param recTypeId
	 * @param operUserFlow
	 * @return
	 */
	List<ResRec> searchByRecWithBLOBs(String recTypeId, String operUserFlow);
	
	List<ResRec> searchByRecWithBLOBs(ResRec resRec, String trainYear);

	List<ResRec> searchByRecWithBLOBsBySchRotationFlows(String recTypeId, String doctorFlow, List<String> schRotationDeptFlows);
	
	/**
	 * 通过 教学安排 的大字段和itemGroup的flow获取itemGroup数据
	 * @param content
	 * @return
	 */
	Map<String, Object> parseTeachPlanContent(String content,String recordFlow);
	
	/**
	 * 编辑岗前培训表
	 * @param recFlow
	 * @param roleFlag
	 * @param req
	 * @return
	 */
	int editPreTrainForm(String recFlow, String resultFlow, String roleFlag,HttpServletRequest req);


    /**
	 * 保存年度培训记录
	 * @param recFlow
	 * @param roleFlag
	 * @param req
	 * @return
	 */
	int editAnnualTrainForm(String recFlow, String roleFlag,
			HttpServletRequest req);


    /**
	 * 获取用户们的单一类型记录
	 * @param userFlows
	 * @param recTypeId
	 * @return
	 */
	List<ResRec> searchRecByUserFlows(List<String> userFlows, String recTypeId);

	//	int editSpeAbilityAccess(String recFlow, String resultFlow,
//			String roleFlag, HttpServletRequest req);
//	Map<String, Map<String, String>> getSpeAbilityAssessDataMap(
//			String recContent);
	List<ResRec> searchByUserFlowAndTypeId(String operUserFlow,String recTypeId);
	int editRec(ResRec rec);
	
	/**
	 * 根据表单类型和轮转科室获取rec记录
	 * @param recTypeId
	 * @param schDeptFlows
	 * @return
	 */
	List<ResRec> searchByDeptWithBLOBs(String recTypeId,
			List<String> schDeptFlows);
	
	/**
	 * 查询当前带教或科主任的待出科人员数据
	 * @param process
	 * @param user
	 * @param recTypeIds
	 * @return
	 */
	List<ResRec> searchAfterAuditRec(ResDoctorSchProcess process, SysUser user,
			List<String> recTypeIds,Map<String,String> roleFlagMap);
	
	/**
	 * 根据rec流水集合获取含大字段rec
	 * @param recFlows
	 * @return
	 */
//	List<ResRec> searchByRecWithBLOBs(List<String> recFlows);
	
	/**
	 * 根据rec流水集合审核rec
	 * @param recFlows
	 * @return
	 */
	int auditRecs(String[] recFlows, ResRec rec);

    /**
	 * 保存表单
	 * @param formFileName
	 * @param recFlow
	 * @param schDeptFlow
	 * @param rotationFlow
	 * @param req
	 * @param orgFlow
	 * @param medicineTypeId
	 * @return
	 */
	int saveRegistryForm(String formFileName, String recFlow,
						 String schDeptFlow, String rotationFlow, HttpServletRequest req,
						 String orgFlow, String medicineTypeId,String canEditAppendix);
	
	/**
	 * 根据过程记录和类型获取登记数据
	 * @param processFlow
	 * @param recTypeId
	 * @return
	 */
	List<ResRec> searchRecByProcessWithBLOBs(String processFlow,
			String recTypeId);
	List<ResSchProcessExpress> searchRecByProcessWithBLOBs(String processFlow,
			List<String> recTypeIds);
	ResSchProcessExpress readResSchProcessExpress(String recFlow);
	
	List<ResRec> searchRecByProcess(String processFlow,String doctorFlow);
	/**
	 * 获取该机构待出科情况
	 * @param orgFlow
	 * @return
	 */
	List<Map<String,Object>> searchDeptWillAfter(String orgFlow);
	/**
	 * 获取该机构待出科情况
	 * @param orgFlow
	 * @return
	 */
	List<Map<String, Object>> searchDeptWillAfterDoc(String orgFlow,String deptFlow);
	
	/**
	 * 根据process和登记类型集合查询该用户的登记数据
	 * @param recTypeIds
	 * @param processFlow
	 * @param operUserFlow
	 * @return
	 */
	List<ResRec> searchRecByProcessWithBLOBs(List<String> recTypeIds,
			String processFlow, String operUserFlow);

	List<ResRec> searchRecAuditByProcessWithBLOBs(List<String> recTypeIds,
											 String processFlow, String operUserFlow);
	
	/**
	 * 计算该用户的登记进度
	 * @param doctorFlow
	 * @param rotationFlow
	 * @return
	 */
	Map<String, String> getProcessPer(String doctorFlow, String rotationFlow);
	ResRec queryResRec(String processFlow, String operUserFlow,String recTypeId);
	Map<String, String> resRecImg(String recFlow, MultipartFile file,String fileAddress);
	Map<String, String> upload(MultipartFile file,String fileAddress);
	Map<String, String> getProcessPer(String doctorFlow, String rotationFlow,
			String relRecordFlow);
	List<ResRec> searchByResRecWithBLOBs(String recTypeId, String schRotationDeptFlow,
										 String operUserFlow, String itemId);

	void updateResultHaveAfter(String schRotationDeptFlow, String operUserFlow, String recContent) throws DocumentException;

	Map<String, String> resRecImgs(String recFlow, MultipartFile file,
								   String fileAddress);

	List<ResRec> searchResRecWithBLOBs(String recTypeId, String processFlow,
			String operUserFlow);



	List<ResRec> searchResRecWithBLOBs(String recTypeId, String processFlow,
			String operUserFlow,String itemId);
	Map<String, String> getProcessPer(String doctorFlow, String rotationFlow,
			String relRecordFlow, int format);
	Map<String, String> getProcessPer(String doctorFlow, String rotationFlow,
			String relRecordFlow, int format, List<ResRec> recList);
	
	/**
	 * 查找轮转中登记信息
	 * @param paramMap
	 * @return
	 */
	List<ResRec> searchProssingRec(Map<String, Object> paramMap);
	
	
	List<ResRec> searchByUserFlow(String operUserFlow);

	/**
	 * 根据dept获得resRec数据
	 * @param deptFlow
	 * @return
	 */
//	int searchByDeptFlow(String deptFlow);
	/**
	 * 根据老师查所有学员老师评价表
	 * @param teacherUserFlow
	 * @return
	 */
	List<ResRec> searchProssFlowRec(String teacherUserFlow,String startDate,String endDate);
	List<ResRec> resSearchProssFlowRec(String userFlow, String startDate, String endDate);

	/**
	 * 根据科室查所有学员对科室的评价
	 * @param deptFlow
	 * @return
	 */
	List<ResRec> searchDeptFlowRec(String deptFlow,String startDate,String endDate);
	List<ResRec> resSearchDeptFlowRec(String deptFlow,String startDate,String endDate);

	/**
	 * 按条件获取比例
	 * @param doctorFlow
	 * @param processFlow
	 * @param recTypeId
	 * @param itemId
	 * @return
	 */
	Map<String, Object> getRecProgressIn(String doctorFlow, String processFlow,
			String recTypeId, String itemId);

	Map<String, Object> getRecAuditProgressIn(String doctorFlow, String processFlow,
										 String recTypeId, String itemId);
	/**
	 * 获取某类型的登记数据
	 * @param recTypeId
	 * @param rotationDeptFlow
	 * @param operUserFlow
	 * @return
	 */
	List<ResRec> searchRecByProcess(String recTypeId, String rotationDeptFlow, String operUserFlow);

	/**
	 * 获取某类型的登记数据和活动 数据
	 * @return
	 */
	List<ResRec> searchRecAndActivityByProcess(Map<String, Object> paramMap);


    /**
	 * 获取评分内容
	 * @param paramMap
	 * @return
	 */
	List<Map<String,String>> getRecContentByProcess(Map<String, Object> paramMap);
	List<Map<String,String>> getRecContentByProcessForUni(Map<String, Object> paramMap);

		List<ResRec> searchResRecWithBLOBsByRotationDeptFlow(String recTypeId,
			String recordFlow, String operUserFlow);

	/**
	 * 根据老师获取未审核数
	 * @param teacherUserFlow
	 * @param typeList
	 * @return
	 */
	int notAudited(String teacherUserFlow, String startDate, String endDate, List<String> typeList);
	/**
	 * 根据老师获取已审核数
	 * @param teacherUserFlow
	 * @param typeList
	 * @return
	 */
	int isNotAudited(String teacherUserFlow, String startDate, String endDate, List<String> typeList);

	/**
	 * 根据老师list获取未审核数
	 * @return
	 */
	List<Map<String,String>> notAuditedMaps(Map<String, Object> paramMap);
	/**
	 * 根据老师list获取已审核数
	 * @return
	 */
	List<Map<String,String>> isNotAuditedMaps(Map<String, Object> paramMap);
	List<ResRec> searchProssFlowRec(String teacherUserFlow);
	
	List<Map<String, String>> parseImageXml(String content) throws DocumentException;

	List<Map<String, String>> parseTitle(String recForm,String recType,String recVer);
	
	/**
	 * 保存表单
	 * @param req
	 * @return
	 */
//	int saveTheRecForm(HttpServletRequest req);
	/**
	 * 获取表单路径
	 * @param rotationFlow
	 * @param recTypeId
	 * @param currVer
	 * @param recForm
	 * @param type
	 * @return
	 */
	String getFormPath(String rotationFlow, String recTypeId, String currVer,
					   String recForm, String type,String medicineTypeId,String recordFlow);
	List<ResRec> searchByProcessFlowAndRecTypeIdClob(String processFlow,String recTypeId);

	List<ResRec> searchByRecAndProcess(String recTypeId, String operUserFlow, String processFlow);

	/**
	 * 查询学员的数据登记信息
	 * @param recTypeIds
	 * @param roleFlagMap-
     * @return
     */
	List<ResRec> searchSXSRecData(List<String> recTypeIds, Map<String, String> roleFlagMap);


	List<Map<String,String>> findTrainCharts(List<String> orgFlowList, String year, String speName);


	/****************************高******校******管******理******员******角******色************************************************/

	List<Map<String,String>> searchInfoForUni(ResRec resRec,ResDoctor doctor);

	List<ResRec> searchDelayInfoForUni(ResRec resRec, ResDoctor doctor);

	List<Map<String,String>> searchInfo2(ResRec resRec, ResDoctor currdoctor, List<String> userFlowList, List<String> orgFlowList);

	String getTeaAuditRecContent(String formName, List<Element> itemList, HttpServletRequest req, Element root);

	int orgBatchAuditDoctorInfo(Map<String, Object> param);


	List<SchDoctorDept> searchReductionDept(String userFlow, String rotationFlow, String secondRotationFlow);

	ResStandardDeptPer getStandardDeptPer(String doctorFlow, String recordFlow, String rotationFlow);

	List<TeachingActivityInfo> searchJoinActivityByProcessFlow(String processFlow);

	List<TeachingActivityInfo> searchJoinActivityByProcessFlownotScore(String processFlow);

	List<TeachingActivityInfo> searchJoinActivityByStrandDeptFlow(String doctorFlow, String recordFlow);

	List<TeachingActivityInfo> searchJoinActivityByStrandDeptFlowAndNotScore(String doctorFlow, String recordFlow);

	List<Map<String,Object>> queryJoinActivityAndEditList(String doctorFlow, String recTypeId, String processFlow);

	List<ResRec> searchByRecWithBLOBsByRotationDeptFlows(String doctorFlow, List<String> schRotationDeptFlows);

	Map<String,Object> parseDocotrGradeXml(String recContent);

	Map<String,Object> parseDeptGradeXml(String recContent);

	ExcelUtile importJsresData(MultipartFile file, String recordFlow, String processFlow, String doctorFlow, String recTypeId);


    /**
	 * @Department：研发部
	 * @Description 查询审核信息
	 * @Author fengxf
	 * @Date 2020/11/18
	 */
	List<Map<String,String>> searchAuditedDataList(Map<String, Object> paramMap);

	/**
	 * @Department：研发部
	 * @Description 查询审核信息拆分成每个学员
	 * @Author xieyongheng
	 * @Date 2023/4/12
	 */
	List<Map<String,String>> searchAuditedDataListByOper(Map<String, Object> paramMap);

	//查询护士评价学员的信息
	List<Map<String,String>> searchNurseAssEvaluate(Map<String, Object> paramMap);
}
	
	
