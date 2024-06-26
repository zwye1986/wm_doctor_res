package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.IXjEduCourseBiz;
import com.pinde.sci.biz.xjgl.IXjEduCourseMajorBiz;
import com.pinde.sci.biz.xjgl.IXjEduStudentCourseBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduStudentCourseExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/xjgl/course/manage")
public class XjglCourseManageController extends GeneralController{
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IXjEduCourseBiz eduCourseBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	@Autowired
	private IXjEduStudentCourseBiz studentCourseBiz;
	@Autowired
	private IXjEduCourseMajorBiz courseMajorBiz;
	@Autowired
	private IOrgBiz orgBiz;

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
	
	@RequestMapping(value="/courseMajor")
	public String courseMajor(String planYear,String assumeOrgFlow, String courseFlow, Model model){
		String curryear=DateUtil.getCurrDateTime("yyyy");
		if (StringUtil.isBlank(planYear)) {
			planYear = curryear;
		}
		model.addAttribute("planYear", planYear);

		EduCourse eduCourseParam=new EduCourse();
		eduCourseParam.setCourseSession(planYear);
		List<EduCourse> recordList1 = eduCourseBiz.searchCourseList(eduCourseParam);
		List<EduCourse> recordList = eduCourseBiz.searchCourseList(new EduCourse());
		Map<String,String> assumeOrgFlowMap = new HashMap<String, String>();
		Map<String,List<EduCourse>> orgCourseMap = new HashMap<String, List<EduCourse>>();
		for(EduCourse course:recordList){
			if (StringUtil.isNotBlank(course.getAssumeOrgFlow())) {
				assumeOrgFlowMap.put(course.getAssumeOrgFlow(), course.getAssumeOrgName());
			}

//			List<EduCourse> temp = orgCourseMap.get(course.getAssumeOrgFlow());
//			if(temp == null){
//				temp = new ArrayList<EduCourse>();
//			}
//			temp.add(course);
//			orgCourseMap.put(course.getAssumeOrgFlow(), temp);
		}
		for(EduCourse course1:recordList1){
			List<EduCourse> temp = orgCourseMap.get(course1.getAssumeOrgFlow());
			if(temp == null){
				temp = new ArrayList<EduCourse>();
			}
			temp.add(course1);
			orgCourseMap.put(course1.getAssumeOrgFlow(), temp);
		}
		model.addAttribute("assumeOrgFlowMap", assumeOrgFlowMap);
		List<EduCourse> courseList = new ArrayList<EduCourse>();

		if(StringUtil.isNotBlank(assumeOrgFlow)){
			courseList = orgCourseMap.get(assumeOrgFlow);
		}else{
			courseList = recordList1;
		}
		if(StringUtil.isNotBlank(courseFlow)){
			courseList.clear();
			EduCourse eduCourse = eduCourseBiz.readCourse(courseFlow);
			courseList.add(eduCourse);
		}

		//上课人数
		Map<String, List<XjEduStudentCourseExt>> studentMap = new HashMap<String, List<XjEduStudentCourseExt>>();
		if(courseList!=null&&courseList.size()>0){
			for(EduCourse course : courseList){
				List<XjEduStudentCourseExt> studentCourseExt = studentCourseBiz.searchStudentCourseExtWithUserList(planYear, course.getCourseFlow());
				studentMap.put(course.getCourseFlow(), studentCourseExt);
			}
		}
		model.addAttribute("courseList", courseList);
		model.addAttribute("studentMap", studentMap);
		return "xjgl/plat/courseOverview";
	}
	
	/**
	 * 加载该年度选择该课程的所有学员
	 * @param planYear
	 * @param courseFlow
	 * @param request
	 * @param currentPage
	 * @param model
	 */
	@RequestMapping(value="/searchStudentList")
	public String searchStudentList(String planYear, String courseFlow, HttpServletRequest request, Integer currentPage, Model model){
		if (StringUtil.isNotBlank(courseFlow)) {
			PageHelper.startPage(currentPage, getPageSize(request));
			List<XjEduStudentCourseExt> studentCourseExt = studentCourseBiz.searchStudentCourseExtWithUserList(planYear, courseFlow);
			model.addAttribute("studentCourseExt", studentCourseExt);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pageSize", getPageSize(request));
		}
		return "xjgl/plat/studentList";
	}
	
