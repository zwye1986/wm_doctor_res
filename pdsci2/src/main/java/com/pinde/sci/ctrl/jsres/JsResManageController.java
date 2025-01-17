package com.pinde.sci.ctrl.jsres;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.common.enums.ResAssessTypeEnum;
import com.pinde.core.common.enums.ResDocTypeEnum;
import com.pinde.core.common.enums.pub.UserSexEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.OperTypeEnum;
import com.pinde.core.common.enums.sys.ReqTypeEnum;
import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.page.Page;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.DocumentVo;
import com.pinde.core.pdf.PdfDocumentGenerator;
import com.pinde.core.pdf.utils.ResourceLoader;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sch.impl.SchRotationGroupBizImpl;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.RSAUtils;
import com.pinde.sci.ctrl.cfg.JsresPowerCfgController;
import com.pinde.sci.ctrl.res.ResMonthlyReportGlobalControllerClass;
import com.pinde.sci.dao.jsres.MonthlyReportExtMapper;
import com.pinde.sci.dao.jsres.SchdualTaskMapper;
import com.pinde.sci.dao.res.ResDoctorRecruitExtMapper;
import com.pinde.core.common.form.UserResumeExtInfoForm;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.bouncycastle.util.encoders.Hex;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/manage")
public class JsResManageController extends GeneralController {
	final static String Jxcf = "1";
	final static String Ynbltl = "2"; final static String Wzbltl = "3";final static String Swbltl = "5";final static String Jxbltl = "11";
	final static String Xsjz = "4";  final static String Rkjy = "6";
	final static String Ckks = "7"; final static String Jnpx = "8"; final static String Yph = "9";
	final static String Jxhz = "10";
	final static String Lcczjnzd = "12"; final static String Lcblsxzd = "13";
	final static String Ssczzd = "14"; final static String Yxzdbgsxzd = "15"; final static String Lcwxyd = "16";
	final static String Ryjy = "17"; final static String Rzyjdjy = "18"; final static String Cjbg = "19";
	final static String Bgdfx = "20";final static String Jxsj = "21";final static String Sjys = "22";

	private final static String chinaIdLoose = "^(\\d{18}|\\d{15}|\\d{17}[X])$";
	private final static String mobile = "^[1][3456789]\\d{9}$";

	private final static Pattern id_pattern = Pattern.compile(chinaIdLoose);
	private final static Pattern mobile_pattern = Pattern.compile(mobile);

	private List<String> acceptedRoleNameList = Arrays.asList("带教老师", "科主任", "科秘", "教学主任", "教学秘书", "督导-评分专家");
	private static Logger logger = LoggerFactory.getLogger(JsResManageController.class);


	@Autowired
	private ResTeacherTrainingMapper teacherTrainingMapper;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IJsResRecruitDoctorInfoBiz recruitDoctorInfoBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private IResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private IJsResDoctorOrgHistoryBiz jsDocOrgHistoryBiz;
	@Autowired
	private IJsResStatisticBiz resStatisticBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private ISchExamCfgBiz schExamCfgBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ILoginBiz loginBiz;
	@Autowired
	private IJsResRecBiz jsResRecBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExternalRelBiz;
	@Autowired
	private ISchAndStandardDeptCfgBiz deptCfgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private ResMonthlyReportGlobalControllerClass resMonthlyReportGlobalControllerClass;
	@Autowired
	private MonthlyReportExtMapper monthlyReportExtMapper2;
	@Autowired
	private ResDoctorRecruitExtMapper resDoctorRecruitExtMapper;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private SchRotationGroupBizImpl rotationGroupBiz;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private SchdualTaskMapper schdualTaskMapper;
	@Autowired
	private IResTrainingSpeDeptBiz resTrainingSpeDeptBiz;
	@Autowired
	private SysCfgMapper sysCfgMapper;
	@Autowired
	private ResTeacherTrainingMapper resTeacherTrainingMapper;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private SysUserDeptMapper userDeptMapper;
	@Autowired
	private PubFileMapper pubFileMapper;

	/**
	 * 管理员主界面
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = "/{role}")
	public String index(@PathVariable String role, String more, Model model, Integer currentPage, HttpServletRequest request,HttpServletResponse response,String school) throws Exception {
		int countryOrgCount = 0;//主基地的国家基地
		int jointOrgCount = 0;//协同基地的国家基地
		List<String> orgFlowList = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		//省厅
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(role) || "quality".equals(role) || "maintenance".equals(role)) {
			SysOrg searchOrg = new SysOrg();
			searchOrg.setOrgProvId("320000");
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
			for (SysOrg g : exitOrgs) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						list.add(jointOrg.getJointOrgFlow());
					}
				}
				list.add(g.getOrgFlow());
			}

		/*	//首页的echart表
			shouYeEchart(model,"inTraining","20",com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());	//在培人员情况
			shouYeEchart(model,"graduation","21",com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());	//结业人员情况
			shouYeEchart2(model,"recruitment",com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());	//结业人员情况*/
		}

		//市局
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
			SysUser sysuser = GlobalContext.getCurrentUser();
			SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
			SysOrg searchOrg = new SysOrg();
			searchOrg.setOrgProvId("320000");
			searchOrg.setOrgCityId(org.getOrgCityId());
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
			for (SysOrg g : exitOrgs) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						list.add(jointOrg.getJointOrgFlow());
					}
				}
				list.add(g.getOrgFlow());
			}
		}
		//基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(role)) {
			SysUser sysuser = GlobalContext.getCurrentUser();
			SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
                isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(sysuser.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						list.add(jointOrg.getJointOrgFlow());
					}
				}
			}
			list.add(sysuser.getOrgFlow());
		}
        setSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE, role);
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage, getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos", infos);
//		String date = DateUtil.getCurrDateTime("yyyy-MM");
//		List<Map<String, Object>> trainLst = new ArrayList<>();//processBiz.searTrainingQuery(user.getOrgFlow(),date);//存储过滤出的即将出科学员
//		if(null != trainLst && trainLst.size() > 0){
//			for (int i = 0; i < trainLst.size(); i++) {
//				if(i < trainLst.size()-1 && trainLst.get(i).get("doctorFlow").equals(trainLst.get(i+1).get("doctorFlow"))
//						&& !(trainLst.get(i).get("recTypeId").equals(trainLst.get(i+1).get("recTypeId")) && "AfterSummary".equals(trainLst.get(i).get("recTypeId")))){
//					if("AfterSummary".equals(trainLst.get(i+1).get("recTypeId"))){
//						trainLst.remove(i);
//					}else{
//						trainLst.remove(i+1);
//					}
//					i=i-1;
//				}
//			}
//		}
//		Map<String, String> stateMap=new HashMap<String, String>();
//		if(null != trainLst && trainLst.size() > 0){
//			for (Map<String, Object> map : trainLst) {
//				String recTypeId = (String)map.get("recTypeId");
//				String recContent=(String) map.get("recContent");
//				String key=(String)map.get("doctorFlow")+map.get("schDeptFlow");
//				String endDate = (String)map.get("schEndDate");
//				if(DateUtil.getCurrDate().compareTo(endDate) >= 0){
//					stateMap.put(key, "已出科");
//				}else if("AfterSummary".equals(recTypeId) && StringUtil.isNotBlank(recContent)){
//					List<Map<String, String>> imageList = resRecBiz.parseImageXml(recContent);
//					if (imageList!=null && !imageList.isEmpty()) {
//						stateMap.put(key, "已出科");
//					}else{
//						stateMap.put(key, "待出科");
//					}
//				}else{
//					stateMap.put(key, "待出科");
//				}
//			}
//		}
//		model.addAttribute("stateMap", stateMap);
//		model.addAttribute("trainingList", trainLst);
		//当前orgFlow
		if (org != null) {
			model.addAttribute("orgFlow", org.getOrgFlow());
		}

		int speFlag = 0;
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();

        if (com.pinde.core.common.GlobalConstant.SYSTEM_ROLE.equals(role)) { //主管部门
			return "jsres/system/index";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(role) || "institute".equals(role) || "maintenance".equals(role)) { //省级管理员
			String evalRoleFlow = InitConfig.getSysCfg("eval_global_role_flow");
			if (StringUtil.isNotBlank(evalRoleFlow)) {

				SysUserRole userRole = new SysUserRole();
				userRole.setUserFlow(user.getUserFlow());
                userRole.setWsId(com.pinde.core.common.GlobalConstant.EVAL_WS_ID);
				List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
				if (userRoleList != null && userRoleList.size() > 0) {
					boolean f = false;
					for (SysUserRole sur : userRoleList) {
						String ur = sur.getRoleFlow();
						if (evalRoleFlow.equals(ur)) {
							f = true;
							break;
						}
					}
					if (f) {
						HttpSession session = request.getSession();
                        session.setAttribute("jsresUserRoleFlag", com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					}
					model.addAttribute("f", f);
				}
			}
//			int provinceOrgCount=0;//省级基地
//			int doctorCount=0;//医师总数
//			int yearConDocCount=0;//本年度国家基地的医师数
//			String year=DateUtil.getCurrDate().substring(0, 4);//当前的年份
//			SysOrg sysOrg=new SysOrg();
//			sysOrg.setOrgProvId(org.getOrgProvId());
//			sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
//			sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//			//所有国家基地
//			List<SysOrg>sysOrgs=orgBiz.searchAllSysOrg(sysOrg);
//			//本季度的国家基地的医师数
//			ResDoctor d=new ResDoctor();
//			d.setSessionNumber(year);
//			yearConDocCount=resStatisticBiz.statisticYearCondocCount(d, sysOrgs);
//			//主基地的国家基地
//			countryOrgCount=resStatisticBiz.statisticCountyOrgCount(sysOrg);
//			sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
//			//省级基地
//			provinceOrgCount=resStatisticBiz.statisticCountyOrgCount(sysOrg);
//			ResDoctorRecruit re=new ResDoctorRecruit();
//			re.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
//			List<String> cityIdList =InitConfig.getCityMap(org.getOrgProvId());
//			//培训医师总数
//			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
//				re.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
//			}
//			//待结业审核
//			int waitPassCount=jsResDoctorBiz.findWaitPassCountByOrgFlow(1,list);
//			model.addAttribute("waitPassCount", waitPassCount);
//			doctorCount=resStatisticBiz.statisticDoctorCount(re,cityIdList);
//			//
//			init(model,cityIdList,null,list);
//			//协同基地的国家基地
//			jointOrgCount=resStatisticBiz.statisticJointOrgCount(null);
//			history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
//			history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
//			List<ResDoctorOrgHistory> spe=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
//			if(spe!=null && !spe.isEmpty()){
//				speFlag=1;
//			}
//			ResRec resRec=new ResRec();
//			resRec.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
//			PageHelper.startPage(currentPage,getPageSize(request));
//			ResDoctor currdoctor = new ResDoctor();
//			currdoctor.setEmergencyName("2");//"0"审核不通过"1"审核通过"2"待审核
//			List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec, currdoctor, null, null);
//			if(resRecList !=null&& !resRecList.isEmpty()){
//				model.addAttribute("backFlag","1" );
//			}
//			SysOrg sysOrg2=new SysOrg();
//			sysOrg2.setOrgProvId(org.getOrgProvId());
//			sysOrg2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//			List<SysOrg> orgs=orgBiz.searchAllSysOrg(sysOrg2);
//			model.addAttribute("orgs", orgs);
//			model.addAttribute("countryOrgCount", countryOrgCount);
//			model.addAttribute("jointOrgCount", jointOrgCount);
//			model.addAttribute("doctorCount", doctorCount);
//			model.addAttribute("provinceOrgCount", provinceOrgCount);
//			model.addAttribute("yearConDocCount", yearConDocCount);
            history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			List<ResDoctorOrgHistory> spe = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			if (spe != null && !spe.isEmpty()) {
				speFlag = 1;
			}
			model.addAttribute("speFlag", speFlag);
			int baseFlag = 0;
            history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
			List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			if ((baseOne != null && !baseOne.isEmpty())) {
				baseFlag = 1;
			}
			model.addAttribute("baseFlag", baseFlag);
			if ("institute".equals(role)) {
				return "jsres/institute/index";
			}
			if ("maintenance".equals(role)){
				return "jsres/maintenance/index";
			}
			SysUser sysuser = GlobalContext.getCurrentUser();
			model.addAttribute("currUser", sysuser);
			return "jsres/global/globalIndex";
		} else if ("quality".equals(role)) { //质控组
			SysUser sysuser = GlobalContext.getCurrentUser();
			if (StringUtil.isBlank(sysuser.getResTrainingSpeId())) {
                model.addAttribute("isHaveDept", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
			String evalRoleFlow = InitConfig.getSysCfg("eval_global_role_flow");
			if (StringUtil.isNotBlank(evalRoleFlow)) {

				SysUserRole userRole = new SysUserRole();
				userRole.setUserFlow(user.getUserFlow());
                userRole.setWsId(com.pinde.core.common.GlobalConstant.EVAL_WS_ID);
				List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
				if (userRoleList != null && userRoleList.size() > 0) {
					boolean f = false;
					for (SysUserRole sur : userRoleList) {
						String ur = sur.getRoleFlow();
						if (evalRoleFlow.equals(ur)) {
							f = true;
							break;
						}
					}
					if (f) {
						HttpSession session = request.getSession();
                        session.setAttribute("jsresUserRoleFlag", com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					}
					model.addAttribute("f", f);
				}
			}
            history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			List<ResDoctorOrgHistory> spe = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			if (spe != null && !spe.isEmpty()) {
				speFlag = 1;
			}
			model.addAttribute("speFlag", speFlag);
			int baseFlag = 0;
            history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
			List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			if ((baseOne != null && !baseOne.isEmpty())) {
				baseFlag = 1;
			}
			model.addAttribute("baseFlag", baseFlag);
			return "jsres/global/qualityIndex";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_PROVINCE.equals(role)) { //审核部门
			return "jsres/province/index";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
			if (StringUtil.isNotBlank(school)){
				user.setSchool(school);
                setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
			}
			initUni(model);
			return "jsres/university/index";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) { //市局
			//培训医师数
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
			List<String> cityIdList = new ArrayList<String>();
			cityIdList.add(org.getOrgCityId());
			if (StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))) {
				recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
			}
			recruit.setCatSpeId("DoctorTrainingSpe");
			int passCount = resStatisticBiz.statisticDoctorCount(recruit, cityIdList);
			model.addAttribute("passCount", passCount);
			//待审核的数量
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
			Calendar date = Calendar.getInstance();
			String year = String.valueOf(date.get(Calendar.YEAR));
			int waitCount = jsResDoctorRecruitBiz.searchBasePassCount(recruit, orgFlowList,year);
			model.addAttribute("waitCount", waitCount);
			int waitPassCount = jsResDoctorBiz.findWaitPassCountByOrgFlow(2, list);
			model.addAttribute("waitPassCount", waitPassCount);
			//国家基地的数量
			SysOrg sysOrg = new SysOrg();
			sysOrg.setOrgCityId(org.getOrgCityId());
            sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
            sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			countryOrgCount = resStatisticBiz.statisticCountyOrgCount(sysOrg);
			model.addAttribute("countryOrgCount", countryOrgCount);
			//协同基地的数量
			jointOrgCount = resStatisticBiz.statisticJointOrgCount(org);
			SysOrg sysOrg2 = new SysOrg();
			sysOrg2.setOrgProvId(org.getOrgProvId());
			sysOrg2.setOrgCityId(org.getOrgCityId());
            sysOrg2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(sysOrg2);
			model.addAttribute("orgs", orgs);
			model.addAttribute("jointOrgCount", jointOrgCount);
			int provinceOrgCount = 0;//省级基地
            sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
			provinceOrgCount = resStatisticBiz.statisticCountyOrgCount(sysOrg);
			model.addAttribute("provinceOrgCount", provinceOrgCount);
			String cfgCode = "jswjw_" + user.getOrgFlow() + "_P003";
			SysCfg cfg = cfgBiz.read(cfgCode);
            if (cfg == null || com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue())) {
				return "jsres/city/index";
			}
			init(model, cityIdList, null, list);
			return "jsres/city/cityIndex";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(role)) { //培训基地
			//培训医师数
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
			recruit.setOrgFlow(user.getOrgFlow());
			if (StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))) {
				recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
			}
			int passCount = resStatisticBiz.statisticDoctorCount(recruit, null);
			model.addAttribute("passCount", passCount);
			//待审核
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJointOrg)) {
				recruit.setJointOrgFlow(recruit.getOrgFlow());
				recruit.setOrgFlow("");
				recruit.setJointOrgAudit("Auditing");
            } else if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(isJointOrg)) {
				recruit.setJointOrgAudit("Passed");
			}
			model.addAttribute("isJointOrg", isJointOrg);
			Calendar date = Calendar.getInstance();
			String year = String.valueOf(date.get(Calendar.YEAR));
			int waitCount = jsResDoctorRecruitBiz.searchBasePassCount(recruit, null,year);
			model.addAttribute("waitCount", waitCount);
			int waitPassCount = jsResDoctorBiz.findWaitPassCountByOrgFlow(2, list);
			model.addAttribute("waitPassCount", waitPassCount);
			//专业和基地变更待办事项
			int baseFlag = 0;
			history.setOrgFlow(user.getOrgFlow());
            history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());
			List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			history.setOrgFlow("");
			history.setHistoryOrgFlow(user.getOrgFlow());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyWaiting.getId());
			List<ResDoctorOrgHistory> baseTwo = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			if ((baseOne != null && !baseOne.isEmpty()) || (baseTwo != null && !baseTwo.isEmpty())) {
				baseFlag = 1;
			}

            history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseWaitingAudit.getId());
			List<ResDoctorOrgHistory> spe = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			if (spe != null && !spe.isEmpty()) {
				speFlag = 1;
			}
			model.addAttribute("speFlag", speFlag);
			model.addAttribute("baseFlag", baseFlag);
			String cfgCode = "jsres_" + user.getOrgFlow() + "_guocheng";
			JsresPowerCfg cfg = jsResPowerCfgBiz.read(cfgCode);

            if (cfg == null || com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue()) || !"Passed".equals(org.getCheckStatusId())) {
				return "jsres/hospital/index";
			}
			List<String> orgFlows = new ArrayList<>();
			orgFlows.add(user.getOrgFlow());
			init(model, orgFlows, "hosipital", list);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
				List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
				List<String> jointOrgFlowList = new ArrayList<>();
				if (jointOrgs != null && jointOrgs.size() > 0) {
					for (ResJointOrg join : jointOrgs) {
						jointOrgFlowList.add(join.getJointOrgFlow());
					}
				}
				List<SysOrg> orgs = new ArrayList<>();
				if (jointOrgFlowList != null && jointOrgFlowList.size() > 0) {
					orgs = orgBiz.searchOrgFlowIn(jointOrgFlowList);
				}
				orgs.add(org);
				model.addAttribute("orgs", orgs);
				model.addAttribute("countryOrg", "countryOrg");
			}
			//院级督导 -- 管理员配置功能权限
			JsresPowerCfg powerCfg = jsResPowerCfgBiz.read("jsres_hospital_yjdd_"+user.getOrgFlow());
            if (null != powerCfg && powerCfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                model.addAttribute("hospitalSupervisor", com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
            if (GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_NAME).equals("医院秘书")) {
				return "jsres/hospital/hospitalSecretaryIndex";
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(more)) {
				return "jsres/hospital/hospitalIndexNew";
			}
			String filter = "('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'',key:orgFlow.keyword,negate:!f" +
					",params:(query:'" + user.getOrgFlow() + "'),type:phrase),query:(match_phrase:(orgFlow.keyword:'" + user.getOrgFlow() + "')))";
//			String iframe = "<iframe src=\"https://restest.njpdxx.com:5650/app/dashboards?auth_provider_hint=anonymous1#/view/41e46fe1-80c5-4f1b-bdba-c00822929a4b?embed=true" +
//					"&_g=(filters:!(" + filter + "),refreshInterval%3A(pause%3A!t%2Cvalue%3A60000)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))&hide-filter-bar=true\"  height=\"2100\" width=\"1460\"></iframe>";
			String iframe = "<iframe src=\"https://js.ezhupei.com:5601/app/dashboards?auth_provider_hint=anonymous1#/view/0525f6c0-e4dc-45d4-a25f-bc338e758934?embed=true" +
					"&_g=(filters:!(" + filter + "),refreshInterval%3A(pause%3A!t%2Cvalue%3A60000)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))&hide-filter-bar=true\" height=\"2100\" width=\"1460\"></iframe>";
			model.addAttribute("iframe", iframe);
			return "jsres/hospital/hospitalIndex";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(role)) { //专业基地
			SysUser sysuser = GlobalContext.getCurrentUser();
			if (StringUtil.isBlank(sysuser.getResTrainingSpeId())) {
                model.addAttribute("isHaveDept", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
			model.addAttribute("speFlow",sysuser.getResTrainingSpeId());
			return "jsres/speAdmin/index";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(role)) { //专业基地秘书
			SysUser sysuser = GlobalContext.getCurrentUser();
			if (StringUtil.isBlank(sysuser.getResTrainingSpeId())) {
                model.addAttribute("isHaveDept", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
			return "jsres/speAdminSecretary/index";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_PERSONAL.equals(role)) {
			return "jsres/doctor/index";
		} else if ("school".equals(role)) {
			return "jsres/school/index";
		}
		// 协同基地需要查看报名在主基地实际在协同基地培训的学员
        else if (com.pinde.core.common.GlobalConstant.USER_LIST_BASE.equals(role)) {
			return "jsres/base/baseIndex";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_MANAGEMENT.equals(role)) {
			model.addAttribute("user",user);
			return "jsres/hospital/supervisio/index";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_EXPERTLEADER.equals(role)) {
			model.addAttribute("user",user);
			return "jsres/hospital/supervisio/index";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_PHYASS.equals(role)) {
			model.addAttribute("user",user);
			return "jsres/phyAss/index";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_BASEEXPERT.equals(role)) {
			return "jsres/hospital/supervisio/baseIndex";
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_HOSPITALLEADER.equals(role)) {
			return "jsres/hospital/supervisio/hospitalLeaderIndex";
		}
		return "/inx/jsres/login";
	}
/*
	private void shouYeEchart(Model model,String type,String doctorStatudId,String catSpeId){
		ArrayList<String> doctorTypeIdList = new ArrayList<>();
		doctorTypeIdList.add("Company");
		doctorTypeIdList.add("CompanyEntrust");
		doctorTypeIdList.add("Social");
		String year = jsResDoctorRecruitBiz.searchDoctorNumYear(doctorStatudId,catSpeId);
		if (StringUtil.isNotBlank(year)){
			String years="";	//年份
			String inTrainingNum="";	//住院医师的数量
			String inTrainingNum2="";	//在校专硕的数量
			String inTrainingNumAll="";	//总数量
			int newYear=Integer.parseInt(year);
			List<String> inTrainingList = jsResDoctorRecruitBiz.searchDoctorNum(doctorTypeIdList, doctorStatudId,catSpeId);  //住院医师
			doctorTypeIdList.removeAll(doctorTypeIdList);
			doctorTypeIdList.add("Graduate");
			List<String> inTrainingList2 = jsResDoctorRecruitBiz.searchDoctorNum(doctorTypeIdList, doctorStatudId,catSpeId);  //在校专硕

			HashMap<Integer, Integer> map1 = new HashMap<>();
			HashMap<Integer, Integer> map2 = new HashMap<>();
			for (String s : inTrainingList) {
				String[] split = s.split(",");
				map1.put(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
			}
			for (String s : inTrainingList2) {
				String[] split = s.split(",");
				map2.put(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
			}
			for (int i = 4; i >=0 ; i--) {
				int a = newYear - i;
				years = years + a + ",";
				Integer num1=map1.get(a)==null?0:map1.get(a);
				Integer num2=map2.get(a)==null?0:map2.get(a);
				inTrainingNum=inTrainingNum+num1+",";
				inTrainingNum2=inTrainingNum2+num2+",";
				num1=num1+num2;
				inTrainingNumAll=inTrainingNumAll+num1+",";
			}
			model.addAttribute(type+"Years",years.substring(0,years.length()-1));
			model.addAttribute(type+"Num",inTrainingNum.substring(0,inTrainingNum.length()-1));
			model.addAttribute(type+"Num2",inTrainingNum2.substring(0,inTrainingNum2.length()-1));
			model.addAttribute(type+"NumAll",inTrainingNumAll.substring(0,inTrainingNumAll.length()-1));
		}
	}

	private void shouYeEchartAcc(Model model,String type,String doctorStatudId,String catSpeId){
		ArrayList<String> doctorTypeIdList = new ArrayList<>();
		doctorTypeIdList.add("Company");
		doctorTypeIdList.add("CompanyEntrust");
		doctorTypeIdList.add("Social");
		doctorTypeIdList.add("Graduate");
		String year = jsResDoctorRecruitBiz.searchDoctorNumYear(doctorStatudId,catSpeId);
		if (StringUtil.isNotBlank(year)){
			String years="";	//年份
			String inTrainingNum="";
			int newYear=Integer.parseInt(year);
			List<String> inTrainingList = jsResDoctorRecruitBiz.searchDoctorNum(doctorTypeIdList, doctorStatudId,catSpeId);  //住院医师

			HashMap<Integer, Integer> map1 = new HashMap<>();
			for (String s : inTrainingList) {
				String[] split = s.split(",");
				map1.put(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
			}

			for (int i = 4; i >=0 ; i--) {
				int a = newYear - i;
				years = years + a + ",";
				Integer num1=map1.get(a)==null?0:map1.get(a);
				inTrainingNum=inTrainingNum+num1+",";
			}
			model.addAttribute(type+"Years",years.substring(0,years.length()-1));
			model.addAttribute(type+"Num",inTrainingNum.substring(0,inTrainingNum.length()-1));
		}
	}

	private void shouYeEchart2(Model model,String type,String catSpeId){
		ArrayList<String> doctorTypeIdList = new ArrayList<>();
		doctorTypeIdList.add("Company");
		doctorTypeIdList.add("CompanyEntrust");
		doctorTypeIdList.add("Social");
		String year = jsResDoctorRecruitBiz.searchDoctorNumYear2(catSpeId);

		if (StringUtil.isNotBlank(year)){
			String years="";	//年份
			String inTrainingNum="";	//住院医师的数量

			String inTrainingNum2="";	//在校专硕的数量
			String inTrainingNumAll="";	//总数量
			int newYear=Integer.parseInt(year);
			List<String> inTrainingList = jsResDoctorRecruitBiz.searchDoctorNum2(doctorTypeIdList,catSpeId);  //住院医师
			doctorTypeIdList.removeAll(doctorTypeIdList);
			doctorTypeIdList.add("Graduate");
			List<String> inTrainingList2 = jsResDoctorRecruitBiz.searchDoctorNum2(doctorTypeIdList,catSpeId);  //在校专硕

			HashMap<Integer, Integer> map1 = new HashMap<>();
			HashMap<Integer, Integer> map2 = new HashMap<>();
			for (String s : inTrainingList) {
				String[] split = s.split(",");
				map1.put(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
			}
			for (String s : inTrainingList2) {
				String[] split = s.split(",");
				map2.put(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
			}
			for (int i = 4; i >=0 ; i--) {
				int a = newYear - i;
				years = years + a + ",";
				Integer num1=map1.get(a)==null?0:map1.get(a);
				Integer num2=map2.get(a)==null?0:map2.get(a);
				inTrainingNum=inTrainingNum+num1+",";
				inTrainingNum2=inTrainingNum2+num2+",";
				num1=num1+num2;
				inTrainingNumAll=inTrainingNumAll+num1+",";
			}
			model.addAttribute(type+"Years",years.substring(0,years.length()-1));
			model.addAttribute(type+"Num",inTrainingNum.substring(0,inTrainingNum.length()-1));
			model.addAttribute(type+"Num2",inTrainingNum2.substring(0,inTrainingNum2.length()-1));
			model.addAttribute(type+"NumAll",inTrainingNumAll.substring(0,inTrainingNumAll.length()-1));
		}
	}

	private void shouYeEchart2Acc(Model model,String type,String catSpeId){
		ArrayList<String> doctorTypeIdList = new ArrayList<>();
		doctorTypeIdList.add("Company");
		doctorTypeIdList.add("CompanyEntrust");
		doctorTypeIdList.add("Social");
		doctorTypeIdList.add("Graduate");
		String year = jsResDoctorRecruitBiz.searchDoctorNumYear2(catSpeId);

		if (StringUtil.isNotBlank(year)){
			String years="";	//年份
			String inTrainingNum="";	//住院医师的数量
			int newYear=Integer.parseInt(year);
			List<String> inTrainingList = jsResDoctorRecruitBiz.searchDoctorNum2(doctorTypeIdList,catSpeId);  //住院医师

			HashMap<Integer, Integer> map1 = new HashMap<>();
			for (String s : inTrainingList) {
				String[] split = s.split(",");
				map1.put(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
			}
			for (int i = 4; i >=0 ; i--) {
				int a = newYear - i;
				years = years + a + ",";
				Integer num1=map1.get(a)==null?0:map1.get(a);
				inTrainingNum=inTrainingNum+num1+",";
			}
			model.addAttribute(type+"Years",years.substring(0,years.length()-1));
			model.addAttribute(type+"Num",inTrainingNum.substring(0,inTrainingNum.length()-1));
		}
	}*/

	@RequestMapping(value = "/homePageTrainingMain")
	public String homePageTrainingMain(Model model,String sessionNumber,String statisticsType)  {
		model.addAttribute("sessionNumber",sessionNumber);
		model.addAttribute("statisticsType",statisticsType);
		model.addAttribute("tabId","orgType");
		model.addAttribute("catSpeId","DoctorTrainingSpe");
		return "jsres/global/homePageTrainingMain";
	}

	@RequestMapping(value = "/homePageTrainingMainAcc")
	public String homePageTrainingMainAcc(Model model,String sessionNumber,String statisticsType)  {
		model.addAttribute("sessionNumber",sessionNumber);
		model.addAttribute("statisticsType",statisticsType);
		model.addAttribute("tabId","orgType");
		model.addAttribute("catSpeId","AssiGeneral");
		return "jsres/global/homePageTrainingMainAcc";
	}

	@RequestMapping(value = "/homePageTrainingList")
	public String homePageTrainingList(Model model,String sessionNumber,String tabId,String statisticsType,String catSpeId)  {
		Map<Object, Object> resultMap=new HashMap<>();
		if (tabId.equals("orgType")){	//按基地统计
			SysOrg org = new SysOrg();
			org.setOrgProvId("320000");
			org.setOrgTypeId("Hospital");
			org.setOrgLevelId("CountryOrg");
            org.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<SysOrg> orgList = orgBiz.searchOrgByClause(org, "ORDINAL, ORG_CODE, RECORD_STATUS DESC, ORG_FLOW DESC");
			model.addAttribute("orgList",orgList);
			List<Map<String, Object>> list = jsResDoctorRecruitBiz.searchDoctorTrainingNum(sessionNumber,statisticsType,catSpeId);
			for (Map<String, Object> map : list) {
				resultMap.put(map.get("dataInfo"),map.get("num"));
			}
			//学员在协同基地，但是该协同基地和主基地的关系取消了
			List<Map<String, Object>> list2 = jsResDoctorRecruitBiz.searchDoctorTrainingNumWithNotJoin(sessionNumber,statisticsType,catSpeId);
			if (null!=list2 && list2.size()>0){
				//基地去重
				HashSet<Object> set = new HashSet<>();
				List<Map<String, Object>> orgMapList=new ArrayList<>();
				for (Map<String, Object> map : list2) {
					if (!set.contains(map.get("orgFlow"))){
						set.add(map.get("orgFlow"));
						Map<String, Object> m=new HashMap<>();
						m.put("orgFlow",map.get("orgFlow"));
						m.put("orgName",map.get("orgName"));
						orgMapList.add(m);
					}
				}
				model.addAttribute("orgMapList",orgMapList);

				for (Map<String, Object> map : list2) {
					resultMap.put(map.get("dataInfo"),map.get("num"));
				}
			}
		}else if (tabId.equals("speType")){	//按专业统计
			List<Map<String, Object>> list = jsResDoctorRecruitBiz.searchSpeDoctorTrainingNum(sessionNumber,statisticsType,catSpeId);
			for (Map<String, Object> map : list) {
				resultMap.put(map.get("dataInfo"),map.get("num"));
			}
		}
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("tabId",tabId);
		model.addAttribute("sessionNumber",sessionNumber);
		return "jsres/global/homePageTrainingList";
	}

	@RequestMapping(value = "/homePageTrainingListAcc")
	public String homePageTrainingListAcc(Model model,String sessionNumber,String tabId,String statisticsType,String catSpeId)  {
		Map<Object, Object> resultMap=new HashMap<>();
		if (tabId.equals("orgType")){	//按基地统计
			SysOrg org = new SysOrg();
			org.setOrgProvId("320000");
			org.setOrgTypeId("Hospital");
			org.setOrgLevelId("CountryOrg");
            org.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<SysOrg> orgList = orgBiz.searchOrgByClause(org, "ORDINAL, ORG_CODE, RECORD_STATUS DESC, ORG_FLOW DESC");
			model.addAttribute("orgList",orgList);
			List<Map<String, Object>> list = jsResDoctorRecruitBiz.searchDoctorTrainingNum(sessionNumber,statisticsType,catSpeId);
			for (Map<String, Object> map : list) {
				resultMap.put(map.get("dataInfo"),map.get("num"));
			}
			//学员在协同基地，但是该协同基地和主基地的关系取消了
			List<Map<String, Object>> list2 = jsResDoctorRecruitBiz.searchDoctorTrainingNumWithNotJoin(sessionNumber,statisticsType,catSpeId);
			if (null!=list2 && list2.size()>0){
				//基地去重
				HashSet<Object> set = new HashSet<>();
				List<Map<String, Object>> orgMapList=new ArrayList<>();
				for (Map<String, Object> map : list2) {
					if (!set.contains(map.get("orgFlow"))){
						set.add(map.get("orgFlow"));
						Map<String, Object> m=new HashMap<>();
						m.put("orgFlow",map.get("orgFlow"));
						m.put("orgName",map.get("orgName"));
						orgMapList.add(m);
					}
				}
				model.addAttribute("orgMapList",orgMapList);

				for (Map<String, Object> map : list2) {
					resultMap.put(map.get("dataInfo"),map.get("num"));
				}
			}
		}else if (tabId.equals("speType")){	//按专业统计
			List<Map<String, Object>> list = jsResDoctorRecruitBiz.searchSpeDoctorTrainingNum(sessionNumber,statisticsType,catSpeId);
			for (Map<String, Object> map : list) {
				resultMap.put(map.get("dataInfo"),map.get("num"));
			}
		}
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("tabId",tabId);
		model.addAttribute("sessionNumber",sessionNumber);
		return "jsres/global/homePageTrainingListAcc";
	}

	@RequestMapping(value = "/homePageRecruitMain")
	public String homePageRecruitMain(Model model,String sessionNumber)  {
		model.addAttribute("sessionNumber",sessionNumber);
		model.addAttribute("tabId","orgType");
		model.addAttribute("catSpeId","DoctorTrainingSpe");
		return "jsres/global/homePageRecruitMain";
	}

	@RequestMapping(value = "/homePageRecruitMainAcc")
	public String homePageRecruitMainAcc(Model model,String sessionNumber)  {
		model.addAttribute("sessionNumber",sessionNumber);
		model.addAttribute("tabId","orgType");
		model.addAttribute("catSpeId","AssiGeneral");
		return "jsres/global/homePageRecruitMainAcc";
	}

	@RequestMapping(value = "/homePageRecruitList")
	public String homePageRecruitList(Model model,String sessionNumber,String tabId,String catSpeId)  {
		Map<Object, Object> resultMap=new HashMap<>();
		if (tabId.equals("orgType")){	//按基地统计
			SysOrg org = new SysOrg();
			org.setOrgProvId("320000");
			org.setOrgTypeId("Hospital");
			org.setOrgLevelId("CountryOrg");
            org.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<SysOrg> orgList = orgBiz.searchOrgByClause(org, "ORDINAL, ORG_CODE, RECORD_STATUS DESC, ORG_FLOW DESC");
			model.addAttribute("orgList",orgList);

			List<Map<String, Object>> list = jsResDoctorRecruitBiz.searchDoctorRecruitNum(sessionNumber,catSpeId);
			for (Map<String, Object> map : list) {
				resultMap.put(map.get("dataInfo"),map.get("num"));
			}

			//学员在协同基地，但是该协同基地和主基地的关系取消了
			List<Map<String, Object>> list2 = jsResDoctorRecruitBiz.searchDoctorRecruitNumWithNotJoin(sessionNumber,catSpeId);
			if (null!=list2 && list2.size()>0){
				//基地去重
				HashSet<Object> set = new HashSet<>();
				List<Map<String, Object>> orgMapList=new ArrayList<>();
				for (Map<String, Object> map : list2) {
					if (!set.contains(map.get("orgFlow"))){
						set.add(map.get("orgFlow"));
						Map<String, Object> m=new HashMap<>();
						m.put("orgFlow",map.get("orgFlow"));
						m.put("orgName",map.get("orgName"));
						orgMapList.add(m);
					}
				}
				model.addAttribute("orgMapList",orgMapList);

				for (Map<String, Object> map : list2) {
					resultMap.put(map.get("dataInfo"),map.get("num"));
				}
			}
		}else if (tabId.equals("speType")){	//按专业统计
			List<Map<String, Object>> list = jsResDoctorRecruitBiz.searchSpeDoctorRecruitNum(sessionNumber,catSpeId);
			for (Map<String, Object> map : list) {
				resultMap.put(map.get("dataInfo"),map.get("num"));
			}
		}
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("tabId",tabId);
		model.addAttribute("sessionNumber",sessionNumber);
		return "jsres/global/homePageRecruitList";
	}

	@RequestMapping(value = "/homePageRecruitListAcc")
	public String homePageRecruitListAcc(Model model,String sessionNumber,String tabId,String catSpeId)  {
		Map<Object, Object> resultMap=new HashMap<>();
		if (tabId.equals("orgType")){	//按基地统计
			SysOrg org = new SysOrg();
			org.setOrgProvId("320000");
			org.setOrgTypeId("Hospital");
			org.setOrgLevelId("CountryOrg");
            org.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<SysOrg> orgList = orgBiz.searchOrgByClause(org, "ORDINAL, ORG_CODE, RECORD_STATUS DESC, ORG_FLOW DESC");
			model.addAttribute("orgList",orgList);

			List<Map<String, Object>> list = jsResDoctorRecruitBiz.searchDoctorRecruitNum(sessionNumber,catSpeId);
			for (Map<String, Object> map : list) {
				resultMap.put(map.get("dataInfo"),map.get("num"));
			}

			//学员在协同基地，但是该协同基地和主基地的关系取消了
			List<Map<String, Object>> list2 = jsResDoctorRecruitBiz.searchDoctorRecruitNumWithNotJoin(sessionNumber,catSpeId);
			if (null!=list2 && list2.size()>0){
				//基地去重
				HashSet<Object> set = new HashSet<>();
				List<Map<String, Object>> orgMapList=new ArrayList<>();
				for (Map<String, Object> map : list2) {
					if (!set.contains(map.get("orgFlow"))){
						set.add(map.get("orgFlow"));
						Map<String, Object> m=new HashMap<>();
						m.put("orgFlow",map.get("orgFlow"));
						m.put("orgName",map.get("orgName"));
						orgMapList.add(m);
					}
				}
				model.addAttribute("orgMapList",orgMapList);

				for (Map<String, Object> map : list2) {
					resultMap.put(map.get("dataInfo"),map.get("num"));
				}
			}
		}else if (tabId.equals("speType")){	//按专业统计
			List<Map<String, Object>> list = jsResDoctorRecruitBiz.searchSpeDoctorRecruitNum(sessionNumber,catSpeId);
			for (Map<String, Object> map : list) {
				resultMap.put(map.get("dataInfo"),map.get("num"));
			}
		}
		model.addAttribute("resultMap",resultMap);
		model.addAttribute("tabId",tabId);
		model.addAttribute("sessionNumber",sessionNumber);
		return "jsres/global/homePageTrainingListAcc";
	}




	@RequestMapping(value = "/globalToEval")
	public String globalToEval(Model model) throws Exception {
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		//加载系统权限
        loginBiz.loadSysRole(user.getUserFlow(), com.pinde.core.common.GlobalConstant.EVAL_WS_ID);

        return "redirect:/main/" + com.pinde.core.common.GlobalConstant.EVAL_WS_ID;
	}

	private void init(Model model, List<String> cityIdList, String roleId, List<String> orgFlows) {
		///========================================================================================================
		//待结业审核各人员类型数
		Map<String, Map> doctorCountExtMap = new HashMap<>();
		Map<String, String> doctorCountMap = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (int i = 0; i < 3; i++) {
			String sessionNummber = DateUtil.getYear();
			sessionNummber = Integer.parseInt(sessionNummber) - i + "";
			paramMap.put("sessionNumber", sessionNummber);
			if ("hosipital".equals(roleId)) {
				if (cityIdList != null && cityIdList.size() > 0) {
					List<ResJointOrg> resJointOrgs = null;
					String orgFlow = cityIdList.get(0).toString();
					if(StringUtil.isNotEmpty(orgFlow)){
						resJointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
					}
					if(CollectionUtils.isNotEmpty(resJointOrgs)){
						//协同基地
						paramMap.put("jointOrgFlow",cityIdList.get(0));
					}else {
						paramMap.put("orgFlow",cityIdList.get(0));
					}
				}
			} else {
				paramMap.put("cityIdList", cityIdList);
			}
			doctorCountMap = resStatisticBiz.statisticDoctorCountMap(paramMap);
			doctorCountExtMap.put(sessionNummber + "pl", doctorCountMap);
		}
		//当前住培情况
		List<Map<String, Object>> currDocDetails = new ArrayList<>();
		Map<String, String> currDocSumBef2014 = new HashMap<>();
		paramMap = new HashMap<>();
		if ("hosipital".equals(roleId)) {
			if (cityIdList != null && cityIdList.size() > 0) {
				List<ResJointOrg> resJointOrgs = null;
				String orgFlow = cityIdList.get(0).toString();
				if(StringUtil.isNotEmpty(orgFlow)){
					resJointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
				}
				if(CollectionUtils.isNotEmpty(resJointOrgs)){
					//协同基地
					paramMap.put("jointOrgFlow",cityIdList.get(0));
				}else {
					paramMap.put("orgFlow",cityIdList.get(0));
				}
			}
		} else {
			paramMap.put("cityIdList", cityIdList);
		}
		currDocDetails = resStatisticBiz.getCurrDocDetails(paramMap);
		int _20count = 0;//在培
		int _21count = 0;//结业
		int _22count = 0;//考核通过带结业
		List<Map<String, Object>> currDocDetailMaps = new ArrayList<>();
		Map<String, Object> currDocDetailMap = new HashMap<String, Object>();
		String year = "";
		for (Map<String, Object> demap : currDocDetails) {
			if (Integer.parseInt(demap.get("SESSIONNUMBER").toString()) >= 2014) {
				if ("20".equals(demap.get("STATUSID"))) {
					_20count += Integer.parseInt(demap.get("NUM").toString());
				} else if ("21".equals(demap.get("STATUSID"))) {
					_21count += Integer.parseInt(demap.get("NUM").toString());
				} else if ("22".equals(demap.get("STATUSID"))) {
					_22count += Integer.parseInt(demap.get("NUM").toString());
				}
			}
			if (!year.equals(demap.get("SESSIONNUMBER"))) {
				year = demap.get("SESSIONNUMBER").toString();
				currDocDetailMap = new HashMap<>();
				currDocDetailMap.put("SESSIONNUMBER", demap.get("SESSIONNUMBER"));
			}
			if ("20".equals(demap.get("STATUSID"))) {
				currDocDetailMap.put("20", demap.get("NUM"));
			} else if ("21".equals(demap.get("STATUSID"))) {
				currDocDetailMap.put("21", demap.get("NUM"));
			} else if ("22".equals(demap.get("STATUSID"))) {
				currDocDetailMap.put("22", demap.get("NUM"));
			}
			if (currDocDetailMaps != null && currDocDetailMaps.size() > 0) {
				for (Iterator<Map<String, Object>> it = currDocDetailMaps.iterator(); it.hasNext(); ) {
					Map<String, Object> bmap = it.next();
					if (year.equals(bmap.get("SESSIONNUMBER"))) {
						it.remove();
					}
				}
			}
			currDocDetailMaps.add(currDocDetailMap);
		}
		currDocSumBef2014.put("_20count", _20count + "");
		currDocSumBef2014.put("_21count", _21count + "");
		currDocSumBef2014.put("_22count", _22count + "");

		model.addAttribute("currDocDetailMaps", currDocDetailMaps);
		model.addAttribute("currDocSumBef2014", currDocSumBef2014);
		model.addAttribute("doctorCountExtMap", doctorCountExtMap);
		///========================================================================================================
		//填写数据总量
//		Map<String,Object> sumCountAudit = new HashMap<>();
//		sumCountAudit=resStatisticBiz.sumCountAudit(orgFlows);
		//带教审核总量
//		Map<String,Object> sumCountAuditRes = new HashMap<>();
//		sumCountAuditRes=resStatisticBiz.sumCountAuditRes(orgFlows);
		//系统总访问量
//		SysLogExample example = new SysLogExample();
//		example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOperIdEqualTo(OperTypeEnum.LogIn.getId());
//		int count = logMapper.countByExample(example);
//		sumCountAudit.put("SUMCOUNTRES",sumCountAuditRes.get("SUMCOUNTRES"));
//		model.addAttribute("sumCountAudit", sumCountAudit);
//		model.addAttribute("count", count);
	}

	private void initUni(Model model) {
		//待结业审核各人员类型数
		Map<String, Map> doctorCountExtMap = new HashMap<>();
		Map<String, String> doctorCountMap = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
		model.addAttribute("org", org);
		if (org == null || (StringUtil.isBlank(org.getSendSchoolId()) && StringUtil.isBlank(org.getSendSchoolName()))) {
			return;
		}
		SysDict dictSearch = new SysDict();
		dictSearch.setDictId(org.getSendSchoolId());
		dictSearch.setDictTypeId("SendSchool");
		SysDict dict = dictBiz.searchDict(dictSearch);
		model.addAttribute("dict",dict);
		List<SysOrg> orgs = orgBiz.getUniOrgs(org.getSendSchoolId(), org.getSendSchoolName());
		model.addAttribute("orgs", orgs);
		paramMap.put("org", org);
		//每年的在校专硕有多少
		for (int i = 0; i < 3; i++) {
			String sessionNummber = DateUtil.getYear();
			if (StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))) {
				sessionNummber = InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
			}
			sessionNummber = Integer.parseInt(sessionNummber) - i + "";
			paramMap.put("sessionNumber", sessionNummber);
			doctorCountMap = resStatisticBiz.statisticDoctorCountMapForUni(paramMap);
			doctorCountExtMap.put(sessionNummber + "pl", doctorCountMap);
		}
		//当前住培情况
		List<Map<String, Object>> currDocDetails = new ArrayList<>();
		Map<String, String> currDocSumBef2014 = new HashMap<>();
		paramMap = new HashMap<>();
		paramMap.put("org", org);
		currDocDetails = resStatisticBiz.getCurrDocDetailsForUni(paramMap);
		int _20count = 0;//在培
		int _21count = 0;//结业
		int _22count = 0;//考核通过带结业
		List<Map<String, Object>> currDocDetailMaps = new ArrayList<>();
		Map<String, Object> currDocDetailMap = new HashMap<String, Object>();
		String year = "";
		for (Map<String, Object> demap : currDocDetails) {
			if (Integer.parseInt(demap.get("SESSIONNUMBER").toString()) >= 2014) {
				if ("20".equals(demap.get("STATUSID"))) {
					_20count += Integer.parseInt(demap.get("NUM").toString());
				} else if ("21".equals(demap.get("STATUSID"))) {
					_21count += Integer.parseInt(demap.get("NUM").toString());
				} else if ("22".equals(demap.get("STATUSID"))) {
					_22count += Integer.parseInt(demap.get("NUM").toString());
				}
			}
			if (!year.equals(demap.get("SESSIONNUMBER"))) {
				year = demap.get("SESSIONNUMBER").toString();
				currDocDetailMap = new HashMap<>();
				currDocDetailMap.put("SESSIONNUMBER", demap.get("SESSIONNUMBER"));
			}
			if ("20".equals(demap.get("STATUSID"))) {
				currDocDetailMap.put("20", demap.get("NUM"));
			} else if ("21".equals(demap.get("STATUSID"))) {
				currDocDetailMap.put("21", demap.get("NUM"));
			} else if ("22".equals(demap.get("STATUSID"))) {
				currDocDetailMap.put("22", demap.get("NUM"));
			}
			if (currDocDetailMaps != null && currDocDetailMaps.size() > 0) {
				for (Iterator<Map<String, Object>> it = currDocDetailMaps.iterator(); it.hasNext(); ) {
					Map<String, Object> bmap = it.next();
					if (year.equals(bmap.get("SESSIONNUMBER"))) {
						it.remove();
					}
				}
			}
			currDocDetailMaps.add(currDocDetailMap);
		}
		currDocSumBef2014.put("_20count", _20count + "");
		currDocSumBef2014.put("_21count", _21count + "");
		currDocSumBef2014.put("_22count", _22count + "");
		System.err.println(JSON.toJSON(doctorCountExtMap));
		System.err.println(JSON.toJSON(currDocDetailMaps));
		System.err.println(JSON.toJSON(currDocSumBef2014));
		model.addAttribute("currDocDetailMaps", currDocDetailMaps);
		model.addAttribute("currDocSumBef2014", currDocSumBef2014);
		model.addAttribute("doctorCountExtMap", doctorCountExtMap);
	}

	@RequestMapping(value = "/accounts")
	public String accounts(Model model) throws Exception {
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		SysLogExample example = new SysLogExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(user.getUserFlow());
		example.setOrderByClause("create_time desc");
		List<SysLog> logs = logMapper.selectByExample(example);
		if (logs != null && logs.size() > 0) {
			model.addAttribute("log", logs.get(0));
		}
		return "jsres/accounts";
	}

	@RequestMapping(value = "/userCenter")
	public String userCenter(Model model) throws Exception {
		SysUser currUser = GlobalContext.getCurrentUser();

        String roleId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE);
		if (StringUtil.isBlank(roleId)) {
            List<String> currRoleList = (List<String>) GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST);
			SysRole role = userRoleBiz.read(currRoleList.get(0));
			String roleName = role.getRoleName();
			if (roleName.equals("学员")) {
				roleId = "student";
			}
		}
		model.addAttribute("roleId",roleId);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId) || "student".equals(roleId)) {
			ResTeacherTraining teacherTraining = null;
			ResTeacherTrainingExample trainingExample = new ResTeacherTrainingExample();
            trainingExample.createCriteria().andRecordFlowEqualTo(currUser.getUserFlow()).andDoctorNameEqualTo(currUser.getUserName()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<ResTeacherTraining> teacherTrainingList = resTeacherTrainingMapper.selectByExample(trainingExample);
			if (CollectionUtils.isNotEmpty(teacherTrainingList)) {
				teacherTraining = teacherTrainingList.get(0);
			}
			if (null!=teacherTraining){
				int year = Integer.parseInt(DateUtil.getYear());
				int moYear = Integer.parseInt(teacherTraining.getModifyTime().substring(0, 4));
				int num=year- moYear;
				if (StringUtil.isNotBlank(teacherTraining.getDoctorAge())){
					teacherTraining.setDoctorAge(String.valueOf(Integer.parseInt(teacherTraining.getDoctorAge())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getOfficeYear())){
					teacherTraining.setOfficeYear(String.valueOf(Integer.parseInt(teacherTraining.getOfficeYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getWorkYear())){
					teacherTraining.setWorkYear(String.valueOf(Integer.parseInt(teacherTraining.getWorkYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getInternYear())){
					teacherTraining.setInternYear(String.valueOf(Integer.parseInt(teacherTraining.getInternYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getHosYear())){
					teacherTraining.setHosYear(String.valueOf(Integer.parseInt(teacherTraining.getHosYear())+num));
				}
			} else {
				teacherTraining = new ResTeacherTraining();
				teacherTraining.setRecordFlow(currUser.getUserFlow());
                teacherTraining.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				teacherTraining.setDoctorName(currUser.getUserName());
				teacherTraining.setOrgFlow(currUser.getOrgFlow());
				teacherTraining.setOrgName(currUser.getOrgName());
				teacherTraining.setDeptFlow(currUser.getDeptFlow());
				teacherTraining.setDeptName(currUser.getDeptName());
				GeneralMethod.setRecordInfo(teacherTraining, true);
				resTeacherTrainingMapper.insert(teacherTraining);
			}
			model.addAttribute("teacher",teacherTraining);
			List<SysDept> deptList=deptBiz.searchDeptByOrg(teacherTraining.getOrgFlow());
			model.addAttribute("deptList",deptList);
		}

		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		orgs.add(s);
		model.addAttribute("orgs", orgs);

		SysDept sysDept = new SysDept();
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		sysDept.setOrgFlow(currUser.getOrgFlow());
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);

		model.addAttribute("sysUser", currUser);

		List<SysUserDept> userDepts = userBiz.searchUserDeptByUser(currUser.getUserFlow());
		if (userDepts != null && !userDepts.isEmpty()) {
			Map<String, SysUserDept> userDeptMap = new HashMap<String, SysUserDept>();
			for (SysUserDept sud : userDepts) {
				userDeptMap.put(sud.getDeptFlow(), sud);
			}
			model.addAttribute("userDeptMap", userDeptMap);
		}
		return "jsres/userCenter";
	}

	@RequestMapping(value = {"/modPasswd"})
	public String modPasswd(Model model) {
		// 获取公钥系数和公钥指数
		KeyPair defaultKeyPair = RSAUtils.getDefaultKeyPair();
		setSessionAttribute("defaultKeyPairModPasswd",defaultKeyPair);
		RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
		if(null != publicKey){
			//公钥-系数(n)
			model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
			//公钥-指数(e1)
			model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
		}
		return "jsres/system/modPasswd";
	}

	@RequestMapping(value = {"/auditInfo"})
	public String auditInfo(Model model) {
		return "jsres/hospital/doctor/auditInfo";
	}

	@RequestMapping(value = {"/info"})
	public String info(Model model, String doctorFlow, String recordFlow) {
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor", doctor);
		ResDoctorOrgHistory history = jsDocOrgHistoryBiz.readDocOrgHistory(recordFlow);
		model.addAttribute("history", history);
		if (history != null) {
            com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(history.getRecruitFlow());
			model.addAttribute("recruit", recruit);
		}
		return "jsres/hospital/doctor/info";
	}

	/**
	 * 系统判断医院是否开通带教APP功能，如开通则医院新增的带教默认开通带教APP权限。
	 *
	 * @param userFlow
	 * @param roleFlow
	 * @param wsId
	 * @return
	 */
	@RequestMapping(value = {"/selRole"}, method = {RequestMethod.GET})
	@ResponseBody
	public String rotationList(String userFlow, String roleFlow, String wsId, String checked) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		String currentOrgFlow = currentUser.getOrgFlow();
		int result = userRoleBiz.saveSysUserRole(userFlow, roleFlow, wsId);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
			if (InitConfig.getSysCfg("res_teacher_role_flow").equals(roleFlow)) {
				String cfgCode = "jsres_" + currentOrgFlow + "_guocheng";
				JsresPowerCfg jsresPowerCfg = jsResPowerCfgBiz.read(cfgCode);
				String value = "";
				if (jsresPowerCfg != null) {
					value = jsresPowerCfg.getCfgValue();
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(value) && "Passed".equals(jsresPowerCfg.getCheckStatusId())) {
						String appCfgCode = "jsres_teacher_app_login_" + userFlow;
						String appCfgValue = "";
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checked)) {
                            appCfgValue = com.pinde.core.common.GlobalConstant.FLAG_Y;
						} else {
                            appCfgValue = com.pinde.core.common.GlobalConstant.FLAG_N;
						}
						String desc = "是否开放带教app登录权限";
						JsresPowerCfg cfg = new JsresPowerCfg();
						cfg.setCfgCode(appCfgCode);
						cfg.setCfgValue(appCfgValue);
						cfg.setCfgDesc(desc);
						cfg.setCheckStatusId("Passed");
						cfg.setCheckStatusName("审核通过");
                        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
						jsResPowerCfgBiz.save(cfg);
					}
				}
			}
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 跳到父页面，用子页面处理
	 * <p>
	 * <p>
	 * (未用)
	 */
	@RequestMapping(value = {"/{role}/doctor/auditDoctors"})
	public String auditDoctors(@PathVariable String role) {
		return "/jsres/" + role + "/doctor/auditDoctors";
	}

	/**
	 * 医师信息审核列表
	 *
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param resDoctorRecruit
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/doctorTrendList")
	public String doctorRecruit(Model model, Integer currentPage, HttpServletRequest request, ResDoctorRecruit resDoctorRecruit, SysUser user) {
		SysUser sysuser = GlobalContext.getCurrentUser();
		resDoctorRecruit.setOrgFlow(sysuser.getOrgFlow());
        resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorRecruitExt> RecruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList(resDoctorRecruit, user, null, null);
		model.addAttribute("recruitList", RecruitList);
		return "jsres/province/doctor/auditDoctorsZi";

	}

	/**
	 * 审核信息页面
	 *
	 * @param role
	 * @param model
	 * @param doctorFlow
	 * @return
	 */
	@RequestMapping(value = {"/{role}/doctor/doctorMain"})
	public String doctorMain(@PathVariable String role, Model model, String doctorFlow) {
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		model.addAttribute("recruitList", recruitList);
		return "/jsres/province/doctor/doctorMain";
	}

	//*************************医师信息详情**************************//
	@RequestMapping(value = {"/{role}/doctor/doctorPassedList"})
	public String doctorPassedList(@PathVariable String role, Model model, String doctorFlow, String studyFlag, String isRetrunShow, String recruitFlow) {

        List<com.pinde.core.model.ResDoctorRecruit> recruitList = new ArrayList<>();
		if (StringUtil.isBlank(isRetrunShow)) {
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());//  页面过滤 方便判断是否允许退回
			recruit.setDoctorFlow(doctorFlow);
            recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		} else {
            com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
			recruitList.add(recruit);
		}
		model.addAttribute("recruitList", recruitList);
		if (StringUtil.isNotBlank(studyFlag)) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(studyFlag)) {
				model.addAttribute("studyFlag", studyFlag);
			}
		}
		ResRec resRec = new ResRec();
        resRec.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
		List<String> operUserFlowList = new ArrayList<String>();
		operUserFlowList.add(doctorFlow);
		ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
		docotrBackTeturn.setDoctorFlow(doctorFlow);
        docotrBackTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
		List<ResDocotrDelayTeturn> backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn, null, null, null);
//		List<ResRec> recList = resRecBiz.searchRecInfo(resRec, operUserFlowList);
		model.addAttribute("recList", backList);
		ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
		docotrDelayTeturn.setDoctorFlow(doctorFlow);
        docotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
		List<ResDocotrDelayTeturn> delayList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, null, null, null);
//		List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId(), doctorFlow);
		model.addAttribute("delayList", delayList);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
			return "/jsres/university/doctor/doctorMain";
		}
		return "/jsres/province/doctor/doctorMain";
	}

	@RequestMapping(value = {"/{role}/doctor/doctorPass"})
	public String doctorPass(@PathVariable String role, Model model, String recruitFlow, String studyFlag) {
        com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
		model.addAttribute("recruit", recruit);
//		if(recruit!=null){
//
//			if(StringUtil.isNotBlank(recruit.getDoctorFlow()) && !StringUtil.isNotBlank(recruit.getProveFileUrl())){
//				ResDoctor doctor = resDoctorBiz.readDoctor(recruit.getDoctorFlow());
//				if(doctor!=null){
//					model.addAttribute("doctor",doctor);
//
//					String degreeType = doctor.getDegreeCategoryId();
//					if(com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)){
//						PubUserResume resume = userResumeBiz.readPubUserResume(recruit.getDoctorFlow());
//						String content = resume.getUserResume();
//						if(StringUtil.isNotBlank(content)){
//							UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
//							model.addAttribute("userResumeExt", userResumeExt);
//
//							recruit.setProveFileUrl(userResumeExt.getDegreeUri());
//						}
//					}
//				}
//			}
//		}
		model.addAttribute("studyFlag", studyFlag);
		return "/jsres/province/doctor/doctorMain";
	}

	//********************************医师培训审核（培训）***********************************************

	/**
	 * 医师报到审核
	 * @param role
	 * @return
	 */
	@RequestMapping(value = {"/{role}/doctor/recruitSignupSearch"})
	public String recruitSignupList(@PathVariable String role) {
		return "jsres/province/doctor/recruitSignupSearch";
	}

	/**
	 * 医师信息审核  住院医师
	 *
	 * @param role
	 * @return
	 */
	@RequestMapping(value = {"/{role}/doctor/recruitAuditSearch"})
	public String recruitAuditList(@PathVariable String role,Model model) {
		SysUser user = GlobalContext.getCurrentUser();
		//当前基地是否为协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isJointOrg",isJointOrg);
		model.addAttribute("roleFlag",role);
		return "jsres/province/doctor/recruitAuditSearch";
	}


	/**
	 * 医师信息审核  住院医师
	 *
	 * @param role
	 * @return
	 */
	@RequestMapping(value = {"/{role}/doctor/recruitAuditSearchAcc"})
	public String recruitAuditListAcc(@PathVariable String role,Model model) {
		SysUser user = GlobalContext.getCurrentUser();
		//当前基地是否为协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isJointOrg",isJointOrg);
		model.addAttribute("roleFlag",role);
		return "jsres/province/doctor/recruitAuditSearchAcc";
	}


	/**
	 * 省厅招录统计
	 *
	 * @param sessionNumber
	 * @return
	 */
	@RequestMapping(value = {"/zlxytj"})
	public String zlxytj(String sessionNumber, Model model) {

		List<Map<String, String>> citys = new ArrayList<>();
		Map<String, String> city = new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		List<Map<String, Object>> list = jsResDoctorRecruitBiz.zlxytj(sessionNumber);
		Map<String, List<String>> citySessionMap = new HashMap<>();
		Map<String, Object> citySessionNumMap = new HashMap<>();
		Map<String, Integer> cityNumMap = new HashMap<>();
		Map<String, Integer> typeNumMap = new HashMap<>();
		if (list != null) {
			for (Map<String, Object> map : list) {
				//每个城市有多个届别
				String cityId = (String) map.get("cityId");
				String sessionNumber2 = (String) map.get("sessionNumber");
				String typeId = (String) map.get("typeId");
				List<String> citySessionNumbers = citySessionMap.get(cityId);
				if (citySessionNumbers == null)
					citySessionNumbers = new ArrayList<>();
				if (!citySessionNumbers.contains(sessionNumber2)) {
					citySessionNumbers.add(sessionNumber2);
				}
				citySessionMap.put(cityId, citySessionNumbers);

				citySessionNumMap.put(cityId + sessionNumber2 + typeId, map.get("num"));

				Integer sum = cityNumMap.get(cityId);
				if (sum == null)
					sum = 0;
				sum += (Integer) map.get("num");
				cityNumMap.put(cityId, sum);

				Integer sum2 = typeNumMap.get(typeId);
				if (sum2 == null)
					sum2 = 0;
				sum2 += (Integer) map.get("num");
				typeNumMap.put(typeId, sum2);

			}
		}
		model.addAttribute("citySessionMap", citySessionMap);
		model.addAttribute("citySessionNumMap", citySessionNumMap);
		model.addAttribute("cityNumMap", cityNumMap);
		model.addAttribute("typeNumMap", typeNumMap);
		model.addAttribute("citys", citys);
		return "jsres/manage/zlxytj";
	}

	@RequestMapping(value = {"/zlxytj2"})
	public String zlxytj2(String sessionNumber, String orgCityId, String joint, String trainTypeId, String trainingSpeId, String datas[], Model model) {
		List<Map<String, String>> citys = new ArrayList<>();
		Map<String, String> city = new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		if (StringUtil.isBlank(sessionNumber)) {
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if (month < 9) {
				year = cal.get(Calendar.YEAR) - 1;
			} else {
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = year + "";
			model.addAttribute("sessionNumber", sessionNumber);
		} else {
			model.addAttribute("sessionNumber", sessionNumber);
		}
		List<String> docTypeList = new ArrayList<String>();
		ResDoctor doctor = new ResDoctor();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		model.addAttribute("datas", datas);
		Map<String, Object> param = new HashMap<>();
		param.put("docTypeList", docTypeList);
		param.put("orgCityId", orgCityId);
		param.put("joint", joint);
		param.put("sessionNumber", sessionNumber);
		param.put("trainTypeId", trainTypeId);
		param.put("trainingSpeId", trainingSpeId);
		List<SysOrg> orgs = orgBiz.queryAllSysOrg(new SysOrg());
		Map<String, String> orgNameMap = new HashMap<>();
		Map<String, List<String>> cityOrgMap = new HashMap<>();
		Map<Object, Object> orgSpeFlagMap = new HashMap<Object, Object>();//基地专业标志的的map
		Map<String, List<String>> jointOrgMap = new HashMap<>();
		Map<String, Integer> typeNumMap = new HashMap<>();
		if (orgs != null) {
			for (SysOrg o : orgs) {
				orgNameMap.put(o.getOrgFlow(), o.getOrgName());
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(o.getOrgLevelId())) {
					List<String> orgFlows = cityOrgMap.get(o.getOrgCityId());
					if (orgFlows == null)
						orgFlows = new ArrayList<>();
					if (!orgFlows.contains(o.getOrgFlow())) {
						orgFlows.add(o.getOrgFlow());
					}
					cityOrgMap.put(o.getOrgCityId(), orgFlows);

                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(joint)) {
						List<String> jointOrgFlowList = new ArrayList<>();
						List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(o.getOrgFlow());//查询每家基地的协同基地
						if (jointOrgList != null && !jointOrgList.isEmpty()) {
							for (SysOrg resJointOrg : jointOrgList) {
								if (!jointOrgFlowList.contains(resJointOrg.getOrgFlow())) {
									jointOrgFlowList.add(resJointOrg.getOrgFlow());
								}
							}
						}
						jointOrgMap.put(o.getOrgFlow(), jointOrgFlowList);
					}
				}

				ResOrgSpe resOrgSpe = new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList = resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if (orgSpeList != null && !orgSpeList.isEmpty()) {//每家基地的专业
					for (ResOrgSpe r : orgSpeList) {
                        orgSpeFlagMap.put(o.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
			}
		}
		model.addAttribute("orgNameMap", orgNameMap);
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);


		List<Map<String, Object>> list = jsResDoctorRecruitBiz.zlxytj2(param);
		Map<String, Object> cityOrgNumMap = new HashMap<>();
		if (list != null) {
			for (Map<String, Object> map : list) {
				//每个城市有多个届别
				String cityId = (String) map.get("cityId");
				String orgFlow = (String) map.get("orgFlow");
				String speId = (String) map.get("speId");
				cityOrgNumMap.put(cityId + orgFlow + speId, map.get("num"));

				Integer sum2 = typeNumMap.get(speId);
				if (sum2 == null)
					sum2 = 0;
				sum2 += (Integer) map.get("num");
				typeNumMap.put(speId, sum2);

                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(joint)) {
					param.put("orgFlow", orgFlow);
					List<Map<String, Object>> list2 = jsResDoctorRecruitBiz.zlxytjJoint(param);
					if (list2 != null) {
						for (Map<String, Object> map2 : list2) {
							//每个城市有多个届别
							String cityId2 = (String) map2.get("cityId");
							String jointOrgFlow = (String) map2.get("jointOrgFlow");
							String speId2 = (String) map2.get("speId");
							cityOrgNumMap.put(cityId2 + jointOrgFlow + speId2, map2.get("num"));
						}
					}
				}
			}
		}
		model.addAttribute("jointOrgMap", jointOrgMap);
		model.addAttribute("typeNumMap", typeNumMap);
		model.addAttribute("cityOrgMap", cityOrgMap);
		model.addAttribute("cityOrgNumMap", cityOrgNumMap);
		model.addAttribute("citys", citys);
		return "jsres/manage/zlxytj2";
	}

	/**
	 * @Author xieyh
	 * @Description 省厅招录统计报表（医院/科室）
	 * @Date  2024-05-17
	 **/
	@RequestMapping(value = {"/recruitStatisticsReport"})
	public String recruitStatisticsReport(String sessionNumber, String orgFlow, String speId, String statusId, String docType, String isLoad, Model model) {
		Map<String,Object> params = new HashMap<>();
		List<String> docTypeList = new ArrayList<>();
		if(StringUtil.isNotBlank(docType)){
			docTypeList.add(docType);
		}else{
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
			}
		}
		params.put("docTypeList",docTypeList);
		params.put("statusId",statusId);
		params.put("speId",speId);

		if (StringUtil.isBlank(sessionNumber)) {
			if (DateUtil.getCurrDate().compareTo(DateUtil.getYear() + "-09-30") > 0) {
				sessionNumber = DateUtil.getYear();
			} else {
				sessionNumber = String.valueOf(Integer.parseInt(DateUtil.getYear()) - 1);
			}
		}
		model.addAttribute("sessionNumber", sessionNumber);

		SysCfg photoRecruitDataCfg = sysCfgMapper.selectByPrimaryKey("jsres_photo_recruitData");
		model.addAttribute("jsres_photo_recruitData", photoRecruitDataCfg.getCfgValue());

		List<String> orgLevels = new ArrayList<>();
        orgLevels.add(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		// 查询基地/医院
		List<SysOrg> orgList = orgBiz.searchSysOrgOrder(orgLevels);
		orgList.sort(Comparator.comparingInt(o -> o.getOrgCode().hashCode()));
		model.addAttribute("orgList",orgList);

		List<SysOrg> searchOrgList = new ArrayList<>();
		if (StringUtil.isNotBlank(orgFlow)) {
			searchOrgList.add(orgBiz.readSysOrg(orgFlow));
		} else {
			searchOrgList = orgList;
		}

		model.addAttribute("searchOrgList",searchOrgList);
		model.addAttribute("speId",speId);

		// 对数据进行计算，初始化值
		// 住院医师
		HashMap<String, Integer> doctorTrainingMap = new HashMap<>();
		doctorTrainingMap.put("all", 0);
		// 在校专硕
		HashMap<String, Integer> graduateMap = new HashMap<>();
		graduateMap.put("all", 0);
		// 各专业所有学校总计
		Map<String,Integer> speAll = new HashMap<>();

		if (searchOrgList != null && searchOrgList.size() > 0) {
			// 查询专业
			Map<String, Map<String,Object>> orgSpeList = new HashMap<>();
				for (SysOrg sysOrg: searchOrgList) {
				String searchOrgFlow = sysOrg.getOrgFlow() == null ? "" : sysOrg.getOrgFlow();
				// 查询人数信息
//                List<Map<String,Object>> infos = recruitDoctorInfoBiz.getRecruitSpeInfo(orgFlow,sessionNumber,"");
				params.put("orgFlow",searchOrgFlow);
				params.put("sessionNumber",sessionNumber);
//				List<Map<String,Object>> infos = recruitDoctorInfoBiz.getRecruitSpeInfoNew(params);

				// 查询人员名单并处理数据
				Map<String,Integer> speInfos = new HashMap<>();
				speInfos.put("all", 0);
				List<Map<String,Object>> infos = recruitDoctorInfoBiz.getOrgRecruitSpeInfo(params);
				for (Map<String, Object> info : infos) {
					calculateRecruitStudentForReport(doctorTrainingMap, graduateMap, speAll, speInfos, info);
				}

				Map<String, Object> map = new HashMap<>();
				map.put(searchOrgFlow,speInfos);
				orgSpeList.put(searchOrgFlow,map);
			}
			model.addAttribute("orgSpeList",orgSpeList);
			model.addAttribute("doctorTrainingMap",doctorTrainingMap);
			model.addAttribute("graduateMap",graduateMap);
			model.addAttribute("speAll",speAll);
		}

//		return "jsres/manage/recruitList";
		if (StringUtil.isNotBlank(isLoad)) {
			return "jsres/manage/statisticsLoad";
		} else {
			return "jsres/manage/recruitStudentList";
		}
	}

	/**
	 * @Description 对本年度招录数据进行快照备份处理
	 * @return
	 * @Date 2024-05-18
	 * @Author xieyh
	 */
	@RequestMapping(value = {"/photoRecruitInfo"})
	@ResponseBody
	public String photoRecruitInfo() {
		Map<String,Object> params = new HashMap<>();
		List<String> docTypeList = new ArrayList<>();
        for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
			docTypeList.add(e.getId());
		}
		params.put("docTypeList",docTypeList);
		List<String> orgLevels = new ArrayList<>();
        orgLevels.add(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		// 查询基地/医院
		List<SysOrg> searchOrgList = orgBiz.searchSysOrgOrder(orgLevels);

		String sessionNumber = DateUtil.getYear();
		String currDate = DateUtil.getCurrDate();
		List<String> photoTimeList = recruitDoctorInfoBiz.getHistoryPhotoTimeList();
		if (CollectionUtils.isNotEmpty(photoTimeList) && photoTimeList.contains(currDate)) {
            return com.pinde.core.common.GlobalConstant.PHOTO_ALREADY;
		}

		Map<String, Map<String,Integer>> orgSpeList = new HashMap<>();
		if (searchOrgList != null && searchOrgList.size() > 0) {
			// 查询专业
			for (SysOrg sysOrg: searchOrgList) {
				String searchOrgFlow = sysOrg.getOrgFlow() == null ? "" : sysOrg.getOrgFlow();
				// 查询人数信息
				params.put("orgFlow",searchOrgFlow);
				params.put("sessionNumber",sessionNumber);
				// 查询人员名单并处理数据
				Map<String,Integer> speInfos = new HashMap<>();
				List<Map<String,Object>> infos = recruitDoctorInfoBiz.getOrgRecruitSpeInfo(params);
				for (Map<String, Object> info : infos) {
                    if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(info.get("doctorTypeId"))) {
						// 做每家基地各专业基地的在校专硕以及总人数的统计
						// 此处的key用的是各专业基地代码拼上  graduate 表示该专业基地在校专硕的人数
						if (speInfos.containsKey(info.get("speId") + "graduate")) {
							speInfos.put(info.get("speId") + "graduate", speInfos.get(info.get("speId") + "graduate") + 1);
						} else {
							speInfos.put(info.get("speId") + "graduate", 1);
						}
						// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数
						if (speInfos.containsKey(info.get("speId") + "all")) {
							speInfos.put(info.get("speId") + "all", speInfos.get(info.get("speId") + "all") + 1);
						} else {
							speInfos.put(info.get("speId") + "all", 1);
						}
					}
                    if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(info.get("doctorTypeId"))) {
						// 做每家基地各专业基地的在校专硕以及总人数的统计
						// 此处的key用的是各专业基地代码拼上  graduate 表示该专业基地在校专硕的人数
						if (speInfos.containsKey(info.get("speId") + "company")) {
							speInfos.put(info.get("speId") + "company", speInfos.get(info.get("speId") + "company") + 1);
						} else {
							speInfos.put(info.get("speId") + "company", 1);
						}
						// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数
						if (speInfos.containsKey(info.get("speId") + "all")) {
							speInfos.put(info.get("speId") + "all", speInfos.get(info.get("speId") + "all") + 1);
						} else {
							speInfos.put(info.get("speId") + "all", 1);
						}
					}
                    if (com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(info.get("doctorTypeId"))) {
						// 做每家基地各专业基地的在校专硕以及总人数的统计
						// 此处的key用的是各专业基地代码拼上  graduate 表示该专业基地在校专硕的人数
						if (speInfos.containsKey(info.get("speId") + "companyEntrust")) {
							speInfos.put(info.get("speId") + "companyEntrust", speInfos.get(info.get("speId") + "companyEntrust") + 1);
						} else {
							speInfos.put(info.get("speId") + "companyEntrust", 1);
						}
						// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数
						if (speInfos.containsKey(info.get("speId") + "all")) {
							speInfos.put(info.get("speId") + "all", speInfos.get(info.get("speId") + "all") + 1);
						} else {
							speInfos.put(info.get("speId") + "all", 1);
						}
					}
                    if (com.pinde.core.common.enums.ResDocTypeEnum.Social.getId().equals(info.get("doctorTypeId"))) {
						// 做每家基地各专业基地的在校专硕以及总人数的统计
						// 此处的key用的是各专业基地代码拼上  graduate 表示该专业基地在校专硕的人数
						if (speInfos.containsKey(info.get("speId") + "social")) {
							speInfos.put(info.get("speId") + "social", speInfos.get(info.get("speId") + "social") + 1);
						} else {
							speInfos.put(info.get("speId") + "social", 1);
						}
						// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数
						if (speInfos.containsKey(info.get("speId") + "all")) {
							speInfos.put(info.get("speId") + "all", speInfos.get(info.get("speId") + "all") + 1);
						} else {
							speInfos.put(info.get("speId") + "all", 1);
						}
					}
				}
				orgSpeList.put(searchOrgFlow, speInfos);
			}
		}
        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		// 执行数据固定操作（存入res_recruit_history表中）
		for (SysOrg sysOrg : searchOrgList) {
			Map<String, Integer> speInfos = orgSpeList.get(sysOrg.getOrgFlow());
			for (SysDict dict : sysDictList) {
				ResRecruitHistory history = new ResRecruitHistory();
				history.setHistoryFlow(PkUtil.getUUID());
				history.setOrgFlow(sysOrg.getOrgFlow());
				history.setOrgName(sysOrg.getOrgName());
				history.setPhotoTime(DateUtil.getCurrDate());
                history.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				history.setSpeId(dict.getDictId());
				history.setSpeName(dict.getDictName());
				if (speInfos.containsKey(dict.getDictId() + "all")) {
					history.setRecruitNumber(String.valueOf(speInfos.get(dict.getDictId() + "all")));
				} else {
					history.setRecruitNumber("0");
				}
				if (speInfos.containsKey(dict.getDictId() + "graduate")) {
					history.setGraduateNumber(String.valueOf(speInfos.get(dict.getDictId() + "graduate")));
				} else {
					history.setGraduateNumber("0");
				}
				if (speInfos.containsKey(dict.getDictId() + "company")) {
					history.setCompanyNumber(String.valueOf(speInfos.get(dict.getDictId() + "company")));
				} else {
					history.setCompanyNumber("0");
				}
				if (speInfos.containsKey(dict.getDictId() + "companyEntrust")) {
					history.setCompanyEntrustNumber(String.valueOf(speInfos.get(dict.getDictId() + "companyEntrust")));
				} else {
					history.setCompanyEntrustNumber("0");
				}
				if (speInfos.containsKey(dict.getDictId() + "social")) {
					history.setSocialNumber(String.valueOf(speInfos.get(dict.getDictId() + "social")));
				} else {
					history.setSocialNumber("0");
				}
				GeneralMethod.setRecordInfo(history, true);
				recruitDoctorInfoBiz.saveRecruitHistory(history);
			}
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * @Description 招录学员统计计算各类型学员数据
	 * @param doctorTrainingMap 住院医师统计
	 * @param graduateMap 在校专硕统计
	 * @param speAll 按专业分总人数统计
	 * @param speInfos 每个学校各专业各类型人数统计
	 * @param info 查询数据库原始数据
	 * @Date  2024-05-16
	 * @Author xieyh
	 */
	private void calculateRecruitStudentForReport(HashMap<String, Integer> doctorTrainingMap, HashMap<String, Integer> graduateMap, Map<String, Integer> speAll, Map<String, Integer> speInfos, Map<String, Object> info) {
        if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(info.get("doctorTypeId"))) {
			// 做大数据在校专硕各项的分开统计
			graduateMap.put("all", graduateMap.get("all") + 1); // 在校专硕总人数+1
			// 此处的key用的是各学员在校状态的ID
			if (graduateMap.containsKey(info.get("doctorStatusId"))) {
				graduateMap.put((String) info.get("doctorStatusId"), graduateMap.get(info.get("doctorStatusId")) + 1);
			} else {
				graduateMap.put((String) info.get("doctorStatusId"), 1);
			}
			// 做每家基地各专业基地的在校专硕以及总人数的统计
			speInfos.put("all", speInfos.get("all") + 1); // 各基地总人数+1
			// 此处的key用的是各专业基地代码拼上  graduate 表示该专业基地在校专硕的人数
			if (speInfos.containsKey(info.get("speId") + "graduate")) {
				speInfos.put(info.get("speId") + "graduate", speInfos.get(info.get("speId") + "graduate") + 1);
			} else {
				speInfos.put(info.get("speId") + "graduate", 1);
			}
			// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数
			if (speInfos.containsKey(info.get("speId") + "all")) {
				speInfos.put(info.get("speId") + "all", speInfos.get(info.get("speId") + "all") + 1);
			} else {
				speInfos.put(info.get("speId") + "all", 1);
			}
			// 做每个专业基地的在校专硕以及总人数的统计
			// 此处的key用的是各专业基地代码拼上  graduate 表示该专业基地在校专硕的人数 但是是各基地累加的区分上面speInfos
			if (speAll.containsKey(info.get("speId") + "graduate")) {
				speAll.put(info.get("speId") + "graduate", speAll.get(info.get("speId") + "graduate") + 1);
			} else {
				speAll.put(info.get("speId") + "graduate", 1);
			}
			// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数 但是是各基地累加的区分上面speInfos
			if (speAll.containsKey(info.get("speId") + "all")) {
				speAll.put(info.get("speId") + "all", speAll.get(info.get("speId") + "all") + 1);
			} else {
				speAll.put(info.get("speId") + "all", 1);
			}
		} else {
			// 做大数据住院医师各项的分开统计
			doctorTrainingMap.put("all", doctorTrainingMap.get("all") + 1);
			// 此处的key用的是各学员在校状态的ID
			if (doctorTrainingMap.containsKey(info.get("doctorStatusId"))) {
				doctorTrainingMap.put((String) info.get("doctorStatusId"), doctorTrainingMap.get(info.get("doctorStatusId")) + 1);
			} else {
				doctorTrainingMap.put((String) info.get("doctorStatusId"), 1);
			}
			// 做每家基地各专业基地的住院医师以及总人数的统计
			speInfos.put("all", speInfos.get("all") + 1);
			// 此处的key用的是各专业基地代码拼上  doctorTraining 表示该专业基地住院医师的人数
			if (speInfos.containsKey(info.get("speId") + "doctorTraining")) {
				speInfos.put(info.get("speId") + "doctorTraining", speInfos.get(info.get("speId") + "doctorTraining") + 1);
			} else {
				speInfos.put(info.get("speId") + "doctorTraining", 1);
			}
			// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数
			if (speInfos.containsKey(info.get("speId") + "all")) {
				speInfos.put(info.get("speId") + "all", speInfos.get(info.get("speId") + "all") + 1);
			} else {
				speInfos.put(info.get("speId") + "all", 1);
			}
			// 做每个专业基地的住院医师以及总人数的统计
			// 此处的key用的是各专业基地代码拼上  doctorTraining 表示该专业基地住院医师的人数 但是是各基地累加的区分上面speInfos
			if (speAll.containsKey(info.get("speId") + "doctorTraining")) {
				speAll.put(info.get("speId") + "doctorTraining", speAll.get(info.get("speId") + "doctorTraining") + 1);
			} else {
				speAll.put(info.get("speId") + "doctorTraining", 1);
			}
			// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数 但是是各基地累加的区分上面speInfos
			if (speAll.containsKey(info.get("speId") + "all")) {
				speAll.put(info.get("speId") + "all", speAll.get(info.get("speId") + "all") + 1);
			} else {
				speAll.put(info.get("speId") + "all", 1);
			}
		}
	}

	@RequestMapping(value = {"/recruitHistoryReport"})
	public String recruitHistoryReport(String photoTime, String orgFlow, String speId, String docType, String isLoad, String isModify, Model model) {

		List<String> orgLevels = new ArrayList<>();
        orgLevels.add(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		// 查询基地/医院
		List<SysOrg> orgList = orgBiz.searchSysOrgOrder(orgLevels);
		orgList.sort(Comparator.comparingInt(o -> o.getOrgCode().hashCode()));
		model.addAttribute("orgList",orgList);

		List<SysOrg> searchOrgList = new ArrayList<>();
		if (StringUtil.isNotBlank(orgFlow)) {
			searchOrgList.add(orgBiz.readSysOrg(orgFlow));
		} else {
			searchOrgList = orgList;
		}
		model.addAttribute("searchOrgList",searchOrgList);

		// 初始化时间节点
		List<String> photoTimeList = recruitDoctorInfoBiz.getHistoryPhotoTimeList();
		model.addAttribute("photoTimeList", photoTimeList);
		if (StringUtil.isBlank(photoTime)) {
			if (CollectionUtils.isNotEmpty(photoTimeList)) {
				photoTime = photoTimeList.get(0);
			}
		}
		model.addAttribute("photoTime", photoTime);
		model.addAttribute("orgFlow", orgFlow);
		model.addAttribute("speId", speId);
		List<ResRecruitHistory> recruitHistoryList =  recruitDoctorInfoBiz.getRecruitHistoryInfo(photoTime, orgFlow, speId);
		Map<String, Integer> summaryCount = new HashMap<>();
		// 住院医师总人数
		summaryCount.put("doctorTraining", 0);
		// 在校专硕总人数
		summaryCount.put("graduate", 0);
		// 各专业所有学校总计
		Map<String,Integer> speAll = new HashMap<>();
		StringBuilder remarks = new StringBuilder();
		for (ResRecruitHistory recruitHistory : recruitHistoryList) {
			if (StringUtil.isNotBlank(recruitHistory.getRemarks())) {
				remarks.append(recruitHistory.getRemarks()).append("<br/>").append("&#12288;&#12288;&#12288;");
			}
			calculateRecruitStudentHisTory(docType, summaryCount, speAll, recruitHistory);
		}
		model.addAttribute("summaryCount", summaryCount);
		model.addAttribute("speAll", speAll);
		model.addAttribute("isModify", isModify);
		model.addAttribute("remarks", remarks.toString());
		if (StringUtil.isNotBlank(isLoad)) {
			return "jsres/manage/historyInner";
		} else {
			return "jsres/manage/historyLoad";
		}
	}

	private void calculateRecruitStudentHisTory(String docType, Map<String, Integer> summaryCount, Map<String, Integer> speAll, ResRecruitHistory recruitHistory) {
		String orgFlow = recruitHistory.getOrgFlow();
		String speId = recruitHistory.getSpeId();
		// 本单位人
		String companyNumber = (recruitHistory.getCompanyNumber() == null ? "0" : recruitHistory.getCompanyNumber());
		// 委培单位人
		String companyEntrustNumber = (recruitHistory.getCompanyEntrustNumber() == null ? "0" : recruitHistory.getCompanyEntrustNumber());
		// 行业内人
		String socialNumber = (recruitHistory.getSocialNumber() == null ? "0" : recruitHistory.getSocialNumber());
		// 在校专硕
		String graduateNumber = (recruitHistory.getGraduateNumber() == null ? "0" : recruitHistory.getGraduateNumber());
		// 住院医师总人数
		int doctorTrainingNumber = Integer.parseInt(companyEntrustNumber) + Integer.parseInt(companyNumber) + Integer.parseInt(socialNumber);
		// 总人数
		int allNumber = Integer.parseInt(companyEntrustNumber) + Integer.parseInt(companyNumber) + Integer.parseInt(socialNumber) + Integer.parseInt(graduateNumber);
		if (StringUtil.isBlank(docType)) {
			summaryCount.put("doctorTraining", summaryCount.get("doctorTraining") + doctorTrainingNumber);
			summaryCount.put("graduate", summaryCount.get("graduate") + Integer.parseInt(graduateNumber));

			if (speAll.containsKey(speId + "doctorTraining")) {
				speAll.put(speId + "doctorTraining", speAll.get(speId + "doctorTraining") + doctorTrainingNumber);
			} else {
				speAll.put(speId + "doctorTraining", doctorTrainingNumber);
			}
			if (speAll.containsKey(speId + "graduate")) {
				speAll.put(speId + "graduate", speAll.get(speId + "graduate") + Integer.parseInt(graduateNumber));
			} else {
				speAll.put(speId + "graduate", Integer.parseInt(graduateNumber));
			}
			if (speAll.containsKey(speId + "all")) {
				speAll.put(speId + "all", speAll.get(speId + "all") + allNumber);
			} else {
				speAll.put(speId + "all", allNumber);
			}
			speAll.put(orgFlow + speId + "doctorTraining", doctorTrainingNumber);
			speAll.put(orgFlow + speId + "graduate", Integer.parseInt(graduateNumber));
			speAll.put(orgFlow + speId + "all", allNumber);
			if (speAll.containsKey(orgFlow + "all")) {
				speAll.put(orgFlow + "all", speAll.get(orgFlow + "all") + allNumber);
			} else {
				speAll.put(orgFlow + "all", allNumber);
			}
		} else {
            if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(docType)) {
				summaryCount.put("doctorTraining", summaryCount.get("doctorTraining") + Integer.parseInt(companyNumber));
				// 此处的key用的是各专业基地代码拼上  doctorTraining 表示该专业基地住院医师的人数 但是是各基地累加的
				if (speAll.containsKey(speId + "doctorTraining")) {
					speAll.put(speId + "doctorTraining", speAll.get(speId + "doctorTraining") + Integer.parseInt(companyNumber));
				} else {
					speAll.put(speId + "doctorTraining", Integer.parseInt(companyNumber));
				}
				// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数 但是是各基地累加的
				if (speAll.containsKey(speId + "all")) {
					speAll.put(speId + "all", speAll.get(speId + "all") + Integer.parseInt(companyNumber));
				} else {
					speAll.put(speId + "all", Integer.parseInt(companyNumber));
				}
				speAll.put(orgFlow + speId + "doctorTraining", Integer.parseInt(companyNumber));
				speAll.put(orgFlow + speId + "all", Integer.parseInt(companyNumber));
				if (speAll.containsKey(orgFlow + "all")) {
					speAll.put(orgFlow + "all", speAll.get(orgFlow + "all") + Integer.parseInt(companyNumber));
				} else {
					speAll.put(orgFlow + "all", Integer.parseInt(companyNumber));
				}
			}
            if (com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId().equals(docType)) {
				summaryCount.put("doctorTraining", summaryCount.get("doctorTraining") + Integer.parseInt(companyEntrustNumber));
				// 此处的key用的是各专业基地代码拼上  doctorTraining 表示该专业基地住院医师的人数 但是是各基地累加的
				if (speAll.containsKey(speId + "doctorTraining")) {
					speAll.put(speId + "doctorTraining", speAll.get(speId + "doctorTraining") + Integer.parseInt(companyEntrustNumber));
				} else {
					speAll.put(speId + "doctorTraining", Integer.parseInt(companyEntrustNumber));
				}
				// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数 但是是各基地累加的
				if (speAll.containsKey(speId + "all")) {
					speAll.put(speId + "all", speAll.get(speId + "all") + Integer.parseInt(companyEntrustNumber));
				} else {
					speAll.put(speId + "all", Integer.parseInt(companyEntrustNumber));
				}
				speAll.put(orgFlow + speId + "doctorTraining", Integer.parseInt(companyEntrustNumber));
				speAll.put(orgFlow + speId + "all", Integer.parseInt(companyEntrustNumber));
				if (speAll.containsKey(orgFlow + "all")) {
					speAll.put(orgFlow + "all", speAll.get(orgFlow + "all") + Integer.parseInt(companyEntrustNumber));
				} else {
					speAll.put(orgFlow + "all", Integer.parseInt(companyEntrustNumber));
				}
			}
            if (com.pinde.core.common.enums.ResDocTypeEnum.Social.getId().equals(docType)) {
				summaryCount.put("doctorTraining", summaryCount.get("doctorTraining") + Integer.parseInt(socialNumber));
				// 此处的key用的是各专业基地代码拼上  doctorTraining 表示该专业基地住院医师的人数 但是是各基地累加的
				if (speAll.containsKey(speId + "doctorTraining")) {
					speAll.put(speId + "doctorTraining", speAll.get(speId + "doctorTraining") + Integer.parseInt(socialNumber));
				} else {
					speAll.put(speId + "doctorTraining", Integer.parseInt(socialNumber));
				}
				// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数 但是是各基地累加的
				if (speAll.containsKey(speId + "all")) {
					speAll.put(speId + "all", speAll.get(speId + "all") + Integer.parseInt(socialNumber));
				} else {
					speAll.put(speId + "all", Integer.parseInt(socialNumber));
				}
				speAll.put(orgFlow + speId + "doctorTraining", Integer.parseInt(socialNumber));
				speAll.put(orgFlow + speId + "all", Integer.parseInt(socialNumber));
				if (speAll.containsKey(orgFlow + "all")) {
					speAll.put(orgFlow + "all", speAll.get(orgFlow + "all") + Integer.parseInt(socialNumber));
				} else {
					speAll.put(orgFlow + "all", Integer.parseInt(socialNumber));
				}
			}
            if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(docType)) {
				summaryCount.put("graduate", summaryCount.get("graduate") + Integer.parseInt(graduateNumber));
				// 做每个专业基地的在校专硕以及总人数的统计
				// 此处的key用的是各专业基地代码拼上  graduate 表示该专业基地在校专硕的人数 但是是各基地累加的
				if (speAll.containsKey(speId + "graduate")) {
					speAll.put(speId + "graduate", speAll.get(speId + "graduate") + Integer.parseInt(graduateNumber));
				} else {
					speAll.put(speId + "graduate", Integer.parseInt(graduateNumber));
				}
				// 此处的key用的是各专业基地代码拼上  all 表示该专业基地的总人数 但是是各基地累加的
				if (speAll.containsKey(speId + "all")) {
					speAll.put(speId + "all", speAll.get(speId + "all") + Integer.parseInt(graduateNumber));
				} else {
					speAll.put(speId + "all", Integer.parseInt(graduateNumber));
				}
				speAll.put(orgFlow + speId + "graduate", Integer.parseInt(graduateNumber));
				speAll.put(orgFlow + speId + "all", Integer.parseInt(graduateNumber));
				if (speAll.containsKey(orgFlow + "all")) {
					speAll.put(orgFlow + "all", speAll.get(orgFlow + "all") + Integer.parseInt(graduateNumber));
				} else {
					speAll.put(orgFlow + "all", Integer.parseInt(graduateNumber));
				}
			}
		}
	}

	@RequestMapping(value = {"/recruitStatisticsReportAcc"})
	public String recruitStatisticsReportAcc(String sessionNumber,String statusId,String datas[],Model model) {
		Map<String,Object> params = new HashMap<>();
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s0:datas){
				docTypeList.add(s0);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		model.addAttribute("datas", datas);
		params.put("docTypeList",docTypeList);
		params.put("statusId",statusId);
		List<String> orgLevels = new ArrayList<>();
        orgLevels.add(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		// 查询基地/医院
		List<SysOrg> orgList = orgBiz.searchSysOrgOrder(orgLevels);
		model.addAttribute("orgList",orgList);
		if (orgList != null && orgList.size() > 0) {
			// 查询专业
			Map<String, Map<String,Object>> orgSpeList = new HashMap<>();
			for (SysOrg sysOrg: orgList) {
				String orgFlow = sysOrg.getOrgFlow() == null ? "" : sysOrg.getOrgFlow();
				params.put("orgFlow",orgFlow);
				params.put("sessionNumber",sessionNumber);
				List<Map<String,Object>> infos = recruitDoctorInfoBiz.getRecruitSpeInfoNew(params);
				Map<String, Object> map = new HashMap<>();
				map.put(orgFlow,infos);
				orgSpeList.put(orgFlow,map);
			}
			model.addAttribute("orgSpeList",orgSpeList);
		}
		return "jsres/manage/recruitListAcc";
	}

	/**
	 * @Author xieyh
	 * @Description 导出招录学员统计数据
	 * @Date  2024-05-18
	 **/
	@RequestMapping(value = {"/exportRecruitStatistics"})
	public void exportRecruitStatistics(String sessionNumber, String orgFlow, String speId, String statusId, String docType, HttpServletResponse response) throws IOException {

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
		HSSFRow rowOne = sheet.createRow(0);//第1行
		HSSFRow rowTwo = sheet.createRow(1);//第2行

        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		List<String> titles = new ArrayList<>();
		titles.add("基地名称");
		for (SysDict sysDict : sysDictList) {
			if (sysDict.getDictId().equals("50")) {
				continue;
			}
			titles.add(sysDict.getDictName());
			titles.add("合计");
		}
		titles.add("总计");
		// 设计表头
		HSSFCell cellTitleOne;
		HSSFCell cellTitleTwo;
		HSSFCell cellTitleThree;
		int k = 0;
		for (int i = 0; i < titles.size(); i++) {
			if (i % 2 == 1 && i != titles.size() - 1) {
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, i + k, i + k + 1);
				sheet.addMergedRegion(cellRangeAddress);
				k++;
				cellTitleOne = rowOne.createCell(i + k - 1);
				cellTitleTwo = rowTwo.createCell(i + k - 1);
				cellTitleThree = rowTwo.createCell(i + k);
				cellTitleTwo.setCellValue("住院医师");
				cellTitleTwo.setCellStyle(styleCenter);
				cellTitleThree.setCellValue("在校专硕");
				cellTitleThree.setCellStyle(styleCenter);
			} else {
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, i + k, i + k);
				sheet.addMergedRegion(cellRangeAddress);
				cellTitleOne = rowOne.createCell(i + k);
			}
			cellTitleOne.setCellValue(titles.get(i));
			cellTitleOne.setCellStyle(styleCenter);
		}

		Map<String,Object> params = new HashMap<>();
		List<String> docTypeList = new ArrayList<>();
		if(StringUtil.isNotBlank(docType)){
			docTypeList.add(docType);
		}else{
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
			}
		}
		params.put("docTypeList",docTypeList);
		params.put("statusId",statusId);
		params.put("speId",speId);
		List<String> orgLevels = new ArrayList<>();
        orgLevels.add(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		// 查询基地/医院
		List<SysOrg> orgList = orgBiz.searchSysOrgOrder(orgLevels);
		orgList.sort(Comparator.comparingInt(o -> o.getOrgCode().hashCode()));
		List<SysOrg> searchOrgList = new ArrayList<>();
		if (StringUtil.isNotBlank(orgFlow)) {
			searchOrgList.add(orgBiz.readSysOrg(orgFlow));
		} else {
			searchOrgList = orgList;
		}

		// 对数据进行计算，初始化值
		// 住院医师
		HashMap<String, Integer> doctorTrainingMap = new HashMap<>();
		doctorTrainingMap.put("all", 0);
		// 在校专硕
		HashMap<String, Integer> graduateMap = new HashMap<>();
		graduateMap.put("all", 0);
		// 各专业所有学校总计
		Map<String,Integer> speAll = new HashMap<>();
		if (searchOrgList != null && searchOrgList.size() > 0) {
			// 查询专业
			for (int i = 0; i < searchOrgList.size(); i++) {// 34 33
				SysOrg sysOrg = searchOrgList.get(i);
				int j = 0;
				// 查询人员名单并处理数据
				Map<String,Integer> speInfos = new HashMap<>();
				speInfos.put("all", 0);
				String searchOrgFlow = sysOrg.getOrgFlow() == null ? "" : sysOrg.getOrgFlow();
				String orgName = sysOrg.getOrgName() == null ? "" : sysOrg.getOrgName();

				HSSFRow rowThree = sheet.createRow(i + 2); //第3行
				HSSFCell cellOne = rowThree.createCell(j++);
				cellOne.setCellStyle(styleCenter);
				cellOne.setCellValue(orgName);
				params.put("orgFlow",searchOrgFlow);
				params.put("sessionNumber",sessionNumber);
				List<Map<String,Object>> infos = recruitDoctorInfoBiz.getOrgRecruitSpeInfo(params);
				for (Map<String, Object> info : infos) {
					calculateRecruitStudentForReport(doctorTrainingMap, graduateMap, speAll, speInfos, info);
				}
				for (SysDict sysDict : sysDictList) {
					if (sysDict.getDictId().equals("50")) {
						continue;
					}
					HSSFCell cellTwo = rowThree.createCell(j++);
					cellTwo.setCellStyle(styleCenter);
					cellTwo.setCellValue(speInfos.getOrDefault(sysDict.getDictId() + "doctorTraining", 0));

					HSSFCell cellThree = rowThree.createCell(j++);
					cellThree.setCellStyle(styleCenter);
					cellThree.setCellValue(speInfos.getOrDefault(sysDict.getDictId() + "graduate", 0));

					HSSFCell cellFour = rowThree.createCell(j++);
					cellFour.setCellStyle(styleCenter);
					cellFour.setCellValue(speInfos.getOrDefault(sysDict.getDictId() + "all", 0));
				}
				HSSFCell cellFive = rowThree.createCell(j);
				cellFive.setCellStyle(styleCenter);
				cellFive.setCellValue(speInfos.getOrDefault("all", 0));

			}
		}

		// 最下方的统计
		HSSFRow rowFour = sheet.createRow(searchOrgList.size() + 2);
		int j = 0;
		HSSFCell cellOne = rowFour.createCell(j++);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("总计");
		for (SysDict sysDict : sysDictList) {
			if (sysDict.getDictId().equals("50")) {
				continue;
			}
			HSSFCell cellTwo = rowFour.createCell(j++);
			cellTwo.setCellStyle(styleCenter);
			cellTwo.setCellValue(speAll.getOrDefault(sysDict.getDictId() + "doctorTraining", 0));

			HSSFCell cellThree = rowFour.createCell(j++);
			cellThree.setCellStyle(styleCenter);
			cellThree.setCellValue(speAll.getOrDefault(sysDict.getDictId() + "graduate", 0));

			HSSFCell cellFour = rowFour.createCell(j++);
			cellFour.setCellStyle(styleCenter);
			cellFour.setCellValue(speAll.getOrDefault(sysDict.getDictId() + "all", 0));
		}
		HSSFCell cellFive = rowFour.createCell(j);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue(doctorTrainingMap.getOrDefault("all", 0) + graduateMap.getOrDefault("all", 0));

		String fileName = "招录统计报表.xls";
		if (StringUtil.isNotBlank(sessionNumber)) {
			fileName = sessionNumber + "年招录统计报表.xls";
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * @Author xieyh
	 * @Description 导出招录学员历史数据
	 * @Date  2024-05-18
	 **/
	@RequestMapping(value = {"/exportRecruitHistory"})
	public void exportRecruitHistory(String photoTime, String orgFlow, String speId, String docType, HttpServletResponse response) throws IOException {

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
		HSSFRow rowOne = sheet.createRow(0);//第1行
		HSSFRow rowTwo = sheet.createRow(1);//第2行

        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		List<String> titles = new ArrayList<>();
		titles.add("基地名称");
		for (SysDict sysDict : sysDictList) {
			if (sysDict.getDictId().equals("50")) {
				continue;
			}
			titles.add(sysDict.getDictName());
			titles.add("合计");
		}
		titles.add("总计");
		// 设计表头
		HSSFCell cellTitleOne;
		HSSFCell cellTitleTwo;
		HSSFCell cellTitleThree;
		int k = 0;
		for (int i = 0; i < titles.size(); i++) {
			if (i % 2 == 1 && i != titles.size() - 1) {
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, i + k, i + k + 1);
				sheet.addMergedRegion(cellRangeAddress);
				k++;
				cellTitleOne = rowOne.createCell(i + k - 1);
				cellTitleTwo = rowTwo.createCell(i + k - 1);
				cellTitleThree = rowTwo.createCell(i + k);
				cellTitleTwo.setCellValue("住院医师");
				cellTitleTwo.setCellStyle(styleCenter);
				cellTitleThree.setCellValue("在校专硕");
				cellTitleThree.setCellStyle(styleCenter);
			} else {
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, i + k, i + k);
				sheet.addMergedRegion(cellRangeAddress);
				cellTitleOne = rowOne.createCell(i + k);
			}
			cellTitleOne.setCellValue(titles.get(i));
			cellTitleOne.setCellStyle(styleCenter);
		}

		List<String> orgLevels = new ArrayList<>();
        orgLevels.add(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		// 查询基地/医院
		List<SysOrg> orgList = orgBiz.searchSysOrgOrder(orgLevels);
		orgList.sort(Comparator.comparingInt(o -> o.getOrgCode().hashCode()));

		String fileName = "招录统计报表.xls";
		List<SysOrg> searchOrgList = new ArrayList<>();
		if (StringUtil.isNotBlank(orgFlow)) {
			searchOrgList.add(orgBiz.readSysOrg(orgFlow));
			fileName = orgBiz.readSysOrg(orgFlow).getOrgName() + "招录统计报表.xls";
		} else {
			searchOrgList = orgList;
		}
		List<ResRecruitHistory> recruitHistoryList =  recruitDoctorInfoBiz.getRecruitHistoryInfo(photoTime, orgFlow, speId);
		Map<String, Integer> summaryCount = new HashMap<>();
		// 住院医师总人数
		summaryCount.put("doctorTraining", 0);
		// 在校专硕总人数
		summaryCount.put("graduate", 0);
		// 各专业所有学校总计
		Map<String,Integer> speAll = new HashMap<>();
		for (ResRecruitHistory recruitHistory : recruitHistoryList) {
			calculateRecruitStudentHisTory(docType, summaryCount, speAll, recruitHistory);
		}
		for (int i = 0; i < searchOrgList.size(); i++) {
			SysOrg sysOrg = searchOrgList.get(i);
			int j = 0;
			String searchOrgFlow = sysOrg.getOrgFlow() == null ? "" : sysOrg.getOrgFlow();
			String orgName = sysOrg.getOrgName() == null ? "" : sysOrg.getOrgName();
			HSSFRow rowThree = sheet.createRow(i + 2); //第3行
			HSSFCell cellOne = rowThree.createCell(j++);
			cellOne.setCellStyle(styleCenter);
			cellOne.setCellValue(orgName);
			for (SysDict sysDict : sysDictList) {
				if (sysDict.getDictId().equals("50")) {
					continue;
				}
				HSSFCell cellTwo = rowThree.createCell(j++);
				cellTwo.setCellStyle(styleCenter);
				cellTwo.setCellValue(speAll.getOrDefault(searchOrgFlow + sysDict.getDictId() + "doctorTraining", 0));

				HSSFCell cellThree = rowThree.createCell(j++);
				cellThree.setCellStyle(styleCenter);
				cellThree.setCellValue(speAll.getOrDefault(searchOrgFlow + sysDict.getDictId() + "graduate", 0));

				HSSFCell cellFour = rowThree.createCell(j++);
				cellFour.setCellStyle(styleCenter);
				cellFour.setCellValue(speAll.getOrDefault(searchOrgFlow + sysDict.getDictId() + "all", 0));
			}
			HSSFCell cellFive = rowThree.createCell(j);
			cellFive.setCellStyle(styleCenter);
			cellFive.setCellValue(speAll.getOrDefault(searchOrgFlow + "all", 0));
		}

		// 最下方的统计
		HSSFRow rowFour = sheet.createRow(searchOrgList.size() + 2);
		int j = 0;
		HSSFCell cellOne = rowFour.createCell(j++);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("总计");
		for (SysDict sysDict : sysDictList) {
			if (sysDict.getDictId().equals("50")) {
				continue;
			}
			HSSFCell cellTwo = rowFour.createCell(j++);
			cellTwo.setCellStyle(styleCenter);
			cellTwo.setCellValue(speAll.getOrDefault(sysDict.getDictId() + "doctorTraining", 0));

			HSSFCell cellThree = rowFour.createCell(j++);
			cellThree.setCellStyle(styleCenter);
			cellThree.setCellValue(speAll.getOrDefault(sysDict.getDictId() + "graduate", 0));

			HSSFCell cellFour = rowFour.createCell(j++);
			cellFour.setCellStyle(styleCenter);
			cellFour.setCellValue(speAll.getOrDefault(sysDict.getDictId() + "all", 0));
		}
		HSSFCell cellFive = rowFour.createCell(j);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue(summaryCount.getOrDefault("doctorTraining", 0) + summaryCount.getOrDefault("graduate", 0));

		if (StringUtil.isNotBlank(photoTime)) {
			fileName = photoTime + "节点" + fileName;
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * @Author xieyh
	 * @Description 省厅招录统计分析报表
	 * @Date  2024-10-25
	 **/
	@RequestMapping(value = {"/recruitReport"})
	public String recruitReport(Model model) {
		return "jsres/manage/recruitReport";
	}

	/**
	 * @Description 删除选中节点的历史数据
	 * @param photoTime 时间节点
	 * @return 操作是否成功
	 * @Author xieyh
	 * @Date  2024-05-27
	 */
	@RequestMapping(value = {"/removeHistoryReport"})
	@ResponseBody
	public String removeHistoryReport(String photoTime) {
		List<ResRecruitHistory> recruitHistoryList =  recruitDoctorInfoBiz.getRecruitHistoryInfo(photoTime, "", "");
        recruitHistoryList.forEach(e -> e.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N));
		recruitHistoryList.forEach(e -> recruitDoctorInfoBiz.updateRecruitHistory(e));
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * @Description 修改历史数据
	 * @param photoTime 时间节点
	 * @param orgFlow 医院标识
	 * @param speId 专业标识
	 * @param docType 医师类别
	 * @Author xieyh
	 * @Date  2024-05-27
	 */
	@RequestMapping(value = {"/modifyHistoryData"})
	public String modifyHistoryData(String photoTime, String orgFlow, String speId, String docType, Model model) {
		List<ResRecruitHistory> recruitHistoryList =  recruitDoctorInfoBiz.getRecruitHistoryInfo(photoTime, orgFlow, speId);
		model.addAttribute("recruitHistory", recruitHistoryList.get(0));
		model.addAttribute("docType", docType);
		return "jsres/manage/modifyHistoryData";
	}

	/**
	 * @Description 保存修改历史数据，处理逻辑为若为某节点首次修改，则保存的时候对该节点进行整表保存，若非首次修改，则只对当条记录进行修改保存
	 * @param resRecruitHistory 历史数据的实体类
	 * @return 操作是否成功
	 * @Author xieyh
	 * @Date  2024-05-28
	 */
	@RequestMapping(value = {"/saveHistoryReport"})
	@ResponseBody
	public String saveHistoryReport(ResRecruitHistory resRecruitHistory) {
		int socialNumber = Integer.parseInt(resRecruitHistory.getSocialNumber());
		int companyNumber = Integer.parseInt(resRecruitHistory.getCompanyNumber());
		int companyEntrustNumber = Integer.parseInt(resRecruitHistory.getCompanyEntrustNumber());
		int graduateNumber = Integer.parseInt(resRecruitHistory.getGraduateNumber());
		int recruitNumber = socialNumber + companyNumber + companyEntrustNumber + graduateNumber;
		String photoTime = resRecruitHistory.getPhotoTime();
		if (photoTime.contains("（修改）")) { // 非首次修改
			resRecruitHistory.setRecruitNumber(String.valueOf(recruitNumber));
			GeneralMethod.setRecordInfo(resRecruitHistory, false);
			recruitDoctorInfoBiz.updateRecruitHistory(resRecruitHistory);
		} else {
			String modifyPhotoTime = photoTime + "（修改）";
			List<ResRecruitHistory> historyList = recruitDoctorInfoBiz.getRecruitHistoryInfo(modifyPhotoTime, resRecruitHistory.getOrgFlow(), resRecruitHistory.getSpeId());
			if (CollectionUtils.isNotEmpty(historyList)) { // 非首次修改
				ResRecruitHistory recruitHistory = historyList.get(0);
				recruitHistory.setSocialNumber(resRecruitHistory.getSocialNumber());
				recruitHistory.setCompanyNumber(resRecruitHistory.getCompanyNumber());
				recruitHistory.setCompanyEntrustNumber(resRecruitHistory.getCompanyEntrustNumber());
				recruitHistory.setGraduateNumber(resRecruitHistory.getGraduateNumber());
				recruitHistory.setRecruitNumber(String.valueOf(recruitNumber));
				recruitHistory.setRemarks(resRecruitHistory.getRemarks());
				GeneralMethod.setRecordInfo(recruitHistory, false);
				recruitDoctorInfoBiz.updateRecruitHistory(recruitHistory);
			} else { // 首次修改
				List<ResRecruitHistory> recruitHistoryList = recruitDoctorInfoBiz.getRecruitHistoryInfo(photoTime, "", "");
				for (ResRecruitHistory recruitHistory : recruitHistoryList) {
					if (recruitHistory.getOrgFlow().equals(resRecruitHistory.getOrgFlow()) && recruitHistory.getSpeId().equals(resRecruitHistory.getSpeId())) {
						recruitHistory.setSocialNumber(resRecruitHistory.getSocialNumber());
						recruitHistory.setCompanyNumber(resRecruitHistory.getCompanyNumber());
						recruitHistory.setCompanyEntrustNumber(resRecruitHistory.getCompanyEntrustNumber());
						recruitHistory.setGraduateNumber(resRecruitHistory.getGraduateNumber());
						recruitHistory.setRecruitNumber(String.valueOf(recruitNumber));
						recruitHistory.setRemarks(resRecruitHistory.getRemarks());
					}
					recruitHistory.setHistoryFlow(PkUtil.getUUID());
					recruitHistory.setPhotoTime(modifyPhotoTime);
					GeneralMethod.setRecordInfo(recruitHistory, true);
					recruitDoctorInfoBiz.saveRecruitHistory(recruitHistory);
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	@RequestMapping(value = {"/exportRecruitStatisticsAcc"})
	public void exportRecruitStatisticsAcc(String sessionNumber,String statusId,String datas[], HttpServletResponse response) throws IOException {
		Map<String,Object> params = new HashMap<>();
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s0:datas){
				docTypeList.add(s0);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		params.put("docTypeList",docTypeList);
		params.put("statusId",statusId);
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
		HSSFRow rowThree = sheet.createRow(0);//第一行

		String[] titles = new String[]{
				"基地名称",
				"助理全科"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, 8000);
		}

		List<String> orgLevels = new ArrayList<>();
        orgLevels.add(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
		// 查询基地/医院
		List<SysOrg> orgList = orgBiz.searchSysOrgOrder(orgLevels);
		int titlesLen = titles.length;
		if (orgList != null && orgList.size() > 0) {
			// 查询专业
			int countSum = 0;
			for (int i = 0; i < orgList.size(); i++) {// 34 33
				SysOrg sysOrg = orgList.get(i);
				int j = 0;
				String orgFlow = sysOrg.getOrgFlow() == null ? "" : sysOrg.getOrgFlow();
				String orgName = sysOrg.getOrgName() == null ? "" : sysOrg.getOrgName();
				HSSFRow rowFour = sheet.createRow(i + 1); //第二行
				HSSFCell cellFirst = rowFour.createCell(j++);
				cellFirst.setCellStyle(styleCenter);
				cellFirst.setCellValue(orgName);
				params.put("orgFlow",orgFlow);
				params.put("sessionNumber",sessionNumber);
				List<Map<String,Object>> infos = recruitDoctorInfoBiz.getRecruitSpeInfoNew(params);
				if(infos != null && infos.size() > 0){
					for (Map map: infos) {
						String count = (String)map.get("count");
						String speId = (String)map.get("speId");
						HSSFCell cellFirst1 = rowFour.createCell(j++);
						cellFirst1.setCellStyle(styleCenter);
						cellFirst1.setCellValue(count);
						int countSon = Integer.parseInt(count);
						countSum += countSon;
					}
				}
			}
			HSSFRow rowFour = sheet.createRow(orgList.size() + 1);
			String[] titles1 = new String[]{
					"合计",
					String.valueOf(countSum)
			};
			HSSFCell cellTitle2 = null;
			for (int i = 0; i < titles1.length; i++) {
				cellTitle2 = rowFour.createCell(i);
				cellTitle2.setCellValue(titles1[i]);
				cellTitle2.setCellStyle(styleCenter);
				sheet.setColumnWidth(i, 8000);
			}
		}

		String fileName = "招录统计报表.xls";
		if (StringUtil.isNotBlank(sessionNumber)) {
			fileName = sessionNumber + "年招录统计报表.xls";
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}


	/**
	 * 省厅招录统计
	 *
	 * @param sessionNumber
	 * @return
	 */
	@RequestMapping(value = {"/exportZlxytj"})
	public void exportZlxytj(String sessionNumber, HttpServletResponse response, Model model) throws IOException {

		List<Map<String, String>> citys = new ArrayList<>();
		Map<String, String> city = new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		List<Map<String, Object>> list = jsResDoctorRecruitBiz.zlxytj(sessionNumber);
		Map<String, List<String>> citySessionMap = new HashMap<>();
		Map<String, Object> citySessionNumMap = new HashMap<>();
		Map<String, Integer> cityNumMap = new HashMap<>();
		Map<String, Integer> typeNumMap = new HashMap<>();
		if (list != null) {
			for (Map<String, Object> map : list) {
				//每个城市有多个届别
				String cityId = (String) map.get("cityId");
				String sessionNumber2 = (String) map.get("sessionNumber");
				String typeId = (String) map.get("typeId");
				List<String> citySessionNumbers = citySessionMap.get(cityId);
				if (citySessionNumbers == null)
					citySessionNumbers = new ArrayList<>();
				if (!citySessionNumbers.contains(sessionNumber2)) {
					citySessionNumbers.add(sessionNumber2);
				}
				citySessionMap.put(cityId, citySessionNumbers);

				citySessionNumMap.put(cityId + sessionNumber2 + typeId, map.get("num"));

				Integer sum = cityNumMap.get(cityId);
				if (sum == null)
					sum = 0;
				sum += (Integer) map.get("num");
				cityNumMap.put(cityId, sum);

				Integer sum2 = typeNumMap.get(typeId);
				if (sum2 == null)
					sum2 = 0;
				sum2 += (Integer) map.get("num");
				typeNumMap.put(typeId, sum2);

			}
		}

		String[] titles = new String[]{
				"地市名称", "年份", "单位人", "委培单位人", "行业人", "在校专硕", "小计", "合计",
		};
		String fileName = "住院医师招录统计表.xls";
		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream os = response.getOutputStream();
		Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		Sheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		//HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);
		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//

		Map<Integer, Integer> columnWidth = new HashMap<>();
		Row row = sheet.createRow(0);
		Cell cell = null;
		for (int i = 0; i < titles.length; i++) {
			String title = titles[i];
			cell = row.createCell(i);
			cell.setCellValue(title);
			cell.setCellStyle(style);
			//宽度自适应
			int nl = title.toString().getBytes().length;
			if (columnWidth.containsKey(i)) {
				Integer ol = columnWidth.get(i);
				if (ol < nl)
					columnWidth.put(i, nl);
			} else {
				columnWidth.put(i, nl);
			}
		}
		if (citys != null) {
			Cell rowCell = null;
			int rowNum = 1;
			int xjsum = 0;
			int hjsum = 0;
			for (int i = 0; i < citys.size(); i++) {
				Map<String, String> item = citys.get(i);
				String cityName = item.get("cityName");
				String cityId = item.get("cityId");
				List<String> sessionNumbers = citySessionMap.get(cityId);
				if (sessionNumbers != null && sessionNumbers.size() > 0) {
					for (int j = 0; j < sessionNumbers.size(); j++) {

						row = sheet.createRow(rowNum++);
						String sn = sessionNumbers.get(j);
						if (j == 0) {
							rowCell = row.createCell(0);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(cityName);
							//宽度自适应
							setColumnWidth(cityName.toString().getBytes().length, 0, columnWidth);
							sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, sessionNumbers.size() - 1 + rowNum - 1, 0, 0));
						}
						rowCell = row.createCell(1);
						rowCell.setCellStyle(styleTwo);
						rowCell.setCellValue(sn);
						//宽度自适应
						setColumnWidth(sn.toString().getBytes().length, 1, columnWidth);
						int k = 2;
						int sum = 0;
                        for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
							String key = cityId + sn + e.getId();
							Integer num = (Integer) citySessionNumMap.get(key);
							if (num == null)
								num = 0;
							sum += num;
							rowCell = row.createCell(k);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(String.valueOf(num));
							//宽度自适应
							setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
							k++;
						}
						rowCell = row.createCell(k);
						rowCell.setCellStyle(styleTwo);
						rowCell.setCellValue(String.valueOf(sum));
						xjsum += sum;
						//宽度自适应
						setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
						if (j == 0) {
							k++;
							rowCell = row.createCell(k);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(String.valueOf(cityNumMap.get(cityId)));
							hjsum += cityNumMap.get(cityId);
							//宽度自适应
							setColumnWidth(String.valueOf(cityNumMap.get(cityId)).toString().getBytes().length, k, columnWidth);
							sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, sessionNumbers.size() - 1 + rowNum - 1, k, k));
						}
					}
				}
			}

			row = sheet.createRow(rowNum++);
			int k = 0;
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue("合计");
			//宽度自适应
			setColumnWidth("合计".toString().getBytes().length, k++, columnWidth);

			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue(" ");
			//宽度自适应
			setColumnWidth(" ".getBytes().length, k++, columnWidth);
			int sum = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				Integer num = (Integer) typeNumMap.get(e.getId());
				if (num == null)
					num = 0;
				sum += num;
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
				rowCell.setCellValue(String.valueOf(num));
				//宽度自适应
				setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
				k++;
			}
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue(String.valueOf(xjsum));
			//宽度自适应
			setColumnWidth(String.valueOf(xjsum).toString().getBytes().length, k, columnWidth);
			k++;
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue(String.valueOf(hjsum));
			//宽度自适应
			setColumnWidth(String.valueOf(cityNumMap.get(hjsum)).toString().getBytes().length, k, columnWidth);
			Set<Integer> keys = columnWidth.keySet();
			for (Integer key : keys) {
				int width = columnWidth.get(key);
				sheet.setColumnWidth(key, width * 2 * 256);
			}
		}
		wb.write(os);
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	private void setColumnWidth(int length, int key, Map<Integer, Integer> columnWidth) {
		if (columnWidth.containsKey(key)) {
			Integer ol = columnWidth.get(key);
			if (ol < length)
				columnWidth.put(key, length);
		} else {
			columnWidth.put(key, length);
		}
	}

	@RequestMapping(value = {"/exportZlxytj2"})
	public void exportZlxytj2(String sessionNumber, String orgCityId, String joint, String trainTypeId, String trainingSpeId, String datas[], HttpServletResponse response, Model model) throws IOException {
		List<Map<String, String>> citys = new ArrayList<>();
		Map<String, String> city = new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);

		List<String> docTypeList = new ArrayList<String>();
		ResDoctor doctor = new ResDoctor();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		Map<String, Object> param = new HashMap<>();
		param.put("docTypeList", docTypeList);
		param.put("orgCityId", orgCityId);
		param.put("joint", joint);
		param.put("sessionNumber", sessionNumber);
		param.put("trainTypeId", trainTypeId);
		param.put("trainingSpeId", trainingSpeId);
		List<SysOrg> orgs = orgBiz.queryAllSysOrg(new SysOrg());
		Map<String, String> orgNameMap = new HashMap<>();
		Map<String, List<String>> cityOrgMap = new HashMap<>();
		Map<Object, Object> orgSpeFlagMap = new HashMap<Object, Object>();//基地专业标志的的map
		Map<String, List<String>> jointOrgMap = new HashMap<>();
		if (orgs != null) {
			for (SysOrg o : orgs) {
				orgNameMap.put(o.getOrgFlow(), o.getOrgName());
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(o.getOrgLevelId())) {
					List<String> orgFlows = cityOrgMap.get(o.getOrgCityId());
					if (orgFlows == null)
						orgFlows = new ArrayList<>();
					if (!orgFlows.contains(o.getOrgFlow())) {
						orgFlows.add(o.getOrgFlow());
					}
					cityOrgMap.put(o.getOrgCityId(), orgFlows);

                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(joint)) {
						List<String> jointOrgFlowList = new ArrayList<>();
						List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(o.getOrgFlow());//查询每家基地的协同基地
						if (jointOrgList != null && !jointOrgList.isEmpty()) {
							for (SysOrg resJointOrg : jointOrgList) {
								if (!jointOrgFlowList.contains(resJointOrg.getOrgFlow())) {
									jointOrgFlowList.add(resJointOrg.getOrgFlow());
								}
							}
						}
						jointOrgMap.put(o.getOrgFlow(), jointOrgFlowList);
					}
				}

				ResOrgSpe resOrgSpe = new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList = resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if (orgSpeList != null && !orgSpeList.isEmpty()) {//每家基地的专业
					for (ResOrgSpe r : orgSpeList) {
                        orgSpeFlagMap.put(o.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
			}
		}
		model.addAttribute("orgNameMap", orgNameMap);
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);


		Map<String, Integer> typeNumMap = new HashMap<>();
		List<Map<String, Object>> list = jsResDoctorRecruitBiz.zlxytj2(param);
		Map<String, Object> cityOrgNumMap = new HashMap<>();
		if (list != null) {
			for (Map<String, Object> map : list) {
				//每个城市有多个届别
				String cityId = (String) map.get("cityId");
				String orgFlow = (String) map.get("orgFlow");
				String speId = (String) map.get("speId");
				cityOrgNumMap.put(cityId + orgFlow + speId, map.get("num"));

				Integer sum2 = typeNumMap.get(speId);
				if (sum2 == null)
					sum2 = 0;
				sum2 += (Integer) map.get("num");
				typeNumMap.put(speId, sum2);

                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(joint)) {
					param.put("orgFlow", orgFlow);
					List<Map<String, Object>> list2 = jsResDoctorRecruitBiz.zlxytjJoint(param);
					if (list2 != null) {
						for (Map<String, Object> map2 : list2) {
							//每个城市有多个届别
							String cityId2 = (String) map2.get("cityId");
							String jointOrgFlow = (String) map2.get("jointOrgFlow");
							String speId2 = (String) map2.get("speId");
							cityOrgNumMap.put(cityId2 + jointOrgFlow + speId2, map2.get("num"));
						}
					}
				}
			}
		}
		String fileName = "招录学员统计表.xls";
		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream os = response.getOutputStream();
		Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		Sheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		//HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);

		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//

		List<SysDict> dicts = new ArrayList<>();
		if (StringUtil.isNotBlank(trainTypeId)) {
			dicts = dictBiz.searchDictListByDictTypeIdAndDictId(trainTypeId, trainingSpeId);//每个培训类别对应的专业
			List<String> titleList = new ArrayList<>();
			titleList.add("地市名称");
			titleList.add("基地名称");
			if (dicts != null && dicts.size() > 0) {
				for (SysDict d : dicts) {
					titleList.add(d.getDictName());
				}
			}
			titleList.add("小计");
			Map<Integer, Integer> columnWidth = new HashMap<>();
			Row row = sheet.createRow(0);
			Cell cell = null;
			for (int i = 0; i < titleList.size(); i++) {
				String title = titleList.get(i);
				cell = row.createCell(i);
				cell.setCellValue(title);
				cell.setCellStyle(style);
				//宽度自适应
				int nl = title.toString().getBytes().length;
				if (columnWidth.containsKey(i)) {
					Integer ol = columnWidth.get(i);
					if (ol < nl)
						columnWidth.put(i, nl);
				} else {
					columnWidth.put(i, nl);
				}
			}
			int rowNum = 1;
			if (citys != null) {
				Cell rowCell = null;
				for (int i = 0; i < citys.size(); i++) {
					Map<String, String> item = citys.get(i);
					String cityName = item.get("cityName");
					String cityId = item.get("cityId");
					List<String> orgFlows = cityOrgMap.get(cityId);
					if (orgFlows != null && orgFlows.size() > 0) {
						int rowspan = orgFlows.size();
						for (int n = 0; n < orgFlows.size(); n++) {
							List<String> jointOrgFlows = jointOrgMap.get(orgFlows.get(n));
							if (jointOrgFlows != null)
								rowspan += jointOrgFlows.size();
						}
						for (int j = 0; j < orgFlows.size(); j++) {
							row = sheet.createRow(rowNum++);
							String orgFlow = orgFlows.get(j);
							String orgName = orgNameMap.get(orgFlow);
							if (j == 0) {
								rowCell = row.createCell(0);
								rowCell.setCellStyle(styleTwo);
								rowCell.setCellValue(cityName);
								//宽度自适应
								setColumnWidth(cityName.toString().getBytes().length, 0, columnWidth);
								sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowspan - 1 + rowNum - 1, 0, 0));
							}
							rowCell = row.createCell(1);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(orgName);
							//宽度自适应
							setColumnWidth(orgName.toString().getBytes().length, 1, columnWidth);
							int k = 2;
							int sum = 0;
							for (SysDict e : dicts) {
								String key = cityId + orgFlow + e.getDictId();
								String flow = orgFlow + trainTypeId + e.getDictId();
								String result = "--";
                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgSpeFlagMap.get(flow))) {
									Integer num = (Integer) cityOrgNumMap.get(key);
									if (num == null)
										num = 0;
									sum += num;
									result = String.valueOf(num);
								}
								rowCell = row.createCell(k);
								rowCell.setCellStyle(styleTwo);
								rowCell.setCellValue(result);
								//宽度自适应
								setColumnWidth(result.toString().getBytes().length, k, columnWidth);
								k++;
							}
							rowCell = row.createCell(k);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(String.valueOf(sum));
							//宽度自适应
							setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);

							List<String> jointOrgFlows = jointOrgMap.get(orgFlow);
							if (jointOrgFlows != null) {
								for (int m = 0; m < jointOrgFlows.size(); m++) {
									row = sheet.createRow(rowNum++);
									String jointOrgFlow = jointOrgFlows.get(m);
									orgName = orgNameMap.get(jointOrgFlow);

									rowCell = row.createCell(1);
									rowCell.setCellStyle(styleTwo);
									rowCell.setCellValue(orgName);
									//宽度自适应
									setColumnWidth(orgName.toString().getBytes().length, 1, columnWidth);
									int k2 = 2;
									int sum2 = 0;
									for (SysDict e : dicts) {
										String key = cityId + jointOrgFlow + e.getDictId();
										String flow = jointOrgFlow + trainTypeId + e.getDictId();
										String result = "--";
                                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgSpeFlagMap.get(flow))) {
											Integer num = (Integer) cityOrgNumMap.get(key);
											if (num == null)
												num = 0;
											sum2 += num;
											result = String.valueOf(num);
										}
										rowCell = row.createCell(k2);
										rowCell.setCellStyle(styleTwo);
										rowCell.setCellValue(result);
										//宽度自适应
										setColumnWidth(result.toString().getBytes().length, k2, columnWidth);
										k2++;
									}
									rowCell = row.createCell(k2);
									rowCell.setCellStyle(styleTwo);
									rowCell.setCellValue(String.valueOf(sum2));
									//宽度自适应
									setColumnWidth(String.valueOf(sum2).toString().getBytes().length, k2, columnWidth);
								}
							}
						}
					}
				}

				row = sheet.createRow(rowNum++);
				int k = 0;
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
				rowCell.setCellValue("合计(已包含协同)");
				//宽度自适应
				setColumnWidth("合计(已包含协同)".toString().getBytes().length, k++, columnWidth);
				sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 1));
				k++;
				int sum = 0;
				for (SysDict e : dicts) {
					String key = e.getDictId();
					String result = "--";
					Integer num = (Integer) typeNumMap.get(key);
					if (num == null)
						num = 0;
					sum += num;
					result = String.valueOf(num);
					rowCell = row.createCell(k);
					rowCell.setCellStyle(styleTwo);
					rowCell.setCellValue(result);
					//宽度自适应
					setColumnWidth(result.toString().getBytes().length, k, columnWidth);
					k++;
				}
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
				rowCell.setCellValue(String.valueOf(sum));
				//宽度自适应
				setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
			}
			Set<Integer> keys = columnWidth.keySet();
			for (Integer key : keys) {
				int width = columnWidth.get(key);
				sheet.setColumnWidth(key, width * 2 * 200);
			}
		}
		wb.write(os);
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	/**
	 * 加载医师审核列表
	 *
	 * @param resDoctorRecruit
	 * @param sysUser
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recruitListInfo")
	public String recruitListInfo(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, Integer currentPage, HttpServletRequest request,
								  Model model, String datas[]) {
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
			String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				resDoctorRecruit.setSessionNumber("");
			}
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		//判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isJointOrg",isJointOrg);
		resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
//		resDoctorRecruit.setRecruitFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);//同意招录
//		resDoctorRecruit.setConfirmFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);//学员已确认
//		resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> orgFlowList = new ArrayList<>();
		if(StringUtil.isNotBlank(resDoctorRecruit.getOrgFlow())){
			orgFlowList.add(resDoctorRecruit.getOrgFlow());
		}
		List<JsResDoctorRecruitExt> recruitList = new ArrayList<>();
		PageHelper.startPage(currentPage, getPageSize(request));
		recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitExtList1(resDoctorRecruit, sysUser, orgFlowList, docTypeList, sessionNumbers,isJointOrg);
		model.addAttribute("doctorStatusId", resDoctorRecruit.getDoctorStatusId());
		for (JsResDoctorRecruitExt recruitExt : recruitList) {
			if(!"Auditing".equals(recruitExt.getDoctorStatusId())&&!"NotPassed".equals(recruitExt.getDoctorStatusId())
					&&!"OrgAuditing".equals(recruitExt.getDoctorStatusId())&&StringUtil.isNotBlank(recruitExt.getDoctorStatusId())){
				recruitExt.setDoctorStatusName("审核通过");
			}
			if(StringUtil.isNotEmpty(recruitExt.getIsRetrain())){
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitExt.getIsRetrain())) {
					recruitExt.setIsRetrain("是");
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(recruitExt.getIsRetrain())) {
					recruitExt.setIsRetrain("否");
				}
			}
		}

		model.addAttribute("recruitList", recruitList);
		model.addAttribute("currentDate", DateUtil.getCurrDate());
		return "jsres/recruitInfoList";
	}

	/**
	 * 加载医师审核列表
	 *
	 * @param resDoctorRecruit
	 * @param sysUser
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recruitList")
	public String doctorRecruitList(ResDoctorRecruit resDoctorRecruit, SysUser sysUser,String roleFlag, Integer currentPage, HttpServletRequest request,
									Model model, String datas[],String studentType,String joinOrgFlow,String orgFlow,String signupWay,String isArmy) {
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
			String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				resDoctorRecruit.setSessionNumber("");
			}
		}
		SysUser sysuser = GlobalContext.getCurrentUser();
//		if(!com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)&&!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
//			resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
//		}
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
//		List<String> orgFlowList = new ArrayList<>();
//		if(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
//			List<SysOrg> orgs=new ArrayList<>();
//			SysOrg sysorg = new SysOrg();
//			sysorg.setOrgProvId(org.getOrgProvId());
//			sysorg.setOrgCityId(org.getOrgCityId());
//			sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//			sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//			orgs = orgBiz.searchOrg(sysorg);
//			if(orgs != null || orgs.size()>0){
//				for (SysOrg sysOrg : orgs) {
//					orgFlowList.add(sysOrg.getOrgFlow());
//				}
//			}
//		}
		//当前基地是否为协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
			if(StringUtil.isNotBlank(resDoctorRecruit.getJointOrgAudit()) && "OrgAudit".equals(resDoctorRecruit.getJointOrgAudit())){
				resDoctorRecruit.setOrgAudit("Auditing");
				resDoctorRecruit.setJointOrgAudit("Passed");
			}else if("Passed".equals(resDoctorRecruit.getJointOrgAudit())){
				resDoctorRecruit.setOrgAudit("Passed");
				resDoctorRecruit.setJointOrgAudit("Passed");
			}
		}else{
			/*if(StringUtil.isNotBlank(resDoctorRecruit.getOrgAudit()) && !"UnPassed".equals(resDoctorRecruit.getOrgAudit())){
				resDoctorRecruit.setJointOrgAudit("Passed");
			}*/
			if("UnPassed".equals(resDoctorRecruit.getOrgAudit())){
				resDoctorRecruit.setOrgAudit("");
				resDoctorRecruit.setJointOrgAudit("");
				resDoctorRecruit.setAuditStatusId("NotPassed");
			}
		}
		model.addAttribute("isJointOrg",isJointOrg);
		List<String> jointOrgFlowList=new ArrayList<String>();
		if (StringUtil.isBlank(orgFlow)) {
            if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)
                    || com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
				List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							//2021/5/7修改
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
					jointOrgFlowList.add(g.getOrgFlow());
				}
			}
		}else{
			jointOrgFlowList.add(orgFlow);
			//选择主培训基地 直接查询主基地以及协同基地数据 2021/5/7修改
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()) {
				for (ResJointOrg jointOrg : resJointOrgList) {
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}
        resDoctorRecruit.setRecruitFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);//同意招录
        resDoctorRecruit.setConfirmFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);//学员已确认
//		resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		List<String> docTypeList = new ArrayList<String>();

		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		if(StringUtils.isNotBlank(signupWay)){
			resDoctorRecruit.setSignupWay(signupWay);
		}
		PageHelper.startPage(currentPage, getPageSize(request));
//		List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList1(resDoctorRecruit, sysUser, jointOrgFlowList, docTypeList, sessionNumbers);
		List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtNew(resDoctorRecruit, sysUser, jointOrgFlowList, docTypeList, sessionNumbers,joinOrgFlow,isJointOrg,isArmy);
		for (JsResDoctorRecruitExt recruitExt : recruitList) {
			if(StringUtil.isNotEmpty(recruitExt.getIsRetrain())){
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitExt.getIsRetrain())) {
					recruitExt.setIsRetrain("是");
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(recruitExt.getIsRetrain())) {
					recruitExt.setIsRetrain("否");
				}
			}
		}
		model.addAttribute("recruitList", recruitList);
		model.addAttribute("currentDate", DateUtil.getCurrDate());

        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag) || com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			return "jsres/recruit/recruitUnPassedDetail";
		}
		return "jsres/recruitList";
	}

	public List<String> searchJointOrgList(String flag,String Flow) {
		List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(Flow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}
		return jointOrgFlowList;
	}

	/**
	 * 导出excel
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/exportRecruitList")
	public void exportRecruitList(HttpServletResponse response,ResDoctorRecruit resDoctorRecruit,String roleFlag, SysUser sysUser,
								  Integer currentPage, HttpServletRequest request, Model model, String datas[],
								  String studentType,String joinOrgFlow,String orgFlow,String isArmy) throws Exception {
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
			String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				resDoctorRecruit.setSessionNumber("");
			}
		}
		SysUser sysuser = GlobalContext.getCurrentUser();
//		if(!com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)&&!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
//			resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
//		}
		SysOrg sysOrg = orgBiz.readSysOrg(sysuser.getOrgFlow());
//		List<String> orgFlowList = new ArrayList<>();
//		if(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
//			List<SysOrg> orgs=new ArrayList<>();
//			SysOrg sysorg = new SysOrg();
//			sysorg.setOrgProvId(org.getOrgProvId());
//			sysorg.setOrgCityId(org.getOrgCityId());
//			sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//			sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//			orgs = orgBiz.searchOrg(sysorg);
//			if(orgs != null || orgs.size()>0){
//				for (SysOrg sysOrg : orgs) {
//					orgFlowList.add(sysOrg.getOrgFlow());
//				}
//			}
//		}

		List<String> jointOrgFlowList=new ArrayList<String>();
		if (StringUtil.isBlank(orgFlow)) {
            if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)
                    || com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
				List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							//2021/5/7修改
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
					jointOrgFlowList.add(g.getOrgFlow());
				}
			}
		}else{
			jointOrgFlowList.add(orgFlow);
			//选择主培训基地 直接查询主基地以及协同基地数据 2021/5/7修改
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()) {
				for (ResJointOrg jointOrg : resJointOrgList) {
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}

        resDoctorRecruit.setRecruitFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);//同意招录
        resDoctorRecruit.setConfirmFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);//学员已确认
		List<String> docTypeList = new ArrayList<String>();
		if(StringUtil.isNotBlank(studentType)){
			docTypeList.add(studentType);
		}
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		//当前基地是否为协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
//		List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtList1(resDoctorRecruit, sysUser, null, docTypeList, sessionNumbers);
		List<JsResDoctorRecruitExt> recruitList = jsResDoctorRecruitBiz.resDoctorRecruitExtNew2(resDoctorRecruit, sysUser, jointOrgFlowList, docTypeList, sessionNumbers,joinOrgFlow,isJointOrg,isArmy);

		// 增加几个导出字段 性别/年龄/ 地址/工作单位 /毕业院校/毕业时间
		// /毕业院校/毕业时间 根据最高学历，从PubUserResume里取
		List<String> userFlowList = new ArrayList<>();
        List<SysDict> sendSchoolList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
		Map<String, String> sendSchoolIdToNameMap = sendSchoolList.stream().collect(Collectors.toMap(vo -> vo.getDictId(), vo -> vo.getDictName(), (vo1, vo2) -> vo1));

		recruitList.forEach(vo -> {
			if(",硕士研究生,博士研究生,本科,专科,".contains("," + vo.getSysUser().getEducationName() + ",")) {
				userFlowList.add(vo.getSysUser().getUserFlow());
			}
			// 在校专硕
            if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(vo.getResDoctor().getDoctorTypeId())) {
				// 派送学校
				vo.setWorkSchoolName(sendSchoolIdToNameMap.get(vo.getResDoctor().getWorkOrgId()));
			}
		});

		List<PubUserResume> pubUserResumeList = userResumeBiz.findPubUserResumeByUserFlows(userFlowList);
		Map<String, UserResumeExtInfoForm> userStudyMap = new HashMap<>();
		if (pubUserResumeList != null) {
			for (PubUserResume pubUserResume : pubUserResumeList) {
				String xmlContent = pubUserResume.getUserResume();
				if (StringUtil.isNotBlank(xmlContent)) {
					//xml转换成JavaBean
					UserResumeExtInfoForm userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
					userStudyMap.put(pubUserResume.getUserFlow() ,userResumeExt);
				}
			}
		}

		for (JsResDoctorRecruitExt recruitExt : recruitList) {
			// 性别/年龄从身份证里获取
			String idNo = recruitExt.getSysUser().getIdNo();
			String sex = "";
			String age = "";
			int birthYear = 0;
			if (StringUtils.isNotEmpty(idNo)) {
				if(idNo.length() != 18){
					SysUser user = sysUserMapper.selectByPrimaryKey(recruitExt.getSysUser().getUserFlow());
					sex = user.getSexName();
					String birthday = user.getUserBirthday();
					birthYear = Integer.valueOf(birthday.split("-")[0]);
				}else{
					// 第17位表性别，奇数为男，偶数为女
					int sexInt = Integer.parseInt(idNo.substring(16, 17));
					if (sexInt % 2 == 0) {
						sex = "女";
					} else {
						sex = "男";
					}

					birthYear = Integer.parseInt(idNo.substring(6, 10));
				}



				int currYear = Integer.parseInt(DateUtil.getYear());
				age = String.valueOf(currYear - birthYear);
			}
			recruitExt.setSex(sex);
			recruitExt.setAge(age);

			String graduateSchool = "";
			String graduateTime = "";
			UserResumeExtInfoForm userResumeExtInfoForm = userStudyMap.get(recruitExt.getSysUser().getUserFlow());
			if(null != userResumeExtInfoForm) {
				switch (recruitExt.getSysUser().getEducationName()) {
					case "专科":
						graduateSchool = userResumeExtInfoForm.getJuniorCollegeSchoolName();
						graduateTime = userResumeExtInfoForm.getJuniorCollegeGradate();
						break;
					case "本科":
						graduateSchool = userResumeExtInfoForm.getGraduatedName();
						graduateTime = userResumeExtInfoForm.getGraduationTime();
						break;
					case "硕士研究生":
						graduateSchool = userResumeExtInfoForm.getMasterGraSchoolName();
						graduateTime = userResumeExtInfoForm.getMasterGraTime();
						break;
					case "博士研究生":
						graduateSchool = userResumeExtInfoForm.getDoctorGraSchoolName();
						graduateTime = userResumeExtInfoForm.getDoctorGraTime();
						break;
					default:
				}

				// 工作单位
				recruitExt.setWorkAddr(userResumeExtInfoForm.getWorkUnit());
			}
			// /毕业院校/毕业时间
			recruitExt.setGraduateSchool(graduateSchool);
			recruitExt.setGraduateTime(graduateTime);
		}

		jsResDoctorRecruitBiz.exportRecruitList(recruitList,response);
	}

	/**
	 * 获取单条医师审核信息
	 *
	 * @param role
	 * @param doctorFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/{role}/doctor/auditRecruit"})
	public String getRecruitAudit(@PathVariable String role, String recruitFlow, String doctorFlow, Model model,String doctorSignupFlag) {
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitsList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		model.addAttribute("recruitsList", recruitsList);

		ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
		docotrBackTeturn.setDoctorFlow(doctorFlow);
        docotrBackTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
		List<ResDocotrDelayTeturn> backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn, null, null, null);
		model.addAttribute("backList", backList);
		model.addAttribute("doctorSignupFlag", doctorSignupFlag);
		return "jsres/province/doctor/auditRecruit";
	}

	@RequestMapping(value="/allAuditRecruit")
	public String auditList(Model model, String orgCityId, String orgFlow, String trainingTypeId,
							String trainingSpeId, String sessionNumber, String idNo,
							String userName,String data,String doctorSignupFlag){
		model.addAttribute("orgCityId", orgCityId);
		model.addAttribute("orgFlow", orgFlow);
		model.addAttribute("trainingTypeId", trainingTypeId);
		model.addAttribute("idNo", idNo);
		model.addAttribute("trainingSpeId", trainingSpeId);
		model.addAttribute("sessionNumber", sessionNumber);
		model.addAttribute("userName", userName);
		data=data.substring(1,data.length());
		model.addAttribute("data", data);
		model.addAttribute("doctorSignupFlag", doctorSignupFlag);
		return "/jsres/doctor/AllTrainInfo";
	}

	@RequestMapping(value = {"allSaveGlobalAuditRecruit"})
	@ResponseBody
	public String allSaveGlobalAuditRecruit(String orgCityId, String orgFlow, String trainingTypeId,
											String trainingSpeId, String sessionNumber, String idNo,
											String userName,String data,String doctorSignupFlag,
											String auditStatusId,String notice) {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());

		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}

		List<String> docTypeList=new ArrayList<String>();
		if (StringUtil.isNotBlank(data)){
			String[] datas=data.split(",");
			if(datas!=null&&datas.length>0){
				docTypeList=Arrays.asList(datas);
			}
		}

		Map<String,Object> param=new HashMap<>();
		SysOrg org = new SysOrg();
		org.setOrgProvId(sysOrg.getOrgProvId());
		if(StringUtil.isNotBlank(orgCityId)) {
			org.setOrgCityId(orgCityId);
		}

        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("sysOrg",org);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("idNo",idNo);
		param.put("userName",userName);
		param.put("docTypeList",docTypeList);
		param.put("sessionNumbers",sessionNumbers);
		param.put("doctorSignupFlag",doctorSignupFlag);//报名方式
		param.put("orgFlow",orgFlow);
		SysCfg sysCfg = sysCfgMapper.selectByPrimaryKey("jsres_is_train");

		if (null !=sysCfg && StringUtil.isNotBlank(sysCfg.getCfgValue())){	//查看省厅是否开启招录
			String cfgValue = sysCfg.getCfgValue();
            if (cfgValue.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {        //省厅开启招录（关闭时无数据：院级招录流程优化 ）
				List<JsResDoctorRecruitExt> recruitList = recruitDoctorInfoBiz.searchRecruitExtList(param);
				if (null!=recruitList && recruitList.size()>0){
					int result=0;
					for (JsResDoctorRecruitExt ext : recruitList) {
						ResDoctorRecruitWithBLOBs recruitWithBLOBs = jsResDoctorRecruitBiz.readRecruit(ext.getRecruitFlow());
						recruitWithBLOBs.setAuditStatusId(auditStatusId);
						recruitWithBLOBs.setGlobalNotice(notice);
						if ("Passed".equals(recruitWithBLOBs.getAuditStatusId())) {
							recruitWithBLOBs.setAuditStatusId("Passed");
						} else if ("NotPassed".equals(recruitWithBLOBs.getAuditStatusId())) {
							recruitWithBLOBs.setAuditStatusId("NotPassed");
							recruitWithBLOBs.setOrgAudit("UnPassed");
							recruitWithBLOBs.setJointOrgAudit("UnPassed");
						}
						result = jsResDoctorRecruitBiz.saveAuditRecruit(recruitWithBLOBs)+result;
					}
					if (result==recruitList.size()){
                        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
					}
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 获取单条医师审核信息
	 *
	 * @param role
	 * @param doctorFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/{role}/doctor/auditRecruitSignup"})
	public String getRecruitSignupAudit(@PathVariable String role, String recruitFlow, String doctorFlow, Model model) {
		model.addAttribute("doctorFlow",doctorFlow);
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitsList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		model.addAttribute("recruitsList", recruitsList);
		ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
		docotrBackTeturn.setDoctorFlow(doctorFlow);
        docotrBackTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
		List<ResDocotrDelayTeturn>  backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn,null,null,null);
		model.addAttribute("resRecList", backList);
		return "jsres/province/doctor/auditRecruitSignup";
	}
	/**
	 * 保存医师信息审核
	 *
	 * @param role
	 * @param recruitWithBLOBs
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/{role}/doctor/saveAuditRecruit"})
	@ResponseBody
	public String saveAuditRecruit(@PathVariable String role, ResDoctorRecruitWithBLOBs recruitWithBLOBs, Model model) {
		if (StringUtil.isNotBlank(recruitWithBLOBs.getRecruitFlow()) && StringUtil.isNotBlank(recruitWithBLOBs.getAuditStatusId()) && StringUtil.isNotBlank(recruitWithBLOBs.getDoctorFlow())) {
			SysUser user = GlobalContext.getCurrentUser();
			ResDoctorRecruitWithBLOBs recruit = jsResDoctorRecruitBiz.readRecruit(recruitWithBLOBs.getRecruitFlow());
			//住院医师需要协同基地审核后主基地审核，助理全科只需要选择的基地审核
			//2022年新增 省厅审核功能
			if("DoctorTrainingSpe".equals(recruit.getCatSpeId())) {
				List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
				if (!tempJoinOrgs.isEmpty() && tempJoinOrgs.size() > 0) {//是协同基地
					if ("Passed".equals(recruitWithBLOBs.getAuditStatusId())) {
						recruitWithBLOBs.setAuditStatusId("Auditing");
						recruitWithBLOBs.setJointOrgAudit("Passed");
					} else if ("NotPassed".equals(recruitWithBLOBs.getAuditStatusId())) {
						recruitWithBLOBs.setAuditStatusId("NotPassed");
						recruitWithBLOBs.setJointOrgAudit("UnPassed");
						recruitWithBLOBs.setOrgAudit("UnPassed");
						recruitWithBLOBs.setRecruitFlag("");
						recruitWithBLOBs.setConfirmFlag("");
					}
				} else {
					if ("Passed".equals(recruitWithBLOBs.getAuditStatusId())) {
//						recruitWithBLOBs.setAuditStatusId("Passed");
						// 判断该基地该专业是否已配置轮转方案，未配置则无法审核
						ResOrgRotationCfg rotationCfg = schRotationtBiz.getRotationCfg(recruit.getOrgFlow(), recruit.getSpeId(), recruit.getSessionNumber());
						if (StringUtil.isBlank(rotationCfg.getRotationCfgFlow())) {
                            return com.pinde.core.common.GlobalConstant.HAVE_NO_ROTATION_CFG;
						}

						// 设置学员轮转方案改到此处进行
						SchRotation schRotation = schRotationtBiz.readSchRotation(rotationCfg.getRotationFlow());
						recruitWithBLOBs.setRotationFlow(schRotation.getRotationFlow());
						recruitWithBLOBs.setRotationName(schRotation.getRotationName());

						recruitWithBLOBs.setAuditStatusId("WaitGlobalPass");
						recruitWithBLOBs.setAuditStatusName("待省厅审核");
						recruitWithBLOBs.setOrgAudit("Passed");
					} else if ("NotPassed".equals(recruitWithBLOBs.getAuditStatusId())) {
						//学员报道不通过，学员自行修改相关资料，再次提交，基地进行学员报道审核即可
					/*	recruitWithBLOBs.setRecruitFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
						recruitWithBLOBs.setConfirmFlag("");*/
						recruitWithBLOBs.setAuditStatusId("NotPassed");
						recruitWithBLOBs.setOrgAudit("UnPassed");
						recruitWithBLOBs.setJointOrgAudit("UnPassed");
						recruitWithBLOBs.setRecruitFlag("");
						recruitWithBLOBs.setConfirmFlag("");
					}
				}
			}else{
				if ("Passed".equals(recruitWithBLOBs.getAuditStatusId())) {
//					recruitWithBLOBs.setAuditStatusId("Passed");
					recruitWithBLOBs.setAuditStatusId("WaitGlobalPass");
					recruitWithBLOBs.setAuditStatusName("待省厅审核");
					recruitWithBLOBs.setJointOrgAudit("Passed");
					recruitWithBLOBs.setOrgAudit("Passed");
				} else if ("NotPassed".equals(recruitWithBLOBs.getAuditStatusId())) {
					recruitWithBLOBs.setAuditStatusId("NotPassed");
					/*recruitWithBLOBs.setOrgAudit("UnPassed");
					recruitWithBLOBs.setJointOrgAudit("UnPassed");*/
					recruitWithBLOBs.setConfirmFlag("");
				}
			}
			int result = jsResDoctorRecruitBiz.saveAuditRecruit(recruitWithBLOBs);
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value = {"saveGlobalAuditRecruit"})
	@ResponseBody
	public String saveGlobalAuditRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, Model model) {
		if (StringUtil.isNotBlank(recruitWithBLOBs.getRecruitFlow()) && StringUtil.isNotBlank(recruitWithBLOBs.getAuditStatusId()) && StringUtil.isNotBlank(recruitWithBLOBs.getDoctorFlow())) {
			SysUser user = GlobalContext.getCurrentUser();
			ResDoctorRecruitWithBLOBs recruit = jsResDoctorRecruitBiz.readRecruit(recruitWithBLOBs.getRecruitFlow());
			if ("Passed".equals(recruitWithBLOBs.getAuditStatusId())) {
				recruitWithBLOBs.setAuditStatusId("Passed");
			} else if ("NotPassed".equals(recruitWithBLOBs.getAuditStatusId())) {
				recruitWithBLOBs.setAuditStatusId("NotPassed");
				recruitWithBLOBs.setOrgAudit("UnPassed");
				recruitWithBLOBs.setJointOrgAudit("UnPassed");
			}
			int result = jsResDoctorRecruitBiz.saveAuditRecruit(recruitWithBLOBs);
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	//***********************************  培训变更管理   *******************************************
	@RequestMapping(value = {"/changeBaseMain"})
	public String changeBaseMain() {
		return "jsres/hospital/doctor/changeBaseMain";
	}

	@RequestMapping(value = {"/changeBaseMainAcc"})
	public String changeBaseMainAcc() {
		return "jsres/hospital/doctor/changeBaseMainAcc";
	}

	@RequestMapping(value = {"/speMain"})
	public String speMain() {
		return "jsres/hospital/speMain";
	}

	@RequestMapping(value = {"/speMainAcc"})
	public String speMainAcc() {
		return "jsres/hospital/speMainAcc";
	}

	/**
	 * 专业变更查询
	 *
	 * @param currentPage
	 * @param doctor
	 * @param request
	 * @param model
	 * @param passFlag
	 * @return
	 */
	@RequestMapping(value = {"/searchChangeSpe"})
	public String searchChangeSpe(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String passFlag,
								  String orgFlow0, String datas[]) {
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			SysUser sysUser = GlobalContext.getCurrentUser();
			orgHistory.setOrgFlow(sysUser.getOrgFlow());
			if (StringUtil.isNotBlank(passFlag)) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(passFlag)) {
                    changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
				} else {
                    changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
				}
			} else {
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseAuditUnPass.getId());
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			}
		}
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
				org.setOrgCityId(s.getOrgCityId());
			}
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//			orgs = orgBiz.searchAllSysOrg(org);
			orgs=orgBiz.searchOrgListNew(org);
			model.addAttribute("orgs", orgs);
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
				if (orgs != null && !orgs.isEmpty()) {
					for (SysOrg sysOrg : orgs) {
						orgFlowList.add(sysOrg.getOrgFlow());
					}
				}
			}
			if (StringUtil.isNotBlank(passFlag)) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(passFlag)) {
                    changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
				} else {
                    changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
				}
			} else {
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
			}
		}
		List<SysOrg> orgList = new ArrayList<>();
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		orgList.add(0, org);
//		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
//			model.addAttribute("countryOrgFlag",com.pinde.core.common.GlobalConstant.FLAG_Y);
//			if(null != jointOrg && jointOrg.equals("checked")){
//				orgFlowList.add(orgHistory.getOrgFlow());
//				orgHistory.setOrgFlow("");
//				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
//				if(null != jointOrgs && jointOrgs.size() > 0){
//					for(ResJointOrg so : jointOrgs){
//						orgFlowList.add(so.getJointOrgFlow());
//					}
//				}
//			}
//		}
		if (StringUtil.isBlank(orgFlow0)) {
			orgFlow0 = org.getOrgFlow();
		} else if ("all".equals(orgFlow0)) {
			orgFlowList.add(orgHistory.getOrgFlow());
			orgHistory.setOrgFlow("");
			if (orgList != null && orgList.size() > 0) {
				for (SysOrg so : orgList) {
					orgFlowList.add(so.getOrgFlow());
				}
			}
		} else {
			orgHistory.setOrgFlow(orgFlow0);
		}
		model.addAttribute("orgFlow", orgFlow0);
		model.addAttribute("orgList", orgList);
		PageHelper.startPage(currentPage, getPageSize(request));
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
			if (StringUtil.isNotBlank(doctor.getOrgFlow())) {
				orgFlowList.add(doctor.getOrgFlow());
			}
			doctor.setOrgFlow("");
		}
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1(orgHistory, changeStatusIdList, doctor, orgFlowList, docTypeList, sessionNumbers);
		model.addAttribute("historyExts", historyExts);
		return "jsres/changeSpeMain";
	}

	@RequestMapping(value = {"/searchChangeSpeNew"})
	public String searchChangeSpeNew(Model model) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		//查询本基地的协同基地
		List<SysOrg> orgList = new ArrayList<>();
		SysOrg doctorOrg = new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		orgList.add(0, doctorOrg);
		model.addAttribute("orgList", orgList);
		return "jsres/searchSpeMainNew";
	}

	@RequestMapping(value = {"/searchChangeSpeNewAcc"})
	public String searchChangeSpeNewAcc(Model model) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		//查询本基地的协同基地
		List<SysOrg> orgList = new ArrayList<>();
		SysOrg doctorOrg = new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		orgList.add(0, doctorOrg);
		model.addAttribute("orgList", orgList);
		return "jsres/searchSpeMainNewAcc";
	}

	@RequestMapping(value = {"/searchChangeSpeList"})
	public String searchChangeSpeList(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String passFlag,
									  String orgFlow0, String datas[],String jointOrgFlag,String cityId) {
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			if (StringUtil.isNotBlank(cityId)) {
				org.setOrgCityId(s.getOrgCityId());
			}
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchOrgListNew(org);
			model.addAttribute("orgs", orgs);
			if(StringUtil.isBlank(doctor.getOrgFlow())) {
				for (SysOrg tempOrg : orgs) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
						List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
						if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
							for (ResJointOrg jointOrg : resJointOrgList) {
								orgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
					}
					orgFlowList.add(tempOrg.getOrgFlow());
				}
			}else{
				orgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}

//			if (StringUtil.isNotBlank(passFlag)) {
//				if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(passFlag)) {
//					changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
//				} else {
//					changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
//				}
//			} else {
//				changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
//				changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
//			}
		}else{
			//查询本基地的协同基地
			List<SysOrg> orgList = new ArrayList<>();
			SysOrg doctorOrg = new SysOrg();
			doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("JointOrgCount", orgList.size());
			orgList.add(0, doctorOrg);
			if (StringUtil.isBlank(orgFlow0)) {
//			orgFlowList.add(doctorOrg.getOrgFlow());
				orgFlow0 = doctorOrg.getOrgFlow();
			} else if ("all".equals(orgFlow0)) {
				if (orgList != null && orgList.size() > 0) {
					for (SysOrg so : orgList) {
						orgFlowList.add(so.getOrgFlow());
					}
				}
			} else {
				orgFlowList.add(orgFlow0);
			}
		}
		if (StringUtil.isNotBlank(passFlag)) {
			changeStatusIdList.add(passFlag);
		}
		List<SysOrg> orgList = new ArrayList<>();
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		orgList.add(0, org);

		model.addAttribute("orgList", orgList);
		PageHelper.startPage(currentPage, getPageSize(request));
//		if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
//			if (StringUtil.isNotBlank(doctor.getOrgFlow())) {
//				orgFlowList.add(doctor.getOrgFlow());
//			}
//			doctor.setOrgFlow("");
//		}
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("DoctorTrainingSpe");
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1(orgHistory, changeStatusIdList, doctor, orgFlowList, docTypeList, sessionNumbers);
		model.addAttribute("historyExts", historyExts);
		return "jsres/changeSpeMainList";
	}

	@RequestMapping(value = {"/searchChangeSpeListAcc"})
	public String searchChangeSpeListAcc(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String passFlag,
										 String orgFlow0, String datas[],String jointOrgFlag,String cityId) {
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			if (StringUtil.isNotBlank(cityId)) {
				org.setOrgCityId(s.getOrgCityId());
			}
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchOrgListNew(org);
			model.addAttribute("orgs", orgs);
			if(StringUtil.isBlank(doctor.getOrgFlow())) {
				for (SysOrg tempOrg : orgs) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
						List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
						if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
							for (ResJointOrg jointOrg : resJointOrgList) {
								orgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
					}
					orgFlowList.add(tempOrg.getOrgFlow());
				}
			}else{
				orgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}

//			if (StringUtil.isNotBlank(passFlag)) {
//				if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(passFlag)) {
//					changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
//				} else {
//					changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
//				}
//			} else {
//				changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
//				changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
//			}
		}else{
			//查询本基地的协同基地
			List<SysOrg> orgList = new ArrayList<>();
			SysOrg doctorOrg = new SysOrg();
			doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("JointOrgCount", orgList.size());
			orgList.add(0, doctorOrg);
			if (StringUtil.isBlank(orgFlow0)) {
//			orgFlowList.add(doctorOrg.getOrgFlow());
				orgFlow0 = doctorOrg.getOrgFlow();
			} else if ("all".equals(orgFlow0)) {
				if (orgList != null && orgList.size() > 0) {
					for (SysOrg so : orgList) {
						orgFlowList.add(so.getOrgFlow());
					}
				}
			} else {
				orgFlowList.add(orgFlow0);
			}
		}
		if (StringUtil.isNotBlank(passFlag)) {
			changeStatusIdList.add(passFlag);
		}
		List<SysOrg> orgList = new ArrayList<>();
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		orgList.add(0, org);

		model.addAttribute("orgList", orgList);
		PageHelper.startPage(currentPage, getPageSize(request));
//		if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
//			if (StringUtil.isNotBlank(doctor.getOrgFlow())) {
//				orgFlowList.add(doctor.getOrgFlow());
//			}
//			doctor.setOrgFlow("");
//		}
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("AssiGeneral");
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1(orgHistory, changeStatusIdList, doctor, orgFlowList, docTypeList, sessionNumbers);
		model.addAttribute("historyExts", historyExts);
		return "jsres/changeSpeMainList";
	}

	@RequestMapping(value = {"/exportSpeList"})
	public void exportSpeList(ResDoctor doctor, HttpServletResponse response, String passFlag,
							  String orgFlow0, String datas[],String jointOrgFlag,String cityId) throws Exception{
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			if (StringUtil.isNotBlank(cityId)) {
				org.setOrgCityId(s.getOrgCityId());
			}
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchOrgListNew(org);
			if(StringUtil.isBlank(doctor.getOrgFlow())) {
				for (SysOrg tempOrg : orgs) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
						List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
						if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
							for (ResJointOrg jointOrg : resJointOrgList) {
								orgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
					}
					orgFlowList.add(tempOrg.getOrgFlow());
				}
			}else{
				orgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}

			if (StringUtil.isNotBlank(passFlag)) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(passFlag)) {
                    changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
				} else {
                    changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
				}
			} else {
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
			}
		}
		List<SysOrg> orgList = new ArrayList<>();
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		orgList.add(0, org);

		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("DoctorTrainingSpe");
		List<JsResDoctorOrgHistoryExt> list = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1(orgHistory, changeStatusIdList, doctor, orgFlowList, docTypeList, sessionNumbers);
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFRow rowDep = sheet.createRow(0);//第一行
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		rowDep.setHeightInPoints(20);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("专业变更信息表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"序号",
				"姓名",
				"届别",
				"人员类型",
				"培训基地",
				"审核状态",
				"原培训专业",
				"变更后专业",
				"审核时间"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		//行数
		int rowNum = 2;
		//存放在excel中的行数据
		String[] resultList = null;
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				JsResDoctorOrgHistoryExt ext = list.get(i);
				resultList = new String[]{
						i + 1 + "",
						ext.getDoctorName() == null ? "" : ext.getDoctorName(),
						ext.getResDoctor().getSessionNumber() == null ? "" : ext.getResDoctor().getSessionNumber(),
						ext.getResDoctor().getDoctorTypeName() == null ? "" : ext.getResDoctor().getDoctorTypeName(),
						ext.getHistoryOrgName() == null ? "" : ext.getHistoryOrgName(),
						ext.getChangeStatusName() == null ? "无" : ext.getChangeStatusName(),
						ext.getHistoryTrainingSpeName() == null ? "无" : ext.getHistoryTrainingSpeName(),
						ext.getTrainingSpeName() == null ? "" : ext.getTrainingSpeName(),
						ext.getModifyTime() == null ? "" : ext.getModifyTime()
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
				}
			}
		}
		String fileName = "专业变更信息表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping(value = {"/exportSpeListAcc"})
	public void exportSpeListAcc(ResDoctor doctor, HttpServletResponse response, String passFlag,
								 String orgFlow0, String datas[],String jointOrgFlag,String cityId) throws Exception{
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL) || getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			if (StringUtil.isNotBlank(cityId)) {
				org.setOrgCityId(s.getOrgCityId());
			}
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchOrgListNew(org);
			if(StringUtil.isBlank(doctor.getOrgFlow())) {
				for (SysOrg tempOrg : orgs) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
						List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
						if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
							for (ResJointOrg jointOrg : resJointOrgList) {
								orgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
					}
					orgFlowList.add(tempOrg.getOrgFlow());
				}
			}else{
				orgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}

			if (StringUtil.isNotBlank(passFlag)) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(passFlag)) {
                    changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
				} else {
                    changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
				}
			} else {
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
			}
		}
		List<SysOrg> orgList = new ArrayList<>();
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		orgList.add(0, org);

		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("AssiGeneral");
		List<JsResDoctorOrgHistoryExt> list = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1(orgHistory, changeStatusIdList, doctor, orgFlowList, docTypeList, sessionNumbers);
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFRow rowDep = sheet.createRow(0);//第一行
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		rowDep.setHeightInPoints(20);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("专业变更信息表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"序号",
				"姓名",
				"届别",
				"人员类型",
				"培训基地",
				"审核状态",
				"原培训专业",
				"变更后专业",
				"审核时间"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		//行数
		int rowNum = 2;
		//存放在excel中的行数据
		String[] resultList = null;
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				JsResDoctorOrgHistoryExt ext = list.get(i);
				resultList = new String[]{
						i + 1 + "",
						ext.getDoctorName() == null ? "" : ext.getDoctorName(),
						ext.getResDoctor().getSessionNumber() == null ? "" : ext.getResDoctor().getSessionNumber(),
						ext.getResDoctor().getDoctorTypeName() == null ? "" : ext.getResDoctor().getDoctorTypeName(),
						ext.getHistoryOrgName() == null ? "" : ext.getHistoryOrgName(),
						ext.getChangeStatusName() == null ? "无" : ext.getChangeStatusName(),
						ext.getHistoryTrainingSpeName() == null ? "无" : ext.getHistoryTrainingSpeName(),
						ext.getTrainingSpeName() == null ? "" : ext.getTrainingSpeName(),
						ext.getModifyTime() == null ? "" : ext.getModifyTime()
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
				}
			}
		}
		String fileName = "专业变更信息表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping(value = {"/changeSpeMain"})
	public String changeSpeMain(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model,
								String datas[],String jointOrgFlag,String orgFlow) {
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
		List<String> jointOrgFlowList=new ArrayList<String>();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		SysUser sysUser = GlobalContext.getCurrentUser();
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			orgHistory.setOrgFlow(sysUser.getOrgFlow());
            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseWaitingAudit.getId());

			if (StringUtil.isBlank(doctor.getOrgFlow())) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysUser.getOrgFlow());
				jointOrgFlowList.add(sysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			SysOrg org = new SysOrg();
			org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//			orgs = orgBiz.searchAllSysOrg(org);
			orgs=orgBiz.searchOrgListNew(org);
			model.addAttribute("orgs", orgs);
			if (StringUtil.isBlank(doctor.getOrgFlow())) {
				for (SysOrg g : orgs) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
                            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
								String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
								if (StringUtil.isNotBlank(cityId) && cityId.equals(s.getOrgCityId())) {
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							} else {
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
					}
					jointOrgFlowList.add(g.getOrgFlow());
				}
			}else{
				jointOrgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		List<String> docTypeList = new ArrayList<>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String ds : datas) {
				docTypeList.add(ds);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}

		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1New(orgHistory, null, doctor, null, docTypeList, sessionNumbers,jointOrgFlowList);
		model.addAttribute("historyExts", historyExts);
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int speFlag = 0;
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			history.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseWaitingAudit.getId());
		} else {
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
		}
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<ResDoctorOrgHistory> spe = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if (spe != null && !spe.isEmpty()) {
			speFlag = 1;
		}
		model.addAttribute("speFlag", speFlag);
		return "jsres/changeSpeMain";
	}

	@RequestMapping(value = {"/changeSpeMainNew"})
	public String changeSpeMainNew(Model model) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		return "jsres/changeSpeMainNew";
	}

	@RequestMapping(value = {"/changeSpeMainNewAcc"})
	public String changeSpeMainNewAcc(Model model) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		return "jsres/changeSpeMainNewAcc";
	}
	@RequestMapping(value = {"/changeSpeMainListAcc"})
	public String changeSpeMainListAcc(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model,
									   String datas[],String jointOrgFlag,String orgFlow,String cityId) {
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
		List<String> jointOrgFlowList=new ArrayList<String>();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		SysUser sysUser = GlobalContext.getCurrentUser();
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			orgHistory.setOrgFlow(sysUser.getOrgFlow());
            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseWaitingAudit.getId());

			if (StringUtil.isBlank(doctor.getOrgFlow())) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysUser.getOrgFlow());
				jointOrgFlowList.add(sysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			SysOrg org = new SysOrg();
			org.setOrgProvId(s.getOrgProvId());
			if (StringUtil.isNotBlank(cityId)) {
				org.setOrgCityId(s.getOrgCityId());
			}
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchOrgListNew(org);
			model.addAttribute("orgs", orgs);
			if (StringUtil.isBlank(doctor.getOrgFlow())) {
				for (SysOrg g : orgs) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
                            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
								String cityId2 = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
								if (StringUtil.isNotBlank(cityId2) && cityId2.equals(s.getOrgCityId())) {
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							} else {
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
					}
					jointOrgFlowList.add(g.getOrgFlow());
				}
			}else{
				jointOrgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		List<String> docTypeList = new ArrayList<>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String ds : datas) {
				docTypeList.add(ds);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		doctor.setTrainingTypeId("AssiGeneral");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1New(orgHistory, null, doctor, null, docTypeList, sessionNumbers,jointOrgFlowList);
		model.addAttribute("historyExts", historyExts);
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int speFlag = 0;
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			history.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseWaitingAudit.getId());
		} else {
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
		}
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<ResDoctorOrgHistory> spe = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if (spe != null && !spe.isEmpty()) {
			speFlag = 1;
		}
		model.addAttribute("speFlag", speFlag);
		return "jsres/changeSpeMainList";
	}


	@RequestMapping(value = {"/changeSpeMainList"})
	public String changeSpeMainList(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model,
									String datas[],String jointOrgFlag,String orgFlow,String cityId) {
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
		List<String> jointOrgFlowList=new ArrayList<String>();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		SysUser sysUser = GlobalContext.getCurrentUser();
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			orgHistory.setOrgFlow(sysUser.getOrgFlow());
            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseWaitingAudit.getId());

			if (StringUtil.isBlank(doctor.getOrgFlow())) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysUser.getOrgFlow());
				jointOrgFlowList.add(sysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		List<SysOrg> orgs = new ArrayList<SysOrg>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			SysOrg org = new SysOrg();
			org.setOrgProvId(s.getOrgProvId());
			if (StringUtil.isNotBlank(cityId)) {
				org.setOrgCityId(s.getOrgCityId());
			}
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchOrgListNew(org);
			model.addAttribute("orgs", orgs);
			if (StringUtil.isBlank(doctor.getOrgFlow())) {
				for (SysOrg g : orgs) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
                            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
								String cityId2 = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
								if (StringUtil.isNotBlank(cityId2) && cityId2.equals(s.getOrgCityId())) {
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							} else {
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
					}
					jointOrgFlowList.add(g.getOrgFlow());
				}
			}else{
				jointOrgFlowList.add(doctor.getOrgFlow());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		List<String> docTypeList = new ArrayList<>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String ds : datas) {
				docTypeList.add(ds);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		doctor.setTrainingTypeId("DoctorTrainingSpe");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1New(orgHistory, null, doctor, null, docTypeList, sessionNumbers,jointOrgFlowList);
		model.addAttribute("historyExts", historyExts);
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int speFlag = 0;
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			history.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseWaitingAudit.getId());
		} else {
            history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
		}
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<ResDoctorOrgHistory> spe = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if (spe != null && !spe.isEmpty()) {
			speFlag = 1;
		}
		model.addAttribute("speFlag", speFlag);
		return "jsres/changeSpeMainList";
	}

	@RequestMapping(value = {"/changeBase"})
	public String changeBase(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String statusFlag,
							 String historyOrgFlow, String datas[]) {
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			org.setOrgCityId(s.getOrgCityId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs = orgBiz.searchAllSysOrg(org);
			if (orgs != null && !orgs.isEmpty()) {
				for (SysOrg sysOrg : orgs) {
					orgFlowList.add(sysOrg.getOrgFlow());
				}
			}
		}
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		orgHistory.setHistoryOrgFlow(historyOrgFlow);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("DoctorTrainingSpe");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlow1(orgHistory, changeStatusIdList, docTypeList, doctor, orgFlowList, sessionNumbers);
		model.addAttribute("historyExts2", historyExts);
		System.err.println("=============" + JSON.toJSONString(historyExts));
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org", org);
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
		model.addAttribute("orgs", orgs);
		return "jsres/global/hospital/findBaseChange";
	}

	@RequestMapping(value = {"/changeBaseAcc"})
	public String changeBaseAcc(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String statusFlag,
								String historyOrgFlow, String datas[]) {
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			org.setOrgCityId(s.getOrgCityId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs = orgBiz.searchAllSysOrg(org);
			if (orgs != null && !orgs.isEmpty()) {
				for (SysOrg sysOrg : orgs) {
					orgFlowList.add(sysOrg.getOrgFlow());
				}
			}
		}
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		orgHistory.setHistoryOrgFlow(historyOrgFlow);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("AssiGeneral");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlow1(orgHistory, changeStatusIdList, docTypeList, doctor, orgFlowList, sessionNumbers);
		model.addAttribute("historyExts2", historyExts);
		System.err.println("=============" + JSON.toJSONString(historyExts));
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org", org);
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
		model.addAttribute("orgs", orgs);
		return "jsres/global/hospital/findBaseChangeAcc";
	}



	@RequestMapping(value = {"/changeBaseMainNew"})
	public String changeBaseMainNew(Model model) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		return "jsres/global/hospital/findBaseChangeMain";
	}

	@RequestMapping(value = {"/changeBaseMainNewAcc"})
	public String changeBaseMainNewAcc(Model model) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		return "jsres/global/hospital/findBaseChangeMainAcc";
	}

	@RequestMapping(value = {"/changeBaseList"})
	public String changeBaseList(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String statusFlag,
								 String orgFlow, String datas[],String jointOrgFlag,String cityId) {
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();

		List<SysOrg> orgs = new ArrayList<SysOrg>();
		SysOrg org = new SysOrg();
		SysOrg so = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(so.getOrgProvId());
		if(StringUtil.isNotBlank(cityId)){
			org.setOrgCityId(cityId);
		}
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchOrgListNew(org);
		model.addAttribute("orgs", orgs);
		if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
			for (SysOrg tempOrg : orgs) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				orgFlowList.add(tempOrg.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			orgFlowList.add(doctor.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}

		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		orgHistory.setHistoryOrgFlow(orgFlow);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlow1(orgHistory, changeStatusIdList, docTypeList, doctor, orgFlowList, sessionNumbers);
		model.addAttribute("historyExts2", historyExts);

		return "jsres/global/hospital/findBaseChangeList";
	}

	@RequestMapping(value = {"/changeBaseListAcc"})
	public String changeBaseListAcc(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String statusFlag,
									String orgFlow, String datas[],String jointOrgFlag,String cityId) {
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();

		List<SysOrg> orgs = new ArrayList<SysOrg>();
		SysOrg org = new SysOrg();
		SysOrg so = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(so.getOrgProvId());
		if(StringUtil.isNotBlank(cityId)){
			org.setOrgCityId(cityId);
		}
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchOrgListNew(org);
		model.addAttribute("orgs", orgs);
		if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
			for (SysOrg tempOrg : orgs) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				orgFlowList.add(tempOrg.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			orgFlowList.add(doctor.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}

		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		orgHistory.setHistoryOrgFlow(orgFlow);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
//		doctor.setTrainingTypeId("AssiGeneral");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlow1(orgHistory, changeStatusIdList, docTypeList, doctor, orgFlowList, sessionNumbers);
		model.addAttribute("historyExts2", historyExts);

		return "jsres/global/hospital/findBaseChangeList";
	}

	@RequestMapping(value = {"/audit"})
	public String audit(Model model, String recordFlow, String doctorFlow) {
		if (StringUtil.isNotBlank(recordFlow)) {
			ResDoctorOrgHistory orgHistory = jsDocOrgHistoryBiz.readDocOrgHistory(recordFlow);
			model.addAttribute("orgHistory", orgHistory);
			if (StringUtil.isNotBlank(doctorFlow)) {
				ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
				model.addAttribute("doctor", doctor);
				ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
				doctorRecruit.setDoctorFlow(doctorFlow);
				doctorRecruit.setRecruitFlow(orgHistory.getRecruitFlow());
                doctorRecruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                List<com.pinde.core.model.ResDoctorRecruit> recruits = jsResDoctorRecruitBiz.searchResDoctorRecruitList(doctorRecruit, "CREATE_TIME DESC");
				if (recruits != null && recruits.size() > 0) {
                    com.pinde.core.model.ResDoctorRecruit recruit = recruits.get(0);
					model.addAttribute("recruit", recruit);
				}
			}

		}
		return "jsres/hospital/doctor/audit";
	}

	@RequestMapping(value = {"/saveAudit"})
	@ResponseBody
    public String saveAudit(Model model, ResDoctorOrgHistory history, String flag, com.pinde.core.model.ResDoctorRecruit recruit) {
		String msgContent = "";
		if (StringUtil.isNotBlank(history.getRecordFlow())) {
			ResDoctorOrgHistory orgHistory = jsDocOrgHistoryBiz.readDocOrgHistory(history.getRecordFlow());
			if (orgHistory != null) {
				if (StringUtil.isNotBlank(flag)) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
                        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
							msgContent = "省厅审核通过！";
                            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
                            orgHistory.setChangeStatusName(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getName());
						}
                        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
							msgContent = "基地审核通过！";
                            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
                            orgHistory.setChangeStatusName(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getName());
						}
					} else {
                        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
							msgContent = "省厅审核不通过！";
                            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
                            orgHistory.setChangeStatusName(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getName());
						}
                        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
							msgContent = "基地审核不通过！";
                            orgHistory.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseAuditUnPass.getId());
                            orgHistory.setChangeStatusName(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseAuditUnPass.getName());
						}
					}
				}
				if (StringUtil.isNotBlank(history.getAuditOpinion())) {
					orgHistory.setAuditOpinion(history.getAuditOpinion());
				}
                if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
					orgHistory.setOutDate(DateUtil.getCurrDateTime());
				}
                if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
					orgHistory.setInDate(DateUtil.getCurrDateTime());
				}
				int result = jsDocOrgHistoryBiz.saveDocOrgHistory(orgHistory);
				int recResult = 0;
				String mark = "";
                if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL) && result != com.pinde.core.common.GlobalConstant.ZERO_LINE && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
					recResult = jsDocOrgHistoryBiz.changeStatus(orgHistory, recruit);
                    mark = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
                if (result == com.pinde.core.common.GlobalConstant.ZERO_LINE || (recResult == com.pinde.core.common.GlobalConstant.ZERO_LINE && StringUtil.isBlank(mark) && getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag))) {
                    return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
				} else {
					String msgTitle = "变更专业审核结果";
					msgBiz.addSysMsg(orgHistory.getDoctorFlow(), msgTitle, msgContent);
                    return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
				}
			} else {
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
			}
		} else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
		}
	}

	/**
	 * 加载转出  住院医师
	 *
	 * @return
	 */
	@RequestMapping(value = {"/turnOutMain"})
	public String turnOutMain(Integer currentPage, ResDoctor resDoctor, HttpServletRequest request, String orgFlow0,
							  Model model, String datas[]) {
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
		docOrgHistory.setHistoryOrgFlow(currUser.getOrgFlow());
        docOrgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		//查询本基地的协同基地
		List<SysOrg> orgList = new ArrayList<>();
		SysOrg doctorOrg = new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		orgList.add(0, doctorOrg);
		List<String> orgFlowList = new ArrayList<>();
		if (StringUtil.isBlank(orgFlow0)) {
			orgFlow0 = doctorOrg.getOrgFlow();
		} else if ("all".equals(orgFlow0)) {
			if (orgList != null && orgList.size() > 0) {
				docOrgHistory.setHistoryOrgFlow("");
				for (SysOrg so : orgList) {
					orgFlowList.add(so.getOrgFlow());
				}
			}
		} else {
			docOrgHistory.setHistoryOrgFlow(orgFlow0);
		}
		resDoctor.setOrgFlow("");
		model.addAttribute("orgFlow", orgFlow0);
		model.addAttribute("orgList", orgList);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		resDoctor.setTrainingTypeId("DoctorTrainingSpe");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(docOrgHistory, null, resDoctor, orgFlowList, docTypeList);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int baseFlag = 0;
		history.setOrgFlow(currUser.getOrgFlow());
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		history.setOrgFlow("");
		history.setHistoryOrgFlow(currUser.getOrgFlow());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseTwo = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if ((baseOne != null && !baseOne.isEmpty()) || (baseTwo != null && !baseTwo.isEmpty())) {
			baseFlag = 1;
		}
		model.addAttribute("baseFlag", baseFlag);
		return "jsres/hospital/doctor/turnOutMain";
	}


	/**
	 * 加载转出  助理全科
	 *
	 * @return
	 */
	@RequestMapping(value = {"/turnOutMainAcc"})
	public String turnOutMainAcc(Integer currentPage, ResDoctor resDoctor, HttpServletRequest request, String orgFlow0,
								 Model model, String datas[]) {
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
		docOrgHistory.setHistoryOrgFlow(currUser.getOrgFlow());
        docOrgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		//查询本基地的协同基地
		List<SysOrg> orgList = new ArrayList<>();
		SysOrg doctorOrg = new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		orgList.add(0, doctorOrg);
		List<String> orgFlowList = new ArrayList<>();
		if (StringUtil.isBlank(orgFlow0)) {
			orgFlow0 = doctorOrg.getOrgFlow();
		} else if ("all".equals(orgFlow0)) {
			if (orgList != null && orgList.size() > 0) {
				docOrgHistory.setHistoryOrgFlow("");
				for (SysOrg so : orgList) {
					orgFlowList.add(so.getOrgFlow());
				}
			}
		} else {
			docOrgHistory.setHistoryOrgFlow(orgFlow0);
		}
		resDoctor.setOrgFlow("");
		model.addAttribute("orgFlow", orgFlow0);
		model.addAttribute("orgList", orgList);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		resDoctor.setTrainingTypeId("AssiGeneral");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(docOrgHistory, null, resDoctor, orgFlowList, docTypeList);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int baseFlag = 0;
		history.setOrgFlow(currUser.getOrgFlow());
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		history.setOrgFlow("");
		history.setHistoryOrgFlow(currUser.getOrgFlow());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseTwo = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if ((baseOne != null && !baseOne.isEmpty()) || (baseTwo != null && !baseTwo.isEmpty())) {
			baseFlag = 1;
		}
		model.addAttribute("baseFlag", baseFlag);
		return "jsres/hospital/doctor/turnOutMainAcc";
	}

	/**
	 * 转出
	 *
	 * @return
	 */
	@RequestMapping(value = {"/turnOutOrg"})
	@ResponseBody
	public String turnOutOrg(ResDoctorOrgHistory history, String time) {
		if (StringUtil.isNotBlank(history.getRecordFlow()) && StringUtil.isNotBlank(history.getChangeStatusId()) && StringUtil.isNotBlank(history.getDoctorFlow())) {
			if (StringUtil.isBlank(time)) {
				time = "";
			}
			int result = jsDocOrgHistoryBiz.auditTurnOutOrg(history, time);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}


	/**
	 * 变更详情
	 *
	 * @param doctorFlow
	 * @param recordFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/getChangeOrgDetail"})
	public String getChangeOrgDetail(String doctorFlow, String recordFlow, Model model) {
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitsList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		model.addAttribute("recruitsList", recruitsList);
		ResRec resRec = new ResRec();
		List<String> operUserFlowList = new ArrayList<String>();
		operUserFlowList.add(doctorFlow);
        resRec.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
		List<ResRec> recList = resRecBiz.searchRecInfo(resRec, operUserFlowList);
		model.addAttribute("recList", recList);
		ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
		docotrDelayTeturn.setDoctorFlow(doctorFlow);
        docotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
		List<ResDocotrDelayTeturn> delayList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn, null, null, null);
//		List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId(), doctorFlow);
		model.addAttribute("delayList", delayList);
		return "jsres/hospital/changOrgInfo";
	}


	/**
	 * 加载转入
	 *
	 * @return
	 */
	@RequestMapping(value = {"/turnInMain"})
	public String turnInMain(Integer currentPage, ResDoctor resDoctor, String orgFlow0,
							 HttpServletRequest request, Model model, String datas[]) {
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
		docOrgHistory.setOrgFlow(currUser.getOrgFlow());
        docOrgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());//转入审核通过
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());//转入审核不通过
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyPass.getId());//转入审核通过
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());//待省厅审核
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());//省厅审核不通过
		}
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyPass.getId());//转入审核通过
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
		}
		//查询本基地的协同基地
		List<SysOrg> orgList = new ArrayList<>();
		SysOrg doctorOrg = new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		orgList.add(0, doctorOrg);
//		List<String> orgFlowList = new ArrayList<>();
		if (StringUtil.isBlank(orgFlow0)) {
//			orgFlowList.add(doctorOrg.getOrgFlow());
			orgFlow0 = doctorOrg.getOrgFlow();
		} else if ("all".equals(orgFlow0)) {
//			orgFlowList.add(docOrgHistory.getOrgFlow());
			docOrgHistory.setOrgFlow("");
			if (orgList != null && orgList.size() > 0) {
				for (SysOrg so : orgList) {
//					orgFlowList.add(so.getOrgFlow());
				}
			}
		} /*else {
			orgFlowList.add(orgFlow0);
		}*/
		model.addAttribute("orgFlow", orgFlow0);
		model.addAttribute("orgList", orgList);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		resDoctor.setTrainingTypeId("DoctorTrainingSpe");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<String> orgFlowList = new ArrayList<String>();
		orgList.stream().forEach(e -> orgFlowList.add(e.getOrgFlow()));
		List<JsResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(docOrgHistory, changeStatusIdList, resDoctor, orgFlowList, docTypeList);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int baseFlag = 0;
		history.setOrgFlow(currUser.getOrgFlow());
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		history.setOrgFlow("");
		history.setHistoryOrgFlow(currUser.getOrgFlow());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseTwo = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if ((baseOne != null && !baseOne.isEmpty()) || (baseTwo != null && !baseTwo.isEmpty())) {
			baseFlag = 1;
		}
		model.addAttribute("baseFlag", baseFlag);
		return "jsres/hospital/doctor/turnInMain";
	}


	@RequestMapping(value = {"/turnInMainAcc"})
	public String turnInMainAcc(Integer currentPage, ResDoctor resDoctor, String orgFlow0,
								HttpServletRequest request, Model model, String datas[]) {
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
		docOrgHistory.setOrgFlow(currUser.getOrgFlow());
        docOrgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());//转入审核通过
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());//转入审核不通过
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyPass.getId());//转入审核通过
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());//待省厅审核
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());//省厅审核不通过
		}
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyPass.getId());//转入审核通过
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
		}
		//查询本基地的协同基地
		List<SysOrg> orgList = new ArrayList<>();
		SysOrg doctorOrg = new SysOrg();
		doctorOrg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		doctorOrg.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		orgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount", orgList.size());
		orgList.add(0, doctorOrg);
		List<String> orgFlowList = new ArrayList<>();
		if (StringUtil.isBlank(orgFlow0)) {
//			orgFlowList.add(doctorOrg.getOrgFlow());
			orgFlow0 = doctorOrg.getOrgFlow();
		} else if ("all".equals(orgFlow0)) {
			orgFlowList.add(docOrgHistory.getOrgFlow());
			docOrgHistory.setOrgFlow("");
			if (orgList != null && orgList.size() > 0) {
				for (SysOrg so : orgList) {
					orgFlowList.add(so.getOrgFlow());
				}
			}
		} else {
			orgFlowList.add(orgFlow0);
		}
		model.addAttribute("orgFlow", orgFlow0);
		model.addAttribute("orgList", orgList);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		resDoctor.setTrainingTypeId("AssiGeneral");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(docOrgHistory, changeStatusIdList, resDoctor, orgFlowList, docTypeList);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int baseFlag = 0;
		history.setOrgFlow(currUser.getOrgFlow());
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		history.setOrgFlow("");
		history.setHistoryOrgFlow(currUser.getOrgFlow());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseTwo = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if ((baseOne != null && !baseOne.isEmpty()) || (baseTwo != null && !baseTwo.isEmpty())) {
			baseFlag = 1;
		}
		model.addAttribute("baseFlag", baseFlag);
		return "jsres/hospital/doctor/turnInMainAcc";
	}

	/**
	 * 转入
	 *
	 * @return
	 */
	@RequestMapping(value = {"/turnInOrg"})
	@ResponseBody
	public String turnInOrg(String recordFlow, String changeStatusId, String doctorFlow, String time, String chooseFlag) {
		if (StringUtil.isNotBlank(recordFlow)) {
			int result = jsDocOrgHistoryBiz.auditTurnInOrg(recordFlow, changeStatusId, doctorFlow, time, chooseFlag);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}


	//********************************  更新医师走向 *****************************************


	/**
	 * 跳转至更新医师走向
	 */
	@RequestMapping(value = "/updateDoctorRecruit")
	public String updateDoctorRecruit(String recruitFlow, Model model, String doctorFlow) {
		if (StringUtil.isNotBlank(recruitFlow)) {
            String isFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
            com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
			JsresRecruitDocInfo jsresRecruit = recruitDoctorInfoBiz.readRecruit(recruitFlow);
			ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
			doctorRecruit.setDoctorFlow(doctorFlow);
            doctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
			List<ResDoctorRecruitWithBLOBs> recruitList = jsResDoctorRecruitBiz.searchRecruitWithBLOBs(doctorRecruit);
			if (recruitList != null && !recruitList.isEmpty()) {
				if (recruitList.get(0).getRecruitFlow().equals(recruitFlow)) {
                    isFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			}
			model.addAttribute("isFlag", isFlag);
			model.addAttribute("doctorRecruit", recruit);
			model.addAttribute("jsresRecruitDocInfo", jsresRecruit);
			ResDocotrDelayTeturn ret = resDoctorDelayTeturnBiz.findTeturnInfo(recruitFlow);
			model.addAttribute("ret", ret);

		}
		return "jsres/hospital/doctor/editDoctorTrend";
	}

	/**
	 *
	 */
	@RequestMapping(value = "/updateDoctorTrend")
	@ResponseBody
	public String updateDoctorTrend(ResDoctorRecruitWithBLOBs recruitWithBLOBs, ResDocotrDelayTeturn docotrDelayTeturn, HttpServletRequest request, String delayPicValueFile) throws IOException {
		int result = 0;
		int backResult = 0;
		int delayResult = 0;
		int userblack = 0;
		result = jsResDoctorRecruitBiz.updateDoctorTrend(recruitWithBLOBs);
        String flag = com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		List<PubFile> pubFiles = new ArrayList<>();
		if (StringUtil.isNotBlank(docotrDelayTeturn.getReasonId())) {//退培
			//以下为多文件上传********************************************
			//创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			//判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				//转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				//取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					//记录上传过程起始时的时间，用来计算上传时间
					//int pre = (int) System.currentTimeMillis();
					//取得上传文件
					List<MultipartFile> files = multiRequest.getFiles(iter.next());
					if (files != null && files.size() > 0) {
						for (MultipartFile file : files) {
							//保存附件
							PubFile pubFile = new PubFile();
							if (file.getSize() > 10 * 1024 * 1024) {
                                return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + "10M";
							}
							//取得当前上传文件的文件名称
							String oldFileName = file.getOriginalFilename();
							//如果名称不为“”,说明该文件存在，否则说明该文件不存在
							if (StringUtil.isNotBlank(oldFileName)) {
								//定义上传路径
								String dateString = DateUtil.getCurrDate2();
								String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "returnImg" + File.separator + dateString;
								File fileDir = new File(newDir);
								if (!fileDir.exists()) {
									fileDir.mkdirs();
								}
								//重命名上传后的文件名
								String originalFilename = "";
								originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
								File newFile = new File(fileDir, originalFilename);
								try {
									file.transferTo(newFile);
								} catch (Exception e) {
									logger.error("", e);
									throw new RuntimeException("保存文件失败！");
								}
								String filePath = File.separator + "returnImg" + File.separator + dateString + File.separator + originalFilename;
								pubFile.setFilePath(filePath);
								pubFile.setFileName(oldFileName);
								pubFile.setProductType("退培文件");
								pubFiles.add(pubFile);
							}
						}
					}
					//记录上传该文件后的时间
					//int finaltime = (int) System.currentTimeMillis();
				}
			}
			//以上为多文件上传********************************************

			// 注：1.以前版本中退培学员由基地负责（无需经省厅同意），基地将doctorRecruit表auditStatusId置为NotSubmit，在基地退培时将医师信息置为空，
			//	   2.现优化为基地提交退培学员由省厅审核，基地将doctorRecruit表auditStatusId置为NotSubmit，省厅审核同意时将医师信息置为空，若不同意将doctorRecruit表auditStatusId置为Passed
			//     3.经过现版本优化可能会出现与以前医师信息出现无法融合的问题
			//     4.需求经优化后，退培附件为必传，故退培信息不会出现没有附件的记录
			//     5.省厅审核状态；"0"审核不通过，"1"审核通过，"2"待审核
			//该功能因RES_REC表拆分原因再次优化将RES_REC中退培延期的数据迁移至RES_DOCOTR_DELAY_TETURN中业务逻辑未发生变化。

			//更新docotrDelayTeturn数据
			operDocotrDelayTeturn(docotrDelayTeturn, "back");
            docotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
            docotrDelayTeturn.setTypeName(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getName());
			docotrDelayTeturn.setAuditStatusId("2");
//			docotrDelayTeturn.setAuditStatusId(com.pinde.core.common.enums.ResBaseStatusEnum.ChargeAudit.getId());
            docotrDelayTeturn.setAuditStatusName(com.pinde.core.common.enums.ResBaseStatusEnum.Auditing.getName());
//			docotrDelayTeturn.setAuditStatusName(com.pinde.core.common.enums.ResBaseStatusEnum.ChargeAudit.getName());
			docotrDelayTeturn.setPolicyName("1".equals(docotrDelayTeturn.getPolicyId()) ? "协议退培" : "违约退培");
			docotrDelayTeturn.setReasonName("1".equals(docotrDelayTeturn.getReasonId()) ? "辞职" : ("2".equals(docotrDelayTeturn.getReasonId()) ? "考研" : "其他"));
			backResult = resDoctorDelayTeturnBiz.edit(docotrDelayTeturn, pubFiles);
		}
		if (StringUtil.isNotBlank(docotrDelayTeturn.getDelayreason())) {//延期
			//更新docotrDelayTeturn数据
			operDocotrDelayTeturn(docotrDelayTeturn, "delay");
            docotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
            docotrDelayTeturn.setTypeName(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getName());
			delayResult = resDoctorDelayTeturnBiz.saveDelayInfo(docotrDelayTeturn);
		}
        if (result == com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            flag = com.pinde.core.common.GlobalConstant.OPRE_FAIL;
		}
        if ((StringUtil.isNotBlank(docotrDelayTeturn.getReasonId()) && backResult == com.pinde.core.common.GlobalConstant.ZERO_LINE)) {
            flag = com.pinde.core.common.GlobalConstant.OPRE_FAIL;
		}
        if ((StringUtil.isNotBlank(docotrDelayTeturn.getDelayreason()) && delayResult == com.pinde.core.common.GlobalConstant.ZERO_LINE)) {
            flag = com.pinde.core.common.GlobalConstant.OPRE_FAIL;
		}
		return flag;
	}

	/**
	 * 延期校验
	 */
	@RequestMapping(value = "/checkDelay")
	@ResponseBody
	public String checkDelay(String doctorFlow, String graduactionYear){
		//1.已经有考试成绩不能延期
		List<ResScore> resScores = resScoreBiz.selectByParam(doctorFlow);
		if(CollectionUtils.isNotEmpty(resScores)){
			return "已参加过结业考核，不允许延期";
		}
		ResDoctor resDoctor = resDoctorBiz.readDoctor(doctorFlow);
		ResDocotrDelayTeturn resDocotrDelayTeturn = new ResDocotrDelayTeturn();
		resDocotrDelayTeturn.setDoctorFlow(doctorFlow);
        resDocotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
		//所有延期记录 2.不能超过一次
		List<ResDocotrDelayTeturn> resDocotrDelayTeturns = resDoctorDelayTeturnBiz.searchInfoNew(resDocotrDelayTeturn, null, null, null);
		if(resDocotrDelayTeturns != null && resDocotrDelayTeturns.size() > 0){
			// 取最新的一条记录
			ResDocotrDelayTeturn docotrDelayTeturn = resDocotrDelayTeturns.get(0);
			// 待审核
			if("2".equals(docotrDelayTeturn.getAuditStatusId())){
				return "存在待审核延期记录";
			}
		}
		// 培训年限 默认3年
		int trainYear = 3;
        if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(resDoctor.getTrainingYears())) {
			trainYear = 1;
        } else if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(resDoctor.getTrainingYears())) {
			trainYear = 2;
		}
		// 年级+培训年限+3为最晚结业年份
		int graducationYearLast = Integer.parseInt(resDoctor.getSessionNumber()) + trainYear + 3;
		// 延期后结业年份超过最晚应结业年份
		if(graducationYearLast < Integer.parseInt(graduactionYear)){
			return "延期后结业年份不应小于最晚应结业年份";
		}
        return com.pinde.core.common.GlobalConstant.FLAG_Y;
	}

	public void operDocotrDelayTeturn(ResDocotrDelayTeturn docotrDelayTeturn, String typeId) {
		String doctorFlow = docotrDelayTeturn.getDoctorFlow();
		ResDoctor doctor = resDoctorBiz.findByFlow(doctorFlow);
		ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
		doctorRecruit.setDoctorFlow(doctor.getDoctorFlow());
		//doctor.orgFlow doctorRecruit.orgFlow 存在不一样的情况
//		doctorRecruit.setOrgFlow(doctor.getOrgFlow());
		if (!"/inx/tjres".equals(InitConfig.getSysCfg("sys_index_url"))) {
			doctorRecruit.setCatSpeId(doctor.getTrainingTypeId());
		}
		doctorRecruit.setSpeId(doctor.getTrainingSpeId());
        doctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        com.pinde.core.model.ResDoctorRecruit recruit = doctorRecruitBiz.searchRecruitByResDoctor(doctorRecruit);
		if(recruit != null){
			docotrDelayTeturn.setOrgFlow(recruit.getOrgFlow());
			docotrDelayTeturn.setOrgName(recruit.getOrgName());
			docotrDelayTeturn.setSessionNumber(recruit.getSessionNumber());
			docotrDelayTeturn.setTrainingYears(recruit.getTrainYear());
			docotrDelayTeturn.setDoctorFlow(doctorFlow);
			docotrDelayTeturn.setDoctorName(doctor.getDoctorName());
			docotrDelayTeturn.setTrainingSpeId(recruit.getSpeId());
			docotrDelayTeturn.setTrainingSpeName(recruit.getSpeName());
			docotrDelayTeturn.setTrainingTypeId(recruit.getCatSpeId());
			docotrDelayTeturn.setTrainingTypeName(recruit.getCatSpeName());
			docotrDelayTeturn.setDoctorTypeId(doctor.getDoctorTypeId());
			docotrDelayTeturn.setDoctorTypeName(doctor.getDoctorTypeName());
			//注：结业考核年份graduationYear,记录类型typeId延期和退培分开考虑
			if ("back".equals(typeId)) {
				docotrDelayTeturn.setGraduationYear(recruit.getGraduationYear());
			}
			docotrDelayTeturn.setRecruitFlow(recruit.getRecruitFlow());
		}
		docotrDelayTeturn.setPolicyTime(DateUtil.getCurrentTime());
		docotrDelayTeturn.setPolicyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		docotrDelayTeturn.setPolicyUserName(GlobalContext.getCurrentUser().getUserName());
	}

	/**
	 * 减免学员列表 住院医师
	 *
	 * @param currentPage
	 * @param degreeType
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reductionRotationOper")
	public String reductionRotationOper(Integer currentPage, String degreeType, String viewJoint, String sessionNumber, String status,
										String doctorName, Model model, HttpServletRequest request) {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		String sessionNumber2 = "2015";
        String trainType = com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId();
		List<String> trainingYears = new ArrayList<String>();
        trainingYears.add(com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId());
        trainingYears.add(com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId());
		List<String> degreeTypes = new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(degreeType)) {
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicMaster.getId());
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicDoctor.getId());
		} else {
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.Bachelor.getId());
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.AcademicMaster.getId());
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.AcademicDoctor.getId());
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.NoDegree.getId());
		}

		List<String> orgFlows = new ArrayList<String>();
		orgFlows.add(orgFlow);

        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewJoint)) {
			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			if (jointOrgList != null && !jointOrgList.isEmpty()) {
				for (ResJointOrg rjo : jointOrgList) {
					orgFlows.add(rjo.getJointOrgFlow());
				}
			}
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("degreeTypes", degreeTypes);
		paramMap.put("sessionNumber2", sessionNumber2);
		paramMap.put("sessionNumber", sessionNumber);
		paramMap.put("status", status);
		paramMap.put("trainingYears", trainingYears);
		paramMap.put("trainType", trainType);
		paramMap.put("orgFlows", orgFlows);
		paramMap.put("doctorName", doctorName);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResDoctor> doctorList = jsResDoctorBiz.searchReductionDoc(paramMap);
		if (doctorList != null && !doctorList.isEmpty()) {
			model.addAttribute("doctorList", doctorList);

			List<String> doctorFlows = new ArrayList<String>();
			Map<String, Integer> operFlagMap = new HashMap<String, Integer>();
			Map<String, ResDoctorRecruit> recruitMap = new HashMap<String, ResDoctorRecruit>();
			Map<String, UserResumeExtInfoForm> userResumeExtMap = new HashMap<String, UserResumeExtInfoForm>();
			for (ResDoctor doc : doctorList) {
				String doctorFlow = doc.getDoctorFlow();

				doctorFlows.add(doctorFlow);

				int result = doctorDeptBiz.countSchDoctorDeptIgnoreStatus(doctorFlow, doc.getRotationFlow(), doc.getOrgFlow());
				operFlagMap.put(doctorFlow, result);

                com.pinde.core.model.ResDoctorRecruit recruit = doctorRecruitBiz.getNewRecruit(doctorFlow);
				recruitMap.put(doctorFlow, recruit);

                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(degreeType)) {
					PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
					if (resume != null) {
						String content = resume.getUserResume();
						if (StringUtil.isNotBlank(content)) {
							UserResumeExtInfoForm userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
							userResumeExtMap.put(doctorFlow, userResumeExt);
						}
					}
				}
			}
			model.addAttribute("operFlagMap", operFlagMap);
			model.addAttribute("recruitMap", recruitMap);
			model.addAttribute("userResumeExtMap", userResumeExtMap);

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(doctorFlows);
			if (userList != null && !userList.isEmpty()) {
				Map<String, SysUser> userMap = new HashMap<String, SysUser>();
				for (SysUser su : userList) {
					userMap.put(su.getUserFlow(), su);
				}
				model.addAttribute("userMap", userMap);
			}
		}
		return "jsres/hospital/reductionRotationDocList";
	}


	/**
	 * 减免学员列表 助理全科
	 *
	 * @param currentPage
	 * @param degreeType
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reductionRotationOperAcc")
	public String reductionRotationOperAcc(Integer currentPage, String degreeType, String viewJoint, String sessionNumber, String status,
										   String doctorName, Model model, HttpServletRequest request) {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		String sessionNumber2 = "2015";
        String trainType = com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId();
		List<String> trainingYears = new ArrayList<String>();
        trainingYears.add(com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId());
        trainingYears.add(com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId());
		List<String> degreeTypes = new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(degreeType)) {
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicMaster.getId());
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicDoctor.getId());
		} else {
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.Bachelor.getId());
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.AcademicMaster.getId());
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.AcademicDoctor.getId());
            degreeTypes.add(com.pinde.core.common.enums.JsResDegreeCategoryEnum.NoDegree.getId());
		}

		List<String> orgFlows = new ArrayList<String>();
		orgFlows.add(orgFlow);

        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewJoint)) {
			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			if (jointOrgList != null && !jointOrgList.isEmpty()) {
				for (ResJointOrg rjo : jointOrgList) {
					orgFlows.add(rjo.getJointOrgFlow());
				}
			}
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("degreeTypes", degreeTypes);
		paramMap.put("sessionNumber2", sessionNumber2);
		paramMap.put("sessionNumber", sessionNumber);
		paramMap.put("status", status);
		paramMap.put("trainingYears", trainingYears);
		paramMap.put("trainType", trainType);
		paramMap.put("orgFlows", orgFlows);
		paramMap.put("doctorName", doctorName);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResDoctor> doctorList = jsResDoctorBiz.searchReductionDoc(paramMap);
		if (doctorList != null && !doctorList.isEmpty()) {
			model.addAttribute("doctorList", doctorList);

			List<String> doctorFlows = new ArrayList<String>();
			Map<String, Integer> operFlagMap = new HashMap<String, Integer>();
			Map<String, ResDoctorRecruit> recruitMap = new HashMap<String, ResDoctorRecruit>();
			Map<String, UserResumeExtInfoForm> userResumeExtMap = new HashMap<String, UserResumeExtInfoForm>();
			for (ResDoctor doc : doctorList) {
				String doctorFlow = doc.getDoctorFlow();

				doctorFlows.add(doctorFlow);

				int result = doctorDeptBiz.countSchDoctorDeptIgnoreStatus(doctorFlow, doc.getRotationFlow(), doc.getOrgFlow());
				operFlagMap.put(doctorFlow, result);

                com.pinde.core.model.ResDoctorRecruit recruit = doctorRecruitBiz.getNewRecruit(doctorFlow);
				recruitMap.put(doctorFlow, recruit);

                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(degreeType)) {
					PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
					String content = resume.getUserResume();
					if (StringUtil.isNotBlank(content)) {
						UserResumeExtInfoForm userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
						userResumeExtMap.put(doctorFlow, userResumeExt);
					}
				}
			}
			model.addAttribute("operFlagMap", operFlagMap);
			model.addAttribute("recruitMap", recruitMap);
			model.addAttribute("userResumeExtMap", userResumeExtMap);

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(doctorFlows);
			if (userList != null && !userList.isEmpty()) {
				Map<String, SysUser> userMap = new HashMap<String, SysUser>();
				for (SysUser su : userList) {
					userMap.put(su.getUserFlow(), su);
				}
				model.addAttribute("userMap", userMap);
			}
		}
		return "jsres/hospital/reductionRotationDocList";
	}

	/**
	 * 调整学员方案减免
	 *
	 * @param doctorFlow
	 * @param rotationFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/operReduction")
	public String operReduction(String doctorFlow, String rotationFlow, String orgFlow, Model model) {
		if (StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(orgFlow)) {
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			if (doctor != null) {
				model.addAttribute("doctor", doctor);

				String degreeType = doctor.getDegreeCategoryId();
                if (com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)) {
					PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
					String content = resume.getUserResume();
					if (StringUtil.isNotBlank(content)) {
						UserResumeExtInfoForm userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
						model.addAttribute("userResumeExt", userResumeExt);
					}
				}
			}

            List<com.pinde.core.model.ResDoctorRecruit> recruitList = doctorRecruitBiz.searchRecruitByDoctor(doctorFlow);
			model.addAttribute("recruitList", recruitList);

			List<SchRotationDept> rotationDeptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);
			if (rotationDeptList != null && !rotationDeptList.isEmpty()) {
				Map<String, List<SchRotationDept>> rotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
				for (SchRotationDept srd : rotationDeptList) {
					String key = srd.getGroupFlow();
					if (rotationDeptListMap.get(key) == null) {
						List<SchRotationDept> srds = new ArrayList<SchRotationDept>();
						srds.add(srd);
						rotationDeptListMap.put(key, srds);
					} else {
						rotationDeptListMap.get(key).add(srd);
					}
				}
				model.addAttribute("rotationDeptListMap", rotationDeptListMap);
			}

			List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
			model.addAttribute("groupList", groupList);

			List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchSchDoctorDeptIgnoreStatus(doctorFlow, orgFlow);
			if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
				Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
				for (SchDoctorDept sdd : doctorDeptList) {
					String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
					doctorDeptMap.put(key, sdd);
				}
				model.addAttribute("doctorDeptMap", doctorDeptMap);
			}
		}
		return "jsres/hospital/operReduction";
	}

	@RequestMapping(value = "/saveReductionOper")
	@ResponseBody
	public String saveReductionOper(@RequestBody List<SchDoctorDept> doctorDeptList) {
		int result = doctorDeptBiz.editDoctorDeptList(doctorDeptList);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	//*******************************************************************************************

	@RequestMapping(value = "/baseInfo", method = {RequestMethod.GET})
	public String baseInfo(SysDept dept, Integer currentPage, HttpServletRequest request, Model model) {
		return "jsres/hospital/baseInfoMain";
	}

	@RequestMapping(value = "/baseInfoTeacher", method = {RequestMethod.GET})
	public String baseInfoTeacher(SysDept dept, Integer currentPage, HttpServletRequest request, Model model) {
		return "jsres/hospital/baseInfoMainTeacher";
	}

	@RequestMapping(value = "/deptSearch", method = {RequestMethod.POST, RequestMethod.GET})
	public String deptSearch(SysDept dept, Integer currentPage, HttpServletRequest request, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(currUser.getOrgFlow());
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);
		return "jsres/hospital/deptSearch";
	}

	@RequestMapping(value = "/exportDept")
	public void exportDept(SysDept dept, HttpServletResponse response, String isUnion) throws Exception {
//		List<SysDept> deptList = deptBiz.searchDept(dept);
		List<Map<String, String>> deptList = deptBiz.searchDeptByUnion(dept, isUnion);
		SysOrg org = orgBiz.readSysOrg(dept.getOrgFlow());
		String fileName = "科室信息.xls";
		if (org != null) {
			fileName = "【" + org.getOrgName() + "】" + fileName;
		}
		String[] titles = new String[]{
				"deptCode:科室代码",
				"deptName:科室名称"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, deptList, response.getOutputStream());
	}

	/**
	 * 带教导入
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/importUsers")
	public String importUsers() {
		return "jsres/hospital/importTeachings";
	}

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@RequestMapping(value = "/importTeachingFromExcel")
	@ResponseBody
	public String importTeachingFromExcel(MultipartFile file) {
		SysUser loginUser=GlobalContext.getCurrentUser();
		if (file.getSize() > 0) {
			try {
				Pair<Integer, List<String>> result = importTeachingFromExcel(file,loginUser);
				List<String> errorMsg = result.getRight();
				Integer count = result.getLeft();
				if (CollectionUtils.isEmpty(errorMsg)) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
				} else {
					StringBuilder sb = new StringBuilder();
					for (String msg : errorMsg) {
						sb.append(msg).append(System.lineSeparator());
					}
					sb.append("其余"+count+"用户信息正常导入系统");
					return sb.toString();
				}
			} catch (RuntimeException e) {
				logger.error("", e);
				return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	public Pair<Integer, List<String>> importTeachingFromExcel(MultipartFile file, SysUser user) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseExcelAndAudit(wb,user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	private Pair<Integer, List<String>> parseExcelAndAudit(Workbook wb,SysUser user){
		List<String> errorMsg = new ArrayList<>();
		int sheetNum = wb.getNumberOfSheets();
		int count = 0;
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum();
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}

			int row = -1;
			loop:
			for(int i = 1;i <= row_num; i++){
				row++;
				Row r =  sheet.getRow(i);
				SysUser sysUser = new SysUser();
				String userName;
				String idNo;
				String userPhone;
				List<String> allDeptFlows = new ArrayList<String>();
				String userCode;
				List<String> allRoleFlows = new ArrayList<String>();
				for (int j = 0; j < colnames.size(); j++) {
					String value = "";
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if (cell.getCellType().getCode() == 1) {
							value = cell.getStringCellValue().trim();
						} else {
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if("姓名".equals(colnames.get(j))){
						if(StringUtil.isEmpty(value)) {
							errorMsg.add("第"+(row+2)+"行姓名未填写，导入失败");
							continue loop;
						}
						userName = value;
						sysUser.setUserName(userName);
					}else if("身份证号".equals(colnames.get(j))){
						if(StringUtil.isNotEmpty(value) && !id_pattern.matcher(value).matches()) {
							errorMsg.add("第"+(row+2)+"行身份证号格式不对，导入失败");
							continue loop;
						}
						idNo = value;
						sysUser.setIdNo(idNo);
					} else if("电话号码".equals(colnames.get(j))){
						if(StringUtil.isNotEmpty(value) && !mobile_pattern.matcher(value).matches()) {
							errorMsg.add("第"+(row+2)+"行电话号码格式不对，导入失败");
							continue loop;
						}
						userPhone = value;
						sysUser.setUserPhone(userPhone);
					}else if("科室名称".equals(colnames.get(j))){
						if(StringUtils.isNotEmpty(value)){
							String[] mulDeptName = value.split(";");
							for (String deptName : mulDeptName) {
								if(StringUtils.isEmpty(deptName)){
									continue loop;
								}
								SysDept sysDept = deptBiz.readSysDeptByName(GlobalContext.getCurrentUser().getOrgFlow(),deptName);
								if (sysDept == null) {
									errorMsg.add("导入失败！第"+ (row+2) +"行，【"+deptName+"科室】不属于该机构！");
									continue loop;
								}
								if (sysDept != null) {
									allDeptFlows.add(sysDept.getDeptFlow());
								}
							}
						}
					}else if("用户名".equals(colnames.get(j))){
						if(StringUtil.isEmpty(value)) {
							errorMsg.add("第"+(row+2)+"行用户名未填写，导入失败");
							continue loop;
						}
						userCode = value;
						sysUser.setUserCode(userCode);
					} else if("角色".equals(colnames.get(j))){
						if(StringUtils.isNotEmpty(value)){
							String[] mulRoleName = value.split(";");
							for (String roleName : mulRoleName) {
								if(StringUtils.isEmpty(roleName)){
									continue loop;
								}
								if(!acceptedRoleNameList.contains(roleName)) {
									errorMsg.add("导入失败！第"+ (row+2) +"行，【"+roleName+"角色】不在允许的角色范围内！");
									continue loop;
								}

								SysRole sysRole = userRoleBiz.getByRoleName(roleName);
								if (sysRole == null) {
									errorMsg.add("导入失败！第"+ (row+2) +"行，【"+roleName+"角色】不属于该机构！");
									continue loop;
								}
								if (sysRole != null) {
									allRoleFlows.add(sysRole.getRoleFlow());
								}
							}
						}

					}
				}
				//验证惟一用户登录名
				if(StringUtil.isNotBlank(sysUser.getUserCode())){
					SysUserExample example=new SysUserExample();
                    example.createCriteria().andOrgFlowEqualTo(user.getOrgFlow()).andUserCodeEqualTo(sysUser.getUserCode())/*.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)*/;
					List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
					if(sysUserList != null && !sysUserList.isEmpty()){
						errorMsg.add("导入失败！第"+(row+2) +"行，当前系统已存在登录名为"+sysUser.getUserCode()+"的用户");
						continue;
					}
				}

				if(StringUtils.isNotBlank(sysUser.getUserPhone())){
					SysUserExample example=new SysUserExample();
					example.createCriteria().andUserPhoneEqualTo(sysUser.getUserPhone())/*.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)*/;
					List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
					if(CollectionUtils.isNotEmpty(sysUserList)){
						for(SysUser su : sysUserList){
							su.setUserPhone(null);
							sysUserMapper.updateByPrimaryKey(su);
							logger.info("phone number already exsits  ,user = {},set null",JSON.toJSONString(su));
						}
					}
				}

				if(StringUtil.isNotBlank(sysUser.getSexId())){
					sysUser.setSexName(UserSexEnum.getNameById(user.getSexId()));
				}
                sysUser.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
                sysUser.setDegreeName(com.pinde.core.common.enums.DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
                sysUser.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
                sysUser.setEducationName(com.pinde.core.common.enums.DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
				SysDept dept =deptBiz.readSysDept(sysUser.getDeptFlow());
				if(dept!=null)
				{
					sysUser.setDeptName(dept.getDeptName());
				}
				sysUser.setOrgFlow(user.getOrgFlow());
				sysUser.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
				userBiz.saveUser(sysUser);
				if(StringUtil.isNotBlank(sysUser.getDeptFlow())&&!allDeptFlows.contains(sysUser.getDeptFlow())){
					allDeptFlows.add(sysUser.getDeptFlow());
				}
				if(allDeptFlows.size()>0){
					userBiz.addUserDept(sysUser,allDeptFlows);
				}else {
					userBiz.disUserDept(sysUser);
				}
				//打开app权限
				String cfgCode = "jsres_teacher_app_login_"+sysUser.getUserFlow();
                String cfgValue = com.pinde.core.common.GlobalConstant.FLAG_Y;
				String cfgDesc = "是否开放带教app权限";
				JsresPowerCfg cfg = new JsresPowerCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(cfgValue);
				cfg.setCfgDesc(cfgDesc);
                cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				jsResPowerCfgBiz.save(cfg);
				for (String roleFlow : allRoleFlows) {
                    userRoleBiz.saveSysUserRole(sysUser.getUserFlow(), roleFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
				}

				count++;
			}
		}

		return Pair.of(count, errorMsg);
	}

	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
//		// EXCEL2003使用的是微软的文件系统
//		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//			return new HSSFWorkbook(inS);
//		}
//		// EXCEL2007使用的是OOM文件格式
//		if (POIXMLDocument.hasOOXMLHeader(inS)) {
//			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
//			return new XSSFWorkbook(OPCPackage.open(inS));
//		}
		try{
			return WorkbookFactory.create(inS);
		}catch (Exception e) {
			throw new IOException("不能解析的excel版本");
		}
	}

	@RequestMapping(value = "/exportUser")
	public void exportUser(SysUser user, String teacher, String head, String secretary, String moreDept, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleTeacher = null;
		String roleHead = null;
		String roleScretary = null;
		List<String> roleList = new ArrayList<String>();

        String isSelect = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(teacher)) {
            isSelect = com.pinde.core.common.GlobalConstant.FLAG_Y;
			roleTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
			roleList.add(roleTeacher);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(head)) {
            isSelect = com.pinde.core.common.GlobalConstant.FLAG_Y;
			roleHead = InitConfig.getSysCfg("res_head_role_flow");
			roleList.add(roleHead);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(secretary)) {
            isSelect = com.pinde.core.common.GlobalConstant.FLAG_Y;
			roleScretary = InitConfig.getSysCfg("res_secretary_role_flow");
			roleList.add(roleScretary);
		}
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(teacher) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(head) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(secretary)) {
			roleTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
			roleList.add(roleTeacher);
			roleHead = InitConfig.getSysCfg("res_head_role_flow");
			roleList.add(roleHead);
			roleScretary = InitConfig.getSysCfg("res_secretary_role_flow");
			roleList.add(roleScretary);
		}
		String examTeaRole = InitConfig.getSysCfg("osca_examtea_role_flow");

		user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		user.setIsForeign(moreDept);//用作暂存多科室查询字段
		List<SysUser> sysUserList = userBiz.searchResManageUserNotSelf(user, roleList, GlobalContext.getCurrentUser().getUserFlow(), isSelect, examTeaRole);

		Map<String, List<String>> sysUserRoleMap = new HashMap<String, List<String>>();
		if(CollectionUtils.isNotEmpty(sysUserList)){
			List<String> userFlows = sysUserList.stream().map(SysUser::getUserFlow).collect(Collectors.toList());
            String wsId = com.pinde.core.common.GlobalConstant.RES_WS_ID;
			int start = 0;
			int batchSize = 100;
			while (start < userFlows.size()) {
				int end = Math.min(start + batchSize, userFlows.size());
				List<String> batchList = userFlows.subList(start, end);
				List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(batchList, wsId);
				for (SysUserRole sysUserRole : sysUserRoleList) {
					String userFlow = sysUserRole.getUserFlow();
					if (sysUserRoleMap.containsKey(userFlow)) {
						List<String> list = sysUserRoleMap.get(userFlow);
						list.add(sysUserRole.getRoleFlow());
					} else {
						List<String> list = new ArrayList<String>();
						list.add(sysUserRole.getRoleFlow());
						sysUserRoleMap.put(userFlow, list);
					}
				}
				start += batchSize;
			}
		}

		roleTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
		roleHead = InitConfig.getSysCfg("res_head_role_flow");
		roleScretary = InitConfig.getSysCfg("res_secretary_role_flow");
		String teachingHead = InitConfig.getSysCfg("res_teaching_head_role_flow");
		String teachingSecretary = InitConfig.getSysCfg("res_teaching_secretary_role_flow");
		String hospitalLeader = InitConfig.getSysCfg("res_hospitalLeader_role_flow");
		for (SysUser su : sysUserList) {
			List<String> roles = sysUserRoleMap.get(su.getUserFlow());
			String roleName = "";
			if (roles != null && roles.size() > 0) {
				if (roles.contains(roleTeacher) && roleTeacher != null) {
					roleName += "带教老师";
				}
				if (roles.contains(roleHead) && roleHead != null) {
					if (StringUtil.isNotBlank(roleName)) {
						roleName += ",";
					}
					roleName += "科主任";
				}
				if (roles.contains(roleScretary) && roleScretary != null) {
					if (StringUtil.isNotBlank(roleName)) {
						roleName += ",";
					}
					roleName += "科秘";
				}
				if (roles.contains(teachingHead) && teachingHead != null) {
					if (StringUtil.isNotBlank(roleName)) {
						roleName += ",";
					}
					roleName += "教学主任";
				}
				if (roles.contains(teachingSecretary) && teachingSecretary != null) {
					if (StringUtil.isNotBlank(roleName)) {
						roleName += ",";
					}
					roleName += "教学秘书";
				}
				if (roles.contains(hospitalLeader) && hospitalLeader != null) {
					if (StringUtil.isNotBlank(roleName)) {
						roleName += ",";
					}
					roleName += "督导-评分专家";
				}
			}
			su.setAppLoginTime(roleName);
		}
		SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
		String fileName = "用户信息.xls";
		if (org != null) {
			fileName = "【" + org.getOrgName() + "】" + fileName;
		}
		String[] titles = new String[]{
				"userName:姓名",
				"userCode:用户名",
				"statusDesc:状态",
				"sexName:性别",
				"deptName:科室名称",
				"idNo:身份证号",
				"teacherLevel:师资级别",
				"userPhone:手机号",
				"userEmail:电子邮箱",
				"appLoginTime:角色"
		};
//		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, sysUserList, response.getOutputStream());
	}



	/**
	 * 科室维护
	 *
	 * @param dept
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deptList", method = {RequestMethod.POST, RequestMethod.GET})
	public String list(SysDept dept, Integer currentPage, HttpServletRequest request, Model model, String isUnion) {
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> list = deptBiz.searchDeptByUnion(dept, isUnion);
//		List<SysDept> deptList = deptBiz.searchDept(dept);
		model.addAttribute("deptList", list);
		return "jsres/hospital/deptList";
	}

	/**
	 * 删除科室
	 *
	 * @param dept
	 * @return
	 */
	@RequestMapping(value = "/deleteDept", method = RequestMethod.GET)
	public @ResponseBody
	String deleteDept(SysDept dept,HttpServletRequest request) {
		SysUser user = GlobalContext.getCurrentUser();
		deptBiz.saveDept(dept);
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(dept.getRecordStatus())) {
			SysLog log = new SysLog();
			//log.setReqTypeId(ReqTypeEnum.GET.getId());
			log.setOperId(OperTypeEnum.LogUpdate.getId());
			log.setOperName(OperTypeEnum.LogUpdate.getName());
			log.setReqTypeId(ReqTypeEnum.PUT.getId());
			log.setLogDesc(user.getUserName()+OperTypeEnum.LogUpdate.getName()+"了"+dept.getDeptName()+"轮转科室，"+"登录IP["+request.getRemoteAddr()+"]");
            log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			logMapper.insert(log);
			schExamCfgBiz.deleteSchExamStandardDeptByDeptId(dept.getDeptFlow());
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 编辑科室
	 *
	 * @param deptFlow
	 * @return
	 */
	@RequestMapping(value = {"/editDept"}, method = RequestMethod.GET)
	public ModelAndView editDept(@RequestParam(value = "deptFlow", required = false) String deptFlow) {
		ModelAndView mav = new ModelAndView();
		if (StringUtil.isNotBlank(deptFlow)) {
			SysDept sysDept = deptBiz.readSysDept(deptFlow);
			mav.addObject("sysDept", sysDept);
		}
		mav.setViewName("jsres/hospital/editDept");
		return mav;
	}

	@RequestMapping(value = "/userSearch", method = {RequestMethod.POST, RequestMethod.GET})
	public String userSearch(SysUser user, Integer currentPage, HttpServletRequest request, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(currUser.getOrgFlow());
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);
		return "jsres/hospital/userSearch";
	}


	@RequestMapping(value = "/commonSzManage", method = {RequestMethod.POST, RequestMethod.GET})
	public String commonSzManage(HttpServletRequest request, Model model,String teacherLevelId) {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(currUser.getOrgFlow());
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);
		model.addAttribute("teacherLevelId", teacherLevelId);
		return "jsres/hospital/commonSzSearch";
	}

	@RequestMapping(value = "/responsibleTutor", method = {RequestMethod.POST, RequestMethod.GET})
	public String responsibleTutor(HttpServletRequest request, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(currUser.getOrgFlow());
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);
		return "jsres/hospital/responsibleTutorSearch";
	}

	/**
	 * 师资维护
	 *
	 * @param user
	 * @param teacher
	 * @param head
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userList", method = {RequestMethod.POST, RequestMethod.GET})
	public String userList(SysUser user, String teacher, String head, String secretary, String moreDept, String[] userRoleList, Integer currentPage, HttpServletRequest request, Model model) {
		String roleTeacher = null;
		String roleHead = null;
		String roleScretary = null;
		List<String> roleList = new ArrayList<String>();
        String isSelect = com.pinde.core.common.GlobalConstant.FLAG_N;
		if(ArrayUtils.isNotEmpty(userRoleList)) {
            isSelect = com.pinde.core.common.GlobalConstant.FLAG_Y;
			roleList = Arrays.stream(userRoleList).collect(Collectors.toList());
		}else {
			roleList.add(InitConfig.getSysCfg("res_teacher_role_flow"));
			roleList.add(InitConfig.getSysCfg("res_head_role_flow"));
			roleList.add(InitConfig.getSysCfg("res_secretary_role_flow"));
			roleList.add(InitConfig.getSysCfg("res_teaching_head_role_flow"));
			roleList.add(InitConfig.getSysCfg("res_teaching_secretary_role_flow"));
			roleList.add(InitConfig.getSysCfg("res_hospitalLeader_role_flow"));
		}
		/*if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(teacher)) {
			isSelect = com.pinde.core.common.GlobalConstant.FLAG_Y;
			roleTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
			roleList.add(roleTeacher);
		}
		if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(head)) {
			isSelect = com.pinde.core.common.GlobalConstant.FLAG_Y;
			roleHead = InitConfig.getSysCfg("res_head_role_flow");
			roleList.add(roleHead);
		}
		if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(secretary)) {
			isSelect = com.pinde.core.common.GlobalConstant.FLAG_Y;
			roleScretary = InitConfig.getSysCfg("res_secretary_role_flow");
			roleList.add(roleScretary);
		}
		if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(teacher) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(head) && !com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(secretary)) {
			roleTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
			roleList.add(roleTeacher);
			roleHead = InitConfig.getSysCfg("res_head_role_flow");
			roleList.add(roleHead);
			roleScretary = InitConfig.getSysCfg("res_secretary_role_flow");
			roleList.add(roleScretary);
		}*/
		String examTeaRole = InitConfig.getSysCfg("osca_examtea_role_flow");

		PageHelper.startPage(currentPage, getPageSize(request));
		user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		user.setIsForeign(moreDept);//用作暂存多科室查询字段
		List<SysUser> sysUserList = userBiz.searchResManageUserNotSelf2(user, roleList, GlobalContext.getCurrentUser().getUserFlow(), isSelect, examTeaRole);
		model.addAttribute("sysUserList", sysUserList);

		if(CollectionUtils.isNotEmpty(sysUserList)){
			List<String> userFlows = sysUserList.stream().map(SysUser::getUserFlow).collect(Collectors.toList());

            String wsId = com.pinde.core.common.GlobalConstant.RES_WS_ID;
			List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(userFlows, wsId);
			Map<String, List<String>> sysUserRoleMap = new HashMap<String, List<String>>();
			for (SysUserRole sysUserRole : sysUserRoleList) {
				String userFlow = sysUserRole.getUserFlow();
				if (sysUserRoleMap.containsKey(userFlow)) {
					List<String> list = sysUserRoleMap.get(userFlow);
					list.add(sysUserRole.getRoleFlow());
				} else {
					List<String> list = new ArrayList<String>();
					list.add(sysUserRole.getRoleFlow());
					sysUserRoleMap.put(userFlow, list);
				}
			}
			model.addAttribute("sysUserRoleMap", sysUserRoleMap);


			SysUserDeptExample example = new SysUserDeptExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlows);
			List<SysUserDept> sysUserDeptList = userDeptMapper.selectByExample(example);
			Map<String, List<String>> sysUserDeptMap = new HashMap<String, List<String>>();
			for (SysUserDept sysUserDept : sysUserDeptList) {
				String userFlow = sysUserDept.getUserFlow();
				if (sysUserDeptMap.containsKey(userFlow)) {
					List<String> list = sysUserDeptMap.get(userFlow);
					list.add(sysUserDept.getDeptName());
				} else {
					List<String> list = new ArrayList<String>();
					list.add(sysUserDept.getDeptName());
					sysUserDeptMap.put(userFlow, list);
				}
			}
			Map<String,String> sysUserDeptNameMap = new HashMap<>();
			for(Map.Entry<String, List<String>> entry : sysUserDeptMap.entrySet()){
				String key = entry.getKey();
				List<String> list = entry.getValue();
				sysUserDeptNameMap.put(key,StringUtils.join(list,","));
			}
			model.addAttribute("sysUserDeptNameMap", sysUserDeptNameMap);
		}
		return "jsres/hospital/userList";
	}


	@RequestMapping(value = "/commonSzList", method = {RequestMethod.POST, RequestMethod.GET})
	public String commonSzList(ResTeacherTraining resTeacherTraining, Integer currentPage, HttpServletRequest request, Model model,String isQueryTutor) {
		if(null == resTeacherTraining){
			resTeacherTraining = new ResTeacherTraining();
		}
//		resTeacherTraining.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//		resTeacherTraining.setTeacherLevelName(JsResTeacherLevelEnum.getNameById(resTeacherTraining.getTeacherLevelId()));
        resTeacherTraining.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> sysUserList = teacherTrainingMapper.selectByCondition(resTeacherTraining);
		model.addAttribute("sysUserList", sysUserList);


		if(CollectionUtils.isNotEmpty(sysUserList)){
			List<String> userFlows = sysUserList.stream().map(ResTeacherTraining::getRecordFlow).collect(Collectors.toList());

//			String wsId = com.pinde.core.common.GlobalConstant.RES_WS_ID;
//			List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(userFlows, wsId);
//			Map<String, List<String>> sysUserRoleMap = new HashMap<String, List<String>>();
//			for (SysUserRole sysUserRole : sysUserRoleList) {
//				String userFlow = sysUserRole.getUserFlow();
//				if (sysUserRoleMap.containsKey(userFlow)) {
//					List<String> list = sysUserRoleMap.get(userFlow);
//					list.add(sysUserRole.getRoleFlow());
//				} else {
//					List<String> list = new ArrayList<String>();
//					list.add(sysUserRole.getRoleFlow());
//					sysUserRoleMap.put(userFlow, list);
//				}
//			}
//			model.addAttribute("sysUserRoleMap", sysUserRoleMap);

			SysUserDeptExample example = new SysUserDeptExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlows);
			List<SysUserDept> sysUserDeptList = userDeptMapper.selectByExample(example);
			Map<String, List<String>> sysUserDeptMap = new HashMap<String, List<String>>();
			for (SysUserDept sysUserDept : sysUserDeptList) {
				String userFlow = sysUserDept.getUserFlow();
				if (sysUserDeptMap.containsKey(userFlow)) {
					List<String> list = sysUserDeptMap.get(userFlow);
					list.add(sysUserDept.getDeptName());
				} else {
					List<String> list = new ArrayList<String>();
					list.add(sysUserDept.getDeptName());
					sysUserDeptMap.put(userFlow, list);
				}
			}
			Map<String,String> sysUserDeptNameMap = new HashMap<>();
			for(Map.Entry<String, List<String>> entry : sysUserDeptMap.entrySet()){
				String key = entry.getKey();
				List<String> list = entry.getValue();
				sysUserDeptNameMap.put(key,StringUtils.join(list,","));
			}
			model.addAttribute("sysUserDeptNameMap", sysUserDeptNameMap);
		}
		model.addAttribute("isQueryTutor", isQueryTutor);
		return "jsres/hospital/commonSzList";
	}

	@RequestMapping(value = "/exportSzList", method = {RequestMethod.POST, RequestMethod.GET})
	public void exportSzList(ResTeacherTraining resTeacherTraining, Integer currentPage, HttpServletRequest request, Model model,String isQueryTutor, HttpServletResponse response) throws Exception {
		if(null == resTeacherTraining){
			resTeacherTraining = new ResTeacherTraining();
		}
        resTeacherTraining.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<ResTeacherTraining> sysUserList = teacherTrainingMapper.selectByConditionAddUserDept(resTeacherTraining);
		model.addAttribute("sysUserList", sysUserList);

		String fileName;
		String[] titles;
		SysOrg org = orgBiz.readSysOrg(resTeacherTraining.getOrgFlow());
        if (Objects.equals(isQueryTutor, com.pinde.core.common.GlobalConstant.FLAG_Y)) {
			fileName = "责任导师信息.xls";
		}else{
			fileName = "师资信息.xls";
		}
		titles = new String[]{
				"doctorName:姓名",
				"sexName:性别",
				"userPhone:手机号",
				"technicalTitle:技术职称",
				"speName:专业",
				"allUserDeptNames:科室名称",
				"trainingYear:培训年份",
				"certificateNo:证书编号",
				"teacherLevelName:师资级别",
				"isResponsibleTutor:是否责任导师"
		};

		if (org != null) {
			fileName = "【" + org.getOrgName() + "】" + fileName;
		}

		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, sysUserList, response.getOutputStream());
	}

	/**
	 * 编辑用户信息
	 *
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/editUser"})
	public String editUser(String userFlow, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		sysDept.setOrgFlow(currUser.getOrgFlow());
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);
		if (StringUtil.isNotBlank(userFlow)) {
			SysUser sysUser = userBiz.readSysUser(userFlow);
			model.addAttribute("sysUser", sysUser);

            List<SysUserRole> userRoleList = userRoleBiz.getByUserFlowAndWsid(userFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
			List<String> roleFlowList = userRoleList.stream().map(vo -> vo.getRoleFlow()).collect(Collectors.toList());
			model.addAttribute("roleFlowList", roleFlowList);

			List<SysUserDept> userDepts = userBiz.searchUserDeptByUser(userFlow);
			if (userDepts != null && !userDepts.isEmpty()) {
				Map<String, SysUserDept> userDeptMap = new HashMap<String, SysUserDept>();
				for (SysUserDept sud : userDepts) {
					userDeptMap.put(sud.getDeptFlow(), sud);
				}
				model.addAttribute("userDeptMap", userDeptMap);
			}
		}

		return "jsres/hospital/editUser";
	}

	/**
	 * 停用原因编辑
	 *
	 * @param userFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/reasonForLockUser"})
	public String reasonForLockUser(String userFlow, Model model) {
		model.addAttribute("userFlow", userFlow);
		return "jsres/hospital/reasonForLockUser";
	}

	/**
	 * 基地双向评分
	 *
	 * @param gradeRole
	 * @param deptFlow
	 * @param userName
	 * @param operStartDate
	 * @param operEndDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/gradeSearch"})
	public String gradeSearch(
			String gradeRole,
			String deptFlow,
			String recOrgFlow,
			String userName,
			String operStartDate,
			String operEndDate,
			String role,
			String sessionNumber,
			Model model, Integer currentPage, HttpServletRequest request
	) {

		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}
		if (currentPage == null)
			currentPage = 1;

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		//省厅下基地及协同基地
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
			String orgFlowRec = GlobalContext.getCurrentUser().getOrgFlow();
			SysOrg org = orgBiz.readSysOrg(orgFlowRec);
            if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
				org.setOrgCityId("");
				List<SysOrg> sysOrgList = orgBiz.searOrgTeacherRoleCheckUser(org);
				model.addAttribute("sysOrgList", sysOrgList);
			}
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
				List<SysOrg> sysOrgList = orgBiz.searOrgTeacherRoleCheckUser(org);
				model.addAttribute("sysOrgList", sysOrgList);
			}
			if (StringUtil.isNotBlank(recOrgFlow)) {
				orgFlow = recOrgFlow;
			} else {
				String date = DateUtil.getCurrDateTime("yyyy");
				model.addAttribute("currDate", date);
				int d = Integer.valueOf(date);
				d = d - 1;
				date = String.valueOf(d);
				model.addAttribute("PreviouYearDate", date);
				int s = Integer.valueOf(date);
				s = s - 1;
				date = String.valueOf(s);
				model.addAttribute("FirstTwoYearsDate", date);
				int o = Integer.valueOf(date);
				o = o - 1;
				date = String.valueOf(o);
				model.addAttribute("ZeroTwoYearsDate", date);
				return "jsres/hospital/gradeSearch";
			}
		}

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("operStartDate", operStartDate);
		paramMap.put("operEndDate", operEndDate);
		paramMap.put("userName", userName);
		paramMap.put("deptFlow", deptFlow);

		//查出当前机构的所有科室
		List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
		model.addAttribute("depts", deptList);

		List<String> keys = new ArrayList<String>();
		Object waitSort = null;
		Map<String, Object> scoreMap = new HashMap<String, Object>();
		//专业基地下的科室
		List<String> speDepts = new ArrayList<>();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(role)) {
			ResTrainingSpeDept resTrainingSpeDept = new ResTrainingSpeDept();
			resTrainingSpeDept.setOrgFlow(orgFlow);
			resTrainingSpeDept.setTrainingSpeId(GlobalContext.getCurrentUser().getResTrainingSpeId());
			List<ResTrainingSpeDept> resTrainingSpeDepts = resTrainingSpeDeptBiz.search(resTrainingSpeDept);
			if (resTrainingSpeDepts != null && resTrainingSpeDepts.size() > 0) {
				speDepts = resTrainingSpeDepts.stream().map(resTrainingSpeDept1 -> resTrainingSpeDept1.getDeptFlow()).collect(Collectors.toList());
			}
		}
		if ("teacher".equals(gradeRole)) {
			//带教flow
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			paramMap.put("roleFlow", teacherRoleFlow);

            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList", assessCfgList);
			//总标准分
			int baseScore = 0;
			for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
				if (resAssessCfgTitleForm.getItemList() != null && !resAssessCfgTitleForm.getItemList().isEmpty()) {
					for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
						int s = Integer.parseInt(resAssessCfgItemForm.getScore());
						baseScore += s;
					}
				}
			}
			model.addAttribute("baseScore", baseScore);
			//查出当前机构的所有带教老师
			List<SysUser> userList;
            if ((com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(role)) && (speDepts == null || speDepts.size() == 0)) {
				//没有配置科室的专业基地、专业基地秘书
				userList = null;
			} else {
				PageHelper.startPage(currentPage, getPageSize(request));
				paramMap.put("speDepts", speDepts);
				userList = resGradeBiz.getUserByRec(paramMap);
			}
			model.addAttribute("datas", userList);
			waitSort = userList;
			if (userList != null && !userList.isEmpty()) {
				for (SysUser su : userList) {
					keys.add(su.getUserFlow());
				}
			}
			paramMap.put("teacherFlows", keys);
		} else if ("head".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList", assessCfgList);
			//总标准分
			int baseScore = 0;
			for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
				if (resAssessCfgTitleForm.getItemList() != null && !resAssessCfgTitleForm.getItemList().isEmpty()) {
					for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
						int s = Integer.parseInt(resAssessCfgItemForm.getScore());
						baseScore += s;
					}
				}
			}
			model.addAttribute("baseScore", baseScore);

			List<SysDept> depts;
            if ((com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(role)) && (speDepts == null || speDepts.size() == 0)) {
				//没有配置科室的专业基地、专业基地秘书
				depts = null;
			} else {
				PageHelper.startPage(currentPage, getPageSize(request));
				paramMap.put("speDepts", speDepts);
				depts = resGradeBiz.getDeptByRec(paramMap);
			}
			model.addAttribute("datas", depts);
			waitSort = depts;
			if (depts != null && !depts.isEmpty()) {
				for (SysDept sd : depts) {
					keys.add(sd.getDeptFlow());
				}
			}
			paramMap.put("deptFlows", keys);
		}

		if (keys != null && keys.size() > 0) {
			List<String> sessionNumbers = new ArrayList<>();

			if (StringUtil.isNotBlank(sessionNumber)) {
				sessionNumbers.add(sessionNumber);
			} else {

				String date = DateUtil.getCurrDateTime("yyyy");

				int d0 = Integer.valueOf(date);
				d0 = d0 - 3;
				sessionNumbers.add(String.valueOf(d0));
				model.addAttribute("ZeroTwoYearsDate", String.valueOf(d0));

				int d = Integer.valueOf(date);
				d = d - 2;
				sessionNumbers.add(String.valueOf(d));
				model.addAttribute("FirstTwoYearsDate", String.valueOf(d));
				int d2 = Integer.valueOf(date);
				d2 = d2 - 1;
				sessionNumbers.add(String.valueOf(d2));
				model.addAttribute("PreviouYearDate", String.valueOf(d2));

				sessionNumbers.add(date);
				model.addAttribute("currDate", date);
			}
			paramMap.put("deptFlow", "");//评分分数不过滤科室
			paramMap.put("sessionNumbers", sessionNumbers);
			model.addAttribute("sessionNumbers", sessionNumbers);
			Map<String, Object> avgMap = resGradeBiz.getJsresGradeAvgScoreByProcessSessionNumber(paramMap);
			model.addAttribute("avgMap", avgMap);
			Map<String, Object> allAvgMap = resGradeBiz.getJsresGradeAvgScoreByProcess(paramMap);
			model.addAttribute("allAvgMap", allAvgMap);
//			//获取评分数据
//			List<Map<String, String>> recList = resGradeBiz.getJsresRecContentByProcess(paramMap);
//
//			if (StringUtil.isNotBlank(sessionNumber)) {
//				paramMap.put("sessionNumber", sessionNumber);
//				if(recList!=null&&recList.size()>0) {
//					List<Map<String, String>> recDateList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
//					for(Map<String, String> m:recList)
//					{
//						if(sessionNumber.equals(m.get("sessionNumber")))
//							recDateList.add(m);
//					}
//					if (recDateList != null && !recDateList.isEmpty()) {
//						Map<String, Float> recDateAvgMap = avg(recDateList);
//						model.addAttribute("recDateAvgMap", recDateAvgMap);
//					}
//				}
//			} else {
//				String date = DateUtil.getCurrDateTime("yyyy");
//				model.addAttribute("currDate", date);
//				paramMap.put("sessionNumber", date);
//
//				if(recList!=null&&recList.size()>0) {
//					List<Map<String, String>> recCurrDateList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
//					for(Map<String, String> m:recList)
//					{
//						if(date.equals(m.get("sessionNumber")))
//							recCurrDateList.add(m);
//					}
//					if (recCurrDateList != null && !recCurrDateList.isEmpty()) {
//						Map<String, Float> recCurrDateAvgMap = avg(recCurrDateList);
//						model.addAttribute("recCurrDateAvgMap", recCurrDateAvgMap);
//					}
//				}
//				int d = Integer.valueOf(date);
//				d = d - 1;
//				date = String.valueOf(d);
//				model.addAttribute("PreviouYearDate", date);
//				paramMap.put("sessionNumber", date);
//
//				if(recList!=null&&recList.size()>0) {
//					List<Map<String, String>> recpreviouYearList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
//					for(Map<String, String> m:recList)
//					{
//						if(date.equals(m.get("sessionNumber")))
//							recpreviouYearList.add(m);
//					}
//					if (recpreviouYearList != null && !recpreviouYearList.isEmpty()) {
//						Map<String, Float> recpreviouYearAvgMap = avg(recpreviouYearList);
//						model.addAttribute("recpreviouYearAvgMap", recpreviouYearAvgMap);
//					}
//				}
//				int s = Integer.valueOf(date);
//				s = s - 1;
//				date = String.valueOf(s);
//				model.addAttribute("FirstTwoYearsDate", date);
//				paramMap.put("sessionNumber", date);
//				if(recList!=null&&recList.size()>0) {
//					List<Map<String, String>> recFirstTwoYearsList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
//					for(Map<String, String> m:recList)
//					{
//						if(date.equals(m.get("sessionNumber")))
//							recFirstTwoYearsList.add(m);
//					}
//					if (recFirstTwoYearsList != null && !recFirstTwoYearsList.isEmpty()) {
//						Map<String, Float> recFirstTwoYearsAvgMap = avg(recFirstTwoYearsList);
//						model.addAttribute("recFirstTwoYearsAvgMap", recFirstTwoYearsAvgMap);
//					}
//				}
//			}
//
//			if (recList != null && !recList.isEmpty()) {
//				Map<String, Float> avgMap = avg(recList);
			if (waitSort != null) {
				final Map<String, Object> scoreMapTemp = allAvgMap;
				if ("teacher".equals(gradeRole)) {
					List<SysUser> userList = (List<SysUser>) waitSort;
					Collections.sort(userList, new Comparator<SysUser>() {
						@Override
						public int compare(SysUser u1, SysUser u2) {
							String k1 = u1.getUserFlow();
							String k2 = u2.getUserFlow();
							Float s1 = scoreMapTemp.get(k1) == null ? 0.0f : Float.valueOf((String) scoreMapTemp.get(k1));
							Float s2 = scoreMapTemp.get(k2) == null ? 0.0f : Float.valueOf((String) scoreMapTemp.get(k2));
							if (s1 == null) {
								s1 = 0f;
							}
							if (s2 == null) {
								s2 = 0f;
							}
							Float result = s2 - s1;

							return result > 0 ? 1 : result == 0 ? 0 : -1;
						}

					});
				} else if ("head".equals(gradeRole)) {
					List<SysDept> depts = (List<SysDept>) waitSort;
					Collections.sort(depts, new Comparator<SysDept>() {
						@Override
						public int compare(SysDept d1, SysDept d2) {
							String k1 = d1.getDeptFlow();
							String k2 = d2.getDeptFlow();
							Float s1 = scoreMapTemp.get(k1) == null ? 0.0f : Float.valueOf((String) scoreMapTemp.get(k1));
							Float s2 = scoreMapTemp.get(k2) == null ? 0.0f : Float.valueOf((String) scoreMapTemp.get(k2));
							if (s1 == null) {
								s1 = 0f;
							}
							if (s2 == null) {
								s2 = 0f;
							}
							Float result = s2 - s1;
							return result > 0 ? 1 : result == 0 ? 0 : -1;
						}

					});
				}
			}
//				model.addAttribute("avgMap", avgMap);
//			}
		}
		model.addAttribute("scoreMap", scoreMap);
		model.addAttribute("role", role);
		return "jsres/hospital/gradeSearch";
	}

	//
	@RequestMapping(value = {"/gradeItemEval"})
	public String gradeItemEval(
			String gradeRole,
			String keyCode,
			String operStartDate,
			String operEndDate,
			String sessionNumber,
			Model model, HttpServletRequest request
	) {

		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}
		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("operStartDate", operStartDate);
		paramMap.put("operEndDate", operEndDate);

		List<String> keys = new ArrayList<String>();
		keys.add(keyCode);
		if ("teacher".equals(gradeRole)) {
			SysUser user = userBiz.readSysUser(keyCode);
			model.addAttribute("data", user);
			paramMap.put("teacherFlow", keyCode);
			//带教flow
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			paramMap.put("roleFlow", teacherRoleFlow);
            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId", recType);
			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList", assessCfgList);
		} else if ("head".equals(gradeRole)) {
			SysDept dept = deptBiz.readSysDept(keyCode);
			model.addAttribute("data", dept);
			paramMap.put("deptFlow", keyCode);
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList", assessCfgList);
		}

		if (keys != null && keys.size() > 0) {
			//获取评分数据
			List<Map<String, String>> recList = resGradeBiz.getJsresRecContentByProcess(paramMap);
			if (StringUtil.isNotBlank(sessionNumber)) {
				paramMap.put("sessionNumber", sessionNumber);
				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recDateList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
					for (Map<String, String> m : recList) {
						if (sessionNumber.equals(m.get("sessionNumber")))
							recDateList.add(m);
					}
					if (recDateList != null && !recDateList.isEmpty()) {
						Map<String, Float> recDateAvgMap = avg(recDateList);
						model.addAttribute("recDateAvgMap", recDateAvgMap);
					}
				}
			} else {
				String date = DateUtil.getCurrDateTime("yyyy");
				model.addAttribute("currDate", date);
				paramMap.put("sessionNumber", date);

				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recCurrDateList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
					for (Map<String, String> m : recList) {
						if (date.equals(m.get("sessionNumber")))
							recCurrDateList.add(m);
					}
					if (recCurrDateList != null && !recCurrDateList.isEmpty()) {
						Map<String, Float> recCurrDateAvgMap = avg(recCurrDateList);
						model.addAttribute("recCurrDateAvgMap", recCurrDateAvgMap);
					}
				}
				int d = Integer.valueOf(date);
				d = d - 1;
				date = String.valueOf(d);
				model.addAttribute("PreviouYearDate", date);
				paramMap.put("sessionNumber", date);

				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recpreviouYearList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
					for (Map<String, String> m : recList) {
						if (date.equals(m.get("sessionNumber")))
							recpreviouYearList.add(m);
					}
					if (recpreviouYearList != null && !recpreviouYearList.isEmpty()) {
						Map<String, Float> recpreviouYearAvgMap = avg(recpreviouYearList);
						model.addAttribute("recpreviouYearAvgMap", recpreviouYearAvgMap);
					}
				}
				int s = Integer.valueOf(date);
				s = s - 1;
				date = String.valueOf(s);
				model.addAttribute("FirstTwoYearsDate", date);
				paramMap.put("sessionNumber", date);
				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recFirstTwoYearsList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
					for (Map<String, String> m : recList) {
						if (date.equals(m.get("sessionNumber")))
							recFirstTwoYearsList.add(m);
					}
					if (recFirstTwoYearsList != null && !recFirstTwoYearsList.isEmpty()) {
						Map<String, Float> recFirstTwoYearsAvgMap = avg(recFirstTwoYearsList);
						model.addAttribute("recFirstTwoYearsAvgMap", recFirstTwoYearsAvgMap);
					}
				}
				int o = Integer.valueOf(date);
				o = o - 1;
				date = String.valueOf(o);
				model.addAttribute("ZeroTwoYearsDate", date);
				paramMap.put("sessionNumber", date);
				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recZeroTwoYearsList = new ArrayList<>();
					for (Map<String, String> m : recList) {
						if (date.equals(m.get("sessionNumber")))
							recZeroTwoYearsList.add(m);
					}
					if (recZeroTwoYearsList != null && !recZeroTwoYearsList.isEmpty()) {
						Map<String, Float> recZeroTwoYearsAvgMap = avg(recZeroTwoYearsList);
						model.addAttribute("recZeroTwoYearsAvgMap", recZeroTwoYearsAvgMap);
					}
				}
			}

			if (recList != null && recList.size() > 0) {
				Map<String, Float> avgMap = avg(recList);
				model.addAttribute("avgMap", avgMap);
			}
		}
		return "jsres/hospital/gradeItemEval";
	}

	private Map<String, Float> avg(List<Map<String, String>> recList) {
		//均分Map
		Map<String, Float> avgMap = new HashMap<String, Float>();
		String count = "Count";
		String total = "Total";
		for (Map<String, String> map : recList) {
			String key = map.get("key");

			putMapVal(avgMap, key + count, 1f);

			String content = map.get("content");

			Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
			if (gradeMap != null && !gradeMap.isEmpty()) {
				for (String gk : gradeMap.keySet()) {
					Object o = gradeMap.get(gk);
					if (o instanceof Map) {
						Map<String, String> dataMap = (Map<String, String>) o;
						if (dataMap != null) {
							Float score = 0f;
							try {
								String scoreS = dataMap.get("score");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
								logger.error("", e);
							}

							putMapVal(avgMap, key + "_" + gk, score);
						}

					} else {
						Float score = 0f;
						try {
							String scoreS = (String) o;
							score = Float.valueOf(scoreS);
						} catch (Exception e) {
							logger.error("", e);
						}

						putMapVal(avgMap, key + "_" + total, score);
					}
				}

			}
		}

		if (!avgMap.isEmpty()) {
			Set<String> keySet = avgMap.keySet();
			for (String k : keySet) {
				if (k != null) {
					int ki = k.indexOf("_");
					if (!(ki < 0)) {
						String dataKey = k.substring(0, ki);

						Float itemTotal = avgMap.get(k);
						if (itemTotal != null) {
							Float countUser = avgMap.get(dataKey + count);
							if (itemTotal != 0 && countUser != 0) {
								Float result = itemTotal / countUser;
								result = new BigDecimal(result).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
								avgMap.put(k, result);
							}
						}
					}
				}
			}
		}
		return avgMap;
	}

	private void putMapVal(Map<String, Float> map, String key, Float val) {
		if (map != null) {
			Float v = map.get(key);
			if (v == null) {
				v = val;
			} else {
				v += val;
			}
			v = new BigDecimal(v).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
			map.put(key, v);
		}
	}

	/**
	 * 基地双向评分
	 *
	 * @param gradeRole
	 * @param keyCode
	 * @param operStartDate
	 * @param operEndDate
	 * @param date
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/gradeSearchDoc"})
	public String gradeSearchDoc(
			String gradeRole,
			String keyCode,
			String operStartDate,
			String operEndDate,
			String date,
			Model model
	) {
		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String, Object> scoreSumMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("sessionNumber", date);
		paramMap.put("operStartDate", operStartDate);
		paramMap.put("operEndDate", operEndDate);

		if ("teacher".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();

			paramMap.put("teacherFlow", keyCode);
		} else if ("head".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();

			paramMap.put("deptFlow", keyCode);
		}
//		List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
		Map<String, List<ResAssessCfgTitleForm>> assessCfgMap = assessCfgBiz.getParsedGradeMap(cfgCode);
		//获取评分数据
		List<Map<String, String>> recList = resGradeBiz.getJsresRecContentByProcess(paramMap);
		if (recList != null && !recList.isEmpty()) {
			model.addAttribute("recList", recList);

			Map<String, Float> scoreMap = new HashMap<String, Float>();

			for (Map<String, String> map : recList) {
				String operUserFlow = map.get("operUserFlow") + map.get("recFlow");
				int scoreSum = 0;
				String cfgFlow = map.get("cfgFlow");
				List<ResAssessCfgTitleForm> assessCfgList = assessCfgMap.get(cfgFlow);
				if (null==assessCfgList || assessCfgList.isEmpty()){
					continue;
				}
				for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
					if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
						for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
							int s = Integer.parseInt(resAssessCfgItemForm.getScore());
							scoreSum += s;
						}
					}
				}
				scoreSumMap.put(operUserFlow, scoreSum);
				String content = map.get("content");

				Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
				if (gradeMap != null && !gradeMap.isEmpty()) {
					for (String gk : gradeMap.keySet()) {
						Object o = gradeMap.get(gk);
						Float score = 0f;
						if (o instanceof Map) {
							Map<String, String> dataMap = (Map<String, String>) o;
							if (dataMap != null) {
								try {
									String scoreS = dataMap.get("score");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
									logger.error("", e);
								}

								putMapVal(scoreMap, operUserFlow + gk, score);
							}
						} else {
							try {
								String scoreS = (String) gradeMap.get("totalScore");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
								logger.error("", e);
							}

							putMapVal(scoreMap, operUserFlow, score);
						}
					}

				}
			}

			model.addAttribute("scoreMap", scoreMap);

			final Map<String, Float> scoreMapTemp = scoreMap;
			Collections.sort(recList, new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> m1, Map<String, String> m2) {
					String k1 = m1.get("operUserFlow") + m1.get("recFlow");
					String k2 = m2.get("operUserFlow") + m2.get("recFlow");
					Float s1 = scoreMapTemp.get(k1);
					Float s2 = scoreMapTemp.get(k2);
					Float result = s2 - s1;

					return result > 0 ? 1 : result == 0 ? 0 : -1;
				}

			});
		}

		model.addAttribute("scoreSumMap", scoreSumMap);
		model.addAttribute("assessCfgMap", assessCfgMap);

		return "jsres/hospital/gradeSearchDoc";
	}

	@RequestMapping(value = {"/gradeSearchDocForUni"})
	public String gradeSearchDocForUni(
			String gradeRole,
			String keyCode,
			String operStartDate,
			String operEndDate,
			String date,
			Model model
	) {
		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		SysOrg org = orgBiz.readSysOrg(orgFlow);

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String, Object> scoreSumMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("sessionNumber", date);
		paramMap.put("operStartDate", operStartDate);
		paramMap.put("operEndDate", operEndDate);
		paramMap.put("org", org);
		if ("teacher".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();

			paramMap.put("teacherFlow", keyCode);
		} else if ("head".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();

			paramMap.put("deptFlow", keyCode);
		}
		List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
		//获取评分数据
		List<Map<String, String>> recList = resGradeBiz.getRecContentByProcessForUni(paramMap);
		if (recList != null && !recList.isEmpty()) {
			model.addAttribute("recList", recList);

			Map<String, Float> scoreMap = new HashMap<String, Float>();

			for (Map<String, String> map : recList) {
				String operUserFlow = map.get("operUserFlow") + map.get("recFlow");
				int scoreSum = 0;
				for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
					for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
						int s = Integer.parseInt(resAssessCfgItemForm.getScore());
						scoreSum += s;
					}
				}
				scoreSumMap.put(operUserFlow, scoreSum);
				String content = map.get("content");

				Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
				if (gradeMap != null && !gradeMap.isEmpty()) {
					for (String gk : gradeMap.keySet()) {
						Object o = gradeMap.get(gk);
						Float score = 0f;
						if (o instanceof Map) {
							Map<String, String> dataMap = (Map<String, String>) o;
							if (dataMap != null) {
								try {
									String scoreS = dataMap.get("score");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
									logger.error("", e);
								}

								putMapVal(scoreMap, operUserFlow + gk, score);
							}
						} else {
							try {
								String scoreS = (String) gradeMap.get("totalScore");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
								logger.error("", e);
							}

							putMapVal(scoreMap, operUserFlow, score);
						}
					}

				}
			}

			model.addAttribute("scoreMap", scoreMap);

			final Map<String, Float> scoreMapTemp = scoreMap;
			Collections.sort(recList, new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> m1, Map<String, String> m2) {
					String k1 = m1.get("operUserFlow") + m1.get("recFlow");
					String k2 = m2.get("operUserFlow") + m2.get("recFlow");
					Float s1 = scoreMapTemp.get(k1);
					Float s2 = scoreMapTemp.get(k2);
					Float result = s2 - s1;

					return result > 0 ? 1 : result == 0 ? 0 : -1;
				}

			});
		}

		model.addAttribute("scoreSumMap", scoreSumMap);
		model.addAttribute("assessCfgList", assessCfgList);

		return "jsres/hospital/gradeSearchDoc";
	}

	/**
	 * 登记详情
	 *
	 * @param doctorFlow
	 * @param schRotationDeptFlow
	 * @param recTypeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogue", method = {RequestMethod.GET, RequestMethod.POST})
	public String catalogue(String doctorFlow, String schRotationDeptFlow, String recTypeId, Model model, String queryTypes) throws Exception {
		if (StringUtil.isBlank(recTypeId)) {
			return "jsres/doctor/catalogue";
		}
		Map<String, Map<String, Object>> resRecMap = new HashMap<String, Map<String, Object>>();
        String id = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
        if (recTypeId.equals(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId()))
			//出科考核查询
			dealCkkhb(doctorFlow, schRotationDeptFlow, recTypeId, model, resRecMap);
		//学员信息查询
		else studentInfo(doctorFlow, schRotationDeptFlow, recTypeId, model, queryTypes, resRecMap);
		return "jsres/doctor/catalogue";
	}

	private void studentInfo(String doctorFlow, String schRotationDeptFlow, String recTypeId, Model model, String queryTypes, Map<String, Map<String, Object>> resRecMap) throws DocumentException {
		List<ResRec> resRecList = new ArrayList<ResRec>();
		if ("1".equals(queryTypes)) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("schRotationDeptFlow", schRotationDeptFlow);
			paramMap.put("doctorFlow", doctorFlow);
			paramMap.put("recTypeId", recTypeId);
			// 点击活动登记表查询
			resRecList = resRecBiz.searchRecAndActivityByProcess(paramMap);
		} else {
			// 登记详情
			resRecList = resRecBiz.searchRecByProcess(recTypeId, schRotationDeptFlow, doctorFlow);
		}

		if (resRecList != null) {
			Map<String, Object> activityDataMap = new HashMap<>();
			for (ResRec resRec : resRecList) {
				Map<String, Object> formDataMap = new HashMap<>();
				String queryType = resRec.getQueryType() == null ? "" : resRec.getQueryType();
				String recFlow = resRec.getRecFlow() == null ? "" : resRec.getRecFlow();
				// 增加参加教学活动类型 + 点击活动登记表查询
				if ("1".equals(queryType) && "1".equals(queryTypes)) {
					formDataMap.put("activity_date", resRec.getCreateTime() == null ? "" : resRec.getCreateTime());//  活动时间
					formDataMap.put("activity_way", resRec.getRecTypeName() == null ? "" : resRec.getRecTypeName());  //  活动形式
					formDataMap.put("activity_period", "1"); //  学时 默认为1
					formDataMap.put("activity_speaker", resRec.getAuditUserName() == null ? "" : resRec.getAuditUserName()); // 主讲人
					formDataMap.put("activity_content", resRec.getRecForm() == null ? "" : resRec.getRecForm()); // 内容
				} else {
					String recContent = resRec.getRecContent();
					formDataMap = resRecBiz.parseRecContent(recContent);
				}
				resRecMap.put(recFlow, formDataMap);
				formDataMap = null;
			}
		}
		model.addAttribute("resRecList", resRecList);
		model.addAttribute("resRecMap", resRecMap);

        if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
			String cfgCodeId = null;
            if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
			}
			if (resRecList != null && resRecList.size() > 0) {
				ResRec rec = resRecList.get(0);
				if (rec != null) {
					model.addAttribute("rec", rec);
					Map<String, Object> gradeMap = resRecBiz.parseGradeXml(rec.getRecContent());
					model.addAttribute("gradeMap", gradeMap);
				}
			}
			ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if (assessCfgList != null && !assessCfgList.isEmpty()) {
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg", assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if (titleElementList != null && !titleElementList.isEmpty()) {
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					for (Element te : titleElementList) {
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if (itemElementList != null && !itemElementList.isEmpty()) {
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for (Element ie : itemElementList) {
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}
	}

	private void dealCkkhb(String doctorFlow, String schRotationDeptFlow, String recTypeId, Model model, Map<String, Map<String, Object>> resRecMap) throws DocumentException {
		List<ResSchProcessExpress> resRecList = new ArrayList<>();
		SchRotationDept dept = rotationDeptBiz.readSchRotationDept(schRotationDeptFlow);
		if (dept != null) {
			List<SchRotationDept> depts = new ArrayList<>();
			depts.add(dept);
			List<SchArrangeResult> results = resultBiz.searchResultByRotationDepts(depts, doctorFlow);
			if (results != null) {
				for (SchArrangeResult result : results) {
					ResDoctorSchProcess process = processBiz.searchByResultFlow(result.getResultFlow());
					if (process != null) {
						List<ResSchProcessExpress> recs = expressBiz.searchByRecAndProcess(recTypeId, process.getProcessFlow());
						if (recs != null) {
							resRecList.addAll(recs);
							for (ResSchProcessExpress resRec : recs) {
								String recContent = resRec.getRecContent();
								Map<String, Object> formDataMap = resRecBiz.parseRecContent(recContent);
								resRecMap.put(resRec.getRecFlow(), formDataMap);
							}
						}
					}
				}
			}
		}
		model.addAttribute("resRecList", resRecList);
		model.addAttribute("resRecMap", resRecMap);

		if (resRecList != null) {
			Map<String, Map<String, Object>> dataAllMap = new HashMap<String, Map<String, Object>>();
			for (ResSchProcessExpress resRec : resRecList) {
				String operUserFlow = doctorFlow;
				String processFlow = resRec.getProcessFlow();
				boolean f = false;
				f = resDoctorBiz.getCkkPower(operUserFlow);
				if (f) {
					ResScore outScore = resScoreBiz.getScoreByProcess(processFlow);
					model.addAttribute("outScore", outScore);
				}
				model.addAttribute("ckk", f);
				Map<String, Object> dataMap = new HashMap<String, Object>();
				Map<String, Object> processPerMap = resRecBiz.getRecProgressIn(operUserFlow, processFlow, null, null);
				if (processPerMap == null) {
					processPerMap = new HashMap<String, Object>();
				}
				//获取不同类型并定义接受
				if (processPerMap != null) {
                    String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
                    String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
                    String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
                    String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();

					String caseRegistry = (String) processPerMap.get(processFlow + caseRegistryId);
					String caseRegistryReqNum = (String) processPerMap.get(processFlow + caseRegistryId + "ReqNum");
					String caseRegistryFinished = (String) processPerMap.get(processFlow + caseRegistryId + "Finished");

					String diseaseRegistry = (String) processPerMap.get(processFlow + diseaseRegistryId);
					String diseaseRegistryReqNum = (String) processPerMap.get(processFlow + diseaseRegistryId + "ReqNum");
					String diseaseRegistryFinished = (String) processPerMap.get(processFlow + diseaseRegistryId + "Finished");

					String skillRegistry = (String) processPerMap.get(processFlow + skillRegistryId);
					String skillRegistryReqNum = (String) processPerMap.get(processFlow + skillRegistryId + "ReqNum");
					String skillRegistryFinished = (String) processPerMap.get(processFlow + skillRegistryId + "Finished");

					String skillAndOperationRegistry = (String) processPerMap.get(processFlow + operationRegistryId);
					String skillAndOperationRegistryReqNum = (String) processPerMap.get(processFlow + operationRegistryId + "ReqNum");
					String skillAndOperationRegistryFinished = (String) processPerMap.get(processFlow + operationRegistryId + "Finished");

                    String recTypeIdt = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
//						int teachingRounds = 0;
//						int difficult = 0;
//						int lecture = 0;
//						int death = 0;
					int jxcf = 0;  int xjk = 0;
					int rkjy = 0;  int ckkh = 0; int jnpx = 0;
					int yph = 0; int jxhz = 0; int jxbltl = 0; int lcczjnzd = 0;
					int lcblsxzd = 0; int ssczzd = 0; int yxzdbgsxzd = 0; int lcwxyd = 0;
					int ryjy = 0; int rzyjdjy = 0; int cjbg = 0;
					int ynbltl=0;	int wzbltl=0; int swbltl=0;
					int bgdfx=0;	int jxsj=0;	int sjys=0;
					List<String> recTypes = new ArrayList<String>();
					recTypes.add(recTypeIdt);
					List<ResRec> recs = resRecBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, operUserFlow);
					for (ResRec resRec2 : recs) {
						String content = resRec2.getRecContent();
						Document document = DocumentHelper.parseText(content);
						Element root = document.getRootElement();
						Element ec = root.element("activity_way");
						if (ec != null) {
							String text = ec.attributeValue("id");
							if(Jxcf.equals(text)){
								jxcf++;
							}else if(Ynbltl.equals(text)){
								ynbltl++;
							}else if(Wzbltl.equals(text)){
								wzbltl++;
							}else if(Xsjz.equals(text)){
								xjk++;
							}else if(Swbltl.equals(text)){
								swbltl++;
							}else if(Rkjy.equals(text)){
								rkjy++;
							}else if(Ckks.equals(text)){
								ckkh++;
							}else if(Jnpx.equals(text)){
								jnpx++;
							}else if(Yph.equals(text)){
								yph++;
							}else if(Jxhz.equals(text)){
								jxhz++;
							}else if(Jxbltl.equals(text)){
								jxbltl++;
							}else if(Lcczjnzd.equals(text)){
								lcczjnzd++;
							}else if(Lcblsxzd.equals(text)){
								lcblsxzd++;
							}else if(Ssczzd.equals(text)){
								ssczzd++;
							}else if(Yxzdbgsxzd.equals(text)){
								yxzdbgsxzd++;
							}else if(Lcwxyd.equals(text)){
								lcwxyd++;
							}else if(Ryjy.equals(text)){
								ryjy++;
							}else if(Rzyjdjy.equals(text)){
								rzyjdjy++;
							}else if(Cjbg.equals(text)){
								cjbg++;
							}else if (Bgdfx.equals(text)){
								bgdfx++;
							}else if(Jxsj.equals(text)){
								jxsj++;
							}else if (Sjys.equals(text)){
								sjys++;
							}
						}
					}

					List<TeachingActivityInfo> infos = new ArrayList<>();
					String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
					JsresPowerCfg orgApprove = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_ctrl_approve_activity");//教学活动评价配置
					JsresPowerCfg approve = jsResPowerCfgBiz.read("jsres_"+orgFlow+"_org_approve_activity");//教学活动评价配置评审类型
                    if (null != orgApprove && null != approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(), orgApprove.getCfgValue(), com.pinde.core.common.GlobalConstant.FLAG_Y)) {        //开启必评
						infos = resRecBiz.searchJoinActivityByProcessFlownotScore(processFlow);
					}else {
						infos = resRecBiz.searchJoinActivityByProcessFlow(processFlow);
					}
					if (infos != null && infos.size() > 0) {
						for (TeachingActivityInfo info : infos) {
							String text = info.getActivityTypeId();
							if(Jxcf.equals(text)){
								jxcf++;
							}else if(Ynbltl.equals(text)){
								ynbltl++;
							}else if(Wzbltl.equals(text)){
								wzbltl++;
							}else if(Xsjz.equals(text)){
								xjk++;
							}else if(Swbltl.equals(text)){
								swbltl++;
							}else if(Rkjy.equals(text)){
								rkjy++;
							}else if(Ckks.equals(text)){
								ckkh++;
							}else if(Jnpx.equals(text)){
								jnpx++;
							}else if(Yph.equals(text)){
								yph++;
							}else if(Jxhz.equals(text)){
								jxhz++;
							}else if(Jxbltl.equals(text)){
								jxbltl++;
							}else if(Lcczjnzd.equals(text)){
								lcczjnzd++;
							}else if(Lcblsxzd.equals(text)){
								lcblsxzd++;
							}else if(Ssczzd.equals(text)){
								ssczzd++;
							}else if(Yxzdbgsxzd.equals(text)){
								yxzdbgsxzd++;
							}else if(Lcwxyd.equals(text)){
								lcwxyd++;
							}else if(Ryjy.equals(text)){
								ryjy++;
							}else if(Rzyjdjy.equals(text)){
								rzyjdjy++;
							}else if(Cjbg.equals(text)){
								cjbg++;
							}else if (Bgdfx.equals(text)){
								bgdfx++;
							}else if(Jxsj.equals(text)){
								jxsj++;
							}else if (Sjys.equals(text)){
								sjys++;
							}
						}
					}

					dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
					dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
					dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

					dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
					dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
					dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

					dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
					dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
					dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

					dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
					dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
					dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

					dataMap.put("jxcf",String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
					dataMap.put("xjk",String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
					dataMap.put("rkjy",String.valueOf(rkjy));
					dataMap.put("ckkh",String.valueOf(ckkh));
					dataMap.put("jnpx",String.valueOf(jnpx));
					dataMap.put("yph",String.valueOf(yph));
					dataMap.put("jxhz",String.valueOf(jxhz));
					dataMap.put("jxbltl",String.valueOf(jxbltl));
					dataMap.put("wzbltl",String.valueOf(wzbltl));
					dataMap.put("swbltl",String.valueOf(swbltl));
					dataMap.put("ynbltl",String.valueOf(ynbltl));
					dataMap.put("lcczjnzd",String.valueOf(lcczjnzd));
					dataMap.put("lcblsxzd",String.valueOf(lcblsxzd));
					dataMap.put("ssczzd",String.valueOf(ssczzd));
					dataMap.put("yxzdbgsxzd",String.valueOf(yxzdbgsxzd));
					dataMap.put("lcwxyd",String.valueOf(lcwxyd));
					dataMap.put("ryjy",String.valueOf(ryjy));
					dataMap.put("rzyjdjy",String.valueOf(rzyjdjy));
					dataMap.put("cjbg",String.valueOf(cjbg));
					dataMap.put("bgdfx",String.valueOf(bgdfx));
					dataMap.put("jxsj",String.valueOf(jxsj));
					dataMap.put("sjys",String.valueOf(sjys));
					dataAllMap.put(resRec.getRecFlow(), dataMap);
				}
			}
			model.addAttribute("dataAllMap", dataAllMap);
		}
	}

	@RequestMapping(value = "/publicOpen")
	public String publicOpen(String deptFlow, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(currUser.getOrgFlow());
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);
		SysOrg sysOrg = new SysOrg();
        String orgType = com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId();
		sysOrg.setOrgTypeId(orgType);
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> sysOrgList = orgBiz.searchOrgWithBLOBs(sysOrg);
		model.addAttribute("sysOrgList", sysOrgList);
		List<SchDeptExternalRel> deptExternalRelList = deptExternalRelBiz.readSchDeptExtRelByDept(deptFlow);
		Map<String, Object> map = new HashMap<String, Object>();
		for (SchDeptExternalRel schDeptExternalRel : deptExternalRelList) {
			map.put(schDeptExternalRel.getRelOrgFlow(), schDeptExternalRel);
		}
		model.addAttribute("currOrgFlow", currUser.getOrgFlow());
		model.addAttribute("map", map);
		return "jsres/hospital/publicOpen";
	}

	@RequestMapping(value = "/modifyCfg")
	public String modifyCfg(String deptFlow, String recordFlow, Model model) {
		SchAndStandardDeptCfg cfg = deptCfgBiz.readByFlow(recordFlow);
		model.addAttribute("cfg", cfg);
		List<SysDict> allDicts = new ArrayList<>();
		SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.StandardDept.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> sysDictList = dictBiz.searchDictList(sysDict);
		if (sysDictList != null && sysDictList.size() > 0) {
			allDicts.addAll(sysDictList);
			for (SysDict dict : sysDictList) {
				String typeId = dict.getDictTypeId() + "." + dict.getDictId();
                if (com.pinde.core.common.enums.DictTypeEnum.StandardDept.getLevel() > 1) {
					sysDict.setDictTypeId(typeId);
					List<SysDict> sysDictSecondList = dictBiz.searchDictList(sysDict);
					if (sysDictSecondList != null && sysDictSecondList.size() > 0) {
						allDicts.addAll(sysDictSecondList);
						for (SysDict sDict : sysDictSecondList) {
							String tTypeId = typeId + "." + sDict.getDictId();
							sDict.setDictId(dict.getDictId() + "." + sDict.getDictId());
							sDict.setDictName(dict.getDictName() + "." + sDict.getDictName());
                            if (com.pinde.core.common.enums.DictTypeEnum.StandardDept.getLevel() > 2) {
								sysDict.setDictTypeId(tTypeId);
								List<SysDict> sysDictThirdList = dictBiz.searchDictList(sysDict);
								if (sysDictThirdList != null && sysDictThirdList.size() > 0) {
									allDicts.addAll(sysDictThirdList);
									for (SysDict tDict : sysDictThirdList) {
										tDict.setDictId(sDict.getDictId() + "." + tDict.getDictId());
										tDict.setDictName(sDict.getDictName() + "." + tDict.getDictName());
									}
								}
							}

						}
					}
				}
			}
		}
		model.addAttribute("allDicts", allDicts);
		return "jsres/hospital/modifyCfg";
	}

	@RequestMapping(value = "/saveCfg")
	@ResponseBody
	public String saveCfg(String deptFlow, String recordFlow, String standardDeptId, Model model) {
		SchAndStandardDeptCfg cfg = deptCfgBiz.readByFlow(recordFlow);
		if (cfg == null)
			cfg = new SchAndStandardDeptCfg();
		cfg.setStandardDeptId(standardDeptId);
        cfg.setStandardDeptName(com.pinde.core.common.enums.DictTypeEnum.StandardDept.getDictNameById(standardDeptId));
		SysDept dept = deptBiz.readSysDept(deptFlow);
		cfg.setSchDeptFlow(deptFlow);
		cfg.setSchDeptName(dept.getDeptName());
        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		SysOrg org = orgBiz.readSysOrg(dept.getOrgFlow());
		cfg.setOrgFlow(dept.getOrgFlow());
		cfg.setOrgName(org.getOrgName());
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("schDeptFlow", deptFlow);
		paramMap.put("orgFlow", dept.getOrgFlow());
		paramMap.put("standardDeptId", standardDeptId);
		SchAndStandardDeptCfg temptcfg = deptCfgBiz.searchByItems(paramMap);
		if (temptcfg != null)
			cfg.setRecordFlow(temptcfg.getRecordFlow());
		int result = deptCfgBiz.save(cfg);
		if (result == 0) {
			return "保存失败！";
		}
		return "保存成功！";
	}

	@RequestMapping(value = "/relationSave", method = {RequestMethod.POST})
	@ResponseBody
	public String relationSave(String orgFlow, String orgName, String deptFlow) {
		SchDeptExternalRel deptExternalRel = deptExternalRelBiz.readSchDeptExtRelByDeptAndRelOrgFlow(deptFlow, orgFlow);
		int i = 0;
		if (deptExternalRel != null) {
            deptExternalRel.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			i = deptExternalRelBiz.editSchDeptExtRel(deptExternalRel);
		} else {
			deptExternalRel = new SchDeptExternalRel();
			SysDept dept = deptBiz.readSysDept(deptFlow);
			SysUser currUser = GlobalContext.getCurrentUser();
			deptExternalRel.setDeptFlow(dept.getDeptFlow());
			deptExternalRel.setDeptName(dept.getDeptName());
			deptExternalRel.setOrgFlow(currUser.getOrgFlow());
			deptExternalRel.setOrgName(currUser.getOrgName());
			deptExternalRel.setRelOrgFlow(orgFlow);
			deptExternalRel.setRelOrgName(orgName);
			i = deptExternalRelBiz.editSchDeptExtRel(deptExternalRel);
		}
		if (i > 0) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		} else {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		}
	}

	@RequestMapping(value = "/relationDelete", method = {RequestMethod.POST})
	@ResponseBody
	public String relationDelete(String orgFlow, String deptFlow) {
		SchDeptExternalRel deptExternalRel = deptExternalRelBiz.readSchDeptExtRelByDeptAndRelOrgFlow(deptFlow, orgFlow);
		if (deptExternalRel != null) {
            deptExternalRel.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			int i = deptExternalRelBiz.editSchDeptExtRel(deptExternalRel);
			if (i > 0) {
                return com.pinde.core.common.GlobalConstant.UPDATE_SUCCESSED;
			} else {
                return com.pinde.core.common.GlobalConstant.UPDATE_FAIL;
			}
		} else {
            return com.pinde.core.common.GlobalConstant.UPDATE_FAIL;
		}

	}

	@RequestMapping(value = "/getNeedReducation", method = {RequestMethod.POST})
	@ResponseBody
	public int getNeedReducation(String orgFlow) {
		//获取需要减免维护的人员数量
		int result = doctorDeptBiz.countReducation(orgFlow);
		return result;
	}

	@RequestMapping(value = "/teacherWorkload")
	public String teacherWorkload(Model model, SysUser sysUser, String[] doctorTypeIdList, String operStartDate, String operEndDate, String orderItem, String sortName, Integer currentPage, HttpServletRequest request) throws DocumentException {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		if (StringUtil.isBlank(operStartDate) && StringUtil.isBlank(operEndDate)) {
			operStartDate = DateUtil.addMonth(DateUtil.getCurrMonth(), -6) + "-01";
			operEndDate = DateUtil.getCurrDate();
		}
		model.addAttribute("operStartDate", operStartDate);
		model.addAttribute("operEndDate", operEndDate);
		String startDate = operStartDate;
		String endDate = operEndDate;
		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}
		//复选框勾选标识
		Map<String, String> doctorTypeSelectMap = new HashMap<>();
		List<String> typeList = null;
		if (doctorTypeIdList != null && doctorTypeIdList.length > 0) {
			typeList = Arrays.asList(doctorTypeIdList);
            for (ResDocTypeEnum dict : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				if (typeList.contains(dict.getId())) {
					doctorTypeSelectMap.put(dict.getId(), "checked");
				}
			}
		}
		if (doctorTypeIdList == null) {
			typeList = new ArrayList<>();
            for (ResDocTypeEnum dict : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				typeList.add(dict.getId());
				doctorTypeSelectMap.put(dict.getId(), "checked");
			}
		}
		List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
		String cfgTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
		if (StringUtil.isBlank(orderItem)) {
			orderItem = "0";
		}
		if (StringUtil.isBlank(sortName)) {
			sortName = "DESC";
		}
		if (StringUtil.isNotBlank(orderItem)) {
			if ("0".equals(orderItem)) {
				orderItem = "currStudentHe";
			} else if ("1".equals(orderItem)) {
				orderItem = "notAudited";
			} else if ("2".equals(orderItem)) {
				orderItem = "isNotAudited";
			} else {
				orderItem = "";
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> dataList = processBiz.jsresSearchTeacherWorkInfo(operStartDate, operEndDate, typeList, sysUser, orgFlow, cfgTeacher, orderItem, sortName);
		model.addAttribute("dataList", dataList);
		if (dataList != null && dataList.size() > 0) {
			List<String> teacherFlows = new ArrayList<>();
			for (Map<String, String> tmap : dataList) {
				teacherFlows.add(tmap.get("USER_FLOW"));
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sysUser", sysUser);
			paramMap.put("teacherUserFlows", teacherFlows);
			paramMap.put("startDate", operStartDate);
			paramMap.put("endDate", operEndDate);
			paramMap.put("typeList", typeList);
			paramMap.put("workOrgId", "");
			paramMap.put("roleFlow", cfgTeacher);
			paramMap.put("orgFlow", orgFlow);
			paramMap.put("schStartDate", startDate);
			paramMap.put("schEndDate", endDate);
//			List<Map<String, String>> notAuditedMaps = resRecBiz.notAuditedMaps(paramMap);
//			List<Map<String, String>> isNotAuditedMaps = resRecBiz.isNotAuditedMaps(paramMap);
			List<Map<String, String>> auditedMapList = resRecBiz.searchAuditedDataList(paramMap);
			List<Map<String, String>> gradeAvgMaps = resGradeBiz.searchGradeAvgMaps(paramMap);
			for (Map<String, String> tmap : dataList) {
				String teacherFlow = tmap.get("USER_FLOW");
				if (auditedMapList != null && auditedMapList.size() > 0) {
					for (Map<String, String> tempMap : auditedMapList) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("notAudited", tempMap.get("notAudited"));
							tmap.put("isNotAudited", tempMap.get("isNotAudited"));
							continue;
						}
					}
				}
//				if (notAuditedMaps != null && notAuditedMaps.size() > 0) {
//					for (Map<String, String> tempMap : notAuditedMaps) {
//						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
//							tmap.put("notAudited", tempMap.get("counts") + "");
//							continue;
//						}
//					}
//				}
//				if (isNotAuditedMaps != null && isNotAuditedMaps.size() > 0) {
//					for (Map<String, String> tempMap : isNotAuditedMaps) {
//						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
//							tmap.put("isNotAudited", tempMap.get("counts") + "");
//							continue;
//						}
//					}
//				}
				if (gradeAvgMaps != null && gradeAvgMaps.size() > 0) {
					for (Map<String, String> tempMap : gradeAvgMaps) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("AVERAGE", tempMap.get("counts") + "");
							continue;
						}
					}
				}
			}
		}

		model.addAttribute("deptList", deptList);
		model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
		return "jsres/hospital/teacherWorkload";
	}

	@RequestMapping(value = "/details")
	public String details(Model model, String teacherUserFlow, String[] doctorTypeIdList, String biaoJi, String startDate, String endDate) throws DocumentException {
		List<Map<String, Object>> doctorListMap = new ArrayList<Map<String, Object>>();
		List<String> typeList = null;
		if (doctorTypeIdList != null && doctorTypeIdList.length > 0) {
			typeList = Arrays.asList(doctorTypeIdList);
		}
        if (com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG.equals(biaoJi)) {
			doctorListMap = resDoctorBiz.schDoctorQuery(teacherUserFlow, startDate, endDate, typeList, "");
		}
        if (com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG.equals(biaoJi)) {
			List<Map<String, Object>> TeacherProcessMap = resDoctorBiz.chTeacherProcessFlowRec(teacherUserFlow, startDate, endDate, typeList);
			for (Map<String, Object> map : TeacherProcessMap) {
				String recContent = (String) map.get("recContent");
				List<Map<String, String>> imageList = resRecBiz.parseImageXml(recContent);
				if (imageList != null && !imageList.isEmpty()) {
					doctorListMap.add(map);
				}
			}
		}
		model.addAttribute("doctorListMap", doctorListMap);
		return "jsres/hospital/studentDetails";
	}

	@RequestMapping(value = "/delayDate")
	public String delayDate(String recruitFlow, Model model) {
		return "jsres/hospital/doctor/addDelayInfo";
	}

	@RequestMapping(value = "/checkStatus")
	@ResponseBody
	public String checkStatus(String doctorFlow) {
		SysUser user = userBiz.readSysUser(doctorFlow);
        String flag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		if (UserStatusEnum.Locked.getId().equals(user.getStatusId()) || UserStatusEnum.SysLocked.getId().equals(user.getStatusId())) {
            flag = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		return flag;
	}

	@RequestMapping(value = "/doctorNumForUni1")
	public String doctorNumForUni1(String orgFlow, String trainTypeId, String sessionNumber, Model model) {

		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg sysorg = orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<SysOrg> orgs = orgBiz.getUniOrgs(sysorg.getSendSchoolId(), sysorg.getSendSchoolName());
		List<String> orgFlows = new ArrayList<>();
		if (orgs != null && orgs.size() > 0) {
			for (SysOrg o : orgs) {
				orgFlows.add(o.getOrgFlow());
			}
		}
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
		recruit.setOrgFlow(orgFlow);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());

		List<Map<String, String>> listSpe1 = resStatisticBiz.doctorNumForUni1(recruit, orgFlows, sysorg);
		Map<String, Map<String, String>> mapSpe = new HashMap<>();
		List<String> SpeIds = new ArrayList<>();
		if (listSpe1 != null && listSpe1.size() > 0) {
			for (Map<String, String> m : listSpe1) {
				String speId = m.get("speId");
				mapSpe.put(speId, m);
				if (!SpeIds.contains(speId)) {
					SpeIds.add(speId);
				}
			}
		}
		model.addAttribute("SpeIds", SpeIds);
		model.addAttribute("mapSpe", mapSpe);

		return "jsres/university/report/doctorNumForUni1";
	}

	@RequestMapping(value = "/doctorNumForUni2")
	public String doctorNumForUni2(String trainTypeId, String sessionNumber, Model model) {
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg sysorg = orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<SysOrg> orgs = orgBiz.getUniOrgs(sysorg.getSendSchoolId(), sysorg.getSendSchoolName());
		List<String> orgFlows = new ArrayList<>();
		if (orgs != null && orgs.size() > 0) {
			for (SysOrg o : orgs) {
				orgFlows.add(o.getOrgFlow());
			}
		}
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());

		List<Map<String, String>> listSpe1 = resStatisticBiz.doctorNumForUni2(recruit, orgFlows, sysorg);
		Map<String, Map<String, String>> mapSpe = new HashMap<>();
		List<String> SpeIds = new ArrayList<>();
		if (listSpe1 != null && listSpe1.size() > 0) {
			for (Map<String, String> m : listSpe1) {
				String speId = m.get("orgFlow");
				mapSpe.put(speId, m);
				if (!SpeIds.contains(speId)) {
					SpeIds.add(speId);
				}
			}
		}
		model.addAttribute("SpeIds", SpeIds);
		model.addAttribute("mapSpe", mapSpe);

		return "jsres/university/report/doctorNumForUni2";
	}

	@RequestMapping("/daochu")
	public void daochu(HttpServletResponse response, String orgFlow, String trainTypeId, String sessionNumber) throws Exception {
		String[] titles = new String[]{
				"catSpeName:培训类别",
				"speName:培训专业",
				"docNum:人数"
		};
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg sysorg = orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<SysOrg> orgs = orgBiz.getUniOrgs(sysorg.getSendSchoolId(), sysorg.getSendSchoolName());
		List<String> orgFlows = new ArrayList<>();
		if (orgs != null && orgs.size() > 0) {
			for (SysOrg o : orgs) {
				orgFlows.add(o.getOrgFlow());
			}
		}
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
		recruit.setOrgFlow(orgFlow);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());

		List<Map<String, String>> listSpe1 = resStatisticBiz.doctorNumForUni1DaoChu(recruit, orgFlows, sysorg);
		String fileName = "专业人员信息统计.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, listSpe1, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");

	}

	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response, SysUser sysUser, String[] doctorTypeIdList, String operStartDate, String operEndDate, String orderItem, String sortName, HttpServletRequest request) throws Exception {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		if (StringUtil.isBlank(operStartDate) && StringUtil.isBlank(operEndDate)) {
			operStartDate = DateUtil.addMonth(DateUtil.getCurrMonth(), -6) + "-01";
			operEndDate = DateUtil.getCurrDate();
		}

		String startDate = operStartDate;
		String endDate = operEndDate;
		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}
		//复选框勾选标识
		Map<String, String> doctorTypeSelectMap = new HashMap<>();
		List<String> typeList = null;
		if (doctorTypeIdList != null && doctorTypeIdList.length > 0) {
			typeList = Arrays.asList(doctorTypeIdList);
            for (ResDocTypeEnum dict : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				if (typeList.contains(dict.getId())) {
					doctorTypeSelectMap.put(dict.getId(), "checked");
				}
			}
		}
		if (doctorTypeIdList == null) {
			typeList = new ArrayList<>();
            for (ResDocTypeEnum dict : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				typeList.add(dict.getId());
				doctorTypeSelectMap.put(dict.getId(), "checked");
			}
		}
		List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
		String cfgTeacher = InitConfig.getSysCfg("res_teacher_role_flow");
		if (StringUtil.isBlank(orderItem)) {
			orderItem = "0";
		}
		if (StringUtil.isBlank(sortName)) {
			sortName = "DESC";
		}
		if (StringUtil.isNotBlank(orderItem)) {
			if ("0".equals(orderItem)) {
				orderItem = "currStudentHe";
			} else if ("1".equals(orderItem)) {
				orderItem = "notAudited";
			} else if ("2".equals(orderItem)) {
				orderItem = "isNotAudited";
			} else {
				orderItem = "";
			}
		}

		List<Map<String, String>> dataList = processBiz.jsresSearchTeacherWorkInfo(operStartDate, operEndDate, typeList, sysUser, orgFlow, cfgTeacher, orderItem, sortName);
		List<Map<String, String>> auditedMapListByOper = new ArrayList<>();

		if (dataList != null && dataList.size() > 0) {
			List<String> teacherFlows1 = new ArrayList<>();
			for (Map<String, String> tmap : dataList) {
				teacherFlows1.add(tmap.get("USER_FLOW"));
			}
			LinkedHashSet<String> linkedHashSet  = new LinkedHashSet<>(teacherFlows1);
			ArrayList<String> teacherFlows = new ArrayList<>(linkedHashSet);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sysUser", sysUser);
			paramMap.put("teacherUserFlows", teacherFlows);
			paramMap.put("startDate", operStartDate);
			paramMap.put("endDate", operEndDate);
			paramMap.put("typeList", typeList);
			paramMap.put("workOrgId", "");
			paramMap.put("roleFlow", cfgTeacher);
			paramMap.put("orgFlow", orgFlow);
			paramMap.put("schStartDate", startDate);
			paramMap.put("schEndDate", endDate);
//			List<Map<String, String>> notAuditedMaps = resRecBiz.notAuditedMaps(paramMap);
//			List<Map<String, String>> isNotAuditedMaps = resRecBiz.isNotAuditedMaps(paramMap);
			List<Map<String, String>> auditedMapList = resRecBiz.searchAuditedDataList(paramMap);
			auditedMapListByOper = resRecBiz.searchAuditedDataListByOper(paramMap);
			List<Map<String, String>> gradeAvgMaps = resGradeBiz.searchGradeAvgMaps(paramMap);
			List<Map<String, String>> doctorListMap = resDoctorBiz.searchSchDoctorList(paramMap);
			for (Map<String, String> tmap : dataList) {
				String teacherFlow = tmap.get("USER_FLOW");
				if (auditedMapList != null && auditedMapList.size() > 0) {
					for (Map<String, String> tempMap : auditedMapList) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("NOTAUDITED", tempMap.get("notAudited"));
							tmap.put("ISNOTAUDITED", tempMap.get("isNotAudited"));
							continue;
						}
					}
				}

				if (auditedMapListByOper != null && auditedMapListByOper.size() > 0) {
					for (Map<String, String> tempMap : auditedMapListByOper) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tempMap.put("USER_NAME",tmap.get("USER_NAME"));
							tempMap.put("USER_CODE",tmap.get("USER_CODE"));
							tempMap.put("DEPT_NAME",tmap.get("DEPT_NAME"));
							continue;
						}
					}
				}

//				if (notAuditedMaps != null && notAuditedMaps.size() > 0) {
//					for (Map<String, String> tempMap : notAuditedMaps) {
//						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
//							tmap.put("NOTAUDITED", tempMap.get("counts") + "");
//							continue;
//						}
//					}
//				}
				if(StringUtil.isBlank(tmap.get("NOTAUDITED"))){
					tmap.put("NOTAUDITED","0");
				}
//				if (isNotAuditedMaps != null && isNotAuditedMaps.size() > 0) {
//					for (Map<String, String> tempMap : isNotAuditedMaps) {
//						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
//							tmap.put("ISNOTAUDITED", tempMap.get("counts") + "");
//							continue;
//						}
//					}
//				}
				if(StringUtil.isBlank(tmap.get("ISNOTAUDITED"))){
					tmap.put("ISNOTAUDITED","0");
				}
				if (gradeAvgMaps != null && gradeAvgMaps.size() > 0) {
					for (Map<String, String> tempMap : gradeAvgMaps) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("AVERAGE", tempMap.get("counts") + "");
							continue;
						}
					}
				}

				if (doctorListMap != null && doctorListMap.size() > 0) {
					for (Map<String, String> tempMap : doctorListMap) {
						if (teacherFlow.equals(tempMap.get("teacherUserFlow"))) {
							tmap.put("doctorList", tempMap.get("doctorNameStr"));
							continue;
						}
					}
				}

//				//江苏西医平台优化11.17需求 一
//				List<Map<String, Object>> doctorListMap = new ArrayList<Map<String, Object>>();
//				doctorListMap = resDoctorBiz.schDoctorQuery(tmap.get("USER_FLOW"), startDate, endDate, typeList, "");
//				StringBuilder sb = new StringBuilder();
//				if (doctorListMap != null && doctorListMap.size() > 0) {
//					sb.append("[");
//					Map<String, Object> tInfoMap = new HashMap<>();
//					List<String> tempList = new ArrayList<>();
//					int offset = doctorListMap.size() - 1;
//					for (int k = 0; k < offset; k++) {
//						tInfoMap = doctorListMap.get(k);
//						if (!tempList.contains((String) tInfoMap.get("doctorName"))) {
//							sb.append(tInfoMap.get("doctorName")).append(",");
//							tempList.add((String) tInfoMap.get("doctorName"));
//						}
//					}
//					if (!tempList.contains(doctorListMap.get(offset).get("doctorName"))) {
//						sb.append(doctorListMap.get(offset).get("doctorName")).append("]");
//					} else {
//						sb.replace(sb.length() - 1, sb.length(), "]");
//					}
//				}
//				tmap.put("doctorList", sb + "");
			}
		}

		if (auditedMapListByOper != null && auditedMapListByOper.size() > 0) {
			for (Map<String, String> tmap : auditedMapListByOper) {
				if(StringUtil.isBlank(tmap.get("notAudited"))){
					tmap.put("notAudited","0");
				}
				if(StringUtil.isBlank(tmap.get("isNotAudited"))){
					tmap.put("isNotAudited","0");
				}
			}
		}

		String fileName = "带教老师工作量统计.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");

		String[] sheet1Titles = new String[]{
				"USER_NAME:姓名",
				"USER_CODE:用户名",
				"DEPT_NAME:科室",
				"AVERAGE:老师均分",
				"ISNOTAUDITED:已审核数",
				"NOTAUDITED:未审核数",
				"ACTIVITYNUM:教学活动数",
				"CURRSTUDENTHE:带教名单",
				"CURRSTUDENTCOUNT:带教人次",
				"doctorList:学员姓名"
		};

		String[] sheet2Titles = new String[]{
				"USER_NAME:带教姓名",
				"USER_CODE:用户名",
				"DEPT_NAME:科室",
				"isNotAudited:已审核数",
				"notAudited:未审核数",
				"operUserName:学员姓名"
		};

		OutputStream os = response.getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet1.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

		List<String> paramIds = new ArrayList<String>();

		Map<Integer, Integer> columnWidth = new HashMap<>();
		HSSFCell cell = null;
		for (int i = 0; i < sheet1Titles.length; i++) {
			String[] title = sheet1Titles[i].split(":");
			cell = row.createCell(i);
			cell.setCellValue(title[1]);
			cell.setCellStyle(style);
			paramIds.add(title[0]);
			int length = title[1].length();
			//宽度自适应
			setColumnWidth(title[1].toString().getBytes().length, i, columnWidth);
		}
		if (dataList != null) {
			for (int i = 0; i < dataList.size(); i++) {
				Map item = dataList.get(i);
				row = sheet1.createRow(i + 1);
				Object result = null;
				for (int j = 0; j < paramIds.size(); j++) {
					String paramId = paramIds.get(j);
					result = item.get(paramId);
					if (result == null)
						result = "";
					row.createCell(j).setCellValue(result.toString());
					//宽度自适应
					setColumnWidth(result.toString().getBytes().length, j, columnWidth);
				}
			}
		}

		List<String> sheet2ParamIds = new ArrayList<String>();
		HSSFSheet sheet2 = wb.createSheet("sheet2");
		HSSFRow sheet2Row = sheet2.createRow(0);
		for (int i = 0; i < sheet2Titles.length; i++) {
			String[] title = sheet2Titles[i].split(":");
			cell = sheet2Row.createCell(i);
			cell.setCellValue(title[1]);
			cell.setCellStyle(style);
			sheet2ParamIds.add(title[0]);
			int length = title[1].length();
			//宽度自适应
			setColumnWidth(title[1].toString().getBytes().length, i, columnWidth);
		}
		if (auditedMapListByOper != null) {
			for (int i = 0; i < auditedMapListByOper.size(); i++) {
				Map item = auditedMapListByOper.get(i);
				row = sheet2.createRow(i + 1);
				Object result = null;
				for (int j = 0; j < sheet2ParamIds.size(); j++) {
					String paramId = sheet2ParamIds.get(j);
					result = item.get(paramId);
					if (result == null)
						result = "";
					row.createCell(j).setCellValue(result.toString());
					//宽度自适应
					setColumnWidth(result.toString().getBytes().length, j, columnWidth);
				}
			}
		}

		wb.write(os);
	}

	@RequestMapping("/daochu2")
	public void daochu2(HttpServletResponse response, String trainTypeId, String sessionNumber) throws Exception {
		String[] titles = new String[]{
				"orgName:培训基地",
				"catSpeName:培训类别",
				"docNum:人数"
		};
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg sysorg = orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<SysOrg> orgs = orgBiz.getUniOrgs(sysorg.getSendSchoolId(), sysorg.getSendSchoolName());
		List<String> orgFlows = new ArrayList<>();
		if (orgs != null && orgs.size() > 0) {
			for (SysOrg o : orgs) {
				orgFlows.add(o.getOrgFlow());
			}
		}
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		List<Map<String, String>> listSpe1 = resStatisticBiz.doctorNumForUni2DaoChu(recruit, orgFlows, sysorg);
		String fileName = "基地人员信息统计.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, listSpe1, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");

	}

	@RequestMapping(value = "/doctorNumSearch")
	public String doctorNumSearch(String cityId, String orgLevel, String orgFlow, String trainTypeId, String sessionNumber, String datas[], String[] docType, Model model) {

		List<String> orgFlowList = new ArrayList<String>();
		List<String> docTypeList = new ArrayList<String>();
		List<SysOrg> orgs = new ArrayList<>();
		ResDoctor doctor = new ResDoctor();
		if (docType != null && docType.length > 0) {
			for (String s : docType) {
				docTypeList.add(s);
			}
		}
		SysOrg org = new SysOrg();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			SysUser user = GlobalContext.getCurrentUser();
			org = orgBiz.readSysOrg(user.getOrgFlow());
			if (StringUtil.isBlank(orgFlow)) {
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
					List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
					List<String> jointOrgFlowList = new ArrayList<>();
					if (jointOrgs != null && jointOrgs.size() > 0) {
						for (ResJointOrg join : jointOrgs) {
							jointOrgFlowList.add(join.getJointOrgFlow());
						}
					}
					if (jointOrgFlowList != null && jointOrgFlowList.size() > 0) {
						orgs = orgBiz.searchOrgFlowIn(jointOrgFlowList);
					}
					orgs.add(org);
				} else {
					orgs.add(org);
				}
			} else {
				SysOrg org2 = orgBiz.readSysOrg(orgFlow);
				orgs.add(org2);
			}
        } else if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			if (StringUtil.isNotBlank(orgFlow)) {
				org = orgBiz.readSysOrg(orgFlow);
				orgs.add(org);
			} else {
				SysUser user = GlobalContext.getCurrentUser();
				org = orgBiz.readSysOrg(user.getOrgFlow());
				SysOrg sysOrg2 = new SysOrg();
				sysOrg2.setOrgProvId("320000");
				sysOrg2.setOrgCityId(org.getOrgCityId());
                sysOrg2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
				orgs = orgBiz.searchOrg(sysOrg2);
//				if(StringUtil.isNotBlank(orgLevel)){
//					sysOrg2.setOrgLevelId(orgLevel);
//				}
//				sysOrg2.setOrgCityId(org.getOrgCityId());
//				sysOrg2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//				if(StringUtil.isNotBlank(orgLevel)){
//					orgs=orgBiz.searchOrg(sysOrg2);
//				}else {
//					orgs=jointOrgBiz.searchCouAndProList(sysOrg2);
//				}
			}
		} else {
			//查询所有国家基地
			List<SysOrg> sysOrgList = new ArrayList<SysOrg>();
			SysOrg org2 = new SysOrg();
            org2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org2.setOrgProvId(s.getOrgProvId());
			if (StringUtil.isNotBlank(orgFlow)) {
				orgFlowList.add(orgFlow);
			} else {
				if (StringUtil.isNotBlank(cityId)) {
					org2.setOrgCityId(cityId);
				}
				if (StringUtil.isNotBlank(orgLevel)) {
					org2.setOrgLevelId(orgLevel);
                    org2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
					sysOrgList = orgBiz.searchAllSysOrg(org2);
				} else {
					sysOrgList = jointOrgBiz.searchCouAndProList(org2);
				}
				if (sysOrgList != null && sysOrgList.size() > 0) {
					for (SysOrg o : sysOrgList) {
						orgFlowList.add(o.getOrgFlow());
					}
				}
			}
			if (orgFlowList != null && orgFlowList.size() > 0) {
				orgs = orgBiz.searchOrgFlowIn(orgFlowList);
			}
		}
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		//每家基地每个专业的医师培训记录总数
		Map<Object, Object> totalCountMap = new HashMap<Object, Object>();//保存每家基地的培训记录总数
		Map<Object, Object> orgSpeFlagMap = new HashMap<Object, Object>();//基地专业标志的的map
//		Boolean isSee = Arrays.asList(datas).contains(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
		if (orgs != null && orgs.size() > 0) {
			if (StringUtil.isBlank(orgFlow) && StringUtil.isNotBlank(orgLevel)) {
				List<Map<String, Object>> doctorCountList = new ArrayList<>();
				for (SysOrg o : orgs) {
					List<Map<String, Object>> countList = new ArrayList<>();
					orgFlowList = new ArrayList<String>();
					orgFlowList.add(o.getOrgFlow());
                    if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(orgLevel)) {//查询协同基地人数
						List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
						if (jointOrgList != null && !jointOrgList.isEmpty()) {
							for (ResJointOrg resJointOrg : jointOrgList) {
								orgFlowList.add(resJointOrg.getJointOrgFlow());
							}
						}
						countList = resStatisticBiz.statisticJointCountByOrg(recruit, orgFlowList, trainTypeId, docTypeList);
						if (jointOrgList != null && !jointOrgList.isEmpty() && countList != null && countList.size() > 1) {
							String orgFlow2 = o.getOrgFlow();
							int count = 0;
							for (int i = 0; i < countList.size(); i++) {
								count += Integer.parseInt(countList.get(i).get("COUNT").toString());
							}
							Map<String, Object> bemap = new HashMap<>();
							bemap.put("ORGFLOW", orgFlow2);
							bemap.put("COUNT", count);
							countList.clear();
							countList.add(bemap);
						}
					} else {
						countList = resStatisticBiz.statisticJointCountByOrg(recruit, orgFlowList, trainTypeId, docTypeList);
					}
					doctorCountList.addAll(countList);
				}

				Map<String, String> countMap = new HashMap<>();
				for (Map<String, Object> bemap : doctorCountList) {
					countMap.put(bemap.get("ORGFLOW").toString(), bemap.get("COUNT").toString());
				}
				model.addAttribute("countMap", countMap);
				model.addAttribute("orgs", orgs);
                model.addAttribute("seeFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
			} else {
				for (SysOrg o : orgs) {
					orgFlowList = new ArrayList<String>();
					ResOrgSpe resOrgSpe = new ResOrgSpe();
					resOrgSpe.setOrgFlow(o.getOrgFlow());
                    resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
					List<ResOrgSpe> orgSpeList = resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
					if (orgSpeList != null && !orgSpeList.isEmpty()) {//每家主基地的专业
						for (ResOrgSpe r : orgSpeList) {
                            orgSpeFlagMap.put(o.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
						}
					}
					orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
                    if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {//如果地市不为空时，只查询当前地市的基地人数。否则需要查询协同基地人数
						List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
						if (jointOrgList != null && !jointOrgList.isEmpty()) {
							for (ResJointOrg resJointOrg : jointOrgList) {
								orgFlowList.add(resJointOrg.getJointOrgFlow());
							}
						}
					}
					//每家基地每个专业的医师培训记录总数
					List<Map<String, Object>> doctorCountList = resStatisticBiz.statisticJointCount(recruit, orgFlowList, doctor, docTypeList);
					if (doctorCountList != null && !doctorCountList.isEmpty()) {
						for (Map<String, Object> en : doctorCountList) {
							Object key = o.getOrgFlow() + en.get("key");
							Object value = en.get("value");
							totalCountMap.put(key, value);
						}
					}
				}
                model.addAttribute("seeFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
		}
		model.addAttribute("totalCountMap", totalCountMap);
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);
		return "jsres/global/report/doctorNumSearch";
	}

	@RequestMapping(value = "/findTrainCharts", method = RequestMethod.POST)
	public String findTrainCharts(String orgLevel, String sessionNumber, String speName, Model model, String datas[],HttpServletRequest request) {
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = new ArrayList<SysOrg>();
		List<String> orgFlowList = new ArrayList<String>();
		SysOrg org = new SysOrg();
		SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if (StringUtil.isNotBlank(orgLevel)) {
			org.setOrgLevelId(orgLevel);
		}
		orgs = orgBiz.searchAllSysOrg(org);
		Map<String, List<ResJointOrg>> map = new HashMap<>();
		if (orgs != null && !orgs.isEmpty()) {
			for (SysOrg o : orgs) {
				orgFlowList.add(o.getOrgFlow());
				List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());
				if (joinOrgs != null && joinOrgs.size() > 0) {
					map.put(o.getOrgFlow(), joinOrgs);
					for (ResJointOrg jointOrg : joinOrgs) {
						if (!orgFlowList.contains(jointOrg.getJointOrgFlow())) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		model.addAttribute("orgMap", map);
		model.addAttribute("orgs", orgs);
//		List<Map<String,String>> list=resRecBiz.findTrainCharts(orgFlowList,sessionNumber,speName);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s0 : datas) {
				docTypeList.add(s0);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		String resTrainingSpeId = "";
		List<Map<String, String>> list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList, sessionNumber, speName,
				docTypeList,resTrainingSpeId);
		Map<String, String> orgCountMap = new HashMap<>();
		if (list != null && list.size() > 0) {
			for (Map<String, String> m : list) {
				orgCountMap.put(m.get("orgFlow"), m.get("countNum"));
			}
		}
		model.addAttribute("orgCountMap", orgCountMap);
		return "jsres/global/report/findTrainCharts";
	}


	@RequestMapping(value = "/findTrainChartsAcc", method = RequestMethod.POST)
	public String findTrainChartsAcc(String orgLevel, String sessionNumber, String speName, Model model, String datas[],HttpServletRequest request) {
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = new ArrayList<SysOrg>();
		List<String> orgFlowList = new ArrayList<String>();
		SysOrg org = new SysOrg();
		SysOrg s = orgBiz.readSysOrg(user.getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if (StringUtil.isNotBlank(orgLevel)) {
			org.setOrgLevelId(orgLevel);
		}
		orgs = orgBiz.searchAllSysOrg(org);
		Map<String, List<ResJointOrg>> map = new HashMap<>();
		if (orgs != null && !orgs.isEmpty()) {
			for (SysOrg o : orgs) {
				orgFlowList.add(o.getOrgFlow());
				List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());
				if (joinOrgs != null && joinOrgs.size() > 0) {
					map.put(o.getOrgFlow(), joinOrgs);
					for (ResJointOrg jointOrg : joinOrgs) {
						if (!orgFlowList.contains(jointOrg.getJointOrgFlow())) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		model.addAttribute("orgMap", map);
		model.addAttribute("orgs", orgs);
//		List<Map<String,String>> list=resRecBiz.findTrainCharts(orgFlowList,sessionNumber,speName);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s0 : datas) {
				docTypeList.add(s0);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		String resTrainingSpeId = "";
		List<Map<String, String>> list = resDoctorDelayTeturnBiz.findTrainCharts(orgFlowList, sessionNumber, speName,
				docTypeList,resTrainingSpeId);
		Map<String, String> orgCountMap = new HashMap<>();
		if (list != null && list.size() > 0) {
			for (Map<String, String> m : list) {
				orgCountMap.put(m.get("orgFlow"), m.get("countNum"));
			}
		}
		model.addAttribute("orgCountMap", orgCountMap);
		return "jsres/global/report/findTrainCharts";
	}


	@RequestMapping(value = "/graduateNumSearch")
	public String graduateNumSearch(String sessionNumber, String workOrgName, Model model) throws UnsupportedEncodingException {
		workOrgName = java.net.URLDecoder.decode(workOrgName, "UTF-8");
		List<Map<String, Object>> graduates = new ArrayList<>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtil.isBlank(sessionNumber)) {
			sessionNumber = DateUtil.getYear();
		}
		paramMap.put("sessionNumber", sessionNumber);
		paramMap.put("workOrgName", workOrgName);
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			SysUser user = GlobalContext.getCurrentUser();
			SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
			SysOrg sysOrg2 = new SysOrg();
			sysOrg2.setOrgProvId("320000");
			sysOrg2.setOrgCityId(org.getOrgCityId());
            sysOrg2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			paramMap.put("cityOrg", sysOrg2);
		}
		Map<String, Object> graduatesMap = new HashMap<String, Object>();
		if (StringUtil.isBlank(workOrgName)) {
            List<SysDict> SendSchoolList = com.pinde.core.common.enums.DictTypeEnum.SendSchool.getSysDictList();
			List<String> schools = new ArrayList<>();
			for (SysDict dict : SendSchoolList) {
				schools.add(dict.getDictName());
			}
			paramMap.put("SendSchoolList", schools);
			graduates = jsResDoctorBiz.searchGraduatesByOrg(paramMap);
			if (graduates != null && graduates.size() > 0) {
				for (Map<String, Object> map : graduates) {
					graduatesMap.put(map.get("SCHOOLNAME").toString(), map.get("GRADUATESCOUNT"));
				}
			}
			model.addAttribute("schools", schools);
            model.addAttribute("seeFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		} else {
			graduates = jsResDoctorBiz.searchGraduates(paramMap);
			if (graduates != null && graduates.size() > 0) {
				for (Map<String, Object> map : graduates) {
					graduatesMap.put(map.get("TRAININGSPEID").toString(), map.get("GRADUATESCOUNT"));
				}
			}
            model.addAttribute("seeFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
		}
		model.addAttribute("graduatesMap", graduatesMap);
		return "jsres/global/report/graduateNumSearch";
	}

	@RequestMapping(value = "/orgSpe")
	public String orgSpe(String cityId, String orgLevel, Model model, String orgFlow) {

		List<String> orgFlowList = new ArrayList<String>();
		SysOrg sysOrg2 = new SysOrg();
		if (StringUtil.isNotBlank(cityId)) {
			sysOrg2.setOrgCityId(cityId);
		}
		if (StringUtil.isNotBlank(orgLevel)) {
			sysOrg2.setOrgLevelId(orgLevel);
		}
		sysOrg2.setOrgProvId("320000");
        sysOrg2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		SysOrg org = new SysOrg();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			if (StringUtil.isNotBlank(orgFlow)) {
				org = orgBiz.readSysOrg(orgFlow);
			} else {
				SysUser user = GlobalContext.getCurrentUser();
				org = orgBiz.readSysOrg(user.getOrgFlow());
			}
			sysOrg2.setOrgCityId(org.getOrgCityId());
		}
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(sysOrg2);
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			if (StringUtil.isNotBlank(orgFlow)) {
				orgs = new ArrayList<>();
				orgs.add(org);
			}
		}
		if (orgs != null && orgs.size() > 0) {
			for (SysOrg o : orgs) {
				orgFlowList.add(o.getOrgFlow());
			}
		}
		//每家基地每个专业的医师培训记录总数
		Map<Object, Object> orgSpeFlagMap = new HashMap<Object, Object>();//基地专业标志的的map
		if (orgs != null && orgs.size() > 0) {
			for (SysOrg o : orgs) {
				orgFlowList = new ArrayList<String>();
				ResOrgSpe resOrgSpe = new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList = resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if (orgSpeList != null && !orgSpeList.isEmpty()) {//每家主基地的专业
					for (ResOrgSpe r : orgSpeList) {
                        orgSpeFlagMap.put(o.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
			}
		}
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);
		model.addAttribute("sysOrgList", orgs);
		return "jsres/global/report/orgSpe";
	}

	@RequestMapping(value = "/exTrainingDocPdf")
	public void exTrainingDocPdf(String orgFlow, String archiveFlow, HttpServletResponse response) throws Exception {
		if (StringUtil.isBlank(orgFlow)) {
			throw new Exception("机构标识符为空...");
		}

		SysOrg org = orgBiz.readSysOrg(orgFlow);
		if (org == null) {
			throw new Exception("读取机构信息失败...");
		}

		final String fileName = org.getOrgName() + "在培医师花名册";
		String outputFileClass = ResourceLoader.getPath("");
		String outputFile = new File(outputFileClass)
				+ "/load/" + fileName + ".pdf";
		File file = new File(outputFile);
		//root 储存的数据
		final Map<String, Object> root = new HashMap<String, Object>();

		//基地名称
		root.put("orgName", org.getOrgName());
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		root.put("currUser", user);
		//当前时间
		root.put("currDate", DateUtil.getCurrDate());


		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		//各届别各类型人员数量
		//获取当前配置届别
		String sessionNumber = InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
		List<String> sessions = new ArrayList<String>();
		if (StringUtil.isNotBlank(sessionNumber)) {
			Integer sessionInt = Integer.valueOf(sessionNumber);

			paramMap.put("sessionNumber", sessionInt);
			List<Map<String, Object>> counts = jsResDoctorBiz.getDocCountBySession(paramMap);
			String k = "oneYear";
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());

			sessionInt--;
			paramMap.put("sessionNumber", sessionInt);
			k = "twoYear";
			counts = jsResDoctorBiz.getDocCountBySession(paramMap);
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());

			sessionInt--;
			paramMap.put("sessionNumber", sessionInt);
			k = "threeYear";
			counts = jsResDoctorBiz.getDocCountBySession(paramMap);
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());
		}

		//获取所有学员
		paramMap.put("sessions", sessions);
		List<Map<String, Object>> docListMap = jsResDoctorBiz.getDocByOrg(paramMap);
		root.put("docMapList", docListMap);

		// 模板数据
		DocumentVo vo = new DocumentVo() {
			@Override
			public String findPrimaryKey() {
				return fileName;
			}

			@Override
			public Map<String, Object> fillDataMap() {
				return root;
			}
		};

		String template = "jsres/trainingDocTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, vo, outputFile);

		pubFileBiz.downFile(file, response);

	}

	@RequestMapping(value = "/exTrainingDocLogPdf")
	public void exTrainingDocLogPdf(String orgFlow, String archiveFlow, String sessionNumber, HttpServletResponse response) throws Exception {
		if (StringUtil.isBlank(orgFlow)) {
			throw new Exception("机构标识符为空...");
		}
		if (StringUtil.isBlank(archiveFlow)) {
			throw new Exception("存档时间为空...");
		}
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		if (org == null) {
			throw new Exception("读取机构信息失败...");
		}

		final String fileName = org.getOrgName() + "在培医师花名册";
		String outputFileClass = ResourceLoader.getPath("");
		String outputFile = new File(outputFileClass)
				+ "/load/" + fileName + ".pdf";
		File file = new File(outputFile);
		//root 储存的数据
		final Map<String, Object> root = new HashMap<String, Object>();

		//基地名称
		root.put("orgName", org.getOrgName());
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		root.put("currUser", user);
		//当前时间
		root.put("currDate", DateUtil.getCurrDate());


		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("archiveFlow", archiveFlow);
		//各届别各类型人员数量
		List<String> sessions = new ArrayList<String>();
		if (StringUtil.isNotBlank(sessionNumber)) {
			Integer sessionInt = Integer.valueOf(sessionNumber);

			paramMap.put("sessionNumber", sessionInt);
			List<Map<String, Object>> counts = jsResDoctorBiz.getDocLogCountBySession(paramMap);
			String k = "oneYear";
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());

			sessionInt--;
			paramMap.put("sessionNumber", sessionInt);
			k = "twoYear";
			counts = jsResDoctorBiz.getDocLogCountBySession(paramMap);
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());

			sessionInt--;
			paramMap.put("sessionNumber", sessionInt);
			k = "threeYear";
			counts = jsResDoctorBiz.getDocLogCountBySession(paramMap);
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());
		}

		//获取所有学员
		paramMap.put("sessions", sessions);
		List<Map<String, Object>> docListMap = jsResDoctorBiz.getDocLogByOrg(paramMap);
		root.put("docMapList", docListMap);

		// 模板数据
		DocumentVo vo = new DocumentVo() {
			@Override
			public String findPrimaryKey() {
				return fileName;
			}

			@Override
			public Map<String, Object> fillDataMap() {
				return root;
			}
		};

		String template = "jsres/trainingDocTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, vo, outputFile);

		pubFileBiz.downFile(file, response);

	}

	//国家基地导出在培助理全科学员
	@RequestMapping(value = "/exZLTrainingDocPdf")
	public void exZLTrainingDocPdf(String orgFlow, final HttpServletResponse response) throws Exception {
		if (StringUtil.isBlank(orgFlow)) {
			throw new Exception("机构标识符为空...");
		}

		SysOrg org = orgBiz.readSysOrg(orgFlow);
		if (org == null) {
			throw new Exception("读取机构信息失败...");
		}

		final String fileName = org.getOrgName() + "在培助理全科花名册";
		String outputFileClass = ResourceLoader.getPath("");
		String outputFile = new File(outputFileClass)
				+ "/load/" + fileName + ".pdf";
		File file = new File(outputFile);
		//root 储存的数据
		final Map<String, Object> root = new HashMap<String, Object>();

		//基地名称
		root.put("orgName", org.getOrgName());
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		root.put("currUser", user);
		//当前时间
		root.put("currDate", DateUtil.getCurrDate());


		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		//各届别各类型人员数量
		//获取当前配置届别
		String sessionNumber = InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
		List<String> sessions = new ArrayList<String>();
		if (StringUtil.isNotBlank(sessionNumber)) {
			Integer sessionInt = Integer.valueOf(sessionNumber);

			paramMap.put("sessionNumber", sessionInt);
			List<Map<String, Object>> counts = jsResDoctorBiz.getAssiGeneralDocCountBySession(paramMap);
			String k = "oneYear";
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());

			sessionInt--;
			paramMap.put("sessionNumber", sessionInt);
			k = "twoYear";
			counts = jsResDoctorBiz.getAssiGeneralDocCountBySession(paramMap);
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());
		}

		//获取所有学员
		paramMap.put("sessions", sessions);
		List<Map<String, Object>> docListMap = jsResDoctorBiz.getAssiGeneralDocByOrg(paramMap);
		root.put("docMapList", docListMap);

		// 模板数据
		DocumentVo vo = new DocumentVo() {
			@Override
			public String findPrimaryKey() {
				return fileName;
			}

			@Override
			public Map<String, Object> fillDataMap() {
				return root;
			}
		};

		String template = "jsres/trainingAssiGeneralDocTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, vo, outputFile);

		pubFileBiz.downFile(file, response);

	}

	//国家基地导出在培助理全科学员
	@RequestMapping(value = "/exZLTrainingDocLogPdf")
	public void exZLTrainingDocLogPdf(String orgFlow, String archiveFlow, String sessionNumber, final HttpServletResponse response) throws Exception {
		if (StringUtil.isBlank(orgFlow)) {
			throw new Exception("机构标识符为空...");
		}
		if (StringUtil.isBlank(archiveFlow)) {
			throw new Exception("存档时间为空...");
		}
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		if (org == null) {
			throw new Exception("读取机构信息失败...");
		}

		final String fileName = org.getOrgName() + "在培助理全科花名册";
		String outputFileClass = ResourceLoader.getPath("");
		String outputFile = new File(outputFileClass)
				+ "/load/" + fileName + ".pdf";
		File file = new File(outputFile);
		//root 储存的数据
		final Map<String, Object> root = new HashMap<String, Object>();

		//基地名称
		root.put("orgName", org.getOrgName());
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		root.put("currUser", user);
		//当前时间
		root.put("currDate", DateUtil.getCurrDate());


		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("archiveFlow", archiveFlow);
		//各届别各类型人员数量
		List<String> sessions = new ArrayList<String>();
		if (StringUtil.isNotBlank(sessionNumber)) {
			Integer sessionInt = Integer.valueOf(sessionNumber);

			paramMap.put("sessionNumber", sessionInt);
			List<Map<String, Object>> counts = jsResDoctorBiz.getAssiGeneralDocLogCountBySession(paramMap);
			String k = "oneYear";
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());

			sessionInt--;
			paramMap.put("sessionNumber", sessionInt);
			k = "twoYear";
			counts = jsResDoctorBiz.getAssiGeneralDocLogCountBySession(paramMap);
			setRootDocCount(k, counts, root);
			root.put(k, sessionInt.toString());
			sessions.add(sessionInt.toString());
		}

		//获取所有学员
		paramMap.put("sessions", sessions);
		List<Map<String, Object>> docListMap = jsResDoctorBiz.getAssiGeneralDocLogByOrg(paramMap);
		root.put("docMapList", docListMap);

		// 模板数据
		DocumentVo vo = new DocumentVo() {
			@Override
			public String findPrimaryKey() {
				return fileName;
			}

			@Override
			public Map<String, Object> fillDataMap() {
				return root;
			}
		};

		String template = "jsres/trainingAssiGeneralDocTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, vo, outputFile);

		pubFileBiz.downFile(file, response);

	}

	private void setRootDocCount(String k, List<Map<String, Object>> counts, Map<String, Object> root) {
		for (Map<String, Object> m : counts) {
			Object docTypeId = m.get("doctorTypeId");
			Object docCount = m.get("docCount");
			if (docTypeId != null) {
				root.put(docTypeId.toString() + k, docCount);
			}
		}
	}
	/****************************高******校******管******理******员******角******色************************************************/
	/**
	 * 基地变更查询
	 *
	 * @param currentPage
	 * @param doctor
	 * @param statusFlag
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/changeBaseMainForUni"})
	public String changeBaseMainForUni(Integer currentPage, ResDoctor doctor, String statusFlag, String isQuery,
									   HttpServletRequest request, Model model, String datas[]) {
        doctor.setDoctorTypeId(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
        doctor.setDoctorTypeName(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getName());
		SysUser currUser = GlobalContext.getCurrentUser();
		SysOrg currOrg = orgBiz.readSysOrg(currUser.getOrgFlow());
		doctor.setWorkOrgId(currOrg.getSendSchoolId());
		doctor.setWorkOrgName(currOrg.getSendSchoolName());
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		if (StringUtil.isBlank(doctor.getTrainingTypeId()) && StringUtil.isBlank(isQuery)) {
            doctor.setTrainingTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		}
		model.addAttribute("trainingTypeId", doctor.getTrainingTypeId());
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		if(StringUtil.isNotEmpty(currUser.getSchool())){
			doctor.setSchool(currUser.getSchool());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlowForUni(orgHistory, changeStatusIdList, doctor, docTypeList);
		model.addAttribute("docOrgHistoryExtList", historyExts);
		return "jsres/university/doctor/turnMain";
	}

	/**
	 * 专业变更查询
	 *
	 * @param currentPage
	 * @param doctor
	 * @param request
	 * @param model
	 * @param orgFlow
	 * @param passFlag
	 * @return
	 */
	@RequestMapping(value = {"/searchChangeSpeForUni"})
	public String searchChangeSpeForUni(Integer currentPage, String orgFlow, ResDoctor doctor, HttpServletRequest request, Model model, String passFlag) {
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
		SysUser sysUser = GlobalContext.getCurrentUser();
		orgHistory.setOrgFlow(orgFlow);
		if (StringUtil.isNotBlank(passFlag)) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(passFlag)) {
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
			} else {
                changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
			}
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseAuditUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplySpeEnum.GlobalWaitingAudit.getId());
		}
		List<SysOrg> orgs = new ArrayList<SysOrg>();
		SysOrg currOrg = orgBiz.readSysOrg(sysUser.getOrgFlow());
		orgs = orgBiz.getUniOrgs(currOrg.getSendSchoolId(), currOrg.getSendSchoolName());
		model.addAttribute("orgs", orgs);
        doctor.setDoctorTypeId(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
        doctor.setDoctorTypeName(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getName());
		doctor.setWorkOrgId(currOrg.getSendSchoolId());
		doctor.setWorkOrgName(currOrg.getSendSchoolName());
		if(StringUtil.isNotEmpty(sysUser.getSchool())){
			doctor.setSchool(sysUser.getSchool());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtListForUni(orgHistory, changeStatusIdList, doctor);
		model.addAttribute("historyExts", historyExts);
		return "jsres/university/changeSpeMain";
	}

	/**
	 * 双向评价
	 *
	 * @param gradeRole
	 * @param deptFlow
	 * @param recOrgFlow
	 * @param userName
	 * @param operStartDate
	 * @param operEndDate
	 * @param role
	 * @param sessionNumber
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/gradeSearchForUni"})
	public String gradeSearchForUni(
			String gradeRole,
			String deptFlow,
			String recOrgFlow,
			String userName,
			String operStartDate,
			String operEndDate,
			String role,
			String sessionNumber,
			Model model, Integer currentPage, HttpServletRequest request
	) {

		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}
		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		SysUser currUser=GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		if (org == null || (StringUtil.isBlank(org.getSendSchoolId()) && StringUtil.isBlank(org.getSendSchoolName()))) {
			return "jsres/university/gradeSearch";
		}
		List<SysOrg> sysOrgList = orgBiz.getUniOrgs(org.getSendSchoolId(), org.getSendSchoolName());
		model.addAttribute("sysOrgList", sysOrgList);
		if(StringUtil.isBlank(recOrgFlow)){
			model.addAttribute("datas", null);
			return "jsres/university/gradeSearch";
		}
		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(currUser.getSchool())){
			paramMap.put("school", currUser.getSchool());
		}
		paramMap.put("orgFlow", recOrgFlow);
		paramMap.put("operStartDate", operStartDate);
		paramMap.put("operEndDate", operEndDate);
		paramMap.put("userName", userName);
		paramMap.put("deptFlow", deptFlow);
		paramMap.put("org", org);
		//查出当前机构的所有科室
		List<SysDept> deptList = deptBiz.searchDeptByOrg(recOrgFlow);
		model.addAttribute("depts", deptList);

		List<String> keys = new ArrayList<String>();
		Object waitSort = null;
		Map<String, Object> scoreMap = new HashMap<String, Object>();
		if ("teacher".equals(gradeRole)) {
			//带教flow
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			paramMap.put("roleFlow", teacherRoleFlow);

            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList", assessCfgList);
			//查出当前机构的所有带过本高校学员的带教老师
			PageHelper.startPage(currentPage, getPageSize(request));
			List<SysUser> userList = resGradeBiz.getUserByRecForUni(paramMap);
			model.addAttribute("datas", userList);
			waitSort = userList;
			if (userList != null && !userList.isEmpty()) {
				for (SysUser su : userList) {
					keys.add(su.getUserFlow());
					int score = 0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if (resAssessCfgTitleForm.getItemList() != null && !resAssessCfgTitleForm.getItemList().isEmpty()) {
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s = Integer.parseInt(resAssessCfgItemForm.getScore());
								score += s;
							}
						}
					}
					scoreMap.put(su.getUserFlow(), score);
				}
			}
			paramMap.put("teacherFlows", keys);
		} else if ("head".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList", assessCfgList);

			PageHelper.startPage(currentPage, getPageSize(request));
			List<SysDept> depts = resGradeBiz.getDeptByRecForUni(paramMap);
			model.addAttribute("datas", depts);
			waitSort = depts;
			if (depts != null && !depts.isEmpty()) {
				for (SysDept sd : depts) {
					keys.add(sd.getDeptFlow());
					int score = 0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
							int s = Integer.parseInt(resAssessCfgItemForm.getScore());
							score += s;
						}
					}
					scoreMap.put(sd.getDeptFlow(), score);
				}
			}
			paramMap.put("deptFlows", keys);
		}
		if (keys != null && keys.size() > 0) {
			//获取评分数据
			List<Map<String, String>> recList = resGradeBiz.getRecContentByProcessForUni(paramMap);

			if (StringUtil.isNotBlank(sessionNumber)) {
				paramMap.put("sessionNumber", sessionNumber);
				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recDateList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
					for (Map<String, String> m : recList) {
						if (sessionNumber.equals(m.get("sessionNumber")))
							recDateList.add(m);
					}
					if (recDateList != null && !recDateList.isEmpty()) {
						Map<String, Float> recDateAvgMap = avg(recDateList);
						model.addAttribute("recDateAvgMap", recDateAvgMap);
					}
				}
			} else {
				String date = DateUtil.getCurrDateTime("yyyy");
				model.addAttribute("currDate", date);
				paramMap.put("sessionNumber", date);

				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recCurrDateList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
					for (Map<String, String> m : recList) {
						if (date.equals(m.get("sessionNumber")))
							recCurrDateList.add(m);
					}
					if (recCurrDateList != null && !recCurrDateList.isEmpty()) {
						Map<String, Float> recCurrDateAvgMap = avg(recCurrDateList);
						model.addAttribute("recCurrDateAvgMap", recCurrDateAvgMap);
					}
				}
				int d = Integer.valueOf(date);
				d = d - 1;
				date = String.valueOf(d);
				model.addAttribute("PreviouYearDate", date);
				paramMap.put("sessionNumber", date);

				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recpreviouYearList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
					for (Map<String, String> m : recList) {
						if (date.equals(m.get("sessionNumber")))
							recpreviouYearList.add(m);
					}
					if (recpreviouYearList != null && !recpreviouYearList.isEmpty()) {
						Map<String, Float> recpreviouYearAvgMap = avg(recpreviouYearList);
						model.addAttribute("recpreviouYearAvgMap", recpreviouYearAvgMap);
					}
				}
				int s = Integer.valueOf(date);
				s = s - 1;
				date = String.valueOf(s);
				model.addAttribute("FirstTwoYearsDate", date);
				paramMap.put("sessionNumber", date);
				if (recList != null && recList.size() > 0) {
					List<Map<String, String>> recFirstTwoYearsList = new ArrayList<>();// resRecBiz.getRecContentByProcessForUni(paramMap);
					for (Map<String, String> m : recList) {
						if (date.equals(m.get("sessionNumber")))
							recFirstTwoYearsList.add(m);
					}
					if (recFirstTwoYearsList != null && !recFirstTwoYearsList.isEmpty()) {
						Map<String, Float> recFirstTwoYearsAvgMap = avg(recFirstTwoYearsList);
						model.addAttribute("recFirstTwoYearsAvgMap", recFirstTwoYearsAvgMap);
					}
				}
			}

			if (recList != null && !recList.isEmpty()) {
				Map<String, Float> avgMap = avg(recList);
				if (waitSort != null) {
					final Map<String, Float> scoreMapTemp = avgMap;
					if ("teacher".equals(gradeRole)) {
						List<SysUser> userList = (List<SysUser>) waitSort;
						Collections.sort(userList, new Comparator<SysUser>() {
							@Override
							public int compare(SysUser u1, SysUser u2) {
								String k1 = u1.getUserFlow();
								String k2 = u2.getUserFlow();
								Float s1 = scoreMapTemp.get(k1 + "_Total");
								Float s2 = scoreMapTemp.get(k2 + "_Total");
								if (s1 == null) {
									s1 = 0f;
								}
								if (s2 == null) {
									s2 = 0f;
								}
								Float result = s2 - s1;

								return result > 0 ? 1 : result == 0 ? 0 : -1;
							}

						});
					} else if ("head".equals(gradeRole)) {
						List<SysDept> depts = (List<SysDept>) waitSort;
						Collections.sort(depts, new Comparator<SysDept>() {
							@Override
							public int compare(SysDept d1, SysDept d2) {
								String k1 = d1.getDeptFlow();
								String k2 = d2.getDeptFlow();
								Float s1 = scoreMapTemp.get(k1 + "_Total");
								Float s2 = scoreMapTemp.get(k2 + "_Total");
								if (s1 == null) {
									s1 = 0f;
								}
								if (s2 == null) {
									s2 = 0f;
								}
								Float result = s2 - s1;
								return result > 0 ? 1 : result == 0 ? 0 : -1;
							}

						});
					}
				}
				model.addAttribute("avgMap", avgMap);
			}
		}
		model.addAttribute("scoreMap", scoreMap);
		return "jsres/university/gradeSearch";
	}

	//考评指标维护
	@RequestMapping(value = "/evaluationIndex", method = {RequestMethod.GET})
	public String evaluationIndex(Model model) throws DocumentException {
		ResDoctorProcessEvalConfig config = jsResDoctorBiz.readEvalConfig(GlobalContext.getCurrentUser().getOrgFlow());
		if (null != config && StringUtil.isNotBlank(config.getFormCfg())) {
			Document dom = DocumentHelper.parseText(config.getFormCfg());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if (null != titleElementList && titleElementList.size() > 0) {
				List<JsEvalCfgTitleExt> titleFormList = new ArrayList<JsEvalCfgTitleExt>();
				for (Element te : titleElementList) {
					JsEvalCfgTitleExt titleForm = new JsEvalCfgTitleExt();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setOrderNum(te.attributeValue("orderNum"));
					List<Element> itemElementList = te.elements("item");
					List<JsEvalCfgItemExt> itemFormList = null;
					if (null != itemElementList && itemElementList.size() > 0) {
						itemFormList = new ArrayList<JsEvalCfgItemExt>();
						for (Element ie : itemElementList) {
							JsEvalCfgItemExt itemForm = new JsEvalCfgItemExt();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
							itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
							itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
							itemFormList.add(itemForm);
						}
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
				}
				model.addAttribute("titleFormList", titleFormList);
			}
		}
		model.addAttribute("evalConfig", config);
		return "jsres/hospital/evaluationIndex";
	}

	//保存考评项目
	@RequestMapping(value = {"/saveEvalConfig"})
	@ResponseBody
	public String saveEvalConfig(String configFlow, JsEvalCfgTitleExt title) throws DocumentException {
		int result = jsResDoctorBiz.saveEvalConfig(configFlow, title);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	//修改保存评分指标
	@RequestMapping(value = "/modifyItem")
	@ResponseBody
	public String modifyItem(String configFlow, JsEvalCfgItemExt itemForm) throws DocumentException {
		int result = jsResDoctorBiz.modifyItem(configFlow, itemForm);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	//保存所有评分指标
	@RequestMapping(value = "/saveEvalItemList")
	@ResponseBody
	public String saveEvalItemList(@RequestBody JsEvalCfgExt form) throws DocumentException {
		int result = jsResDoctorBiz.saveFormItemList(form);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	//删除评分项目
	@RequestMapping(value = "/deleteTitle")
	@ResponseBody
	public String deleteTitle(String configFlow, String id) throws Exception {
		int result = jsResDoctorBiz.deleteTitle(configFlow, id);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

	//删除评分指标
	@RequestMapping(value = "/deleteItem")
	@ResponseBody
	public String deleteItem(String configFlow, String id) throws Exception {
		int result = jsResDoctorBiz.deleteItem(configFlow, id);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 导出双向评价查询
	 *
	 * @param gradeRole
	 * @param deptFlow
	 * @param recOrgFlow
	 * @param userName
	 * @param operStartDate
	 * @param operEndDate
	 * @param role
	 * @param sessionNumber
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = {"/exportGradeSearch"})
	public void exportGradeSearch(
			String gradeRole,
			String deptFlow,
			String recOrgFlow,
			String userName,
			String operStartDate,
			String operEndDate,
			String role,
			String sessionNumber,
			HttpServletResponse response, HttpServletRequest request
	) throws Exception {

		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		//省厅下基地及协同基地
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
			String orgFlowRec = GlobalContext.getCurrentUser().getOrgFlow();
			SysOrg org = orgBiz.readSysOrg(orgFlowRec);
            if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
				org.setOrgCityId("");
				List<SysOrg> sysOrgList = orgBiz.searOrgTeacherRoleCheckUser(org);
			}
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
				List<SysOrg> sysOrgList = orgBiz.searOrgTeacherRoleCheckUser(org);
			}
			if (StringUtil.isNotBlank(recOrgFlow)) {
				orgFlow = recOrgFlow;
			} else {
				String date = DateUtil.getCurrDateTime("yyyy");
				int d = Integer.valueOf(date);
				d = d - 1;
				date = String.valueOf(d);
				int s = Integer.valueOf(date);
				s = s - 1;
				date = String.valueOf(s);
//				return "jsres/hospital/gradeSearch";
			}
		}

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("operStartDate", operStartDate);
		paramMap.put("operEndDate", operEndDate);
		paramMap.put("userName", userName);
		paramMap.put("deptFlow", deptFlow);
		List<String> keys = new ArrayList<String>();
		//专业基地下的科室
		List<String> speDepts = new ArrayList<>();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(role)) {
			ResTrainingSpeDept resTrainingSpeDept = new ResTrainingSpeDept();
			resTrainingSpeDept.setOrgFlow(orgFlow);
			resTrainingSpeDept.setTrainingSpeId(GlobalContext.getCurrentUser().getResTrainingSpeId());
			List<ResTrainingSpeDept> resTrainingSpeDepts = resTrainingSpeDeptBiz.search(resTrainingSpeDept);
			if (resTrainingSpeDepts != null && resTrainingSpeDepts.size() > 0) {
				speDepts = resTrainingSpeDepts.stream().map(resTrainingSpeDept1 -> resTrainingSpeDept1.getDeptFlow()).collect(Collectors.toList());
			}
		}
		Object waitSort = null;
		Map<String, Object> scoreMap = new HashMap<String, Object>();
		if ("teacher".equals(gradeRole)) {
			//带教flow
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			paramMap.put("roleFlow", teacherRoleFlow);

            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			//查出当前机构的所有带教老师
			List<SysUser> userList;
            if ((com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(role)) && (speDepts == null || speDepts.size() == 0)) {
				//没有配置科室的专业基地、专业基地秘书
				userList = null;
			} else {
				paramMap.put("speDepts", speDepts);
				userList = resGradeBiz.getUserByRec(paramMap);
			}
			waitSort = userList;
			if (userList != null && !userList.isEmpty()) {
				for (SysUser su : userList) {
					keys.add(su.getUserFlow());
					int score = 0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if (resAssessCfgTitleForm.getItemList() != null && !resAssessCfgTitleForm.getItemList().isEmpty()) {
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s = Integer.parseInt(resAssessCfgItemForm.getScore());
								score += s;
							}
						}
					}
					scoreMap.put(su.getUserFlow(), score);
				}
			}
			paramMap.put("teacherFlows", keys);
		} else if ("head".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			List<SysDept> depts;
            if ((com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(role)) && (speDepts == null || speDepts.size() == 0)) {
				//没有配置科室的专业基地、专业基地秘书
				depts = null;
			} else {
				paramMap.put("speDepts", speDepts);
				depts = resGradeBiz.getDeptByRec(paramMap);
			}
			waitSort = depts;
			if (depts != null && !depts.isEmpty()) {
				for (SysDept sd : depts) {
					keys.add(sd.getDeptFlow());
					int score = 0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s = Integer.parseInt(resAssessCfgItemForm.getScore());
								score += s;
							}
						}
					}
					scoreMap.put(sd.getDeptFlow(), score);
				}
			}
			paramMap.put("deptFlows", keys);
		}
		Map<String, Float> scoreMapExcel = null;
		if (keys != null && keys.size() > 0) {
			//获取评分数据
			List<Map<String, String>> recList = resGradeBiz.getJsresRecContentByProcess(paramMap);
			if (recList != null && !recList.isEmpty()) {
				Map<String, Float> avgMap = avg(recList);
				scoreMapExcel = avgMap;
				if (waitSort != null) {
					final Map<String, Float> scoreMapTemp = avgMap;
					if ("teacher".equals(gradeRole)) {
						List<SysUser> userList = (List<SysUser>) waitSort;
						Collections.sort(userList, new Comparator<SysUser>() {
							@Override
							public int compare(SysUser u1, SysUser u2) {
								String k1 = u1.getUserFlow();
								String k2 = u2.getUserFlow();
								Float s1 = scoreMapTemp.get(k1 + "_Total");
								Float s2 = scoreMapTemp.get(k2 + "_Total");
								if (s1 == null) {
									s1 = 0f;
								}
								if (s2 == null) {
									s2 = 0f;
								}
								Float result = s2 - s1;

								return result > 0 ? 1 : result == 0 ? 0 : -1;
							}

						});
					} else if ("head".equals(gradeRole)) {
						List<SysDept> depts = (List<SysDept>) waitSort;
						Collections.sort(depts, new Comparator<SysDept>() {
							@Override
							public int compare(SysDept d1, SysDept d2) {
								String k1 = d1.getDeptFlow();
								String k2 = d2.getDeptFlow();
								Float s1 = scoreMapTemp.get(k1 + "_Total");
								Float s2 = scoreMapTemp.get(k2 + "_Total");
								if (s1 == null) {
									s1 = 0f;
								}
								if (s2 == null) {
									s2 = 0f;
								}
								Float result = s2 - s1;
								return result > 0 ? 1 : result == 0 ? 0 : -1;
							}

						});
					}
				}
			}
		}

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFFont font = wb.createFont();	//设置字体
		font.setBold(true);//粗体显示
		HSSFCellStyle fontLeftStyle = wb.createCellStyle();	//粗体居中显示
		fontLeftStyle.setFont(font);
		fontLeftStyle.setAlignment(HorizontalAlignment.LEFT);
		fontLeftStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		List<Map<String, String>> exportList = new ArrayList<>();
		HSSFSheet sheet = wb.createSheet("双向评价数据汇总");//sheet名称
		if ("teacher".equals(gradeRole)) {
			HSSFSheet sheet2 = wb.createSheet("评价详情");//sheet名称
			//设置每一列的宽度 行和列都是从0开始
			sheet.setColumnWidth(0,10000);
			sheet.setColumnWidth(1,10000);
			sheet.setColumnWidth(2,10000);
			sheet2.setColumnWidth(0,5000);
			sheet2.setColumnWidth(1,5000);
			sheet2.setColumnWidth(2,5000);
			sheet2.setColumnWidth(3,5000);
			sheet2.setColumnWidth(4,5000);
			sheet2.setColumnWidth(5,25000);
			sheet2.setColumnWidth(6,5000);
			sheet2.setColumnWidth(7,5000);
			sheet2.setColumnWidth(8,5000);
			HSSFRow row0 = sheet.createRow(0);//第一行
			HSSFRow rowsheet2 = sheet2.createRow(0);//第一行
			String[] titles0 = new String[]{
					"老师姓名",
					"科室信息",
					"总均分"
			};
			String[] titleSheet2 = new String[]{
					"老师姓名",
					"科室",
					"总均分",
					"学生姓名",
					"学生年级",
					"评价指标",
					"标准分",
					"评分",
					"学生总分"
			};
			HSSFCell cellTitle0 = null;
			for (int i = 0; i < titles0.length; i++) {
				cellTitle0 = row0.createCell(i);
				cellTitle0.setCellValue(titles0[i]);
				cellTitle0.setCellStyle(fontLeftStyle);
				cellTitle0.setCellType(CellType.STRING);
			}

			HSSFCell cellTitleSheet0 = null;
			for (int i = 0; i < titleSheet2.length; i++) {
				cellTitleSheet0 = rowsheet2.createCell(i);
				cellTitleSheet0.setCellValue(titleSheet2[i]);
				cellTitleSheet0.setCellStyle(fontLeftStyle);
				cellTitleSheet0.setCellType(CellType.STRING);
			}
			List<SysUser> userList = (List<SysUser>) waitSort;
			//获取评分数据
			List<Map<String, String>> recList = resGradeBiz.getJsresRecContentByProcess2(paramMap);
			//查看表单
			Map<String, List<ResAssessCfgTitleForm>> assessCfgMap = assessCfgBiz.getParsedGradeMap(cfgCode);

			for (SysUser user : userList) {
				Map<String, String> map = new HashMap<>();
				map.put("teacherFlow", user.getUserFlow());
				map.put("teacherName", user.getUserName());
				map.put("deptFlow", user.getDeptFlow());
				map.put("deptInfo", user.getDeptName());
				map.put("avgScore", null == scoreMapExcel || null == scoreMapExcel.get(user.getUserFlow() + "_Total") ? "" : scoreMapExcel.get(user.getUserFlow() + "_Total") + "");
				exportList.add(map);
			}
			if (null!=exportList && exportList.size()>0){
				for (int i = 0; i < exportList.size(); i++) {
					String[] titles = new String[]{
							exportList.get(i).get("teacherName"),
							exportList.get(i).get("deptInfo"),
							exportList.get(i).get("avgScore")
					};
					HSSFRow row = sheet.createRow(i+1);
					HSSFCell cellTitle = null;
					for (int j = 0; j < titles.length; j++) {
						cellTitle = row.createCell(j);
						cellTitle.setCellValue(titles[j]);
						cellTitle.setCellStyle(styleLeft);
						cellTitle.setCellType(CellType.STRING);
					}
				}
				if (null!=recList && !recList.isEmpty()){
					int num=1;
					for (Map<String, String> exportMap : exportList) {
						List<Map<String, String>> collect = recList.stream().filter(r -> filterResRec(r, exportMap, "teacher")).collect(Collectors.toList());
						Integer recStartNum=num;
						if (null!=collect && !collect.isEmpty()){
							for (Map<String, String> recMap : collect) {
								if (StringUtil.isBlank(recMap.get("content"))){
									continue;
								}
								List<ResAssessCfgTitleForm> forms = assessCfgMap.get(recMap.get("cfgFlow"));
								if (null==forms || forms.isEmpty()){
									continue;
								}
								Map<String, Object> gradeMap = resRecBiz.parseGradeXml(recMap.get("content"));
								int startNum=num;
								for (int u = 0; u < forms.size(); u++) {
									List<ResAssessCfgItemForm> itemList = forms.get(u).getItemList();
									if (null!=itemList && itemList.size()>0){
										for (ResAssessCfgItemForm itemForm : itemList) {
											String[] titleGrade =new String[]{};
											Map<String,String> score=new HashMap<>();
											if (null !=gradeMap.get(itemForm.getId())){
												score=(Map<String,String>)gradeMap.get(itemForm.getId());
											}else {
												score.put("score","");
											}
											if (num==startNum) {
												titleGrade = new String[]{
														exportMap.get("teacherName"),
														exportMap.get("deptInfo"),
														exportMap.get("avgScore"),
														recMap.get("operUserName"),
														recMap.get("sessionNumber"),
														itemForm.getName(),
														itemForm.getScore(),
														score.get("score"),
														StringUtil.isBlank(gradeMap.get("totalScore").toString())?"":(String) gradeMap.get("totalScore")};
											} else {
												titleGrade = new String[]{
														"",
														"",
														"",
														"",
														"",
														itemForm.getName(),
														itemForm.getScore(),
														score.get("score"),
														""};
											}
											HSSFRow row = sheet2.createRow(num);
											HSSFCell cellTitle = null;
											for (int j = 0; j < titleGrade.length; j++) {
												cellTitle = row.createCell(j);
												cellTitle.setCellValue(titleGrade[j]);
												cellTitle.setCellStyle(styleLeft);
												cellTitle.setCellType(CellType.STRING);
											}
											num++;
										}
									}
								}
								CellRangeAddress address3 = new CellRangeAddress(startNum, num-1, 3, 3);//起始行，终止行，起始列，终止列
								CellRangeAddress address4 = new CellRangeAddress(startNum, num-1, 4, 4);//起始行，终止行，起始列，终止列
								CellRangeAddress address8 = new CellRangeAddress(startNum, num-1, 8, 8);//起始行，终止行，起始列，终止列
								sheet2.addMergedRegion(address3);
								sheet2.addMergedRegion(address4);
								sheet2.addMergedRegion(address8);
							}
							CellRangeAddress address0 = new CellRangeAddress(recStartNum, num-1, 0, 0);//起始行，终止行，起始列，终止列
							CellRangeAddress address1 = new CellRangeAddress(recStartNum, num-1, 1, 1);//起始行，终止行，起始列，终止列
							CellRangeAddress address2 = new CellRangeAddress(recStartNum, num-1, 2, 2);//起始行，终止行，起始列，终止列
							sheet2.addMergedRegion(address0);
							sheet2.addMergedRegion(address1);
							sheet2.addMergedRegion(address2);
						}
					}
				}
			}
		}else if ("head".equals(gradeRole)) {
			//设置每一列的宽度 行和列都是从0开始
			sheet.setColumnWidth(0,10000);
			sheet.setColumnWidth(1,10000);
			HSSFRow row0 = sheet.createRow(0);//第一行
			String[] titles0 = new String[]{
					"科室信息",
					"总均分"
			};
			HSSFCell cellTitle0 = null;
			for (int i = 0; i < titles0.length; i++) {
				cellTitle0 = row0.createCell(i);
				cellTitle0.setCellValue(titles0[i]);
				cellTitle0.setCellStyle(fontLeftStyle);
				cellTitle0.setCellType(CellType.STRING);
			}
			List<SysDept> depts = (List<SysDept>) waitSort;
			for (SysDept dept : depts) {
				Map<String, String> map = new HashMap<>();
				map.put("deptInfo", dept.getDeptName());
				map.put("avgScore", null == scoreMapExcel || null == scoreMapExcel.get(dept.getDeptFlow() + "_Total") ? "" : scoreMapExcel.get(dept.getDeptFlow() + "_Total") + "");
				exportList.add(map);
			}
			if (null!=exportList && exportList.size()>0){
				for (int i = 0; i < exportList.size(); i++) {
					String[] titles = new String[]{
							exportList.get(i).get("deptInfo"),
							exportList.get(i).get("avgScore")
					};
					HSSFRow row = sheet.createRow(i+1);
					HSSFCell cellTitle = null;
					for (int j = 0; j < titles.length; j++) {
						cellTitle = row.createCell(j);
						cellTitle.setCellValue(titles[j]);
						cellTitle.setCellStyle(styleLeft);
						cellTitle.setCellType(CellType.STRING);
					}
				}
			}
		}
		String fileName = "双向评价导出.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}

	private boolean filterResRec(Map<String, String> rec,Map<String, String> ex,String type){
		if (type.equals("teacher")){
			if (null!=rec && null!=ex && StringUtil.isNotBlank(ex.get("teacherFlow")) && StringUtil.isNotBlank(rec.get("key"))
					&& StringUtil.isNotBlank(ex.get("deptFlow")) && StringUtil.isNotBlank(rec.get("deptFlow"))){
				if (ex.get("teacherFlow").equals(rec.get("key"))
						&& ex.get("deptFlow").equals(rec.get("deptFlow"))){
					return true;
				}
			}
		}
		return false;
	}

	@RequestMapping(value = {"/exportGradeSearchDoc"})
	public void exportGradeSearchDoc(
			String gradeRole,
			String keyCode,
			String operStartDate,
			String operEndDate,
			String date,
			String recFlow,
			HttpServletResponse response, HttpServletRequest request
	) throws Exception {
		boolean isOneFile = false;
		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String, Object> scoreSumMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("sessionNumber", date);
		paramMap.put("operStartDate", operStartDate);
		paramMap.put("operEndDate", operEndDate);
		paramMap.put("recFlow", recFlow);
		if ("teacher".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId", recType);
			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			paramMap.put("teacherFlow", keyCode);
		} else if ("head".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId", recType);
			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			paramMap.put("deptFlow", keyCode);
		}
//		List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
		Map<String, List<ResAssessCfgTitleForm>> assesscfgMap = assessCfgBiz.getParsedGradeMap(cfgCode);
		//获取评分数据
		List<Map<String, String>> recList = resGradeBiz.getJsresRecContentByProcess(paramMap);
		//导出结果集合
		List<List<Map<String, String>>> exportInfoList = new ArrayList<>();
		List<String> fileNames = new ArrayList<>();

		String[] titles = {
				"itemName:评分细则",
				"itemScore:标准分",
				"avgScore:总分"
		};
		int i = 0;
		if (recList != null && !recList.isEmpty()) {
			if (recList.size() == 1) {
				isOneFile = true;
			}
			Map<String, Float> scoreMap = new HashMap<String, Float>();
			for (Map<String, String> map : recList) {
				i++;
				//单条结果导出集合
				List<Map<String, String>> oneInfoList = new ArrayList<>();

				String operUserFlow = map.get("operUserFlow") + map.get("recFlow");

				String content = map.get("content");
				Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
				if (gradeMap != null && !gradeMap.isEmpty()) {
					for (String gk : gradeMap.keySet()) {
						Object o = gradeMap.get(gk);
						Float score = 0f;
						if (o instanceof Map) {
							Map<String, String> dataMap = (Map<String, String>) o;
							if (dataMap != null) {
								try {
									String scoreS = dataMap.get("score");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
									logger.error("", e);
								}

								putMapVal(scoreMap, operUserFlow + gk, score);
							}
						} else {
							try {
								String scoreS = (String) gradeMap.get("totalScore");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
								logger.error("", e);
							}

							putMapVal(scoreMap, operUserFlow, score);
						}
					}

				}
				List<ResAssessCfgTitleForm> assessCfgList = assesscfgMap.get(map.get("cfgFlow"));
				if (null==assessCfgList || assessCfgList.isEmpty()){
					continue;
				}
				for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
					Map<String, String> itemMap = new HashMap<>();
					itemMap.put("itemName", resAssessCfgTitleForm.getName());
					itemMap.put("itemScore", "");
					itemMap.put("avgScore", "");
					oneInfoList.add(itemMap);
					if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
						for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
							itemMap = new HashMap<>();
							itemMap.put("itemName", resAssessCfgItemForm.getName());
							itemMap.put("itemScore", resAssessCfgItemForm.getScore());
							itemMap.put("avgScore", scoreMap.get(operUserFlow + resAssessCfgItemForm.getId()) + "");
							oneInfoList.add(itemMap);

						}
					}
				}
				String fileName = map.get("operUserName") + map.get("deptName") + "(" + map.get("schStartDate") + "~" + map.get("schEndDate") + ")";
				if (isOneFile) {
					if (StringUtil.isBlank(fileName))
						fileName = "双向评价明细导出.xls";
					else
						fileName += ".xls";
					fileName = URLEncoder.encode(fileName, "UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					response.setContentType("application/octet-stream;charset=UTF-8");
					ExcleUtile.exportSimpleExcleByObjs(titles, oneInfoList, response.getOutputStream());
				} else {
					if (StringUtil.isBlank(fileName))
						fileName = "双向评价明细导出-" + i;
					fileNames.add(fileName);
					exportInfoList.add(oneInfoList);
				}
			}
		}
		if (!isOneFile) {
			String fileName = "双向评价明细导出";
			response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName + ".zip", "UTF-8") + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			ExcleUtile.exportExcel(fileName, fileNames, titles, exportInfoList, response.getOutputStream(), request);
		}

	}

	@RequestMapping(value = {"/exportBaseList"})
	public void exportBaseList(ResDoctor doctor, HttpServletRequest request, String datas[], String statusFlag, String historyOrgFlow, HttpServletResponse response) throws Exception {
		String titleYear = "";
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			titleYear = doctor.getSessionNumber().replace(",", "_");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			org.setOrgCityId(s.getOrgCityId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs = orgBiz.searchAllSysOrg(org);
			if (orgs != null && !orgs.isEmpty()) {
				for (SysOrg sysOrg : orgs) {
					orgFlowList.add(sysOrg.getOrgFlow());
				}
			}
		}

		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		orgHistory.setHistoryOrgFlow(historyOrgFlow);
		doctor.setTrainingTypeId("AssiGeneral");
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlow1(orgHistory, changeStatusIdList, docTypeList, doctor, orgFlowList, sessionNumbers);
		for (JsResDoctorOrgHistoryExt ohe : historyExts) {
			if (ohe != null) {
				if (StringUtil.isNotBlank(ohe.getInDate())) {
					ohe.setInDate(DateUtil.transDate(ohe.getInDate()));
				}
				if (StringUtil.isNotBlank(ohe.getModifyTime())) {
					ohe.setModifyTime(DateUtil.transDateTime(ohe.getModifyTime()));
				}
			}
		}
		String[] titles = new String[]{
				"resDoctor.doctorName:姓名",
				"historyTrainingSpeName:培训专业",
				"resDoctor.sessionNumber:年级",
				"inDate:转入审核时间",
				"historyOrgName:原培训基地",
				"orgName:变更后基地",
				"modifyTime:审核时间"
		};
		String fileName = titleYear + "基地变更信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, historyExts, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}


	@RequestMapping(value = {"/exportBaseListAcc"})
	public void exportBaseListAcc(ResDoctor doctor, HttpServletRequest request, String datas[], String statusFlag, String historyOrgFlow, HttpServletResponse response) throws Exception {
		String titleYear = "";
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			titleYear = doctor.getSessionNumber().replace(",", "_");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs = new ArrayList<SysOrg>();
			SysOrg org = new SysOrg();
			SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			org.setOrgCityId(s.getOrgCityId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs = orgBiz.searchAllSysOrg(org);
			if (orgs != null && !orgs.isEmpty()) {
				for (SysOrg sysOrg : orgs) {
					orgFlowList.add(sysOrg.getOrgFlow());
				}
			}
		}

		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		orgHistory.setHistoryOrgFlow(historyOrgFlow);
		doctor.setTrainingTypeId("DoctorTrainingSpe");
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlow1(orgHistory, changeStatusIdList, docTypeList, doctor, orgFlowList, sessionNumbers);
		for (JsResDoctorOrgHistoryExt ohe : historyExts) {
			if (ohe != null) {
				if (StringUtil.isNotBlank(ohe.getInDate())) {
					ohe.setInDate(DateUtil.transDate(ohe.getInDate()));
				}
				if (StringUtil.isNotBlank(ohe.getModifyTime())) {
					ohe.setModifyTime(DateUtil.transDateTime(ohe.getModifyTime()));
				}
			}
		}
		String[] titles = new String[]{
				"resDoctor.doctorName:姓名",
				"historyTrainingSpeName:培训专业",
				"resDoctor.sessionNumber:年级",
				"inDate:转入审核时间",
				"historyOrgName:原培训基地",
				"orgName:变更后基地",
				"modifyTime:审核时间"
		};
		String fileName = titleYear + "基地变更信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, historyExts, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}



	@RequestMapping(value = {"/exportBaseListNew"})
	public void exportBaseListNew(ResDoctor doctor, HttpServletResponse response, Model model, String statusFlag,
								  String orgFlow, String datas[],String jointOrgFlag,String cityId) throws Exception{
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();

		List<SysOrg> orgs = new ArrayList<SysOrg>();
		SysOrg org = new SysOrg();
		SysOrg so = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(so.getOrgProvId());
		if(StringUtil.isNotBlank(cityId)){
			org.setOrgCityId(cityId);
		}
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchOrgListNew(org);
		model.addAttribute("orgs", orgs);
		if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
			for (SysOrg tempOrg : orgs) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				orgFlowList.add(tempOrg.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			orgFlowList.add(doctor.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}

		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		orgHistory.setHistoryOrgFlow(orgFlow);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("DoctorTrainingSpe");
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlow1(orgHistory, changeStatusIdList, docTypeList, doctor, orgFlowList, sessionNumbers);
		for (JsResDoctorOrgHistoryExt ohe : historyExts) {
			if (ohe != null) {
				if (StringUtil.isNotBlank(ohe.getInDate())) {
					ohe.setInDate(DateUtil.transDate(ohe.getInDate()));
				}
				if (StringUtil.isNotBlank(ohe.getModifyTime())) {
					ohe.setModifyTime(DateUtil.transDateTime(ohe.getModifyTime()));
				}
			}
		}
		String[] titles = new String[]{
				"resDoctor.doctorName:姓名",
				"historyTrainingSpeName:培训专业",
				"resDoctor.doctorTypeName:人员类型",
				"resDoctor.sessionNumber:届别",
				"inDate:转入审核时间",
				"historyOrgName:原培训基地",
				"orgName:变更后基地",
				"modifyTime:审核时间"
		};
		String fileName = "基地变更信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, historyExts, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value = {"/exportBaseListNewAcc"})
	public void exportBaseListNewAcc(ResDoctor doctor, HttpServletResponse response, Model model, String statusFlag,
									 String orgFlow, String datas[],String jointOrgFlag,String cityId) throws Exception{
		List<String> changeStatusIdList = new ArrayList<String>();
		if (StringUtil.isNotBlank(statusFlag)) {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyUnPass.getId());
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		} else {
            changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();

		List<SysOrg> orgs = new ArrayList<SysOrg>();
		SysOrg org = new SysOrg();
		SysOrg so = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(so.getOrgProvId());
		if(StringUtil.isNotBlank(cityId)){
			org.setOrgCityId(cityId);
		}
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchOrgListNew(org);
		model.addAttribute("orgs", orgs);
		if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
			for (SysOrg tempOrg : orgs) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				orgFlowList.add(tempOrg.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			orgFlowList.add(doctor.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}

		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
		orgHistory.setHistoryOrgFlow(orgFlow);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("AssiGeneral");
		List<JsResDoctorOrgHistoryExt> historyExts = jsDocOrgHistoryBiz.seearchInfoByFlow1(orgHistory, changeStatusIdList, docTypeList, doctor, orgFlowList, sessionNumbers);
		for (JsResDoctorOrgHistoryExt ohe : historyExts) {
			if (ohe != null) {
				if (StringUtil.isNotBlank(ohe.getInDate())) {
					ohe.setInDate(DateUtil.transDate(ohe.getInDate()));
				}
				if (StringUtil.isNotBlank(ohe.getModifyTime())) {
					ohe.setModifyTime(DateUtil.transDateTime(ohe.getModifyTime()));
				}
			}
		}
		String[] titles = new String[]{
				"resDoctor.doctorName:姓名",
				"historyTrainingSpeName:培训专业",
				"resDoctor.doctorTypeName:人员类型",
				"resDoctor.sessionNumber:届别",
				"inDate:转入审核时间",
				"historyOrgName:原培训基地",
				"orgName:变更后基地",
				"modifyTime:审核时间"
		};
		String fileName = "基地变更信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, historyExts, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value = {"/auditBaseChange"})
	public String auditBaseChange(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String statusFlag,
								  String historyOrgFlow, String datas[]) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		//查询时用于存放审核状态
		List<String> changeStatusIdList = new ArrayList<String>();
        changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
		//查询时用于存放机构流水号
		List<String> orgFlowList = new ArrayList<String>();
		//用于查询
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());

		//查询时用于存放条件的map
		Map<String, Object> paramMap = new HashMap<>();
		List<SysOrg> orgs = new ArrayList<>();
		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		orgs = orgBiz.searchOrg(sysorg4Search);
		if (orgs != null && orgs.size() > 0) {
			for (SysOrg tempOrg : orgs) {
				orgFlowList.add(tempOrg.getOrgFlow());
			}
		}
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int baseFlag = 0;
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if ((baseOne != null && !baseOne.isEmpty())) {
			baseFlag = 1;
		}
		model.addAttribute("baseFlag", baseFlag);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1(orgHistory, changeStatusIdList, doctor, orgFlowList, docTypeList, sessionNumbers);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		return "jsres/global/hospital/auditBaseChange";
	}

	@RequestMapping(value = {"/auditBaseChangeMain"})
	public String auditBaseChangeNew(Model model) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		return "jsres/global/hospital/auditBaseChangeMain";
	}

	@RequestMapping(value = {"/auditBaseChangeMainAcc"})
	public String auditBaseChangeMainAcc(Model model) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		List<SysOrg> orgs=orgBiz.searchOrgListNew(sysorg4Search);
		model.addAttribute("orgs", orgs);
		return "jsres/global/hospital/auditBaseChangeMainAcc";
	}

	@RequestMapping(value = {"/auditBaseChangeList"})
	public String auditBaseChangeList(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String statusFlag,
									  String historyOrgFlow, String datas[],String jointOrgFlag, String cityId) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		//查询时用于存放审核状态
		List<String> changeStatusIdList = new ArrayList<String>();
        changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
		//查询时用于存放机构流水号
		List<String> orgFlowList = new ArrayList<String>();
		//用于查询
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());

		//查询时用于存放条件的map
		Map<String, Object> paramMap = new HashMap<>();
		List<SysOrg> orgs = new ArrayList<>();
		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
		if(StringUtil.isNotBlank(cityId)){
			sysorg4Search.setOrgCityId(cityId);
		}
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		orgs = orgBiz.searchOrg(sysorg4Search);
		if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
			for (SysOrg tempOrg : orgs) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				orgFlowList.add(tempOrg.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			orgFlowList.add(doctor.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int baseFlag = 0;
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if ((baseOne != null && !baseOne.isEmpty())) {
			baseFlag = 1;
		}
		model.addAttribute("baseFlag", baseFlag);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("DoctorTrainingSpe");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1(orgHistory, changeStatusIdList, doctor, orgFlowList, docTypeList, sessionNumbers);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		return "jsres/global/hospital/auditBaseChangeList";
	}


	@RequestMapping(value = {"/auditBaseChangeListAcc"})
	public String auditBaseChangeListAcc(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String statusFlag,
										 String historyOrgFlow, String datas[],String jointOrgFlag, String cityId) {
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		//查询时用于存放审核状态
		List<String> changeStatusIdList = new ArrayList<String>();
        changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
		//查询时用于存放机构流水号
		List<String> orgFlowList = new ArrayList<String>();
		//用于查询
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
        orgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());

		//查询时用于存放条件的map
		Map<String, Object> paramMap = new HashMap<>();
		List<SysOrg> orgs = new ArrayList<>();
		SysOrg sysorg4Search = new SysOrg();
		sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
		if(StringUtil.isNotBlank(cityId)){
			sysorg4Search.setOrgCityId(cityId);
		}
        sysorg4Search.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//省里所有医院
		orgs = orgBiz.searchOrg(sysorg4Search);
		if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
			for (SysOrg tempOrg : orgs) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				orgFlowList.add(tempOrg.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			orgFlowList.add(doctor.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();
		int baseFlag = 0;
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if ((baseOne != null && !baseOne.isEmpty())) {
			baseFlag = 1;
		}
		model.addAttribute("baseFlag", baseFlag);
		List<String> docTypeList = new ArrayList<String>();
		model.addAttribute("datas", datas);
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
			String[] numbers = doctor.getSessionNumber().split(",");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				doctor.setSessionNumber("");
			}
		}
		doctor.setTrainingTypeId("AssiGeneral");
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList1(orgHistory, changeStatusIdList, doctor, orgFlowList, docTypeList, sessionNumbers);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		return "jsres/global/hospital/auditBaseChangeList";
	}


	@RequestMapping(value = {"/auditInfoGlobal"})
	public String auditInfoGlobal(Model model) {
		return "jsres/hospital/doctor/auditInfo";
	}

	@RequestMapping(value = {"/turnOutOrgGlobal"})
	@ResponseBody
	public String turnOutOrgGlobal(ResDoctorOrgHistory history, String time) {
		if (StringUtil.isNotBlank(history.getRecordFlow()) && StringUtil.isNotBlank(history.getChangeStatusId()) && StringUtil.isNotBlank(history.getDoctorFlow())) {
			if (StringUtil.isBlank(time)) {
				time = "";
			}
			int result = jsDocOrgHistoryBiz.auditTurnInOrgGlobal(history);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value = "/activityDetails")
	public String activityDetails(Model model, TeachingActivityInfo activityInfo) throws DocumentException {
		if (StringUtil.isNotBlank(activityInfo.getStartTime())) {
			String startTime = activityInfo.getStartTime() + " 00:00";
			activityInfo.setStartTime(startTime);
		}
		if (StringUtil.isNotBlank(activityInfo.getEndTime())) {
			String endTime = activityInfo.getEndTime() + " 23:59";
			activityInfo.setEndTime(endTime);
		}
		List<TeachingActivityInfo> activityInfoList = processBiz.searchActivityList(activityInfo);
		model.addAttribute("activityInfoList", activityInfoList);
		return "jsres/hospital/activityDetails";
	}

	@RequestMapping(value = {"/baseMain"})
	public String baseMain(Model model) {
		return "jsres/global/hospital/changeBaseMain";
	}

	@RequestMapping(value = {"/baseMainAcc"})
	public String baseMainAcc() {
		return "jsres/global/hospital/changeBaseMainAcc";
	}

	@RequestMapping(value = {"/rotationStatistics"})
	public String rotationStatistics(Model model, String roleFlag) {
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/global/hospital/rotationStatistics";
	}

	@RequestMapping("/initRotationNew")
	@ResponseBody
	public List initRotationNew(String role, String monthDate, String orderBy, String inSchool, String resident){
		Map<String,Object> paramMap = new HashMap<>();
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		paramMap.put("monthDate",monthDate);
		String doctorTypeId = "All";
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(inSchool) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resident)) {//在校专硕
			doctorTypeId = "Graduate";
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resident) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(inSchool)) {//住院医师
			doctorTypeId = "Doctor";
		}
		paramMap.put("doctorTypeId",doctorTypeId);
		paramMap.put("orderBy",orderBy);
		List<JsresSchDataStatistics> listm = schdualTaskMapper.selectLunzhuanData(paramMap);
		return listm;
	}
	/**
	 * 初始化学员轮转情况统计chent new
	 *
	 * @return
	 */
	@RequestMapping("/initRotation")
	@ResponseBody
	public List initRotation_New(String role, String monthDate, String isContain, String orderBy, String inSchool, String resident){
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> listm=new ArrayList<>();
		try {
			if(!"".equals(role)){
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("monthDate",monthDate);
				if(null==isContain){
                    isContain = com.pinde.core.common.GlobalConstant.FLAG_N;
				}else{
                    isContain = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
				paramMap.put("isContain",isContain);

				if(StringUtil.isNotBlank(inSchool) && StringUtil.isNotBlank(resident)){
                    paramMap.put("notGraduate", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    paramMap.put("graduate", com.pinde.core.common.GlobalConstant.FLAG_Y);
				}else{
					if(StringUtil.isNotBlank(inSchool)){
                        paramMap.put("notGraduate", com.pinde.core.common.GlobalConstant.FLAG_N);
                        paramMap.put("graduate", com.pinde.core.common.GlobalConstant.FLAG_Y);
					}else{
                        paramMap.put("notGraduate", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        paramMap.put("graduate", com.pinde.core.common.GlobalConstant.FLAG_N);
					}
				}
				SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
				String currentOrgName = currOrg.getOrgName();
				//String currentOrgId = currOrg.getOrgFlow();
                if (role.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
					paramMap.put("province",currOrg.getOrgProvId());
                    paramMap.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
				}
                if (role.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
					paramMap.put("city",currOrg.getOrgCityId());
                    paramMap.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
				}
                if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
					String currentOrgId="";
					Boolean gaoxiaoFlg = false;
                    List<SysDict> sendSchools = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
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
                        paramMap.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY);
					}else{
						throw new RuntimeException("请联系管理员“派送学校”是否有“"+currentOrgName+"”高校");
					}
				}
				listm =schdualTaskMapper.selectPCUDoctorLunzhuanFind(paramMap);
				listm= listSort(orderBy,listm);
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
	 * 初始化学员轮转情况统计chent old
	 *
	 * @return
	 */
	/*@RequestMapping("/initRotation")
	@ResponseBody*/
	public List initRotation(String role, String monthDate, String isContain, String orderBy, String inSchool, String resident) {
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = new ArrayList<>();
		List<String> paramList = new ArrayList<>();
		if (StringUtil.isNotBlank(inSchool)) {
			paramList.add("Graduate");
		}
		if (StringUtil.isNotBlank(resident)) {
			paramList.add("Company");
			paramList.add("CompanyEntrust");
			paramList.add("Social");
		}
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位
		try {
			SysOrg searchOrg = new SysOrg();
			SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
			searchOrg.setOrgProvId(currOrg.getOrgProvId());
            if (role.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
				searchOrg.setOrgCityId(currOrg.getOrgCityId());
			}
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            searchOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
            searchOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
			/*Map<String, Object> map = new HashMap<>();*/
			//高校角色
            if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
				List li = resMonthlyReportGlobalControllerClass.rotation(role, monthDate, isContain, orderBy, inSchool, resident, orgs);
				return li;
			}
			//包含协同
			if ("isContain".equals(isContain)) {
				Map<String, Object> map = new HashMap<>();
				for (int i = 0; i < exitOrgs.size(); i++) {
					List<String> allOrgFlow = new ArrayList<>();
					List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
					for (int j = 0; j < resJointOrgList.size(); j++) {
						allOrgFlow.add(resJointOrgList.get(j).getOrgFlow());
					}
					allOrgFlow.add(exitOrgs.get(i).getOrgFlow());
					exitOrgs.get(i).setParentOrgFlow("");
					map.put("allOrgFlow", allOrgFlow);
					map.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
					map.put("doctorTypeIdList", paramList);
					List<SysOrg> list = resStatisticBiz.getRotationData(map);
					if (list.size() > 0) {
						Integer trainDoctorTotal = 0;
						Integer fillNum = 0;
						Integer auditNum = 0;
						for (SysOrg s : list) {
							trainDoctorTotal = trainDoctorTotal + s.getTrainDoctorTotal();
							fillNum = fillNum + s.getFillNum();
							auditNum = auditNum + s.getAuditNum();
						}
						exitOrgs.get(i).setTrainDoctorTotal(trainDoctorTotal);
						exitOrgs.get(i).setFillNum(fillNum);
						exitOrgs.get(i).setAuditNum(auditNum);
						String auditRate = "0%";
						String avgFillNum = "0";
						if (null != fillNum && !"".equals(fillNum)) {
							if(fillNum==0){
								auditRate ="0%";
							}else{
								auditRate = numberFormat.format((float) auditNum / (float) fillNum * 100) + "%";
							}
						}
						if (null != trainDoctorTotal && !"".equals(trainDoctorTotal)) {
							if(trainDoctorTotal==0){
								avgFillNum = "0";
							}else{
								avgFillNum = numberFormat.format((float) fillNum / (float) trainDoctorTotal);
							}

						}
						exitOrgs.get(i).setAuditRate(auditRate);
						exitOrgs.get(i).setAvgfillNum(avgFillNum);
					}
					List<SysOrg> resJointOrgListT = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
					if (resJointOrgListT != null && !resJointOrgListT.isEmpty()) {
						Map<String, Object> secondMap = new HashMap<>();
						secondMap.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
						secondMap.put("doctorTypeIdList", paramList);
						for (int j = 0; j < resJointOrgListT.size(); j++) {
							resJointOrgListT.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
							resJointOrgListT.get(j).setOrgCode(exitOrgs.get(i).getOrgCode() + "-" + (j + 1));
							secondMap.put("orgFlow", resJointOrgListT.get(j).getOrgFlow());
							List<SysOrg> list2 = resStatisticBiz.getRotationData(secondMap);
							if (list2.size() > 0) {
								SysOrg sysOrgT2 = list2.get(0);
								resJointOrgListT.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
								resJointOrgListT.get(j).setFillNum(sysOrgT2.getFillNum());
								resJointOrgListT.get(j).setAuditNum(sysOrgT2.getAuditNum());
								String auditRate2 = "0%";
								String avgFillNum = "0";
								if (null != sysOrgT2.getFillNum() && !"".equals(sysOrgT2.getFillNum())) {
									if( sysOrgT2.getFillNum()==0){
										auditRate2="0%";
									}else{
										auditRate2 = numberFormat.format((float) sysOrgT2.getAuditNum() / (float) sysOrgT2.getFillNum() * 100) + "%";
									}
								}
								if (null != sysOrgT2.getTrainDoctorTotal() && !"".equals(sysOrgT2.getTrainDoctorTotal())) {
									if(sysOrgT2.getTrainDoctorTotal()==0){
										avgFillNum ="0";
									}else{
										avgFillNum = numberFormat.format((float) sysOrgT2.getFillNum() / (float) sysOrgT2.getTrainDoctorTotal());
									}
								}
								resJointOrgListT.get(j).setAuditRate(auditRate2);
								resJointOrgListT.get(j).setAvgfillNum(avgFillNum);
							}
							orgs.add(resJointOrgListT.get(j));
						}
					}
					orgs.add(exitOrgs.get(i));
				}
				//不包含协同
			} else {
				Map<String, Object> map = new HashMap<>();
				for (int i = 0; i < exitOrgs.size(); i++) {
					exitOrgs.get(i).setParentOrgFlow("");
					String countryOrgFlow = exitOrgs.get(i).getOrgFlow();
					map.put("orgFlow", countryOrgFlow);
					map.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
					map.put("doctorTypeIdList", paramList);
					List<SysOrg> list = resStatisticBiz.getRotationData(map);
					if (list.size() > 0) {
						SysOrg sysOrgT = list.get(0);
						exitOrgs.get(i).setTrainDoctorTotal(sysOrgT.getTrainDoctorTotal());
						exitOrgs.get(i).setFillNum(sysOrgT.getFillNum());
						exitOrgs.get(i).setAuditNum(sysOrgT.getAuditNum());
						String auditRate = "0%";
						String avgFillNum = "0";
						if (null != sysOrgT.getFillNum() && !"".equals(sysOrgT.getFillNum())) {
							if(sysOrgT.getFillNum()==0){
								auditRate="0%";
							}else{
								auditRate = numberFormat.format((float) sysOrgT.getAuditNum() / (float) sysOrgT.getFillNum() * 100) + "%";
							}
						}
						if (null != sysOrgT.getTrainDoctorTotal() && !"".equals(sysOrgT.getTrainDoctorTotal())) {
							if(sysOrgT.getTrainDoctorTotal()==0){
								avgFillNum="0";
							}else{
								avgFillNum = numberFormat.format((float) sysOrgT.getFillNum() / (float) sysOrgT.getTrainDoctorTotal());
							}
						}
						exitOrgs.get(i).setAuditRate(auditRate);
						exitOrgs.get(i).setAvgfillNum(avgFillNum);
					}
					List<SysOrg> resJointOrgList = orgBiz.searchJointOrgsByOrg(exitOrgs.get(i).getOrgFlow());
					Map<String, Object> secondMap = new HashMap<>();
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (int j = 0; j < resJointOrgList.size(); j++) {
							resJointOrgList.get(j).setParentOrgFlow(exitOrgs.get(i).getOrgFlow());
							resJointOrgList.get(j).setOrgCode(exitOrgs.get(i).getOrgCode() + "-" + (j + 1));
							secondMap.put("orgFlow", resJointOrgList.get(j).getOrgFlow());
							secondMap.put("doctorTypeIdList", paramList);
							secondMap.put("monthDate", monthDate.split("-")[0]+monthDate.split("-")[1]);
							List<SysOrg> list2 = resStatisticBiz.getRotationData(secondMap);
							if (list2.size() > 0) {
								SysOrg sysOrgT2 = list2.get(0);
								resJointOrgList.get(j).setTrainDoctorTotal(sysOrgT2.getTrainDoctorTotal());
								resJointOrgList.get(j).setFillNum(sysOrgT2.getFillNum());
								resJointOrgList.get(j).setAuditNum(sysOrgT2.getAuditNum());
								String auditRate2 = "0%";
								String avgFillNum = "0";
								if (null != sysOrgT2.getFillNum() && !"".equals(sysOrgT2.getFillNum())) {
									if( sysOrgT2.getFillNum()==0){
										auditRate2="0%";
									}else{
										auditRate2 = numberFormat.format((float) sysOrgT2.getAuditNum() / (float) sysOrgT2.getFillNum() * 100) + "%";
									}
								}
								if (null != sysOrgT2.getTrainDoctorTotal() && !"".equals(sysOrgT2.getTrainDoctorTotal())) {
									if(sysOrgT2.getTrainDoctorTotal()==0){
										avgFillNum="0";
									}else{
										avgFillNum = numberFormat.format((float) sysOrgT2.getFillNum() / (float) sysOrgT2.getTrainDoctorTotal());
									}
								}
								resJointOrgList.get(j).setAuditRate(auditRate2);
								resJointOrgList.get(j).setAvgfillNum(avgFillNum);
							}
							orgs.add(resJointOrgList.get(j));
						}
					}
					orgs.add(exitOrgs.get(i));
				}
			}
			orgs = listSort(orderBy, orgs);
			return orgs;
		} catch (RuntimeException e) {
			List<Map<String, String>> list = new ArrayList<>();
			Map<String, String> map = new HashMap<>();
			map.put("error", e.getMessage());
			list.add(map);
			return list;
		}
	}

	@RequestMapping("/getRchartsInfo")
	@ResponseBody
	public List[] getRchartsInfo_New(String role, String monthDate, String isContain, String chartOrderBy, String inSchool, String resident){
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(4);//设置精确到小数点后2位
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = initRotation_New(role, monthDate, isContain, chartOrderBy, inSchool, resident);
		List<String> orgList = new ArrayList<>();
		List<Integer> fillNum = new ArrayList<>();
		List<Integer> auditNum = new ArrayList<>();
		List<String> fillAvgNum = new ArrayList<>();
		List<String> auditRate = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
		searchOrg.setOrgProvId(currOrg.getOrgProvId());
        if (role.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(currOrg.getOrgCityId());
		}
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        searchOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        searchOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
		for (SysOrg org : orgs) {
			for (SysOrg exitOrg : exitOrgs) {
				if (org.getOrgFlow().equals(exitOrg.getOrgFlow())) {
                    if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
						orgList.add(org.getOrgCode());//org.getOrgName()
					} else {
						orgList.add(org.getOrgCode());
					}
					if (null != org.getFillNum() && !"".equals(org.getFillNum()) && org.getFillNum()!=0) {
						fillNum.add(org.getFillNum());
						auditRate.add(numberFormat.format((float) org.getAuditNum() / (float) org.getFillNum() / org.getTrainDoctorTotal()));
					} else {
						auditRate.add(numberFormat.format(0));
						fillNum.add(0);
					}
					if (null != org.getTrainDoctorTotal() && !"".equals(org.getTrainDoctorTotal())) {
						fillAvgNum.add(numberFormat.format((float) org.getFillNum() / (float) org.getTrainDoctorTotal()));
						auditNum.add(org.getAuditNum());
					} else {
						fillAvgNum.add("0");
						auditNum.add(0);
					}
				}
			}

		}
		List[] arrayList = {orgList, fillNum, auditNum, fillAvgNum, auditRate};
		return arrayList;
	}
	/**
	 * 初始化学员轮转情况统计chent old
	 *
	 * @return
	 */
	/*@RequestMapping("/getRchartsInfo")
	@ResponseBody*/
	public List[] getRchartsInfo(String role, String monthDate, String isContain, String chartOrderBy, String inSchool, String resident) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(4);//设置精确到小数点后2位
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = initRotation(role, monthDate, isContain, chartOrderBy, inSchool, resident);
		List<String> orgList = new ArrayList<>();
		List<Integer> fillNum = new ArrayList<>();
		List<Integer> auditNum = new ArrayList<>();
		List<String> fillAvgNum = new ArrayList<>();
		List<String> auditRate = new ArrayList<>();
		SysOrg searchOrg = new SysOrg();
		SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
		searchOrg.setOrgProvId(currOrg.getOrgProvId());
        if (role.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(currOrg.getOrgCityId());
		}
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        searchOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        searchOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);//获取所有省级或者市级国家基地
		for (SysOrg org : orgs) {
			for (SysOrg exitOrg : exitOrgs) {
				if (org.getOrgFlow().equals(exitOrg.getOrgFlow())) {
                    if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
						orgList.add(org.getOrgCode());//org.getOrgName()
					} else {
						orgList.add(org.getOrgCode());
					}
					if (null != org.getFillNum() && !"".equals(org.getFillNum()) && org.getFillNum()!=0) {
						fillNum.add(org.getFillNum());
						auditRate.add(numberFormat.format((float) org.getAuditNum() / (float) org.getFillNum() / org.getTrainDoctorTotal()));
					} else {
						auditRate.add(numberFormat.format(0));
						fillNum.add(0);
					}
					if (null != org.getTrainDoctorTotal() && !"".equals(org.getTrainDoctorTotal())) {
						fillAvgNum.add(numberFormat.format((float) org.getFillNum() / (float) org.getTrainDoctorTotal()));
						auditNum.add(org.getAuditNum());
					} else {
						fillAvgNum.add("0");
						auditNum.add(0);
					}
				}
			}

		}
		List[] arrayList = {orgList, fillNum, auditNum, fillAvgNum, auditRate};
		return arrayList;
	}

	@RequestMapping(value = {"/personStatistic"})
	public String personStatistic(Model model, String roleFlag) {
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/global/hospital/personStatistic";
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
	 * 初始化学员轮转情况统计chent
	 *
	 * @return
	 */
	@RequestMapping("/initpersonStatic")
	@ResponseBody
	public List PersonUseInfo_New(String role, String monthDate, String isContain){
		String lastMonthdateStr = DateUtil.addMonth(monthDate,-1)/*year + "-" + monthZero((month - 1))*/;
		SysUser user = GlobalContext.getCurrentUser();
		role=getRoleFlag();
		List<SysOrg> listm=new ArrayList<>();
		List<SysOrg> lastListm=new ArrayList<>();
		List<PersonStaticExample> staticData = new ArrayList<>();
		try {
			if(!"".equals(role)){
				Map<String,Object> paramMap = new HashMap<>();
				Map<String,Object> paramMap2 = new HashMap<>();
				paramMap.put("monthDate",monthDate);
				paramMap2.put("monthDate",lastMonthdateStr);
				if(null==isContain){
                    isContain = com.pinde.core.common.GlobalConstant.FLAG_N;
				}else{
                    isContain = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
				paramMap.put("isContain",isContain);
				paramMap2.put("isContain",isContain);
				SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
				String currentOrgName = currOrg.getOrgName();
				//String currentOrgId = currOrg.getOrgFlow();
                if (role.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
					paramMap.put("province",currOrg.getOrgProvId());
                    paramMap.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
					paramMap2.put("province",currOrg.getOrgProvId());
                    paramMap2.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
				}
                if (role.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
					paramMap.put("city",currOrg.getOrgCityId());
                    paramMap.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
					paramMap2.put("city",currOrg.getOrgCityId());
                    paramMap2.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
				}
                if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
					String currentOrgId="";
					Boolean gaoxiaoFlg = false;
                    List<SysDict> sendSchools = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
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
                        paramMap.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY);
						paramMap2.put("university",currentOrgId);
                        paramMap2.put("roleFlag", com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY);
					}else{
						throw new RuntimeException("请联系管理员“派送学校”是否有“"+currentOrgName+"”高校");
					}
				}
				listm =schdualTaskMapper.selectPCUAppInfo(paramMap);
				lastListm = schdualTaskMapper.selectPCUAppInfo(paramMap2);
			}
			for (SysOrg org: listm) {
				PersonStaticExample example = new PersonStaticExample();
				example.setLastInSchoolNum(0);
				example.setInSchoolNum(0);
				example.setLastResidentNum(0);
				example.setResidentNum(0);
				example.setResidentRecruitNum(0);//
				example.setInSchoolRecruitNum(0);//
				example.setResidentExaminedNum(0);
				example.setInSchoolExaminedNum(0);
				example.setResidentGraduatNum(0);
				example.setInSchoolGraduatNum(0);
				example.setResidentReturnNum(0);
				example.setInSchoolReturnNum(0);
				example.setResidentOutNum(0);
				example.setInSchoolOutNum(0);
				example.setResidentInNum(0);
				example.setInSchoolInNum(0);
				example.setLastBothNum(0);
				example.setBothNum(0);
				example.setOrgCode(org.getOrgCode());
				example.setOrgName(org.getOrgName());
				example.setNo(org.getNo());
				example.setParentOrgFlow(org.getParentOrgFlow());
				example.setOrgFlow(org.getOrgFlow());
				example.setInSchoolNum(org.getMasterSum());
				example.setResidentNum(org.getDoctorSum());
				example.setBothNum(org.getTrainDoctorTotal());
				staticData.add(example);
			}
			if(staticData!= null && staticData.size()>0) {
				for (SysOrg org2:lastListm) {
					for (PersonStaticExample example: staticData) {
						if (org2.getOrgFlow().equals(example.getOrgFlow())) {
							example.setLastInSchoolNum(org2.getMasterSum());
							example.setLastResidentNum(org2.getDoctorSum());
							example.setLastBothNum(org2.getTrainDoctorTotal());
						}
					}
				}
			}else {
				for (SysOrg org2:lastListm) {
					PersonStaticExample example = new PersonStaticExample();
					example.setLastInSchoolNum(0);
					example.setInSchoolNum(0);
					example.setLastResidentNum(0);
					example.setResidentNum(0);
					example.setResidentRecruitNum(0);//
					example.setInSchoolRecruitNum(0);//
					example.setResidentExaminedNum(0);
					example.setInSchoolExaminedNum(0);
					example.setResidentGraduatNum(0);
					example.setInSchoolGraduatNum(0);
					example.setResidentReturnNum(0);
					example.setInSchoolReturnNum(0);
					example.setResidentOutNum(0);
					example.setInSchoolOutNum(0);
					example.setResidentInNum(0);
					example.setInSchoolInNum(0);
					example.setLastBothNum(0);
					example.setBothNum(0);
					example.setOrgCode(org2.getOrgCode());
					example.setOrgName(org2.getOrgName());
					example.setNo(org2.getNo());
					example.setParentOrgFlow(org2.getParentOrgFlow());
					example.setOrgFlow(org2.getOrgFlow());
					example.setLastInSchoolNum(org2.getMasterSum());
					example.setLastResidentNum(org2.getDoctorSum());
					example.setLastBothNum(org2.getTrainDoctorTotal());
					staticData.add(example);
				}
			}

			return staticData;
		}catch (RuntimeException e){
			List<Map<String,String>> list=new ArrayList<>();
			Map<String,String> map=new HashMap<>();
			if(e.getMessage()==null){
				map.put("error","数据异常,请联系管理员解决");
			}else{
				map.put("error","数据异常,请联系管理员解决");
			}
			list.add(map);
			return list;
		}

	}
	/*@RequestMapping("/initpersonStatic")
	@ResponseBody
	public List initpersonStatic(String role, String monthDate, String isContain) {
	*//*	Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String lastMonthdateStr = year + "-" + monthZero((month - 1));
		String monthdateStr = year + "-" + monthZero(month);
		String lastMonthdate = year + monthZero(month-1);
		String monthdate = year + monthZero(month);*//*
		String lastMonthdateStr = DateUtil.addMonth(monthDate,-1)*//*year + "-" + monthZero((month - 1))*//*;
		String monthdateStr = monthDate*//*year + "-" + monthZero(month)*//*;
		String lastMonthdate = lastMonthdateStr.split("-")[0]+lastMonthdateStr.split("-")[1]*//*year + monthZero(month-1)*//*;
		String monthdate = monthDate.split("-")[0]+monthDate.split("-")[1]*//*year + monthZero(month)*//*;

		SysUser user = GlobalContext.getCurrentUser();
		List<PersonStaticExample> staticData = new ArrayList<>();
		List<SysOrg> orgsList = new ArrayList<>();
		try {
			if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
				List li = resMonthlyReportGlobalControllerClass.gaoxiaoPersonSattic(lastMonthdateStr,monthdateStr,lastMonthdate,monthdate, isContain);
				return li;
			}
			SysOrg searchOrg = new SysOrg();
			SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
			searchOrg.setOrgProvId(currOrg.getOrgProvId());
			if (role.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
				searchOrg.setOrgCityId(currOrg.getOrgCityId());
			}
			searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			searchOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
			searchOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
			*//*List<PersonStaticExample> countryOrgSession = orgBiz.searchOrgSession(searchOrg);//获取所有省级或者市级国家基地
			for (PersonStaticExample orgSession : countryOrgSession) {
				orgsList.add(orgSession);
			}*//*
			//对象转化
			for (int i = 0; i < orgsList.size(); i++) {
				PersonStaticExample staticExample = new PersonStaticExample();
				BeanUtils.copyProperties(orgsList.get(i), staticExample);
				staticData.add(staticExample);
			}
			//包含协同
			if ("isContain".equals(isContain)) {
				for (PersonStaticExample personStaticExample : staticData) {
					Integer lastInSchoolNum = 0;
					Integer inSchoolNum = 0;
					Integer lastResidentNum = 0;
					Integer residentNum = 0;
					Integer residentRecruitNum = 0;
					Integer inSchoolRecruitNum = 0;
					Integer residentExaminedNum = 0;
					Integer inSchoolExaminedNum = 0;
					Integer residentGraduatNum = 0;
					Integer inSchoolGraduatNum = 0;
					Integer residentReturnNum = 0;
					Integer inSchoolReturnNum = 0;
					Integer residentOutNum = 0;
					Integer inSchoolOutNum = 0;
					Integer residentInNum = 0;
					Integer inSchoolInNum = 0;
					Integer lastBothNum=0;
					Integer bothNum=0;
					String orgFlow = personStaticExample.getOrgFlow();
					Map<String, Object> parmMap = new HashMap<>();
					parmMap.put("orgFlow", orgFlow);
					parmMap.put("lastMonthdateStr", lastMonthdateStr);//"2019-09"
					parmMap.put("monthdateStr", monthdateStr);//"2019-10"
					parmMap.put("lastMonthdate", lastMonthdate);//"201909"
					parmMap.put("monthdate", monthdate);//"201910"
					List<PersonStaticExample> staticExample = resStatisticBiz.getPersonStaticDataNewyuh(parmMap);
					//获取本月在培人数
					Integer residentRecruitCount =resStatisticBiz.residentRecruitCount(parmMap);
					//获取本月在校专硕在培人数
					Integer inSchoolRecruitCount = resStatisticBiz.inSchoolRecruitCount(parmMap);
					for (PersonStaticExample orgStatic : staticExample) {
						lastInSchoolNum = orgStatic.getLastInSchoolNum()==null?0:orgStatic.getLastInSchoolNum();
						inSchoolNum = orgStatic.getInSchoolNum()==null?0:orgStatic.getInSchoolNum();
						lastResidentNum = orgStatic.getLastResidentNum()==null?0:orgStatic.getLastResidentNum();
						residentNum = orgStatic.getResidentNum()==null?0:orgStatic.getResidentNum();
						residentRecruitNum = residentRecruitCount==null?0:residentRecruitCount*//*orgStatic.getResidentRecruitNum()*//*;
						inSchoolRecruitNum = inSchoolRecruitCount==null?0:inSchoolRecruitCount*//*orgStatic.getInSchoolRecruitNum()*//*;
						residentExaminedNum = orgStatic.getResidentExaminedNum()==null?0:orgStatic.getResidentExaminedNum();
						inSchoolExaminedNum = orgStatic.getInSchoolExaminedNum()==null?0:orgStatic.getInSchoolExaminedNum();
						residentGraduatNum = orgStatic.getResidentGraduatNum()==null?0:orgStatic.getResidentGraduatNum();
						inSchoolGraduatNum = orgStatic.getInSchoolGraduatNum()==null?0:orgStatic.getInSchoolGraduatNum();
						residentReturnNum = orgStatic.getResidentReturnNum()==null?0:orgStatic.getResidentReturnNum();
						inSchoolReturnNum = orgStatic.getInSchoolReturnNum()==null?0:orgStatic.getInSchoolReturnNum();
						residentOutNum = orgStatic.getResidentOutNum()==null?0:orgStatic.getResidentOutNum();
						inSchoolOutNum = orgStatic.getInSchoolOutNum()==null?0:orgStatic.getInSchoolOutNum();
						residentInNum = orgStatic.getResidentInNum()==null?0:orgStatic.getResidentInNum();
						inSchoolInNum = orgStatic.getInSchoolInNum()==null?0:orgStatic.getInSchoolInNum();
						lastBothNum = orgStatic.getLastBothNum()==null?0:orgStatic.getLastBothNum();
						bothNum = orgStatic.getBothNum()==null?0:orgStatic.getBothNum();
					}
					for (PersonStaticExample example22 : staticData) {
						if (orgFlow.equals(example22.getParentOrgFlow())) {
							Map<String, Object> parmMap22 = new HashMap<>();
							parmMap22.put("orgFlow", example22.getOrgFlow());
							parmMap22.put("lastMonthdateStr", lastMonthdateStr);//"2019-09"
							parmMap22.put("monthdateStr", monthdateStr);//"2019-10"
							parmMap22.put("lastMonthdate", lastMonthdate);//"201909"
							parmMap22.put("monthdate", monthdate);//"201910"
							List<PersonStaticExample> staticExample22 = resStatisticBiz.getPersonStaticDataNewyuh(parmMap22);
							Integer residentRecruitCount22 =resStatisticBiz.residentRecruitCount(parmMap22);
							Integer inSchoolRecruitCount22 = resStatisticBiz.inSchoolRecruitCount(parmMap22);
							if(staticExample22.size()>0){
								PersonStaticExample example=   staticExample22.get(0);

								lastInSchoolNum = lastInSchoolNum + (example.getLastInSchoolNum()==null?0:example.getLastInSchoolNum());
								inSchoolNum = inSchoolNum + (example.getInSchoolNum()==null?0:example.getInSchoolNum());
								lastResidentNum = lastResidentNum + (example.getLastResidentNum()==null?0:example.getLastResidentNum());
								residentNum = residentNum + (example.getResidentNum()==null?0:example.getResidentNum());
								residentRecruitNum = residentRecruitNum + (residentRecruitCount22==null?0:residentRecruitCount22)*//*example.getResidentRecruitNum()*//*;
								inSchoolRecruitNum = inSchoolRecruitNum + (inSchoolRecruitCount22==null?0:inSchoolRecruitCount22)*//*example.getInSchoolRecruitNum()*//*;
								residentExaminedNum = residentExaminedNum + (example.getResidentExaminedNum()==null?0:example.getResidentExaminedNum());
								inSchoolExaminedNum = inSchoolExaminedNum + (example.getInSchoolExaminedNum()==null?0:example.getInSchoolExaminedNum());
								residentGraduatNum = residentGraduatNum + (example.getResidentGraduatNum()==null?0:example.getResidentGraduatNum());
								inSchoolGraduatNum = inSchoolGraduatNum + (example.getInSchoolGraduatNum()==null?0:example.getInSchoolGraduatNum());
								residentReturnNum = residentReturnNum + (example.getResidentReturnNum()==null?0:example.getResidentReturnNum());
								inSchoolReturnNum = inSchoolReturnNum + (example.getInSchoolReturnNum()==null?0:example.getInSchoolReturnNum());
								residentOutNum = residentOutNum + (example.getResidentOutNum()==null?0:example.getResidentOutNum());
								inSchoolOutNum = inSchoolOutNum + (example.getInSchoolOutNum()==null?0:example.getInSchoolOutNum());
								residentInNum = residentInNum + (example.getResidentInNum()==null?0:example.getResidentInNum());
								inSchoolInNum = inSchoolInNum + (example.getInSchoolInNum()==null?0:example.getInSchoolInNum());
								lastBothNum = lastBothNum +(example.getLastBothNum()==null?0:example.getLastBothNum());
								bothNum =bothNum+(example.getBothNum()==null?0:example.getBothNum());
							}
						}
					}
					personStaticExample.setLastInSchoolNum(lastInSchoolNum);
					personStaticExample.setInSchoolNum(inSchoolNum);
					personStaticExample.setLastResidentNum(lastResidentNum);
					personStaticExample.setResidentNum(residentNum);
					personStaticExample.setResidentRecruitNum(residentRecruitNum);
					personStaticExample.setInSchoolRecruitNum(inSchoolRecruitNum);
					personStaticExample.setResidentExaminedNum(residentExaminedNum);
					personStaticExample.setInSchoolExaminedNum(inSchoolExaminedNum);
					personStaticExample.setResidentGraduatNum(residentGraduatNum);
					personStaticExample.setInSchoolGraduatNum(inSchoolGraduatNum);
					personStaticExample.setResidentReturnNum(residentReturnNum);
					personStaticExample.setInSchoolReturnNum(inSchoolReturnNum);
					personStaticExample.setResidentOutNum(residentOutNum);
					personStaticExample.setInSchoolOutNum(inSchoolOutNum);
					personStaticExample.setResidentInNum(residentInNum);
					personStaticExample.setInSchoolInNum(inSchoolInNum);
					personStaticExample.setLastBothNum(lastBothNum);
					personStaticExample.setBothNum(bothNum);
				}
				//不包含协同
			} else {
				for (PersonStaticExample personStaticExample : staticData) {
					Integer lastInSchoolNum = 0;
					Integer inSchoolNum = 0;
					Integer lastResidentNum = 0;
					Integer residentNum = 0;
					Integer residentRecruitNum = 0;
					Integer inSchoolRecruitNum = 0;
					Integer residentExaminedNum = 0;
					Integer inSchoolExaminedNum = 0;
					Integer residentGraduatNum = 0;
					Integer inSchoolGraduatNum = 0;
					Integer residentReturnNum = 0;
					Integer inSchoolReturnNum = 0;
					Integer residentOutNum = 0;
					Integer inSchoolOutNum = 0;
					Integer residentInNum = 0;
					Integer inSchoolInNum = 0;
					Integer lastBothNum=0;
					Integer bothNum=0;
					String orgFlow = personStaticExample.getOrgFlow();
					Map<String, Object> parmMap = new HashMap<>();
					parmMap.put("orgFlow", orgFlow);
					parmMap.put("lastMonthdateStr", lastMonthdateStr);//"2019-09"
					parmMap.put("monthdateStr", monthdateStr);//"2019-10"
					parmMap.put("lastMonthdate", lastMonthdate);//"201909"
					parmMap.put("monthdate", monthdate);//"201910"
					List<PersonStaticExample> staticExample = resStatisticBiz.getPersonStaticDataNewyuh(parmMap);
					Integer residentRecruitCount =resStatisticBiz.residentRecruitCount(parmMap);
					Integer inSchoolRecruitCount = resStatisticBiz.inSchoolRecruitCount(parmMap);
					for (PersonStaticExample orgStatic : staticExample) {
						lastInSchoolNum = orgStatic.getLastInSchoolNum();
						inSchoolNum = orgStatic.getInSchoolNum();
						lastResidentNum=	orgStatic.getLastResidentNum();
						residentNum = orgStatic.getResidentNum();
						residentRecruitNum =residentRecruitCount*//*orgStatic.getResidentRecruitNum()*//*;
						inSchoolRecruitNum =inSchoolRecruitCount*//*orgStatic.getInSchoolRecruitNum()*//*;
						residentExaminedNum= orgStatic.getResidentExaminedNum();
						inSchoolExaminedNum=orgStatic.getInSchoolExaminedNum();
						residentGraduatNum=orgStatic.getResidentGraduatNum();
						inSchoolReturnNum= orgStatic.getInSchoolReturnNum();
						residentReturnNum= orgStatic.getResidentReturnNum();
						inSchoolGraduatNum =orgStatic.getInSchoolGraduatNum();
						residentOutNum = orgStatic.getResidentOutNum();
						inSchoolOutNum =orgStatic.getInSchoolOutNum();
						residentInNum =orgStatic.getResidentInNum();
						inSchoolInNum =orgStatic.getInSchoolInNum();
						lastBothNum = orgStatic.getLastBothNum();
						bothNum=orgStatic.getBothNum();
					}
					personStaticExample.setLastInSchoolNum(lastInSchoolNum==null?0:lastInSchoolNum);
					personStaticExample.setInSchoolNum(inSchoolNum==null?0:inSchoolNum);
					personStaticExample.setLastResidentNum(lastResidentNum==null?0:lastResidentNum);
					personStaticExample.setResidentNum(residentNum==null?0:residentNum);
					personStaticExample.setResidentRecruitNum(residentRecruitNum==null?0:residentRecruitNum);//
					personStaticExample.setInSchoolRecruitNum(inSchoolRecruitNum==null?0:inSchoolRecruitNum);//
					personStaticExample.setResidentExaminedNum(residentExaminedNum==null?0:residentExaminedNum);
					personStaticExample.setInSchoolExaminedNum(inSchoolExaminedNum==null?0:inSchoolExaminedNum);
					personStaticExample.setResidentGraduatNum(residentGraduatNum==null?0:residentGraduatNum);
					personStaticExample.setInSchoolGraduatNum(inSchoolGraduatNum==null?0:inSchoolGraduatNum);
					personStaticExample.setResidentReturnNum(residentReturnNum==null?0:residentReturnNum);
					personStaticExample.setInSchoolReturnNum(inSchoolReturnNum==null?0:inSchoolReturnNum);
					personStaticExample.setResidentOutNum(residentOutNum==null?0:residentOutNum);
					personStaticExample.setInSchoolOutNum(inSchoolOutNum==null?0:inSchoolOutNum);
					personStaticExample.setResidentInNum(residentInNum==null?0:residentInNum);
					personStaticExample.setInSchoolInNum(inSchoolInNum==null?0:inSchoolInNum);
					personStaticExample.setLastBothNum(lastBothNum==null?0:lastBothNum);
					personStaticExample.setBothNum(bothNum==null?0:bothNum);
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
	}*/

	/**
	 * 基地导出医师协会名册
	 */
	@RequestMapping(value = "/exportForDetailByOrg")
	public void exportForDetailByOrg(HttpServletResponse response, ResDoctorRecruit resDoctorRecruit, SysUser sysUser, String datas[]) throws Exception {
		SysUser currUser = GlobalContext.getCurrentUser();
		//判断是否是协同基地
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
//		resDoctorRecruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		} else {
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
				datas[i++] = e.getId();
			}
		}
		String titleYear = "";
		List<String> sessionNumbers = new ArrayList<String>();//年级多选
		if (StringUtil.isNotBlank(resDoctorRecruit.getSessionNumber())) {
			String[] numbers = resDoctorRecruit.getSessionNumber().split(",");
			titleYear = resDoctorRecruit.getSessionNumber().replace(",", "_");
			if (numbers != null && numbers.length > 0) {
				sessionNumbers = Arrays.asList(numbers);
				resDoctorRecruit.setSessionNumber("");
			}
		}
		List<String> orgFlowList = new ArrayList<>();
		if(StringUtil.isNotBlank(resDoctorRecruit.getOrgFlow())){
			orgFlowList.add(resDoctorRecruit.getOrgFlow());
		}
		List<JsDoctorInfoExt> doctorInfoList = jsResDoctorRecruitBiz.resDoctorInfoExtListNew(resDoctorRecruit, sysUser, orgFlowList, docTypeList, sessionNumbers,isJointOrg);
		jsResDoctorRecruitBiz.exportForDetailByOrg(doctorInfoList, titleYear, response);
	}

	//	学员认证手机号
	@RequestMapping(value = {"/authenPhone"})
	public String authenPhone(String userFlow,Model model) {
		KeyPair defaultKeyPair = RSAUtils.getDefaultKeyPair();
		setSessionAttribute("defaultKeyPairAuthenPhone",defaultKeyPair);
		RSAPublicKey publicKey = (RSAPublicKey)defaultKeyPair.getPublic();
		if (null != publicKey) {
			//公钥-系数(n)
			model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
			//公钥-指数(e1)
			model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
		}
		return "jsres/authenPhone";
	}

	//	学员修改手机号
	@RequestMapping(value = {"/modifyPhone"})
	public String modifyPhone(String userFlow) {
		return "jsres/modifyPhone";
	}

	//	认证手机号
	@RequestMapping(value = {"/authenPhoneProcess"})
	public String authenPhoneProcess(String userFlow) {
		return "jsres/authenPhoneProcess";
	}

	//	修改手机号
	@RequestMapping(value = {"/modifyPhoneProcess"})
	public String modifyPhoneProcess(String userFlow) {
		return "jsres/modifyPhoneProcess";
	}
	/**
	 * pageTOlocalDoctorDataMonthData（学员轮转数据月报）
	 *
	 * @return
	 */
	@RequestMapping("/localDoctorDataMonthReport")
	public String PageTolocalDoctorDataMonthReport(Model model) {
		SysUser user = GlobalContext.getCurrentUser();
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isJointOrg",isJointOrg);
		return "jsres/hospital/localDoctorDataMonthReport";
	}
	/**
	 * @Description 基地----初始化学员轮转数据月报new
	 * @Author  yuh
	 * @Date   2020/3/4 16:33
	 * @Param
	 */
	@RequestMapping("/initDoctorDataMonthReport")
	@ResponseBody
	public List initDoctorDataMonthReportNew(String orderBy, String monthDate, String isContain, String inSchool,
											 String resident,String isJointOrg){
		SysUser user = GlobalContext.getCurrentUser();
		Map<String,Object> paramMap = new HashMap();
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		paramMap.put("monthDate",monthDate);
		String doctorTypeId = "All";
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(inSchool) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resident)) {//在校专硕
			doctorTypeId = "Graduate";
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resident) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(inSchool)) {//住院医师
			doctorTypeId = "Doctor";
		}
		paramMap.put("doctorTypeId",doctorTypeId);
		paramMap.put("orderBy",orderBy);

		List<JsresSchDataStatistics> list = null;
		//当前基地是否为协同基地
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
			//查询本基地数据
			paramMap.put("orgFlow",user.getOrgFlow());
			list = schdualTaskMapper.selectOrgLunzhuanData(paramMap);
			if(null != list && list.size()>0){
				for (JsresSchDataStatistics info:list) {
					info.setParentOrgFlow("");
				}
			}
		}else {
			List<String> orgFlows = new ArrayList<>();
			orgFlows.add(user.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
				List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(user.getOrgFlow());
				if (null != jointOrgList && jointOrgList.size() > 0) {
					for (ResJointOrg org : jointOrgList) {
						orgFlows.add(org.getJointOrgFlow());
					}
				}
			}
			paramMap.put("orgFlows",orgFlows);
			list = schdualTaskMapper.selectOrgLunzhuanData(paramMap);
		}
		return  list;
	}
	/**
	 * @Description 基地----初始化学员轮转数据月报old
	 * @Author  yuh
	 * @Date   2020/3/4 16:33
	 * @Param
	 */
   /* @RequestMapping("/initDoctorDataMonthReport")
    @ResponseBody*/
	public List initDoctorDataMonthReport(String sortFlag, String monthDate, String isContain, String[] datas)  {
		List<DoctorLunZhuanDataMonthReport> bList=new ArrayList<>();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

		List<SysOrg> joinorgList = new ArrayList<>();
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
		//orgs.add(currOrg);//获取机构和协同机构
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currOrg.getOrgLevelId())) {
			joinorgList = orgBiz.searchJointOrgsByOrg(currOrg.getOrgFlow());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dictTypeId", com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		List<SysDict> doctorTrainingSpeList = this.resDoctorRecruitExtMapper.searchTrainSpeList(paramMap);//所有专业
		String[] doctype = new String[4];
		if (datas.length == 2) {
			doctype[0] = "Company";
			doctype[1] = "CompanyEntrust";
			doctype[2] = "Social";
			doctype[3] = "Graduate";
		} else {
			if (datas[0].equals("NotGraduate")) {
				doctype[0] = "Company";
				doctype[1] = "CompanyEntrust";
				doctype[2] = "Social";
			} else {
				doctype[0] = "Graduate";
			}
		}
		for(int i=0;i<doctorTrainingSpeList.size();i++){
			DoctorLunZhuanDataMonthReport doctorLunZhuanDataMonthReport=new DoctorLunZhuanDataMonthReport();
			Map<String, Object> spemap = new HashMap<>();
			spemap.put("orgFlow", currOrg.getOrgFlow());
			spemap.put("docTypeList", doctype);
			spemap.put("speId", doctorTrainingSpeList.get(i).getDictId());
			List<JsResDoctorRecruitExt> zaipeiList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
			spemap.put("monthDate", monthDate);
			DoctorLunZhuanDataMonthReport dlzdmr= monthlyReportExtMapper2.findDoctorLunZhuanDataMonthReport(spemap);

			doctorLunZhuanDataMonthReport.setId(i+"");
			doctorLunZhuanDataMonthReport.setPid("");
			doctorLunZhuanDataMonthReport.setLevel("1");
			doctorLunZhuanDataMonthReport.setSpeName(doctorTrainingSpeList.get(i).getDictName());
			doctorLunZhuanDataMonthReport.setSpeId(doctorTrainingSpeList.get(i).getDictId());

			doctorLunZhuanDataMonthReport.setTrainSum(zaipeiList.size());
			doctorLunZhuanDataMonthReport.setWriteSum(dlzdmr.getWriteSum());
			doctorLunZhuanDataMonthReport.setDataAuditSum(dlzdmr.getDataAuditSum());
			BigDecimal trainSum=new BigDecimal(zaipeiList.size());
			BigDecimal writeSum=new BigDecimal(dlzdmr.getWriteSum());
			BigDecimal dataAuditSum=new BigDecimal(dlzdmr.getDataAuditSum());
			Double auditScale= 0.00;
			if(dlzdmr.getWriteSum()!=0){
				BigDecimal c= dataAuditSum.divide(writeSum,2,BigDecimal.ROUND_HALF_UP);
				auditScale= c.doubleValue();
			}
			doctorLunZhuanDataMonthReport.setAuditScale(auditScale*100+"%");
			Double aveWriteSum= 0.00;
			Double aveAuditSum=0.00;
			if(zaipeiList.size()!=0){
				BigDecimal c= writeSum.divide(trainSum,2,BigDecimal.ROUND_HALF_UP);
				aveWriteSum= c.doubleValue();
				BigDecimal aveAuditcount= dataAuditSum.divide(trainSum,2,BigDecimal.ROUND_HALF_UP);
				aveAuditSum= aveAuditcount.doubleValue();
			}
			doctorLunZhuanDataMonthReport.setAveWriteSum(aveWriteSum);
			Double aveAuditScale= 0.00;
			BigDecimal aveAuditSumBigdecimal =new BigDecimal(aveAuditSum);
			BigDecimal aveWriteSumBigdecimal =new BigDecimal(aveWriteSum);
			if(aveWriteSum!=0.00){
				BigDecimal c= aveAuditSumBigdecimal.divide(aveWriteSumBigdecimal,2,BigDecimal.ROUND_HALF_UP);
				aveAuditScale= c.doubleValue();
			}
			doctorLunZhuanDataMonthReport.setAveAuditScale((aveAuditScale*100)+"%");

			bList.add(doctorLunZhuanDataMonthReport);
			/**年级*/
			Map<String,Object> sessionNumbermap=new HashMap<>();
			List<String> sessionNumberDistinct=new ArrayList<>();
			sessionNumbermap.put("orgFlow",currOrg.getOrgFlow());
			String[] docTypeList={"Company", "CompanyEntrust", "Social","Graduate"};
			sessionNumbermap.put("docTypeList",docTypeList);
			sessionNumbermap.put("speId",doctorTrainingSpeList.get(i).getDictId());
			List<JsResDoctorRecruitExt>  sessionNUmberList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
			for(JsResDoctorRecruitExt jrs:sessionNUmberList){
				if(!sessionNumberDistinct.contains(jrs.getSessionNumber())){
					sessionNumberDistinct.add(jrs.getSessionNumber());
				}
			}
			for(int j=0;j<sessionNumberDistinct.size();j++) {
				DoctorLunZhuanDataMonthReport doctorLunZhuanDataMonthReport2 = new DoctorLunZhuanDataMonthReport();
				Map<String, Object> spemap1 = new HashMap<>();
				spemap1.put("orgFlow", currOrg.getOrgFlow());
				spemap1.put("docTypeList", doctype);
				spemap1.put("speId", doctorTrainingSpeList.get(i).getDictId());
				spemap1.put("sessionNumber", sessionNumberDistinct.get(j));
				List<JsResDoctorRecruitExt> zaipeiList1 = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap1);
				spemap1.put("monthDate", monthDate);
				DoctorLunZhuanDataMonthReport dlzdmr1= monthlyReportExtMapper2.findDoctorLunZhuanDataMonthReport(spemap1);

				doctorLunZhuanDataMonthReport2.setId(i + "-" + j);
				doctorLunZhuanDataMonthReport2.setPid(doctorLunZhuanDataMonthReport.getId());//
				doctorLunZhuanDataMonthReport2.setLevel("2");
				doctorLunZhuanDataMonthReport2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber

				doctorLunZhuanDataMonthReport2.setTrainSum(zaipeiList1.size());
				doctorLunZhuanDataMonthReport2.setWriteSum(dlzdmr1.getWriteSum());
				doctorLunZhuanDataMonthReport2.setDataAuditSum(dlzdmr1.getDataAuditSum());
				BigDecimal trainSum1=new BigDecimal(zaipeiList1.size());
				BigDecimal writeSum1=new BigDecimal(dlzdmr1.getWriteSum());
				BigDecimal dataAuditSum1=new BigDecimal(dlzdmr1.getDataAuditSum());
				Double auditScale1= 0.00;
				if(dlzdmr1.getWriteSum()!=0){
					BigDecimal c= dataAuditSum1.divide(writeSum1,2,BigDecimal.ROUND_HALF_UP);
					auditScale1= c.doubleValue();
				}
				doctorLunZhuanDataMonthReport2.setAuditScale(auditScale1*100+"%");
				Double aveWriteSum1= 0.00;
				Double aveAuditSum1=0.00;
				if(zaipeiList1.size()!=0){
					BigDecimal c= writeSum1.divide(trainSum1,2,BigDecimal.ROUND_HALF_UP);
					aveWriteSum1= c.doubleValue();
					BigDecimal aveAuditcount= dataAuditSum1.divide(trainSum1,2,BigDecimal.ROUND_HALF_UP);
					aveAuditSum1= aveAuditcount.doubleValue();
				}
				doctorLunZhuanDataMonthReport2.setAveWriteSum(aveWriteSum1);
				Double aveAuditScale1= 0.00;
				BigDecimal aveAuditSumBigdecimal1 =new BigDecimal(aveAuditSum1);
				BigDecimal aveWriteSumBigdecimal1 =new BigDecimal(aveWriteSum1);
				if(aveWriteSum1!=0.00){
					BigDecimal c= aveAuditSumBigdecimal1.divide(aveWriteSumBigdecimal1,2,BigDecimal.ROUND_HALF_UP);
					aveAuditScale1= c.doubleValue();
				}
				doctorLunZhuanDataMonthReport2.setAveAuditScale((aveAuditScale1*100)+"%");

				bList.add(doctorLunZhuanDataMonthReport2);
				if (joinorgList.size() > 0) {
					for (int k = 0; k < joinorgList.size(); k++) {
						DoctorLunZhuanDataMonthReport doctorLunZhuanDataMonthReport3 = new DoctorLunZhuanDataMonthReport();                        // 第三层协同 入科异常 出科异常 出科考核异常
						Map<String,Object> spemap2=new HashMap<>();
						spemap2.put("orgFlow",joinorgList.get(k).getOrgFlow());
						spemap2.put("speId",doctorTrainingSpeList.get(i).getDictId());
						spemap2.put("docTypeList",doctype);
						spemap2.put("sessionNumber",sessionNumberDistinct.get(j));
						List<JsResDoctorRecruitExt> zaipeiList2= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
						spemap2.put("monthDate", monthDate);
						DoctorLunZhuanDataMonthReport dlzdmr2= monthlyReportExtMapper2.findDoctorLunZhuanDataMonthReport(spemap2);

						doctorLunZhuanDataMonthReport3.setId(i + "-" + j+"-"+k);
						doctorLunZhuanDataMonthReport3.setPid(doctorLunZhuanDataMonthReport2.getId());//
						doctorLunZhuanDataMonthReport3.setLevel("3");
						doctorLunZhuanDataMonthReport3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地

						doctorLunZhuanDataMonthReport3.setTrainSum(zaipeiList2.size());
						doctorLunZhuanDataMonthReport3.setWriteSum(dlzdmr2.getWriteSum());
						doctorLunZhuanDataMonthReport3.setDataAuditSum(dlzdmr2.getDataAuditSum());
						BigDecimal trainSum2=new BigDecimal(zaipeiList2.size());
						BigDecimal writeSum2=new BigDecimal(dlzdmr2.getWriteSum());
						BigDecimal dataAuditSum2=new BigDecimal(dlzdmr2.getDataAuditSum());
						Double auditScale2= 0.00;
						if(dlzdmr2.getWriteSum()!=0){
							BigDecimal c= dataAuditSum2.divide(writeSum2,2,BigDecimal.ROUND_HALF_UP);
							auditScale2= c.doubleValue();
						}
						doctorLunZhuanDataMonthReport3.setAuditScale(auditScale2*100+"%");
						Double aveWriteSum2= 0.00;
						Double aveAuditSum2=0.00;
						if(zaipeiList2.size()!=0){
							BigDecimal c= writeSum2.divide(trainSum2,2,BigDecimal.ROUND_HALF_UP);
							aveWriteSum2= c.doubleValue();
							BigDecimal aveAuditcount= dataAuditSum2.divide(trainSum2,2,BigDecimal.ROUND_HALF_UP);
							aveAuditSum2= aveAuditcount.doubleValue();
						}
						doctorLunZhuanDataMonthReport3.setAveWriteSum(aveWriteSum2);
						Double aveAuditScale2= 0.00;
						BigDecimal aveAuditSumBigdecimal2 =new BigDecimal(aveAuditSum2);
						BigDecimal aveWriteSumBigdecimal2 =new BigDecimal(aveWriteSum2);
						if(aveWriteSum2!=0.00){
							BigDecimal c= aveAuditSumBigdecimal2.divide(aveWriteSumBigdecimal2,2,BigDecimal.ROUND_HALF_UP);
							aveAuditScale2= c.doubleValue();
						}
						doctorLunZhuanDataMonthReport3.setAveAuditScale((aveAuditScale2*100)+"%");

						bList.add(doctorLunZhuanDataMonthReport3);
					}
				}

			}
		}
		if ("isContain".equals(isContain)){
			for(int i=0;i<bList.size();i++){
				if("2".equals(bList.get(i).getLevel())){
					DoctorLunZhuanDataMonthReport jj =getChildrenInfoLocalDataMonthReport("",bList.get(i).getId(),bList);
					Integer trainSum=  bList.get(i).getTrainSum() + jj.getTrainSum();
					Integer writeSum=  bList.get(i).getWriteSum() + jj.getWriteSum();
					Integer dataAuditSum=  bList.get(i).getDataAuditSum() + jj.getDataAuditSum();

					bList.get(i).setTrainSum(trainSum);
					bList.get(i).setWriteSum(writeSum);
					bList.get(i).setDataAuditSum(dataAuditSum);
					BigDecimal trainSum2=new BigDecimal(trainSum);
					BigDecimal writeSum2=new BigDecimal(writeSum);
					BigDecimal dataAuditSum2=new BigDecimal(dataAuditSum);
					Double auditScale2= 0.00;
					if(writeSum!=0){
						BigDecimal c= dataAuditSum2.divide(writeSum2,2,BigDecimal.ROUND_HALF_UP);
						auditScale2= c.doubleValue();
					}
					bList.get(i).setAuditScale(auditScale2*100+"%");
					Double aveWriteSum2= 0.00;
					Double aveAuditSum2=0.00;
					if(trainSum!=0){
						BigDecimal c= writeSum2.divide(trainSum2,2,BigDecimal.ROUND_HALF_UP);
						aveWriteSum2= c.doubleValue();
						BigDecimal aveAuditcount= dataAuditSum2.divide(trainSum2,2,BigDecimal.ROUND_HALF_UP);
						aveAuditSum2= aveAuditcount.doubleValue();
					}
					bList.get(i).setAveWriteSum(aveWriteSum2);
					Double aveAuditScale2= 0.00;
					BigDecimal aveAuditSumBigdecimal2 =new BigDecimal(aveAuditSum2);
					BigDecimal aveWriteSumBigdecimal2 =new BigDecimal(aveWriteSum2);
					if(aveWriteSum2!=0.00){
						BigDecimal c= aveAuditSumBigdecimal2.divide(aveWriteSumBigdecimal2,2,BigDecimal.ROUND_HALF_UP);
						aveAuditScale2= c.doubleValue();
					}
					bList.get(i).setAveAuditScale((aveAuditScale2*100)+"%");
				}
			}
			for(int i=0;i<bList.size();i++){
				if("1".equals(bList.get(i).getLevel())){
					DoctorLunZhuanDataMonthReport jj =getChildrenInfoLocalDataMonthReport("1",bList.get(i).getId(),bList);
					Integer trainSum=  bList.get(i).getTrainSum() + jj.getTrainSum();
					Integer writeSum=  bList.get(i).getWriteSum() + jj.getWriteSum();
					Integer dataAuditSum=  bList.get(i).getDataAuditSum() + jj.getDataAuditSum();

					bList.get(i).setTrainSum(trainSum);
					bList.get(i).setWriteSum(writeSum);
					bList.get(i).setDataAuditSum(dataAuditSum);
					BigDecimal trainSum2=new BigDecimal(trainSum);
					BigDecimal writeSum2=new BigDecimal(writeSum);
					BigDecimal dataAuditSum2=new BigDecimal(dataAuditSum);
					Double auditScale2= 0.00;
					if(writeSum!=0){
						BigDecimal c= dataAuditSum2.divide(writeSum2,2,BigDecimal.ROUND_HALF_UP);
						auditScale2= c.doubleValue();
					}
					bList.get(i).setAuditScale(auditScale2*100+"%");
					Double aveWriteSum2= 0.00;
					Double aveAuditSum2=0.00;
					if(trainSum!=0){
						BigDecimal c= writeSum2.divide(trainSum2,2,BigDecimal.ROUND_HALF_UP);
						aveWriteSum2= c.doubleValue();
						BigDecimal aveAuditcount= dataAuditSum2.divide(trainSum2,2,BigDecimal.ROUND_HALF_UP);
						aveAuditSum2= aveAuditcount.doubleValue();
					}
					bList.get(i).setAveWriteSum(aveWriteSum2);
					Double aveAuditScale2= 0.00;
					BigDecimal aveAuditSumBigdecimal2 =new BigDecimal(aveAuditSum2);
					BigDecimal aveWriteSumBigdecimal2 =new BigDecimal(aveWriteSum2);
					if(aveWriteSum2!=0.00){
						BigDecimal c= aveAuditSumBigdecimal2.divide(aveWriteSumBigdecimal2,2,BigDecimal.ROUND_HALF_UP);
						aveAuditScale2= c.doubleValue();
					}
					bList.get(i).setAveAuditScale((aveAuditScale2*100)+"%");

				}
			}
		}
		bList =doctorLunZhuanDataMonthReportSort(sortFlag,bList);
		return bList;
	}
	/**基地--学员轮转数据月报排序*/
	public List<DoctorLunZhuanDataMonthReport> doctorLunZhuanDataMonthReportSort(String value,List<DoctorLunZhuanDataMonthReport> list){
		if(!"".equals(value)){
			List<DoctorLunZhuanDataMonthReport> firstList=new ArrayList<>();
			List<DoctorLunZhuanDataMonthReport> NofirstList=new ArrayList<>();
			for(DoctorLunZhuanDataMonthReport li:list){
				if("".equals(li.getPid()) || null==li.getPid()){
					firstList.add(li);
				}
				if(!"".equals(li.getPid()) && null!=li.getPid()){
					NofirstList.add(li);
				}
			}
			if("auditScaleDesc".equals(value)){
				Collections.sort(firstList, new Comparator<DoctorLunZhuanDataMonthReport>() {
					public int compare(DoctorLunZhuanDataMonthReport o1, DoctorLunZhuanDataMonthReport o2) {
						Double auditScale1=0.00;
						Double auditScale2=0.00;
						if(null!=o1.getAuditScale()&& !"".equals(o1.getAuditScale())){
							auditScale1 = Double.parseDouble(o1.getAuditScale().replace("%",""));
						}
						if(null!=o2.getAuditScale()&& !"".equals(o2.getAuditScale())){
							auditScale2 = Double.parseDouble(o2.getAuditScale().replace("%",""));
						}
						//降序
						return auditScale2.compareTo(auditScale1);
					}
				});
				list = listFormatDoctorLunZhuanDataMonthReport(firstList,NofirstList);
			}else if("auditScaleAsc".equals(value)){
				Collections.sort(firstList, new Comparator<DoctorLunZhuanDataMonthReport>() {
					public int compare(DoctorLunZhuanDataMonthReport o1, DoctorLunZhuanDataMonthReport o2) {
						Double auditScale1=0.00;
						Double auditScale2=0.00;
						if(null!=o1.getAuditScale()&& !"".equals(o1.getAuditScale())){
							auditScale1 = Double.parseDouble(o1.getAuditScale().replace("%",""));
						}
						if(null!=o2.getAuditScale()&& !"".equals(o2.getAuditScale())){
							auditScale2 = Double.parseDouble(o2.getAuditScale().replace("%",""));
						}
						//升序
						return auditScale1.compareTo(auditScale2);
					}
				});
				list = listFormatDoctorLunZhuanDataMonthReport(firstList,NofirstList);
			}else if("aveWriteDesc".equals(value)){
				Collections.sort(firstList, new Comparator<DoctorLunZhuanDataMonthReport>() {
					public int compare(DoctorLunZhuanDataMonthReport o1, DoctorLunZhuanDataMonthReport o2) {
						Double	aveWriteSum1 = o1.getAveWriteSum();
						Double	aveWriteSum2 = o2.getAveWriteSum();
						//降序
						return aveWriteSum2.compareTo(aveWriteSum1);
					}
				});
				list = listFormatDoctorLunZhuanDataMonthReport(firstList,NofirstList);
			}else if("aveWriteAsc".equals(value)){
				Collections.sort(firstList, new Comparator<DoctorLunZhuanDataMonthReport>() {
					public int compare(DoctorLunZhuanDataMonthReport o1, DoctorLunZhuanDataMonthReport o2) {
						Double	aveWriteSum1 = o1.getAveWriteSum();
						Double	aveWriteSum2 = o2.getAveWriteSum();
						//升序
						return aveWriteSum1.compareTo(aveWriteSum2);
					}
				});
				list = listFormatDoctorLunZhuanDataMonthReport(firstList,NofirstList);
			}
		}
		return list;
	}
	public List<DoctorLunZhuanDataMonthReport> listFormatDoctorLunZhuanDataMonthReport(List<DoctorLunZhuanDataMonthReport> first,List<DoctorLunZhuanDataMonthReport> notFirst){
		first.addAll(notFirst);
		return first;
	}
	/**获取每层子辈的信息之和（学员轮转数据月报）*/
	public DoctorLunZhuanDataMonthReport getChildrenInfoLocalDataMonthReport(String level ,String id,List<DoctorLunZhuanDataMonthReport> blist){
		DoctorLunZhuanDataMonthReport jj=new DoctorLunZhuanDataMonthReport();
		if("".equals(level)){
			Integer trainSum=0;
			Integer writeSum=0;
			Integer dataAuditSum=0;
			for(int i=0;i<blist.size();i++){
				if(id.equals(blist.get(i).getPid())){
					trainSum = trainSum + blist.get(i).getTrainSum(); //在培人数
					writeSum = writeSum + blist.get(i).getWriteSum(); //数据填写量
					dataAuditSum = dataAuditSum + blist.get(i).getDataAuditSum(); //数据审核量
				}
			}
			jj.setTrainSum(trainSum);
			jj.setWriteSum(writeSum);
			jj.setDataAuditSum(dataAuditSum);
		}else{
			Integer trainSum=0;
			Integer writeSum=0;
			Integer dataAuditSum=0;
			for(int i=0;i<blist.size();i++){
				Integer trainSum1=0;
				Integer writeSum1=0;
				Integer dataAuditSum1=0;
				if(id.equals(blist.get(i).getPid())){
					for(int j=0;j<blist.size();j++){
						if(blist.get(i).getId().equals(blist.get(j).getPid())){
							trainSum1 = trainSum1 + blist.get(j).getTrainSum(); //在培人数
							writeSum1 = writeSum1 + blist.get(j).getWriteSum(); //数据填写量
							dataAuditSum1=dataAuditSum1+ blist.get(j).getDataAuditSum();//数据审核量
						}
					}
				}
				trainSum = trainSum+ trainSum1; //在陪人数
				writeSum = writeSum+ writeSum1; //数据填写量
				dataAuditSum=dataAuditSum+ dataAuditSum1;//数据审核量
			}
			jj.setTrainSum(trainSum);
			jj.setWriteSum(writeSum);
			jj.setDataAuditSum(dataAuditSum);
		}
		return jj;
	}
	public SchRotationDept readStandardRotationDept(String resultFlow){
		SchRotationDept rotationDept = null;
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if(result!=null){
				String rotationFlow = result.getRotationFlow();
				String standardDeptId = result.getStandardDeptId();
				SchRotationGroup group = rotationGroupBiz.readSchRotationGroup(result.getStandardGroupFlow());
				if(group!=null){
					if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)){
						SchRotationDeptExample example = new SchRotationDeptExample();
                        SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
								.andRotationFlowEqualTo(rotationFlow)
								.andStandardDeptIdEqualTo(standardDeptId)
								.andOrgFlowIsNull()
								.andGroupFlowEqualTo(result.getStandardGroupFlow());
						List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
						if(rotationDeptList!=null && rotationDeptList.size()>0){
							rotationDept = rotationDeptList.get(0);
						}
					}
				}
			}
		}
		return rotationDept;
	}

	@RequestMapping("/doctorException")
	public String doctorException(Model model) {
		return "jsres/global/doctorException";
	}

	@RequestMapping("/doctorExceptionListByOrg")
	@ResponseBody
	public List doctorExceptionListByOrg(String monthDate, String[] datas) {
//		List<DoctorLunZhuanExceptionParam> bList = new ArrayList<>();
		List<String> doctype = new ArrayList<>();
		if (datas.length == 2) {
			doctype.add("Company");
			doctype.add("CompanyEntrust");
			doctype.add("Social");
			doctype.add("Graduate");
		} else {
			if (datas[0].equals("NotGraduate")) {
				doctype.add("Company");
				doctype.add("CompanyEntrust");
				doctype.add("Social");
			} else {
				doctype.add("Graduate");
			}
		}
		Map<String,Object> param = new HashMap<>();
		param.put("docTypeList",doctype);
		String startDate = DateUtil.setFirstDayOfMonth(monthDate);
		String endDate = DateUtil.setFirstDayOfMonth(DateUtil.addMonth(monthDate,1));
		param.put("startDate",startDate);
		param.put("endDate",endDate);
		List<DoctorLunZhuanExceptionParam> bList = monthlyReportExtMapper2.searchDocCycleListOrg(param);
		return bList;
	}

	@RequestMapping("/localDoctorExceptionNew")
	public String localDoctorExceptionNew(Model model) {
		SysUser user = GlobalContext.getCurrentUser();
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isJointOrg",isJointOrg);
		return "jsres/hospital/localDoctorExceptionNew";
	}

	@RequestMapping("/initDoctorExceptionNew")
	@ResponseBody
	public List initDoctorExceptionNew(String sortFlag, String monthDate, String isContain, String[] datas) {
		List<DoctorLunZhuanExceptionParam> bList = new ArrayList<>();

		SysUser user = GlobalContext.getCurrentUser();
		SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());

		List<String> doctype = new ArrayList<>();
		if (datas.length == 2) {
			doctype.add("Company");
			doctype.add("CompanyEntrust");
			doctype.add("Social");
			doctype.add("Graduate");
		} else {
			if (datas[0].equals("NotGraduate")) {
				doctype.add("Company");
				doctype.add("CompanyEntrust");
				doctype.add("Social");
			} else {
				doctype.add("Graduate");
			}
		}

		Map<String,Object> param = new HashMap<>();
		Map<String,Object> param2 = new HashMap<>();
		String startDate = DateUtil.setFirstDayOfMonth(monthDate);
		String endDate = DateUtil.setFirstDayOfMonth(DateUtil.addMonth(monthDate,1));
		List<String> orgFlows = new ArrayList<>();
		orgFlows.add(currOrg.getOrgFlow());
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(currOrg.getOrgFlow());
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
			if(null != jointOrgList && jointOrgList.size()>0){
				for (ResJointOrg jointOrg:jointOrgList) {
					orgFlows.add(jointOrg.getJointOrgFlow());
				}
			}
		}
//		param.put("orgFlow",currOrg.getOrgFlow());
		param.put("orgFlows",orgFlows);
		param2.put("orgFlows",orgFlows);
		param.put("docTypeList",doctype);
		param2.put("docTypeList",doctype);
		param.put("startDate",startDate);
		param2.put("startDate",startDate);
		param.put("endDate",endDate);
		param2.put("endDate",endDate);
		List<Map<String,Object>> mapList = monthlyReportExtMapper2.searchDocCycleListNew(param);
		if(null != mapList && mapList.size()>0) {
			for (int i = 0; i < mapList.size(); i++) {
				DoctorLunZhuanExceptionParam doctorLunZhuanExceptionParam = new DoctorLunZhuanExceptionParam();

				doctorLunZhuanExceptionParam.setId(i + "");
				doctorLunZhuanExceptionParam.setPid("");
				doctorLunZhuanExceptionParam.setLevel("1");
				doctorLunZhuanExceptionParam.setSpeName((String) mapList.get(i).get("DICT_NAME"));
				doctorLunZhuanExceptionParam.setSpeId((String) mapList.get(i).get("DICT_ID"));
				doctorLunZhuanExceptionParam.setOrgFlow(currOrg.getOrgFlow());
				doctorLunZhuanExceptionParam.setOutException(Integer.parseInt(mapList.get(i).get("NOT_NUM").toString()));
				doctorLunZhuanExceptionParam.setOutExamException(Integer.parseInt(mapList.get(i).get("NOT_EXAM_NUM").toString()));
				bList.add(doctorLunZhuanExceptionParam);

				/**按年级*/
				List<String> sessionNumberDistinct=new ArrayList<>();
				Map<String,Integer> sessionNumberMap = new HashMap<>();
				Map<String,Integer> sessionNumberMap2 = new HashMap<>();
				param.put("speId",(String) mapList.get(i).get("DICT_ID"));
				param2.put("speId",(String) mapList.get(i).get("DICT_ID"));
				List<Map<String,Object>> sessionNumberMapList = monthlyReportExtMapper2.searchDocCKYCBySpeId(param);//出科异常
				List<Map<String,Object>> sessionNumberMapList2 = monthlyReportExtMapper2.searchDocCKYCBySpeId2(param);//出科考核异常
				if(null != sessionNumberMapList && sessionNumberMapList.size()>0){
					for (Map<String,Object> map:sessionNumberMapList) {
						if(!sessionNumberDistinct.contains((String)map.get("SESSION_NUMBER"))){
							sessionNumberDistinct.add((String)map.get("SESSION_NUMBER"));
							sessionNumberMap.put((String)map.get("SESSION_NUMBER"),Integer.parseInt(map.get("NOT_NUM").toString()));
						}else{
							sessionNumberMap.put((String)map.get("SESSION_NUMBER"),sessionNumberMap.get((String)map.get("SESSION_NUMBER"))+Integer.parseInt(map.get("NOT_NUM").toString()));
						}
					}
				}
				if(null != sessionNumberMapList2 && sessionNumberMapList2.size()>0){
					for (Map<String,Object> map:sessionNumberMapList2) {
						if(!sessionNumberDistinct.contains((String)map.get("SESSION_NUMBER"))){
							sessionNumberDistinct.add((String)map.get("SESSION_NUMBER"));
							sessionNumberMap2.put((String)map.get("SESSION_NUMBER"),Integer.parseInt(map.get("NOT_TEST_NUM").toString()));
						}else{
							if(null == sessionNumberMap2.get((String)map.get("SESSION_NUMBER"))){
								sessionNumberMap2.put((String)map.get("SESSION_NUMBER"),Integer.parseInt(map.get("NOT_TEST_NUM").toString()));
							}else {
								sessionNumberMap2.put((String) map.get("SESSION_NUMBER"), sessionNumberMap2.get((String) map.get("SESSION_NUMBER")) + Integer.parseInt(map.get("NOT_TEST_NUM").toString()));
							}
						}
					}
				}
				if(null != sessionNumberDistinct && sessionNumberDistinct.size()>0) {
					for (int j = 0; j < sessionNumberDistinct.size(); j++) {
						DoctorLunZhuanExceptionParam doctorLunZhuanExceptionParam2 = new DoctorLunZhuanExceptionParam();
						// 第二层 年级 出科异常 出科考核异常
						doctorLunZhuanExceptionParam2.setId(i + "-" + j);
						doctorLunZhuanExceptionParam2.setPid(doctorLunZhuanExceptionParam.getId());
						doctorLunZhuanExceptionParam2.setLevel("2");
						doctorLunZhuanExceptionParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
						if(null == sessionNumberMap.get(sessionNumberDistinct.get(j))){
							doctorLunZhuanExceptionParam2.setOutException(0);
						}else {
							doctorLunZhuanExceptionParam2.setOutException((Integer) sessionNumberMap.get(sessionNumberDistinct.get(j)));
						}
						if(null == sessionNumberMap2.get(sessionNumberDistinct.get(j))){
							doctorLunZhuanExceptionParam2.setOutExamException(0);
						}else {
							doctorLunZhuanExceptionParam2.setOutExamException((Integer) sessionNumberMap2.get(sessionNumberDistinct.get(j)));
						}
						bList.add(doctorLunZhuanExceptionParam2);

						//包含协同基地
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
							if(null != jointOrgList && jointOrgList.size()>0) {
								for (int k = 0; k < jointOrgList.size(); k++) {
									param2.put("orgFlow",jointOrgList.get(k).getJointOrgFlow());
									param2.put("sessionNumber",sessionNumberDistinct.get(j));
									List<Map<String,Object>> jointMapList = monthlyReportExtMapper2.searchDocCycleListNew(param2);
									DoctorLunZhuanExceptionParam doctorLunZhuanExceptionParam3 = new DoctorLunZhuanExceptionParam();
									doctorLunZhuanExceptionParam3.setId(i + "-" + j + "-" + k);
									doctorLunZhuanExceptionParam3.setPid(doctorLunZhuanExceptionParam2.getId());//
									doctorLunZhuanExceptionParam3.setLevel("3");
									doctorLunZhuanExceptionParam3.setSpeName(jointOrgList.get(k).getJointOrgName());//存协同基地
									if(null != jointMapList && jointMapList.size()>0) {
										doctorLunZhuanExceptionParam3.setOutException(Integer.parseInt(jointMapList.get(0).get("NOT_NUM").toString()));
										doctorLunZhuanExceptionParam3.setOutExamException(Integer.parseInt(jointMapList.get(0).get("NOT_EXAM_NUM").toString()));
									}else{
										doctorLunZhuanExceptionParam3.setOutException(0);
										doctorLunZhuanExceptionParam3.setOutExamException(0);
									}

									bList.add(doctorLunZhuanExceptionParam3);
								}
							}
						}

					}
				}
			}
		}
		return bList;
	}

	@RequestMapping("/initLocalDoctorExceptionDetailNew")
	public  String initLocalDoctorExceptionDetailNew(Model model,String orgFlow,String speId,String data[],String isContain,String monthDate,String tabTag){
		List<SysOrg> orgs = new ArrayList<>();
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		orgs.add(org);

		List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
		if (orgList != null && orgList.size() > 0) {
			orgs.addAll(orgList);
		}
		model.addAttribute("orgs",orgs);
		model.addAttribute("dataType",data);
		model.addAttribute("orgFlow",orgFlow);
		model.addAttribute("isContain",isContain);
		model.addAttribute("monthDate",monthDate);
		model.addAttribute("speId",speId);
		model.addAttribute("tabTag",tabTag);
		return "jsres/hospital/localDoctorExceptionDetailNew";
	}

	@RequestMapping("/searchDocInfoDetail")
	public  String searchDocInfoDetail(Integer currentPage,HttpServletRequest request,Model model,String orgFlow,String isContain,
									   String orgFlow2,String sessionNumber,String[] datas,String speId,String monthDate,String tabTag) {

		Map<String, Object> param = new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		List<String> orgFlows = new ArrayList<>();
		orgFlows.add(orgFlow);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
				if (null != jointOrgList && jointOrgList.size() > 0) {
					for (ResJointOrg jointOrg : jointOrgList) {
						orgFlows.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}
		param.put("orgFlows",orgFlows);
		param.put("orgFlow2", orgFlow2);
		param.put("docTypeList", docTypeList);
		param.put("sessionNumber", sessionNumber);
		param.put("trainingSpeId", speId);
		String startDate = DateUtil.setFirstDayOfMonth(monthDate);
		String endDate = DateUtil.setFirstDayOfMonth(DateUtil.addMonth(monthDate,1));
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		List<Map<String, Object>> list = null;
		PageHelper.startPage(currentPage, getPageSize(request));
		if("notNum".equals(tabTag)) {
			list = monthlyReportExtMapper2.searchNotNumListInfo(param);
		}else{
			list = monthlyReportExtMapper2.searchNotExamNumListInfo(param);
		}
		model.addAttribute("list",list);
		return "jsres/hospital/searchNotNumDetail";
	}

	@RequestMapping("/exportDocInfoDetail")
	public void exportDocInfoDetail(HttpServletResponse response,String orgFlow,String isContain, String orgFlow2,
									String sessionNumber,String[] datas,String speId,String monthDate,String tabTag,
									String[] doctorFlows) throws Exception{

		Map<String, Object> param = new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		List<String> orgFlows = new ArrayList<>();
		orgFlows.add(orgFlow);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
				if (null != jointOrgList && jointOrgList.size() > 0) {
					for (ResJointOrg jointOrg : jointOrgList) {
						orgFlows.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}
		param.put("orgFlows",orgFlows);
		param.put("orgFlow2", orgFlow2);
		param.put("docTypeList", docTypeList);
		param.put("sessionNumber", sessionNumber);
		param.put("trainingSpeId", speId);
		String startDate = DateUtil.setFirstDayOfMonth(monthDate);
		String endDate = DateUtil.setFirstDayOfMonth(DateUtil.addMonth(monthDate,1));
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if(doctorFlows != null && doctorFlows.length > 0) {
			param.put("doctorFlows", Arrays.asList(doctorFlows));
		}
		List<Map<String, Object>> list = null;
		String name = "";
		if("notNum".equals(tabTag)) {
			list = monthlyReportExtMapper2.searchNotNumListInfo(param);
			name = "出科异常";
		}else{
			list = monthlyReportExtMapper2.searchNotExamNumListInfo(param);
			name = "出科考核异常";
		}
		String fileName = name+"学员明细.xls";
		String []titles = new String[]{
				"doctorName:姓名",
				"sexName:性别",
				"userPhone:手机号码",
				"sessionNumber:年级",
				"trainingSpeName:培训专业",
				"schDeptName:当前轮转科室",
				"schEndDate:应出科日期",
				"schStartDate:应入科日期",
		};
		ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream());
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}



	/**
	 * pageTOlocalDoctorException（学员轮转异常统计）
	 *
	 * @return
	 */
	@RequestMapping("/localDoctorException")
	public String PageTolocalDoctorException(Model model) {
		return "jsres/hospital/localDoctorException";
	}
	/**
	 * 基地----初始化学员轮转异常
	 *
	 * @param monthDate
	 * @param isContain
	 * @return
	 */
	@RequestMapping("/initDoctorException")
	@ResponseBody
	public List init(String sortFlag, String monthDate, String isContain, String[] datas)  {
		List<DoctorLunZhuanExceptionParam> bList=new ArrayList<>();
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

		List<SysOrg> joinorgList = new ArrayList<>();
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
		//orgs.add(currOrg);//获取机构和协同机构
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currOrg.getOrgLevelId())) {
			joinorgList = orgBiz.searchJointOrgsByOrg(currOrg.getOrgFlow());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dictTypeId", com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		List<SysDict> doctorTrainingSpeList = this.resDoctorRecruitExtMapper.searchTrainSpeList(paramMap);//所有专业
		String[] doctype = new String[4];
		if (datas.length == 2) {
			doctype[0] = "Company";
			doctype[1] = "CompanyEntrust";
			doctype[2] = "Social";
			doctype[3] = "Graduate";
		} else {
			if (datas[0].equals("NotGraduate")) {
				doctype[0] = "Company";
				doctype[1] = "CompanyEntrust";
				doctype[2] = "Social";
			} else {
				doctype[0] = "Graduate";
			}
		}
		String isPayCurrentOrg= JsresPowerCfgController.jsresPowerCfgMap("jsres_"+user.getOrgFlow()+"_guocheng");//当前基地有无付费
		//if ("isContain".equals(isContain)) {

		//} else {
		for(int i=0;i<doctorTrainingSpeList.size();i++){
			DoctorLunZhuanExceptionParam doctorLunZhuanExceptionParam=new DoctorLunZhuanExceptionParam();

			Map<String,Object> map=new HashMap<>();
			String startDate = DateUtil.setFirstDayOfMonth(monthDate);
			String endDate =DateUtil.setLastDateOfMonth(monthDate);
			map.put("orgFlow",currOrg.getOrgFlow());
			map.put("trainingSpeId",doctorTrainingSpeList.get(i).getDictId());
			map.put("docTypeList",doctype);
			map.put("startDate",startDate);
			map.put("endDate",endDate);
			Integer inExceptionCount=0;
			List<String> inExceptionDoctorflowCount=new ArrayList<>();
			List<Map<String,Object>> mapList=monthlyReportExtMapper2.searchDocCycleList(map);
			for(Map<String,Object> mapp:mapList){
				String doctorFlow = (String) mapp.get("doctorFlow");
				Integer count= monthlyReportExtMapper2.getCurrentMonthInExceptionCount(doctorFlow,startDate,endDate);
				/*if(count==0){
					inExceptionCount++;
					inExceptionDoctorflowCount.add(doctorFlow);
				}*/
				//      20200521 sessionNumber<=monthdate
				if(count==0){
					String sessionNumber = (String) mapp.get("sessionNumber");
					Integer sessionNunberNUM=Integer.parseInt(sessionNumber);
					Integer yearNUM=Integer.parseInt(monthDate.split("-")[0]);
					if(sessionNunberNUM<=yearNUM){
						inExceptionCount++;
						inExceptionDoctorflowCount.add(doctorFlow);
					}
				}
				//      20200521 sessionNumber<=monthdate
			}
			List<SchArrangeResult> arrResultList  = monthlyReportExtMapper2.searchCycleArrangeResults(map);//获取排班结果
			List<String> outExceptionDoctorflowCount=new ArrayList<>();
			List<String> outExamExceptionDoctorflowCount=new ArrayList<>();
			if(arrResultList.size() > 0){
				for(SchArrangeResult schr:arrResultList){
					String content="";
					List<ResSchProcessExpress> resRecList=new ArrayList<>();
					//出科考核表附件（出科异常）
					SchArrangeResult result=schArrangeResultBiz.readSchArrangeResult(schr.getResultFlow());
					SchRotationDept schRotationDept=readStandardRotationDept(schr.getResultFlow());
					if (result != null&&StringUtil.isNotBlank(result.getDoctorFlow())&&schRotationDept!=null&&StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
						ResSchProcessExpress rec = expressBiz.queryResRec(schRotationDept.getRecordFlow(), result.getDoctorFlow(), AfterRecTypeEnum.AfterSummary.getId());
						content = null == rec ? "" : rec.getRecContent();
					}
					//出科考核表（出科考核异常）
					ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(schr.getResultFlow());
					if (resDoctorSchProcess != null) {
						String processFlow = resDoctorSchProcess.getProcessFlow();
						resRecList = expressBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, "AfterEvaluation");
					}
					//出科考核异常
					if(resRecList.size()==0 || null == resRecList){
						if(!outExamExceptionDoctorflowCount.contains(schr.getDoctorFlow())){
							outExamExceptionDoctorflowCount.add(schr.getDoctorFlow());
						}
					}
					//出科异常
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPayCurrentOrg)) {
						if(resRecList.size()==0 || null == resRecList || StringUtil.isBlank(content)){
							if(!outExceptionDoctorflowCount.contains(schr.getDoctorFlow())){
								outExceptionDoctorflowCount.add(schr.getDoctorFlow());
							}
						}
					}else{
						if (StringUtil.isBlank(content)) {
							if(!outExceptionDoctorflowCount.contains(schr.getDoctorFlow())){
								outExceptionDoctorflowCount.add(schr.getDoctorFlow());
							}
						}
					}
				}
			}
			doctorLunZhuanExceptionParam.setId(i+"");
			doctorLunZhuanExceptionParam.setPid("");
			doctorLunZhuanExceptionParam.setLevel("1");
			doctorLunZhuanExceptionParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
			doctorLunZhuanExceptionParam.setSpeId(doctorTrainingSpeList.get(i).getDictId());

			doctorLunZhuanExceptionParam.setInException(inExceptionCount);
			doctorLunZhuanExceptionParam.setInExceptionDoctorFlowList(inExceptionDoctorflowCount);
			doctorLunZhuanExceptionParam.setOutException(outExceptionDoctorflowCount.size());
			doctorLunZhuanExceptionParam.setOutExceptionDoctorFlowList(outExceptionDoctorflowCount);
			doctorLunZhuanExceptionParam.setOutExamException(outExamExceptionDoctorflowCount.size());
			doctorLunZhuanExceptionParam.setOutExamExceptionDoctorFlowList(outExamExceptionDoctorflowCount);

			bList.add(doctorLunZhuanExceptionParam);
			/**年级*/
			Map<String,Object> sessionNumbermap=new HashMap<>();
			List<String> sessionNumberDistinct=new ArrayList<>();
			sessionNumbermap.put("orgFlow",currOrg.getOrgFlow());
			String[] docTypeList={"Company", "CompanyEntrust", "Social","Graduate"};
			sessionNumbermap.put("docTypeList",docTypeList);
			sessionNumbermap.put("speId",doctorTrainingSpeList.get(i).getDictId());
			List<JsResDoctorRecruitExt>  sessionNUmberList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
			for(JsResDoctorRecruitExt jrs:sessionNUmberList){
				if(!sessionNumberDistinct.contains(jrs.getSessionNumber())){
					sessionNumberDistinct.add(jrs.getSessionNumber());
				}
			}
			for(int j=0;j<sessionNumberDistinct.size();j++) {
				DoctorLunZhuanExceptionParam doctorLunZhuanExceptionParam2 = new DoctorLunZhuanExceptionParam();
				// 第二层 年级 入科异常 出科异常 出科考核异常
				Map<String,Object> map1=new HashMap<>();
				map1.put("orgFlow",currOrg.getOrgFlow());
				map1.put("trainingSpeId",doctorTrainingSpeList.get(i).getDictId());
				map1.put("docTypeList",doctype);
				map1.put("startDate",DateUtil.setFirstDayOfMonth(monthDate));
				map1.put("endDate",DateUtil.setLastDateOfMonth(monthDate));
				map1.put("sessionNumber",sessionNumberDistinct.get(j));

				Integer inExceptionCount1=0;
				List<String> inExceptionDoctorflowCount1=new ArrayList<>();
				List<Map<String,Object>> mapList1=monthlyReportExtMapper2.searchDocCycleList(map1);
				for(Map<String,Object> mapp1:mapList1){
					String doctorFlow = (String) mapp1.get("doctorFlow");
					Integer count= monthlyReportExtMapper2.getCurrentMonthInExceptionCount(doctorFlow,startDate,endDate);
					if(count==0){
						inExceptionCount1++;
						inExceptionDoctorflowCount1.add(doctorFlow);
					}
				}

				List<SchArrangeResult> arrResultList1  = monthlyReportExtMapper2.searchCycleArrangeResults(map1);//获取排班结果
				List<String> outExceptionDoctorflowCount1=new ArrayList<>();
				List<String> outExamExceptionDoctorflowCount1=new ArrayList<>();
				if(arrResultList1.size() > 0){
					for(SchArrangeResult schr:arrResultList1){
						String content="";
						List<ResSchProcessExpress> resRecList=new ArrayList<>();
						//出科考核表附件（出科异常）
						SchArrangeResult result=schArrangeResultBiz.readSchArrangeResult(schr.getResultFlow());
						SchRotationDept schRotationDept=readStandardRotationDept(schr.getResultFlow());
						if (result != null&&StringUtil.isNotBlank(result.getDoctorFlow())&&schRotationDept!=null&&StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
							ResSchProcessExpress rec = expressBiz.queryResRec(schRotationDept.getRecordFlow(), result.getDoctorFlow(), AfterRecTypeEnum.AfterSummary.getId());
							content = null == rec ? "" : rec.getRecContent();
						}
						//出科考核表（出科考核异常）
						ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(schr.getResultFlow());
						if (resDoctorSchProcess != null) {
							String processFlow = resDoctorSchProcess.getProcessFlow();
							resRecList = expressBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, "AfterEvaluation");
						}
						//出科考核异常
						if(resRecList.size()==0 || null == resRecList){
							if(!outExamExceptionDoctorflowCount1.contains(schr.getDoctorFlow())){
								outExamExceptionDoctorflowCount1.add(schr.getDoctorFlow());
							}
						}
						//出科异常
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPayCurrentOrg)) {
							if(resRecList.size()==0 || null == resRecList || StringUtil.isBlank(content)){
								if(!outExceptionDoctorflowCount1.contains(schr.getDoctorFlow())){
									outExceptionDoctorflowCount1.add(schr.getDoctorFlow());
								}
							}
						}else{
							if (StringUtil.isBlank(content)) {
								if(!outExceptionDoctorflowCount1.contains(schr.getDoctorFlow())){
									outExceptionDoctorflowCount1.add(schr.getDoctorFlow());
								}
							}
						}
					}
				}

				doctorLunZhuanExceptionParam2.setId(i + "-" + j);
				doctorLunZhuanExceptionParam2.setPid(doctorLunZhuanExceptionParam.getId());//
				doctorLunZhuanExceptionParam2.setLevel("2");
				doctorLunZhuanExceptionParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber

				doctorLunZhuanExceptionParam2.setInException(inExceptionCount1);
				doctorLunZhuanExceptionParam2.setInExceptionDoctorFlowList(inExceptionDoctorflowCount1);
				doctorLunZhuanExceptionParam2.setOutException(outExceptionDoctorflowCount1.size());
				doctorLunZhuanExceptionParam2.setOutExceptionDoctorFlowList(outExceptionDoctorflowCount1);
				doctorLunZhuanExceptionParam2.setOutExamException(outExamExceptionDoctorflowCount1.size());
				doctorLunZhuanExceptionParam2.setOutExamExceptionDoctorFlowList(outExamExceptionDoctorflowCount1);

				bList.add(doctorLunZhuanExceptionParam2);
				if (joinorgList.size() > 0) {
					for (int k = 0; k < joinorgList.size(); k++) {
						String isPayCurrentOrg2= JsresPowerCfgController.jsresPowerCfgMap("jsres_"+joinorgList.get(k).getOrgFlow()+"_guocheng");//当前基地有无付费
						DoctorLunZhuanExceptionParam doctorLunZhuanExceptionParam3 = new DoctorLunZhuanExceptionParam();
						// 第三层协同 入科异常 出科异常 出科考核异常
						Map<String,Object> map2=new HashMap<>();
						map2.put("orgFlow",joinorgList.get(k).getOrgFlow());
						map2.put("trainingSpeId",doctorTrainingSpeList.get(i).getDictId());
						map2.put("docTypeList",doctype);
						map2.put("startDate",DateUtil.setFirstDayOfMonth(monthDate));
						map2.put("endDate",DateUtil.setLastDateOfMonth(monthDate));
						map2.put("sessionNumber",sessionNumberDistinct.get(j));

						Integer inExceptionCount2=0;
						List<String> inExceptionDoctorflowCount2=new ArrayList<>();
						List<Map<String,Object>> mapList2=monthlyReportExtMapper2.searchDocCycleList(map2);
						for(Map<String,Object> mapp2:mapList2){
							String doctorFlow = (String) mapp2.get("doctorFlow");
							Integer count= monthlyReportExtMapper2.getCurrentMonthInExceptionCount(doctorFlow,startDate,endDate);
							if(count==0){
								inExceptionCount2++;
								inExceptionDoctorflowCount2.add(doctorFlow);
							}
						}

						List<SchArrangeResult> arrResultList2  = monthlyReportExtMapper2.searchCycleArrangeResults(map2);//获取排班结果
						List<String> outExceptionDoctorflowCount2=new ArrayList<>();
						List<String> outExamExceptionDoctorflowCount2=new ArrayList<>();
						if(arrResultList2.size() > 0){
							for(SchArrangeResult schr:arrResultList2){
								String content="";
								List<ResSchProcessExpress> resRecList=new ArrayList<>();
								//出科考核表附件（出科异常）
								SchArrangeResult result=schArrangeResultBiz.readSchArrangeResult(schr.getResultFlow());
								SchRotationDept schRotationDept=readStandardRotationDept(schr.getResultFlow());
								if (result != null&&StringUtil.isNotBlank(result.getDoctorFlow())&&schRotationDept!=null&&StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
									ResSchProcessExpress rec = expressBiz.queryResRec(schRotationDept.getRecordFlow(), result.getDoctorFlow(), AfterRecTypeEnum.AfterSummary.getId());
									content = null == rec ? "" : rec.getRecContent();
								}
								//出科考核表（出科考核异常）
								ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(schr.getResultFlow());
								if (resDoctorSchProcess != null) {
									String processFlow = resDoctorSchProcess.getProcessFlow();
									resRecList = expressBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, "AfterEvaluation");
								}
								//出科考核异常
								if(resRecList.size()==0 || null == resRecList){
									if(!outExamExceptionDoctorflowCount2.contains(schr.getDoctorFlow())){
										outExamExceptionDoctorflowCount2.add(schr.getDoctorFlow());
									}
								}
								//出科异常
                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPayCurrentOrg2)) {
									if(resRecList.size()==0 || null == resRecList || StringUtil.isBlank(content)){
										if(!outExceptionDoctorflowCount2.contains(schr.getDoctorFlow())){
											outExceptionDoctorflowCount2.add(schr.getDoctorFlow());
										}
									}
								}else{
									if (StringUtil.isBlank(content)) {
										if(!outExceptionDoctorflowCount2.contains(schr.getDoctorFlow())){
											outExceptionDoctorflowCount2.add(schr.getDoctorFlow());
										}
									}
								}
							}
						}

						doctorLunZhuanExceptionParam3.setId(i + "-" + j+"-"+k);
						doctorLunZhuanExceptionParam3.setPid(doctorLunZhuanExceptionParam2.getId());//
						doctorLunZhuanExceptionParam3.setLevel("3");
						doctorLunZhuanExceptionParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地

						doctorLunZhuanExceptionParam3.setInException(inExceptionCount2);
						doctorLunZhuanExceptionParam3.setInExceptionDoctorFlowList(inExceptionDoctorflowCount2);
						doctorLunZhuanExceptionParam3.setOutException(outExceptionDoctorflowCount2.size());
						doctorLunZhuanExceptionParam3.setOutExceptionDoctorFlowList(outExceptionDoctorflowCount2);
						doctorLunZhuanExceptionParam3.setOutExamException(outExamExceptionDoctorflowCount2.size());
						doctorLunZhuanExceptionParam3.setOutExamExceptionDoctorFlowList(outExamExceptionDoctorflowCount2);

						bList.add(doctorLunZhuanExceptionParam3);
					}
				}

			}
		}
		//}
		if ("isContain".equals(isContain)){
			for(int i=0;i<bList.size();i++){
				if("2".equals(bList.get(i).getLevel())){
					DoctorLunZhuanExceptionParam jj =getChildrenInfoLocalDoctorException("",bList.get(i).getId(),bList);
					Integer inException=  bList.get(i).getInException() + jj.getInException();
					bList.get(i).getInExceptionDoctorFlowList().addAll(jj.getInExceptionDoctorFlowList());

					Integer outException=  bList.get(i).getOutException() + jj.getOutException();
					bList.get(i).getOutExceptionDoctorFlowList().addAll(jj.getOutExceptionDoctorFlowList());
					Integer outExamException=  bList.get(i).getOutExamException() + jj.getOutExamException();
					bList.get(i).getOutExamExceptionDoctorFlowList().addAll(jj.getOutExamExceptionDoctorFlowList());

					bList.get(i).setInException(inException);
					bList.get(i).setInExceptionDoctorFlowList( bList.get(i).getInExceptionDoctorFlowList());
					bList.get(i).setOutException(outException);
					bList.get(i).setOutExceptionDoctorFlowList( bList.get(i).getOutExceptionDoctorFlowList());
					bList.get(i).setOutExamException(outExamException);
					bList.get(i).setOutExamExceptionDoctorFlowList(bList.get(i).getOutExamExceptionDoctorFlowList());//场均参加人次

				}
			}
			for(int i=0;i<bList.size();i++){
				if("1".equals(bList.get(i).getLevel())){
					DoctorLunZhuanExceptionParam jj =getChildrenInfoLocalDoctorException("1",bList.get(i).getId(),bList);
					Integer inException=  bList.get(i).getInException() + jj.getInException();
					bList.get(i).getInExceptionDoctorFlowList().addAll(jj.getInExceptionDoctorFlowList());

					Integer outException=  bList.get(i).getOutException() + jj.getOutException();
					bList.get(i).getOutExceptionDoctorFlowList().addAll(jj.getOutExceptionDoctorFlowList());
					Integer outExamException=  bList.get(i).getOutExamException() + jj.getOutExamException();
					bList.get(i).getOutExamExceptionDoctorFlowList().addAll(jj.getOutExamExceptionDoctorFlowList());

					bList.get(i).setInException(inException);
					bList.get(i).setInExceptionDoctorFlowList( bList.get(i).getInExceptionDoctorFlowList());
					bList.get(i).setOutException(outException);
					bList.get(i).setOutExceptionDoctorFlowList( bList.get(i).getOutExceptionDoctorFlowList());
					bList.get(i).setOutExamException(outExamException);
					bList.get(i).setOutExamExceptionDoctorFlowList(bList.get(i).getOutExamExceptionDoctorFlowList());//场均参加人次

				}
			}
		}
		return bList;
	}
	/**获取每层子辈的信息之和*/
	public DoctorLunZhuanExceptionParam  getChildrenInfoLocalDoctorException(String level ,String id,List<DoctorLunZhuanExceptionParam> blist){
		DoctorLunZhuanExceptionParam jj=new DoctorLunZhuanExceptionParam();
		if("".equals(level)){
			Integer inException=0;
			List<String> inExceptionDoctorFlowList=new ArrayList<>();
			Integer outException=0;
			List<String> outExceptionDoctorFlowList=new ArrayList<>();
			Integer outExamException=0;
			List<String> outExamExceptionDoctorFlowList=new ArrayList<>();
			for(int i=0;i<blist.size();i++){
				if(id.equals(blist.get(i).getPid())){
					inException = inException + blist.get(i).getInException(); //ru科人数
					inExceptionDoctorFlowList.addAll(blist.get(i).getInExceptionDoctorFlowList()) ;//ru科人数doctorid集合
					outException = outException + blist.get(i).getOutException(); //出科人数
					outExceptionDoctorFlowList.addAll(blist.get(i).getOutExceptionDoctorFlowList()) ;//出科人数doctorid集合
					outExamException=outExamException+ blist.get(i).getOutExamException();//出科考核人数
					outExamExceptionDoctorFlowList.addAll(blist.get(i).getOutExamExceptionDoctorFlowList());//出科考核人数doctorid集合
				}
			}
			jj.setInException(inException);
			jj.setInExceptionDoctorFlowList(inExceptionDoctorFlowList);
			jj.setOutException(outException);
			jj.setOutExceptionDoctorFlowList(outExceptionDoctorFlowList);
			jj.setOutExamException(outExamException);
			jj.setOutExamExceptionDoctorFlowList(outExamExceptionDoctorFlowList);
		}else{
			Integer inException=0;
			List<String> inExceptionDoctorFlowList=new ArrayList<>();
			Integer outException=0;
			List<String> outExceptionDoctorFlowList=new ArrayList<>();
			Integer outExamException=0;
			List<String> outExamExceptionDoctorFlowList=new ArrayList<>();
			for(int i=0;i<blist.size();i++){
				Integer inException1=0;
				List<String> inExceptionDoctorFlowList1=new ArrayList<>();
				Integer outException1=0;
				List<String> outExceptionDoctorFlowList1=new ArrayList<>();
				Integer outExamException1=0;
				List<String> outExamExceptionDoctorFlowList1=new ArrayList<>();
				if(id.equals(blist.get(i).getPid())){
					for(int j=0;j<blist.size();j++){
						if(blist.get(i).getId().equals(blist.get(j).getPid())){
							inException1 = inException1 + blist.get(j).getInException(); //ru科人数
							inExceptionDoctorFlowList1.addAll(blist.get(j).getInExceptionDoctorFlowList()) ;//ru科人数doctorid集合

							outException1 = outException1 + blist.get(j).getOutException(); //出科人数
							outExceptionDoctorFlowList1.addAll(blist.get(j).getOutExceptionDoctorFlowList()) ;//出科人数doctorid集合
							outExamException1=outExamException1+ blist.get(j).getOutExamException();//出科考核人数
							outExamExceptionDoctorFlowList1.addAll(blist.get(j).getOutExamExceptionDoctorFlowList());//出科考核人数doctorid集合
						}
					}
				}
				inException = inException+ inException1; //在陪人数
				inExceptionDoctorFlowList.addAll(inExceptionDoctorFlowList1);//场次
				outException = outException+ outException1; //在陪人数
				outExceptionDoctorFlowList.addAll(outExceptionDoctorFlowList1);//场次
				outExamException=outExamException+ outExamException1;//人次
				outExamExceptionDoctorFlowList.addAll(outExamExceptionDoctorFlowList1);//总时长
			}
			jj.setInException(inException);
			jj.setInExceptionDoctorFlowList(inExceptionDoctorFlowList);
			jj.setOutException(outException);
			jj.setOutExceptionDoctorFlowList(outExceptionDoctorFlowList);
			jj.setOutExamException(outExamException);
			jj.setOutExamExceptionDoctorFlowList(outExamExceptionDoctorFlowList);
		}
		return jj;
	}
	/**
	 * 基地----学员轮转异常详细页面
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping("/initLocalDoctorExceptionDetail")
	public  String initLocalDoctorExceptionDetail(Model model,String name,String data[],String[] doctorIds,String monthDate){
		SysUser user = GlobalContext.getCurrentUser();
		List<SysOrg> orgs = new ArrayList<>();
		String role =getLocalRoleFlag();//基地角色
		if(!"".equals(role)){
			SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
				List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				if (orgList != null && orgList.size() > 0) {
					orgs.addAll(orgList);
				}
			}
			model.addAttribute("orgs",orgs);
			model.addAttribute("dataType",data);
			model.addAttribute("name",name);
			model.addAttribute("userListScope",role);
			model.addAttribute("doctorIds",doctorIds);
			model.addAttribute("monthDate",monthDate);
		}
		return "jsres/hospital/localDoctorExceptionDetail";
	}
	/**
	 * 基地---学员轮转异常用户信息详细页面
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping("/localDoctorExceptionUseList")
	public  String localDoctorExceptionUseList(Integer currentPage,HttpServletRequest request,Model model,String orgFlow,
											   String sessionNumber,String[] datas,String name,String[] doctorIds,String monthDate) throws ParseException {
		model.addAttribute("titleName",name);
		Map<String,Object> param =new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		List<String> doctorFlowList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}if (doctorIds != null && doctorIds.length > 0) {
			for (String s : doctorIds) {
				doctorFlowList.add(s);
			}
		}
		if(doctorFlowList.size()>0){
			param.put("doctorFlowList",doctorFlowList);
			param.put("orgFlow", orgFlow);
			param.put("docTypeList", docTypeList);
			param.put("sessionNumber", sessionNumber);

			List<Map<String, Object>> list1 = monthlyReportExtMapper2.searchDocCycleList(param);
			for(Map<String, Object> m:list1){
				String doctorFlow = (String) m.get("doctorFlow");
				List<SchArrangeResult>  schArrResList=monthlyReportExtMapper2.getCurrentMonthInException(doctorFlow);
				if("入科异常".equals(name)){
					if(schArrResList.size()==0 || null==schArrResList){
						m.put("currentSchDept","-");
						m.put("shouldEndDate","-");
						m.put("shouldStartSchDept","-");
						m.put("shouldStartDate","-");
						m.put("reason","没有入科轮转");
					}else{
						SchArrangeResult   schArrangeR=	getZuijinCurrentMonthDate(monthDate,schArrResList);
						if(null==schArrangeR){
							m.put("currentSchDept","-");
							m.put("shouldEndDate","-");
							m.put("shouldStartSchDept","-");
							m.put("shouldStartDate","-");
							m.put("reason","没有入科轮转");
						}else{
							m.put("currentSchDept",schArrangeR.getSchDeptName());
							m.put("shouldEndDate",schArrangeR.getSchEndDate());
							m.put("shouldStartSchDept","-");
							m.put("shouldStartDate",DateUtil.addDate(schArrangeR.getSchEndDate(),1));
							m.put("reason","没有入科轮转");
						}
					}
				}else if("出科异常".equals(name)){
					if(schArrResList.size()==0 || null==schArrResList){
						m.put("currentSchDept","-");
						m.put("shouldEndDate","-");
						m.put("shouldStartSchDept","-");
						m.put("shouldStartDate","-");
						m.put("reason","无出科考核表附件");
					}else{
						SchArrangeResult   schArrangeR=	getZuijinCurrentMonthDate(monthDate,schArrResList);
						if(null==schArrangeR){
							m.put("currentSchDept","-");
							m.put("shouldEndDate","-");
							m.put("shouldStartSchDept","-");
							m.put("shouldStartDate","-");
							m.put("reason","无出科考核表附件");
						}else{
							m.put("currentSchDept",schArrangeR.getSchDeptName());
							m.put("shouldEndDate",schArrangeR.getSchEndDate());
							SchArrangeResult   schArrangeR2= getZuijinChukeMonthDate(schArrangeR.getSchEndDate(),schArrResList);
							if(null==schArrangeR2){
								m.put("shouldStartSchDept","-");
								m.put("shouldStartDate","-");
							}else{
								m.put("shouldStartSchDept",schArrangeR2.getSchDeptName());
								m.put("shouldStartDate",schArrangeR2.getSchStartDate());
							}
							m.put("reason","无出科考核表附件");
						}
					}
				}else{
					if(schArrResList.size()==0 || null==schArrResList){
						m.put("currentSchDept","-");
						m.put("shouldEndDate","-");
						m.put("shouldStartSchDept","-");
						m.put("shouldStartDate","-");
						m.put("reason","无出科考核表");
					}else{
						SchArrangeResult   schArrangeR=	getZuijinCurrentMonthDate(monthDate,schArrResList);
						if(null==schArrangeR){
							m.put("currentSchDept","-");
							m.put("shouldEndDate","-");
							m.put("shouldStartSchDept","-");
							m.put("shouldStartDate","-");
							m.put("reason","无出科考核表");
						}else{
							m.put("currentSchDept",schArrangeR.getSchDeptName());
							m.put("shouldEndDate",schArrangeR.getSchEndDate());
							SchArrangeResult   schArrangeR2= getZuijinChukeMonthDate(schArrangeR.getSchEndDate(),schArrResList);
							if(null==schArrangeR2){
								m.put("shouldStartSchDept","-");
								m.put("shouldStartDate","-");
							}else{
								m.put("shouldStartSchDept",schArrangeR2.getSchDeptName());
								m.put("shouldStartDate",schArrangeR2.getSchStartDate());
							}
							m.put("reason","无出科考核表");
						}
					}
				}
			}
			Page list=pageGetInfo1(currentPage,getPageSize(request),list1);
			model.addAttribute("list",list);
		}
		return "jsres/hospital/localDoctorExceptionUseList";
	}
	/**
	 * 基地---学员轮转异常用户信息详细页面(excel导出)
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping("/exportExcelLocalDoctorException")
	public  void excellocalDoctorException(HttpServletResponse response,String[]  arrayId,HttpServletRequest request,Model model,String orgFlow,
										   String sessionNumber,String[] datas,String name,String[] doctorIds,String monthDate) throws Exception {
		Map<String,Object> param =new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		List<String> doctorFlowList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}if (doctorIds != null && doctorIds.length > 0) {
			for (String s : doctorIds) {
				doctorFlowList.add(s);
			}
		}
		if(doctorFlowList.size()>0){
			param.put("doctorFlowList",doctorFlowList);
			param.put("orgFlow", orgFlow);
			param.put("docTypeList", docTypeList);
			param.put("sessionNumber", sessionNumber);

			List<Map<String, Object>> list1 = monthlyReportExtMapper2.searchDocCycleList(param);
			for(Map<String, Object> m:list1){
				String doctorFlow = (String) m.get("doctorFlow");
				List<SchArrangeResult>  schArrResList=monthlyReportExtMapper2.getCurrentMonthInException(doctorFlow);
				if("入科异常".equals(name)){
					if(schArrResList.size()==0 || null==schArrResList){
						m.put("currentSchDept","-");
						m.put("shouldEndDate","-");
						m.put("shouldStartSchDept","-");
						m.put("shouldStartDate","-");
						m.put("reason","没有入科轮转");
					}else{
						SchArrangeResult   schArrangeR=	getZuijinCurrentMonthDate(monthDate,schArrResList);
						if(null==schArrangeR){
							m.put("currentSchDept","-");
							m.put("shouldEndDate","-");
							m.put("shouldStartSchDept","-");
							m.put("shouldStartDate","-");
							m.put("reason","没有入科轮转");
						}else{
							m.put("currentSchDept",schArrangeR.getSchDeptName());
							m.put("shouldEndDate",schArrangeR.getSchEndDate());
							m.put("shouldStartSchDept","-");
							m.put("shouldStartDate",DateUtil.addDate(schArrangeR.getSchEndDate(),1));
							m.put("reason","没有入科轮转");
						}
					}
				}else if("出科异常".equals(name)){
					if(schArrResList.size()==0 || null==schArrResList){
						m.put("currentSchDept","-");
						m.put("shouldEndDate","-");
						m.put("shouldStartSchDept","-");
						m.put("shouldStartDate","-");
						m.put("reason","无出科考核表附件");
					}else{
						SchArrangeResult   schArrangeR=	getZuijinCurrentMonthDate(monthDate,schArrResList);
						if(null==schArrangeR){
							m.put("currentSchDept","-");
							m.put("shouldEndDate","-");
							m.put("shouldStartSchDept","-");
							m.put("shouldStartDate","-");
							m.put("reason","无出科考核表附件");
						}else{
							m.put("currentSchDept",schArrangeR.getSchDeptName());
							m.put("shouldEndDate",schArrangeR.getSchEndDate());
							SchArrangeResult   schArrangeR2= getZuijinChukeMonthDate(schArrangeR.getSchEndDate(),schArrResList);
							if(null==schArrangeR2){
								m.put("shouldStartSchDept","-");
								m.put("shouldStartDate","-");
							}else{
								m.put("shouldStartSchDept",schArrangeR2.getSchDeptName());
								m.put("shouldStartDate",schArrangeR2.getSchStartDate());
							}
							m.put("reason","无出科考核表附件");
						}
					}
				}else{
					if(schArrResList.size()==0 || null==schArrResList){
						m.put("currentSchDept","-");
						m.put("shouldEndDate","-");
						m.put("shouldStartSchDept","-");
						m.put("shouldStartDate","-");
						m.put("reason","无出科考核表");
					}else{
						SchArrangeResult   schArrangeR=	getZuijinCurrentMonthDate(monthDate,schArrResList);
						if(null==schArrangeR){
							m.put("currentSchDept","-");
							m.put("shouldEndDate","-");
							m.put("shouldStartSchDept","-");
							m.put("shouldStartDate","-");
							m.put("reason","无出科考核表");
						}else{
							m.put("currentSchDept",schArrangeR.getSchDeptName());
							m.put("shouldEndDate",schArrangeR.getSchEndDate());
							SchArrangeResult   schArrangeR2= getZuijinChukeMonthDate(schArrangeR.getSchEndDate(),schArrResList);
							if(null==schArrangeR2){
								m.put("shouldStartSchDept","-");
								m.put("shouldStartDate","-");
							}else{
								m.put("shouldStartSchDept",schArrangeR2.getSchDeptName());
								m.put("shouldStartDate",schArrangeR2.getSchStartDate());
							}
							m.put("reason","无出科考核表");
						}
					}
				}
			}
			if(null!=arrayId){
				List<Map<String, Object>> partlist=new ArrayList<>();
				for(Map<String,Object> map:list1){
					String userflow= (String) map.get("doctorFlow");
					if(Arrays.asList(arrayId).contains(userflow)){
						partlist.add(map);
					}
				}
				list1=partlist;
			}
			String fileName = name+"学员明细.xls";
			String []titles = new String[]{
					"userName:姓名",
					"sexName:性别",
					"userPhone:手机号码",
					"sessionNumber:年级",
					"trainingSpeName:培训专业",
					"tutorName:责任导师",
					"currentSchDept:当前轮转科室",
					"shouldEndDate:应出科日期",
					"shouldStartSchDept:应入科轮转科室",
					"shouldStartDate:应入科日期",
					"reason:"+name+"原因"
			};
			ExcleUtile.exportSimpleExcleByObjs(titles, list1, response.getOutputStream());
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	/**获取入科时间 （最近出科时间 后的时间）*/
	public SchArrangeResult getZuijinChukeMonthDate(String monthDate,List<SchArrangeResult>  schArrResList) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date chukeDate =sdf.parse(monthDate);

		List<SchArrangeResult> li=new ArrayList<>();
		for(SchArrangeResult sar : schArrResList){
			SchArrangeResult s=new SchArrangeResult();
			Date startDate =sdf.parse(sar.getSchStartDate());
			Long diff = startDate.getTime()-chukeDate.getTime() ;//这样得到的差值是毫秒级别
			if(diff>=0){
				s.setTimecha(diff);
				s.setSchDeptName(sar.getSchDeptName());
				s.setSchStartDate(sar.getSchStartDate());
				li.add(s);
			}
		}
		Collections.sort(li, new Comparator<SchArrangeResult>() {
			public int compare(SchArrangeResult o1, SchArrangeResult o2) {
				Long	teachActiveSessionSum1 = o1.getTimecha();
				Long	teachActiveSessionSum2 = o2.getTimecha();
				//升序
				return teachActiveSessionSum1.compareTo(teachActiveSessionSum2);
			}
		});
		if(li.size()==0 || null==li){
			return null;
		}else{
			return li.get(0);
		}
	}
	/**获取出科时间（最近当前月 前的时间）  */
	public SchArrangeResult  getZuijinCurrentMonthDate(String monthDate,List<SchArrangeResult>  schArrResList) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ymd= DateUtil.setLastDateOfMonth(monthDate);//年-月-最后一天
		Date currnetDate =sdf.parse(ymd);
		List<SchArrangeResult> li=new ArrayList<>();
		for(SchArrangeResult sar : schArrResList){
			SchArrangeResult s=new SchArrangeResult();
			Date endDate =sdf.parse(sar.getSchEndDate());
			Long diff = currnetDate.getTime() - endDate.getTime();//这样得到的差值是毫秒级别
			if(diff>=0){
				s.setTimecha(diff);
				s.setSchDeptName(sar.getSchDeptName());
				s.setSchEndDate(sar.getSchEndDate());
				li.add(s);
			}
		}
		Collections.sort(li, new Comparator<SchArrangeResult>() {
			public int compare(SchArrangeResult o1, SchArrangeResult o2) {
				Long	teachActiveSessionSum1  =o1.getTimecha();
				Long	teachActiveSessionSum2 = o2.getTimecha();
				//升序
				return teachActiveSessionSum1.compareTo(teachActiveSessionSum2);
			}
		});
		if(li.size()==0 || null==li){
			return null;
		}else{
			return li.get(0);
		}
	}
	public Page pageGetInfo1(Integer pageNumber,Integer pageSize,List<Map<String,Object>> list){
		Page MemberArticleBeanPage=new Page(pageNumber,pageSize,list.size());
		int totalPage = list.size() / pageSize + ((list.size() % pageSize == 0) ? 0 : 1);
		MemberArticleBeanPage.setPages(totalPage);
		/*List<Map<String,String>> MemberArticleBeanPage = new ArrayList<>();*/
		int currIdx = (pageNumber > 1 ? (pageNumber -1) * pageSize : 0);
		for (int i = 0; i < pageSize && i < list.size() - currIdx; i++) {
			Map<String,Object> memberArticleBean = list.get(currIdx + i);
			memberArticleBean.put("ROW_ID",(i+1)+"");
			MemberArticleBeanPage.add(memberArticleBean);
		}
		return MemberArticleBeanPage;
	}
	/***********************************************************************************************
	 * pageTOlocalJiaoxueActive
	 *
	 * @return
	 */
	@RequestMapping("/localJiaoxueActive")
	public String PageTolocalJiaoxueActive(Model model) {
		return "jsres/hospital/localJiaoxueActive";
	}
	/**
	 * 基地----初始化教学情况统计
	 *
	 * @param monthDate
	 * @param isContain
	 * @return
	 */
	@RequestMapping("/initJiaoxueActive")
	@ResponseBody
	public List initJiaoxueActive(String sortFlag, String monthDate, String isContain, String[] datas) throws ParseException {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

		List<JiaoxueActivePojoParam> bList = new ArrayList<>();
		List<SysOrg> joinorgList = new ArrayList<>();
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg currOrg = orgBiz.readSysOrg(user.getOrgFlow());
		//orgs.add(currOrg);//获取机构和协同机构
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currOrg.getOrgLevelId())) {
			joinorgList = orgBiz.searchJointOrgsByOrg(currOrg.getOrgFlow());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dictTypeId", com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		List<SysDict> doctorTrainingSpeList = this.resDoctorRecruitExtMapper.searchTrainSpeList(paramMap);//所有专业
		String[] doctype = new String[4];
		if (datas.length == 2) {
			doctype[0] = "Company";
			doctype[1] = "CompanyEntrust";
			doctype[2] = "Social";
			doctype[3] = "Graduate";
		} else {
			if (datas[0].equals("NotGraduate")) {
				doctype[0] = "Company";
				doctype[1] = "CompanyEntrust";
				doctype[2] = "Social";
			} else {
				doctype[0] = "Graduate";
			}
		}

		//if ("isContain".equals(isContain)) {

		//	} else {
		for (int i = 0; i < doctorTrainingSpeList.size(); i++) {
			JiaoxueActivePojoParam jiaoxueActivePojoParam = new JiaoxueActivePojoParam();
			Map<String, Object> spemap = new HashMap<>();
			spemap.put("orgFlow", currOrg.getOrgFlow());
			spemap.put("docTypeList", doctype);
			spemap.put("speId", doctorTrainingSpeList.get(i).getDictId());
			List<JsResDoctorRecruitExt> zaipeiList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
			//TODO
			Map<String, Object> mymap = new HashMap<>();
			mymap.put("orgFlow", currOrg.getOrgFlow());
			Map<String, Object> manzuTiaojianMap = new HashMap<>();
			manzuTiaojianMap.put("orgFlow", currOrg.getOrgFlow());
			manzuTiaojianMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
			manzuTiaojianMap.put("docTypeList", doctype);

			String month=monthDate.split("-")[0];
			String date=monthDate.split("-")[1];
			manzuTiaojianMap.put("monthDate", month+date);
			List<JiaoxueActiveAndResultParam>  activeAndResultParamList =monthlyReportExtMapper2.findActivityInfoAndActivityResultByTiaojian(manzuTiaojianMap);

			//List<Map<String, Object>> activeInfoList = monthlyReportExtMapper2.findActivityListyuh(mymap);
			Integer changci = 0;//场次
			Integer alljoin = 0;//总参加人次
			Double shichangTotal = 0.00;//总时长
			changci=  activeAndResultParamList.size();
			for(JiaoxueActiveAndResultParam jar:activeAndResultParamList){
				alljoin=  alljoin+   jar.getResultList().size();
				List<TeachingActivityResult> resultsList =jar.getResultList();
				String yearMonthshichang = maxminTime(resultsList);
				if ("".equals(yearMonthshichang)) {
					throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
				}
				if (!"0".equals(yearMonthshichang)) {
					String yearmonth = yearMonthshichang.split(",")[0];
					String shichang = yearMonthshichang.split(",")[1];
					shichangTotal = shichangTotal + Double.parseDouble(shichang);
				}
			}

				/*for (Map<String, Object> li : activeInfoList) {
					String activityFlow = (String) li.get("activityFlow");
					String scanNum = ((BigDecimal) li.get("scanNum")).toString(); //签字个数
					if (!"0".equals(scanNum)) {
						manzuTiaojianMap.put("activityFlow",activityFlow);
						List<TeachingActivityResult> teachingActivityResultListNew = monthlyReportExtMapper2.getScanTime1AndScanTime2LeftjoinUserFlow(manzuTiaojianMap);

						String yearMonthshichang = maxminTime(teachingActivityResultListNew);
						if ("".equals(yearMonthshichang)) {
							throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
						}
						if (!"0".equals(yearMonthshichang)) {
							String yearmonth = yearMonthshichang.split(",")[0];
							String shichang = yearMonthshichang.split(",")[1];
							if (monthDate.equals(yearmonth)) {
								if (teachingActivityResultListNew.size() > 0) {
									changci++;
								}
								alljoin = alljoin + teachingActivityResultListNew.size();
								shichangTotal = shichangTotal + Double.parseDouble(shichang);
							}
						}

					}
				}*/
			jiaoxueActivePojoParam.setTeachActiveSessionSum(changci);// 教学活动举办场次
			jiaoxueActivePojoParam.setAllJoinSum(alljoin); //总参加人次
			BigDecimal a = new BigDecimal(alljoin);
			BigDecimal b = new BigDecimal(changci);
			Double cdValue = 0.0;
			if (!changci.equals(0)) {
				BigDecimal c = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
				cdValue = c.doubleValue();
			}
			jiaoxueActivePojoParam.setAverJoinSum(cdValue);//场均参加人次

			BigDecimal sct = new BigDecimal(shichangTotal);//总时长
			Double averSctValue = 0.0;
			if (!changci.equals(0)) {
				BigDecimal averSct = sct.divide(b, 2, BigDecimal.ROUND_HALF_UP);
				averSctValue = averSct.doubleValue();
			}
			//BigDecimal averSct= sct.divide(b);
			jiaoxueActivePojoParam.setAverDureTime(averSctValue);//场均时长（分钟）
			jiaoxueActivePojoParam.setDureTime(shichangTotal);

			jiaoxueActivePojoParam.setId(i + "");
			jiaoxueActivePojoParam.setPid("");
			jiaoxueActivePojoParam.setLevel("1");
			jiaoxueActivePojoParam.setTrainerSum(zaipeiList.size() + "");
			jiaoxueActivePojoParam.setSpeId(doctorTrainingSpeList.get(i).getDictId());
			jiaoxueActivePojoParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
			bList.add(jiaoxueActivePojoParam);

			/**年级*/
				/*Map<String, Object> sessionNumbermap = new HashMap<>();
				List<String> sessionNumberDistinct = new ArrayList<>();
				sessionNumbermap.put("orgFlow", currOrg.getOrgFlow());
				sessionNumbermap.put("docTypeList", doctype);
				sessionNumbermap.put("speId", doctorTrainingSpeList.get(i).getDictId());
				List<JsResDoctorRecruitExt> sessionNUmberList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
				for (JsResDoctorRecruitExt jrs : sessionNUmberList) {
					if (!sessionNumberDistinct.contains(jrs.getSessionNumber())) {
						sessionNumberDistinct.add(jrs.getSessionNumber());
					}
				}
				for (int j = 0; j < sessionNumberDistinct.size(); j++) {
					JiaoxueActivePojoParam jiaoxueActivePojoParam2 = new JiaoxueActivePojoParam();
					Map<String, Object> sessionMap = new HashMap<>();
					sessionMap.put("orgFlow", currOrg.getOrgFlow());
					sessionMap.put("docTypeList", doctype);
					sessionMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
					sessionMap.put("sessionNumber", sessionNumberDistinct.get(j));
					List<JsResDoctorRecruitExt> sessionNUmberZaipeiList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap);
					//TODO
					Map<String, Object> mymap1 = new HashMap<>();
					mymap1.put("orgFlow", currOrg.getOrgFlow());
					Map<String, Object> manzuTiaojianMap1 = new HashMap<>();
					manzuTiaojianMap1.put("orgFlow", currOrg.getOrgFlow());
					manzuTiaojianMap1.put("speId", doctorTrainingSpeList.get(i).getDictId());
					manzuTiaojianMap1.put("docTypeList", doctype);
					manzuTiaojianMap1.put("sessionNumber", sessionNumberDistinct.get(j));
					List<Map<String, Object>> activeInfoList1 = monthlyReportExtMapper2.findActivityListyuh(mymap1);
					Integer changci1 = 0;//场次
					Integer alljoin1 = 0;//总参加人次
					Double shichangTotal1 = 0.00;//总时长
					for (Map<String, Object> li : activeInfoList1) {
						String activityFlow = (String) li.get("activityFlow");
						String scanNum = ((BigDecimal) li.get("scanNum")).toString(); //签字个数
						if (!"0".equals(scanNum)) {
							List<TeachingActivityResult> teachingActivityResultList = monthlyReportExtMapper2.getScanTime1AndScanTime2(activityFlow);
							List<TeachingActivityResult> teachingActivityResultListNew = new ArrayList<>();
							for (TeachingActivityResult tt : teachingActivityResultList) {
								manzuTiaojianMap1.put("userFlow", tt.getUserFlow());
								List<DoctorInfoParam> doli = monthlyReportExtMapper2.findManzuTiaojianDoctor(manzuTiaojianMap1);
								if (doli.size() > 0) {
									teachingActivityResultListNew.add(tt);
								}
							}

							String yearMonthshichang = maxminTime(teachingActivityResultListNew);
							if ("".equals(yearMonthshichang)) {
								throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
							}
							if (!"0".equals(yearMonthshichang)) {
								String yearmonth = yearMonthshichang.split(",")[0];
								String shichang = yearMonthshichang.split(",")[1];
								if (monthDate.equals(yearmonth)) {
									if (teachingActivityResultListNew.size() > 0) {
										changci1++;
									}
									alljoin1 = alljoin1 + teachingActivityResultListNew.size();
									shichangTotal1 = shichangTotal1 + Double.parseDouble(shichang);
								}
							}

						}
					}
					jiaoxueActivePojoParam2.setTeachActiveSessionSum(changci1);// 教学活动举办场次
					jiaoxueActivePojoParam2.setAllJoinSum(alljoin1); //总参加人次
					BigDecimal a1 = new BigDecimal(alljoin1);
					BigDecimal b1 = new BigDecimal(changci1);
					Double cdValue1 = 0.0;
					if (!changci1.equals(0)) {
						BigDecimal c = a1.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
						cdValue1 = c.doubleValue();
					}
					jiaoxueActivePojoParam2.setAverJoinSum(cdValue1);//场均参加人次

					BigDecimal sct1 = new BigDecimal(shichangTotal1);//总时长
					Double averSctValue1 = 0.0;
					if (!changci1.equals(0)) {
						BigDecimal averSct = sct1.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
						averSctValue1 = averSct.doubleValue();
					}
					//BigDecimal averSct= sct.divide(b);
					jiaoxueActivePojoParam2.setAverDureTime(averSctValue1);//场均时长（分钟）
					jiaoxueActivePojoParam2.setDureTime(shichangTotal1);

					jiaoxueActivePojoParam2.setId(i + "-" + j);
					jiaoxueActivePojoParam2.setPid(jiaoxueActivePojoParam.getId());//
					jiaoxueActivePojoParam2.setLevel("2");
					jiaoxueActivePojoParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
					jiaoxueActivePojoParam2.setTrainerSum(sessionNUmberZaipeiList.size() + "");


					bList.add(jiaoxueActivePojoParam2);
					if (joinorgList.size() > 0) {
						for (int k = 0; k < joinorgList.size(); k++) {
							JiaoxueActivePojoParam jiaoxueActivePojoParam3 = new JiaoxueActivePojoParam();
							Map<String, Object> joinOrgMap = new HashMap<>();
							joinOrgMap.put("orgFlow", joinorgList.get(k).getOrgFlow());
							joinOrgMap.put("docTypeList", doctype);
							joinOrgMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
							joinOrgMap.put("sessionNumber", sessionNumberDistinct.get(j));
							List<JsResDoctorRecruitExt> joinorgZaipeiList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap);
							//TODO
							Map<String, Object> mymap2 = new HashMap<>();
							mymap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
							Map<String, Object> manzuTiaojianMap2 = new HashMap<>();
							manzuTiaojianMap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
							manzuTiaojianMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
							manzuTiaojianMap2.put("docTypeList", doctype);
							manzuTiaojianMap2.put("sessionNumber", sessionNumberDistinct.get(j));
							List<Map<String, Object>> activeInfoList2 = monthlyReportExtMapper2.findActivityListyuh(mymap2);
							Integer changci2 = 0;//场次
							Integer alljoin2 = 0;//总参加人次
							Double shichangTotal2 = 0.00;//总时长
							for (Map<String, Object> li : activeInfoList2) {
								String activityFlow = (String) li.get("activityFlow");
								String scanNum = ((BigDecimal) li.get("scanNum")).toString(); //签字个数
								if (!"0".equals(scanNum)) {
									List<TeachingActivityResult> teachingActivityResultList = monthlyReportExtMapper2.getScanTime1AndScanTime2(activityFlow);
									List<TeachingActivityResult> teachingActivityResultListNew = new ArrayList<>();
									for (TeachingActivityResult tt : teachingActivityResultList) {
										manzuTiaojianMap2.put("userFlow", tt.getUserFlow());
										List<DoctorInfoParam> doli = monthlyReportExtMapper2.findManzuTiaojianDoctor(manzuTiaojianMap2);
										if (doli.size() > 0) {
											teachingActivityResultListNew.add(tt);
										}
									}

									String yearMonthshichang = maxminTime(teachingActivityResultListNew);
									if ("".equals(yearMonthshichang)) {
										throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
									}
									if (!"0".equals(yearMonthshichang)) {
										String yearmonth = yearMonthshichang.split(",")[0];
										String shichang = yearMonthshichang.split(",")[1];
										if (monthDate.equals(yearmonth)) {
											if (teachingActivityResultListNew.size() > 0) {
												changci2++;
											}
											alljoin2 = alljoin2 + teachingActivityResultListNew.size();
											shichangTotal2 = shichangTotal2 + Double.parseDouble(shichang);
										}
									}

								}
							}
							jiaoxueActivePojoParam3.setTeachActiveSessionSum(changci2);// 教学活动举办场次
							jiaoxueActivePojoParam3.setAllJoinSum(alljoin2); //总参加人次
							BigDecimal a2 = new BigDecimal(alljoin2);
							BigDecimal b2 = new BigDecimal(changci2);
							Double cdValue2 = 0.0;
							if (!changci2.equals(0)) {
								BigDecimal c = a2.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
								cdValue2 = c.doubleValue();
							}
							jiaoxueActivePojoParam3.setAverJoinSum(cdValue2);//场均参加人次

							BigDecimal sct2 = new BigDecimal(shichangTotal2);//总时长
							Double averSctValue2 = 0.0;
							if (!changci2.equals(0)) {
								BigDecimal averSct = sct2.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
								averSctValue2 = averSct.doubleValue();
							}
							//BigDecimal averSct= sct.divide(b);
							jiaoxueActivePojoParam3.setAverDureTime(averSctValue2);//场均时长（分钟）
							jiaoxueActivePojoParam3.setDureTime(shichangTotal2);


							jiaoxueActivePojoParam3.setId(i + "-" + j + "-" + k);
							jiaoxueActivePojoParam3.setPid(jiaoxueActivePojoParam2.getId());//
							jiaoxueActivePojoParam3.setLevel("3");
							jiaoxueActivePojoParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地
							jiaoxueActivePojoParam3.setTrainerSum(joinorgZaipeiList.size() + "");

							bList.add(jiaoxueActivePojoParam3);
						}
					}

				}*/
		}
		bList=	sessionNumberSecond(currOrg.getOrgFlow(), doctype, monthDate, bList,joinorgList);/**年级*/
		//如果包含协同
		if("isContain".equals(isContain)){
			for(int i=0;i<bList.size();i++){
				if("2".equals(bList.get(i).getLevel())){
					JiaoxueActivePojoParam jj =getChildrenInfo("",bList.get(i).getId(),bList);
					Integer trainsumAdd=  Integer.parseInt(bList.get(i).getTrainerSum()) + Integer.parseInt(jj.getTrainerSum());
					Integer teachActiveSessionSumAdd=  bList.get(i).getTeachActiveSessionSum() + jj.getTeachActiveSessionSum();
					Integer allJoinsumAdd=  bList.get(i).getAllJoinSum() + jj.getAllJoinSum();
					Double  dureTimeAdd=  bList.get(i).getDureTime() + jj.getDureTime();

					bList.get(i).setTrainerSum(trainsumAdd+"");
					bList.get(i).setTeachActiveSessionSum(teachActiveSessionSumAdd);
					bList.get(i).setAllJoinSum(allJoinsumAdd);

					BigDecimal a2 = new BigDecimal(allJoinsumAdd);
					BigDecimal b2 = new BigDecimal(teachActiveSessionSumAdd);
					Double cdValue2 = 0.0;
					if (!teachActiveSessionSumAdd.equals(0)) {
						BigDecimal c = a2.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
						cdValue2 = c.doubleValue();
					}
					bList.get(i).setAverJoinSum(cdValue2);//场均参加人次

					BigDecimal sct2 = new BigDecimal(dureTimeAdd);//总时长
					Double averSctValue2 = 0.0;
					if (!teachActiveSessionSumAdd.equals(0)) {
						BigDecimal averSct = sct2.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
						averSctValue2 = averSct.doubleValue();
					}
					bList.get(i).setAverDureTime(averSctValue2);//场均时长（分钟）
					bList.get(i).setDureTime(dureTimeAdd);
				}
			}
			for(int i=0;i<bList.size();i++){
				if("1".equals(bList.get(i).getLevel())){
					JiaoxueActivePojoParam jj =getChildrenInfo("1",bList.get(i).getId(),bList);
					Integer trainsumAdd=  Integer.parseInt(bList.get(i).getTrainerSum()) + Integer.parseInt(jj.getTrainerSum());
					Integer teachActiveSessionSumAdd=  bList.get(i).getTeachActiveSessionSum() + jj.getTeachActiveSessionSum();
					Integer allJoinsumAdd=  bList.get(i).getAllJoinSum() + jj.getAllJoinSum();
					Double  dureTimeAdd=  bList.get(i).getDureTime() + jj.getDureTime();

					bList.get(i).setTrainerSum(trainsumAdd+"");
					bList.get(i).setTeachActiveSessionSum(teachActiveSessionSumAdd);
					bList.get(i).setAllJoinSum(allJoinsumAdd);

					BigDecimal a2 = new BigDecimal(allJoinsumAdd);
					BigDecimal b2 = new BigDecimal(teachActiveSessionSumAdd);
					Double cdValue2 = 0.0;
					if (!teachActiveSessionSumAdd.equals(0)) {
						BigDecimal c = a2.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
						cdValue2 = c.doubleValue();
					}
					bList.get(i).setAverJoinSum(cdValue2);//场均参加人次

					BigDecimal sct2 = new BigDecimal(dureTimeAdd);//总时长
					Double averSctValue2 = 0.0;
					if (!teachActiveSessionSumAdd.equals(0)) {
						BigDecimal averSct = sct2.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
						averSctValue2 = averSct.doubleValue();
					}
					bList.get(i).setAverDureTime(averSctValue2);//场均时长（分钟）
					bList.get(i).setDureTime(dureTimeAdd);
				}
			}

		}
		//}
		bList = teachActiveSort(sortFlag, bList);//排序
		return bList;
	}
	/**获取每层子辈的信息之和*/
	public JiaoxueActivePojoParam  getChildrenInfo(String level ,String id,List<JiaoxueActivePojoParam> blist){
		JiaoxueActivePojoParam jj=new JiaoxueActivePojoParam();
		if("".equals(level)){
			Integer trainSum=0;
			Integer changci=0;
			Integer renci=0;
			Double zongshichang=0.00;
			for(int i=0;i<blist.size();i++){
				if(id.equals(blist.get(i).getPid())){
					trainSum = trainSum+ Integer.parseInt(blist.get(i).getTrainerSum()); //在陪人数
					changci=changci+ blist.get(i).getTeachActiveSessionSum();//场次
					renci=renci+ blist.get(i).getAllJoinSum();//人次
					zongshichang=zongshichang+blist.get(i).getDureTime();//总时长
				}
			}
			jj.setTrainerSum(trainSum+"");
			jj.setTeachActiveSessionSum(changci);
			jj.setAllJoinSum(renci);
			jj.setDureTime(zongshichang);
		}else{
			Integer trainSum=0;
			Integer changci=0;
			Integer renci=0;
			Double zongshichang=0.00;
			List<JiaoxueActivePojoParam> tempLIst=new ArrayList<>();
			for(int i=0;i<blist.size();i++){
				Integer trainSum1=0;
				Integer changci1=0;
				Integer renci1=0;
				Double zongshichang1=0.00;
				if(id.equals(blist.get(i).getPid())){
					for(int j=0;j<blist.size();j++){
						if(blist.get(i).getId().equals(blist.get(j).getPid())){
							trainSum1 = trainSum1+ Integer.parseInt(blist.get(j).getTrainerSum()); //在陪人数
							changci1=changci1+ blist.get(j).getTeachActiveSessionSum();//场次
							renci1=renci1+ blist.get(j).getAllJoinSum();//人次
							zongshichang1=zongshichang1+blist.get(j).getDureTime();//总时长
						}
					}
				}
				trainSum = trainSum+ trainSum1; //在陪人数
				changci=changci+ changci1;//场次
				renci=renci+ renci1;//人次
				zongshichang=zongshichang+zongshichang1;//总时长
			}
			jj.setTrainerSum(trainSum+"");
			jj.setTeachActiveSessionSum(changci);
			jj.setAllJoinSum(renci);
			jj.setDureTime(zongshichang);
		}
		return jj;
	}

	public List<JiaoxueActivePojoParam> sessionNumberSecond(String orgFlow, String[] doctype, String monthDate, List<JiaoxueActivePojoParam> bList,List<SysOrg> joinOrgList) throws ParseException {
		List<JiaoxueActivePojoParam> sessionNumberList = new ArrayList<>();
		for (int i = 0; i < bList.size(); i++) {
			Map<String, Object> sessionNumbermap = new HashMap<>();
			List<String> sessionNumberDistinct = new ArrayList<>();
			sessionNumbermap.put("orgFlow", orgFlow);
			sessionNumbermap.put("docTypeList", doctype);
			sessionNumbermap.put("speId", bList.get(i).getSpeId());
			List<JsResDoctorRecruitExt> sessionNUmberList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
			for (JsResDoctorRecruitExt jrs : sessionNUmberList) {
				if (!sessionNumberDistinct.contains(jrs.getSessionNumber())) {
					sessionNumberDistinct.add(jrs.getSessionNumber());
				}
			}
			for (int j = 0; j < sessionNumberDistinct.size(); j++) {
				JiaoxueActivePojoParam jiaoxueActivePojoParam2 = new JiaoxueActivePojoParam();
				Map<String, Object> sessionMap = new HashMap<>();
				sessionMap.put("orgFlow", orgFlow);
				sessionMap.put("docTypeList", doctype);
				sessionMap.put("speId", bList.get(i).getSpeId());
				sessionMap.put("sessionNumber", sessionNumberDistinct.get(j));
				List<JsResDoctorRecruitExt> sessionNUmberZaipeiList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap);
				//TODO
				Map<String, Object> mymap1 = new HashMap<>();
				mymap1.put("orgFlow", orgFlow);
				Map<String, Object> manzuTiaojianMap1 = new HashMap<>();
				manzuTiaojianMap1.put("orgFlow", orgFlow);
				manzuTiaojianMap1.put("speId",  bList.get(i).getSpeId());
				manzuTiaojianMap1.put("docTypeList", doctype);
				manzuTiaojianMap1.put("sessionNumber", sessionNumberDistinct.get(j));

				String month=monthDate.split("-")[0];
				String date=monthDate.split("-")[1];
				manzuTiaojianMap1.put("monthDate", month+date);
				List<JiaoxueActiveAndResultParam>  activeAndResultParamList1 =monthlyReportExtMapper2.findActivityInfoAndActivityResultByTiaojian(manzuTiaojianMap1);

				//List<Map<String, Object>> activeInfoList1 = monthlyReportExtMapper2.findActivityListyuh(mymap1);
				Integer changci1 = 0;//场次
				Integer alljoin1 = 0;//总参加人次
				Double shichangTotal1 = 0.00;//总时长
				changci1= activeAndResultParamList1.size();
				for(JiaoxueActiveAndResultParam jar:activeAndResultParamList1){
					alljoin1=alljoin1+jar.getResultList().size();
					List<TeachingActivityResult> resultsList = jar.getResultList();
					String yearMonthshichang = maxminTime(resultsList);
					if ("".equals(yearMonthshichang)) {
						throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
					}
					if (!"0".equals(yearMonthshichang)) {
						String yearmonth = yearMonthshichang.split(",")[0];
						String shichang = yearMonthshichang.split(",")[1];
						shichangTotal1 = shichangTotal1 + Double.parseDouble(shichang);
					}
				}
				/*for (Map<String, Object> li : activeInfoList1) {
					String activityFlow = (String) li.get("activityFlow");
					String scanNum = ((BigDecimal) li.get("scanNum")).toString(); //签字个数
					if (!"0".equals(scanNum)) {
						manzuTiaojianMap1.put("activityFlow",activityFlow);
						List<TeachingActivityResult> teachingActivityResultListNew = monthlyReportExtMapper2.getScanTime1AndScanTime2LeftjoinUserFlow(manzuTiaojianMap1);

						String yearMonthshichang = maxminTime(teachingActivityResultListNew);
						if ("".equals(yearMonthshichang)) {
							throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
						}
						if (!"0".equals(yearMonthshichang)) {
							String yearmonth = yearMonthshichang.split(",")[0];
							String shichang = yearMonthshichang.split(",")[1];
							if (monthDate.equals(yearmonth)) {
								if (teachingActivityResultListNew.size() > 0) {
									changci1++;
								}
								alljoin1 = alljoin1 + teachingActivityResultListNew.size();
								shichangTotal1 = shichangTotal1 + Double.parseDouble(shichang);
							}
						}

					}
				}*/
				jiaoxueActivePojoParam2.setTeachActiveSessionSum(changci1);// 教学活动举办场次
				jiaoxueActivePojoParam2.setAllJoinSum(alljoin1); //总参加人次
				BigDecimal a1 = new BigDecimal(alljoin1);
				BigDecimal b1 = new BigDecimal(changci1);
				Double cdValue1 = 0.0;
				if (!changci1.equals(0)) {
					BigDecimal c = a1.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
					cdValue1 = c.doubleValue();
				}
				jiaoxueActivePojoParam2.setAverJoinSum(cdValue1);//场均参加人次

				BigDecimal sct1 = new BigDecimal(shichangTotal1);//总时长
				Double averSctValue1 = 0.0;
				if (!changci1.equals(0)) {
					BigDecimal averSct = sct1.divide(b1, 2, BigDecimal.ROUND_HALF_UP);
					averSctValue1 = averSct.doubleValue();
				}
				//BigDecimal averSct= sct.divide(b);
				jiaoxueActivePojoParam2.setAverDureTime(averSctValue1);//场均时长（分钟）
				jiaoxueActivePojoParam2.setDureTime(shichangTotal1);

				jiaoxueActivePojoParam2.setId(i + "-" + j);
				jiaoxueActivePojoParam2.setPid(bList.get(i).getId());//
				jiaoxueActivePojoParam2.setLevel("2");
				jiaoxueActivePojoParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
				jiaoxueActivePojoParam2.setSpeId(bList.get(i).getSpeId());//存sessionNumber
				jiaoxueActivePojoParam2.setTrainerSum(sessionNUmberZaipeiList.size() + "");
				sessionNumberList.add(jiaoxueActivePojoParam2);
			}
		}
		bList.addAll(sessionNumberList);
		bList =joinOrgTrird(orgFlow, doctype, monthDate,bList,joinOrgList);
		return bList;
	}
	public List<JiaoxueActivePojoParam> joinOrgTrird(String orgFlow, String[] doctype,String monthDate,List<JiaoxueActivePojoParam> bList,List<SysOrg> joinorgList) throws ParseException {
		List<JiaoxueActivePojoParam> joinList = new ArrayList<>();
		for(int p=0;p<bList.size();p++){
			if(bList.get(p).getLevel().equals("2")){
				if (joinorgList.size() > 0) {
					for (int k = 0; k < joinorgList.size(); k++) {
						JiaoxueActivePojoParam jiaoxueActivePojoParam3 = new JiaoxueActivePojoParam();
						Map<String, Object> joinOrgMap = new HashMap<>();
						joinOrgMap.put("orgFlow", joinorgList.get(k).getOrgFlow());
						joinOrgMap.put("docTypeList", doctype);
						joinOrgMap.put("speId", bList.get(p).getSpeId());
						joinOrgMap.put("sessionNumber", bList.get(p).getSpeName());
						List<JsResDoctorRecruitExt> joinorgZaipeiList = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap);
						//TODO
						Map<String, Object> mymap2 = new HashMap<>();
						mymap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
						Map<String, Object> manzuTiaojianMap2 = new HashMap<>();
						manzuTiaojianMap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
						manzuTiaojianMap2.put("speId", bList.get(p).getSpeId());
						manzuTiaojianMap2.put("docTypeList", doctype);
						manzuTiaojianMap2.put("sessionNumber",bList.get(p).getSpeName());

						String month=monthDate.split("-")[0];
						String date=monthDate.split("-")[1];
						manzuTiaojianMap2.put("monthDate", month+date);
						List<JiaoxueActiveAndResultParam>  activeAndResultParamList2 =monthlyReportExtMapper2.findActivityInfoAndActivityResultByTiaojian(manzuTiaojianMap2);


						//List<Map<String, Object>> activeInfoList2 = monthlyReportExtMapper2.findActivityListyuh(mymap2);
						Integer changci2 = 0;//场次
						Integer alljoin2 = 0;//总参加人次
						Double shichangTotal2 = 0.00;//总时长
						changci2=activeAndResultParamList2.size();
						for(JiaoxueActiveAndResultParam jar:activeAndResultParamList2){
							alljoin2=alljoin2+jar.getResultList().size();
							List<TeachingActivityResult> resultsList = jar.getResultList();
							String yearMonthshichang = maxminTime(resultsList);
							if ("".equals(yearMonthshichang)) {
								throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
							}
							if (!"0".equals(yearMonthshichang)) {
								String yearmonth = yearMonthshichang.split(",")[0];
								String shichang = yearMonthshichang.split(",")[1];
								shichangTotal2 = shichangTotal2 + Double.parseDouble(shichang);
							}
						}
						/*for (Map<String, Object> li : activeInfoList2) {
							String activityFlow = (String) li.get("activityFlow");
							String scanNum = ((BigDecimal) li.get("scanNum")).toString(); //签字个数
							if (!"0".equals(scanNum)) {
								manzuTiaojianMap2.put("activityFlow", activityFlow);
								List<TeachingActivityResult> teachingActivityResultListNew = monthlyReportExtMapper2.getScanTime1AndScanTime2LeftjoinUserFlow(manzuTiaojianMap2);

								String yearMonthshichang = maxminTime(teachingActivityResultListNew);
								if ("".equals(yearMonthshichang)) {
									throw new RuntimeException("同一个教学活动签到和签退不能跨年月！");
								}
								if (!"0".equals(yearMonthshichang)) {
									String yearmonth = yearMonthshichang.split(",")[0];
									String shichang = yearMonthshichang.split(",")[1];
									if (monthDate.equals(yearmonth)) {
										if (teachingActivityResultListNew.size() > 0) {
											changci2++;
										}
										alljoin2 = alljoin2 + teachingActivityResultListNew.size();
										shichangTotal2 = shichangTotal2 + Double.parseDouble(shichang);
									}
								}

							}
						}*/
						jiaoxueActivePojoParam3.setTeachActiveSessionSum(changci2);// 教学活动举办场次
						jiaoxueActivePojoParam3.setAllJoinSum(alljoin2); //总参加人次
						BigDecimal a2 = new BigDecimal(alljoin2);
						BigDecimal b2 = new BigDecimal(changci2);
						Double cdValue2 = 0.0;
						if (!changci2.equals(0)) {
							BigDecimal c = a2.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
							cdValue2 = c.doubleValue();
						}
						jiaoxueActivePojoParam3.setAverJoinSum(cdValue2);//场均参加人次

						BigDecimal sct2 = new BigDecimal(shichangTotal2);//总时长
						Double averSctValue2 = 0.0;
						if (!changci2.equals(0)) {
							BigDecimal averSct = sct2.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
							averSctValue2 = averSct.doubleValue();
						}
						//BigDecimal averSct= sct.divide(b);
						jiaoxueActivePojoParam3.setAverDureTime(averSctValue2);//场均时长（分钟）
						jiaoxueActivePojoParam3.setDureTime(shichangTotal2);


						jiaoxueActivePojoParam3.setId(/*i + "-" + j */bList.get(p).getId()+ "-" + k);
						jiaoxueActivePojoParam3.setPid(bList.get(p).getId());//
						jiaoxueActivePojoParam3.setLevel("3");
						jiaoxueActivePojoParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地
						jiaoxueActivePojoParam3.setTrainerSum(joinorgZaipeiList.size() + "");

						joinList.add(jiaoxueActivePojoParam3);
					}
				}
			}
		}
		bList.addAll(joinList);
		return bList;
	}
	/**基地--教学活动排序*/
	public List<JiaoxueActivePojoParam> teachActiveSort(String value,List<JiaoxueActivePojoParam> list){
		if(!"".equals(value)){
			List<JiaoxueActivePojoParam> firstList=new ArrayList<>();
			List<JiaoxueActivePojoParam> NofirstList=new ArrayList<>();
			for(JiaoxueActivePojoParam li:list){
				if("".equals(li.getPid())){
					firstList.add(li);
				}
				if(!"".equals(li.getPid())){
					NofirstList.add(li);
				}
			}
			if("jubanchangciDesc".equals(value)){
				Collections.sort(firstList, new Comparator<JiaoxueActivePojoParam>() {
					public int compare(JiaoxueActivePojoParam o1, JiaoxueActivePojoParam o2) {
						Integer teachActiveSessionSum1=0;
						Integer teachActiveSessionSum2=0;
						if(null!=o1.getTeachActiveSessionSum()&& !"".equals(o1.getTeachActiveSessionSum())){
							teachActiveSessionSum1 =o1.getTeachActiveSessionSum();
						}
						if(null!=o2.getTeachActiveSessionSum()&& !"".equals(o2.getTeachActiveSessionSum())){
							teachActiveSessionSum2 =o2.getTeachActiveSessionSum();
						}
						//降序
						return teachActiveSessionSum2.compareTo(teachActiveSessionSum1);
					}
				});
				list = listFormat(firstList,NofirstList);
			}else if("jubanchangciAsc".equals(value)){
				Collections.sort(firstList, new Comparator<JiaoxueActivePojoParam>() {
					public int compare(JiaoxueActivePojoParam o1, JiaoxueActivePojoParam o2) {
						Integer teachActiveSessionSum1=0;
						Integer teachActiveSessionSum2=0;
						if(null!=o1.getTeachActiveSessionSum()&& !"".equals(o1.getTeachActiveSessionSum())){
							teachActiveSessionSum1 = o1.getTeachActiveSessionSum();
						}
						if(null!=o2.getTeachActiveSessionSum()&& !"".equals(o2.getTeachActiveSessionSum())){
							teachActiveSessionSum2 =o2.getTeachActiveSessionSum();
						}
						//升序
						return teachActiveSessionSum1.compareTo(teachActiveSessionSum2);
					}
				});
				list = listFormat(firstList,NofirstList);
			}else if("joinrenciDesc".equals(value)){
				Collections.sort(firstList, new Comparator<JiaoxueActivePojoParam>() {
					public int compare(JiaoxueActivePojoParam o1, JiaoxueActivePojoParam o2) {
						Integer allJoinSum1=0;
						Integer allJoinSum2=0;
						if(null!=o1.getAllJoinSum()&& !"".equals(o1.getAllJoinSum())){
							allJoinSum1 = o1.getAllJoinSum();
						}
						if(null!=o2.getAllJoinSum()&& !"".equals(o2.getAllJoinSum())){
							allJoinSum2 =o2.getAllJoinSum();
						}
						//降序
						return allJoinSum2.compareTo(allJoinSum1);
					}
				});
				list = listFormat(firstList,NofirstList);
			}else if("joinrenciAsc".equals(value)){
				Collections.sort(firstList, new Comparator<JiaoxueActivePojoParam>() {
					public int compare(JiaoxueActivePojoParam o1, JiaoxueActivePojoParam o2) {
						Integer allJoinSum1=0;
						Integer allJoinSum2=0;
						if(null!=o1.getAllJoinSum()&& !"".equals(o1.getAllJoinSum())){
							allJoinSum1 = o1.getAllJoinSum();
						}
						if(null!=o2.getAllJoinSum()&& !"".equals(o2.getAllJoinSum())){
							allJoinSum2 =o2.getAllJoinSum();
						}
						//升序
						return allJoinSum1.compareTo(allJoinSum2);
					}
				});
				list = listFormat(firstList,NofirstList);
			}else if("changjunshichangDesc".equals(value)){
				Collections.sort(firstList, new Comparator<JiaoxueActivePojoParam>() {
					public int compare(JiaoxueActivePojoParam o1, JiaoxueActivePojoParam o2) {
						Double averDureTime1=0.00;
						Double averDureTime2=0.00;
						if(null!=o1.getAverDureTime()&& !"".equals(o1.getAverDureTime())){
							averDureTime1 = o1.getAverDureTime();
						}
						if(null!=o2.getAverDureTime()&& !"".equals(o2.getAverDureTime())){
							averDureTime2 =o2.getAverDureTime();
						}
						//降序
						return averDureTime2.compareTo(averDureTime1);
					}
				});
				list = listFormat(firstList,NofirstList);
			}else if("changjunshichangAsc".equals(value)){
				Collections.sort(firstList, new Comparator<JiaoxueActivePojoParam>() {
					public int compare(JiaoxueActivePojoParam o1, JiaoxueActivePojoParam o2) {
						Double averDureTime1=0.00;
						Double averDureTime2=0.00;
						if(null!=o1.getAverDureTime()&& !"".equals(o1.getAverDureTime())){
							averDureTime1 = o1.getAverDureTime();
						}
						if(null!=o2.getAverDureTime()&& !"".equals(o2.getAverDureTime())){
							averDureTime2 =o2.getAverDureTime();
						}
						//升序
						return averDureTime1.compareTo(averDureTime2);
					}
				});
				list = listFormat(firstList,NofirstList);
			}
		}
		return list;
	}
	public List<JiaoxueActivePojoParam> listFormat(List<JiaoxueActivePojoParam> first,List<JiaoxueActivePojoParam> notFirst){
		first.addAll(notFirst);
		return first;
	}
	public String maxminTime( List<TeachingActivityResult> teachingActivityResultList) throws ParseException {
		if(teachingActivityResultList.size()>0){
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
		return "0";
	}
	/**
	 *pageTOAppuse
	 * @return
	 */
	@RequestMapping("/localAppUse")
	public String PageToAppUseInfo(Model model){
		return "jsres/hospital/localAppUseInfo";
	}

	@RequestMapping("/localAppUseNew")
	public String localAppUseNew(Model model){
		SysUser user = GlobalContext.getCurrentUser();
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isJointOrg",isJointOrg);
		return "jsres/hospital/localAppUseInfoNew";
	}

	/**
	 * 初始化app使用清情况统计new
	 * @param monthDate
	 * @param isContain
	 * @return
	 */
	@RequestMapping("/initAppUserInfo")
	@ResponseBody
	public List initAppUserInfoNew(String monthDate,String isContain,String roleFlag){
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(isContain)){
            isContain = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		List<JsresAppInfo> li  = null;
		Map<String ,Object> param = new HashMap<>();
		param.put("monthDate",monthDate);
		//当前基地是否为协同基地
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
			//查询本基地数据
			param.put("orgFlow",user.getOrgFlow());
			li = schdualTaskMapper.searchJointData(param);
			if(null != li && li.size()>0){
				for (JsresAppInfo info:li) {
					info.setParentOrgFlow("");
				}
			}
		}else {
			List<String> orgFlows = new ArrayList<>();
			orgFlows.add(user.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
				List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(user.getOrgFlow());
				if (null != jointOrgList && jointOrgList.size() > 0) {
					for (ResJointOrg org : jointOrgList) {
						orgFlows.add(org.getJointOrgFlow());
					}
				}
			}
			param.put("orgFlows",orgFlows);
			li = schdualTaskMapper.searchLocalData(param);
		}
		return li;
	}

	@RequestMapping("/localActivity")
	public String localActivity(Model model){
		SysUser user = GlobalContext.getCurrentUser();
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isJointOrg",isJointOrg);
		return "jsres/hospital/localJiaoxueActive";
	}

	@RequestMapping("/initJiaoxueActiveNew")
	@ResponseBody
	public List initJiaoxueActiveNew(String monthDate,String isContain,String notGraduate,String graduate,String orderBy){
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(isContain)){
            isContain = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		String doctorTypeId = "All";
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(notGraduate) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(graduate)) {
			doctorTypeId = "Doctor";
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(graduate) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(notGraduate)) {
			doctorTypeId = "Graduate";
		}
		List<JsresActivityStatistics> li  = null;
		Map<String ,Object> param = new HashMap<>();
		param.put("monthDate",monthDate);
		param.put("doctorTypeId",doctorTypeId);
		param.put("orderBy",orderBy);
		//当前基地是否为协同基地
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
			//查询本基地数据
			param.put("orgFlow",user.getOrgFlow());
			li = schdualTaskMapper.searchLocalActivityData(param);
			if(null != li && li.size()>0){
				for (JsresActivityStatistics info:li) {
					info.setParentOrgFlow("");
				}
			}
		}else {
			List<String> orgFlows = new ArrayList<>();
			orgFlows.add(user.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
				List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(user.getOrgFlow());
				if (null != jointOrgList && jointOrgList.size() > 0) {
					for (ResJointOrg org : jointOrgList) {
						orgFlows.add(org.getJointOrgFlow());
					}
				}
			}
			param.put("orgFlows",orgFlows);
			li = schdualTaskMapper.searchLocalActivityData(param);
		}
		return li;
	}

	@RequestMapping("/doctorOutDept")
	public String doctorOutDept(Model model) {
		SysUser user = GlobalContext.getCurrentUser();
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isJointOrg",isJointOrg);
		return "jsres/hospital/doctorOutDept";
	}

	@RequestMapping("/initDoctorOutDept")
	@ResponseBody
	public List initDoctorOutDept(String monthDate,String isContain,String notGraduate,String graduate,String sortFlag){
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(isContain)){
            isContain = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		if(StringUtil.isBlank(monthDate)){
			monthDate = DateUtil.addMonth(DateUtil.getMonth(),-1);
		}
		String doctorTypeId = "All";
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(notGraduate) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(graduate)) {
			doctorTypeId = "Doctor";
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(graduate) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(notGraduate)) {
			doctorTypeId = "Graduate";
		}
		List<JsresOutDeptStatistics> li  = null;
		Map<String ,Object> param = new HashMap<>();
		param.put("monthDate",monthDate);
		param.put("doctorTypeId",doctorTypeId);
		param.put("sortFlag",sortFlag);
		//当前基地是否为协同基地
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(user.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
			//查询本基地数据
			param.put("orgFlow",user.getOrgFlow());
			li = schdualTaskMapper.searchOutDeptDate(param);
			if(null != li && li.size()>0){
				for (JsresOutDeptStatistics info:li) {
					info.setParentOrgFlow("");
				}
			}
		}else {
			List<String> orgFlows = new ArrayList<>();
			orgFlows.add(user.getOrgFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isContain)) {
				List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(user.getOrgFlow());
				if (null != jointOrgList && jointOrgList.size() > 0) {
					for (ResJointOrg org : jointOrgList) {
						orgFlows.add(org.getJointOrgFlow());
					}
				}
			}
			param.put("orgFlows",orgFlows);
			li = schdualTaskMapper.searchOutDeptDate(param);
		}
		return li;
	}

	/**
	 * 初始化app使用清情况统计old
	 * @param monthDate
	 * @param isContain
	 * @return
	 */
	/*@RequestMapping("/initAppUserInfo")
	@ResponseBody*/
	public List initAppUserInfo(String monthDate,String isContain){
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);//设置精确到小数点后2位

		List<AppUseInfoPojoParam> bList =new ArrayList<>();
		List<SysOrg> joinorgList =new ArrayList<>();
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg currOrg=orgBiz.readSysOrg(user.getOrgFlow());
		//orgs.add(currOrg);//获取机构和协同机构
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(currOrg.getOrgLevelId())) {
			joinorgList = orgBiz.searchJointOrgsByOrg(currOrg.getOrgFlow());
			/*if (orgList != null && orgList.size() > 0) {
				orgs.addAll(orgList);
			}*/
		}
		Map<String , Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dictTypeId", com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		List<SysDict> doctorTrainingSpeList =this.resDoctorRecruitExtMapper.searchTrainSpeList(paramMap);//所有专业
		String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
		String[] docTypeListMast ={"Graduate"};
		//
		if("isContain".equals(isContain)) {
/**第一层专业*/
			for(int i=0;i<doctorTrainingSpeList.size();i++){
		/*AppUseInfoPojoParam appUseInfoPojoParam=new AppUseInfoPojoParam();
		Map<String,Object> spemap=new HashMap<>();
		Map<String,Object> spemap2=new HashMap<>();
		Map<String,Object> spemap3=new HashMap<>();
		spemap.put("orgFlow",currOrg.getOrgFlow());
		spemap2.put("orgFlow",currOrg.getOrgFlow());
		spemap3.put("orgFlow",currOrg.getOrgFlow());
		spemap3.put("monthDate",monthDate);
		String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
		String[] docTypeListMast ={"Graduate"};
		spemap.put("docTypeList",docTypeListDoc);
		spemap2.put("docTypeList",docTypeListMast);
		spemap.put("speId",doctorTrainingSpeList.get(i).getDictId());
		spemap2.put("speId",doctorTrainingSpeList.get(i).getDictId());
		List<JsResDoctorRecruitExt>  zaipeiDocList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
		List<JsResDoctorRecruitExt> zaipeiMasterList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
		int avgzaipei= zaipeiDocList.size()+ zaipeiMasterList.size();
		List<String> userFlowList= monthlyReportExtMapper2.getAppUserSum(spemap3);//该基地下该年月使用app用户id
		List<JsResDoctorRecruitExt> appuserDOcList= getUseAppinfo(zaipeiDocList, userFlowList);
		List<JsResDoctorRecruitExt> appuserMastList =getUseAppinfo(zaipeiMasterList, userFlowList);
		int avguse= appuserDOcList.size()+appuserMastList.size();

		String doctorRate="";
		String masterRate="";
		String avgRate="";
		if(zaipeiDocList.size()==0){
			doctorRate="0%";
		}
		if(null!=zaipeiDocList&& !"".equals(zaipeiDocList) &&  zaipeiDocList.size()!=0){
			doctorRate = numberFormat.format((float) appuserDOcList.size() / (float) zaipeiDocList.size() * 100)+"%";
		}
		if(zaipeiMasterList.size()==0){
			masterRate="0%";
		}
		if(null!=zaipeiMasterList&& !"".equals(zaipeiMasterList) && zaipeiMasterList.size()!=0){
			masterRate = numberFormat.format((float) appuserMastList.size() / (float) zaipeiMasterList.size() * 100)+"%";
		}
		if(avgzaipei==0){
			avgRate="0%";
		}
		if(0!=avgzaipei){
			avgRate = numberFormat.format((float) avguse / (float) avgzaipei * 100)+"%";
		}

		appUseInfoPojoParam.setId(i+"");
		appUseInfoPojoParam.setPid("");
		appUseInfoPojoParam.setLevel("1");
		appUseInfoPojoParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
		appUseInfoPojoParam.setDoctorRateTrainSum(zaipeiDocList.size()+"");
		appUseInfoPojoParam.setMasterRateTrainSum(zaipeiMasterList.size()+"");
		appUseInfoPojoParam.setAvgRateTrainSum(avgzaipei+"");
		appUseInfoPojoParam.setDoctorRateUseSum(appuserDOcList.size()+"");
		appUseInfoPojoParam.setMasterRateUseSum(appuserMastList.size()+"");
		appUseInfoPojoParam.setAvgRateUseSum(avguse+"");

		appUseInfoPojoParam.setDoctorRateRate(doctorRate);
		appUseInfoPojoParam.setMasterRateRate(masterRate);
		appUseInfoPojoParam.setAvgRateRate(avgRate);

		bList.add(appUseInfoPojoParam);*/
				int speDoczaipei=0;
				int speDocuse=0;
				int speMastzaipei=0;
				int speMastuse=0;
				int speavgzaipei=0;
				int speavguse=0;
				/**年级*/
				Map<String,Object> sessionNumbermap=new HashMap<>();
				List<String> sessionNumberDistinct=new ArrayList<>();
				sessionNumbermap.put("orgFlow",currOrg.getOrgFlow());
				String[] docTypeList={"Company", "CompanyEntrust", "Social","Graduate"};
				sessionNumbermap.put("docTypeList",docTypeList);
				sessionNumbermap.put("speId",doctorTrainingSpeList.get(i).getDictId());
				List<JsResDoctorRecruitExt>  sessionNUmberList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
				for(JsResDoctorRecruitExt jrs:sessionNUmberList){
					if(!sessionNumberDistinct.contains(jrs.getSessionNumber())){
						sessionNumberDistinct.add(jrs.getSessionNumber());
					}
				}
				for(int j=0;j<sessionNumberDistinct.size();j++) {
					int sessionDoczaipei=0;
					int sessionDocuse=0;
					int sessionMastzaipei=0;
					int sessionMastuse=0;
					int sessionavgzaipei=0;
					int sessionavguse=0;
					if (joinorgList.size() > 0) {
						for (int k = 0; k < joinorgList.size(); k++) {
							AppUseInfoPojoParam appUseInfoPojoParam3 = new AppUseInfoPojoParam();
							Map<String, Object> joinOrgMap = new HashMap<>();
							Map<String, Object> joinOrgMap2 = new HashMap<>();
							Map<String, Object> joinOrgMap3 = new HashMap<>();
							joinOrgMap.put("orgFlow", joinorgList.get(k).getOrgFlow());
							joinOrgMap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
							joinOrgMap.put("docTypeList", docTypeListDoc);
							joinOrgMap2.put("docTypeList", docTypeListMast);
							joinOrgMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
							joinOrgMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
							joinOrgMap.put("sessionNumber", sessionNumberDistinct.get(j));
							joinOrgMap2.put("sessionNumber", sessionNumberDistinct.get(j));
							joinOrgMap3.put("orgFlow", joinorgList.get(k).getOrgFlow());
							joinOrgMap3.put("monthDate", monthDate);
							List<JsResDoctorRecruitExt> joinorgZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap);
							List<JsResDoctorRecruitExt> joinorgZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap2);
							int joinorgavgzaipei = joinorgZaipeiListDoc.size() + joinorgZaipeiListMaster.size();
							List<String> joinorguserFlowList = monthlyReportExtMapper2.getAppUserSum(joinOrgMap3);//该基地下该年月使用app用户id
							List<JsResDoctorRecruitExt> joinorgappuserDOcList = getUseAppinfo(joinorgZaipeiListDoc, joinorguserFlowList);
							List<JsResDoctorRecruitExt> joinorgappuserMastList = getUseAppinfo(joinorgZaipeiListMaster, joinorguserFlowList);
							int joinorgavguse = joinorgappuserDOcList.size() + joinorgappuserMastList.size();

							String joinorgdoctorRate = "";
							String joinorgmasterRate = "";
							String joinorgavgRate = "";
							if (joinorgZaipeiListDoc.size() == 0) {
								joinorgdoctorRate = "0%";
							}
							if (null != joinorgZaipeiListDoc && !"".equals(joinorgZaipeiListDoc) && joinorgZaipeiListDoc.size() != 0) {
								joinorgdoctorRate = numberFormat.format((float) joinorgappuserDOcList.size() / (float) joinorgZaipeiListDoc.size() * 100) + "%";
							}
							if (joinorgZaipeiListMaster.size() == 0) {
								joinorgmasterRate = "0%";
							}
							if (null != joinorgZaipeiListMaster && !"".equals(joinorgZaipeiListMaster) && joinorgZaipeiListMaster.size() != 0) {
								joinorgmasterRate = numberFormat.format((float) joinorgappuserMastList.size() / (float) joinorgZaipeiListMaster.size() * 100) + "%";
							}
							if (joinorgavgzaipei == 0) {
								joinorgavgRate = "0%";
							}
							if (0 != joinorgavgzaipei) {
								joinorgavgRate = numberFormat.format((float) joinorgavguse / (float) joinorgavgzaipei * 100) + "%";
							}
							//
							appUseInfoPojoParam3.setId(i + "-" + j+"-"+k);
							appUseInfoPojoParam3.setPid(i + "-" + j);//
							appUseInfoPojoParam3.setLevel("3");
							appUseInfoPojoParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地
							appUseInfoPojoParam3.setDoctorRateTrainSum(joinorgZaipeiListDoc.size() + "");
							appUseInfoPojoParam3.setMasterRateTrainSum(joinorgZaipeiListMaster.size() + "");
							appUseInfoPojoParam3.setAvgRateTrainSum(joinorgavgzaipei + "");
							appUseInfoPojoParam3.setDoctorRateUseSum(joinorgappuserDOcList.size() + "");
							appUseInfoPojoParam3.setMasterRateUseSum(joinorgappuserMastList.size() + "");
							appUseInfoPojoParam3.setAvgRateUseSum(joinorgavguse + "");

							appUseInfoPojoParam3.setDoctorRateRate(joinorgdoctorRate);
							appUseInfoPojoParam3.setMasterRateRate(joinorgmasterRate);
							appUseInfoPojoParam3.setAvgRateRate(joinorgavgRate);

							bList.add(appUseInfoPojoParam3);
							sessionDoczaipei=sessionDoczaipei+joinorgZaipeiListDoc.size();
							sessionMastzaipei=sessionMastzaipei+joinorgZaipeiListMaster.size();
							sessionavgzaipei=sessionavgzaipei+joinorgavgzaipei;
							sessionDocuse=sessionDocuse+joinorgappuserDOcList.size();
							sessionMastuse=sessionMastuse+joinorgappuserMastList.size();
							sessionavguse=sessionavguse+joinorgavguse;
						}
					}
					AppUseInfoPojoParam appUseInfoPojoParam2 = new AppUseInfoPojoParam();
					Map<String, Object> sessionMap = new HashMap<>();
					Map<String, Object> sessionMap2 = new HashMap<>();
					Map<String, Object> sessionMap3 = new HashMap<>();
					sessionMap.put("orgFlow", currOrg.getOrgFlow());
					sessionMap2.put("orgFlow", currOrg.getOrgFlow());
					sessionMap.put("docTypeList", docTypeListDoc);
					sessionMap2.put("docTypeList", docTypeListMast);
					sessionMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
					sessionMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
					sessionMap.put("sessionNumber", sessionNumberDistinct.get(j));
					sessionMap2.put("sessionNumber", sessionNumberDistinct.get(j));
					sessionMap3.put("orgFlow", currOrg.getOrgFlow());
					sessionMap3.put("monthDate", monthDate);
					List<JsResDoctorRecruitExt> sessionNUmberZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap);
					List<JsResDoctorRecruitExt> sessionNUmberZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap2);
					int SessionNumberavgzaipei = sessionNUmberZaipeiListDoc.size() + sessionNUmberZaipeiListMaster.size();
					List<String> SesionNumberuserFlowList = monthlyReportExtMapper2.getAppUserSum(sessionMap3);//该基地下该年月使用app用户id
					List<JsResDoctorRecruitExt> SessionNumberappuserDOcList = getUseAppinfo(sessionNUmberZaipeiListDoc, SesionNumberuserFlowList);
					List<JsResDoctorRecruitExt> SessionNumberappuserMastList = getUseAppinfo(sessionNUmberZaipeiListMaster, SesionNumberuserFlowList);
					int SessionNumberavguse = SessionNumberappuserDOcList.size() + SessionNumberappuserMastList.size();

					int sessionDoczaipeiIscontain=sessionNUmberZaipeiListDoc.size()+sessionDoczaipei;
					int sessionDocuseIscontain=SessionNumberappuserDOcList.size()+sessionDocuse;
					int sessionMastzaipeiIscontain=sessionNUmberZaipeiListMaster.size()+sessionMastzaipei;
					int sessionMastuseIscontain=SessionNumberappuserMastList.size()+sessionMastuse;
					int sessionavgzaipeiIscontain=SessionNumberavgzaipei+sessionavgzaipei;
					int sessionavguseIscontain=SessionNumberavguse+sessionavguse;

					String SessionNUmbrdoctorRate = "";
					String SessionNUmbrmasterRate = "";
					String SessionNUmbravgRate = "";
					if (sessionDoczaipeiIscontain == 0) {
						SessionNUmbrdoctorRate = "0%";
					}
					if (sessionDoczaipeiIscontain != 0) {
						SessionNUmbrdoctorRate = numberFormat.format((float) sessionDocuseIscontain / (float) sessionDoczaipeiIscontain * 100) + "%";
					}
					if (sessionMastzaipeiIscontain == 0) {
						SessionNUmbrmasterRate = "0%";
					}
					if (sessionMastzaipeiIscontain != 0) {
						SessionNUmbrmasterRate = numberFormat.format((float)sessionMastuseIscontain / (float) sessionMastzaipeiIscontain * 100) + "%";
					}
					if (sessionavgzaipeiIscontain == 0) {
						SessionNUmbravgRate = "0%";
					}
					if (0 != sessionavgzaipeiIscontain) {
						SessionNUmbravgRate = numberFormat.format((float) sessionavguseIscontain / (float) sessionavgzaipeiIscontain * 100) + "%";
					}
					appUseInfoPojoParam2.setId(i + "-" + j);
					appUseInfoPojoParam2.setPid(i+"");//
					appUseInfoPojoParam2.setLevel("2");
					appUseInfoPojoParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
					appUseInfoPojoParam2.setDoctorRateTrainSum(sessionDoczaipeiIscontain + "");
					appUseInfoPojoParam2.setMasterRateTrainSum(sessionMastzaipeiIscontain + "");
					appUseInfoPojoParam2.setAvgRateTrainSum(sessionavgzaipeiIscontain + "");
					appUseInfoPojoParam2.setDoctorRateUseSum(sessionDocuseIscontain + "");
					appUseInfoPojoParam2.setMasterRateUseSum(sessionMastuseIscontain+ "");
					appUseInfoPojoParam2.setAvgRateUseSum(sessionavguseIscontain + "");

					appUseInfoPojoParam2.setDoctorRateRate(SessionNUmbrdoctorRate);
					appUseInfoPojoParam2.setMasterRateRate(SessionNUmbrmasterRate);
					appUseInfoPojoParam2.setAvgRateRate(SessionNUmbravgRate);

					bList.add(appUseInfoPojoParam2);
					speDoczaipei=speDoczaipei+sessionDoczaipeiIscontain;
					speDocuse=speDocuse+sessionDocuseIscontain;
					speMastzaipei=speMastzaipei+sessionMastzaipeiIscontain;
					speMastuse=speMastuse+sessionMastuseIscontain;
					speavgzaipei=speavgzaipei+sessionavgzaipeiIscontain;
					speavguse=speavguse+sessionavguseIscontain;

				}
				AppUseInfoPojoParam appUseInfoPojoParam=new AppUseInfoPojoParam();
				Map<String,Object> spemap=new HashMap<>();
				Map<String,Object> spemap2=new HashMap<>();
				Map<String,Object> spemap3=new HashMap<>();
				spemap.put("orgFlow",currOrg.getOrgFlow());
				spemap2.put("orgFlow",currOrg.getOrgFlow());
				spemap3.put("orgFlow",currOrg.getOrgFlow());
				spemap3.put("monthDate",monthDate);
		/*String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
		String[] docTypeListMast ={"Graduate"};*/
				spemap.put("docTypeList",docTypeListDoc);
				spemap2.put("docTypeList",docTypeListMast);
				spemap.put("speId",doctorTrainingSpeList.get(i).getDictId());
				spemap2.put("speId",doctorTrainingSpeList.get(i).getDictId());
				List<JsResDoctorRecruitExt>  zaipeiDocList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
				List<JsResDoctorRecruitExt> zaipeiMasterList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
				int avgzaipei= zaipeiDocList.size()+ zaipeiMasterList.size();
				List<String> userFlowList= monthlyReportExtMapper2.getAppUserSum(spemap3);//该基地下该年月使用app用户id
				List<JsResDoctorRecruitExt> appuserDOcList= getUseAppinfo(zaipeiDocList, userFlowList);
				List<JsResDoctorRecruitExt> appuserMastList =getUseAppinfo(zaipeiMasterList, userFlowList);
				int avguse= appuserDOcList.size()+appuserMastList.size();



				int speDoczaipeiIscontain=/*zaipeiDocList.size()+*/speDoczaipei;
				int speDocuseIscontain=/*appuserDOcList.size()+*/speDocuse;
				int speMastzaipeiIscontain=/*zaipeiMasterList.size()+*/speMastzaipei;
				int speMastuseIscontain=/*appuserMastList.size()+*/speMastuse;
				int speavgzaipeiIscontain=/*avgzaipei+*/speavgzaipei;
				int speavguseIscontain=/*avguse+*/speavguse;

				String doctorRate="";
				String masterRate="";
				String avgRate="";
				if(speDoczaipeiIscontain==0){
					doctorRate="0%";
				}
				if(speDoczaipeiIscontain!=0){
					doctorRate = numberFormat.format((float) speDocuseIscontain / (float) speDoczaipeiIscontain * 100)+"%";
				}
				if(speMastzaipeiIscontain==0){
					masterRate="0%";
				}
				if( speMastzaipeiIscontain!=0){
					masterRate = numberFormat.format((float) speMastuseIscontain / (float) speMastzaipeiIscontain * 100)+"%";
				}
				if(speavgzaipeiIscontain==0){
					avgRate="0%";
				}
				if(0!=speavgzaipeiIscontain){
					avgRate = numberFormat.format((float) speavguseIscontain / (float) speavgzaipeiIscontain * 100)+"%";
				}

				appUseInfoPojoParam.setId(i+"");
				appUseInfoPojoParam.setPid("");
				appUseInfoPojoParam.setLevel("1");
				appUseInfoPojoParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
				appUseInfoPojoParam.setDoctorRateTrainSum(speDoczaipeiIscontain+"");
				appUseInfoPojoParam.setMasterRateTrainSum(speMastzaipeiIscontain+"");
				appUseInfoPojoParam.setAvgRateTrainSum(speavgzaipeiIscontain+"");
				appUseInfoPojoParam.setDoctorRateUseSum(speDocuseIscontain+"");
				appUseInfoPojoParam.setMasterRateUseSum(speMastuseIscontain+"");
				appUseInfoPojoParam.setAvgRateUseSum(speavguseIscontain+"");

				appUseInfoPojoParam.setDoctorRateRate(doctorRate);
				appUseInfoPojoParam.setMasterRateRate(masterRate);
				appUseInfoPojoParam.setAvgRateRate(avgRate);

				bList.add(appUseInfoPojoParam);
			}
		}else{
			/**第一层专业*/
			for(int i=0;i<doctorTrainingSpeList.size();i++){
				AppUseInfoPojoParam appUseInfoPojoParam=new AppUseInfoPojoParam();
				Map<String,Object> spemap=new HashMap<>();
				Map<String,Object> spemap2=new HashMap<>();
				Map<String,Object> spemap3=new HashMap<>();
				spemap.put("orgFlow",currOrg.getOrgFlow());
				spemap2.put("orgFlow",currOrg.getOrgFlow());
				spemap3.put("orgFlow",currOrg.getOrgFlow());
				spemap3.put("monthDate",monthDate);
			/*String[] docTypeListDoc ={"Company", "CompanyEntrust", "Social"};
			String[] docTypeListMast ={"Graduate"};*/
				spemap.put("docTypeList",docTypeListDoc);
				spemap2.put("docTypeList",docTypeListMast);
				spemap.put("speId",doctorTrainingSpeList.get(i).getDictId());
				spemap2.put("speId",doctorTrainingSpeList.get(i).getDictId());
				List<JsResDoctorRecruitExt>  zaipeiDocList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap);
				List<JsResDoctorRecruitExt> zaipeiMasterList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(spemap2);
				int avgzaipei= zaipeiDocList.size()+ zaipeiMasterList.size();
				List<String> userFlowList= monthlyReportExtMapper2.getAppUserSum(spemap3);//该基地下该年月使用app用户id
				List<JsResDoctorRecruitExt> appuserDOcList= getUseAppinfo(zaipeiDocList, userFlowList);
				List<JsResDoctorRecruitExt> appuserMastList =getUseAppinfo(zaipeiMasterList, userFlowList);
				int avguse= appuserDOcList.size()+appuserMastList.size();

				String doctorRate="";
				String masterRate="";
				String avgRate="";
				if(zaipeiDocList.size()==0){
					doctorRate="0%";
				}
				if(null!=zaipeiDocList&& !"".equals(zaipeiDocList) &&  zaipeiDocList.size()!=0){
					doctorRate = numberFormat.format((float) appuserDOcList.size() / (float) zaipeiDocList.size() * 100)+"%";
				}
				if(zaipeiMasterList.size()==0){
					masterRate="0%";
				}
				if(null!=zaipeiMasterList&& !"".equals(zaipeiMasterList) && zaipeiMasterList.size()!=0){
					masterRate = numberFormat.format((float) appuserMastList.size() / (float) zaipeiMasterList.size() * 100)+"%";
				}
				if(avgzaipei==0){
					avgRate="0%";
				}
				if(0!=avgzaipei){
					avgRate = numberFormat.format((float) avguse / (float) avgzaipei * 100)+"%";
				}

				appUseInfoPojoParam.setId(i+"");
				appUseInfoPojoParam.setPid("");
				appUseInfoPojoParam.setLevel("1");
				appUseInfoPojoParam.setSpeName(doctorTrainingSpeList.get(i).getDictName());
				appUseInfoPojoParam.setDoctorRateTrainSum(zaipeiDocList.size()+"");
				appUseInfoPojoParam.setMasterRateTrainSum(zaipeiMasterList.size()+"");
				appUseInfoPojoParam.setAvgRateTrainSum(avgzaipei+"");
				appUseInfoPojoParam.setDoctorRateUseSum(appuserDOcList.size()+"");
				appUseInfoPojoParam.setMasterRateUseSum(appuserMastList.size()+"");
				appUseInfoPojoParam.setAvgRateUseSum(avguse+"");

				appUseInfoPojoParam.setDoctorRateRate(doctorRate);
				appUseInfoPojoParam.setMasterRateRate(masterRate);
				appUseInfoPojoParam.setAvgRateRate(avgRate);

				bList.add(appUseInfoPojoParam);
				/**年级*/
				Map<String,Object> sessionNumbermap=new HashMap<>();
				List<String> sessionNumberDistinct=new ArrayList<>();
				sessionNumbermap.put("orgFlow",currOrg.getOrgFlow());
				String[] docTypeList={"Company", "CompanyEntrust", "Social","Graduate"};
				sessionNumbermap.put("docTypeList",docTypeList);
				sessionNumbermap.put("speId",doctorTrainingSpeList.get(i).getDictId());
				List<JsResDoctorRecruitExt>  sessionNUmberList= monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionNumbermap);
				for(JsResDoctorRecruitExt jrs:sessionNUmberList){
					if(!sessionNumberDistinct.contains(jrs.getSessionNumber())){
						sessionNumberDistinct.add(jrs.getSessionNumber());
					}
				}
				for(int j=0;j<sessionNumberDistinct.size();j++) {
					AppUseInfoPojoParam appUseInfoPojoParam2 = new AppUseInfoPojoParam();
					Map<String, Object> sessionMap = new HashMap<>();
					Map<String, Object> sessionMap2 = new HashMap<>();
					Map<String, Object> sessionMap3 = new HashMap<>();
					sessionMap.put("orgFlow", currOrg.getOrgFlow());
					sessionMap2.put("orgFlow", currOrg.getOrgFlow());
					sessionMap.put("docTypeList", docTypeListDoc);
					sessionMap2.put("docTypeList", docTypeListMast);
					sessionMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
					sessionMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
					sessionMap.put("sessionNumber", sessionNumberDistinct.get(j));
					sessionMap2.put("sessionNumber", sessionNumberDistinct.get(j));
					sessionMap3.put("orgFlow", currOrg.getOrgFlow());
					sessionMap3.put("monthDate", monthDate);
					List<JsResDoctorRecruitExt> sessionNUmberZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap);
					List<JsResDoctorRecruitExt> sessionNUmberZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(sessionMap2);
					int SessionNumberavgzaipei = sessionNUmberZaipeiListDoc.size() + sessionNUmberZaipeiListMaster.size();
					List<String> SesionNumberuserFlowList = monthlyReportExtMapper2.getAppUserSum(sessionMap3);//该基地下该年月使用app用户id
					List<JsResDoctorRecruitExt> SessionNumberappuserDOcList = getUseAppinfo(sessionNUmberZaipeiListDoc, SesionNumberuserFlowList);
					List<JsResDoctorRecruitExt> SessionNumberappuserMastList = getUseAppinfo(sessionNUmberZaipeiListMaster, SesionNumberuserFlowList);
					int SessionNumberavguse = SessionNumberappuserDOcList.size() + SessionNumberappuserMastList.size();

					String SessionNUmbrdoctorRate = "";
					String SessionNUmbrmasterRate = "";
					String SessionNUmbravgRate = "";
					if (sessionNUmberZaipeiListDoc.size() == 0) {
						SessionNUmbrdoctorRate = "0%";
					}
					if (null != sessionNUmberZaipeiListDoc && !"".equals(sessionNUmberZaipeiListDoc) && sessionNUmberZaipeiListDoc.size() != 0) {
						SessionNUmbrdoctorRate = numberFormat.format((float) SessionNumberappuserDOcList.size() / (float) sessionNUmberZaipeiListDoc.size() * 100) + "%";
					}
					if (sessionNUmberZaipeiListMaster.size() == 0) {
						SessionNUmbrmasterRate = "0%";
					}
					if (null != sessionNUmberZaipeiListMaster && !"".equals(sessionNUmberZaipeiListMaster) && sessionNUmberZaipeiListMaster.size() != 0) {
						SessionNUmbrmasterRate = numberFormat.format((float) SessionNumberappuserMastList.size() / (float) sessionNUmberZaipeiListMaster.size() * 100) + "%";
					}
					if (SessionNumberavgzaipei == 0) {
						SessionNUmbravgRate = "0%";
					}
					if (0 != SessionNumberavgzaipei) {
						SessionNUmbravgRate = numberFormat.format((float) SessionNumberavguse / (float) SessionNumberavgzaipei * 100) + "%";
					}
					appUseInfoPojoParam2.setId(i + "-" + j);
					appUseInfoPojoParam2.setPid(appUseInfoPojoParam.getId());//
					appUseInfoPojoParam2.setLevel("2");
					appUseInfoPojoParam2.setSpeName(sessionNumberDistinct.get(j));//存sessionNumber
					appUseInfoPojoParam2.setDoctorRateTrainSum(sessionNUmberZaipeiListDoc.size() + "");
					appUseInfoPojoParam2.setMasterRateTrainSum(sessionNUmberZaipeiListMaster.size() + "");
					appUseInfoPojoParam2.setAvgRateTrainSum(SessionNumberavgzaipei + "");
					appUseInfoPojoParam2.setDoctorRateUseSum(SessionNumberappuserDOcList.size() + "");
					appUseInfoPojoParam2.setMasterRateUseSum(SessionNumberappuserMastList.size() + "");
					appUseInfoPojoParam2.setAvgRateUseSum(SessionNumberavguse + "");

					appUseInfoPojoParam2.setDoctorRateRate(SessionNUmbrdoctorRate);
					appUseInfoPojoParam2.setMasterRateRate(SessionNUmbrmasterRate);
					appUseInfoPojoParam2.setAvgRateRate(SessionNUmbravgRate);

					bList.add(appUseInfoPojoParam2);
					if (joinorgList.size() > 0) {
						for (int k = 0; k < joinorgList.size(); k++) {
							AppUseInfoPojoParam appUseInfoPojoParam3 = new AppUseInfoPojoParam();
							Map<String, Object> joinOrgMap = new HashMap<>();
							Map<String, Object> joinOrgMap2 = new HashMap<>();
							Map<String, Object> joinOrgMap3 = new HashMap<>();
							joinOrgMap.put("orgFlow", joinorgList.get(k).getOrgFlow());
							joinOrgMap2.put("orgFlow", joinorgList.get(k).getOrgFlow());
							joinOrgMap.put("docTypeList", docTypeListDoc);
							joinOrgMap2.put("docTypeList", docTypeListMast);
							joinOrgMap.put("speId", doctorTrainingSpeList.get(i).getDictId());
							joinOrgMap2.put("speId", doctorTrainingSpeList.get(i).getDictId());
							joinOrgMap.put("sessionNumber", sessionNumberDistinct.get(j));
							joinOrgMap2.put("sessionNumber", sessionNumberDistinct.get(j));
							joinOrgMap3.put("orgFlow", joinorgList.get(k).getOrgFlow());
							joinOrgMap3.put("monthDate", monthDate);
							List<JsResDoctorRecruitExt> joinorgZaipeiListDoc = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap);
							List<JsResDoctorRecruitExt> joinorgZaipeiListMaster = monthlyReportExtMapper2.getTraincountbySpeNameSessionNumberDocType(joinOrgMap2);
							int joinorgavgzaipei = joinorgZaipeiListDoc.size() + joinorgZaipeiListMaster.size();
							List<String> joinorguserFlowList = monthlyReportExtMapper2.getAppUserSum(joinOrgMap3);//该基地下该年月使用app用户id
							List<JsResDoctorRecruitExt> joinorgappuserDOcList = getUseAppinfo(joinorgZaipeiListDoc, joinorguserFlowList);
							List<JsResDoctorRecruitExt> joinorgappuserMastList = getUseAppinfo(joinorgZaipeiListMaster, joinorguserFlowList);
							int joinorgavguse = joinorgappuserDOcList.size() + joinorgappuserMastList.size();

							String joinorgdoctorRate = "";
							String joinorgmasterRate = "";
							String joinorgavgRate = "";
							if (joinorgZaipeiListDoc.size() == 0) {
								joinorgdoctorRate = "0%";
							}
							if (null != joinorgZaipeiListDoc && !"".equals(joinorgZaipeiListDoc) && joinorgZaipeiListDoc.size() != 0) {
								joinorgdoctorRate = numberFormat.format((float) joinorgappuserDOcList.size() / (float) joinorgZaipeiListDoc.size() * 100) + "%";
							}
							if (joinorgZaipeiListMaster.size() == 0) {
								joinorgmasterRate = "0%";
							}
							if (null != joinorgZaipeiListMaster && !"".equals(joinorgZaipeiListMaster) && joinorgZaipeiListMaster.size() != 0) {
								joinorgmasterRate = numberFormat.format((float) joinorgappuserMastList.size() / (float) joinorgZaipeiListMaster.size() * 100) + "%";
							}
							if (joinorgavgzaipei == 0) {
								joinorgavgRate = "0%";
							}
							if (0 != joinorgavgzaipei) {
								joinorgavgRate = numberFormat.format((float) joinorgavguse / (float) joinorgavgzaipei * 100) + "%";
							}
							//
							appUseInfoPojoParam3.setId(i + "-" + j+"-"+k);
							appUseInfoPojoParam3.setPid(appUseInfoPojoParam2.getId());//
							appUseInfoPojoParam3.setLevel("3");
							appUseInfoPojoParam3.setSpeName(joinorgList.get(k).getOrgName());//存协同基地
							appUseInfoPojoParam3.setDoctorRateTrainSum(joinorgZaipeiListDoc.size() + "");
							appUseInfoPojoParam3.setMasterRateTrainSum(joinorgZaipeiListMaster.size() + "");
							appUseInfoPojoParam3.setAvgRateTrainSum(joinorgavgzaipei + "");
							appUseInfoPojoParam3.setDoctorRateUseSum(joinorgappuserDOcList.size() + "");
							appUseInfoPojoParam3.setMasterRateUseSum(joinorgappuserMastList.size() + "");
							appUseInfoPojoParam3.setAvgRateUseSum(joinorgavguse + "");

							appUseInfoPojoParam3.setDoctorRateRate(joinorgdoctorRate);
							appUseInfoPojoParam3.setMasterRateRate(joinorgmasterRate);
							appUseInfoPojoParam3.setAvgRateRate(joinorgavgRate);

							bList.add(appUseInfoPojoParam3);
						}
					}

				}
			}

		}

		return bList;
	}

	public List<JsResDoctorRecruitExt> getUseAppinfo(List<JsResDoctorRecruitExt> list,List<String> userFlowList){
		List<JsResDoctorRecruitExt> listnew=new ArrayList<>();
		for(JsResDoctorRecruitExt js:list){
			boolean falg=false;
			for(String li:userFlowList){
				if(js.getDoctorFlow().equals(li)){
					falg=true;
					break;
				}
			}
			if(falg){
				listnew.add(js);
			}
		}
		return listnew;
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
			String role =getLocalRoleFlag();//基地角色
			if(!"".equals(role)){
				SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
				orgs.add(org);
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
					List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
					if (orgList != null && orgList.size() > 0) {
						orgs.addAll(orgList);
					}
				}
				model.addAttribute("orgs",orgs);
				model.addAttribute("userListScope",role);
			}
		}catch (RuntimeException e){
			logger.error("", e);
			if(null==e.getMessage()){
				model.addAttribute("error","null");
			}else{
				model.addAttribute("error",e.getMessage());
			}
			return "jsres/hospital/appUseInfoDetail";
		}
		return "jsres/hospital/appUseInfoDetail";
	}
	/***
	 * app使用详细信息 （未使用用户信息）
	 */
	@RequestMapping("/appNotUseList")
	public String appNotUseList(Integer currentPage,HttpServletRequest request, Model model,String monthDate,
								String userListScope, String orgFlow, String[] datas,
								String trainingSpeId, String sessionNumber) {

		Map<String, Object> param = new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
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
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {//基地
			if (StringUtil.isBlank(orgFlow)) {
				orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			}
			param.put("orgFlow", orgFlow);
		}
		List<Map<String, String>> list1 = jsResDoctorRecruitBiz.searchOrgNotUseAppDoc(param);
		for(Map<String,String> map:list1){
			Map<String,Object> m=new HashMap<>();
			String schName="";
			String userflow= map.get("userFlow");
			m.put("doctorFlow",userflow);
			m.put("monthDate",monthDate);
			List<Map<String, String>> schNameList =jsResDoctorRecruitBiz.findSchArrengResultByDoctorAndYearMonth(m);
			if(schNameList.size()>0){
				for(Map<String, String> ma:schNameList){
					String name =ma.get("schDeptName");
					schName=schName+name+",";
				}
			}
			map.put("schDeptName",schName);
		}

		Page list=pageGetInfo(currentPage,getPageSize(request),list1);
		model.addAttribute("list", list);
		return "jsres/hospital/appNotUseList";
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
	@RequestMapping("/exportExcelAppuse")
	public void exportExcel( HttpServletRequest request,String[] arrayId, Model model,String monthDate,
							 String userListScope, String orgFlow, String[] datas,
							 String trainingSpeId, String sessionNumber,HttpServletResponse response) throws Exception{
		Map<String, Object> param = new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
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
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {//基地
			if (StringUtil.isBlank(orgFlow)) {
				orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			}
			param.put("orgFlow", orgFlow);
		}
		List<Map<String, String>> list1 = jsResDoctorRecruitBiz.searchOrgNotUseAppDoc(param);
		if(null!=arrayId){
			List<Map<String, String>> partlist=new ArrayList<>();
			for(Map<String,String> map:list1){
				String userflow= map.get("userFlow");
				if(Arrays.asList(arrayId).contains(userflow)){
					partlist.add(map);
				}
			}
			list1=partlist;
		}
		for(Map<String,String> map:list1){
			Map<String,Object> m=new HashMap<>();
			String schName="";
			String userflow= map.get("userFlow");
			m.put("doctorFlow",userflow);
			m.put("monthDate",monthDate);
			List<Map<String, String>> schNameList =jsResDoctorRecruitBiz.findSchArrengResultByDoctorAndYearMonth(m);
			if(schNameList.size()>0){
				for(Map<String, String> ma:schNameList){
					String name =ma.get("schDeptName");
					schName=schName+name+",";
				}
			}
			map.put("schDeptName",schName);
		}
		String fileName = "未使用APP学员明细.xls";
		String []titles = new String[]{
				"userName:学员姓名",
				"sexName:性别",
				"orgName:培训基地",
				"sessionNumber:年级",
				"speName:培训专业",
				"userPhone:手机号码",
				"idNo:证件号码",
				"doctorStatusName:培训状态",
				"tutorName:责任导师",
				"schDeptName:当前轮转科室"
		};
		ExcleUtile.exportSimpleExcleByObjs(titles, list1, response.getOutputStream());
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
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

	/***
	 * 获取角色标识   local
	 * @return
	 */
	public String getLocalRoleFlag(){
		List<Map<String,String>>  rolelistInfo=getRoles();
		List<String> roleUrlList=new ArrayList<>();
		for(Map<String,String> map:rolelistInfo){
			String roleurl = map.get("roleIndex");
			if(StringUtil.isNotBlank(roleurl)){
				//local 基地
				if("/jsres/manage/local".equals(roleurl) ){
					String flag =roleurl.split("/")[3];
					roleUrlList.add(flag);
				}
			}
		}
		if(roleUrlList.size()>0){
			return roleUrlList.get(0);
			/*if(roleUrlList.contains("global") && roleUrlList.contains("charge") ){
				throw new RuntimeException("请联系管理员，角色不能既是省级部门又是主管部门");
			}else if(roleUrlList.contains("global")  && roleUrlList.contains("university")){
				throw new RuntimeException("请联系管理员，角色不能既是省级部门又是高校");
			}else if(roleUrlList.contains("charge")  && roleUrlList.contains("university")){
				throw new RuntimeException("请联系管理员，角色不能既是主管部门又是高校");
			}else if(roleUrlList.contains("global") && roleUrlList.contains("charge")  && roleUrlList.contains("university")){
				throw new RuntimeException("请联系管理员，角色不能同时省级部门、主管部门、高校");
			}else{
				return roleUrlList.get(0);
			}*/
		}
		return "";
	}
	public List<Map<String,String>> getRoles(){
		List<Map<String,String>> roles=new ArrayList<>();
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        List<String> currRoleList = (List<String>) GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST);
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
				role.put("roleIndex","/index");
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

	//排序
	public List listSort(String value,List<SysOrg> list){
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(4);//设置精确到小数点后4位
		List<SysOrg> firstList=new ArrayList<>();
		List<SysOrg> secondList=new ArrayList<>();
		if(!"".equals(value)){
			for(SysOrg li:list){
				if("".equals(li.getParentOrgFlow()) || null==li.getParentOrgFlow()){
					firstList.add(li);
				}
				if(!"".equals(li.getParentOrgFlow())&& null!=li.getParentOrgFlow()){
					secondList.add(li);
				}
			}
			if("auditDesc".equals(value)){
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
				firstList.addAll(secondList);
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
				firstList.addAll(secondList);
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
				firstList.addAll(secondList);
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
			}else if("avgAuditDesc".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double avgAuditNum1=0.00;
						Double avgAuditNum2=0.00;
						if(null!=o1.getFillNum()&& !"".equals(o1.getFillNum())&& !"".equals(o1.getTrainDoctorTotal())&& null!=o1.getTrainDoctorTotal()){
							if(o1.getFillNum()==0 || o1.getTrainDoctorTotal()==0){
								avgAuditNum1=0.00;
							}else{
								avgAuditNum1 = Double.parseDouble(numberFormat.format((float) o1.getAuditNum().intValue() / (float) o1.getFillNum().intValue() /o1.getTrainDoctorTotal().intValue()));
							}

						}
						if(null!=o2.getFillNum()&& !"".equals(o2.getFillNum())&& !"".equals(o2.getTrainDoctorTotal())&& null!=o2.getTrainDoctorTotal()){
							if(o2.getFillNum()==0 || o2.getTrainDoctorTotal()==0 ){
								avgAuditNum2=0.00;
							}else{
								avgAuditNum2 = Double.parseDouble(numberFormat.format((float) o2.getAuditNum().intValue() / (float) o2.getFillNum().intValue() /o2.getTrainDoctorTotal().intValue()));
							}
						}
						//降序
						return avgAuditNum2.compareTo(avgAuditNum1);
					}
				});
				firstList.addAll(secondList);
			}else if("avgAuditOrder".equals(value)){
				Collections.sort(firstList, new Comparator<SysOrg>() {
					public int compare(SysOrg o1, SysOrg o2) {
						Double avgAuditNum1=0.00;
						Double avgAuditNum2=0.00;
						if(null!=o1.getFillNum()&& !"".equals(o1.getFillNum())&& !"".equals(o1.getTrainDoctorTotal())&& null!=o1.getTrainDoctorTotal()){
							if(o1.getFillNum()==0|| o1.getTrainDoctorTotal()==0){
								avgAuditNum1=0.00;
							}else{
								avgAuditNum1 = Double.parseDouble(numberFormat.format((float) o1.getAuditNum().intValue() / (float) o1.getFillNum().intValue() /o1.getTrainDoctorTotal().intValue()));
							}
						}
						if(null!=o2.getFillNum()&& !"".equals(o2.getFillNum())&& !"".equals(o2.getTrainDoctorTotal())&& null!=o2.getTrainDoctorTotal()){
							if( o2.getFillNum()==0|| o2.getTrainDoctorTotal()==0){
								avgAuditNum2=0.00;
							}else{
								avgAuditNum2 = Double.parseDouble(numberFormat.format((float) o2.getAuditNum().intValue() / (float) o2.getFillNum().intValue() /o2.getTrainDoctorTotal().intValue()));
							}
						}
						//升序
						return avgAuditNum1.compareTo(avgAuditNum2);
					}
				});
				firstList.addAll(secondList);
			}
			return firstList;
		}
		return list;
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

	@RequestMapping("/monthDataMain")
	public String monthDataStatistics(Model model) {
		String monthDate = DateUtil.getMonth();
		model.addAttribute("month",monthDate);
		return "jsres/global/statistics/monthDataMain";
	}

	@RequestMapping("/monthDataStatistics")
	public String monthDataStatistics(String monthDate,Model model) {
		Map<String,Object> param = new HashMap<>();
		param.put("monthDate",monthDate);
		List<JsresDataStatistics> dataList = monthlyReportExtMapper2.searchDataStatistics(param);
		model.addAttribute("dataList",dataList);
		//查询平台访问量
		param.put("monthDate2",monthDate.replace("-",""));
		param.put("doctorRoleFlow",InitConfig.getSysCfg("res_doctor_role_flow"));
		List<String> teacherRoleFlows = new ArrayList<>();
		teacherRoleFlows.add(InitConfig.getSysCfg("res_teacher_role_flow"));
		teacherRoleFlows.add(InitConfig.getSysCfg("res_secretary_role_flow"));
		teacherRoleFlows.add(InitConfig.getSysCfg("res_head_role_flow"));
		param.put("teacherRoleFlows",teacherRoleFlows);
		param.put("orgRoleFlow",InitConfig.getSysCfg("res_admin_role_flow"));
		Map<String,Object> visitsMap = monthlyReportExtMapper2.searchVisitsList(param);
		model.addAttribute("visitsMap",visitsMap);
		//查询数据填写量
		List<JsresMonthStatistics> monthList = monthlyReportExtMapper2.searchMonthDataList(param);
		model.addAttribute("monthList",monthList);
		return "jsres/global/statistics/monthDataStatistics";
	}

	@RequestMapping("/exportMonthList")
	public void exportMonthList(String monthDate, HttpServletResponse response) throws Exception{
		Map<String,Object> param = new HashMap<>();
		param.put("monthDate",monthDate);
		List<JsresDataStatistics> dataList = monthlyReportExtMapper2.searchDataStatistics(param);
		//查询平台访问量
		param.put("monthDate2",monthDate.replace("-",""));
		param.put("doctorRoleFlow",InitConfig.getSysCfg("res_doctor_role_flow"));
		List<String> teacherRoleFlows = new ArrayList<>();
		teacherRoleFlows.add(InitConfig.getSysCfg("res_teacher_role_flow"));
		teacherRoleFlows.add(InitConfig.getSysCfg("res_secretary_role_flow"));
		teacherRoleFlows.add(InitConfig.getSysCfg("res_head_role_flow"));
		param.put("teacherRoleFlows",teacherRoleFlows);
		param.put("orgRoleFlow",InitConfig.getSysCfg("res_admin_role_flow"));
		Map<String,Object> visitsMap = monthlyReportExtMapper2.searchVisitsList(param);
		//查询数据填写量
		List<JsresMonthStatistics> monthList = monthlyReportExtMapper2.searchMonthDataList(param);

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);

		String fileName = "月度统计报表.xls";
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("学员出科统计");
		HSSFRow rowOne = sheet.createRow(0);//第一行
		String[] titles = new String[]{
				"基地名称",
				"住院医师出科考核次数",
				"在校专硕出科考核次数",
				"教学活动发布次数",
				"考勤签到次数"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowOne.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			cellTitle.setCellType(CellType.STRING);
			sheet.setColumnWidth(i, titles[i].getBytes().length * 800);
		}
		int rowNum = 1;
		String[] dList = null;
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				JsresDataStatistics dataStatistics = dataList.get(i);
				dList = new String[]{
						dataStatistics.getOrgName(),
						dataStatistics.getDoctorOutdeptData(),
						dataStatistics.getGraduateOutdeptData(),
						dataStatistics.getActivityData(),
						dataStatistics.getAttendanceData()
				};
				for (int j = 0; j < dList.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dList[j]);
				}
			}
		}

		// 为工作簿添加sheet
		HSSFSheet sheet2 = wb.createSheet("平台访问量");
		HSSFRow rowOne2 = sheet2.createRow(0);//第一行
		String[] titles2 = new String[]{
				"基地访问次数",
				"师资访问次数",
				"学员访问次数"
		};
		HSSFCell cellTitle2 = null;
		for (int i = 0; i < titles2.length; i++) {
			cellTitle2 = rowOne2.createCell(i);
			cellTitle2.setCellValue(titles2[i]);
			cellTitle2.setCellStyle(styleCenter);
			cellTitle2.setCellType(CellType.STRING);
			sheet2.setColumnWidth(i, titles2[i].getBytes().length * 800);
		}
		int rowNum2 = 1;
		HSSFRow rowFour2 = sheet2.createRow(rowNum2);//第二行
		String[] dList2 = new String[]{
				null == (BigDecimal)visitsMap.get("ORG_NUMBER") ? null : ((BigDecimal)visitsMap.get("ORG_NUMBER")).toPlainString(),
				null == (BigDecimal)visitsMap.get("TEACHER_NUMBER") ? null : ((BigDecimal)visitsMap.get("TEACHER_NUMBER")).toPlainString(),
				null == (BigDecimal)visitsMap.get("DOCTOR_NUMBER") ? null : ((BigDecimal)visitsMap.get("DOCTOR_NUMBER")).toPlainString()
		};
		for (int j = 0; j < dList2.length; j++) {
			HSSFCell cellFirst = rowFour2.createCell(j);
			cellFirst.setCellStyle(styleCenter);
			cellFirst.setCellValue(dList2[j]);
		}

		// 为工作簿添加sheet
		HSSFSheet sheet3 = wb.createSheet("学员填写量数据统计");
		HSSFRow rowOne3 = sheet3.createRow(0);//第一行
		String[] titles3 = new String[]{
				"基地名称",
				"住院医师学员数量",
				"住院医师填写数量",
				"住院医师审核数量",
				"在校专硕学员数量",
				"在校专硕填写数量",
				"在校专硕审核数量"
		};
		HSSFCell cellTitle3 = null;
		for (int i = 0; i < titles3.length; i++) {
			cellTitle3 = rowOne3.createCell(i);
			cellTitle3.setCellValue(titles3[i]);
			cellTitle3.setCellStyle(styleCenter);
			cellTitle3.setCellType(CellType.STRING);
			sheet3.setColumnWidth(i, titles3[i].getBytes().length * 800);
		}
		int rowNum3 = 1;
		String[] dList3 = null;
		if (monthList != null && monthList.size() > 0) {
			for (int i = 0; i < monthList.size(); i++, rowNum3++) {
				HSSFRow rowFour3 = sheet3.createRow(rowNum3);//第二行
				JsresMonthStatistics monthStatistics = monthList.get(i);
				dList3 = new String[]{
						monthStatistics.getOrgName(),
						monthStatistics.getDoctorNumber(),
						monthStatistics.getDoctorTotalNumber(),
						monthStatistics.getDoctorAuditNumber(),
						monthStatistics.getGraduateNumber(),
						monthStatistics.getGraduateTotalNumber(),
						monthStatistics.getGraduateAuditNumber()
				};
				for (int j = 0; j < dList3.length; j++) {
					HSSFCell cellFirst = rowFour3.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dList3[j]);
				}
			}
		}

		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}
/*
	@RequestMapping("/changePhyAcc")
	public String changePhyAcc(Model model,String type) {
		if (type.equals("phy")){	//住院医师
			//首页的echart表
			shouYeEchart(model,"inTraining","20",com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());	//在培人员情况
			shouYeEchart(model,"graduation","21",com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());	//结业人员情况
			shouYeEchart2(model,"recruitment",com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());	//结业人员情况
			return "jsres/global/globalIndex";
		}else if (type.equals("acc")){	//助理全科
			//首页的echart表
			shouYeEchartAcc(model,"inTraining","20",com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());	//在培人员情况
			shouYeEchartAcc(model,"graduation","21",com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());	//结业人员情况
			shouYeEchart2Acc(model,"recruitment",com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());	//结业人员情况
			return "jsres/global/globalIndexAcc";
		}
		return "/inx/jsres/login";
	}*/

	@RequestMapping("/cityChangePhyAcc")
	public String cityChangePhyAcc(String type,Model model, HttpServletRequest request) {
		int countryOrgCount = 0;//主基地的国家基地
		int jointOrgCount = 0;//协同基地的国家基地
		List<String> orgFlowList = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg searchOrg = new SysOrg();
		searchOrg.setOrgProvId("320000");
		searchOrg.setOrgCityId(org.getOrgCityId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
		for (SysOrg g : exitOrgs) {
			List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
			if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
				for (ResJointOrg jointOrg : resJointOrgList) {
					list.add(jointOrg.getJointOrgFlow());
				}
			}
			list.add(g.getOrgFlow());
		}

        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		InxInfo info = new InxInfo();
		PageHelper.startPage(1, getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos", infos);

		//培训医师数
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		List<String> cityIdList = new ArrayList<String>();
		cityIdList.add(org.getOrgCityId());
		if (StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))) {
			recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
		}
		int waitPassCount=0;

		if (type.equals("phy")){	//住院医师
			recruit.setCatSpeId("DoctorTrainingSpe");
			waitPassCount = jsResDoctorBiz.findWaitPassCountByOrgFlow(2, list);
		}else if (type.equals("acc")){	//助理全科
			recruit.setCatSpeId("AssiGeneral");
			waitPassCount = jsResDoctorBiz.findWaitPassCountByOrgFlow(2, list);
		}
		model.addAttribute("waitPassCount", waitPassCount);
		int passCount = resStatisticBiz.statisticDoctorCount(recruit, cityIdList);
		model.addAttribute("passCount", passCount);
		//待审核的数量
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		int waitCount = jsResDoctorRecruitBiz.searchBasePassCount(recruit, orgFlowList,year);
		model.addAttribute("waitCount", waitCount);
		//国家基地的数量
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgCityId(org.getOrgCityId());
        sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		countryOrgCount = resStatisticBiz.statisticCountyOrgCount(sysOrg);
		model.addAttribute("countryOrgCount", countryOrgCount);
		//协同基地的数量
		jointOrgCount = resStatisticBiz.statisticJointOrgCount(org);
		SysOrg sysOrg2 = new SysOrg();
		sysOrg2.setOrgProvId(org.getOrgProvId());
		sysOrg2.setOrgCityId(org.getOrgCityId());
        sysOrg2.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchOrg(sysOrg2);
		model.addAttribute("orgs", orgs);
		model.addAttribute("jointOrgCount", jointOrgCount);
		int provinceOrgCount = 0;//省级基地
        sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
		provinceOrgCount = resStatisticBiz.statisticCountyOrgCount(sysOrg);
		model.addAttribute("provinceOrgCount", provinceOrgCount);
		String cfgCode = "jswjw_" + user.getOrgFlow() + "_P003";
		SysCfg cfg = cfgBiz.read(cfgCode);
		init(model, cityIdList, null, list);
        if (cfg == null || com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue())) {
			if (type.equals("phy")){	//住院医师
				return "jsres/city/index";
			}else if (type.equals("acc")){	//助理全科
				return "jsres/city/indexAcc";
			}
		}
		return "jsres/city/cityIndex";
	}


	@RequestMapping("/localChangePhyAcc")
	public String localChangePhyAcc(String type,Model model, HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
        String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;

		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
            isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
			List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(sysuser.getOrgFlow());
			if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
				for (ResJointOrg jointOrg : resJointOrgList) {
					list.add(jointOrg.getJointOrgFlow());
				}
			}
		}
		list.add(sysuser.getOrgFlow());
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		InxInfo info = new InxInfo();
		PageHelper.startPage(1, getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos", infos);
		if (org != null) {
			model.addAttribute("orgFlow", org.getOrgFlow());
		}
		int speFlag = 0;
		ResDoctorOrgHistory history = new ResDoctorOrgHistory();

		//培训医师数
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		recruit.setOrgFlow(user.getOrgFlow());
		if (StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))) {
			recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
		}
		int passCount = resStatisticBiz.statisticDoctorCount(recruit, null);
		model.addAttribute("passCount", passCount);
		//待审核
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJointOrg)) {
			recruit.setJointOrgFlow(recruit.getOrgFlow());
			recruit.setOrgFlow("");
			recruit.setJointOrgAudit("Auditing");
        } else if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(isJointOrg)) {
			recruit.setJointOrgAudit("Passed");
		}
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		if (type.equals("phy")){	//住院医师
			recruit.setCatSpeId("DoctorTrainingSpe");
		}else if (type.equals("acc")){	//助理全科
			recruit.setCatSpeId("AssiGeneral");
		}
		int waitCount = jsResDoctorRecruitBiz.searchBasePassCount(recruit, null,year);
		model.addAttribute("waitCount", waitCount);
		int waitPassCount = jsResDoctorBiz.findWaitPassCountByOrgFlow(2, list);
		model.addAttribute("waitPassCount", waitPassCount);
		//专业和基地变更待办事项
		int baseFlag = 0;
		history.setOrgFlow(user.getOrgFlow());
        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		history.setOrgFlow("");
		history.setHistoryOrgFlow(user.getOrgFlow());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseTwo = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if ((baseOne != null && !baseOne.isEmpty()) || (baseTwo != null && !baseTwo.isEmpty())) {
			baseFlag = 1;
		}

        history.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
        history.setChangeStatusId(com.pinde.core.common.enums.JsResChangeApplySpeEnum.BaseWaitingAudit.getId());
		List<ResDoctorOrgHistory> spe = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if (spe != null && !spe.isEmpty()) {
			speFlag = 1;
		}
		model.addAttribute("speFlag", speFlag);
		model.addAttribute("baseFlag", baseFlag);
		String cfgCode = "jsres_" + user.getOrgFlow() + "_guocheng";
		JsresPowerCfg cfg = jsResPowerCfgBiz.read(cfgCode);

        if (cfg == null || com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue()) || !"Passed".equals(org.getCheckStatusId())) {
			return "jsres/hospital/index";
		}
		List<String> orgFlows = new ArrayList<>();
		orgFlows.add(user.getOrgFlow());
		init(model, orgFlows, "hosipital", list);
        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
			List<String> jointOrgFlowList = new ArrayList<>();
			if (jointOrgs != null && jointOrgs.size() > 0) {
				for (ResJointOrg join : jointOrgs) {
					jointOrgFlowList.add(join.getJointOrgFlow());
				}
			}
			List<SysOrg> orgs = new ArrayList<>();
			if (jointOrgFlowList != null && jointOrgFlowList.size() > 0) {
				orgs = orgBiz.searchOrgFlowIn(jointOrgFlowList);
			}
			orgs.add(org);
			model.addAttribute("orgs", orgs);
			model.addAttribute("countryOrg", "countryOrg");
		}
		//院级督导 -- 管理员配置功能权限
		JsresPowerCfg powerCfg = jsResPowerCfgBiz.read("jsres_hospital_yjdd_"+user.getOrgFlow());
        if (null != powerCfg && powerCfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
            model.addAttribute("hospitalSupervisor", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		if (type.equals("phy")){	//住院医师
			String filter = "('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'',key:orgFlow.keyword,negate:!f" +
					",params:(query:'" + user.getOrgFlow() + "'),type:phrase),query:(match_phrase:(orgFlow.keyword:'" + user.getOrgFlow() + "')))";
//			String iframe = "<iframe src=\"https://restest.njpdxx.com:5650/app/dashboards?auth_provider_hint=anonymous1#/view/41e46fe1-80c5-4f1b-bdba-c00822929a4b?embed=true" +
//					"&_g=(filters:!(" + filter + "),refreshInterval%3A(pause%3A!t%2Cvalue%3A60000)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))&hide-filter-bar=true\"  height=\"2100\" width=\"1460\"></iframe>";
			String iframe = "<iframe src=\"https://js.ezhupei.com:5601/app/dashboards?auth_provider_hint=anonymous1#/view/0525f6c0-e4dc-45d4-a25f-bc338e758934?embed=true" +
					"&_g=(filters:!(" + filter + "),refreshInterval%3A(pause%3A!t%2Cvalue%3A60000)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))&hide-filter-bar=true\" height=\"2100\" width=\"1460\"></iframe>";
			model.addAttribute("iframe", iframe);
            if (GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_NAME).equals("医院秘书")) {
				return "jsres/hospital/hospitalSecretaryIndex";
			}
			return "jsres/hospital/hospitalIndex";
		}else if (type.equals("acc")){	//助理全科
            if (GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_NAME).equals("医院秘书")) {
				return "jsres/hospital/hospitalSecretaryIndexAcc";
			}
			return "jsres/hospital/hospitalIndexAcc";
		}
		return "/inx/jsres/login";
	}

	/**
	 * @Department：研发部
	 * @Description 师资分配角色
	 * @Author fengxf
	 * @Date 2024/2/21
	 */
	@RequestMapping("/authRole")
	public String authRole(String userFlow,Model model) {
		model.addAttribute("userFlow", userFlow);
		// 查询用户已分配角色信息
        List<SysUserRole> userRoleList = userRoleBiz.getByUserFlowAndWsid(userFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
		List<String> roleFlowList = new ArrayList<>();
		if(userRoleList != null && userRoleList.size() > 0){
			roleFlowList = userRoleList.stream().map(userRole -> userRole.getRoleFlow()).collect(Collectors.toList());
		}
		model.addAttribute("roleFlowList", roleFlowList);
		return "jsres/hospital/authRole";
	}


	@RequestMapping("/deleteCommonSzInfo")
	@ResponseBody
	public String deleteCommonSzInfo(String recordFlow,String roleFlag,Model model){
		if(StringUtils.isEmpty(recordFlow)){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}
		//删除附件
		PubFileExample example = new PubFileExample();
		example.createCriteria().andProductFlowEqualTo(recordFlow).andProductTypeIn(Lists.newArrayList("szcgAttach","szzsAttach"));
		pubFileMapper.deleteByExample(example);
		//删除师资
		teacherTrainingMapper.deleteByPrimaryKey(recordFlow);
		//更新用户信息
		SysUser user = new SysUser();
		user.setUserFlow(recordFlow);
		user.setTeacherLevel("");
		user.setIsResponsibleTutor("");
		userBiz.saveUser(user);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

//	@RequestMapping("/deleteTutorInfo")
//	@ResponseBody
//	public String deleteTutorInfo(String recordFlow,String roleFlag,Model model){
//		if(StringUtils.isEmpty(recordFlow)){
//			return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
//		}
//		//删除附件
//		PubFileExample example = new PubFileExample();
//		example.createCriteria().andProductFlowEqualTo(recordFlow).andProductTypeIn(Lists.newArrayList("szcgAttach","szzsAttach"));
//		pubFileMapper.deleteByExample(example);
//		//删除师资
//		teacherTrainingMapper.deleteByPrimaryKey(recordFlow);
//		//更新用户信息
//		SysUser user = new SysUser();
//		user.setUserFlow(recordFlow);
//		user.setIsResponsibleTutor("");
//		userBiz.saveUser(user);
//		return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
//	}

	@RequestMapping("/editCommonSzInfo")
	public String editCommonSzInfo(String recordFlow,String roleFlag,Model model){
		if(StringUtils.isNotEmpty(recordFlow)){
			ResTeacherTraining teacherTraining=teacherTrainingMapper.selectDetailByKey(recordFlow);
			model.addAttribute("teacher",teacherTraining);
		}

		List<SysUserDept> userDepts = userBiz.searchUserDeptByUser(recordFlow);
		Map<String, String> sysUserDeptMap = userDepts.stream().collect(Collectors.toMap(SysUserDept::getDeptFlow, SysUserDept::getDeptFlow,
				(existing, replacement) -> existing));
		model.addAttribute("sysUserDeptMap", sysUserDeptMap);

		SysUser currUser = GlobalContext.getCurrentUser();
		SysDept sysDept = new SysDept();
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		sysDept.setOrgFlow(currUser.getOrgFlow());
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList", sysDeptList);

//		List<SysOrg> orgs=new ArrayList<SysOrg>();
//		SysOrg org=new SysOrg();
//		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
//		if(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
//			orgs.add(s);
//		}else if(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
//			org.setOrgProvId(s.getOrgProvId());
//			org.setOrgCityId(s.getOrgCityId());
//			org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//			orgs = orgBiz.searchAllSysOrg(org);
//		}else {
//			org.setOrgProvId(s.getOrgProvId());
//			org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
//			orgs = orgBiz.searchAllSysOrg(org);
//		}
//		model.addAttribute("roleFlag",roleFlag);
//		model.addAttribute("orgs", orgs);
		return "jsres/hospital/editCommonSzInfo";
	}

	@RequestMapping(value="/saveCommonSzInfo",method={RequestMethod.POST})
	@ResponseBody
	public String saveCommonSzInfo(ResTeacherTraining teacherTraining, String[] userDepts, ServletRequest request,String coverPhone){
		if(StringUtil.isBlank(teacherTraining.getRecordFlow())){
			// 判断用户phone是否存在
			if(StringUtils.isNotEmpty(teacherTraining.getUserPhone())) {
				SysUser oldUser = userBiz.findByUserPhone(teacherTraining.getUserPhone());
				if(oldUser!=null){
					//已结业的学员可用作师资账号
					ResDoctor resDoctor = resDoctorBiz.readDoctor(oldUser.getUserFlow());
					if(coverPhone==null){
						if(null == resDoctor || !"21".equals(resDoctor.getDoctorStatusId())){
                            return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
						}
                        oldUser.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
						oldUser.setUserPhone(oldUser.getUserPhone() + "_x"); // 因为手机号不允许重复，这里把手机号做个标记
						userBiz.edit(oldUser);
					}else {
						oldUser.setUserPhone("");
						userBiz.edit(oldUser);
					}
				}
			}
		}else{
			// 判断用户phone是否重复
			if(StringUtils.isNotEmpty(teacherTraining.getUserPhone())) {
				SysUser oldUser = userBiz.findByUserPhoneNotSelf(teacherTraining.getRecordFlow(), teacherTraining.getUserPhone());
				if(oldUser!=null){
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
				}
			}
		}
		// 用户信息
		SysUser user = new SysUser();
		user.setUserFlow(teacherTraining.getRecordFlow());
		user.setUserName(teacherTraining.getDoctorName());
		user.setUserPhone(teacherTraining.getUserPhone());
		user.setIdNo(teacherTraining.getIdNo());
		if(UserSexEnum.Man.getName().equals(teacherTraining.getSexName())){
			user.setSexId(UserSexEnum.Man.getId());
		}
		if(UserSexEnum.Woman.getName().equals(teacherTraining.getSexName())){
			user.setSexId(UserSexEnum.Woman.getId());
		}
		user.setSexName(teacherTraining.getSexName());
		SysUser sysUser = userBiz.readSysUser(teacherTraining.getRecordFlow());
		if (sysUser == null) {
			user.setUserCode(teacherTraining.getUserPhone());
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitConfig.getJxInitPassWord()));
			user.setStatusId(UserStatusEnum.Activated.getId());
			user.setStatusDesc(UserStatusEnum.Activated.getName());
			user.setOrgFlow(teacherTraining.getOrgFlow());
			user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
			GeneralMethod.setRecordInfo(user, true);
			sysUserMapper.insert(user);
		}else{
			userBiz.saveUser(user);
			user.setOrgFlow(sysUser.getOrgFlow());
		}

		// 师资
		List<SysDict> list = (List<SysDict>) request.getServletContext().getAttribute("dictTypeEnumDoctorTrainingSpeList");
		Map<String,String> map = list.stream().collect(Collectors.toMap(SysDict::getDictId, SysDict::getDictName, (key1, key2)-> key2));
		teacherTraining.setSpeName(map.get(teacherTraining.getSpeId()));
		resStatisticBiz.save(teacherTraining);

		// 科室
		List<String> allDeptFlows = new ArrayList<String>();
		if(userDepts!=null){
			allDeptFlows.addAll(Arrays.asList(userDepts));
		}
		if(allDeptFlows.size()>0){
			userBiz.addUserDept(user,allDeptFlows);
		}else {
			userBiz.disUserDept(user);
		}

		//打开app权限
		String cfgCode = "jsres_teacher_app_login_"+user.getUserFlow();
        String cfgValue = com.pinde.core.common.GlobalConstant.FLAG_Y;
		String cfgDesc = "是否开放带教app权限";
		JsresPowerCfg cfg = new JsresPowerCfg();
		cfg.setCfgCode(cfgCode);
		cfg.setCfgValue(cfgValue);
		cfg.setCfgDesc(cfgDesc);
        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		jsResPowerCfgBiz.save(cfg);

		SysRole sysRole = userRoleBiz.getByRoleName("带教老师");
		SysUserRole sysUserRole = userRoleBiz.readUserRole(user.getUserFlow(), sysRole.getRoleFlow());
		if (sysUserRole == null) {
			List<String> allRoleFlows = new ArrayList<String>();
			allRoleFlows.add(sysRole.getRoleFlow());
			for (String roleFlow : allRoleFlows) {
                userRoleBiz.saveSysUserRole(user.getUserFlow(), roleFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}


	@RequestMapping(value = {"/attachment"})
	public String attachment(Model model,String recFlow,String readonly,String recType){
		if(StringUtils.isEmpty(readonly)){
            readonly = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		model.addAttribute("readonly",readonly);
		model.addAttribute("recFlow",recFlow);
		model.addAttribute("recType",recType);
		List<PubFile> pubFiles = fileBiz.findFileByTypeFlow(recType,recFlow);
		model.addAttribute("pubFiles",pubFiles);
		return "jsres/hospital/attachment";
	}

	@RequestMapping(value="/attachmentUpload")
	@ResponseBody
	public Map<String,String> attachmentUpload(String recFlow, String recType , MultipartFile checkFile){
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isEmpty(recFlow)){
            map.put("status", com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG);
			return map;
		}
		if(checkFile!=null && checkFile.getSize() > 0){
			String resultPath = resStatisticBiz.saveFileToDirs("",checkFile,recType);

			String originalFilename = checkFile.getOriginalFilename();
			PubFile pubFile = new PubFile();
			pubFile.setFileFlow(PkUtil.getUUID());
            pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			pubFile.setFilePath(resultPath);
			pubFile.setFileName(originalFilename);
			pubFile.setFileSuffix(originalFilename.substring(originalFilename.lastIndexOf(".")));
			pubFile.setProductType(recType);
			pubFile.setProductFlow(recFlow);
			fileBiz.addFile(pubFile);

			map.put("url",InitConfig.getSysCfg("upload_base_url")+File.separator+resultPath);
			map.put("flow",pubFile.getFileFlow());
            map.put("status", com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG);
		}
		return map;
	}

	@RequestMapping(value="/attachmentDelete")
	@ResponseBody
	public String attachmentDelete(String recFlow, String fileFlow) {
		pubFileMapper.deleteByPrimaryKey(fileFlow);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
}
