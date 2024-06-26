package com.pinde.sci.ctrl.res;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchDoctorAbsenceBiz;
import com.pinde.sci.biz.sch.IZseyHrKqMonthBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.res.HBRecDocTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/zsrykqmonth")
public class ResZsryKqMonthController extends GeneralController{

	@Autowired
	private IZseyHrKqMonthBiz zseyHrKqMonthBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchDoctorAbsenceBiz doctorAbsenceBiz;

	@RequestMapping("/viewList/{role}")
	public String viewList(@PathVariable String role,Model model){
		model.addAttribute("role",role);
		return "res/zsrykqmonth/viewList";
	}

	@RequestMapping("/list/time/{role}")
	public String listTime(@PathVariable String role, ResDoctor resDoctor, Model model, HttpServletRequest request,Integer currentPage
		,String startDate,String endDate,String[] doctorTypeIdList,String onlyMiss,String graduationYear,String onlyAbsence,
					   String inHosDate,String trainingYears,String deptFlow){
		model.addAttribute("role",role);
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-5);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		Long sumAttendantDay = 1-DateUtil.signDaysBetweenTowDate(startDate,endDate);//总天数
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		//所有科室
		List<SysDept> deptList = deptBiz.searchDeptByOrg(user.getOrgFlow());
		model.addAttribute("deptList",deptList);
		if(doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			paramMap.put("doctorTypeIdList",Arrays.asList(doctorTypeIdList));
			model.addAttribute("doctorTypeIdList",Arrays.asList(doctorTypeIdList));//上一次选中的条件传到页面上
		}
		paramMap.put("doctorName",resDoctor.getDoctorName());
		paramMap.put("trainingSpeId",resDoctor.getTrainingSpeId());
		paramMap.put("sessionNumber",resDoctor.getSessionNumber());
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("inHosDate",inHosDate);
		paramMap.put("trainingYears",trainingYears);
		paramMap.put("deptFlow",deptFlow);
		paramMap.put("onlyMiss",onlyMiss);
		paramMap.put("onlyAbsence",onlyAbsence);

		if("doctor".equals(role)){
			paramMap.put("doctorFlow",user.getUserFlow());
		}else if("teacher".equals(role)){
			paramMap.put("teacherUserFlow",user.getUserFlow());
		}else if("head".equals(role)){
//			paramMap.put("headUserFlow",user.getUserFlow());
			List<SchDept> schDeptList = schDeptBiz.searchrotationDept(user.getUserFlow());
			paramMap.put("schDeptList",schDeptList);
		}else if("speBase".equals(role)){
			paramMap.put("speId",user.getResTrainingSpeId());
		}else if("manager".equals(role)){
			paramMap.put("orgFlow",user.getOrgFlow());
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctor> doctorList = doctorBiz.searchDoctor4Kq(paramMap);
		model.addAttribute("doctorList",doctorList);
		Map<String,String> resultMap = new HashMap<>();
		Map<String,Double> sumMap = new HashMap<>();
		Map<String,Double> sumAttendantMap = new HashMap<>();
		DecimalFormat df = new DecimalFormat("#0%");
		Map<String,Object> percentMap = new HashMap<>();
		if(doctorList!=null&&doctorList.size()>0){
			for(ResDoctor doctor:doctorList){
				String userFlow=doctor.getDoctorFlow();
				List<Map<String,String>> kqMap = zseyHrKqMonthBiz.searchKq(startDate,endDate,userFlow);
				if(kqMap!=null&&kqMap.size()>0){
					double sum = 0;
					for(Map<String,String> kq :kqMap){
						String absenceTypeId = kq.get("ABSENCE_TYPE_ID");
						String num = kq.get("NUM");
						if("03".equals(absenceTypeId)||"05".equals(absenceTypeId)||"07".equals(absenceTypeId)||"09".equals(absenceTypeId)||"21".equals(absenceTypeId)||"22".equals(absenceTypeId)){
							sum+=Double.parseDouble(num)/2;
						}else {
							sum+=Double.parseDouble(num);
						}
						resultMap.put(userFlow+absenceTypeId,num);
						sumMap.put(userFlow,sum);
						sumAttendantMap.put(userFlow,sumAttendantDay.doubleValue()-sum);
						Double percent = (double)(sumAttendantDay.doubleValue()-sum)/(double)sumAttendantDay.doubleValue();
						percentMap.put(userFlow,df.format(percent));
					}
				}
			}
		}
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("sumMap",sumMap);
		model.addAttribute("sumAttendantMap",sumAttendantMap);
		model.addAttribute("sumAttendantDay",sumAttendantDay);
		model.addAttribute("percentMap",percentMap);
		return "res/zsrykqmonth/list";
	}