	/**
	 * 导出学员名册（课程情况查询）
	 * @param
	 * @param studentCount
	 * @param planYear
	 * @param
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/exportStudentList", method={RequestMethod.POST,RequestMethod.GET})
	public void exportStudentList(String exportCourseFlow, String studentCount, String planYear,  HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtil.isNotBlank(exportCourseFlow)) {
			EduCourse course = eduCourseBiz.readCourse(exportCourseFlow);
			StringBuffer sb = new StringBuffer();
			sb.append("承担单位："+ StringUtil.defaultString(course.getAssumeOrgName()));
			sb.append("  课程：[" + course.getCourseCode() +"]" +course.getCourseName());
			sb.append("  学分：" +  StringUtil.defaultString(course.getCourseCredit()));
			sb.append("  总学时：" + StringUtil.defaultString(course.getCoursePeriod()));
			sb.append("  上课人数：" + studentCount);
			String courseInfo = sb.toString();
			String[] headLines = new String[]{
				"课程信息",
				courseInfo,
				planYear + "年学员名册"
			};
			String[] titles = new String[]{
					":序号",
					"eduUser.sid:学号",
					"sysUser.userName:姓名",
					"eduUser.majorName:专业",
					"eduUser.trainCategoryName:培养类型",
					"eduUser.className:行政班级",
					"sysUser.userPhone:联系方式",
					"sysUser.userEmail:邮箱",
					"resDoctor.orgName:培养单位"
			};
			//有种情况：非当前年获得课程成绩（grade_year为选课年份student_period获成绩年份）
			planYear="";
			List<XjEduStudentCourseExt> studentCourseExt = studentCourseBiz.searchStudentCourseExtWithUserList(planYear, exportCourseFlow);
			String fileName = "学员名册.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			ExcleUtile.exportSimpleExcleWithHeadlineByObjs(headLines, titles, studentCourseExt, response.getOutputStream());
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
	@RequestMapping(value="/exportMultiExcle", method={RequestMethod.POST,RequestMethod.GET})
	public void exportMultiExcle(String planYear, HttpServletRequest request, HttpServletResponse response) throws Exception {
		EduCourse example = new EduCourse();
		example.setCourseSession(planYear);
		List<EduCourse> courseList = eduCourseBiz.searchCourseList(example);
		String folder=System.getProperty("java.io.tmpdir")+File.separator;
		String zipFile = PkUtil.getUUID();
		String dir = folder+zipFile;
		File dirFile = new File(dir);
		if(dirFile.exists()==false){
			dirFile.mkdirs();
		}
		if(courseList != null && !courseList.isEmpty()){
			for(EduCourse course : courseList){
				String courseFlow = course.getCourseFlow();
				String[] titles = new String[]{
					":序号",
					"eduUser.sid:学号",
					"sysUser.userName:姓名",
					"eduUser.className:行政班级"
				};
				if (StringUtil.isNotBlank(courseFlow)) {
					List<XjEduStudentCourseExt> studentCourseExt = studentCourseBiz.searchStudentCourseExtWithUserList(planYear, courseFlow);
					int studentCount = 0;
					if(studentCourseExt != null && !studentCourseExt.isEmpty()){
						studentCount =  studentCourseExt.size();
					}
					StringBuffer sb = new StringBuffer();
					sb.append("承担单位："+ StringUtil.defaultIfEmpty(course.getDeptName(), "--"));
					sb.append("  课程：[" + course.getCourseCode() +"]" +course.getCourseName());
					sb.append("  培养层次：" +  StringUtil.defaultIfEmpty(course.getGradationName(), "--"));
					sb.append("  学分：" +  StringUtil.defaultIfEmpty(course.getCourseCredit(), "--"));
					sb.append("  总学时：" + StringUtil.defaultIfEmpty(course.getCoursePeriod(), "--"));
					sb.append("  上课人数：" + studentCount);
					String courseInfo = sb.toString();
					String[] headLines = new String[]{
						"课程信息",
						courseInfo,
						planYear + "年学员名册"
					};
					String fileName = course.getCourseCode()+course.getCourseName()+"学员名册.xls";
					File excel = new File(dir+File.separator+fileName);
					ExcleUtile.exportSimpleExcleWithHeadlineByObjs(headLines, titles, studentCourseExt, excel);
				}
			}
		}
		ZipUtil.makeDirectoryToZip(dirFile, new File(folder+zipFile+".zip"), "", 7);

		String fileName = "课程信息.zip";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

		byte[] data = getByte(new File(folder+zipFile+".zip"));
	    if (data != null) {
			outputStream.write(data);
		}
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 增加和修改同用一个方法
	 * */
	@RequestMapping(value="/saveCourse")
	@ResponseBody
	public String saveCourse(EduCourse eduCourse,String itemId[],String itemName[]){
		int num=eduCourseBiz.saveCourse(eduCourse);
		if (num==GlobalConstant.ONE_LINE) {
			if(StringUtil.isNotBlank(eduCourse.getCourseFlow()) && null != itemId){
				Map<String,Object> mp = new HashMap<>();
				mp.put("courseFlow",eduCourse.getCourseFlow());
				mp.put("itemIdLst", Arrays.asList(itemId));
				mp.put("itemNameLst", Arrays.asList(itemName));
				eduCourseBiz.courseTeacherRelation(mp);
			}
			return GlobalConstant.SAVE_SUCCESSED;
		}else{
			return GlobalConstant.SAVE_FAIL;
		}


	   /* DictTypeEnum.DoctorStatus.getDictNameById("1");*/
	}
	
