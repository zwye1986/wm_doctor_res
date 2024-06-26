package com.pinde.sci.ctrl.hbres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.ExamManageBiz;
import com.pinde.sci.biz.hbres.GradeManageBiz;
import com.pinde.sci.biz.hbres.IHbResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.ExamStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.hbres.ResDoctorTrainingSpeForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 成绩管理
 * --成绩录入
 * @author shenzhen
 *
 */
@Controller
@RequestMapping("/hbres/grade")
public class GradeManageController extends GeneralController{
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private GradeManageBiz gradeManageBiz;
	@Autowired
	private IHbResDoctorRecruitBiz recruitBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ExamManageBiz examManageBiz;
	@Autowired
	private IHbResDoctorRecruitBiz doctorRecruitBiz;
	
	/**
	 * 成绩录入
	 * @return
	 */
	@RequestMapping("/input")
	public String input(ResExamDoctorExt examDoctor , Integer currentPage , Model model,HttpServletRequest request){
		List<ResExam> exams = examManageBiz.findALLExam();
		List<ResExam> finishedExams = new ArrayList<ResExam>();
		for(ResExam exam : exams){
			if(ExamStatusEnum.Finished.getId().equals(exam.getExamStatusId())){
				finishedExams.add(exam);
			}
		}
		model.addAttribute("exams" , finishedExams);
		if(StringUtil.isBlank(examDoctor.getExamFlow())){
			if(finishedExams.size()>0){
				examDoctor.setExamFlow(finishedExams.get(0).getExamFlow());
			}else{
				examDoctor.setExamFlow(exams.get(0).getExamFlow());
			}
		}
		if(StringUtil.isNotBlank(examDoctor.getExamFlow())){
			//查询该场考试下的考点
			List<ResExamSite> sites = this.examManageBiz.findAllUsablelExamSite(examDoctor.getExamFlow());
			model.addAttribute("sites" , sites);
		}
		if(StringUtil.isNotBlank(examDoctor.getSiteFlow())){
			//查询该考点下的考场
			List<ResExamRoom> rooms = this.examManageBiz.findExamRoomsBySiteFlow(examDoctor.getSiteFlow());
			model.addAttribute("rooms" , rooms);
		}
		if(StringUtil.isNotBlank(examDoctor.getExamFlow())){
			PageHelper.startPage(currentPage, getPageSize(request));
			List<ResExamDoctorExt> userList = this.examManageBiz.findExamDoctorExts(examDoctor);
			model.addAttribute("userList" , userList);
		}
		return "hbres/manage/grade/gradeinput";
	}
	
	@RequestMapping("/getsites")
	@ResponseBody
	public Object getSites(@RequestParam(required=true)String examFlow){
		List<ResExamSite> sites = this.examManageBiz.findAllUsablelExamSite(examFlow);
		return sites;
	}
	
	@RequestMapping("/gradelist")
	public String gradeList(Integer currentPage , Model model){
		ResDoctorExt resDoctorExt = new ResDoctorExt();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		resDoctorExt.setSessionNumber(regYear);
		resDoctorExt.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		SysUser sysUser = new SysUser();
		sysUser.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysUser.setStatusId(UserStatusEnum.Activated.getId());//已激活的用户
		resDoctorExt.setSysUser(sysUser);
		PageHelper.startPage(currentPage, 10);
		List<ResDoctorExt> userList= resDoctorBiz.searchDocUser(resDoctorExt, "");
		model.addAttribute("userList" , userList);
		Map<String , ResDoctorRecruit> doctorRecruitMap = new HashMap<String, ResDoctorRecruit>();
		//查询今年报名人员对应的RES_DOCTOR_RECRUIT记录
		for(ResDoctorExt doctorExt:userList){
			ResDoctorRecruit doctorRecruit = this.gradeManageBiz.findResDoctorRecruitByDoctorFlow(doctorExt.getDoctorFlow());
			doctorRecruitMap.put(doctorExt.getDoctorFlow(), doctorRecruit);
		}
		model.addAttribute("doctorRecruitMap" , doctorRecruitMap);
		return "hbres/manage/grade/gradelist";
	}

