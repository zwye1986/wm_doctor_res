package com.pinde.sci.ctrl.jsres;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResMonthlyReportBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Joq
 *
 */
@Controller
@RequestMapping("/jsres/monthlyReportCity")
public class JsResMonthlyReportCityController extends GeneralController {
	@Autowired
	private IJsResMonthlyReportBiz monthlyReportBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private MonthlyReportExtMapper monthlyReportExtMapper;

	/**
	 * 市局角色
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
		return "jsres/city/monthlyReport/monthlyReportMain";
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

		return "jsres/city/monthlyReport/content0";
	}

	@RequestMapping("/content1")
	public String content1(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		return "jsres/city/monthlyReport/content1";
	}

	@RequestMapping("/chart123")//图一图二图三
	public String chart123(String monthDate,String isGraduate,Model model){
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorTypeId(isGraduate);
		search.setDoctorStatusId("20");
		search.setChangeTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> allOrgFlow = new ArrayList<>();
		List<SysOrg> orgs=new ArrayList<>();
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgCityId(org.getOrgCityId());
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		orgs = orgBiz.searchOrg(sysorg);
		if(!orgs.isEmpty()){
			for(SysOrg sysOrg:orgs){
				allOrgFlow.add(sysOrg.getOrgFlow());
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

		Map<String,Integer> orgMap = new HashMap<>();//图一数据
		Map<String,Integer> sessionNumberMap = new HashMap<>();//图二数据
		Map<String,Integer> speMap = new HashMap<>();//图三数据

		if(!doctorInfoListFinal.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:doctorInfoListFinal){
				String orgName = sysMonthlyDoctorInfo.getOrgName();
				if(orgMap.get(orgName)==null){
					orgMap.put(orgName,1);
				}else {
					int sum = orgMap.get(orgName);
					orgMap.put(orgName,sum+1);
				}

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
			}
			model.addAttribute("orgMap",orgMap);
			model.addAttribute("sessionNumberMap",sessionNumberMap);
			model.addAttribute("speMap",speMap);
		}

		return "jsres/city/monthlyReport/chart123";
	}

	@RequestMapping("/chart4")//图四
	public String chart4(String monthDate,String isGraduate,Model model){

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<SysMonthlyChangeInfo> sysMonthlyChangeInfoList = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> allOrgFlow = new ArrayList<>();
		List<SysOrg> orgs=new ArrayList<>();
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgCityId(org.getOrgCityId());
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		orgs = orgBiz.searchOrg(sysorg);
		if(!orgs.isEmpty()){
			for(SysOrg sysOrg:orgs){
				allOrgFlow.add(sysOrg.getOrgFlow());
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
		changeTypeMap.put("基地变更",0);
		changeTypeMap.put("延期",0);
		changeTypeMap.put("退培",0);

		if(!SysMonthlyChangeInfoFinal.isEmpty()){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:SysMonthlyChangeInfoFinal){
				if("SpeChange".equals(sysMonthlyChangeInfo.getChangeTypeId())){
					int sum = changeTypeMap.get("专业变更");
					changeTypeMap.put("专业变更",sum+1);
				}
				if("BaseChange".equals(sysMonthlyChangeInfo.getChangeTypeId())){
					int sum = changeTypeMap.get("基地变更");
					changeTypeMap.put("基地变更",sum+1);
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
		return "jsres/city/monthlyReport/chart4";
	}

	@RequestMapping("/chart5")//图五
	public String chart5(String monthDate,String isGraduate,Model model){

		Map<String,Object> paramMap = new HashMap<>();

		List<String> dateMonthList = new ArrayList<>();
		dateMonthList.add(monthDate);
		paramMap.put("dateMonthList",dateMonthList);
		paramMap.put("doctorTypeId",isGraduate);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> allOrgFlow = new ArrayList<>();
		List<SysOrg> orgs = new ArrayList<>();
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgCityId(org.getOrgCityId());
		sysorg.setOrgProvId("320000");
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		orgs = orgBiz.searchOrg(sysorg);
		if(!orgs.isEmpty()){
			for(SysOrg sysOrg:orgs){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getNoAppNum(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/city/monthlyReport/chart5";
	}

	@RequestMapping("/chart6")//图六
	public String chart6(String monthDate,String isGraduate,Model model){

		Map<String,Object> paramMap = new HashMap<>();

		paramMap.put("dateMonth1",monthDate);
		paramMap.put("dateMonth2",getLastMonths(-2));
		paramMap.put("dateMonth3",getLastMonths(-3));
		paramMap.put("doctorTypeId",isGraduate);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> allOrgFlow = new ArrayList<>();
		List<SysOrg> orgs = new ArrayList<>();
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgCityId(org.getOrgCityId());
		sysorg.setOrgProvId("320000");
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		orgs = orgBiz.searchOrg(sysorg);
		if(!orgs.isEmpty()){
			for(SysOrg sysOrg:orgs){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getNoAppNum3(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/city/monthlyReport/chart6";
	}

	@RequestMapping("/chart7")//图七
	public String chart7(String monthDate,String isGraduate,Model model){

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> allOrgFlow = new ArrayList<>();
		List<SysOrg> orgs = new ArrayList<>();
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgCityId(org.getOrgCityId());
		sysorg.setOrgProvId("320000");
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		orgs = orgBiz.searchOrg(sysorg);
		if(!orgs.isEmpty()){
			for(SysOrg sysOrg:orgs){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);

		Map<String,Integer> registerMap = new HashMap<>();

		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorTypeId(isGraduate);
		search.setDoctorStatusId("20");
		search.setChangeTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		List<SysMonthlyDoctorInfo> doctorInfoListFinal = new ArrayList<>();
		if(!sysMonthlyDoctorInfoList.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getOrgFlow()) && (!allOrgFlow.isEmpty()) &&
						allOrgFlow.contains(sysMonthlyDoctorInfo.getOrgFlow())){
					doctorInfoListFinal.add(sysMonthlyDoctorInfo);
				}
			}
		}

		Map<String,Integer> orgMap = new HashMap<>();//图一数据

		if(!doctorInfoListFinal.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:doctorInfoListFinal){
				String orgFlow = sysMonthlyDoctorInfo.getOrgFlow();
				if(orgMap.get(orgFlow)==null){
					orgMap.put(orgFlow,1);
				}else {
					int sum = orgMap.get(orgFlow);
					orgMap.put(orgFlow,sum+1);
				}
			}
		}

		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				String orgFlow = sysMonthlyDocCycleInfo.getOrgFlow();
				int registerNum = sysMonthlyDocCycleInfo.getRecNum();
				if(registerMap.get(orgFlow)==null){
					registerMap.put(orgFlow,registerNum);
				}else {
					registerMap.put(orgFlow,registerMap.get(orgFlow)+registerNum);
				}
			}
		}

		if(!registerMap.isEmpty()){//计算每个基地的平均值
			for(String key:registerMap.keySet()){
				int value = registerMap.get(key);
				if(orgMap.get(key)==null){
					value = 0;
				}else if(orgMap.get(key)!=0){
					value = value/(orgMap.get(key));
				}
				registerMap.put(key,value);
			}
		}

		model.addAttribute("orgs",orgs);
		model.addAttribute("registerMap",registerMap);

		return "jsres/city/monthlyReport/chart7";
	}

	@RequestMapping("/chart8")//图八
	public String chart8(String monthDate,String isGraduate,Model model){

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> allOrgFlow = new ArrayList<>();
		List<SysOrg> orgs = new ArrayList<>();
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		sysorg.setOrgCityId(org.getOrgCityId());
		sysorg.setOrgProvId("320000");
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		orgs = orgBiz.searchOrg(sysorg);
		if(!orgs.isEmpty()){
			for(SysOrg sysOrg:orgs){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);

		Map<String,Integer> registerMap = new HashMap<>();
		Map<String,Integer> auditingMap = new HashMap<>();
		int registerSum = 0;
		int auditingSum = 0;

		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				String orgFlow = sysMonthlyDocCycleInfo.getOrgFlow();
				int registerNum = sysMonthlyDocCycleInfo.getRecNum();
				registerSum+=registerNum;
				if(registerMap.get(orgFlow)==null){
					registerMap.put(orgFlow,registerNum);
				}else {
					registerMap.put(orgFlow,registerMap.get(orgFlow)+registerNum);
				}

				int auditingNum = sysMonthlyDocCycleInfo.getAuditNum();
				auditingSum+=auditingNum;
				if(auditingMap.get(orgFlow)==null){
					auditingMap.put(orgFlow,auditingNum);
				}else {
					auditingMap.put(orgFlow,auditingMap.get(orgFlow)+auditingNum);
				}
			}
		}

		model.addAttribute("registerMap",registerMap);
		model.addAttribute("auditingMap",auditingMap);
		model.addAttribute("registerSum",registerSum);
		model.addAttribute("auditingSum",auditingSum);
		model.addAttribute("orgs",orgs);
		return "jsres/city/monthlyReport/chart8";
	}

}
