package com.pinde.res.biz.jswjw;

import com.pinde.app.common.UserResumeExtInfoForm;
import com.pinde.res.ctrl.jswjw.ActivityImageFileForm;
import com.pinde.res.ctrl.jswjw.ImageFileForm;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.res.model.jswjw.mo.JsResDataExt;
import com.pinde.res.model.jswjw.mo.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IJswjwBiz {

	List<SysUserDept> getUserDept(SysUser user);

	public List<Map<String,Object>> globalProgress(String userFlow,String deptFlow,boolean isChargeOrg);

	public List<Map<String,Object>> categoryProgress(String dataType,String userFlow,String deptFlow,String rotationFlow,Integer pageIndex,Integer pageSize);

	List<Map<String,Object>> dataList(String dataType,String userFlow,String deptFlow,String cataFlow);


	public List<Map<String, Object>> commReqOptionNameList(String userFlow,String deptFlow,String dataType,String catFlow);


	public void addData(String dataType, String userFlow, String deptFlow, String cataFlow, Map<String, Object> paramMap, boolean isChargeOrg, String json);

	public void addData2(String dataType, String userFlow, String deptFlow, String cataFlow, JsResDataExt dataExt, boolean isChargeOrg);

	public Map<String, Object> viewData(String dataType, String userFlow,String deptFlow, String dataFlow);

	public void delData(String dataType, String userFlow, String deptFlow,String dataFlow);

	public void modData(String dataType, String userFlow, String deptFlow, String dataFlow, String cataFlow, Map<String, Object> paramMap, boolean isChargeOrg, String json);

	public void modData2(JsResDataExt dataExt, boolean isChargeOrg);

	// jswjw
	public SysUser findByUserCode(String userCode);

	public List<ResRec> searchByUserFlowAndTypeId(String userFlow, String id);

	public ResDoctor readResDoctor(String userFlow);

	public ResDoctor readResDoctorTwo(String userFlow);

	List<Map<String, Object>> searchSchRotationDept(Map<String, Object> paramMap);

	String searchByDoctorAndRotationFlow(String doctorFlow,String rotationFlow);

	List<SchArrangeResult> searchSchArrangeResultPassing(String doctorFlow,String rotationFlow);

	Integer searchSchRotationDeptCount(Map<String, Object> paramMap);

	/**
	 * 统计登记进度
	 * @param doctorFlow
	 * @param rotationFlow
	 * @return
	 */
	Map<String, String> getProcessPer(String doctorFlow, String rotationFlow);

	Map<String, String> getProcessPer(String doctorFlow, String rotationFlow,
									  String relRecordFlow);
	Map<String, String> getProcessPer(String doctorFlow, String rotationFlow,
									  String relRecordFlow, int format, List<ResRec> recList);
	public List<SchArrangeResult> searchSchArrangeResult(String userFlow,
														 String deptFlow,Integer pageIndex,Integer pageSize);

	public void addSubDept(String userFlow, String deptFlow,
						   String subDeptName, String startDate, String endDate) throws ParseException;

	public void modSubDept(String userFlow, String deptFlow,
						   String subDeptFlow, String subDeptName, String startDate,
						   String endDate) throws ParseException;

	public void deleteSubDept(String userFlow, String deptFlow,
							  String subDeptFlow);

	public List<Map<String,String>> viewImage(String userFlow, String deptFlow);

	public void deleteImage(String userFlow, String deptFlow, String imageFlow);

	int temporaryOutFamily(String processFlow);

	int temporaryOutAudit(String processFlow,String auditStatus);

	public void addImage(ImageFileForm from);

	public void addImage2(ImageFileForm from);

	public void modifyDoctor(ResDoctor doctor);

	public SchRotation readRotation(String string);

	/**
	 * 检查时间是否重叠
	 * @param doctorFlow
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate,
										   String endDate);

	List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate,
										   String endDate, String subDeptFlow);

	List<SchArrangeResult> checkResultDate(String doctorFlow, String startDate, String endDate,
										   String subDeptFlow,String rotationFlow);

	/**
	 * 获取标准科室map
	 * @param rotationFlow
	 * @return
	 */
	Map<String, SchRotationDept> getRotationDeptMap(String rotationFlow);

	/**
	 * 获取该用户的缩减科室信息
	 * @param doctorFlow
	 * @return
	 */
	List<SchDoctorDept> searchReductionDept(String doctorFlow,
											String rotationFlow);

	/**
	 * 缩减调整的科室与标准科室的关联map
	 * @param doctorFlow
	 * @return
	 */
	Map<String, SchDoctorDept> getReductionDeptMap(String doctorFlow,
												   String rotationFlow);

	/**
	 * 获取个人信息
	 * @param doctorFlow
	 * @return
	 */
	Map<String, Object> getDoctorExtInfo(String doctorFlow);

	SchRotation searchRoattion(String docCategroyId, String trainingSpeId);

	/**
	 * 登录记录
	 * @param user
	 */
	boolean saveLoginLog(SysUser user);

	/**
	 * 通过flow读取用户信息
	 * @param userFlow
	 * @return
	 */
	SysUser readSysUser(String userFlow);


	String getCfgCode(String code);

	/**
	 * @Description 查询配置信息
	 * @Date  2020-12-21
	 **/
	String getJsResCfgCode(String code);

	String getJsResCfgCodeNew(String code);

	/**
	 * @Author shengl
	 * @Description 付费功能
	 * @Date  2020-12-21
	 * @Param [code]
	 * @return java.lang.String
	 **/
	String getJsResCfgAppMenu(String code);

	String getJsResCfgCheckByCode(String code);

	public List<ResDoctorSchProcess> searchSchProcess(String userFlow,
													  String deptFlow);

	List<SysDept> searchSysDeptList(String orgFlow, String searchStr);

	public List<SysUser> searchTeacherList(String sysDeptFlow,
										   String roleId,String userName);

	public void addSubDept(String userFlow, String deptFlow,
						   String sysDeptFlow, String headFlow, String teacherFlow,
						   String startDate, String endDate) throws ParseException;

	public void modSubDept(String userFlow, String deptFlow,
						   String subDeptFlow,String sysDeptFlow, String headFlow, String teacherFlow,
						   String startDate, String endDate) throws ParseException;


	public int searchRecCount(String subDeptFlow);

	public int searchCount(String subDeptFlow,String userFlow);

	public ResRec readResRec(String dataFlow);
	public ResRec readResRecByDataType(String dataFlow, String dataType);

	public List<ResRec> searchGrade(String userFlow, String deptFlow);

	public List<ResRec> searchByDoctorFlow(String doctorFlow,String date);

	public Map<String, String> getGradeMap(List<ResRec> gradeList);

	public ResAssessCfg getGradeTemplate(String assessType);

	public List<Map<String, Object>> parseAssessCfg(String content);

	public Object parseDocGradeXml(String content);

	Map<String, Object> parseGradeXmlNew(String recContent);

	ResAssessCfg readResAssessCfg(String cfgFlow);

	public ResRec getGradeRec(String userFlow, String deptFlow,
							  String sysDeptFlow, String id);

	public void saveGrade(String userFlow, String deptFlow, String subDeptFlow, String assessType, HttpServletRequest request);

	public ResDoctorSchProcess getProcessByResultFlow(String subDeptFlow);

	public List<SysUserRole> getSysUserRole(String userFlow);

	public List<Map<String, Object>> getDocListByTeacher(
			Map<String, Object> paramMap);

	public void auditDate(String userFlow, String dataFlow);

	public void saveSummary(String userFlow, String doctorFlow,
							String subDeptFlow,Map<String,Object> paramMap);

	/**
	 * 获取该用户最新培训信息审核状态
	 * @param doctorFlow
	 * @return
	 */
	boolean getRecruitStatus(String doctorFlow);

	/**
	 * 查询当前机构下的所有最新讲座
	 */
	List<ResLectureInfo> SearchNewLectures(String orgFlow, String roleId, String roleFlow);
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
	List<ResLectureEvaDetail> searchByUserFlowLectureFlow(String userFlow,String lectureFlow );

	/**
	 * 学生讲座报名
	 * @param lectureFlow
	 * @param currUser
	 * @param regist
	 * @param doctor
	 * @return
	 */
	int editLectureScanRegist(String lectureFlow, SysUser currUser, ResLectureScanRegist regist, ResDoctor doctor);

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
	 * 保存rec
	 * @param re
	 * @return
	 */
	int editResRec(ResRec re,SysUser user, String recType);
	int editResRec(ResRec re,SysUser user);
	/**
	 * 按条件获取比例
	 * @param doctorFlow
	 * @param processFlow
	 * @param recTypeId
	 * @param itemId
	 * @return
	 */
	Map<String, Object> getRecProgressIn(String doctorFlow, String processFlow,
										 String recTypeId, String itemId);
	/**
	 * 通过groupFlow同standardDeptId查询
	 * @param groupFlow,standardDeptId
	 * @return
	 */
	SchRotationDept searchGroupFlowAndStandardDeptIdQuery(String groupFlow, String standardDeptId);

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

	SysOrg getOrg(String orgFlow);

	SysOrg getOrgTwo(String orgFlow);

	Map<String,String> getPowerMap(String docFlow);

	boolean getCkkPower(String operUserFlow);

	boolean getPxscPower(String operUserFlow);

	int saveScore(ResScore score, SysUser user);

	int getDoctorAuth(String userFlow);

	Map<String,String> getNewGradeMap(List<DeptTeacherGradeInfo> gradeList);

	List<DeptTeacherGradeInfo> searchNewGrade(String userFlow, String deptFlow);

	List<SchArrangeResult> getSchArrangeResult(String userFlow, String orgFlow, Integer pageIndex, Integer pageSize,String rotationFlow);

	List<DeptTeacherGradeInfo> searchAllGrade(String userFlow);

	List<DeptTeacherGradeInfo> searchAllGradeTwo(String userFlow);

	ResDoctorSchProcessEvalWithBLOBs getDoctorProcessEval(String processFlow, String evalMonth);

	ResDoctorSchProcessEvalWithBLOBs getDoctorProcessEvalByFlow(String recordFlow);

	List<FromTitle> parseFromXmlForList(String formCfg);

	String getScoreXml(HttpServletRequest request);

	ResDoctorProcessEvalConfig getProcessEvalConfig(String orgFlow);
	ResDoctorProcessEvalConfig getProcessEvalConfigByFlow(String configFlow);

	Map<String,Object> parseScoreXml(String evalResult);

	int saveProcessEval(ResDoctorSchProcessEvalWithBLOBs eval2, SysUser userinfo);

	SysDept readSysDept(String deptFlow);

	List<SysDept> getHeadDeptList(String userFlow, String deptFlow);

	Map<String,Object> parseGradeInfoXml(String recContent);

	int saveUserInfo(SysUser user);

	int saveUserInfo2(String userPhone,String userPasswd,String code);

	List<SysDept> getOrgDeptList(String orgFlow, String deptName);

	List<ResJointOrg> searchResJointByJointOrgFlow(String orgFlow);

	List<SysDict> getDictListByDictId(String id);

	List<ResLectureScanRegist> searchIsScan(String lectureFlow);

	Map<String,String> addCaseImage(ImageFileForm form);

	Map<String,String> addCaseImage2(ImageFileForm form);

	JsresAttendance getJsresAttendanceByFlow(String attendanceFlow);

	int editJsresAttendance(JsresAttendance attendance);

	void addActivityImage(ActivityImageFileForm form,SysUser user);

	void addActivityImage2(ActivityImageFileForm form,SysUser user);

	void deleteActivityImage(SysUser user, String activityFlow, String imageFlow) throws DocumentException;

	List<SchArrangeResult> searchSchArrangeResults(String userFlow, String rotationFlow, String startDate);

	List<ResLectureScanRegist> searchIsRegists(String lectureFlow);
	ResUserBindMacid readMacidByUserFlow(String userFlow);

	void saveUserMacid(ResUserBindMacid macid);

	ResUserBindMacid readMacidByMacid(String uuid);

	List<ResLectureInfo> checkJoinList(String lectureFlow, String userFlow);

	int saveRegist(ResLectureScanRegist regist);

	ResDoctorRecruit getNewRecruit(String doctorFlow);

	ResLectureRandomSign readLectureRandomSign(String randomFlow);

	ResLectureRandomScan readLectureRandomScan(String userFlow, String randomFlow);

	int saveLectureRandomScan(ResLectureRandomScan scan);

	/**
	 * 查询轮转方案
	 * @param deptFlow
	 * @return
	 */
	String queryRotationPlan(Map<String, Object> map);

	ResAssessCfg readCfgAssess(String cfgFlow);

	List<ResAssessCfg> selectResByExampleWithBLOBs(ResAssessCfg search);

	/**
	 * 查询学员填写数据信息
	 * @param paramMap
	 * @return
	 */
	int queryStudentsFilldDta(Map<String, Object> paramMap);

	int updateRecording(DoctorUntiedRecording recording);

	List<DoctorUntiedRecording> queryDoctorUnLockDate(Map<String, Object> queryMap);

	int queryStudenLoginLog(SysLog sysLog);

	List<SysUser> checkPhoneIsVerify(String userPhone);

	int saveForgetPasswdUser(String userPhone, String code, String currDateTime);

	SysUser selectByUserPhone(String userPhone);
	/**
	 * 根据用户去修改密码
	 *
	 * @param sysUser
	 * @return
	 */
	int updateUser(SysUser sysUser);

	List<SysUser> selectByUserPhoneAndIsVerify(String userPhone);

	int saveModifyUser(SysUser currentUser);

	int saveAuthenSuccessUser(SysUser currentUser);

	SysUser findByUserPhone(String userCode);

	int saveDoctorInfo(SysUser user, ResDoctor doctor, UserResumeExtInfoForm userResumeExt, String qtCountry);

	SysUser findByIdNo(String idNo);

	SchRotation searchRoattionNew(String trainingType, String trainingSpeId);

	List<ResDoctorRecruit> getRecruitList(String userFlow);

	List<LectureInfoTarget> searchLectureInfoTargetList(String lectureFlow);

	List<ResLectureEvaDetail> searchUserEvalList(Map<String, String> param);

	ResLectureScanRegist getLectureScanRegistInfoByFlow(String recordFlow);

	String saveLectureEval(ResLectureScanRegist scanRegist, Map<String, Object> paramMap);

	SchRotation searchAssiGeneralRoattion(String trainingType, String trainingSpeId, String sessionNumber);

	/**
	 * @Author shengl
	 * @Description //查询医师权限
	 * @Date  2020-12-21
	 * @Param [model, userFlow]
	 * @return void
	 **/
	void getDoctorAuthorityInfo(Model model, String userFlow, String orgFlow);

	Object getDoctorAuthorityInfo2(Map<String,Object> map, String userFlow, String orgFlow);

	/**
	 * @Author shengl
	 * @Description 查询师资权限
	 * @Date  2020-12-22
	 * @Param [model, userFlow]
	 * @return void
	 **/
	void getTeacherAuthorityInfo(Model model, String userFlow, String orgFlow);

	JsresPowerCfg readPowerCfg(String cfgCode);

	JsresDeptConfig searchDeptCfg(String orgFlow, String schDeptFlow);

	JsresDeptConfig searchBaseDeptConfig(String orgFlow);

	/**
	 * @Department：研发部
	 * @Description 查询学员减免信息数量
	 * @Author fengxf
	 * @Date 2021/4/7
	 */
	int countDoctorSchRotationDept(String doctorFlow, String rotationFlow);

	//获取当前城市在当前时间存在的考试
	List<ResTestConfig> findEffective(String currDateTime, String orgCityId);

	List<ResTestConfig> findEffectiveByParam(String currDateTime, String applyTime, String orgCityId);

	//获取该医师的所有补考记录
	List<JsresExamSignup> readDoctorExanSignUps(String doctorFlow);

	List<ResDoctorRecruit> searchResDoctorRecruitList(ResDoctorRecruit recruit, String orderByClause);

	JsresGraduationApply searchByRecruitFlow(String recruitFlow, String applyYear);

	//获取所有的成绩记录
	List<ResScore> selectAllScore( String userFlow,String recruitFlow);

	List<ResScore> selectByRecruitFlowAndScoreType(String recruitFlow, String id);

	PubUserResume readPubUserResume(String userFlow);

	int savePubUserResume(SysUser user, PubUserResume resume);

	void saveRegisteManua(String registeManua, String recruitFlow, String applyYear);

	<T> T converyToJavaBean(String xml, Class<T> clazz) throws DocumentException;

	List<SysDict> searchDictListByDictTypeId(String dictTypeId);

	SchRotation readSchRotation(String rotationFlow);

	SchRotation getRotationByRecruit(ResDoctorRecruit recruit);

	ResDoctor findByFlow(String doctorFlow);

	List<SchDoctorDept> searchDoctorDeptForReductionIgnoreStatus(String doctorFlow, String rotationFlow);

	List<SchRotationGroup> searchSchRotationGroup(String rotationFlow);

	List<SchRotationDept> searchSchRotationDeptTwo(String rotationFlow);

	List<SchArrangeResult> searchResultByGroupFlow(String groupFlow, String standardDeptId,String doctorFlow);

	List<ResSchProcessExpress> searchByUserFlowAndTypeIdTwo(String operUserFlow,String recTypeId);

	List<JsresDoctorDeptDetail> deptDoctorAllWorkDetailByNow(String recruitFlow, String doctorFlow, String applyYear);

	Map<String,Object> doctorDeptAvgWorkDetail(String recruitFlow, String applyYear);

	ResDoctor readDoctor(String recordFlow);

	int editGraduationApply2(JsresGraduationApply jsresGraduationApply, String recruitFlow, String changeSpeId, String doctorFlow, String applyYear, Map<String, String> practicingMap,SysUser user) throws DocumentException;

	ResDoctorRecruit readResDoctorRecruitBySessionNumber(String doctorFlow, String sessionNumber);

	List<JsresExamSignup> getSignUpListByYear(String year, String doctorFlow, String typeId);

	int saveSignUp(JsresExamSignup signup,SysUser user);

	String convertMapToXml(Map<String,String> map,ResScore resScore) throws Exception;

	int  savePublic(ResScore resScore,SysUser user);

	ResDoctorRecruit getNewRecruitTwo(String recruitFlow);

	String checkImg(MultipartFile uploadFile);

	String checkUserHeader(MultipartFile uploadFile);

	String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName);

	String saveFileToDirsNew(String oldFolderName, MultipartFile file, String folderName,String fileType);

	/**
	 * @Department：研发部
	 * @Description 查询系统配置的特定角色flow信息
	 * @Author fengxf
	 * @Date 2022/3/3
	 */
	Map<String, Object> getUserRoleCfgInfo();

	/**
	 * @Department：研发部
	 * @Description 查询用户基本信息
	 * @Author fengxf
	 * @Date 2022/3/3
	 */
	SysUser getUserBaseInfo(String userFlow);

	List<SysLog> searchSysLog(SysLog log);

	List<ResDoctorRecruit> readDoctorRecruits(ResDoctorRecruit recruit);

	List<Map<String,Object>> searchAssignInfoListNew(Map<String, Object> paramMap);

	List<Map<String,String>> searchAssignOrgSpeListNew(Map<String, String> paramMap);

	String doctorSignupFlagNew(String userFlow);

	List<Map<String,String>> searchDoctorSpe();

	ResOrgSpeAssign getResOrgSpeAssignInfo(String assignFlow);

	ResDoctorRecruit readResDoctorRecruit(String recruitFlow);

	List<SysOrg> searchOrgByExample(SysOrgExample example);

	List<ResOrgSpeAssign> searchSpeAssign(String orgFlow , String assignYear);

	int findSignupCount(String doctorFlow, String year);

	List<ResOrgSpe> searchResOrgSpeList(ResOrgSpe resOrgSpe);

	int editDoctor(ResDoctor doctor);

	List<ResDocotrDelayTeturn> searchInfo(ResDocotrDelayTeturn resDocotrDelayTeturn, List<String> orgFlowList,List<String> flags
			, List<String> docTypeList);

	List<ResDoctorRecruit> searchRecruitList(String doctorFlow);

	int saveDoctorRecruit(ResDoctorRecruitWithBLOBs doctorRecruitWithBLOBs);

	List<JsResDoctorRecruitExt> resDoctorRecruitExtList3New(ResDoctorRecruit resDoctorRecruit);

	List<SchRotation> searchOrgStandardRotation(SchRotation rotation);

	List<SysOrg> searchOrgNotJointOrg(SysOrg sysOrg, List<String> orgLevelList);

	List<SysOrg> searchOrg(SysOrg sysOrg);

	List<ResOrgSpe> searchResOrgSpeListNew(ResOrgSpe resOrgSpe, String trainCategoryTypeId,String speFlag);

	List<ResJointOrg> searchResJointByOrgFlow(String orgFlow);

	int editResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, ResDoctorRecruitWithBLOBs prevDocRec);

	JsresRecruitDocInfo readRecruit(String recruitFlow);

	ResDocotrDelayTeturn findTeturnInfo(String recruitFlow);

	List<DoctorAuth> searchAuthsByOperUserFlow(String operUserFlow);

	List<ResDoctorOrgHistory> searchWaitingChangeOrgHistoryList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList);

	ResDoctorRecruitWithBLOBs readRecruitNew(String recruitFlow);

	ResDoctorReduction findReductionByRecruitFlow(String recruitFlow);

	//查询用户是否付费
	String selectPowerCfg(Map<String, String> paramMap );

	//查询用户发送短信的次数
	VerificationCodeRecord selectPhone(String phone);

	//删除用户发送记录
	int deleteRecordFlow(String recordFlow);

	//更新用户记录
	int updateRecordFlow(VerificationCodeRecord record);

	String searchRotationDeptName(String deptFlow);

	SchRotationDept readSchRotationDept(String deptFlow);

	ResDoctorSchProcess readSchProcess(String processFlow);

	ResDoctorSchProcess readSchProcessByResultFlow(String resultFlow);

	List<ResOutOfficeLock> readResOutOfficeLock(String userFlow,String auditStatus);

	int deleteLeaveImage(String fileFlow);

	SysUser readSysUserByOpenId(String openId);

	String addHeadImage(ImageFileForm form);

	String addApplicationFile(ImageFileForm form);

	int saveOutLock(String outDate, String userFlow) throws ParseException;

	List<ResDoctorSchProcess> selectProcessByDoctorNew(Map<String, Object> param);

	SchArrangeResult readSchArrangeResult(String resultFlow);

	Map<String, Object> parseGradeXmlTwo(String recContent);

	List<SysUser> searchSysUserByuserFlows(List<String> userFlows);

	List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows);

