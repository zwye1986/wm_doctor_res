package com.pinde.res.ctrl.hbres;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.*;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IJswjwStudentBiz;
import com.pinde.res.biz.jswjw.ISchExamCfgBiz;
import com.pinde.res.biz.jswjw.ISchExamScoreQueryBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.enums.hbres.AfterRecTypeEnum;
import com.pinde.res.enums.hbres.RotationStatusEnum;
import com.pinde.res.enums.hbres.SchUnitEnum;
import com.pinde.res.enums.hbres.TrainYearEnum;
import com.pinde.res.enums.stdp.PreRecTypeEnum;
import com.pinde.res.enums.stdp.ResScoreTypeEnum;
import com.pinde.res.enums.stdp.ResultEnum;
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
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/res/hbres/student")
public class HbresStudentController{
	private static Logger logger = LoggerFactory.getLogger(HbresStudentController.class);

	@Autowired
	private IJswjwBiz jswjwBiz;
	@Autowired
	private IHbresStudentBiz hbresStudentBiz;
	@Autowired
	private IHbresAppBiz hbresAppBiz;
	@Autowired
	private IResOrgTimeBiz timeBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
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
	IHbResLiveTrainingBiz resLiveTrainingBiz;
	@Autowired
	IJswjwStudentBiz jswjwStudentBiz;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private IResActivityTargetBiz activityTargeBiz;
	@Autowired
	private IExamResultsBiz examResultsBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IInxInfoBiz inxInfoBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/stdp/500";
    }

	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/hbres/student/test";
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
		return "/res/hbres/student/test";
	}

	@RequestMapping(value={"/deptList"},method={RequestMethod.GET})
	public String deptList(String userFlow,String searchData, Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3020101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/deptList";
		}
		
		if(pageIndex==null){
			model.addAttribute("resultId", "3020102");
			model.addAttribute("resultType", "起始页为空");
			return "res/hbres/student/deptList";
		}
		
		if(pageSize==null){
			model.addAttribute("resultId", "3020103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/hbres/student/deptList";
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
		if("Action2".equals(hbresAppBiz.getCfgByCode("res_sch_action_type")))
		{
			paramMap.put("isAction2","Y");
		}else{
			paramMap.put("isAction2","N");
		}
		//按条件查询轮转数据
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> results = hbresStudentBiz.searchResult(paramMap);
		
		if(results!=null && !results.isEmpty()){
			//轮转计划单位
			String unit = hbresAppBiz.getCfgByCode("res_rotation_unit");
			
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
				if(GlobalConstant.FLAG_Y.equals(hbresAppBiz.getCfgByCode("res_custom_result_flag"))||
						(!GlobalConstant.FLAG_Y.equals(hbresAppBiz.getCfgByCode("res_custom_result_flag"))&&
								GlobalConstant.FLAG_Y.equals(hbresAppBiz.getCfgByCode("res_doc_in_by_self"))
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
							int result = hbresStudentBiz.updateProcess(process);
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
		Map<String,Object> deptPerMap = hbresStudentBiz.getRegPer(0,userFlow);
		model.addAttribute("deptPerMap",deptPerMap);

		//System.out.println("deptPerMap:"+ JSON.toJSONString(deptPerMap));
		//数据量
		model.addAttribute("dataCount", PageHelper.total);
		
		return "res/hbres/student/deptList";
	}
	
	@RequestMapping(value={"/funcList"},method={RequestMethod.GET})
	public String funcList(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/funcList";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/hbres/student/funcList";
		}
    	
		ResDoctorSchProcess process = hbresStudentBiz.getProcessByResult(deptFlow);
		model.addAttribute("process",process);
		model.addAttribute("disabled",process==null);
		model.addAttribute("disabledTip","该项暂时无法填写数据！");

		//学员开通app登录权限
		Map<String, String> appParamMap = new HashMap();
		String appCfgCode = "res_doctor_app_login_" + userFlow;
		appParamMap.put("cfgCode", appCfgCode);
		String isAppFlag = resPowerCfgBiz.getResPowerCfg(appParamMap);
		model.addAttribute("isAppFlag", isAppFlag);

		if(process!=null){
			ResSchProcessExpress rec = expressBiz.getExpressByRecType(process.getProcessFlow(), AfterRecTypeEnum.AfterEvaluation.getId());
			model.addAttribute("afterEva", rec);
			String code=  "res_"+ PreRecTypeEnum.InProcessInfo.getId()+"_form_flag";
			String flag = hbresAppBiz.getCfgByCode(code);
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
			Map<String,Object> perMap = hbresStudentBiz.getRegPer(0, userFlow, deptFlow,null,null,true);
			model.addAttribute("perMap",perMap);

			//出科考核对接判断
			boolean ckk=false;
			String regScore=hbresAppBiz.getCfgByCode("res_doc_reg_score");
			if(GlobalConstant.FLAG_Y.equals(regScore))
			{
				String switchFlag=hbresAppBiz.getCfgByCode("res_after_test_switch");
				String urlCfg=hbresAppBiz.getCfgByCode("res_mobile_after_url_cfg");
//				String orgTestSwitch=hbresAppBiz.getCfgByCode("jswjw_"+process.getOrgFlow()+"_P004");
//				String docTestSwitch=hbresAppBiz.getCfgByCode("doc_test_switch_"+process.getOrgFlow()+"_"+process.getUserFlow());
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
		
		List<ResSignin> signins = hbresAppBiz.getSignin(userFlow,deptFlow);
		if(signins!=null && !signins.isEmpty()){
			ResSignin signin = signins.get(0);
			if(signin!=null){
				model.addAttribute("lastSignin",signin.getSignDate());
			}
		}
		return "res/hbres/student/funcList";
	}

	@RequestMapping(value={"/inProcessInfo"},method={RequestMethod.GET})
	public String inProcessInfo(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/inProcessInfo";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/hbres/student/inProcessInfo";
		}
		ResDoctorSchProcess process = hbresStudentBiz.getProcessByResult(deptFlow);
		if(process!=null) {
			SchDept schDept=hbresAppBiz.readSchDept(process.getSchDeptFlow());
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

		return "res/hbres/student/inProcessInfo";
	}
	@RequestMapping(value={"/userCenter"},method={RequestMethod.POST})
	public String userCenter(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/userCenter";
		}
		//验证用户是否存在
		SysUser userinfo = hbresAppBiz.readSysUser(userFlow);
		model.addAttribute("userinfo",userinfo);
		//读取是否开启自主增加轮转计划开关 res_custom_result_flag
		String isManualFlag = hbresAppBiz.getCfgByCode("res_custom_result_flag");
		if(!GlobalConstant.FLAG_Y.equals(isManualFlag)){
			isManualFlag = GlobalConstant.FLAG_N;
		}
		//读取是否允许学员自己入科开关 res_custom_result_flag
		String isInBySelfFlag = hbresAppBiz.getCfgByCode("res_doc_in_by_self");
		if(!GlobalConstant.FLAG_Y.equals(isInBySelfFlag)){
			isInBySelfFlag = GlobalConstant.FLAG_N;
		}
		model.addAttribute("isInBySelfFlag",isInBySelfFlag);
		model.addAttribute("manualRotationFlag",isManualFlag);

		//读取这个用户的医师信息
		ResDoctor doctor = hbresAppBiz.readResDoctor(userFlow);
		model.addAttribute("doctor", doctor);
		if(doctor==null){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/hbres/student/userCenter";
		}
		//出科考核对接判断
		boolean ckk=false;
		String regScore=hbresAppBiz.getCfgByCode("res_doc_reg_score");
		if(GlobalConstant.FLAG_Y.equals(regScore))
		{
			String switchFlag=hbresAppBiz.getCfgByCode("res_after_test_switch");
			String urlCfg=hbresAppBiz.getCfgByCode("res_mobile_after_url_cfg");
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
		Map<String,Object> processAreaMap = hbresAppBiz.getDocProcessArea(userFlow);
		if(processAreaMap!=null){
			String minDate = (String)processAreaMap.get("minDate");
			model.addAttribute("minDate",minDate);
			doctor.setInHosDate(minDate);
			hbresAppBiz.editResDoctor(doctor);
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
		return "res/hbres/student/userCenter";
	}
	@RequestMapping(value={"/joinExam"},method={RequestMethod.POST})
	public String joinExam(String userFlow, String deptFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "40401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/joinExam";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "40402");
			model.addAttribute("resultType", "轮转计划标识符为空");
			return "res/hbres/joinExam";
		}
		//考试地址
		String testUrl = hbresAppBiz.getCfgByCode("res_mobile_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "40403");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/hbres/joinExam";
		}
		SysUser user=hbresAppBiz.readSysUser(userFlow);
		ResDoctorSchProcess process=hbresStudentBiz.getProcessByResult(deptFlow);
		if(process==null)
		{
			model.addAttribute("resultId", "40404");
			model.addAttribute("resultType", "当前轮转信息获取失败!");
			return "res/hbres/joinExam";
		}
		SchAndStandardDeptCfg cfg =deptCfgBiz.readBySchDeptFlow(process.getSchDeptFlow());
		if(cfg==null)
		{
			model.addAttribute("resultId", "40406");
			model.addAttribute("resultType", "请基地管理员维护出科考核标准科室!");
			return "res/hbres/joinExam";
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
				return "res/gzzyyy/joinExam";
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
			return "res/hbres/joinExam";
		}
		//创建分数数据
		ResScore score = hbresAppBiz.getScoreByProcess(process.getProcessFlow());
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

		int saveResult = hbresAppBiz.saveScore(score,user);
		if(GlobalConstant.ZERO_LINE>=saveResult){
			model.addAttribute("resultId", "40408");
			model.addAttribute("resultType", "分数信息创建出错!");
			return "res/hbres/joinExam";
		}
		testUrl=testUrl+"?Action=ChuKeMobileExam&ExamSoluID="+ExamSoluID+"&CardID="+ URLEncoder.encode(user.getUserCode(), "utf-8")+"&ProcessFlow="+process.getProcessFlow()+"&TestNum="+TestNum+"&Date="+Date;
		model.addAttribute("testUrl",testUrl);
		return "res/hbres/joinExam";
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
			return "res/hbres/student/standardDeptList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "当前页数为空");
			return "res/hbres/student/standardDeptList";
		}

		if(pageSize==null){
			model.addAttribute("resultId", "3020203");
			model.addAttribute("resultType", "每页数据条数为空");
			return "res/hbres/student/standardDeptList";
		}

		ResDoctor doctor = hbresAppBiz.readResDoctor(userFlow);
		if(doctor==null || StringUtil.isEmpty(doctor.getRotationFlow())){
			model.addAttribute("resultId", "3020204");
			model.addAttribute("resultType", "读取医师信息出错");
			return "res/hbres/student/standardDeptList";
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
		List<Map<String,Object>> resultMaps = hbresStudentBiz.getDoctorRotationDept(paramMap);
		model.addAttribute("resultMaps",resultMaps);

		model.addAttribute("dataCount",PageHelper.total);
		//查询标准科室下相应的轮转科室信息
		//组织查询条件
		Map<String,Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("doctorFlow",userFlow);
		String isAction2="N";
		if("Action2".equals(hbresAppBiz.getCfgByCode("res_sch_action_type")))
		{
			isAction2="Y";
		}
		paramMap2.put("isAction2",isAction2);
		//按条件查询轮转数据
		List<Map<String,Object>> results = hbresStudentBiz.searchResult(paramMap2);
		if(results!=null && !results.isEmpty()){

			Map<String, List<Map<String,Object>>> resultMap = new HashMap<String,List<Map<String,Object>>>();
			//轮转计划单位
			String unit = hbresAppBiz.getCfgByCode("res_rotation_unit");
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
				if(GlobalConstant.FLAG_Y.equals(hbresAppBiz.getCfgByCode("res_custom_result_flag"))||
						(!GlobalConstant.FLAG_Y.equals(hbresAppBiz.getCfgByCode("res_custom_result_flag"))&&
								GlobalConstant.FLAG_Y.equals(hbresAppBiz.getCfgByCode("res_doc_in_by_self"))
						)){
					if (!GlobalConstant.FLAG_Y.equals(isCurrentFlag) && !GlobalConstant.FLAG_Y.equals(schFlag)) {
						String rotationStatus = (String) map.get("rotationStatus");
						if (RotationStatusEnum.Rounding.getId().equals(rotationStatus)) {
							String processFlow = (String) map.get("processFlow");
							ResDoctorSchProcess process = new ResDoctorSchProcess();
							process.setProcessFlow(processFlow);
							process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
							int result = hbresStudentBiz.updateProcess(process);
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
		Map<String,Object> deptPerMap = hbresStudentBiz.getRegPer(0,userFlow);
		model.addAttribute("deptPerMap",deptPerMap);

		return "res/hbres/student/standardDeptList";
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
			return "res/hbres/student/addRotationDept";
		}

		if(StringUtil.isEmpty(standardDeptFlow)){
			model.addAttribute("resultId", "3020302");
			model.addAttribute("resultType", "标准科室标识符为空");
			return "res/hbres/student/addRotationDept";
		}

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "3020303");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/hbres/student/addRotationDept";
		}

		if(StringUtil.isEmpty(schStartDate)){
			model.addAttribute("resultId", "3020304");
			model.addAttribute("resultType", "开始时间标识符为空");
			return "res/hbres/student/addRotationDept";
		}

		if(StringUtil.isEmpty(schEndDate)){
			model.addAttribute("resultId", "3020305");
			model.addAttribute("resultType", "结束时间标识符为空");
			return "res/hbres/student/addRotationDept";
		}

		if(StringUtil.isEmpty(teacherUserFlow)){
			model.addAttribute("resultId", "3020306");
			model.addAttribute("resultType", "带教老师标识符为空");
			return "res/hbres/student/addRotationDept";
		}

		if(StringUtil.isEmpty(headUserFlow)){
			model.addAttribute("resultId", "3020307");
			model.addAttribute("resultType", "科主任标识符为空");
			return "res/hbres/student/addRotationDept";
		}

		if(schEndDate.compareTo(schStartDate)<0){
			model.addAttribute("resultId", "3020308");
			model.addAttribute("resultType", "结束时间小于开始时间");
			return "res/hbres/student/addRotationDept";
		}

		ResDoctor doctor = hbresAppBiz.readResDoctor(userFlow);
		if(doctor==null || StringUtil.isEmpty(doctor.getRotationFlow())){
			model.addAttribute("resultId", "3020309");
			model.addAttribute("resultType", "读取医师信息失败");
			return "res/hbres/student/addRotationDept";
		}

		List<SchArrangeResult> resultList = hbresStudentBiz.checkResultDate(userFlow,schStartDate,schEndDate,null,doctor.getRotationFlow());
		if(resultList!=null && !resultList.isEmpty()){
			model.addAttribute("resultId", "3020310");
			model.addAttribute("resultType", "轮转时间与其他科室重叠");
			return "res/hbres/student/addRotationDept";
		}

		hbresStudentBiz.editDoctorResult(userFlow,standardDeptFlow,schDeptFlow,schStartDate,schEndDate,teacherUserFlow,headUserFlow,null);

		return "res/hbres/student/addRotationDept";
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
			return "res/hbres/student/modRotationDept";
		}

		if(StringUtil.isEmpty(standardDeptFlow)){
			model.addAttribute("resultId", "3020402");
			model.addAttribute("resultType", "标准科室标识符为空");
			return "res/hbres/student/modRotationDept";
		}

		if(StringUtil.isEmpty(schDeptFlow)){
			model.addAttribute("resultId", "3020403");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/hbres/student/modRotationDept";
		}

		if(StringUtil.isEmpty(schStartDate)){
			model.addAttribute("resultId", "3020404");
			model.addAttribute("resultType", "开始时间标识符为空");
			return "res/hbres/student/modRotationDept";
		}

		if(StringUtil.isEmpty(schEndDate)){
			model.addAttribute("resultId", "3020405");
			model.addAttribute("resultType", "结束时间标识符为空");
			return "res/hbres/student/modRotationDept";
		}

		if(StringUtil.isEmpty(teacherUserFlow)){
			model.addAttribute("resultId", "3020406");
			model.addAttribute("resultType", "带教老师标识符为空");
			return "res/hbres/student/modRotationDept";
		}

		if(StringUtil.isEmpty(headUserFlow)){
			model.addAttribute("resultId", "3020407");
			model.addAttribute("resultType", "科主任标识符为空");
			return "res/hbres/student/modRotationDept";
		}

		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020408");
			model.addAttribute("resultType", "轮转计划标识符为空");
			return "res/hbres/student/modRotationDept";
		}

		if(schEndDate.compareTo(schStartDate)<0){
			model.addAttribute("resultId", "3020409");
			model.addAttribute("resultType", "结束时间小于开始时间");
			return "res/hbres/student/modRotationDept";
		}

		ResDoctor doctor = hbresAppBiz.readResDoctor(userFlow);
		if(doctor==null || StringUtil.isEmpty(doctor.getRotationFlow())){
			model.addAttribute("resultId", "3020410");
			model.addAttribute("resultType", "读取医师信息失败");
			return "res/hbres/student/modRotationDept";
		}

		List<SchArrangeResult> resultList = hbresStudentBiz.checkResultDate(userFlow,schStartDate,schEndDate,deptFlow,doctor.getRotationFlow());
		if(resultList!=null && !resultList.isEmpty()){
			model.addAttribute("resultId", "3020411");
			model.addAttribute("resultType", "轮转时间与其他科室重叠");
			return "res/hbres/student/modRotationDept";
		}

		hbresStudentBiz.editDoctorResult(userFlow,standardDeptFlow,schDeptFlow,schStartDate,schEndDate,teacherUserFlow,headUserFlow,deptFlow);

		return "res/hbres/student/modRotationDept";
	}

	@RequestMapping(value={"/deleteRotationDept"},method={RequestMethod.POST})
	public String deleteRotationDept(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3020501");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/deleteRotationDept";
		}

		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020502");
			model.addAttribute("resultType", "轮转计划标识符为空");
			return "res/hbres/student/deleteRotationDept";
		}

		hbresStudentBiz.delDoctorResult(deptFlow);

		return "res/hbres/student/deleteRotationDept";
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
			return "res/hbres/student/gradeDeptList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/hbres/student/gradeDeptList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/hbres/student/gradeDeptList";
		}
		//组织查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		if("Action2".equals(hbresAppBiz.getCfgByCode("res_sch_action_type")))
		{
			paramMap.put("isAction2","Y");
		}else{
			paramMap.put("isAction2","N");
		}
		//按条件查询轮转数据
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> results = hbresStudentBiz.searchResult(paramMap);
		model.addAttribute("results", results);
		model.addAttribute("dataCount", PageHelper.total);

		List<DeptTeacherGradeInfo> gradeList = hbresAppBiz.searchAllGrade(userFlow);
		Map<String,String> gradeMap = hbresAppBiz.getNewGradeMap(gradeList);
		model.addAttribute("gradeMap", gradeMap);
		return "res/hbres/student/gradeDeptList";
	}

	//出科考试：各科室出科考试列表(已存在“开始考试”，对应出科考核)
	@RequestMapping(value={"/allAfterDept"},method={RequestMethod.POST})
	public String allAfterDept(String userFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/allAfterDept";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/hbres/student/allAfterDept";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/hbres/student/allAfterDept";
		}
		//考试地址
		String testUrl = hbresAppBiz.getCfgCode("res_mobile_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "30403");
			model.addAttribute("resultType", "请联系管理员维护考试地址!");
			return "res/hbres/student/allAfterDept";
		}
		//组织查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		if("Action2".equals(hbresAppBiz.getCfgByCode("res_sch_action_type")))
		{
			paramMap.put("isAction2","Y");
		}else{
			paramMap.put("isAction2","N");
		}
		//按条件查询轮转数据
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> results = hbresStudentBiz.searchResult(paramMap);
		model.addAttribute("results", results);
		model.addAttribute("dataCount", PageHelper.total);

		Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();
		Map<String,String> countMap=new HashMap<>();
		List<String> orgFlows=new ArrayList<>();
		for(Map<String,Object> result:results) {
			ResDoctorSchProcess process=hbresAppBiz.getProcessByResultFlow((String) result.get("resultFlow"));
			if(process!=null)
			{
				ResScore score = hbresAppBiz.readScoreByProcessFlow(process.getProcessFlow());
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
		return "res/hbres/student/allAfterDept";
	}


	//住培反馈
	@RequestMapping(value = "/suggestions")
	public String suggestions(String userFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/suggestions";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/hbres/student/suggestions";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/hbres/student/suggestions";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.readByOpinionUserFlow(userFlow);
		model.addAttribute("trainingOpinions",trainingOpinions);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/hbres/student/suggestions";
	}
	@RequestMapping(value="/delOpinions")
	public String delOpinions(String trainingOpinionFlow,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/success";
		}
		if(StringUtil.isEmpty(trainingOpinionFlow)){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈流水号为空");
			return "res/hbres/success";
		}
		ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
		if(trainingOpinion==null){
			model.addAttribute("resultId", "32603");
			model.addAttribute("resultType", "意见反馈不存在");
			return "res/hbres/success";
		}
		trainingOpinion.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		int count = resLiveTrainingBiz.edit(trainingOpinion,userFlow);
		if(count==0){
			model.addAttribute("resultId", "32601");
			model.addAttribute("resultType", "删除失败");
		}
		return "res/hbres/success";
	}
	@RequestMapping(value="/opinionsDetail")
	public String opinionsDetail(String trainingOpinionFlow,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/suggestions";
		}
		if(StringUtil.isEmpty(trainingOpinionFlow)){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈流水号为空");
			return "res/hbres/student/suggestions";
		}
		ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
		List<ResTrainingOpinion> trainingOpinions=new ArrayList<>();
		trainingOpinions.add(trainingOpinion);
		model.addAttribute("trainingOpinions",trainingOpinions);
		return "res/hbres/student/suggestions";
	}
	//反馈保存操作
	@RequestMapping(value="/saveOpinions")
	public String saveOpinions(ResTrainingOpinion trainingOpinion,String userFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/success";
		}
		if(StringUtil.isEmpty(trainingOpinion.getOpinionUserContent())){
			model.addAttribute("resultId", "32602");
			model.addAttribute("resultType", "意见反馈信息为空");
			return "res/hbres/success";
		}else{
			try {
				String content = URLDecoder.decode(trainingOpinion.getOpinionUserContent(), "UTF-8") ;
				trainingOpinion.setOpinionUserContent(content);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		SysUser currentUser =hbresAppBiz.readSysUser(userFlow);
		String userName = currentUser.getUserName();
		if(StringUtil.isNotBlank(userName)){
			trainingOpinion.setOpinionUserName(userName);
		}
		trainingOpinion.setOpinionUserFlow(userFlow);
		ResDoctor doctor = hbresAppBiz.readResDoctor(userFlow);
		String orgFlow = doctor.getOrgFlow();
		String orgName = doctor.getOrgName();
		if (StringUtil.isNotBlank(orgFlow)) {
			trainingOpinion.setOrgFlow(orgFlow);
		}
		if(StringUtil.isNotBlank(orgName)){
			trainingOpinion.setOrgName(orgName);
		}
		String currTime = DateUtil.getCurrDateTime();
		trainingOpinion.setEvaTime(currTime);
		int count=resLiveTrainingBiz.edit(trainingOpinion,userFlow);
		if(count==0){
			model.addAttribute("resultId", "32601");
			model.addAttribute("resultType", "保存失败");
		}
		return "res/hbres/success";
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
			return "res/hbres/student/paperDataList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/hbres/student/paperDataList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/hbres/student/paperDataList";
		}
		if(StringUtil.isBlank(type)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "添加数据类型为空");
			return "res/hbres/student/paperDataList";
		}
		if(!type.equals("Paper")&&!type.equals("Part")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型选择错误");
			return "res/hbres/student/paperDataList";
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
		return "res/hbres/student/paperDataList";
	}
	//文章发表、科研记录（新增）
	@RequestMapping(value={"/addPaperOrPart"},method={RequestMethod.POST})
	public String addPaperOrPart(String userFlow,String type,String recordFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/addPaperOrPart";
		}
		if(StringUtil.isBlank(type)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "添加数据类型为空");
			return "res/hbres/student/addPaperOrPart";
		}
		if(!type.equals("Paper")&&!type.equals("Part")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型选择错误");
			return "res/hbres/student/addPaperOrPart";
		}
		if(type.equals("Paper")&&StringUtil.isNotBlank(recordFlow)){
			JsresDoctorPaper paper=jswjwStudentBiz.readJsresDoctorPaperByFlow(recordFlow);
			model.addAttribute("resultData",paper);
		}
		if(type.equals("Part")&&StringUtil.isNotBlank(recordFlow)){
			JsresDoctorParticipation paper=jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByFlow(recordFlow);
			model.addAttribute("resultData",paper);
		}
		ResDoctor doctor=hbresAppBiz.readResDoctor(userFlow);
		model.addAttribute("doctor",doctor);
		return "res/hbres/student/addPaperOrPart";
	}
	//文章发表，科研记录保存操作
	@RequestMapping(value={"/savePaperOrPart"},method={RequestMethod.POST})
	public String savePaperOrPart(String userFlow,String type,JsresDoctorPaper paper,JsresDoctorParticipation part,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/success";
		}
		if(StringUtil.isBlank(type)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "添加数据类型为空");
			return "res/hbres/success";
		}
		if(!type.equals("Paper")&&!type.equals("Part")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型选择错误");
			return "res/hbres/success";
		}
		SysUser user=hbresAppBiz.readSysUser(userFlow);
		if(type.equals("Paper")){
			if(paper==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文信息为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(paper.getPaperDate())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "发表日期为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(paper.getPaperTitle())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文题目为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(paper.getPaperTypeId())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文类型为空");
				return "res/hbres/success";
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
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(paper.getAuthor())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "论文信息为空");
				return "res/hbres/success";
			}
			paper.setDoctorFlow(userFlow);
			paper.setDoctorName(user.getUserName());
			int count=jswjwStudentBiz.editJsresDoctorPaper(paper,user);
			if(count==0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "保存失败");
			}
			return "res/hbres/success";
		}
		if(type.equals("Part")){

			if(part==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "科研记录为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(part.getParticipationDate())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "日期为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(part.getParticipationRoom())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "所在实验室为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(part.getParticipationAuthor())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "负责人为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(part.getParticipationTitle())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "题目为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(part.getParticipationRole())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "参与角色为空");
				return "res/hbres/success";
			}
			if(StringUtil.isBlank(part.getParticipationComplete())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "完成情况为空");
				return "res/hbres/success";
			}
			part.setDoctorFlow(userFlow);
			part.setDoctorName(user.getUserName());
			int count=jswjwStudentBiz.editJsresDoctorPart(part,user);
			if(count==0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "保存失败");
			}
			return "res/hbres/success";
		}
		return "res/hbres/success";
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
			return "res/hbres/success";
		}

		if(StringUtil.isBlank(type)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/hbres/success";
		}
		if(StringUtil.isBlank(recordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据流水号为空");
			return "res/hbres/success";
		}
		if(!type.equals("Paper")&&!type.equals("Part")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型选择错误");
			return "res/hbres/success";
		}
		SysUser user=hbresAppBiz.readSysUser(userFlow);
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
		return "res/hbres/success";
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
			return "res/hbres/student/studentSignIn";
		}
		SysUser user= hbresAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "用户不存在");
			return "res/hbres/student/studentSignIn";
		}
		ResDoctor resDoctor=hbresAppBiz.readResDoctor(userFlow);
		if(resDoctor==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/hbres/student/studentSignIn";
		}
		String nowDay=DateUtil.getCurrDate();
		List<JsresAttendanceDetail> list=hbresAppBiz.getAttendanceDetailList(nowDay,userFlow);
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
		return "res/hbres/student/studentSignIn";
	}
	@RequestMapping(value={"/saveSignIn"},method ={RequestMethod.POST})
	public String saveSignIn(String userFlow,String date,String time,String local,String remark,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/success";
		}
		if(StringUtil.isBlank(date)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到日期为空");
			return "res/hbres/success";
		}
		if(StringUtil.isBlank(time)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到时间为空");
			return "res/hbres/success";
		}
		if(StringUtil.isBlank(local)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到地点为空");
			return "res/hbres/success";
		}
		SysUser user=hbresAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "用户不存在");
			return "res/hbres/success";
		}
		ResDoctor resDoctor=hbresAppBiz.readResDoctor(userFlow);
		if(resDoctor==null){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/hbres/success";
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
				return "res/hbres/success";
			}
		}
		String nowDay=DateUtil.getCurrDate();
		JsresAttendance attendance=hbresAppBiz.getJsresAttendance(nowDay,userFlow);
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
			hbresAppBiz.addJsresAttendance(attendance);
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
		count=hbresAppBiz.addJsresAttendanceDetail(detail);
		if(count!=1){
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "签到失败！");
		}
		return "res/hbres/success";
	}
	@RequestMapping(value={"/findActivityList"},method={RequestMethod.GET,RequestMethod.POST})
	public String findActivityList(String userFlow,String typeId,String isCurrent,String deptFlow,Integer pageIndex,
								   Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/findActivityList";
		}
		if(StringUtil.isEmpty(typeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "类型标识符为空");
			return "res/hbres/student/findActivityList";
		}
		if(!"isNew".equals(typeId)&&!"isEval".equals(typeId))
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "类型标识符不正确,isNew，isEval");
			return "res/hbres/student/findActivityList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/hbres/student/findActivityList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/hbres/student/findActivityList";
		}
		ResDoctor doctor=hbresAppBiz.readResDoctor(userFlow);
		if(doctor==null)
		{
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "医师信息不存在");
			return "res/hbres/student/findActivityList";
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
		return "res/hbres/student/findActivityList";
	}

	@RequestMapping(value = "/joinActivity")
	public String joinActivity(Model model, String activityFlow, String userFlow) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/success";
		}
		if (StringUtil.isEmpty(activityFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/hbres/student/success";
		}
		TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
		if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
			return "res/hbres/student/success";
		}

		if (DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime()) >= 0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动已开始，无法报名！");
			return "res/hbres/student/success";
		}
		TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
		if (result != null&&GlobalConstant.FLAG_Y.equals(result.getIsRegiest())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已报名，请勿重复报名！");
			return "res/hbres/student/success";
		}
		List<TeachingActivityInfo> infos=activityBiz.checkJoinList(activityFlow,userFlow);
		if(infos!=null&&infos.size()>0)
		{
			TeachingActivityInfo activityInfo=infos.get(0);
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "已报名同一时间【"+activityInfo.getActivityName()+"】，不能报名！");
			return "res/hbres/student/success";
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
			return "res/hbres/student/success";
		}
		return "res/hbres/student/success";

	}
	@RequestMapping(value="/cannelRegiest")
	public String cannelRegiest(Model model,String activityFlow,String resultFlow, String userFlow){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/success";
		}
		if (StringUtil.isEmpty(activityFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/hbres/student/success";
		}
		TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
		if (info == null || !GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
			return "res/hbres/student/success";
		}
		TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
		if (result == null) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你未报名，无法取消报名信息！");
			return "res/hbres/student/success";
		}
		if (!GlobalConstant.FLAG_Y.equals(result.getIsRegiest()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已取消报名！");
			return "res/hbres/student/success";
		}
		if(GlobalConstant.FLAG_Y.equals(result.getIsScan()))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你已扫码签到，无法取消报名信息！");
			return "res/hbres/student/success";
		}
		result.setIsRegiest(GlobalConstant.FLAG_N);
		int c = activityBiz.saveResult(result, userFlow);
		if (c == 0) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "取消报名失败！");
			return "res/hbres/student/success";
		}
		return "res/hbres/student/success";

	}
	@RequestMapping(value = "/showDocEval")
	public String showDocEval(Model model, String resultFlow, String userFlow) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/showDocEval";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评价标识符为空");
			return "res/hbres/student/showDocEval";
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
			return "res/hbres/student/showDocEval";
		}
		return "res/hbres/student/showDocEval";

	}

	@RequestMapping(value = {"/saveEvalInfo"}, method = {RequestMethod.POST,RequestMethod.GET})
	public	 String saveEvalInfo(String evals, String resultFlow,String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {

		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/student/success";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择需要评价的活动");
			return "res/hbres/student/success";
		}
		TeachingActivityResult result=activityBiz.readResult(resultFlow);
		if(result==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "你未扫码参加该活动，无法评价");
			return "res/hbres/student/success";
		}
		if(result!=null&&result.getEvalScore()!=null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "该活动已评价，请刷新页面！");
			return "res/hbres/student/success";
		}
		if(StringUtil.isBlank(evals))
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择评分！");
			return "res/hbres/student/success";
		}
		List<TeachingActivityEval> activityEvals=null;
		try {
			activityEvals=JSON.parseArray(evals, TeachingActivityEval.class);
		}catch (Exception e){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评分数据格式不正确！");
			return "res/hbres/student/success";
		}
		if(activityEvals==null||activityEvals.size()<=0)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "请选择评分！");
			return "res/hbres/student/success";
		}
		int count=activityBiz.saveEvalInfo(activityEvals, resultFlow,userFlow);
		if(count==0)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "评价失败！");
			return "res/hbres/student/success";
		}
		return "res/hbres/student/success";
	}

	/**
	 * 查看错题
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/viewError"},method={RequestMethod.GET})
	public String viewError(String processFlow,Model model){
		String result = "res/hbres/student/viewError";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return result;
		}
		List<ExamResults> results=examResultsBiz.getByProcessFlow(processFlow);
		model.addAttribute("results",results);
		return "res/hbres/student/viewError";
	}
	@RequestMapping(value={"/viewErrorDetail"},method={RequestMethod.GET})
	public String viewErrorDetail(String processFlow,String resultsId , Model model){
		String result = "res/hbres/student/viewErrorDetail";
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
		String testUrl = hbresAppBiz.getCfgCode("res_after_wrong_exam_url_cfg");
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

		String result="/res/hbres/success";
		String baseDir = hbresAppBiz.getCfgCode("upload_base_dir");
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

		ResLectureInfo lectureInfo = hbresAppBiz.read(lectureFlow);
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

	/**
	 * 学员角色年度理论考试
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/theoreticalExam")
	public String theoreticalExam(String userFlow ,Model model) {
		String result = "res/hbres/student/examList";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功");
		ResDoctor resDoctor = hbresAppBiz.readResDoctor(userFlow);
		if(resDoctor==null)
		{
			model.addAttribute("resultId","6011");
			model.addAttribute("resultType", "用户不存在！");
			return result;
		}
		SchExamArrangement schExamArrangement = new SchExamArrangement();
		schExamArrangement.setOrgFlow(resDoctor.getOrgFlow());
		schExamArrangement.setSessionNumber(resDoctor.getSessionNumber());
		schExamArrangement.setTrainingTypeId(resDoctor.getTrainingTypeId());
		schExamArrangement.setTrainingSpeId(resDoctor.getTrainingSpeId());
		List<SchExamArrangement> examArrangements = examCfgBiz.searchList(schExamArrangement);
		//查询条件
		Map<String,Object> param = new HashMap<>();
		List<String> userFlows = new ArrayList<>();
		userFlows.add(userFlow);
		param.put("orgFlow",resDoctor.getOrgFlow());
		param.put("userFlows",userFlows);
		List<SchExamDoctorArrangement> doctorArrangements = scoreQueryBiz.getDoctorArrangements(param);
		if(doctorArrangements != null && doctorArrangements.size() > 0){
			Map<String,SchExamDoctorArrangement> doctorArrangementMap = new HashMap<>();
			for(SchExamDoctorArrangement da : doctorArrangements)
			{
				doctorArrangementMap.put(da.getArrangeFlow(),da);
			}
			model.addAttribute("daMap",doctorArrangementMap);
		}
		Map<String,Map<String,String>> examLogMaps = null;
		if(examArrangements != null && examArrangements.size() > 0){
			examLogMaps = new HashMap<>();
			for(SchExamArrangement tempExam : examArrangements){
				Map<String,String> paramMap = new HashMap<>();
				paramMap.put("arrangeFlow",tempExam.getArrangeFlow());
				paramMap.put("doctorFlow",resDoctor.getDoctorFlow());
				List<Map<String,String>> examArrangementMaps = examCfgBiz.searchExamLogByItems(paramMap);
				if(examArrangementMaps != null && examArrangementMaps.size() > 0){
					for(Map<String,String> tempMap : examArrangementMaps){
						Map<String,String> paramTempMap = new HashMap<>();
						paramTempMap.put("countNum",tempMap.get("COUNTNUM"));
						paramTempMap.put("maxScore",tempMap.get("MAXSCORE"));
						if(GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) &&
								Integer.parseInt(tempExam.getExamNumber()) > Integer.parseInt(tempMap.get("COUNTNUM"))){
							paramTempMap.put("canExam",GlobalConstant.FLAG_Y);
						}
						if(StringUtil.isNotBlank(tempExam.getExamStartTime())&&
								StringUtil.isNotBlank(tempExam.getExamEndTime())) {
							Date today = DateUtil.parseDate(DateUtil.getCurrDateTime2(), DateUtil.defDtPtn02);
							Date examStartTime = DateUtil.parseDate(tempExam.getExamStartTime(), "yyyy-MM-dd HH:mm");
							Date examEndTime = DateUtil.parseDate(tempExam.getExamEndTime(), "yyyy-MM-dd HH:mm");
							if (examStartTime.after(today) || today.after(examEndTime)) {
								paramTempMap.put("canExam", GlobalConstant.FLAG_N);
								paramTempMap.put("isEnd", GlobalConstant.FLAG_Y);
							}
						}
						examLogMaps.put(tempMap.get("ARRANGEFLOW"),paramTempMap);
					}
				}else {
					if(GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen())){
						Map<String,String> paramTempMap = new HashMap<>();
						paramTempMap.put("canExam",GlobalConstant.FLAG_Y);
						if(StringUtil.isNotBlank(tempExam.getExamStartTime())&&
								StringUtil.isNotBlank(tempExam.getExamEndTime())) {
							Date today = DateUtil.parseDate(DateUtil.getCurrDateTime2(), DateUtil.defDtPtn02);
							Date examStartTime = DateUtil.parseDate(tempExam.getExamStartTime(), "yyyy-MM-dd HH:mm");
							Date examEndTime = DateUtil.parseDate(tempExam.getExamEndTime(), "yyyy-MM-dd HH:mm");
							if (examStartTime.after(today) || today.after(examEndTime)) {
								paramTempMap.put("canExam", GlobalConstant.FLAG_N);
								paramTempMap.put("isEnd", GlobalConstant.FLAG_Y);
							}
						}
						examLogMaps.put(tempExam.getArrangeFlow(),paramTempMap);
					}
				}
			}
			model.addAttribute("examLogMaps",examLogMaps);
		}
		model.addAttribute("examArrangements",examArrangements);
		return result;
	}

	@Autowired
	private ISchExamScoreQueryBiz scoreQueryBiz;
	@Autowired
	private ISchExamCfgBiz examCfgBiz;
	/**
	 * 参加年度考试
	 * @param arrangeFlow
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/toYearTest"})
	public String toYearTest(String arrangeFlow, String userFlow,Model model) throws UnsupportedEncodingException {
		String result = "res/hbres/student/toYearTest";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(arrangeFlow)){
			model.addAttribute("resultId", "6021");
			model.addAttribute("resultType", "年度考核信息标识符为空");
			return result;
		}
		//考试地址
		String testUrl = hbresAppBiz.getCfgCode("res_mobile_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("resultId", "6022");
			model.addAttribute("resultType", "请联系管理员维护年度考试地址");
			return result;
		}
		SchExamArrangement ment=examCfgBiz.readByFlow(arrangeFlow);
		if(ment==null)
		{
			model.addAttribute("resultId", "6023");
			model.addAttribute("resultType", "考核信息不存在");
			return result;
		}
		//校验考核是否开放
		if(!GlobalConstant.FLAG_Y.equals(ment.getIsOpen()))
		{
			model.addAttribute("resultId", "6024");
			model.addAttribute("resultType", "该考核暂未开放，无法参加考试");
			return result;
		}
		//当前用户

		//校验学员已经考核了几次
		int examCount=examCfgBiz.findDocExamCount(userFlow,arrangeFlow);
		if(examCount>=Integer.valueOf(ment.getExamNumber()))
		{
			model.addAttribute("resultId", "6025");
			model.addAttribute("resultType", "考核次数已达到最大考核次数，无法再次考试");
			return result;
		}
		//用户的医师信息
		ResDoctor doctor = hbresAppBiz.readResDoctor(userFlow);
		if(doctor==null)
		{
			model.addAttribute("resultId", "6026");
			model.addAttribute("resultType", "无医师信息，无法参加考试");
			return result;
		}
		//获取考试参数
		//考试结果记录流水号
		String recordFlow= PkUtil.getUUID();
		//考试范围标准科室名称
		String standarDeptNames="";
		Map<String,Object> map=null;
		//随机试卷
		if("1".equals(ment.getExampaperType()))
		{
			map=examCfgBiz.getSuiJiConfig(ment,userFlow);
		}else if("2".equals(ment.getExampaperType())) {
			map=examCfgBiz.getGuDingConfig(ment);
		}else{
			model.addAttribute("resultId", "6027");
			model.addAttribute("resultType", "考核信息无考核类型，无法参加考试");
			return result;
		}
		if(map==null||StringUtil.isBlank((String) map.get("standardDeptNames")))
		{
			model.addAttribute("resultId", "6028");
			model.addAttribute("resultType", "考核信息无考核范围，无法参加考试");
			return result;
		}
		standarDeptNames=(String) map.get("standardDeptNames");
		//试类型 (年度考试)（传递）1：出科考试；2：年度考试
		String ExamTestType = "2";
		//时间戳
		String Date = "0";
		//试卷类型
		String paperType=ment.getExampaperType();
		String StartTime="";
		String EndTime="";
		String SoluTime="";
		SysUser user = hbresAppBiz.readSysUser(userFlow);
		//考试次数
		String examNumber=ment.getExamNumber();
		model.addAttribute("Action","YearExam");
		model.addAttribute("CardID",user.getUserCode());
		model.addAttribute("ExamTestType",ExamTestType);
		model.addAttribute("ExamSoluClass",paperType);
		model.addAttribute("TestNumber",examNumber);
		model.addAttribute("ExamKnowledge",standarDeptNames);
		model.addAttribute("SpecName",doctor.getTrainingSpeName());
		model.addAttribute("ProcessFlow",recordFlow);
		model.addAttribute("Date",Date);
		model.addAttribute("StartTime",ment.getExamStartTime());
		model.addAttribute("EndTime",ment.getExamEndTime());
		model.addAttribute("SoluTime",ment.getExamDuration());
		if(StringUtil.isNotBlank(ment.getExamStartTime()))
		{
			StartTime=ment.getExamStartTime();
		}
		if(StringUtil.isNotBlank(ment.getExamEndTime()))
		{
			EndTime=ment.getExamEndTime();
		}
		if(StringUtil.isNotBlank(ment.getExamDuration()))
		{
			SoluTime=ment.getExamDuration();
		}
//		&StartTime=2018-05-02 00:00:00.000&EndTime=2019-04-26 23:59:59.000&SoluTime=60
		//创建考核结果
		SchExamDoctorArrangement schExamDoctorArrangement=new SchExamDoctorArrangement();
		schExamDoctorArrangement.setRecordFlow(recordFlow);
		schExamDoctorArrangement.setArrangeFlow(arrangeFlow);
		schExamDoctorArrangement.setAssessmentYear(ment.getAssessmentYear());
		schExamDoctorArrangement.setDoctorFlow(user.getUserFlow());
		schExamDoctorArrangement.setDoctorName(user.getUserName());

		schExamDoctorArrangement.setOrgName(doctor.getOrgName());
		schExamDoctorArrangement.setOrgFlow(doctor.getOrgFlow());
		schExamDoctorArrangement.setSessionNumber(doctor.getSessionNumber());

		int saveResult = examCfgBiz.save(schExamDoctorArrangement);
		if(GlobalConstant.ZERO_LINE>=saveResult){
			model.addAttribute("resultId", "6029");
			model.addAttribute("resultType", "考核结果信息创建出错");
			return result;
		}

		testUrl=testUrl+"?Action=YearExam&CardID="+URLEncoder.encode(user.getUserCode(), "utf-8")
				+"&ExamTestType="+ExamTestType
				+"&ExamSoluClass="+paperType
				+"&TestNumber="+examNumber
				+"&ExamKnowledge="+standarDeptNames
				+"&SpecName="+doctor.getTrainingSpeName()
				+"&ProcessFlow="+recordFlow
				+"&Date="+Date
				+"&StartTime="+StartTime
				+"&EndTime="+EndTime
				+"&SoluTime="+SoluTime;
		model.addAttribute("testUrl",testUrl);
		//System.out.println("testUrl="+testUrl);
		return result;
	}

	/**
	 * 科教通知
	 *
	 * @return
	 */
	@RequestMapping(value = {"/getSysNotice"}, method = {RequestMethod.POST})
	public String getSysNotice(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/sysNotices/getSysNotice";
		}
		//验证用户是否存在
		SysUser userinfo = hbresAppBiz.readSysUser(userFlow);
		ResDoctor doctorinfo = hbresAppBiz.readResDoctor(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/hbres/sysNotices/getSysNotice";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/hbres/sysNotices/getSysNotice";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/hbres/sysNotices/getSysNotice";
		}
		String orgFlow = doctorinfo.getOrgFlow();
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgBeforeDate(orgFlow,null, GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow,doctorinfo.getSessionNumber());
		model.addAttribute("infoList",infos);

		Map<String,Object> isReadMap=new HashMap<>();
		if(infos!=null&&infos.size()>0)
		{
			for(Map<String,String> info:infos)
			{
				ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(info.get("infoFlow"),userFlow);
				isReadMap.put(info.get("infoFlow"),resReadInfo);
			}
		}
		model.addAttribute("isReadMap", isReadMap);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/hbres/sysNotices/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/hbres/sysNotices/getSysNotice";
	}

	@RequestMapping(value={"/sysNoticeDetail"})
	public String sysNoticeDetail(String userFlow,String infoFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/hbres/sysNotices/sysNoticeDetail";
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态标识符为空");
			return "res/hbres/sysNotices/sysNoticeDetail";
		}
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态不存在，请刷新列表页面");
			return "res/hbres/sysNotices/sysNoticeDetail";
		}
		ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(infoFlow,userFlow);
		if(resReadInfo==null)
		{
			resReadInfo=new ResReadInfo();
			resReadInfo.setInfoFlow(infoFlow);
			resReadInfo.setUserFlow(userFlow);
			inxInfoBiz.saveReadInfo(userFlow,resReadInfo);
		}
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/hbres/sysNotices/showSysNotice.jsp?recordFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/hbres/sysNotices/showSysNotice2.jsp?recordFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/hbres/sysNotices/sysNoticeDetail";
	}
}

