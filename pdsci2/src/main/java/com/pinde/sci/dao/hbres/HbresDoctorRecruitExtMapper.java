package com.pinde.sci.dao.hbres;

import com.pinde.sci.form.hbres.ResDoctorTrainingSpeForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.ResExamDoctor;

import java.util.List;
import java.util.Map;

public interface HbresDoctorRecruitExtMapper {
	/**
	 * 住培注册学员统计
	 * @param paramMap
	 * @return
	 */
	List<ResDoctorTrainingSpeForm> searchRegisterStatistics(Map<String, Object> paramMap);

	/**
	 * 学员重报
	 * @param doctorFlow
	 * @return
     */
	List<ResExamDoctor> searchExamByDoctor(String doctorFlow);

	/**
	 * 获取已招收人数
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> getRecruitDocCount(Map<String, Object> paramMap);

	/**
	 * 查询省厅可以调整的学员列表
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>> searchSwapDocList(Map<String, Object> paramMap);

	/**
	 * 查询省厅调剂学员人数
	 * @param paramMap
	 * @return
     */
	List<Map<String,Object>> getAdminSwapCount(Map<String, Object> paramMap);

	//根据复合条件查询报到信息
	List<Map<String,String>> searchRegistList(Map<String,Object> paramMap);

	List<JsResDoctorRecruitExt> searchJsDoctorScoreExtList(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchDoctorSkillAndTheoryScoreExts(Map<String, Object> paramMap);
	List<JsResDoctorRecruitExt> searchJsDoctorSkillScoreExtList(Map<String, Object> paramMap);
}