//	List<Map<String, Object>> phyAssDoctorList(Map<String,Object> paramMap);

	List<SysOrg> searchJointOrgsByOrg(String orgFlow);

	String addActivityImage3(ActivityImageFileForm form,SysUser user,String imageXML,String recordFlow);

	List<String> findDoctorNameByRecTime(Map<String, Object> param);

	SysOrg readSysOrg(String orgFlow);

	List<Map<String, Object>> zltjOrgLocalListNew(Map<String, Object> param);

	List<SysDept> searchDeptByOrg(String searchFlow);

	List<Map<String, String>> attendanceList2(Map<String, Object> beMap);

	List<Map<String, String>> attendanceList3(Map<String, Object> beMap);

	List<Map<String, Object>> getKqStatistics(Map<String, Object> paramMap);

	List<Map<String, String>> temporaryOutSearch(Map<String, Object> schArrangeResultMap);

	List<Map<String, Object>> searchDocResultsListNew(Map<String, Object> paramMap);

	List<SchDept> searchSchDeptList(String orgFlow);

	List<SchArrangeResult> searchArrangeResultByDateAndOrg1(Map<String, Object> parmMap);

	List<SysUserRole> getByUserFlow(String userFlow);

	List<Map<String, Object>> findActivityList(Map<String, String> param);

	List<Map<String, Object>> readActivityResults(String activityFlow);

	List<Map<String, Object>> getDeptCountActivityStatisticsList(String orgFlow, String deptFlow, String startTime, String endTime, String notNull);

	Object getDeptActivityStatisticsMap(String deptFlow, String startTime, String endTime);

	List<Map<String, String>> schProcessStudentDistinctQuery2(Map<String, Object> param);

	List<Map<String, Object>> searchDoctorDataNew2(Map<String, Object> param);

	List<Map<String, Object>> searchTeacherUserList(Map<String, Object> param);

	List<Map<String, Object>> searchTeacherAuditList(Map<String, Object> param);

	List<ResJointOrg> selectByJointOrgFlow(String orgFlow);

	List<Map<String, Object>> chargeQueryApplyList2(Map<String, Object> param);

	List<ResTestConfig> findLocalEffective(String currDateTime2);

	List<ResTestConfig> findChargeEffective(String currDateTime2);

	List<ResTestConfig> findGlobalEffective(String currDateTime2);

	List<Map<String, Object>> queryExamSignUpList(Map<String, Object> param);

    List<SchArrangeResult> searchSchArrangeResultByMap(Map<String, String> pMap);

	ResDoctorSchProcess searchByResultFlow(String resultFlow);

	List<ResSchProcessExpress> searchByProcessFlowClob(String processFlow);

	Map<String, Object> parseRecContent(String recContent);

    Map<String, Object> readActivity(String activityFlow);

	List<PubFile> findFileByTypeFlow(String activity, String activityFlow);

	TeachingActivityInfo readActivityInfo(String activityFlow);

	int saveActivity(TeachingActivityInfo activityInfo, String userFlow);

	ResSchProcessExpress queryResRec(String recordFlow, String userFlow, String recTypeId);

	int edit(ResSchProcessExpress express);

	List<Map<String, String>> parseImageXml(String content) throws DocumentException;

	PubFile readFile(String fileFlow);

    List<ResDoctorKq> kqStatisticsDetail(ResDoctorKq kq);

	Map<String, Object> getDoctorSchInfo(String userFlow);
}
