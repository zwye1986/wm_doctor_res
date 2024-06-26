package com.pinde.sci.ctrl.edu;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.edu.*;
import com.pinde.sci.biz.edu.IEduImportRecordBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.enums.edu.EduCollectionTypeEnum;
import com.pinde.sci.enums.edu.EduQuestionStatusEnum;
import com.pinde.sci.enums.edu.EduStudyStatusEnum;
import com.pinde.sci.enums.edu.ImpTypeEnum;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.sys.*;
import com.pinde.sci.form.edu.CourseInfoForm;
import com.pinde.sci.form.edu.EduUserExtInfoForm;
import com.pinde.sci.form.edu.EduUserForm;
import com.pinde.sci.model.edu.*;
import com.pinde.sci.model.mo.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Controller
@RequestMapping("/edu/user")
public class EduUserCenterController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(EduUserCenterController.class);
	
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IEduUserCenterBiz userCenterBiz;
	@Autowired
	private IEduCourseBiz courseBiz;
	@Autowired
	private IEduCourseChapterBiz eduCourseChapterBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IEduCourseQuestionBiz questionBiz;
	@Autowired
	private IEduCourseAnswerBiz answerBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IEduCourseScheduleBiz courseScheduleBiz;
	@Autowired
	private IEduCollectionBiz collectionBiz;
	@Autowired
	private IEduUserBiz eduUserBiz;
	@Autowired
	private IEduCourseChapterBiz chapterBiz;
	@Autowired
	private IEduCourseNoticeBiz courseNoticeBiz;
	@Autowired
	private IEduCourseFileBiz courseFileBiz;
	@Autowired
	private IEduStudentCourseBiz studentCourseBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IEduCourseMajorBiz courseMajorBiz;
	@Autowired
	private IEduImportRecordBiz importRecordBiz;
    @Autowired
    private IRoleBiz roleBiz;

	/**
	 * 设置个人信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/setUserInfo")
	public String setUserInfo(Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			String userFlow = currUser.getUserFlow();
			SysUser user = userBiz.readSysUser(userFlow);
			model.addAttribute("user", user);
			//查询学校
			SysOrg sysOrg = new SysOrg();
			sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
			sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
			model.addAttribute("orgList", orgList);
			//专业
			EduUser eduUser = eduUserBiz.readEduUser(userFlow);
			model.addAttribute("eduUser", eduUser);
			String role=checkRole();
			if(role.equals(GlobalConstant.TEACHER_ROLE)){
				return "xjgl/user/teacher/userInfo";
			}else if(role.equals(GlobalConstant.STUDENT_ROLE)){
				return "xjgl/user/student/userInfo";
			}else if(role.equals(GlobalConstant.ADMIN_ROLE)){
				return "xjgl/user/admin/userInfo";
			}else if(role.equals(GlobalConstant.SYSTEM_ROLE)){
				return "xjgl/user/system/userInfo";
			}else{
				return "inx/xjgl/login";
			}	
		}
		return "inx/xjgl/login";
	}
	


	@RequestMapping(value="/courseFlowCourse")
	@ResponseBody
	public List<EduCourseMajorExt> courseFlowCourse(String majorId,String period){
		List<EduCourseMajorExt> currCourseMajorlist=eduUserBiz.searchEduUserCourseList(majorId,period);
		return currCourseMajorlist;
	}

	@RequestMapping(value="/saveStudentCourse")
	public @ResponseBody String saveStudentCourse(@RequestBody List<EduStudentCourse> studentCourseList ){
		EduUser eduUser=new EduUser();
		EduCourse course=new EduCourse();
		EduCourseMajor eduCourseMajor=new EduCourseMajor();
		for (EduStudentCourse eduStudentCourse : studentCourseList) {
			if (StringUtil.isNotBlank(eduStudentCourse.getCourseFlow())) {
				eduUser=eduUserBiz.readEduUser(eduStudentCourse.getUserFlow());
				course=courseBiz.readCourse(eduStudentCourse.getCourseFlow());
				eduCourseMajor.setMajorId(eduUser.getMajorId());
				eduCourseMajor.setCourseFlow(eduStudentCourse.getCourseFlow());
				eduCourseMajor.setPlanYear(eduUser.getPeriod());
				List<EduCourseMajor> courseMajorList=courseMajorBiz.onlyCourseMajor(eduCourseMajor);
				String gradeName=DictTypeEnum.RecruitSeason.getDictNameById(eduStudentCourse.getGradeTermId());
				eduStudentCourse.setGradeTermName(gradeName);
				if(StringUtil.isBlank(eduStudentCourse.getGradeTermId())){
					eduStudentCourse.setGradeTermId("");
					eduStudentCourse.setGradeTermName("");
				}
				if(StringUtil.isNotBlank(eduStudentCourse.getStudyWayId())){
					eduStudentCourse.setStudyWayName(DictTypeEnum.XjStudyWay.getDictNameById(eduStudentCourse.getStudyWayId())); 
				}
				if(StringUtil.isNotBlank(eduStudentCourse.getAssessTypeId())){
					eduStudentCourse.setAssessTypeName(DictTypeEnum.XjEvaluationMode.getDictNameById(eduStudentCourse.getAssessTypeId())); 
				}
				if(course!=null){
					eduStudentCourse.setCourseCode(course.getCourseCode());
					eduStudentCourse.setCourseName(course.getCourseName());
					eduStudentCourse.setCourseNameEn(course.getCourseNameEn());
					eduStudentCourse.setCourseCredit(course.getCourseCredit());
					eduStudentCourse.setCoursePeriod(course.getCoursePeriod());
				}
				eduStudentCourse.setStudentPeriod(eduUser.getPeriod());
				if(courseMajorList!=null&&!courseMajorList.isEmpty()){
					eduStudentCourse.setCourseTypeId(courseMajorList.get(0).getCourseTypeId());
					eduStudentCourse.setCourseTypeName(courseMajorList.get(0).getCourseTypeName());
				}
				
				studentCourseBiz.save(eduStudentCourse);
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}
	
	/**
	 * 检查手机号、邮箱、身份证号唯一
	 * @param sysUser
	 * @param userFlow 检查流水号
	 * @return
	 */
	@RequestMapping(value="/checkUser")  
	
	@ResponseBody
	public String checkUser(SysUser sysUser, String userFlow){
			SysUser user = null;
			//用户登录名唯一
			String userCode = sysUser.getUserCode();
			if(StringUtil.isNotBlank(userCode)){
				user = userBiz.findByUserCode(userCode.trim());
				if(user != null){
					if(StringUtil.isNotBlank(userFlow)){
						if(!user.getUserFlow().equals(userFlow)){
							return GlobalConstant.USER_CODE_REPETE;
						}
					}else{
						return GlobalConstant.USER_CODE_REPETE;
					}
				}
			}
			//身份证号唯一
			String idNo = sysUser.getIdNo();
			if(StringUtil.isNotBlank(idNo)){
				user = userBiz.findByIdNo(idNo.trim());
				if(user != null){
					if(StringUtil.isNotBlank(userFlow)){
						if(!user.getUserFlow().equals(userFlow)){
							return GlobalConstant.USER_ID_NO_REPETE;
						}
					}else{
						return GlobalConstant.USER_ID_NO_REPETE;
					}
				}
			}
			//手机号唯一
			String userPhone = sysUser.getUserPhone();
			if(StringUtil.isNotBlank(userPhone)){
				user = userBiz.findByUserPhone(userPhone.trim());
				if(user != null){
					if(StringUtil.isNotBlank(userFlow)){
						if(!user.getUserFlow().equals(userFlow)){
							return GlobalConstant.USER_PHONE_REPETE;
						}
					}else{
						return GlobalConstant.USER_PHONE_REPETE;
					}
				}
			}
			//邮箱唯一
			String userEmail = sysUser.getUserEmail();
			if(StringUtil.isNotBlank(userEmail)){
				user = userBiz.findByUserEmail(userEmail.trim());
				if(user != null){
					if(StringUtil.isNotBlank(userFlow)){
						if(!user.getUserFlow().equals(userFlow)){
							return GlobalConstant.USER_EMAIL_REPETE;
						}
					}else{
						return GlobalConstant.USER_EMAIL_REPETE;
					}
				}
			}
			return GlobalConstant.FLAG_Y;
	}
	
	/**
	 * 保存个人设置
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveUserInfo")
	@ResponseBody
	public String saveUserInfo(SysUser sysUser, EduUser eduUser){
		String userFlow = sysUser.getUserFlow();
		if(StringUtil.isNotBlank(userFlow)){
			String checkResult = checkUser(sysUser, userFlow);
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}
			//*****************
			String orgFlow = sysUser.getOrgFlow();
			SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);		
			int result = eduUserBiz.saveUserAndEduUser(sysUser, eduUser);
			if(result != GlobalConstant.ZERO_LINE){
				//设置当前用户
				SysUser currUser = userBiz.readSysUser(userFlow);
				this.setSessionAttribute(GlobalConstant.CURRENT_USER, currUser);
				if(StringUtil.isNotBlank(orgFlow)){
					this.setSessionAttribute(GlobalConstant.CURRENT_ORG, sysOrg);
				}
				EduUser newEduUser = this.eduUserBiz.readEduUser(userFlow); 
				this.setSessionAttribute(GlobalConstant.CURR_EDU_USER, newEduUser);
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 跳转至修改密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/setUserPasswd")
	public String setUserPasswd(Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			return "xjgl/user/userPasswd";
		}else{//登录
			return "inx/xjgl/login";
		}
	}

	
	/**
	 * 修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveUserPasswd")
	@ResponseBody
	public String saveUserPasswd(String userFlow,String userPasswd, String userPasswd2, String userPasswd3){
		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(userPasswd) && StringUtil.isNotBlank(userPasswd2) && StringUtil.isNotBlank(userPasswd3)){
			SysUser user = userBiz.readSysUser(userFlow);
			if(user != null){
				userPasswd = userPasswd.trim();
				userPasswd2 = userPasswd2.trim();
				String enctyptPassword = PasswordHelper.encryptPassword(userFlow, userPasswd);
				if(!user.getUserPasswd().equals(enctyptPassword)){
					return GlobalConstant.PASSWD_ERROR;
				}
				String enctyptPassword2 = PasswordHelper.encryptPassword(userFlow, userPasswd2);
				user.setUserPasswd(enctyptPassword2);
				int result = userBiz.updateUser(user);
				if(result != GlobalConstant.ZERO_LINE){
					return GlobalConstant.OPRE_SUCCESSED;
				}
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}	
	
	/**
	 * 学生个人中心
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/student")
	public String center(Model model){
		SysUser currUser=GlobalContext.getCurrentUser();
		//查询该学生选择的每种类型课程的数量
		Map<String,Object> countCourseTypeMap=this.userCenterBiz.countUserCourseByType(currUser.getUserFlow());
		model.addAttribute("countCourseTypeMap", countCourseTypeMap);
		//查询该学生已经获得的学分数量
		int allCredit=this.courseBiz.countUserAllCredit(currUser.getUserFlow());
		model.addAttribute("allCredit", allCredit);

		return "xjgl/user/student/main";
	}
	
	/**
	 * 根据条件查询学生推荐课程(选的人最多或者评分最高)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/recommendCourse")
	public String recommendCourse(String sortType,Model model){
		if(StringUtil.isBlank(sortType)){
			sortType="score";
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		//查询EduUser
		EduUser eduUser=this.eduUserBiz.readEduUser(currUser.getUserFlow());
		if(eduUser!=null){
			//查询推荐课程
			List<EduCourseExt> recommendList=null;
			if(sortType.equals("choose")){
				PageHelper.startPage(1, 5);
				recommendList=this.userCenterBiz.countRecommendCourseByChooseMost(eduUser);
			}else if(sortType.equals("score")){
				PageHelper.startPage(1, 5);
				recommendList=this.userCenterBiz.countRecommendCourseByScoreMost(eduUser);
			}
		    
			model.addAttribute("recommendCourse", recommendList);
			
		}
		return "xjgl/user/student/recommendCourse";
	}
	/**
	 * 教师个人中心
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/teacher")
	public String teacherCenter(Model model){
		SysUser currUser=GlobalContext.getCurrentUser();
		//查询当前用户信息
		SysUser user=this.userBiz.readSysUser(currUser.getUserFlow());
		model.addAttribute("user", user);
		//查询该教师的每种类型课程的数量
		Map<String,Object> countCourseTypeMap=this.userCenterBiz.countTchCourse(currUser.getUserFlow());
		model.addAttribute("countCourseTypeMap", countCourseTypeMap);

		return "xjgl/user/teacher/main";
	}
	
	/**
	 * 教师课程排行
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchOrder")
	public String searchOrder(String sortType, Model model){
		if(StringUtil.isBlank(sortType)){
			sortType="score";
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		//查询EduUser（学校、专业）
		EduUser eduUser = this.eduUserBiz.readEduUser(currUser.getUserFlow());
		if(eduUser!=null){
			//查询课程排行
			List<EduCourseExt> courseList=null;
			if(sortType.equals("question")){
				PageHelper.startPage(1, 5);
				courseList=this.userCenterBiz.searchCourseListByQuestionOrder(eduUser);
			}else if(sortType.equals("score")){
				PageHelper.startPage(1, 5);
				courseList=this.userCenterBiz.searchCourseListByScoreOrder(eduUser);
			}
			model.addAttribute("courseList", courseList);
		}
		return "xjgl/user/teacher/courseOrder";
	}
	
	/**
	 * 教师所有问题
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/allQuestion")
	public String allQuestion(EduQuestion eduQuestion,Model model){
		SysUser currUser=GlobalContext.getCurrentUser();
		//查看所有向该老师提出的问题
		if(StringUtil.isBlank(eduQuestion.getQuestionStatusId())){
			eduQuestion.setQuestionStatusId(EduQuestionStatusEnum.Unanswered.getId());
		}
	    List<EduQuestion> questionList=this.questionBiz.searchEduQuestionsList(eduQuestion,currUser);
		model.addAttribute("questionList", questionList);
		//查看选择该教师课程的学生
		List<SysUser> stuList=this.courseBiz.searchUserByTch(currUser);
		Map<String,Object> stuMap=new HashMap<String, Object>();
		Map<String,Object> eduMap=new HashMap<String, Object>();
		if(stuList!=null && !stuList.isEmpty()){
			for(SysUser user:stuList){
				EduUser eduUser=this.eduUserBiz.readEduUser(user.getUserFlow());
				if(eduUser!=null){
					eduMap.put(eduUser.getUserFlow(), eduUser);
				}
				stuMap.put(user.getUserFlow(), user);
			}
			
		}
		model.addAttribute("stuMap", stuMap);
		model.addAttribute("eduMap", eduMap);
		if(questionList!=null && !questionList.isEmpty()){
			Map<String,List<EduAnswer>> answerMap=new HashMap<String, List<EduAnswer>>();
			for(EduQuestion question:questionList){
				List<EduAnswer> answerList=answerBiz.searchAnswerList(question.getQuestionFlow());
	    	    answerMap.put(question.getQuestionFlow(), answerList);
			}
			model.addAttribute("answerMap", answerMap);
		}
		//组织课程-章节Map
		EduCourse eduCourse=new EduCourse();
		List<EduCourse> courseList=this.courseBiz.searchTchCourseList(eduCourse,currUser);
		//存放课程信息Map
		Map<String,Object> courseMap=new HashMap<String, Object>();
		//存放课程-章节信息Map
		Map<String,Map<String,Object>> courseChapterMap=new HashMap<String, Map<String,Object>>();
		//存放章节信息Map
		Map<String,Object> chapterMap=null;
		if(courseList!=null && !courseList.isEmpty()){
			for(EduCourse course:courseList){
				courseMap.put(course.getCourseFlow(), course);
				List<EduCourseChapter> chapterList=eduCourseChapterBiz.getAllChapter(course.getCourseFlow());
				chapterMap=new HashMap<String, Object>();
				if(chapterList!=null && !chapterList.isEmpty()){
					for(EduCourseChapter chapter:chapterList){
				    	chapterMap.put(chapter.getChapterFlow(), chapter);
				    }
				}
				courseChapterMap.put(course.getCourseFlow(), chapterMap);
			    
			}		
		}
		model.addAttribute("courseMap", courseMap);
		model.addAttribute("courseChapterMap", courseChapterMap);
		return "xjgl/user/teacher/allQuestion";
	}
	
	@RequestMapping(value="/submitAnswer")
	@ResponseBody
	public String submitAnswer(EduAnswer answer,Model model){
		SysUser currUser=GlobalContext.getCurrentUser();
		answer.setAnswerUserFlow(currUser.getUserFlow());		 
		answer.setAnswerTime(DateUtil.getCurrDateTime());
		int result=answerBiz.saveAnswer(answer);
		if(result==GlobalConstant.ONE_LINE){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	/**
	 * 教师所授课程
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/teachCourses")
	public String teachCourses(EduCourse eduCourse,Model model){
		SysUser currUser=GlobalContext.getCurrentUser();
		//查询出该老师所教的课程
		List<EduCourse> courseList=this.courseBiz.searchTchCourseList(eduCourse,currUser);
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
		return "xjgl/user/teacher/courseList";
	}
	
	
	@RequestMapping(value="/showStuCourse")
	public String showCourse(EduCourse eduCourse,String studyStatus,Model model){
		SysUser currUser=GlobalContext.getCurrentUser();
		//如果学习状态为空，默认正在学习
		if(StringUtil.isBlank(studyStatus)){
			studyStatus=EduStudyStatusEnum.Underway.getId();
		}
		//查询出该学生选择的所有课程
		List<EduCourse> courseList=courseBiz.searchStuCourseList(eduCourse, currUser,studyStatus);
		model.addAttribute("courseList", courseList);
		//存放每门课的章的数量
	    Map<String,Integer> noParentMap=new HashMap<String, Integer>();
	    //存放每门课节的数量
		Map<String,Integer> parentMap=new HashMap<String, Integer>();
		//查询该学生每门课进度
		EduCourseSchedule schedule=null;
		Map<String,Object> scheduleMap=new HashMap<String, Object>();
		//存放每门课正在学习的章节
		Map<String,Object> underWayChapterMap=new HashMap<String, Object>();
		
		if(null!=courseList && !courseList.isEmpty()){
			for(EduCourse course:courseList){
				EduCourseExt courseExt = this.courseBiz.searchOneWithChapters(course.getCourseFlow());
				int noParent=this.eduCourseChapterBiz.countNoParentChapterByCourseFlow(course);
				int parent=this.eduCourseChapterBiz.countParentChapterByCourseFlow(course);
				noParentMap.put(course.getCourseFlow(), noParent);
				parentMap.put(course.getCourseFlow(), parent);
				schedule=new EduCourseSchedule();
				schedule.setUserFlow(currUser.getUserFlow());
				schedule.setCourseFlow(course.getCourseFlow());
				//查询当前用户当前课程总共的章节数量
				List<EduCourseSchedule> allScheduleList=this.courseBiz.searchScheduleList(schedule);
				//查询当前用户正在学习的章节信息
				schedule.setStudyStatusId(EduStudyStatusEnum.Underway.getId());
				List<EduCourseSchedule> underwayScheduleList=this.courseBiz.searchScheduleList(schedule);
				if(!underwayScheduleList.isEmpty()){
					EduCourseChapter chapter=this.chapterBiz.seachOne(underwayScheduleList.get(0).getChapterFlow());
					underWayChapterMap.put(course.getCourseFlow(), chapter);
				}
				//查询当前用户当前课程已完成的章节数量
				schedule.setStudyStatusId(EduStudyStatusEnum.Finish.getId());
				List<EduCourseSchedule> finishScheduleList=this.courseBiz.searchScheduleList(schedule);
				if(allScheduleList!=null && !allScheduleList.isEmpty()
				   && finishScheduleList!=null && !finishScheduleList.isEmpty()){
					BigDecimal allScheduleSize=new BigDecimal(allScheduleList.size());
					BigDecimal finishScheduleSize=new BigDecimal(finishScheduleList.size());
					BigDecimal countSchedule=finishScheduleSize.divide(allScheduleSize, 2 , BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_HALF_UP);
					scheduleMap.put(course.getCourseFlow(), countSchedule);
				}
				
			}
		}
		model.addAttribute("noParentMap", noParentMap);
		model.addAttribute("parentMap", parentMap);
		model.addAttribute("scheduleMap",scheduleMap);
		model.addAttribute("underWayChapterMap", underWayChapterMap);

		return "xjgl/user/student/courseList";
	}
	/**
	 * 系统管理员
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/system")
	public String system(Model model){
		return "xjgl/user/system/main";
	}
	
	/**
	 * 学校管理员
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin")
	public String admin(Model model){
		return "xjgl/user/admin/main";
	}
	/**
	 * 管理员-教师信息
	 * @param condition
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tchInfo")
	public String searchTeacherInfoAdmin(Integer currentPage,String condition,Model model){
		SysUser sysUser=new SysUser();
		if(StringUtil.isBlank(condition)){
        	condition="";
        }
        String tchRoleFlow=InitConfig.getSysCfg("teacher_role_flow");
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("condition", "%"+condition+"%");
        paramMap.put("roleFlow", tchRoleFlow);
        //验证当前登录者权限
        String role=checkRole();
        if(GlobalConstant.ADMIN_ROLE.equals(role)){
        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        	paramMap.put("sysUser", sysUser);
        }
        PageHelper.startPage(currentPage, 15);
		List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
		model.addAttribute("eduUserExtList", eduUserExtList);
		//查询每个教师所教授的课程数量和详细信息
		Map<String, Map<String, Object>> searchAndCountCourseMap=this.courseBiz.searchCourseInfoAndCountByEduUserExt(eduUserExtList);
		model.addAttribute("searchAndCountCourseMap", searchAndCountCourseMap);
		return "xjgl/user/tchInfo";
	}
	
	
	
	
	/**
	 * 管理员-教师详细情况
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tchDetail")
	public String searchTeacherDeatil(String userFlow,Model model){
	    Map<String,Object> paramMap=new HashMap<String, Object>();
	    paramMap.put("userFlow", userFlow);
	    //根据流水号查询该教师的详细信息
	    List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
	    if(eduUserExtList!=null && !eduUserExtList.isEmpty()){
	    	model.addAttribute("eduUserExt", eduUserExtList.get(0));
	    }
	    //查询该教师所教授的全部章节信息
	    Map<String,Map<String,List<EduCourseChapterExt>>> teacherChapterMap=this.chapterBiz.searchTeachersChapterList(eduUserExtList);
		model.addAttribute("teacherChapterMap", teacherChapterMap);
	    //查询该教师所需要回答的全部问题数量
		Map<String,Map<String,Integer>> countQuestionMap=this.questionBiz.countQuestionMap(eduUserExtList);
		model.addAttribute("countQuestionMap", countQuestionMap);
		return "xjgl/user/tchDetail";
	}
	
	/**
	 * 新增教师
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editTeacher")
	public String editTeacher(String userFlow, Model model){
		if(StringUtil.isNotBlank(userFlow)){
			EduUserExt eduUserExt = eduUserBiz.readEduUserInfo(userFlow);
			model.addAttribute("eduUserExt", eduUserExt);
		}
		//查询学校
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
		sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
		model.addAttribute("orgList", orgList);
		return "xjgl/user/editTeacher";
	}
		
	/**
	 * 保存教师
	 * @param sysUser
	 * @param eduUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveStudentOrTeacher")
	@ResponseBody
	public String saveTeacher(SysUser sysUser, EduUser eduUser,String type, Model model){
		String userFlow = sysUser.getUserFlow();
		String checkResult;
		if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
			SysOrg sysOrg=this.orgBiz.readSysOrg(sysUser.getOrgFlow());
			if(sysOrg!=null){
				sysUser.setOrgName(sysOrg.getOrgName());
			}
		}
		if(StringUtil.isBlank(userFlow)){
			checkResult = checkUser(sysUser, null);
		}else{
			checkResult = checkUser(sysUser, userFlow);
		}
		if(!GlobalConstant.FLAG_Y.equals(checkResult)){
			return checkResult;
		}
		int result = eduUserBiz.saveUserAndRole(sysUser, eduUser,type);
		
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	
	
	/**
	 * 系统管理员-打开教师简介编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editTchIntro")
	public String editTchIntro(String userFlow,Model model){
	    Map<String,Object> paramMap=new HashMap<String, Object>();
	    paramMap.put("userFlow", userFlow);
	    //根据流水号查询该教师的详细信息
	    List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
	    if(eduUserExtList!=null && !eduUserExtList.isEmpty()){
	    	 EduUserExt eduUserExt=eduUserExtList.get(0);
	    	 model.addAttribute("eduUserExt", eduUserExt);
	    }
		return "xjgl/user/editIntro";
	}
	
	/**
	 * 系统管理员-保存教师简介
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveIntro")
	@ResponseBody
	public String saveIntro(EduUser eduUser){
	    EduUser user=this.eduUserBiz.readEduUser(eduUser.getUserFlow());
	    user.setIntro(eduUser.getIntro());
	    int result=this.eduUserBiz.addEduUser(eduUser);
	    if(result==GlobalConstant.ONE_LINE){
	    	return GlobalConstant.SAVE_SUCCESSED;
	    }
		return GlobalConstant.SAVE_FAIL;
	}
	
	
	/**
	 * 管理员-学分管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/creditInfo")
	public String searchCreditInfo(Integer currentPage,String condition,Model model){
		  SysUser sysUser=new SysUser();
		if(StringUtil.isBlank(condition)){
        	condition="";
        }
		String stuRoleFlow=InitConfig.getSysCfg("student_role_flow");
	    Map<String,Object> paramMap=new HashMap<String, Object>();
	    paramMap.put("condition", "%"+condition+"%");
	    paramMap.put("roleFlow", stuRoleFlow);
	    //验证当前登录者权限
        String role=checkRole();
        if(GlobalConstant.ADMIN_ROLE.equals(role)){
        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        	paramMap.put("sysUser", sysUser);
        }
        PageHelper.startPage(currentPage, 15);
	    List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
	    model.addAttribute("eduUserExtList", eduUserExtList);
	    Map<String,Map<String, Object>> searchStudentChooseCourseMap=this.studentCourseBiz.searchStudentChooseCourses(eduUserExtList);
		model.addAttribute("searchStudentChooseCourseMap", searchStudentChooseCourseMap);
		return "xjgl/user/creditInfo";
	}
	
	/**
	 * 管理员-学分详细	
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/creditDetail")
	public String searchCreditDetail(String userFlow,Model model){
		Map<String,Object> paramMap=new HashMap<String, Object>();
	    paramMap.put("userFlow", userFlow);
	    List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
	    if(eduUserExtList != null && !eduUserExtList.isEmpty()){
			EduUserExt eduUserExt = eduUserExtList.get(0);
			model.addAttribute("eduUserExt", eduUserExt);
		}
	    Map<String,Map<String, Object>> searchStudentChooseCourseMap=this.studentCourseBiz.searchStudentChooseCourses(eduUserExtList);
		model.addAttribute("searchStudentChooseCourseMap", searchStudentChooseCourseMap);
		return "xjgl/user/creditDetail";
	}
	
	/**
	 * 管理员-学生学习进度
	 * @param condition
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/scheduleInfo")
	public String scheduleInfo(Integer currentPage,String condition,Model model){
		    SysUser sysUser=new SysUser();
			if(StringUtil.isBlank(condition)){
	        	condition="";
	        }
			String stuRoleFlow=InitConfig.getSysCfg("student_role_flow");
		    Map<String,Object> paramMap=new HashMap<String, Object>();
		    //验证当前登录者权限
	        String role=checkRole();
	        if(GlobalConstant.ADMIN_ROLE.equals(role)){
	        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
	        	paramMap.put("sysUser", sysUser);
	        }
	        paramMap.put("role", "student");
		    paramMap.put("condition", "%"+condition+"%");
		    paramMap.put("roleFlow", stuRoleFlow);
		PageHelper.startPage(currentPage, 15);
	    List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduAndCourseList(paramMap);
	    model.addAttribute("eduUserExtList", eduUserExtList);
	    Map<String, Map<String, Map<String, Object>>> resultMap=this.courseScheduleBiz.searchStuSchedule(eduUserExtList);
	    model.addAttribute("resultMap", resultMap);
	    //查询用于制图的统计数据
	    SysOrg sysOrg=new SysOrg();
	    sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
	    List<SysOrg> orgList=this.orgBiz.searchSysOrg(sysOrg);
	    EduCourse course=new EduCourse();
	    List<EduCourse> courseList=this.courseBiz.searchCourseList(course);
	    Map<String, Map<String, Object>> orgAndCourseMap=this.courseScheduleBiz.searchCourseFinishInfoByOrg(orgList,courseList);
	    model.addAttribute("orgList", orgList);
	    model.addAttribute("courseList", courseList);
	    model.addAttribute("orgAndCourseMap", orgAndCourseMap);
		return "xjgl/user/scheduleInfo";
	}
	
	/**
	 * 管理员-学生学习进度
	 * @param condition
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/scheduleInfoChart")
	public String scheduleInfoChart(Model model){
		    SysUser sysUser=new SysUser();
			
			String stuRoleFlow=InitConfig.getSysCfg("student_role_flow");
		    Map<String,Object> paramMap=new HashMap<String, Object>();
		    //验证当前登录者权限
	        String role=checkRole();
	        List<SysOrg> orgList = new ArrayList<SysOrg>();
	        if(GlobalConstant.ADMIN_ROLE.equals(role)){
	        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
	        	paramMap.put("sysUser", sysUser);
	        	SysOrg sysOrg = this.orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
	        	orgList.add(sysOrg);
	        }else if(GlobalConstant.SYSTEM_ROLE.equals(role)){
	        	SysOrg sysOrg=new SysOrg();
	    	    sysOrg.setOrgTypeId(OrgTypeEnum.University.getId());
	    	    orgList = this.orgBiz.searchSysOrg(sysOrg);
	        }
	        paramMap.put("role", "student");
		    paramMap.put("roleFlow", stuRoleFlow);
	    //查询用于制图的统计数据
	    EduCourse course=new EduCourse();
	    List<EduCourse> courseList=this.courseBiz.searchCourseList(course);
	    Map<String, Map<String, Object>> orgAndCourseMap=this.courseScheduleBiz.searchCourseFinishInfoByOrg(orgList,courseList);
	    model.addAttribute("orgList", orgList);
	    model.addAttribute("courseList", courseList);
	    model.addAttribute("orgAndCourseMap", orgAndCourseMap);
		return "xjgl/user/chart/scheduleInfoChart";
	}
	
	/**
	 * 管理员-测验考试情况
	 * @param condition
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/testInfo")
	public String searchTestInfo(Integer currentPage,String condition,Model model){
		    SysUser sysUser=new SysUser();
			if(StringUtil.isBlank(condition)){
	        	condition="";
	        }
			String stuRoleFlow=InitConfig.getSysCfg("student_role_flow");
		    Map<String,Object> paramMap=new HashMap<String, Object>();
		    //验证当前登录者权限
	        String role=checkRole();
	        if(GlobalConstant.ADMIN_ROLE.equals(role)){
	        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
	        	paramMap.put("sysUser", sysUser);
	        }
	        paramMap.put("role", "student");
		    paramMap.put("condition", "%"+condition+"%");
		    paramMap.put("roleFlow", stuRoleFlow);
		    PageHelper.startPage(currentPage, 15);
	    List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduAndCourseList(paramMap);
	    model.addAttribute("eduUserExtList", eduUserExtList);

		return "xjgl/user/testInfo";
	}
	
	/**
	 * 管理员--课程概况
	 * @param schedule
	 * @return
	 */
	@RequestMapping(value="/courseInfo")
	public String courseInfo(String searchCondition,Model model){
		if(StringUtil.isBlank(searchCondition)){
			searchCondition="";
		}
		List<EduCourse> courseList = this.courseBiz.searchCourseByCondition(searchCondition);
		model.addAttribute("courseList", courseList);
		List<EduCourseChapter> chapterList = eduCourseChapterBiz.searchCourseChapterList(new EduCourseChapter(), null,null);
		//教师Map
		Map<String, List<SysUser>> teacherMap = new HashMap<String, List<SysUser>>();
		List<SysUser> teacherList=null;
		List<String> teacherFlowList=null;
		if(courseList!=null && !courseList.isEmpty()){
		   for(EduCourse course:courseList){
			   teacherList = new ArrayList<SysUser>();
			   teacherFlowList=new ArrayList<String>();
			   EduCourseExt eduCourseExt=this.courseBiz.searchOneWithChapters(course.getCourseFlow());
			   if(eduCourseExt!=null){
                 if(eduCourseExt.getChapterList()!=null && !eduCourseExt.getChapterList().isEmpty()){
                	 for(EduCourseChapter chapter:eduCourseExt.getChapterList()){
                	    SysUser user=this.userBiz.readSysUser(chapter.getTeacherId());                	    
                	    if(user!=null){
                	    	if(!teacherFlowList.contains(user.getUserFlow())){
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
		Map<String,List<String>> teacherFlowMap=new HashMap<String, List<String>>();
		Map<String, BigDecimal> scoreMap = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> scoreCountMap = new HashMap<String, BigDecimal>();
		if(chapterList != null && !chapterList.isEmpty()){
 			for(EduCourseChapter cc : chapterList){
				String courseFlow = cc.getCourseFlow();
				List<String> temp = teacherFlowMap.get(courseFlow);
				if(temp == null){
					temp = new ArrayList<String>();
				}
				temp.add(cc.getTeacherId());
				teacherFlowMap.put(courseFlow, temp);
				
				//章
				String parentChapterFlow = cc.getParentChapterFlow();
				if(parentChapterFlow == null){
					Integer count = parentChapterNullMap.get(courseFlow);
					if(count == null){ 
						count = 0;
					}
					count++;
					parentChapterNullMap.put(courseFlow, count);
				}else{//节
					Integer count = parentChapterNotNullMap.get(courseFlow);
					if(count == null){ 
						count = 0;
					}
					count++;
					parentChapterNotNullMap.put(courseFlow, count);
				}
				//评分
				if(StringUtil.isNotBlank(parentChapterFlow)){
					BigDecimal chapteScore = cc.getChapterScore();
					if(chapteScore==null){
						chapteScore=new BigDecimal(0);
					}
					if(chapteScore != null){
						BigDecimal tempScore = scoreMap.get(courseFlow);
						BigDecimal tempScoreCount = scoreCountMap.get(courseFlow);
						if(tempScoreCount != null && tempScore != null){
							tempScore = tempScore.multiply(tempScoreCount).add(chapteScore);
							tempScoreCount=tempScoreCount.add(new BigDecimal(1));
						}else{
							tempScoreCount = new BigDecimal(1);
							tempScore=new BigDecimal(0).add(chapteScore);
						}
						scoreCountMap.put(courseFlow,tempScoreCount);  
						scoreMap.put(courseFlow, tempScore.divide(tempScoreCount,1,BigDecimal.ROUND_HALF_UP));
					  
					}
				}
				
			}
			model.addAttribute("parentChapterNullMap", parentChapterNullMap);
			model.addAttribute("parentChapterNotNullMap", parentChapterNotNullMap);
			model.addAttribute("scoreMap", scoreMap);
		}
		
		
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
					if(studyStatusId.equals(EduStudyStatusEnum.Finish.getId())){
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
		return "xjgl/user/courseInfo";
	}
	
	/**
	 * 管理员--课程概况--图
	 * @param schedule
	 * @return
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
					if(studyStatusId.equals(EduStudyStatusEnum.Finish.getId())){
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
		return "xjgl/user/chart/courseInfoChart";
	}
	
	@RequestMapping(value="/system/schedule")
	public String schedule(EduCourseSchedule schedule){
		return "xjgl/user/system/schedule";
	}

	
	/**
	 * 管理员-学生审核
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/userList")
	public String systemUserList(Integer currentPage,String condition, String statusId, Model model){
		SysUser sysUser = new SysUser();
		if(StringUtil.isBlank(condition)){
        	condition="";
        }
        Map<String,Object> paramMap=new HashMap<String, Object>();
        String roleFlow=InitConfig.getSysCfg("student_role_flow");
        paramMap.put("roleFlow", roleFlow);
        paramMap.put("condition", "%"+condition+"%");
        //验证当前登录者权限
        String role=checkRole();
        if(GlobalConstant.ADMIN_ROLE.equals(role)){
        	sysUser.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());  	
        }
        if(StringUtil.isNotBlank(statusId)){
        	sysUser.setStatusId(statusId);
        }
        paramMap.put("sysUser", sysUser);
        PageHelper.startPage(currentPage, 15);
		List<EduUserExt> userList=this.eduUserBiz.searchEduUserForManage(paramMap);
		model.addAttribute("userList", userList);
		return "xjgl/user/userList";
	}
	
	/**
	 * 管理员-学生信息查看
	 * @param userFlow
	 * @param model
	 * @return
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
		return "xjgl/user/studentInfo";
	}
	
	/**
	 * 管理员--教师授课信息
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lessionInfo")
	public String lessionInfo(Integer currentPage,String condition,Model model){
		SysUser sysUser=new SysUser();
		if(StringUtil.isBlank(condition)){
        	condition = "";
        }
        String roleFlow = InitConfig.getSysCfg("teacher_role_flow");
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("condition", "%"+condition+"%");
        paramMap.put("roleFlow", roleFlow);
        paramMap.put("role", "teacher");
        //验证当前登录者权限
        String role=checkRole();
        if(GlobalConstant.ADMIN_ROLE.equals(role)){
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
			answeredCountMap.put(map.getKey(),answeredCount);		
		}
		model.addAttribute("highCountMap",highCountMap);
		model.addAttribute("lowCountMap",lowCountMap);
		model.addAttribute("praiseCountMap",praiseCountMap);
		model.addAttribute("answeredCountMap",answeredCountMap);
	    model.addAttribute("eduUserExtListChart", eduUserExtListChart);
		return "xjgl/user/lessonInfo";
	}
    
	/**
	 * 管理员--教师授课信息--图
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lessionInfoChart")
	public String lessionInfo(Model model){
		SysUser sysUser=new SysUser();
        String roleFlow = InitConfig.getSysCfg("teacher_role_flow");
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
			answeredCountMap.put(map.getKey(),answeredCount);		
		}
		model.addAttribute("highCountMap",highCountMap);
		model.addAttribute("lowCountMap",lowCountMap);
		model.addAttribute("praiseCountMap",praiseCountMap);
		model.addAttribute("answeredCountMap",answeredCountMap);
	    model.addAttribute("eduUserExtListChart", eduUserExtListChart);
		return "xjgl/user/chart/lessonInfoChart";
	}
	
	/**
	 * 我的收藏
	 * @param schedule
	 * @return
	 */
	@RequestMapping(value="/setCollection")
	public String setCollection(Model model){
		return "xjgl/course/collectionList";
	}
	
	/**
	 * 加载收藏
	 * @param model
	 * @return
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
				if(collectionTypeId.equals(EduCollectionTypeEnum.Course.getId())){
					List<EduCourse> courseList = courseBiz.searchCourseListByCourseFlowList(resourseFlowList);
					model.addAttribute("courseList", courseList);
				}
				if(collectionTypeId.equals(EduCollectionTypeEnum.Chapter.getId())){
					List<EduCourseChapter> chapterList = eduCourseChapterBiz.searchChapterListByChapterFlowList(resourseFlowList);
					if(chapterList != null && !chapterList.isEmpty()){
						List<String> userFlowList = new ArrayList<String>();
						for(EduCourseChapter ch : chapterList){
							userFlowList.add(ch.getTeacherId());
						}
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
		return "xjgl/course/collectionDetail";
	}
	
	@RequestMapping(value="/checkRole")
	@ResponseBody
	public String checkRole(){
		List<String> currRoleList=(List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST);
		String stuRoleFlow=InitConfig.getSysCfg("student_role_flow");
		String tchRoleFlow=InitConfig.getSysCfg("teacher_role_flow");
		String adminRoleFlow=InitConfig.getSysCfg("admin_role_flow");
		String systemRoleFlow=InitConfig.getSysCfg("system_role_flow");
		if(currRoleList!=null && !currRoleList.isEmpty()){
			if(currRoleList.contains(stuRoleFlow)){
				return GlobalConstant.STUDENT_ROLE;
			}else if(currRoleList.contains(tchRoleFlow)){
				return GlobalConstant.TEACHER_ROLE;
			}else if(currRoleList.contains(adminRoleFlow)){
				return GlobalConstant.ADMIN_ROLE;
			}else if(currRoleList.contains(systemRoleFlow)){
				return GlobalConstant.SYSTEM_ROLE;
			}else{
				return "";
			}
		}
		return null;
	}
	/**
	 * 上传头像
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/uploadImg")
	public @ResponseBody String uploadImg(MultipartFile imageFile){
		return this.eduUserBiz.uploadImg(imageFile);
	}
	
	
	
	//*********** 老师   ***************
	
	/**
	 * 最新通知（老师）
	 * @param courseFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notice")
	public String searchNotice(String courseFlow, Model model){
		EduCourseNotice courseNotice = new EduCourseNotice();
		courseNotice.setCourseFlow(courseFlow);
		List<EduCourseNoticeExt> eduCourseNoticeExtList = courseNoticeBiz.searchCourseNoticeListByCourseFlow(courseFlow);
		model.addAttribute("eduCourseNoticeExtList", eduCourseNoticeExtList);

		return "xjgl/user/noticeList";
	}
	
	/**
	 * 保存通知（老师）
	 * @param courseNotice
	 * @param model
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
	 */
	@RequestMapping(value="/chapterList")
	public String searchChapterList(String courseFlow, Model model){
		EduCourseExt courseExt = courseBiz.searchOneWithChapters(courseFlow);
		model.addAttribute("courseExt", courseExt);
		return "xjgl/user/chapterList";
	}
	
	/**
	 * 课程资料（教师）
	 * @param courseFlow
	 * @return
	 */
	@RequestMapping(value="/courseFile")
	public String searchCourseFile(EduCourseFile courseFile,Model model){
		List<EduCourseFile> courseFileList = courseFileBiz.searchCourseFileList(courseFile);
		model.addAttribute("courseFileList", courseFileList);
		return "xjgl/user/fileList";
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
		if(file.getSize() > 0){
			String checkResult = checkFile(file);
			if(checkResult != GlobalConstant.FLAG_Y){
				return checkResult;
			}
		}
		int result = courseFileBiz.operateFile(file, courseFlow);
		if(result != GlobalConstant.ZERO_LINE){
			return GlobalConstant.UPLOAD_SUCCESSED;
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	
	/**
	 * 检查文件大小及类型
	 * @param file
	 * @return
	 */
	private String checkFile(MultipartFile file) {
		// 检查文件大小及类型
		if(file.getSize() > 10*1024*1024){
			return GlobalConstant.VALIDATE_FILE_FAIL ;
		}
		return GlobalConstant.FLAG_Y;//可执行保存
	}
	
	/**
	 * 删除课程资料
	 * @param courseFlow
	 * @param model
	 * @return
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
	
	
	//*********** 学生   ***************
	
	/**
	 * 最新通知（学生）
	 * @param courseFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/studentNotice")
	public String studentNotice(String courseFlow, Model model){
		EduCourseNotice courseNotice = new EduCourseNotice();
		courseNotice.setCourseFlow(courseFlow);
		
		List<EduCourseNoticeExt> eduCourseNoticeExtList = courseNoticeBiz.searchCourseNoticeListByCourseFlow(courseFlow);
		model.addAttribute("eduCourseNoticeExtList", eduCourseNoticeExtList);
		return "xjgl/user/student/noticeList";
	}
	
	
	/**
	 * 课程章节（学生）
	 * @param courseFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/studentChapter")
	public String studentChapter(String courseFlow, Model model){
		EduCourseExt courseExt = courseBiz.searchOneWithChapters(courseFlow);
		model.addAttribute("courseExt", courseExt);
		return "xjgl/user/student/chapterList";
	}
	
	/**
	 * 课程资料（学生）
	 * @param courseFlow
	 * @return
	 */
	@RequestMapping(value="/studentCourseFile")
	public String studentCourseFile(EduCourseFile courseFile, Model model){
		List<EduCourseFile> courseFileList = courseFileBiz.searchCourseFileList(courseFile);
		model.addAttribute("courseFileList", courseFileList);
		return "xjgl/user/student/fileList";
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
	 * @return
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
	 * @return
	 */
	@RequestMapping(value="/tchMienList")
	public String searchTchMienList(Integer currentPage,Model model){
		
        String tchRoleFlow=InitConfig.getSysCfg("teacher_role_flow");
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("roleFlow", tchRoleFlow);
		List<EduUserExt> eduUserExtList=this.eduUserBiz.searchEduUserForManage(paramMap);
		model.addAttribute("eduUserExtList", eduUserExtList);
		return "xjgl/teacherMien/teacherList";
	}
	/**
	 * 教师风采-教师主页
	 * @param condition
	 * @param model
	 * @return
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

		return "xjgl/teacherMien/detail";
	}
	

	

	
	/**
	 * 保存eduUserForm
	 * @param eduUserForm
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/saveEduUser")
	@ResponseBody
	public String saveUserSchool(EduUserForm eduUserForm) throws Exception {
		
		SysUser user=eduUserForm.getSysUser();
		EduUser eduUser=eduUserForm.getEduUser();
		ResDoctor resDoctor=eduUserForm.getResDoctor();
		EduUserExtInfoForm extInfoForm=eduUserForm.getEduUserExtInfoForm();
		if(StringUtil.isNotBlank(eduUser.getStudentSourceId())){
			eduUser.setStudentSourceName(DictTypeEnum.StudentSource.getDictNameById(eduUser.getStudentSourceId()));
		}
		
		if(StringUtil.isNotBlank(eduUser.getAdmitTypeId())){
			eduUser.setAdmitTypeName(DictTypeEnum.AdmitType.getDictNameById(eduUser.getAdmitTypeId()));
		}
		if(StringUtil.isNotBlank(eduUser.getTrainTypeId())){
			eduUser.setTrainTypeName(DictTypeEnum.TrainType.getDictNameById(eduUser.getTrainTypeId()));
		}
		if(StringUtil.isNotBlank(eduUser.getMajorId())){
			eduUser.setMajorName(DictTypeEnum.Major.getDictNameById(eduUser.getMajorId()));
		}
		if(StringUtil.isNotBlank(eduUser.getStudyFormId())){
			eduUser.setStudyFormName(DictTypeEnum.StudyForm.getDictNameById(eduUser.getStudyFormId()));
		}
		if(StringUtil.isNotBlank(eduUser.getAtSchoolStatusId())){
			eduUser.setAtSchoolStatusName(DictTypeEnum.AtSchoolStatus.getDictNameById(eduUser.getAtSchoolStatusId()));
		}
		if(StringUtil.isNotBlank(eduUser.getSchoolRollStatusId())){
			eduUser.setSchoolRollStatusName(DictTypeEnum.SchoolRollStatus.getDictNameById(eduUser.getSchoolRollStatusId()));
		}
		if(StringUtil.isNotBlank(eduUser.getRecruitSeasonId())){
			eduUser.setRecruitSeasonName(DictTypeEnum.RecruitSeason.getDictNameById(eduUser.getRecruitSeasonId()));
		}
		if(StringUtil.isNotBlank(eduUser.getTrainCategoryId())){
			eduUser.setTrainCategoryName(DictTypeEnum.TrainCategory.getDictNameById(eduUser.getTrainCategoryId()));
		}
		if(StringUtil.isNotBlank(eduUser.getIsExceptionalId())){
			eduUser.setIsExceptionalName(DictTypeEnum.IsExceptional.getDictNameById(eduUser.getIsExceptionalId()));
		}
		if(StringUtil.isNotBlank(eduUser.getIsRecommendId())){
			eduUser.setIsRecommendName(DictTypeEnum.IsRecommend.getDictNameById(eduUser.getIsRecommendId()));
		}
		if(StringUtil.isNotBlank(extInfoForm.getFirstEducationContentId())){
			extInfoForm.setFirstEducationContentName(DictTypeEnum.FirstEducation.getDictNameById(extInfoForm.getFirstEducationContentId()));
		}
		if(StringUtil.isNotBlank(eduUser.getClassId())){
			eduUser.setClassName(DictTypeEnum.XjClass.getDictNameById(eduUser.getClassId()));
		}
		if(StringUtil.isNotBlank(user.getSexId())){
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		}
		if(StringUtil.isNotBlank(user.getCretTypeId())){
			user.setCretTypeName(CertificateTypeEnum.getNameById(user.getCretTypeId()));
		}
		if(StringUtil.isNotBlank(user.getNationId())){
			user.setNationName(UserNationEnum.getNameById(user.getNationId()));
		}
		if(StringUtil.isNotBlank(user.getPoliticsStatusId())){
			user.setPoliticsStatusName(PoliticsAppearanceEnum.getNameById(user.getPoliticsStatusId()));
		}
		if(StringUtil.isNotBlank(user.getMaritalId())){
			user.setMaritalName(MarriageTypeEnum.getNameById(user.getMaritalId()));
		}
		if(StringUtil.isNotBlank(eduUser.getAwardDegreeCategoryId())){
			eduUser.setAwardDegreeCategoryName(DictTypeEnum.DegreeCategory.getDictNameById(eduUser.getAwardDegreeCategoryId()));
		}
		
		if(StringUtil.isBlank(eduUser.getUserFlow())){
			EduUser edu=null;
			if(StringUtil.isNotBlank(eduUser.getSid())){
				edu = eduUserBiz.findBySid(eduUser.getSid());
				if(edu!=null){
					return GlobalConstant.USER_SID_REPETE;
				}		
			}
		}else{
			EduUser edu=null;
			String userFlow = eduUser.getUserFlow();
			if(StringUtil.isNotBlank(eduUser.getSid())){
				edu = eduUserBiz.findBySidNotSelf(userFlow, eduUser.getSid());
				if(edu!=null){
					return GlobalConstant.USER_SID_REPETE;
				}		
			}
		}
		user.setUserCode(eduUser.getSid());
		user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		user.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		eduUser.setTrainOrgId(user.getDeptFlow());
		if(StringUtil.isNotBlank(user.getDeptFlow())){
			SysDept dept=this.deptBiz.readSysDept(user.getDeptFlow());
			if(dept!=null){
				user.setDeptName(dept.getDeptName());
				eduUser.setTrainOrgName(dept.getDeptName());
			}
		}
		resDoctor.setSessionNumber(eduUser.getPeriod());
		resDoctor.setDoctorCode(eduUser.getSid());
		resDoctor.setTutorName(eduUser.getFirstTeacher());
		eduUser.setTrainOrgId(user.getDeptFlow());
		eduUser.setTrainOrgName(user.getDeptName());
		resDoctor.setDoctorCategoryId(RecDocCategoryEnum.Graduate.getId());
		resDoctor.setDoctorCategoryName(RecDocCategoryEnum.Graduate.getName());
		resDoctor.setDoctorLicenseNo(extInfoForm.getCodeDoctorQuaCert());
		resDoctor.setQualifiedNo(extInfoForm.getCodeMedicalPractitioner());
		resDoctor.setQualifiedFile(extInfoForm.getIsDoctorQuaCert());
		resDoctor.setDoctorLicenseFlag(extInfoForm.getIsMedicalPractitioner());
		resDoctor.setForeignSkills(extInfoForm.getForeignLanguageName());
		resDoctor.setComputerSkills(extInfoForm.getComputerLevel());
		
		int result=eduUserBiz.save(eduUserForm);            
		
		if(result!=GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	

	
	@RequestMapping(value="/userHeadImgUpload")
	@ResponseBody
	public String userHeadImgUpload(String userFlow,MultipartFile headImg){
		if(headImg!=null && headImg.getSize() > 0){             
			return userBiz.uploadImg(userFlow,headImg);
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value="/sysCfgUpdate")
	@ResponseBody
	public String sysCfgUpdate(String startCode,String startValue,String endCode,String endValue){
		SysCfg start=cfgBiz.read(startCode);
		start.setCfgValue(startValue);
		int startnum=cfgBiz.saveSysCfg(start, null);
		
		SysCfg end=cfgBiz.read(endCode);
		end.setCfgValue(endValue);
		int endnum =cfgBiz.saveSysCfg(null, end);
		if (startnum!=GlobalConstant.ONE_LINE && endnum!=GlobalConstant.ONE_LINE) {
			return GlobalConstant.SAVE_FAIL;
		}else{
			return GlobalConstant.SAVE_SUCCESSED;
		}
	}
	@RequestMapping(value="/delCourse")
	@ResponseBody
	public String delCourse(String recordFlow){
		int result=0;
		if(StringUtil.isNotBlank(recordFlow)){
			result=studentCourseBiz.deleteCourse(recordFlow);
		}
		if(GlobalConstant.ZERO_LINE==result){
			return GlobalConstant.DELETE_FAIL;
		}else{
			return GlobalConstant.DELETE_SUCCESSED;
		}
	}
	

	

	@RequestMapping("/auditUI")
	public String userAuditUI(String userFlow , Model model) throws Exception{
		//根据用户流水号查询用户注册时的角色
		List<SysUserRole> userRoleList = this.userRoleBiz.getByUserFlow(userFlow);
		if(userRoleList!=null && userRoleList.size()==1){
			SysUserRole userRole = userRoleList.get(0);
			String roleFlow = userRole.getRoleFlow();
			SysRole role = this.roleBiz.read(roleFlow);
			String regPageId = role.getRegPageId();
			//教育平台管理员审核
			SysUser user = this.userBiz.readSysUser(userFlow);
			EduUser eduUser = this.eduUserBiz.readEduUser(userFlow);
			model.addAttribute("user", user);
			model.addAttribute("eduUser", eduUser);
			return "sys/user/eduUserRegAudit";
		}
		return null;
	}
}
