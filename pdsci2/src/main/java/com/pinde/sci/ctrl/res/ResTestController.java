package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchAndStandardDeptCfgBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchExamCfgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResDoctorSchProcessMapper;
import com.pinde.sci.dao.base.ResSchProcessExpressMapper;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/test")
public class ResTestController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResTestController.class);
	
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ResPaperBiz paperBiz;
	@Autowired
	private IResScoreBiz scoreBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private ISchAndStandardDeptCfgBiz deptCfgBiz;
	@Autowired
	private ISchExamCfgBiz examCfgBiz;
	@Resource
	private ResSchProcessExpressMapper expressMapper;
	@Resource
	private ResDoctorSchProcessMapper processMapper;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Resource
	private IUserBiz userBiz;
	@Autowired
	private IExamResultsBiz examResultsBiz;
	
	@RequestMapping("/toTest")
	public String toTest(String userCode, String processFlow, Model model, HttpServletRequest request){
		String errorPage = "res/edu/student/errorView";

		if(!StringUtil.isNotBlank(processFlow)){
			model.addAttribute("errorMeg","当前轮转科室标识为空！");
			return errorPage;
		}
		
		//考试地址
		String testUrl = InitConfig.getSysCfg("res_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("errorMeg","请联系管理员维护考试地址！");
			return errorPage;
		}
		
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		String userFlow = user.getUserFlow();
		userCode = StringUtil.defaultIfEmpty(userCode,user.getUserCode());

		//获取当前轮转信息
		ResDoctorSchProcess process = processBiz.read(processFlow);
		if(process==null){
			model.addAttribute("errorMeg","当前轮转信息获取失败！");
			return errorPage;
		}

		//轮转计划信息
		String resultFlow = process.getSchResultFlow();
		SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
		if(result==null){
			model.addAttribute("errorMeg","轮转计划信息获取失败！");
			return errorPage;
		}
		SchAndStandardDeptCfg cfg =deptCfgBiz.readBySchDeptFlow(process.getSchDeptFlow());
		if(cfg==null)
		{
			model.addAttribute("errorMeg","请基地管理员维护出科考核标准科室！");
			return errorPage;
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
				model.addAttribute("errorMeg","当前考试次数已经达到"+powerCfg.getCfgValue()+",无法再次考试!");
				return errorPage;
			}
		}else{
//			JsresPowerCfg jsresPowerCfg= jsResPowerCfgBiz.read("out_test_limit_" + process.getOrgFlow());
//			if(jsresPowerCfg!=null&&StringUtil.isNotBlank(jsresPowerCfg.getCfgValue())) {
//				TestNum = jsresPowerCfg.getCfgValue();
//				int c = 0;
//				List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
//				if (examResultsList != null)
//					c = examResultsList.size();
//				if (c >= Integer.valueOf(jsresPowerCfg.getCfgValue())) {
//					model.addAttribute("errorMeg", "当前考试次数已经达到" + jsresPowerCfg.getCfgValue() + ",无法再次考试!");
//					return errorPage;
//				}
//			}
			//禅道201 更改
			JsresPowerCfg jsresPowerCfg= jsResPowerCfgBiz.read("out_test_check_" + process.getOrgFlow());
			if(jsresPowerCfg!=null && StringUtil.isNotBlank(jsresPowerCfg.getCfgValue()) &&
                    com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(jsresPowerCfg.getCfgValue())) {
				//查询科室是否单独配置出科考核参数
				JsresDeptConfig deptConfig = jsResPowerCfgBiz.searchDeptCfg(process.getOrgFlow(),process.getSchDeptFlow());
				if(null != deptConfig){
					if(StringUtil.isNotBlank(deptConfig.getTestNum())) {
						TestNum = deptConfig.getTestNum();
						int c = 0;
						List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
						if (examResultsList != null)
							c = examResultsList.size();
						if (c >= Integer.valueOf(TestNum)) {
							model.addAttribute("errorMeg", "当前考试次数已经达到" + deptConfig.getTestNum() + ",无法再次考试!");
							return errorPage;
						}
					}
					//是否允许学员轮转时间结束后考试
                    if (StringUtil.isNotBlank(deptConfig.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(deptConfig.getIsTestOut())) {
						//不允许  则判断时间是否在轮转时间内
						String currDate = DateUtil.getCurrDate();
						String endDate = process.getSchEndDate();
						try {
							int num = this.compareDate(endDate, currDate);
							if(num > 0){
								model.addAttribute("errorMeg", "轮转时间结束,无法参加考试!");
								return errorPage;
							}
						}catch (Exception e){
                            logger.error("", e);
						}
                    } else if (StringUtil.isNotBlank(deptConfig.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getIsTestOut())) {
						String currDate = DateUtil.getCurrDate();
						String endDate = process.getSchEndDate();
						if(StringUtil.isNotBlank(deptConfig.getTestOutTime())) {
							String testOutDate = deptConfig.getTestOutTime();
							String newDate = DateUtil.addDate(endDate, Integer.parseInt(testOutDate));
							try {
								int num = this.compareDate(newDate, currDate);
								if (num > 0) {
									model.addAttribute("errorMeg", "超出轮转时间结束后" + testOutDate + "天,无法参加考试!");
									return errorPage;
								}
							} catch (Exception e) {
                                logger.error("", e);
							}
						}
					}
				}else {
					//未单独配置  取全局配置
					JsresDeptConfig config = jsResPowerCfgBiz.searchBaseDeptConfig(process.getOrgFlow());
					if(null != config){
						if(StringUtil.isNotBlank(config.getTestNum())) {
							TestNum = config.getTestNum();
							int c = 0;
							List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
							if (examResultsList != null)
								c = examResultsList.size();
							if (c >= Integer.valueOf(TestNum)) {
								model.addAttribute("errorMeg", "当前考试次数已经达到" + config.getTestNum() + ",无法再次考试!");
								return errorPage;
							}
						}
						//是否允许学员轮转时间结束后考试
                        if (StringUtil.isNotBlank(config.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(config.getIsTestOut())) {
							//不允许  则判断时间是否在轮转时间内
							String currDate = DateUtil.getCurrDate();
							String endDate = process.getSchEndDate();
							try {
								int num = this.compareDate(endDate, currDate);
								if(num > 0){
									model.addAttribute("errorMeg", "轮转时间结束,无法参加考试!");
									return errorPage;
								}
							}catch (Exception e){
                                logger.error("", e);
							}
                        } else if (StringUtil.isNotBlank(config.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(config.getIsTestOut())) {
							String currDate = DateUtil.getCurrDate();
							String endDate = process.getSchEndDate();
							if(StringUtil.isNotBlank(config.getTestOutTime())) {
								String testOutDate = config.getTestOutTime();
								String newDate = DateUtil.addDate(endDate, Integer.parseInt(testOutDate));
								try {
									int num = this.compareDate(newDate, currDate);
									if (num > 0) {
										model.addAttribute("errorMeg", "超出轮转时间结束后" + testOutDate + "天,无法参加考试!");
										return errorPage;
									}
								} catch (Exception e) {
                                    logger.error("", e);
								}
							}
						}
					}
				}
			}
		}
		//试卷id
		String ExamSoluID = "0";
		//试卷类型
		String CardType = "0";
		//时间戳
		String Date = "0";
		
		//用户的医师信息
		ResDoctor doctor = doctorBiz.readDoctor(userFlow);
		if(doctor!=null){
			//标准科室
			String standardDeptId = cfg.getStandardDeptId();
			//专业
			String speId = doctor.getTrainingSpeId();
			ResPaper paper = paperBiz.getPaperByOrgStandardDeptId(process.getOrgName(), standardDeptId);
//			ResPaper paper = paperBiz.getPaperByRotationAndDept(speId, standardDeptId);
			if(paper==null){
				 paper = paperBiz.getPaperByStandardDeptId(standardDeptId);
			}
			
			if(paper!=null){
				ExamSoluID = paper.getPaperFlow();
			}
			
			if("0".equals(ExamSoluID)){
				model.addAttribute("errorMeg","该科室暂无试卷信息！");
				return errorPage;
			}
			
			TestPaper standardPaper = paperBiz.readTestPaper(ExamSoluID);
			if(standardPaper!=null){
				CardType = standardPaper.getPaperTypeId();
			}
		}
		
		model.addAttribute("ExamSoluID",ExamSoluID);
//		model.addAttribute("CardID",userCode);
//		model.addAttribute("CardType",CardType);
		model.addAttribute("ProcessFlow",processFlow);
//		model.addAttribute("Date",Date);
		model.addAttribute("TestNum",TestNum);
		model.addAttribute("sessionNumber", process.getSchStartDate().substring(0,4));
		model.addAttribute("token",request.getSession().getId());
		model.addAttribute("userFlow", userFlow);
		logger.debug("=====================考试对接参数开始========================");
		logger.debug("=====================testUrl============："+testUrl);
		logger.debug("=====================ExamSoluID============："+ExamSoluID);
		logger.debug("=====================userCode============："+userCode);
		logger.debug("=====================CardType============："+CardType);
		logger.debug("=====================ProcessFlow============："+processFlow);
		logger.debug("=====================Date============："+Date);
		logger.debug("=====================TestNum============："+TestNum);
		logger.debug("=====================考试对接参数结束========================");
		//创建分数数据
		ResScore score = scoreBiz.getScoreByProcess(processFlow);
		if(score==null){
			score = new ResScore();
			score.setDoctorFlow(userFlow);
            score.setScoreTypeId(com.pinde.core.common.enums.ResScoreTypeEnum.DeptScore.getId());
            score.setScoreTypeName(com.pinde.core.common.enums.ResScoreTypeEnum.DeptScore.getName());
			score.setResultFlow(resultFlow);
			score.setProcessFlow(processFlow);
			score.setSchDeptFlow(result.getSchDeptFlow());
			score.setSchDeptName(result.getSchDeptName());
		}
			
		score.setPaperFlow(ExamSoluID);
		
		int saveResult = scoreBiz.save(score);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
			model.addAttribute("errorMeg","分数信息创建出错！");
			return errorPage;
		}
		
		return "redirect:"+testUrl + "?ExamSoluID=" + ExamSoluID + "&ProcessFlow=" + processFlow + "&TestNum=" + TestNum + "&sessionNumber=" + process.getSchStartDate().substring(0,4) + "&token=" + request.getSession().getId() + "&userFlow=" + userFlow;
	}

	private int compareDate(String Date, String sysDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = sdf.parse(Date);
		Date date2 = sdf.parse(sysDate);
		//获取毫秒
		long time = date1.getTime();
		long sysTime = date2.getTime();
		//小于系统时间
		if (time < sysTime) {
			return 1;
		} else {
			return -1;
		}
	}

	@RequestMapping("/toGraduationTest")
	public String toGraduationTest(String userCode,Model model){
		String errorPage = "res/edu/student/errorView";

		//考试地址
		String testUrl = InitConfig.getSysCfg("res_after_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("errorMeg","请联系管理员维护考试地址！");
			return errorPage;
		}

		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		String userFlow = user.getUserFlow();
		userCode = StringUtil.defaultIfEmpty(userCode,user.getUserCode());
		//用户的医师信息
		ResDoctor doctor = doctorBiz.readDoctor(userFlow);
		if(doctor==null)
		{
			model.addAttribute("errorMeg","无医师信息，无法参加考试！");
			return errorPage;
		}
		if(StringUtil.isBlank(doctor.getTrainingSpeId()))
		{
			model.addAttribute("errorMeg","医师无培训专业信息，无法参加考试！");
			return errorPage;
		}
		//获取考试参数
		//考试结果记录流水号
		String recordFlow= PkUtil.getUUID();

		//试卷id
		String ExamSoluID = "0";
		//试卷类型
		String CardType = "0";
		//时间戳
		String Date = "0";

		if(doctor!=null){
			//专业
			String speId = doctor.getTrainingSpeId();
			ResPaper paper =  paperBiz.getPaperBySpeId(speId);

			if(paper!=null){
				ExamSoluID = paper.getPaperFlow();
			}
			if("0".equals(ExamSoluID)){
				model.addAttribute("errorMeg","该专业下暂无试卷信息！");
				return errorPage;
			}

			TestPaper standardPaper = paperBiz.readTestPaper(ExamSoluID);
			if(standardPaper!=null){
				CardType = standardPaper.getPaperTypeId();
			}
		}

		model.addAttribute("ExamSoluID",ExamSoluID);
		model.addAttribute("CardID",userCode);
		model.addAttribute("CardType",CardType);
		model.addAttribute("ProcessFlow",recordFlow);
		model.addAttribute("Date",Date);
		model.addAttribute("TestNum","");
		logger.debug("=====================考试对接参数开始========================");
		logger.debug("=====================testUrl============："+testUrl);
		logger.debug("=====================ExamSoluID============："+ExamSoluID);
		logger.debug("=====================userCode============："+userCode);
		logger.debug("=====================CardType============："+CardType);
		logger.debug("=====================ProcessFlow============："+recordFlow);
		logger.debug("=====================Date============："+Date);
		logger.debug("=====================TestNum============：");
		logger.debug("=====================考试对接参数结束========================");
		//创建考核结果
		ResDoctorGraduationExam result=new ResDoctorGraduationExam();
		result.setExamFlow(recordFlow);
		result.setDoctorFlow(userFlow);
		result.setExamYear(DateUtil.getYear());
		int saveResult = examCfgBiz.saveGraduationExam(result);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
			model.addAttribute("errorMeg","考核结果信息创建出错！");
			return errorPage;
		}
		return "redirect:"+testUrl;
	}
	//年度
	@RequestMapping("/toYearTest")
	public String toYearTest(String userCode,String arrangeFlow,Model model){
		String errorPage = "res/edu/student/errorView";

		//考试地址
		String testUrl = InitConfig.getSysCfg("res_year_exam_url_cfg");
		if(!StringUtil.isNotBlank(testUrl)){
			model.addAttribute("errorMeg","请联系管理员维护年度考试地址！");
			return errorPage;
		}

		if(!StringUtil.isNotBlank(arrangeFlow)){
			model.addAttribute("errorMeg","考核信息标识为空！");
			return errorPage;
		}
		SchExamArrangement ment=examCfgBiz.readByFlow(arrangeFlow);
		if(ment==null)
		{
			model.addAttribute("errorMeg","考核信息不存在！");
			return errorPage;
		}
		//校验考核是否开放
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ment.getIsOpen()))
		{
			model.addAttribute("errorMeg","该考核暂未开放，无法参加考试！");
			return errorPage;
		}
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		String userFlow = user.getUserFlow();
		user=userBiz.readSysUser(userFlow);
		userCode = StringUtil.defaultIfEmpty(userCode,user.getUserCode());
		//校验学员已经考核了几次
		int examCount=examCfgBiz.findDocExamCount(userFlow,arrangeFlow);
		if(examCount>=Integer.valueOf(ment.getExamNumber()))
		{
			model.addAttribute("errorMeg","考核次数已达到最大考核次数，无法再次考试！");
			return errorPage;
		}
		//用户的医师信息
		ResDoctor doctor = doctorBiz.readDoctor(userFlow);
		if(doctor==null)
		{
			model.addAttribute("errorMeg","无医师信息，无法参加考试！");
			return errorPage;
		}
		//获取考试参数
		//考试结果记录流水号
		String recordFlow= PkUtil.getUUID();
		//考试范围标准科室名称
		String standarDeptNames="";

		String UserTurn="";
		Map<String,Object> map=null;
		List<SchArrangeResult> results=null;
		//随机试卷
		if("1".equals(ment.getExampaperType()))
		{
			results=examCfgBiz.getSuiJiConfigs(ment,userFlow);
			if(results==null)
			{
				model.addAttribute("errorMeg","考核信息无考核范围，无法参加考试！");
				return errorPage;
			}else {
				map=new HashMap<>();
				for(SchArrangeResult r:results)
				{
					List<String> times= TimeUtil.getMonthsByTwoMonth(DateUtil.monthOfDate(r.getSchStartDate()),DateUtil.monthOfDate(r.getSchEndDate()));
					if(times!=null)
					{
						for(String time:times)
						{
							List<String> temp= (List<String>) map.get(time);
							if(temp==null)
								temp=new ArrayList<>();
							if(!temp.contains(r.getStandardDeptName()))
							{
								temp.add(r.getStandardDeptName());
							}
							map.put(time,temp);
						}
					}
				}
			}
			if(map!=null&&map.size()<=0)
			{
				model.addAttribute("errorMeg","考核信息无考核范围，无法参加考试！");
				return errorPage;
			}
			List<Map<String,String>> list=new ArrayList<>();
			for(String key:map.keySet())
			{
				List<String> temp= (List<String>) map.get(key);
				if(temp!=null&&temp.size()>0)
				{
					Map<String,String> b=new HashMap<>();
					b.put("TurnTime",key);
					String v="";
					for(int j=0;j<temp.size();j++)
					{
						if(j==0)
						{
							v+=temp.get(j);
						}else{
							v+=","+temp.get(j);
						}
					}
					b.put("TurnSectionName",v);
					list.add(b);
				}

			}
			UserTurn= JSON.toJSONString(list);
		}else if("2".equals(ment.getExampaperType())) {
			map=examCfgBiz.getGuDingConfig(ment);
			if(map==null||StringUtil.isBlank((String) map.get("standardDeptNames")))
			{
				model.addAttribute("errorMeg","考核信息无考核范围，无法参加考试！");
				return errorPage;
			}
			standarDeptNames=(String) map.get("standardDeptNames");
		}else{
			model.addAttribute("errorMeg","考核信息无考核类型，无法参加考试！");
			return errorPage;
		}



		//试类型 (年度考试)（传递）1：出科考试；2：年度考试
		String ExamTestType = "2";
		//时间戳
		String Date = "0";
		//试卷类型
		String paperType=ment.getExampaperType();
		String StartTime="";
		String EndTime="";
		String SoluTime="";

		//考试次数
		String examNumber=ment.getExamNumber();
		model.addAttribute("Action","YearExam");
		model.addAttribute("CardID",userCode);
		model.addAttribute("ExamTestType",ExamTestType);
		model.addAttribute("ExamSoluClass",paperType);
		model.addAttribute("TestNumber",examNumber);
		model.addAttribute("UserTurn",UserTurn);
		model.addAttribute("ExamKnowledge",standarDeptNames);
		model.addAttribute("SpecName",doctor.getTrainingSpeName());
		model.addAttribute("ProcessFlow",recordFlow);
		model.addAttribute("Date",Date);

		model.addAttribute("StartTime",ment.getExamStartTime()==null?"":ment.getExamStartTime());
		model.addAttribute("EndTime",ment.getExamEndTime()==null?"":ment.getExamEndTime());
		model.addAttribute("SoluTime",ment.getExamDuration()==null?"":ment.getExamDuration());