	@RequestMapping(value="/updateCourse")
	@ResponseBody
	public String updateCourse(String courseFlow,String courseUserCount){
		EduCourse course= eduCourseBiz.readCourse(courseFlow);
		course.setCourseUserCount(courseUserCount);
		int num=eduCourseBiz.saveCourse(course);
		if (num!=GlobalConstant.ONE_LINE) {
			return GlobalConstant.UPDATE_FAIL;
		}else{
			return GlobalConstant.UPDATE_SUCCESSED;
		}
	}
	@RequestMapping(value="/sysCfgUpdate")
	@ResponseBody
	public String sysCfgUpdate(String startCode,String startValue,String endCode,String endValue){
		List<SysCfg> cfgList = new ArrayList<SysCfg>();
		SysCfg start=new SysCfg();
		start.setCfgCode(startCode);
		start.setCfgValue(startValue);
		cfgList.add(start);
		
		SysCfg end=new SysCfg();
		end.setCfgCode(endCode);
		end.setCfgValue(endValue);
		cfgList.add(end);
		
		cfgBiz.save(cfgList);
		return GlobalConstant.SAVE_SUCCESSED;
	}
	
	
	//查询courseList
	@RequestMapping(value="/courseList")
	public String courseList(Model model,Integer currentPage, HttpServletRequest request,EduCourse course){
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
		return "xjgl/plat/courseInfoMaintain";
	}
	/**
	 * 查询course
	 * */
	@RequestMapping(value="/currCourse")
	public String currCourse(Model model,String courseFlow){
		if (StringUtil.isNotBlank(courseFlow)) {
			EduCourse course=eduCourseBiz.readCourse(courseFlow);
			List<EduCourseTeacher> teacherLst = eduCourseBiz.readCourseTeacherLst(courseFlow);
			model.addAttribute("course",course);
			model.addAttribute("teacherLst",teacherLst);
		}
		List<SysOrg> sysOrg=orgBiz.queryAllSysOrg(null);
		model.addAttribute("sysOrg",sysOrg);

		List<String> roleList = new ArrayList<String>();
		String tutorRoleFlow = InitConfig.getSysCfg("xjgl_teacher_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			roleList.add(tutorRoleFlow);
			List<SysUser> userList = userBiz.searchResManageUser(new SysUser(),roleList,null);
			model.addAttribute("userList",userList);
		}
		String masterFlow = InitConfig.getSysCfg("xjgl_master_role_flow");
		roleList.clear();
		if(StringUtil.isNotBlank(masterFlow)){
			roleList.add(masterFlow);
			List<SysUser> masterList = userBiz.searchResManageUser(new SysUser(),roleList,null);
			model.addAttribute("masterList",masterList);
		}
		return "xjgl/plat/courseInfoAdd";
	}
	//删除
	@RequestMapping(value="/courseDelete")
	@ResponseBody
	public String courseDelete(String courseFlow){
		EduCourseMajor courseMajor=new EduCourseMajor();
		courseMajor.setCourseFlow(courseFlow);
		List<EduCourseMajor>	list=courseMajorBiz.searchCourseMajorList(courseMajor);
		if(null!=list&&list.size()>0){
			return "该课程已经关联专业信息，不可删除！！";
		}
		EduStudentCourse studentCourse=new EduStudentCourse();
		studentCourse.setCourseFlow(courseFlow);
		studentCourse.setRecordStatus(GlobalConstant.FLAG_Y);
		List<EduStudentCourse> list1=studentCourseBiz.searchStudentCourseList(studentCourse);
		if(null!=list1&&list1.size()>0){
			return "已有学生选修该门课程，不可删除！！";
		}
		int num=eduCourseBiz.delCourse(courseFlow);

		if (num!=GlobalConstant.ONE_LINE) {
			return GlobalConstant.DELETE_FAIL;
		}else{
			return GlobalConstant.DELETE_SUCCESSED;
		}
	}
	/**
	 * 导入课程
	 * @param deptFlow
	 */
	@RequestMapping(value="/importCourse")
	public String importCourse(String deptFlow){
		return "xjgl/plat/courseDaoRu";
	}
	
