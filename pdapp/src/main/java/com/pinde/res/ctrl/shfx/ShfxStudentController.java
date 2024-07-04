package com.pinde.res.ctrl.shfx;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.hbres.IHbResLiveTrainingBiz;
import com.pinde.res.biz.hbres.IResInprocessInfoBiz;
import com.pinde.res.biz.jswjw.IJswjwStudentBiz;
import com.pinde.res.biz.shfx.IShfxAppBiz;
import com.pinde.res.biz.shfx.IShfxStudentBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.enums.hbres.RotationStatusEnum;
import com.pinde.res.enums.hbres.SchUnitEnum;
import com.pinde.res.enums.hbres.TrainYearEnum;
import com.pinde.res.enums.stdp.DeptActivityItemTypeEnum;
import com.pinde.res.enums.stdp.PreRecTypeEnum;
import com.pinde.res.enums.stdp.ResScoreTypeEnum;
import com.pinde.res.enums.stdp.ResultEnum;
import com.pinde.res.model.stdp.mo.QingpuLectureCfgItemExt;
import com.pinde.res.model.stdp.mo.QingpuLectureCfgTitleExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/shfx/student")
public class ShfxStudentController {
	private static Logger logger = LoggerFactory.getLogger(ShfxStudentController.class);

	@Autowired
	private IExamResultsBiz examResultsBiz;
	@Autowired
	private IShfxStudentBiz studentBiz;
	@Autowired
	private IShfxAppBiz appBiz;
	@Autowired
	private IResOrgTimeBiz timeBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private ISchAndStandardDeptCfgBiz deptCfgBiz;
	@Autowired
	private ResPaperBiz paperBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private IHbResLiveTrainingBiz resLiveTrainingBiz;
	@Autowired
	private IJswjwStudentBiz jswjwStudentBiz;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private IResActivityTargetBiz activityTargeBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/stdp/500";
    }

	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/shfx/student/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String deptFlow,String cataFlow,String dataFlow,String funcTypeId,String funcFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/shfx/student/test";
	}

	@RequestMapping(value={"/deptList"},method={RequestMethod.GET})
	public String deptList(String userFlow,String searchData, Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3020101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/deptList";
		}
		
		if(pageIndex==null){
			model.addAttribute("resultId", "3020102");
			model.addAttribute("resultType", "起始页为空");
			return "res/shfx/student/deptList";
		}
		
		if(pageSize==null){
			model.addAttribute("resultId", "3020103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/shfx/student/deptList";
		}
		
		//查询条件
		//Entering(已入科) , NotEntered(未入科) , Exited(已出科) 
		String statusId = "";
		String deptName = "";
		if(StringUtil.isNotBlank(searchData)){
			try {
				//为json字符串转码
				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//转换json字符串为map对象
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			statusId = searchMap.get("statusId");
			deptName = searchMap.get("deptName");
		}
		
//		if(StringUtil.isBlank(statusId)){
//			statusId = DeptStatusEnum.Entering.getId();
//		}
		
		//组织查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		paramMap.put("statusId",statusId);
		paramMap.put("deptName",deptName);
		if("Action2".equals(appBiz.getCfgByCode("res_sch_action_type")))
		{
			paramMap.put("isAction2","Y");
		}else{
			paramMap.put("isAction2","N");
		}
		//按条件查询轮转数据
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> results = studentBiz.searchResult(paramMap);
		
		if(results!=null && !results.isEmpty()){
			//轮转计划单位
			String unit = appBiz.getCfgByCode("res_rotation_unit");
			
			//默认按月计算
			int step = 30;
			if(SchUnitEnum.Week.getId().equals(unit)){
				//如果是周按7天算/没配置或者选择月按30天
				step = 7;
			}
			
			//循环计算实际轮转月份
			for(Map<String,Object> map : results){
				//获取实际的开始时间与结束时间
				String startDate = (String)map.get("schStartDate");
				String endDate = (String)map.get("schEndDate");

				BigDecimal realMonth = BigDecimal.valueOf(0);
				if(StringUtil.isNotBlank(startDate)){

					if (SchUnitEnum.Week.getId().equals(unit)) {
						//如果是周按7天算/没配置或者选择月按30天
						step = 7;
						long realDays = DateUtil.signDaysBetweenTowDate(endDate,startDate)+1;
						if (realDays != 0) {
							//计算实际轮转的月/周数
							double realMonthF = (realDays / (step * 1.0));
							realMonth = BigDecimal.valueOf(realMonthF);
							realMonth = realMonth.setScale(1, BigDecimal.ROUND_HALF_UP);
						}
					}else{
						Map<String,String> map2= new HashMap<>();
						map2.put("startDate",startDate);
						map2.put("endDate",endDate);
						Double month = TimeUtil.getMonthsBetween(map2);
						realMonth=new BigDecimal(month).setScale(1, BigDecimal.ROUND_HALF_UP);
					}
				}
				//轮转中，结束时间为当前时间
				String rotationStatus = (String)map.get("rotationStatus");
				map.put("realMonth",realMonth);

				String standardDeptName = (String)map.get("standardDeptName");
				if(StringUtil.isNotBlank(standardDeptName)){
					String[] standardDeptNames = standardDeptName.split("\\.");
					int len = standardDeptNames.length;
					String subDeptName = standardDeptNames[len-1];
					map.put("standardDeptName",subDeptName);
				}

				//如果是学员自主入科
				// 是否让学员填写轮转计划
				if(GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_custom_result_flag"))||
						(!GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_custom_result_flag"))&&
								GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_doc_in_by_self"))
						)){
					String isCurrentFlag = (String)map.get("isCurrentFlag");
					String schFlag = (String)map.get("schFlag");
					if(!GlobalConstant.FLAG_Y.equals(isCurrentFlag) && !GlobalConstant.FLAG_Y.equals(schFlag)){
						String currDate = DateUtil.getCurrDate();
						String schStartDate = (String) map.get("schStartDate");
//					if(StringUtil.isNotBlank(schStartDate) && currDate.compareTo(schStartDate)>=0){
						if(RotationStatusEnum.Rounding.getId().equals(rotationStatus)){
							String processFlow = (String)map.get("processFlow");
							ResDoctorSchProcess process = new ResDoctorSchProcess();
							process.setProcessFlow(processFlow);
							process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
							int result = studentBiz.updateProcess(process);
							if(result!=0){
								map.put("isCurrentFlag",GlobalConstant.FLAG_Y);
							}
						}
					}
				}
			}
		}
		model.addAttribute("results",results);
		
		//百分比算法
		Map<String,Object> deptPerMap = studentBiz.getRegPer(0,userFlow);
		model.addAttribute("deptPerMap",deptPerMap);

		//System.out.println("deptPerMap:"+ JSON.toJSONString(deptPerMap));
		//数据量
		model.addAttribute("dataCount", PageHelper.total);
		
		return "res/shfx/student/deptList";
	}
	
	@RequestMapping(value={"/funcList"},method={RequestMethod.GET})
	public String funcList(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/funcList";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/shfx/student/funcList";
		}
    	
		ResDoctorSchProcess process = studentBiz.getProcessByResult(deptFlow);
		model.addAttribute("process",process);
		model.addAttribute("disabled",process==null);
		model.addAttribute("disabledTip","该项暂时无法填写数据！");
		
		if(process!=null){
			String code=  "res_"+ PreRecTypeEnum.InProcessInfo.getId()+"_form_flag";
			String flag = appBiz.getCfgByCode(code);
			model.addAttribute("isMonthOpen",flag);
			if(GlobalConstant.FLAG_Y.equals(flag))
			{
				String deptFlow1=process.getDeptFlow();
				String orgFlow=process.getOrgFlow();
				ResInprocessInfo info=resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow1,orgFlow);
				model.addAttribute("info",info);
				if(info==null)
				{
					model.addAttribute("inProcessInfo","科室暂未添加入科教育信息！");
				}
			}
			//百分比算法
			Map<String,Object> perMap = studentBiz.getRegPer(0, userFlow, deptFlow,null,null,true);
			model.addAttribute("perMap",perMap);

			//出科考核对接判断
			boolean ckk=false;
			String regScore=appBiz.getCfgByCode("res_doc_reg_score");
			if(GlobalConstant.FLAG_Y.equals(regScore))
			{
				String switchFlag=appBiz.getCfgByCode("res_after_test_switch");
				String urlCfg=appBiz.getCfgByCode("res_mobile_after_url_cfg");
//				String orgTestSwitch=appBiz.getCfgByCode("jswjw_"+process.getOrgFlow()+"_P004");
//				String docTestSwitch=appBiz.getCfgByCode("doc_test_switch_"+process.getOrgFlow()+"_"+process.getUserFlow());
				//学员开通出科考试权限
				Map<String,String> paramMap = new HashMap();
				String cfgCode = "res_doctor_ckks_" + userFlow;
				paramMap.put("cfgCode",cfgCode);
				String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
				if(GlobalConstant.FLAG_Y.equals(switchFlag) && GlobalConstant.FLAG_Y.equals(isCkksFlag)
						&& StringUtil.isNotBlank(urlCfg))
				{
					ckk=true;
				}

			}
			model.addAttribute("ckk",ckk);
		}
		
		List<ResSignin> signins = appBiz.getSignin(userFlow,deptFlow);
		if(signins!=null && !signins.isEmpty()){
			ResSignin signin = signins.get(0);
			if(signin!=null){
				model.addAttribute("lastSignin",signin.getSignDate());
			}
		}
		return "res/shfx/student/funcList";
	}

	@RequestMapping(value={"/inProcessInfo"},method={RequestMethod.GET})
	public String inProcessInfo(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/inProcessInfo";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/shfx/student/inProcessInfo";
		}
		ResDoctorSchProcess process = studentBiz.getProcessByResult(deptFlow);
		if(process!=null) {
			SchDept schDept=appBiz.readSchDept(process.getSchDeptFlow());
			if (schDept != null) {
				String deptFlow1 = schDept.getDeptFlow();
				String orgFlow = schDept.getOrgFlow();
				ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow1, orgFlow);
				model.addAttribute("info", info);
				if (info != null) {

					List<ResInprocessInfoMember> members = resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
					model.addAttribute("members", members);
					List<PubFile> files = pubFileBiz.searchByProductFlow(info.getRecordFlow());
					model.addAttribute("files", files);
					String arrange = info.getTeachingArrangement();
					if (StringUtil.isNotBlank(arrange)) {
						Map<String, Object> arrangeMap = new HashMap<>();
						arrangeMap = paressXml(arrange);
						model.addAttribute("arrangeMap", arrangeMap);

						Map<Integer, String> week = new HashMap<>();
						week.put(1, "周一");
						week.put(2, "周二");
						week.put(3, "周三");
						week.put(4, "周四");
						week.put(5, "周五");
						week.put(6, "周六");
						week.put(7, "周日");
						List<Map<String, String>> days = new ArrayList<>();
						for (int i = 1; i <= 7; i++) {
							String addressV = (String) arrangeMap.get("address" + i);
							String contentV = (String) arrangeMap.get("content" + i);
							if (StringUtil.isNotBlank(addressV) || StringUtil.isNotBlank(contentV)) {
								Map<String, String> m = new HashMap<>();
								m.put("address", addressV);
								m.put("content", contentV);
								m.put("dayName", week.get(i));
								days.add(m);
							}
						}
						model.addAttribute("days", days);
					}
				}
			}
		}

		return "res/shfx/student/inProcessInfo";
	}
	@RequestMapping(value={"/userCenter"},method={RequestMethod.POST})
	public String userCenter(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/userCenter";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		model.addAttribute("userinfo",userinfo);
		//读取是否开启自主增加轮转计划开关 res_custom_result_flag
		String isManualFlag = appBiz.getCfgByCode("res_custom_result_flag");
		if(!GlobalConstant.FLAG_Y.equals(isManualFlag)){
			isManualFlag = GlobalConstant.FLAG_N;
		}
		//读取是否允许学员自己入科开关 res_custom_result_flag
		String isInBySelfFlag = appBiz.getCfgByCode("res_doc_in_by_self");
		if(!GlobalConstant.FLAG_Y.equals(isInBySelfFlag)){
			isInBySelfFlag = GlobalConstant.FLAG_N;
		}
		model.addAttribute("isInBySelfFlag",isInBySelfFlag);
		model.addAttribute("manualRotationFlag",isManualFlag);

		//读取这个用户的医师信息
		ResDoctor doctor = appBiz.readResDoctor(userFlow);
		model.addAttribute("doctor", doctor);
		if(doctor==null){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/shfx/student/userCenter";
		}
		//出科考核对接判断
		boolean ckk=false;
		String regScore=appBiz.getCfgByCode("res_doc_reg_score");
		if(GlobalConstant.FLAG_Y.equals(regScore))
		{
			String switchFlag=appBiz.getCfgByCode("res_after_test_switch");
			String urlCfg=appBiz.getCfgByCode("res_mobile_after_url_cfg");
			//学员开通出科考试权限
			Map<String,String> paramMap = new HashMap();
			String cfgCode = "res_doctor_ckks_" + userFlow;
			paramMap.put("cfgCode",cfgCode);
			String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
			if(GlobalConstant.FLAG_Y.equals(switchFlag) && GlobalConstant.FLAG_Y.equals(isCkksFlag)
					&& StringUtil.isNotBlank(urlCfg))
			{
				ckk=true;
			}

		}
		model.addAttribute("isCkk",ckk);
		//获取该用户的入科记录的第一次入科时间和最后一次出科时间
		Map<String,Object> processAreaMap = appBiz.getDocProcessArea(userFlow);
		if(processAreaMap!=null){
			String minDate = (String)processAreaMap.get("minDate");
			model.addAttribute("minDate",minDate);
			doctor.setInHosDate(minDate);
			appBiz.editResDoctor(doctor);
			long schDays = 0;
			if(StringUtil.isNotBlank(minDate)){
				String currDate = DateUtil.getCurrDate();
				model.addAttribute("maxDate",currDate);

				//获取该医师的已轮转天数
				schDays = DateUtil.signDaysBetweenTowDate(currDate,minDate)+1;
				model.addAttribute("schDays",schDays);

				long trainingDays = 365;
				if((TrainYearEnum.OneYear.getId()+"").equals(doctor.getTrainingYears())){
					trainingDays*=1;
				}else if((TrainYearEnum.TwoYear.getId()+"").equals(doctor.getTrainingYears())){
					trainingDays*=2;
				}else if((TrainYearEnum.ThreeYear.getId()+"").equals(doctor.getTrainingYears())){
					trainingDays*=3;
				}else{
					trainingDays*=0;
				}
				model.addAttribute("trainingDays",trainingDays);
			}
		}
		return "res/shfx/student/userCenter";
	}
	@RequestMapping(value={"/joinExam"},method={RequestMethod.POST})
	public String joinExam(String userFlow, String deptFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/joinExam";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "40402");
			model.addAttribute("resultType", "轮转计划标识符为空");
			return "res/shfx/joinExam";
		}
		//考试地址
		String testUrl = appBiz.getCfgByCode("res_mobile_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "40403");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/shfx/joinExam";
		}
		SysUser user=appBiz.readSysUser(userFlow);
		ResDoctorSchProcess process=studentBiz.getProcessByResult(deptFlow);
		if(process==null)
		{
			model.addAttribute("resultId", "40404");
			model.addAttribute("resultType", "当前轮转信息获取失败!");
			return "res/shfx/joinExam";
		}
		SchAndStandardDeptCfg cfg =deptCfgBiz.readBySchDeptFlow(process.getSchDeptFlow());
		if(cfg==null)
		{
			model.addAttribute("resultId", "40406");
			model.addAttribute("resultType", "请基地管理员维护出科考核标准科室!");
			return "res/shfx/joinExam";
		}
		String TestNum="";
		ResPowerCfg powerCfg = resPowerCfgBiz.read("out_test_limit_" + process.getOrgFlow());
		if(powerCfg!=null&&StringUtil.isNotBlank(powerCfg.getCfgValue())) {
			TestNum=powerCfg.getCfgValue();
			int c=0;
			List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
			if (examResultsList != null)
				c=examResultsList.size();
			if(c>=Integer.valueOf(powerCfg.getCfgValue()))
			{
				model.addAttribute("resultId", "40404");
				model.addAttribute("resultType", "当前考试次数已经达到"+powerCfg.getCfgValue()+",无法再次考试!");
				return "res/shfx/joinExam";
			}
		}
		//试卷id
		String ExamSoluID = "0";
		//时间戳
		String Date = "0";
		//标准科室
		String standardDeptId = cfg.getStandardDeptId();
		ResPaper paper = paperBiz.getPaperByOrgStandardDeptId(process.getOrgName(), standardDeptId);
		if(paper==null){
			paper = paperBiz.getPaperByStandardDeptId(standardDeptId);
		}
		if (paper != null) {
			ExamSoluID = paper.getPaperFlow();
		}
		if ("0".equals(ExamSoluID)) {
			model.addAttribute("resultId", "40407");
			model.addAttribute("resultType", "该科室暂无试卷信息!");
			return "res/shfx/joinExam";
		}
		//创建分数数据
		ResScore score = appBiz.getScoreByProcess(process.getProcessFlow());
		if(score==null){
			score = new ResScore();
			score.setDoctorFlow(userFlow);
			score.setScoreTypeId(ResScoreTypeEnum.DeptScore.getId());
			score.setScoreTypeName(ResScoreTypeEnum.DeptScore.getName());
			score.setResultFlow(deptFlow);
			score.setProcessFlow(process.getProcessFlow());
			score.setSchDeptFlow(process.getSchDeptFlow());
			score.setSchDeptName(process.getSchDeptName());
		}

		score.setPaperFlow(ExamSoluID);

		int saveResult = appBiz.saveScore(score,user);
		if(GlobalConstant.ZERO_LINE>=saveResult){
			model.addAttribute("resultId", "40408");
			model.addAttribute("resultType", "分数信息创建出错!");
			return "res/shfx/joinExam";
		}
		testUrl=testUrl+"?Action=ChuKeMobileExam&ExamSoluID="+ExamSoluID+"&CardID="+ URLEncoder.encode(user.getUserCode(), "utf-8")+"&ProcessFlow="+process.getProcessFlow()+"&TestNum="+TestNum+"&Date="+Date;
		model.addAttribute("testUrl",testUrl);
		return "res/shfx/joinExam";
	}


	private Map<String,Object> paressXml(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for(Element element : elements){
					formDataMap.put(element.getName(), element.getText());
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}
	@RequestMapping(value={"/standardDeptList"},method={RequestMethod.GET})
	public String standardDeptList(String userFlow,String searchData,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/standardDeptList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "当前页数为空");
			return "res/shfx/student/standardDeptList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "3020203");
			model.addAttribute("resultType", "每页数据条数为空");
			return "res/shfx/student/standardDeptList";
		}

		ResDoctor doctor = appBiz.readResDoctor(userFlow);
		if(doctor==null || StringUtil.isEmpty(doctor.getRotationFlow())){
			model.addAttribute("resultId", "3020204");
			model.addAttribute("resultType", "读取医师信息出错");
			return "res/shfx/student/standardDeptList";
		}

		//包装查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		//解析查询条件json字符串  科室名称
		if(StringUtil.isNotBlank(searchData)){
			try {
				//为json字符串转码
				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//转换json字符串为map对象
			Map<String,String> searchMap = (Map<String,String>)JSON.parse(searchData);
			if(searchMap!=null && !searchMap.isEmpty()){
				paramMap.put("deptName ",searchMap.get("deptName "));
			}
		}
		//读取医师的轮转方案
		paramMap.put("rotationFlow",doctor.getRotationFlow());
		paramMap.put("orgFlow",doctor.getOrgFlow());
		paramMap.put("sessionNumber",doctor.getSessionNumber());

		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> resultMaps = studentBiz.getDoctorRotationDept(paramMap);
		model.addAttribute("resultMaps",resultMaps);

		model.addAttribute("dataCount",PageHelper.total);
		//查询标准科室下相应的轮转科室信息
		//组织查询条件
		Map<String,Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("doctorFlow",userFlow);
		String isAction2="N";
		if("Action2".equals(appBiz.getCfgByCode("res_sch_action_type")))
		{
			isAction2="Y";
		}
		paramMap2.put("isAction2",isAction2);
		//按条件查询轮转数据
		List<Map<String,Object>> results = studentBiz.searchResult(paramMap2);
		if(results!=null && !results.isEmpty()){

			Map<String, List<Map<String,Object>>> resultMap = new HashMap<String,List<Map<String,Object>>>();
			//轮转计划单位
			String unit = appBiz.getCfgByCode("res_rotation_unit");
			//默认按月计算
			int step = 30;
			if(SchUnitEnum.Week.getId().equals(unit)){
				//如果是周按7天算/没配置或者选择月按30天
				step = 7;
			}

			//循环计算实际轮转月份
			for(Map<String,Object> map : results){
				//获取实际的开始时间与结束时间
				String startDate = (String)map.get("schStartDate");
				String endDate = (String)map.get("schEndDate");

				BigDecimal realMonth = BigDecimal.valueOf(0);
				if(StringUtil.isNotBlank(startDate)){

					if (SchUnitEnum.Week.getId().equals(unit)) {
						//如果是周按7天算/没配置或者选择月按30天
						step = 7;
						long realDays = DateUtil.signDaysBetweenTowDate(endDate,startDate)+1;
						if (realDays != 0) {
							//计算实际轮转的月/周数
							double realMonthF = (realDays / (step * 1.0));
							realMonth = BigDecimal.valueOf(realMonthF);
							realMonth = realMonth.setScale(1, BigDecimal.ROUND_HALF_UP);
						}
					}else{
						Map<String,String> map2= new HashMap<>();
						map2.put("startDate",startDate);
						map2.put("endDate",endDate);
						Double month = TimeUtil.getMonthsBetween(map2);
						realMonth=new BigDecimal(month).setScale(1, BigDecimal.ROUND_HALF_UP);
					}
				}
				map.put("realMonth",realMonth);

				String standardDeptName = (String)map.get("standardDeptName");
				if(StringUtil.isNotBlank(standardDeptName)){
					String[] standardDeptNames = standardDeptName.split("\\.");
					int len = standardDeptNames.length;
					String subDeptName = standardDeptNames[len-1];
					map.put("standardDeptName",subDeptName);
				}

				String isCurrentFlag = (String)map.get("isCurrentFlag");
				String schFlag = (String)map.get("schFlag");
				// 是否让学员填写轮转计划
				if(GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_custom_result_flag"))||
						(!GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_custom_result_flag"))&&
								GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_doc_in_by_self"))
						)){
					if (!GlobalConstant.FLAG_Y.equals(isCurrentFlag) && !GlobalConstant.FLAG_Y.equals(schFlag)) {
						String rotationStatus = (String) map.get("rotationStatus");
						if (RotationStatusEnum.Rounding.getId().equals(rotationStatus)) {
							String processFlow = (String) map.get("processFlow");
							ResDoctorSchProcess process = new ResDoctorSchProcess();
							process.setProcessFlow(processFlow);
							process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
							int result = studentBiz.updateProcess(process);
							if (result != 0) {
								map.put("isCurrentFlag", GlobalConstant.FLAG_Y);
							}
						}
					}
				}
				String key = (String) map.get("rotationDeptFlow");
				List<Map<String,Object>> resultList= resultMap.get(key);
				if(resultList==null){
					resultList = new ArrayList<Map<String,Object>>();
					resultMap.put(key, resultList);
				}
				resultList.add(map);
			}
			model.addAttribute("resultMap",resultMap);
		}
		model.addAttribute("results",results);

		//百分比算法
		Map<String,Object> deptPerMap = studentBiz.getRegPer(0,userFlow);
		model.addAttribute("deptPerMap",deptPerMap);

		return "res/shfx/student/standardDeptList";
	}

	@RequestMapping(value={"/addRotationDept"},method={RequestMethod.POST})
	public String addRotationDept(
			String userFlow,
			String standardDeptFlow,
			String schDeptFlow,
			String schStartDate,
			String schEndDate,
			String teacherUserFlow,
			String headUserFlow,
			HttpServletRequest request,
			Model model
	) throws Exception {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3020301");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/addRotationDept";
		}

		if(StringUtil.isEmpty(standardDeptFlow)){
			model.addAttribute("resultId", "3020302");
			model.addAttribute("resultType", "标准科室标识符为空");
			return "res/shfx/student/addRotationDept";
		}

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "3020303");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/shfx/student/addRotationDept";
		}

		if(StringUtil.isEmpty(schStartDate)){
			model.addAttribute("resultId", "3020304");
			model.addAttribute("resultType", "开始时间标识符为空");
			return "res/shfx/student/addRotationDept";
		}

		if(StringUtil.isEmpty(schEndDate)){
			model.addAttribute("resultId", "3020305");
			model.addAttribute("resultType", "结束时间标识符为空");
			return "res/shfx/student/addRotationDept";
		}

		if(StringUtil.isEmpty(teacherUserFlow)){
			model.addAttribute("resultId", "3020306");
			model.addAttribute("resultType", "带教老师标识符为空");
			return "res/shfx/student/addRotationDept";
		}

		if(StringUtil.isEmpty(headUserFlow)){
			model.addAttribute("resultId", "3020307");
			model.addAttribute("resultType", "科主任标识符为空");
			return "res/shfx/student/addRotationDept";
		}

		if(schEndDate.compareTo(schStartDate)<0){
			model.addAttribute("resultId", "3020308");
			model.addAttribute("resultType", "结束时间小于开始时间");
			return "res/shfx/student/addRotationDept";
		}

		ResDoctor doctor = appBiz.readResDoctor(userFlow);
		if(doctor==null || StringUtil.isEmpty(doctor.getRotationFlow())){
			model.addAttribute("resultId", "3020309");
			model.addAttribute("resultType", "读取医师信息失败");
			return "res/shfx/student/addRotationDept";
		}

		List<SchArrangeResult> resultList = studentBiz.checkResultDate(userFlow,schStartDate,schEndDate,null,doctor.getRotationFlow());
		if(resultList!=null && !resultList.isEmpty()){
			model.addAttribute("resultId", "3020310");
			model.addAttribute("resultType", "轮转时间与其他科室重叠");
			return "res/shfx/student/addRotationDept";
		}

		studentBiz.editDoctorResult(userFlow,standardDeptFlow,schDeptFlow,schStartDate,schEndDate,teacherUserFlow,headUserFlow,null);

		return "res/shfx/student/addRotationDept";
	}

	@RequestMapping(value={"/modRotationDept"},method={RequestMethod.POST})
	public String modRotationDept(
			String userFlow,
			String standardDeptFlow,
			String schDeptFlow,
			String schStartDate,
			String schEndDate,
			String teacherUserFlow,
			String headUserFlow,
			String deptFlow,
			HttpServletRequest request,
			Model model
	) throws Exception {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3020401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/modRotationDept";
		}

		if(StringUtil.isEmpty(standardDeptFlow)){
			model.addAttribute("resultId", "3020402");
			model.addAttribute("resultType", "标准科室标识符为空");
			return "res/shfx/student/modRotationDept";
		}

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "3020403");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/shfx/student/modRotationDept";
		}

		if(StringUtil.isEmpty(schStartDate)){
			model.addAttribute("resultId", "3020404");
			model.addAttribute("resultType", "开始时间标识符为空");
			return "res/shfx/student/modRotationDept";
		}

		if(StringUtil.isEmpty(schEndDate)){
			model.addAttribute("resultId", "3020405");
			model.addAttribute("resultType", "结束时间标识符为空");
			return "res/shfx/student/modRotationDept";
		}

		if(StringUtil.isEmpty(teacherUserFlow)){
			model.addAttribute("resultId", "3020406");
			model.addAttribute("resultType", "带教老师标识符为空");
			return "res/shfx/student/modRotationDept";
		}

		if(StringUtil.isEmpty(headUserFlow)){
			model.addAttribute("resultId", "3020407");
			model.addAttribute("resultType", "科主任标识符为空");
			return "res/shfx/student/modRotationDept";
		}

		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020408");
			model.addAttribute("resultType", "轮转计划标识符为空");
			return "res/shfx/student/modRotationDept";
		}

		if(schEndDate.compareTo(schStartDate)<0){
			model.addAttribute("resultId", "3020409");
			model.addAttribute("resultType", "结束时间小于开始时间");
			return "res/shfx/student/modRotationDept";
		}

		ResDoctor doctor = appBiz.readResDoctor(userFlow);
		if(doctor==null || StringUtil.isEmpty(doctor.getRotationFlow())){
			model.addAttribute("resultId", "3020410");
			model.addAttribute("resultType", "读取医师信息失败");
			return "res/shfx/student/modRotationDept";
		}

		List<SchArrangeResult> resultList = studentBiz.checkResultDate(userFlow,schStartDate,schEndDate,deptFlow,doctor.getRotationFlow());
		if(resultList!=null && !resultList.isEmpty()){
			model.addAttribute("resultId", "3020411");
			model.addAttribute("resultType", "轮转时间与其他科室重叠");
			return "res/shfx/student/modRotationDept";
		}

		studentBiz.editDoctorResult(userFlow,standardDeptFlow,schDeptFlow,schStartDate,schEndDate,teacherUserFlow,headUserFlow,deptFlow);

		return "res/shfx/student/modRotationDept";
	}

	@RequestMapping(value={"/deleteRotationDept"},method={RequestMethod.POST})
	public String deleteRotationDept(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3020501");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/deleteRotationDept";
		}

		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020502");
			model.addAttribute("resultType", "轮转计划标识符为空");
			return "res/shfx/student/deleteRotationDept";
		}

		studentBiz.delDoctorResult(deptFlow);

		return "res/shfx/student/deleteRotationDept";
	}

	/*=====================产品学员APP优化（首页） begin===================*/

	//双向评价（详情页，已存在，对应评价科室/老师）
	@RequestMapping(value={"/gradeDeptList"},method={RequestMethod.POST})
	public String gradeDeptList(String userFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/gradeDeptList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/gradeDeptList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/gradeDeptList";
		}
		//组织查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		if("Action2".equals(appBiz.getCfgByCode("res_sch_action_type")))
		{
			paramMap.put("isAction2","Y");
		}else{
			paramMap.put("isAction2","N");
		}
		//按条件查询轮转数据
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> results = studentBiz.searchResult(paramMap);
		model.addAttribute("results", results);
		model.addAttribute("dataCount", PageHelper.total);

		List<DeptTeacherGradeInfo> gradeList = appBiz.searchAllGrade(userFlow);
		Map<String,String> gradeMap = appBiz.getNewGradeMap(gradeList);
		model.addAttribute("gradeMap", gradeMap);
		return "res/shfx/student/gradeDeptList";
	}

	//出科考试：各科室出科考试列表(已存在“开始考试”，对应出科考核)
	@RequestMapping(value={"/allAfterDept"},method={RequestMethod.POST})
	public String allAfterDept(String userFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/allAfterDept";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/allAfterDept";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/allAfterDept";
		}
		//考试地址
		String testUrl = appBiz.getCfgCode("res_mobile_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "30403");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/shfx/student/allAfterDept";
		}
		//组织查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		if("Action2".equals(appBiz.getCfgByCode("res_sch_action_type")))
		{
			paramMap.put("isAction2","Y");
		}else{
			paramMap.put("isAction2","N");
		}
		//按条件查询轮转数据
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> results = studentBiz.searchResult(paramMap);
		model.addAttribute("results", results);
		model.addAttribute("dataCount", PageHelper.total);

		Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();
		Map<String,String> countMap=new HashMap<>();
		List<String> orgFlows=new ArrayList<>();
		for(Map<String,Object> result:results) {
			ResDoctorSchProcess process=appBiz.getProcessByResultFlow((String) result.get("resultFlow"));
			if(process!=null)
			{
				ResScore score = appBiz.readScoreByProcessFlow(process.getProcessFlow());
				scoreMap.put((String) result.get("resultFlow"), score);
				List<ExamResults> examResultsList=examResultsBiz.getByProcessFlow(process.getProcessFlow());
				if(examResultsList!=null)
					countMap.put(process.getProcessFlow(), examResultsList.size()+"");
			}
			if(!orgFlows.contains(result.get("orgFlow"))) {
				ResPowerCfg powerCfg = resPowerCfgBiz.read("out_test_limit_" + result.get("orgFlow"));
				if (powerCfg != null) {
					countMap.put((String) result.get("orgFlow"), powerCfg.getCfgValue());
				}
				orgFlows.add((String) result.get("orgFlow"));
			}

		}
		String currDate = DateUtil.getCurrDate();
		model.addAttribute("currDate", currDate);
		model.addAttribute("scoreMap", scoreMap);
		model.addAttribute("countMap", countMap);
		return "res/shfx/student/allAfterDept";
	}

	@RequestMapping("/deptActivtiyList")
	public String deptActivtiyList(Model model,String userFlow,String isEval,Integer pageIndex,Integer pageSize) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/deptActivtiyList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/deptActivtiyList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/deptActivtiyList";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/student/deptActivtiyList";
		}

		ResDoctor doctor = appBiz.readResDoctor(currUser.getUserFlow());
		if(doctor==null)
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/shfx/student/deptActivtiyList";
		}
		String orgFlow = doctor.getOrgFlow();
		if(StringUtil.isBlank(orgFlow))
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "未参加培训，无法使用");
			return "res/shfx/student/deptActivtiyList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		Map<String,Object> param=new HashMap<>();
		param.put("orgFlow",orgFlow);
		List<String> itemTypeIds=new ArrayList<>();
		for(DeptActivityItemTypeEnum e: DeptActivityItemTypeEnum.values())
		{
			if(e.getIsCfg().equals("Y"))
			{
				itemTypeIds.add(e.getId());
			}
		}
		param.put("itemTypeIds",itemTypeIds);
		param.put("isEval",isEval);
		param.put("userFlow",userFlow);
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> list=studentBiz.searchStatisticList(param);

		model.addAttribute("list", list);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/shfx/student/deptActivtiyList";
	}
	@RequestMapping("/deptActivtiyEval")
	public String deptActivtiyEval(Model model,String userFlow,String  itemFlow,String evalFlow) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/deptActivtiyEval";
		}
		if(StringUtil.isEmpty(itemFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "科室活动标识符为空");
			return "res/shfx/student/deptActivtiyEval";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/student/deptActivtiyEval";
		}

		ResDoctor doctor = appBiz.readResDoctor(currUser.getUserFlow());
		if(doctor==null)
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/shfx/student/deptActivtiyEval";
		}
		SysDeptMonthPlanItem item=appBiz.readPlanItem(itemFlow);
		if(item==null)
		{
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "科室活动不存在");
			return "res/shfx/student/deptActivtiyEval";
		}
		SysDeptMonthPlanItemEval eval=appBiz.readPlanItemEval(evalFlow);
		if(eval==null)
		{
			eval=appBiz.readPlanItemEvalByUser(itemFlow,userFlow);
		}
		model.addAttribute("user",currUser);
		model.addAttribute("doctor",doctor);
		model.addAttribute("item",item);
		model.addAttribute("eval",eval);

		if(item!=null)
		{
			SysUser user=appBiz.readSysUser(item.getJoinUserFlow());
			model.addAttribute("joinUser",user);
			if(StringUtil.isNotBlank(item.getEvalCfg()))
			{

				List<QingpuLectureCfgTitleExt> titleFormList =parseDeptCfg(item.getEvalCfg());
				model.addAttribute("titleFormList", titleFormList);

			}
		}

		if(eval!=null)
		{
			String content = eval.getEvalFormContent();
			Map<String, Object> gradeMap = parseDeptGradeXml(content);
			model.addAttribute("gradeMap",gradeMap);
		}
		return "res/shfx/student/deptActivtiyEval";
	}

	private List<QingpuLectureCfgTitleExt> parseDeptCfg(String evalCfg) throws DocumentException {

		List<QingpuLectureCfgTitleExt> titleFormList = new ArrayList<>();
		if (StringUtil.isNotBlank(evalCfg)) {
			Document dom = DocumentHelper.parseText(evalCfg);
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if (titleElementList != null && !titleElementList.isEmpty()) {
				for (Element te : titleElementList) {
					QingpuLectureCfgTitleExt titleForm = new QingpuLectureCfgTitleExt();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setOrder(te.attributeValue("order"));
					List<Element> itemElementList = te.elements("item");
					List<QingpuLectureCfgItemExt> itemFormList = null;
					if (itemElementList != null && !itemElementList.isEmpty()) {
						itemFormList = new ArrayList<QingpuLectureCfgItemExt>();
						int sum = 0;
						for (Element ie : itemElementList) {
							QingpuLectureCfgItemExt itemForm = new QingpuLectureCfgItemExt();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
							String score = ie.element("score") == null ? "" : ie.element("score").getTextTrim();
							itemForm.setScore(score);
							itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
							itemFormList.add(itemForm);
							if (StringUtil.isNotBlank(score)) {
								sum += Double.parseDouble(score);
							}
						}
						titleForm.setScore(String.valueOf(sum));
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
				}
			}
		}
		return titleFormList;
	}

	@RequestMapping("/saveDeptActivtiyEval")
	public String saveDeptActivtiyEval(Model model,String userFlow,String  evalContent,String  itemFlow,HttpServletRequest request) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(itemFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "科室活动标识符为空");
			return "res/shfx/success";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/success";
		}

		ResDoctor doctor = appBiz.readResDoctor(currUser.getUserFlow());
		if(doctor==null)
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/shfx/success";
		}
		SysDeptMonthPlanItem item=appBiz.readPlanItem(itemFlow);
		if(item==null)
		{
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "科室活动不存在");
			return "res/shfx/success";
		}
		SysDeptMonthPlanItemEval eval=appBiz.readPlanItemEvalByUser(itemFlow,userFlow);
		if(eval!=null)
		{
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "已评价过科室活动，请刷新列表页面");
			return "res/shfx/success";
		}
		eval=new SysDeptMonthPlanItemEval();
		List<QingpuLectureCfgTitleExt> titleFormList =parseDeptCfg(item.getEvalCfg());
		String recContent = createDeptGradeXml(titleFormList,request.getParameterMap());
		eval.setEvalContent(evalContent);
		eval.setItemFlow(itemFlow);
		if(eval != null){
			eval.setEvalFormContent(recContent);
			if(StringUtil.isNotBlank(recContent))
			{
				Document document = DocumentHelper.parseText(recContent);
				String totalScore=document.getRootElement().elementText("totalScore");
				eval.setEvalScore(totalScore);
			}
			String userName =currUser.getUserName();
			if(StringUtil.isNotBlank(userFlow)){
				eval.setOperUserFlow(userFlow);
			}
			if(StringUtil.isNotBlank(userName)){
				eval.setOperUserName(userName);
			}
			if(doctor!=null)
			{
				eval.setOrgFlow(doctor.getOrgFlow());
				eval.setOrgName(doctor.getOrgName());
			}
		}
		if(appBiz.savePlanItemEval(eval,currUser) == GlobalConstant.ZERO_LINE){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "保存失败");
		}
		return "res/shfx/success";
	}

	private String createDeptGradeXml(List<QingpuLectureCfgTitleExt> titleFormList, Map<String, String[]> gradeInfo){
		List<String> params=new ArrayList<>();
		params.add("planTime");
		params.add("userName");
		params.add("orgName");
		params.add("name");
		params.add("teaName");
		params.add("teaTitle");
		params.add("deptName");
		params.add("shouHuo");
		Document document = DocumentHelper.createDocument();
		Element rootEle = document.addElement("gradeInfo");
		//计算总分
		Float totalScore = 0f;
		if(gradeInfo!=null){
			if(titleFormList!=null)
			{
				for(QingpuLectureCfgTitleExt titleExt:titleFormList)
				{
					for(QingpuLectureCfgItemExt itemExt: titleExt.getItemList())
					{
						if(StringUtil.isNotBlank(itemExt.getId())){
							Element grade = rootEle.addElement("grade");
							Element scoreEle = grade.addElement("score");
							grade.addAttribute("assessId",itemExt.getId());

							//获取分数并统计
							String key =itemExt.getId()+"_score";
							String[] vs = gradeInfo.get(key);
							if (vs != null && vs.length > 0 && StringUtil.isNotBlank(vs[0])) {
								scoreEle.setText(vs[0]);
								Float scf = Float.valueOf(vs[0]);
								totalScore+=scf;
							}
						}
					}
				}
			}
			for(String param:params) {
				String[] vs = gradeInfo.get(param);
				if (vs != null && vs.length > 0 && StringUtil.isNotBlank(vs[0])) {
					Element item = rootEle.addElement(param);
					item.setText(vs[0]);
				}
			}
		}
		Element totalScoreEle = rootEle.addElement("totalScore");
		totalScoreEle.setText(totalScore.toString());
		return document.asXML();
	}
	private void putMapVal(Map<String,Float> map,String key,Float val){
		if(map!=null){
			Float v = map.get(key);
			if(v==null){
				v = val;
			}else{
				v+=val;
			}
			v = new BigDecimal(v).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
			map.put(key,v);
		}
	}
	public Map<String,Object> parseDeptGradeXml(String recContent){
		Map<String,Object> gradeMap = null;
		if(StringUtil.isNotBlank(recContent)){
			try {
				Document doc = DocumentHelper.parseText(recContent);
				Element root = doc.getRootElement();
				if(root!=null){
					List<Element> items = root.elements();
					if(items!=null && items.size()>0){
						gradeMap = new HashMap<String, Object>();
						for(Element e : items){
							if("grade".equals(e.getName())) {
								String assessId = e.attributeValue("assessId");
								Map<String, String> dataMap = new HashMap<String, String>();
								gradeMap.put(assessId, dataMap);

								Element score = e.element("score");
								if (score != null) {
									String scoreStr = score.getText();
									dataMap.put("score", scoreStr);
								}
								Element lostReason = e.element("lostReason");
								if (lostReason != null) {
									dataMap.put("lostReason", lostReason.getText());
								}
							}else{

								gradeMap.put(e.getName(),e.getText());
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gradeMap;
	}
	//最新讲座信息(讲座信息)
	@RequestMapping("/getNewLectures")
	public String getNewLectures(Model model,String userFlow,Integer pageIndex,Integer pageSize) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/getNewLectures";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/getNewLectures";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/getNewLectures";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/student/getNewLectures";
		}
		String orgFlow = currUser.getOrgFlow();
		if(StringUtil.isBlank(orgFlow)){
			ResDoctor doctor = appBiz.readResDoctor(currUser.getUserFlow());
			orgFlow = doctor.getOrgFlow();
		}
		//获取当前配置的医师角色
		String doctorRole = appBiz.getCfgCode("res_doctor_role_flow");
		//获取当前配置的老师角色
		String teacherRole = appBiz.getCfgCode("res_teacher_role_flow");
		//获取当前配置的科主任角色
		String headRole = appBiz.getCfgCode("res_head_role_flow");
		//获取当前配置的科秘角色
		String secretaryRole = appBiz.getCfgCode("res_secretary_role_flow");
		String roleFlow=doctorRole;
		PageHelper.startPage(pageIndex, pageSize);
		List<ResLectureInfo> lectureInfos = appBiz.SearchNewLectures(orgFlow,"Student",roleFlow);
		model.addAttribute("lectureInfos",lectureInfos);
		model.addAttribute("dataCount", PageHelper.total);
		Map<String,ResLectureScanRegist> registMap = new HashMap<>();
		Map<String,String> evalTypeMap = new HashMap<>();
		if(lectureInfos!=null&&lectureInfos.size()>0){
			for(ResLectureInfo lectureInfo:lectureInfos){
				String lectureFlow = lectureInfo.getLectureFlow();
				ResLectureScanRegist lectureScanRegist = appBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
				registMap.put(lectureFlow,lectureScanRegist);
				evalTypeMap.put(lectureFlow,"N");
			}
			model.addAttribute("registMap",registMap);
			model.addAttribute("evalTypeMap",evalTypeMap);
		}
		String uploadBaseUrl = appBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		return "res/shfx/student/getNewLectures";
	}
	//历史讲座查询(讲座信息)
	@RequestMapping("/getHistoryLectures")
	public String getHistoryLectures(Model model,String userFlow,Integer pageIndex,Integer pageSize) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/getHistoryLectures";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/getHistoryLectures";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/getHistoryLectures";
		}
		PageHelper.startPage(pageIndex, pageSize);

		Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
		Map<String,Integer> evaMap = new HashMap<>();
		Map<String,String> scanMap = new HashMap<>();
		Map<String,String> scan2Map = new HashMap<>();
		List<Map<String,String>> newList=appBiz.getHistoryLecture(userFlow);
		Map<String,String> evalTypeMap = new HashMap<>();
		if(newList!=null&&!newList.isEmpty())
		{
			String currDateTime = DateUtil.getCurrDateTime();
			String currDate = currDateTime.substring(0,4)+"-"+currDateTime.substring(4,6)+"-"+currDateTime.substring(6,8);
			String currTime = currDateTime.substring(8,10)+":"+currDateTime.substring(10,12);
			for(Map<String,String> bean:newList){
				String isScan = bean.get("isCan");
				String isScan2 = bean.get("isCan2");
				String lectureFlow = bean.get("lectureFlow");
				String lectureEndTime = bean.get("lectureEndTime");
				String lectureTrainDate = bean.get("lectureTrainDate");
				String lectureTypeId = bean.get("lectureTypeId");
				//判断是否到评价期限
				String date = bean.get("lectureTrainDate");
				String time = bean.get("lectureEndTime");
				String unitID = bean.get("lectureUnitId");
				String period = bean.get("lectureEvaPeriod");
				String startDate = date.substring(0,4)+date.substring(5,7)+date.substring(8,10)+time.substring(0,2)+time.substring(3,5)+"00";
				int step = 0;
				if(SchUnitEnum.Hour.getId().equals(unitID)){
					step = Integer.parseInt(period);
				}
				if(SchUnitEnum.Day.getId().equals(unitID)){
					step = Integer.parseInt(period)*24;
				}
				if(SchUnitEnum.Week.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*7;
				}
				if(SchUnitEnum.Month.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*30;
				}
				if(SchUnitEnum.Year.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*365;
				}
				String endDate = DateUtil.addHour(startDate,step);
				String currentDate = DateUtil.getCurrDateTime();
				int dateFlag = endDate.compareTo(currentDate);
				//判断结束
				if((lectureEndTime.compareTo(currTime)<0&&lectureTrainDate.compareTo(currDate)==0)||(lectureTrainDate.compareTo(currDate)<0)){
					if("Y".equals(isScan))
					{
						scanMap.put(lectureFlow,"Y");
					}
					if("Y".equals(isScan2)) {
						scan2Map.put(lectureFlow, "Y");
					}
					evaMap.put(lectureFlow,dateFlag);
				}
				List<ResLectureEvaDetail> lectureEvaDetails = appBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
				if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
					ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
					evaDetailMap.put(lectureFlow,lectureEvaDetail);
				}
				evalTypeMap.put(lectureFlow,"N");
			}
		}
		model.addAttribute("evalTypeMap",evalTypeMap);
		model.addAttribute("scanMap",scanMap);
		model.addAttribute("scan2Map",scan2Map);
		model.addAttribute("evaMap",evaMap);
		model.addAttribute("dataCount", PageHelper.total);
		model.addAttribute("evaDetailMap",evaDetailMap);
		model.addAttribute("lectureInfos",newList);
		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		return "res/shfx/student/getHistoryLectures";
	}

	/**
	 * 报名讲座
	 */
	@RequestMapping("/lectureRegist")
	public synchronized String lectureRegist(String lectureFlow,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "报名成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/lectureRegist";
		}
		if(StringUtil.isEmpty(lectureFlow)){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座流水号为空");
			return "res/shfx/student/lectureRegist";
		}
		ResLectureInfo lectureInfo = appBiz.read(lectureFlow);
		if(lectureInfo==null)
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座信息不存在 ");
			return "res/xnres/student/lectureRegist";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		ResLectureScanRegist regist=appBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
		if(regist!=null&&StringUtil.isNotBlank(regist.getIsRegist())&&"Y".equals(regist.getIsRegist()))
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "已经报过名了！！");
			return "res/xnres/student/lectureRegist";
		}
		int registNum=appBiz.findLectureRegistNum(lectureFlow);
		if(StringUtil.isNotBlank(lectureInfo.getLimitNum())&&Integer.valueOf(lectureInfo.getLimitNum())<=registNum)
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "报名人数已满！ ");
			return "res/xnres/student/lectureRegist";

		}
		int count=appBiz.editLectureScanRegist(lectureFlow,currUser,regist);
		if(count==1) {
			String lectureTrainDate = lectureInfo.getLectureTrainDate();
			String lectureStartTime = lectureInfo.getLectureStartTime();
			String year = lectureTrainDate.substring(0, 4);
			model.addAttribute("year", year);
			String month = lectureTrainDate.substring(5, 7);
			model.addAttribute("month", month);
			String day = lectureTrainDate.substring(8, 10);
			model.addAttribute("day", day);
			String hour = lectureStartTime.substring(0, 2);
			model.addAttribute("hour", hour);
			String min = lectureStartTime.substring(3, 5);
			model.addAttribute("min", min);
		}else{
			model.addAttribute("resultId", "32302");
			model.addAttribute("resultType", "报名失败！");
		}
		return "res/shfx/student/lectureRegist";
	}

	/**
	 * 查看页面
	 */
	@RequestMapping("/evaluate")
	public String evaluate(String lectureFlow,String  userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/evaluate";
		}
		if(StringUtil.isEmpty(lectureFlow)){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座流水号为空");
			return "res/shfx/student/evaluate";
		}
		model.addAttribute("lectureFlow",lectureFlow);
		SysUser currUser = appBiz.readSysUser(userFlow);
		List<ResLectureEvaDetail> lectureEvaDetails = appBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
			if(resLectureEvaDetail!=null) {
				model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
			}
		}
		return "res/shfx/student/evaluate";
	}
	/**
	 * 查看页面
	 */
	@RequestMapping("/newEvaluate")
	public String newEvaluate(String lectureFlow,String  userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/newEvaluate";
		}
		if(StringUtil.isEmpty(lectureFlow)){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座流水号为空");
			return "res/shfx/student/newEvaluate";
		}
		model.addAttribute("lectureFlow",lectureFlow);
		ResLectureInfo lectureInfo = appBiz.read(lectureFlow);
		if(lectureInfo==null)
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座信息不存在 ");
			return "res/shfx/student/newEvaluate";
		}
		if(StringUtil.isNotBlank(lectureInfo.getEvalFormContent())){
			//解析评分xml
			List<Map<String,Object>> assessMaps = appBiz.parseAssessCfg(lectureInfo.getEvalFormContent());
			model.addAttribute("assessMaps",assessMaps);
		}else
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "未配置评分项 ");
			return "res/shfx/student/newEvaluate";
		}
		List<ResLectureEvaDetail> lectureEvaDetails = appBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
			if(resLectureEvaDetail!=null&&StringUtil.isNotBlank(resLectureEvaDetail.getEvalFormContent())) {
				Object formDataMap = appBiz.parseDocGradeXml(resLectureEvaDetail.getEvalFormContent());
				model.addAttribute("formDataMap",formDataMap);
				model.addAttribute("resLectureEvaDetail",resLectureEvaDetail);
			}
		}
		return "res/shfx/student/newEvaluate";
	}
	/**
	 * 保存评价
	 */
	@RequestMapping("/saveNewEvaluate")
	public String saveNewEvaluate(ResLectureEvaDetail resLectureEvaDetail,String userFlow,
								  HttpServletRequest request,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(resLectureEvaDetail.getLectureFlow())){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座流水号为空");
			return "res/shfx/success";
		}
		ResLectureInfo lectureInfo = appBiz.read(resLectureEvaDetail.getLectureFlow());
		if(lectureInfo==null)
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座信息不存在 ");
			return "res/shfx/success";
		}
		if(!StringUtil.isNotBlank(lectureInfo.getEvalFormContent()))
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "未配置评分项 ");
			return "res/shfx/success";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		String userName = currUser.getUserName();
		if(StringUtil.isNotBlank(userFlow)){
			resLectureEvaDetail.setOperUserFlow(userFlow);
		}
		if(StringUtil.isNotBlank(userName)){
			resLectureEvaDetail.setOperUserName(userName);
		}
		ResLectureScanRegist lectureScanRegists=appBiz.searchByUserFlowAndLectureFlow(userFlow,resLectureEvaDetail.getLectureFlow());
		if(lectureScanRegists==null)
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "报名信息与扫码信息为空");
			return "res/shfx/success";
		}
		if(StringUtil.isBlank(lectureScanRegists.getIsScan()))
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "扫码信息为空,不得评价");
			return "res/shfx/success";
		}
		List<ResLectureEvaDetail> lectureEvaDetails = appBiz.searchByUserFlowLectureFlow(userFlow,resLectureEvaDetail.getLectureFlow());
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			model.addAttribute("resultId", "32302");
			model.addAttribute("resultType", "已经评价过讲座信息！请刷新页面后重试！");
			return "res/shfx/success";
		}
		int count=appBiz.editNewResLectureEvaDetail(resLectureEvaDetail,userFlow,request,lectureInfo.getEvalFormContent());
		if(count==0){
			model.addAttribute("resultId", "32302");
			model.addAttribute("resultType", "保存评价失败！");
		}
		return "res/shfx/success";
	}
	/**
	 * 保存评价
	 */
	@RequestMapping("/saveEvaluate")
	public String saveEvaluate(ResLectureEvaDetail resLectureEvaDetail,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(resLectureEvaDetail.getLectureFlow())){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座流水号为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(resLectureEvaDetail.getEvaContent())){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "评价内容为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(resLectureEvaDetail.getEvaScore())){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "评价分数为空");
			return "res/shfx/success";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		String userName = currUser.getUserName();
		if(StringUtil.isNotBlank(userFlow)){
			resLectureEvaDetail.setOperUserFlow(userFlow);
		}
		if(StringUtil.isNotBlank(userName)){
			resLectureEvaDetail.setOperUserName(userName);
		}
		ResLectureScanRegist lectureScanRegists=appBiz.searchByUserFlowAndLectureFlow(userFlow,resLectureEvaDetail.getLectureFlow());
		if(lectureScanRegists==null)
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "报名信息与扫码信息为空");
			return "res/shfx/success";
		}
		if(StringUtil.isBlank(lectureScanRegists.getIsScan()))
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "扫码信息为空,不得评价");
			return "res/shfx/success";
		}
		List<ResLectureEvaDetail> lectureEvaDetails = appBiz.searchByUserFlowLectureFlow(userFlow,resLectureEvaDetail.getLectureFlow());
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			model.addAttribute("resultId", "32302");
			model.addAttribute("resultType", "已经评价过讲座信息！请刷新页面后重试！");
			return "res/shfx/success";
		}
		int count=appBiz.editResLectureEvaDetail(resLectureEvaDetail,userFlow);
		if(count==0){
			model.addAttribute("resultId", "32302");
			model.addAttribute("resultType", "保存评价失败！");
		}
		return "res/shfx/success";
	}

	//住培反馈
	@RequestMapping(value = "/suggestions")
	public String suggestions(String userFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/suggestions";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/suggestions";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/suggestions";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.readByOpinionUserFlow(userFlow);
		model.addAttribute("trainingOpinions",trainingOpinions);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/shfx/student/suggestions";
	}
	@RequestMapping(value="/delOpinions")
	public String delOpinions(String trainingOpinionFlow,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(trainingOpinionFlow)){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈流水号为空");
			return "res/shfx/success";
		}
		ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
		if(trainingOpinion==null){
			model.addAttribute("resultId", "32603");
			model.addAttribute("resultType", "意见反馈不存在");
			return "res/shfx/success";
		}
		trainingOpinion.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		int count = resLiveTrainingBiz.edit(trainingOpinion,userFlow);
		if(count==0){
			model.addAttribute("resultId", "32601");
			model.addAttribute("resultType", "删除失败");
		}
		return "res/shfx/success";
	}
	@RequestMapping(value="/opinionsDetail")
	public String opinionsDetail(String trainingOpinionFlow,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/suggestions";
		}
		if(StringUtil.isEmpty(trainingOpinionFlow)){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈流水号为空");
			return "res/shfx/student/suggestions";
		}
		ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
		List<ResTrainingOpinion> trainingOpinions=new ArrayList<>();
		trainingOpinions.add(trainingOpinion);
		model.addAttribute("trainingOpinions",trainingOpinions);
		return "res/shfx/student/suggestions";
	}
	//反馈保存操作
	@RequestMapping(value="/saveOpinions")
	public String saveOpinions(ResTrainingOpinion trainingOpinion,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(trainingOpinion.getOpinionUserContent())){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈信息为空");
			return "res/shfx/success";
		}else{
			try {
				String content = URLDecoder.decode(trainingOpinion.getOpinionUserContent(), "UTF-8") ;
				trainingOpinion.setOpinionUserContent(content);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		SysUser currentUser =appBiz.readSysUser(userFlow);
		String userName = currentUser.getUserName();
		if(StringUtil.isNotBlank(userName)){
			trainingOpinion.setOpinionUserName(userName);
		}
		trainingOpinion.setOpinionUserFlow(userFlow);
		ResDoctor doctor = appBiz.readResDoctor(userFlow);
		String orgFlow = doctor.getOrgFlow();
		String orgName = doctor.getOrgName();
		if (StringUtil.isNotBlank(orgFlow)) {
			trainingOpinion.setOrgFlow(orgFlow);
		}
		if (StringUtil.isNotBlank(orgName)) {
			trainingOpinion.setOrgName(orgName);
		}
		String currTime = DateUtil.getCurrDateTime();
		trainingOpinion.setEvaTime(currTime);
		int count=resLiveTrainingBiz.edit(trainingOpinion,userFlow);
		if(count==0){
			model.addAttribute("resultId", "32601");
			model.addAttribute("resultType", "保存失败");
		}
		return "res/shfx/success";
	}

	//最新指南（同江苏住培）
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/getZhupeiNotices"})
	public String getZhupeiNotices(String userFlow,Integer pageIndex,Integer pageSize,String noticeTitle,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/noticeList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/noticeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/noticeList";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		String orgFlow = currUser.getOrgFlow();
		ResDoctor doctor = appBiz.readResDoctor(userFlow);
		if(StringUtil.isBlank(orgFlow)){
			orgFlow = doctor.getOrgFlow();
		}
		if(pageIndex==null){
			pageIndex=1;
		}
		PageHelper.startPage(pageIndex,pageSize);
		List<ResTarinNotice> tarinNotices = resLiveTrainingBiz.searchByTitleOrgFlow(noticeTitle, orgFlow);
		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);

		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/shfx/showNotice/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		model.addAttribute("tarinNotices",tarinNotices);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/shfx/student/noticeList";
	}
	//展示指南详情（同江苏住培）
	@RequestMapping(value={"/zpNoticeDetail"})
	public String zpNoticeDetail(String userFlow,String recordFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/zpNoticeDetail";
		}
		if(StringUtil.isEmpty(recordFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培指南标识符为空");
			return "res/shfx/student/zpNoticeDetail";
		}
		ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
		if(tarinNotices==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培指南标不存在，请刷新列表页面");
			return "res/shfx/student/zpNoticeDetail";
		}
		model.addAttribute("title",tarinNotices.getResNoticeTitle());
		model.addAttribute("content",tarinNotices.getResNoticeContent());
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/shfx/showNotice/showNoticeDetail.jsp?recordFlow="+recordFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/shfx/showNotice/showNoticeDetail2.jsp?recordFlow="+recordFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/shfx/student/zpNoticeDetail";
	}
	//培训手册(已存在，对应进入培训方案)


	//科研记录
	@RequestMapping(value={"/paperDataList"},method={RequestMethod.GET})
	public String paperDataList(String userFlow,String type,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/paperDataList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/paperDataList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/paperDataList";
		}
		if(StringUtil.isBlank(type)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "添加数据类型为空");
			return "res/shfx/student/paperDataList";
		}
		if(!type.equals("Paper")&&!type.equals("Part")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型选择错误");
			return "res/shfx/student/paperDataList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		if(type.equals("Paper")){
			List<JsresDoctorPaper> list=jswjwStudentBiz.readJsresDoctorPaperByDoctorFlow(userFlow);
			model.addAttribute("list",list);
		}
		if(type.equals("Part")){
			List<JsresDoctorParticipation> list=jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByDoctorFlow(userFlow);
			model.addAttribute("list",list);

		}
		model.addAttribute("dataCount", PageHelper.total);
		return "res/shfx/student/paperDataList";
	}
	//文章发表、科研记录（新增）
	@RequestMapping(value={"/addPaperOrPart"},method={RequestMethod.POST})
	public String addPaperOrPart(String userFlow,String type,String recordFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/addPaperOrPart";
		}
		if(StringUtil.isBlank(type)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "添加数据类型为空");
			return "res/shfx/student/addPaperOrPart";
		}
		if(!type.equals("Paper")&&!type.equals("Part")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型选择错误");
			return "res/shfx/student/addPaperOrPart";
		}
		if(type.equals("Paper")&&StringUtil.isNotBlank(recordFlow)){
			JsresDoctorPaper paper=jswjwStudentBiz.readJsresDoctorPaperByFlow(recordFlow);
			model.addAttribute("resultData",paper);
		}
		if(type.equals("Part")&&StringUtil.isNotBlank(recordFlow)){
			JsresDoctorParticipation paper=jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByFlow(recordFlow);
			model.addAttribute("resultData",paper);
		}
		ResDoctor doctor=appBiz.readResDoctor(userFlow);
		model.addAttribute("doctor",doctor);
		return "res/shfx/student/addPaperOrPart";
	}
	//文章发表，科研记录保存操作
	@RequestMapping(value={"/savePaperOrPart"},method={RequestMethod.POST})
	public String savePaperOrPart(String userFlow,String type,JsresDoctorPaper paper,JsresDoctorParticipation part,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isBlank(type)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "添加数据类型为空");
			return "res/shfx/success";
		}
		if(!type.equals("Paper")&&!type.equals("Part")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型选择错误");
			return "res/shfx/success";
		}
		SysUser user=appBiz.readSysUser(userFlow);
		if(type.equals("Paper")){
			if(paper==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文信息为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(paper.getPaperDate())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "发表日期为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(paper.getPaperTitle())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文题目为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(paper.getPaperTypeId())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文类型为空");
				return "res/shfx/success";
			}else{
				String typeId=paper.getPaperTypeId();
				if("1".equals(typeId)){
					paper.setPaperTypeName("专业期刊发表的文章");
				}
				if("2".equals(typeId)){
					paper.setPaperTypeName("学术会议交流论文");
				}
				if("3".equals(typeId)){
					paper.setPaperTypeName("院（所）级学时会议交流文章");
				}
				if("4".equals(typeId)){
					paper.setPaperTypeName("提交文章");
				}
			}
			if(StringUtil.isBlank(paper.getPublishedJournals())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文信息为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(paper.getAuthor())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文信息为空");
				return "res/shfx/success";
			}
			paper.setDoctorFlow(userFlow);
			paper.setDoctorName(user.getUserName());
			int count=jswjwStudentBiz.editJsresDoctorPaper(paper,user);
			if(count==0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "保存失败");
			}
			return "res/shfx/success";
		}
		if(type.equals("Part")){

			if(part==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "科研记录为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(part.getParticipationDate())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "日期为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(part.getParticipationRoom())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "所在实验室为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(part.getParticipationAuthor())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "负责人为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(part.getParticipationTitle())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "题目为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(part.getParticipationRole())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "参与角色为空");
				return "res/shfx/success";
			}
			if(StringUtil.isBlank(part.getParticipationComplete())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "完成情况为空");
				return "res/shfx/success";
			}
			part.setDoctorFlow(userFlow);
			part.setDoctorName(user.getUserName());
			int count=jswjwStudentBiz.editJsresDoctorPart(part,user);
			if(count==0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "保存失败");
			}
			return "res/shfx/success";
		}
		return "res/shfx/success";
	}
	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST})
	public String deleteData(String userFlow,String recordFlow,String type,
							 HttpServletRequest request,
							 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}

		if(StringUtil.isBlank(type)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/shfx/success";
		}
		if(StringUtil.isBlank(recordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据流水号为空");
			return "res/shfx/success";
		}
		if(!type.equals("Paper")&&!type.equals("Part")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型选择错误");
			return "res/shfx/success";
		}
		SysUser user=appBiz.readSysUser(userFlow);
		if(type.equals("Paper")&&StringUtil.isNotBlank(recordFlow)){
			JsresDoctorPaper paper=jswjwStudentBiz.readJsresDoctorPaperByFlow(recordFlow);
			model.addAttribute("resultData",paper);
		}
		if(type.equals("Part")&&StringUtil.isNotBlank(recordFlow)){
			JsresDoctorParticipation paper=jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByFlow(recordFlow);
			model.addAttribute("resultData",paper);

		}
		if(type.equals("Paper")){
			jswjwStudentBiz.deleteJsresDoctorPaperByFlow(recordFlow);
		}
		if(type.equals("Part")){
			jswjwStudentBiz.deleteJsresDoctorParticipationByFlow(recordFlow);
		}
		return "res/shfx/success";
	}
	/*=====================产品学员APP优化（首页） end===================*/

	//签到功能
	@RequestMapping(value={"/studentSignIn"},method ={RequestMethod.POST})
	public String studentSignIn(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/studentSignIn";
		}
		SysUser user= appBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/student/studentSignIn";
		}
		ResDoctor resDoctor=appBiz.readResDoctor(userFlow);
		if(resDoctor==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/shfx/student/studentSignIn";
		}
		String nowDay=DateUtil.getCurrDate();
		List<JsresAttendanceDetail> list=appBiz.getAttendanceDetailList(nowDay,userFlow);
		int count=0;
		if(list!=null){
			count=list.size();
		}
		model.addAttribute("count",count);
		model.addAttribute("list",list);
		model.addAttribute("nowDay",nowDay);
		String timeN[]=nowDay.split("-");
		String timeInfoN=timeN[0]+"年"+timeN[1]+"月"+timeN[2]+"日";
		model.addAttribute("chinessNowDay",timeInfoN);
		model.addAttribute("resdoctor",resDoctor);
		List<ResOrgAddress> orgAddresses=timeBiz.readOrgAddress(resDoctor.getOrgFlow());
		model.addAttribute("orgAddresses", orgAddresses);
		List<ResOrgTime> times=timeBiz.readOrgTime(resDoctor.getOrgFlow());
		model.addAttribute("times", times);
		return "res/shfx/student/studentSignIn";
	}
	@RequestMapping(value={"/saveSignIn"},method ={RequestMethod.POST})
	public String saveSignIn(String userFlow,String date,String time,String local,String remark,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isBlank(date)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到日期为空");
			return "res/shfx/success";
		}
		if(StringUtil.isBlank(time)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到时间为空");
			return "res/shfx/success";
		}
		if(StringUtil.isBlank(local)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到地点为空");
			return "res/shfx/success";
		}
		SysUser user=appBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/success";
		}
		ResDoctor resDoctor=appBiz.readResDoctor(userFlow);
		if(resDoctor==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/shfx/success";
		}
		List<ResOrgTime> times=timeBiz.readOrgTime(resDoctor.getOrgFlow());
		if(times!=null&&times.size()>0)
		{
			boolean f=false;
			for(ResOrgTime t:times)
			{
				if(t.getStartTime().compareTo(time)<=0&&t.getEndTime().compareTo(time)>=0)
				{
					f=true;
				}
			}
			if(!f)
			{
				model.addAttribute("resultId", "3011102");
				model.addAttribute("resultType", "当前时间不在签到时间范围内，无法签到！");
				return "res/shfx/success";
			}
		}
		String nowDay=DateUtil.getCurrDate();
		JsresAttendance attendance=appBiz.getJsresAttendance(nowDay,userFlow);
		String attendanceFlow= PkUtil.getUUID();
		if(attendance!=null)
			attendanceFlow = attendance.getAttendanceFlow();
		if(attendance==null) {
			attendance=new JsresAttendance();
			attendance.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			attendance.setAttendanceFlow(attendanceFlow);
			attendance.setDoctorFlow(userFlow);
			attendance.setDoctorName(user.getUserName());
			attendance.setAttendDate(nowDay);
			attendance.setCreateTime(DateUtil.getCurrDateTime());
			attendance.setCreateUserFlow(userFlow);
			attendance.setModifyTime(DateUtil.getCurrDateTime());
			attendance.setModifyUserFlow(userFlow);
			appBiz.addJsresAttendance(attendance);
		}
		JsresAttendanceDetail detail=new JsresAttendanceDetail();
		detail.setRecordFlow(PkUtil.getUUID());
		detail.setAttendanceFlow(attendanceFlow);
		detail.setAttendDate(nowDay);
		detail.setAttendTime(time);
		detail.setAttendLocal(local);
		detail.setSelfRemarks(remark);
		detail.setDoctorFlow(userFlow);
		detail.setDoctorName(user.getUserName());
		detail.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		detail.setCreateTime(DateUtil.getCurrDateTime());
		detail.setCreateUserFlow(userFlow);
		detail.setModifyTime(DateUtil.getCurrDateTime());
		detail.setModifyUserFlow(userFlow);
		int count=0;
		count=appBiz.addJsresAttendanceDetail(detail);
		if(count!=1){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "签到失败！");
		}
		return "res/shfx/success";
	}
	@RequestMapping(value={"/findActivityList"},method={RequestMethod.GET,RequestMethod.POST})
	public String findActivityList(String userFlow,String typeId,String isCurrent,String deptFlow,Integer pageIndex,
								   Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/findActivityList";
		}
		if(StringUtil.isEmpty(typeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "类型标识符为空");
			return "res/shfx/student/findActivityList";
		}
		if(!"isNew".equals(typeId)&&!"isEval".equals(typeId))
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "类型标识符不正确,isNew，isEval");
			return "res/shfx/student/findActivityList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/shfx/student/findActivityList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/shfx/student/findActivityList";
		}
		ResDoctor doctor=appBiz.readResDoctor(userFlow);
		if(doctor==null)
		{
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/shfx/student/findActivityList";
		}
		Map<String,String> param=new HashMap<>();
		if ("isNew".equals(typeId)) {
			param.put("isNew","Y");//最新活动
		}
		if ("isEval".equals(typeId)) {
			param.put("isEval","Y");//活动评价
		}
		param.put("roleFlag","doctor");
		param.put("userFlow",userFlow);
		String orgFlow = "";
		if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
			orgFlow = doctor.getSecondOrgFlow();
		} else {
			orgFlow = doctor.getOrgFlow();
		}

		List<Map<String, Object>> depts=activityBiz.searchDeptByDoctor(userFlow,orgFlow);
		model.addAttribute("depts",depts);
		param.put("orgFlow",orgFlow);
		param.put("isCurrent",isCurrent);
		param.put("deptFlow",deptFlow);
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		model.addAttribute("list", list);

		model.addAttribute("dataCount", PageHelper.total);
		return "res/shfx/student/findActivityList";
	}

	@RequestMapping(value = "/joinActivity")
	public String joinActivity(Model model, String activityFlow, String userFlow) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/success";
		}
		if (StringUtil.isEmpty(activityFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/shfx/student/success";
		}
		TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
		if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
			return "res/shfx/student/success";
		}

		if (DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime()) >= 0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动已开始，无法报名！");
			return "res/shfx/student/success";
		}
		TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
		if (result != null&&GlobalConstant.FLAG_Y.equals(result.getIsRegiest())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已报名，请勿重复报名！");
			return "res/shfx/student/success";
		}
		List<TeachingActivityInfo> infos=activityBiz.checkJoinList(activityFlow,userFlow);
		if(infos!=null&&infos.size()>0)
		{
			TeachingActivityInfo activityInfo=infos.get(0);
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "已报名同一时间【"+activityInfo.getActivityName()+"】，不能报名！");
			return "res/shfx/student/success";
		}
		if(result==null){
			result = new TeachingActivityResult();
		}
		result.setActivityFlow(activityFlow);
		result.setUserFlow(userFlow);
		result.setIsRegiest(GlobalConstant.FLAG_Y);
		result.setRegiestTime(DateUtil.getCurrDateTime());
		result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		int c = activityBiz.saveResult(result, userFlow);
		if (c == 0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "报名失败！");
			return "res/shfx/student/success";
		}
		return "res/shfx/student/success";

	}

	@RequestMapping(value="/cannelRegiest")
	public String cannelRegiest(Model model,String activityFlow,String userFlow){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/success";
		}
		if (StringUtil.isEmpty(activityFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/shfx/student/success";
		}
		TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
		if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
			return "res/shfx/student/success";
		}
		TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
		if (result == null) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你未报名，无法取消报名信息！");
			return "res/shfx/student/success";
		}
		if (!GlobalConstant.FLAG_Y.equals(result.getIsRegiest()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已取消报名！");
			return "res/shfx/student/success";
		}
		if(GlobalConstant.FLAG_Y.equals(result.getIsScan()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已扫码签到，无法取消报名信息！");
			return "res/shfx/student/success";
		}
		result.setIsRegiest(GlobalConstant.FLAG_N);
		int c = activityBiz.saveResult(result, userFlow);
		if (c == 0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "取消报名失败！");
			return "res/shfx/student/success";
		}
		return "res/shfx/student/success";

	}
	@RequestMapping(value = "/showDocEval")
	public String showDocEval(Model model, String resultFlow, String userFlow) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/showDocEval";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评价标识符为空");
			return "res/shfx/student/showDocEval";
		}
		//评价人员
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		model.addAttribute("result",result);
		if(result!=null)
		{
			//评价人员评分详情
			Map<String,Object> evalDetailMap=new HashMap<>();
			//评价的指标
			List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(result.getActivityFlow());
			model.addAttribute("targets",targets);
			List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(resultFlow);
			if(evals!=null)
			{
				for(TeachingActivityEval e:evals)
				{
					evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
				}
			}
			model.addAttribute("evalDetailMap",evalDetailMap);
		}else{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "扫码信息不存在 ！");
			return "res/shfx/student/showDocEval";
		}
		return "res/shfx/student/showDocEval";

	}

	@RequestMapping(value = {"/saveEvalInfo"}, method = {RequestMethod.POST,RequestMethod.GET})
	public	 String saveEvalInfo(String evals, String resultFlow,String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {

		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/success";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择需要评价的活动");
			return "res/shfx/student/success";
		}
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		if(result==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你未扫码参加该活动，无法评价");
			return "res/shfx/student/success";
		}
		if(result!=null&&result.getEvalScore()!=null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "该活动已评价，请刷新页面！");
			return "res/shfx/student/success";
		}
		if(StringUtil.isBlank(evals))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择评分！");
			return "res/shfx/student/success";
		}
		List<TeachingActivityEval> activityEvals=null;
		try {
			activityEvals=JSON.parseArray(evals, TeachingActivityEval.class);
		}catch (Exception e){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评分数据格式不正确！");
			return "res/shfx/student/success";
		}
		if(activityEvals==null||activityEvals.size()<=0)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择评分！");
			return "res/shfx/student/success";
		}
		int count=activityBiz.saveEvalInfo(activityEvals, resultFlow,userFlow);
		if(count==0)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评价失败！");
			return "res/shfx/student/success";
		}
		return "res/shfx/student/success";
	}

	/**
	 * 查看错题
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/viewError"},method={RequestMethod.GET})
	public String viewError(String processFlow,Model model){
		String result = "res/shfx/student/viewError";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return result;
		}
		List<ExamResults> results=examResultsBiz.getByProcessFlow(processFlow);
		model.addAttribute("results",results);
		return "res/shfx/student/viewError";
	}
	@RequestMapping(value={"/viewErrorDetail"},method={RequestMethod.GET})
	public String viewErrorDetail(String processFlow,String resultsId , Model model){
		String result = "res/shfx/student/viewErrorDetail";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return result;
		}

		if(!StringUtil.isNotBlank(resultsId)){
			model.addAttribute("resultId", "30402");
			model.addAttribute("resultType", "当前考试试卷ID为空");
			return result;
		}

		//错题查看地址
		String testUrl = appBiz.getCfgCode("res_after_wrong_exam_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "30403");
			model.addAttribute("resultType", "请联系管理员维护错题查看地址");
			return result;
		}
		ExamResults results=examResultsBiz.getByFlow(resultsId);

		if(results==null) {
			model.addAttribute("resultId", "30403");
			model.addAttribute("resultType", "当前考试记录信息获取失败");
			return result;
		}

		testUrl=testUrl+"?RequestType=pc&ProcessFlow="+processFlow+"&SoluID="+results.getSoluId()+"&ResultID="+resultsId;
		model.addAttribute("testUrl",testUrl);
		return result;
	}

	@RequestMapping(value = {"/checkFile"}, method = {RequestMethod.POST})
	public String checkFile(String userFlow, String lectureFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		String result="/res/shfx/success";
		String baseDir = appBiz.getCfgCode("upload_base_dir");
		if(StringUtil.isBlank(baseDir))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系系统管理员，配置文件上传路径！");
			return result;
		}
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "01000301");
			model.addAttribute("resultType", "用户流水号为空");
			return result;
		}
		if (StringUtil.isEmpty(lectureFlow)) {
			model.addAttribute("resultId", "01000301");
			model.addAttribute("resultType", "讲座流水号为空");
			return result;
		}

		ResLectureInfo lectureInfo = appBiz.read(lectureFlow);
		if(lectureInfo==null)
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座信息不存在 ");
			return result;
		}
		if(StringUtil.isBlank(lectureInfo.getCoursewareUrl()))
		{
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "管理员未上传课件材料！ ");
			return result;
		}
		String filePath = baseDir + "/" ;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		filePath=filePath+lectureInfo.getCoursewareUrl();
		File f = new File(filePath);
		if (!f.exists()) {
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "文件信息不存在，请联系管理员！");
			return result;
		}
		return result;
	}

	@RequestMapping(value = {"/downFile"}, method = {RequestMethod.POST,RequestMethod.GET})
	public void downFile(String userFlow, String lectureFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		String baseDir = appBiz.getCfgCode("upload_base_dir");
		String filePath = baseDir + "/" ;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		ResLectureInfo lectureInfo = appBiz.read(lectureFlow);
		String fileName="未发现文件.png";
		if(lectureInfo!=null&& StringUtil.isNotBlank(lectureInfo.getCoursewareUrl()))
		{
			filePath=filePath+lectureInfo.getCoursewareUrl();
			fileName=lectureInfo.getLectureContent()+"课件"+lectureInfo.getCoursewareUrl().substring(lectureInfo.getCoursewareUrl().lastIndexOf("."));
		}

        /*文件是否存在*/
		File downLoadFile = new File(filePath);
		byte[] data = null;
		long dataLength = 0;
		/*文件是否存在*/
		if (downLoadFile.exists()) {
			InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
			data = new byte[fis.available()];
			dataLength = downLoadFile.length();
			fis.read(data);
			fis.close();
		}
		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
			response.addHeader("Content-Length", "" + dataLength);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			if (data != null) {
				outputStream.write(data);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {

		}
	}


	@RequestMapping(value={"/viewLectureImage"},method={RequestMethod.POST})
	public String viewLectureImage(String userFlow,String registFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/student/viewLectureImage";
		}
		if(StringUtil.isBlank(registFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到标识符为空");
			return "res/shfx/student/viewLectureImage";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/student/viewLectureImage";
		}

		ResLectureScanRegist regist=appBiz.readUserRegistInfo(registFlow);

		if(regist==null)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "签到信息不存在");
			return "res/shfx/student/viewLectureImage";
		}
		if(regist!=null)
		{
			String content=regist.getProofUrls();
			List<Map<String, String>> imageList=appBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
		}
		return "res/shfx/student/viewLectureImage";
	}
	@RequestMapping(value={"/addLectureImage"},method={RequestMethod.POST})
	public String addLectureImage(LectureImageFileForm form, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(form.getUserFlow())){
			model.addAttribute("resultId", "31601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(form.getRegistFlow())){
			model.addAttribute("resultId", "31602");
			model.addAttribute("resultType", "签到标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(form.getFileName())){
			model.addAttribute("resultId", "31603");
			model.addAttribute("resultType", "文件名为空");
			return "res/shfx/success";
		}
		if(form.getUploadFile()==null && StringUtil.isBlank(form.getUploadFileData())){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "上传文件不能为空");
			return "res/shfx/success";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(form.getUserFlow());
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/success";
		}

		ResLectureScanRegist regist=appBiz.readUserRegistInfo(form.getRegistFlow());

		if(regist==null)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "签到信息不存在");
			return "res/shfx/success";
		}
		appBiz.addLectureImage(form,userinfo);
		return "res/shfx/success";
	}

	@RequestMapping(value={"/deleteLectureImage"},method={RequestMethod.GET})
	public String deleteLectureImage(String userFlow,String registFlow,String imageFlow, HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(registFlow)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "签到标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(imageFlow)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "图片标识符为空");
			return "res/shfx/success";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/success";
		}

		ResLectureScanRegist regist=appBiz.readUserRegistInfo(registFlow);
		if(regist==null)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "签到信息不存在");
			return "res/shfx/success";
		}
		appBiz.deleteLectureImage(userinfo,registFlow,imageFlow);
		return "res/shfx/success";
	}

	/**
	 * 取消报名讲座
	 */
	@RequestMapping("/lectureCannelRegist")
	public synchronized String lectureCannelRegist(String lectureFlow,String roleId,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "取消成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(lectureFlow)){
			model.addAttribute("resultId", "32301");
			model.addAttribute("resultType", "讲座流水号为空");
			return "res/shfx/success";
		}
		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "roleId标识符为空");
			return "res/shfx/success";
		}
		if(!"Student".equals(roleId)&&!"Teacher".equals(roleId)&&!"Head".equals(roleId)&&!"Seretary".equals(roleId)) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "roleId标识符不正确");
			return "res/shfx/success";
		}
		SysUser currUser = appBiz.readSysUser(userFlow);
		if(currUser==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "用户不存在");
			return "res/shfx/success";
		}
		String orgFlow = currUser.getOrgFlow();
		//获取当前配置的医师角色
		String doctorRole = appBiz.getCfgCode("res_doctor_role_flow");
		//获取当前配置的老师角色
		String teacherRole = appBiz.getCfgCode("res_teacher_role_flow");
		//获取当前配置的科主任角色
		String headRole = appBiz.getCfgCode("res_head_role_flow");
		String roleFlow="";
		ResDoctor doctor=null;
		if("Student".equals(roleId)) {
			doctor = appBiz.readResDoctor(currUser.getUserFlow());
			orgFlow = doctor.getOrgFlow();
			roleFlow=doctorRole;
		}
		if("Teacher".equals(roleId)){
			roleFlow=teacherRole;
		}
		if("Head".equals(roleId)) {
			roleFlow=headRole;
		}
		ResLectureScanRegist regist=appBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
		if(regist==null)
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "你未报名，无法取消报名信息！");
			return "res/shfx/success";
		}
		if(StringUtil.isBlank(regist.getIsRegist()))
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "你未报名，无法取消报名信息");
			return "res/shfx/success";
		}
		if (!GlobalConstant.FLAG_Y.equals(regist.getIsRegist()))
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "你已取消报名！");
			return "res/shfx/success";
		}
		if(GlobalConstant.FLAG_Y.equals(regist.getIsScan()))
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "你已扫码签到，无法取消报名信息！");
			return "res/shfx/success";
		}
		regist.setIsRegist(GlobalConstant.FLAG_N);
		int c=appBiz.scanRegist(regist);
		if(c==0)
		{
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "取消失败！");
			return "res/shfx/success";
		}
		return "res/shfx/success";
	}
}

