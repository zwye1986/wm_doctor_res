package com.pinde.res.biz.jszy;

import com.pinde.core.model.*;
import com.pinde.res.model.jszy.mo.UploadFileForm;

import java.util.List;
import java.util.Map;

public interface IJszyStudentBiz {


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

	List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate, String resultFlow, String rotationFlow, String secondRotationFlow);

	List<ResDoctorSchProcess> searchProcessByDoctor(String userFlow);


	String getTeacherName(String userFlow);

	ResBookStudyRecord getBookStudyRecord(String recordFlow);


	/**
	 * 保存结业考核
	 * @param graduationAssessmentWithBLOBs
	 * @param fileFlows
	 *@param members @return
	 */
	int save(ResGraduationAssessmentWithBLOBs graduationAssessmentWithBLOBs, SysUser user, List<String> fileFlows, List<UploadFileForm> members);

}
