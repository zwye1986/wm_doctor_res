package com.pinde.res.biz.shfx;

import com.pinde.res.ctrl.shfx.LectureImageFileForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IShfxAppBiz {

	/**
	 * 根据用户名查询用户
	 * @param userCode
	 * @return
	 */
	SysUser getUserByCode(String userCode);

	/**
	 * 获取医师信息
	 * @param userFlow
	 * @return
	 */
	ResDoctor readResDoctor(String userFlow);

	/**
	 * 读取一条用户信息
	 * @param userFlow
	 * @return
     */
	SysUser readSysUser(String userFlow);

	/**
	 * 获取该用户的所有角色
	 * @param userFlow
	 * @return
	 */
	List<SysUserRole> getSysUserRole(String userFlow);

	/**
	 * 获取配置
	 * @param code
	 * @return
	 */
	String getCfgByCode(String code);

	/**
	 * 获取该医师轮转计划的开始时间和结束时间
	 * @param doctorFlow
	 * @return
	 */
	Map<String, Object> getDocResultArea(String doctorFlow);

	/**
	 * 获取该医师实际轮转的开始时间和结束时间
	 * @param doctorFlow
	 * @return
	 */
	Map<String, Object> getDocProcessArea(String doctorFlow);

	/**
	 * 获取登记数据
	 * @param recFlow
	 * @return
	 */
	ResRec getRecByRecFlow(String recFlow);

	/**
	 * 解析登记数据xml
	 * @param recContent
	 * @return
	 */
	Map<String, String> parseRecContent(String recContent);

	/**
	 * 获取评分模版
	 * @param cfgCode
	 * @return
	 */
	ResAssessCfg getGradeTemplate(String cfgCode);

	/**
	 * 解析评分模版
	 * @param content
	 * @return
	 */
	List<Map<String, Object>> parseAssessCfg(String content);

	/**
	 * 解析评分数据
	 * @param content
	 * @return
	 */
	List<Map<String,String>> parseDocGradeXml(String content);

	/**
	 * 获取登记数据及解析后数据
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getParsedRecs(Map<String, Object> paramMap);

	/**
	 * 根据科室和类型获取一条登记信息
	 * @param processFlow
	 * @param recTypeId
	 * @return
	 */
	ResRec getRecByRecType(String processFlow, String recTypeId);

	/**
	 * 根据标准组和标准科室获取一条标准规则
	 * @param standardGroupFlow
	 * @param standardDeptId
	 * @return
	 */
	SchRotationDept getRotationDeptByResult(String standardGroupFlow,
											String standardDeptId);

	/**
	 * 根据条件获取要求
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getReqByResult(Map<String, Object> paramMap);

	/**
	 * 编辑一条登记数据
	 * @param recFlow
	 * @param operUserFlow
	 * @param resultFlow
	 * @param recTypeId
	 * @param itemId
	 * @param request
	 */
	String editRec(String recFlow,
				   String operUserFlow,
				   String resultFlow,
				   String recTypeId,
				   String itemId,
				   HttpServletRequest request);

	/**
	 * 根据reqFlow读取一条req
	 * @param reqFlow
	 * @param relRecordFlow
	 * @param itemId
	 * @return
	 */
	SchRotationDeptReq readReq(String reqFlow, String relRecordFlow, String itemId);

	/**
	 * 删除一条rec
	 * @param recFlow
	 */
	void delRec(String recFlow);

	/**
	 * 验证签到信息并且签到
	 * @param userFlow
	 * @param deptFlow
	 * @param signinType
	 * @return
	 */
	boolean signin(String userFlow, String deptFlow, String signinType);

	/**
	 * 教师端的登记数据列表
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getTeacherParsedRecs(Map<String, Object> paramMap);

	/**
	 * 获取该科室最签到记录
	 * @param userFlow
	 * @return
	 */
	List<ResSignin> getSignin(String userFlow);

	//获取该科室最签到记录
	List<ResSignin> getSignin(String userFlow, String deptFlow);

	/**
	 * 根据机构查找轮转科室
	 * @param orgFlow
	 * @return
     */
	List<SchDept> getSchDeptListByOrg(String orgFlow, String deptName);


	List<SchDeptRel> searchRelByStandard(String orgFlow, String standardDeptId, String deptName);

	/**
	 * 根据轮转科室查询该科室下的所有带教或科主任
	 * @param schDeptFlow
	 * @return
     */
	List<SysUser> getUserListBySchDept(String schDeptFlow, String roleFlow, String userName);

	/**
	 * 读取一条轮转科室信息
	 * @param deptFlow
	 * @return
     */
	SchDept readSchDept(String deptFlow);
	/**
	 * 更新医师表
	 */
	int editResDoctor(ResDoctor doctor);

	String groupContent(String recTypeId, Map<String, String> dataMap, String mapStr, String separator);

	/**
	 * 查询子项
	 * @param userFlow
	 * @param deptFlow
	 * @param recType
	 * @param cataFlow
     * @return
     */
	List<Map<String,Object>> commReqOptionNameList(String userFlow, String deptFlow, String recType, String cataFlow);

	ResScore getScoreByProcess(String processFlow);

	ResDoctorSchProcess readSchProcess(String processFlow);

	ResDoctorSchProcess readSchProcessByResultFlow(String resultFlow);


	/**
	 * 查询当前机构下的所有最新讲座
	 */
	List<ResLectureInfo> SearchNewLectures(String orgFlow, String roleId, String roleFlow);
	/**
	 * 根据userFlow和lectureFlow读取一条报名记录
	 */
	ResLectureScanRegist searchByUserFlowAndLectureFlow(String userFlow, String lectureFlow);
	/**
	 * 查询某个学生报过名或扫过码的记录
	 */
	List<ResLectureScanRegist> searchByUserFLowAndRegist(String userFlow);
	/**
	 * 根据主键读信息
	 */
	ResLectureInfo read(String lectureFlow);
	/**
	 * 通过学员流水号和讲座流水号查询评价记录
	 */
	List<ResLectureEvaDetail> searchByUserFlowLectureFlow(String userFlow, String lectureFlow);
	/**
	 * 学生讲座报名
	 * @param lectureFlow
	 * @param currUser
	 * @param regist
	 * @return
	 */
	int editLectureScanRegist(String lectureFlow, SysUser currUser, ResLectureScanRegist regist);

	/**
	 * 保存评价
	 * @param resLectureEvaDetail
	 * @return
	 */
	int editResLectureEvaDetail(ResLectureEvaDetail resLectureEvaDetail, String userFlow);

	/**
	 * 扫二维码
	 * @param regist
	 * @return
	 */
	int scanRegist(ResLectureScanRegist regist);

	/**
	 * 获取当天签到次数
	 * @param nowDay
	 * @param userFlow
	 * @return
	 */
	List<JsresAttendanceDetail> getAttendanceDetailList(String nowDay, String userFlow);

	/**
	 * 签到信息
	 * @param nowDay
	 * @param userFlow
	 * @return
	 */
	JsresAttendance getJsresAttendance(String nowDay, String userFlow);

	/**
	 *
	 * @param attendance
	 */
	int addJsresAttendance(JsresAttendance attendance);

	int addJsresAttendanceDetail(JsresAttendanceDetail detail);

	List<Map<String,String>> getHistoryLecture(String userFlow);

	int saveScore(ResScore score, SysUser user);

	/*=====================产品学员APP优化（首页） begin===================*/
	String getCfgCode(String code);
	List<DeptTeacherGradeInfo> searchAllGrade(String userFlow);
	Map<String,String> getNewGradeMap(List<DeptTeacherGradeInfo> gradeList);
	ResDoctorSchProcess getProcessByResultFlow(String subDeptFlow);
	SchRotationDept searchGroupFlowAndStandardDeptIdQuery(String groupFlow, String standardDeptId);
	List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate, String subDeptFlow, String rotationFlow);
	ResScore readScoreByProcessFlow(String processFlow);

	int findLectureRegistNum(String lectureFlow);

	SchArrangeResult getResultByDeptFlow(List<String> deptFlows, String userFlow);

	SchEntryReport getSchEntryReport(String resultFlow);

	int addEntryReport(SchEntryReport schEntryReport, String userFlow);

	List<ResDoctorSchProcess> getCurrentProcesses(String userFlow);

	List<QingpuDoctorSignin> searchQingpuDoctorSignin(QingpuDoctorSignin doctorSignin);

	int addQingpuDoctorSignin(QingpuDoctorSignin doctorSignin);

	ResLectureScanRegist readUserRegistInfo(String registFlow);

	void deleteLectureImage(SysUser userinfo, String registFlow, String imageFlow) throws DocumentException;

	void addLectureImage(LectureImageFileForm form, SysUser userinfo);

	List<Map<String,String>> parseImageXml(String content) throws DocumentException;

	int editNewResLectureEvaDetail(ResLectureEvaDetail resLectureEvaDetail, String userFlow, HttpServletRequest request, String evalForm) throws DocumentException;

	SysDeptMonthPlanItem readPlanItem(String itemFlow);

	SysDeptMonthPlanItemEval readPlanItemEval(String evalFlow);

	SysDeptMonthPlanItemEval readPlanItemEvalByUser(String itemFlow, String userFlow);

	int savePlanItemEval(SysDeptMonthPlanItemEval eval, SysUser currUser);

	String getJsResCfgCode(String s);
	/*=====================产品学员APP优化（首页） end===================*/
	String getResCfgCode(String s);

	List<ResLectureInfo> checkJoinList(String lectureFlow, String userFlow);

	List<DictForm> readDictFormsByFlow(String dictFlow);

	SysDict getDictByTypeId(String activityType, String activityTypeId);

	String saveGradeInfo(String userFlow,String dataFlow, String operUserFlow, String resultFlow, String funcFlow, HttpServletRequest request, String formId);

	String editGradeInfo(String dataFlow, String operUserFlow, String resultFlow, String funcFlow, HttpServletRequest request, String formId);


	int saveLectureRandomScan(ResLectureRandomScan scan);

	ResLectureRandomScan readLectureRandomScan(String userFlow, String randomFlow);

	ResLectureRandomSign readLectureRandomSign(String randomFlow);
}
