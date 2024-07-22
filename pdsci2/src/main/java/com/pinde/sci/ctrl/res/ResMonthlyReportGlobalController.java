package com.pinde.sci.ctrl.res;

import com.pinde.core.page.Page;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResMonthlyReportBiz;
import com.pinde.sci.biz.res.IResSchProcessExpressBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.dao.jsres.SchdualTaskMapper;
import com.pinde.sci.dao.res.ResMonthlyReportExtMapper;
import com.pinde.sci.enums.res.AfterRecTypeEnum;
import com.pinde.sci.enums.res.RecStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Joq
 *
 */
@Controller
@RequestMapping("/res/monthlyReportGlobal")
public class ResMonthlyReportGlobalController extends GeneralController {
	@Autowired
	private IResMonthlyReportBiz monthlyReportBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ResMonthlyReportExtMapper monthlyReportExtMapper;
	@Autowired
	private IResJointOrgBiz resJointOrgBiz;
	@Autowired
	private MonthlyReportExtMapper monthlyReportExtMapper2;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
    private ResMonthlyReportGlobalControllerClass resMonthlyReportGlobalControllerClass;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private SchdualTaskMapper schdualTaskMapper;

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
		return "res/platform/monthlyReport/monthlyReportMain";
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

		return "res/platform/monthlyReport/content0";
	}

	@RequestMapping("/content1")
	public String content1(String baseRange,String monthDate,Model model){
		model.addAttribute("baseRange",baseRange);
		model.addAttribute("monthDate",monthDate);
		return "res/platform/monthlyReport/content1";
	}

	@RequestMapping("/chart123")//图一图二图三
	public String chart123(String baseRange,String monthDate,String isGraduate,Model model){
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorTypeId(isGraduate);
		search.setDoctorStatusId("Training");
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search);

		List<String> allOrgFlow = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg> countryOrgList = orgBiz.searchOrg(searchOrg);//所有国家基地
		List<String> countryOrgFlowList = new ArrayList<>();//国家基地ORGFLOWLIST
		Map<String,String> countryOrgNameMap = new HashMap<>();
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchJointOrgAll();//所有协同基地
		if(!countryOrgList.isEmpty()){
			for(SysOrg sysOrg:countryOrgList){
				allOrgFlow.add(sysOrg.getOrgFlow());
				countryOrgFlowList.add(sysOrg.getOrgFlow());
				countryOrgNameMap.put(sysOrg.getOrgFlow(),sysOrg.getOrgName());
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

		List<SysMonthlyDoctorInfo> doctorInfoListFinal = new ArrayList<>();//三图公用数据
		if(!sysMonthlyDoctorInfoList.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				if(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getOrgFlow()) && (!allOrgFlow.isEmpty()) &&
						allOrgFlow.contains(sysMonthlyDoctorInfo.getOrgFlow())){
					doctorInfoListFinal.add(sysMonthlyDoctorInfo);
				}
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

		Map<String,Integer> orgMap = new HashMap<>();//图一数据
		Map<String,Integer> sessionNumberMap = new HashMap<>();//图二数据
		Map<String,Integer> speMap = new HashMap<>();//图三数据

		if(!doctorInfoListFinal.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:doctorInfoListFinal){
				String orgFlow = sysMonthlyDoctorInfo.getOrgFlow();
				if(countryOrgFlowList.contains(orgFlow)){
					if(orgMap.get(orgFlow)==null){
						orgMap.put(orgFlow,1);
					}else {
						int sum = orgMap.get(orgFlow);
						orgMap.put(orgFlow,sum+1);
					}
				}else {
					String countryOrgFlow = jointOrgMap.get(orgFlow);
					if(StringUtil.isNotBlank(countryOrgFlow)&&countryOrgFlowList.contains(countryOrgFlow)){
						if(orgMap.get(countryOrgFlow)==null){
							orgMap.put(countryOrgFlow,1);
						}else {
							int sum = orgMap.get(countryOrgFlow);
							orgMap.put(countryOrgFlow,sum+1);
						}
					}
				}

				String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
				if(sessionNumberMap.get(sessionNumber)==null){
					sessionNumberMap.put(sessionNumber,1);
				}else {
					int sum = sessionNumberMap.get(sessionNumber);
					sessionNumberMap.put(sessionNumber,sum+1);
				}

				String speName = sysMonthlyDoctorInfo.getTrainingSpeName();
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
			model.addAttribute("countryOrgNameMap",countryOrgNameMap);
		}
		return "res/platform/monthlyReport/chart123";
	}

	@RequestMapping("/chart4")//图四
	public String chart4(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("trainingTypeId",isGraduate);

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
		changeTypeMap.put("延期",0);
		changeTypeMap.put("退培",0);

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
		return "res/platform/monthlyReport/chart4";
	}

	@RequestMapping("/chart5")//图五
	public String chart5(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();

		List<String> dateMonthList = new ArrayList<>();
		dateMonthList.add(monthDate);
		paramMap.put("dateMonthList",dateMonthList);
		paramMap.put("doctorTypeId",isGraduate);

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
		paramMap.put("allOrgFlow",allOrgFlow);

		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getNoAppNum(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "res/platform/monthlyReport/chart5";
	}


	@RequestMapping("/chart6")//图六
	public String chart6(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();

		paramMap.put("doctorTypeId",isGraduate);
		paramMap.put("dateMonth1",monthDate);
		paramMap.put("dateMonth2",getLastMonths(-2));
		paramMap.put("dateMonth3",getLastMonths(-3));


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
		paramMap.put("allOrgFlow",allOrgFlow);

		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getNoAppNum3(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "res/platform/monthlyReport/chart6";
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

		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				String orgFlow = sysMonthlyDocCycleInfo.getOrgFlow();
				int registerNum = sysMonthlyDocCycleInfo.getRecNum();
				if(countryOrgFlowList.contains(orgFlow)){
					registerMap.put(orgFlow,registerMap.get(orgFlow)+registerNum);
					registerSum+=registerNum;
				}else {
					String countryOrgFlow = jointOrgMap.get(orgFlow);
					if(StringUtil.isNotBlank(countryOrgFlow)&&countryOrgFlowList.contains(countryOrgFlow)){
						registerMap.put(countryOrgFlow,registerMap.get(countryOrgFlow)+registerNum);
						registerSum+=registerNum;
					}
				}

				int auditingNum = sysMonthlyDocCycleInfo.getAuditNum();
				if(countryOrgFlowList.contains(orgFlow)){
					auditingMap.put(orgFlow,auditingMap.get(orgFlow)+auditingNum);
					auditingSum+=auditingNum;
				}else {
					String countryOrgFlow = jointOrgMap.get(orgFlow);
					if(StringUtil.isNotBlank(countryOrgFlow)&&countryOrgFlowList.contains(countryOrgFlow)){
						auditingMap.put(countryOrgFlow,auditingMap.get(countryOrgFlow)+auditingNum);
						auditingSum+=auditingNum;
					}
				}
			}
		}

		model.addAttribute("registerMap",registerMap);
		model.addAttribute("auditingMap",auditingMap);
		model.addAttribute("registerSum",registerSum);
		model.addAttribute("auditingSum",auditingSum);
		model.addAttribute("countryOrgList",countryOrgList);
		return "res/platform/monthlyReport/chart7";
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
		search.setDoctorStatusId("Training");
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search);

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
				int registerNum = sysMonthlyDocCycleInfo.getRecNum();
				if(countryOrgFlowList.contains(orgFlow)){
					registerMap.put(orgFlow,registerMap.get(orgFlow)+registerNum);
				}else {
					String countryOrgFlow = jointOrgMap.get(orgFlow);
					if(StringUtil.isNotBlank(countryOrgFlow)&&countryOrgFlowList.contains(countryOrgFlow)){
						registerMap.put(countryOrgFlow,registerMap.get(countryOrgFlow)+registerNum);
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
		return "res/platform/monthlyReport/chart8";
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
		search.setDoctorStatusId("Training");
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search);

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
				int registerNum = sysMonthlyDocCycleInfo.getRecNum();
				if(countryOrgFlowList.contains(orgFlow)){
					registerMap.put(orgFlow,registerMap.get(orgFlow)+registerNum);
				}else {
					String countryOrgFlow = jointOrgMap.get(orgFlow);
					if(StringUtil.isNotBlank(countryOrgFlow)&&countryOrgFlowList.contains(countryOrgFlow)){
						registerMap.put(countryOrgFlow,registerMap.get(countryOrgFlow)+registerNum);
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
		return "res/platform/monthlyReport/chart9";
	}
	@RequestMapping("/doctorOutOfficeInfo")
	public String PageToDoctorOutOfficeInfo(Model model,String monthDate,String isContain,String orgFlow){
		try {
			String role=getRoleFlag();//角色
			if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
				model.addAttribute("university","university");
			}
			return "res/platform/monthlyReport/doctorOutOfficeInfo";
		}catch (RuntimeException e){
			e.printStackTrace();
			return "res/platform/monthlyReport/doctorOutOfficeInfo";
		}

	}
	/**
	 * 初始化学员出科情况统计yuh（省、市）new
	 * @return
	 */
	@RequestMapping("/initDoctorOutOfficeInfo")
	@ResponseBody
	public List doctorOutOfficeInfoNew(String sortFlag,String monthDate,String isContain,String[] datas){
		SysUser user = GlobalContext.getCurrentUser();
		String role=getRoleFlag();
		List<DoctorOutOfficeParamPO> orgInfoList = new ArrayList<>();
		Map<String,Object> paramMap = new HashMap<>();

		try {
			if(!"".equals(role)){

				paramMap.put("monthDate",monthDate);
				if(null==isContain){
					isContain="N";
				}else{
					isContain="Y";
				}
				paramMap.put("isContain",isContain);
				SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
				String currentOrgName = currOrg.getOrgName();

				if(Arrays.asList(datas).contains("Graduate") && Arrays.asList(datas).contains("NotGraduate")){
					paramMap.put("notGraduate","Y");
					paramMap.put("graduate","Y");
				}else{
					if(Arrays.asList(datas).contains("Graduate")){
						if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
							paramMap.put("graduate","Y");
						}else{
							paramMap.put("notGraduate","N");
							paramMap.put("graduate","Y");
						}
					}else{
						paramMap.put("notGraduate","Y");
						paramMap.put("graduate","N");
					}
				}
				if(role.equals(GlobalConstant.USER_LIST_GLOBAL)){
					paramMap.put("province",currOrg.getOrgProvId());
					paramMap.put("roleFlag",GlobalConstant.USER_LIST_GLOBAL);
				}
				if(role.equals(GlobalConstant.USER_LIST_CHARGE)){
					paramMap.put("city",currOrg.getOrgCityId());
					paramMap.put("roleFlag",GlobalConstant.USER_LIST_CHARGE);
				}
				if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
					String currentOrgId="";
					Boolean gaoxiaoFlg = false;
					List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
					if (sendSchools != null && sendSchools.size() > 0) {
						for (SysDict dict : sendSchools) {
							if (currentOrgName.equals(dict.getDictName())) {
								currentOrgId=dict.getDictFlow();
								gaoxiaoFlg = true;
							}
						}
					}
					if(gaoxiaoFlg){
						paramMap.put("university",currentOrgId);
						paramMap.put("roleFlag",GlobalConstant.USER_LIST_UNIVERSITY);
					}else{
						throw new RuntimeException("请联系管理员“派送学校”是否有“"+currentOrgName+"”高校");
					}
				}
				orgInfoList =  schdualTaskMapper.selectPCUDoctorOutOffice(paramMap);
				orgInfoList=	outOfficeListSort(sortFlag,orgInfoList);/*排序*/
			}
			return orgInfoList;
		}catch (RuntimeException e){
			List<Map<String,String>> list=new ArrayList<>();
			Map<String,String> map=new HashMap<>();
			if(e.getMessage()==null){
				map.put("error","null");
			}else{
				map.put("error",e.getMessage());
			}
			list.add(map);
			return list;
		}
	}
	/**
	 * 初始化学员出科情况统计yuh（省、市）old
	 * @return
	 */
	/*@RequestMapping("/initDoctorOutOfficeInfo")
	@ResponseBody*/
	public List doctorOutOfficeInfo(String sortFlag,String monthDate,String isContain,String[] datas/*String isDoctor,String isMaster,*/){
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = new ArrayList<>();
		List<DoctorOutOfficeParamPO> doctorOutOfficeParamPOList=new ArrayList<>();

		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
		String docTypeList[]=convert(datas);//人员类型

		try {
			String role=getRoleFlag();
			if(!"".equals(role)){


			//高校角色
			if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
				List li = resMonthlyReportGlobalControllerClass.doctorOutOfficeInfo_University(sortFlag,monthDate,isContain, orgs ,doctorOutOfficeParamPOList,docTypeList,role);
				return li;
			}

			SysOrg searchOrg=new SysOrg();
			SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
			searchOrg.setOrgProvId(currOrg.getOrgProvId());
			if(role.equals(GlobalConstant.USER_LIST_CHARGE)){
				searchOrg.setOrgCityId(currOrg.getOrgCityId());
			}

			searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
			searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
             //---start--
			List<SysOrg> countryOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
			for(int i =0;i<countryOrgs.size();i++){
				countryOrgs.get(i).setParentOrgFlow("");
				countryOrgs.get(i).setNo(/*(i+1)+""*/countryOrgs.get(i).getOrgCode());
				List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					for(int j=0;j<jointOrgList.size();j++){
						jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
						jointOrgList.get(j).setNo(/*(i+1)*/countryOrgs.get(i).getOrgCode()+"-"+(j+1));
						orgs.add(jointOrgList.get(j));
					}
				}
				orgs.add(countryOrgs.get(i));
			}
			//对象转化
			for(int i=0;i<orgs.size();i++){
				DoctorOutOfficeParamPO dooppo=new DoctorOutOfficeParamPO();
				BeanUtils.copyProperties(orgs.get(i),dooppo);
				doctorOutOfficeParamPOList.add(dooppo);
			}
//包含协同
if("isContain".equals(isContain)){
	for(DoctorOutOfficeParamPO doctorOutOfficeParamPO:doctorOutOfficeParamPOList){
		Integer outOfficeSum=0;
		Integer outOfficeActualSum=0;
		List<String> doctorFlowLIst=new ArrayList<>(); //存取每月实际出科人的userflow
		Map<String,Object> outOfficeDoctorMap=new HashMap<>();
		String orgFlow = doctorOutOfficeParamPO.getOrgFlow();
		outOfficeDoctorMap.put("orgFlow",orgFlow);
		outOfficeDoctorMap.put("docTypeList",docTypeList);
		List<Map<String,Object>> outOfficeDoctorInfoList= monthlyReportExtMapper2.findOutOfficeDoctorInfo(outOfficeDoctorMap);
		String cksh="N";
		JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_cksh");
		if(cfg!=null)
		{
			cksh=cfg.getCfgValue();
		}
		for(Map<String,Object> mapInfo: outOfficeDoctorInfoList){
			Map<String,String> doctorMap=new HashMap<>();
			doctorMap.put("doctorFlow",(String) mapInfo.get("doctorFlow"));
			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(doctorMap);
			Integer outOfficeSumByOnePersion= getMonthOutOfficeSum(monthDate,arrResultList);//1或者0
			Integer outOfficeActualSumByOnePersion = getMonthActualOutOfficeSum(cksh,monthDate,arrResultList);//1或者0
			outOfficeSum= outOfficeSum+ outOfficeSumByOnePersion;
			outOfficeActualSum= outOfficeActualSum+ outOfficeActualSumByOnePersion;
			if(1==outOfficeActualSumByOnePersion){
				doctorFlowLIst.add((String) mapInfo.get("doctorFlow"));
			}
		}
		/*outOfficeDoctorMap.put("doctorFlowList",doctorFlowLIst);
		List<Map<String, Object>> FinishSumAndTotalSumList=new ArrayList<>();
		if(doctorFlowLIst.size()>0){
			FinishSumAndTotalSumList= monthlyReportExtMapper2.findOutOfficeFinishSumAndTotalSum(outOfficeDoctorMap);
		}
		Integer[] finishAndtotal = getFinishSumAndTotalSum( FinishSumAndTotalSumList);
		Integer finishSum =finishAndtotal[0];Integer totalSum= finishAndtotal[1];*/

		doctorOutOfficeParamPO.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数
		doctorOutOfficeParamPO.setMonthActualOutOfficeSum(outOfficeActualSum);//本月实际出科人数
		Integer notOutOfficeSum= outOfficeSum-outOfficeActualSum;
		doctorOutOfficeParamPO.setMonthNotOutOfficeSum(notOutOfficeSum);//本月未出科人数

		doctorOutOfficeParamPO.setDoctorFlows(doctorFlowLIst);
		/*String outOfficeExceptionRate="0%";
		if(0!=outOfficeSum){
			outOfficeExceptionRate = numberFormat.format((float) notOutOfficeSum / (float) outOfficeSum * 100)+"%";
		}
		doctorOutOfficeParamPO.setOutOfficeExceptionRate(outOfficeExceptionRate);//出科异常比例
		String outOfficeFinishRate="0%";
		if(0!=totalSum){
			outOfficeFinishRate= numberFormat.format((float) finishSum / (float) totalSum * 100)+"%";
		}
		doctorOutOfficeParamPO.setOutOfficeDataFinishRate(outOfficeFinishRate);//出科学员数据平均完成率*/
	}
	for(DoctorOutOfficeParamPO dooppoA:doctorOutOfficeParamPOList){
		String orgFlow =dooppoA.getOrgFlow();
		Integer outOfficeSum=dooppoA.getMonthOutOfficeSum();
		Integer outActualOfficeSum=dooppoA.getMonthActualOutOfficeSum();
		Integer outNotOfficeSum=dooppoA.getMonthNotOutOfficeSum();
		List<String> doctorFlowsA =   dooppoA.getDoctorFlows(); //
		for(DoctorOutOfficeParamPO dooppoB:doctorOutOfficeParamPOList){
			if(orgFlow.equals(dooppoB.getParentOrgFlow())){
				  outOfficeSum =outOfficeSum+ dooppoB.getMonthOutOfficeSum();
				  outActualOfficeSum = outActualOfficeSum + dooppoB.getMonthActualOutOfficeSum();
				  outNotOfficeSum = outNotOfficeSum + dooppoB.getMonthNotOutOfficeSum();
				        List<String> doctorFlowsB=  dooppoB.getDoctorFlows();
				   doctorFlowsA.addAll(doctorFlowsB);
			}
		}
		dooppoA.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数(国家基地+协同)
		dooppoA.setMonthActualOutOfficeSum(outActualOfficeSum);//本月实际出科人数（国家+协同）
		dooppoA.setMonthNotOutOfficeSum(outNotOfficeSum);//本月未出科人数（国家+协同）
		String outOfficeExceptionRate="0%";
		if(0!=outOfficeSum){
			outOfficeExceptionRate = numberFormat.format((float) outNotOfficeSum / (float) outOfficeSum * 100)+"%";
		}
		dooppoA.setOutOfficeExceptionRate(outOfficeExceptionRate);//出科异常比例（国家+协同）
		Map<String,Object> outMap=new HashMap<>();
		outMap.put("orgFlow",orgFlow);
		outMap.put("docTypeList",docTypeList);
		outMap.put("doctorFlowList",doctorFlowsA);
		List<Map<String, Object>> FinishSumAndTotalSumList=new ArrayList<>();
		if(doctorFlowsA.size()>0){
			FinishSumAndTotalSumList= monthlyReportExtMapper2.findOutOfficeFinishSumAndTotalSum(outMap);
		}
		Integer[] finishAndtotal = getFinishSumAndTotalSum( FinishSumAndTotalSumList);
		Integer finishSum =finishAndtotal[0];Integer totalSum= finishAndtotal[1];

		String outOfficeFinishRate="0%";
		if(0!=totalSum){
			outOfficeFinishRate= numberFormat.format((float) finishSum / (float) totalSum * 100)+"%";
		}
		dooppoA.setOutOfficeDataFinishRate(outOfficeFinishRate);//出科学员数据平均完成率（国家+协同）
	}

//不包含协同
}else{
				/*List<SysOrg> countryOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
				for(int i =0;i<countryOrgs.size();i++){
					countryOrgs.get(i).setParentOrgFlow("");
					countryOrgs.get(i).setNo((i+1)+"");
					List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
					if(jointOrgList!=null&&!jointOrgList.isEmpty()){
						for(int j=0;j<jointOrgList.size();j++){
							jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
							jointOrgList.get(j).setNo((i+1)+"-"+(j+1));
							orgs.add(jointOrgList.get(j));
						}
					}
					orgs.add(countryOrgs.get(i));
				}
				//对象转化
				for(int i=0;i<orgs.size();i++){
					DoctorOutOfficeParamPO dooppo=new DoctorOutOfficeParamPO();
					BeanUtils.copyProperties(orgs.get(i),dooppo);
					doctorOutOfficeParamPOList.add(dooppo);
				}*/
				for(DoctorOutOfficeParamPO doctorOutOfficeParamPO:doctorOutOfficeParamPOList){
					Integer outOfficeSum=0;
					Integer outOfficeActualSum=0;
					List<String> doctorFlowLIst=new ArrayList<>(); //装每月实际出科人的userflow
					Map<String,Object> outOfficeDoctorMap=new HashMap<>();
					String orgFlow = doctorOutOfficeParamPO.getOrgFlow();
					outOfficeDoctorMap.put("orgFlow",orgFlow);
					outOfficeDoctorMap.put("docTypeList",docTypeList);
					List<Map<String,Object>> outOfficeDoctorInfoList= monthlyReportExtMapper2.findOutOfficeDoctorInfo(outOfficeDoctorMap);
					String cksh="N";
					JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_cksh");
					if(cfg!=null)
					{
						cksh=cfg.getCfgValue();
					}
					for(Map<String,Object> mapInfo: outOfficeDoctorInfoList){
						Map<String,String> doctorMap=new HashMap<>();
						doctorMap.put("doctorFlow",(String) mapInfo.get("doctorFlow"));
						List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(doctorMap);
						Integer outOfficeSumByOnePersion= getMonthOutOfficeSum(monthDate,arrResultList);//1或者0
						Integer outOfficeActualSumByOnePersion = getMonthActualOutOfficeSum(cksh,monthDate,arrResultList);//1或者0
						outOfficeSum= outOfficeSum+ outOfficeSumByOnePersion;
						outOfficeActualSum= outOfficeActualSum+ outOfficeActualSumByOnePersion;
						if(1==outOfficeActualSumByOnePersion){
							doctorFlowLIst.add((String) mapInfo.get("doctorFlow"));
						}
					}
					outOfficeDoctorMap.put("doctorFlowList",doctorFlowLIst);
					List<Map<String, Object>> FinishSumAndTotalSumList=new ArrayList<>();
					if(doctorFlowLIst.size()>0){
						 FinishSumAndTotalSumList= monthlyReportExtMapper2.findOutOfficeFinishSumAndTotalSum(outOfficeDoctorMap);
					}
					Integer[] finishAndtotal = getFinishSumAndTotalSum( FinishSumAndTotalSumList);
					Integer finishSum =finishAndtotal[0];Integer totalSum= finishAndtotal[1];

					doctorOutOfficeParamPO.setMonthOutOfficeSum(outOfficeSum);//本月应出科人数
					doctorOutOfficeParamPO.setMonthActualOutOfficeSum(outOfficeActualSum);//本月实际出科人数
					Integer notOutOfficeSum= outOfficeSum-outOfficeActualSum;
					doctorOutOfficeParamPO.setMonthNotOutOfficeSum(notOutOfficeSum);//本月未出科人数

					String outOfficeExceptionRate="0%";
					if(0!=outOfficeSum){
						 outOfficeExceptionRate = numberFormat.format((float) notOutOfficeSum / (float) outOfficeSum * 100)+"%";
					}
					doctorOutOfficeParamPO.setOutOfficeExceptionRate(outOfficeExceptionRate);//出科异常比例
					String outOfficeFinishRate="0%";
					if(0!=totalSum){
                        outOfficeFinishRate= numberFormat.format((float) finishSum / (float) totalSum * 100)+"%";
                    }
					doctorOutOfficeParamPO.setOutOfficeDataFinishRate(outOfficeFinishRate);//出科学员数据平均完成率
				}


}
			doctorOutOfficeParamPOList=	outOfficeListSort(sortFlag,doctorOutOfficeParamPOList);/*排序*/
}
			return doctorOutOfficeParamPOList;
		}catch (RuntimeException e){
			e.printStackTrace();
			List<Map<String,String>> list1=new ArrayList<>();
			Map<String,String> map=new HashMap<>();
			if(e.getMessage()==null){
				map.put("error","null");
			}else{
				map.put("error",e.getMessage());
			}
			list1.add(map);
			return list1;
		}

	}
	//学员出科排序
	public List<DoctorOutOfficeParamPO> outOfficeListSort(String value,List<DoctorOutOfficeParamPO> list){
		if(!"".equals(value)){
			List<DoctorOutOfficeParamPO> firstList=new ArrayList<>();
			List<DoctorOutOfficeParamPO> secondList=new ArrayList<>();
			for(DoctorOutOfficeParamPO li:list){
				if("".equals(li.getParentOrgFlow()) || null==li.getParentOrgFlow()){
					firstList.add(li);
				}
				if(!"".equals(li.getParentOrgFlow()) && null!=li.getParentOrgFlow()){
					secondList.add(li);
				}
			}
			if("outOfficeDesc".equals(value)){
				Collections.sort(firstList, new Comparator<DoctorOutOfficeParamPO>() {
					public int compare(DoctorOutOfficeParamPO o1, DoctorOutOfficeParamPO o2) {
						Integer MonthNotOutOfficeSum1=0;
						Integer MonthNotOutOfficeSum2=0;
						if(null!=o1.getMonthNotOutOfficeSum()&& !"".equals(o1.getMonthNotOutOfficeSum())){
							MonthNotOutOfficeSum1 =o1.getMonthNotOutOfficeSum();
						}
						if(null!=o2.getMonthNotOutOfficeSum()&& !"".equals(o2.getMonthNotOutOfficeSum())){
							MonthNotOutOfficeSum2 =o2.getMonthNotOutOfficeSum();
						}
						//降序
						return MonthNotOutOfficeSum2.compareTo(MonthNotOutOfficeSum1);
					}
				});
				list = listResetNo(firstList,secondList);//重新设置NO序号

			}else if("outOfficeAsc".equals(value)){
				Collections.sort(firstList, new Comparator<DoctorOutOfficeParamPO>() {
					public int compare(DoctorOutOfficeParamPO o1, DoctorOutOfficeParamPO o2) {
						Integer MonthNotOutOfficeSum1=0;
						Integer MonthNotOutOfficeSum2=0;
						if(null!=o1.getMonthNotOutOfficeSum()&& !"".equals(o1.getMonthNotOutOfficeSum())){
							MonthNotOutOfficeSum1 = o1.getMonthNotOutOfficeSum();
						}
						if(null!=o2.getMonthNotOutOfficeSum()&& !"".equals(o2.getMonthNotOutOfficeSum())){
							MonthNotOutOfficeSum2 =o2.getMonthNotOutOfficeSum();
						}
						//升序
						return MonthNotOutOfficeSum1.compareTo(MonthNotOutOfficeSum2);
					}
				});
				list = listResetNo(firstList,secondList);//重新设置NO序号
			}else if("dataFinishDesc".equals(value)){
				Collections.sort(firstList, new Comparator<DoctorOutOfficeParamPO>() {
					public int compare(DoctorOutOfficeParamPO o1, DoctorOutOfficeParamPO o2) {
						Double outOfficeDataFinishRate1=0.00;
						Double outOfficeDataFinishRate2=0.00;
						if(null!=o1.getOutOfficeDataFinishRate()&& !"".equals(o1.getOutOfficeDataFinishRate())){
							outOfficeDataFinishRate1 = Double.parseDouble(o1.getOutOfficeDataFinishRate().replace("%",""));
						}
						if(null!=o2.getOutOfficeDataFinishRate()&& !"".equals(o2.getOutOfficeDataFinishRate())){
							outOfficeDataFinishRate2 =Double.parseDouble(o2.getOutOfficeDataFinishRate().replace("%",""));
						}
						//降序
						return outOfficeDataFinishRate2.compareTo(outOfficeDataFinishRate1);
					}
				});
				list = listResetNo(firstList,secondList);//重新设置NO序号
			}else if("dataFinishAsc".equals(value)){
				Collections.sort(firstList, new Comparator<DoctorOutOfficeParamPO>() {
					public int compare(DoctorOutOfficeParamPO o1, DoctorOutOfficeParamPO o2) {
						Double outOfficeDataFinishRate1=0.00;
						Double outOfficeDataFinishRate2=0.00;
						if(null!=o1.getOutOfficeDataFinishRate()&& !"".equals(o1.getOutOfficeDataFinishRate())){
							outOfficeDataFinishRate1 = Double.parseDouble(o1.getOutOfficeDataFinishRate().replace("%",""));
						}
						if(null!=o2.getOutOfficeDataFinishRate()&& !"".equals(o2.getOutOfficeDataFinishRate())){
							outOfficeDataFinishRate2 =Double.parseDouble(o2.getOutOfficeDataFinishRate().replace("%",""));
						}
						//升序
						return outOfficeDataFinishRate1.compareTo(outOfficeDataFinishRate2);
					}
				});
				list = listResetNo(firstList,secondList);//重新设置NO序号
			}
		}
		return list;
	}
	//学员出科重新设置No
	public List<DoctorOutOfficeParamPO> listResetNo(List<DoctorOutOfficeParamPO> firstList,List<DoctorOutOfficeParamPO> secondList){
		for(int i=0;i<firstList.size();i++){
			firstList.get(i).setNo(/*(i+1)+""*/firstList.get(i).getOrgCode());
			String orgflow =firstList.get(i).getOrgFlow();
			int count=0;
			for(int j=0;j<secondList.size();j++){
				if(secondList.get(j).getParentOrgFlow().equals(orgflow)){
					secondList.get(j).setNo(/*(i+1)*/firstList.get(i).getOrgCode()+"-"+(count+1));
					count++;
				}
			}
		}
		firstList.addAll(secondList);
		return firstList;
	}
	public String[] convert(String[] datas){
		String docTypeList[]=new String[4];//人员类型
		if(Arrays.asList(datas).contains("Graduate") && Arrays.asList(datas).contains("NotGraduate")){
			docTypeList[0]="Graduate";docTypeList[1]="Company";docTypeList[2]="CompanyEntrust";docTypeList[3]="Social";
		}else{
			if(Arrays.asList(datas).contains("Graduate")){
				docTypeList[0]="Graduate";
			}else{
				docTypeList[0]="Company";docTypeList[1]="CompanyEntrust";docTypeList[2]="Social";
			}
		}
		return docTypeList;
	}
	//获取本月该实际出科人数的培训数据完成数、要求数
	public Integer[] getFinishSumAndTotalSum(List<Map<String, Object>> FinishSumAndTotalSumList){
		Integer[] array=new Integer[2];
		Integer finishSum=0,totalSum=0;
		for(Map<String,Object> map:FinishSumAndTotalSumList){
			String reqNumStr="0";String completeNumStr="0";
			if(!"".equals(map.get("reqNum"))&& null!=map.get("reqNum") ){
				 reqNumStr = (String)map.get("reqNum");
			}
			if(!"".equals(map.get("completeNum"))&& null!=map.get("completeNum") ){
				 completeNumStr = (String)map.get("completeNum");
			}
			Integer reqNum = Integer.parseInt(reqNumStr);
			Integer completeNum= Integer.parseInt(completeNumStr);
			finishSum=finishSum+completeNum;
			totalSum=totalSum+reqNum;
		}
		array[0]=finishSum;array[1]=totalSum;
		return array;
	}
	//获取本月一个人实际出科人数
	public Integer getMonthActualOutOfficeSum(String cksh,String monthDate,List<SchArrangeResult> arrResultList ){
		Integer count=0;
		for(SchArrangeResult schArrangeResult:arrResultList){
			String schEndDateT=schArrangeResult.getSchEndDate();
			String resultFlow = schArrangeResult.getResultFlow();
			if(StringUtil.isNotBlank(schEndDateT)){
				String schEndDate= schEndDateT.substring(0,7);
				if(monthDate.equals(schEndDate)){
							ResDoctorSchProcess doctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
							if(doctorSchProcess!=null){
								String processFlow = doctorSchProcess.getProcessFlow();
								List<ResSchProcessExpress> resRecs = expressBiz.searchByProcessFlowClob(processFlow);
								if(resRecs!=null&&resRecs.size()>0){
									for(ResSchProcessExpress r:resRecs){
										//出科考核表
										if(AfterRecTypeEnum.AfterEvaluation.getId().equals(r.getRecTypeId())){
											//如果科主任审核
											if(cksh.equals("Y")){
												//科主任通过
												if(RecStatusEnum.HeadAuditY.getId().equals(r.getHeadAuditStatusId())){
													count++;
												}
											}else{
												//带教老师通过
												if(RecStatusEnum.TeacherAuditY.getId().equals(r.getAuditStatusId())){
													count++;
												}
											}

										}
									}
								}
							}
				}
			}


		}
		return count;
	}
	//获取本月一个人应出科人数
	public Integer getMonthOutOfficeSum(String monthDate,List<SchArrangeResult> arrResultList ){
		Integer count=0;
		for(SchArrangeResult schArrangeResult:arrResultList){
			String schEndDateT=schArrangeResult.getSchEndDate();
			if(StringUtil.isNotBlank(schEndDateT)){
			    String schEndDate= schEndDateT.substring(0,7);
				if(monthDate.equals(schEndDate)){
					count++;
				}
			}
		}
		return count;
	}

	@RequestMapping("/teachActiveInfoNew")
	public String teachActiveInfoNew(Model model){
		return "res/platform/monthlyReport/teachActiveInfo";
	}

	@RequestMapping("/initTeachActiveNew")
	@ResponseBody
	public List initTeachActiveNew(String monthDate){
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		List<JsresActivityStatistics> listm = schdualTaskMapper.selectAllActivityData(monthDate);
		return listm;
	}

	@RequestMapping("/getActivityNew")
	@ResponseBody
	public List getActivityNew(String monthDate){
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		List<JsresActivityStatistics> listm = schdualTaskMapper.selectAllActivityData(monthDate);
		return listm;
	}

	@RequestMapping("/teachActiveInfo")
	public String PageToteachActiveInfo(Model model,String monthDate,String isContain,String orgFlow){

		return "res/platform/monthlyReport/teachActiveInfo";
	}

	@RequestMapping("/doctorOutDeptNew")
	public String doctorOutDeptNew(Model model,String monthDate,String isContain,String orgFlow){
		return "res/platform/monthlyReport/doctorOutOfficeInfo";
	}

	@RequestMapping("/initDoctorOutDept")
	@ResponseBody
	public List initDoctorOutDept(String sortFlag,String monthDate,String notGraduate,String graduate){
		Map<String,Object> param = new HashMap<>();
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		param.put("monthDate",monthDate);
		param.put("sortFlag",sortFlag);
		String doctorTypeId = "All";
		if("Y".equals(notGraduate) && !"Y".equals(graduate)){
			doctorTypeId = "Doctor";
		}
		if("Y".equals(graduate) && !"Y".equals(notGraduate)){
			doctorTypeId = "Graduate";
		}
		param.put("doctorTypeId",doctorTypeId);
		List<JsresOutDeptStatistics> list = schdualTaskMapper.searchOutDeptDate(param);
		// 计算学员填写数据完成度（暂不要）
//		String startDate = DateUtil.setFirstDayOfMonth(monthDate);//上个月第一天
//		String endDate = DateUtil.setFirstDayOfMonth(DateUtil.addMonth(monthDate,1));//第一天
//		param.put("startDate",startDate);
//		param.put("endDate",endDate);
//		if(null != list && list.size()>0){
//			for (JsresOutDeptStatistics jods:list) {
//				param.put("orgFlow", jods.getOrgFlow());
//				List<Map<String, String>> doctorOutDeptList = schdualTaskMapper.selectDoctorOutDeptData(param);
//
//
//			}
//		}
		return list;
	}

	/**
	 * 初始化教学活动开展情况table yuh new
	 * @return
	 */
	@RequestMapping("/initTeachActive")
	@ResponseBody
	public List teachActiveNew(String monthDate,String isContain){
		SysUser user = GlobalContext.getCurrentUser();
		String role=getRoleFlag();
		List<TeachActiveParamPO> orgInfoList = new ArrayList<>();
		try {
			if(!"".equals(role)){
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("monthDate",monthDate);
				if(null==isContain){
					isContain="N";
				}else{
					isContain="Y";
				}
				paramMap.put("isContain",isContain);
				SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
				String currentOrgName = currOrg.getOrgName();
				if(role.equals(GlobalConstant.USER_LIST_GLOBAL)){
					paramMap.put("province",currOrg.getOrgProvId());
					paramMap.put("roleFlag",GlobalConstant.USER_LIST_GLOBAL);
				}
				if(role.equals(GlobalConstant.USER_LIST_CHARGE)){
					paramMap.put("city",currOrg.getOrgCityId());
					paramMap.put("roleFlag",GlobalConstant.USER_LIST_CHARGE);
				}
				if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
					String currentOrgId="";
					Boolean gaoxiaoFlg = false;
					List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
					if (sendSchools != null && sendSchools.size() > 0) {
						for (SysDict dict : sendSchools) {
							if (currentOrgName.equals(dict.getDictName())) {
								currentOrgId=dict.getDictFlow();
								gaoxiaoFlg = true;
							}
						}
					}
					if(gaoxiaoFlg){
						paramMap.put("university",currentOrgId);
						paramMap.put("roleFlag",GlobalConstant.USER_LIST_UNIVERSITY);
					}else{
						throw new RuntimeException("请联系管理员“派送学校”是否有“"+currentOrgName+"”高校");
					}
				}
				orgInfoList =  schdualTaskMapper.selectPCUTeachActive(paramMap);
			}
			return orgInfoList;
		}catch (RuntimeException e){
			List<Map<String,String>> list=new ArrayList<>();
			Map<String,String> map=new HashMap<>();
			if(e.getMessage()==null){
				map.put("error","null");
			}else{
				map.put("error",e.getMessage());
			}
			list.add(map);
			return list;
		}
	}
	/**
	 * 初始化教学活动开展情况table yuh  old
	 * @return
	 */
	/*@RequestMapping("/initTeachActive")
	@ResponseBody*/
	public List teachActive(String monthDate,String isContain){
		//   start
		SysUser user = GlobalContext.getCurrentUser();
		List<TeachActiveParamPO> orgInfoList = new ArrayList<>();
		List<SysOrg> orgsList = new ArrayList<>();

		try {
			String roleFlag = getRoleFlag();
			if(!"".equals(roleFlag)){


			//高校角色
			if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
				List li = resMonthlyReportGlobalControllerClass.TeachActiveInfo_University(monthDate,isContain ,orgInfoList,roleFlag);
				return li;
			}

			SysOrg searchOrg=new SysOrg();
			SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
			searchOrg.setOrgProvId(currOrg.getOrgProvId());
			//如果是市角色
			if(roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)){
				searchOrg.setOrgCityId(currOrg.getOrgCityId());
			}
			searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
			searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			//包含协同
			if("isContain".equals(isContain)){
				List<SysOrg> countryOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
				for(int i =0;i<countryOrgs.size();i++){
					countryOrgs.get(i).setParentOrgFlow("");
					countryOrgs.get(i).setNo(/*(i+1)+""*/countryOrgs.get(i).getOrgCode());
					List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
					if(jointOrgList!=null&&!jointOrgList.isEmpty()){
						for(int j=0;j<jointOrgList.size();j++){
							jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
							jointOrgList.get(j).setNo(/*(i+1)*/countryOrgs.get(i).getOrgCode()+"-"+(j+1));
							orgsList.add(jointOrgList.get(j));
						}
					}
					orgsList.add(countryOrgs.get(i));
				}
				//对象转化
				for(int i=0;i<orgsList.size();i++){
					TeachActiveParamPO t=new TeachActiveParamPO();
					BeanUtils.copyProperties(orgsList.get(i),t);
					orgInfoList.add(t);
				}
				for(TeachActiveParamPO teachActiveParamPO:orgInfoList){
					String orgFlow =teachActiveParamPO.getOrgFlow();
					Map<String,Object> mymap=new HashMap<>();
					mymap.put("orgFlow",orgFlow);
					Integer trainerSumCount =monthlyReportExtMapper2.getTrainerSum(mymap);
					teachActiveParamPO.setTrainerSum(trainerSumCount); //在培总数

					List<Map<String,Object>> activeInfoList=monthlyReportExtMapper2.findActivityListyuh(mymap);
					Integer changci=0;//场次
					Integer alljoin=0;//总参加人次
					Double shichangTotal=0.00;//总时长
					for(Map<String,Object> li:activeInfoList){
						String activityFlow  = (String)li.get("activityFlow");
						String scanNum  = ((BigDecimal)li.get("scanNum")).toString(); //签字个数
						if(!"0".equals(scanNum)){
							List<TeachingActivityResult> teachingActivityResultList =monthlyReportExtMapper2.getScanTime1AndScanTime2(activityFlow);
							String yearMonthshichang=maxminTime(teachingActivityResultList);
							if("".equals(yearMonthshichang)){
								throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
							}
							String yearmonth=yearMonthshichang.split(",")[0];
							String shichang=yearMonthshichang.split(",")[1];
							if(monthDate.equals(yearmonth)){
								changci++;
								alljoin=alljoin+teachingActivityResultList.size();
								shichangTotal=shichangTotal+Double.parseDouble(shichang);
							}
						}
					}
					teachActiveParamPO.setTeachActiveSessionSum(changci);// 教学活动举办场次
					teachActiveParamPO.setAllJoinSum(alljoin); //总参加人次
					BigDecimal a = new BigDecimal(alljoin);
					BigDecimal b = new BigDecimal(changci);
					Double cdValue=0.0;
					if(!changci.equals(0)){
						BigDecimal c= a.divide(b,2,BigDecimal.ROUND_HALF_UP);
						cdValue = c.doubleValue();
					}
					teachActiveParamPO.setAverJoinSum(cdValue);//场均参加人次

					BigDecimal sct = new BigDecimal(shichangTotal);//总时长
					Double averSctValue=0.0;
					if(!changci.equals(0)){
						BigDecimal averSct= sct.divide(b,2,BigDecimal.ROUND_HALF_UP);
						averSctValue =averSct.doubleValue();
					}
					teachActiveParamPO.setAverDureTime(averSctValue);//场均时长（分钟）
					teachActiveParamPO.setDureTime(shichangTotal);
				}
				//给国家基地赋值包含协同的信息
				for (TeachActiveParamPO tpo:orgInfoList){
					if("".equals(tpo.getParentOrgFlow())){
						Integer zaipeiSum=tpo.getTrainerSum(); //在陪人数
						Integer jubanchangci=tpo.getTeachActiveSessionSum();
						Integer zongcanjiarenci=tpo.getAllJoinSum();
						Double zongshichang=tpo.getDureTime();
						for(TeachActiveParamPO newTpo2:orgInfoList){
							 if(tpo.getOrgFlow().equals(newTpo2.getParentOrgFlow())){
								 zaipeiSum = zaipeiSum + newTpo2.getTrainerSum();
								 jubanchangci=jubanchangci+newTpo2.getTeachActiveSessionSum();
								 zongcanjiarenci=zongcanjiarenci+newTpo2.getAllJoinSum();
								 zongshichang=zongshichang+newTpo2.getDureTime();
							 }
						}
						tpo.setTrainerSum(zaipeiSum);
						tpo.setTeachActiveSessionSum(jubanchangci);
						tpo.setAllJoinSum(zongcanjiarenci);

						BigDecimal a1 = new BigDecimal(zongcanjiarenci);
						BigDecimal b1 = new BigDecimal(jubanchangci);
						Double cdValue=0.0;
						if(!jubanchangci.equals(0)){
							BigDecimal c= a1.divide(b1,2,BigDecimal.ROUND_HALF_UP);
							cdValue = c.doubleValue();
						}
						tpo.setAverJoinSum(cdValue);//场均参加人次

						BigDecimal sct = new BigDecimal(zongshichang);//总时长
						Double averSctValue=0.0;
						if(!jubanchangci.equals(0)){
							BigDecimal averSct= sct.divide(b1,2,BigDecimal.ROUND_HALF_UP);
							averSctValue =averSct.doubleValue();
						}
						tpo.setAverDureTime(averSctValue);//场均时长
					}
				}
				List<TeachActiveParamPO>  newList1=  activitySessionDevideMax( orgInfoList );//求进度条值
				orgInfoList=newList1;
//不包含协同
	}else{
				Map<String,Object> map=new HashMap<>();
				map.put("monthDate",monthDate);
				List<SysOrg> countryOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
				for(int i =0;i<countryOrgs.size();i++){
					countryOrgs.get(i).setParentOrgFlow("");
					countryOrgs.get(i).setNo(/*(i+1)+""*/countryOrgs.get(i).getOrgCode());
					List<SysOrg>  jointOrgList= orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
					if(jointOrgList!=null&&!jointOrgList.isEmpty()){
						for(int j=0;j<jointOrgList.size();j++){
							jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
							jointOrgList.get(j).setNo(/*(i+1)*/countryOrgs.get(i).getOrgCode()+"-"+(j+1));

							orgsList.add(jointOrgList.get(j));
						}
					}
					orgsList.add(countryOrgs.get(i));
				}
				//对象转化
				for(int i=0;i<orgsList.size();i++){
					TeachActiveParamPO t=new TeachActiveParamPO();
					BeanUtils.copyProperties(orgsList.get(i),t);
					orgInfoList.add(t);
				}
				//
				for(TeachActiveParamPO teachActiveParamPO:orgInfoList){
					String orgFlow =teachActiveParamPO.getOrgFlow();
					Map<String,Object> mymap=new HashMap<>();
					mymap.put("orgFlow",orgFlow);
					Integer trainerSumCount =monthlyReportExtMapper2.getTrainerSum(mymap);
					teachActiveParamPO.setTrainerSum(trainerSumCount); //在培总数

					List<Map<String,Object>> activeInfoList=monthlyReportExtMapper2.findActivityListyuh(mymap);
					Integer changci=0;//场次
					Integer alljoin=0;//总参加人次
					Double shichangTotal=0.00;//总时长
					for(Map<String,Object> li:activeInfoList){
						String activityFlow  = (String)li.get("activityFlow");
						String scanNum  = ((BigDecimal)li.get("scanNum")).toString(); //签字个数
						if(!"0".equals(scanNum)){
						    List<TeachingActivityResult> teachingActivityResultList =monthlyReportExtMapper2.getScanTime1AndScanTime2(activityFlow);
							String yearMonthshichang=maxminTime(teachingActivityResultList);
							if("".equals(yearMonthshichang)){
								throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
							}
							String yearmonth=yearMonthshichang.split(",")[0];
							String shichang=yearMonthshichang.split(",")[1];
							if(monthDate.equals(yearmonth)){
								changci++;
								alljoin=alljoin+teachingActivityResultList.size();
								shichangTotal=shichangTotal+Double.parseDouble(shichang);
							}
						}
					}
					teachActiveParamPO.setTeachActiveSessionSum(changci);// 教学活动举办场次
					teachActiveParamPO.setAllJoinSum(alljoin); //总参加人次
					BigDecimal a = new BigDecimal(alljoin);
					BigDecimal b = new BigDecimal(changci);
					Double cdValue=0.0;
					if(!changci.equals(0)){
						 BigDecimal c= a.divide(b,2,BigDecimal.ROUND_HALF_UP);
						 cdValue = c.doubleValue();
					}
					//BigDecimal c= a.divide(b);
					teachActiveParamPO.setAverJoinSum(cdValue);//场均参加人次

					BigDecimal sct = new BigDecimal(shichangTotal);//总时长
					Double averSctValue=0.0;
					if(!changci.equals(0)){
						BigDecimal averSct= sct.divide(b,2,BigDecimal.ROUND_HALF_UP);
						averSctValue =averSct.doubleValue();
					}
					//BigDecimal averSct= sct.divide(b);
					teachActiveParamPO.setAverDureTime(averSctValue);//场均时长（分钟）
					teachActiveParamPO.setDureTime(shichangTotal);
				}
				 List<TeachActiveParamPO>  newList=  activitySessionDevideMax( orgInfoList );//求进度条值
				 orgInfoList=newList;

			}
			}
			return orgInfoList;
		}catch (RuntimeException e){
			e.printStackTrace();
			List<Map<String,String>> list=new ArrayList<>();
			Map<String,String> map=new HashMap<>();
			if(e.getMessage()==null){
				map.put("error","null");
			}else{
				map.put("error",e.getMessage());
			}
			list.add(map);
			return list;
		} catch (ParseException e) {
			e.printStackTrace();
			List<Map<String,String>> list=new ArrayList<>();
			Map<String,String> map=new HashMap<>();
			if(e.getMessage()==null){
				map.put("error","null");
			}else{
				map.put("error",e.getMessage());
			}
			list.add(map);
			return list;
		}
	}
	/**
	 * 初始化教学活动开展情况echarts yuh new
	 * @param monthDate
	 * @param isContain
	 * @return
	 */
	@RequestMapping("/getEchartsInfo")
	@ResponseBody
	public List getEchartsInfoNew(String monthDate,String isContain){
		List li=new ArrayList();
		List<List> bigList=new ArrayList<>();
		List jidi=new ArrayList();
		List jubanchangci=new ArrayList();
		List zongcanjiarenci=new ArrayList();
		List changjuncanjiarenci=new ArrayList();
		List changjunshichang=new ArrayList();


		List list = teachActiveNew(monthDate,isContain);
		if(list.size()>0){
			try {
				List<TeachActiveParamPO> newList =list;
				for(TeachActiveParamPO tapo:newList){
					//直取国家基地  去除协同基地
					if(null==tapo.getParentOrgFlow() || "".equals(tapo.getParentOrgFlow())){

					jidi.add(/*tapo.getOrgName()*/tapo.getNo());
					jubanchangci.add(tapo.getTeachActiveSessionSum());
					zongcanjiarenci.add(tapo.getAllJoinSum());
					changjuncanjiarenci.add(tapo.getAverJoinSum());
					changjunshichang.add(tapo.getAverDureTime());
					}
				}
				bigList.add(jidi);
				bigList.add(jubanchangci);
				bigList.add(zongcanjiarenci);
				bigList.add(changjuncanjiarenci);
				bigList.add(changjunshichang);
				return bigList;
			}catch (Exception e){
				e.printStackTrace();
				return li;
			}
		}else{
			return li;//[]
		}
	}
	/**
	 * 初始化教学活动开展情况echarts yuh old
	 * @param monthDate
	 * @param isContain
	 * @return
	 */
	/*@RequestMapping("/getEchartsInfo")
	@ResponseBody*/
	public List getEchartsInfo(String monthDate,String isContain){
	  List li=new ArrayList();
	  List<List> bigList=new ArrayList<>();
      List jidi=new ArrayList();
      List jubanchangci=new ArrayList();
      List zongcanjiarenci=new ArrayList();
      List changjuncanjiarenci=new ArrayList();
      List changjunshichang=new ArrayList();


	  List list = teachActive(monthDate,isContain);
		if(list.size()>0){
			try {
				List<TeachActiveParamPO> newList =list;
				for(TeachActiveParamPO tapo:newList){
					jidi.add(/*tapo.getOrgName()*/tapo.getNo());
					jubanchangci.add(tapo.getTeachActiveSessionSum());
					zongcanjiarenci.add(tapo.getAllJoinSum());
					changjuncanjiarenci.add(tapo.getAverJoinSum());
					changjunshichang.add(tapo.getAverDureTime());
				}
				bigList.add(jidi);
				bigList.add(jubanchangci);
				bigList.add(zongcanjiarenci);
				bigList.add(changjuncanjiarenci);
				bigList.add(changjunshichang);
				return bigList;
			}catch (Exception e){
				e.printStackTrace();
				return li;
			}
		}else{
			return li;//[]
		}

	}
	//教学活动举办场次和最高值比例
	public List<TeachActiveParamPO> activitySessionDevideMax(List<TeachActiveParamPO> orgInfoList ){
		Integer max=0;
		Integer xitongMax=0;
		for(TeachActiveParamPO t:orgInfoList){
			if("".equals(t.getParentOrgFlow())){
				Integer A =t.getTeachActiveSessionSum();
				if(A>max){
					max=A;
				}
			}else{
				Integer xitongA =t.getTeachActiveSessionSum();
				if(xitongA>xitongMax){
					xitongMax=xitongA;
				}
			}

		}
		BigDecimal a = new BigDecimal(max);
		BigDecimal xitonga = new BigDecimal(xitongMax);
		for(TeachActiveParamPO t1:orgInfoList){
			BigDecimal b = new BigDecimal(t1.getTeachActiveSessionSum());
			Double cdValue=0.00;
			if("".equals(t1.getParentOrgFlow())){
				if(!max.equals(0)){
					BigDecimal c= b.divide(a,2,BigDecimal.ROUND_HALF_UP);
					cdValue=c.doubleValue();
				}
			}else{
				if(!xitongMax.equals(0)){
					BigDecimal c= b.divide(xitonga,2,BigDecimal.ROUND_HALF_UP);
					cdValue=c.doubleValue();
				}
			}
			t1.setAverTeachActiveSessionSum(cdValue*100+"");
		}
		return orgInfoList;
	}
	public String maxminTime( List<TeachingActivityResult> teachingActivityResultList) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM");
		List<Date> time1List=new ArrayList<>();
		List<Date> time2List=new ArrayList<>();
		for(TeachingActivityResult tar:teachingActivityResultList){
		  Date scantime1=	sdf.parse(tar.getScanTime());
		  Date scantime2=	sdf.parse(tar.getScanTime2());
			time1List.add(scantime1);
			time2List.add(scantime2);
		}
		Date min1 =Collections.min(time1List);
		Date min2 =Collections.min(time2List);
		long diff = min2.getTime() - min1.getTime();//这样得到的差值是毫秒级别
		long minutes = (diff)/(1000* 60);
		String ym1 = sdf2.format(min1);
		String ym2 = sdf2.format(min2);
		if(ym1.equals(ym2)){
			return ym1+","+minutes;
		}else{
			return "";
		}

	}

	@RequestMapping("/appUseInfo")
	public String PageToAppUseInfo(Model model,String monthDate,String isContain,String orgFlow){
		try {
			String role=getRoleFlag();//角色
			if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
				model.addAttribute("university","university");
			}
			return "res/platform/monthlyReport/appUseInfo";
		}catch (RuntimeException e){
			e.printStackTrace();
			return "res/platform/monthlyReport/appUseInfo";
		}
	}

	@RequestMapping("/initAppUseInfoNew")
	@ResponseBody
	public List initAppUseInfoNew(Model model,String monthDate,String isOrder){
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("isOrder",isOrder);
		List<JsresAppInfo> listm = schdualTaskMapper.searchAllData(paramMap);
		return listm;
	}

	/**
	 * 初始化app使用情况月报yuh new
	 * @return
	 */
	@RequestMapping("/initAppUseInfo")
	@ResponseBody
	public List appUseInfo_New(Model model,String monthDate,String isContain,String orgFlow){
		SysUser user = GlobalContext.getCurrentUser();
		String role=getRoleFlag();
		List<SysOrg> listm=new ArrayList<>();
		try {
			if(!"".equals(role)){
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("monthDate",monthDate);
				if(null==isContain){
					isContain="N";
				}else{
					isContain="Y";
				}
				paramMap.put("isContain",isContain);
				SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
				String currentOrgName = currOrg.getOrgName();
				//String currentOrgId = currOrg.getOrgFlow();
				if(role.equals(GlobalConstant.USER_LIST_GLOBAL)){
					paramMap.put("province",currOrg.getOrgProvId());
					paramMap.put("roleFlag",GlobalConstant.USER_LIST_GLOBAL);
				}
				if(role.equals(GlobalConstant.USER_LIST_CHARGE)){
					paramMap.put("city",currOrg.getOrgCityId());
					paramMap.put("roleFlag",GlobalConstant.USER_LIST_CHARGE);
				}
				if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
					String currentOrgId="";
					Boolean gaoxiaoFlg = false;
					List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
					if (sendSchools != null && sendSchools.size() > 0) {
						for (SysDict dict : sendSchools) {
							if (currentOrgName.equals(dict.getDictName())) {
								currentOrgId=dict.getDictFlow();
								gaoxiaoFlg = true;
							}
						}
					}
					if(gaoxiaoFlg){
						paramMap.put("university",currentOrgId);
						paramMap.put("roleFlag",GlobalConstant.USER_LIST_UNIVERSITY);
					}else{
						throw new RuntimeException("请联系管理员“派送学校”是否有“"+currentOrgName+"”高校");
					}
				}
				listm =schdualTaskMapper.selectPCUAppInfo(paramMap);
				listm= listSort(orgFlow,listm);
			}
			return listm;
		}catch (RuntimeException e){
			List<Map<String,String>> list=new ArrayList<>();
			Map<String,String> map=new HashMap<>();
			if(e.getMessage()==null){
				map.put("error","null");
			}else{
				map.put("error",e.getMessage());
			}
			list.add(map);
			return list;
		}

	}
	/**
	 * 初始化app使用情况月报yuh
	 * @return
	 */
	/*@RequestMapping("/initAppUseInfo")
	@ResponseBody*/
	public List appUseInfo(Model model,String monthDate,String isContain,String orgFlow){
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = new ArrayList<>();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
        String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
        String[] docTypeListMast ={"Graduate"};
		try {
			String role=getRoleFlag();
			if(!"".equals(role)){


			//高校角色
			if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
				List li = resMonthlyReportGlobalControllerClass.appUserInfo_University(monthDate,isContain ,orgFlow,orgs,role);
				return li;
			}

			SysOrg searchOrg=new SysOrg();
			SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
			searchOrg.setOrgProvId(currOrg.getOrgProvId());
			if(role.equals(GlobalConstant.USER_LIST_CHARGE)){
				searchOrg.setOrgCityId(currOrg.getOrgCityId());
			}
			searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
			searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			//包含协同
			if("isContain".equals(isContain)){
				Map<String,Object> map=new HashMap<>();
				Map<String,Object> map1=new HashMap<>();
				map.put("monthDate",monthDate);
				List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
				for(int i=0;i<exitOrgs.size();i++){
					List<String> allOrgFlow = new ArrayList<>();
					List<SysOrg>  resJointOrgList= orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
					for (int j=0;j<resJointOrgList.size();j++){
						allOrgFlow.add(resJointOrgList.get(j).getOrgFlow());
					}
					allOrgFlow.add(exitOrgs.get(i).getOrgFlow());
					exitOrgs.get(i).setParentOrgFlow("");
					exitOrgs.get(i).setNo(/*(i+1)+""*/exitOrgs.get(i).getOrgCode());
					map.put("allOrgFlow",allOrgFlow);
					map.put("docTypeList",docTypeListDoc);
                    map1.put("allOrgFlow",allOrgFlow);
                    map1.put("docTypeList",docTypeListMast);
					/*List<SysOrg> list =monthlyReportExtMapper2.getAppDatayuh(map);*/
                    List<JsResDoctorRecruitExt> docList=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map);
                    List<JsResDoctorRecruitExt> mastlist=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(map1);
                    int allTrain=docList.size()+mastlist.size();//所有在培人数

                    Map<String,Object> paramMap3 = new HashMap<>();
                    paramMap3.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                    paramMap3.put("myDoctorTypeId",docTypeListDoc);
                    paramMap3.put("allOrgFlow",allOrgFlow);
                    Map<String,Object> paramMap4 = new HashMap<>();
                    paramMap4.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                    paramMap4.put("myDoctorTypeId",docTypeListMast);
                    paramMap4.put("allOrgFlow",allOrgFlow);
                    List<String> docAppuse= monthlyReportExtMapper2.getAppUserSum(paramMap3);//
                    List<String> masteAppuse= monthlyReportExtMapper2.getAppUserSum(paramMap4);
                    int allAppuse=docAppuse.size()+masteAppuse.size();  //所有app使用人数
					//if(list.size()>0){
						Integer trainDoctorTotal=0;
						Integer doctorSum=0;
						Integer masterSum=0;
                     trainDoctorTotal=allTrain;
                     doctorSum=docList.size();
                     masterSum=mastlist.size();
						/*for(SysOrg s:list){
							trainDoctorTotal=trainDoctorTotal+s.getTrainDoctorTotal();
							doctorSum=doctorSum+s.getDoctorSum();
							masterSum=masterSum+s.getMasterSum();
						}*/
						exitOrgs.get(i).setTrainDoctorTotal(trainDoctorTotal);
						exitOrgs.get(i).setDoctorSum(doctorSum);
						exitOrgs.get(i).setMasterSum(masterSum);
						String doctorRate="";
						String masterRate="";

						if(0==doctorSum){
                            doctorRate="0%";
                        }
						if(0!=doctorSum){
							doctorRate = numberFormat.format((float) docAppuse.size() / (float) doctorSum * 100)+"%";
						}
                        if(0==masterSum){
                            masterRate="0%";
                        }
                        if(0!=masterSum){
                            masterRate = numberFormat.format((float) masteAppuse.size() / (float) masterSum * 100)+"%";
                        }
						exitOrgs.get(i).setDoctorRate(doctorRate);
						exitOrgs.get(i).setMasterRate(masterRate);
						Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(map);//该基地在培总人数
						String rate="";
						if(0==trainPersonTotal){
                            rate="0%";
                        }
						if(0!=trainPersonTotal){
							rate=numberFormat.format((float) allAppuse / (float)trainPersonTotal  * 100)+"%";
						}
						exitOrgs.get(i).setRate(rate);
					//}
					List<SysOrg>  resJointOrgListT= orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
					if(resJointOrgListT!=null&&!resJointOrgListT.isEmpty()){
						Map<String,Object> secondMap=new HashMap<>();
						secondMap.put("monthDate",monthDate);
						for(int j= 0;j<resJointOrgListT.size();j++){
							resJointOrgListT.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
							resJointOrgListT.get(j).setNo(/*(i+1)+"-"+(j+1)*/exitOrgs.get(i).getOrgCode()+"-"+(j+1));
							secondMap.put("orgFlow",resJointOrgListT.get(j).getOrgFlow());

                            Map<String,Object> paramMapXieTong=new HashMap<>();
                            Map<String,Object> paramMap2XieTong=new HashMap<>();
                            paramMapXieTong.put("orgFlow",resJointOrgList.get(j).getOrgFlow());
                            paramMapXieTong.put("docTypeList",docTypeListDoc);
                            paramMap2XieTong.put("orgFlow",resJointOrgList.get(j).getOrgFlow());
                            paramMap2XieTong.put("docTypeList",docTypeListMast);

                            List<SysOrg> list2=new ArrayList<>();
                            SysOrg ms=new SysOrg();
                            List<JsResDoctorRecruitExt> docListXietong=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                            List<JsResDoctorRecruitExt> mastlistXietong=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                            Map<String,Object> paramMap3Xietong = new HashMap<>();
                            paramMap3Xietong.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                            paramMap3Xietong.put("myDoctorTypeId",docTypeListDoc);
                            paramMap3Xietong.put("orgFlow",resJointOrgList.get(j).getOrgFlow());
                            Map<String,Object> paramMap4Xietong = new HashMap<>();
                            paramMap4Xietong.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                            paramMap4Xietong.put("myDoctorTypeId",docTypeListMast);
                            paramMap4Xietong.put("orgFlow",resJointOrgList.get(j).getOrgFlow());
                            List<String> docAppuseXietong= monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                            List<String> masteAppuseXietong= monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                            int allAppuseXietong=docAppuseXietong.size()+masteAppuseXietong.size();  //所有app使用人数
                            ms.setTrainDoctorTotal(docListXietong.size()+mastlistXietong.size());
                            ms.setDoctorSum(docListXietong.size());
                            ms.setMasterSum(mastlistXietong.size());
                            list2.add(ms);

							/*List<SysOrg> list2 =monthlyReportExtMapper2.getAppDatayuh(secondMap);*/
							if(list2.size()>0){
								SysOrg sysOrgT2 =list2.get(0);
								resJointOrgListT.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
								resJointOrgListT.get(j).setDoctorSum(sysOrgT2.getDoctorSum());
								resJointOrgListT.get(j).setMasterSum(sysOrgT2.getMasterSum());
								String doctorRate2="";
								String masterRate2="";
								if(0==sysOrgT2.getDoctorSum()){
                                    doctorRate2="0%";
                                }
								if(0!=sysOrgT2.getDoctorSum()){
									doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100)+"%";
								}
								if(0==sysOrgT2.getMasterSum()){
                                    masterRate2="0%";
                                }
                                if(0!=sysOrgT2.getMasterSum()){
                                    masterRate2 = numberFormat.format((float) masteAppuseXietong.size() / (float) sysOrgT2.getMasterSum() * 100)+"%";
                                }
								resJointOrgListT.get(j).setDoctorRate(doctorRate2);
								resJointOrgListT.get(j).setMasterRate(masterRate2);
								Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(secondMap);//该基地在培总人数
								String rate2="";
                                if(0==trainPersonTotal2){
                                    rate2="0%";
                                }
								if(0!=trainPersonTotal2){
									rate2=numberFormat.format((float)allAppuseXietong / (float)trainPersonTotal2  * 100)+"%";
								}
								resJointOrgListT.get(j).setRate(rate2);
							}

							orgs.add(resJointOrgListT.get(j));
							//allOrgFlow.add(jointOrg.getOrgFlow());
						}
					}
					orgs.add(exitOrgs.get(i));
				}

			//不包含协同
			}else{
				Map<String,Object> paramMap = new HashMap<>();
                Map<String,Object> paramMap2=new HashMap<>();
				paramMap.put("monthDate",monthDate);
				List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
					for(int i=0;i<exitOrgs.size();i++){
						exitOrgs.get(i).setParentOrgFlow("");
						exitOrgs.get(i).setNo(/*(i+1)+""*/exitOrgs.get(i).getOrgCode());
						String countryOrgFlow=exitOrgs.get(i).getOrgFlow();
						paramMap.put("orgFlow",countryOrgFlow);
						paramMap.put("docTypeList",docTypeListDoc);
                        paramMap2.put("orgFlow",countryOrgFlow);
                        paramMap2.put("docTypeList",docTypeListMast);
						/*List<SysOrg> list =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
                        List<SysOrg> list=new ArrayList<>();
                        SysOrg sss=new SysOrg();
                        List<JsResDoctorRecruitExt> docList=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap);
                        List<JsResDoctorRecruitExt> mastlist=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2);
                        Map<String,Object> paramMap3 = new HashMap<>();
                        paramMap3.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                        paramMap3.put("myDoctorTypeId",docTypeListDoc);
                        paramMap3.put("orgFlow",countryOrgFlow);
                        Map<String,Object> paramMap4 = new HashMap<>();
                        paramMap4.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                        paramMap4.put("myDoctorTypeId",docTypeListMast);
                        paramMap4.put("orgFlow",countryOrgFlow);
                        List<String> docAppuse= monthlyReportExtMapper2.getAppUserSum(paramMap3);//
                        List<String> masteAppuse= monthlyReportExtMapper2.getAppUserSum(paramMap4);
                        int allAppuse=docAppuse.size()+masteAppuse.size();  //所有app使用人数
                        sss.setTrainDoctorTotal(docList.size()+mastlist.size());
                        sss.setDoctorSum(docList.size());
                        sss.setMasterSum(mastlist.size());
                        list.add(sss);

						if(list.size()>0){
							SysOrg sysOrgT =list.get(0);
							exitOrgs.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
							exitOrgs.get(i).setDoctorSum(sysOrgT.getDoctorSum());
							exitOrgs.get(i).setMasterSum(sysOrgT.getMasterSum());
							String doctorRate="";
							String masterRate="";
							if(sysOrgT.getDoctorSum()==0){
                                doctorRate="0%";
                            }
							if(0!=sysOrgT.getDoctorSum()){
								 doctorRate = numberFormat.format((float) docAppuse.size() / (float) sysOrgT.getDoctorSum() * 100)+"%";
							}
							if(sysOrgT.getMasterSum()==0){
                                masterRate="0%";
                            }
                            if(0!=sysOrgT.getMasterSum()){
                                masterRate = numberFormat.format((float)masteAppuse.size() / (float) sysOrgT.getMasterSum() * 100)+"%";
                            }
							exitOrgs.get(i).setDoctorRate(doctorRate);
							exitOrgs.get(i).setMasterRate(masterRate);
							Integer trainPersonTotal = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数
							String rate="";
							if(trainPersonTotal==0){
                                rate="0%";
                            }
							if(0!=trainPersonTotal){
								 rate=numberFormat.format((float) allAppuse / (float)trainPersonTotal  * 100)+"%";
							}
							exitOrgs.get(i).setRate(rate);
						}

						List<SysOrg>  resJointOrgList= orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(int j= 0;j<resJointOrgList.size();j++){
								resJointOrgList.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
								resJointOrgList.get(j).setNo(/*(i+1)+"-"+(j+1)*/exitOrgs.get(i).getOrgCode()+"-"+(j+1));
								paramMap.put("orgFlow",resJointOrgList.get(j).getOrgFlow());

                                Map<String,Object> paramMapXieTong=new HashMap<>();
                                Map<String,Object> paramMap2XieTong=new HashMap<>();
                                paramMapXieTong.put("orgFlow",resJointOrgList.get(j).getOrgFlow());
                                paramMapXieTong.put("docTypeList",docTypeListDoc);
                                paramMap2XieTong.put("orgFlow",resJointOrgList.get(j).getOrgFlow());
                                paramMap2XieTong.put("docTypeList",docTypeListMast);

                                List<SysOrg> list2=new ArrayList<>();
                                SysOrg ms=new SysOrg();
                                List<JsResDoctorRecruitExt> docListXietong=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMapXieTong);
                                List<JsResDoctorRecruitExt> mastlistXietong=  monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(paramMap2XieTong);
                                Map<String,Object> paramMap3Xietong = new HashMap<>();
                                paramMap3Xietong.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                                paramMap3Xietong.put("myDoctorTypeId",docTypeListDoc);
                                paramMap3Xietong.put("orgFlow",resJointOrgList.get(j).getOrgFlow());
                                Map<String,Object> paramMap4Xietong = new HashMap<>();
                                paramMap4Xietong.put("monthDate",monthDate.split("-")[0]+monthDate.split("-")[1]);
                                paramMap4Xietong.put("myDoctorTypeId",docTypeListMast);
                                paramMap4Xietong.put("orgFlow",resJointOrgList.get(j).getOrgFlow());
                                List<String> docAppuseXietong= monthlyReportExtMapper2.getAppUserSum(paramMap3Xietong);//
                                List<String> masteAppuseXietong= monthlyReportExtMapper2.getAppUserSum(paramMap4Xietong);
                                int allAppuseXietong=docAppuseXietong.size()+masteAppuseXietong.size();  //所有app使用人数
                                ms.setTrainDoctorTotal(docListXietong.size()+mastlistXietong.size());
                                ms.setDoctorSum(docListXietong.size());
                                ms.setMasterSum(mastlistXietong.size());
                                list2.add(ms);
								/*List<SysOrg> list2 =monthlyReportExtMapper2.getAppDatayuh(paramMap);*/
								if(list2.size()>0){
									SysOrg sysOrgT2 =list2.get(0);
									resJointOrgList.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
									resJointOrgList.get(j).setDoctorSum(sysOrgT2.getDoctorSum());
									resJointOrgList.get(j).setMasterSum(sysOrgT2.getMasterSum());
									String doctorRate2="";
									String masterRate2="";
									if(sysOrgT2.getDoctorSum()==0){
                                        doctorRate2="0%";
                                    }
									if(0!=sysOrgT2.getDoctorSum()){
										 doctorRate2 = numberFormat.format((float) docAppuseXietong.size() / (float) sysOrgT2.getDoctorSum() * 100)+"%";
									}
									if(sysOrgT2.getMasterSum()==0){
                                        masterRate2="0%";
                                    }
                                    if(0!= sysOrgT2.getMasterSum()){
                                        masterRate2 = numberFormat.format((float) masteAppuseXietong.size()/ (float)  sysOrgT2.getMasterSum()  * 100)+"%";
                                    }
									resJointOrgList.get(j).setDoctorRate(doctorRate2);
									resJointOrgList.get(j).setMasterRate(masterRate2);
									Integer trainPersonTotal2 = monthlyReportExtMapper2.getTrainTotal(paramMap);//该基地在培总人数
									String rate2="";
									if(trainPersonTotal2==0){
                                        rate2="0%";
                                    }
									if(0!=trainPersonTotal2){
										 rate2=numberFormat.format((float) allAppuseXietong/ (float)trainPersonTotal2  * 100)+"%";
									}
									resJointOrgList.get(j).setRate(rate2);
								}

								orgs.add(resJointOrgList.get(j));
								//allOrgFlow.add(jointOrg.getOrgFlow());
							}
						}
						orgs.add(exitOrgs.get(i));
						//allOrgFlow.add(g.getOrgFlow());
					}
			}
			orgs= listSort(orgFlow,orgs);
			}
			return orgs;
		}catch (RuntimeException e){
			List<Map<String,String>> list=new ArrayList<>();
			Map<String,String> map=new HashMap<>();
			if(e.getMessage()==null){
				map.put("error","null");
			}else{
				map.put("error",e.getMessage());
			}
			list.add(map);
			//model.addAttribute("error",e.getMessage());
			return list;
		}

	}

	/**
	 * app使用详细信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/initAppUseInfoDetail")
	public String appUserInfoDetail(Model model){
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = new ArrayList<>();
		try {
			String role =getRoleFlag();
			if(!"".equals(role)){
				//高校角色
				if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
					SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
					String currOrgName = currOrg.getOrgName();

					Boolean gaoxiaoFlg = false;
					List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
					if (sendSchools != null && sendSchools.size() > 0) {
						for (SysDict dict : sendSchools) {
							if (currOrgName.equals(dict.getDictName())) {
								gaoxiaoFlg = true;
							}
						}
					}
					if(gaoxiaoFlg){
						Map<String, Object> map1 = new HashMap<>();
						List<SysOrg> orgUniversity = new ArrayList<>();
						List<SysOrg> distinctOrgUniversity = new ArrayList<>();
						map1.put("universityName", currOrgName);
						List<JsResDoctorRecruitExt> resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map1);
						for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
							SysOrg s1 = new SysOrg();
							List<String> doctorlistS = new ArrayList<>();
							String orgFlow = jRR.getOrgFlow();
							String orgName = jRR.getOrgName();
							s1.setOrgFlow(orgFlow);
							s1.setOrgName(orgName);
							for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
								if (orgFlow.equals(jRR2.getOrgFlow())) {
									doctorlistS.add(jRR2.getDoctorFlow());
								}
							}
							s1.setDoctorFlowsInOrg(doctorlistS);
							orgUniversity.add(s1);
						}
						Set<SysOrg> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
						personSet.addAll(orgUniversity);
						distinctOrgUniversity = new ArrayList<>(personSet);
						model.addAttribute("orgs",distinctOrgUniversity);
						model.addAttribute("userListScope",role);
					}else{
						throw new RuntimeException("请联系管理员“派送学校”是否有“"+currOrgName+"”高校");
					}

				}else{
					SysOrg searchOrg=new SysOrg();
					SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
					searchOrg.setOrgProvId(currOrg.getOrgProvId());
					///省级
					if(role.equals(GlobalConstant.USER_LIST_CHARGE)){
						searchOrg.setOrgCityId(currOrg.getOrgCityId());
					}
					searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
					searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
					orgs.addAll(exitOrgs);
					for(int i=0;i<exitOrgs.size();i++) {
						List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
						orgs.addAll(resJointOrgList);
					}
					model.addAttribute("orgs",orgs);
					model.addAttribute("userListScope",role);
				}

			}
		}catch (RuntimeException e){
			e.printStackTrace();
			if(null==e.getMessage()){
				model.addAttribute("error","null");
			}else{
				model.addAttribute("error",e.getMessage());
			}
			return "res/platform/monthlyReport/appUseInfoDetail";
		}
		return "res/platform/monthlyReport/appUseInfoDetail";
	}
	/***
	 * app使用详细信息 （未使用用户信息）
	 */
	@RequestMapping("/appNotUseList")
	public String appNotUseList(Integer currentPage, HttpServletRequest request, Model model,String monthDate,
								String userListScope, String orgFlow, String[] datas,
								 String trainingSpeId, String sessionNumber) {
		Map<String, Object> param = new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		/*param.put("orgLevel", "CountryOrg");//国家基地*/
		param.put("startDate", monthDate);
		param.put("endDate", monthDate);
		param.put("orgFlow", orgFlow);
		param.put("docTypeList", docTypeList);
		param.put("trainingTypeId", "DoctorTrainingSpe");//住院医师
		param.put("trainingSpeId", trainingSpeId);
		param.put("sessionNumber", sessionNumber);
		param.put("userListScope", userListScope);

		List<String> orgFlows = new ArrayList<>();
		param.put("orgFlows", orgFlows);

		SysUser user = GlobalContext.getCurrentUser();
		SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
		String currOrgName = currOrg.getOrgName();
		Map<String, Object> map1 = new HashMap<>();
		List<SysOrg> orgUniversity = new ArrayList<>();
		List<SysOrg> distinctOrgUniversity = new ArrayList<>();
		List<JsResDoctorRecruitExt> resDoctorRecruitExtsList=new ArrayList<>();
		if (GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)) {//高校
			map1.put("universityName", currOrgName);
			resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map1);
			if (StringUtil.isBlank(orgFlow)) {
				for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
					SysOrg s1 = new SysOrg();
					List<String> doctorlistS = new ArrayList<>();
					s1.setOrgFlow(jRR.getOrgFlow());
					s1.setOrgName(jRR.getOrgName());
					for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
						if (orgFlow.equals(jRR2.getOrgFlow())) {
							doctorlistS.add(jRR2.getDoctorFlow());
						}
					}
					s1.setDoctorFlowsInOrg(doctorlistS);
					orgUniversity.add(s1);
				}
				Set<SysOrg> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
				personSet.addAll(orgUniversity);
				distinctOrgUniversity = new ArrayList<>(personSet);
				for(SysOrg s:distinctOrgUniversity){
					orgFlows.add(s.getOrgFlow());
				}
			}
			param.put("orgFlows", orgFlows); //所在高校机构
		} else if (GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {//市局
			if (StringUtil.isBlank(orgFlow)) {
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId("320000");
				searchOrg.setOrgCityId(currOrg.getOrgCityId());
				searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
				searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
					for(SysOrg join:orgList){
						if (!orgFlows.contains(join.getOrgFlow())) {
							orgFlows.add(join.getOrgFlow());
						}
					}
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
					}
				}

			}
			param.put("orgFlows", orgFlows);
		} else if (GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {//省厅
			if (StringUtil.isBlank(orgFlow)) {
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId("320000");
				searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
				searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
					for(SysOrg join:orgList){
						if (!orgFlows.contains(join.getOrgFlow())) {
							orgFlows.add(join.getOrgFlow());
						}
					}
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
					}
				}
			}
			param.put("orgFlows", orgFlows);
		}
		//PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> list1 = jsResDoctorRecruitBiz.searchOrgNotUseAppDoc(param);
		List<Map<String, String>> newList =new ArrayList<>();
		if(GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)){
			if(resDoctorRecruitExtsList.size()>0){
				for(Map<String, String> map:list1){
					Boolean flag=false;
					String userFlow=map.get("userFlow");
					for(JsResDoctorRecruitExt jr:resDoctorRecruitExtsList){
						if(userFlow.equals(jr.getDoctorFlow())){
							flag=true;
							break;
						}
					}
					if(flag){
						newList.add(map);
					}
				}
			}
			list1=newList;
		}
		Page list=pageGetInfo(currentPage,getPageSize(request),list1);
		model.addAttribute("list", list);
		return "res/platform/monthlyReport/appNotUseList";
	}

	/**
	 * app使用详细信息 导出（未使用用户信息）//
	 * @param request
	 * @param model
	 * @param userListScope
	 * @param orgFlow
	 * @param datas
	 * @param trainingSpeId
	 * @param sessionNumber
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel( HttpServletRequest request, Model model,String monthDate,
							String userListScope, String orgFlow, String[] datas,
							String trainingSpeId, String sessionNumber,HttpServletResponse response) throws Exception{
		Map<String, Object> param = new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		/*param.put("orgLevel", "CountryOrg");//国家基地*/
		param.put("startDate", monthDate);
		param.put("endDate", monthDate);
		param.put("orgFlow", orgFlow);
		param.put("docTypeList", docTypeList);
		param.put("trainingTypeId", "DoctorTrainingSpe");//住院医师
		param.put("trainingSpeId", trainingSpeId);
		param.put("sessionNumber", sessionNumber);
		param.put("userListScope", userListScope);

		List<String> orgFlows = new ArrayList<>();
		param.put("orgFlows", orgFlows);

		SysUser user = GlobalContext.getCurrentUser();
		SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
		String currOrgName = currOrg.getOrgName();
		Map<String, Object> map1 = new HashMap<>();
		List<SysOrg> orgUniversity = new ArrayList<>();
		List<SysOrg> distinctOrgUniversity = new ArrayList<>();
		List<JsResDoctorRecruitExt> resDoctorRecruitExtsList=new ArrayList<>();
		if (GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)) {//高校
			map1.put("universityName", currOrgName);
			resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map1);
			if (StringUtil.isBlank(orgFlow)) {
				for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
					SysOrg s1 = new SysOrg();
					List<String> doctorlistS = new ArrayList<>();
					s1.setOrgFlow(jRR.getOrgFlow());
					s1.setOrgName(jRR.getOrgName());
					for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
						if (orgFlow.equals(jRR2.getOrgFlow())) {
							doctorlistS.add(jRR2.getDoctorFlow());
						}
					}
					s1.setDoctorFlowsInOrg(doctorlistS);
					orgUniversity.add(s1);
				}
				Set<SysOrg> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
				personSet.addAll(orgUniversity);
				distinctOrgUniversity = new ArrayList<>(personSet);
				for(SysOrg s:distinctOrgUniversity){
					orgFlows.add(s.getOrgFlow());
				}
			}
			param.put("orgFlows", orgFlows); //所在高校机构
		} else if (GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {//市局
			if (StringUtil.isBlank(orgFlow)) {
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId("320000");
				searchOrg.setOrgCityId(currOrg.getOrgCityId());
				searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
				searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
					for(SysOrg join:orgList){
						if (!orgFlows.contains(join.getOrgFlow())) {
							orgFlows.add(join.getOrgFlow());
						}
					}
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
					}
				}

			}
			param.put("orgFlows", orgFlows);
		} else if (GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {//省厅
			if (StringUtil.isBlank(orgFlow)) {
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId("320000");
				searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
				searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
					for(SysOrg join:orgList){
						if (!orgFlows.contains(join.getOrgFlow())) {
							orgFlows.add(join.getOrgFlow());
						}
					}
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
					}
				}
			}
			param.put("orgFlows", orgFlows);
		}
		List<Map<String, String>> list1 = jsResDoctorRecruitBiz.searchOrgNotUseAppDoc(param);
		List<Map<String, String>> newList =new ArrayList<>();
		if(GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)){
			if(resDoctorRecruitExtsList.size()>0){
				for(Map<String, String> map:list1){
					Boolean flag=false;
					String userFlow=map.get("userFlow");
					for(JsResDoctorRecruitExt jr:resDoctorRecruitExtsList){
						if(userFlow.equals(jr.getDoctorFlow())){
							flag=true;
							break;
						}
					}
					if(flag){
						newList.add(map);
					}
				}
			}
			list1=newList;
		}
		String fileName = "未使用APP学员明细.xls";
		String []titles = new String[]{
				"userName:学员姓名",
				"orgName:培训基地",
				"sessionNumber:年级",
				"speName:培训专业",
				"userPhone:手机号码",
				"idNo:证件号码"
		};
		ExcleUtile.exportSimpleExcleByObjs(titles, list1, response.getOutputStream());
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

    /**
     * 出科异常详细信息
     * @return
     */
    @RequestMapping("/initDoctorOutOfficeInfoDetail")
	public String initDoctorOutOfficeInfoDetail(Model model){
        SysUser user = GlobalContext.getCurrentUser();
        List<SysOrg> orgs = new ArrayList<>();
        try {
            String role =getRoleFlag();
            if(!"".equals(role)){
                //高校角色
                if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
                    SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
                    String currOrgName = currOrg.getOrgName();

                    Boolean gaoxiaoFlg = false;
                    List<SysDict> sendSchools = DictTypeEnum.sysListDictMap.get(DictTypeEnum.SendSchool.getId());
                    if (sendSchools != null && sendSchools.size() > 0) {
                        for (SysDict dict : sendSchools) {
                            if (currOrgName.equals(dict.getDictName())) {
                                gaoxiaoFlg = true;
                            }
                        }
                    }
                    if(gaoxiaoFlg){
                        Map<String, Object> map1 = new HashMap<>();
                        List<SysOrg> orgUniversity = new ArrayList<>();
                        List<SysOrg> distinctOrgUniversity = new ArrayList<>();
                        map1.put("universityName", currOrgName);
                        List<JsResDoctorRecruitExt> resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map1);
                        for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
                            SysOrg s1 = new SysOrg();
                            List<String> doctorlistS = new ArrayList<>();
                            String orgFlow = jRR.getOrgFlow();
                            String orgName = jRR.getOrgName();
                            s1.setOrgFlow(orgFlow);
                            s1.setOrgName(orgName);
                            for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
                                if (orgFlow.equals(jRR2.getOrgFlow())) {
                                    doctorlistS.add(jRR2.getDoctorFlow());
                                }
                            }
                            s1.setDoctorFlowsInOrg(doctorlistS);
                            orgUniversity.add(s1);
                        }
                        Set<SysOrg> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
                        personSet.addAll(orgUniversity);
                        distinctOrgUniversity = new ArrayList<>(personSet);
                        model.addAttribute("orgs",distinctOrgUniversity);
                        model.addAttribute("userListScope",role);
                    }else{
                        throw new RuntimeException("请联系管理员“派送学校”是否有“"+currOrgName+"”高校");
                    }

                }else{
                    SysOrg searchOrg=new SysOrg();
                    SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
                    searchOrg.setOrgProvId(currOrg.getOrgProvId());
                    ///省级
                    if(role.equals(GlobalConstant.USER_LIST_CHARGE)){
                        searchOrg.setOrgCityId(currOrg.getOrgCityId());
                    }
                    searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                    searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                    searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    List<SysOrg> exitOrgs=orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
                    orgs.addAll(exitOrgs);
                    for(int i=0;i<exitOrgs.size();i++) {
                        List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
                        orgs.addAll(resJointOrgList);
                    }
                    model.addAttribute("orgs",orgs);
                    model.addAttribute("userListScope",role);
                }

            }
        }catch (RuntimeException e){
            e.printStackTrace();
            if(null==e.getMessage()){
                model.addAttribute("error","null");
            }else{
                model.addAttribute("error",e.getMessage());
            }
            return "res/platform/monthlyReport/doctorOutOfficeDetail";
        }
        return "res/platform/monthlyReport/doctorOutOfficeDetail";
    }

    /**
     *
        出科异常学员信息
     * @return
     */
    @RequestMapping("/doctorOutOfficeUseList")
    public String doctorOutOfficeUseList(Integer currentPage, HttpServletRequest request, Model model,String monthDate,
                                String userListScope, String orgFlow, String[] datas,
                                String trainingSpeId, String sessionNumber) {
        Map<String, Object> param = new HashMap<>();
        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
      /*  param.put("orgLevel", "CountryOrg");*///国家基地
        param.put("orgFlow", orgFlow);
        param.put("docTypeList", docTypeList);
        param.put("trainingTypeId", "DoctorTrainingSpe");//住院医师
        param.put("trainingSpeId", trainingSpeId);
        param.put("sessionNumber", sessionNumber);
        param.put("userListScope", userListScope);

        List<String> orgFlows = new ArrayList<>();
        param.put("orgFlows", orgFlows);

        SysUser user = GlobalContext.getCurrentUser();
        SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
        String currOrgName = currOrg.getOrgName();
        Map<String, Object> map1 = new HashMap<>();
        List<SysOrg> orgUniversity = new ArrayList<>();
        List<SysOrg> distinctOrgUniversity = new ArrayList<>();
        List<JsResDoctorRecruitExt> resDoctorRecruitExtsList=new ArrayList<>();
        if (GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)) {//高校
            map1.put("universityName", currOrgName);
            resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map1);
            if (StringUtil.isBlank(orgFlow)) {
                for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
                    SysOrg s1 = new SysOrg();
                    List<String> doctorlistS = new ArrayList<>();
                    s1.setOrgFlow(jRR.getOrgFlow());
                    s1.setOrgName(jRR.getOrgName());
                    for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
                        if (orgFlow.equals(jRR2.getOrgFlow())) {
                            doctorlistS.add(jRR2.getDoctorFlow());
                        }
                    }
                    s1.setDoctorFlowsInOrg(doctorlistS);
                    orgUniversity.add(s1);
                }
                Set<SysOrg> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
                personSet.addAll(orgUniversity);
                distinctOrgUniversity = new ArrayList<>(personSet);
                for(SysOrg s:distinctOrgUniversity){
                    orgFlows.add(s.getOrgFlow());
                }
            }
            param.put("orgFlows", orgFlows); //所在高校机构
        } else if (GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {//市局
            if (StringUtil.isBlank(orgFlow)) {
                SysOrg searchOrg=new SysOrg();
                searchOrg.setOrgProvId("320000");
                searchOrg.setOrgCityId(currOrg.getOrgCityId());//市
                searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
                for(SysOrg g:exitOrgs){
                    List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
                    for(SysOrg join:orgList){
                        if (!orgFlows.contains(join.getOrgFlow())) {
                            orgFlows.add(join.getOrgFlow());
                        }
                    }
                    if (!orgFlows.contains(g.getOrgFlow())) {
                        orgFlows.add(g.getOrgFlow());
                    }
                }
            }
            param.put("orgFlows", orgFlows);
        } else if (GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {//省厅
            if (StringUtil.isBlank(orgFlow)) {
                SysOrg searchOrg=new SysOrg();
                searchOrg.setOrgProvId("320000");
                searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
                for(SysOrg g:exitOrgs){
                    List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
                    for(SysOrg join:orgList){
                        if (!orgFlows.contains(join.getOrgFlow())) {
                            orgFlows.add(join.getOrgFlow());
                        }
                    }
                    if (!orgFlows.contains(g.getOrgFlow())) {
                        orgFlows.add(g.getOrgFlow());
                    }
                }
            }
            param.put("orgFlows", orgFlows);
        }
        //PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> list1 = monthlyReportExtMapper2.findDoctorOutOffice(param); //根据条件获取所有（正常和异常）出科学员信息
         list1= getExceptionDoctorOutOfficeInfo(list1,monthDate); //根据条件获取（正常和异常）出科学员信息-筛选年月、异常，学员数据
        List<Map<String, String>> newList =new ArrayList<>();
        if(GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)){
            if(resDoctorRecruitExtsList.size()>0){
                for(Map<String, String> map:list1){
                    Boolean flag=false;
                    String userFlow=map.get("doctorFlow");
                    for(JsResDoctorRecruitExt jr:resDoctorRecruitExtsList){
                        if(userFlow.equals(jr.getDoctorFlow())){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        newList.add(map);
                    }
                }
            }
            list1=newList;
        }
        Page list=pageGetInfo(currentPage,getPageSize(request),list1);
        model.addAttribute("list", list);
        return "res/platform/monthlyReport/doctorOutOfficeUseList";
    }

    /**
     *
     * 出科异常学员信息 excel
     * @void
     */
    @RequestMapping("/exportExcelDoctorOutOffice")
    public void exportExcelDoctorOutOffice( HttpServletRequest request, Model model,String monthDate,
                                         String userListScope, String orgFlow, String[] datas,
                                         String trainingSpeId, String sessionNumber ,HttpServletResponse response) throws Exception {
        Map<String, Object> param = new HashMap<>();
        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        /*  param.put("orgLevel", "CountryOrg");*///国家基地
        param.put("orgFlow", orgFlow);
        param.put("docTypeList", docTypeList);
        param.put("trainingTypeId", "DoctorTrainingSpe");//住院医师
        param.put("trainingSpeId", trainingSpeId);
        param.put("sessionNumber", sessionNumber);
        param.put("userListScope", userListScope);

        List<String> orgFlows = new ArrayList<>();
        param.put("orgFlows", orgFlows);

        SysUser user = GlobalContext.getCurrentUser();
        SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
        String currOrgName = currOrg.getOrgName();
        Map<String, Object> map1 = new HashMap<>();
        List<SysOrg> orgUniversity = new ArrayList<>();
        List<SysOrg> distinctOrgUniversity = new ArrayList<>();
        List<JsResDoctorRecruitExt> resDoctorRecruitExtsList=new ArrayList<>();
        if (GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)) {//高校
            map1.put("universityName", currOrgName);
            resDoctorRecruitExtsList = monthlyReportExtMapper2.getUserInfoAndOrgNameByUniversity(map1);
            if (StringUtil.isBlank(orgFlow)) {
                for (JsResDoctorRecruitExt jRR : resDoctorRecruitExtsList) {
                    SysOrg s1 = new SysOrg();
                    List<String> doctorlistS = new ArrayList<>();
                    s1.setOrgFlow(jRR.getOrgFlow());
                    s1.setOrgName(jRR.getOrgName());
                    for (JsResDoctorRecruitExt jRR2 : resDoctorRecruitExtsList) {
                        if (orgFlow.equals(jRR2.getOrgFlow())) {
                            doctorlistS.add(jRR2.getDoctorFlow());
                        }
                    }
                    s1.setDoctorFlowsInOrg(doctorlistS);
                    orgUniversity.add(s1);
                }
                Set<SysOrg> personSet = new TreeSet<>((o1, o2) -> o1.getOrgFlow().compareTo(o2.getOrgFlow()));
                personSet.addAll(orgUniversity);
                distinctOrgUniversity = new ArrayList<>(personSet);
                for(SysOrg s:distinctOrgUniversity){
                    orgFlows.add(s.getOrgFlow());
                }
            }
            param.put("orgFlows", orgFlows); //所在高校机构
        } else if (GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {//市局
            if (StringUtil.isBlank(orgFlow)) {
                SysOrg searchOrg=new SysOrg();
                searchOrg.setOrgProvId("320000");
                searchOrg.setOrgCityId(currOrg.getOrgCityId());//市
                searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
                for(SysOrg g:exitOrgs){
                    List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
                    for(SysOrg join:orgList){
                        if (!orgFlows.contains(join.getOrgFlow())) {
                            orgFlows.add(join.getOrgFlow());
                        }
                    }
                    if (!orgFlows.contains(g.getOrgFlow())) {
                        orgFlows.add(g.getOrgFlow());
                    }
                }
            }
            param.put("orgFlows", orgFlows);
        } else if (GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {//省厅
            if (StringUtil.isBlank(orgFlow)) {
                SysOrg searchOrg=new SysOrg();
                searchOrg.setOrgProvId("320000");
                searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
                searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
                searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
                for(SysOrg g:exitOrgs){
                    List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
                    for(SysOrg join:orgList){
                        if (!orgFlows.contains(join.getOrgFlow())) {
                            orgFlows.add(join.getOrgFlow());
                        }
                    }
                    if (!orgFlows.contains(g.getOrgFlow())) {
                        orgFlows.add(g.getOrgFlow());
                    }
                }
            }
            param.put("orgFlows", orgFlows);
        }
        //PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> list1 = monthlyReportExtMapper2.findDoctorOutOffice(param); //根据条件获取（正常和异常）出科学员信息
        list1= getExceptionDoctorOutOfficeInfo(list1,monthDate); //根据条件获取（正常和异常）出科学员信息-筛选获取异常学员数据
        List<Map<String, String>> newList =new ArrayList<>();
        if(GlobalConstant.USER_LIST_UNIVERSITY.equals(userListScope)){
            if(resDoctorRecruitExtsList.size()>0){
                for(Map<String, String> map:list1){
                    Boolean flag=false;
                    String userFlow=map.get("doctorFlow");
                    for(JsResDoctorRecruitExt jr:resDoctorRecruitExtsList){
                        if(userFlow.equals(jr.getDoctorFlow())){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        newList.add(map);
                    }
                }
            }
            list1=newList;
        }
        String fileName = "出科异常学员明细.xls";
        String []titles = new String[]{
                "userName:学员姓名",
                "orgName:培训基地",
                "sessionNumber:年级",
                "trainingSpeName:培训专业",
                "userPhone:手机号码",
                "idNo:证件号码"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, list1, response.getOutputStream());
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");

    }
    /*根据所有学员出科信息筛选获取异常学员数据*/
   public List<Map<String,String>> getExceptionDoctorOutOfficeInfo(List<Map<String,String>>  list,String monthDate){
        List<Map<String,String>> exceptionList=new ArrayList<>();

        for(Map<String,String> mapInfo:list){
            Map<String,String> doctorMap=new HashMap<>();
            String orgFlow= mapInfo.get("orgFlow");
            doctorMap.put("doctorFlow",(String) mapInfo.get("doctorFlow"));
            String cksh="N";
            JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_cksh");
            if(cfg!=null)
            {
                cksh=cfg.getCfgValue();
            }
            List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(doctorMap);
            Boolean isException = getArrResultInfo(arrResultList,cksh,monthDate);
            if(isException){
            	//20200520 修改 当前出科时间的以前招录sessionNumber(1.先修改sql、专业和年级对应的招录表而不是医生表2.将招录重复的数据根据年级筛选)
				for(SchArrangeResult schArrangeResult:arrResultList){
					if(StringUtil.isNotBlank(schArrangeResult.getSchEndDate())){
						String schEndDate= schArrangeResult.getSchEndDate().substring(0,7);
						if(monthDate.equals(schEndDate)){
							if(mapInfo.get("sessionNumber").equals(schArrangeResult.getSessionNumber())){
								exceptionList.add(mapInfo);
								break;
							}
						}
					}
				}
				//20200520 修改 当前出科时间的以前招录sessionNumber(1.先修改sql、专业和年级对应的招录表而不是医生表2.将招录重复的数据根据年级筛选)
               // exceptionList.add(mapInfo);
            }
        }
        return exceptionList;
    }
    public Boolean getArrResultInfo(List<SchArrangeResult> arrResultList,String cksh,String monthDate){
        Integer count=0;
        Integer noCkshCount=0;
		Integer flg=0;
        for(SchArrangeResult schArrangeResult:arrResultList){
            String resultFlow = schArrangeResult.getResultFlow();
            String schEndDateT= schArrangeResult.getSchEndDate();
            if(StringUtil.isNotBlank(schEndDateT)){
				String schEndDate= schEndDateT.substring(0,7);
				if(monthDate.equals(schEndDate)){

					ResDoctorSchProcess doctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
					if(doctorSchProcess!=null){
						String processFlow = doctorSchProcess.getProcessFlow();
						List<ResSchProcessExpress> resRecs = expressBiz.searchByProcessFlowClob(processFlow);
						if(resRecs!=null&&resRecs.size()>0){
							for(ResSchProcessExpress r:resRecs){
								//出科考核表
								if(AfterRecTypeEnum.AfterEvaluation.getId().equals(r.getRecTypeId())){
									//如果科主任审核
									if(cksh.equals("Y")){
										//科主任通过
										if(RecStatusEnum.HeadAuditY.getId().equals(r.getHeadAuditStatusId())){
											count++;
										}
									}else{
										//带教老师通过
										if(RecStatusEnum.TeacherAuditY.getId().equals(r.getAuditStatusId())){
											count++;
										}
									}

								}
							}
						}
					}
					if(count==1){
						noCkshCount++;
						flg++;
					}else{
						noCkshCount--;
						flg++;
					}
				}
			}
        }
        if(noCkshCount>=1){
            return false;
        } if(noCkshCount<=-1){
        	return true;
		}
		if(flg==2){
			return true;
		}else{
        	return false;
		}
    }
	/*重写分页*/
	public Page pageGetInfo(Integer pageNumber,Integer pageSize,List<Map<String,String>> list){
		Page MemberArticleBeanPage=new Page(pageNumber,pageSize,list.size());
		int totalPage = list.size() / pageSize + ((list.size() % pageSize == 0) ? 0 : 1);
		MemberArticleBeanPage.setPages(totalPage);
		/*List<Map<String,String>> MemberArticleBeanPage = new ArrayList<>();*/
		int currIdx = (pageNumber > 1 ? (pageNumber -1) * pageSize : 0);
		for (int i = 0; i < pageSize && i < list.size() - currIdx; i++) {
			Map<String,String> memberArticleBean = list.get(currIdx + i);
			memberArticleBean.put("ROW_ID",(i+1)+"");
			MemberArticleBeanPage.add(memberArticleBean);
		}
		return MemberArticleBeanPage;
	}
	//排序
	public List listSort(String value,List<SysOrg> list){
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(4);//设置精确到小数点后4位
		if(!"".equals(value)){
			List<SysOrg> firstList=new ArrayList<>();
			List<SysOrg> secondList=new ArrayList<>();
			for(SysOrg li:list){
				if("".equals(li.getParentOrgFlow()) || null==li.getParentOrgFlow()){
					firstList.add(li);
				}
				if(!"".equals(li.getParentOrgFlow()) && null!=li.getParentOrgFlow()){
					secondList.add(li);
				}
			}
			if("doctorDesc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double doctorRate1=0.00;
						Double doctorRate2=0.00;
						if(null!=o1.getDoctorRate()&& !"".equals(o1.getDoctorRate())){
						    doctorRate1 = Double.parseDouble(o1.getDoctorRate().replace("%",""));
						}
						if(null!=o2.getDoctorRate()&& !"".equals(o2.getDoctorRate())){
							 doctorRate2 =Double.parseDouble(o2.getDoctorRate().replace("%",""));
						}
						//降序
						return doctorRate2.compareTo(doctorRate1);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号

			}else if("doctorAsc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double doctorRate1=0.00;
						Double doctorRate2=0.00;
						if(null!=o1.getDoctorRate()&& !"".equals(o1.getDoctorRate())){
							doctorRate1 = Double.parseDouble(o1.getDoctorRate().replace("%",""));
						}
						if(null!=o2.getDoctorRate()&& !"".equals(o2.getDoctorRate())){
							doctorRate2 =Double.parseDouble(o2.getDoctorRate().replace("%",""));
						}
						//升序
						return doctorRate1.compareTo(doctorRate2);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("masterDesc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double masterRate1=0.00;
						Double masterRate2=0.00;
						if(null!=o1.getMasterRate()&& !"".equals(o1.getMasterRate())){
							masterRate1 = Double.parseDouble(o1.getMasterRate().replace("%",""));
						}
						if(null!=o2.getMasterRate()&& !"".equals(o2.getMasterRate())){
							masterRate2 =Double.parseDouble(o2.getMasterRate().replace("%",""));
						}
						//降序
						return masterRate2.compareTo(masterRate1);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("masterAsc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double masterRate1=0.00;
						Double masterRate2=0.00;
						if(null!=o1.getMasterRate()&& !"".equals(o1.getMasterRate())){
							masterRate1 = Double.parseDouble(o1.getMasterRate().replace("%",""));
						}
						if(null!=o2.getMasterRate()&& !"".equals(o2.getMasterRate())){
							masterRate2 =Double.parseDouble(o2.getMasterRate().replace("%",""));
						}
						//升序
						return masterRate1.compareTo(masterRate2);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("synthDesc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double rate1=0.00;
						Double rate2=0.00;
						if(null!=o1.getRate()&& !"".equals(o1.getRate())){
							rate1 = Double.parseDouble(o1.getRate().replace("%",""));
						}
						if(null!=o2.getRate()&& !"".equals(o2.getRate())){
							rate2 =Double.parseDouble(o2.getRate().replace("%",""));
						}
						//降序
						return rate2.compareTo(rate1);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("synthAsc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double rate1=0.00;
						Double rate2=0.00;
						if(null!=o1.getRate()&& !"".equals(o1.getRate())){
							rate1 = Double.parseDouble(o1.getRate().replace("%",""));
						}
						if(null!=o2.getRate()&& !"".equals(o2.getRate())){
							rate2 =Double.parseDouble(o2.getRate().replace("%",""));
						}
						//升序
						return rate1.compareTo(rate2);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("auditDesc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double auditRate1=0.00;
						Double auditRate2=0.00;
						if(null!=o1.getAuditRate()&& !"".equals(o1.getAuditRate())){
							auditRate1 = Double.parseDouble(o1.getAuditRate().replace("%",""));
						}
						if(null!=o2.getAuditRate()&& !"".equals(o2.getAuditRate())){
							auditRate2 =Double.parseDouble(o2.getAuditRate().replace("%",""));
						}
						//降序
						return auditRate2.compareTo(auditRate1);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("auditOrder".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double auditRate1=0.00;
						Double auditRate2=0.00;
						if(null!=o1.getAuditRate()&& !"".equals(o1.getAuditRate())){
							auditRate1 = Double.parseDouble(o1.getAuditRate().replace("%",""));
						}
						if(null!=o2.getAuditRate()&& !"".equals(o2.getAuditRate())){
							auditRate2 =Double.parseDouble(o2.getAuditRate().replace("%",""));
						}
						//升序
						return auditRate1.compareTo(auditRate2);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("FillDesc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double avgFillNum1=0.00;
						Double avgFillNum2=0.00;
						if(null!=o1.getAvgfillNum()&& !"".equals(o1.getAvgfillNum())){
							avgFillNum1 = Double.parseDouble(o1.getAvgfillNum());
						}
						if(null!=o2.getAvgfillNum()&& !"".equals(o2.getAvgfillNum())){
							avgFillNum2 =Double.parseDouble(o2.getAvgfillNum());
						}
						//降序
						return avgFillNum2.compareTo(avgFillNum1);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("FillOrder".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double avgFillNum1=0.00;
						Double avgFillNum2=0.00;
						if(null!=o1.getAvgfillNum()&& !"".equals(o1.getAvgfillNum())){
							avgFillNum1 = Double.parseDouble(o1.getAvgfillNum());
						}
						if(null!=o2.getAvgfillNum()&& !"".equals(o2.getAvgfillNum())){
							avgFillNum2 =Double.parseDouble(o2.getAvgfillNum());
						}
						//升序
						return avgFillNum1.compareTo(avgFillNum2);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("avgAuditDesc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double avgAuditNum1=0.00;
						Double avgAuditNum2=0.00;
						if(null!=o1.getFillNum()&& !"".equals(o1.getFillNum())&& !"".equals(o1.getTrainDoctorTotal())&& null!=o1.getTrainDoctorTotal()){
							avgAuditNum1 = Double.parseDouble(numberFormat.format((float) o1.getAuditNum() / (float) o1.getFillNum() /o1.getTrainDoctorTotal()));

						}
						if(null!=o2.getFillNum()&& !"".equals(o2.getFillNum())&& !"".equals(o2.getTrainDoctorTotal())&& null!=o2.getTrainDoctorTotal()){
							avgAuditNum2 = Double.parseDouble(numberFormat.format((float) o2.getAuditNum() / (float) o2.getFillNum() /o2.getTrainDoctorTotal()));
						}
						//降序
						return avgAuditNum2.compareTo(avgAuditNum1);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}else if("avgAuditOrder".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double avgAuditNum1=0.00;
						Double avgAuditNum2=0.00;
						if(null!=o1.getFillNum()&& !"".equals(o1.getFillNum())&& !"".equals(o1.getTrainDoctorTotal())&& null!=o1.getTrainDoctorTotal()){
							avgAuditNum1 = Double.parseDouble(numberFormat.format((float) o1.getAuditNum() / (float) o1.getFillNum() /o1.getTrainDoctorTotal()));

						}
						if(null!=o2.getFillNum()&& !"".equals(o2.getFillNum())&& !"".equals(o2.getTrainDoctorTotal())&& null!=o2.getTrainDoctorTotal()){
							avgAuditNum2 = Double.parseDouble(numberFormat.format((float) o2.getAuditNum() / (float) o2.getFillNum() /o2.getTrainDoctorTotal()));
						}
						//升序
						return avgAuditNum1.compareTo(avgAuditNum2);
					}
				});
				list = listSetNo(firstList,secondList);//重新设置NO序号
			}
		}
		return list;
	}
	public List<SysOrg> listSetNo(List<SysOrg> firstList,List<SysOrg> secondList){
		for(int i=0;i<firstList.size();i++){
			firstList.get(i).setNo(/*(i+1)+""*/firstList.get(i).getOrgCode());
			String orgflow =firstList.get(i).getOrgFlow();
			int count=0;
			for(int j=0;j<secondList.size();j++){
				if(secondList.get(j).getParentOrgFlow().equals(orgflow)){
					secondList.get(j).setNo(/*(i+1)*/firstList.get(i).getOrgCode()+"-"+(count+1));
					count++;
				}
			}
		}
		firstList.addAll(secondList);
		return firstList;
	}

	/***
	 * 获取角色标识（如：global charge local 等）
	 * @return
	 */
	public String getRoleFlag(){
		List<Map<String,String>>  rolelistInfo=getRoles();
		List<String> roleUrlList=new ArrayList<>();
		for(Map<String,String> map:rolelistInfo){
			String roleurl = map.get("roleIndex");
			if(StringUtil.isNotBlank(roleurl)){
				//省级 主管部门  高校
				if("/jsres/manage/global".equals(roleurl) || "/jsres/manage/charge".equals(roleurl) || "/jsres/manage/university".equals(roleurl)){
					String flag =roleurl.split("/")[3];
					roleUrlList.add(flag);
				}
			}
		}
		if(roleUrlList.size()>0){
			if(roleUrlList.contains("global") && roleUrlList.contains("charge") ){
				throw new RuntimeException("请联系管理员，角色不能既是省级部门又是主管部门");
			}else if(roleUrlList.contains("global")  && roleUrlList.contains("university")){
				throw new RuntimeException("请联系管理员，角色不能既是省级部门又是高校");
			}else if(roleUrlList.contains("charge")  && roleUrlList.contains("university")){
				throw new RuntimeException("请联系管理员，角色不能既是主管部门又是高校");
			}else if(roleUrlList.contains("global") && roleUrlList.contains("charge")  && roleUrlList.contains("university")){
				throw new RuntimeException("请联系管理员，角色不能同时省级部门、主管部门、高校");
			}else{
				return roleUrlList.get(0);
			}
		}
		return "";
	}
	public List<Map<String,String>> getRoles(){
		List<Map<String,String>> roles=new ArrayList<>();
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		List<String> currRoleList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST);
			for(String roleFlow : currRoleList) {
				Map<String, String> role = getRoleUrl(roleFlow);
				if (role != null) {
					roles.add(role);
				}
			}
			return roles;
	}
	public Map<String,String> getRoleUrl(String roleFlow){
		if (StringUtil.isNotBlank(roleFlow)){
			Map<String,String> role = new HashMap<String, String>();
			if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
				role.put("roleName",InitConfig.getSysCfgDesc("res_global_role_flow"));
				role.put("roleIndex","/jsres/manage/global");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_qkzx_role_flow"))) {//审核部门之全科中心
				role.put("roleName",InitConfig.getSysCfgDesc("res_qkzx_role_flow"));
				role.put("roleIndex","/jsres/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_bjw_role_flow"))) {//审核部门之毕教委
				role.put("roleName",InitConfig.getSysCfgDesc("res_bjw_role_flow"));
				role.put("roleIndex","/jsres/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_zyglj_role_flow"))) {//审核部门之中医管理局
				role.put("roleName",InitConfig.getSysCfgDesc("res_zyglj_role_flow"));
				role.put("roleIndex","/jsres/manage/province");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_school_role_flow"))) {//学校
				role.put("roleName",InitConfig.getSysCfgDesc("res_school_role_flow"));
				role.put("roleIndex","/jsres/manage/school");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))) {//主管部门
				role.put("roleName",InitConfig.getSysCfgDesc("res_charge_role_flow"));
				role.put("roleIndex","/jsres/manage/charge");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
				role.put("roleName",InitConfig.getSysCfgDesc("res_admin_role_flow"));
				role.put("roleIndex","/jsres/manage/local");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow"))) {//科主任
				role.put("roleName",InitConfig.getSysCfgDesc("res_head_role_flow"));
				role.put("roleIndex","/jsres/kzr/index");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_secretary_role_flow"))) {//科秘
				role.put("roleName",InitConfig.getSysCfgDesc("res_secretary_role_flow"));
				role.put("roleIndex","/jsres/km/index");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {//带教老师
				role.put("roleName",InitConfig.getSysCfgDesc("res_teacher_role_flow"));
				role.put("roleIndex","/jsres/teacher/index");
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				role.put("roleName",InitConfig.getSysCfgDesc("res_doctor_role_flow"));
				role.put("roleIndex","/jsres/doctor/index");
			}
			else if (roleFlow.equals(InitConfig.getSysCfg("res_university_role_flow"))) {//高校管理员角色
				role.put("roleName",InitConfig.getSysCfgDesc("res_university_role_flow"));
				role.put("roleIndex","/jsres/manage/university");
			}
			else if (roleFlow.equals(InitConfig.getSysCfg("res_university_manager_role_flow"))) {//高校管理员角色
				role.put("roleName",InitConfig.getSysCfgDesc("res_university_manager_role_flow"));
				role.put("roleIndex","/jsres/manage/university");
			}
			return role;
		}
		return null;
	}

	@RequestMapping("/rotationStatistics")
	public String rotationStatistics(Model model){
		return "res/platform/statistics/rotationStatistics";
	}

	@RequestMapping("/personStatistic")

	public String personStatistic(Model model) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String monthDate = DateUtil.getCurrDate().substring(0,7);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(monthDate));
		c.add(Calendar.MONTH,-1);
		String lastMonth = sdf.format(c.getTime());
		return "res/platform/statistics/personStatistic";
	}

	public String monthZero(int month){
		String StrMonth="";
		if(month<10){
			StrMonth="0"+month;
		}else{
			StrMonth=""+month;
		}
		return StrMonth;
	}
	/**
	 *  @author:
	 *  @Date: 2020/1/27 11:13
	 *  @Description: topage 人员异动
	 */
	@RequestMapping(value = {"/toPageDelaySpeChange"})
	public String toPageDelaySpeChange(Model model, String roleFlag) {
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/global/hospital/personChange";
	}

	/**
	 *  @author:
	 *  @Date: 2020/1/27 15:51
	 *  @Description 初始化人员异动
	 */
	@RequestMapping(value = {"/initDelaySpeChange"})
	@ResponseBody
	public List initDelaySpeChange(Model model, String roleFlag2,String isContain2,String monthDate2) {
		/*Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String lastMonthdate = year + monthZero(month-1);   //上月数据*/
		String lastMonth= DateUtil.addMonth(monthDate2,-1);
		String lastMonthdate = lastMonth.split("-")[0]+lastMonth.split("-")[1]; /*year + monthZero(month-1)*/;

		SysUser user = GlobalContext.getCurrentUser();
		List<PersonChangeEntity> staticData = new ArrayList<>();
		List<SysOrg> orgsList = new ArrayList<>();
		try {
			if (GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag2)) {
				List li = resMonthlyReportGlobalControllerClass.gaoxiaopersonChange(lastMonthdate, isContain2);
				return li;
			}
			SysOrg searchOrg = new SysOrg();
			SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
			searchOrg.setOrgProvId(currOrg.getOrgProvId());
			if (roleFlag2.equals(GlobalConstant.USER_LIST_CHARGE)) {
				searchOrg.setOrgCityId(currOrg.getOrgCityId());
			}
			searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			searchOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
			searchOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<SysOrg> countryOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
			for (int i = 0; i < countryOrgs.size(); i++) {
				countryOrgs.get(i).setParentOrgFlow("");
				List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(countryOrgs.get(i).getOrgFlow());
				if (jointOrgList != null && !jointOrgList.isEmpty()) {
					for (int j = 0; j < jointOrgList.size(); j++) {
						jointOrgList.get(j).setParentOrgFlow(countryOrgs.get(i).getOrgFlow());
						orgsList.add(jointOrgList.get(j));
					}
				}
				orgsList.add(countryOrgs.get(i));
			}
			//对象转化
			for (int i = 0; i < orgsList.size(); i++) {
				PersonChangeEntity staticExample = new PersonChangeEntity();
				BeanUtils.copyProperties(orgsList.get(i), staticExample);
				staticData.add(staticExample);
			}
			Integer changeTimeNum = 0;
			Integer speChangeNum = 0;
			//包含协同
			if ("isContain2".equals(isContain2)) {
				for (PersonChangeEntity personStaticExample : staticData) {
					String orgFlow = personStaticExample.getOrgFlow();
					Map<String, Object> parmMap = new HashMap<>();
					parmMap.put("orgFlow", orgFlow);
					parmMap.put("monthDate", lastMonthdate);//"201909"
					List<PersonChangeEntity> staticExample = monthlyReportExtMapper2.selectFindChangeSpe(parmMap);
					if(staticExample.size()>0){
						for (PersonChangeEntity orgStatic : staticExample) {
							if(null==orgStatic.getChangeTimeNum() || "".equals(orgStatic.getChangeTimeNum())){
								changeTimeNum = 0;
							}else{
								changeTimeNum = orgStatic.getChangeTimeNum();
							}
							if(null==orgStatic.getSpeChangeNum() || "".equals(orgStatic.getSpeChangeNum())){
								speChangeNum = 0;
							}else{
								speChangeNum = orgStatic.getSpeChangeNum();
							}
						}
					}else{
						changeTimeNum=0;
						speChangeNum=0;
					}
					for (PersonChangeEntity example22 : staticData) {
						if (orgFlow.equals(example22.getParentOrgFlow())) {
							Map<String, Object> parmMap22 = new HashMap<>();
							parmMap22.put("orgFlow", example22.getOrgFlow());
							parmMap22.put("monthDate", lastMonthdate);//"201909"
							List<PersonChangeEntity> staticExample22 = monthlyReportExtMapper2.selectFindChangeSpe(parmMap22);
							if(staticExample22.size()>0){
								PersonChangeEntity example=   staticExample22.get(0);
								if(null!=example.getChangeTimeNum()){
									changeTimeNum = changeTimeNum + example.getChangeTimeNum();
								}
								if(null!=example.getSpeChangeNum()){
									speChangeNum = speChangeNum + example.getSpeChangeNum();
								}
							}
						}
					}
					personStaticExample.setChangeTimeNum(changeTimeNum);
					personStaticExample.setSpeChangeNum(speChangeNum);

				}
				//不包含协同
			} else {
				for (PersonChangeEntity personStaticExample : staticData) {
					String orgFlow = personStaticExample.getOrgFlow();
					Map<String, Object> parmMap = new HashMap<>();
					parmMap.put("orgFlow", orgFlow);
					parmMap.put("monthDate", lastMonthdate);//"201909"
					List<PersonChangeEntity> staticExample = monthlyReportExtMapper2.selectFindChangeSpe(parmMap);
					if(staticExample.size()>0){
						for (PersonChangeEntity orgStatic : staticExample) {
							if(null==orgStatic.getChangeTimeNum() || "".equals(orgStatic.getChangeTimeNum())){
								changeTimeNum = 0;
							}else{
								changeTimeNum = orgStatic.getChangeTimeNum();
							}
							if(null==orgStatic.getSpeChangeNum() || "".equals(orgStatic.getSpeChangeNum())){
								speChangeNum = 0;
							}else{
								speChangeNum = orgStatic.getSpeChangeNum();
							}
						}
					}else{
						changeTimeNum=0;
						speChangeNum=0;
					}
					personStaticExample.setChangeTimeNum(changeTimeNum);
					personStaticExample.setSpeChangeNum(speChangeNum);

				}
			}
			return staticData;
		} catch (RuntimeException e) {
			List<Map<String, String>> list = new ArrayList<>();
			Map<String, String> map = new HashMap<>();
			map.put("error", e.getMessage());
			list.add(map);
			return list;
		}
	}
}