	/**
	 * 导入课程
	 * @param
	 */
	@RequestMapping(value="/importCourseFromExcel")
	@ResponseBody
	public String importCourseFromExcel(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) eduCourseBiz.importCourseFromExcel(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
						return GlobalConstant.UPLOAD_FAIL+msg;
					}else{
						if(GlobalConstant.ZERO_LINE != count){
							return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
						}else{
							return GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
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
	 * 判断课程代码是否存在
	 * */
	@RequestMapping(value="/courseCodeOk")
	@ResponseBody
	public String courseCodeOk(EduCourse course){
		List<EduCourse> coursesList=eduCourseBiz.searchCourseList(course);
		if (null != coursesList && coursesList.size() > 0) {
			return GlobalConstant.COURSE_COURSECODE_EXIST;
		}else{
			return GlobalConstant.COURSE_COURSECODE_SUCCESSED;
		}
		
	}
	/**
	 * 课程导出
	 * */
	@RequestMapping("/courseExport")
	public void courseExport(HttpServletResponse response, EduCourse course) throws Exception {
		String[] titles = new String[]{
				"courseSession:所属学年",
				"courseCode:代码",
				"courseName:中文名称",
				"gradationName:授课层次",
				"courseTypeName:课程类别",
				"courseUserCount:容纳人数",
				"courseMoudleName:所属模块",
				"assumeOrgName:承担单位",
				"courseNameEn:英文名称",
				"courseCredit:学分",
				"coursePeriod:总学时",
				"coursePeriodTeach:讲授学时",
				"coursePeriodExper:实验学时",
				"coursePeriodMachine:上机学时",
				"coursePeriodOther:其他学时"
		};
		List<EduCourse> coursesList=eduCourseBiz.searchCourseList(course);
	    String fileName = "课程信息名单.xls";
	    fileName = URLEncoder.encode(fileName, "UTF-8");
	    ExcleUtile.exportSimpleExcleByObjs(titles, coursesList, response.getOutputStream(),new String[]{"1"});
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
		
	}

	/**
	 * 学籍各版块状态确认
     */
	@RequestMapping("/savePartStatus")
	@ResponseBody
	public String savePartStatus(String userFlow,String partId){
		int count = eduCourseBiz.savePartStatus(userFlow,partId,null);
		if(count <= 0){
			return GlobalConstant.SAVE_FAIL;
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 学籍各版块状态确认退回操作
	 */
	@RequestMapping("/backPartStatus")
	@ResponseBody
	public String backPartStatuss(String userFlow,String partId){
		int count = eduCourseBiz.savePartStatus(userFlow,partId,"back");
		if(count <= 0){
			return GlobalConstant.SAVE_FAIL;
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 大纲教材录入弹框
     */
	@RequestMapping("/uploadOutlineInfo")
	public String uploadOutlineInfo(String courseFlow,Model model){
		model.addAttribute("eduCourse",eduCourseBiz.readCourse(courseFlow));
		return "xjgl/plat/courseOutline";
	}

	/**
	 * 大纲教材录入(修改)操作
     */
	@RequestMapping("/uploadOption")
	@ResponseBody
	public String uploadOption(String courseFlow,HttpServletRequest request){
		String outlineContent = request.getParameter("outline_ueditor");
		String teachingContent = request.getParameter("teaching_ueditor");
		EduCourse course = new EduCourse();
		course.setCourseFlow(courseFlow);
		course.setOutlineContent(outlineContent);
		course.setTeachingContent(teachingContent);
		int count = eduCourseBiz.saveCourseOutline(course);
		if(count <= 0){
			return GlobalConstant.SAVE_FAIL;
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 课程大纲及教材查看
	 */
	@RequestMapping("/searchOutline")
	public String searchOutlin(String courseFlow,String reqFlag,Model model){
		model.addAttribute("eduCourse",eduCourseBiz.readCourse(courseFlow));
		model.addAttribute("viewFlag","view");
		return "xjgl/plat/courseOutline";
	}

	/**
	 * 查看2016年研究生课程（全部）
	 */
	@RequestMapping("/searchAllCourse")
	public String searchAllCourse(String year,Integer currentPage, HttpServletRequest request,Model model){
		PageHelper.startPage(currentPage,getPageSize(request));
		EduCourse course = new EduCourse();
		course.setCourseSession(year);
		course.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<EduCourse> coursesList=eduCourseBiz.searchCourseList(course);
		model.addAttribute("coursesList",coursesList);
		return "xjgl/plat/searchAllCourse";
	}
}
