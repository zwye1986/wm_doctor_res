package com.pinde.sci.ctrl.xjgl;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.res.XjPartStatusEnum;
import com.pinde.sci.enums.xjgl.ClassOrderEnum;
import com.pinde.sci.form.xjgl.XjStudentCourseForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.xjgl.XjEduScheduleClassExt;
import com.pinde.sci.model.xjgl.XjEduUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/xjgl/student/course")
public class XjglStudentCourseController extends GeneralController{
	@Autowired
	private IXjEduStudentCourseBiz studentCourseBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IXjEduCourseMajorBiz courseMajorBiz;
	@Autowired
	private IXjEduCourseBiz courseBiz;
	@Autowired
	private IXjEduUserBiz eduUserBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	@Autowired
	private IXjEduTermBiz eduTermBiz;
	@Autowired
	private IXjEduScheduleBiz scheduleBiz;
	@Autowired
	private XjglTermManageController termController;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	
	
	//**********************************学员****************************************
	
	/**
	 * 跳转至网上选课
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/interCourse")
	public String interCourse(String userFlow, Model model){
		SysCfg chooseCourseStartTimeCfg = cfgBiz.read("choose_course_start_time");
		SysCfg chooseCourseEndTimeCfg = cfgBiz.read("choose_course_end_time");
		model.addAttribute("chooseCourseStartTimeCfg", chooseCourseStartTimeCfg);
		model.addAttribute("chooseCourseEndTimeCfg", chooseCourseEndTimeCfg);
		if(StringUtil.isBlank(userFlow)){
			SysUser currUser =  GlobalContext.getCurrentUser();
			userFlow = currUser.getUserFlow();
		}
		EduUser eduUser = eduUserBiz.readEduUser(userFlow);
		model.addAttribute("eduUser", eduUser);
		SysUser sysUser = userBiz.readSysUser(userFlow);
		model.addAttribute("sysUser", sysUser);
		List<EduUserInfoStatus> statusLst = courseBiz.searchPartStatus(userFlow);
		if(null != statusLst && statusLst.size() > 0){
			String needStatus = null;
			String selectStatus = null;
			for(int i = 0; i < statusLst.size(); i++){
				if(XjPartStatusEnum.NeedInfo.getId().equals(statusLst.get(i).getPartId())){
					if(GlobalConstant.RECORD_STATUS_Y.equals(statusLst.get(i).getPartStatus())){
						needStatus = GlobalConstant.RECORD_STATUS_Y;
					}
				}
				if(XjPartStatusEnum.SelectInfo.getId().equals(statusLst.get(i).getPartId())){
					if(GlobalConstant.RECORD_STATUS_Y.equals(statusLst.get(i).getPartStatus())){
						selectStatus = GlobalConstant.RECORD_STATUS_Y;
					}
				}
			}
			model.addAttribute("choseCourseFlag",StringUtil.isNotBlank(needStatus) && StringUtil.isNotBlank(selectStatus));
		}
		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
			EduMajorCredit eduMajorCredit=new EduMajorCredit();
			if(eduUser!=null){
				eduMajorCredit.setMajorId(eduUser.getMajorId());
				eduMajorCredit.setTrainTypeId(eduUser.getTrainTypeId());
				List<EduMajorCredit> eduMajorCreditList=courseMajorBiz.searchMajorCreditList(eduMajorCredit);
				if(eduMajorCreditList!=null&&eduMajorCreditList.size()>0){
					model.addAttribute("eduMajorCredit",eduMajorCreditList.get(0));
				}
			}
			return "xjgl/student/interCourseOfGz";//广州医科大学
		}
		return "xjgl/student/interCourse";
	}
	
	/**
	 * 加载该年度该专业所有课程选择列表
	 * @param period
	 * @param majorId
	 * @param trainTypeId  培养层次（硕、博）
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/editCourseList")
	public String chooseCourseList(String period, String majorId, String trainTypeId, String userFlow, Model model){
		String planYear = period;
		List<XjEduCourseMajorExt> courseMajorExtList = courseMajorBiz.searchCourseMajorExtList(planYear, majorId, trainTypeId);
		List<XjEduCourseMajorExt> courseMajorExtList2 =new ArrayList<>();
		//去除所有重复的课程
		List<String> courseFlows = new ArrayList<>();
		if(courseMajorExtList != null && !courseMajorExtList.isEmpty()){
			for (XjEduCourseMajorExt xjEduCourseMajorExt:courseMajorExtList){
				String courseFlow = xjEduCourseMajorExt.getCourseFlow();
				if(!courseFlows.contains(courseFlow)){
					courseFlows.add(courseFlow);
					courseMajorExtList2.add(xjEduCourseMajorExt);
				}
			}
		}
		if(courseMajorExtList2 != null && !courseMajorExtList2.isEmpty()){
			model.addAttribute("majorId",majorId);
			model.addAttribute("trainTypeId",trainTypeId);
			model.addAttribute("courseMajorExtList", courseMajorExtList2);
			Map<String, Object> resultMap = courseMajorBiz.extractCourseMajorMap(period, courseMajorExtList2);
			model.addAttribute("resultMap", resultMap);
		}
		//查询学生信息，主要是为了学生的培养层次
		//学位课程中的课程层次与学生的培养层次一致时，默认全部选中
		EduUser eduUser = eduUserBiz.readEduUser(userFlow);
		model.addAttribute("eduUser", eduUser);
		//根据课程类别组织该用户所选课程对应的课程Map
		Map<String, Object> chooseCourseMap = studentCourseBiz.extractStudentCourseMapByCourseType(period, userFlow);
		model.addAttribute("chooseCourseMap", chooseCourseMap);
		//学籍必填信息和选填信息确认状态
		List<EduUserInfoStatus> statusLst = courseBiz.searchPartStatus(userFlow);
		if(null != statusLst && statusLst.size() > 0){
			for(int i = 0; i < statusLst.size(); i++){
				if(XjPartStatusEnum.NeedInfo.getId().equals(statusLst.get(i).getPartId())){
					model.addAttribute("NeedInfo",statusLst.get(i).getPartStatus());
				}
				if(XjPartStatusEnum.SelectInfo.getId().equals(statusLst.get(i).getPartId())){
					model.addAttribute("SelectInfo",statusLst.get(i).getPartStatus());
				}
			}
		}
		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
			SysCfg chooseCourseStartTimeCfg = cfgBiz.read("choose_course_start_time");
			SysCfg chooseCourseEndTimeCfg = cfgBiz.read("choose_course_end_time");
			model.addAttribute("chooseCourseStartTimeCfg", chooseCourseStartTimeCfg);
			model.addAttribute("chooseCourseEndTimeCfg", chooseCourseEndTimeCfg);
			return "xjgl/student/editCourseListOfGz";//广州医科大学
		}
		return "xjgl/student/editCourseList";
	}
	
	/**
	 * 加载学员已选课程列表
	 * @param studentPeriod
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/courseList")
	public String courseList(String studentPeriod, String userFlow, Model model){
		if(StringUtil.isNotBlank(studentPeriod) && StringUtil.isNotBlank(userFlow)){
			//该用户选课记录
			EduStudentCourse esc=new EduStudentCourse();
			esc.setStudentPeriod(studentPeriod);
			esc.setUserFlow(userFlow);
			esc.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<EduStudentCourse> studentCourseList = studentCourseBiz.searchStudentCourseList(esc);
			model.addAttribute("studentCourseList", studentCourseList);
			Map<String, List<EduStudentCourse>> studentCourseMap = new LinkedHashMap<String, List<EduStudentCourse>>();
			for(EduStudentCourse eduStudentCourse :studentCourseList){
				String courseTypeId = eduStudentCourse.getCourseTypeId();
				List<EduStudentCourse> tempList = studentCourseMap.get(courseTypeId);
				if(tempList == null){
					tempList = new ArrayList<EduStudentCourse>();
				}
				tempList.add(eduStudentCourse);
				studentCourseMap.put(courseTypeId, tempList);
			}
			model.addAttribute("studentCourseMap", studentCourseMap);
		}
		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
			return "xjgl/student/courseListOfGz";//广州医科大学
		}
		return "xjgl/student/courseList";
	}


	/**
	 * 保存选课、补选
	 * @param form
	 * @param studentPeriod
	 * @param userFlow
	 * @param replenishFlag
	 */
	@RequestMapping(value="/saveStudentCourse")
	@ResponseBody
	public String saveStudentCourse(@RequestBody XjStudentCourseForm form, String studentPeriod, String userFlow, String replenishFlag, String saveBeforeFlag) {
		List<EduStudentCourse> studentCourseList = form.getStudentCourseList();
		if(studentCourseList!=null && !studentCourseList.isEmpty()){
			EduCourse course=null;
			for(EduStudentCourse esc:studentCourseList){
				course=this.courseBiz.readCourse(esc.getCourseFlow());
				if(course!=null){
					esc.setCourseCode(course.getCourseCode());
					esc.setCourseName(course.getCourseName());
					esc.setCourseNameEn(course.getCourseNameEn());
				}
			}
		}
		int result = studentCourseBiz.saveStudentCourseByCourseFlowList(studentPeriod, userFlow, studentCourseList, replenishFlag,saveBeforeFlag);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	/**
	 * 跳转至补选
	 * @param period
	 * @param majorId
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/replenish")
	public String replenish(String period, String majorId, String trainTypeId, String userFlow, Model model){
		String planYear = period;
		List<XjEduCourseMajorExt> courseMajorExtList = courseMajorBiz.searchReplenishCourseMajorExtList(planYear, majorId, trainTypeId, userFlow);
		model.addAttribute("courseMajorExtList", courseMajorExtList);
		if(courseMajorExtList !=null && !courseMajorExtList.isEmpty()){
			Map<String, Object> resultMap = courseMajorBiz.extractCourseMajorMap(period, courseMajorExtList);
			model.addAttribute("resultMap", resultMap);
		}
		//学生的培养层次
		//学位课程中的课程层次与学生的培养层次一致时，默认全部选中
		model.addAttribute("trainTypeId", trainTypeId);
		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
			return "xjgl/student/replenishCourseOfGz";//广州医科大学
		}
		return "xjgl/student/replenishCourse";
	}

	//************************************** 平台  ***************************************************
	
	/**
	 * 选课管理
	 * @param sysUser
	 * @param eduUser
	 * @param currentPage
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/selectApproval")
	public String selectApproval(SysUser sysUser, EduUser eduUser,ResDoctor doctor,String studentPeriod, Integer currentPage, 
			HttpServletRequest request, Model model,String from){
		SysCfg start=cfgBiz.read("choose_course_start_time");
		SysCfg end=cfgBiz.read("choose_course_end_time");
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		PageHelper.startPage(currentPage, getPageSize(request));
		
		SysUser user=GlobalContext.getCurrentUser();
		if(GlobalConstant.USER_LIST_LOCAL.equals(from)){
			//培养单位
			doctor.setOrgFlow(user.getOrgFlow());
		}else if(GlobalConstant.USER_LIST_CHARGE.equals(from)){
			//分委会
			eduUser.setTrainOrgId(user.getDeptFlow());
		}


		List<XjEduUserExt> eduUserExtList = eduUserBiz.searchEduUserExtList(sysUser, eduUser, doctor);
		model.addAttribute("eduUserExtList", eduUserExtList);
		if(eduUserExtList != null && !eduUserExtList.isEmpty()){
			List<String> userFlowList = new ArrayList<String>();
			for (XjEduUserExt euE : eduUserExtList) {
				userFlowList.add(euE.getUserFlow());
			}
			List<EduStudentCourse> studentCourseList = studentCourseBiz.searchStudentCourseListByUserFlowList(userFlowList);
			if(studentCourseList != null && !studentCourseList.isEmpty()){
				Map<String, Map<String,Map<String, List<EduStudentCourse>>>> studentCourseMap = new LinkedHashMap<String, Map<String,Map<String, List<EduStudentCourse>>>>();
				Map<String,Integer> studentPeriodCountMap = new HashMap<String, Integer>();
				for(EduStudentCourse esc :studentCourseList){
					if(StringUtil.isNotBlank(studentPeriod)&&!esc.getStudentPeriod().equals(studentPeriod)){
						continue;
					}
					String userFlow = esc.getUserFlow();
					Map<String,Map<String, List<EduStudentCourse>>> periodUserCourseMap = studentCourseMap.get(userFlow);
					if(periodUserCourseMap == null){
						periodUserCourseMap = new HashMap<String,Map<String, List<EduStudentCourse>>>();
					}
					String sp = esc.getStudentPeriod();
					Map<String, List<EduStudentCourse>> courseMap = periodUserCourseMap.get(sp);
					if(courseMap == null){
						courseMap = new HashMap<String, List<EduStudentCourse>>();
					}
					String courseTypeId = esc.getCourseTypeId();
					List<EduStudentCourse> tempList = courseMap.get(courseTypeId);
					if(tempList == null){
						tempList = new ArrayList<EduStudentCourse>();
					}
					tempList.add(esc);
					courseMap.put(courseTypeId, tempList);
					periodUserCourseMap.put(sp, courseMap);
					studentCourseMap.put(userFlow, periodUserCourseMap);
					
					
					studentPeriodCountMap.put(userFlow, periodUserCourseMap.size());
				}
				model.addAttribute("studentCourseMap", studentCourseMap);
				model.addAttribute("studentPeriodCountMap", studentPeriodCountMap);
			}
		}
		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
			return "/xjgl/plat/selectApprovalOfGz";//广州医科大学
		}
		return "/xjgl/plat/selectApproval";
	}
	
	/**
	 * 跳转至课程维护
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/courseMaintain")
	public String courseMaintain(String period,String majorId,String userFlow, Model model){
		EduUser userTemp=new EduUser();
		if(StringUtil.isNotBlank(userFlow)){
			EduUser eduUser = eduUserBiz.readEduUser(userFlow);
			model.addAttribute("eduUser", eduUser);
			SysUser sysUser = userBiz.readSysUser(userFlow);
			model.addAttribute("sysUser", sysUser);
			model.addAttribute("period", period);
			userTemp=eduUser;
		}
		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
			EduMajorCredit eduMajorCredit=new EduMajorCredit();
			if(userTemp!=null){
				eduMajorCredit.setMajorId(userTemp.getMajorId());
				eduMajorCredit.setTrainTypeId(userTemp.getTrainTypeId());
				List<EduMajorCredit> eduMajorCreditList=courseMajorBiz.searchMajorCreditList(eduMajorCredit);
				if(eduMajorCreditList!=null&&eduMajorCreditList.size()>0){
					model.addAttribute("eduMajorCredit",eduMajorCreditList.get(0));
				}
			}
			return "xjgl/plat/courseMaintainOfGz";//广州医科大学
		}
		return "xjgl/plat/courseMaintain";
	}
	
	/**
	 * 加载课程维护选择列表
	 * @param period
	 * @param majorId
	 * @param userFlow
	 * @param model
	 */
	@RequestMapping(value="/editCourseMaintain")
	public String editCourseMaintain(String period, String majorId, String trainTypeId, String userFlow, String strideFlag, String courseName, String periodFlag,String periodYear,Model model) throws UnsupportedEncodingException {
		String planYear = period;
		if(StringUtil.isNotBlank(courseName)){
			courseName = new String(courseName.getBytes("ISO-8859-1"),"UTF-8");
		}
		List<XjEduCourseMajorExt> courseMajorExtList = null;
		List<XjEduCourseMajorExt> courseMajorExtList2 = new ArrayList<>();
		if(StringUtil.isNotBlank(strideFlag) && strideFlag.equals("checked")){
			model.addAttribute("strideFlag",strideFlag);
			if(StringUtil.isNotBlank(periodFlag) && periodFlag.equals("checked")){
				model.addAttribute("periodFlag",periodFlag);
				courseMajorExtList = courseMajorBiz.searchCourseMajorExtList(periodYear, "", "", courseName);//跨年级、专业及培养层次
			}else{
				courseMajorExtList = courseMajorBiz.searchCourseMajorExtList(planYear, "", "", courseName);//跨专业及培养层次
			}
			//去除所有重复的课程
			List<String> courseFlows = new ArrayList<>();
			if(courseMajorExtList != null && !courseMajorExtList.isEmpty()){
				for (XjEduCourseMajorExt xjEduCourseMajorExt:courseMajorExtList){
					String courseFlow = xjEduCourseMajorExt.getCourseFlow();
					if(!courseFlows.contains(courseFlow)){
						courseFlows.add(courseFlow);
						courseMajorExtList2.add(xjEduCourseMajorExt);
					}
				}
			}
			model.addAttribute("courseMajorExtList", courseMajorExtList2);
		}else{
			if(StringUtil.isNotBlank(periodFlag) && periodFlag.equals("checked")){
				model.addAttribute("periodFlag",periodFlag);
				courseMajorExtList = courseMajorBiz.searchCourseMajorExtList(periodYear, majorId, trainTypeId, courseName);
			}else{
				courseMajorExtList = courseMajorBiz.searchCourseMajorExtList(planYear, majorId, trainTypeId, courseName);
			}
				//去除所有重复的课程
				List<String> courseFlows = new ArrayList<>();
				if(courseMajorExtList != null && !courseMajorExtList.isEmpty()){
					for (XjEduCourseMajorExt xjEduCourseMajorExt:courseMajorExtList){
						String courseFlow = xjEduCourseMajorExt.getCourseFlow();
						if(!courseFlows.contains(courseFlow)){
							courseFlows.add(courseFlow);
							courseMajorExtList2.add(xjEduCourseMajorExt);
						}
					}
				}
			model.addAttribute("courseMajorExtList", courseMajorExtList);
		}
		if(courseMajorExtList2 != null && !courseMajorExtList2.isEmpty()){
			if(StringUtil.isNotBlank(strideFlag) && strideFlag.equals("checked")){
				List<String> strLst = new ArrayList<>();
				List<XjEduCourseMajorExt> courseLst = courseMajorBiz.searchCourseMajorExtList(planYear, majorId, trainTypeId, courseName);
				if(null != courseLst && courseLst.size() > 0){
					for (XjEduCourseMajorExt obj :courseLst){
						String courseTypeId = obj.getCourseTypeId();
						if(StringUtil.isNotBlank(courseTypeId) && (courseTypeId.equals("Degree") || courseTypeId.equals("Optional") || courseTypeId.equals("OptionalNeed")
								|| courseTypeId.equals("Public") || courseTypeId.equals("PublicNeed"))){//只支持学位、专业选修、公共选修课程
							strLst.add(obj.getCourseFlow());
						}
					}
				}
				List<Integer> indexLst = new ArrayList<>();//存储该学员本专业本培养层次 或者 跨该学生本专业本培养层次但课程相同 的索引
				for (int i = 0; i < courseMajorExtList2.size(); i++) {
					XjEduCourseMajorExt obj = courseMajorExtList2.get(i);
					if((null != majorId && null !=trainTypeId && majorId.equals(obj.getMajorId()) && trainTypeId.equals(obj.getCourse().getGradationId())) || strLst.contains(obj.getCourseFlow())){
						indexLst.add(i);
					}
				}
				for (int j = courseMajorExtList2.size()-1; j >= 0; j--) {
					for (int i = 0; i < indexLst.size(); i++) {
						if(indexLst.get(i) == j){
							courseMajorExtList2.remove(j);//去除该学员本专业本培养层次的课程
						}
					}
				}
			}
			Map<String, Object> resultMap = courseMajorBiz.extractCourseMajorMap(period, courseMajorExtList2);
			model.addAttribute("resultMap", resultMap);
		}
		//查询学生信息，主要是为了学生的培养层次
		//学位课程中的课程层次与学生的培养层次一致时，默认全部选中
		EduUser eduUser = eduUserBiz.readEduUser(userFlow);
		model.addAttribute("eduUser", eduUser);
		//根据课程类别组织该用户所选课程对应的课程Map
		Map<String, Object> chooseCourseMap = studentCourseBiz.extractStudentCourseMapByCourseType(period, userFlow);
		model.addAttribute("chooseCourseMap", chooseCourseMap);
		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
			return "xjgl/plat/editCourseMaintainOfGz";//广州医科大学
		}
		return "xjgl/plat/editCourseMaintain";
	}
	
