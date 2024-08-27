package com.pinde.sci.ctrl.jsres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResMonthlyReportBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.enums.jsres.JsResDocTypeEnum;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.res.ResDocTypeEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Joq
 *
 */
@Controller
@RequestMapping("/jsres/monthlyReport")
public class JsResMonthlyReportController extends GeneralController {
	@Autowired
	private IJsResMonthlyReportBiz monthlyReportBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private MonthlyReportExtMapper monthlyReportExtMapper;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;

	/**
	 * 医院角色
     */

	@RequestMapping("/main")
	public String main(Model model){
		//时间选项
		List<SysMonthly> monthlies = monthlyReportBiz.getMonths(null);
		List<String> monthliesString = new ArrayList<>();
		if(!monthlies.isEmpty()){
			for(SysMonthly sysMonthly:monthlies){
				monthliesString.add(sysMonthly.getDateMonth());
			}
		}
		List<String> resultMonths = new ArrayList<>();
		for(int i=-1;i>-11;i--){
			String single = getLastMonths(i);
			if(monthliesString.contains(single)){
				String monthString = single.split("-")[0]+"年"+single.split("-")[1]+"月";
				resultMonths.add(monthString);
				if(i==-1){
					String currentMonth = single;
					model.addAttribute("currentMonth",currentMonth);
				}
			}
		}
		model.addAttribute("resultMonths",resultMonths);
		return "jsres/hospital/monthlyReport/monthlyReportMain";
	}

