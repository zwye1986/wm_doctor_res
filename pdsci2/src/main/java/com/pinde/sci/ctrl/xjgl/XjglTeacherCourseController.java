package com.pinde.sci.ctrl.xjgl;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.jsres.UserInfoExtForm;
import com.pinde.sci.form.xjgl.XjEduUserForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/xjgl/teacher")
public class XjglTeacherCourseController extends GeneralController {
	@Autowired
	private IXjEduCourseBiz eduCourseBiz;
	@Autowired
	private IXjEduTermBiz eduTermBiz;
	@Autowired
	private IXjEduScheduleBiz eduScheduleBiz;
	@Autowired
	private IXjEduUserBiz eduUserBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IXjEduCourseMajorBiz eduCourseMajorBiz;

	@RequestMapping(value = "/courseList")
	public String courseList(Model model, Integer currentPage, HttpServletRequest request, EduCourse course){
		PageHelper.startPage(currentPage,getPageSize(request));
		List<EduCourse> coursesList=eduCourseBiz.searchCourseList(course);
		model.addAttribute("coursesList",coursesList);
		List<EduCourse> records=eduCourseBiz.searchCourseList(new EduCourse());
		Map<String,String> orgMap = new HashMap<String, String>();
		for(EduCourse temp : records){
			if(StringUtil.isNotBlank(temp.getAssumeOrgFlow())){
				orgMap.put(temp.getAssumeOrgFlow(), temp.getAssumeOrgName());
			}
		}
		model.addAttribute("orgMap",orgMap);
		return "xjgl/plat/courseInfoTeacher";
	}
	private static boolean customerCfg(){
		return "gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"));
	}
	@RequestMapping(value = "/teacherSchedule")
	public String searchTeacherSchedule (Model model,String courseSession,String termStartTime,String termEndTime,String classId,
								  String gradeTermId,String assumeOrgFlow,String classTeacherName)throws ParseException {
		List<EduCourse> records=eduCourseBiz.searchCourseList(new EduCourse());
		Map<String,String> orgMap = new HashMap<String, String>();
		for(EduCourse temp : records){
			if(StringUtil.isNotBlank(temp.getAssumeOrgFlow())){
				orgMap.put(temp.getAssumeOrgFlow(), temp.getAssumeOrgName());
			}
		}
		model.addAttribute("orgMap",orgMap);
		//设置默认年份和学期
		String year = DateUtil.getYear();
		model.addAttribute("year",year);
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH)+1;
		String grade = "";
		if(month<=7&&month>=2){
			grade = "spring";
		}else{
			grade = "autumn";
		}
		model.addAttribute("grade",grade);
		if(StringUtil.isBlank(gradeTermId)){
			gradeTermId = grade;
		}
		if(StringUtil.isBlank(courseSession)){
			courseSession = year;
		}
		//设置默认年份和学期完毕
		//设置默认查课时间
		String startDate0 = DateUtil.getCurrDate();
		model.addAttribute("startDate",startDate0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(startDate0);
		Calendar c0 = Calendar.getInstance();
		c0.setTime(date);
		c0.add(Calendar.DATE,7);
		Date end = c0.getTime();
		String endDate0 = sdf.format(end);
		model.addAttribute("endDate",endDate0);
		if(StringUtil.isBlank(termStartTime)){
			termStartTime = startDate0;
		}
		if (StringUtil.isBlank(termEndTime)){
			termEndTime = endDate0;
		}
		//设置默认查课完毕
		//获得起始日期和之后两周之间的所有日期 并且补齐头尾周，同时获取下一页的头日期和上一页头日期
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		Calendar next =  Calendar.getInstance();
		Calendar before = Calendar.getInstance();
			Date startDate3 = sdf.parse(termStartTime);
			startCalendar.setFirstDayOfWeek(customerCfg()?Calendar.MONDAY:Calendar.SUNDAY);
			startCalendar.setTime(startDate3);
			startCalendar.set(Calendar.DAY_OF_WEEK, customerCfg()?Calendar.MONDAY:Calendar.SUNDAY);
			Date start0 = startCalendar.getTime();
			String start2 = sdf.format(start0);

			before.setTime(start0);
			before.add(Calendar.DAY_OF_MONTH, -8);
			String beforeDay = sdf.format(before.getTime());
			model.addAttribute("beforeDay",beforeDay);
		    before.add(Calendar.DAY_OF_MONTH,7);
			String beforeDay2 = sdf.format(before.getTime());
			model.addAttribute("beforeDay2",beforeDay2);

			endCalendar.setTime(startDate3);
			endCalendar.add(Calendar.DATE,7);
			endCalendar.setFirstDayOfWeek(customerCfg()?Calendar.MONDAY:Calendar.SUNDAY);
			endCalendar.set(Calendar.DAY_OF_WEEK, customerCfg()?Calendar.SUNDAY:Calendar.SATURDAY);
			Date end0 = endCalendar.getTime();
			String end2 = sdf.format(end0);

			next.setTime(end0);
			next.add(Calendar.DATE,1);
			String nextDay = sdf.format(next.getTime());
			model.addAttribute("nextDay",nextDay);
			next.add(Calendar.DATE,7);
			String nextDay2 = sdf.format(next.getTime());
			model.addAttribute("nextDay2",nextDay2);

		List<String> days = new ArrayList();
		while(true) {
			if (startCalendar.compareTo(endCalendar) <= 0) {
				days.add(sdf.format(startCalendar.getTime()));
				startCalendar.add(Calendar.DAY_OF_MONTH, 1);
			} else {
				break;
			}
		}
		model.addAttribute("days",days);
		//获取完毕
		//获取日期对应的星期几的MAP
		Map<String,String> weekMaps = new HashMap<>();
		Calendar c = Calendar.getInstance();
		String dayForWeek="";
		for(String day:days){
			c.setTime(sdf.parse(day));
			int i = c.get(Calendar.DAY_OF_WEEK) - 1;
			switch (i){
				case 0:{dayForWeek="日";break;}
				case 1:{dayForWeek="一";break;}
				case 2:{dayForWeek="二";break;}
				case 3:{dayForWeek="三";break;}
				case 4:{dayForWeek="四";break;}
				case 5:{dayForWeek="五";break;}
				case 6:{dayForWeek="六";break;}
			}
			weekMaps.put(day,dayForWeek);
		}
		model.addAttribute("weekMaps",weekMaps);
		//星期几MAP获取完毕
		//获取日期对应的周次MAP
		List<EduTerm> eduTerms= eduTermBiz.searchBySessionAndGrade(courseSession,gradeTermId);
		if(eduTerms.size()>0){
			String startTime = eduTerms.get(0).getTermStartTime();
			String endTime = eduTerms.get(0).getTermEndTime();
			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);
			Calendar termStartCalendar = Calendar.getInstance();
			Calendar termEndCalendar = Calendar.getInstance();
			termStartCalendar.setFirstDayOfWeek(customerCfg()?Calendar.MONDAY:Calendar.SUNDAY);
			termStartCalendar.setTime(startDate);
			termStartCalendar.set(Calendar.DAY_OF_WEEK, customerCfg()?Calendar.MONDAY:Calendar.SUNDAY);
			termEndCalendar.setFirstDayOfWeek(customerCfg()?Calendar.MONDAY:Calendar.SUNDAY);
			termEndCalendar.setTime(endDate);
			termEndCalendar.set(Calendar.DAY_OF_WEEK, customerCfg()?Calendar.SUNDAY:Calendar.SATURDAY);
			List<String> allDays = new ArrayList();
			while(true) {
				if (termStartCalendar.compareTo(termEndCalendar) <= 0) {
					allDays.add(sdf.format(termStartCalendar.getTime()));
					termStartCalendar.add(Calendar.DAY_OF_MONTH, 1);
				} else {
					break;
				}
			}



			Map<String,String> allWeekMaps = new HashMap<>();
			Calendar cl = Calendar.getInstance();
			String allDayForWeek="";
			for(String day:allDays){
				cl.setTime(sdf.parse(day));
				int j = cl.get(Calendar.DAY_OF_WEEK) - 1;
				switch (j){
					case 0:{allDayForWeek="日";break;}
					case 1:{allDayForWeek="一";break;}
					case 2:{allDayForWeek="二";break;}
					case 3:{allDayForWeek="三";break;}
					case 4:{allDayForWeek="四";break;}
					case 5:{allDayForWeek="五";break;}
					case 6:{allDayForWeek="六";break;}
				}
				allWeekMaps.put(day,allDayForWeek);
			}
			Map<String,Integer> weekOrderMaps = new HashMap<>();
			int weekOrder = 1;
			for(String day:allDays){
				weekOrderMaps.put(day,weekOrder);
				if(allWeekMaps.get(day).equals(customerCfg()?"日":"六")){
					weekOrder++;
				}
			}
			model.addAttribute("weekOrderMaps",weekOrderMaps);
			//周次MAP获取完毕
		}
		//页面课表信息
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("termStartTime",start2);
		paramMap.put("termEndTime",end2);
		paramMap.put("courseSession",courseSession);
		paramMap.put("gradeTermId",gradeTermId);
		paramMap.put("assumeOrgFlow", assumeOrgFlow);
		paramMap.put("classTeacherName", classTeacherName);
		paramMap.put("classId", classId);
		List<Map<String, String>> list0 = eduTermBiz.searchTeachersClassSchedule(paramMap);
		List<Map<String,String>> list = new ArrayList<>();
		List<String> flows = new ArrayList<String>();
		if(list0!=null&&list0.size()>0){
			for(Map<String,String> map : list0){
				String flow = map.get("recordFlow");
				if(!flows.contains(flow)){
					list.add(map);
					flows.add(flow);
				}
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> map:list){
				String key=map.get("classTime")+map.get("classOrder");
				List<Map<String, String>> list2= (List<Map<String,String>>)resultMap.get(key);
				if(list2==null)
				{
					list2=new ArrayList<>();
					resultMap.put(key,list2);
				}
				list2.add(map);
			}
		}
		model.addAttribute("resultMap",resultMap);
		//页面信息获取完毕
		return "xjgl/plat/courseScheduleTeacher";
	}

	@RequestMapping(value = "/details")
	public String details(String recordFlow,String courseMajorId,String count,Model model){
		model.addAttribute("recordFlow",recordFlow);
		//String courseMajorName = DictTypeEnum.CourseMajor.getDictNameById(courseMajorId);
		EduScheduleClass scheduleClass = eduScheduleBiz.readByRecordFlow(recordFlow);
		String courseFlow = scheduleClass.getCourseFlow();
		List<EduCourseMajor> courseMajors = eduCourseMajorBiz.searchCoursesBycourseFlow(courseFlow);
		List courseMajorNames = new ArrayList();
		if(courseMajors!=null&&courseMajors.size()>0){
			for(EduCourseMajor courseMajor:courseMajors){
				String majorName = courseMajor.getMajorName();
				courseMajorNames.add(majorName);
			}
			model.addAttribute("courseMajorNames",courseMajorNames);
		}
		String classroomName = scheduleClass.getClassroomName();
		model.addAttribute("classroomName",classroomName);
		String classCourseName = scheduleClass.getClassCourseName();
		model.addAttribute("classCourseName",classCourseName);
		String studentMaxmun = scheduleClass.getStudentMaxmun();
		model.addAttribute("studentMaxmun",studentMaxmun);
		model.addAttribute("count",count);
		List<EduScheduleTeacher> scheduleTeachers = eduScheduleBiz.readByClassFlow(recordFlow);
		if(scheduleTeachers!=null&&scheduleTeachers.size()>0){
			List<String> teacherNames = new ArrayList<>();
			for(EduScheduleTeacher scheduleTeacher:scheduleTeachers){
				String classTeacherName =scheduleTeacher.getClassTeacherName();
				teacherNames.add(classTeacherName);
			}
			model.addAttribute("teacherNames",teacherNames);
		}
		return "xjgl/plat/courseScheduleDetails";
	}

	@RequestMapping(value = "/exportStudents")
	public void exportStudents(String recordFlow,HttpServletResponse response) throws Exception {
		String classCourseName = eduScheduleBiz.readByRecordFlow(recordFlow).getClassCourseName();
		String headLines[] = {classCourseName+"学生名单"};
		String[] titles = new String[]{
				"eduUser.sid:学号",
				"sysUser.userName:姓名",
				"eduUser.majorName:专业"
		};
		List<XjEduUserForm> eduUserForms = new ArrayList<XjEduUserForm>();
		List<EduScheduleStudent> scheduleStudents = eduScheduleBiz.readStudentsByClassFlow(recordFlow);
		if(scheduleStudents!=null&&scheduleStudents.size()>0){
			for(EduScheduleStudent scheduleStudent:scheduleStudents){
				String userFlow = scheduleStudent.getUserFlow();
				EduUser eduUser = eduUserBiz.readEduUser(userFlow);
				SysUser sysUser = userBiz.readSysUser(userFlow);
				XjEduUserForm eduUserForm = new XjEduUserForm();
				eduUserForm.setEduUser(eduUser);
				eduUserForm.setSysUser(sysUser);
				eduUserForms.add(eduUserForm);
			}
		}
		String fileName = classCourseName+"学生名单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, eduUserForms, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
}