//		&StartTime=2018-05-02 00:00:00.000&EndTime=2019-04-26 23:59:59.000&SoluTime=60
		//创建考核结果
		SchExamDoctorArrangement result=new SchExamDoctorArrangement();
		result.setRecordFlow(recordFlow);
		result.setArrangeFlow(arrangeFlow);
		result.setAssessmentYear(ment.getAssessmentYear());
		result.setDoctorFlow(user.getUserFlow());
		result.setDoctorName(user.getUserName());

		result.setOrgName(ment.getOrgName());
		result.setOrgFlow(ment.getOrgFlow());
		result.setSessionNumber(doctor.getSessionNumber());

		int saveResult = examCfgBiz.save(result);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
			model.addAttribute("errorMeg","考核结果信息创建出错！");
			return errorPage;
		}

		logger.debug("Action===YearExam");
		logger.debug("CardID==="+userCode);
		logger.debug("ExamTestType==="+ExamTestType);
		logger.debug("ExamSoluClass==="+paperType);
		logger.debug("TestNumber==="+examNumber);
		logger.debug("ExamKnowledge==="+standarDeptNames);
		logger.debug("UserTurn==="+UserTurn);
		logger.debug("SpecName==="+doctor.getTrainingSpeName());
		logger.debug("ProcessFlow==="+recordFlow);
		logger.debug("Date==="+Date);
		logger.debug(testUrl);
		return "redirect:"+testUrl;
	}

