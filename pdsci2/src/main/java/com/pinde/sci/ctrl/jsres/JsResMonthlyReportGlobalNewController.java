package com.pinde.sci.ctrl.jsres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResMonthlyReportBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Joq
 *
 */
@Controller
@RequestMapping("/jsres/monthlyReportGlobalNew")
public class JsResMonthlyReportGlobalNewController extends GeneralController {
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
		return "jsres/global/monthlyReportNew/monthlyReportMain";
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
        if (StringUtil.isNotBlank(monthDate)) {
            String monthString = monthDate.split("-")[0]+"年"+monthDate.split("-")[1]+"月";
            model.addAttribute("monthString",monthString);
        }else{
            monthDate = DateUtil.getMonth();
        }

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

		return "jsres/global/monthlyReportNew/content0";
	}

	@RequestMapping("/content1")
	public String content1(String baseRange,String monthDate,Model model){
		model.addAttribute("baseRange",baseRange);
		model.addAttribute("monthDate",monthDate);
		return "jsres/global/monthlyReport/content1";
	}

	@RequestMapping("/chart12")//图一二
	public String chart12(String monthDate,Model model) throws ParseException {
		long startTime = System.currentTimeMillis();
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorStatusId("20");
        search.setChangeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());

		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo2(search);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(monthDate));
		c.add(Calendar.MONTH,-1);
		String lastMonth = sdf.format(c.getTime());
		search.setDateMonth(lastMonth);
		List<SysMonthlyDoctorInfo> lastSysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo2(search);

		List<String> yearList = new ArrayList<>();
		Map<String,Integer> notGraduateMap = new HashMap<>();
		Map<String,Integer> graduateMap = new HashMap<>();
		Map<String,Integer> totalMap = new HashMap<>();
		Map<String,Integer> lastTotalMap = new HashMap<>();

		Map<String,Integer> sumMap = new HashMap<>();
		sumMap.put("notGraduate",0);
		sumMap.put("graduate",0);
		sumMap.put("total",0);
		sumMap.put("lastTotal",0);

		if(lastSysMonthlyDoctorInfoList!=null&&lastSysMonthlyDoctorInfoList.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:lastSysMonthlyDoctorInfoList){
				String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
				if(null==lastTotalMap.get(sessionNumber)){
					lastTotalMap.put(sessionNumber,1);
				}else{
					lastTotalMap.put(sessionNumber,lastTotalMap.get(sessionNumber)+1);
				}
				sumMap.put("lastTotal",sumMap.get("lastTotal")+1);
			}
		}

		if(sysMonthlyDoctorInfoList!=null&&sysMonthlyDoctorInfoList.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
				String doctorTypeId = sysMonthlyDoctorInfo.getDoctorTypeId();

				if(!yearList.contains(sessionNumber)){
					yearList.add(sessionNumber);
				}

				if(null==totalMap.get(sessionNumber)){
					totalMap.put(sessionNumber,1);
				}else{
					totalMap.put(sessionNumber,totalMap.get(sessionNumber)+1);
				}

				if(null==graduateMap.get(sessionNumber)){
                    if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
						graduateMap.put(sessionNumber,1);
					}
				}else{
                    if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
						graduateMap.put(sessionNumber,graduateMap.get(sessionNumber)+1);
					}
				}

				if(null==notGraduateMap.get(sessionNumber)){
                    if (!com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
						notGraduateMap.put(sessionNumber,1);
					}
				}else{
                    if (!com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
						notGraduateMap.put(sessionNumber,notGraduateMap.get(sessionNumber)+1);
					}
				}

                if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
					sumMap.put("graduate",sumMap.get("graduate")+1);
				}else {
					sumMap.put("notGraduate",sumMap.get("notGraduate")+1);
				}
				sumMap.put("total",sumMap.get("total")+1);
			}
		}

		Collections.sort(yearList);
		model.addAttribute("yearList",yearList);
		model.addAttribute("graduateMap",graduateMap);
		model.addAttribute("notGraduateMap",notGraduateMap);
		model.addAttribute("totalMap",totalMap);
		model.addAttribute("lastTotalMap",lastTotalMap);
		model.addAttribute("sumMap",sumMap);

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);

		Map<String,Integer> speChangeMap = new HashMap<>();
		Map<String,Integer> baseChangeMap = new HashMap<>();
		Map<String,Integer> delayMap = new HashMap<>();
		Map<String,Integer> returnTrainingMap = new HashMap<>();
		sumMap.put("speChange",0);
		sumMap.put("baseChange",0);
		sumMap.put("delay",0);
		sumMap.put("returnTraining",0);
		sumMap.put("statusIdChange",0);

		List<SysMonthlyChangeInfo> sysMonthlyChangeInfoList = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);
		if(sysMonthlyChangeInfoList!=null&sysMonthlyChangeInfoList.size()>0){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:sysMonthlyChangeInfoList){
				String sessionNumber = sysMonthlyChangeInfo.getSessionNumber();
				String changeTypeId = sysMonthlyChangeInfo.getChangeTypeId();
				if(null==speChangeMap.get(sessionNumber)){
					if("SpeChange".equals(changeTypeId)){
						speChangeMap.put(sessionNumber,1);
					}
				}else{
					if("SpeChange".equals(changeTypeId)){
						speChangeMap.put(sessionNumber,speChangeMap.get(sessionNumber)+1);
					}
				}

				if(null==baseChangeMap.get(sessionNumber)){
					if("BaseChange".equals(changeTypeId)){
						baseChangeMap.put(sessionNumber,1);
					}
				}else{
					if("BaseChange".equals(changeTypeId)){
						baseChangeMap.put(sessionNumber,baseChangeMap.get(sessionNumber)+1);
					}
				}

				if("SpeChange".equals(changeTypeId)){
					sumMap.put("speChange",sumMap.get("speChange")+1);
				}else if("BaseChange".equals(changeTypeId)){
					sumMap.put("baseChange",sumMap.get("baseChange")+1);
				}
			}
		}
		if(sysMonthlyReturnDelayInfoList!=null&&sysMonthlyReturnDelayInfoList.size()>0){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoList){
				String sessionNumber = sysMonthlyReturnDelayInfo.getSessionNumber();
				String typeId = sysMonthlyReturnDelayInfo.getTypeId();
				if(null==delayMap.get(sessionNumber)){
					if("Delay".equals(typeId)){
						delayMap.put(sessionNumber,1);
					}
				}else{
					if("Delay".equals(typeId)){
						delayMap.put(sessionNumber,delayMap.get(sessionNumber)+1);
					}
				}

				if(null==returnTrainingMap.get(sessionNumber)){
					if("ReturnTraining".equals(typeId)){
						returnTrainingMap.put(sessionNumber,1);
					}
				}else{
					if("ReturnTraining".equals(typeId)){
						returnTrainingMap.put(sessionNumber,returnTrainingMap.get(sessionNumber)+1);
					}
				}

				if("Delay".equals(typeId)){
					sumMap.put("delay",sumMap.get("delay")+1);
				}else if("ReturnTraining".equals(typeId)){
					sumMap.put("returnTraining",sumMap.get("returnTraining")+1);
				}
			}
		}
		model.addAttribute("delayMap",delayMap);
		model.addAttribute("returnTrainingMap",returnTrainingMap);
		model.addAttribute("speChangeMap",speChangeMap);
		model.addAttribute("baseChangeMap",baseChangeMap);

		Map<String,SysMonthlyDoctorInfo> lastMonthDoctorMap = new HashMap<>();
		Map<String,Integer> statusIdChangeMap = new HashMap<>();
		search.setDoctorStatusId("");
		List<SysMonthlyDoctorInfo> lastSysMonthlyDoctorInfoAll = monthlyReportBiz.getMonthlyDoctorInfo2(search);
		search.setDateMonth(monthDate);
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoAll = monthlyReportBiz.getMonthlyDoctorInfo2(search);
		if(lastSysMonthlyDoctorInfoAll!=null&&lastSysMonthlyDoctorInfoAll.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:lastSysMonthlyDoctorInfoAll){
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				if(null==lastMonthDoctorMap.get(recruitFlow)){
					lastMonthDoctorMap.put(recruitFlow,sysMonthlyDoctorInfo);
				}
			}
		}
		if(sysMonthlyDoctorInfoAll!=null&&sysMonthlyDoctorInfoAll.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoAll){
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				String doctorStatusId = sysMonthlyDoctorInfo.getDoctorStatusId();
				SysMonthlyDoctorInfo lastInfo = lastMonthDoctorMap.get(recruitFlow);
				if(null!=lastInfo) {
					String lastDoctorStatusId = lastInfo.getDoctorStatusId();
					String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
					if (null == statusIdChangeMap.get(sessionNumber)) {
						if ("20".equals(lastDoctorStatusId) && "22".equals(doctorStatusId) ||
								"20".equals(lastDoctorStatusId) && "21".equals(doctorStatusId) ||
								"22".equals(lastDoctorStatusId) && "20".equals(doctorStatusId) ||
								"21".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)
						) {
							statusIdChangeMap.put(sessionNumber, 1);
						}
					} else {
						if ("20".equals(lastDoctorStatusId) && "22".equals(doctorStatusId) ||
								"20".equals(lastDoctorStatusId) && "21".equals(doctorStatusId) ||
								"22".equals(lastDoctorStatusId) && "20".equals(doctorStatusId) ||
								"21".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)
						) {
							statusIdChangeMap.put(sessionNumber, statusIdChangeMap.get(sessionNumber)+1);
						}
					}

					if( "20".equals(lastDoctorStatusId)&&"22".equals(doctorStatusId) ||
							"20".equals(lastDoctorStatusId)&&"21".equals(doctorStatusId) ||
							"22".equals(lastDoctorStatusId)&&"20".equals(doctorStatusId) ||
							"21".equals(lastDoctorStatusId)&&"20".equals(doctorStatusId)
					){
						sumMap.put("statusIdChange",sumMap.get("statusIdChange")+1);
					}
				}
			}
		}
		model.addAttribute("statusIdChangeMap",statusIdChangeMap);

		long end = System.currentTimeMillis();
		System.out.println("程序时间"+(end - startTime)+"ms");
		return "jsres/global/monthlyReportNew/chart12";
	}

	@RequestMapping("/exportChart1")//导出图一
	public void exportChart1(String monthDate,HttpServletResponse response) throws Exception {
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorStatusId("20");
        search.setChangeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());

		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(monthDate));
		c.add(Calendar.MONTH,-1);
		String lastMonth = sdf.format(c.getTime());
		search.setDateMonth(lastMonth);
		List<SysMonthlyDoctorInfo> lastSysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		List<String> yearList = new ArrayList<>();
		Map<String,Integer> notGraduateMap = new HashMap<>();
		Map<String,Integer> graduateMap = new HashMap<>();
		Map<String,Integer> totalMap = new HashMap<>();
		Map<String,Integer> lastTotalMap = new HashMap<>();

		Map<String,Integer> sumMap = new HashMap<>();
		sumMap.put("notGraduate",0);
		sumMap.put("graduate",0);
		sumMap.put("total",0);
		sumMap.put("lastTotal",0);

		if(lastSysMonthlyDoctorInfoList!=null&&lastSysMonthlyDoctorInfoList.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:lastSysMonthlyDoctorInfoList){
				String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
				if(null==lastTotalMap.get(sessionNumber)){
					lastTotalMap.put(sessionNumber,1);
				}else{
					lastTotalMap.put(sessionNumber,lastTotalMap.get(sessionNumber)+1);
				}
				sumMap.put("lastTotal",sumMap.get("lastTotal")+1);
			}
		}

		if(sysMonthlyDoctorInfoList!=null&&sysMonthlyDoctorInfoList.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
				String doctorTypeId = sysMonthlyDoctorInfo.getDoctorTypeId();

				if(!yearList.contains(sessionNumber)){
					yearList.add(sessionNumber);
				}

				if(null==totalMap.get(sessionNumber)){
					totalMap.put(sessionNumber,1);
				}else{
					totalMap.put(sessionNumber,totalMap.get(sessionNumber)+1);
				}

				if(null==graduateMap.get(sessionNumber)){
                    if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
						graduateMap.put(sessionNumber,1);
					}
				}else{
                    if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
						graduateMap.put(sessionNumber,graduateMap.get(sessionNumber)+1);
					}
				}

				if(null==notGraduateMap.get(sessionNumber)){
                    if (!com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
						notGraduateMap.put(sessionNumber,1);
					}
				}else{
                    if (!com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
						notGraduateMap.put(sessionNumber,notGraduateMap.get(sessionNumber)+1);
					}
				}

                if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctorTypeId)) {
					sumMap.put("graduate",sumMap.get("graduate")+1);
				}else {
					sumMap.put("notGraduate",sumMap.get("notGraduate")+1);
				}
				sumMap.put("total",sumMap.get("total")+1);
			}
		}

		Collections.sort(yearList);

		List<Map<String,Object>> exportMapList = new ArrayList<>();
		if(yearList!=null&&yearList.size()>0){
			for(String year:yearList){
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("year",year);
				subMap.put("graduate",graduateMap.get(year)==null?0:graduateMap.get(year));
				subMap.put("notGraduate",notGraduateMap.get(year)==null?0:notGraduateMap.get(year));
				subMap.put("total",totalMap.get(year)==null?0:totalMap.get(year));
				subMap.put("lastTotal",lastTotalMap.get(year)==null?0:lastTotalMap.get(year));
				subMap.put("difference",lastTotalMap.get(year)-totalMap.get(year));
				exportMapList.add(subMap);
			}
		}
		Map<String,Object> lastSubMap = new HashMap<>();
		lastSubMap.put("year","合计");
		lastSubMap.put("graduate",sumMap.get("graduate"));
		lastSubMap.put("notGraduate",sumMap.get("notGraduate"));
		lastSubMap.put("total",sumMap.get("total"));
		lastSubMap.put("lastTotal",sumMap.get("lastTotal"));
		lastSubMap.put("difference",sumMap.get("total")-sumMap.get("lastTotal"));
		exportMapList.add(lastSubMap);

		String fileName = "本月在培学员人数统计.xls";
		String titles[] = {
				"year:培训年级",
				"notGraduate:住院医师",
				"graduate:在校专硕",
				"total:本月总数",
				"lastTotal:上月总数",
				"difference:与上月差异"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportChart2")//导出图二
	public void exportChart2(String monthDate,Model model,HttpServletResponse response) throws Exception {
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorStatusId("20");
        search.setChangeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());

		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(monthDate));
		c.add(Calendar.MONTH,-1);
		String lastMonth = sdf.format(c.getTime());
		search.setDateMonth(lastMonth);

		List<String> yearList = new ArrayList<>();

		if(sysMonthlyDoctorInfoList!=null&&sysMonthlyDoctorInfoList.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
				if(!yearList.contains(sessionNumber)){
					yearList.add(sessionNumber);
				}
			}
		}

		Collections.sort(yearList);

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);

		Map<String,Integer> speChangeMap = new HashMap<>();
		Map<String,Integer> baseChangeMap = new HashMap<>();
		Map<String,Integer> delayMap = new HashMap<>();
		Map<String,Integer> returnTrainingMap = new HashMap<>();
		Map<String,Integer> sumMap = new HashMap<>();

		sumMap.put("speChange",0);
		sumMap.put("baseChange",0);
		sumMap.put("delay",0);
		sumMap.put("returnTraining",0);
		sumMap.put("statusIdChange",0);

		List<SysMonthlyChangeInfo> sysMonthlyChangeInfoList = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);
		if(sysMonthlyChangeInfoList!=null&sysMonthlyChangeInfoList.size()>0){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:sysMonthlyChangeInfoList){
				String sessionNumber = sysMonthlyChangeInfo.getSessionNumber();
				String changeTypeId = sysMonthlyChangeInfo.getChangeTypeId();
				if(null==speChangeMap.get(sessionNumber)){
					if("SpeChange".equals(changeTypeId)){
						speChangeMap.put(sessionNumber,1);
					}
				}else{
					if("SpeChange".equals(changeTypeId)){
						speChangeMap.put(sessionNumber,speChangeMap.get(sessionNumber)+1);
					}
				}

				if(null==baseChangeMap.get(sessionNumber)){
					if("BaseChange".equals(changeTypeId)){
						baseChangeMap.put(sessionNumber,1);
					}
				}else{
					if("BaseChange".equals(changeTypeId)){
						baseChangeMap.put(sessionNumber,baseChangeMap.get(sessionNumber)+1);
					}
				}

				if("SpeChange".equals(changeTypeId)){
					sumMap.put("speChange",sumMap.get("speChange")+1);
				}else if("BaseChange".equals(changeTypeId)){
					sumMap.put("baseChange",sumMap.get("baseChange")+1);
				}
			}
		}
		if(sysMonthlyReturnDelayInfoList!=null&&sysMonthlyReturnDelayInfoList.size()>0){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoList){
				String sessionNumber = sysMonthlyReturnDelayInfo.getSessionNumber();
				String typeId = sysMonthlyReturnDelayInfo.getTypeId();
				if(null==delayMap.get(sessionNumber)){
					if("Delay".equals(typeId)){
						delayMap.put(sessionNumber,1);
					}
				}else{
					if("Delay".equals(typeId)){
						delayMap.put(sessionNumber,delayMap.get(sessionNumber)+1);
					}
				}

				if(null==returnTrainingMap.get(sessionNumber)){
					if("ReturnTraining".equals(typeId)){
						returnTrainingMap.put(sessionNumber,1);
					}
				}else{
					if("ReturnTraining".equals(typeId)){
						returnTrainingMap.put(sessionNumber,returnTrainingMap.get(sessionNumber)+1);
					}
				}

				if("Delay".equals(typeId)){
					sumMap.put("delay",sumMap.get("delay")+1);
				}else if("ReturnTraining".equals(typeId)){
					sumMap.put("returnTraining",sumMap.get("returnTraining")+1);
				}
			}
		}
		model.addAttribute("delayMap",delayMap);
		model.addAttribute("returnTrainingMap",returnTrainingMap);
		model.addAttribute("speChangeMap",speChangeMap);
		model.addAttribute("baseChangeMap",baseChangeMap);

		Map<String,SysMonthlyDoctorInfo> lastMonthDoctorMap = new HashMap<>();
		Map<String,Integer> statusIdChangeMap = new HashMap<>();
		search.setDoctorStatusId("");
		List<SysMonthlyDoctorInfo> lastSysMonthlyDoctorInfoAll = monthlyReportBiz.getMonthlyDoctorInfo(search,null);
		search.setDateMonth(monthDate);
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoAll = monthlyReportBiz.getMonthlyDoctorInfo(search,null);
		if(lastSysMonthlyDoctorInfoAll!=null&&lastSysMonthlyDoctorInfoAll.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:lastSysMonthlyDoctorInfoAll){
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				if(null==lastMonthDoctorMap.get(recruitFlow)){
					lastMonthDoctorMap.put(recruitFlow,sysMonthlyDoctorInfo);
				}
			}
		}
		if(sysMonthlyDoctorInfoAll!=null&&sysMonthlyDoctorInfoAll.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoAll){
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				String doctorStatusId = sysMonthlyDoctorInfo.getDoctorStatusId();
				SysMonthlyDoctorInfo lastInfo = lastMonthDoctorMap.get(recruitFlow);
				if(null!=lastInfo) {
					String lastDoctorStatusId = lastInfo.getDoctorStatusId();
					String sessionNumber = sysMonthlyDoctorInfo.getSessionNumber();
					if (null == statusIdChangeMap.get(sessionNumber)) {
						if ("20".equals(lastDoctorStatusId) && "22".equals(doctorStatusId) ||
								"20".equals(lastDoctorStatusId) && "21".equals(doctorStatusId) ||
								"22".equals(lastDoctorStatusId) && "20".equals(doctorStatusId) ||
								"21".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)
						) {
							statusIdChangeMap.put(sessionNumber, 1);
						}
					} else {
						if ("20".equals(lastDoctorStatusId) && "22".equals(doctorStatusId) ||
								"20".equals(lastDoctorStatusId) && "21".equals(doctorStatusId) ||
								"22".equals(lastDoctorStatusId) && "20".equals(doctorStatusId) ||
								"21".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)
						) {
							statusIdChangeMap.put(sessionNumber, statusIdChangeMap.get(sessionNumber));
						}
					}

					if ("20".equals(lastDoctorStatusId) && "22".equals(doctorStatusId) ||
							"20".equals(lastDoctorStatusId) && "21".equals(doctorStatusId) ||
							"22".equals(lastDoctorStatusId) && "20".equals(doctorStatusId) ||
							"21".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)
					) {
						sumMap.put("statusIdChange", sumMap.get("statusIdChange"));
					}
				}
			}
		}
		model.addAttribute("statusIdChangeMap",statusIdChangeMap);

		List<Map<String,Object>> exportMapList = new ArrayList<>();
		if(yearList!=null&&yearList.size()>0){
			for(String year:yearList){
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("year",year);
				subMap.put("returnTraining",returnTrainingMap.get(year)==null?0:returnTrainingMap.get(year));
				subMap.put("delay",delayMap.get(year)==null?0:delayMap.get(year));
				subMap.put("baseChange",baseChangeMap.get(year)==null?0:baseChangeMap.get(year));
				subMap.put("speChange",speChangeMap.get(year)==null?0:speChangeMap.get(year));
				subMap.put("statusIdChange",statusIdChangeMap.get(year)==null?0:statusIdChangeMap.get(year));
				exportMapList.add(subMap);
			}
		}
		Map<String,Object> lastSubMap = new HashMap<>();
		lastSubMap.put("year","合计");
		lastSubMap.put("returnTraining",sumMap.get("returnTraining"));
		lastSubMap.put("delay",sumMap.get("delay"));
		lastSubMap.put("baseChange",sumMap.get("baseChange"));
		lastSubMap.put("speChange",sumMap.get("speChange"));
		lastSubMap.put("statusIdChange",sumMap.get("statusIdChange"));
		exportMapList.add(lastSubMap);

		String fileName = "本月异动学员人数统计.xls";
		String titles[] = {
				"year:培训年级",
				"returnTraining:退培",
				"delay:延期",
				"baseChange:基地变更",
				"speChange:专业变更",
				"statusIdChange:培训状态变更"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");

	}

	@RequestMapping("/hospitalDoctorsNum")
	public String hospitalDoctorsNum(String monthDate,String sessionNumber,String orgFlow,Model model,Integer currentPage,HttpServletRequest request){
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = null;
		SysOrg searchOrg = new SysOrg();
        searchOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		searchOrg.setOrgProvId("320000");
		orgs = orgBiz.searchOrg(searchOrg);
		model.addAttribute("orgs", orgs);

		Map<String,Object> paramMap = new HashMap<>();
		model.addAttribute("orgFlow",orgFlow);
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("monthDate",monthDate);
		paramMap.put("sessionNumber",sessionNumber);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = monthlyReportExtMapper.getHospitalDoctorsNum(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/global/monthlyReportNew/hospitalDoctorsNum";
	}

	@RequestMapping("/exportHospitalDoctorsNum")
	public void exportHospitalDoctorsNum(String monthDate,String sessionNumber,String orgFlow,HttpServletResponse response) throws Exception{
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = null;
		SysOrg searchOrg = new SysOrg();
        searchOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		orgs = orgBiz.searchOrg(searchOrg);

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("monthDate",monthDate);
		paramMap.put("sessionNumber",sessionNumber);
		List<Map<String,Object>> resultMapList = monthlyReportExtMapper.getHospitalDoctorsNum(paramMap);

		String fileName = "本月在培学员数据.xls";
		String titles[] = {
				"ORG_NAME:培训基地",
				"SESSION_NUMBER:年级",
				"B:住院医师",
				"A:在校专硕",
				"C:总数"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");

	}

	@RequestMapping("/exportHospitalDoctorsDetail")
	public void exportHospitalDoctorsDetail(String monthDate,String sessionNumber,String orgFlow,HttpServletResponse response) throws Exception{
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorStatusId("20");
		search.setSessionNumber(sessionNumber);
        search.setChangeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		search.setOrgFlow(orgFlow);

		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);
		if(sysMonthlyDoctorInfoList!=null&&sysMonthlyDoctorInfoList.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				String trainingYears = sysMonthlyDoctorInfo.getTrainingYears();
                sysMonthlyDoctorInfo.setTrainingYears(com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(trainingYears));
			}
		}

		String fileName = "本月在培学员详情.xls";
		String titles[] = {
				"doctorName:姓名",
				"sessionNumber:年级",
				"orgName:培训基地",
				"trainingTypeName:培训类型",
				"trainingSpeName:培训专业",
				"trainingYears:培训年限",
				"idNo:证件号码",
				"doctorTypeName:人员类型",
				"userPhone:联系方式"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, sysMonthlyDoctorInfoList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportDifference")//导出与上月差异
	public void exportDifference(String monthDate,String sessionNumber,Model model,HttpServletResponse response) throws Exception {
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
        search.setChangeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		search.setSessionNumber(sessionNumber);

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("typeId","ReturnTraining");

		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);

		List<Map<String,Object>> exportMapList = new ArrayList<>();

		if(sysMonthlyReturnDelayInfoList!=null&&sysMonthlyReturnDelayInfoList.size()>0){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoList){
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("doctorName",sysMonthlyReturnDelayInfo.getDoctorName());
				subMap.put("sessionNumber",sysMonthlyReturnDelayInfo.getSessionNumber());
				subMap.put("orgName",sysMonthlyReturnDelayInfo.getOrgName());
				subMap.put("trainingTypeName",sysMonthlyReturnDelayInfo.getTrainingTypeName());
				subMap.put("trainingSpeName",sysMonthlyReturnDelayInfo.getTrainingSpeName());
                subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyReturnDelayInfo.getTrainingYears()));
				subMap.put("idNo",sysMonthlyReturnDelayInfo.getIdNo());
				subMap.put("doctorTypeName",sysMonthlyReturnDelayInfo.getDoctorTypeName());
				subMap.put("userPhone",sysMonthlyReturnDelayInfo.getUserPhone());
				subMap.put("statusDetail","退培");
				exportMapList.add(subMap);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(monthDate));
		c.add(Calendar.MONTH,-1);
		String lastMonth = sdf.format(c.getTime());

		SysMonthlyBlacklistInfo searchBlacklist = new SysMonthlyBlacklistInfo();
		searchBlacklist.setSessionNumber(sessionNumber);
		searchBlacklist.setDateMonth(monthDate);
		List<SysMonthlyBlacklistInfo> blacklistInfos = monthlyReportBiz.getBlacklist(searchBlacklist);
		Map<String,SysMonthlyBlacklistInfo> lastBlacklistMap = new HashMap<>();
		searchBlacklist.setDateMonth(lastMonth);
		List<SysMonthlyBlacklistInfo> lastBlacklistInfos = monthlyReportBiz.getBlacklist(searchBlacklist);
		if(lastBlacklistInfos!=null&&lastBlacklistInfos.size()>0){
			for(SysMonthlyBlacklistInfo blacklistInfo:lastBlacklistInfos){
				lastBlacklistMap.put(blacklistInfo.getDoctorFlow(),blacklistInfo);
			}
		}

		if(blacklistInfos!=null&&blacklistInfos.size()>0){
			for(SysMonthlyBlacklistInfo blacklistInfo:blacklistInfos){
                if (blacklistInfo.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)) {
					Map<String,Object> subMap = new HashMap<>();
					subMap.put("doctorName",blacklistInfo.getDoctorName());
					subMap.put("sessionNumber",blacklistInfo.getSessionNumber());
					subMap.put("orgName",blacklistInfo.getOrgName());
					subMap.put("trainingTypeName",blacklistInfo.getTrainingTypeName());
					subMap.put("trainingSpeName",blacklistInfo.getTrainingSpeName());
                    subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(blacklistInfo.getTrainingYears()));
					subMap.put("idNo",blacklistInfo.getIdNo());
					subMap.put("doctorTypeName",blacklistInfo.getDoctorTypeName());
					subMap.put("userPhone",blacklistInfo.getUserPhone());
					subMap.put("statusDetail","加入黑名单");
					exportMapList.add(subMap);
                } else if (blacklistInfo.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N)) {
					String doctorFlow = blacklistInfo.getDoctorFlow();
					SysMonthlyBlacklistInfo lastBlacklistInfo = lastBlacklistMap.get(doctorFlow);
                    if (lastBlacklistInfo != null && lastBlacklistInfo.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)) {
						Map<String,Object> subMap = new HashMap<>();
						subMap.put("doctorName",blacklistInfo.getDoctorName());
						subMap.put("sessionNumber",blacklistInfo.getSessionNumber());
						subMap.put("orgName",blacklistInfo.getOrgName());
						subMap.put("trainingTypeName",blacklistInfo.getTrainingTypeName());
						subMap.put("trainingSpeName",blacklistInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(blacklistInfo.getTrainingYears()));
						subMap.put("idNo",blacklistInfo.getIdNo());
						subMap.put("doctorTypeName",blacklistInfo.getDoctorTypeName());
						subMap.put("userPhone",blacklistInfo.getUserPhone());
						subMap.put("statusDetail","从黑名单移除");
						exportMapList.add(subMap);
					}
				}
			}
		}

		Map<String,SysMonthlyDoctorInfo> lastMonthDoctorMap = new HashMap<>();
		search.setDateMonth(lastMonth);
		List<SysMonthlyDoctorInfo> lastSysMonthlyDoctorInfoAll = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		search.setDateMonth(monthDate);
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoAll = monthlyReportBiz.getMonthlyDoctorInfo(search,null);
		if(lastSysMonthlyDoctorInfoAll!=null&&lastSysMonthlyDoctorInfoAll.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:lastSysMonthlyDoctorInfoAll){
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				if(null==lastMonthDoctorMap.get(recruitFlow)){
					lastMonthDoctorMap.put(recruitFlow,sysMonthlyDoctorInfo);
				}
			}
		}
		if(sysMonthlyDoctorInfoAll!=null&&sysMonthlyDoctorInfoAll.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoAll){
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				String doctorStatusId = sysMonthlyDoctorInfo.getDoctorStatusId();
				SysMonthlyDoctorInfo lastInfo = lastMonthDoctorMap.get(recruitFlow);
				if(null!=lastInfo) {
					String lastDoctorStatusId = lastInfo.getDoctorStatusId();
					if ("20".equals(lastDoctorStatusId) && "22".equals(doctorStatusId)) {
						Map<String, Object> subMap = new HashMap<>();
						subMap.put("doctorName", sysMonthlyDoctorInfo.getDoctorName());
						subMap.put("sessionNumber", sysMonthlyDoctorInfo.getSessionNumber());
						subMap.put("orgName", sysMonthlyDoctorInfo.getOrgName());
						subMap.put("trainingTypeName", sysMonthlyDoctorInfo.getTrainingTypeName());
						subMap.put("trainingSpeName", sysMonthlyDoctorInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyDoctorInfo.getTrainingYears()));
						subMap.put("idNo", sysMonthlyDoctorInfo.getIdNo());
						subMap.put("doctorTypeName", sysMonthlyDoctorInfo.getDoctorTypeName());
						subMap.put("userPhone", sysMonthlyDoctorInfo.getUserPhone());
						subMap.put("statusDetail", "在培—已考核待结业");
						exportMapList.add(subMap);
					} else if ("20".equals(lastDoctorStatusId) && "21".equals(doctorStatusId)) {
						Map<String, Object> subMap = new HashMap<>();
						subMap.put("doctorName", sysMonthlyDoctorInfo.getDoctorName());
						subMap.put("sessionNumber", sysMonthlyDoctorInfo.getSessionNumber());
						subMap.put("orgName", sysMonthlyDoctorInfo.getOrgName());
						subMap.put("trainingTypeName", sysMonthlyDoctorInfo.getTrainingTypeName());
						subMap.put("trainingSpeName", sysMonthlyDoctorInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyDoctorInfo.getTrainingYears()));
						subMap.put("idNo", sysMonthlyDoctorInfo.getIdNo());
						subMap.put("doctorTypeName", sysMonthlyDoctorInfo.getDoctorTypeName());
						subMap.put("userPhone", sysMonthlyDoctorInfo.getUserPhone());
						subMap.put("statusDetail", "在培—结业");
						exportMapList.add(subMap);
					} else if ("22".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)) {
						Map<String, Object> subMap = new HashMap<>();
						subMap.put("doctorName", sysMonthlyDoctorInfo.getDoctorName());
						subMap.put("sessionNumber", sysMonthlyDoctorInfo.getSessionNumber());
						subMap.put("orgName", sysMonthlyDoctorInfo.getOrgName());
						subMap.put("trainingTypeName", sysMonthlyDoctorInfo.getTrainingTypeName());
						subMap.put("trainingSpeName", sysMonthlyDoctorInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyDoctorInfo.getTrainingYears()));
						subMap.put("idNo", sysMonthlyDoctorInfo.getIdNo());
						subMap.put("doctorTypeName", sysMonthlyDoctorInfo.getDoctorTypeName());
						subMap.put("userPhone", sysMonthlyDoctorInfo.getUserPhone());
						subMap.put("statusDetail", "已考核待结业—在培");
						exportMapList.add(subMap);
					} else if ("21".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)) {
						Map<String, Object> subMap = new HashMap<>();
						subMap.put("doctorName", sysMonthlyDoctorInfo.getDoctorName());
						subMap.put("sessionNumber", sysMonthlyDoctorInfo.getSessionNumber());
						subMap.put("orgName", sysMonthlyDoctorInfo.getOrgName());
						subMap.put("trainingTypeName", sysMonthlyDoctorInfo.getTrainingTypeName());
						subMap.put("trainingSpeName", sysMonthlyDoctorInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyDoctorInfo.getTrainingYears()));
						subMap.put("idNo", sysMonthlyDoctorInfo.getIdNo());
						subMap.put("doctorTypeName", sysMonthlyDoctorInfo.getDoctorTypeName());
						subMap.put("userPhone", sysMonthlyDoctorInfo.getUserPhone());
						subMap.put("statusDetail", "结业—在培");
						exportMapList.add(subMap);
					}
				}
			}
		}

		String fileName = "与上月差异导出.xls";
		String titles[] = {
				"doctorName:姓名",
				"sessionNumber:年级",
				"orgName:培训基地",
				"trainingTypeName:培训类型",
				"trainingSpeName:培训专业",
				"trainingYears:培训年限",
				"idNo:证件号码",
				"doctorTypeName:人员类型",
				"userPhone:联系方式",
				"statusDetail:异动详情"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportReturn")//导出退培
	public void exportReturn(String monthDate,Model model,HttpServletResponse response) throws Exception {

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("typeId","ReturnTraining");

		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);

		List<Map<String,Object>> exportMapList = new ArrayList<>();

		if(sysMonthlyReturnDelayInfoList!=null&&sysMonthlyReturnDelayInfoList.size()>0){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoList){
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("doctorName",sysMonthlyReturnDelayInfo.getDoctorName());
				subMap.put("sessionNumber",sysMonthlyReturnDelayInfo.getSessionNumber());
				subMap.put("orgName",sysMonthlyReturnDelayInfo.getOrgName());
				subMap.put("trainingTypeName",sysMonthlyReturnDelayInfo.getTrainingTypeName());
				subMap.put("trainingSpeName",sysMonthlyReturnDelayInfo.getTrainingSpeName());
                subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyReturnDelayInfo.getTrainingYears()));
				subMap.put("idNo",sysMonthlyReturnDelayInfo.getIdNo());
				subMap.put("doctorTypeName",sysMonthlyReturnDelayInfo.getDoctorTypeName());
				subMap.put("userPhone",sysMonthlyReturnDelayInfo.getUserPhone());
				exportMapList.add(subMap);
			}
		}

		String fileName = "退培学员导出.xls";
		String titles[] = {
				"doctorName:姓名",
				"sessionNumber:年级",
				"orgName:培训基地",
				"trainingTypeName:培训类型",
				"trainingSpeName:培训专业",
				"trainingYears:培训年限",
				"idNo:证件号码",
				"doctorTypeName:人员类型",
				"userPhone:联系方式"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportDelay")//导出延期
	public void exportDelay(String monthDate,Model model,HttpServletResponse response) throws Exception {

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("typeId","Delay");

		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoList = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);

		List<Map<String,Object>> exportMapList = new ArrayList<>();

		if(sysMonthlyReturnDelayInfoList!=null&&sysMonthlyReturnDelayInfoList.size()>0){
			for(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo:sysMonthlyReturnDelayInfoList){
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("doctorName",sysMonthlyReturnDelayInfo.getDoctorName());
				subMap.put("sessionNumber",sysMonthlyReturnDelayInfo.getSessionNumber());
				subMap.put("orgName",sysMonthlyReturnDelayInfo.getOrgName());
				subMap.put("trainingTypeName",sysMonthlyReturnDelayInfo.getTrainingTypeName());
				subMap.put("trainingSpeName",sysMonthlyReturnDelayInfo.getTrainingSpeName());
                subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyReturnDelayInfo.getTrainingYears()));
				subMap.put("idNo",sysMonthlyReturnDelayInfo.getIdNo());
				subMap.put("doctorTypeName",sysMonthlyReturnDelayInfo.getDoctorTypeName());
				subMap.put("userPhone",sysMonthlyReturnDelayInfo.getUserPhone());
				exportMapList.add(subMap);
			}
		}

		String fileName = "延期学员导出.xls";
		String titles[] = {
				"doctorName:姓名",
				"sessionNumber:年级",
				"orgName:培训基地",
				"trainingTypeName:培训类型",
				"trainingSpeName:培训专业",
				"trainingYears:培训年限",
				"idNo:证件号码",
				"doctorTypeName:人员类型",
				"userPhone:联系方式"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportSpeChange")//导出专业变更
	public void exportSpeChange(String monthDate,HttpServletResponse response) throws Exception {

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("changeTypeId","SpeChange");

		List<SysMonthlyChangeInfo> sysMonthlyChangeInfoList = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);

		List<Map<String,Object>> exportMapList = new ArrayList<>();

		if(sysMonthlyChangeInfoList!=null&&sysMonthlyChangeInfoList.size()>0){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:sysMonthlyChangeInfoList){
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("doctorName",sysMonthlyChangeInfo.getDoctorName());
				subMap.put("sessionNumber",sysMonthlyChangeInfo.getSessionNumber());
				subMap.put("orgName",sysMonthlyChangeInfo.getOrgName());
				subMap.put("trainingTypeName",sysMonthlyChangeInfo.getTrainingTypeName());
				subMap.put("trainingSpeName",sysMonthlyChangeInfo.getTrainingSpeName());
                subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyChangeInfo.getTrainingYears()));
				subMap.put("idNo",sysMonthlyChangeInfo.getIdNo());
				subMap.put("doctorTypeName",sysMonthlyChangeInfo.getDoctorTypeName());
				subMap.put("userPhone",sysMonthlyChangeInfo.getUserPhone());
				subMap.put("historySpeName",sysMonthlyChangeInfo.getHistoryTrainingSpeName());
				exportMapList.add(subMap);
			}
		}

		String fileName = "变更专业学员导出.xls";
		String titles[] = {
				"doctorName:姓名",
				"sessionNumber:年级",
				"orgName:培训基地",
				"trainingTypeName:培训类型",
				"trainingSpeName:培训专业",
				"trainingYears:培训年限",
				"idNo:证件号码",
				"doctorTypeName:人员类型",
				"userPhone:联系方式",
				"historySpeName:变更前专业"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportBaseChange")//导出基地变更
	public void exportBaseChange(String monthDate,HttpServletResponse response) throws Exception {

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("changeTypeId","BaseChange");

		List<SysMonthlyChangeInfo> sysMonthlyChangeInfoList = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);

		List<Map<String,Object>> exportMapList = new ArrayList<>();

		if(sysMonthlyChangeInfoList!=null&&sysMonthlyChangeInfoList.size()>0){
			for(SysMonthlyChangeInfo sysMonthlyChangeInfo:sysMonthlyChangeInfoList){
				Map<String,Object> subMap = new HashMap<>();
				subMap.put("doctorName",sysMonthlyChangeInfo.getDoctorName());
				subMap.put("sessionNumber",sysMonthlyChangeInfo.getSessionNumber());
				subMap.put("orgName",sysMonthlyChangeInfo.getOrgName());
				subMap.put("trainingTypeName",sysMonthlyChangeInfo.getTrainingTypeName());
				subMap.put("trainingSpeName",sysMonthlyChangeInfo.getTrainingSpeName());
                subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyChangeInfo.getTrainingYears()));
				subMap.put("idNo",sysMonthlyChangeInfo.getIdNo());
				subMap.put("doctorTypeName",sysMonthlyChangeInfo.getDoctorTypeName());
				subMap.put("userPhone",sysMonthlyChangeInfo.getUserPhone());
				subMap.put("historyOrgName",sysMonthlyChangeInfo.getHistoryOrgName());
				exportMapList.add(subMap);
			}
		}

		String fileName = "变更基地学员导出.xls";
		String titles[] = {
				"doctorName:姓名",
				"sessionNumber:年级",
				"orgName:培训基地",
				"trainingTypeName:培训类型",
				"trainingSpeName:培训专业",
				"trainingYears:培训年限",
				"idNo:证件号码",
				"doctorTypeName:人员类型",
				"userPhone:联系方式",
				"historyOrgName:原培训基地"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportStatusChange")//导出状态变更
	public void exportStatusChange(String monthDate,String sessionNumber,Model model,HttpServletResponse response) throws Exception {
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
        search.setChangeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		search.setSessionNumber(sessionNumber);

		List<Map<String,Object>> exportMapList = new ArrayList<>();

		Map<String,SysMonthlyDoctorInfo> lastMonthDoctorMap = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(monthDate));
		c.add(Calendar.MONTH,-1);
		String lastMonth = sdf.format(c.getTime());
		search.setDateMonth(lastMonth);
		List<SysMonthlyDoctorInfo> lastSysMonthlyDoctorInfoAll = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		search.setDateMonth(monthDate);
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoAll = monthlyReportBiz.getMonthlyDoctorInfo(search,null);
		if(lastSysMonthlyDoctorInfoAll!=null&&lastSysMonthlyDoctorInfoAll.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:lastSysMonthlyDoctorInfoAll){
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				if(null==lastMonthDoctorMap.get(recruitFlow)){
					lastMonthDoctorMap.put(recruitFlow,sysMonthlyDoctorInfo);
				}
			}
		}
		if(sysMonthlyDoctorInfoAll!=null&&sysMonthlyDoctorInfoAll.size()>0){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoAll){
				String recruitFlow = sysMonthlyDoctorInfo.getRecruitFlow();
				String doctorStatusId = sysMonthlyDoctorInfo.getDoctorStatusId();
				SysMonthlyDoctorInfo lastInfo = lastMonthDoctorMap.get(recruitFlow);
				if(null!=lastInfo) {
					String lastDoctorStatusId = lastInfo.getDoctorStatusId();
					if ("20".equals(lastDoctorStatusId) && "22".equals(doctorStatusId)) {
						Map<String, Object> subMap = new HashMap<>();
						subMap.put("doctorName", sysMonthlyDoctorInfo.getDoctorName());
						subMap.put("sessionNumber", sysMonthlyDoctorInfo.getSessionNumber());
						subMap.put("orgName", sysMonthlyDoctorInfo.getOrgName());
						subMap.put("trainingTypeName", sysMonthlyDoctorInfo.getTrainingTypeName());
						subMap.put("trainingSpeName", sysMonthlyDoctorInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyDoctorInfo.getTrainingYears()));
						subMap.put("idNo", sysMonthlyDoctorInfo.getIdNo());
						subMap.put("doctorTypeName", sysMonthlyDoctorInfo.getDoctorTypeName());
						subMap.put("userPhone", sysMonthlyDoctorInfo.getUserPhone());
						subMap.put("statusDetail", "在培—已考核待结业");
						exportMapList.add(subMap);
					} else if ("20".equals(lastDoctorStatusId) && "21".equals(doctorStatusId)) {
						Map<String, Object> subMap = new HashMap<>();
						subMap.put("doctorName", sysMonthlyDoctorInfo.getDoctorName());
						subMap.put("sessionNumber", sysMonthlyDoctorInfo.getSessionNumber());
						subMap.put("orgName", sysMonthlyDoctorInfo.getOrgName());
						subMap.put("trainingTypeName", sysMonthlyDoctorInfo.getTrainingTypeName());
						subMap.put("trainingSpeName", sysMonthlyDoctorInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyDoctorInfo.getTrainingYears()));
						subMap.put("idNo", sysMonthlyDoctorInfo.getIdNo());
						subMap.put("doctorTypeName", sysMonthlyDoctorInfo.getDoctorTypeName());
						subMap.put("userPhone", sysMonthlyDoctorInfo.getUserPhone());
						subMap.put("statusDetail", "在培—结业");
						exportMapList.add(subMap);
					} else if ("22".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)) {
						Map<String, Object> subMap = new HashMap<>();
						subMap.put("doctorName", sysMonthlyDoctorInfo.getDoctorName());
						subMap.put("sessionNumber", sysMonthlyDoctorInfo.getSessionNumber());
						subMap.put("orgName", sysMonthlyDoctorInfo.getOrgName());
						subMap.put("trainingTypeName", sysMonthlyDoctorInfo.getTrainingTypeName());
						subMap.put("trainingSpeName", sysMonthlyDoctorInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyDoctorInfo.getTrainingYears()));
						subMap.put("idNo", sysMonthlyDoctorInfo.getIdNo());
						subMap.put("doctorTypeName", sysMonthlyDoctorInfo.getDoctorTypeName());
						subMap.put("userPhone", sysMonthlyDoctorInfo.getUserPhone());
						subMap.put("statusDetail", "已考核待结业—在培");
						exportMapList.add(subMap);
					} else if ("21".equals(lastDoctorStatusId) && "20".equals(doctorStatusId)) {
						Map<String, Object> subMap = new HashMap<>();
						subMap.put("doctorName", sysMonthlyDoctorInfo.getDoctorName());
						subMap.put("sessionNumber", sysMonthlyDoctorInfo.getSessionNumber());
						subMap.put("orgName", sysMonthlyDoctorInfo.getOrgName());
						subMap.put("trainingTypeName", sysMonthlyDoctorInfo.getTrainingTypeName());
						subMap.put("trainingSpeName", sysMonthlyDoctorInfo.getTrainingSpeName());
                        subMap.put("trainingYears", com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(sysMonthlyDoctorInfo.getTrainingYears()));
						subMap.put("idNo", sysMonthlyDoctorInfo.getIdNo());
						subMap.put("doctorTypeName", sysMonthlyDoctorInfo.getDoctorTypeName());
						subMap.put("userPhone", sysMonthlyDoctorInfo.getUserPhone());
						subMap.put("statusDetail", "结业—在培");
						exportMapList.add(subMap);
					}
				}
			}
		}

		String fileName = "培训状态变更.xls";
		String titles[] = {
				"doctorName:姓名",
				"sessionNumber:年级",
				"orgName:培训基地",
				"trainingTypeName:培训类型",
				"trainingSpeName:培训专业",
				"trainingYears:培训年限",
				"idNo:证件号码",
				"doctorTypeName:人员类型",
				"userPhone:联系方式",
				"statusDetail:变更状态"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}


	@RequestMapping("/chart3")//在培学员未使用APP人数统计表
	public String chart3(String monthDate,String orgFlow,String sessionNumber,String trainingTypeId,String trainingSpeId,
						 String monthLengh,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingTypeId",trainingTypeId);
		paramMap.put("trainingSpeId",trainingSpeId);

		List<SysOrg> orgs = null;
		SysOrg searchOrg = new SysOrg();
        searchOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		searchOrg.setOrgProvId("320000");
		orgs = orgBiz.searchOrg(searchOrg);
		model.addAttribute("orgs", orgs);

		model.addAttribute("orgFlow",orgFlow);

		paramMap.put("orgFlow",orgFlow);

		List<Map<String,Object>> resultMapList = null;
		if("1".equals(monthLengh)||StringUtil.isBlank(monthLengh)){
			resultMapList = monthlyReportExtMapper.getAppData(paramMap);
			model.addAttribute("monthLengh","1");
		}else if("3".equals(monthLengh)){
			paramMap.put("dateMonth1",monthDate);
			paramMap.put("dateMonth2",getLastMonths(-2));
			paramMap.put("dateMonth3",getLastMonths(-3));
			resultMapList = monthlyReportExtMapper.getAppData3(paramMap);
			model.addAttribute("monthLengh","3");
		}
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/global/monthlyReportNew/chart3";
	}

	@RequestMapping("/exportChart3")//导出在培学员未使用APP人数统计表
	public void exportChart3(String monthDate,String orgFlow,String sessionNumber,String trainingTypeId,String trainingSpeId,
						 String monthLengh,HttpServletResponse response)throws Exception{
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingTypeId",trainingTypeId);
		paramMap.put("trainingSpeId",trainingSpeId);

		paramMap.put("orgFlow",orgFlow);

		List<Map<String,Object>> resultMapList = null;
		if("1".equals(monthLengh)||StringUtil.isBlank(monthLengh)){
			resultMapList = monthlyReportExtMapper.getAppData(paramMap);
		}else if("3".equals(monthLengh)){
			paramMap.put("dateMonth1",monthDate);
			paramMap.put("dateMonth2",getLastMonths(-2));
			paramMap.put("dateMonth3",getLastMonths(-3));
			resultMapList = monthlyReportExtMapper.getAppData3(paramMap);
		}

		String fileName = "在培学员未使用APP人数统计表.xls";
		String titles[] = {
				"ORG_NAME:培训基地",
				"A:住院医师",
				"B:在校专硕",
				"C:总人数"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/exportChart3Detail")//导出在培学员未使用APP人数统计表
	public void exportChart3Detail(String monthDate,String orgFlow,String sessionNumber,String trainingTypeId,String trainingSpeId,
							 String monthLengh,HttpServletResponse response)throws Exception{
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingTypeId",trainingTypeId);
		paramMap.put("trainingSpeId",trainingSpeId);

		paramMap.put("orgFlow",orgFlow);

		List<SysMonthlyNotUseappInfo> resultMapList = null;
		if("1".equals(monthLengh)||StringUtil.isBlank(monthLengh)){
			resultMapList = monthlyReportExtMapper.getAppDataDetail(paramMap);
		}else if("3".equals(monthLengh)){
			paramMap.put("dateMonth1",monthDate);
			paramMap.put("dateMonth2",getLastMonths(-2));
			paramMap.put("dateMonth3",getLastMonths(-3));
			resultMapList = monthlyReportExtMapper.getAppData3Detail(paramMap);
		}

		if(resultMapList!=null&&resultMapList.size()>0){
			for(SysMonthlyNotUseappInfo sysMonthlyNotUseappInfo:resultMapList){
				String trainingYears = sysMonthlyNotUseappInfo.getTrainingYears();
                sysMonthlyNotUseappInfo.setTrainingYears(com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(trainingYears));
			}
		}

		String fileName = "在培学员未使用APP人员详情.xls";
		String titles[] = {
				"doctorName:姓名",
				"sessionNumber:培训年级",
				"orgName:培训基地",
				"trainingTypeName:培训类型",
				"trainingSpeName:培训专业",
				"trainingYears:培训年限",
				"idNo:证件号码",
				"doctorTypeName:人员类型",
				"userPhone:联系方式"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping("/content0_2")
	public String content0_2(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		return "jsres/global/monthlyReportNew/content0_2";
	}

	@RequestMapping("/content1_2")
	public String content1_2(String baseRange,String monthDate,Model model){
		model.addAttribute("baseRange",baseRange);
		model.addAttribute("monthDate",monthDate);
		return "jsres/global/monthlyReportNew/content1";
	}
}