	@RequestMapping("/exportExl/{role}")
	public void exportExl(@PathVariable String role, ResDoctor resDoctor,String startDate,String endDate,String[] doctorTypeIdList,
						  String onlyMiss,String graduationYear,String onlyAbsence,HttpServletResponse response,
						  String inHosDate,String trainingYears,String deptFlow) throws Exception {
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-5);
		}
		Long sumAttendantDay = 1-DateUtil.signDaysBetweenTowDate(startDate,endDate);//总天数
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		//所有科室
		if(doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			paramMap.put("doctorTypeIdList",Arrays.asList(doctorTypeIdList));
		}
		paramMap.put("doctorName",resDoctor.getDoctorName());
		paramMap.put("trainingSpeId",resDoctor.getTrainingSpeId());
		paramMap.put("sessionNumber",resDoctor.getSessionNumber());
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("inHosDate",inHosDate);
		paramMap.put("trainingYears",trainingYears);
		paramMap.put("deptFlow",deptFlow);
		paramMap.put("onlyMiss",onlyMiss);
		paramMap.put("onlyAbsence",onlyAbsence);

		if("doctor".equals(role)){
			paramMap.put("doctorFlow",user.getUserFlow());
		}else if("teacher".equals(role)){
			paramMap.put("teacherUserFlow",user.getUserFlow());
		}else if("head".equals(role)){
//			paramMap.put("headUserFlow",user.getUserFlow());
			List<SchDept> schDeptList = schDeptBiz.searchrotationDept(user.getUserFlow());
			paramMap.put("schDeptList",schDeptList);
		}else if("speBase".equals(role)){
			paramMap.put("speId",user.getResTrainingSpeId());
		}else if("manager".equals(role)){
			paramMap.put("orgFlow",user.getOrgFlow());
		}
		List<ResDoctor> doctorList = doctorBiz.searchDoctor4Kq(paramMap);

		List<Map<String,Object>> exportMapList = new ArrayList<>();

		Map<String,String> resultMap = new HashMap<>();
		Map<String,Double> sumMap = new HashMap<>();
		Map<String,Double> sumAttendantMap = new HashMap<>();
		DecimalFormat df = new DecimalFormat("#0%");
		Map<String,Object> percentMap = new HashMap<>();
		if(doctorList!=null&&doctorList.size()>0){
			for(ResDoctor doctor:doctorList){
				String userFlow=doctor.getDoctorFlow();
				List<Map<String,String>> kqMap = zseyHrKqMonthBiz.searchKq(startDate,endDate,userFlow);
				if(kqMap!=null&&kqMap.size()>0){
					double sum = 0;
					for(Map<String,String> kq :kqMap){
						String absenceTypeId = kq.get("ABSENCE_TYPE_ID");
						String num = kq.get("NUM");
						if("03".equals(absenceTypeId)||"05".equals(absenceTypeId)||"07".equals(absenceTypeId)||"09".equals(absenceTypeId)||"21".equals(absenceTypeId)||"22".equals(absenceTypeId)){
							sum+=Double.parseDouble(num)/2;
						}else {
							sum+=Double.parseDouble(num);
						}
						resultMap.put(userFlow+absenceTypeId,num);
						sumMap.put(userFlow,sum);
						sumAttendantMap.put(userFlow,sumAttendantDay.doubleValue()-sum);
						Double percent = (double)(sumAttendantDay.doubleValue()-sum)/(double)sumAttendantDay.doubleValue();
						percentMap.put(userFlow,df.format(percent));
					}
				}
			Map<String,Object> subMap = new HashMap<>();
			subMap.put("kqDate",startDate+"~"+endDate);
			subMap.put("doctorName",doctor.getDoctorName());
			subMap.put("sessionNumber",doctor.getSessionNumber());
			if(HBRecDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())){
				subMap.put("departMentName",doctor.getDepartMentName());
			}else if(HBRecDocTypeEnum.Social.getId().equals(doctor.getDoctorTypeId())){
				subMap.put("departMentName","--");
			}else{
				subMap.put("departMentName",doctor.getWorkOrgName());
			}
			subMap.put("trainingSpeName",doctor.getTrainingSpeName());
			subMap.put("trainingYears",doctor.getTrainingYears());
			subMap.put("inHosDate",doctor.getInHosDate());
			subMap.put("graduationYear",doctor.getGraduationYear());
			double content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"02"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"03"),"0"))/2;
			subMap.put("0203",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"04"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"05"),"0"))/2;
			subMap.put("0405",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"06"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"07"),"0"))/2;
			subMap.put("0607",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"10"),"0"));
			subMap.put("10",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"11"),"0"));
			subMap.put("11",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"12"),"0"));
			subMap.put("12",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"13"),"0"));
			subMap.put("13",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"15"),"0"));
			subMap.put("15",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"16"),"0"));
			subMap.put("16",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"18"),"0"));
			subMap.put("18",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"19"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"22"),"0"))/2;
			subMap.put("1922",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"20"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(doctor.getDoctorFlow()+"21"),"0"))/2;
			subMap.put("2021",String.valueOf(content));
			subMap.put("sum",String.valueOf(sumMap.get(doctor.getDoctorFlow())));
			subMap.put("sumAttendant",String.valueOf(sumAttendantMap.get(doctor.getDoctorFlow())));
			subMap.put("sumAttendantDay",String.valueOf(sumAttendantDay.doubleValue()));
			subMap.put("percent",percentMap.get(doctor.getDoctorFlow()));
				exportMapList.add(subMap);
			}
			String fileName = "考勤信息统计.xls";
			String titles[] = {
					"kqDate:考勤时间",
					"doctorName:姓名",
					"sessionNumber:年级",
					"departMentName:所属科室",
					"trainingSpeName:培训专业",
					"trainingYears:培训年限",
					"inHosDate:入培时间",
					"graduationYear:结业时间",
					"0203:病假",
					"0405:事假",
					"0607:带薪年假",
					"10:婚假",
					"11:产假",
					"12:陪产假",
					"13:计生假",
					"15:出国",
					"16:进修",
					"18:脱产读研",
					"1922:放射假",
					"2021:旷工",
					"sum:请假合计",
					"sumAttendant:出勤",
					"sumAttendantDay:考勤天数",
					"percent:出勤率"
			};
			fileName = URLEncoder.encode(fileName, "UTF-8");
			ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	@RequestMapping("/list/dept/{role}")
	public String listDept(@PathVariable String role, ResDoctor resDoctor, Model model, HttpServletRequest request,Integer currentPage
			,String startDate,String endDate,String[] doctorTypeIdList,String onlyMiss,String graduationYear,String onlyAbsence,
					   String inHosDate,String trainingYears,String deptFlow,String schDeptFlow){
		model.addAttribute("role",role);
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-5);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		//所有科室
		List<SysDept> deptList = deptBiz.searchDeptByOrg(user.getOrgFlow());
		model.addAttribute("deptList",deptList);
		if(doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			paramMap.put("doctorTypeIdList",Arrays.asList(doctorTypeIdList));
			model.addAttribute("doctorTypeIdList",Arrays.asList(doctorTypeIdList));//上一次选中的条件传到页面上
		}
		paramMap.put("doctorName",resDoctor.getDoctorName());
		paramMap.put("trainingSpeId",resDoctor.getTrainingSpeId());
		paramMap.put("sessionNumber",resDoctor.getSessionNumber());
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("inHosDate",inHosDate);
		paramMap.put("trainingYears",trainingYears);
		paramMap.put("deptFlow",deptFlow);
		paramMap.put("onlyMiss",onlyMiss);
		paramMap.put("onlyAbsence",onlyAbsence);

		if("doctor".equals(role)){
			paramMap.put("doctorFlow",user.getUserFlow());
		}else if("teacher".equals(role)){
			///规秘负责的轮转科室
			List<SchDept> schDeptList = schDeptBiz.userSchDept(user.getUserFlow());
			model.addAttribute("schDeptList",schDeptList);
			paramMap.put("teacherUserFlow",user.getUserFlow());
		}else if("head".equals(role)){
			//规秘负责的轮转科室
			List<SchDept> schDeptList = schDeptBiz.userSchDept(user.getUserFlow());
			model.addAttribute("schDeptList",schDeptList);
			paramMap.put("schDeptList",schDeptList);
		}else if("speBase".equals(role)){
			//所有轮转科室
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(user.getOrgFlow());
			model.addAttribute("schDeptList",schDeptList);
			paramMap.put("speId",user.getResTrainingSpeId());
		}else if("manager".equals(role)){
			//所有轮转科室
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(user.getOrgFlow());
			model.addAttribute("schDeptList",schDeptList);
			paramMap.put("orgFlow",user.getOrgFlow());
		}
		List<ResDoctor> doctorList = doctorBiz.searchDoctor4Kq(paramMap);
		//查询所有搭边儿的轮转记录 然后去头去尾
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SchArrangeResult> schArrangeResults = schArrangeResultBiz.searchArrangeResultByDateAndDoctorFlows(startDate,endDate,doctorList,schDeptFlow);

		Map<String,String> resultMap = new HashMap<>();
		Map<String,Double> sumMap = new HashMap<>();
		Map<String,Object> sumAllMap = new HashMap<>();
		Map<String,Object> percentMap = new HashMap<>();
		Map<String,Object> attendMap = new HashMap<>();
		Map<String,Object> doctorMap = new HashMap<>();
		if(schArrangeResults!=null&&schArrangeResults.size()>0){
			List<Map<String,String>> kqMap =null;
			int i=0;
			for(SchArrangeResult result:schArrangeResults){
				String doctorFlow = result.getDoctorFlow();
				for(ResDoctor doctor : doctorList){
					if(doctor.getDoctorFlow().equals(doctorFlow)){
						doctorMap.put(doctorFlow,doctor);
					}
				}
				if(i==0){
					result.setSchStartDate(startDate);
				}
				if(i==schArrangeResults.size()-1){
					result.setSchEndDate(endDate);
				}
				Long sumAttendantDay = 1-DateUtil.signDaysBetweenTowDate(result.getSchStartDate(),result.getSchEndDate());//总天数
				sumAllMap.put(result.getResultFlow(),sumAttendantDay);
				kqMap = zseyHrKqMonthBiz.searchKq(result.getSchStartDate(),result.getSchEndDate(),doctorFlow);
				if(kqMap!=null&&kqMap.size()>0){
					double sum = 0;
					for(Map<String,String> kq :kqMap){
						String absenceTypeId = kq.get("ABSENCE_TYPE_ID");
						String num = kq.get("NUM");
						if("03".equals(absenceTypeId)||"05".equals(absenceTypeId)||"07".equals(absenceTypeId)||"09".equals(absenceTypeId)||"21".equals(absenceTypeId)||"22".equals(absenceTypeId)){
							sum+=Double.parseDouble(num)/2;
						}else {
							sum+=Double.parseDouble(num);
						}
						resultMap.put(result.getResultFlow()+absenceTypeId,num);
						sumMap.put(result.getResultFlow(),sum);
					}
					DecimalFormat df = new DecimalFormat("#0%");
					String percent = df.format((double)(sumAttendantDay-sum)/(double)sumAttendantDay);
					percentMap.put(result.getResultFlow(),percent);
					attendMap.put(result.getResultFlow(),sumAttendantDay.doubleValue()-sum);
				}
				i++;
			}
		}
		model.addAttribute("schArrangeResults",schArrangeResults);
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("sumMap",sumMap);
		model.addAttribute("sumAllMap",sumAllMap);
		model.addAttribute("percentMap",percentMap);
		model.addAttribute("attendMap",attendMap);
		model.addAttribute("doctorMap",doctorMap);
		return "res/zsrykqmonth/listDept";
	}

	@RequestMapping("/exportExl2/{role}")
	public void exportExl2(@PathVariable String role, ResDoctor resDoctor, Model model, HttpServletResponse response,Integer currentPage
			,String startDate,String endDate,String[] doctorTypeIdList,String onlyMiss,String graduationYear,String onlyAbsence,
						   String inHosDate,String trainingYears,String deptFlow,String schDeptFlow) throws Exception {
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-5);
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		//所有科室
		if(doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			paramMap.put("doctorTypeIdList",Arrays.asList(doctorTypeIdList));
		}
		paramMap.put("doctorName",resDoctor.getDoctorName());
		paramMap.put("trainingSpeId",resDoctor.getTrainingSpeId());
		paramMap.put("sessionNumber",resDoctor.getSessionNumber());
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("inHosDate",inHosDate);
		paramMap.put("trainingYears",trainingYears);
		paramMap.put("deptFlow",deptFlow);
		paramMap.put("onlyMiss",onlyMiss);
		paramMap.put("onlyAbsence",onlyAbsence);

		if("doctor".equals(role)){
			paramMap.put("doctorFlow",user.getUserFlow());
		}else if("teacher".equals(role)){
			paramMap.put("teacherUserFlow",user.getUserFlow());
		}else if("head".equals(role)){
			//规秘负责的轮转科室
			List<SchDept> schDeptList = schDeptBiz.userSchDept(user.getUserFlow());
			paramMap.put("schDeptList",schDeptList);
		}else if("speBase".equals(role)){
			paramMap.put("speId",user.getResTrainingSpeId());
		}else if("manager".equals(role)){
			paramMap.put("orgFlow",user.getOrgFlow());
		}
		List<ResDoctor> doctorList = doctorBiz.searchDoctor4Kq(paramMap);
		//查询所有搭边儿的轮转记录 然后去头去尾
		List<SchArrangeResult> schArrangeResults = schArrangeResultBiz.searchArrangeResultByDateAndDoctorFlows(startDate,endDate,doctorList,schDeptFlow);

		List<Map<String,Object>> exportMapList = new ArrayList<>();

		Map<String,String> resultMap = new HashMap<>();
		Map<String,Double> sumMap = new HashMap<>();
		Map<String,Object> sumAllMap = new HashMap<>();
		Map<String,Object> percentMap = new HashMap<>();
		Map<String,Object> attendMap = new HashMap<>();
		Map<String,Object> doctorMap = new HashMap<>();
		if(schArrangeResults!=null&&schArrangeResults.size()>0){
			List<Map<String,String>> kqMap =null;
			int i=0;
			for(SchArrangeResult result:schArrangeResults){
				String doctorFlow = result.getDoctorFlow();
				for(ResDoctor doctor : doctorList){
					if(doctor.getDoctorFlow().equals(doctorFlow)){
						doctorMap.put(doctorFlow,doctor);
					}
				}
				if(i==0){
					result.setSchStartDate(startDate);
				}
				if(i==schArrangeResults.size()-1){
					result.setSchEndDate(endDate);
				}
				Long sumAttendantDay = 1-DateUtil.signDaysBetweenTowDate(result.getSchStartDate(),result.getSchEndDate());//总天数
				sumAllMap.put(result.getResultFlow(),sumAttendantDay.doubleValue());
				kqMap = zseyHrKqMonthBiz.searchKq(result.getSchStartDate(),result.getSchEndDate(),doctorFlow);
				if(kqMap!=null&&kqMap.size()>0){
					double sum = 0;
					for(Map<String,String> kq :kqMap){
						String absenceTypeId = kq.get("ABSENCE_TYPE_ID");
						String num = kq.get("NUM");
						if("03".equals(absenceTypeId)||"05".equals(absenceTypeId)||"07".equals(absenceTypeId)||"09".equals(absenceTypeId)||"21".equals(absenceTypeId)||"22".equals(absenceTypeId)){
							sum+=Double.parseDouble(num)/2;
						}else {
							sum+=Double.parseDouble(num);
						}
						resultMap.put(result.getResultFlow()+absenceTypeId,num);
						sumMap.put(result.getResultFlow(),sum);
					}
					DecimalFormat df = new DecimalFormat("#0%");
					String percent = df.format((double)(sumAttendantDay-sum)/(double)sumAttendantDay);
					percentMap.put(result.getResultFlow(),percent);
					attendMap.put(result.getResultFlow(),sumAttendantDay.doubleValue()-sum);
				}
				i++;
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("kqDate",startDate+"~"+endDate);
				subMap.put("doctorName",result.getDoctorName());
				subMap.put("sessionNumber",result.getSessionNumber());
				if(HBRecDocTypeEnum.Company.getId().equals(((ResDoctor)doctorMap.get(result.getDoctorFlow())).getDoctorTypeId())){
					subMap.put("departMentName",((ResDoctor)doctorMap.get(result.getDoctorFlow())).getDepartMentName());
				}else if(HBRecDocTypeEnum.Social.getId().equals(((ResDoctor)doctorMap.get(result.getDoctorFlow())).getDoctorTypeId())){
					subMap.put("departMentName","--");
				}else{
					subMap.put("departMentName",((ResDoctor)doctorMap.get(result.getDoctorFlow())).getWorkOrgName());
				}
				subMap.put("trainingSpeName",((ResDoctor)doctorMap.get(result.getDoctorFlow())).getTrainingSpeName());
				subMap.put("trainingYears",((ResDoctor)doctorMap.get(result.getDoctorFlow())).getTrainingYears());
				subMap.put("schDeptName",result.getSchDeptName());
				subMap.put("schDate",result.getSchStartDate()+"~"+result.getSchEndDate());
				double content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"02"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"03"),"0"))/2;
				subMap.put("0203",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"04"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"05"),"0"))/2;
				subMap.put("0405",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"06"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"07"),"0"))/2;
				subMap.put("0607",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"10"),"0"));
				subMap.put("10",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"11"),"0"));
				subMap.put("11",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"12"),"0"));
				subMap.put("12",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"13"),"0"));
				subMap.put("13",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"15"),"0"));
				subMap.put("15",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"16"),"0"));
				subMap.put("16",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"18"),"0"));
				subMap.put("18",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"19"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"22"),"0"))/2;
				subMap.put("1922",String.valueOf(content));
				content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"20"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"21"),"0"))/2;
				subMap.put("2021",String.valueOf(content));
				subMap.put("sum",String.valueOf(sumMap.get(result.getResultFlow())));
				subMap.put("sumAttendant",String.valueOf(attendMap.get(result.getResultFlow())));
				subMap.put("sumAll",String.valueOf(sumAllMap.get(result.getResultFlow())));
				subMap.put("percent",percentMap.get(result.getResultFlow()));
				exportMapList.add(subMap);
			}
			String fileName = "考勤信息统计.xls";
			String titles[] = {
					"kqDate:考勤时间",
					"doctorName:姓名",
					"sessionNumber:年级",
					"departMentName:所属科室",
					"trainingSpeName:培训专业",
					"trainingYears:培训年限",
					"schDeptName:轮转科室",
					"schDate:轮转时间",
					"0203:病假",
					"0405:事假",
					"0607:带薪年假",
					"10:婚假",
					"11:产假",
					"12:陪产假",
					"13:计生假",
					"15:出国",
					"16:进修",
					"18:脱产读研",
					"1922:放射假",
					"2021:旷工",
					"sum:请假合计",
					"sumAttendant:出勤",
					"sumAll:考勤天数",
					"percent:出勤率"
			};
			fileName = URLEncoder.encode(fileName, "UTF-8");
			ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

	//个人详情
	@RequestMapping("/detail")
	public String detail(String doctorFlow,String startDate,String endDate,Model model){
		//查询所有搭边儿的轮转记录 然后去头去尾
		List<SchArrangeResult> schArrangeResults = schArrangeResultBiz.searchArrangeResultByDateAndDoctorFlow(startDate,endDate,doctorFlow);
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		String speName = doctor.getTrainingSpeName();
		model.addAttribute("speName",speName);
		model.addAttribute("doctor",doctor);
		Map<String,String> resultMap = new HashMap<>();
		Map<String,Double> sumMap = new HashMap<>();
		Map<String,Object> sumAllMap = new HashMap<>();
		Map<String,Object> percentMap = new HashMap<>();
		Map<String,Object> attendMap = new HashMap<>();
		if(schArrangeResults!=null&&schArrangeResults.size()>0){
			List<Map<String,String>> kqMap =null;
			int i=0;
			for(SchArrangeResult result:schArrangeResults){
				if(i==0){
					result.setSchStartDate(startDate);
				}
				if(i==schArrangeResults.size()-1){
					result.setSchEndDate(endDate);
				}
				Long sumAttendantDay = 1-DateUtil.signDaysBetweenTowDate(result.getSchStartDate(),result.getSchEndDate());//总天数
				sumAllMap.put(result.getResultFlow(),sumAttendantDay);
				kqMap = zseyHrKqMonthBiz.searchKq(result.getSchStartDate(),result.getSchEndDate(),doctorFlow);
				if(kqMap!=null&&kqMap.size()>0){
					double sum = 0;
					for(Map<String,String> kq :kqMap){
						String absenceTypeId = kq.get("ABSENCE_TYPE_ID");
						String num = kq.get("NUM");
						if("03".equals(absenceTypeId)||"05".equals(absenceTypeId)||"07".equals(absenceTypeId)||"09".equals(absenceTypeId)||"21".equals(absenceTypeId)||"22".equals(absenceTypeId)){
							sum+=Double.parseDouble(num)/2;
						}else {
							sum+=Double.parseDouble(num);
						}
						resultMap.put(result.getResultFlow()+absenceTypeId,num);
						sumMap.put(result.getResultFlow(),sum);
					}
					DecimalFormat df = new DecimalFormat("#0%");
					String percent = df.format((double)(sumAttendantDay-sum)/(double)sumAttendantDay);
					percentMap.put(result.getResultFlow(),percent);
					attendMap.put(result.getResultFlow(),sumAttendantDay.doubleValue()-sum);
				}
				i++;
			}
		}
		model.addAttribute("schArrangeResults",schArrangeResults);
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("sumMap",sumMap);
		model.addAttribute("sumAllMap",sumAllMap);
		model.addAttribute("percentMap",percentMap);
		model.addAttribute("attendMap",attendMap);
		return "res/zsrykqmonth/detail";
	}

	@RequestMapping("/exportDetailExl")
	public void exportDetailExl(String doctorFlow,String startDate,String endDate, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<SchArrangeResult> schArrangeResults = schArrangeResultBiz.searchArrangeResultByDateAndDoctorFlow(startDate,endDate,doctorFlow);
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		String speName = doctor.getTrainingSpeName();
		List<Map<String,Object>> exportMapList = new ArrayList<>();
		Map<String,String> resultMap = new HashMap<>();
		Map<String,Double> sumMap = new HashMap<>();
		Map<String,Object> sumAllMap = new HashMap<>();
		Map<String,Object> percentMap = new HashMap<>();
		Map<String,Object> attendMap = new HashMap<>();
		if(schArrangeResults!=null&&schArrangeResults.size()>0){
			List<Map<String,String>> kqMap =null;
			int i=0;
			for(SchArrangeResult result:schArrangeResults){
				if(i==0){
					result.setSchStartDate(startDate);
				}
				if(i==schArrangeResults.size()-1){
					result.setSchEndDate(endDate);
				}
				Long sumAttendantDay = 1-DateUtil.signDaysBetweenTowDate(result.getSchStartDate(),result.getSchEndDate());//总天数
				sumAllMap.put(result.getResultFlow(),sumAttendantDay.doubleValue());
				kqMap = zseyHrKqMonthBiz.searchKq(result.getSchStartDate(),result.getSchEndDate(),doctorFlow);
				if(kqMap!=null&&kqMap.size()>0){
					double sum = 0;
					for(Map<String,String> kq :kqMap){
						String absenceTypeId = kq.get("ABSENCE_TYPE_ID");
						String num = kq.get("NUM");
						if("03".equals(absenceTypeId)||"05".equals(absenceTypeId)||"07".equals(absenceTypeId)||"09".equals(absenceTypeId)||"21".equals(absenceTypeId)||"22".equals(absenceTypeId)){
							sum+=Double.parseDouble(num)/2;
						}else {
							sum+=Double.parseDouble(num);
						}
						resultMap.put(result.getResultFlow()+absenceTypeId,num);
						sumMap.put(result.getResultFlow(),sum);
					}
					DecimalFormat df = new DecimalFormat("#0%");
					String percent = df.format((double)(sumAttendantDay-sum)/(double)sumAttendantDay);
					percentMap.put(result.getResultFlow(),percent);
					attendMap.put(result.getResultFlow(),sumAttendantDay.doubleValue()-sum);
				}
				i++;
			Map<String,Object> subMap = new HashMap<>();
			subMap.put("doctorName",result.getDoctorName());
			subMap.put("sessionNumber",result.getSessionNumber());
			subMap.put("speName",speName);
			if(HBRecDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())){
				subMap.put("departMentName",(doctor.getDepartMentName()));
			}else if(HBRecDocTypeEnum.Social.getId().equals(doctor.getDoctorTypeId())){
				subMap.put("departMentName","--");
			}else{
				subMap.put("departMentName",(doctor.getWorkOrgName()));
			}
			subMap.put("trainingSpeId",doctor.getTrainingSpeId());
			subMap.put("trainingYears",doctor.getTrainingYears());
			subMap.put("inHosDate",doctor.getInHosDate());
			subMap.put("graduationYear",doctor.getGraduationYear());
			subMap.put("schDeptName",result.getSchDeptName());
			subMap.put("schStartDate",result.getSchStartDate());
			subMap.put("schEndDate",result.getSchEndDate());
			double content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"02"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"03"),"0"))/2;
			subMap.put("0203",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"04"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"05"),"0"))/2;
			subMap.put("0405",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"06"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"07"),"0"))/2;
			subMap.put("0607",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"10"),"0"));
			subMap.put("10",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"11"),"0"));
			subMap.put("11",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"12"),"0"));
			subMap.put("12",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"13"),"0"));
			subMap.put("13",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"15"),"0"));
			subMap.put("15",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"16"),"0"));
			subMap.put("16",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"18"),"0"));
			subMap.put("18",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"19"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"22"),"0"))/2;
			subMap.put("1922",String.valueOf(content));
			content = Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"20"),"0"))+	Double.parseDouble(StringUtil.defaultIfEmpty(resultMap.get(result.getResultFlow()+"21"),"0"))/2;
			subMap.put("2021",String.valueOf(content));
			subMap.put("sum",String.valueOf(sumMap.get(result.getResultFlow())));
			subMap.put("attend",String.valueOf(attendMap.get(result.getResultFlow())));
			subMap.put("sumAll",String.valueOf(sumAllMap.get(result.getResultFlow())));
			subMap.put("percent",percentMap.get(result.getResultFlow()));
			exportMapList.add(subMap);
			}
		}
			String fileName = "考勤信息详情.xls";
			String titles[] = {
					"doctorName:姓名",
					"sessionNumber:年级",
					"departMentName:所属科室",
					"speName:培训专业",
					"trainingYears:培训年限",
					"inHosDate:入培时间",
					"graduationYear:结业时间",
					"schDeptName:轮转科室",
					"schStartDate:轮转开始时间",
					"schEndDate:轮转结束时间",
					"0203:病假",
					"0405:事假",
					"0607:带薪年假",
					"10:婚假",
					"11:产假",
					"12:陪产假",
					"13:计生假",
					"15:出国",
					"16:进修",
					"18:脱产读研",
					"1922:放射假",
					"2021:旷工",
					"sum:请假合计",
					"attend:出勤",
					"sumAll:考勤天数",
					"percent:出勤率"
			};
			fileName = URLEncoder.encode(fileName, "UTF-8");
			ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
		}

		@RequestMapping("/absenseMark/{role}")
		public String absenseMark(@PathVariable String role,Model model,String kqDate,String userName,String sessionNumber,String trainingSpeId,
								  String trainingYears,String schDeptFlow,String graduationYear,String[] doctorTypeList,String onlyMiss,String onlyAbsence,
								  String inHosDate,Integer currentPage,HttpServletRequest request,String deptFlow){
			model.addAttribute("role",role);
			SysUser currentUser = GlobalContext.getCurrentUser();
			Map<String,Object> paramMap = new HashMap<>();
			//所有科室
			List<SysDept> deptList = deptBiz.searchDeptByOrg(currentUser.getOrgFlow());
			model.addAttribute("deptList",deptList);
			//规秘负责的轮转科室
			List<SchDept> schDeptList = schDeptBiz.userSchDept(currentUser.getUserFlow());
			model.addAttribute("schDeptList",schDeptList);
			List<String> ownDepts = new ArrayList<>();
			if(schDeptList!=null&&schDeptList.size()>0){
				for(SchDept dept:schDeptList){
					String flow = dept.getSchDeptFlow();
					ownDepts.add(flow);
				}
			}
			paramMap.put("schDeptList",ownDepts);

			paramMap.put("deptFlow",deptFlow);
			if(StringUtil.isBlank(kqDate)){
				kqDate = DateUtil.getCurrDate().substring(0,7);
				model.addAttribute("kqDate",kqDate);
			}
			if("headSecretary".equals(role)){
				//只能修改当月数据的标识
				boolean isCurrMonth = false;
				String date = DateUtil.getCurrDate();
				date = date.substring(0,7);
				if(kqDate.equals(date)){
					isCurrMonth = true;
				}
				model.addAttribute("isCurrMonth",isCurrMonth);
			}
			paramMap.put("KqDate",kqDate);
			paramMap.put("userName",userName);
			paramMap.put("sessionNumber",sessionNumber);
			paramMap.put("trainingSpeId",trainingSpeId);
			paramMap.put("trainingYears",trainingYears);
			paramMap.put("schDeptFlow",schDeptFlow);
			paramMap.put("graduationYear",graduationYear);
			paramMap.put("onlyAbsence",onlyAbsence);
			if(doctorTypeList!=null&&doctorTypeList.length>0){
				paramMap.put("doctorTypeList",Arrays.asList(doctorTypeList));
				model.addAttribute("doctorTypeList",Arrays.asList(doctorTypeList));
			}else{
				List<String> paramList = new ArrayList<>();
				List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("DoctorType");
				for(SysDict s:dictList){
					paramList.add(s.getDictId());
				}
				paramMap.put("doctorTypeIdList",paramList);
			}
			paramMap.put("inHosDate",inHosDate);
			paramMap.put("onlyMiss",onlyMiss);
			DecimalFormat df= new DecimalFormat("00");
			List<String> Mlist = new ArrayList<>();
			List<String> Mlist2 = new ArrayList<>();
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, Integer.parseInt(kqDate.split("-")[0])); // 年
			c.set(Calendar.MONTH, Integer.parseInt(kqDate.split("-")[1])-1); // 月
			int num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			for(int i=1;i<=num;i++){
				String singleM = "M"+df.format(i);
				Mlist.add(singleM);
				Mlist2.add(df.format(i));
			}
			paramMap.put("Mlist",Mlist);
			model.addAttribute("Mlist",Mlist);
			model.addAttribute("Mlist2",Mlist2);
			PageHelper.startPage(currentPage,getPageSize(request));
			List<Map<String,Object>> resultMapList = zseyHrKqMonthBiz.searchProcessByMonth(paramMap);

			model.addAttribute("resultMapList",resultMapList);
			return "res/zsrykqmonth/absenceMark";
		}

		@RequestMapping("/editAbsence")
		@ResponseBody
		public int editAbsence(ZseyHrKqMonth zseyHrKqMonth){
			if(StringUtil.isBlank(zseyHrKqMonth.getResType())){
				zseyHrKqMonth.setResType("2");
			}
			return zseyHrKqMonthBiz.edit(zseyHrKqMonth);
		}

		@RequestMapping("/kqList/{role}")
		public String kqListView(@PathVariable String role,Model model){
			model.addAttribute("role",role);
			return "res/zsrykqmonth/kqList";
		}

		@RequestMapping("/kqList/time/{role}")
		public String kqListTime(String startTime,String endTime,String userName,String sessionNumber,String trainingSpeId,
				String onlyAbsence,String trainingYears,String graduationYear,String[] doctorTypeList,String onlyMiss,Model model,
							 String inHosDate,Integer currentPage,HttpServletRequest request,String deptFlow,
								 @PathVariable String role) throws ParseException {
			model.addAttribute("role",role);
			Map<String,Object> paramMap = new HashMap<>();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			if(StringUtil.isBlank(startTime)||StringUtil.isBlank(endTime)){
				Date currentDate = new Date();
				startTime = sdf.format(currentDate);
				endTime = startTime;
				model.addAttribute("startTime",startTime);
				model.addAttribute("endTime",endTime);
			}
			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);
			List<Date> dateList = new ArrayList<>();
			dateList.add(startDate);
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(startDate);
			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTime(endDate);
			while (endDate.after(tempStart.getTime())) {
				tempStart.add(Calendar.MONTH, 1);
				dateList.add(tempStart.getTime());
			}
			List<String> paramDates = new ArrayList<>();
			if(dateList!=null&&dateList.size()>0){
				for(Date date : dateList){
					paramDates.add(sdf.format(date));
				}
			}
			paramMap.put("paramDates",paramDates);
			SysUser currentUser = GlobalContext.getCurrentUser();
			//所有科室
			List<SysDept> deptList = deptBiz.searchDeptByOrg(currentUser.getOrgFlow());
			model.addAttribute("deptList",deptList);
			paramMap.put("userName",userName);
			paramMap.put("sessionNumber",sessionNumber);
			paramMap.put("trainingSpeId",trainingSpeId);
			paramMap.put("trainingYears",trainingYears);
			paramMap.put("graduationYear",graduationYear);
			paramMap.put("onlyAbsence",onlyAbsence);
			paramMap.put("deptFlow",deptFlow);
			if(doctorTypeList!=null&&doctorTypeList.length>0){
				paramMap.put("doctorTypeList",Arrays.asList(doctorTypeList));
				model.addAttribute("doctorTypeList",Arrays.asList(doctorTypeList));
			}else{
				List<String> paramList = new ArrayList<>();
				List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("DoctorType");
				for(SysDict s:dictList){
					paramList.add(s.getDictId());
				}
				paramMap.put("doctorTypeIdList",paramList);
			}
			paramMap.put("inHosDate",inHosDate);
			paramMap.put("onlyMiss",onlyMiss);

			DecimalFormat df= new DecimalFormat("00");
			List<String> Mlist = new ArrayList<>();
			List<String> Mlist2 = new ArrayList<>();

			for(int i=1;i<=31;i++){
				String singleM = "M"+df.format(i);
				Mlist.add(singleM);
				Mlist2.add(df.format(i));
			}
			paramMap.put("Mlist",Mlist);
			model.addAttribute("Mlist",Mlist);
			model.addAttribute("Mlist2",Mlist2);
			if("head".equals(role)){
				//规秘负责的轮转科室
				List<SchDept> schDeptList = schDeptBiz.userSchDept(currentUser.getUserFlow());
				paramMap.put("schDeptList",schDeptList);
			}else if("doctor".equals(role)){
				paramMap.put("userFlow",currentUser.getUserFlow());
			}else if("teacher".equals(role)){
				paramMap.put("teacherUserFlow",currentUser.getUserFlow());
			}else if("speBase".equals(role)){
				paramMap.put("speId",currentUser.getResTrainingSpeId());
			}else if("manager".equals(role)){
				paramMap.put("orgFlow",currentUser.getOrgFlow());
			}
			PageHelper.startPage(currentPage,getPageSize(request));
			List<Map<String,Object>> resultMapList = zseyHrKqMonthBiz.searchProcessByTime(paramMap);

			model.addAttribute("resultMapList",resultMapList);
			return "res/zsrykqmonth/kqListByTime";
		}

	@RequestMapping("/exportKqListTime/{role}")
	public void exportKqListTime(String startTime,String endTime,String userName,String sessionNumber,String trainingSpeId,
							 String onlyAbsence,String trainingYears,String graduationYear,String[] doctorTypeList,String onlyMiss,
							 String inHosDate,HttpServletResponse response,String deptFlow,
							 @PathVariable String role) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		if(StringUtil.isBlank(startTime)||StringUtil.isBlank(endTime)){
			Date currentDate = new Date();
			startTime = sdf.format(currentDate);
			endTime = startTime;
		}
		Date startDate = sdf.parse(startTime);
		Date endDate = sdf.parse(endTime);
		List<Date> dateList = new ArrayList<>();
		dateList.add(startDate);
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(startDate);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(endDate);
		while (endDate.after(tempStart.getTime())) {
			tempStart.add(Calendar.MONTH, 1);
			dateList.add(tempStart.getTime());
		}
		List<String> paramDates = new ArrayList<>();
		if(dateList!=null&&dateList.size()>0){
			for(Date date : dateList){
				paramDates.add(sdf.format(date));
			}
		}
		paramMap.put("paramDates",paramDates);
		SysUser currentUser = GlobalContext.getCurrentUser();
		//所有科室
		paramMap.put("userName",userName);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("trainingYears",trainingYears);
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("onlyAbsence",onlyAbsence);
		paramMap.put("deptFlow",deptFlow);
		if(doctorTypeList!=null&&doctorTypeList.length>0){
			paramMap.put("doctorTypeList",Arrays.asList(doctorTypeList));
		}else{
			List<String> paramList = new ArrayList<>();
			List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("DoctorType");
			for(SysDict s:dictList){
				paramList.add(s.getDictId());
			}
			paramMap.put("doctorTypeIdList",paramList);
		}
		paramMap.put("inHosDate",inHosDate);
		paramMap.put("onlyMiss",onlyMiss);

		DecimalFormat df= new DecimalFormat("00");
		List<String> Mlist = new ArrayList<>();
		List<String> Mlist2 = new ArrayList<>();

		for(int i=1;i<=31;i++){
			String singleM = "M"+df.format(i);
			Mlist.add(singleM);
			Mlist2.add(df.format(i));
		}
		paramMap.put("Mlist",Mlist);
		if("head".equals(role)){
			//规秘负责的轮转科室
			List<SchDept> schDeptList = schDeptBiz.userSchDept(currentUser.getUserFlow());
			paramMap.put("schDeptList",schDeptList);
		}else if("doctor".equals(role)){
			paramMap.put("userFlow",currentUser.getUserFlow());
		}else if("teacher".equals(role)){
			paramMap.put("teacherUserFlow",currentUser.getUserFlow());
		}else if("speBase".equals(role)){
			paramMap.put("speId",currentUser.getResTrainingSpeId());
		}else if("manager".equals(role)){
			paramMap.put("orgFlow",currentUser.getOrgFlow());
		}
		List<Map<String,Object>> resultMapList = zseyHrKqMonthBiz.searchProcessByTime(paramMap);
		if(resultMapList!=null&&resultMapList.size()>0){
			for(Map<String,Object> subMap:resultMapList){
				String KQ_DATE = startTime+"~"+endTime;
				subMap.put("KQ_DATE",KQ_DATE);
				if("Company".equals(subMap.get("DOCTOR_TYPE_ID"))){
				}else if("Social".equals(subMap.get("DOCTOR_TYPE_ID"))){
					subMap.put("DEPART_MENT_NAME","-");
				}else{
					subMap.put("DEPART_MENT_NAME",subMap.get("WORK_ORG_NAME"));
				}
				for(int i=1;i<=31;i++){
					String singleM = "M"+df.format(i);
					String code = (String)subMap.get(singleM);
					subMap.put(singleM,transform(code));
				}
			}
		}
		String fileName = "考勤查询（按时间）.xls";
		String titles[] = {
				"KQ_DATE:考勤年月",
				"USER_NAME:姓名",
				"SESSION_NUMBER:年级",
				"DEPART_MENT_NAME:所属科室",
				"TRAINING_SPE_NAME:培训专业",
				"TRAINING_YEARS:培训年限",
				"IN_HOS_DATE:入培时间",
				"GRADUATION_YEAR:结业时间",
				"M01:01",
				"M02:02",
				"M03:03",
				"M04:04",
				"M05:05",
				"M06:06",
				"M07:07",
				"M08:08",
				"M09:09",
				"M10:10",
				"M11:11",
				"M12:12",
				"M13:13",
				"M14:14",
				"M15:15",
				"M16:16",
				"M17:17",
				"M18:18",
				"M19:19",
				"M20:20",
				"M21:21",
				"M22:22",
				"M23:23",
				"M24:24",
				"M25:25",
				"M26:26",
				"M27:27",
				"M28:28",
				"M29:29",
				"M30:30",
				"M31:31"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/kqList/dept/{role}")
	public String kqListDept(String startTime,String endTime,String userName,String sessionNumber,String trainingSpeId,String schDeptFlow,
						 String onlyAbsence,String trainingYears,String graduationYear,String[] doctorTypeList,String onlyMiss,Model model,
						 String inHosDate,Integer currentPage,HttpServletRequest request,String deptFlow,
							 @PathVariable String role) throws ParseException {
		model.addAttribute("role",role);
		Map<String,Object> paramMap = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		if(StringUtil.isBlank(startTime)||StringUtil.isBlank(endTime)){
			Date currentDate = new Date();
			startTime = sdf.format(currentDate);
			endTime = startTime;
			model.addAttribute("startTime",startTime);
			model.addAttribute("endTime",endTime);
		}
		paramMap.put("startTime",startTime);
		paramMap.put("endTime",endTime);

		//所有科室
		SysUser currentUser = GlobalContext.getCurrentUser();
		List<SysDept> deptList = deptBiz.searchDeptByOrg(currentUser.getOrgFlow());
		model.addAttribute("deptList",deptList);
		if("head".equals(role)){
			//规秘负责的轮转科室
			List<SchDept> schDeptList = schDeptBiz.userSchDept(currentUser.getUserFlow());
			paramMap.put("schDeptList",schDeptList);
			model.addAttribute("schDeptList",schDeptList);
		}else if("doctor".equals(role)){
			paramMap.put("userFlow",currentUser.getUserFlow());
		}else if("teacher".equals(role)){
			//规秘负责的轮转科室
			List<SchDept> schDeptList = schDeptBiz.userSchDept(currentUser.getUserFlow());
			paramMap.put("schDeptList",schDeptList);
			paramMap.put("teacherUserFlow",currentUser.getUserFlow());
		}else if("speBase".equals(role)){
			//所有轮转科室
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currentUser.getOrgFlow());
			model.addAttribute("schDeptList",schDeptList);
			paramMap.put("speId",currentUser.getResTrainingSpeId());
		}else if("manager".equals(role)){
			//所有轮转科室
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currentUser.getOrgFlow());
			model.addAttribute("schDeptList",schDeptList);
			paramMap.put("orgFlow",currentUser.getOrgFlow());
		}
		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("userName",userName);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("trainingYears",trainingYears);
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("onlyAbsence",onlyAbsence);
		paramMap.put("deptFlow",deptFlow);
		if(doctorTypeList!=null&&doctorTypeList.length>0){
			paramMap.put("doctorTypeList",Arrays.asList(doctorTypeList));
			model.addAttribute("doctorTypeList",Arrays.asList(doctorTypeList));
		}else{
			List<String> paramList = new ArrayList<>();
			List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("DoctorType");
			for(SysDict s:dictList){
				paramList.add(s.getDictId());
			}
			paramMap.put("doctorTypeIdList",paramList);
		}
		paramMap.put("inHosDate",inHosDate);
		paramMap.put("onlyMiss",onlyMiss);

		DecimalFormat df= new DecimalFormat("00");
		List<String> Mlist = new ArrayList<>();
		List<String> Mlist2 = new ArrayList<>();

		for(int i=1;i<=31;i++){
			String singleM = "m"+df.format(i);
			Mlist.add(singleM);
			Mlist2.add(df.format(i));
		}
		paramMap.put("Mlist",Mlist);
		model.addAttribute("Mlist",Mlist);
		model.addAttribute("Mlist2",Mlist2);

