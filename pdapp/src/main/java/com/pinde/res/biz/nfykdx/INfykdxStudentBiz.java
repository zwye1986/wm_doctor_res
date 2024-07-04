package com.pinde.res.biz.nfykdx;

import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface INfykdxStudentBiz {

	/**
	 * 查询该学员的轮转计划信息
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> searchResult(Map<String, Object> paramMap);

	/**
	 * 读取一条标准科室信息
	 * @param rotationDeptFlow
	 * @return
     */
	SchRotationDept readRotationDept(String rotationDeptFlow);

	/**
	 * 读取一条组合信息
	 * @param groupFlow
	 * @return
     */
	SchRotationGroup readRotationGroup(String groupFlow);

	/**
	 * 读取一条方案信息
	 * @param rotationFlow
	 * @return
     */
	SchRotation readRotation(String rotationFlow);

	/**
	 * 获取该医师的轮转计划列表
	 * @param doctorFlow
	 * @param resultFlow
	 * @return
	 */
	<T> T searcheDocResult(String doctorFlow, String resultFlow);

	/**
	 * 根据resultFlow获取process
	 * @param resultFlow
	 * @return
	 */
	ResDoctorSchProcess getProcessByResult(String resultFlow);

	/**
	 * 获取这个人的所有科室类型等细分比例和完成数等
	 * @param format
	 * @param userFlow
	 * @return
	 */
	Map<String, Object> getRegPer(int format, String userFlow);

	/**
	 * 获取该科室的比例及完成数等
	 * @param format
	 * @param userFlow
	 * @param resultFlow
	 * @return
	 */
	Map<String, Object> getRegPer(int format, String userFlow, String resultFlow);

	/**
	 * 获取科室下某类型的比例及完成数等
	 * @param format
	 * @param userFlow
	 * @param resultFlow
	 * @param recTypeId
	 * @return
	 */
	Map<String, Object> getRegPer(int format, String userFlow,
								  String resultFlow, String recTypeId);

	/**
	 * 获取可是下某子项的比例及完成数等
	 * @param format
	 * @param userFlow
	 * @param resultFlow
	 * @param recTypeId
	 * @param itemId
	 * @param overMethod
	 * @return
	 */
	Map<String, Object> getRegPer(int format, String userFlow,
								  String resultFlow, String recTypeId, String itemId,
								  boolean overMethod);

	/**
	 * 获取可是下某子项的比例及完成数等计算
	 * @param format
	 * @param overMethod
	 * @param results
	 * @param processMap
	 * @param reqMap
	 * @param itemIdsMap
	 * @param finishedMap
	 * @param resTypes
	 * @return
	 */
	Map<String, Object> getRegPer(int format, boolean overMethod,
								  List<SchArrangeResult> results,
								  Map<String, ResDoctorSchProcess> processMap,
								  Map<String, Float> reqMap, Map<String, List<String>> itemIdsMap,
								  Map<String, Float> finishedMap, List<String> resTypes);


	/**
	 * 获取标准轮转信息
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> getDoctorRotationDept(Map<String, Object> paramMap);

	List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate, String resultFlow, String rotationFlow);

	/**
	 * 编辑轮转信息
	 * @param userFlow
	 * @param standardDeptFlow
	 * @param schDeptFlow
	 * @param schStartDate
	 * @param schEndDate
	 * @param teacherUserFlow
	 * @param headUserFlow
     * @param deptFlow
     * @return
     */
	int editDoctorResult(
			String userFlow,
			String standardDeptFlow,
			String schDeptFlow,
			String schStartDate,
			String schEndDate,
			String teacherUserFlow,
			String headUserFlow,
			String deptFlow
	);

	/**
	 * 删除轮转计划
	 * @param deptFlow
	 * @return
     */
	int delDoctorResult(String deptFlow);

	/**
	 * 更新轮转过程记录
	 * @param process
	 * @return
     */
	int updateProcess(ResDoctorSchProcess process);

	List<ResDoctorSchProcess> searchProcessByDoctor(String userFlow);
}
