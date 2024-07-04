package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.DoctorUntiedRecording;
import com.pinde.sci.model.mo.JsresDoctorDeptDetail;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchArrangeResultExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchArrangeResultExtMapper {
	/**
	 * 验证轮转时间是否重叠
	 * @param paramMap
	 * @return
     */
	List<SchArrangeResult> checkResultDate(Map<String,Object> paramMap);

	/**
	 * 培训学员安排轮转进度查询
	 * @param userFlows
	 * @return
     */
	List<Map<String,Object>> countResultByUser(@Param(value="userFlows")List<String> userFlows);
	/**
	 * 查询过程或计划数据
	 * @param paramMap
	 * @return
	 */
	List<SchArrangeResultExt> getResults(Map<String,Object> paramMap);

	void updateResultHaveAfter(@Param("haveAfterPic") String haveAfterPic, @Param("schRotationDeptFlow") String schRotationDeptFlow, @Param("operUserFlow") String operUserFlow);

	List<SchArrangeResult> getResultByDeptFlow(Map<String, Object> paramMap);

	List<SchArrangeResult> searchSchArrangeResults(Map<String, Object> paramMap);

    String queryRotationPlan(Map<String, Object> map);

    int queryStudentsFilldDta(Map<String, Object> paramMap);

	List<DoctorUntiedRecording> queryDoctorUnLockDate(Map<String, Object> queryMap);

	List<JsresDoctorDeptDetail> deptDoctorAllWorkDetailByNow(@Param("recruitFlow") String recruitFlow, @Param("doctorFlow") String doctorFlow, @Param("applyYear") String applyYear);

	/**
	 * @Department：研发部
	 * @Description 查询轮转计划最早的开始时间
	 * @Author fengxf
	 * @Date 2022/3/3
	 */
	String getSchMinStartDate(@Param("rotationFlow") String rotationFlow, @Param("doctorFlow") String doctorFlow);

	/**
	 * @Department：研发部
	 * @Description 查询学员轮转计划组总月数及条数
	 * @Author fengxf
	 * @Date 2022/3/3
	 */
	Map<String, String> getDoctorSchArrange(Map<String, String> param);

    List<Map<String, Object>> searchDocResultsListNew(Map<String, Object> paramMap);

    List<SchArrangeResult> searchSchArrangeResultByMap(Map<String, String> paramMap);
}