//		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = zseyHrKqMonthBiz.searchProcessByDept(paramMap);
		model.addAttribute("resultMapList",resultMapList);

		Map<String,Object> monthMap = new HashMap<>();
		Map<String,Object> sizeMap = new HashMap<>();
		Map<String,Object> dateMap = new HashMap<>();
		if(resultMapList!=null&&resultMapList.size()>0){
			for(Map<String,Object> map:resultMapList){
				String processFlow = (String)map.get("PROCESS_FLOW");
				String userFlow = (String)map.get("USER_FLOW");
				String schStartDate = ((String)map.get("SCH_START_DATE")).substring(0,7);
				String schEndDate = ((String)map.get("SCH_END_DATE")).substring(0,7);
				Date start = sdf.parse(schStartDate);
				Date end = sdf.parse(schEndDate);
				List<Date> dList = new ArrayList<>();
				dList.add(start);
				Calendar tempS = Calendar.getInstance();
				tempS.setTime(start);
				Calendar tempE = Calendar.getInstance();
				tempE.setTime(end);
				while (end.after(tempS.getTime())) {
					tempS.add(Calendar.MONTH, 1);
					dList.add(tempS.getTime());
				}
				List<String> paraDates = new ArrayList<>();
				if(dList!=null&&dList.size()>0){
					for(Date date : dList){
						paraDates.add(sdf.format(date));
					}
				}
				List<ZseyHrKqMonth> kqMonths = zseyHrKqMonthBiz.searchKqByDates(paraDates,userFlow);
				sizeMap.put(processFlow,dList.size());
				dateMap.put(processFlow,paraDates);
				Map<String,Object> dataMap = new HashMap<>();
				if(kqMonths!=null&&kqMonths.size()>0){
					for(ZseyHrKqMonth month:kqMonths){
						String kqDate = month.getKqDate();
						dataMap.put(kqDate,month);
					}
				}
				monthMap.put(processFlow,dataMap);
			}
		}
		model.addAttribute("monthMap",monthMap);
		model.addAttribute("sizeMap",sizeMap);
		model.addAttribute("dateMap",dateMap);
		return "res/zsrykqmonth/kqListByDept";
		}

	@RequestMapping("/exportKqListDept/{role}")
	public void exportKqListDept(String startTime,String endTime,String userName,String sessionNumber,String trainingSpeId,String schDeptFlow,
								 String onlyAbsence,String trainingYears,String graduationYear,String[] doctorTypeList,String onlyMiss,
								 String inHosDate,Integer currentPage,HttpServletResponse response,String deptFlow,
								 @PathVariable String role) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		if(StringUtil.isBlank(startTime)||StringUtil.isBlank(endTime)){
			Date currentDate = new Date();
			startTime = sdf.format(currentDate);
			endTime = startTime;
		}
		paramMap.put("startTime",startTime);
		paramMap.put("endTime",endTime);

		//所有科室
		SysUser currentUser = GlobalContext.getCurrentUser();
		if("head".equals(role)){
			//规秘负责的轮转科室
			List<SchDept> schDeptList = schDeptBiz.userSchDept(currentUser.getUserFlow());
			paramMap.put("schDeptList",schDeptList);
		}else if("doctor".equals(role)){
			paramMap.put("userFlow",currentUser.getUserFlow());
		}else if("teacher".equals(role)){
			paramMap.put("teacherUserFlow",currentUser.getUserFlow());
		}else if("speBase".equals(role)){
			paramMap.put("speId",currentUser.getResTrainingSpeId());
		}else if("manager".equals(role)){
			paramMap.put("orgFlow",currentUser.getOrgFlow());
		}
		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("userName",userName);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("trainingYears",trainingYears);
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("onlyAbsence",onlyAbsence);
		paramMap.put("deptFlow",deptFlow);
		if(doctorTypeList!=null&&doctorTypeList.length>0){
			paramMap.put("doctorTypeList",Arrays.asList(doctorTypeList));
		}else{
			List<String> paramList = new ArrayList<>();
			List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("DoctorType");
			for(SysDict s:dictList){
				paramList.add(s.getDictId());
			}
			paramMap.put("doctorTypeIdList",paramList);
		}
		paramMap.put("inHosDate",inHosDate);
		paramMap.put("onlyMiss",onlyMiss);

		DecimalFormat df= new DecimalFormat("00");
		List<String> Mlist = new ArrayList<>();
		List<String> Mlist2 = new ArrayList<>();

		for(int i=1;i<=31;i++){
			String singleM = "m"+df.format(i);
			Mlist.add(singleM);
			Mlist2.add(df.format(i));
		}
		paramMap.put("Mlist",Mlist);

		List<Map<String,Object>> resultMapList = zseyHrKqMonthBiz.searchProcessByDept(paramMap);

		Map<String,Object> monthMap = new HashMap<>();
		Map<String,Object> sizeMap = new HashMap<>();
		Map<String,Object> dateMap = new HashMap<>();
		if(resultMapList!=null&&resultMapList.size()>0){
			for(Map<String,Object> map:resultMapList){
				String processFlow = (String)map.get("PROCESS_FLOW");
				String userFlow = (String)map.get("USER_FLOW");
				String schStartDate = ((String)map.get("SCH_START_DATE")).substring(0,7);
				String schEndDate = ((String)map.get("SCH_END_DATE")).substring(0,7);
				Date start = sdf.parse(schStartDate);
				Date end = sdf.parse(schEndDate);
				List<Date> dList = new ArrayList<>();
				dList.add(start);
				Calendar tempS = Calendar.getInstance();
				tempS.setTime(start);
				Calendar tempE = Calendar.getInstance();
				tempE.setTime(end);
				while (end.after(tempS.getTime())) {
					tempS.add(Calendar.MONTH, 1);
					dList.add(tempS.getTime());
				}
				List<String> paraDates = new ArrayList<>();
				if(dList!=null&&dList.size()>0){
					for(Date date : dList){
						paraDates.add(sdf.format(date));
					}
				}
				List<ZseyHrKqMonth> kqMonths = zseyHrKqMonthBiz.searchKqByDates(paraDates,userFlow);
				sizeMap.put(processFlow,dList.size());
				dateMap.put(processFlow,paraDates);
				Map<String,Object> dataMap = new HashMap<>();
				if(kqMonths!=null&&kqMonths.size()>0){
					for(ZseyHrKqMonth month:kqMonths){
						String kqDate = month.getKqDate();
						dataMap.put(kqDate,month);
					}
				}
				monthMap.put(processFlow,dataMap);
			}
		}

		List<Map<String,Object>> exportMap = new ArrayList<>();
		if(resultMapList!=null&&resultMapList.size()>0) {
			for (Map<String, Object> map : resultMapList) {
				String key1 = (String)map.get("PROCESS_FLOW");
				for(String date :(List<String>)dateMap.get(key1)){
					Map<String,Object> subMap = new HashMap<>();//打印的单条数据
					subMap.put("USER_NAME",map.get("USER_NAME"));
					subMap.put("SESSION_NUMBER",map.get("SESSION_NUMBER"));
					if("Company".equals(map.get("DOCTOR_TYPE_ID"))){
						subMap.put("DEPART_MENT_NAME",map.get("DEPART_MENT_NAME"));
					}else if("Social".equals(map.get("DOCTOR_TYPE_ID"))){
						subMap.put("DEPART_MENT_NAME","-");
					}else{
						subMap.put("DEPART_MENT_NAME",map.get("WORK_ORG_NAME"));
					}
					subMap.put("TRAINING_SPE_NAME",map.get("TRAINING_SPE_NAME"));
					subMap.put("TRAINING_YEARS",map.get("TRAINING_YEARS"));
					subMap.put("SCH_DEPT_NAME",map.get("SCH_DEPT_NAME"));
					subMap.put("SCH_START_DATE",map.get("SCH_START_DATE"));
					subMap.put("SCH_END_DATE",map.get("SCH_END_DATE"));
					subMap.put("IN_HOS_DATE",map.get("IN_HOS_DATE"));
					subMap.put("GRADUATION_YEAR",map.get("GRADUATION_YEAR"));
					subMap.put("KQ_DATE",date);
					for(int i=1;i<=31;i++){
						String single= df.format(i);
						String singleM = "M"+single;
						Map<String,Object> map1 = (Map<String,Object>)monthMap.get(key1);
						ZseyHrKqMonth month = (ZseyHrKqMonth)map1.get(date);
						if(null!=month){
							String code = "";
							switch (single) {
								case  "01" :{code= month.getM01();break;}
								case  "02" :{code= month.getM02();break;}
								case  "03" :{code= month.getM03();break;}
								case  "04" :{code= month.getM04();break;}
								case  "05" :{code= month.getM05();break;}
								case  "06" :{code= month.getM06();break;}
								case  "07" :{code= month.getM07();break;}
								case  "08" :{code= month.getM08();break;}
								case  "09" :{code= month.getM09();break;}
								case  "10" :{code= month.getM10();break;}
								case  "11" :{code= month.getM11();break;}
								case  "12" :{code= month.getM12();break;}
								case  "13" :{code= month.getM13();break;}
								case  "14" :{code= month.getM14();break;}
								case  "15" :{code= month.getM15();break;}
								case  "16" :{code= month.getM16();break;}
								case  "17" :{code= month.getM17();break;}
								case  "18" :{code= month.getM18();break;}
								case  "19" :{code= month.getM19();break;}
								case  "20" :{code= month.getM20();break;}
								case  "21" :{code= month.getM21();break;}
								case  "22" :{code= month.getM22();break;}
								case  "23" :{code= month.getM23();break;}
								case  "24" :{code= month.getM24();break;}
								case  "25" :{code= month.getM25();break;}
								case  "26" :{code= month.getM26();break;}
								case  "27" :{code= month.getM27();break;}
								case  "28" :{code= month.getM28();break;}
								case  "29" :{code= month.getM29();break;}
								case  "30" :{code= month.getM30();break;}
								case  "31" :{code= month.getM31();break;}
							}
							subMap.put(singleM,transform(code));
						}else {
							subMap.put(singleM,"出勤");
						}
					}
					exportMap.add(subMap);
				}
			}
		}
		String fileName = "考勤查询（按轮转科室）.xls";
		String titles[] = {
				"USER_NAME:姓名",
				"SESSION_NUMBER:年级",
				"DEPART_MENT_NAME:所属科室",
				"TRAINING_SPE_NAME:培训专业",
				"TRAINING_YEARS:培训年限",
				"SCH_DEPT_NAME:轮转科室",
				"SCH_START_DATE:轮转开始时间",
				"SCH_END_DATE:轮转结束时间",
				"IN_HOS_DATE:入培时间",
				"GRADUATION_YEAR:结业时间",
				"KQ_DATE:考勤年月",
				"M01:01",
				"M02:02",
				"M03:03",
				"M04:04",
				"M05:05",
				"M06:06",
				"M07:07",
				"M08:08",
				"M09:09",
				"M10:10",
				"M11:11",
				"M12:12",
				"M13:13",
				"M14:14",
				"M15:15",
				"M16:16",
				"M17:17",
				"M18:18",
				"M19:19",
				"M20:20",
				"M21:21",
				"M22:22",
				"M23:23",
				"M24:24",
				"M25:25",
				"M26:26",
				"M27:27",
				"M28:28",
				"M29:29",
				"M30:30",
				"M31:31"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMap, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

		public String transform(String code){
			String result = "";
			if(StringUtil.isBlank(code)){
				result = "出勤";
			}else{
				switch (code){
					case "04":{result="事假";break;}
					case "02":{result="病假";break;}
					case "11":{result="产假";break;}
					case "10":{result="婚假";break;}
					case "06":{result="年假";break;}
					case "08":{result="补休";break;}
					case "12":{result="陪产假";break;}
					case "13":{result="计生假";break;}
					case "14":{result="丧假";break;}
					case "15":{result="出国";break;}
					case "16":{result="进修";break;}
					case "18":{result="脱产读研";break;}
					case "19":{result="放射假";break;}
					case "20":{result="旷工";break;}
					case "00":{result="出勤";break;}
					case "03":{result="0.5天病假";break;}
					case "05":{result="0.5天事假";break;}
					case "07":{result="0.5天带薪年假";break;}
					case "09":{result="0.5天补休";break;}
					case "21":{result="0.5天旷工";break;}
					case "22":{result="0.5天放射假";break;}
				}
			}
			return result;
		}
	}


