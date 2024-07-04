package com.pinde.res.biz.bengyify;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pinde.res.model.bengyify.mo.Activity;
import com.pinde.res.model.bengyify.mo.Evaluation;
import com.pinde.res.model.bengyify.mo.MedicalInfo;
import com.pinde.res.model.bengyify.mo.StudyInfo;

public interface IBengyifyStudentBiz {
	
	/**
	 * 获取医师轮转科室列表
	 * @param userFlow
	 * @param schStatusId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Map<String,Object>> getPlanList(String userFlow , String schStatusId , String schDeptName , int pageIndex , int pageSize,int examStatusId);
	
	/**
	 * 获取医师入科教育记录
	 * @param userFlow
	 * @param deptFlow
	 */
	Map<String,Object> readEnteredDeptEdu(String schDeptFlow);
	
	/**
	 * 保存入科教育
	 * @param enteredDept
	 * @return
	 */
	String addEnteredDeptEdu(Map<String,Object> enteredDept);
	/**
	 * 保存入科教育
	 * @param enteredDept
	 * @return
	 */
	void modEnteredDeptEdu(Map<String,Object> enteredDept);
	
	/**
	 * 查询医师学习情况列表
	 * @param userFlow
	 * @return
	 */
	List<StudyInfo> getStudyInfos(String schDeptFlow , int pageIndex , int pageSize);
		
	/**
	 * 获取学习情况
	 * @param dataFlow
	 * @return
	 */
	StudyInfo readStudyInfo(String dataFlow);
	
	/**
	 * 新增一个学习情况的记录
	 * @param studyInfo
	 * @return
	 */
	String addStudyInfo(StudyInfo studyInfo);
	
	/**
	 * 修改一个学习情况的记录
	 * @param studyInfo
	 */
	void modStudyInfo(StudyInfo studyInfo);
	
	/**
	 * 删除一个学习情况的记录
	 * @param dataFlow
	 */
	void delStudyInfo(String dataFlow);
	
	/**
	 * 查询医师病例与管床列表
	 * @param userFlow
	 * @return
	 */
	List<MedicalInfo> getMedicalInfos(String schDeptFlow , int pageIndex , int pageSize);
	
	/**
	 * 获取病例与管床详细
	 * @param dataFlow
	 * @return
	 */
	MedicalInfo readMedicalInfo(String dataFlow);
	
	/**
	 * 新增一个病例与管床
	 * @param medicalInfo
	 * @return
	 */
	String addMedicalInfo(MedicalInfo medicalInfo);
	
	/**
	 * 修改病例与管床
	 * @param medicalInfo
	 */
	void modMedicalInfo(MedicalInfo medicalInfo);
	
	/**
	 * 删除一个病例与管床的记录
	 * @param dataFlow
	 */
	void delMedicalInfo(String dataFlow);
	
	/**
	 * 获取评价模板
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getExamItems(String type);

	/**
	 * 获取评价模板
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getExamItemsDOPS(String doctorFlow);


	Map<String, Object> readExamInfo(String type, String schDeptFlow);
	/**
	 * 添加评价信息
	 * @param examInfo
	 * @param marks
	 * @return
	 */
	void addAssess(Map<String, Object> examInfo , List<Map<String, Object>> marks);
	
	/**
	 * 查询评价的科室信息
	 * @param userFlow
	 * @return
	 */
	List<Map<String, Object>> getMarksForDept(String userFlow , String schDeptFlow);
	
	/**
	 * 查询评价教师信息
	 * @param userFlow
	 * @return
	 */
	List<Map<String, Object>> getMarksForTec(String userFlow , String schDeptFlow);
	
	/**
	 * 查询活动列表
	 * @param params
	 * @return
	 */
	List<Activity> getActicitys(Map<String , Object> params , int pageIndex , int pageSize);
	
	/**
	 * 查询活动详情
	 * @param dataFlow
	 * @param userFlow
	 * @return
	 */
	Activity findActivity(String dataFlow , String userFlow);
	/**
	 * 查询活动详情
	 * @param dataFlow
	 * @return
	 */
	Activity readActivity(String dataFlow);
	
	/**
	 * 参加活动
	 * @param activity
	 */
	void joinActivity(String dataFlow , String schDeptFlow , String userFlow);
	
	/**
	 * 活动评分
	 * @param activity
	 */
	void scoreActivity(Activity activity);

	/**
	 * 评价老师量表
	 * @param schDeptFlow
	 * @return
	 */
	void saveOutDops(String userFlow, String schDeptFlow, HttpServletRequest request);

	/**
	 * 评价老师量表
	 * @param schDeptFlow
	 * @return
	 */
	void saveOutMiniCex(String userFlow, String schDeptFlow, HttpServletRequest request);

	/**
	 * 获取自我评价
	 * @param schDeptFlow
	 * @return
	 */
	Evaluation readOutSecBrief(String schDeptFlow);
	
	/**
	 * 添加自我评价
	 * @param evaluation
	 * @return
	 */
	void addOutSecBrief(Evaluation evaluation);
	
	/**
	 * 修改自我评价内容
	 * @param schDeptFlow
	 * @param briefRequrie
	 */
	void modOutSecBrief(String schDeptFlow , String briefRequrie);

	List<Map<String,Object>> getExamItemsByItemId(String s, String examItemID);

	List<Map<String,Object>> getUserSignList(String nowDay, String userFlow);

	Map<String,Object> getDocNowDept(String userFlow);

	int addSign(Map<String, Object> param);
}
