package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.res.AfterRecTypeEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.res.RegistryTypeEnum;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.sch.DoctorSearchForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.poi.hssf.usermodel.*;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/ProfessionalBase")
public class ResProfessionalBaseController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResProfessionalBaseController.class);
    private static final String GZZY_ORG_FLOW="5cb53b872c38457a8e2a798d6c4d002f";

	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorOrgHistoryBiz docOrgHisBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IResUserSpeBiz userSpeBiz;
	@Autowired
	private IResRotationOrgBiz iResRotationOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private IResLectureInfoBiz resLectureInfoBiz;
	@Autowired
	private IResLectureEvaDetailBiz resLectureEvaDetailBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private IResLectureScanRegistBiz resLectureScanRegistBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISchDoctorBiz schDoctortBiz;
	@Autowired
	private ISchDoctorAbsenceBiz schDoctorAbsenceBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private ISchExamScoreQueryBiz scoreQueryBiz;
	@Autowired
	private IResTrainingSpeDeptBiz resTrainingSpeDeptBiz;
	@Autowired
	private IResEvalBiz evalBiz;
	@Autowired
	private SysOrgExtMapper orgExtMapper;


	/**
	 * 科室轮转查询
	 * */
	@RequestMapping(value = {"/schDept" })
	public String schDept (String startDate, String endDate, Model model){
		List<String> titleDate = null;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(!StringUtil.isNotBlank(startDate)){
			startDate = DateUtil.getCurrDate();
			endDate = DateUtil.newDateOfAddMonths(startDate,12);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{// if(SchUnitEnum.Month.getId().equals(InitConfig.getSysCfg("res_rotation_unit")))
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
			model.addAttribute("titleDate",titleDate);
			List<SchArrangeResult> arrResultList = null;

			SysUser currentUser = GlobalContext.getCurrentUser();//查询所有轮转科室
			String trainingSpeId = currentUser.getResTrainingSpeId();
			String orgFlow = currentUser.getOrgFlow();
			ResTrainingSpeDept search = new ResTrainingSpeDept();
			search.setOrgFlow(orgFlow);
			search.setTrainingSpeId(trainingSpeId);
			List<ResTrainingSpeDept> resTrainingSpeDepts = resTrainingSpeDeptBiz.search(search);
			List<String> deptFlows = new ArrayList<>();
			if(resTrainingSpeDepts!=null&&resTrainingSpeDepts.size()>0){
				for(ResTrainingSpeDept resTrainingSpeDept:resTrainingSpeDepts){
					String deptFlow = resTrainingSpeDept.getDeptFlow();
					deptFlows.add(deptFlow);
				}
			}
			List<SchDept> schDeptList = null;
			if(deptFlows!=null&&deptFlows.size()>0){
				schDeptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
			}

			arrResultList =schArrangeResultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("schDeptList",schDeptList);

			if(arrResultList != null && arrResultList.size()>0){
				Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for(SchArrangeResult sar : arrResultList){
					String schDeptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
						if(isWeek){
							List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
							if(weeks!=null){
								for(String week : weeks){
									String key = schDeptFlow+week;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}else{
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = schDeptFlow+month;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
				model.addAttribute("resultListMap",resultListMap);
			}
		}
		return "sch/doc/professionalBaseSchDept";
	}

	/**
	 * 获取两个日期之间的所有周
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getWeeksByTwoDate(String startDate,String endDate){
		List<String> weeks = new ArrayList<>();
		List<String> periodTimeList = getPeriodTimeByTwoDate(startDate,endDate);
		for(String per : periodTimeList){
			weeks.add(per.split("~")[0]);
		}
		/*if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
			weeks = new ArrayList<String>();
			String startYear = startDate.substring(0,4);
			String endYear = endDate.substring(0,4);
			if(startYear.equals(endYear)){
				long startDays = DateUtil.signDaysBetweenTowDate(startDate,startYear+"-01-01");
				long endDays = DateUtil.signDaysBetweenTowDate(endDate,startYear+"-01-01");
				long startWeek = weekFormat(startDays);
				long endWeek = weekFormat(endDays);
				while(startWeek<=endWeek){
					weeks.add(startYear+"-"+startWeek);
					startWeek++;
				}
			}else{
				int start = Integer.parseInt(startYear);
				int end = Integer.parseInt(endYear);
				while(start<=end){
					String currYear = String.valueOf(start);
					long dayNum = 0;
					if(startYear.equals(currYear)){
						dayNum = DateUtil.signDaysBetweenTowDate(startDate,startYear+"-01-01");
						long endNum = DateUtil.signDaysBetweenTowDate(currYear+"-12-31",currYear+"-01-01");
						long startWeek = weekFormat(dayNum);
						long endWeek = weekFormat(endNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}else if(endYear.equals(currYear)){
						dayNum = DateUtil.signDaysBetweenTowDate(endDate,currYear+"-01-01");
						long startWeek = 1;
						long endWeek = weekFormat(dayNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}else{
						dayNum = DateUtil.signDaysBetweenTowDate(currYear+"-12-31",currYear+"-01-01");
						long startWeek = 1;
						long endWeek = weekFormat(dayNum);
						while(startWeek<=endWeek){
							weeks.add(currYear+"-"+startWeek);
							startWeek++;
						}
					}
					start++;
				}
			}
		}*/
		return weeks;
	}

	/**
	 * 获取两个时间段之间所有周时间段
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getPeriodTimeByTwoDate(String startDate,String endDate){
		List<String> weekTime = new ArrayList<>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
			Date startTime = sdf.parse(startDate);
			Date endTime = sdf.parse(endDate);
			Calendar ca = Calendar.getInstance();//创建一个日期实例
			ca.setTime(startTime);
			//int dayNum = ca.get(Calendar.DAY_OF_WEEK);
			int dayNum =startTime.getDay();
			int year = ca.get(Calendar.YEAR);
			int week = ca.get(Calendar.WEEK_OF_YEAR);
			//ca.add(Calendar.DAY_OF_MONTH, -7);
			//  System.out.println(ca.get(Calendar.MONTH));
			if(week==1&&1<ca.get(Calendar.MONTH)){
				year+=1;
			}
			String startWeek = year+"-"+week +"~" + sdf2.format(startTime)+ "—" + sdf2.format(new Date(startTime.getTime() + (7-dayNum) * 24 * 60 * 60 * 1000));
			weekTime.add(startWeek);
			startTime = new Date(startTime.getTime() + (8-dayNum) * 24 * 60 * 60 * 1000);
			while ((startTime.compareTo(new Date(endTime.getTime() - 7 * 24 * 60 * 60 * 1000))) < 0){
				ca.setTime(startTime);
				int year1 = ca.get(Calendar.YEAR);
				int week1 = ca.get(Calendar.WEEK_OF_YEAR);
				//ca.add(Calendar.DAY_OF_MONTH, -7);
				if(week1==1&&1<ca.get(Calendar.MONTH)){
					year1+=1;
				}
				String periodTime =year1+"-"+week1 +"~" + sdf2.format(startTime) +"—" + sdf2.format(new Date(startTime.getTime() + 6 * 24 * 60 * 60 * 1000));
				weekTime.add(periodTime);
				startTime = new Date(startTime.getTime() + 7 * 24 * 60 * 60 * 1000);
			}
			ca.setTime(startTime);//实例化一个日期
			int year2 = ca.get(Calendar.YEAR);
			int week2 = ca.get(Calendar.WEEK_OF_YEAR);
			//ca.add(Calendar.DAY_OF_MONTH, -7);
			if(week2==1&&1<ca.get(Calendar.MONTH)){
				year2+=1;
			}
			String endWeek = year2+"-"+week2 +"~" +sdf2.format(startTime) + "—" + sdf2.format(endTime);
			weekTime.add(endWeek);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return weekTime;
	}

	/**
	 * 获取两个月份之间的所有月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getMonthsByTwoMonth(String startDate,String endDate){
		List<String> months = null;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
			months = new ArrayList<String>();
			months.add(startDate);
			if(!startDate.equals(endDate)){
				String currDate = startDate;
				while(!currDate.equals(endDate)){
					currDate = DateUtil.newMonthOfAddMonths(currDate,1);
					months.add(currDate);
				}
			}
		}
		return months;
	}

	/**
	 * 医师轮转查询
	 * */
	@RequestMapping(value = {"/schDoc" },method = RequestMethod.GET)
	public String schDoc (DoctorSearchForm doctorSearchForm, Model model, Integer currentPage, HttpServletRequest request) throws Exception{
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		if(doctorSearchForm != null ){
			if(StringUtil.isBlank(doctorSearchForm.getDoctorCategoryId())){
				doctorSearchForm.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
			}
			if("all".equals(doctorSearchForm.getDoctorCategoryId())){
				doctorSearchForm.setDoctorCategoryId("");
			}
			model.addAttribute("doctorCategoryId",doctorSearchForm.getDoctorCategoryId());
			if(currentPage==null || currentPage==0){
				currentPage = 1;
			}
			List<ResDoctor> doctorList = null;
			if(StringUtil.isNotBlank(trainingSpeId)){
				doctorSearchForm.setTrainingSpeId(trainingSpeId);
				PageHelper.startPage(currentPage,getPageSize(request));
				doctorList = schDoctortBiz.searchResDoctor(orgFlow,doctorSearchForm);
			}

			if(doctorList != null && doctorList.size()>0){
				model.addAttribute("doctorList",doctorList);

				List<String> doctorFlows = new ArrayList<String>();
				for(ResDoctor doctor : doctorList){
					doctorFlows.add(doctor.getDoctorFlow());
				}
				List<SysUser> userList = userBiz.searchSysUserByuserFlows(doctorFlows);
				if(userList!=null && userList.size()>0){
					Map<String,SysUser> userMap = new HashMap<String, SysUser>();
					Map<String,String> ageMap = new HashMap<>();
					for(SysUser user : userList){
						userMap.put(user.getUserFlow(),user);
						PubUserResume pubUserResume = userResumeBiz.readPubUserResume(user.getUserFlow());
						if(pubUserResume != null){
							String xmlContent =  pubUserResume.getUserResume();
							if(StringUtil.isNotBlank(xmlContent)){
								//xml转换成JavaBean
								BaseUserResumeExtInfoForm extInfo=null;
								extInfo=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
								ageMap.put(user.getUserFlow(),extInfo.getAge());
							}
						}
					}
					model.addAttribute("userMap",userMap);
					model.addAttribute("ageMap",ageMap);
				}
			}
		}

		List<SchRotation> rotationList = schRotationtBiz.searchSchRotationByIsPublish();
		rotationList = schRotationtBiz.schRotations(rotationList,orgFlow);
		if(rotationList != null && rotationList.size()>0){
			Map<String,SchRotation> rotationMap = new HashMap<String,SchRotation>();
			for(SchRotation rotation : rotationList){
				rotationMap.put(rotation.getRotationFlow(),rotation);
			}
			model.addAttribute("rotationMap",rotationMap);
		}
		return "sch/doc/professionalBaseSchDoc";
	}

	@RequestMapping(value = {"/searchSchDoc" },method = RequestMethod.POST)
	public String searchSchDoc (DoctorSearchForm doctorSearchForm,Model model,Integer currentPage,HttpServletRequest request) throws Exception{
		return schDoc(doctorSearchForm,model,currentPage,request);
	}

	@RequestMapping(value = {"/rotationDetail" },method = RequestMethod.GET)
	public String rotationDetail(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = schDoctortBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			model.addAttribute("arrResultList",arrResultList);

			Map<String,Object> resultMap = new HashMap<>();
			if(arrResultList!=null&&arrResultList.size()>0){
				for(SchArrangeResult schArrangeResult:arrResultList){
					String resultFlow = schArrangeResult.getResultFlow();
					ResDoctorSchProcess doctorSchProcess = processBiz.searchByResultFlow(resultFlow);
					resultMap.put(resultFlow,doctorSchProcess);
				}
			}
			model.addAttribute("resultMap",resultMap);
		}
		return "schTwo/doc/professionalBaseRotationDetail";
	}

	@RequestMapping("/docoutReport")
	public String docoutReport(String schDeptFlow,String[] doctorTypeIds,String sessionNumber,String isSubmit,
							   String isAudit,String isHeadAudit,String doctorName,Integer currentPage,
							   HttpServletRequest request,Model model){
		List<String> doctorTypeIdList = new ArrayList<>();
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
			model.addAttribute("doctorTypeIdList",doctorTypeIdList);
		}else{
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("DoctorType");
			for(SysDict s:dictList){
				doctorTypeIdList.add(s.getDictId());
			}
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser currentUser = GlobalContext.getCurrentUser();
		if(currentUser!=null){
			String orgFlow =  currentUser.getOrgFlow();
			String trainingSpeId = currentUser.getResTrainingSpeId();
			if(StringUtil.isNotBlank(orgFlow)&&StringUtil.isNotBlank(trainingSpeId)){
			paramMap.put("orgFlow",orgFlow);
			paramMap.put("schDeptFlow",schDeptFlow);
			paramMap.put("doctorTypeIdList",doctorTypeIdList);
			paramMap.put("sessionNumber",sessionNumber);
			paramMap.put("isSubmit",isSubmit);
			paramMap.put("isAudit",isAudit);
			paramMap.put("isHeadAudit",isHeadAudit);
			paramMap.put("doctorName",doctorName);
			paramMap.put("trainingSpeId",trainingSpeId);
			PageHelper.startPage(currentPage,getPageSize(request));
			List<Map<String,String>> resultMapList = processBiz.searchDocoutReport(paramMap);
			model.addAttribute("resultMapList",resultMapList);

			//科室列表
			paramMap.clear();
			paramMap.put("orgFlow",orgFlow);
			paramMap.put("doctorTypeIdList",doctorTypeIdList);
			paramMap.put("trainingSpeId",trainingSpeId);
			List<Map<String,String>> resultMapList2 = processBiz.searchDocoutReport(paramMap);
			List<Map<String,String>> deptList = new ArrayList<>();
			List<String> deptFlows = new ArrayList<>();
				if(resultMapList2!=null && resultMapList2.size()>0){
					for(Map<String,String> result:resultMapList2){
						String deptFLow = result.get("SCH_DEPT_FLOW");
						if(!deptFlows.contains(deptFLow)){
							deptFlows.add(deptFLow);
							deptList.add(result);
						}
					}
				}
					model.addAttribute("deptList",deptList);
			}
		}
		return "res/professionalBase/docoutReport";
	}

	//导出出科情况
	@RequestMapping(value = "/exportDocoutReport")
	public void exportDocoutReport(String schDeptFlow,String[] doctorTypeIds,String sessionNumber,String isSubmit,
								   String isAudit,String isHeadAudit,String doctorName,Integer currentPage,
								   HttpServletRequest request,HttpServletResponse response)throws Exception {
		List<String> doctorTypeIdList = null;
		if(doctorTypeIds!=null&&doctorTypeIds.length>0){
			doctorTypeIdList = Arrays.asList(doctorTypeIds);
		}
		Map<String,Object> paramMap = new HashMap<>();
		SysUser currentUser = GlobalContext.getCurrentUser();
		List<Map<String, String>> resultMapList = null;
		if(currentUser!=null) {
			String orgFlow = currentUser.getOrgFlow();
			String trainingSpeId = currentUser.getResTrainingSpeId();
			if (StringUtil.isNotBlank(orgFlow)) {
				paramMap.put("orgFlow", orgFlow);
				paramMap.put("schDeptFlow", schDeptFlow);
				paramMap.put("doctorTypeIdList", doctorTypeIdList);
				paramMap.put("sessionNumber", sessionNumber);
				paramMap.put("isSubmit", isSubmit);
				paramMap.put("isAudit", isAudit);
				paramMap.put("isHeadAudit", isHeadAudit);
				paramMap.put("doctorName", doctorName);
				paramMap.put("trainingSpeId",trainingSpeId);
				resultMapList = processBiz.searchDocoutReport(paramMap);
			}
		}
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("出科情况列表");
		//列宽自适应
		sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(0, 0, 0, 7));//合并单元格
		HSSFRow rowOne = sheet.createRow(0);//第一行
		rowOne.setHeightInPoints(18);
		HSSFCell cellOne = rowOne.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("出科情况列表");
		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"姓名",
				"专业",
				"年级",
				"科室",
				"带教老师",
				"学员提交情况",
				"带教老师审核情况",
				"科主任审核情况"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 512);
		}
		int rowNum = 2;
		String[] resultList = null;
		if (resultMapList != null && !resultMapList.isEmpty()) {
			for (int i = 0; i < resultMapList.size(); i++, rowNum++) {
				HSSFRow rowThree = sheet.createRow(rowNum);
				resultList = new String[]{
						resultMapList.get(i).get("DOCTOR_NAME")==null?"":resultMapList.get(i).get("DOCTOR_NAME"),
						resultMapList.get(i).get("TRAINING_SPE_NAME")==null?"":resultMapList.get(i).get("TRAINING_SPE_NAME"),
						resultMapList.get(i).get("SESSION_NUMBER")==null?"":resultMapList.get(i).get("SESSION_NUMBER"),
						resultMapList.get(i).get("SCH_DEPT_NAME")==null?"":resultMapList.get(i).get("SCH_DEPT_NAME"),
						resultMapList.get(i).get("TEACHER_USER_NAME")==null?"":resultMapList.get(i).get("TEACHER_USER_NAME"),
						("0".equals(resultMapList.get(i).get("SUBMIT_NUMBER"))?"未提交":"已提交")+(resultMapList.get(i).get("CREATE_TIME")==null?"":resultMapList.get(i).get("CREATE_TIME")),
						(resultMapList.get(i).get("AUDIT_STATUS_NAME")==null?"":resultMapList.get(i).get("AUDIT_STATUS_NAME"))+(resultMapList.get(i).get("AUDIT_TIME")==null?"":resultMapList.get(i).get("AUDIT_TIME")),
						(resultMapList.get(i).get("HEAD_AUDIT_STATUS_NAME")==null?"":resultMapList.get(i).get("HEAD_AUDIT_STATUS_NAME"))+(resultMapList.get(i).get("HEAD_AUDIT_TIME")==null?"":resultMapList.get(i).get("HEAD_AUDIT_TIME"))
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowThree.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(resultList[j]);
				}
			}
		}
		String fileName = "出科情况列表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	//出科成绩查询
	@RequestMapping("/doctorRecruitResult")
	public String doctorRecruitResult(ResDoctorExt resDoctor,String[] doctorTypeList,Integer currentPage,Model model,
									  HttpServletRequest request,String role){
		List<SysOrg> orgList=new ArrayList<>();
		if(GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(role)){
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgList = orgBiz.searchOrg(org);
			if("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))){//江苏中医默认展示江苏省中医院
				if(StringUtil.isBlank(resDoctor.getOrgFlow())){
					SysOrg sysOrg = orgBiz.readSysOrgByName("江苏省中医院");
					if(sysOrg!=null){
						String orgFlow = sysOrg.getOrgFlow();
						resDoctor.setOrgFlow(orgFlow);
						model.addAttribute("orgFlow",orgFlow);
					}
				}
				if(StringUtil.isBlank(resDoctor.getDoctorCategoryId())){
					String doctorCategoryId = RecDocCategoryEnum.ChineseMedicine.getId();
					resDoctor.setDoctorCategoryId(doctorCategoryId);
					model.addAttribute("doctorCategoryId",doctorCategoryId);
				}
			}
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			if(StringUtil.isNotBlank(currentOrgFlow)){
				SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
				String workOrgId = org.getSendSchoolId();
				orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
				resDoctor.setWorkOrgId(workOrgId);
			}
			if("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))){//江苏中医默认展示江苏省中医院
				if(StringUtil.isBlank(resDoctor.getOrgFlow())){
					SysOrg sysOrg = orgBiz.readSysOrgByName("江苏省中医院");
					if(sysOrg!=null){
						String orgFlow = sysOrg.getOrgFlow();
						resDoctor.setOrgFlow(orgFlow);
						model.addAttribute("orgFlow",orgFlow);
					}
				}
				if(StringUtil.isBlank(resDoctor.getDoctorCategoryId())){
					String doctorCategoryId = RecDocCategoryEnum.ChineseMedicine.getId();
					resDoctor.setDoctorCategoryId(doctorCategoryId);
					model.addAttribute("doctorCategoryId",doctorCategoryId);
				}
			}
		}else{
			//医院管理员增加查协同基地学员轮转情况
			SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			orgList.add(sysOrg);
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
			if("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))){//江苏中医默认展示江苏省中医院
				if(StringUtil.isBlank(resDoctor.getOrgFlow())){
					SysOrg sysOrg1 = orgBiz.readSysOrgByName("江苏省中医院");
					if(sysOrg1!=null){
						String orgFlow = sysOrg1.getOrgFlow();
						resDoctor.setOrgFlow(orgFlow);
						model.addAttribute("orgFlow",orgFlow);
					}
				}
				if(StringUtil.isBlank(resDoctor.getDoctorCategoryId())){
					String doctorCategoryId = RecDocCategoryEnum.ChineseMedicine.getId();
					resDoctor.setDoctorCategoryId(doctorCategoryId);
					model.addAttribute("doctorCategoryId",doctorCategoryId);
				}
			}
		}
		model.addAttribute("orgList",orgList);
        //查询后台配置是否为进修过程管理页面
        String flag= InitConfig.getSysCfg("is_show_jxres");
//        HttpSession session = GlobalContext.getSession();
////      HttpSession session=request.getSession();//获取session
//        String[]   names   =   session.getValueNames();
//        for   (int   i   =   0;   i   <   names.length;   i++)   {
//            System.out.println(names[i] + ","   +   session.getValue(names[i]));
//        }
        setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, deptBiz.searchDeptByOrg(GZZY_ORG_FLOW));
//        Object obj = session.getAttribute(GlobalConstant.CURRENT_DEPT_LIST);
//        System.out.println(obj);
        if(!GlobalConstant.FLAG_Y.equals(flag)){
            List<String> doctorTypeIdList = null;
            if(doctorTypeList!=null&&doctorTypeList.length>0){
                doctorTypeIdList = Arrays.asList(doctorTypeList);
                resDoctor.setDoctorTypeIdList(doctorTypeIdList);
                model.addAttribute("doctorTypeIdList",doctorTypeIdList);//上一次选中的条件传到页面上
            }else{
                List<String> paramList = new ArrayList<>();
                List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("DoctorType");
                for(SysDict s:dictList){
                    paramList.add(s.getDictId());
                }
                resDoctor.setDoctorTypeIdList(paramList);
            }
        }

		SysUser currentUser = GlobalContext.getCurrentUser();
		if(currentUser!=null){
			String orgFlow = currentUser.getOrgFlow();
			String trainingSpeId = currentUser.getResTrainingSpeId();
			if(StringUtil.isBlank(resDoctor.getOrgFlow())){
				if(!GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(role)&&!GlobalConstant.USER_LIST_UNIVERSITY.equals(role)&&orgList.size()>0){
					resDoctor.setOrgFlow(orgFlow);
				}
			}
			if(StringUtil.isBlank(role))
				resDoctor.setTrainingSpeId(trainingSpeId);

			if(StringUtil.isBlank(resDoctor.getDoctorCategoryId())){
                if(GlobalConstant.FLAG_Y.equals(flag)){
                    resDoctor.setDoctorCategoryId(RecDocCategoryEnum.Scholar.getId());//进修生
                }else{
                    resDoctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
                }
			}
            if(GlobalConstant.FLAG_N.equals(flag)){
                if("all".equals(resDoctor.getDoctorCategoryId())){
                    resDoctor.setDoctorCategoryId("");
                }
            }

			model.addAttribute("doctorCategoryId",resDoctor.getDoctorCategoryId());
			List<ResDoctorExt> doctorList = null;
			if(StringUtil.isBlank(role)){
				if (StringUtil.isNotBlank(trainingSpeId)) {
					PageHelper.startPage(currentPage, getPageSize(request));
					doctorList = doctorBiz.searchDocUser(resDoctor, "");
				}
			}else{
				PageHelper.startPage(currentPage, getPageSize(request));
				doctorList = doctorBiz.searchDocUser(resDoctor, "");
			}

			model.addAttribute("doctorList",doctorList);
		}

        if(GlobalConstant.FLAG_Y.equals(flag)){
			return "res/professionalBase/doctorRecuritResultList_jx";
		}
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			return "res/professionalBase/doctorRecuritResultList4jszy";
		}
		return "res/professionalBase/doctorRecuritResultList";
	}

	@RequestMapping("/doctorRecruitResultDetail")
	public String doctorRecruitResultDetail(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = schDoctortBiz.readResDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			model.addAttribute("arrResultList",arrResultList);

			Map<String,Object> resultMap = new HashMap<>();
			Map<String,String> DOPSFlowMap = new HashMap<>();
			Map<String,String> MiniFlowMap = new HashMap<>();
			Map<String,String> AfterFlowMap = new HashMap<>();
			Map<String,String> AfterSummFlowMap = new HashMap<>();
			Map<String,String> fileMap = new HashMap<>();
			if(arrResultList!=null&&arrResultList.size()>0){
				for(SchArrangeResult schArrangeResult:arrResultList){
					String resultFlow = schArrangeResult.getResultFlow();
					List<PubFile> files=fileBiz.findFileByLikeTypeFlow("SkillFile",resultFlow);
					if(files!=null&&files.size()>0)
					{
						fileMap.put(resultFlow,"Y");
					}else{
						fileMap.put(resultFlow,"N");
					}
					ResDoctorSchProcess doctorSchProcess = processBiz.searchByResultFlow(resultFlow);
					resultMap.put(resultFlow,doctorSchProcess);
					if(doctorSchProcess!=null){
						String processFlow = doctorSchProcess.getProcessFlow();
						List<ResSchProcessExpress> resRecs = expressBiz.searchByProcessFlowClob(processFlow);
						if(resRecs!=null&&resRecs.size()>0)
						{
							for(ResSchProcessExpress r:resRecs)
							{
								if(AfterRecTypeEnum.DOPS.getId().equals(r.getRecTypeId()))
								{
									DOPSFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.Mini_CEX.getId().equals(r.getRecTypeId()))
								{
									MiniFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.AfterSummary.getId().equals(r.getRecTypeId()))
								{
									AfterSummFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.AfterEvaluation.getId().equals(r.getRecTypeId()))
								{
									AfterFlowMap.put(processFlow,r.getRecFlow());
								}
							}
						}
					}
				}
			}
			model.addAttribute("fileMap",fileMap);
			model.addAttribute("resultMap",resultMap);
			model.addAttribute("DOPSFlowMap",DOPSFlowMap);
			model.addAttribute("MiniFlowMap",MiniFlowMap);
			model.addAttribute("AfterFlowMap",AfterFlowMap);
			model.addAttribute("AfterSummFlowMap",AfterSummFlowMap);
		}
		String cfg13= InitConfig.getSysCfg("jswjw_"+GlobalContext.getCurrentUser().getOrgFlow()+"_P013");
		model.addAttribute("cfg13",cfg13);
		return "res/professionalBase/doctorRecuritResultDetail";
	}

	//年度成绩查询
	@RequestMapping(value="/annualExamScore")
	public String scorelist(Model model,Integer currentPage ,HttpServletRequest request,String[] doctorTypeList,
							String sessionNumber,String assessmentYear,
							String userName ,String doctorCategoryId , String graduationYear){
		SysUser sysuser=GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(assessmentYear))
			assessmentYear= DateUtil.getYear();
		Integer firstYear=Integer.valueOf(assessmentYear)-2;
		Integer secondYear=Integer.valueOf(assessmentYear)-1;
		List<String> years=new ArrayList<>();
		years.add(String.valueOf(firstYear));
		years.add(String.valueOf(secondYear));
		years.add(assessmentYear);
		model.addAttribute("years",years);
		PageHelper.startPage(currentPage, getPageSize(request));
		ResDoctorExt doctor=new ResDoctorExt();
		doctor.setSessionNumber(sessionNumber);
		doctor.setTrainingSpeId(GlobalContext.getCurrentUser().getResTrainingSpeId());
		SysUser d=new SysUser();
		d.setUserName(userName);
		doctor.setSysUser(d);
		if(doctorTypeList!=null&&doctorTypeList.length>0){
			doctor.setDoctorTypeIdList(Arrays.asList(doctorTypeList));
			model.addAttribute("doctorTypeIdList",Arrays.asList(doctorTypeList));
		}else{
			List<String> paramList = new ArrayList<>();
			List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("DoctorType");
			for(SysDict s:dictList){
				paramList.add(s.getDictId());
			}
			doctor.setDoctorTypeIdList(paramList);
		}
		if(StringUtil.isBlank(doctorCategoryId)){
			doctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
		}else if("all".equals(doctorCategoryId)){
			doctor.setDoctorCategoryId("");
		}else{
			doctor.setDoctorCategoryId(doctorCategoryId);
		}
		model.addAttribute("doctorCategoryId",doctor.getDoctorCategoryId());
		doctor.setGraduationYear(graduationYear);
		doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<ResDoctorExt> list = doctorBiz.searchDocUser(doctor, "");
		model.addAttribute("list",list);
		if(list!=null&&list.size()>0)
		{
			List<String> userFlows=new ArrayList<>();
			for(ResDoctorExt user:list)
			{
				userFlows.add(user.getDoctorFlow());
			}//查询条件
			Map<String,Object> param=new HashMap<>();
			param.put("orgFlow",sysuser.getOrgFlow());
			param.put("userFlows",userFlows);
			List<SchExamDoctorArrangement> doctorArrangements=scoreQueryBiz.getDoctorArrangements(param);
			if(doctorArrangements!=null&&doctorArrangements.size()>0){
				Map<String,SchExamDoctorArrangement> doctorArrangementMap=new HashMap<>();
				for(SchExamDoctorArrangement da:doctorArrangements)
				{
					doctorArrangementMap.put(da.getAssessmentYear()+da.getDoctorFlow()+da.getSessionNumber(),da);
				}
				model.addAttribute("daMap",doctorArrangementMap);
			}
		}

		return "res/professionalBase/annualExamScore";
	}

	//专业基地管理员页面
	@RequestMapping("/professionalBaseManagerList")
	public String professionalBaseManagerList(Integer currentPage ,SysUser search,String deptFlow,String orgFlow,String role,
					   Model model,HttpServletRequest request){
		ServletContext application = request.getServletContext();
		Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
		String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
		List<SysUser> sysUserList=null;//初始化查询结果
		//组织用户查询map
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("roleFlow",sysCfgMap.get("res_professionalBase_admin_role_flow"));
		if(StringUtil.isBlank(sysCfgMap.get("res_professionalBase_admin_role_flow"))){
			return "res/professionalBase/professionalBaseManagerList";
		}
		paramMap.put("wsId","res");
		if(search!=null){
			paramMap.put("resTrainingSpeId",search.getResTrainingSpeId());
			paramMap.put("resTrainingSpeName",search.getResTrainingSpeName());
			paramMap.put("userPhone",search.getUserPhone());
			paramMap.put("userName",search.getUserName());
			paramMap.put("statusId",search.getStatusId());
			paramMap.put("userEmail",search.getUserEmail());
			paramMap.put("userCode",search.getUserCode());
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		paramMap.put("orgFlow",currUser.getOrgFlow());
		List<String> orgFlowList = new ArrayList<>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){
			//查询所有医院
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			model.addAttribute("orgs",orgs);
			paramMap.put("orgFlow",orgFlow);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			model.addAttribute("orgs",orgs);
			paramMap.put("orgFlow",orgFlow);
			paramMap.put("orgFlows",orgFlowList);
		}
		paramMap.put("deptFlow",deptFlow);
		PageHelper.startPage(currentPage, getPageSize(request));
		sysUserList = userBiz.searchUserWithRole(paramMap);
		model.addAttribute("sysUserList",sysUserList);
		model.addAttribute("role",role);
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)||GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			return "res/professionalBase/professionalBaseManagerList4Global";
		}
		return "res/professionalBase/professionalBaseManagerList";
	}

	//打印专业基地管理员页面
	@RequestMapping("/exportProfessionalBaseManagerList")
	public void exportProfessionalBaseManagerList(SysUser search,String deptFlow,String orgFlow,String role,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ServletContext application = request.getServletContext();
		Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
		String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
		List<SysUser> sysUserList=null;//初始化查询结果
		//组织用户查询map
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("roleFlow",sysCfgMap.get("res_professionalBase_admin_role_flow"));
		paramMap.put("wsId","res");
		if(search!=null){
			paramMap.put("resTrainingSpeId",search.getResTrainingSpeId());
			paramMap.put("resTrainingSpeName",search.getResTrainingSpeName());
			paramMap.put("userPhone",search.getUserPhone());
			paramMap.put("userName",search.getUserName());
			paramMap.put("statusId",search.getStatusId());
			paramMap.put("userEmail",search.getUserEmail());
			paramMap.put("userCode",search.getUserCode());
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		paramMap.put("orgFlow",currUser.getOrgFlow());
		List<String> orgFlowList = new ArrayList<>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){
			//查询所有医院
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			paramMap.put("orgFlow",orgFlow);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			paramMap.put("orgFlow",orgFlow);
			paramMap.put("orgFlows",orgFlowList);
		}
		paramMap.put("deptFlow",deptFlow);
		sysUserList = userBiz.searchUserWithRole(paramMap);
		String[] titles = new String[]{
				"userName:负责人",
				"userCode:登录名",
				"sexName:性别",
				"userPhone:手机号码",
				"postName:职务",
				"userEmail:邮箱",
				"resTrainingSpeName:专业基地",
				"statusDesc:状态"
		};

		String fileName = "专业基地管理员列表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, sysUserList, response.getOutputStream());
	}

	//专业基地科室配置
	@RequestMapping("/deptsCfgNew")
	public String deptsCfgNew(Model model, String resTrainingSpeId) {
		SysUser sysUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
		sysDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysDept.setOrgFlow(sysUser.getOrgFlow());
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);//所有科室列表

		ResTrainingSpeDept search = new ResTrainingSpeDept();
		search.setOrgFlow(sysUser.getOrgFlow());
		search.setTrainingSpeId(resTrainingSpeId);
		List<ResTrainingSpeDept> trainingSpeDeptList = resTrainingSpeDeptBiz.search(search);
		model.addAttribute("trainingSpeDeptList", trainingSpeDeptList);//对应科室列表

		List<ResTrainingSpeDept> notCurrentSpeDeptList = resTrainingSpeDeptBiz.notCurrentSpe(search);
		model.addAttribute("notCurrentSpeDeptList", notCurrentSpeDeptList);//非对应科室列表
		return "res/professionalBase/deptsCfgNew";
	}

	//专业基地科室配置
	@RequestMapping("/deptsCfg")
	public String deptsCfg(Model model,String resTrainingSpeId){
		SysUser sysUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
		sysDept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysDept.setOrgFlow(sysUser.getOrgFlow());
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList",sysDeptList);//所有科室列表

		ResTrainingSpeDept search = new ResTrainingSpeDept();
		search.setOrgFlow(sysUser.getOrgFlow());
		search.setTrainingSpeId(resTrainingSpeId);
		List<ResTrainingSpeDept> trainingSpeDeptList = resTrainingSpeDeptBiz.search(search);
		model.addAttribute("trainingSpeDeptList",trainingSpeDeptList);//对应科室列表

		List<ResTrainingSpeDept> notCurrentSpeDeptList = resTrainingSpeDeptBiz.notCurrentSpe(search);
		model.addAttribute("notCurrentSpeDeptList",notCurrentSpeDeptList);//非对应科室列表
		return "res/professionalBase/deptsCfg";
	}

	//保存专业基地科室
	@RequestMapping("/saveDepts")
	@ResponseBody
	public String saveDepts(String jsondata){
		Map<String,Object> dataMap = JSON.parseObject(jsondata);
		SysUser sysUser = GlobalContext.getCurrentUser();
		String orgFlow = sysUser.getOrgFlow();
		String resTrainingSpeId = (String)dataMap.get("resTrainingSpeId");
		List<Map<String,Object>> deptList = (List<Map<String,Object>>)dataMap.get("deptList");
		ResTrainingSpeDept searchOld = new ResTrainingSpeDept();//查询老科室的列表
		searchOld.setOrgFlow(orgFlow);
		searchOld.setTrainingSpeId(resTrainingSpeId);
		List<ResTrainingSpeDept> oldDeptList = resTrainingSpeDeptBiz.search(searchOld);
		Map<String,String> oldDeptFlows = new HashMap<>();
		if(oldDeptList!=null&&oldDeptList.size()>0){
			for(ResTrainingSpeDept resTrainingSpeDept:oldDeptList){
				String deptFlow = resTrainingSpeDept.getDeptFlow();
				String recordFlow = resTrainingSpeDept.getRecordFlow();
				oldDeptFlows.put(deptFlow,recordFlow);
			}
		}

		if(deptList!=null&&deptList.size()>0){
			for(Map<String,Object> dept:deptList){
				if(oldDeptFlows.get((String)dept.get("deptFlow"))!=null){
					oldDeptFlows.remove((String)dept.get("deptFlow"));
				}
				ResTrainingSpeDept trainingSpeDept = new ResTrainingSpeDept();
				trainingSpeDept.setOrgFlow(orgFlow);
				trainingSpeDept.setTrainingSpeId(resTrainingSpeId);
				trainingSpeDept.setTrainingSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(resTrainingSpeId));
				trainingSpeDept.setDeptFlow((String)dept.get("deptFlow"));
				trainingSpeDept.setDeptName((String)dept.get("deptName"));
				List<ResTrainingSpeDept> trainingSpeDeptList = resTrainingSpeDeptBiz.search(trainingSpeDept);//判断是否是存过的科室
				if(trainingSpeDeptList.size()==0){
					int result = resTrainingSpeDeptBiz.edit(trainingSpeDept);
					if(result==0){
						return GlobalConstant.OPERATE_FAIL;
					}
				}
			}
		}
		if(oldDeptFlows!=null&&oldDeptFlows.size()>0){//删除未选中科室
			for(String key : oldDeptFlows.keySet()){
				String recordFlow = oldDeptFlows.get(key);
				delDept(recordFlow);
			}
		}
		return GlobalConstant.OPERATE_SUCCESSED;
	}

	//删除专业基地科室
	public int delDept(String recordFlow){
		ResTrainingSpeDept resTrainingSpeDept = new ResTrainingSpeDept();
		resTrainingSpeDept.setRecordFlow(recordFlow);
		resTrainingSpeDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		return resTrainingSpeDeptBiz.edit(resTrainingSpeDept);
	}

	//专业基地评估配置
	@RequestMapping("/evalCfg")
	public String evalCfg(Model model,String trainingSpeId){
		ResBaseevalForm searchForm = new ResBaseevalForm();
		List<ResBaseevalForm> baseevalFormList = evalBiz.search(searchForm);
		model.addAttribute("baseevalFormList",baseevalFormList);//所有表单列表

		SysUser currentUser = GlobalContext.getCurrentUser();
		ResBaseevalFormCfg search = new ResBaseevalFormCfg();
		if(StringUtil.isBlank(trainingSpeId)){
			trainingSpeId = "xxxxxxxxxxxxxxxxxxxxxxx";
		}
		search.setTrainingSpeId(trainingSpeId);
		search.setOrgFlow(currentUser.getOrgFlow());
		List<ResBaseevalFormCfg> baseevalFormCfgList = evalBiz.searchCfg(search);
		if(baseevalFormCfgList!=null&&baseevalFormCfgList.size()>0){
			model.addAttribute("baseevalFormCfgs",baseevalFormCfgList);//对应专业基地
		}

		return "res/professionalBase/evalCfg";
	}

	//保存专业基地评估表单配置
	@RequestMapping("/saveEvalCfg")
	@ResponseBody
	public int saveEvalCfg(ResBaseevalFormCfg baseevalFormCfg,String jsonData){
		return evalBiz.editEvalCfgs(baseevalFormCfg,jsonData);
	}

	//专业基地评估页面
	@RequestMapping("/eval")
	public String eval(Model model,String role){
		model.addAttribute("role",role);
		SysUser user = GlobalContext.getCurrentUser();
		String trainingSpeId = user.getResTrainingSpeId();

		//评估表单
		ResBaseevalFormCfg search = new ResBaseevalFormCfg();
		search.setTrainingSpeId(trainingSpeId);
		search.setOrgFlow(user.getOrgFlow());
		List<ResBaseevalFormCfg> baseevalFormCfgList = evalBiz.searchCfg(search);
		model.addAttribute("baseevalFormCfgList",baseevalFormCfgList);

		if("manager".equals(role)){
			String formFlow = "00000000000000000000000000000001";
			String formName = "基地评估表单";
			ResBaseevalFormCfg baseCfg = new ResBaseevalFormCfg();
			baseCfg.setFormFlow(formFlow);
			baseCfg.setFormName(formName);
			List<ResBaseevalFormCfg> list = new ArrayList<>();
			list.add(baseCfg);
			model.addAttribute("baseevalFormCfgList",list);
		}
		return "res/professionalBase/eval";
	}

	@RequestMapping("/evalDetail")
	public String evalDetail(Model model,String formFlow,String role){
		model.addAttribute("formFlow",formFlow);
		SysUser user = GlobalContext.getCurrentUser();
		String trainingSpeId = user.getResTrainingSpeId();
		//评估记录
		ResBaseeval searchEval = new ResBaseeval();
		searchEval.setFormFlow(formFlow);
		if("manager".equals(role)){
			searchEval.setSpeId("基地");
		}else {
			searchEval.setSpeId(trainingSpeId);
		}
		List<ResBaseeval> baseevalList = evalBiz.searchBaseeval(searchEval);
		if(baseevalList!=null && baseevalList.size()>0){
			ResBaseeval baseeval = baseevalList.get(0);
			model.addAttribute("baseeval",baseeval);
			String evalFlow = baseeval.getRecordFlow();
			ResBaseevalDetail searchBaseevalDetail = new ResBaseevalDetail();
			searchBaseevalDetail.setBaseevalFlow(evalFlow);
			List<ResBaseevalDetail> baseevalDetailList = evalBiz.searchBaseevalDetail(searchBaseevalDetail);
			Map<String,Object> scoreMap = new HashMap<>();
			Map<String,Object> marksMap = new HashMap<>();
			Map<String,Object> fileMap = new HashMap<>();
			if(baseevalDetailList!=null&&baseevalDetailList.size()>0){
				for(ResBaseevalDetail baseevalDetail:baseevalDetailList){
					String orderNumber = baseevalDetail.getOrderNumber();
					String score = baseevalDetail.getScore();
					String deductMarks = baseevalDetail.getDeductMarks();
					String filePath = baseevalDetail.getFilePath();
					scoreMap.put(orderNumber,score);
					marksMap.put(orderNumber,deductMarks);
					fileMap.put(orderNumber,filePath);

				}
			}
			model.addAttribute("scoreMap",scoreMap);
			model.addAttribute("marksMap",marksMap);
			model.addAttribute("fileMap",fileMap);
		}
		ResBaseevalForm baseevalForm = evalBiz.readForm(formFlow);
		return baseevalForm.getFormUrl();
	}

	//专业基地评估页面
	@RequestMapping("/evalList")
	public String evalList(Model model,String resTrainingSpeName){
		if(StringUtil.isBlank(resTrainingSpeName)){
			resTrainingSpeName = "内科";
			model.addAttribute("resTrainingSpeName",resTrainingSpeName);
		}
		SysUser user = GlobalContext.getCurrentUser();
		String orgFlow = user.getOrgFlow();
		//评估表单
		ResBaseevalFormCfg search = new ResBaseevalFormCfg();
		search.setTrainingSpeName(resTrainingSpeName);
		search.setOrgFlow(orgFlow);
		List<ResBaseevalFormCfg> baseevalFormCfgList = evalBiz.searchCfg(search);
		model.addAttribute("baseevalFormCfgList",baseevalFormCfgList);
		model.addAttribute("viewFlag","Y");
		return "res/professionalBase/evalList";
	}

	//保存专业基地评估记录
	@RequestMapping("/saveEval")
	@ResponseBody
	public int saveEval(String jsonData,String role){
		return evalBiz.editBaseevalTings(jsonData,role);
	}

	//带教工作量查询
	@RequestMapping(value = "/teacherWorkload")
	public String teacherWorkload(String isShow,Model model,SchDept schDept,String operStartDate,String userName,String operEndDate,Integer currentPage,HttpServletRequest request) throws DocumentException {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		if(currentPage==null){
			currentPage=1;
		}
		if(StringUtil.isBlank(operStartDate) && GlobalConstant.FLAG_Y.equals(isShow)){
			operStartDate = DateUtil.addDate(DateUtil.getCurrDate(),-30);
		}
		if(StringUtil.isBlank(operEndDate) && GlobalConstant.FLAG_Y.equals(isShow)){
			operEndDate = DateUtil.getCurrDate();
		}
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		ResTrainingSpeDept search = new ResTrainingSpeDept();//查出当前专业基地的所有科室
		search.setOrgFlow(orgFlow);
		search.setTrainingSpeId(trainingSpeId);
		List<ResTrainingSpeDept> trainingSpeDeptList = resTrainingSpeDeptBiz.search(search);
		model.addAttribute("deptList",trainingSpeDeptList);

		List<String> deptFlows = new ArrayList<>();
		if(trainingSpeDeptList!=null&&trainingSpeDeptList.size()>0){
			for(ResTrainingSpeDept resTrainingSpeDept:trainingSpeDeptList){
				String deptFLow = resTrainingSpeDept.getDeptFlow();
				deptFlows.add(deptFLow);
			}
		}

		model.addAttribute("operStartDate",operStartDate);
		model.addAttribute("operEndDate",operEndDate);
		Map<String,Object> map=new HashMap<String,Object>();
		String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		map.put("deptFlows",deptFlows);
		map.put("schDept",schDept);
		map.put("operStartDate",operStartDate);
		map.put("operEndDate",operEndDate);
		map.put("recTypeIds",recTypeIds);
		map.put("userName",userName);
		map.put("orgFlow",orgFlow);
		map.put("roleFlow",teacherRoleFlow);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list=processBiz.searchTeacherWorkload(map);
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> b:list)
			{
				String teacherFlow= (String) b.get("userFlow");
				String schDeptFlow= (String) b.get("schDeptFlow");
				map.put("userFlow",teacherFlow);
				map.put("schDeptFlow",schDeptFlow);
				int auditNum=processBiz.getTeaDeptAuditNum(map);
				int notAuditNum=processBiz.getTeaDeptNotAuditNum(map);
				b.put("auditNumber",auditNum);
				b.put("notAuditNumber",notAuditNum);
			}
		}
		model.addAttribute("list",list);
		return "res/professionalBase/teacherWorkload";
	}

	@RequestMapping("/uploadFile")
	public String uploadFile(String operType, Model model) {
		model.addAttribute("operType", operType);
		return "res/professionalBase/uploadFile";
	}

	@RequestMapping(value = "/checkUploadFile", method = {RequestMethod.POST})
	public String checkUploadFile(String operType, MultipartFile uploadFile, Model model) {
		model.addAttribute("operType", operType);
		if(uploadFile!=null && (!uploadFile.isEmpty())){
			String originalFileName = uploadFile.getOriginalFilename();
			//定义上传路径
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "baseEval" + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//重命名上传后的文件名
			originalFileName = PkUtil.getUUID() + originalFileName;
			File newFile = new File(fileDir, originalFileName);
			try {
				uploadFile.transferTo(newFile);
			} catch (Exception e) {
				throw new RuntimeException("文件上传异常");
			}
			String filePath = "/baseEval/" + dateString + "/" + originalFileName;
			model.addAttribute("filePath",filePath);
			model.addAttribute("result","Y");
		}
		return "res/professionalBase/uploadFile";
	}
	//出科考配置
	@RequestMapping("/outTestCfg")
	public String outTestCfg(String refresh,HttpServletRequest req){

		return "res/professionalBase/outTestCfg";
	}
	@RequestMapping(value="/cfgDetail",method={RequestMethod.GET})
	public String cfgDetail(Model model){
		return "res/professionalBase/cfgDetail";
	}
}


