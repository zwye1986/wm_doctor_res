package com.pinde.sci.ctrl.jsres;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResMonthlyReportBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.res.ResDocTypeEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Joq
 *
 */
@Controller
@RequestMapping("/jsres/monthlyReportGlobal")
public class JsResMonthlyReportGlobalController extends GeneralController {
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
	 * 省厅角色
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
		return "jsres/global/monthlyReport/monthlyReportMain";
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

		return "jsres/global/monthlyReport/content0";
	}

	@RequestMapping("/content1")
	public String content1(String baseRange,String monthDate,Model model){
		model.addAttribute("baseRange",baseRange);
		model.addAttribute("monthDate",monthDate);
		return "jsres/global/monthlyReport/content1";
	}

	@RequestMapping("/chart123")//图一图二图三
	public String chart123(String baseRange,String monthDate,String isGraduate,Model model){
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorTypeId(isGraduate);
		search.setDoctorStatusId("20");
		search.setChangeTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		List<String> allOrgFlow = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryOrgList = orgBiz.searchOrg(searchOrg);//所有国家基地
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchJointOrgAll();//所有协同基地
		Map<String,String> jointOrgMap = new HashMap<>();
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		if("1".equals(baseRange)){

		}else if("2".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg resJointOrg:jointOrgList){
					allOrgFlow.add(resJointOrg.getJointOrgFlow());
					if(StringUtil.isNotBlank(resJointOrg.getJointOrgFlow())){
						jointOrgMap.put(resJointOrg.getJointOrgFlow(),resJointOrg.getOrgName());
					}
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

		Map<String,Integer> orgMap = new HashMap<>();//图一数据
		Map<String,Integer> sessionNumberMap = new HashMap<>();//图二数据
		Map<String,Integer> speMap = new HashMap<>();//图三数据

		if(!doctorInfoListFinal.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:doctorInfoListFinal){
				String orgName = sysMonthlyDoctorInfo.getOrgName();
				String orgFlow = sysMonthlyDoctorInfo.getOrgFlow();
				if(jointOrgMap.get(orgFlow)!=null){
					if(orgMap.get(jointOrgMap.get(orgFlow))==null){
						orgMap.put(jointOrgMap.get(orgFlow),1);
					}else {
						int sum = orgMap.get(jointOrgMap.get(orgFlow));
						orgMap.put(jointOrgMap.get(orgFlow),sum+1);
					}
				}else {
					if(orgMap.get(orgName)==null){
						orgMap.put(orgName,1);
					}else {
						int sum = orgMap.get(orgName);
						orgMap.put(orgName,sum+1);
					}
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
		return "jsres/global/monthlyReport/chart123";
	}

	@RequestMapping("/chart4")//图四
	public String chart4(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<SysMonthlyChangeInfo> sysMonthlyChangeInfoList = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);

		List<String> allOrgFlow = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryOrgList = orgBiz.searchOrg(searchOrg);//所有国家基地
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchJointOrgAll();//所有协同基地
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		if("1".equals(baseRange)){

		}else if("2".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg resJointOrg:jointOrgList){
					allOrgFlow.add(resJointOrg.getJointOrgFlow());
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
		return "jsres/global/monthlyReport/chart4";
	}

	@RequestMapping("/chart5")//图五
	public String chart5(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();

		List<String> dateMonthList = new ArrayList<>();
		dateMonthList.add(monthDate);
		paramMap.put("dateMonthList",dateMonthList);
		paramMap.put("doctorTypeId",isGraduate);

		List<Map<String,String>> resultMapList = new ArrayList<>();
		List<String> allOrgFlow = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryOrgList = orgBiz.searchOrg(searchOrg);//所有国家基地
		List<String> countryOrgFlowList = new ArrayList<>();//所有国家基地Flow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchJointOrgAll();//所有协同基地
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				allOrgFlow.add(sysOrg.getOrgFlow());
				countryOrgFlowList.add(sysOrg.getOrgFlow());
				Map<String,String> map = new HashMap<>();
				map.put("ORG_FLOW",sysOrg.getOrgFlow());
				map.put("ORG_NAME",sysOrg.getOrgName());
				map.put("NUM","0");
				resultMapList.add(map);
			}
		}
		if("1".equals(baseRange)){

		}else if("2".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg resJointOrg:jointOrgList){
					allOrgFlow.add(resJointOrg.getJointOrgFlow());
				}
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		Map<String,String> jointOrgMap = new HashMap<>();//协同基地对应国家基地MAP
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg resJointOrg:jointOrgList){
				String jointFlow = resJointOrg.getJointOrgFlow();
				String orgFlow = resJointOrg.getOrgFlow();
				jointOrgMap.put(jointFlow,orgFlow);
			}
		}
		List<Map<String,String>> resultMapList0 = monthlyReportExtMapper.getNoAppNum(paramMap);

		if(resultMapList0!=null&&resultMapList0.size()>0){
			for(Map<String,String> map:resultMapList0){
				String orgFlow = map.get("ORG_FLOW");
				String num = String.valueOf(map.get("NUM"));
				if(!countryOrgFlowList.contains(orgFlow)){
					String mainFlow = jointOrgMap.get(orgFlow);
					if(StringUtil.isNotBlank(mainFlow)){
						for(Map<String,String> map2:resultMapList){
							if(map2.get("ORG_FLOW").equals(mainFlow)){
								map2.put("NUM",String.valueOf(Integer.parseInt(map2.get("NUM"))+Integer.parseInt(num)));
							}
						}
					}
				}else{
					for(Map<String,String> map2:resultMapList){
						if(map2.get("ORG_FLOW").equals(orgFlow)){
							map2.put("NUM",String.valueOf(Integer.parseInt(map2.get("NUM"))+Integer.parseInt(num)));
						}
					}
				}
			}
		}
		for(int i=0;i<resultMapList.size();i++){
			if(resultMapList.get(i).get("NUM").equals("0")){
				resultMapList.remove(i);
				i--;
			}
		}
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/global/monthlyReport/chart5";
	}


	@RequestMapping("/chart6")//图六
	public String chart6(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();

		paramMap.put("doctorTypeId",isGraduate);
		paramMap.put("dateMonth1",monthDate);
		paramMap.put("dateMonth2",getLastMonths(-2));
		paramMap.put("dateMonth3",getLastMonths(-3));

		List<Map<String,String>> resultMapList = new ArrayList<>();
		List<String> allOrgFlow = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryOrgList = orgBiz.searchOrg(searchOrg);//所有国家基地
		List<String> countryOrgFlowList = new ArrayList<>();//所有国家基地Flow
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchJointOrgAll();//所有协同基地
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				allOrgFlow.add(sysOrg.getOrgFlow());
				countryOrgFlowList.add(sysOrg.getOrgFlow());
				Map<String,String> map = new HashMap<>();
				map.put("ORG_FLOW",sysOrg.getOrgFlow());
				map.put("ORG_NAME",sysOrg.getOrgName());
				map.put("NUM","0");
				resultMapList.add(map);
			}
		}
		if("1".equals(baseRange)){

		}else if("2".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg resJointOrg:jointOrgList){
					allOrgFlow.add(resJointOrg.getJointOrgFlow());
				}
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);
		Map<String,String> jointOrgMap = new HashMap<>();//协同基地对应国家基地MAP
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg resJointOrg:jointOrgList){
				String jointFlow = resJointOrg.getJointOrgFlow();
				String orgFlow = resJointOrg.getOrgFlow();
				jointOrgMap.put(jointFlow,orgFlow);
			}
		}
		List<Map<String,String>> resultMapList0 = monthlyReportExtMapper.getNoAppNum3(paramMap);
		if(resultMapList0!=null&&resultMapList0.size()>0){
			for(Map<String,String> map:resultMapList0){
				String orgFlow = map.get("ORG_FLOW");
				String num = String.valueOf(map.get("NUM"));
				if(!countryOrgFlowList.contains(orgFlow)){
					String mainFlow = jointOrgMap.get(orgFlow);
					if(StringUtil.isNotBlank(mainFlow)){
						for(Map<String,String> map2:resultMapList){
							if(map2.get("ORG_FLOW").equals(mainFlow)){
								map2.put("NUM",String.valueOf(Integer.parseInt(map2.get("NUM"))+Integer.parseInt(num)));
							}
						}
					}
				}else{
					for(Map<String,String> map2:resultMapList){
						if(map2.get("ORG_FLOW").equals(orgFlow)){
							map2.put("NUM",String.valueOf(Integer.parseInt(map2.get("NUM"))+Integer.parseInt(num)));
						}
					}
				}
			}
		}
		for(int i=0;i<resultMapList.size();i++){
			if(resultMapList.get(i).get("NUM").equals("0")){
				resultMapList.remove(i);
				i--;
			}
		}
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/global/monthlyReport/chart6";
	}

	@RequestMapping("/chart7")//图七
	public String chart7(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<String> allOrgFlow = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		searchOrg.setOrgProvId("320000");
		List<SysOrg> countryOrgList = orgBiz.searchOrg(searchOrg);//所有国家基地

		List<ResJointOrg> jointOrgList = jointOrgBiz.searchJointOrgAll();//所有协同基地
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		if("1".equals(baseRange)){

		}else if("2".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg resJointOrg:jointOrgList){
					allOrgFlow.add(resJointOrg.getJointOrgFlow());
				}
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);
//		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = new ArrayList<>();

		Map<String,Integer> registerMap = new HashMap<>();
		Map<String,Integer> auditingMap = new HashMap<>();
		int registerSum = 0;
		int auditingSum = 0;

		List<String> countryOrgFlowList = new ArrayList<>();//国家基地ORGFLOWLIST
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				registerMap.put(sysOrg.getOrgFlow(),0);
				auditingMap.put(sysOrg.getOrgFlow(),0);
				countryOrgFlowList.add(sysOrg.getOrgFlow());
			}
		}
		Map<String,String> jointOrgMap = new HashMap<>();//协同基地对应国家基地MAP
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg resJointOrg:jointOrgList){
				String jointFlow = resJointOrg.getJointOrgFlow();
				String orgFlow = resJointOrg.getOrgFlow();
				jointOrgMap.put(jointFlow,orgFlow);
			}
		}
		if(sysMonthlyDocCycleInfoList!=null&&sysMonthlyDocCycleInfoList.size()>0){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList) {
				String orgFlow = sysMonthlyDocCycleInfo.getOrgFlow();
				Integer registerNum = sysMonthlyDocCycleInfo.getRecNum();
				if(registerNum!=null){
					if (jointOrgMap.get(orgFlow) == null && registerMap.get(orgFlow)!=null) {
						registerMap.put(orgFlow, registerMap.get(orgFlow) + registerNum);
						registerSum += registerNum;
					} else if(jointOrgMap.get(orgFlow) != null){
						String countryOrgFlow = jointOrgMap.get(orgFlow);
						if (StringUtil.isNotBlank(countryOrgFlow) && countryOrgFlowList.contains(countryOrgFlow)) {
							registerMap.put(countryOrgFlow, registerMap.get(countryOrgFlow) + registerNum);
							registerSum += registerNum;
						}
					}
				}

				Integer auditingNum = sysMonthlyDocCycleInfo.getAuditNum();
				if(auditingNum!=null){
					if (jointOrgMap.get(orgFlow) == null && registerMap.get(orgFlow)!=null) {
						auditingMap.put(orgFlow, auditingMap.get(orgFlow) + auditingNum);
						auditingSum += auditingNum;
					} else if(jointOrgMap.get(orgFlow) != null){
						String countryOrgFlow = jointOrgMap.get(orgFlow);
						if (StringUtil.isNotBlank(countryOrgFlow) && countryOrgFlowList.contains(countryOrgFlow)) {
							auditingMap.put(countryOrgFlow, auditingMap.get(countryOrgFlow) + auditingNum);
							auditingSum += auditingNum;
						}
					}
				}
			}
		}
		model.addAttribute("registerMap",registerMap);
		model.addAttribute("auditingMap",auditingMap);
		model.addAttribute("registerSum",registerSum);
		model.addAttribute("auditingSum",auditingSum);
		model.addAttribute("countryOrgList",countryOrgList);
		return "jsres/global/monthlyReport/chart7";
	}

	@RequestMapping("/chart8")//图八
	public String chart8(String baseRange,String monthDate,String isGraduate,Model model){

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<String> allOrgFlow = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		searchOrg.setOrgProvId("320000");
		List<SysOrg> countryOrgList = orgBiz.searchOrg(searchOrg);//所有国家基地

		List<ResJointOrg> jointOrgList = jointOrgBiz.searchJointOrgAll();//所有协同基地
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		if("1".equals(baseRange)){

		}else if("2".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg resJointOrg:jointOrgList){
					allOrgFlow.add(resJointOrg.getJointOrgFlow());
				}
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);

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

		Map<String,Integer> orgMap = new HashMap<>();
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

		Map<String,Integer> registerMap = new HashMap<>();

		List<String> countryOrgFlowList = new ArrayList<>();//国家基地ORGFLOWLIST
		Map<String,String> countryOrgNameMap = new HashMap<>();
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				registerMap.put(sysOrg.getOrgFlow(),0);
				countryOrgFlowList.add(sysOrg.getOrgFlow());
				countryOrgNameMap.put(sysOrg.getOrgFlow(),sysOrg.getOrgName());
			}
		}
		Map<String,String> jointOrgMap = new HashMap<>();//协同基地对应国家基地MAP
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg resJointOrg:jointOrgList){
				String jointFlow = resJointOrg.getJointOrgFlow();
				String orgFlow = resJointOrg.getOrgFlow();
				jointOrgMap.put(jointFlow,orgFlow);
			}
		}

		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				String orgFlow = sysMonthlyDocCycleInfo.getOrgFlow();
				Integer registerNum = sysMonthlyDocCycleInfo.getRecNum();
				if(registerNum!=null){
					if(jointOrgMap.get(orgFlow) == null && registerMap.get(orgFlow)!=null){
						registerMap.put(orgFlow,registerMap.get(orgFlow)+registerNum);
					}else if(jointOrgMap.get(orgFlow) != null){
						String countryOrgFlow = jointOrgMap.get(orgFlow);
						if(StringUtil.isNotBlank(countryOrgFlow)&&countryOrgFlowList.contains(countryOrgFlow)){
							registerMap.put(countryOrgFlow,registerMap.get(countryOrgFlow)+registerNum);
						}
					}
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

		Map<String, Integer> registerMapTree = new TreeMap<String, Integer>(registerMap);
		// 降序比较器
		Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
		};
		// map转换成list进行排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(registerMapTree.entrySet());
		// 排序
		Collections.sort(list,valueComparator);
		List<Map.Entry<String, Integer>> resultMapOrgList = list.subList(0, 10);
		model.addAttribute("resultMapOrgList",resultMapOrgList);
		model.addAttribute("countryOrgNameMap",countryOrgNameMap);
		return "jsres/global/monthlyReport/chart8";
	}

	@RequestMapping("/chart9")//图九
	public String chart9(String baseRange,String monthDate,String isGraduate,Model model){

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<String> allOrgFlow = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		searchOrg.setOrgProvId("320000");
		List<SysOrg> countryOrgList = orgBiz.searchOrg(searchOrg);//所有国家基地

		List<ResJointOrg> jointOrgList = jointOrgBiz.searchJointOrgAll();//所有协同基地
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		if("1".equals(baseRange)){

		}else if("2".equals(baseRange)){
			if(!jointOrgList.isEmpty()){
				for(ResJointOrg resJointOrg:jointOrgList){
					allOrgFlow.add(resJointOrg.getJointOrgFlow());
				}
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);

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

		Map<String,Integer> orgMap = new HashMap<>();
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

		Map<String,Integer> registerMap = new HashMap<>();

		List<String> countryOrgFlowList = new ArrayList<>();//国家基地ORGFLOWLIST
		Map<String,String> countryOrgNameMap = new HashMap<>();
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				registerMap.put(sysOrg.getOrgFlow(),0);
				countryOrgFlowList.add(sysOrg.getOrgFlow());
				countryOrgNameMap.put(sysOrg.getOrgFlow(),sysOrg.getOrgName());
			}
		}
		Map<String,String> jointOrgMap = new HashMap<>();//协同基地对应国家基地MAP
		if(!jointOrgList.isEmpty()){
			for(ResJointOrg resJointOrg:jointOrgList){
				String jointFlow = resJointOrg.getJointOrgFlow();
				String orgFlow = resJointOrg.getOrgFlow();
				jointOrgMap.put(jointFlow,orgFlow);
			}
		}

		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				String orgFlow = sysMonthlyDocCycleInfo.getOrgFlow();
				Integer registerNum = sysMonthlyDocCycleInfo.getRecNum();
				if(registerNum!=null) {
					if (jointOrgMap.get(orgFlow) == null && registerMap.get(orgFlow) != null) {
						registerMap.put(orgFlow, registerMap.get(orgFlow) + registerNum);
					} else if (jointOrgMap.get(orgFlow) != null) {
						String countryOrgFlow = jointOrgMap.get(orgFlow);
						if (StringUtil.isNotBlank(countryOrgFlow) && countryOrgFlowList.contains(countryOrgFlow)) {
							registerMap.put(countryOrgFlow, registerMap.get(countryOrgFlow) + registerNum);
						}
					}
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

		Map<String, Integer> registerMapTree = new TreeMap<String, Integer>(registerMap);
		// 升序比较器
		Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return o1.getValue()-o2.getValue();
			}
		};
		// map转换成list进行排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(registerMapTree.entrySet());
		// 排序
		Collections.sort(list,valueComparator);
		List<Map.Entry<String, Integer>> resultMapOrgList = list.subList(0, 10);
		model.addAttribute("resultMapOrgList",resultMapOrgList);
		model.addAttribute("countryOrgNameMap",countryOrgNameMap);
		return "jsres/global/monthlyReport/chart9";
	}

	@RequestMapping("/export4")//导出图四
	public void export4(String baseRange, String monthDate, String isGraduate, HttpServletResponse response) throws DocumentException, IOException {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
		SysMonthlyDoctorDetailInfo search2 = new SysMonthlyDoctorDetailInfo();
		search2.setDateMonth(monthDate);
		List<SysMonthlyChangeInfo> SysMonthlyChangeInfoFinal = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoFinal = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);
		List<SysMonthlyDoctorDetailInfo> sysMonthlyDoctorDetailInfoList = monthlyReportBiz.getMonthlyDoctorDetailInfo(search2);
		List<SysUser> userList = monthlyReportExtMapper.getUserList(paramMap);
		List<ResDoctor> doctorList = monthlyReportExtMapper.getDoctorList(paramMap);
		List<ResDoctorRecruit> recruitList = monthlyReportExtMapper.getRecruitList(paramMap);

		List<SysMonthlyChangeInfo> speChangeList = new ArrayList<>();
		List<SysMonthlyChangeInfo> baseChangeInList = new ArrayList<>();
		List<SysMonthlyChangeInfo> baseChangeOutList = new ArrayList<>();
		List<SysMonthlyReturnDelayInfo> delayList = new ArrayList<>();
		List<SysMonthlyReturnDelayInfo> returnTrainingList = new ArrayList<>();
		Map<String,SysMonthlyDoctorDetailInfo> detailInfoMap = new HashMap<>();
		Map<String,SysUser> userMap = new HashMap<>();
		Map<String,ResDoctor> doctorMap = new HashMap<>();
		Map<String,ResDoctorRecruit> recruitMap = new HashMap<>();

		if(SysMonthlyChangeInfoFinal!=null&&SysMonthlyChangeInfoFinal.size()>0){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:SysMonthlyChangeInfoFinal){
				String doctorFlow = sysMonthlyChangeInfo.getDoctorFlow();
				String recruitFlow = sysMonthlyChangeInfo.getRecruitFlow();
				if(StringUtil.isNotBlank(doctorFlow)){
					if(sysMonthlyDoctorDetailInfoList!=null&&sysMonthlyDoctorDetailInfoList.size()>0){
						for(SysMonthlyDoctorDetailInfo doctorDetailInfo:sysMonthlyDoctorDetailInfoList){
							if(doctorFlow.equals(doctorDetailInfo.getDoctorFlow())){
								detailInfoMap.put(doctorFlow,doctorDetailInfo);
								break;
							}
						}
					}
					if(userList!=null&&userList.size()>0){
						for(SysUser sysUser:userList){
							if(doctorFlow.equals(sysUser.getUserFlow())){
								userMap.put(doctorFlow,sysUser);
								break;
							}
						}
					}
					if(doctorList!=null&&doctorList.size()>0){
						for(ResDoctor resDoctor:doctorList){
							if(doctorFlow.equals(resDoctor.getDoctorFlow())){
								doctorMap.put(doctorFlow,resDoctor);
								break;
							}
						}
					}
					if(recruitList!=null&&recruitList.size()>0){
						for(ResDoctorRecruit resDoctorRecruit:recruitList){
							if(StringUtil.isNotBlank(recruitFlow)&&recruitFlow.equals(resDoctorRecruit.getRecruitFlow())){
								recruitMap.put(doctorFlow,resDoctorRecruit);
								break;
							}
						}
					}
				}
			}
		}

		if(sysMonthlyReturnDelayInfoFinal!=null&&sysMonthlyReturnDelayInfoFinal.size()>0){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoFinal){
				String doctorFlow = sysMonthlyReturnDelayInfo.getDoctorFlow();
				String recruitFlow = sysMonthlyReturnDelayInfo.getRecruitFlow();
				if(StringUtil.isNotBlank(doctorFlow)){
					if(sysMonthlyDoctorDetailInfoList!=null&&sysMonthlyDoctorDetailInfoList.size()>0){
						for(SysMonthlyDoctorDetailInfo doctorDetailInfo:sysMonthlyDoctorDetailInfoList){
							if(doctorFlow.equals(doctorDetailInfo.getDoctorFlow())){
								detailInfoMap.put(doctorFlow,doctorDetailInfo);
								break;
							}
						}
					}
					if(userList!=null&&userList.size()>0){
						for(SysUser sysUser:userList){
							if(doctorFlow.equals(sysUser.getUserFlow())){
								userMap.put(doctorFlow,sysUser);
								break;
							}
						}
					}
					if(doctorList!=null&&doctorList.size()>0){
						for(ResDoctor resDoctor:doctorList){
							if(doctorFlow.equals(resDoctor.getDoctorFlow())){
								doctorMap.put(doctorFlow,resDoctor);
								break;
							}
						}
					}
					if(recruitList!=null&&recruitList.size()>0){
						for(ResDoctorRecruit resDoctorRecruit:recruitList){
							if(StringUtil.isNotBlank(recruitFlow)&&recruitFlow.equals(resDoctorRecruit.getRecruitFlow())){
								recruitMap.put(doctorFlow,resDoctorRecruit);
								break;
							}
						}
					}

				}
			}
		}

		if(SysMonthlyChangeInfoFinal!=null&&SysMonthlyChangeInfoFinal.size()>0){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:SysMonthlyChangeInfoFinal){
				if("SpeChange".equals(sysMonthlyChangeInfo.getChangeTypeId())){
					speChangeList.add(sysMonthlyChangeInfo);
				}
				if("BaseChange".equals(sysMonthlyChangeInfo.getChangeTypeId())&&StringUtil.isNotBlank(sysMonthlyChangeInfo.getOrgFlow()) ){
					baseChangeInList.add(sysMonthlyChangeInfo);
				}
				if("BaseChange".equals(sysMonthlyChangeInfo.getChangeTypeId())&&StringUtil.isNotBlank(sysMonthlyChangeInfo.getHistoryOrgFlow()) ){
					baseChangeOutList.add(sysMonthlyChangeInfo);
				}
			}
		}
		if(sysMonthlyReturnDelayInfoFinal!=null&&sysMonthlyReturnDelayInfoFinal.size()>0){
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
				String age = "";
				if (userMap.get(currentDoctorFlow) != null && StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				if (sysUser != null && doctor != null && recruit != null) {
					String CretType = "";
					String area = "";
					String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
					if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
						CretType = "居民身份证";
						area = "中国大陆";
					}
//					else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//						CretType = "军队证件";
//						area = "中国大陆";
//					}
					else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
						CretType = "护照";
						area = "";
					} else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
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

					String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7);
					//解析xml
					UserResumeExtInfoForm userResumeExt = new UserResumeExtInfoForm();
					if (detailInfo != null && StringUtil.isNotBlank(detailInfo.getUserResume())) {
						userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);
					}

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
					String yyjb = "";
					String yydc = "";
					String hospitalCateName = "";
					String hospitalAttrName = "";
					if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
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
						if ("1".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "一级";
							yydc = "未定等";
						}
						if ("2".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "一级";
							yydc = "未定等";
						}
						if ("3".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "未定等";
						}
						if ("4".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "甲等";
						}
						if ("5".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "乙等";
						}
						if ("6".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "未定级";
							yydc = "未定等";
						}
						if ("1".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "省、自治区、直辖市属";
						}
						if ("2".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "省辖市（地区、州、盟、直辖市区）属";
						}
						if ("3".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "县级市（省辖市区）属";
						}
						if ("1".equals(userResumeExt.getMedicalHeaithOrgId())) {
							hospitalCateName = userResumeExt.getHospitalCategoryName();
						}
						if ("3".equals(userResumeExt.getMedicalHeaithOrgId())) {
							hospitalCateName = userResumeExt.getBasicHealthOrgName();
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
						isGeneralOrderOrientationTrainee = "否";
					}
					//规培年限
					String trainYear = "";
					if (StringUtil.isNotBlank(recruit.getTrainYear())) {
						trainYear = recruit.getTrainYear();
					}
					switch (trainYear) {
						case "OneYear": {
							trainYear = "一年";
							break;
						}
						case "TwoYear": {
							trainYear = "两年";
							break;
						}
						case "ThreeYear": {
							trainYear = "三年";
							break;
						}
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
				String age = "";
				if (userMap.get(currentDoctorFlow) != null && StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				if (sysUser != null && doctor != null && recruit != null) {
					String CretType = "";
					String area = "";
					String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
					if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
						CretType = "居民身份证";
						area = "中国大陆";
					}
//					else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//						CretType = "军队证件";
//						area = "中国大陆";
//					}
					else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
						CretType = "护照";
						area = "";
					} else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
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

					String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7);
					//解析xml
					UserResumeExtInfoForm userResumeExt = new UserResumeExtInfoForm();
					if (detailInfo != null && StringUtil.isNotBlank(detailInfo.getUserResume())) {
						userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);
					}

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
					String yyjb = "";
					String yydc = "";
					String hospitalCateName = "";
					String hospitalAttrName = "";
					if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
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
						if ("1".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "一级";
							yydc = "未定等";
						}
						if ("2".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "一级";
							yydc = "未定等";
						}
						if ("3".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "未定等";
						}
						if ("4".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "甲等";
						}
						if ("5".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "乙等";
						}
						if ("6".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "未定级";
							yydc = "未定等";
						}
						if ("1".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "省、自治区、直辖市属";
						}
						if ("2".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "省辖市（地区、州、盟、直辖市区）属";
						}
						if ("3".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "县级市（省辖市区）属";
						}
						if ("1".equals(userResumeExt.getMedicalHeaithOrgId())) {
							hospitalCateName = userResumeExt.getHospitalCategoryName();
						}
						if ("3".equals(userResumeExt.getMedicalHeaithOrgId())) {
							hospitalCateName = userResumeExt.getBasicHealthOrgName();
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
						isGeneralOrderOrientationTrainee = "否";
					}
					//规培年限
					String trainYear = "";
					if (StringUtil.isNotBlank(recruit.getTrainYear())) {
						trainYear = recruit.getTrainYear();
					}
					switch (trainYear) {
						case "OneYear": {
							trainYear = "一年";
							break;
						}
						case "TwoYear": {
							trainYear = "两年";
							break;
						}
						case "ThreeYear": {
							trainYear = "三年";
							break;
						}
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
				String age = "";
				if (userMap.get(currentDoctorFlow) != null && StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				if (sysUser != null && doctor != null && recruit != null) {
					String CretType = "";
					String area = "";
					String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
					// 枚举直拿值 无需加 分支
					if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
						CretType = "居民身份证";
						area = "中国大陆";
					}
//					else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//						CretType = "军队证件";
//						area = "中国大陆";
//					}
					else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
						CretType = "护照";
						area = "";
					} else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
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

					String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7);
					//解析xml
					UserResumeExtInfoForm userResumeExt = new UserResumeExtInfoForm();
					if (detailInfo != null && StringUtil.isNotBlank(detailInfo.getUserResume())) {
						userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);
					}

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
					String yyjb = "";
					String yydc = "";
					String hospitalCateName = "";
					String hospitalAttrName = "";
					if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
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
						if ("1".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "一级";
							yydc = "未定等";
						}
						if ("2".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "一级";
							yydc = "未定等";
						}
						if ("3".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "未定等";
						}
						if ("4".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "甲等";
						}
						if ("5".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "乙等";
						}
						if ("6".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "未定级";
							yydc = "未定等";
						}
						if ("1".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "省、自治区、直辖市属";
						}
						if ("2".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "省辖市（地区、州、盟、直辖市区）属";
						}
						if ("3".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "县级市（省辖市区）属";
						}
						if ("1".equals(userResumeExt.getMedicalHeaithOrgId())) {
							hospitalCateName = userResumeExt.getHospitalCategoryName();
						}
						if ("3".equals(userResumeExt.getMedicalHeaithOrgId())) {
							hospitalCateName = userResumeExt.getBasicHealthOrgName();
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
						isGeneralOrderOrientationTrainee = "否";
					}
					//规培年限
					String trainYear = "";
					if (StringUtil.isNotBlank(recruit.getTrainYear())) {
						trainYear = recruit.getTrainYear();
					}
					switch (trainYear) {
						case "OneYear": {
							trainYear = "一年";
							break;
						}
						case "TwoYear": {
							trainYear = "两年";
							break;
						}
						case "ThreeYear": {
							trainYear = "三年";
							break;
						}
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
				if (jointOrgs != null && jointOrgs.size()>0) {
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

				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				if(sysUser!=null&&doctor!=null&&recruit!=null){
					String CretType = "";
					String area = "";
					String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
					if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
						CretType = "居民身份证";
						area="中国大陆";
					}
//					else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//						CretType = "军队证件";
//						area="中国大陆";
//					}
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
					UserResumeExtInfoForm userResumeExt = new UserResumeExtInfoForm();
					if(detailInfo!=null&&StringUtil.isNotBlank(detailInfo.getUserResume()))	{
						userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);
					}
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
				String age = "";
				if (userMap.get(currentDoctorFlow) != null && StringUtil.isNotBlank(userMap.get(currentDoctorFlow).getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userMap.get(currentDoctorFlow).getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = userMap.get(currentDoctorFlow);
				ResDoctor doctor = doctorMap.get(currentDoctorFlow);
				ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				if (sysUser != null && doctor != null && recruit != null) {
					String CretType = "";
					String area = "";
					String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
					if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
						CretType = "居民身份证";
						area = "中国大陆";
					}
//					else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//						CretType = "军队证件";
//						area = "中国大陆";
//					}
					else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
						CretType = "护照";
						area = "";
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

					String recruitDate = recruit.getRecruitDate().substring(0, 4) + "-" + recruit.getRecruitDate().substring(5, 7);
					//解析xml
					UserResumeExtInfoForm userResumeExt = new UserResumeExtInfoForm();
					if (detailInfo != null && StringUtil.isNotBlank(detailInfo.getUserResume())) {
						userResumeExt = userResumeBiz.converyToJavaBean(detailInfo.getUserResume(), UserResumeExtInfoForm.class);
					}

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
					String yyjb = "";
					String yydc = "";
					String hospitalCateName = "";
					String hospitalAttrName = "";
					if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
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
						if ("1".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "一级";
							yydc = "未定等";
						}
						if ("2".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "一级";
							yydc = "未定等";
						}
						if ("3".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "未定等";
						}
						if ("4".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "甲等";
						}
						if ("5".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "三级";
							yydc = "乙等";
						}
						if ("6".equals(userResumeExt.getBaseAttributeId())) {
							yyjb = "未定级";
							yydc = "未定等";
						}
						if ("1".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "省、自治区、直辖市属";
						}
						if ("2".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "省辖市（地区、州、盟、直辖市区）属";
						}
						if ("3".equals(userResumeExt.getHospitalAttrId())) {
							hospitalAttrName = "县级市（省辖市区）属";
						}
						if ("1".equals(userResumeExt.getMedicalHeaithOrgId())) {
							hospitalCateName = userResumeExt.getHospitalCategoryName();
						}
						if ("3".equals(userResumeExt.getMedicalHeaithOrgId())) {
							hospitalCateName = userResumeExt.getBasicHealthOrgName();
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
						isGeneralOrderOrientationTrainee = "否";
					}
					//规培年限
					String trainYear = "";
					if (StringUtil.isNotBlank(recruit.getTrainYear())) {
						trainYear = recruit.getTrainYear();
					}
					switch (trainYear) {
						case "OneYear": {
							trainYear = "一年";
							break;
						}
						case "TwoYear": {
							trainYear = "两年";
							break;
						}
						case "ThreeYear": {
							trainYear = "三年";
							break;
						}
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
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

}