	//成绩导出
	@RequestMapping("/exportExl")
	public void exportExl(ResExamDoctorExt examDoctor , HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<ResExam> exams = examManageBiz.findALLExam();
		List<ResExam> finishedExams = new ArrayList<ResExam>();
		for(ResExam exam : exams){
			if(ExamStatusEnum.Finished.getId().equals(exam.getExamStatusId())){
				finishedExams.add(exam);
			}
		}
		if(StringUtil.isBlank(examDoctor.getExamFlow())){
			if(finishedExams.size()>0){
				examDoctor.setExamFlow(finishedExams.get(0).getExamFlow());
			}else{
				examDoctor.setExamFlow(exams.get(0).getExamFlow());
			}
		}
		if(StringUtil.isNotBlank(examDoctor.getExamFlow())){
			//查询该场考试下的考点
			List<ResExamSite> sites = this.examManageBiz.findAllUsablelExamSite(examDoctor.getExamFlow());
		}
		if(StringUtil.isNotBlank(examDoctor.getSiteFlow())){
			//查询该考点下的考场
			List<ResExamRoom> rooms = this.examManageBiz.findExamRoomsBySiteFlow(examDoctor.getSiteFlow());
		}
		List<ResExamDoctorExt> userList = null;
		if(StringUtil.isNotBlank(examDoctor.getExamFlow())){
			userList = this.examManageBiz.findExamDoctorExts(examDoctor);
		}
		if(userList!=null&&userList.size()>0){
			for(ResExamDoctorExt examDoctorExt:userList){
				ResDoctor doctor = examDoctorExt.getDoctor();
				if(doctor!=null){
					String specialized = doctor.getSpecialized();
					String specializedName = DictTypeEnum.GraduateMajor.getDictNameById(specialized);
					doctor.setSpecialized(specializedName);
					examDoctorExt.setDoctor(doctor);
				}
			}
		}
		String fileName = "成绩导出.xls";
		String titles[] = {
				"doctor.doctorName:姓名",
				"ticketNum:准考证号",
				"doctor.graduatedName:毕业院校",
				"doctor.specialized:毕业专业",
				"user.idNo:身份证号",
				"examResult:分数"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, userList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	
	@RequestMapping("/inputdoctorgrade")
	@ResponseBody
	public String inputDoctorGrade(String examFlow , String doctorFlow , String examResult){
		this.gradeManageBiz.inputDoctorGrade(examFlow , doctorFlow, examResult);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	//*********************成绩导入***********************

	/**
	 * 跳转至成绩导入
	 * @param examFlow
	 * @return
	 */
	@RequestMapping(value="/openImport",method={RequestMethod.GET})
	public String openImport(String examFlow){
		return "hbres/manage/grade/importResult";
	}

	/**
	 * 跳转至成绩录入页面
	 * @param examFlow
	 * @return
	 */
	@RequestMapping(value="/zsGradeImport",method={RequestMethod.GET})
	public String zsGradeImport(String examFlow,Model model){
		model.addAttribute("zsGradeFlag","Y");
		return "hbres/manage/grade/importResult";
	}

	/**
	 * 录入专硕理论成绩
	 * @param grade
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/importGrade"},method={RequestMethod.POST})
	@ResponseBody
	public String importGrade(String examFlow , String grade) throws Exception{
		try{
			gradeManageBiz.importGrade(examFlow , grade);
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}catch(RuntimeException re){
			return re.getMessage();
		}
	}

	/**
	 * 导入Excel成绩
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value={"/importExcel"},method={RequestMethod.POST})
	@ResponseBody
	public String importExcel(String examFlow , MultipartFile file) throws Exception{
		if(file.getSize() > 0){
			try{
				gradeManageBiz.importExcel(examFlow , file);
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}catch(RuntimeException re){
				return re.getMessage();
			}
			
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	
	//******************* 统计 *************************

	/**
	 * 住培学员注册登记表
	 * @param orgFlow
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("/registerList")
	public String registerList(String orgFlow, Integer currentPage, Model model, String regYear, HttpServletRequest request){
		//基地
		List<SysOrg> orgList = orgBiz.searchHbresOrgList();
		model.addAttribute("orgList",orgList);
		Map<String, Object> paramMap = new HashMap<String , Object>();
		ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
		doctorRecruit.setOrgFlow(orgFlow);
		doctorRecruit.setRecruitYear(regYear);
		paramMap.put("doctorRecruit", doctorRecruit);
		List<ResDoctorExt> doctorExtListTemp = resDoctorBiz.searchRegisterList(paramMap);
		model.addAttribute("totalNum", doctorExtListTemp==null?"0":doctorExtListTemp.size());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResDoctorExt> doctorExtList = resDoctorBiz.searchRegisterList(paramMap);
		model.addAttribute("doctorExtList", doctorExtList);
		return "hbres/manage/registerList";
	}


	/**
	 * 住培注册学员统计表
	 * @param orgFlow
	 * @param model
	 * @return
	 */
	@RequestMapping("/registerStatistics")
	public String registerStatistics(String orgFlow, Model model,String regYear,HttpServletRequest request,Integer currentPage){
		Map<String , Object> paramMap0 = new HashMap<String, Object>();
		paramMap0.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysDict> doctorTrainingSpeList = this.doctorRecruitBiz.searchTrainSpeList(paramMap0);
		model.addAttribute("doctorTrainingSpeList",doctorTrainingSpeList);
		//基地
		List<SysOrg> orgList = orgBiz.searchHbresOrgList();
		model.addAttribute("orgList",orgList);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
		doctorRecruit.setOrgFlow(orgFlow);
		doctorRecruit.setRecruitYear(regYear);
		paramMap.put("doctorRecruit", doctorRecruit);
		List<ResDoctorTrainingSpeForm> doctorRecruitFormList =  recruitBiz.searchRegisterStatistics(paramMap);
		int totalNum=0;
		if(doctorRecruitFormList != null && !doctorRecruitFormList.isEmpty()){
			Map<String, String> doctorRecruitFormMap =  new HashMap<String, String>();
			for(ResDoctorTrainingSpeForm form : doctorRecruitFormList){
				doctorRecruitFormMap.put(form.getSpeId(), form.getDoctorCount());
				if(StringUtil.isNotBlank(form.getDoctorCount())){
					totalNum+=Integer.parseInt(form.getDoctorCount());
				}
			}
			model.addAttribute("doctorRecruitFormMap", doctorRecruitFormMap);
		}
		model.addAttribute("totalNum", totalNum);
		return "hbres/manage/registerStatistics";
	}


	/**
	 * 录取考试成绩排序表
	 * @param orgFlow
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("/recruitResultList")
	public String recruitResultList(String orgFlow, Integer currentPage, Model model, String regYear,HttpServletRequest request){
		//基地recruitResultList
		List<SysOrg> orgList = orgBiz.searchHbresOrgList();
		model.addAttribute("orgList",orgList);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
		doctorRecruit.setOrgFlow(orgFlow);
		doctorRecruit.setRecruitYear(regYear);
		paramMap.put("doctorRecruit", doctorRecruit);
		List<ResDoctorRecruitExt> doctorRecruitExtListTemp =  recruitBiz.searchDoctorRecruitWithUserList(paramMap);
		model.addAttribute("totalNum" , doctorRecruitExtListTemp==null?"0":doctorRecruitExtListTemp.size());
		model.addAttribute("pageSize", getPageSize(request));
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResDoctorRecruitExt> doctorRecruitExtList =  recruitBiz.searchDoctorRecruitWithUserList(paramMap);
		model.addAttribute("doctorRecruitExtList" , doctorRecruitExtList);
		return "hbres/manage/recruitResultList";
	}

	//导出录取考试成绩排序表
	@RequestMapping("/exportRecruitResultList")
	public void exportRecruitResultList(String orgFlow, Model model, String regYear,HttpServletResponse response) throws Exception {
		//基地recruitResultList
		Map<String, Object> paramMap = new HashMap<String, Object>();
		ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
		doctorRecruit.setOrgFlow(orgFlow);
		doctorRecruit.setRecruitYear(regYear);
		paramMap.put("doctorRecruit", doctorRecruit);
		List<ResDoctorRecruitExt> doctorRecruitExtListTemp =  recruitBiz.searchDoctorRecruitWithUserList(paramMap);
		model.addAttribute("totalNum" , doctorRecruitExtListTemp==null?"0":doctorRecruitExtListTemp.size());
		List<ResDoctorRecruitExt> doctorRecruitExtList =  recruitBiz.searchDoctorRecruitWithUserList(paramMap);
		model.addAttribute("doctorRecruitExtList" , doctorRecruitExtList);
		String[] titles = new String[]{
				"recruitYear:年级",
				"sysUser.userName:姓名",
				"sysUser.idNo:身份证号",
				"examResult:笔试",
				"operResult:技能考试",
				"auditionResult:面试",
				"totleResult:总成绩"
		};
		String fileName = "录取考试成绩名单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, doctorRecruitExtList, response.getOutputStream());
	}

	@RequestMapping("/gradeLineInx")
	public String gradeLineInx(){
		return "hbres/manage/grade/gradeLineInx";
	}

	@RequestMapping("/gradeline")
	public String showGradeLine(String examFlow , Model model){
		List<ResExam> exams = examManageBiz.findALLExam();
		List<ResExam> finishedExams = new ArrayList<ResExam>();
		for(ResExam exam : exams){
			if(ExamStatusEnum.Finished.getId().equals(exam.getExamStatusId())){
				finishedExams.add(exam);
			}
		}
		model.addAttribute("exams" , finishedExams);
		ResExam currExam = null;
		if(StringUtil.isBlank(examFlow)){
			if(finishedExams.size()>0){
				currExam = finishedExams.get(0); 
			}
		}else{
			currExam = this.examManageBiz.findExamByFlow(examFlow);
		}
		model.addAttribute("currExam" , currExam);
		
		if(currExam!=null){
			//查询参加考试的总人数
			Integer joinExamSumDoctor = this.examManageBiz.findExamUserCountByExamFlow(currExam.getExamFlow());
			model.addAttribute("joinExamSumDoctor" , joinExamSumDoctor);
			//获取考试不同专业分数线统计信息
			Map<String , GradeBorderlineStatistics> gradeBorderlineStatistics = this.gradeManageBiz.getGradeBorderlineStatistics(currExam.getExamFlow());
			model.addAttribute("statisticsMap"  , gradeBorderlineStatistics);
		}
		
		return "hbres/manage/grade/gradeline";
	}
	
	@RequestMapping("/getpasscount")
	public String getPasscount(ResGradeBorderline borderline , Model model){
		this.gradeManageBiz.addGradeBorderLine(borderline);
		return showGradeLine(borderline.getExamFlow() , model);
	}
	
	@RequestMapping("/publishgradeborderline")
	public String publishGradeBorderline(ResGradeBorderline borderline , Model model){
		borderline.setPublishFlag(GlobalConstant.FLAG_Y);
		this.gradeManageBiz.addGradeBorderLine(borderline);
		return showGradeLine(borderline.getExamFlow() , model);
	}
	
	@RequestMapping("/getgradesteps")
	public String getgradesteps(String examFlow , Model model){
		Map<String ,GradeStepStatistics> gradeSteps = this.gradeManageBiz.getGradeSteps(examFlow);
		model.addAttribute("gradeStepsMap" , gradeSteps);
		ResExam currExam = this.examManageBiz.findExamByFlow(examFlow);
		model.addAttribute("currExam" , currExam);
		return "hbres/manage/grade/gradesteps";
	}

	//导出分数段人数统计
	@RequestMapping("/exportGradesteps")
	public void exportGradesteps(String examFlow,HttpServletResponse response) throws IOException {
		Map<String ,GradeStepStatistics> gradeSteps = this.gradeManageBiz.getGradeSteps(examFlow);

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateMajor");
		for(SysDict dict:dictList){
			String dictName = dict.getDictName();
			String dictId = dict.getDictId();
			List<Map<String,Object>> resultMapList = new ArrayList<>();
			if(gradeSteps!=null&&gradeSteps.get(dictId)!=null){
				List<GradeStep> gradeStepList = gradeSteps.get(dictId).getGradeSteps();
				if(gradeStepList!=null&&gradeStepList.size()>0){
					for(GradeStep step:gradeStepList){
						Map<String,Object> single = new HashMap<>();
						single.put("startGrade",step.getStartGrade());
						single.put("endGrade",step.getEndGrade());
						single.put("count",step.getCount());
						resultMapList.add(single);
					}
				}
			}
			// 为工作簿添加sheet
			HSSFSheet sheet = wb.createSheet(dictName);
			HSSFRow rowOne = sheet.createRow(0);//第一行
			String[] titles = new String[]{
					"分数段",
					"人数"
			};
			HSSFCell cellTitle = null;
			for (int i = 0; i < titles.length; i++) {
				cellTitle = rowOne.createCell(i);
				cellTitle.setCellValue(titles[i]);
				cellTitle.setCellStyle(styleCenter);
				sheet.setColumnWidth(i, titles.length * 4 * 256);
			}
			//行数
			int rowNum = 1;
			//存放在excel中的行数据
			String[] resultList = null;
			if (resultMapList != null && !resultMapList.isEmpty()) {
				for(int i = 0; i < resultMapList.size(); i++, rowNum++){
					HSSFRow rowTwo = sheet.createRow(rowNum);//第二行
					Map<String, Object> resultMap = resultMapList.get(i);
					resultList = new String[]{
						resultMap.get("startGrade")+"~"+resultMap.get("endGrade"),
						resultMap.get("count")+""
					};
					for (int j = 0; j < titles.length; j++) {
						HSSFCell cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
					}
				}
			}
		}
		String fileName = "分数段人数表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping("/getgradestep")
	public String getgradestep(ResGradeBorderline borderline , Model model){
		//实时保存步长
		this.gradeManageBiz.modGradeBorderlineStep(borderline);
		List<GradeStep> gradeSteps = this.gradeManageBiz.getGradeStep(borderline.getExamFlow(), borderline.getSpeId(), Integer.parseInt(borderline.getGradeStep()));
		model.addAttribute("gradeSteps" , gradeSteps);
		return "hbres/manage/grade/gradestep";
	}

	@RequestMapping("/gradelineOrg")
	public String gradelineOrg(String examFlow , Model model,Integer currentPage,HttpServletRequest request){
		List<ResExam> exams = examManageBiz.findALLExam();
		List<ResExam> finishedExams = new ArrayList<ResExam>();
		for(ResExam exam : exams){
			if(ExamStatusEnum.Finished.getId().equals(exam.getExamStatusId())){
				finishedExams.add(exam);
			}
		}
		model.addAttribute("exams" , finishedExams);
		ResExam currExam = null;
		if(StringUtil.isBlank(examFlow)){
			if(finishedExams.size()>0){
				currExam = finishedExams.get(0);
			}
		}else{
			currExam = this.examManageBiz.findExamByFlow(examFlow);
		}
		model.addAttribute("currExam" , currExam);

		Map<String,Object> map01 = new HashMap<>();
		Map<String,Object> map02 = new HashMap<>();

		if(currExam!=null){
			//查询参加考试的总人数
			Integer joinExamSumDoctor = this.examManageBiz.findExamUserCountByExamFlow(currExam.getExamFlow());
			model.addAttribute("joinExamSumDoctor" , joinExamSumDoctor);

			List<ResGradeBorderline> gradeBorderlines = gradeManageBiz.findGradeBorderlineByExamFlow(currExam.getExamFlow());
			if(gradeBorderlines!=null && gradeBorderlines.size()>0){
				model.addAttribute("gradeBorderlines",gradeBorderlines);
				for(ResGradeBorderline gradeBorderline:gradeBorderlines){
					String borderlineFlow = gradeBorderline.getBorderlineFlow();
					ResGradeBorderlineOrg search = new ResGradeBorderlineOrg();
					search.setBorderlineFlow(borderlineFlow);
					List<ResGradeBorderlineOrg> gradeBorderlineOrgList = gradeManageBiz.searchBorderlineOrg(search);
					if(gradeBorderlineOrgList!=null && gradeBorderlineOrgList.size()>0){
						for(ResGradeBorderlineOrg gradeBorderlineOrg:gradeBorderlineOrgList){
							if("01".equals(gradeBorderlineOrg.getSpeId())){
								map01.put(gradeBorderlineOrg.getOrgFlow(),gradeBorderlineOrg);
							}
							if("02".equals(gradeBorderlineOrg.getSpeId())){
								map02.put(gradeBorderlineOrg.getOrgFlow(),gradeBorderlineOrg);
							}
						}
					}
				}
			}
		}
		model.addAttribute("map01",map01);
		model.addAttribute("map02",map02);

		SysOrgExample example = new SysOrgExample();
		example.createCriteria().andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("ordinal");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList", orgList);
		return "hbres/manage/grade/gradelineOrg";
	}

	@RequestMapping("/editLineOrg")
	@ResponseBody
	public String editLineOrg(ResGradeBorderlineOrg gradeBorderlineOrg){
		gradeManageBiz.editBorderlineOrg(gradeBorderlineOrg);
		return GlobalConstant.SAVE_SUCCESSED;
	}
}
