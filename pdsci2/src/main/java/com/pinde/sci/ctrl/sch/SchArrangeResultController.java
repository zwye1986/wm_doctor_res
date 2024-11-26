package com.pinde.sci.ctrl.sch;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResEnterOpenCfgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.ctrl.sch.plan.domain.*;
import com.pinde.sci.ctrl.sch.plan.util.SchDateUtil;
import com.pinde.sci.enums.sch.SchSelYearEnum;
import com.pinde.sci.form.sch.RosterForm;
import com.pinde.sci.form.sch.RosterTime;
import com.pinde.sci.form.sch.SchArrangeForm;
import com.pinde.sci.form.sch.SchDoctorForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.DocumentException;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/sch/arrangeResult")
public class SchArrangeResultController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(SchArrangeResultController.class);

	private static ExecutorService solvingExecutor = Executors.newFixedThreadPool(4);
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ISchRotationCfgBiz rotationCfgBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchRotationGroupBiz schRotationtGroupBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResEnterOpenCfgBiz enterOpenCfgBiz;
	@Autowired
	private ISchDoctorSelectDeptBiz doctorDeptBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;


	@RequestMapping(value = "/main")
	public String main( HttpServletRequest request, Model model) {

		String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
		model.addAttribute("orgFlow",orgFlow);
		return "/sch/result/main";
	}

	@RequestMapping(value = {"/changRotation" })
	@ResponseBody
	public String changRotation (String speId,Model model) throws Exception{
		if(StringUtil.isBlank(speId))
		{
			return "[]";
		}
		List<SchRotation> rotations=schRotationtBiz.searchSchRotation(speId);
		return JSON.toJSONString(rotations);
	}
	@RequestMapping(value = {"/getRotationType" })
	@ResponseBody
	public String getRotationType (String rotationFlow,String sessionNumber,String orgFlow,Model model) throws Exception{
		if(StringUtil.isBlank(sessionNumber))
		{
			return "请选择年级！";
		}
		if(StringUtil.isBlank(rotationFlow))
		{
			return "请选择轮转方案！";
		}
		orgFlow = StringUtil.defaultIfEmpty(orgFlow, GlobalContext.getCurrentUser().getOrgFlow());
		SchRotationOrgCfg cfg=rotationCfgBiz.readOrgRotationCycleType(rotationFlow,orgFlow,sessionNumber);
		if(cfg==null)
		{
			return "";
		}else {
			return cfg.getCycleTypeId();
		}
	}
	@RequestMapping(value = {"/getRotationStartDate" })
	@ResponseBody
	public String getRotationStartDate (String sessionNumber,String orgFlow,Model model) throws Exception{
		if(StringUtil.isBlank(sessionNumber))
		{
			return "请选择年级！";
		}
		orgFlow = StringUtil.defaultIfEmpty(orgFlow, GlobalContext.getCurrentUser().getOrgFlow());
		SchArrangeTime old=enterOpenCfgBiz.getArrangeTimeByOrgYear(sessionNumber,orgFlow);
		if(old==null)
		{
			return "";
		}else {
			return old.getSessionNumber()+"-"+old.getStartDate();
		}
	}

	@RequestMapping(value = {"/students" })
	public String students (String doctorCategoryId,
							String sessionNumber,
							String trainingSpeId,
							String rotationFlow,
							String selectYear,
							Model model) throws Exception{

		if(SchSelYearEnum.One.getId().equals(selectYear))
		{
			selectYear="1";
		}
		if(SchSelYearEnum.Two.getId().equals(selectYear))
		{
			selectYear="2";
		}
		if(SchSelYearEnum.Three.getId().equals(selectYear))
		{
			selectYear="3";
		}
		SysUser user=GlobalContext.getCurrentUser();
		Map<String,String> params=new HashMap<>();
		params.put("doctorCategoryId",doctorCategoryId);
		params.put("sessionNumber",sessionNumber);
		params.put("trainingSpeId",trainingSpeId);
		params.put("trainingYears",selectYear);
		params.put("rotationFlow",rotationFlow);
		params.put("orgFlow",user.getOrgFlow());
		List<ResDoctorExt> users=doctorBiz.getStudents(params);
		model.addAttribute("users",users);
		return "sch/result/students";
	}

	@RequestMapping(value = {"/arrange/results"})
	public String results( String doctorFlow, Model model,String cycleTypeId,String selectYear) throws DocumentException {

		ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);
		//查询学员选科信息
		List<SchOrgArrangeResult> results=doctorDeptBiz.readArrangeResults(doctorFlow,doctor.getSessionNumber(),doctor.getRotationFlow(),doctor.getOrgFlow());
		List<Map<String,String>> minstartList=doctorDeptBiz.readOrgArrangeStartDate(doctorFlow,doctor.getSessionNumber(),doctor.getRotationFlow(),doctor.getOrgFlow());
		model.addAttribute("results",results);
		model.addAttribute("doctor",doctor);
		model.addAttribute("cycleTypeId",cycleTypeId);
		model.addAttribute("selectYear",selectYear);
		Map<String,String> startDateMap=new HashMap<>();
		if(minstartList!=null)
		{
			for(Map<String,String> map:minstartList)
			{
				startDateMap.put(map.get("selectYear"),map.get("schStartDate"));
				startDateMap.put(map.get("selectYear")+"MonthNum",map.get("monthNum"));
			}
		}
		model.addAttribute("startDateMap",startDateMap);
		if(results!=null)
		{
			Map<String,List<SchOrgArrangeResult>> resultMap=new HashMap<>();
			results.forEach(r->{
				List<SchOrgArrangeResult> temp=resultMap.get(r.getSelectYear());
				if(temp==null) temp=new ArrayList<SchOrgArrangeResult>();
				temp.add(r);
				resultMap.put(r.getSelectYear(),temp);
			});
			model.addAttribute("resultMap",resultMap);
		}
		return "sch/result/results";
	}
	@RequestMapping(value = {"/doctorResults"})
	public String doctorResults( @RequestBody  SchArrangeForm form, Model model) throws DocumentException {
		if(form.getDoctors()!=null) {
			String selectYear=form.getSelectYear();
			String cycleTypeId=form.getCycleTypeId();
			Map<String, List<SchOrgArrangeResult>> resultMap = new HashMap<>();
			form.getDoctors().forEach(d->{
				ResDoctor doctor = doctorBiz.readDoctor(d.getUserFlow());
				//查询学员选科信息
				List<SchOrgArrangeResult> results = doctorDeptBiz.readArrangeResults(d.getUserFlow(), doctor.getSessionNumber(), doctor.getRotationFlow(), doctor.getOrgFlow());
				resultMap.put(d.getUserFlow(), results);
				if (results != null) {
					results.forEach(r -> {
						List<SchOrgArrangeResult> temp = resultMap.get(d.getUserFlow()+r.getSelectYear());
						if (temp == null) temp = new ArrayList<SchOrgArrangeResult>();
						temp.add(r);
						resultMap.put(d.getUserFlow()+r.getSelectYear(), temp);
					});
				}
			});
			model.addAttribute("cycleTypeId", cycleTypeId);
			model.addAttribute("selectYear", selectYear);
			model.addAttribute("resultMap", resultMap);
			model.addAttribute("doctors", form.getDoctors());
		}
		return "sch/result/doctorResults";
	}

	@RequestMapping(value = {"/delArrangeResult"},method = RequestMethod.POST)
	@ResponseBody
	public String delArrangeResult(@RequestBody  SchArrangeForm form) throws Exception {
		if(StringUtil.isBlank(form.getCycleYear()))
		{
			return "请选择排班范围！";
		}
		if(form.getDoctors()==null||form.getDoctors().isEmpty())
		{
			return "请选择需要删除排班的学员！";
		}
		doctorDeptBiz.delArrangeResult(form);
		return GlobalConstant.OPRE_SUCCESSED;
	}
	@RequestMapping(value = {"/saveArrange"},method = RequestMethod.POST)
	@ResponseBody
	public String saveArrange(@RequestBody  SchArrangeForm form) throws Exception {
		String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
		if (!orgFlow.equals(form.getOrgFlow()))
		{
			return "当前登录人已改变，请重新登录！";
		}
		if(StringUtil.isBlank(form.getStartDate()))
		{
			return "请选择排班开始时间！";
		}
		if(StringUtil.isBlank(form.getCycleYear()))
		{
			return "请选择排班范围！";
		}
		if(form.getDoctors()==null||form.getDoctors().isEmpty())
		{
			return "请选择需要排班的学员！";
		}
		if(SchSelYearEnum.Two.getId().equals(form.getCycleYear()))
		{
			form.setStartDate(DateUtil.addYear(form.getStartDate(),1));
		}
		if(SchSelYearEnum.Three.getId().equals(form.getCycleYear()))
		{
			form.setStartDate(DateUtil.addYear(form.getStartDate(),2));
		}
		SchDept searchSchDept=new SchDept();
		searchSchDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchSchDept.setOrgFlow(orgFlow);
		List<SchDept> depts=schDeptBiz.searchSchDeptByExample(searchSchDept);
		//轮转科室人员限制
		for(SchDept d:depts){
			if(d.getDeptNum()==null)
			{
				d.setDeptNum(0);
//				return  "请维护轮转科室【"+d.getSchDeptName()+"】的容纳人数！";
			}
		}
		//组装需要排班的数据


		long startTime = System.currentTimeMillis();    //获取开始时间
		Plan unsolvedCloudBalance = initData(form,depts);
		for(Student resDoctor :unsolvedCloudBalance.getDoctors() )
		{
			if(!(resDoctor.getSchDeptPlanList()!=null&&resDoctor.getSchDeptPlanList().size()>0))
			{
				return "学员【"+resDoctor.getName()+"】还未进行选科，请不要选中！";
			}
		}
		File file=SpringUtil.getResource("classpath:planSolverConfig.xml").getFile();
		SolverFactory<Plan> solverFactory = SolverFactory.createFromXmlFile(file);
		Solver<Plan> solver = solverFactory.buildSolver();
		// Solve the problem
		Plan solvedCloudBalance = solver.solve(unsolvedCloudBalance);
//
//		solver.addEventListener(new SolverEventListener<Plan>() {
//			@Override
//			public void bestSolutionChanged(BestSolutionChangedEvent<Plan> event) {
//				Plan bestSolution = event.getNewBestSolution();
//				HardSoftScore newScore= (HardSoftScore) event.getNewBestScore();
//				newScore.getInitScore();
//				newScore.getHardScore();
//				newScore.getSoftScore();
//			}
//		});
//		solvingExecutor.submit(new Runnable() {
//			@Override
//			public void run() {
//				Plan solvedCloudBalance =solver.solve(unsolvedCloudBalance);
//				try {
//					doctorDeptBiz.saveArrangeResult(solvedCloudBalance,form);
//				} catch (Exception e) {
//
//				}
//			}
//		});

		doctorDeptBiz.saveArrangeResult(solvedCloudBalance,form);
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
		return GlobalConstant.OPRE_SUCCESSED;
	}
	@RequestMapping(value = {"/syncArrange"},method = RequestMethod.POST)
	@ResponseBody
	public String syncArrange(@RequestBody  SchArrangeForm form) throws Exception {
		String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
		if (!orgFlow.equals(form.getOrgFlow()))
		{
			return "当前登录人已改变，请重新登录！";
		}
		if(StringUtil.isBlank(form.getCycleYear()))
		{
			return "请选择需要导出的排班范围！";
		}
		if(form.getDoctors()==null||form.getDoctors().isEmpty())
		{
			return "请选择需要导出排班的学员！";
		}
		for(SchDoctorForm d:form.getDoctors())
		{
			String doctorFlow=d.getUserFlow();
			ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);
			if(doctor==null)
			{
				return "无法读取，学员【"+d.getUserName()+"】医师";
			}
			int count=doctorDeptBiz.findCycleResultCount(doctorFlow,form.getCycleYear());
			if(count>0)
			{
				return "学员【"+d.getUserName()+"】已开始轮转，请不要选中！";
			}
			count=doctorDeptBiz.findSchResultCount(doctorFlow,form.getCycleYear());
			if(count<=0)
			{
				return "学员【"+d.getUserName()+"】无排班结果，请不要选中！";
			}
		}
		doctorDeptBiz.syncArrange(form);
		return GlobalConstant.OPRE_SUCCESSED;
	}

	private Plan initData(SchArrangeForm form, List<SchDept> schDepts) throws Exception {

		Plan plan=new Plan();
		plan.setId(0L);
		plan.setStartDate(form.getStartDate());
		Double timeSize=doctorDeptBiz.getRotationCycleMonthNum(form.getRotationFlow(),form.getSelectYear(),form.getCycleYear(),form.getOrgFlow(),form.getSessionNumber());
		//初始化时间信息
		List<PlanMonth> planMonths= SchDateUtil.getTimes(form.getStartDate(),timeSize*2);
		plan.setPlanMonthList(planMonths);

		//初始化医院科室信息
		List<Dept> depts= new ArrayList<>();
		final long[] i = {0};
		Map<String,Dept> deptMap=new HashMap<>();
		schDepts.forEach(d->{
			Dept dept=new Dept(i[0]++,d.getSchDeptFlow(),d.getSchDeptName());
			dept.setMaxNum(d.getDeptNum());
			depts.add(dept);
			deptMap.put(dept.getDeptFlow(),dept);
		});
		plan.setDeptList(depts);

		//初始化医院科室-时间
		List<SchDeptMonth> deptMonths=getSchDeptMoths(depts,planMonths);
		plan.setDeptMonthList(deptMonths);
		//初始化学员
		List<Student> doctors=getResDoctors(form,deptMap);
		plan.setDoctors(doctors);
		//初始化所有学员计划
		List<SchDeptPlan> planList=new ArrayList<>();
		plan.getDoctors().forEach(resDoctor -> {
			if(resDoctor.getSchDeptPlanList()!=null&&resDoctor.getSchDeptPlanList().size()>0)
			{
				planList.addAll(resDoctor.getSchDeptPlanList());
			}
		});
		plan.setDoctorPlanList(planList);

		//初始化所有计划
		List<SchDeptPlanAssignment> assignments=new ArrayList<>();
		final long[] l = {0};
		plan.getDoctorPlanList().forEach(schDeptPlan -> {
			int m= schDeptPlan.getSchMonth();
			for(int k=0;k<m;k++)
			{
				SchDeptPlanAssignment assignment=new SchDeptPlanAssignment();
				assignment.setId(l[0]++);
				assignment.setSchDept(schDeptPlan.getSchDept());
				assignment.setDoctorFlow(schDeptPlan.getDoctorFlow());
				assignment.setRecordFlow(schDeptPlan.getRecordFlow());
				assignments.add(assignment);
			}
		});
		plan.setAssignmentList(assignments);
		return plan;
	}

	private List<Student> getResDoctors(SchArrangeForm form, Map<String, Dept> deptMap) {
		List<Student> list=new ArrayList<>();
		long l=0;
		long i=0;
		int index=0;
		for(SchDoctorForm doctorForm:form.getDoctors())
		{
			Student doctor = new Student(i++,doctorForm.getUserFlow(),doctorForm.getUserName());
			ResDoctor resDoctor=doctorBiz.readDoctor(doctorForm.getUserFlow());
			//查询学员选科信息
			List<SchDoctorSelectDept> selectDepts=doctorDeptBiz.readSelectDepts(doctorForm.getUserFlow(),resDoctor.getSessionNumber(),resDoctor.getRotationFlow(),resDoctor.getOrgFlow(),form.getCycleYear());
			if(selectDepts!=null&&selectDepts.size()>0)
			{
				List<SchDeptPlan> planList=new ArrayList<>();
				int length=selectDepts.size()<index?0:index;
				index=selectDepts.size()<index?0:index;
				for(int k=length;k<selectDepts.size();k++)
				{
					SchDoctorSelectDept selectDept=selectDepts.get(k);
					SchDeptPlan schDeptPlan = new SchDeptPlan();
					schDeptPlan.setId(l++);
					schDeptPlan.setRecordFlow(selectDept.getSelectFlow());
					schDeptPlan.setDoctorFlow(doctor.getDoctorFlow());
					schDeptPlan.setSchMonth((int) (Float.valueOf(selectDept.getSchMonth())/0.5));
					schDeptPlan.setSchDept(deptMap.get(selectDept.getSchDeptFlow()));
					planList.add(schDeptPlan);
				}
				for(int k=0;k<length;k++)
				{
					SchDoctorSelectDept selectDept=selectDepts.get(k);
					SchDeptPlan schDeptPlan = new SchDeptPlan();
					schDeptPlan.setId(l++);
					schDeptPlan.setRecordFlow(selectDept.getSelectFlow());
					schDeptPlan.setDoctorFlow(doctor.getDoctorFlow());
					schDeptPlan.setSchMonth((int) (Float.valueOf(selectDept.getSchMonth())/0.5));
					schDeptPlan.setSchDept(deptMap.get(selectDept.getSchDeptFlow()));
					planList.add(schDeptPlan);
				}
//				for(SchDoctorSelectDept selectDept:selectDepts) {
//					SchDeptPlan schDeptPlan = new SchDeptPlan();
//					schDeptPlan.setId(l++);
//					schDeptPlan.setRecordFlow(selectDept.getSelectFlow());
//					schDeptPlan.setDoctorFlow(doctor.getDoctorFlow());
//					schDeptPlan.setSchMonth((int) (Float.valueOf(selectDept.getSchMonth())/0.5));
//					schDeptPlan.setSchDept(deptMap.get(selectDept.getSchDeptFlow()));
//					planList.add(schDeptPlan);
//				}
				doctor.setSchDeptPlanList(planList);
			}
			list.add(doctor);
			index++;
		}
		return list;
	}
	private List<SchDeptMonth> getSchDeptMoths(List<Dept> depts, List<PlanMonth> planMonths) {
		List<SchDeptMonth> list=new ArrayList<>();
		final int[] i = {0};
		depts.forEach(d->{
			planMonths.forEach(planMonth -> {
				SchDeptMonth schDeptMonth=new SchDeptMonth(i[0]++,d,planMonth);
				int useNum=doctorDeptBiz.findDeptMonthUseNum(d.getDeptFlow(),planMonth.getStartDate(),planMonth.getEndDate());
				schDeptMonth.setUsedNum(useNum);
				list.add(schDeptMonth);
			});
		});
		return list;
	}

	@RequestMapping(value = {"/deptStaticts" })
	public String deptStaticts (String startDate, String endDate,  String schDeptFlow, String orgName, Model model){
		List<String> titleDate = null;
		if(!StringUtil.isNotBlank(startDate)){
			String currDate = DateUtil.getCurrDate();
			startDate = DateUtil.newMonthOfAddMonths(currDate,-6);
			endDate = DateUtil.newMonthOfAddMonths(currDate,6);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}

		List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("schDeptList",schDeptList);

		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			titleDate = SchDateUtil.getMonthsByTwoMonth(startDate,endDate);
			model.addAttribute("titleDate",titleDate);
			Map<String,String> param=new HashMap<>();
			param.put("startDate",startDate);
			param.put("endDate",endDate);
			param.put("schDeptFlow",schDeptFlow);
			param.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
			List<SchOrgArrangeResult>	arrResultList =doctorDeptBiz.readOrgArrangeResult(param);
			if(arrResultList != null && arrResultList.size()>0){
				Map<String,List<SchOrgArrangeResult>> resultListMap = new HashMap<String, List<SchOrgArrangeResult>>();
				Map<String,List<String>> doctorFlowMap = new HashMap<String, List<String>>();
				for(SchOrgArrangeResult sar : arrResultList){
					schDeptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = SchDateUtil.getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = schDeptFlow+month;
									List<String> flows=doctorFlowMap.get(key);
									if(flows==null)
									{
										flows=new ArrayList<>();
									}
									if(resultListMap.get(key)==null){
										List<SchOrgArrangeResult> sarList = new ArrayList<SchOrgArrangeResult>();
										if(!flows.contains(sar.getDoctorFlow())) {
											sarList.add(sar);
											flows.add(sar.getDoctorFlow());
										}
										resultListMap.put(key,sarList);
									}else{
										if(!flows.contains(sar.getDoctorFlow())) {
											resultListMap.get(key).add(sar);
											flows.add(sar.getDoctorFlow());
										}
									}
									doctorFlowMap.put(key,flows);
								}
							}
					}
				}
				model.addAttribute("resultListMap",resultListMap);
			}
		}
		return "sch/result/deptStaticts";
	}
	@RequestMapping(value = {"/exportDeptStaticts" })
	public void exportDeptStaticts (String startDate, String endDate,  String schDeptFlow,
									HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		List<String> titleDate = null;
		if(!StringUtil.isNotBlank(startDate)){
			String currDate = DateUtil.getCurrDate();
			startDate = DateUtil.newMonthOfAddMonths(currDate,-6);
			endDate = DateUtil.newMonthOfAddMonths(currDate,6);
		}
		List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("schDeptList",schDeptList);
		Map<String,List<SchOrgArrangeResult>> resultListMap = new HashMap<String, List<SchOrgArrangeResult>>();
		Map<String,List<String>> doctorFlowMap = new HashMap<String, List<String>>();
		Map<String,Integer> doctorMaxMap = new HashMap<String,Integer>();
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			titleDate = SchDateUtil.getMonthsByTwoMonth(startDate,endDate);
			model.addAttribute("titleDate",titleDate);
			Map<String,String> param=new HashMap<>();
			param.put("startDate",startDate);
			param.put("endDate",endDate);
			param.put("schDeptFlow",schDeptFlow);
			param.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
			List<SchOrgArrangeResult>	arrResultList =doctorDeptBiz.readOrgArrangeResult(param);
			if(arrResultList != null && arrResultList.size()>0){
				for(SchOrgArrangeResult sar : arrResultList){
					String deptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = SchDateUtil.getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = deptFlow+month;
									List<String> flows=doctorFlowMap.get(key);
									if(flows==null)
									{
										flows=new ArrayList<>();
									}
									if(resultListMap.get(key)==null){
										List<SchOrgArrangeResult> sarList = new ArrayList<SchOrgArrangeResult>();
										if(!flows.contains(sar.getDoctorFlow())) {
											sarList.add(sar);
											flows.add(sar.getDoctorFlow());
										}
										resultListMap.put(key,sarList);
									}else{
										if(!flows.contains(sar.getDoctorFlow())) {
											resultListMap.get(key).add(sar);
											flows.add(sar.getDoctorFlow());
										}
									}
									doctorFlowMap.put(key,flows);
									if(doctorMaxMap.get(deptFlow)==null||flows.size()>doctorMaxMap.get(deptFlow))
									{
										doctorMaxMap.put(deptFlow,flows.size());
									}
								}
							}
					}
				}
			}
		}
		createExcle2(response,resultListMap,schDeptList,schDeptFlow,titleDate,doctorMaxMap);
	}
	private void createExcle2(HttpServletResponse response, Map<String, List<SchOrgArrangeResult>> resultListMap, List<SchDept> schDeptList, String schDeptFlow, List<String> titleDate, Map<String, Integer> doctorMaxMap) throws IOException {

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		HSSFRow rowOne = sheet.createRow(0);//第一行
		String[] titles = new String[]{
				"轮转科室"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowOne.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles[i].getBytes().length * 506);
		}
		for(int i=0;i<titleDate.size();i++)
		{
			int j=titles.length+i;
			cellTitle = rowOne.createCell(j);
			cellTitle.setCellValue(titleDate.get(i));
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(j, titleDate.get(i).getBytes().length * 506);
			j++;
		}
		int rowNum = 1;
		String[] resultList = null;
		if (schDeptList != null && !schDeptList.isEmpty()) {
			for (int i = 0; i < schDeptList.size(); i++) {
				SchDept schDept = schDeptList.get(i);
				if (schDept.getSchDeptFlow().equals(schDeptFlow)||StringUtil.isBlank(schDeptFlow)) {
					String deptFlow = schDept.getSchDeptFlow();
					Integer size=doctorMaxMap.get(deptFlow);
					if(size==null||size==0)
					{
						HSSFRow rowTwo = sheet.createRow(rowNum++);
						int j = 0;
						HSSFCell cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(schDept.getSchDeptName());
						sheet.setColumnWidth(j, schDept.getSchDeptName().getBytes().length * 506);
						j++;
					}else{
						for(int n=0;n<size;n++)
						{
							HSSFRow rowTwo = sheet.createRow(rowNum++);
							int j = 0;
							HSSFCell cellFirst = rowTwo.createCell(j);
							cellFirst.setCellStyle(styleCenter);
							cellFirst.setCellValue(schDept.getSchDeptName());
							sheet.setColumnWidth(j, schDept.getSchDeptName().getBytes().length * 506);
							j++;
							for (int k = 0; k < titleDate.size(); k++) {
								String schDate = titleDate.get(k);
								String key = deptFlow + schDate;
								List<SchOrgArrangeResult> sarList = resultListMap.get(key);
								if(sarList==null||sarList.size()<n+1)
								{
									cellTitle = rowTwo.createCell(j);
									cellTitle.setCellValue("");
									cellTitle.setCellStyle(styleCenter);
									j++;
								}else{
									cellTitle = rowTwo.createCell(j);
									cellTitle.setCellValue(sarList.get(n).getDoctorName());
									cellTitle.setCellStyle(styleCenter);
									j++;
								}
							}
						}
					}
				}
			}
		}
		String name = "科室排班结果表.xls";
		response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
		response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}

	@RequestMapping(value = {"/docStaticts" })
	public String docStaticts (String startDate, String endDate, ResDoctorExt doctor, Integer currentPage,
							   HttpServletRequest request, HttpServletResponse response,Model model){
		List<String> titleDate = null;
		if(!StringUtil.isNotBlank(startDate)){
			String currDate = DateUtil.getCurrDate();
			startDate = DateUtil.newMonthOfAddMonths(currDate,-6);
			endDate = DateUtil.newMonthOfAddMonths(currDate,6);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		if (currentPage == null) {
			currentPage = 1;
		}

		PageHelper.startPage(currentPage, getPageSize(request));
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");
		model.addAttribute("doctorList", doctorList);

		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			titleDate = SchDateUtil.getMonthsByTwoMonth(startDate,endDate);
			model.addAttribute("titleDate",titleDate);

			Map<String, List<SchOrgArrangeResult>> resultListMap = new HashMap<String, List<SchOrgArrangeResult>>();

			for(ResDoctorExt doctorExt:doctorList) {
				Map<String, String> param = new HashMap<>();
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				param.put("doctorFlow", doctorExt.getDoctorFlow());
				param.put("sessionNumber", doctorExt.getSessionNumber());
				param.put("rotationFlow", doctorExt.getRotationFlow());
				param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
				if(StringUtil.isNotBlank(doctorExt.getRotationFlow())) {
					List<SchOrgArrangeResult> arrResultList = doctorDeptBiz.readArrangeResultsByMap(param);
					if (arrResultList != null && arrResultList.size() > 0) {
						for (SchOrgArrangeResult sar : arrResultList) {
							String doctorFlow = sar.getDoctorFlow();
							String resultStartDate = sar.getSchStartDate();
							String resultEndDate = sar.getSchEndDate();
							if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
								resultStartDate = resultStartDate.substring(0, 7);
								resultEndDate = resultEndDate.substring(0, 7);
								//计算该轮转科室该月人数
								List<String> months = SchDateUtil.getMonthsByTwoMonth(resultStartDate, resultEndDate);
								if (months != null) {
									for (String month : months) {
										String key = doctorFlow + month;
										if (resultListMap.get(key) == null) {
											List<SchOrgArrangeResult> sarList = new ArrayList<SchOrgArrangeResult>();
											sarList.add(sar);
											resultListMap.put(key, sarList);
										} else {
											resultListMap.get(key).add(sar);
										}
									}
								}
							}
						}
					}
				}
			}
			model.addAttribute("resultListMap", resultListMap);
		}
		return "sch/result/docStaticts";
	}
	@RequestMapping(value = {"/exportArrangeInfo" })
	public void exportArrangeInfo (String startDate, String endDate, ResDoctorExt doctor, Integer currentPage,
									 HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");
		model.addAttribute("doctorList", doctorList);

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("ArrangeResult");
		HSSFRow rowOne = sheet.createRow(0);//第一行
		String[] titles = new String[]{
				"姓名",
				"证件号",
				"年级",
				"培训专业",
				"培养年限",
				"轮转组",
				"轮转组代码",
				"标准科室",
				"标准科室代码",
				"轮转科室",
				"轮转科室代码",
				"开始时间",
				"结束时间",
				"带教老师",
				"科主任"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowOne.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles[i].getBytes().length * 506);
		}

		if(doctorList!=null&&doctorList.size()>0) {
			int rowNum=1;
			Map<String, SchRotationGroup> groupMap = new HashMap<>();
			for (ResDoctorExt doctorExt : doctorList) {
				Map<String, String> param = new HashMap<>();
				param.put("doctorFlow", doctorExt.getDoctorFlow());
				param.put("sessionNumber", doctorExt.getSessionNumber());
				param.put("rotationFlow", doctorExt.getRotationFlow());
				param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
				List<SchOrgArrangeResult> arrResultList = doctorDeptBiz.readArrangeResultsByMap(param);
				if (arrResultList != null && arrResultList.size() > 0) {
					for(SchOrgArrangeResult result:arrResultList) {
						HSSFRow rowTwo = sheet.createRow(rowNum++);
						int j = 0;
						HSSFCell cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(doctorExt.getSysUser().getUserName().toString());
						sheet.setColumnWidth(j, doctorExt.getSysUser().getUserName().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(doctorExt.getSysUser().getIdNo().toString());
						sheet.setColumnWidth(j, doctorExt.getSysUser().getIdNo().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(doctorExt.getSessionNumber().toString());
						sheet.setColumnWidth(j, doctorExt.getSessionNumber().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(doctorExt.getTrainingSpeName().toString());
						sheet.setColumnWidth(j, doctorExt.getTrainingSpeName().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(doctorExt.getTrainingYears().toString());
						j++;
						SchRotationGroup group=groupMap.get(result.getStandardGroupFlow());
						if(group==null)
							group=schRotationtGroupBiz.readSchRotationGroup(result.getStandardGroupFlow());
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(group.getGroupName().toString());
						sheet.setColumnWidth(j, group.getGroupName().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(result.getStandardGroupFlow().toString());
						sheet.setColumnWidth(j, result.getStandardGroupFlow().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(result.getStandardDeptName().toString());
						sheet.setColumnWidth(j, result.getStandardDeptName().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(result.getStandardDeptId().toString());
						sheet.setColumnWidth(j, result.getStandardDeptId().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(result.getSchDeptName().toString());
						sheet.setColumnWidth(j, result.getSchDeptName().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(result.getSchDeptFlow().toString());
						sheet.setColumnWidth(j, result.getSchDeptFlow().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(result.getSchStartDate().toString());
						sheet.setColumnWidth(j, result.getSchStartDate().getBytes().length * 506);
						j++;
						cellFirst = rowTwo.createCell(j);
						cellFirst.setCellStyle(styleCenter);
						cellFirst.setCellValue(result.getSchEndDate().toString());
						sheet.setColumnWidth(j, result.getSchEndDate().getBytes().length * 506);
					}
				}
			}
		}

		String name = "学员排班导入模板.xls";
		response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
		response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}
	@RequestMapping(value = {"/exportDocStaticts" })
	public void exportDocStaticts (String startDate, String endDate, ResDoctorExt doctor, Integer currentPage,
									 HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		List<String> titleDate = null;
		if(!StringUtil.isNotBlank(startDate)){
			String currDate = DateUtil.getCurrDate();
			startDate = DateUtil.newMonthOfAddMonths(currDate,-6);
			endDate = DateUtil.newMonthOfAddMonths(currDate,6);
		}
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");
		model.addAttribute("doctorList", doctorList);

		Map<String, List<SchOrgArrangeResult>> resultListMap = new HashMap<String, List<SchOrgArrangeResult>>();

		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			titleDate = SchDateUtil.getMonthsByTwoMonth(startDate,endDate);
			model.addAttribute("titleDate",titleDate);

			for(ResDoctorExt doctorExt:doctorList) {
				Map<String, String> param = new HashMap<>();
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				param.put("doctorFlow", doctorExt.getDoctorFlow());
				param.put("sessionNumber", doctorExt.getSessionNumber());
				param.put("rotationFlow", doctorExt.getRotationFlow());
				param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
				List<SchOrgArrangeResult> arrResultList = doctorDeptBiz.readArrangeResultsByMap(param);
				if (arrResultList != null && arrResultList.size() > 0) {
					for (SchOrgArrangeResult sar : arrResultList) {
						String doctorFlow = sar.getDoctorFlow();
						String resultStartDate = sar.getSchStartDate();
						String resultEndDate = sar.getSchEndDate();
						if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
							resultStartDate = resultStartDate.substring(0, 7);
							resultEndDate = resultEndDate.substring(0, 7);
							//计算该轮转科室该月人数
							List<String> months = SchDateUtil.getMonthsByTwoMonth(resultStartDate, resultEndDate);
							if (months != null) {
								for (String month : months) {
									String key = doctorFlow + month;
									if (resultListMap.get(key) == null) {
										List<SchOrgArrangeResult> sarList = new ArrayList<SchOrgArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
			}
			model.addAttribute("resultListMap", resultListMap);

		}

		createExcle(response,resultListMap,doctorList,titleDate);
	}

	private void createExcle(HttpServletResponse response, Map<String, List<SchOrgArrangeResult>> resultListMap, List<ResDoctorExt> doctorList, List<String> titleDate) throws IOException {

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		HSSFRow rowOne = sheet.createRow(0);//第一行
		String[] titles = new String[]{
				"姓名",
				"年级",
				"培训专业",
				"培养年限"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowOne.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles[i].getBytes().length * 506);
		}
		for(int i=0;i<titleDate.size();i++)
		{
			int j=titles.length+i*2;
			cellTitle = rowOne.createCell(j);
			cellTitle.setCellValue(titleDate.get(i));
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(j, titleDate.get(i).getBytes().length * 506);
			sheet.setColumnWidth(j+1, titleDate.get(i).getBytes().length * 506);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, j, j+1));//合并单元格
		}
		int rowNum = 1;
		String[] resultList = null;
		if (doctorList != null && !doctorList.isEmpty()) {
			for (int i = 0; i < doctorList.size(); i++, rowNum++) {
				ResDoctorExt doctorExt=doctorList.get(i);
				String doctorFlow=doctorExt.getDoctorFlow();
				HSSFRow rowTwo = sheet.createRow(rowNum);
				int j=0;
				HSSFCell cellFirst = rowTwo.createCell(j);
				cellFirst.setCellStyle(styleCenter);
				cellFirst.setCellValue(doctorExt.getSysUser().getUserName().toString());
				sheet.setColumnWidth(j, doctorExt.getSysUser().getUserName().getBytes().length * 506);
				j++;
				cellFirst = rowTwo.createCell(j);
				cellFirst.setCellStyle(styleCenter);
				cellFirst.setCellValue(doctorExt.getSessionNumber().toString());
				sheet.setColumnWidth(j, doctorExt.getSessionNumber().getBytes().length * 506);
				j++;
				cellFirst = rowTwo.createCell(j);
				cellFirst.setCellStyle(styleCenter);
				cellFirst.setCellValue(doctorExt.getTrainingSpeName().toString());
				sheet.setColumnWidth(j, doctorExt.getTrainingSpeName().getBytes().length * 506);
				j++;
				cellFirst = rowTwo.createCell(j);
				cellFirst.setCellStyle(styleCenter);
				cellFirst.setCellValue(doctorExt.getTrainingYears().toString());
				j++;
				for(int k=0;k<titleDate.size();k++)
				{
					String schDate=titleDate.get(k);
					String key = doctorFlow + schDate;
					List<SchOrgArrangeResult> sarList =resultListMap.get(key);
					if(sarList!=null&&sarList.size()>1)
					{
						for(SchOrgArrangeResult r:sarList)
						{
							cellTitle = rowTwo.createCell(j++);
							cellTitle.setCellValue(r.getSchDeptName().toString());
							cellTitle.setCellStyle(styleCenter);
						}
					}else if(sarList!=null&&sarList.size()==1)
					{
						cellTitle = rowTwo.createCell(j);
						cellTitle.setCellValue(sarList.get(0).getSchDeptName().toString());
						cellTitle.setCellStyle(styleCenter);
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, j, j+1));//合并单元格
						j++;
						j++;
					}else{
						cellTitle = rowTwo.createCell(j);
						cellTitle.setCellValue("");
						cellTitle.setCellStyle(styleCenter);
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, j, j+1));//合并单元格
						j++;
						j++;
					}
				}
			}
		}
		String name = "学员排班结果表.xls";
		response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
		response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}
	@RequestMapping(value = {"/roster"},method = RequestMethod.POST)
	@ResponseBody
	public Object roster(@RequestBody RosterForm rosterForm) throws Exception {
		Map<String,Object> resp=new HashMap<>();
		resp.put("code","0");
		if(rosterForm!=null&&StringUtil.isNotBlank(rosterForm.getStartDate()))
		{
			Double timeSize=0.0;

			int si=0;
			int ei=0;
			for(RosterTime rosterTime:rosterForm.getMonths())
			{
				timeSize+=Double.valueOf(rosterTime.getSchMonth());
				ei= (int) (Double.valueOf(timeSize)*2-1);
				rosterTime.setEi(ei);
				rosterTime.setSi(si);
				si=ei+1;
			}
			//初始化时间信息
			List<PlanMonth> planMonths= SchDateUtil.getTimes(rosterForm.getStartDate(),timeSize*2);
			Map<String,Map<String,String>> timeMap=new HashMap<>();
			for(RosterTime rosterTime:rosterForm.getMonths())
			{
				Map<String,String> time=new HashMap<>();
				time.put("schStartDate",planMonths.get(rosterTime.getSi()).getStartDate());
				time.put("schEndDate",planMonths.get(rosterTime.getEi()).getEndDate());
				timeMap.put(rosterTime.getIndex(),time);
			}
			resp.put("timeMap",timeMap);
		}
		return resp;
	}
	@RequestMapping(value = {"/saveArrangeTime"},method = RequestMethod.POST)
	@ResponseBody
	public String saveArrangeTime(@RequestBody List<SchOrgArrangeResult> results) throws Exception {
		if(results==null||results.size()<=0)
		{
			return "请确认是否有数据提交！";
		}
		doctorDeptBiz.saveArrangeTime(results);
		return GlobalConstant.OPERATE_SUCCESSED;
	}
}