//考试系统出科成绩，学员提交后，直接提交到过程中
	@RequestMapping("/saveKKScore")
	@ResponseBody
	public Object saveKKScore(String theoryScore,String processFlow,String typeId,Model model){
		Map<String,String> json=new HashMap<>();
		json.put("code","200");
		json.put("msg","同步成功！");
		if(StringUtil.isBlank(processFlow))
		{
			json.put("code","3201001");
			json.put("msg","轮转标识符为空！");
			return json;
		}
		if(StringUtil.isBlank(theoryScore))
		{
			json.put("code","3201002");
			json.put("msg","出科成绩为空！");
			return json;
		}
		if(StringUtil.isBlank(typeId))
		{
			json.put("code","3201002");
			json.put("msg","类型为空！");
			return json;
		}
		if(!"ckk".equals(typeId)&&!"ndk".equals(typeId)&&!"jyk".equals(typeId))
		{
			json.put("code","3201002");
			json.put("msg","类型为空！");
			return json;
		}
		if("ckk".equals(typeId)) {
			ResScore score = scoreBiz.getScoreByProcess(processFlow);
			if (score == null) {
				json.put("code", "3201003");
				json.put("msg", "成绩记录为空！");
				return json;
			}
			//未审核出科考核表时，同步成绩至ResDoctorSchProcess
			ResSchProcessExpressExample expressExample = new ResSchProcessExpressExample();
            expressExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andProcessFlowEqualTo(processFlow)
					.andRecTypeIdEqualTo("AfterEvaluation");
			List<ResSchProcessExpress> expresses = expressMapper.selectByExample(expressExample);
			if (expresses != null && expresses.size() > 0) {
				return json;
			}

			BigDecimal totalScore = null;
			try {
				totalScore = new BigDecimal(theoryScore);
			} catch (Exception e) {
				json.put("code", "3201004");
				json.put("msg", "成绩格式错误！");
				return json;
			}
			if (totalScore == null) {
				json.put("code", "3201004");
				json.put("msg", "成绩格式错误！");
				return json;
			}
			if (score.getTheoryScore() == null || totalScore.compareTo(score.getTheoryScore()) > 0) {
				score.setTheoryScore(totalScore);
				int c = scoreBiz.save(score);
				if (c == 0) {
					json.put("code", "3201005");
					json.put("msg", "成绩保存失败！");
					return json;
				}
				ResDoctorSchProcess process = processMapper.selectByPrimaryKey(processFlow);
				if (process != null) {

					process.setSchScore(totalScore);
					processMapper.updateByPrimaryKeySelective(process);
				}
			}
		}
		if("ndk".equals(typeId)) {
			SchExamDoctorArrangement result=examCfgBiz.readExamResult(processFlow);
			if (result == null) {
				json.put("code", "3201003");
				json.put("msg", "成绩记录为空！");
				return json;
			}
			BigDecimal totalScore = null;
			try {
				totalScore = new BigDecimal(theoryScore);
			} catch (Exception e) {
				json.put("code", "3201004");
				json.put("msg", "成绩格式错误！");
				return json;
			}
			if (totalScore == null) {
				json.put("code", "3201004");
				json.put("msg", "成绩格式错误！");
				return json;
			}
			result.setExamScore(totalScore);
			int c = examCfgBiz.update(result);
			if (c == 0) {
				json.put("code", "3201005");
				json.put("msg", "成绩保存失败！");
				return json;
			}
		}
		if("jyk".equals(typeId)) {

		}
		return json;
	}

	@RequestMapping({"/lookScore"})
	@ResponseBody
	public String lookScore(String arrangeFlow, String trainingSpeName, String examStartTime, String examEndTime, String examNumber, String examDuration) throws UnsupportedEncodingException {
		String testUrl = "https://jsks.ezhupei.com:901/ManagerNew/examsolu/serchfixedsolu.aspx?examSoluClass=2&examTestType=2&SpecName=" + trainingSpeName + "&startTime=" + examStartTime.substring(0, 10) + "&endTime=" + examEndTime.substring(0, 10) + "&testNumber=" + examNumber + "&soluTime=" + examDuration;
		List<SchExamStandardDept> list = this.examCfgBiz.readStandardDeptsByFlow(arrangeFlow);
		if (null != list && list.size() > 0) {
			List<String> schDeptFlowList = new ArrayList();
			Iterator var10 = list.iterator();

			while(var10.hasNext()) {
				SchExamStandardDept dept = (SchExamStandardDept)var10.next();
				schDeptFlowList.add(dept.getStandardDeptId());
			}

			List<SchAndStandardDeptCfg> deptList = this.deptCfgBiz.getListByOrgFlow(GlobalContext.getCurrentUser().getOrgFlow(), schDeptFlowList);
			if (null != deptList && deptList.size() > 0) {
				ArrayList<Object> objects = new ArrayList();
				Iterator var12 = deptList.iterator();

				while(var12.hasNext()) {
					SchAndStandardDeptCfg cfg = (SchAndStandardDeptCfg)var12.next();
					Map<String, String> map = new HashMap();
					map.put("TurnSectionName", cfg.getStandardDeptName());
					objects.add(map);
				}

				String deptInfo = JSON.toJSONString(objects);
				testUrl = testUrl + "&UserTurn=" + deptInfo;
			}
		}

		return testUrl;
	}
}