	/**
	 * 保存课程维护
	 * @param studentCourse
	 * @param
	 * @param model
	 */
	@RequestMapping(value="/saveCourseMaintain")
	@ResponseBody
	public String saveCourseMaintain(EduStudentCourse studentCourse, Model model){
		int result = studentCourseBiz.saveCourseMaintain(studentCourse);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	/**
	 * 保存学位课程选择
	 * @param studentCourse
	 */
	@RequestMapping(value="/saveDegreeCourse")
	@ResponseBody
	public String saveDegreeCourse(EduStudentCourse studentCourse){
		int result = studentCourseBiz.saveDegreeCourse(studentCourse);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	@RequestMapping(value="/reOpenChooseCourse")
	@ResponseBody
	public String reOpenChooseCourse(String userFlow, Model model){
		int result = studentCourseBiz.reOpenChooseCourse(userFlow);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	@RequestMapping(value="/choseCourse")
	public String choseCourse(String userFlow,String classId,String trainTypeId,String majorId){
		return "xjgl/student/choseCourse";
	}
	@RequestMapping(value="/loadWeek")
	@ResponseBody
	public List<Integer> loadWeek(String year,String termSeason,String classId,String trainTypeId) throws ParseException {
		EduTerm eduTerm = new EduTerm();
		eduTerm.setSessionNumber(year);
		eduTerm.setGradeTermId(termSeason);
		eduTerm.setClassId(classId);
		eduTerm.setGradationId(trainTypeId);
		List<EduTerm> termLst = eduTermBiz.seachlistByTerm(eduTerm);
		List<Integer> weekNumLst  = new ArrayList();
		if(null != termLst && termLst.size() > 0){
			String termStartTime = termLst.get(0).getTermStartTime();
			String termEndTime = termLst.get(0).getTermEndTime();
			weekNumLst = termController.getWeekNum(termStartTime,termEndTime);
		}
		return weekNumLst;
	}
	private static boolean customerCfg(){
		return "gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"));
	}
	@RequestMapping(value="/searchScheduleClass")
	public String searchScheduleClass(String year,String termSeason,String classId,String trainTypeId,String majorId,String userFlow,String weekNumber,Model model) {
		EduTerm eduTerm = new EduTerm();
		eduTerm.setSessionNumber(year);//年份
		eduTerm.setGradeTermId(termSeason);//学期
		eduTerm.setClassId(classId);//班级
		eduTerm.setGradationId(trainTypeId);//培养层次
		List<EduTerm> termLst = eduTermBiz.seachlistByTerm(eduTerm);
		if (null != termLst && termLst.size() > 0) {
			Map<String, Object> param = new HashMap<>();
			Map<String, Object> weekNuMap = new HashMap<>();
			param.put("term", termLst.get(0));
			param.put("weekNumber",weekNumber);
			param.put("year",year);
			param.put("majorId",majorId);
			param.put("userFlow",userFlow);
			termController.setSearchTime(termLst.get(0), param);

			List<Map<String,String>> maps = new ArrayList<>();
			if(customerCfg()){
				maps = eduTermBiz.getFirstClassOfGzByFlow(termLst.get(0).getRecordFlow());
			}else{
				maps = eduTermBiz.getFirstClassByFlow(termLst.get(0).getRecordFlow());
			}
			Map<String,Object> firstClassMap=new HashMap<>();
			if(null != maps && maps.size() > 0) {
				for(Map<String,String> map:maps) {
					map.put("classOrder",getClassOrderIdByName(map.get("classOrder")));
					String key =map.get("courseCode")+"_"+map.get("classTime")+"_"+map.get("classOrder");
					firstClassMap.put(key,map);
				}
			}
			model.addAttribute("firstClassMap",firstClassMap);

			param.put("termSeason",termSeason);
			List<EduScheduleStudent> schStuLst = scheduleBiz.getChosedClass(param);
			Map<String,Object> chosedClassMap=new HashMap<>();
			if(null != schStuLst && schStuLst.size() > 0) {
				for(EduScheduleStudent obj : schStuLst) {
					String key =obj.getCourseCode()+"_"+obj.getClassTime()+"_"+getClassOrderIdByName(obj.getClassOrder());//同一课程不可能在同一天同一堂课 上
					chosedClassMap.put(key,obj.getScheduleClassFlow());
				}
			}
			model.addAttribute("chosedClassMap",chosedClassMap);

			Map<String, Object> info = new HashMap<>();
			List<XjEduScheduleClassExt> list = scheduleBiz.seachStudentClassByMap(param);
			if (list != null && list.size() > 0) {
				for (XjEduScheduleClassExt b : list) {
					String key = b.getClassTime() + "_" + getClassOrderIdByName(b.getClassOrder());
					List<XjEduScheduleClassExt> list2 = (List<XjEduScheduleClassExt>) info.get(key);
					if (list2 == null) {
						list2 = new ArrayList<>();
						info.put(key, list2);
					}
					list2.add(b);
				}
			}
			//页面课表信息
			weekNuMap = termController.getWeekMap2(param);
			model.addAttribute("weekNuMap", weekNuMap);
			model.addAttribute("info", info);
			model.addAttribute("termCourseFlag",true);
		}
		return "xjgl/student/choseCourseInfo";
	}

	@RequestMapping(value="/submitCourse")
	@ResponseBody
	public String submitCourse(String userFlow,String recordFlow,String status,String flag,Model model) {
		if(!"true".equals(flag)) {
			if (GlobalConstant.FLAG_Y.equals(status)) {
				boolean f = scheduleBiz.isChosedCourse(recordFlow, userFlow);
				if (f) {
					return "该堂课已经选择了学习课程！";
				}
			}
		}
		int result = scheduleBiz.submitScheduleCourse(recordFlow,userFlow,status);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	public  String getClassOrderIdByName(String name){
		String orderId = "";
		for (ClassOrderEnum e : ClassOrderEnum.values()) {
			if (e.getName().equals(name)) {
				orderId = e.getId();
			}
		}
		return orderId;
	}

	//学员总分查询
	@RequestMapping("/getScoreSum")
	public String getScoreSum(EduUser eduUser,String scoreSum,String scoreSumGe,String scoreSumLe,String userName,String courseName,String orgFlow,Model model,HttpServletRequest request,Integer currentPage){
		SysUser user = GlobalContext.getCurrentUser();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("period",eduUser.getPeriod());
		paramMap.put("className",eduUser.getClassName());
		paramMap.put("trainTypeId",eduUser.getTrainTypeId());
		paramMap.put("sid",eduUser.getSid());
		paramMap.put("majorId",eduUser.getMajorId());
		paramMap.put("scoreSum",scoreSum);
		paramMap.put("userName",userName);
		paramMap.put("courseName",courseName);
		paramMap.put("trainCategoryId",eduUser.getTrainCategoryId());
		paramMap.put("trainOrgId",eduUser.getTrainOrgId());
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("scoreSumGe",scoreSumGe);
		paramMap.put("scoreSumLe",scoreSumLe);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> studentList = studentCourseBiz.getScoreSum(paramMap);
		model.addAttribute("studentList",studentList);
		List<SysOrg> orgList = orgBiz.searchTrainOrgList();
		model.addAttribute("orgList", orgList);
		String orgFlowSearch = user.getOrgFlow();
		List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlowSearch);
		model.addAttribute("deptList", deptList);
		return "xjgl/plat/getScoreSum";
	}

	//学员总分查询
	@RequestMapping("/courseDetail")
	public String courseDetail(String nameStr, String periodStr, String creditStr, Model model) throws IOException{
		model.addAttribute("nameStr",java.net.URLDecoder.decode(nameStr,"UTF-8"));
		model.addAttribute("periodStr",java.net.URLDecoder.decode(periodStr,"UTF-8"));
		model.addAttribute("creditStr",java.net.URLDecoder.decode(creditStr,"UTF-8"));
		return "xjgl/plat/courseDetail";
	}
	//成绩统计查询导出
	@RequestMapping("/exportScoreSum")
	public void exportScoreSum(EduUser eduUser,String scoreSum,String scoreSumGe,String scoreSumLe,String userName,String courseName,String orgFlow,HttpServletResponse response) throws Exception{
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("period",eduUser.getPeriod());
		paramMap.put("className",eduUser.getClassName());
		paramMap.put("trainTypeId",eduUser.getTrainTypeId());
		paramMap.put("sid",eduUser.getSid());
		paramMap.put("majorId",eduUser.getMajorId());
		paramMap.put("scoreSum",scoreSum);
		paramMap.put("userName",userName);
		paramMap.put("courseName",courseName);
		paramMap.put("trainCategoryId",eduUser.getTrainCategoryId());
		paramMap.put("trainOrgId",eduUser.getTrainOrgId());
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("scoreSumGe",scoreSumGe);
		paramMap.put("scoreSumLe",scoreSumLe);
		List<Map<String,Object>> studentList = studentCourseBiz.getScoreSum(paramMap);
		String[] titles = new String[]{//基本信息
				"PERIOD:入学年级",
				"CLASS_NAME:班级",
				"TRAIN_TYPE_NAME:培养层次",
				"TRAIN_CATEGORY_NAME:培养类型",
				"SID:学号",
				"USER_NAME:姓名",
				"SEX_NAME:性别",
				"MAJOR_ID:专业代码",
				"MAJOR_NAME:专业",
				"ORG_NAME:培养单位",
				"SCORESUM:总学分"
		};
		String fileName = "成绩统计.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, studentList, response.getOutputStream());
	}
}
