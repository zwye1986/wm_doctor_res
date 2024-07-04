package com.pinde.res.dao.zsyk.ext;

import com.pinde.res.model.nfyy.mo.Activity;
import com.pinde.res.model.nfyy.mo.Evaluation;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZsykStudentMapper {

	/**
	 * 获取医师轮转科室
	 * @param userFlow 用户流水号
	 * @param schStatusId 查询条件
	 * @return
	 */
	List<Map<String,Object>> selectSchPlan(@Param("userFlow") String userFlow, @Param("schStatusId") String schStatusId,
                                           @Param("schDeptName") String schDeptName, @Param("start") int start, @Param("end") int end, @Param("examStatusId") int examStatusId);
	//入科教育
	/**
	 * 获取医师轮转科室的入科教育记录
	 * @param schDeptFlow
	 * @return
	 */
	public Map<String,Object> selectEnteredDeptEdu(@Param("schDeptFlow") String schDeptFlow);

	/**
	 * 插入一条入科教育记录
	 * @param enteredDeptEdu
	 */
	void insertEnteredDeptEdu(Map<String, Object> enteredDeptEdu);

	/**
	 * 修改一条入科教育记录
	 * @param enteredDeptEdu
	 */
	void updateEnteredDeptEdu(Map<String, Object> enteredDeptEdu);

	//学习情况
	/**
	 * 查询医师学习情况
	 * @param schDeptFlow
	 * @return
	 */
	List<StudyInfo> selectStudyInfos(@Param("schDeptFlow") String schDeptFlow, @Param("start") int start, @Param("end") int end);

	/**
	 * 查询医师学习情况详细
	 * @param dataFlow
	 * @return
	 */
	StudyInfo selectStudyInfo(@Param("dataFlow") String dataFlow);

	/**
	 * 新增一个学习情况的记录
	 * @param studyInfo
	 */
	void insertStudyInfo(StudyInfo studyInfo);

	/**
	 * 更新一条学习情况的记录
	 * @param studyInfo
	 */
	void updateStudyInfo(StudyInfo studyInfo);

	/**
	 * 删除一条学习情况的记录
	 * @param dataFlow
	 */
	void delStudyInfo(@Param("dataFlow") String dataFlow);
	//病例与管床
	/**
	 * 查询医师病例与管床
	 * @param schDeptFlow
	 * @return
	 */
	List<MedicalInfo> selectMedicalInfos(@Param("schDeptFlow") String schDeptFlow, @Param("start") int start, @Param("end") int end);

	/**
	 * 查询医师病例与管床详细
	 * @param dataFlow
	 * @return
	 */
	MedicalInfo selectMedicalInfo(@Param("dataFlow") String dataFlow);

	/**
	 * 新增一个病例与管床
	 * @param medicalInfo
	 */
	void insertMedicalInfo(MedicalInfo medicalInfo);

	/**
	 * 更新病例与管床
	 * @param medicalInfo
	 */
	void updateMedicalInfo(MedicalInfo medicalInfo);

	/**
	 * 删除一天病例与管床的记录
	 * @param dataFlow
	 */
	void delMedicalInfo(@Param("dataFlow") String dataFlow);

	//活动
	/**
	 * 根据查询条件查询活动信息
	 * @param paramMap
	 * @return
	 */
	List<Activity> selectActivitys(Map<String, Object> paramMap);

	/**
	 * 查询活动信息详细
	 * @param dataFlow
	 * @return
	 */
	Activity selectActivity(@Param("dataFlow") String dataFlow, @Param("userFlow") String userFlow);

	/**
	 * 根据主键查询活动详情
	 * @param dataFlow
	 * @return
	 */
	Activity selectActivityByFlow(@Param("dataFlow") String dataFlow);

	/**
	 * 插入活动
	 * @param activity
	 */
	void insertActivity(Activity activity);

	/**
	 * 更新活动打分
	 * @param activity
	 */
	void updateActivity(Activity activity);

	//出科小结
	/**
	 * 获取自我评价信息
	 * @param schDeptFlow
	 * @return
	 */
	Evaluation selectOutSecBrief(@Param("schDeptFlow") String schDeptFlow);

	/**
	 * 插入一条自我评价
	 * @param evaluation
	 */
	void insertOutSecBrief(Evaluation evaluation);

	/**
	 * 更新自我评价内容
	 * @param evaluation
	 */
	void updateOutSecBrief(@Param("schDeptFlow") String schDeptFlow, @Param("briefRequrie") String briefRequrie);

	//评价科室与老师
	/**
	 * 获取评价模板
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> selectExamItem(@Param("type") String type);


	List<String> selectExamItemId(@Param("type") String type);

	//评价科室与老师
	/**
	 * 获取评价模板
	 * @param
	 * @return
	 */
	List<Map<String, Object>> selectExamItemByExamItemID(@Param("ExamItemID") String ExamItemID);

	/**
	 * 获取评价模板
	 * @param type
	 * @return
	 */
	Map<String, Object> readExamInfo(@Param("type") String type, @Param("schDeptFlow") String schDeptFlow);

	/**
	 * 添加评价信息
	 * @param examInfo
	 */
	int insertExamInfo(Map<String, Object> examInfo);

	/**
	 * 添加评价具体信息
	 * @param mark
	 */
	void insertMark(Map<String, Object> mark);

	/**
	 * 查询评价科室的信息
	 * @param secId
	 * @return
	 */
	List<Map<String, Object>> selectMarksForDept(@Param("userFlow") String userFlow, @Param("secId") String secId);

	/**
	 * 查询评价带教老师信息
	 * @param tecId
	 * @param userFlow
	 * @return
	 */
	List<Map<String, Object>> selectMarksForTec(@Param("tecId") String tecId, @Param("userFlow") String userFlow);

	//量表
	void modOutDops(Map<String, Object> params);

	void modOutMiniCex(Map<String, Object> params);

	//------------辅助的sql----------------

	/**
	 * 获取轮转科室入科文档ID
	 * @param schDeptFlow
	 * @return
	 */
	String selectReadSecDocumet(@Param("schDeptFlow") String schDeptFlow, @Param("userType") String userType);

	/**
	 * 获取轮转id
	 * @param schDeptFlow
	 * @return
	 */
	String selectCySecId(@Param("schDeptFlow") String schDeptFlow);

	/**
	 * 查询带教id
	 * @param schDeptFlow
	 * @return
	 */
	String selectTecId(@Param("schDeptFlow") String schDeptFlow);

	/**
	 * 查询secid
	 * @param schDeptFlow
	 * @return
	 */
	String selectSecId(@Param("schDeptFlow") String schDeptFlow);

	/**
	 * 获取科室名
	 * @param schDeptFlow
	 * @return
	 */
	String selectHosSecName(@Param("schDeptFlow") String schDeptFlow);

	/**
	 * 获取科室名
	 * @param schDeptFlow
	 * @return
	 */
	String selectSecName(@Param("doctorFlow") String schDeptFlow);

	List<Map<String,String>> selectExamItemByType(@Param("type") String type);

	List<Map<String,Object>> getExamItemsByItemId(@Param("type") String type, @Param("itemId") String itemId);
	List<Map<String,String>> getPaperByStandardDeptId(@Param("secID") String secID, @Param("specialtyID") String specialtyID, @Param("examType") String examType);
}
