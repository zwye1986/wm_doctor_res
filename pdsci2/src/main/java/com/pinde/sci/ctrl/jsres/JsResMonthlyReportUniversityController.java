package com.pinde.sci.ctrl.jsres;

import com.pinde.core.common.enums.sys.CertificateTypeEnum;
import com.pinde.core.common.sci.dao.ResBaseMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResMonthlyReportBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.core.common.form.UserResumeExtInfoForm;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/jsres/monthlyReportUniversity")
public class JsResMonthlyReportUniversityController extends GeneralController {
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
		return "jsres/university/monthlyReport/monthlyReportMain";
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

		return "jsres/university/monthlyReport/content0";
	}

	@RequestMapping("/content1")
	public String content1(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		return "jsres/university/monthlyReport/content1";
	}

	@RequestMapping("/chart123")//图一图二图三
	public String chart123(String monthDate,String isGraduate,Model model){
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorTypeId(isGraduate);
		search.setDoctorStatusId("20");
        search.setChangeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		String sendSchoolId = org.getSendSchoolId();
		String sendSchoolName = org.getSendSchoolName();
		List<SysMonthlyDoctorInfo> doctorInfoListFinal = new ArrayList<>();//三图公用数据
		if(!sysMonthlyDoctorInfoList.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				if(
						(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getWorkOrgId()) &&
						sysMonthlyDoctorInfo.getWorkOrgId().equals(sendSchoolId)) ||
						(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getWorkOrgName()) &&
						sysMonthlyDoctorInfo.getWorkOrgName().equals(sendSchoolName))
						){
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

		return "jsres/university/monthlyReport/chart123";
	}

	@RequestMapping("/chart4")//图四
	public String chart4(String monthDate,String isGraduate,Model model){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
        paramMap.put("sendSchoolFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		paramMap.put("sendSchoolId",StringUtil.defaultIfEmpty(org.getSendSchoolId(), PkUtil.getUUID()));
		paramMap.put("sendSchoolName",StringUtil.defaultIfEmpty(org.getSendSchoolName(), PkUtil.getUUID()));

		List<SysMonthlyChangeInfo> SysMonthlyChangeInfoFinal = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoFinal = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);

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
		return "jsres/university/monthlyReport/chart4";
	}

	@RequestMapping("/chart5")//图五
	public String chart5(String monthDate,Model model){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("sendSchoolId",StringUtil.defaultIfEmpty(org.getSendSchoolId(), PkUtil.getUUID()));
		paramMap.put("sendSchoolName",StringUtil.defaultIfEmpty(org.getSendSchoolName(), PkUtil.getUUID()));

		Map<String,Object> resultMap = monthlyReportBiz.getUniverseChart5Data(paramMap);
		model.addAttribute("resultMap",resultMap);

		return "jsres/university/monthlyReport/chart5";
	}


	@RequestMapping("/chart6")//图六
	public String chart6(String monthDate,String isGraduate,Model model){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
        paramMap.put("sendSchoolFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		paramMap.put("sendSchoolId",StringUtil.defaultIfEmpty(org.getSendSchoolId(), PkUtil.getUUID()));
		paramMap.put("sendSchoolName",StringUtil.defaultIfEmpty(org.getSendSchoolName(), PkUtil.getUUID()));

		Map<String,Object> resultMap = monthlyReportBiz.getChart6Data(paramMap);
		model.addAttribute("resultMap",resultMap);
		return "jsres/university/monthlyReport/chart6";
	}

	@RequestMapping("/chart7")//图七
	public String chart7(String monthDate,String isGraduate,Model model){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
        paramMap.put("sendSchoolFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		paramMap.put("sendSchoolId",StringUtil.defaultIfEmpty(org.getSendSchoolId(), PkUtil.getUUID()));
		paramMap.put("sendSchoolName",StringUtil.defaultIfEmpty(org.getSendSchoolName(), PkUtil.getUUID()));

		List<SysMonthlyDocCycleInfo> sysMonthlyDocCycleInfoList = monthlyReportExtMapper.getSysMonthlyDocCycleInfo(paramMap);

		List<SysOrg> orgs=orgBiz.getUniOrgs(org.getSendSchoolId(),org.getSendSchoolName());

		Map<String,Integer> registerMap = new HashMap<>();
		Map<String,Integer> auditingMap = new HashMap<>();

		if(!sysMonthlyDocCycleInfoList.isEmpty()){
			for(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo:sysMonthlyDocCycleInfoList){
				String orgFlow = sysMonthlyDocCycleInfo.getOrgFlow();
				int registerNum = sysMonthlyDocCycleInfo.getRecNum();
				if(registerMap.get(orgFlow)==null){
					registerMap.put(orgFlow,registerNum);
				}else {
					registerMap.put(orgFlow,registerMap.get(orgFlow)+registerNum);
				}

				int auditingNum = sysMonthlyDocCycleInfo.getAuditNum();
				if(auditingMap.get(orgFlow)==null){
					auditingMap.put(orgFlow,auditingNum);
				}else {
					auditingMap.put(orgFlow,auditingMap.get(orgFlow)+auditingNum);
				}
			}
		}

		model.addAttribute("registerMap",registerMap);
		model.addAttribute("auditingMap",auditingMap);
		model.addAttribute("orgs",orgs);

		return "jsres/university/monthlyReport/chart7";
	}

	@RequestMapping("/chart8")//图八
	public String chart8(String monthDate,String isGraduate,Model model){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());

		Map<String,Object> paramMap = new HashMap<>();


		paramMap.put("doctorTypeId",isGraduate);
		paramMap.put("dateMonth1",monthDate);
		paramMap.put("dateMonth2",getLastMonths(-2));
		paramMap.put("dateMonth3",getLastMonths(-3));

		paramMap.put("sendSchoolId",StringUtil.defaultIfEmpty(org.getSendSchoolId(), PkUtil.getUUID()));
		paramMap.put("sendSchoolName",StringUtil.defaultIfEmpty(org.getSendSchoolName(), PkUtil.getUUID()));

		List<Map<String,String>> resultMapList = monthlyReportExtMapper.getNoAppNum3(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		return "jsres/university/monthlyReport/chart8";
	}

	@RequestMapping("/chart9")//图九
	public String chart9(String baseRange,String monthDate,String isGraduate,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<String> allOrgFlow = new ArrayList<>();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs=orgBiz.getUniOrgs(org.getSendSchoolId(),org.getSendSchoolName());
		if(!orgs.isEmpty()){
			for(SysOrg sysOrg:orgs){
				allOrgFlow.add(sysOrg.getOrgFlow());
			}
		}
		paramMap.put("allOrgFlow",allOrgFlow);

		Map<String,Object> resultMap = monthlyReportBiz.getChart9Data2(paramMap);
		model.addAttribute("resultMap",resultMap);
		return "jsres/university/monthlyReport/chart9";
	}

	@RequestMapping("/export123")
	public void export123(String monthDate,String isGraduate,HttpServletResponse response)
			throws DocumentException, IOException {
		SysMonthlyDoctorInfo search = new SysMonthlyDoctorInfo();
		search.setDateMonth(monthDate);
		search.setDoctorTypeId(isGraduate);
		search.setDoctorStatusId("20");
        search.setChangeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());

		SysMonthlyDoctorDetailInfo search2 = new SysMonthlyDoctorDetailInfo();
		search2.setDateMonth(monthDate);

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);

		List<SysMonthlyDoctorInfo> sysMonthlyDoctorInfoList = monthlyReportBiz.getMonthlyDoctorInfo(search,null);
		List<SysMonthlyDoctorDetailInfo> sysMonthlyDoctorDetailInfoList = monthlyReportBiz.getMonthlyDoctorDetailInfo(search2);
		List<SysUser> userList = monthlyReportExtMapper.getUserList(paramMap);
		List<ResDoctor> doctorList = monthlyReportExtMapper.getDoctorList(paramMap);
		List<com.pinde.core.model.ResDoctorRecruit> recruitList = monthlyReportExtMapper.getRecruitList(paramMap);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		String sendSchoolId = org.getSendSchoolId();
		String sendSchoolName = org.getSendSchoolName();
		List<SysMonthlyDoctorInfo> doctorInfoListFinal = new ArrayList<>();//三图公用数据
		if(!sysMonthlyDoctorInfoList.isEmpty()){
			for(SysMonthlyDoctorInfo sysMonthlyDoctorInfo:sysMonthlyDoctorInfoList){
				if(
						(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getWorkOrgId()) &&
								sysMonthlyDoctorInfo.getWorkOrgId().equals(sendSchoolId)) ||
								(StringUtil.isNotBlank(sysMonthlyDoctorInfo.getWorkOrgName()) &&
										sysMonthlyDoctorInfo.getWorkOrgName().equals(sendSchoolName))
						){
					doctorInfoListFinal.add(sysMonthlyDoctorInfo);
				}
			}
		}

		Map<String,SysMonthlyDoctorDetailInfo> detailInfoMap = new HashMap<>();
		Map<String,SysUser> userMap = new HashMap<>();
		Map<String,ResDoctor> doctorMap = new HashMap<>();
		Map<String, ResDoctorRecruit> recruitMap = new HashMap<>();

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
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

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
				com.pinde.core.model.ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
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
				} else {
					CretType = "其他";
					area = "其他";
				}
				String isYearGraduate = "";
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
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
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
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
	public void export4(String monthDate,String isGraduate,HttpServletResponse response) throws DocumentException, IOException {
		SysMonthlyDoctorDetailInfo search2 = new SysMonthlyDoctorDetailInfo();
		search2.setDateMonth(monthDate);

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("monthDate",monthDate);
		paramMap.put("doctorTypeId",isGraduate);
        paramMap.put("sendSchoolFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		paramMap.put("sendSchoolId",StringUtil.defaultIfEmpty(org.getSendSchoolId(), PkUtil.getUUID()));
		paramMap.put("sendSchoolName",StringUtil.defaultIfEmpty(org.getSendSchoolName(), PkUtil.getUUID()));
		List<SysMonthlyChangeInfo> SysMonthlyChangeInfoFinal = monthlyReportExtMapper.getSysMonthlyChangeInfo(paramMap);
		List<SysMonthlyReturnDelayInfo> sysMonthlyReturnDelayInfoFinal = monthlyReportExtMapper.getSysMonthlyReturnDelayInfo(paramMap);

		List<SysMonthlyDoctorDetailInfo> sysMonthlyDoctorDetailInfoList = monthlyReportBiz.getMonthlyDoctorDetailInfo(search2);
		List<SysUser> userList = monthlyReportExtMapper.getUserList(paramMap);
		List<ResDoctor> doctorList = monthlyReportExtMapper.getDoctorList(paramMap);
		List<com.pinde.core.model.ResDoctorRecruit> recruitList = monthlyReportExtMapper.getRecruitList(paramMap);

		List<SysMonthlyChangeInfo> speChangeList = new ArrayList<>();
		List<SysMonthlyChangeInfo> baseChangeList = new ArrayList<>();
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
				if("BaseChange".equals(sysMonthlyChangeInfo.getChangeTypeId())){
					baseChangeList.add(sysMonthlyChangeInfo);
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
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

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
				com.pinde.core.model.ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(cretTypeId)) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
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
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
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
		HSSFSheet sheet2 = wb.createSheet("基地变更");

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
		if (!baseChangeList.isEmpty()) {
			for (int i = 0; i < baseChangeList.size(); i++, rowNum2++) {
				HSSFRow rowFour = sheet2.createRow(rowNum2);//第二行
				SysMonthlyChangeInfo sysMonthlyDoctorInfo = baseChangeList.get(i);
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
				com.pinde.core.model.ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
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
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
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
				com.pinde.core.model.ResDoctorRecruit recruit = recruitMap.get(currentDoctorFlow);
				SysMonthlyDoctorDetailInfo detailInfo = detailInfoMap.get(currentDoctorFlow);

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(cretTypeId)) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
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
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()) || com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
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

	@RequestMapping("/detail5Main")
	public String detail5Main(String monthDate,Model model){
		model.addAttribute("monthDate",monthDate);
		String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		SysOrg currentOrg = orgBiz.readSysOrg(currentOrgFlow);
		List<SysOrg> orgs=orgBiz.getUniOrgs(currentOrg.getSendSchoolId(),currentOrg.getSendSchoolName());
		Map<String,String> orgMap = new HashMap<>();
		if(orgs!=null&&!orgs.isEmpty()){
			for(SysOrg org:orgs){
				orgMap.put(org.getOrgFlow(),org.getOrgName());
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


		return "jsres/university/monthlyReport/detail5Main";
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

}
