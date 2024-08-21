package com.pinde.sci.biz.sch;

import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.form.sch.SchRotationDeptForm;
import com.pinde.sci.model.mo.*;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface ISchRotationDeptBiz {
	//	List<SchRotationDept> searchSchRotationDept();
	List<SchRotationDeptReq> searchSchRotationDeptReqOrgNull();
	List<SchRotationDept> searchSchRotationDept(String rotationFlow);
	
	SchRotationDept readSchRotationDept(String recordFlow);

	List<SchRotationDeptReq> searchDeptReqByExample(SchRotationDeptReqExample example);

	int saveSchRotationDept(SchRotationDept rotationDept);

	int saveSchRotationDeptForm(SchRotationDeptForm rotationDeptForm);

	List<SchRotationDept> searchSchRotationDeptGroup(String rotationFlow);

//	List<SchRotationDept> searchSchRotationDeptMust(String rotationFlow);

	int saveRotationDeptOrd(String[] recordFlows);

	SchRotationDeptReq readDeptReq(String reqFlow);

	int editDeptReq(SchRotationDeptReq deptReq);

//	SchRotationDept readSchRotationDept(String schDeptFlow, String rotationFlow);

//	List<SchRotationDeptReq> searchDeptReq(String rotationFlow,String schDeptFlow,String recTypeId);

	List<SchRotationDeptReq> searchDeptReq(String rotationFlow,
			String schDeptFlow);

	List<SchRotationDeptReq> searchReqByRotationAndSchDept(
			List<String> rotationFlows, List<String> schDeptFlows,
			String recTypeId, String itemName);

	List<SchRotationDeptReq> searchDeptReqByItemId(String rotationFlow,
			String schDeptFlow, String itemName);

	List<Map<String, Object>> countReqWithSchDept(List<String> rotationFlows,
			List<String> standardDeptIds);


	List<SchRotationDept> searchRotationDeptByFlows(List<String> recordFlows);
	List<SchRotationDept> searchRotationDeptByFlows();

	int saveSelDepts(List<String> recordFlows,Map<String,String> schMonthMap,ResDoctor doctor);

//	int delSchRotationDeptForm(SchRotationDeptForm rotationDeptForm);

	int saveSelDeptsAndResult(List<String> recordFlows,Map<String,String> schMonthMap,ResDoctor doctor);

	List<SchRotationDept> searchDeptByRotations(List<String> rotationFlows);

	int saveRotationDeptList(List<SchRotationDept> rotationDeptList,
			SchRotationGroup group);

	List<SchRotationDeptReq> searchDeptReqByRel(String relRecordFlow,
			String recTypeId);

	List<SchRotationDeptReq> searchDeptReqByRel(String relRecordFlow);

//	List<SchRotationDeptReq> searchStandardReqByMust(
//			String rotationFlow, String standardDeptId);

	List<SchRotationDeptReq> searchStandardReqByGroup(String standardGroupFlow,
			String standardDeptId);
	List<SchRotationDeptReq> searchStandardReqByGroupAndOrgSessionNumber(String standardGroupFlow,
			String standardDeptId,String orgFlow,String sessionNumber);

	List<SchRotationDeptReq> searchStandardReqByResult(SchArrangeResult result,ResDoctor doctor);

	int updateAreaRule(String rotationFlow, String standardDeptId,
			String groupFlow, SysOrg org);

	SchRotationDept readStandardRotationDeptByLocal(String rotationFlow,
			String groupFlow, String standardDeptId);

	List<SchRotationDeptReq> searchStandardReqByRelFlows(
			List<String> relRecordFlows);

	List<SchRotationDeptReq> searchStandardReqByResult(SchArrangeResult result,
			String recTypeId);

	List<SchRotationDeptReq> searchDeptReqByRel(String relRecordFlow,
			String recTypeId, String itemName);

//	List<SchRotationDept> searchRotationDeptByGroupFlow(String groupFlow);

//	List<SchRotationDept> searchSelDeptByRotations(List<String> rotationFlows);

	List<SchRotationDept> searchOrgSchRotationDept(String rotationFlow,
			String orgFlow);
	List<SchRotationDept> searchSchRotationDeptByItems(Map<String,String> paramMap);

	List<SchRotationDept> searchDeptByStandard(String rotationFlow,
			String groupFlow, String standardDeptId, String orgFlow);

	List<SchRotationDept> searchDeptByMap(Map<String,String> parameMap);

	int saveSchRotationDeptForm(SchRotationDeptForm rotationDeptForm,
			String rotationFlow);

	List<SchRotationDept> searchOrgSchRotationDeptMust(String rotationFlow,
			String orgFlow);

	List<SchRotationDept> searchOrgSelDeptByRotations(
			List<String> rotationFlows, String orgFlow);

	List<SchRotationDept> searchOrgSchRotationDeptGroup(String rotationFlow,
			String orgFlow);

	int delGroupOrRotationDept(String recordFlow, String groupFlow,
			String rotationFlow);

	/**
	 * 根据一条排班数据flow查询标准规则科室
	 * @param resultFlow
	 * @return
	 */
	SchRotationDept readStandardRotationDept(String resultFlow);

	List<SchRotationDept> readStandardRotationDeptByExample(SchRotationDeptExample example);

	/**
	 * 根据多条排班数据flow查询标准规则科室
	 * @param resultFlows
	 * @return
	 */
	List<Map<String,String>> readStandardRotationDept(List<String> resultFlows);

	/**
	 * 根据Flow集合获取rotationDept集合
	 * @param recordFlows
	 * @return
	 */
	List<SchRotationDept> searchRotationDeptByRecordFlows(
			List<String> recordFlows);

	/**
	 * 根据方案获取必轮规则,包含大字段
	 * @param rotationFlow
	 * @return
	 */
//	List<SchRotationDept> searchSchRotationDeptMustWithBLOBs(String rotationFlow);

	/**
	 * 根据组合内规则,包含大字段
	 * @param groupFlow
	 * @return
	 */
	List<SchRotationDept> searchRotationDeptByGroupFlowWithBLOBs(
			String groupFlow);

	/**
	 * 计算未关联的科室数量
	 * @param orgFlow
	 * @param rotationFlow
	 * @return
	 */
	int getUnrelCount(String orgFlow, String rotationFlow);

	/**
	 * 获取指定条件要求
	 * @param standardGroupFlow
	 * @param standardDeptId
	 * @param recTypeId
	 * @param itemId
	 * @return
	 */
	List<SchRotationDeptReq> searchStandardReqByStandard(
			String standardGroupFlow, String standardDeptId, String recTypeId,
			String itemId);

	/**
	 * 检测是否拥有默认的其他项,如果没有自动添加
	 * @param relRecordFlow
	 * @param recTypeId
	 * @return
	 */
	int defaultOtherItem(String relRecordFlow, String recTypeId);

	/**
	 * 计算该轮转科室的使用情况
	 * @param schDeptFlow
	 * @param deptFlow
	 * @return
	 */
	int getSchInUsedCount(String schDeptFlow,String deptFlow);

	/**
	 * 计算方案内时间必轮
	 * @param orgFlow
	 * @return
	 */
	List<Map<String, Object>> getRotationMustSum(String orgFlow);

	/**
	 * 计算方案内时间选科
	 * @param orgFlow
	 * @return
	 */
	List<Map<String, Object>> getRotationGroupSum(String orgFlow);

	/**
	 * 获取当前这个机构标准规则下的轮转科室
	 * @param standardDeptRecFlow
	 * @return
	 */
	List<SchRotationDept> searchRotationDeptByStanard(
			String standardDeptRecFlow, String orgFlow);
	List<SchRotationDept> searchAllSchRotationDept();
	/**
	 * 通过relRecordFlow查询
	 * @param relRecordFlow
	 * @return
	 */
	List<SchRotationDeptReq> searchSchRotationDeptReq(String relRecordFlow);
//	int synchronizeReq(String relRecordFlow, String currRelRecordFlow);
//	List<SchRotationDept> searchSchRotationDeptByCurrFlow(
//			String currRelRecordFlow);
	List<SchRotationDeptReq> searchRotationDeptReq(String relRecordFlow,
			String recTypeId);
	int synchronizeReq(String relRecordFlow, String currRelRecordFlow,
			String recTypeId);
	/**
	 * 通过groupFlow同standardDeptId查询
	 * @param groupFlow,standardDeptId
	 * @return
	 */
	SchRotationDept searchGroupFlowAndStandardDeptIdQuery(String groupFlow, String standardDeptId);
	SchRotationDept searchGroupFlowAndStandardDeptIdQueryTwo(String groupFlow, String standardDeptId);

	List<SchRotationDept> searchOrgSchRotationDeptBySessionNumber(String rotationFlow, String orgFlow, String sessionNumber);

	/**
	 * 保存
	 * @param schRotationDeptReqList
     */
	void initSchRotationDeptReq(List<SchRotationDeptReq> schRotationDeptReqList);

	List<SchRotationDept> doctorGetNotSchDept(String rotationFlow, String doctorFlow);

	ExcelUtile importDeptReqExcel(MultipartFile file, Map<String,Object> map);

	ExcelUtile importDeptDataExcel(MultipartFile file, Map<String,Object> map);

	//导入
	Map importRotationFromExcel(MultipartFile file);

	List<SchRotationDeptReq> searchDeptReqByRel2(List<List<String>> relRecordFlowListList);

	Pair<List<SchRotationDeptReq>, List<SchRotationDept>> searchStandardReqByGroup2(List<String> standardGroupFlowList, List<String> standardDeptIdList);

	List<SchRotationDept> searchSchRotationDeptByPartitionList(List<List<String>> rotationFlowListList);
}
