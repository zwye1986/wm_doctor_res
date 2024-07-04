package com.pinde.res.biz.njmu2;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.res.model.nfyy.mo.Evaluation;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;
import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchRotation;

public interface INjmu2StudentBiz {

	/**
	 * 查询该学员的轮转计划信息
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> searchResult(Map<String, Object> paramMap);

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
	 * 读取一条方案信息
	 * @param rotationFlow
	 * @return
	 */
	SchRotation readRotation(String rotationFlow);
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
	
}
