package com.pinde.res.biz.jszy;

import com.pinde.core.model.*;
import com.pinde.res.ctrl.jswjw.ActivityImageFileForm;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

public interface IJszyAppBiz {

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
	 * 验证签到信息并且签到
	 * @param userFlow
	 * @param deptFlow
	 * @param signinType
	 * @return
	 */
	boolean signin(String userFlow, String deptFlow, String signinType);
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
	List<ResLectureInfo> SearchNewLectures(String orgFlow);
	/**
	 * 根据userFlow和lectureFlow读取一条报名记录
	 */
	ResLectureScanRegist searchByUserFlowAndLectureFlow(String userFlow,String lectureFlow);
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
	List<ResLectureEvaDetail> searchByUserFlowLectureFlow(String userFlow, String lectureFlow );
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
	int editResLectureEvaDetail(ResLectureEvaDetail resLectureEvaDetail,String userFlow);

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

	Map<String,SchDoctorDept> getReductionDeptMap(String doctorFlow, String rotationFlow, String secondRotationFlow);
	/**
	 * 获取该用户的缩减科室信息
	 * @param doctorFlow
	 * @return
	 */
	List<SchDoctorDept> searchReductionDept(String doctorFlow,	String rotationFlow, String secondRotationFlow);


	List<DeptTeacherGradeInfo> searchAllGrade(String userFlow);

	Map<String,String> getNewGradeMap(List<DeptTeacherGradeInfo> gradeList);

	ResDoctorSchProcess getProcessByResultFlow(String resultFlow);

	ResScore readScoreByProcessFlow(String processFlow);

	String getCfgCode(String code);

	List<SysDept> getHeadDeptList(String userFlow, String deptFlow);

	void addActivityImage(ActivityImageFileForm form, SysUser user);

	void deleteActivityImage(SysUser user, String activityFlow, String imageFlow) throws DocumentException;

	List<ResLectureInfo> SearchNewLectures(String orgFlow, String roleId, String roleFlow);

	List<ResLectureScanRegist> searchIsScan(String lectureFlow);

	int editLectureScanRegist(String lectureFlow, SysUser currUser, ResLectureScanRegist regist, ResDoctor doctor);

	List<SchArrangeResult> getSchArrangeResult(String userFlow, String orgFlow, Integer pageIndex, Integer pageSize);

	String getJsResCfgCode(String code);

	List<SysDict> getDictListByDictId(String dictTypeId);

	List<ResLectureInfo> checkJoinList(String lectureFlow, String userFlow);


	int saveLectureRandomScan(ResLectureRandomScan scan);

	ResLectureRandomScan readLectureRandomScan(String userFlow, String randomFlow);

	ResLectureRandomSign readLectureRandomSign(String randomFlow);
}
  