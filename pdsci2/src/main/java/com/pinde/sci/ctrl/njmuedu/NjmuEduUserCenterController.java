package com.pinde.sci.ctrl.njmuedu;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.njmuedu.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.enums.njmuedu.*;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.njmuedu.CourseInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.njmuedu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Controller
@RequestMapping("/njmuedu/user")
public class NjmuEduUserCenterController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(NjmuEduUserCenterController.class);

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private INjmuEduUserCenterBiz userCenterBiz;
	@Autowired
	private INjmuEduCourseBiz courseBiz;
	@Autowired
	private INjmuEduCourseChapterBiz eduCourseChapterBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private INjmuEduCourseQuestionBiz questionBiz;
	@Autowired
	private INjmuEduCourseAnswerBiz answerBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private INjmuEduCourseScheduleBiz courseScheduleBiz;
	@Autowired
	private INjmuEduCollectionBiz collectionBiz;
	@Autowired
	private INjmuEduUserBiz eduUserBiz;
	@Autowired
	private INjmuEduCourseChapterBiz chapterBiz;
	@Autowired
	private INjmuEduCourseNoticeBiz courseNoticeBiz;
	@Autowired
	private INjmuEduCourseFileBiz courseFileBiz;
	@Autowired
	private INjmuEduStudentCourseBiz studentCourseBiz;
	@Autowired
	private INjmuEduCourseScheduleBiz eduCourseScheduleBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private INjmuEduSubjectBiz eduSubjectBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private INjmuEduExamScoreBiz eduExamScoreBiz;

	public static byte[] getByte(File file) throws Exception {
		if (file == null) {
			return null;
		}
		try {
			int length = (int) file.length();
			if (length > Integer.MAX_VALUE) {    //当文件的长度超过了int的最大值
				System.out.println("this file is max ");
			}
			FileInputStream stream = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(length);
			byte[] b = new byte[length];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 设置个人信息
	 *
	 * @param model
	 */
	@RequestMapping(value = "/setUserInfo")
	public String setUserInfo(Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		if (currUser != null) {
			String userFlow = currUser.getUserFlow();
			SysUser user = userBiz.readSysUser(userFlow);
			model.addAttribute("user", user);
			//查询系统
			SysOrg sysOrg = new SysOrg();
//			sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
			sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
			model.addAttribute("orgList", orgList);
			//专业
			EduUser eduUser = eduUserBiz.readEduUser(userFlow);
			model.addAttribute("eduUser", eduUser);
			String role = checkRole();
			if (role.equals(GlobalConstant.TEACHER_ROLE)) {
				return "njmuedu/user/teacher/userInfo";
			} else if (role.equals(GlobalConstant.STUDENT_ROLE)) {
				return "njmuedu/user/student/userInfo";
			} else if (role.equals(GlobalConstant.HOSPITAL_ROLE)) {
				return "njmuedu/user/hospital/userInfo";
			} else if (role.equals(GlobalConstant.ADMIN_ROLE)) {
				return "njmuedu/user/admin/userInfo";
			} else if (role.equals(GlobalConstant.SYSTEM_ROLE)) {
				return "njmuedu/user/system/userInfo";
			} else {
				return "inx/njmuedu/login";
			}
		}
		return "inx/njmuedu/login";
	}

	/**
	 * 检查手机号、邮箱、身份证号唯一
	 *
	 * @param sysUser
	 * @param userFlow 检查流水号
	 */
	@RequestMapping(value = "/checkUser")
	@ResponseBody
	public String checkUser(SysUser sysUser, String userFlow) {
		SysUser user = null;
		//用户登录名唯一
		String userCode = sysUser.getUserCode();
		if (StringUtil.isNotBlank(userCode)) {
			user = userBiz.findByUserCode(userCode.trim());
			if (user != null) {
				if (StringUtil.isNotBlank(userFlow)) {
					if (!user.getUserFlow().equals(userFlow)) {
						return GlobalConstant.USER_CODE_REPETE;
					}
				} else {
					return GlobalConstant.USER_CODE_REPETE;
				}
			}
		}
		//身份证号唯一
		String idNo = sysUser.getIdNo();
		if (StringUtil.isNotBlank(idNo)) {
			user = userBiz.findByIdNo(idNo.trim());
			if (user != null) {
				if (StringUtil.isNotBlank(userFlow)) {
					if (!user.getUserFlow().equals(userFlow)) {
						return GlobalConstant.USER_ID_NO_REPETE;
					}
				} else {
					return GlobalConstant.USER_ID_NO_REPETE;
				}
			}
		}
		//手机号唯一
		String userPhone = sysUser.getUserPhone();
		if (StringUtil.isNotBlank(userPhone)) {
			user = userBiz.findByUserPhone(userPhone.trim());
			if (user != null) {
				if (StringUtil.isNotBlank(userFlow)) {
					if (!user.getUserFlow().equals(userFlow)) {
						return GlobalConstant.USER_PHONE_REPETE;
					}
				} else {
					return GlobalConstant.USER_PHONE_REPETE;
				}
			}
		}
		//邮箱唯一
		String userEmail = sysUser.getUserEmail();
		if (StringUtil.isNotBlank(userEmail)) {
			user = userBiz.findByUserEmail(userEmail.trim());
			if (user != null) {
				if (StringUtil.isNotBlank(userFlow)) {
					if (!user.getUserFlow().equals(userFlow)) {
						return GlobalConstant.USER_EMAIL_REPETE;
					}
				} else {
					return GlobalConstant.USER_EMAIL_REPETE;
				}
			}
		}
		return GlobalConstant.FLAG_Y;
	}

	/**
	 * 保存个人设置
	 *
	 * @param model
	 */
	@RequestMapping(value = "/saveUserInfo")
	@ResponseBody
	public String saveUserInfo(SysUser sysUser, EduUser eduUser) {
		String userFlow = sysUser.getUserFlow();
		if (StringUtil.isNotBlank(userFlow)) {
			String checkResult = checkUser(sysUser, userFlow);
			if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
				return checkResult;
			}
			//*****************
			String orgFlow = sysUser.getOrgFlow();
			SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
			int result = eduUserBiz.saveUserAndEduUser(sysUser, eduUser);
			if (result != GlobalConstant.ZERO_LINE) {
				//设置当前用户
				SysUser currUser = userBiz.readSysUser(userFlow);
				this.setSessionAttribute(GlobalConstant.CURRENT_USER, currUser);
				if (StringUtil.isNotBlank(orgFlow)) {
					this.setSessionAttribute(GlobalConstant.CURRENT_ORG, sysOrg);
				}
				EduUser newEduUser = this.eduUserBiz.readEduUser(userFlow);
				this.setSessionAttribute(GlobalConstant.CURR_NJMUEDU_USER, newEduUser);
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 跳转至修改密码页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "/setUserPasswd")
	public String setUserPasswd(Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		if (currUser != null) {
			return "njmuedu/user/userPasswd";
		} else {//登录
			return "inx/njmuedu/login";
		}
	}

	/**
	 * 修改密码
	 *
	 * @param model
	 */
	@RequestMapping(value = "/saveUserPasswd")
	@ResponseBody
	public String saveUserPasswd(String userFlow, String userPasswd, String userPasswd2, String userPasswd3) {
		if (StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(userPasswd) && StringUtil.isNotBlank(userPasswd2) && StringUtil.isNotBlank(userPasswd3)) {
			SysUser user = userBiz.readSysUser(userFlow);
			if (user != null) {
				userPasswd = userPasswd.trim();
				userPasswd2 = userPasswd2.trim();
				String enctyptPassword = PasswordHelper.encryptPassword(userFlow, userPasswd);
				if (!user.getUserPasswd().equals(enctyptPassword)) {
					return GlobalConstant.PASSWD_ERROR;
				}
				String enctyptPassword2 = PasswordHelper.encryptPassword(userFlow, userPasswd2);
				user.setUserPasswd(enctyptPassword2);
				int result = userBiz.updateUser(user);
				if (result != GlobalConstant.ZERO_LINE) {
					return GlobalConstant.OPRE_SUCCESSED;
				}
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 学生个人中心
	 *
	 * @param model
	 */
	@RequestMapping(value = "/student")
	public String center(Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		//查询该学生选择的每种类型课程的数量
		Map<String, Object> countCourseTypeMap = this.userCenterBiz.countUserCourseByType(currUser.getUserFlow());
		model.addAttribute("countCourseTypeMap", countCourseTypeMap);
		//查询该学生已经获得的学分数量
		int allCredit = this.courseBiz.countUserAllCredit(currUser.getUserFlow());
		List<StudySubjectDetail> subjectDetailList = eduSubjectBiz.findByDoctorFlow(currUser.getUserFlow());
		model.addAttribute("allCredit", allCredit);
		if (subjectDetailList.size() > 0 && subjectDetailList != null) {
			for (StudySubjectDetail studySubjectDetail : subjectDetailList) {
				if ("PassedTwo".equals(studySubjectDetail.getAuditStatusId())) {
					model.addAttribute("order", true);
					break;
				}
			}
		}
		return "njmuedu/user/student/main";
	}

	/**
	 * 根据条件查询学生推荐课程(选的人最多或者评分最高)
	 *
	 * @param model
	 */
	@RequestMapping(value = "/recommendCourse")
	public String recommendCourse(String sortType, Model model) {
		if (StringUtil.isBlank(sortType)) {
			sortType = "score";
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		//查询EduUser
		EduUser eduUser = this.eduUserBiz.readEduUser(currUser.getUserFlow());
		if (eduUser != null) {
			//查询推荐课程
			List<EduCourseExt> recommendList = null;
			if (sortType.equals("choose")) {
				PageHelper.startPage(1, 5);
				recommendList = this.userCenterBiz.countRecommendCourseByChooseMost(eduUser);
			} else if (sortType.equals("score")) {
				PageHelper.startPage(1, 5);
				recommendList = this.userCenterBiz.countRecommendCourseByScoreMost(eduUser);
			}

			model.addAttribute("recommendCourse", recommendList);

		}
		return "njmuedu/user/student/recommendCourse";
	}

	/**
	 * 教师个人中心
	 *
	 * @param model
	 */
	@RequestMapping(value = "/teacher")
	public String teacherCenter(Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		//查询当前用户信息
		SysUser user = this.userBiz.readSysUser(currUser.getUserFlow());
		model.addAttribute("user", user);
		//查询该教师的每种类型课程的数量
		Map<String, Object> countCourseTypeMap = this.userCenterBiz.countTchCourse(currUser.getUserFlow());
		model.addAttribute("countCourseTypeMap", countCourseTypeMap);

		return "njmuedu/user/teacher/main";
	}

	/**
	 * 教师课程排行
	 *
	 * @param model
	 */
	@RequestMapping(value = "/searchOrder")
	public String searchOrder(String sortType, Model model) {
		if (StringUtil.isBlank(sortType)) {
			sortType = "score";
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		//查询EduUser（系统、专业）
		EduUser eduUser = this.eduUserBiz.readEduUser(currUser.getUserFlow());
		if (eduUser != null) {
			//查询课程排行
			List<EduCourseExt> courseList = null;
			if (sortType.equals("question")) {
				PageHelper.startPage(1, 5);
				courseList = this.userCenterBiz.searchCourseListByQuestionOrder(eduUser);
			} else if (sortType.equals("score")) {
				PageHelper.startPage(1, 5);
				courseList = this.userCenterBiz.searchCourseListByScoreOrder(eduUser);
			}
			model.addAttribute("courseList", courseList);
		}
		return "njmuedu/user/teacher/courseOrder";
	}

	/**
	 * 教师所有问题
	 *
	 * @param model
	 */
	@RequestMapping(value = "/allQuestion")
	public String allQuestion(EduQuestion eduQuestion, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		//查看所有向该老师提出的问题
		if (StringUtil.isBlank(eduQuestion.getQuestionStatusId())) {
			eduQuestion.setQuestionStatusId(NjmuEduQuestionStatusEnum.Unanswered.getId());
		}
		eduQuestion.setQuesTypeId(NjmuQATypeEnum.QA.getId());
		List<EduQuestion> questionList = this.questionBiz.searchEduQuestionsList(eduQuestion, currUser);
		model.addAttribute("questionList", questionList);
		//查看选择该教师课程的学生
		List<SysUser> stuList = this.courseBiz.searchUserByTch(currUser);
		Map<String, Object> stuMap = new HashMap<String, Object>();
		Map<String, Object> eduMap = new HashMap<String, Object>();
		if (stuList != null && !stuList.isEmpty()) {
			for (SysUser user : stuList) {
				EduUser eduUser = this.eduUserBiz.readEduUser(user.getUserFlow());
				if (eduUser != null) {
					eduMap.put(eduUser.getUserFlow(), eduUser);
				}
				stuMap.put(user.getUserFlow(), user);
			}

		}
		model.addAttribute("stuMap", stuMap);
		model.addAttribute("eduMap", eduMap);
		if (questionList != null && !questionList.isEmpty()) {
			Map<String, List<EduAnswer>> answerMap = new HashMap<String, List<EduAnswer>>();
			for (EduQuestion question : questionList) {
				List<EduAnswer> answerList = answerBiz.searchAnswerList(question.getQuestionFlow());
				answerMap.put(question.getQuestionFlow(), answerList);
			}
			model.addAttribute("answerMap", answerMap);
		}
		//组织课程-章节Map
		EduCourse eduCourse = new EduCourse();
		List<EduCourse> courseList = this.courseBiz.searchTchCourseList(eduCourse, currUser);
		//存放课程信息Map
		Map<String, Object> courseMap = new HashMap<String, Object>();
		//存放课程-章节信息Map
		Map<String, Map<String, Object>> courseChapterMap = new HashMap<String, Map<String, Object>>();
		//存放章节信息Map
		Map<String, Object> chapterMap = null;
		if (courseList != null && !courseList.isEmpty()) {
			for (EduCourse course : courseList) {
				courseMap.put(course.getCourseFlow(), course);
				List<EduCourseChapter> chapterList = eduCourseChapterBiz.getAllChapter(course.getCourseFlow());
				chapterMap = new HashMap<String, Object>();
				if (chapterList != null && !chapterList.isEmpty()) {
					for (EduCourseChapter chapter : chapterList) {
						chapterMap.put(chapter.getChapterFlow(), chapter);
					}
				}
				courseChapterMap.put(course.getCourseFlow(), chapterMap);

			}
		}
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("courseChapterMap", courseChapterMap);
		return "njmuedu/user/teacher/allQuestion";
	}

	@RequestMapping(value = "/submitAnswer")
	@ResponseBody
	public String submitAnswer(EduAnswer answer, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		answer.setAnswerUserFlow(currUser.getUserFlow());
		answer.setAnswerTime(DateUtil.getCurrDateTime());
		answer.setAnsTypeId(NjmuQATypeEnum.QA.getId());
		answer.setAnsTypeName(NjmuQATypeEnum.QA.getName());
		int result = answerBiz.saveAnswer(answer);
		if (result == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 教师所授课程
	 *
	 * @param model
	 */
	@RequestMapping(value = "/teachCourses")
	public String teachCourses(EduCourse eduCourse, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		//查询出该老师所教的课程
		List<EduCourse> courseList = this.courseBiz.searchTchCourseList(eduCourse, currUser);
		model.addAttribute("courseList", courseList);
		//存放每门课的章的数量
		Map<String, Integer> noParentMap = new HashMap<String, Integer>();
		//存放每门课节的数量
		Map<String, Integer> parentMap = new HashMap<String, Integer>();
		//存放选修每门课的人数
		Map<String, Integer> countOneCourseMap = new HashMap<String, Integer>();
		//存放已学人数
		Map<String, Integer> hasStudyMap = new HashMap<String, Integer>();
		if (null != courseList && !courseList.isEmpty()) {
			for (EduCourse course : courseList) {
				String courseFlow = course.getCourseFlow();
				int countOneCourse = this.courseBiz.countUserSelectOneCourse(course);
				int noParent = this.eduCourseChapterBiz.countNoParentChapterByCourseFlow(course);
				int parent = this.eduCourseChapterBiz.countParentChapterByCourseFlow(course);
				List<String> studyStatusIdList = new ArrayList<String>();
				studyStatusIdList.add(NjmuEduStudyStatusEnum.Underway.getId());
				studyStatusIdList.add(NjmuEduStudyStatusEnum.Finish.getId());
				int hasStudy = studentCourseBiz.getStudentCourseCount(courseFlow, studyStatusIdList);
				noParentMap.put(courseFlow, noParent);
				parentMap.put(courseFlow, parent);
				countOneCourseMap.put(courseFlow, countOneCourse);
				hasStudyMap.put(courseFlow, hasStudy);
			}
		}
		model.addAttribute("noParentMap", noParentMap);
		model.addAttribute("parentMap", parentMap);
		model.addAttribute("countOneCourseMap", countOneCourseMap);
		model.addAttribute("hasStudyMap", hasStudyMap);
		return "njmuedu/user/teacher/courseList";
	}

	@RequestMapping(value = "/showStuCourse")
	public String showCourse(EduCourse eduCourse, String studyStatus, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		List<String> studyStatusList = new ArrayList<String>();
		studentCourseBiz.insertRequireCourse(currUser.getUserFlow());
		//如果学习状态为空，默认正在学习
		if (StringUtil.isBlank(studyStatus) || NjmuEduStudyStatusEnum.Underway.getId().equals(studyStatus)) {
			studyStatusList.add(NjmuEduStudyStatusEnum.Underway.getId());
			studyStatusList.add(NjmuEduStudyStatusEnum.NotStarted.getId());
		} else {
			studyStatusList.add(studyStatus);
		}
		//查询出该学生选择的所有课程
		List<EduCourse> courseList = courseBiz.searchStuCourseList(eduCourse, currUser, studyStatusList);
		model.addAttribute("courseList", courseList);
		//存放每门课的章的数量
		Map<String, Integer> noParentMap = new HashMap<String, Integer>();
		//存放每门课节的数量
		Map<String, Integer> parentMap = new HashMap<String, Integer>();
		//查询该学生每门课进度
		EduCourseSchedule schedule = null;
		Map<String, Object> scheduleMap = new HashMap<String, Object>();
		//存放每门课正在学习的章节
		Map<String, Object> underWayChapterMap = new HashMap<String, Object>();
		//查询课程总成绩的流水号
		List<String> courseFlowList = new ArrayList<String>();
		if (null != courseList && !courseList.isEmpty()) {
			for (EduCourse course : courseList) {
				courseFlowList.add(course.getCourseFlow());
				int noParent = this.eduCourseChapterBiz.countNoParentChapterByCourseFlow(course);
				int parent = this.eduCourseChapterBiz.countParentChapterByCourseFlow(course);
				noParentMap.put(course.getCourseFlow(), noParent);
				parentMap.put(course.getCourseFlow(), parent);
				schedule = new EduCourseSchedule();
				schedule.setUserFlow(currUser.getUserFlow());
				schedule.setCourseFlow(course.getCourseFlow());
				//查询当前用户正在学习的章节信息
				schedule.setStudyStatusId(NjmuEduStudyStatusEnum.Underway.getId());
				List<EduCourseSchedule> underwayScheduleList = this.courseBiz.searchScheduleList(schedule);
				if (!underwayScheduleList.isEmpty()) {
					EduCourseChapter chapter = this.chapterBiz.seachOne(underwayScheduleList.get(0).getChapterFlow());
					underWayChapterMap.put(course.getCourseFlow(), chapter);
				}
				//查询当前用户当前课程已完成的章节数量
				schedule.setStudyStatusId(NjmuEduStudyStatusEnum.Finish.getId());
				List<EduCourseSchedule> finishScheduleList = this.courseBiz.searchScheduleList(schedule);
				if (finishScheduleList != null && !finishScheduleList.isEmpty()) {
					BigDecimal allScheduleSize = new BigDecimal(parent);
					BigDecimal finishScheduleSize = new BigDecimal(finishScheduleList.size());
					BigDecimal countSchedule = finishScheduleSize.divide(allScheduleSize, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);
					scheduleMap.put(course.getCourseFlow(), countSchedule);
				}

			}
		}
		if (courseFlowList != null && !courseFlowList.isEmpty()) {
			Map<String, EduStudentCourse> studentCourseMap = new HashMap<String, EduStudentCourse>();
			EduStudentCourse search = new EduStudentCourse();
			search.setUserFlow(currUser.getUserFlow());
			List<EduStudentCourse> studentCourseList = studentCourseBiz.searchStudentCourseList(search, courseFlowList);
			if (studentCourseList != null && !studentCourseList.isEmpty()) {
				for (EduStudentCourse sc : studentCourseList) {
					studentCourseMap.put(sc.getCourseFlow(), sc);
				}
				model.addAttribute("studentCourseMap", studentCourseMap);
			}
		}
		model.addAttribute("noParentMap", noParentMap);
		model.addAttribute("parentMap", parentMap);
		model.addAttribute("scheduleMap", scheduleMap);
		model.addAttribute("underWayChapterMap", underWayChapterMap);

		return "njmuedu/user/student/courseList";
	}

	/**
	 * 系统管理员
	 *
	 * @param model
	 */
	@RequestMapping(value = "/system")
	public String system(Model model) {
		return "njmuedu/user/system/main";
	}

	/**
	 * 系统管理员
	 *
	 * @param model
	 */
	@RequestMapping(value = "/admin")
	public String admin(Model model) {
		return "njmuedu/user/admin/main";
	}

	/**
	 * 医院管理员
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hospital")
	public String hospital(Model model) {
		return "njmuedu/user/hospital/main";
	}

	/**
	 * 管理员-教师信息
	 *
	 * @param condition
	 * @param model
	 */
	@RequestMapping(value = "/tchInfo")
	public String searchTeacherInfoAdmin(Integer currentPage, String condition, Model model) {
		SysUser sysUser = new SysUser();
		if (StringUtil.isBlank(condition)) {
			condition = "";
		}
		String tchRoleFlow = InitConfig.getSysCfg("njmuedu_teacher_role_flow");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("condition", "%" + condition + "%");
		paramMap.put("roleFlow", tchRoleFlow);
		//验证当前登录者权限
		String role = checkRole();
		if (GlobalConstant.ADMIN_ROLE.equals(role)) {
			sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			paramMap.put("sysUser", sysUser);
		}
		PageHelper.startPage(currentPage, 15);
		List<EduUserExt> eduUserExtList = this.eduUserBiz.searchEduUserForManage(paramMap);
		model.addAttribute("eduUserExtList", eduUserExtList);
		//查询每个教师所教授的课程数量和详细信息
		Map<String, Map<String, Object>> searchAndCountCourseMap = this.courseBiz.searchCourseInfoAndCountByEduUserExt(eduUserExtList);
		model.addAttribute("searchAndCountCourseMap", searchAndCountCourseMap);
		return "njmuedu/user/tchInfo";
	}

	/**
	 * 管理员-教师详细情况
	 *
	 * @param model
	 */
	@RequestMapping(value = "/tchDetail")
	public String searchTeacherDeatil(String userFlow, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userFlow", userFlow);
		//根据流水号查询该教师的详细信息
		List<EduUserExt> eduUserExtList = this.eduUserBiz.searchEduUserForManage(paramMap);
		if (eduUserExtList != null && !eduUserExtList.isEmpty()) {
			model.addAttribute("eduUserExt", eduUserExtList.get(0));
		}
		//查询该教师所教授的全部章节信息
		Map<String, Map<String, List<EduCourseChapterExt>>> teacherChapterMap = this.chapterBiz.searchTeachersChapterList(eduUserExtList);
		model.addAttribute("teacherChapterMap", teacherChapterMap);
		//查询该教师所需要回答的全部问题数量
		Map<String, Map<String, Integer>> countQuestionMap = this.questionBiz.countQuestionMap(eduUserExtList);
		model.addAttribute("countQuestionMap", countQuestionMap);
		return "njmuedu/user/tchDetail";
	}

	/**
	 * 新增学生
	 *
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value = "/editStudent")
	public String editStudent(String userFlow, Model model) {
		//SysUser currUser=GlobalContext.getCurrentUser();
		SysOrg sysOrg = new SysOrg();
		//sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
		sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
		//查询系统
		model.addAttribute("orgList", orgList);
		if (StringUtil.isNotBlank(userFlow)) {
			EduUserExt eduUserExt = eduUserBiz.readEduUserInfo(userFlow);
			List<SysDept> deptList = this.deptBiz.searchDeptByOrg(eduUserExt.getSysUser().getOrgFlow());
			model.addAttribute("deptList", deptList);
			model.addAttribute("eduUserExt", eduUserExt);
			return "njmuedu/user/editStudent";
		}
		if (orgList != null && orgList.size() > 0) {
			List<SysDept> deptList = this.deptBiz.searchDeptByOrg(orgList.get(0).getOrgFlow());
			model.addAttribute("deptList", deptList);
		}
		return "njmuedu/user/editStudent";
	}

	@RequestMapping(value = "/getDept", method = RequestMethod.POST)
	@ResponseBody
	public List<SysDept> getDpet(String orgFlow) {
		List<SysDept> deptList = this.deptBiz.searchDeptByOrg(orgFlow);
		return deptList;
	}

	/**
	 * 新增教师
	 *
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value = "/editTeacher")
	public String editTeacher(String userFlow, Model model) {
		if (StringUtil.isNotBlank(userFlow)) {
			EduUserExt eduUserExt = eduUserBiz.readEduUserInfo(userFlow);
			model.addAttribute("eduUserExt", eduUserExt);
		}
		//查询系统
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
		sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
		model.addAttribute("orgList", orgList);
		return "njmuedu/user/editTeacher";
	}

	/**
	 * 保存教师
	 *
	 * @param sysUser
	 * @param eduUser
	 * @param model
	 */
	@RequestMapping(value = "/saveStudentOrTeacher")
	@ResponseBody
	public String saveTeacher(SysUser sysUser, EduUser eduUser, String type, Model model) {
		String userFlow = sysUser.getUserFlow();
		String checkResult;
		if (StringUtil.isNotBlank(sysUser.getOrgFlow())) {
			SysOrg sysOrg = this.orgBiz.readSysOrg(sysUser.getOrgFlow());
			if (sysOrg != null) {
				sysUser.setOrgName(sysOrg.getOrgName());
			}
		}
		if (StringUtil.isNotBlank(sysUser.getDeptFlow())) {
			SysDept sysDept = this.deptBiz.readSysDept(sysUser.getDeptFlow());
			if (sysDept != null) {
				sysUser.setDeptName(sysDept.getDeptName());
			}
		}
		if (StringUtil.isBlank(userFlow)) {
			checkResult = checkUser(sysUser, null);
		} else {
			checkResult = checkUser(sysUser, userFlow);
		}
		if (!GlobalConstant.FLAG_Y.equals(checkResult)) {
			return checkResult;
		}
		int result = eduUserBiz.saveUserAndRole(sysUser, eduUser, type);
		if (GlobalConstant.ZERO_LINE != result) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 系统管理员-打开教师简介编辑页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "/editTchIntro")
	public String editTchIntro(String userFlow, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userFlow", userFlow);
		//根据流水号查询该教师的详细信息
		List<EduUserExt> eduUserExtList = this.eduUserBiz.searchEduUserForManage(paramMap);
		if (eduUserExtList != null && !eduUserExtList.isEmpty()) {
			EduUserExt eduUserExt = eduUserExtList.get(0);
			model.addAttribute("eduUserExt", eduUserExt);
		}
		return "njmuedu/user/editIntro";
	}

	/**
	 * 系统管理员-保存教师简介
	 *
	 * @param model
	 */
	@RequestMapping(value = "/saveIntro")
	@ResponseBody
	public String saveIntro(EduUser eduUser) {
		EduUser user = this.eduUserBiz.readEduUser(eduUser.getUserFlow());
		user.setIntro(eduUser.getIntro());
		int result = this.eduUserBiz.addEduUser(eduUser);
		if (result == GlobalConstant.ONE_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 管理员-学分管理
	 *
	 * @param model
	 */
	@RequestMapping(value = "/creditInfo")
	public String searchCreditInfo(Integer currentPage, String condition, Model model) {
		SysUser sysUser = new SysUser();
		if (StringUtil.isBlank(condition)) {
			condition = "";
		}
		String stuRoleFlow = InitConfig.getSysCfg("njmuedu_student_role_flow");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("condition", "%" + condition + "%");
		paramMap.put("roleFlow", stuRoleFlow);
		//验证当前登录者权限
		String role = checkRole();
		if (GlobalConstant.ADMIN_ROLE.equals(role)) {
			sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			paramMap.put("sysUser", sysUser);
		}
		PageHelper.startPage(currentPage, 15);
		List<EduUserExt> eduUserExtList = this.eduUserBiz.searchEduUserForManage(paramMap);
		model.addAttribute("eduUserExtList", eduUserExtList);
		Map<String, Map<String, Object>> searchStudentChooseCourseMap = this.studentCourseBiz.searchStudentChooseCourses(eduUserExtList);
		model.addAttribute("searchStudentChooseCourseMap", searchStudentChooseCourseMap);
		return "njmuedu/user/creditInfo";
	}

	/**
	 * 管理员-学分详细
	 *
	 * @param model
	 */
	@RequestMapping(value = "/creditDetail")
	public String searchCreditDetail(String userFlow, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userFlow", userFlow);
		List<EduUserExt> eduUserExtList = this.eduUserBiz.searchEduUserForManage(paramMap);
		if (eduUserExtList != null && !eduUserExtList.isEmpty()) {
			EduUserExt eduUserExt = eduUserExtList.get(0);
			model.addAttribute("eduUserExt", eduUserExt);
		}
		Map<String, Map<String, Object>> searchStudentChooseCourseMap = this.studentCourseBiz.searchStudentChooseCourses(eduUserExtList);
		model.addAttribute("searchStudentChooseCourseMap", searchStudentChooseCourseMap);
		return "njmuedu/user/creditDetail";
	}

	/**
	 * 管理员-学生学习进度
	 * @param condition
	 * @param model
	 */
	@RequestMapping(value = "/scheduleInfo")
	public String scheduleInfo(Integer currentPage, String condition, String isAdmin, Model model) {
		SysUser sysUser = new SysUser();
		if (StringUtil.isBlank(condition)) {
			condition = "";
		}
		String stuRoleFlow = InitConfig.getSysCfg("njmuedu_student_role_flow");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//验证当前登录者权限
//	        String role=checkRole();
//	        if(GlobalConstant.ADMIN_ROLE.equals(role)){
//	        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//	        	paramMap.put("sysUser", sysUser);
//	        }
		if (!GlobalConstant.FLAG_Y.equals(isAdmin)) {
			sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			paramMap.put("sysUser", sysUser);
		}
		paramMap.put("role", "student");
		paramMap.put("condition", "%" + condition + "%");
		paramMap.put("roleFlow", stuRoleFlow);
		paramMap.put("userFlowList",true);
		PageHelper.startPage(currentPage, 15);
		List<EduUserExt> eduUserExtList = this.eduUserBiz.searchEduAndCourseList(paramMap);
		model.addAttribute("eduUserExtList", eduUserExtList);
		Map<String, Map<String, Map<String, Object>>> resultMap = this.courseScheduleBiz.searchStuSchedule(eduUserExtList);
		model.addAttribute("resultMap", resultMap);
		//查询用于制图的统计数据
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
		List<SysOrg> orgList = this.orgBiz.searchSysOrg(sysOrg);
		EduCourse course = new EduCourse();
		List<EduCourse> courseList = this.courseBiz.searchCourseList(course);
		Map<String, Map<String, Object>> orgAndCourseMap = this.courseScheduleBiz.searchCourseFinishInfoByOrg(orgList, courseList);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("orgList", orgList);
		model.addAttribute("courseList", courseList);
		model.addAttribute("orgAndCourseMap", orgAndCourseMap);
		return "njmuedu/user/scheduleInfo";
	}

	/**
	 * 管理员-学生学习进度
	 *
	 * @param condition
	 * @param model
	 */
	@RequestMapping(value = "/scheduleInfoChart")
	public String scheduleInfoChart(Model model) {
		SysUser sysUser = new SysUser();
		String stuRoleFlow = InitConfig.getSysCfg("njmuedu_student_role_flow");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//验证当前登录者权限
		String role = checkRole();
		List<SysOrg> orgList = new ArrayList<SysOrg>();
		if (GlobalConstant.ADMIN_ROLE.equals(role)) {
			sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			paramMap.put("sysUser", sysUser);
			SysOrg sysOrg = this.orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			orgList.add(sysOrg);
		} else if (GlobalConstant.SYSTEM_ROLE.equals(role)) {
			SysOrg sysOrg = new SysOrg();
			sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
			orgList = this.orgBiz.searchSysOrg(sysOrg);
		}
		paramMap.put("role", "student");
		paramMap.put("roleFlow", stuRoleFlow);
		//查询用于制图的统计数据
		EduCourse course = new EduCourse();
		List<EduCourse> courseList = this.courseBiz.searchCourseList(course);
		Map<String, Map<String, Object>> orgAndCourseMap = this.courseScheduleBiz.searchCourseFinishInfoByOrg(orgList, courseList);
		model.addAttribute("orgList", orgList);
		model.addAttribute("courseList", courseList);
		model.addAttribute("orgAndCourseMap", orgAndCourseMap);
		return "njmuedu/user/chart/scheduleInfoChart";
	}

	/**
	 * 管理员-测验考试情况
	 *
	 * @param condition
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/testInfo")
	public String searchTestInfo(Integer currentPage, String condition, Model model) {
		SysUser sysUser = new SysUser();
		if (StringUtil.isBlank(condition)) {
			condition = "";
		}
		String stuRoleFlow = InitConfig.getSysCfg("njmuedu_student_role_flow");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//验证当前登录者权限
		String role = checkRole();
		if (GlobalConstant.ADMIN_ROLE.equals(role)) {
			sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			paramMap.put("sysUser", sysUser);
		}
		paramMap.put("role", "student");
		paramMap.put("condition", "%" + condition + "%");
		paramMap.put("roleFlow", stuRoleFlow);
		PageHelper.startPage(currentPage, 15);
		List<EduUserExt> eduUserExtList = this.eduUserBiz.searchEduAndCourseList(paramMap);
		model.addAttribute("eduUserExtList", eduUserExtList);

		return "njmuedu/user/testInfo";
	}

	/**
	 * 管理员--课程概况
	 *
	 * @param schedule
	 */
	@RequestMapping(value = "/courseInfo")
	public String courseInfo(String searchCondition, Model model) {
		if (StringUtil.isBlank(searchCondition)) {
			searchCondition = "";
		}
		List<EduCourse> courseList = this.courseBiz.searchCourseByCondition(searchCondition);
		model.addAttribute("courseList", courseList);
		List<EduCourseChapter> chapterList = eduCourseChapterBiz.searchCourseChapterList(new EduCourseChapter(), null, null);
		//教师Map
		Map<String, List<SysUser>> teacherMap = new HashMap<String, List<SysUser>>();
		List<SysUser> teacherList = null;
		List<String> teacherFlowList = null;
		if (courseList != null && !courseList.isEmpty()) {
			for (EduCourse course : courseList) {
				teacherList = new ArrayList<SysUser>();
				teacherFlowList = new ArrayList<String>();
				EduCourseExt eduCourseExt = this.courseBiz.searchOneWithChapters(course.getCourseFlow());
				if (eduCourseExt != null) {
					if (eduCourseExt.getChapterList() != null && !eduCourseExt.getChapterList().isEmpty()) {
						for (EduCourseChapter chapter : eduCourseExt.getChapterList()) {
							SysUser user = this.userBiz.readSysUser(chapter.getTeacherId());
							if (user != null) {
								if (!teacherFlowList.contains(user.getUserFlow())) {
									teacherList.add(user);
									teacherFlowList.add(user.getUserFlow());
								}
							}

						}
					}
				}
				teacherMap.put(course.getCourseFlow(), teacherList);
			}
			model.addAttribute("teacherMap", teacherMap);
		}
		//章节数Map
		Map<String, Integer> parentChapterNullMap = new HashMap<String, Integer>();
		Map<String, Integer> parentChapterNotNullMap = new HashMap<String, Integer>();
		//评分Map
		Map<String, List<String>> teacherFlowMap = new HashMap<String, List<String>>();
		Map<String, BigDecimal> scoreMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> scoreCountMap = new HashMap<String, BigDecimal>();
		if (chapterList != null && !chapterList.isEmpty()) {
			for (EduCourseChapter cc : chapterList) {
				String courseFlow = cc.getCourseFlow();
				List<String> temp = teacherFlowMap.get(courseFlow);
				if (temp == null) {
					temp = new ArrayList<String>();
				}
				temp.add(cc.getTeacherId());
				teacherFlowMap.put(courseFlow, temp);

				//章
				String parentChapterFlow = cc.getParentChapterFlow();
				if (parentChapterFlow == null) {
					Integer count = parentChapterNullMap.get(courseFlow);
					if (count == null) {
						count = 0;
					}
					count++;
					parentChapterNullMap.put(courseFlow, count);
				} else {//节
					Integer count = parentChapterNotNullMap.get(courseFlow);
					if (count == null) {
						count = 0;
					}
					count++;
					parentChapterNotNullMap.put(courseFlow, count);
				}
				//评分
				if (StringUtil.isNotBlank(parentChapterFlow)) {
					BigDecimal chapteScore = cc.getChapterScore();
					if (chapteScore == null) {
						chapteScore = new BigDecimal(0);
					}
					if (chapteScore != null) {
						BigDecimal tempScore = scoreMap.get(courseFlow);
						BigDecimal tempScoreCount = scoreCountMap.get(courseFlow);
						if (tempScoreCount != null && tempScore != null) {
							tempScore = tempScore.multiply(tempScoreCount).add(chapteScore);
							tempScoreCount = tempScoreCount.add(new BigDecimal(1));
						} else {
							tempScoreCount = new BigDecimal(1);
							tempScore = new BigDecimal(0).add(chapteScore);
						}
						scoreCountMap.put(courseFlow, tempScoreCount);
						scoreMap.put(courseFlow, tempScore.divide(tempScoreCount, 1, BigDecimal.ROUND_HALF_UP));

					}
				}

			}
			model.addAttribute("parentChapterNullMap", parentChapterNullMap);
			model.addAttribute("parentChapterNotNullMap", parentChapterNotNullMap);
			model.addAttribute("scoreMap", scoreMap);
		}


		//学习人数
		EduStudentCourse eduStudentCourse = new EduStudentCourse();
		List<EduStudentCourse> studentCourseList = courseBiz.searchStudentCourse(eduStudentCourse);
		if (studentCourseList != null && !studentCourseList.isEmpty()) {
			Map<String, Integer> studentCountMap = new HashMap<String, Integer>();
			Map<String, Integer> studyFinishCountMap = new HashMap<String, Integer>();
			Map<String, Integer> studyNotFinishCountMap = new HashMap<String, Integer>();
			for (EduStudentCourse sc : studentCourseList) {
				String courseFlow = sc.getCourseFlow();
				Integer tempCount = studentCountMap.get(courseFlow);
				if (tempCount == null) {
					tempCount = 0;
				}
				tempCount++;
				studentCountMap.put(courseFlow, tempCount);
				//完成人数
				String studyStatusId = sc.getStudyStatusId();
				if (StringUtil.isNotBlank(studyStatusId)) {
					if (studyStatusId.equals(NjmuEduStudyStatusEnum.Finish.getId())) {
						Integer tempFinishCount = studyFinishCountMap.get(courseFlow);
						if (tempFinishCount == null) {
							tempFinishCount = 0;
						}
						tempFinishCount++;
						studyFinishCountMap.put(courseFlow, tempFinishCount);
					} else {
						Integer tempNotFinishCount = studyNotFinishCountMap.get(courseFlow);
						if (tempNotFinishCount == null) {
							tempNotFinishCount = 0;
						}
						tempNotFinishCount++;
						studyNotFinishCountMap.put(courseFlow, tempNotFinishCount);
					}


				}
			}
			model.addAttribute("studentCountMap", studentCountMap);
			model.addAttribute("studyFinishCountMap", studyFinishCountMap);
			model.addAttribute("studyNotFinishCountMap", studyNotFinishCountMap);
		}
		return "njmuedu/user/courseInfo";
	}

	/**
	 * 管理员--课程预约
	 *
	 * @return
	 */
	public boolean isAdmin() {
		SysUser user = GlobalContext.getCurrentUser();
		List<SysUserRole> userRoleList = userRoleBiz.getByUserFlowAndWsid(user.getUserFlow(), "njmuedu");
		SysRole role = new SysRole();
		role.setRoleFlow(InitConfig.getSysCfg("njmuedu_admin_role_flow"));
		if (userRoleList.size() > 0) {
			SysUserRole userRole = userRoleList.get(0);
			if (userRole.getRoleFlow().equals(role.getRoleFlow())) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "/courseOrder")
	public String courseOrder(StudySubject subject, Model model, String isAdmin, Integer currentPage, HttpServletRequest request) {
		int pageSize = getPageSize(request);
		if (currentPage == null) currentPage = 1;
		model.addAttribute("isAdmin", isAdmin);
		if (GlobalConstant.FLAG_Y.equals(isAdmin)) {
			PageHelper.startPage(currentPage, pageSize);
			List<StudySubject> list = this.eduSubjectBiz.subjectList(subject);
			model.addAttribute("subjectList", list);
			return "njmuedu/user/courseOrder";
		}
		subject.setPostStatus(GlobalConstant.RECORD_STATUS_Y);
		PageHelper.startPage(currentPage, pageSize);
		List<StudySubject> list = this.eduSubjectBiz.subjectList(subject);
		model.addAttribute("subjectList", list);
		return "njmuedu/user/courseOrder";
	}

	/**
	 * 新增课程预约
	 *
	 * @return
	 */
	@RequestMapping(value = "/editCourse")
	public String editCourse(String subjectFlow, String isEdit, Model model) {
		StudySubject subject = eduSubjectBiz.readSubject(subjectFlow);
		model.addAttribute("subject", subject);
		return "njmuedu/user/editCourse";
	}

	@RequestMapping("/showSubject")
	public String showSubject(String subjectFlow, String isEdit,String flag, Model model) throws Exception {
		StudySubject subject = eduSubjectBiz.readSubject(subjectFlow);
		int passNum = eduSubjectBiz.selectCountByAuditStatusId(subjectFlow);
		model.addAttribute("subject", subject);
		model.addAttribute("passNum", passNum);
		return "njmuedu/user/showSubject";
	}

	/**
	 * 保存课程
	 */
	@RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
	public @ResponseBody
	String save(StudySubject subject) throws Exception {
		if (StringUtil.isNotBlank(subject.getSubjectFlow())) {
			StudySubject old = eduSubjectBiz.readSubject(subject.getSubjectFlow());
			if (old == null) {
				return "课程信息不存在，无法编辑，请刷新列表！";
			}
			if ("N".equals(old.getRecordStatus())) {
				return "课程信息已删除，无法编辑，请刷新列表！";
			}
//			if ("Y".equals(old.getPostStatus())) {
//				return "课程信息已发布，无法编辑，请刷新列表！";
//			}
		}
		int result = eduSubjectBiz.addSubject(subject);
		if (GlobalConstant.ZERO_LINE != result) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 发布课程
	 *
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/release", method = RequestMethod.POST)
	public @ResponseBody
	String release(StudySubject subject) throws Exception {
		if (StringUtil.isNotBlank(subject.getSubjectFlow())) {
			StudySubject old = eduSubjectBiz.readSubject(subject.getSubjectFlow());
			if (old == null) {
				return "课程信息不存在，无法发布，请刷新列表！";
			}
			if ("N".equals(old.getRecordStatus())) {
				return "课程信息已删除，无法发布，请刷新列表！";
			}
			if ("Y".equals(old.getPostStatus())) {
				return "课程信息已发布，无法再次发布，请刷新列表！";
			}
		}
		subject.setPostStatus("Y");
		int result = eduSubjectBiz.addSubject(subject);
		if (GlobalConstant.ZERO_LINE != result) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return "发布失败";
	}

	/**
	 * 删除课程
	 *
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSubject", method = RequestMethod.POST)
	public @ResponseBody
	String delSubject(StudySubject subject) throws Exception {
		if (StringUtil.isNotBlank(subject.getSubjectFlow())) {
			StudySubject old = eduSubjectBiz.readSubject(subject.getSubjectFlow());
			if (old == null) {
				return "课程信息不存在，无法删除，请刷新列表！";
			}
			if ("N".equals(old.getRecordStatus())) {
				return "课程信息已删除，无法删除，请刷新列表！";
			}
			if ("Y".equals(old.getPostStatus())) {
				return "课程信息已发布，无法删除，请刷新列表！";
			}
		}
		subject.setRecordStatus("N");
		int result = eduSubjectBiz.addSubject(subject);
		if (GlobalConstant.ZERO_LINE != result) {
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	@RequestMapping("/detailInfoManage")
	public String detailInfoManage(String subjectFlow, String isEdit, Model model, String isAdmin) {
		StudySubject subject = eduSubjectBiz.readSubject(subjectFlow);
		model.addAttribute("subject", subject);
		model.addAttribute("isAdmin", isAdmin);
		return "njmuedu/user/detailInfoManage";
	}

	@RequestMapping("/detailList")
	public String detailList(Integer currentPage1, String auditStatusId, String doctorName, String year,
							 String subjectFlow, HttpServletRequest request, Model model, String isLocal, String isAdmin) throws IOException {
		int pageSize = getPageSize(request);
		if (currentPage1 == null) currentPage1 = 1;
		Map<String, String> param = new HashMap<>();
		param.put("auditStatusId", auditStatusId);
		param.put("doctorName", doctorName);
		param.put("subjectFlow", subjectFlow);
		if (GlobalConstant.FLAG_Y.equals(isAdmin)) {
			PageHelper.startPage(currentPage1, pageSize);
			List<Map<String, String>> list = eduSubjectBiz.detailList(param);
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("list", list);
			return "njmuedu/user/detailList";
		}
		PageHelper.startPage(currentPage1, pageSize);
		param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
		List<Map<String, String>> list = eduSubjectBiz.detailList(param);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("list", list);
		return "njmuedu/user/detailList";
	}

	@RequestMapping(value = {"/auditPassed"}, method = RequestMethod.POST)
	@ResponseBody
	public String auditPassed(@RequestBody List<String> detailFlows, String isAdmin) throws Exception {
		if (detailFlows.size() <= 0) {
			return "请勾选预约学员信息!";
		}
		if (GlobalConstant.FLAG_Y.equals(isAdmin)) {
			for (String detailFlow : detailFlows) {
				StudySubjectDetail studySubjectDetail = eduSubjectBiz.findBydetailFlow(detailFlow);
				if (!NjmuEduAuditStatusEnum.Passed.getId().equals(studySubjectDetail.getAuditStatusId())) {
					return "医院未审核通过或还未审核";
				}
			}
			eduSubjectBiz.auditPassedTwo(detailFlows);
			return GlobalConstant.OPRE_SUCCESSED;
		} else {
			eduSubjectBiz.auditPassed(detailFlows);
			return GlobalConstant.OPRE_SUCCESSED;
		}
	}

	@RequestMapping(value = {"/auditUnPassed"}, method = RequestMethod.POST)
	@ResponseBody
	public String auditUnPassed(@RequestBody List<String> detailFlows, String isAdmin) throws Exception {
		if (detailFlows.size() <= 0) {
			return "请勾选预约学员信息!";
		}
		if (GlobalConstant.FLAG_Y.equals(isAdmin)) {
			for (String detailFlow : detailFlows) {
				StudySubjectDetail studySubjectDetail = eduSubjectBiz.findBydetailFlow(detailFlow);
				if (!NjmuEduAuditStatusEnum.Passed.getId().equals(studySubjectDetail.getAuditStatusId())) {
					return "医院未审核通过或还未审核";
				}
			}
			eduSubjectBiz.auditUnPassedTwo(detailFlows);
			return GlobalConstant.OPRE_SUCCESSED;
		}
		eduSubjectBiz.auditUnPassed(detailFlows);
		return GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value = {"/auditBack"}, method = RequestMethod.POST)
	@ResponseBody
	public String auditBack(@RequestBody List<String> detailFlows, String isAdmin) throws Exception {
		if (detailFlows.size() <= 0) {
			return "请勾选预约学员信息!";
		}
		if (!GlobalConstant.FLAG_Y.equals(isAdmin)) {
			for (String detailFlow : detailFlows) {
				StudySubjectDetail studySubjectDetail = eduSubjectBiz.findBydetailFlow(detailFlow);
				if (NjmuEduAuditStatusEnum.PassedTwo.getId().equals(studySubjectDetail.getAuditStatusId())) {
					return "请联系学校管理员撤销";
				}
			}
			eduSubjectBiz.auditBack(detailFlows);
			return GlobalConstant.OPRE_SUCCESSED;
		}
		for (String detailFlow : detailFlows) {
			StudySubjectDetail studySubjectDetail = eduSubjectBiz.findBydetailFlow(detailFlow);
			if (!NjmuEduAuditStatusEnum.PassedTwo.getId().equals(studySubjectDetail.getAuditStatusId()) &&
					!NjmuEduAuditStatusEnum.UnPassedTwo.getId().equals(studySubjectDetail.getAuditStatusId())){
				return "该状态不可撤销";
			}
		}
		eduSubjectBiz.auditPassed(detailFlows);
		return GlobalConstant.OPRE_SUCCESSED;
	}

	/**
	 * @author limin
	 * 查询发布的课程
	 */
	@RequestMapping(value="/subjectList")
	public String subjectList(String flag,Integer currentPage,Model model,HttpServletRequest request) throws Exception{
		if (currentPage == null) {
			currentPage = 1;
		}
		SysUser user = GlobalContext.getCurrentUser();
		PageHelper.startPage(currentPage, getPageSize(request));
		ArrayList<StudySubject> subjectList = new ArrayList<>();
		ArrayList<StudySubject> orderList = new ArrayList<>();
		Map<String,StudySubjectDetail> map = new HashMap<>();
		Map<String,Integer> numMap = new HashMap<>();
		if(user!=null){
			String postStatus = "Y";//发布
			if(!"true".equals(flag)){//查询所有
				List<StudySubject> allList = eduSubjectBiz.findAllSubject(postStatus);
				for(StudySubject s:allList)
				{
					int num = eduSubjectBiz.findAppiontNum(s.getSubjectFlow());
					numMap.put(s.getSubjectFlow(),num);
				}
				model.addAttribute("dataList",allList);
			}else{//预约人员未满
				List<StudySubject> allList = eduSubjectBiz.findSubject(postStatus);
				for(StudySubject s:allList)
				{
					int num = eduSubjectBiz.findAppiontNum(s.getSubjectFlow());
					numMap.put(s.getSubjectFlow(),num);
				}
				model.addAttribute("flag","true");
				model.addAttribute("dataList",allList);
			}
			//查询是否预约
			List<StudySubjectDetail> studySubjectDetailList = eduSubjectBiz.findByDoctorFlow(user.getUserFlow());
			if(studySubjectDetailList!=null)
				for(StudySubjectDetail d:studySubjectDetailList)
				{
					map.put(d.getSubjectFlow(),d);
				}
		}

		model.addAttribute("map",map);
		model.addAttribute("numMap",numMap);
		model.addAttribute("nowDate",DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		return "njmuedu/user/student/subjectList";
	}

	/**
	 * 预约课程查看
	 */
	@RequestMapping(value="/searchSubject")
	public String searchSubject(String subjectFlow,Model model) throws Exception{
		StudySubject studySubject = eduSubjectBiz.findBySubjectFlow(subjectFlow);
		model.addAttribute("studySubject",studySubject);
		return "njmuedu/user/student/searchSubject";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value="/saveSubjectDetail",method={RequestMethod.POST})
	@ResponseBody
	public synchronized String saveSubjectDetail(StudySubject studySubject,Model model) throws Exception{
		if(studySubject != null) {
			StudySubject subject = eduSubjectBiz.findBySubjectFlow(studySubject.getSubjectFlow());

			StudySubjectDetail d = eduSubjectBiz.getSubjectDetail(studySubject.getSubjectFlow(),GlobalContext.getCurrentUser().getUserFlow());
			if(d!=null)
			{
				return "你已预约！";
			}
			int num = eduSubjectBiz.findAppiontNum(subject.getSubjectFlow());
			if(Integer.valueOf(subject.getReservationsNum())-num<=0)
			{
				return "预约人数已满";
			}
			int result = eduSubjectBiz.saveSubject(subject);
			if (GlobalConstant.ZERO_LINE != result) {
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping("/exportDetailList")
	public void exportDetailList(String auditStatusId,String doctorName,String isAdmin,
								 String subjectFlow,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> param = new HashMap<>();
		param.put("auditStatusId",auditStatusId);
		param.put("doctorName",doctorName);
		param.put("subjectFlow",subjectFlow);
		if(!GlobalConstant.FLAG_Y.equals(isAdmin)){
		  param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
		}
		List<Map<String,String>> list = eduSubjectBiz.detailList(param);
		String[] titles = new String[]{
				"userName:姓名",
				"idNo:证件号码",
				"sexName:性别",
				"period:培训届别",
				"orgName:培训基地",
				"majorName:培训专业",
				"userPhone:联系方式",
				"orderTime:学员预约时间",
				"auditStatusName:状态"
		};
		String fileName = "预约学员信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream());
	}

	/**
	 * 管理员--课程概况--图
	 * @param schedule
	 */
	@RequestMapping(value="/courseInfoChart")
	public String courseInfoChart(String searchCondition,Model model){
		if(StringUtil.isBlank(searchCondition)){
			searchCondition="";
		}
		List<EduCourse> courseList = this.courseBiz.searchCourseByCondition(searchCondition);
		model.addAttribute("courseList", courseList);
		//学习人数
		EduStudentCourse eduStudentCourse=new EduStudentCourse();
		List<EduStudentCourse> studentCourseList = courseBiz.searchStudentCourse(eduStudentCourse);
		if(studentCourseList != null && !studentCourseList.isEmpty()){
			Map<String , Integer> studentCountMap = new HashMap<String, Integer>();
			Map<String , Integer> studyFinishCountMap = new HashMap<String, Integer>();
			Map<String , Integer> studyNotFinishCountMap = new HashMap<String, Integer>();
			for(EduStudentCourse sc :studentCourseList){
				String courseFlow = sc.getCourseFlow();
				Integer tempCount = studentCountMap.get(courseFlow);
				if(tempCount == null){
					tempCount = 0;
				}
				tempCount++;
				studentCountMap.put(courseFlow, tempCount);
				//完成人数
				String studyStatusId = sc.getStudyStatusId();
				if(StringUtil.isNotBlank(studyStatusId)){
					if(studyStatusId.equals(NjmuEduStudyStatusEnum.Finish.getId())){
						Integer tempFinishCount = studyFinishCountMap.get(courseFlow);
						if(tempFinishCount == null){
							tempFinishCount = 0;
						}
						tempFinishCount++;
						studyFinishCountMap.put(courseFlow, tempFinishCount);
					}else{
						Integer tempNotFinishCount = studyNotFinishCountMap.get(courseFlow);
						if(tempNotFinishCount == null){
							tempNotFinishCount = 0;
						}
						tempNotFinishCount++;
						studyNotFinishCountMap.put(courseFlow, tempNotFinishCount);
					}


				}
			}
			model.addAttribute("studentCountMap", studentCountMap);
			model.addAttribute("studyFinishCountMap", studyFinishCountMap);
			model.addAttribute("studyNotFinishCountMap", studyNotFinishCountMap);
		}
		return "njmuedu/user/chart/courseInfoChart";
	}
	
	@RequestMapping(value="/system/schedule")
	public String schedule(EduCourseSchedule schedule){
		return "njmuedu/user/system/schedule";
	}


	/**
	 * 管理员-学生审核
	 * @param user
	 * @param model
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/userList")
	public String systemUserList(Integer currentPage,String condition,String isAdmin, String statusId, Model model) throws UnsupportedEncodingException{
		SysUser sysUser = new SysUser();
		if(StringUtil.isBlank(condition)){
        	condition="";
        }
        Map<String,Object> paramMap=new HashMap<String, Object>();
        String roleFlow=InitConfig.getSysCfg("njmuedu_student_role_flow");
        paramMap.put("roleFlow", roleFlow);
        paramMap.put("condition", "%"+condition+"%");
        //验证当前登录者权限
        if(!GlobalConstant.FLAG_Y.equals(isAdmin)){
			sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
        if(StringUtil.isNotBlank(statusId)){
        	sysUser.setStatusId(statusId);
        }
        paramMap.put("sysUser", sysUser);
        PageHelper.startPage(currentPage, 15);
		List<EduUserExt> userList=this.eduUserBiz.searchEduUserForManage(paramMap);
		model.addAttribute("isAdmin",isAdmin);
		model.addAttribute("userList", userList);
		return "njmuedu/user/userList";
	}
	
	/**
	 * 管理员-学生信息查看
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/studentInfo")
	public String systemStudentInfo(String userFlow,Model model){
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("userFlow", userFlow);
		List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
		if(eduUserExtList != null && !eduUserExtList.isEmpty()){
			EduUserExt eduUserExt = eduUserExtList.get(0);
			model.addAttribute("eduUserExt", eduUserExt);
		}
		Map<String,Map<String, Object>> searchStudentChooseCourseMap=this.studentCourseBiz.searchStudentChooseCourses(eduUserExtList);
		model.addAttribute("searchStudentChooseCourseMap", searchStudentChooseCourseMap);
		return "njmuedu/user/studentInfo";
	}

	/**
	 * 管理员--教师授课信息
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/lessionInfo")
	public String lessionInfo(Integer currentPage,String condition,Model model){
		SysUser sysUser=new SysUser();
		if(StringUtil.isBlank(condition)){
        	condition = "";
        }
        String roleFlow = InitConfig.getSysCfg("njmuedu_teacher_role_flow");
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("condition", "%"+condition+"%");
        paramMap.put("roleFlow", roleFlow);
        paramMap.put("role", "teacher");
        //验证当前登录者权限
        String role=checkRole();
        if(!GlobalConstant.ADMIN_ROLE.equals(role)){
        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        	paramMap.put("sysUser", sysUser);
        }
        //查询老师信息及其所教授课程信息
        PageHelper.startPage(currentPage, 15);
        List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduAndCourseList(paramMap);
        model.addAttribute("eduUserExtList", eduUserExtList);
        Map<String, Map<String, Object>> resultMap=this.courseScheduleBiz.countInfoOfTeacher(eduUserExtList);
        model.addAttribute("resultMap", resultMap);

		//查询老师授课信息图表需要的信息
        List<EduUserExt> eduUserExtListChart=this.eduUserBiz.searchEduAndCourseList(paramMap);
        Map<String, Map<String, Object>> resultMapChart=this.courseScheduleBiz.countInfoOfTeacher(eduUserExtListChart);

		Map<String,Object> highCountMap=new HashMap<String,Object>();
        Map<String,Object> lowCountMap=new HashMap<String,Object>();
        Map<String,Object> praiseCountMap=new HashMap<String,Object>();
        Map<String,Object> answeredCountMap=new HashMap<String,Object>();
		for(Entry<String, Map<String, Object>> map:resultMapChart.entrySet()){
			int highScoreCount=0;
			int lowScoreCount=0;
			int praiseCount=0;
			int answeredCount=0;
			for(Entry<String, Object> courseMap:map.getValue().entrySet()){
				CourseInfoForm form=(CourseInfoForm) courseMap.getValue();
				highScoreCount=highScoreCount+Integer.parseInt(form.getHighScoreCount());
				lowScoreCount=lowScoreCount+Integer.parseInt(form.getLowScoreCount());
				praiseCount=praiseCount+Integer.parseInt(form.getPraiseCount());
				answeredCount=answeredCount+Integer.parseInt(form.getAnsweredCount());
			}
			highCountMap.put(map.getKey(),highScoreCount);
			lowCountMap.put(map.getKey(),lowScoreCount);
			praiseCountMap.put(map.getKey(),praiseCount);
			answeredCountMap.put(map.getKey(), answeredCount);
		}
		model.addAttribute("highCountMap",highCountMap);
		model.addAttribute("lowCountMap",lowCountMap);
		model.addAttribute("praiseCountMap",praiseCountMap);
		model.addAttribute("answeredCountMap",answeredCountMap);
	    model.addAttribute("eduUserExtListChart", eduUserExtListChart);
        return "njmuedu/user/lessonInfo";
	}

	/**
	 * 管理员--教师授课信息--图
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/lessionInfoChart")
	public String lessionInfo(Model model){
		SysUser sysUser=new SysUser();
        String roleFlow = InitConfig.getSysCfg("njmuedu_teacher_role_flow");
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("roleFlow", roleFlow);
        paramMap.put("role", "teacher");
        //验证当前登录者权限
        String role=checkRole();
        if(GlobalConstant.ADMIN_ROLE.equals(role)){
        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        	paramMap.put("sysUser", sysUser);
        }
        //查询老师授课信息图表需要的信息
        List<EduUserExt> eduUserExtListChart=this.eduUserBiz.searchEduAndCourseList(paramMap);
        Map<String, Map<String, Object>> resultMapChart=this.courseScheduleBiz.countInfoOfTeacher(eduUserExtListChart);

		Map<String,Object> highCountMap=new HashMap<String,Object>();
        Map<String,Object> lowCountMap=new HashMap<String,Object>();
        Map<String,Object> praiseCountMap=new HashMap<String,Object>();
        Map<String,Object> answeredCountMap=new HashMap<String,Object>();
		for(Entry<String, Map<String, Object>> map:resultMapChart.entrySet()){
			int highScoreCount=0;
			int lowScoreCount=0;
			int praiseCount=0;
			int answeredCount=0;
			for(Entry<String, Object> courseMap:map.getValue().entrySet()){
				CourseInfoForm form=(CourseInfoForm) courseMap.getValue();
				highScoreCount=highScoreCount+Integer.parseInt(form.getHighScoreCount());
				lowScoreCount=lowScoreCount+Integer.parseInt(form.getLowScoreCount());
				praiseCount=praiseCount+Integer.parseInt(form.getPraiseCount());
				answeredCount=answeredCount+Integer.parseInt(form.getAnsweredCount());
			}
			highCountMap.put(map.getKey(),highScoreCount);
			lowCountMap.put(map.getKey(),lowScoreCount);
			praiseCountMap.put(map.getKey(),praiseCount);
			answeredCountMap.put(map.getKey(), answeredCount);
		}
		model.addAttribute("highCountMap",highCountMap);
		model.addAttribute("lowCountMap",lowCountMap);
		model.addAttribute("praiseCountMap",praiseCountMap);
		model.addAttribute("answeredCountMap",answeredCountMap);
	    model.addAttribute("eduUserExtListChart", eduUserExtListChart);
        return "njmuedu/user/chart/lessonInfoChart";
	}
	
	/**
	 * 我的收藏
	 * @param schedule
	 */
	@RequestMapping(value="/setCollection")
	public String setCollection(Model model){
		return "njmuedu/course/collectionList";
	}
	
	/**
	 * 加载收藏
	 * @param model
	 */
	@RequestMapping(value="/showCollection")
	public String showCollection(String collectionTypeId, Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null && StringUtil.isNotBlank(collectionTypeId)){
			EduCollection collection = new EduCollection();
			collection.setUserFlow(currUser.getUserFlow());
			collection.setCollectionTypeId(collectionTypeId);
			collection.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<EduCollection> collectionList = collectionBiz.searchCollectionList(collection);
			if(collectionList != null && !collectionList.isEmpty()){
				List<String> resourseFlowList = new ArrayList<String>();
				for(EduCollection c : collectionList){
					resourseFlowList.add(c.getResourceFlow());
				}
				if(collectionTypeId.equals(NjmuEduCollectionTypeEnum.Course.getId())){
					List<EduCourse> courseList = courseBiz.searchCourseListByCourseFlowList(resourseFlowList);
					model.addAttribute("courseList", courseList);
				}
				if(collectionTypeId.equals(NjmuEduCollectionTypeEnum.Chapter.getId())){
					List<EduCourseChapter> chapterList = eduCourseChapterBiz.searchChapterListByChapterFlowList(resourseFlowList);
					Map<String,Object> courseFlowMap=new HashMap<String, Object>();
					if(chapterList != null && !chapterList.isEmpty()){
						List<String> userFlowList = new ArrayList<String>();
						for(EduCourseChapter ch : chapterList){
							userFlowList.add(ch.getTeacherId());
							EduCourse courseFlow=courseBiz.readCourse(ch.getCourseFlow());
							courseFlowMap.put(ch.getChapterFlow(), courseFlow.getCourseName());
						}
						model.addAttribute("courseFlowMap", courseFlowMap);
						List<SysUser> teacherList = userBiz.searchSysUserByuserFlows(userFlowList);
						if(teacherList != null && !teacherList.isEmpty()){
							Map<String, String> teacherMap = new HashMap<String, String>();
							for(SysUser su : teacherList){
								teacherMap.put(su.getUserFlow(), su.getUserName());
							}
							model.addAttribute("teacherMap", teacherMap);
						}
						model.addAttribute("teacherList", teacherList);
					}
					model.addAttribute("chapterList", chapterList);
				}
			}
		}
		return "njmuedu/course/collectionDetail";
	}

	@RequestMapping(value="/checkRole")
	@ResponseBody
	public String checkRole(){
		List<String> currRoleList=(List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST);
		String stuRoleFlow=InitConfig.getSysCfg("njmuedu_student_role_flow");
		String tchRoleFlow=InitConfig.getSysCfg("njmuedu_teacher_role_flow");
		String hospitalRoleFlow=InitConfig.getSysCfg("njmuedu_hospital_role_flow");
		String adminRoleFlow=InitConfig.getSysCfg("njmuedu_admin_role_flow");
		String systemRoleFlow=InitConfig.getSysCfg("njmuedu_system_role_flow");
		if(currRoleList!=null && !currRoleList.isEmpty()){
			if(currRoleList.contains(stuRoleFlow)){
				return GlobalConstant.STUDENT_ROLE;
			}else if(currRoleList.contains(tchRoleFlow)){
				return GlobalConstant.TEACHER_ROLE;
			}else if(currRoleList.contains(adminRoleFlow)){
				return GlobalConstant.ADMIN_ROLE;
			}else if(currRoleList.contains(hospitalRoleFlow)){
				return GlobalConstant.HOSPITAL_ROLE;
			}else if(currRoleList.contains(systemRoleFlow)){
				return GlobalConstant.SYSTEM_ROLE;
			}else{
				return "";
			}
		}
		return null;
	}


	//*********** 老师   ***************
	
	/**
	 * 上传头像
	 * @param file
	 */
	@RequestMapping(value="/uploadImg")
	public @ResponseBody String uploadImg(MultipartFile imageFile){
		return this.eduUserBiz.uploadImg(imageFile);
	}
	
	/**
	 * 最新通知（老师）
	 * @param courseFlow
	 * @param model
	 */
	@RequestMapping(value="/notice")
	public String searchNotice(String courseFlow, Model model){
		EduCourseNotice courseNotice = new EduCourseNotice();
		courseNotice.setCourseFlow(courseFlow);
		List<EduCourseNoticeExt> eduCourseNoticeExtList = courseNoticeBiz.searchCourseNoticeListByCourseFlow(courseFlow);
		model.addAttribute("eduCourseNoticeExtList", eduCourseNoticeExtList);

		return "njmuedu/user/noticeList";
	}
	
	/**
	 * 保存通知（老师）
	 * @param courseNotice
	 * @param model
	 */
	@RequestMapping(value="/saveNotice")
	@ResponseBody
	public String saveNotice(EduCourseNotice courseNotice, Model model){
		if(StringUtil.isNotBlank(courseNotice.getNoticeTitle())){
			SysUser currUser = GlobalContext.getCurrentUser();
			courseNotice.setUserFlow(currUser.getUserFlow());
			courseNotice.setUserName(currUser.getUserName());
			int result = courseNoticeBiz.save(courseNotice);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.RELEASE_SUCCESSED;
			}
		}
		return GlobalConstant.RELEASE_FAIL;
	}
	
	/**
	 * 编辑通知
	 * @param courseNotice
	 * @param model
	 */
	@RequestMapping(value="/editNotice")
	@ResponseBody
	public EduCourseNotice editNotice(String noticeFlow, Model model){
		if(StringUtil.isNotBlank(noticeFlow)){
			EduCourseNotice courseNotice = courseNoticeBiz.readCourseNotice(noticeFlow);
			return courseNotice;
		}
		return null;
	}
	
	/**
	 * 删除通知（老师）
	 * @param noticeFlow
	 * @param model
	 */
	@RequestMapping(value="/delNotice")
	@ResponseBody
	public String delNotice(String noticeFlow, Model model){
		if(StringUtil.isNotBlank(noticeFlow)){
			EduCourseNotice courseNotice = new EduCourseNotice();
			courseNotice.setNoticeFlow(noticeFlow);
			courseNotice.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result = courseNoticeBiz.save(courseNotice);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}
	
	/**
	 * 课程章节（教师）
	 * @param courseFlow
	 * @param model
	 */
	@RequestMapping(value="/chapterList")
	public String searchChapterList(String courseFlow, Model model){
		EduCourseExt courseExt = courseBiz.searchOneWithChapters(courseFlow);
		model.addAttribute("courseExt", courseExt);
		return "njmuedu/user/chapterList";
	}
	
	/**
	 * 课程资料（教师）
	 * @param courseFlow
	 */
	@RequestMapping(value="/courseFile")
	public String searchCourseFile(EduCourseFile courseFile,Model model){
		List<EduCourseFile> courseFileList = courseFileBiz.searchCourseFileList(courseFile);
		model.addAttribute("courseFileList", courseFileList);
		return "njmuedu/user/fileList";
	}

	/**
	 * 保存课程资料（老师）
	 * @param file
	 * @param courseFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/saveCourseFile"})
	@ResponseBody
	public String saveCourseFile(MultipartFile file, String courseFlow) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = courseBiz.checkCourseFile(file);
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				int result = courseFileBiz.operateFile(file, courseFlow);
				if(result != GlobalConstant.ZERO_LINE){
					return GlobalConstant.UPLOAD_SUCCESSED;
				}
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}


	//*********** 学生   ***************
	
	/**
	 * 删除课程资料
	 * @param courseFlow
	 * @param model
	 */
	@RequestMapping(value="/delCourseFile")
	@ResponseBody
	public String delCourseFile(String recordFlow, Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			int result = courseFileBiz.delCourseFile(recordFlow);
			if(result != GlobalConstant.ZERO_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}
	
	/**
	 * 最新通知（学生）
	 * @param courseFlow
	 * @param model
	 */
	@RequestMapping(value="/studentNotice")
	public String studentNotice(String courseFlow, Model model){
		EduCourseNotice courseNotice = new EduCourseNotice();
		courseNotice.setCourseFlow(courseFlow);

		List<EduCourseNoticeExt> eduCourseNoticeExtList = courseNoticeBiz.searchCourseNoticeListByCourseFlow(courseFlow);
		model.addAttribute("eduCourseNoticeExtList", eduCourseNoticeExtList);
		return "njmuedu/user/student/noticeList";
	}
	
	/**
	 * 课程章节（学生）
	 * @param courseFlow
	 * @param model
	 */
	@RequestMapping(value="/studentChapter")
	public String studentChapter(String courseFlow, Model model){
		SysUser currUser=GlobalContext.getCurrentUser();
		EduCourseExt courseExt = courseBiz.searchOneWithChapters(courseFlow);
		model.addAttribute("courseExt", courseExt);
		//查询该学生该课程每一章节的学习情况
				EduCourseSchedule schedule=null;
				Map<String,EduCourseSchedule> scheduleMap=new HashMap<String, EduCourseSchedule>();
				//存放该课程中所有子章节
				List<String> sonChapterFlowList=new ArrayList<String>();
				if(null!=courseExt){
					if(null!=courseExt.getChapterList() && !courseExt.getChapterList().isEmpty()){
						for(EduCourseChapter chapter:courseExt.getChapterList()){
							if(StringUtil.isNotBlank(chapter.getChapterFile())){
								sonChapterFlowList.add(chapter.getChapterFlow());
							}
							schedule=new EduCourseSchedule();
							schedule.setChapterFlow(chapter.getChapterFlow());
							schedule.setUserFlow(currUser.getUserFlow());
							EduCourseSchedule eduCourseSchedule=eduCourseScheduleBiz.searchOne(schedule);
							if(eduCourseSchedule!=null){
								scheduleMap.put(chapter.getChapterFlow(), eduCourseSchedule);
							}
						}
					}
				}
				model.addAttribute("scheduleMap", scheduleMap);
		return "njmuedu/user/student/chapterList";
	}
	
	/**
	 * 课程资料（学生）
	 * @param courseFlow
	 */
	@RequestMapping(value="/studentCourseFile")
	public String studentCourseFile(EduCourseFile courseFile, Model model){
		List<EduCourseFile> courseFileList = courseFileBiz.searchCourseFileList(courseFile);
		model.addAttribute("courseFileList", courseFileList);
		return "njmuedu/user/student/fileList";
	}
	
	/**
	 * 随堂测试（学生）
	 * @param courseFlow
	 */
	@RequestMapping(value="/studentCourseTest")
	public String studentCourseTest(EduCourseFile courseFile, Model model){
		return "njmuedu/user/student/courseTest";
	}
	
	/**
	 * 设置问题为精品状态
	 * @param questionFlow
	 * @return
	 */
	@RequestMapping(value="/setYQuintessence")
	@ResponseBody
	public String setYQuintessence(String questionFlow){
		EduQuestion eduQuestion=this.questionBiz.readEduQuestion(questionFlow);
		if(eduQuestion!=null){
	       if(GlobalConstant.FLAG_Y.equals(eduQuestion.getQuintessence())){
	    	   return GlobalConstant.OPRE_FAIL;
	       }else{
	    	   eduQuestion.setQuintessence(GlobalConstant.FLAG_Y);
	    	   int result=this.questionBiz.saveQuestion(eduQuestion);
	    	   if(result==GlobalConstant.ONE_LINE){
	    		   return GlobalConstant.OPRE_SUCCESSED;
	    	   }else{
	    		   return GlobalConstant.OPRE_FAIL;
	    	   }
	       }
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	/**
	 * 取消设置问题为精品状态
	 * @param questionFlow
	 */
	@RequestMapping(value="/setNQuintessence")
	@ResponseBody
	public String setNQuintessence(String questionFlow){
		EduQuestion eduQuestion=this.questionBiz.readEduQuestion(questionFlow);
		if(eduQuestion!=null){
	       if(GlobalConstant.FLAG_N.equals(eduQuestion.getQuintessence())){
	    	   return GlobalConstant.OPRE_FAIL;
	       }else{
	    	   eduQuestion.setQuintessence(GlobalConstant.FLAG_N);
	    	   int result=this.questionBiz.saveQuestion(eduQuestion);
	    	   if(result==GlobalConstant.ONE_LINE){
	    		   return GlobalConstant.OPRE_SUCCESSED;
	    	   }else{
	    		   return GlobalConstant.OPRE_FAIL;
	    	   }
	       }
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 教师风采列表
	 * @param condition
	 * @param model
	 */
	@RequestMapping(value="/tchMienList")
	public String searchTchMienList(Integer currentPage,Model model){

		String tchRoleFlow=InitConfig.getSysCfg("njmuedu_teacher_role_flow");
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("roleFlow", tchRoleFlow);
		List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
		model.addAttribute("eduUserExtList", eduUserExtList);
	    return "njmuedu/teacherMien/teacherList";
	}

	//****************************  作业 （老师）      ***********************************
	
	/**
	 * 教师风采-教师主页
	 * @param condition
	 * @param model
	 */
	@RequestMapping(value="/tchMienDetail")
	public String searchTchMienDetail(String userFlow,Model model){
		Map<String,Object> paramMap=new HashMap<String, Object>();
	    paramMap.put("userFlow", userFlow);
	    //根据流水号查询该教师的详细信息
	    List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
	    if(eduUserExtList!=null && !eduUserExtList.isEmpty()){
	    	 EduUserExt eduUserExt=eduUserExtList.get(0);
	    	 model.addAttribute("eduUserExt", eduUserExt);
	    }
		SysUser currUser=new SysUser();
		currUser.setUserFlow(userFlow);
		//查询出该老师所教的课程
		List<EduCourse> courseList=this.courseBiz.searchTchCourseList(null,currUser);
		model.addAttribute("courseList", courseList);
		//存放每门课的章的数量
	    Map<String,Integer> noParentMap=new HashMap<String, Integer>();
	    //存放每门课节的数量
		Map<String,Integer> parentMap=new HashMap<String, Integer>();
		//存放选修每门课的人数
		Map<String,Integer> countOneCourseMap=new HashMap<String, Integer>();
		if(null!=courseList && !courseList.isEmpty()){
			for(EduCourse course:courseList){
				int countOneCourse=this.courseBiz.countUserSelectOneCourse(course);
				int noParent=this.eduCourseChapterBiz.countNoParentChapterByCourseFlow(course);
				int parent=this.eduCourseChapterBiz.countParentChapterByCourseFlow(course);
				noParentMap.put(course.getCourseFlow(), noParent);
				parentMap.put(course.getCourseFlow(), parent);
				countOneCourseMap.put(course.getCourseFlow(), countOneCourse);
			}
		}
		model.addAttribute("noParentMap", noParentMap);
		model.addAttribute("parentMap", parentMap);
		model.addAttribute("countOneCourseMap", countOneCourseMap);

		return "njmuedu/teacherMien/detail";
	}
	
	/**
	 * 全部作业
	 * @param model
	 */
	@RequestMapping(value="/teacherWorkList", method={RequestMethod.GET,RequestMethod.POST})
	public String teacherWorkList(EduQuestion question, EduCourse course, EduCourseChapter chapter, Integer currentPage, String courseFlag, Model model){
		if(StringUtil.isBlank(courseFlag)){//课程情况总览查询该课程全部老师作业
			String userFlow = GlobalContext.getCurrentUser().getUserFlow();
			question.setUserFlow(userFlow);
		}
		question.setQuesTypeId(NjmuQATypeEnum.Work.getId());
		PageHelper.startPage(currentPage, 10);
		List<EduQuestionExt> questionExtList = questionBiz.searchTeacherWorkList(question, course, chapter);
		model.addAttribute("questionExtList", questionExtList);
		return "njmuedu/user/teacher/teacherWorkList";
	}
	
	/**
	 * 加载学生上传的作业
	 * @param questionFlow
	 * @param currentPage
	 * @param model
	 */
	@RequestMapping(value={"/studentWorkAnswerList"})
	public String studentWorkAnswerList(EduAnswer answer, SysUser sysUser,
										String courseFlow, String chapterFlow, Integer currentPage, Model model){
		answer.setAnsTypeId(NjmuQATypeEnum.Work.getId());
		PageHelper.startPage(currentPage, 10);
		List<EduAnswerExt> auswerExtList = answerBiz.searchStudentWorkAnswerList(answer, sysUser);
		model.addAttribute("auswerExtList", auswerExtList);
		if(StringUtil.isNotBlank(courseFlow)){
			EduCourse eduCourse = courseBiz.readCourse(courseFlow);
			model.addAttribute("eduCourse", eduCourse);
		}
		if(StringUtil.isNotBlank(chapterFlow)){
			EduCourseChapter eduCourseChapter = eduCourseChapterBiz.readEduCourseChapter(chapterFlow);
			model.addAttribute("eduCourseChapter", eduCourseChapter);
		}
		return "njmuedu/user/teacher/studentWorkList";
	}
	
	/**
	 * 下载单条学生作业
	 * @param questionFlow
	 * @param response
	 * @param ansDir
	 * @throws Exception
	 */
	@RequestMapping(value={"/downloadStuWork"})
	public void downloadStuWork(String questionFlow, String answerUserFlow, HttpServletResponse response, String ansDir) throws Exception{
		// 学生作业存储路径为：  student/questionFlow/userFlow
		String fileName = ansDir.substring(ansDir.lastIndexOf("/") + 1);
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String fileDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + "studentWork" + File.separator + questionFlow + File.separator +  fileName;
		File file = new File(fileDir);

		SysUser sysUser = userBiz.readSysUser(answerUserFlow);
        EduUser eduUser = eduUserBiz.readEduUser(answerUserFlow);
        fileName = StringUtil.defaultString(eduUser.getSid()) +"_"+ StringUtil.defaultString(sysUser.getUserName())+ suffix;
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");

		FileInputStream is = new FileInputStream(file);
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		byte[] b = new byte[(int) file.length()];
        int n;
        while ((n = is.read(b)) != -1){
        	os.write(b, 0, n);
        }
	    os.flush();
	    is.close();
	    os.close();
	}
	
	/**
	 * 批量下载学生作业
	 * @param model
	 * @throws Exception
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value={"/batchDownloadStuWork"})
	public void batchDownloadStuWork(EduAnswer answer, SysUser sysUser, HttpServletRequest request, HttpServletResponse response, Model model) throws FileNotFoundException, Exception{
		String folder = System.getProperty("java.io.tmpdir") + File.separator + PkUtil.getUUID();

		String questionFlowDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + "studentWork" + File.separator + answer.getQuestionFlow();
		File questionFlowDirFile = new File(questionFlowDir);

		answer.setAnsTypeId(NjmuQATypeEnum.Work.getId());
		List<EduAnswerExt> answerExtList = answerBiz.searchStudentWorkAnswerList(answer, null);
		Map<String, String> userMap = new HashMap<String, String>();
		if(answerExtList != null && !answerExtList.isEmpty()){
			for(EduAnswerExt aExt :answerExtList){
				SysUser user = aExt.getUser();
				EduUser eduUser = aExt.getEduUser();
				String downloadFileName = eduUser.getSid() + "_"+ user.getUserName();
				userMap.put(user.getUserFlow(), downloadFileName);
			}
		}
		ZipUtil.makeNjmueduStuWorkDirectoryToZip(questionFlowDirFile, userMap, new File(folder+".zip"), "", 7);

		String fileName = "学生作业.zip";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

		byte[] data = getByte(new File(folder+".zip"));
	    if (data != null) {
			outputStream.write(data);
		}
		outputStream.flush();
		outputStream.close();
	}
	
	/**
	 * 保存作业成绩
	 * @param answer
	 */
	@RequestMapping(value={"/saveWorkAndGrade"})
	@ResponseBody
	public String saveWorkAndGrade(EduAnswer eduAnswer, EduCourseChapter courseChapter){
		if(StringUtil.isNotBlank(eduAnswer.getAnswerFlow())){
			int result = answerBiz.saveWorkAndGrade(eduAnswer, courseChapter);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	/**
	 * 发布作业
	 * @param model
	 */
	@RequestMapping(value="/editWork", method=RequestMethod.GET)
	public String editWork(EduCourseChapter chapter, Model model){
		//加载老师所授的课程
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		List<EduCourse> courseList = courseBiz.searchTeachCourseList(userFlow);
		model.addAttribute("courseList", courseList);
		if(StringUtil.isNotBlank(chapter.getCourseFlow())){
			chapter.setTeacherId(userFlow);
			List<EduCourseChapter> chapterList = eduCourseChapterBiz.searchCourseChapterList(chapter, null, GlobalConstant.FLAG_Y);
			model.addAttribute("chapterList", chapterList);
		}
		return "njmuedu/user/teacher/editWork";
	}
	
	/**
	 * 上传作业 
	 * @param file
	 * @param courseFlow
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadTeacherWork"})
	@ResponseBody
	public String uploadTeacherWork(MultipartFile file, String courseFlow) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = courseBiz.checkCourseFile(file);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				resultPath = courseBiz.saveFileToDirs("", file, "teacherWork");
				return resultPath;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	
	
	/**
	 * 加载该老师所授课程的章节
	 * @param chapter
	 */
	@RequestMapping(value={"/getChapterListByCourseFlow"})
	@ResponseBody
	public List<EduCourseChapter> getChapterListByCourseFlow(EduCourseChapter chapter){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		chapter.setTeacherId(userFlow);
		List<EduCourseChapter> chapterList = eduCourseChapterBiz.searchCourseChapterList(chapter, null, GlobalConstant.FLAG_Y);
		return chapterList;
	}
	
	/**
	 * 保存老师发布作业
	 * @param eduQuestion
	 */
	@RequestMapping(value={"/saveTeacherWork"})
	@ResponseBody
	public String saveTeacherWork(EduQuestion eduQuestion){
		eduQuestion.setQuesTypeId(NjmuQATypeEnum.Work.getId());
		eduQuestion.setQuesTypeName(NjmuQATypeEnum.Work.getName());
		int result = questionBiz.saveQuestion(eduQuestion);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	
	/**
	 * 停用作业
	 * @param eduQuestion
	 */
	@RequestMapping(value={"/stopWork"})
	@ResponseBody
	public String stopWork(String questionFlow, String courseFlow, String chapterFlow, String recordStatus){
		int result = questionBiz.stopWork(questionFlow, courseFlow, chapterFlow, recordStatus);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	
	//******************************  作业（学生） ****************************************
	/**
	 * 我的作业
	 * @param question
	 * @param course
	 * @param chapter
	 * @param currentPage
	 * @param model
	 */
	@RequestMapping(value="/myWorkList", method={RequestMethod.GET,RequestMethod.POST})
	public String myWorkList(EduQuestion question, EduCourse course, EduCourseChapter chapter, Integer currentPage, Model model){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		question.setQuesTypeId(NjmuQATypeEnum.Work.getId());
		EduStudentCourse studentCourse = new EduStudentCourse();
		studentCourse.setUserFlow(userFlow);
		PageHelper.startPage(currentPage, 10);
		List<EduQuestionExt> questionExtList = questionBiz.searchMyWorkList(question, course, chapter, studentCourse);
		model.addAttribute("questionExtList", questionExtList);
		//查询学生已上传的作业
		if(questionExtList != null && !questionExtList.isEmpty()){
			List<String> questionFlowList = new ArrayList<String>();
			for(EduQuestionExt queExt :questionExtList){
				if(!questionFlowList.contains(queExt.getQuestionFlow())){
					questionFlowList.add(queExt.getQuestionFlow());
				}
			}
			EduAnswer answer = new EduAnswer();
			answer.setAnswerUserFlow(userFlow);
			List<EduAnswer> auswerList = answerBiz.searchMyWorkAnswerList(answer, questionFlowList);
			if(auswerList != null && !auswerList.isEmpty()){
				Map<String, EduAnswer> answerMap = new HashMap<String, EduAnswer>();
				for(EduAnswer ans : auswerList){
					answerMap.put(ans.getQuestionFlow(), ans);
				}
				model.addAttribute("answerMap", answerMap);
			}
		}
		return "njmuedu/user/student/myWorkList";
	}
	
	/**
	 * 上传学生作业
	 * @param file
	 * @param eduAnswer
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadStudentWork"})
	@ResponseBody
	public String uploadStudentWork(MultipartFile file, EduAnswer eduAnswer, String oldAnsDir) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = courseBiz.checkCourseFile(file);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				oldAnsDir = oldAnsDir.substring(oldAnsDir.lastIndexOf("/") + 1);//覆盖删除的原文件
				//oldFolder = URLEncoder.encode(oldFolder, "UTF-8");
				resultPath = courseBiz.saveStudentWorkToDirs(oldAnsDir, file, "studentWork", eduAnswer.getQuestionFlow());
				eduAnswer.setAnswerTime(DateUtil.getCurrDateTime());
				eduAnswer.setAnsTypeId(NjmuQATypeEnum.Work.getId());
				eduAnswer.setAnsTypeName(NjmuQATypeEnum.Work.getName());
				eduAnswer.setAnsDir(resultPath);
				answerBiz.saveStudentWork(eduAnswer);
				return GlobalConstant.UPLOAD_SUCCESSED;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 管理员—成绩管理
	 * @return
	 */
	@RequestMapping(value={"/scoreInfo"})
	public String scoreInfo(Integer currentPage,Model model,SysUser user,String majorId,String isAdmin,String isQualified){
		SysOrg org = new SysOrg();
		//验证当前登录者权限
		if(!GlobalConstant.FLAG_Y.equals(isAdmin)){
			user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		org.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> orgList = orgBiz.searchOrg(org);
		model.addAttribute("orgList",orgList);
		Map<String,Object> paramMap=new HashMap<>();
		String roleFlow=InitConfig.getSysCfg("njmuedu_student_role_flow");
		paramMap.put("roleFlow", roleFlow);
		paramMap.put("sysUser", user);
		paramMap.put("majorId",majorId);
		paramMap.put("isQualified",isQualified);
		paramMap.put("userFlowList",true);
		List<EduCourse> courseList = this.courseBiz.searchCourseByCondition(null);
		model.addAttribute("courseList", courseList);
		PageHelper.startPage(currentPage, 15);
		List<EduUserExt> userList = this.eduUserBiz.searchEduUserForManage(paramMap);
        Map<String,Map<String,Object>> resultMap = eduExamScoreBiz.searchStuScore(userList);
		Map<String,Map<String,Object>> finishMap = new HashMap<>();
		finishMap = eduExamScoreBiz.searchFinish(userList);
		model.addAttribute("finishMap",finishMap);
		model.addAttribute("isAdmin",isAdmin);
		model.addAttribute("userList",userList);
		model.addAttribute("resultMap",resultMap);
		return "njmuedu/user/scoreInfo";
	}

	/**
	 * 管理员--成绩导入
	 * @return
	 */
	@RequestMapping(value={"/importStuCoScoreExcel"})
	@ResponseBody
	public String importStuCoScoreExcel(MultipartFile file){
		if(file.getSize() >0){
			try{
				int result = eduExamScoreBiz.importUserFromExcel(file);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 管理员—成绩导出
	 * @return
	 */
	@RequestMapping(value ={"/exportScore"})
	public void exportScore(String userName,String idNo,String orgFlow,String majorId,String isAdmin,String isQualified,HttpServletResponse response) throws Exception{
        SysUser user = new SysUser();
        user.setUserName(userName);
        user.setIdNo(idNo);
        user.setOrgFlow(orgFlow);
		if(!GlobalConstant.FLAG_Y.equals(isAdmin)){
			user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
        Map<String,Object> paramMap = new HashMap<>();
		String roleFlow=InitConfig.getSysCfg("njmuedu_student_role_flow");
		paramMap.put("roleFlow",roleFlow);
        paramMap.put("sysUser",user);
        paramMap.put("majorId",majorId);
		paramMap.put("isQualified",isQualified);
		paramMap.put("userFlowList",true);
		List<EduCourse> courseList = this.courseBiz.searchCourseByCondition(null);
		List<EduUserExt> userList = this.eduUserBiz.searchEduUserForManage(paramMap);
		Map<String,Map<String,Object>> resultMap = eduExamScoreBiz.searchStuScore(userList);
		Map<String,Map<String,Object>> performanceMap = new HashMap<>();
		performanceMap = eduExamScoreBiz.searchFinish(userList);
	//	Map<String,Object> finishMap = new HashMap<>();
	//	finishMap = eduExamScoreBiz.searchByStudentList(userList);
		String[] titles = new String[courseList.size()+5];
		titles[0] = "userName:姓名";
		titles[1] = "idNo:证件号码";
		titles[2] = "orgName:基地" ;
		titles[3] = "majorName:专业";
		for(int i = 0;i<courseList.size();i++){
			titles[4+i] = courseList.get(i).getCourseFlow()+":"+courseList.get(i).getCourseName();
		}
		titles[courseList.size()+4] = "isQualified:是否合格";
		List<Map<String,String>> datas=new ArrayList<>();
		for(EduUserExt user1: userList)
		{
			Map<String,String> data = new HashMap<>();
			data.put("userName",user1.getSysUser().getUserName());
			data.put("idNo",user1.getSysUser().getIdNo());
			data.put("orgName",user1.getSysUser().getOrgName());
			data.put("majorName",user1.getMajorName());
			Double regularGrade = 0.0;
			for(EduCourse course:courseList)
			{
				double examGrade = 0;
				regularGrade = Double.parseDouble(performanceMap.get(course.getCourseFlow()).get(user1.getUserFlow()).toString());
				if(null != resultMap.get(course.getCourseFlow()).get(user1.getUserFlow())){
					 examGrade = Double.parseDouble(resultMap.get(course.getCourseFlow()).get(user1.getUserFlow()).toString()) * 0.8;
				}
				data.put(course.getCourseFlow(),String.format("%.1f",regularGrade+examGrade));
			}
			int num = eduExamScoreBiz.searchByIsqualified(user1.getUserFlow());
            if(num == courseList.size()){
            	data.put("isQualified","合格");
			}else{
            	data.put("isQualified","不合格");
			}
			datas.add(data);
		}
		String fileName = "学员成绩信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles,datas, response.getOutputStream());
	}

	@RequestMapping(value={"/examinationInfo"})
	public String examinationInfo(Integer currentPage,SysUser user,String isFinsh,String majorId,String isAdmin,Model model){
		SysOrg org = new SysOrg();
		org.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		if(!GlobalConstant.FLAG_Y.equals(isAdmin)){
			user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		List<SysOrg> orgList = orgBiz.searchOrg(org);
		model.addAttribute("orgList",orgList);
		Map<String,Object> paramMap=new HashMap<>();
		String roleFlow=InitConfig.getSysCfg("njmuedu_student_role_flow");
		paramMap.put("roleFlow", roleFlow);
		paramMap.put("sysUser",user);
		paramMap.put("majorId",majorId);
		paramMap.put("isFinsh",isFinsh);
		paramMap.put("userFlowList",true);
		List<EduCourse> courseList = this.courseBiz.searchCourseByCondition(null);
		model.addAttribute("courseList", courseList);
		PageHelper.startPage(currentPage,15);
		List<EduUserExt> userList = this.eduUserBiz.searchEduUserForManage(paramMap);
		Map<String,Map<String,Object>> resultMap = eduExamScoreBiz.searchStuCourse(userList);
		model.addAttribute("userList",userList);
		model.addAttribute("resultMap",resultMap);
		return "njmuedu/user/examinationInfo";
	}

	@RequestMapping(value = {"/exportExamination"})
	public void exportExamination(String userName,String idNo,String orgFlow,String majorId,String isAdmin,String isFinsh,HttpServletResponse response) throws Exception{
		SysUser user = new SysUser();
		user.setUserName(userName);
		user.setIdNo(idNo);
		user.setOrgFlow(orgFlow);
		if(!GlobalConstant.FLAG_Y.equals(isAdmin)){
			user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		Map<String,Object> paramMap = new HashMap<>();
		String roleFlow=InitConfig.getSysCfg("njmuedu_student_role_flow");
		paramMap.put("roleFlow",roleFlow);
		paramMap.put("sysUser",user);
		paramMap.put("majorId",majorId);
		paramMap.put("isFinsh",isFinsh);
		paramMap.put("userFlowList",true);
		List<EduCourse> courseList = this.courseBiz.searchCourseByCondition(null);
		List<EduUserExt> userList = this.eduUserBiz.searchEduUserForManage(paramMap);
		Map<String,Map<String,Object>> resultMap = eduExamScoreBiz.searchStuCourse(userList);
		String[] titles = new String[courseList.size()+5];
		titles[0] = "userName:姓名";
		titles[1] = "idNo:证件号码";
		titles[2] = "orgName:基地" ;
		titles[3] = "majorName:专业";
		for(int i = 0;i<courseList.size();i++){
			titles[4+i] = courseList.get(i).getCourseFlow()+":"+courseList.get(i).getCourseName();
		}
		titles[courseList.size()+4] = "isFinsh:是否完成";
		List<Map<String,String>> datas=new ArrayList<>();
		for(EduUserExt user1: userList)
		{
			Map<String,String> data = new HashMap<>();
			data.put("userName",user1.getSysUser().getUserName());
			data.put("idNo",user1.getSysUser().getIdNo());
			data.put("orgName",user1.getSysUser().getOrgName());
			data.put("majorName",user1.getMajorName());
			int num = 0;
			for(EduCourse course:courseList)
			{
				if(null != resultMap.get(course.getCourseFlow()).get(user1.getUserFlow())){
					String courseFinsh = resultMap.get(course.getCourseFlow()).get(user1.getUserFlow()).toString();
					if(NjmuEduStudyStatusEnum.NotStarted.getName().equals(courseFinsh)){
					  data.put(course.getCourseFlow(),"学习中");
					}else if(NjmuEduStudyStatusEnum.Finish.getName().equals(courseFinsh)){
					  data.put(course.getCourseFlow(),courseFinsh);
					  num++;
					}
				}else{
					data.put(course.getCourseFlow(),"未学习");
				}
			}
			//int num = eduExamScoreBiz.searchByStudyStatusId(user1.getUserFlow());
			if(num == courseList.size()){
				data.put("isFinsh","是");
			}else{
				data.put("isFinsh","否");
			}
			datas.add(data);
		}
		String fileName = "学员考试名单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles,datas, response.getOutputStream());

	}
}