	// 获取最近N个月
	public String getLastMonths(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, i);
		Date m = c.getTime();
		return sdf.format(m);
	}

	@RequestMapping("/content0")
	public String content0(String monthDate,Model model) throws ParseException {
		String monthString = monthDate.split("-")[0]+"年"+monthDate.split("-")[1]+"月";
		model.addAttribute("monthString",monthString);

		String str=monthDate+"-01";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date =sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd");
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		String first = format.format(calendar.getTime());

		//获取当前月最后一天
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date);
		calendar2.set(Calendar.DAY_OF_MONTH, calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(calendar2.getTime());

		model.addAttribute("first",first);
		model.addAttribute("last",last);
		model.addAttribute("monthDate",monthDate);

		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		if(StringUtil.isNotBlank(orgFlow)){
			SysOrg currentOrg = orgBiz.readSysOrg(orgFlow);
			model.addAttribute("orgLevelId",currentOrg.getOrgLevelId());
		}
		return "jsres/hospital/monthlyReport/content0";
	}

	@RequestMapping("/content1")
	public String content1(String baseRange,String monthDate,Model model){
		model.addAttribute("baseRange",baseRange);
		model.addAttribute("monthDate",monthDate);
		return "jsres/hospital/monthlyReport/content1";
	}

	@RequestMapping("/chart123")//图一图二图三
	public String chart123(String baseRange,String monthDate,String isGraduate,Model model){
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorTypeId(isGraduate);
		search.setDoctorStatusId("20");
		search.setChangeTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}

		List<SysMonthlyDoctorInfo> doctorInfoListFinal = new ArrayList<>();//三图公用数据
		if(!sysMonthlyDoctorInfoList.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getOrgFlow()) && (!allOrgFlow.isEmpty()) &&
						allOrgFlow.contains(sysMonthlyDoctorInfo.getOrgFlow())){
					doctorInfoListFinal.add(sysMonthlyDoctorInfo);
				}
			}
		}

		Map<String,Integer> sessionNumberMap = new HashMap<>();//图一数据
		Map<String,Integer> speMap = new HashMap<>();//图二数据
		Map<String,Integer> doctorTypeMap = new HashMap<>();//图三数据
		Map<String,String> doctorTypeEnumMap = new HashMap<>();//人员类型枚举MAP

		if(!doctorInfoListFinal.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:doctorInfoListFinal){
				String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
				if(sessionNumberMap.get(sessionNumber)==null){
					sessionNumberMap.put(sessionNumber,1);
				}else {
					int sum = sessionNumberMap.get(sessionNumber);
					sessionNumberMap.put(sessionNumber,sum+1);
				}

				String speName = sysMonthlyDoctorInfo.getChangeSpeName();
				if(speMap.get(speName)==null){
					speMap.put(speName,1);
				}else {
					int sum = speMap.get(speName);
					speMap.put(speName,sum+1);
				}

				String doctorType = sysMonthlyDoctorInfo.getDoctorTypeId();
				if(EnumUtil.getById(doctorType, JsResDocTypeEnum.class)!=null){
					if(doctorTypeMap.get(doctorType)==null){
						doctorTypeMap.put(doctorType,1);
						doctorTypeEnumMap.put(doctorType, JsResDocTypeEnum.getNameById(doctorType));
					}else {
						int sum = doctorTypeMap.get(doctorType);
						doctorTypeMap.put(doctorType,sum+1);
					}
				}
			}
			model.addAttribute("sessionNumberMap",sessionNumberMap);
			model.addAttribute("speMap",speMap);
			model.addAttribute("doctorTypeMap",doctorTypeMap);
			model.addAttribute("doctorTypeEnumMap",doctorTypeEnumMap);

		}

		return "jsres/hospital/monthlyReport/chart123";
	}

	@RequestMapping("/chart4")//图四
	public String chart4(String baseRange,String monthDate,String isGraduate,Model model){

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<SysMonthlyChangeInfo> sysMonthlyChangeInfoList = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}

		List<SysMonthlyChangeInfo> SysMonthlyChangeInfoFinal = new ArrayList<>();
		if(!sysMonthlyChangeInfoList.isEmpty()){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:sysMonthlyChangeInfoList){
				if(StringUtil.isNotBlank(sysMonthlyChangeInfo.getOrgFlow()) &&
						(!allOrgFlow.isEmpty()) &&( allOrgFlow.contains(sysMonthlyChangeInfo.getOrgFlow()) ||
						allOrgFlow.contains(sysMonthlyChangeInfo.getHistoryOrgFlow()) )
						){
					SysMonthlyChangeInfoFinal.add(sysMonthlyChangeInfo);
				}
			}
		}
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoFinal = new ArrayList<>();
		if(!sysMonthlyReturnDelayInfoList.isEmpty()){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoList){
				if(StringUtil.isNotBlank(sysMonthlyReturnDelayInfo.getOrgFlow()) && (!allOrgFlow.isEmpty()) &&
						allOrgFlow.contains(sysMonthlyReturnDelayInfo.getOrgFlow())){
					sysMonthlyReturnDelayInfoFinal.add(sysMonthlyReturnDelayInfo);
				}
			}
		}

		Map<String,Integer> changeTypeMap = new HashMap<>();//图四数据
		changeTypeMap.put("专业变更",0);
		changeTypeMap.put("转入",0);
		changeTypeMap.put("转出",0);
		changeTypeMap.put("延期",0);
		changeTypeMap.put("退培",0);

		if(!SysMonthlyChangeInfoFinal.isEmpty()){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:SysMonthlyChangeInfoFinal){
				if("SpeChange".equals(sysMonthlyChangeInfo.getChangeTypeId())){
					int sum = changeTypeMap.get("专业变更");
					changeTypeMap.put("专业变更",sum+1);
				}
				if("BaseChange".equals(sysMonthlyChangeInfo.getChangeTypeId())&&StringUtil.isNotBlank(sysMonthlyChangeInfo.getOrgFlow()) &&
						(!allOrgFlow.isEmpty()) && allOrgFlow.contains(sysMonthlyChangeInfo.getOrgFlow())){
					int sum = changeTypeMap.get("转入");
					changeTypeMap.put("转入",sum+1);
				}
				if("BaseChange".equals(sysMonthlyChangeInfo.getChangeTypeId())&&StringUtil.isNotBlank(sysMonthlyChangeInfo.getHistoryOrgFlow()) &&
						(!allOrgFlow.isEmpty()) && allOrgFlow.contains(sysMonthlyChangeInfo.getHistoryOrgFlow())){
					int sum = changeTypeMap.get("转出");
					changeTypeMap.put("转出",sum+1);
				}
			}
		}
		if(!sysMonthlyReturnDelayInfoFinal.isEmpty()){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoFinal){
				if("Delay".equals(sysMonthlyReturnDelayInfo.getTypeId())){
					int sum = changeTypeMap.get("延期");
					changeTypeMap.put("延期",sum+1);
				}
				if("ReturnTraining".equals(sysMonthlyReturnDelayInfo.getTypeId())){
					int sum = changeTypeMap.get("退培");
					changeTypeMap.put("退培",sum+1);
				}
			}
		}
		model.addAttribute("changeTypeMap",changeTypeMap);
		return "jsres/hospital/monthlyReport/chart4";
	}

	@RequestMapping("/chart5")//图五
	public String chart5(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}

		Map<String,Integer> resultMap = new HashMap<>();
		resultMap.put("入科人数",0);
		resultMap.put("出科人数",0);
		resultMap.put("出科异常人数",0);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoListFinal = new ArrayList<>();
		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				if(StringUtil.isNotBlank(sysMonthlyDocCycleInfo.getOrgFlow()) && (!allOrgFlow.isEmpty())
						&& allOrgFlow.contains(sysMonthlyDocCycleInfo.getOrgFlow())){
					sysMonthlyDocCycleInfoListFinal.add(sysMonthlyDocCycleInfo);
				}
			}
		}

		if(!sysMonthlyDocCycleInfoListFinal.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoListFinal){
				int inNum = resultMap.get("入科人数");
				int outNum = resultMap.get("出科人数");
				int errorNum = resultMap.get("出科异常人数");
				if(monthDate.equals(sysMonthlyDocCycleInfo.getSchStartDate().substring(0,7))){
					resultMap.put("入科人数",inNum+1);
				}
				if(monthDate.equals(sysMonthlyDocCycleInfo.getSchEndDate().substring(0,7))&&"isSch".equals(sysMonthlyDocCycleInfo.getSchStatus())){
					resultMap.put("出科人数",outNum+1);
				}
				if(monthDate.equals(sysMonthlyDocCycleInfo.getSchEndDate().substring(0,7))&&"isCurrent".equals(sysMonthlyDocCycleInfo.getSchStatus())){
					resultMap.put("出科异常人数",errorNum+1);
				}
			}
		}
		model.addAttribute("resultMap",resultMap);
		return "jsres/hospital/monthlyReport/chart5";
	}


	@RequestMapping("/chart6")//图六
	public String chart6(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		Map<String,Object> resultMap = monthlyReportBiz.getChart6Data(paramMap);
		model.addAttribute("resultMap",resultMap);
		return "jsres/hospital/monthlyReport/chart6";
	}

	@RequestMapping("/chart7")//图七
	public String chart7(String baseRange,String monthDate,String isGraduate,Model model){
//		SysMonthlyDocCycleInfo search = new SysMonthlyDocCycleInfo();
//		search.setDateMonth(monthDate);
//		search.setDoctorTypeId(isGraduate);
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoListFinal = new ArrayList<>();
		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				if(StringUtil.isNotBlank(sysMonthlyDocCycleInfo.getOrgFlow()) && (!allOrgFlow.isEmpty())
						&& allOrgFlow.contains(sysMonthlyDocCycleInfo.getOrgFlow())){
					sysMonthlyDocCycleInfoListFinal.add(sysMonthlyDocCycleInfo);
				}
			}
		}

		Map<String,Integer> resultMap = new HashMap<>();
		resultMap.put("大病例",0);
		resultMap.put("病种",0);
		resultMap.put("操作技能",0);
		resultMap.put("手术",0);

		if(!sysMonthlyDocCycleInfoListFinal.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoListFinal){
				int caseRegistry = resultMap.get("大病例");
				resultMap.put("大病例",caseRegistry+sysMonthlyDocCycleInfo.getCaseRegistry());
				int diseaseRegistry = resultMap.get("病种");
				resultMap.put("病种",diseaseRegistry+sysMonthlyDocCycleInfo.getDiseaseRegistry());
				int skillRegistry = resultMap.get("操作技能");
				resultMap.put("操作技能",skillRegistry+sysMonthlyDocCycleInfo.getSkillRegistry());
				int operationRegistry = resultMap.get("手术");
				resultMap.put("手术",operationRegistry+sysMonthlyDocCycleInfo.getOperationRegistry());
			}
		}
		model.addAttribute("resultMap",resultMap);
		return "jsres/hospital/monthlyReport/chart7";
	}

	@RequestMapping("/chart8")//图八
	public String chart8(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoListFinal = new ArrayList<>();
		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				if(StringUtil.isNotBlank(sysMonthlyDocCycleInfo.getOrgFlow()) && (!allOrgFlow.isEmpty())
						&& allOrgFlow.contains(sysMonthlyDocCycleInfo.getOrgFlow())){
					sysMonthlyDocCycleInfoListFinal.add(sysMonthlyDocCycleInfo);
				}
			}
		}

		Map<String,Integer> resultMap = new HashMap<>();
		resultMap.put("学员填写数据总量",0);
		resultMap.put("审核数据总量",0);
		resultMap.put("未审核数据总量",0);

		if(!sysMonthlyDocCycleInfoListFinal.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoListFinal){
				int recNum = resultMap.get("学员填写数据总量");
				resultMap.put("学员填写数据总量",recNum+sysMonthlyDocCycleInfo.getRecNum());
				int auditNum = resultMap.get("审核数据总量");
				resultMap.put("审核数据总量",auditNum+sysMonthlyDocCycleInfo.getAuditNum());
				int notAuditNum = resultMap.get("未审核数据总量");
				resultMap.put("未审核数据总量",notAuditNum+sysMonthlyDocCycleInfo.getNotAuditNum());
			}
		}
		model.addAttribute("resultMap",resultMap);
		return "jsres/hospital/monthlyReport/chart8";
	}

	@RequestMapping("/chart9")//图九
	public String chart9(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		Map<String,Object> resultMap = monthlyReportBiz.getChart9Data(paramMap);
		model.addAttribute("resultMap",resultMap);
		return "jsres/hospital/monthlyReport/chart9";
	}

	@RequestMapping("/export123")
	public void export123(String baseRange,String monthDate,String isGraduate,HttpServletResponse response)
			throws DocumentException, IOException {
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorTypeId(isGraduate);
		search.setDoctorStatusId("20");
		search.setChangeTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());

		SysMonthlyDoctorDetailInfo search2 = new SysMonthlyDoctorDetailInfo();
		search2.setDateMonth(monthDate);

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);
		List<SysMonthlyDoctorDetailInfo> sysMonthlyDoctorDetailInfoList = monthlyReportBiz.getMonthlyDoctorDetailInfo(search2);
		List<SysUser> userList = monthlyReportExtMapper.getUserList(paramMap);
		List<ResDoctor> doctorList = monthlyReportExtMapper.getDoctorList(paramMap);
		List<ResDoctorRecruit> recruitList = monthlyReportExtMapper.getRecruitList(paramMap);

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}

		List<SysMonthlyDoctorInfo> doctorInfoListFinal = new ArrayList<>();//三图公用数据
		if(!sysMonthlyDoctorInfoList.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getOrgFlow()) && (!allOrgFlow.isEmpty()) &&
						allOrgFlow.contains(sysMonthlyDoctorInfo.getOrgFlow())){
					doctorInfoListFinal.add(sysMonthlyDoctorInfo);
				}
			}
		}

		Map<String,SysMonthlyDoctorDetailInfo> detailInfoMap = new HashMap<>();
		Map<String,SysUser> userMap = new HashMap<>();
		Map<String,ResDoctor> doctorMap = new HashMap<>();
		Map<String,ResDoctorRecruit> recruitMap = new HashMap<>();

		if(!doctorInfoListFinal.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:doctorInfoListFinal){
				String doctorFlow = sysMonthlyDoctorInfo.getDoctorFlow();
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				if(StringUtil.isNotBlank(doctorFlow)){
					for(SysMonthlyDoctorDetailInfo doctorDetailInfo:sysMonthlyDoctorDetailInfoList){
						if(doctorFlow.equals(doctorDetailInfo.getDoctorFlow())){
							detailInfoMap.put(doctorFlow,doctorDetailInfo);
							break;
						}
					}
					for(SysUser sysUser:userList){
						if(doctorFlow.equals(sysUser.getUserFlow())){
							userMap.put(doctorFlow,sysUser);
							break;
						}
					}
					for(ResDoctor resDoctor:doctorList){
						if(doctorFlow.equals(resDoctor.getDoctorFlow())){
							doctorMap.put(doctorFlow,resDoctor);
							break;
						}
					}
					for(ResDoctorRecruit resDoctorRecruit:recruitList){
						if(recruitFlow.equals(resDoctorRecruit.getRecruitFlow())){
							recruitMap.put(doctorFlow,resDoctorRecruit);
							break;
						}
					}
				}
			}
		}

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//列宽自适应
		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"培训基地",
				"*姓名",
				"*性别",
				"*出生日期（yyyy-mm-dd）",
				"*身份证件类型",
				"#身份证件号码",
				"*国家或地区",
				"*民族",
				"*手机号",
				"邮箱",
				"QQ（非必填）",
				"*往届/应届",
				"*培训专业",
				"*是否通过全国医师资格考试",
				"执业医师资格证号",
				"*招收年度",
				"*实际培训开始时间（yyyy-mm）",
				"*是否为对口支援计划住院医师",
				"#对口支援计划住院医师送出单位（请填全称）",
				"*培训年限核定",
				"*学员类型",
				"*毕业学校（本科）",
				"*毕业年份（本科）",
				"*毕业专业（本科）",
				"*是否全科订单定向学员",
				"*学位（本科）",
				"*是否硕士研究生",
				"#毕业院校（硕士）",
				"#毕业年份（硕士）",
				"#毕业专业（硕士）",
				"#学位（硕士）",
				"#学位类型（硕士）",
				"*是否博士研究生",
				"#毕业院校（博士）",
				"#毕业年份（博士）",
				"#毕业专业（博士）",
				"#学位（博士）",
				"#学位类型（博士）",
				"#工作单位（与单位公章对应的官方全称）",
				"#医院级别",
				"#医院等次",
				"#医疗卫生机构类别",
				"#医疗卫生机构隶属关系",
				"*是否在协同单位培训",
				"#协同单位（与单位公章对应的官方全称）",
				"#协同医院级别",
				"#协同医院等次",
				"#医疗卫生机构类别"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 1;
		String[] dataList = null;
		if (!doctorInfoListFinal.isEmpty()) {
			for (int i = 0; i < doctorInfoListFinal.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行

				SysMonthlyDoctorInfo sysMonthlyDoctorInfo = doctorInfoListFinal.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(sysMonthlyDoctorInfo.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(sysMonthlyDoctorInfo.getOrgFlow());
					joinName = sysMonthlyDoctorInfo.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = sysMonthlyDoctorInfo.getOrgName();
				}
				String currentDoctorFlow = sysMonthlyDoctorInfo.getDoctorFlow();
				String age ="";
				if(userMap.get(currentDoctorFlow)!=null&&StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7) ;
				//解析xml
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);

				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(recruit.getTrainYear())){
					trainYear = recruit.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						recruit.getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						doctor.getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

						jointFlag,
						joinName,
						"",
						"",
						""
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());

	}

	@RequestMapping("/export4")//导出图四
	public void export4(String baseRange,String monthDate,String isGraduate,HttpServletResponse response) throws DocumentException, IOException {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
		SysMonthlyDoctorDetailInfo search2 = new SysMonthlyDoctorDetailInfo();
		search2.setDateMonth(monthDate);
		List<SysMonthlyChangeInfo> sysMonthlyChangeInfoList = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);
		List<SysMonthlyDoctorDetailInfo> sysMonthlyDoctorDetailInfoList = monthlyReportBiz.getMonthlyDoctorDetailInfo(search2);
		List<SysUser> userList = monthlyReportExtMapper.getUserList(paramMap);
		List<ResDoctor> doctorList = monthlyReportExtMapper.getDoctorList(paramMap);
		List<ResDoctorRecruit> recruitList = monthlyReportExtMapper.getRecruitList(paramMap);

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}

		List<SysMonthlyChangeInfo> SysMonthlyChangeInfoFinal = new ArrayList<>();
		if(!sysMonthlyChangeInfoList.isEmpty()){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:sysMonthlyChangeInfoList){
				if(StringUtil.isNotBlank(sysMonthlyChangeInfo.getOrgFlow()) &&
						(!allOrgFlow.isEmpty()) &&( allOrgFlow.contains(sysMonthlyChangeInfo.getOrgFlow()) ||
						allOrgFlow.contains(sysMonthlyChangeInfo.getHistoryOrgFlow()) )
						){
					SysMonthlyChangeInfoFinal.add(sysMonthlyChangeInfo);
				}
			}
		}
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoFinal = new ArrayList<>();
		if(!sysMonthlyReturnDelayInfoList.isEmpty()){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoList){
				if(StringUtil.isNotBlank(sysMonthlyReturnDelayInfo.getOrgFlow()) && (!allOrgFlow.isEmpty()) &&
						allOrgFlow.contains(sysMonthlyReturnDelayInfo.getOrgFlow())){
					sysMonthlyReturnDelayInfoFinal.add(sysMonthlyReturnDelayInfo);
				}
			}
		}

		List<SysMonthlyChangeInfo> speChangeList = new ArrayList<>();
		List<SysMonthlyChangeInfo> baseChangeInList = new ArrayList<>();
		List<SysMonthlyChangeInfo> baseChangeOutList = new ArrayList<>();
		List<SysMonthlyReturnDelayInfo> delayList = new ArrayList<>();
		List<SysMonthlyReturnDelayInfo> returnTrainingList = new ArrayList<>();
		Map<String,SysMonthlyDoctorDetailInfo> detailInfoMap = new HashMap<>();
		Map<String,SysUser> userMap = new HashMap<>();
		Map<String,ResDoctor> doctorMap = new HashMap<>();
		Map<String,ResDoctorRecruit> recruitMap = new HashMap<>();

		if(!SysMonthlyChangeInfoFinal.isEmpty()){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:SysMonthlyChangeInfoFinal){
				String doctorFlow = sysMonthlyChangeInfo.getDoctorFlow();
				String recruitFlow = sysMonthlyChangeInfo.getRecruitFlow();
				if(StringUtil.isNotBlank(doctorFlow)){
					for(SysMonthlyDoctorDetailInfo doctorDetailInfo:sysMonthlyDoctorDetailInfoList){
						if(doctorFlow.equals(doctorDetailInfo.getDoctorFlow())){
							detailInfoMap.put(doctorFlow,doctorDetailInfo);
							break;
						}
					}
					for(SysUser sysUser:userList){
						if(doctorFlow.equals(sysUser.getUserFlow())){
							userMap.put(doctorFlow,sysUser);
							break;
						}
					}
					for(ResDoctor resDoctor:doctorList){
						if(doctorFlow.equals(resDoctor.getDoctorFlow())){
							doctorMap.put(doctorFlow,resDoctor);
							break;
						}
					}
					for(ResDoctorRecruit resDoctorRecruit:recruitList){
						if(recruitFlow.equals(resDoctorRecruit.getRecruitFlow())){
							recruitMap.put(doctorFlow,resDoctorRecruit);
							break;
						}
					}
				}
			}
		}

		if(!sysMonthlyReturnDelayInfoFinal.isEmpty()){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoFinal){
				String doctorFlow = sysMonthlyReturnDelayInfo.getDoctorFlow();
				String recruitFlow = sysMonthlyReturnDelayInfo.getRecruitFlow();
				if(StringUtil.isNotBlank(doctorFlow)){
					for(SysMonthlyDoctorDetailInfo doctorDetailInfo:sysMonthlyDoctorDetailInfoList){
						if(doctorFlow.equals(doctorDetailInfo.getDoctorFlow())){
							detailInfoMap.put(doctorFlow,doctorDetailInfo);
							break;
						}
					}
					for(SysUser sysUser:userList){
						if(doctorFlow.equals(sysUser.getUserFlow())){
							userMap.put(doctorFlow,sysUser);
							break;
						}
					}
					for(ResDoctor resDoctor:doctorList){
						if(doctorFlow.equals(resDoctor.getDoctorFlow())){
							doctorMap.put(doctorFlow,resDoctor);
							break;
						}
					}
					for(ResDoctorRecruit resDoctorRecruit:recruitList){
						if(recruitFlow.equals(resDoctorRecruit.getRecruitFlow())){
							recruitMap.put(doctorFlow,resDoctorRecruit);
							break;
						}
					}
				}
			}
		}

		if(!SysMonthlyChangeInfoFinal.isEmpty()){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:SysMonthlyChangeInfoFinal){
				if("SpeChange".equals(sysMonthlyChangeInfo.getChangeTypeId())){
					speChangeList.add(sysMonthlyChangeInfo);
				}
				if("BaseChange".equals(sysMonthlyChangeInfo.getChangeTypeId())&&StringUtil.isNotBlank(sysMonthlyChangeInfo.getOrgFlow()) &&
						(!allOrgFlow.isEmpty()) && allOrgFlow.contains(sysMonthlyChangeInfo.getOrgFlow())){
					baseChangeInList.add(sysMonthlyChangeInfo);
				}
				if("BaseChange".equals(sysMonthlyChangeInfo.getChangeTypeId())&&StringUtil.isNotBlank(sysMonthlyChangeInfo.getHistoryOrgFlow()) &&
						(!allOrgFlow.isEmpty()) && allOrgFlow.contains(sysMonthlyChangeInfo.getHistoryOrgFlow())){
					baseChangeOutList.add(sysMonthlyChangeInfo);
				}
			}
		}
		if(!sysMonthlyReturnDelayInfoFinal.isEmpty()){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoFinal){
				if("Delay".equals(sysMonthlyReturnDelayInfo.getTypeId())){
					delayList.add(sysMonthlyReturnDelayInfo);
				}
				if("ReturnTraining".equals(sysMonthlyReturnDelayInfo.getTypeId())){
					returnTrainingList.add(sysMonthlyReturnDelayInfo);
				}
			}
		}

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("专业变更");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//列宽自适应
		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"培训基地",
				"*姓名",
				"*性别",
				"*出生日期（yyyy-mm-dd）",
				"*身份证件类型",
				"#身份证件号码",
				"*国家或地区",
				"*民族",
				"*手机号",
				"邮箱",
				"QQ（非必填）",
				"*往届/应届",
				"*培训专业",
				"*是否通过全国医师资格考试",
				"执业医师资格证号",
				"*招收年度",
				"*实际培训开始时间（yyyy-mm）",
				"*是否为对口支援计划住院医师",
				"#对口支援计划住院医师送出单位（请填全称）",
				"*培训年限核定",
				"*学员类型",
				"*毕业学校（本科）",
				"*毕业年份（本科）",
				"*毕业专业（本科）",
				"*是否全科订单定向学员",
				"*学位（本科）",
				"*是否硕士研究生",
				"#毕业院校（硕士）",
				"#毕业年份（硕士）",
				"#毕业专业（硕士）",
				"#学位（硕士）",
				"#学位类型（硕士）",
				"*是否博士研究生",
				"#毕业院校（博士）",
				"#毕业年份（博士）",
				"#毕业专业（博士）",
				"#学位（博士）",
				"#学位类型（博士）",
				"#工作单位（与单位公章对应的官方全称）",
				"#医院级别",
				"#医院等次",
				"#医疗卫生机构类别",
				"#医疗卫生机构隶属关系",
				"*是否在协同单位培训",
				"#协同单位（与单位公章对应的官方全称）",
				"#协同医院级别",
				"#协同医院等次",
				"#医疗卫生机构类别"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 1;
		String[] dataList = null;
		if (!speChangeList.isEmpty()) {
			for (int i = 0; i < speChangeList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				SysMonthlyChangeInfo sysMonthlyDoctorInfo = speChangeList.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(sysMonthlyDoctorInfo.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(sysMonthlyDoctorInfo.getOrgFlow());
					joinName = sysMonthlyDoctorInfo.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = sysMonthlyDoctorInfo.getOrgName();
				}
				String currentDoctorFlow = sysMonthlyDoctorInfo.getDoctorFlow();
				String age ="";
				if(userMap.get(currentDoctorFlow)!=null&&StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7) ;
				//解析xml
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);

				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(recruit.getTrainYear())){
					trainYear = recruit.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						recruit.getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						doctor.getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

						jointFlag,
						joinName,
						"",
						"",
						""
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		// 为工作簿添加sheet
		HSSFSheet sheet2 = wb.createSheet("基地转入");

		//列宽自适应
		HSSFRow rowThree2 = sheet2.createRow(0);//第三行
		HSSFCell cellTitle2 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle2 = rowThree2.createCell(i);
			cellTitle2.setCellValue(titles[i]);
			cellTitle2.setCellStyle(styleCenter);
			sheet2.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum2 = 1;
		String[] dataList2 = null;
		if (!baseChangeInList.isEmpty()) {
			for (int i = 0; i < baseChangeInList.size(); i++, rowNum2++) {
				HSSFRow rowFour = sheet2.createRow(rowNum2);//第二行
				SysMonthlyChangeInfo sysMonthlyDoctorInfo = baseChangeInList.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(sysMonthlyDoctorInfo.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(sysMonthlyDoctorInfo.getOrgFlow());
					joinName = sysMonthlyDoctorInfo.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = sysMonthlyDoctorInfo.getOrgName();
				}
				String currentDoctorFlow = sysMonthlyDoctorInfo.getDoctorFlow();
				String age ="";
				if(userMap.get(currentDoctorFlow)!=null&&StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7) ;
				//解析xml
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);

				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(recruit.getTrainYear())){
					trainYear = recruit.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList2 = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						recruit.getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						doctor.getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

						jointFlag,
						joinName,
						"",
						"",
						""
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList2[j]);
				}
			}
		}

		// 为工作簿添加sheet
		HSSFSheet sheet4 = wb.createSheet("基地转出");

		//列宽自适应
		HSSFRow rowThree4 = sheet4.createRow(0);//第三行
		HSSFCell cellTitle4 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle4 = rowThree4.createCell(i);
			cellTitle4.setCellValue(titles[i]);
			cellTitle4.setCellStyle(styleCenter);
			sheet4.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum4 = 1;
		String[] dataList4 = null;
		if (!baseChangeOutList.isEmpty()) {
			for (int i = 0; i < baseChangeOutList.size(); i++, rowNum4++) {
				HSSFRow rowFour = sheet4.createRow(rowNum4);//第二行
				SysMonthlyChangeInfo sysMonthlyDoctorInfo = baseChangeOutList.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(sysMonthlyDoctorInfo.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(sysMonthlyDoctorInfo.getOrgFlow());
					joinName = sysMonthlyDoctorInfo.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = sysMonthlyDoctorInfo.getOrgName();
				}
				String currentDoctorFlow = sysMonthlyDoctorInfo.getDoctorFlow();
				String age ="";
				if(userMap.get(currentDoctorFlow)!=null&&StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7) ;
				//解析xml
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);

				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(recruit.getTrainYear())){
					trainYear = recruit.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList4 = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						recruit.getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						doctor.getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

						jointFlag,
						joinName,
						"",
						"",
						""
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList4[j]);
				}
			}
		}

		// 为工作簿添加sheet
		HSSFSheet sheet3 = wb.createSheet("延期");

		//列宽自适应
		HSSFRow rowThree3 = sheet3.createRow(0);//第三行
		HSSFCell cellTitle3 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle3 = rowThree3.createCell(i);
			cellTitle3.setCellValue(titles[i]);
			cellTitle3.setCellStyle(styleCenter);
			sheet3.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum3 = 1;
		String[] dataList3 = null;
		if (!delayList.isEmpty()) {
			for (int i = 0; i < delayList.size(); i++, rowNum3++) {
				HSSFRow rowFour = sheet3.createRow(rowNum3);//第二行
				SysMonthlyReturnDelayInfo sysMonthlyDoctorInfo = delayList.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(sysMonthlyDoctorInfo.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(sysMonthlyDoctorInfo.getOrgFlow());
					joinName = sysMonthlyDoctorInfo.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = sysMonthlyDoctorInfo.getOrgName();
				}
				String currentDoctorFlow = sysMonthlyDoctorInfo.getDoctorFlow();
				String age ="";
				if(userMap.get(currentDoctorFlow)!=null&&StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7) ;
				//解析xml
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);

				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(recruit.getTrainYear())){
					trainYear = recruit.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList3 = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						recruit.getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						doctor.getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

						jointFlag,
						joinName,
						"",
						"",
						""
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList3[j]);
				}
			}
		}

		// 为工作簿添加sheet
		HSSFSheet sheet5 = wb.createSheet("退培");

		//列宽自适应
		HSSFRow rowThree5 = sheet5.createRow(0);//第三行
		HSSFCell cellTitle5 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle5 = rowThree5.createCell(i);
			cellTitle5.setCellValue(titles[i]);
			cellTitle5.setCellStyle(styleCenter);
			sheet5.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum5 = 1;
		String[] dataList5 = null;
		if (!returnTrainingList.isEmpty()) {
			for (int i = 0; i < returnTrainingList.size(); i++, rowNum5++) {
				HSSFRow rowFour = sheet5.createRow(rowNum5);//第二行
				SysMonthlyReturnDelayInfo sysMonthlyDoctorInfo = returnTrainingList.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(sysMonthlyDoctorInfo.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(sysMonthlyDoctorInfo.getOrgFlow());
					joinName = sysMonthlyDoctorInfo.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = sysMonthlyDoctorInfo.getOrgName();
				}
				String currentDoctorFlow = sysMonthlyDoctorInfo.getDoctorFlow();
				String age ="";
				if(userMap.get(currentDoctorFlow)!=null&&StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7) ;
				//解析xml
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);

				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(recruit.getTrainYear())){
					trainYear = recruit.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList5 = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						recruit.getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						doctor.getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),

						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,

						jointFlag,
						joinName,
						"",
						"",
						""
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList5[j]);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping("/getDepts")
	@ResponseBody
	public Object getDepts(String orgFlow){
		SysMonthlyDocCycleInfo search = new SysMonthlyDocCycleInfo();
		if(!"all".equals(orgFlow)){
			search.setOrgFlow(orgFlow);
		}
		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportBiz.getSysMonthlyDocCycleInfo(search);

		Map<String,String> deptMap = new HashMap<>();
		if(sysMonthlyDocCycleInfoList!=null && sysMonthlyDocCycleInfoList.size()>0){
			for(SysMonthlyDocCycleInfo info:sysMonthlyDocCycleInfoList){
				String deptFlow = info.getDeptFlow();
				if(StringUtil.isNotBlank(deptFlow)){
					if(deptMap.get(deptFlow)==null){
						deptMap.put(deptFlow,info.getSchDeptName());
					}
				}
			}
		}
		return deptMap;
	}

	@RequestMapping("/detail5Main")
	public String detail5Main(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		SysOrg currentOrg = orgBiz.readSysOrg(currentOrgFlow);
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		Map<String,String> orgMap = new HashMap<>();
		orgMap.put(currentOrg.getOrgFlow(),currentOrg.getOrgName());
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgList){
				orgMap.put(jointOrg.getJointOrgFlow(),jointOrg.getJointOrgName());
			}
		}
		model.addAttribute("currentOrgFlow",currentOrgFlow);
		model.addAttribute("orgMap",orgMap);//培训基地


		Map<String,String> deptMap = new HashMap<>();
		Map<String,String> speMap = new HashMap<>();

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("dateMonth",monthDate);
		List<String> allOrgFlow = new ArrayList<>();
		for(String s:orgMap.keySet()){
			allOrgFlow.add(s);
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		List<SysMonthlyDocCycleInfo>  sysMonthlyDocCycleInfos= monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);
		if(!sysMonthlyDocCycleInfos.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfos){
				String schDeptFlow = sysMonthlyDocCycleInfo.getSchDeptFlow();
				String trainingSpeId = sysMonthlyDocCycleInfo.getTrainingSpeId();
				if(StringUtil.isNotBlank(schDeptFlow)){
					if(deptMap.get(schDeptFlow)==null){
						deptMap.put(schDeptFlow,sysMonthlyDocCycleInfo.getSchDeptName());
					}
				}
				if(StringUtil.isNotBlank(trainingSpeId)){
					if(speMap.get(trainingSpeId)==null){
						speMap.put(trainingSpeId,sysMonthlyDocCycleInfo.getTrainingSpeName());
					}
				}
			}
		}
		model.addAttribute("deptMap",deptMap);//轮转科室
		model.addAttribute("speMap",speMap);//专业


		return "jsres/hospital/monthlyReport/detail5Main";
	}

	@RequestMapping("/detail5")
	public String detail5(String orgFlow,String schDeptFlow,String sessionNumber,String trainingSpeId,String monthDate,Integer currentPage,
						  String[] doctorTypeIds,Model model,HttpServletRequest request){
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
		}

		Map<String,Object> paramMap = new HashMap<>();

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorTypeIdList",doctorTypeIdList);
		paramMap.put("monthDate",monthDate);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getHospitalChart5Detail(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/hospital/monthlyReport/detail5";
	}

	@RequestMapping("/exportDetail5")
	public void exportDetail5(String orgFlow,String schDeptFlow,String sessionNumber,String trainingSpeId,String monthDate,
						  String[] doctorTypeIds,Model model,HttpServletResponse response) throws Exception {
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
		}
		Map<String,Object> paramMap = new HashMap<>();

		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorTypeIdList",doctorTypeIdList);
		paramMap.put("monthDate",monthDate);
		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getHospitalChart5Detail(paramMap);
		String fileName = "出入科人数统计.xls";
		String titles[] = {
				"ORG_NAME:培训基地",
				"SCH_DEPT_NAME:科室名称",
				"INNUM:入科人数",
				"OUTNUM:出科人数",
				"ERRORNUM:出科异常人数"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportDoctorDetail5")
	public void exportDoctorDetail5(String orgFlow,String schDeptFlow,String sessionNumber,String trainingSpeId,String monthDate,
							  String[] doctorTypeIds,Model model,HttpServletResponse response) throws Exception {
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorTypeIdList",doctorTypeIdList);
		paramMap.put("monthDate",monthDate);
		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getHospitalChart5DoctorDetail(paramMap);
		paramMap.put("schStatus","isSch");
		List<Map<String,String>> resultMapList2 = monthlyReportExtMapper.getHospitalChart5DoctorDetail(paramMap);
		paramMap.put("schStatus","isCurrent");
		List<Map<String,String>> resultMapList3 = monthlyReportExtMapper.getHospitalChart5DoctorDetail(paramMap);

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("入科学员");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		//列宽自适应
		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"培训基地",
				"科室名称",
				"学员姓名",
				"年级",
				"专业",
				"联系电话",
				"轮转开始时间",
				"轮转结束时间",
				"带教老师"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 156);
		}

		int rowNum = 1;
		String[] dataList = null;
		if (!resultMapList.isEmpty()) {
			for (int i = 0; i < resultMapList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				Map<String,String> map = resultMapList.get(i);
				dataList = new String[]{
					map.get("ORG_NAME"),
					map.get("SCH_DEPT_NAME"),
					map.get("DOCTOR_NAME"),
					map.get("SESSION_NUMBER"),
					map.get("TRAINING_SPE_NAME"),
					map.get("USER_PHONE"),
					map.get("SCH_START_DATE"),
					map.get("SCH_END_DATE"),
					map.get("TEACHER_USER_NAME")
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		// 为工作簿添加sheet
		HSSFSheet sheet2 = wb.createSheet("出科学员");

		//列宽自适应
		HSSFRow rowThree2 = sheet2.createRow(0);//第三行
		HSSFCell cellTitle2 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle2 = rowThree2.createCell(i);
			cellTitle2.setCellValue(titles[i]);
			cellTitle2.setCellStyle(styleCenter);
			sheet2.setColumnWidth(i, titles.length * 2 * 156);
		}

		int rowNum2 = 1;
		String[] dataList2 = null;
		if (!resultMapList2.isEmpty()) {
			for (int i = 0; i < resultMapList2.size(); i++, rowNum2++) {
				HSSFRow rowFour = sheet2.createRow(rowNum2);//第二行
				Map<String,String> map = resultMapList2.get(i);
				dataList2 = new String[]{
						map.get("ORG_NAME"),
						map.get("SCH_DEPT_NAME"),
						map.get("DOCTOR_NAME"),
						map.get("SESSION_NUMBER"),
						map.get("TRAINING_SPE_NAME"),
						map.get("USER_PHONE"),
						map.get("SCH_START_DATE"),
						map.get("SCH_END_DATE"),
						map.get("TEACHER_USER_NAME")
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList2[j]);
				}
			}
		}

		// 为工作簿添加sheet
		HSSFSheet sheet3 = wb.createSheet("出科异常学员");

		//列宽自适应
		HSSFRow rowThree3 = sheet3.createRow(0);//第三行
		HSSFCell cellTitle3 = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle3 = rowThree3.createCell(i);
			cellTitle3.setCellValue(titles[i]);
			cellTitle3.setCellStyle(styleCenter);
			sheet3.setColumnWidth(i, titles.length * 2 * 156);
		}

		int rowNum3 = 1;
		String[] dataList3 = null;
		if (!resultMapList3.isEmpty()) {
			for (int i = 0; i < resultMapList3.size(); i++, rowNum3++) {
				HSSFRow rowFour = sheet3.createRow(rowNum3);//第二行
				Map<String,String> map = resultMapList3.get(i);
				dataList3 = new String[]{
						map.get("ORG_NAME"),
						map.get("SCH_DEPT_NAME"),
						map.get("DOCTOR_NAME"),
						map.get("SESSION_NUMBER"),
						map.get("TRAINING_SPE_NAME"),
						map.get("USER_PHONE"),
						map.get("SCH_START_DATE"),
						map.get("SCH_END_DATE"),
						map.get("TEACHER_USER_NAME")
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList3[j]);
				}
			}
		}
		String fileName = "出入科人员信息统计.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping("/detail6Main")
	public String detail6Main(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		SysOrg currentOrg = orgBiz.readSysOrg(currentOrgFlow);
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		Map<String,String> orgMap = new HashMap<>();
		orgMap.put(currentOrg.getOrgFlow(),currentOrg.getOrgName());
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgList){
				orgMap.put(jointOrg.getJointOrgFlow(),jointOrg.getJointOrgName());
			}
		}
		model.addAttribute("currentOrgFlow",currentOrgFlow);
		model.addAttribute("orgMap",orgMap);//培训基地


		Map<String,String> deptMap = new HashMap<>();
		Map<String,String> speMap = new HashMap<>();

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("dateMonth",monthDate);
		List<String> allOrgFlow = new ArrayList<>();
		for(String s:orgMap.keySet()){
			allOrgFlow.add(s);
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		List<SysMonthlyDocCycleInfo>  sysMonthlyDocCycleInfos= monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);
		if(!sysMonthlyDocCycleInfos.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfos){
				String schDeptFlow = sysMonthlyDocCycleInfo.getSchDeptFlow();
				String trainingSpeId = sysMonthlyDocCycleInfo.getTrainingSpeId();
				if(StringUtil.isNotBlank(schDeptFlow)){
					if(deptMap.get(schDeptFlow)==null){
						deptMap.put(schDeptFlow,sysMonthlyDocCycleInfo.getSchDeptName());
					}
				}
				if(StringUtil.isNotBlank(trainingSpeId)){
					if(speMap.get(trainingSpeId)==null){
						speMap.put(trainingSpeId,sysMonthlyDocCycleInfo.getTrainingSpeName());
					}
				}
			}
		}
		model.addAttribute("deptMap",deptMap);//轮转科室
		model.addAttribute("speMap",speMap);//专业


		return "jsres/hospital/monthlyReport/detail6Main";
	}

	@RequestMapping("/detail6")
	public String detail6(String orgFlow,String schDeptFlow,String sessionNumber,String trainingSpeId,String monthDate,Integer currentPage,
						  String[] doctorTypeIds,Model model,HttpServletRequest request){
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);


		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorTypeIdList",doctorTypeIdList);
		paramMap.put("monthDate",monthDate);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getHospitalChart6Detail(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/hospital/monthlyReport/detail6";
	}

	@RequestMapping("/exportDetail6")
	public void exportDetail6(String orgFlow,String schDeptFlow,String sessionNumber,String trainingSpeId,String monthDate,
							  String[] doctorTypeIds,HttpServletResponse response) throws Exception {
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorTypeIdList",doctorTypeIdList);
		paramMap.put("monthDate",monthDate);
		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getHospitalChart6Detail(paramMap);
		String fileName = "出科考核详情.xls";
		String titles[] = {
				"ORG_NAME:培训基地",
				"DOCTOR_NAME:学员姓名",
				"SESSION_NUMBER:年级",
				"TRAINING_SPE_NAME:专业",
				"SCH_DEPT_NAME:轮转科室",
				"SCH_START_DATE:轮转开始时间",
				"SCH_END_DATE:轮转结束时间",
				"TEACHER_USER_NAME:带教老师",
				"HEAD_USER_NAME:科主任",
				"IS_HAVE_GRADE:是否完成双向评价",
				"THEORY_SCORE:出科理论成绩",
				"SKILL_SCORE:出科技能成绩",
				"SKILL_NAME:考核技能名称"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportDetail6_2")
	public void exportDetail6_2(String baseRange,String monthDate,String isGraduate,
							  HttpServletResponse response) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("1".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
		}else if("2".equals(baseRange)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else if("3".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getHospitalChart6Detail2(paramMap);
		String fileName = "理论考核详情.xls";
		String titles[] = {
				"ORG_NAME:培训基地",
				"DOCTOR_NAME:学员姓名",
				"SESSION_NUMBER:培训年级",
				"TRAINING_SPE_NAME:培训专业",
				"SCH_DEPT_NAME:轮转科室",
				"SCH_START_DATE:轮转开始时间",
				"SCH_END_DATE:轮转结束时间",
				"TEACHER_USER_NAME:带教老师",
				"EXAM_NUM:总考试次数",
				"EXAM_PASS:第几次合格"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/detail7Main")
	public String detail7Main(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		SysOrg currentOrg = orgBiz.readSysOrg(currentOrgFlow);
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		Map<String,String> orgMap = new HashMap<>();
		orgMap.put(currentOrg.getOrgFlow(),currentOrg.getOrgName());
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgList){
				orgMap.put(jointOrg.getJointOrgFlow(),jointOrg.getJointOrgName());
			}
		}
		model.addAttribute("currentOrgFlow",currentOrgFlow);
		model.addAttribute("orgMap",orgMap);//培训基地


		Map<String,String> deptMap = new HashMap<>();
		Map<String,String> speMap = new HashMap<>();

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("dateMonth",monthDate);
		List<String> allOrgFlow = new ArrayList<>();
		for(String s:orgMap.keySet()){
			allOrgFlow.add(s);
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		List<SysMonthlyDocCycleInfo>  sysMonthlyDocCycleInfos= monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);
		if(!sysMonthlyDocCycleInfos.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfos){
				String schDeptFlow = sysMonthlyDocCycleInfo.getSchDeptFlow();
				String trainingSpeId = sysMonthlyDocCycleInfo.getTrainingSpeId();
				if(StringUtil.isNotBlank(schDeptFlow)){
					if(deptMap.get(schDeptFlow)==null){
						deptMap.put(schDeptFlow,sysMonthlyDocCycleInfo.getSchDeptName());
					}
				}
				if(StringUtil.isNotBlank(trainingSpeId)){
					if(speMap.get(trainingSpeId)==null){
						speMap.put(trainingSpeId,sysMonthlyDocCycleInfo.getTrainingSpeName());
					}
				}
			}
		}
		model.addAttribute("deptMap",deptMap);//轮转科室
		model.addAttribute("speMap",speMap);//专业


		return "jsres/hospital/monthlyReport/detail7Main";
	}

	@RequestMapping("/detail7")
	public String detail7(String orgFlow,String schDeptFlow,String sessionNumber,String trainingSpeId,String monthDate,Integer currentPage,
						  String[] doctorTypeIds,Model model,HttpServletRequest request){
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);


		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorTypeIdList",doctorTypeIdList);
		paramMap.put("monthDate",monthDate);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getHospitalChart7Detail(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/hospital/monthlyReport/detail7";
	}

	@RequestMapping("/exportDetail7")
	public void exportDetail7(String orgFlow,String schDeptFlow,String sessionNumber,String trainingSpeId,String monthDate,
							  String[] doctorTypeIds,HttpServletResponse response) throws Exception {
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("doctorTypeIdList",doctorTypeIdList);
		paramMap.put("monthDate",monthDate);
		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getHospitalChart7Detail(paramMap);
		String fileName = "轮转数据登记详情.xls";
		String titles[] = {
				"ORG_NAME:培训基地",
				"DOCTOR_NAME:学员姓名",
				"SESSION_NUMBER:年级",
				"TRAINING_SPE_NAME:专业",
				"SCH_DEPT_NAME:轮转科室",
				"SCH_START_DATE:轮转开始时间",
				"SCH_END_DATE:轮转结束时间",
				"TEACHER_USER_NAME:带教老师",
				"HEAD_USER_NAME:科主任",
				"ACTIVITY_NUM:教学活动",
				"REC_NUM:填写数",
				"AUDIT_NUM:已审核数",
				"SCH_STATUS_NAME:轮转状态"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/detail8Main")
	public String detail8Main(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		SysOrg currentOrg = orgBiz.readSysOrg(currentOrgFlow);
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		Map<String,String> orgMap = new HashMap<>();
		orgMap.put(currentOrg.getOrgFlow(),currentOrg.getOrgName());
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgList){
				orgMap.put(jointOrg.getJointOrgFlow(),jointOrg.getJointOrgName());
			}
		}
		model.addAttribute("currentOrgFlow",currentOrgFlow);
		model.addAttribute("orgMap",orgMap);//培训基地

		Map<String,String> deptMap = new HashMap<>();
		Map<String,String> speMap = new HashMap<>();

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("dateMonth",monthDate);
		List<String> allOrgFlow = new ArrayList<>();
		for(String s:orgMap.keySet()){
			allOrgFlow.add(s);
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		List<SysMonthlyDocCycleInfo>  sysMonthlyDocCycleInfos= monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);
		if(!sysMonthlyDocCycleInfos.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfos){
				String schDeptFlow = sysMonthlyDocCycleInfo.getSchDeptFlow();
				String trainingSpeId = sysMonthlyDocCycleInfo.getTrainingSpeId();
				if(StringUtil.isNotBlank(schDeptFlow)){
					if(deptMap.get(schDeptFlow)==null){
						deptMap.put(schDeptFlow,sysMonthlyDocCycleInfo.getSchDeptName());
					}
				}
				if(StringUtil.isNotBlank(trainingSpeId)){
					if(speMap.get(trainingSpeId)==null){
						speMap.put(trainingSpeId,sysMonthlyDocCycleInfo.getTrainingSpeName());
					}
				}
			}
		}
		model.addAttribute("deptMap",deptMap);//轮转科室
		model.addAttribute("speMap",speMap);//专业

		return "jsres/hospital/monthlyReport/detail8Main";
	}

	@RequestMapping("/detail8")
	public String detail8(String orgFlow,String schDeptFlow,String monthDate,Integer currentPage,
						  String teacherUserName,Model model,HttpServletRequest request){
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("monthDate",monthDate);
		paramMap.put("teacherUserName",teacherUserName);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = monthlyReportExtMapper.getHospitalChart8Detail(paramMap);
		if(!resultMapList.isEmpty()){
			for(Map<String,Object> map:resultMapList){
				BigDecimal recNum = (BigDecimal)map.get("REC_NUM");
				BigDecimal auditNum = (BigDecimal)map.get("AUDIT_NUM");
				DecimalFormat df=new DecimalFormat("0.00");
				Double percent=null;
				Double a = recNum.doubleValue();
				Double b = auditNum.doubleValue();
				percent= a==0.0?0:b/a;
				map.put("percent",df.format(percent*100)+"%");
			}
		}
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/hospital/monthlyReport/detail8";
	}

	@RequestMapping("/exportDetail8")
	public void exportDetail8(String orgFlow,String schDeptFlow,String monthDate,
						  String teacherUserName,Model model,HttpServletResponse response) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("monthDate",monthDate);
		paramMap.put("teacherUserName",teacherUserName);
		List<Map<String,Object>> resultMapList = monthlyReportExtMapper.getHospitalChart8Detail(paramMap);
		if(!resultMapList.isEmpty()){
			for(Map<String,Object> map:resultMapList){
				BigDecimal recNum = (BigDecimal)map.get("REC_NUM");
				BigDecimal auditNum = (BigDecimal)map.get("AUDIT_NUM");
				DecimalFormat df=new DecimalFormat("0.00");
				Double percent=null;
				Double a = recNum.doubleValue();
				Double b = auditNum.doubleValue();
				percent= a==0.0?0:b/a;
				map.put("percent",df.format(percent*100)+"%");
			}
		}
		String fileName = "轮转数据审核详情.xls";
		String titles[] = {
				"ORG_NAME:培训基地",
				"SCH_DEPT_NAME:科室",
				"TEACHER_USER_NAME:带教老师",
				"USER_CODE:用户名",
				"TEACHER_SCORE:评价均分",
				"ACTIVITY_NUM:教学活动开展数",
				"DOCTOR_NUM:带教人数",
				"REC_NUM:需审核数",
				"AUDIT_NUM:已审核数",
				"percent:审核比例"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/detail9Main")
	public String detail9Main(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		SysOrg currentOrg = orgBiz.readSysOrg(currentOrgFlow);
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		Map<String,String> orgMap = new HashMap<>();
		orgMap.put(currentOrg.getOrgFlow(),currentOrg.getOrgName());
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgList){
				orgMap.put(jointOrg.getJointOrgFlow(),jointOrg.getJointOrgName());
			}
		}
		model.addAttribute("currentOrgFlow",currentOrgFlow);
		model.addAttribute("orgMap",orgMap);//培训基地

		Map<String,String> deptMap = new HashMap<>();

		List<String> allOrgFlow = new ArrayList<>();
		for(String s:orgMap.keySet()){
			allOrgFlow.add(s);
		}
		List<SysMonthlyActivityInfo>  sysMonthlyActivityInfos= monthlyReportBiz.getSysMonthlyActivityInfo(monthDate,allOrgFlow);
		if(!sysMonthlyActivityInfos.isEmpty()){
			for(SysMonthlyActivityInfo sysMonthlyActivityInfo:sysMonthlyActivityInfos){
				String deptFlow = sysMonthlyActivityInfo.getDeptFlow();
				if(StringUtil.isNotBlank(deptFlow)){
					if(deptMap.get(deptFlow)==null){
						deptMap.put(deptFlow,sysMonthlyActivityInfo.getDeptName());
					}
				}
			}
		}
		model.addAttribute("deptMap",deptMap);//轮转科室
		return "jsres/hospital/monthlyReport/detail9Main";
	}

	@RequestMapping("/detail9")
	public String detail9(String orgFlow,String deptFlow,String monthDate,Integer currentPage,Model model,HttpServletRequest request){
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		paramMap.put("deptFlow",deptFlow);
		paramMap.put("monthDate",monthDate);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = monthlyReportExtMapper.getHospitalChart9Detail(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/hospital/monthlyReport/detail9";
	}

	@RequestMapping("/exportDetail9")
	public void exportDetail9(String orgFlow,String deptFlow,String monthDate,HttpServletResponse response) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		SysUser user = GlobalContext.getCurrentUser();
		String currentOrgFlow = user.getOrgFlow();//本基地orgFlow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currentOrgFlow);//所有协同基地
		List<String> allOrgFlow = new ArrayList<>();
		if("all".equals(orgFlow)){
			allOrgFlow.add(currentOrgFlow);
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:jointOrgList){
					String jointOrgFlow = jointOrg.getJointOrgFlow();
					allOrgFlow.add(jointOrgFlow);
				}
			}
		}else {
			allOrgFlow.add(orgFlow);
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		paramMap.put("deptFlow",deptFlow);
		paramMap.put("monthDate",monthDate);
		List<Map<String,Object>> resultMapList = monthlyReportExtMapper.getHospitalChart9Detail(paramMap);
		String fileName = "教学活动统计详情.xls";
		String titles[] = {
				"ORG_NAME:培训基地",
				"DEPT_NAME:科室",
				"ACTIVTIY_NUM:活动开展数",
				"JOIN_NUM:活动参加数",
				"XJK:小讲课",
				"SWBL:死亡病例讨论",
				"JXCF:教学查房",
				"YNBL:疑难病例讨论",
				"WZBL:危重症病例讨论"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");

	}
}
