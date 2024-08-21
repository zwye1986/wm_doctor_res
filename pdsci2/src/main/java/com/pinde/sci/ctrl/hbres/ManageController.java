package com.pinde.sci.ctrl.hbres;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.ExamManageBiz;
import com.pinde.sci.biz.hbres.IHbResDoctorRecruitBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.IJsResBaseBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.hbres.HbResDegreeCategoryEnum;
import com.pinde.sci.enums.jsres.JsResDoctorAuditStatusEnum;
import com.pinde.sci.enums.jsres.JsResTrainYearEnum;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.res.RecDocTypeEnum;
import com.pinde.sci.enums.res.RecruitTypeEnum;
import com.pinde.sci.enums.res.ResScoreTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.DateCfgMsg;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

import static com.pinde.sci.enums.res.RegStatusEnum.*;

@Controller
@RequestMapping("/hbres/singup")
public class ManageController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ManageController.class);
	public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";

	@Value("#{configProperties['remote.refresh.port']}")
	private String remoteRefreshPort;
	@Value("#{configProperties['remote.refresh.host']}")
	private String remoteRefreshHost;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IResOrgSpeAssignBiz speAssignBiz;
	@Autowired
	private INoticeBiz noticeBiz;
    @Autowired
	private IRecruitCfgBiz recruitCfgBiz;
    @Autowired
	private IHbResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private IResDoctorRecruitBiz resDoctorRecruitBiz;
    @Autowired
    private IResRegBiz resRegBiz;
    @Autowired
    private ExamManageBiz examManageBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IJsResBaseBiz baseBiz;
	
	/**
	 * 管理员主界面
	 */
	@RequestMapping(value="/manage/{role}")
	public String index(@PathVariable String role, Model model, Integer currentPage, HttpServletRequest request){
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage, getPageSize(request));
		info.setRoleFlow(GlobalConstant.ZL_NOTICE_SYS_ID);
		List<InxInfo> notices = noticeBiz.findNotice(info);
		model.addAttribute("messages" , notices);
		
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		
		if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) { //省级部门
			//待审核
			Map<String, Object> regParamMap = new HashMap<String, Object>();
			regParamMap.put("sessionNumber", regYear);
			List<String> regStatus = new ArrayList<String>();
			regStatus.add(Passing.getId());
			regParamMap.put("status", regStatus);
			regParamMap.put("categoryId", RecDocCategoryEnum.Doctor.getId());
			Integer regCount = this.resDoctorBiz.searchResDoctorUserCount(regParamMap);
			
			//总报名人数(提交后的所有学员)
			Map<String, Object> totleCountParamMap = new HashMap<String, Object>();
			totleCountParamMap.put("regYear", regYear);
			List<String> statusList = new ArrayList<String>();
			statusList.add(Passing.getId());
			statusList.add(Passed.getId());
			statusList.add(UnPassed.getId());
			totleCountParamMap.put("status", statusList);
			Integer totleCount = this.resDoctorBiz.searchResRegUserCount(totleCountParamMap);
			
			//审核通过
			Map<String, Object> activatedCountParamMap = new HashMap<String, Object>();
			activatedCountParamMap.put("regYear", regYear);
			List<String> activatedCountParamMapStatus = new ArrayList<String>();
			activatedCountParamMapStatus.add(Passed.getId());
			activatedCountParamMap.put("status", activatedCountParamMapStatus);
			Integer activatedCount = this.resDoctorBiz.searchResRegUserCount(activatedCountParamMap);
			ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);//招录时间信息

			model.addAttribute("regCount", regCount);
			model.addAttribute("totleCount", totleCount);
			model.addAttribute("activatedCount", activatedCount);
			model.addAttribute("recruitCfg", recruitCfg);
			model.addAttribute("curDate", DateUtil.getCurrDate());
			
			return "hbres/manage/index";
		} else if (GlobalConstant.USER_LIST_LOCAL.equals(role)) { //医院管理员
			//志愿填报数
			ResDoctorRecruit recruit = new ResDoctorRecruit();
			recruit.setOrgFlow(orgFlow);
			recruit.setRecruitYear(regYear);
			recruit.setRecruitTypeId(RecruitTypeEnum.Fill.getId());
			int volunNum = doctorRecruitBiz.searchDoctorNum(recruit);
			model.addAttribute("volunNum", volunNum);
			
			//复试数
			ResDoctorRecruit recruit1 = new ResDoctorRecruit();
			recruit1.setOrgFlow(orgFlow);
			recruit1.setRecruitYear(regYear);
			recruit1.setRecruitTypeId(RecruitTypeEnum.Fill.getId());
			recruit1.setRetestFlag(GlobalConstant.FLAG_Y);
			int retestNum = doctorRecruitBiz.searchDoctorNum(recruit1);
			model.addAttribute("retestNum", retestNum);
			
			//录取数
			ResDoctorRecruit recruit2 = new ResDoctorRecruit();
			recruit2.setOrgFlow(orgFlow);
			recruit2.setRecruitYear(regYear);
			recruit2.setRecruitFlag(GlobalConstant.FLAG_Y);
			recruit2.setConfirmFlag(GlobalConstant.FLAG_Y);
			int recruitNum = doctorRecruitBiz.searchDoctorNum(recruit2);
			model.addAttribute("recruitNum", recruitNum);

			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
			userRole.setWsId(GlobalConstant.RES_WS_ID);
			List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
			boolean isFreeAdmin=false;
			if(userRoleList!=null&&!userRoleList.isEmpty())
			{
				for(SysUserRole r:userRoleList)
				{
					SysRole sysRole = roleBiz.read(r.getRoleFlow());
					if (sysRole != null) {
						if(r.getRoleFlow().equals(InitConfig.getSysCfg("res_admin_role_flow_free")))
						{
							isFreeAdmin=true;
						}
					}
				}
			}
			model.addAttribute("isFreeAdmin",isFreeAdmin);
			return "hbres/hospital/index";
		}
		
		InxInfo temp = new InxInfo();
		PageHelper.startPage(1,6);
		List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBs(temp);
		model.addAttribute("infos",infos);
		return "/inx/hbres/login";
	}
	


	/**
	 * 一键审核通过
	 */
	@RequestMapping(value="/manage/auditAll")
	@ResponseBody
	public String auditAll(String [] recordLst){
		int count = 0;
		List<String> records = Arrays.asList(recordLst);
		for(int i=0;i<records.size();i++){
			count += resDoctorBiz.auditBatchDoctor(records.get(i));
		}
		if(count > 0){
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return  GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 学员重报页面
	 */
	@RequestMapping(value="/manage/stuReg")
	public String stuReg(Model model,String key){
		if(!StringUtil.isBlank(key)){
			Map<String , Object> parMp = new HashMap<String, Object>();
			parMp.put("key", key);
			List<Map<String, Object>> regStuLst = resDoctorBiz.searchRegStuLst(parMp);
            if(null != regStuLst && regStuLst.size() > 0){
                String doctorFlow = (String)regStuLst.get(0).get("userFlow");
                model.addAttribute("regLst",this.resRegBiz.searchAllResReg(doctorFlow));
                model.addAttribute("examLst",this.examManageBiz.searchExamByDoctor(doctorFlow));
                model.addAttribute("recruitLst",this.resDoctorRecruitBiz.searchRecruitByDoctor(doctorFlow));
				model.addAttribute("user", regStuLst.get(0));
			}
			model.addAttribute("key",key);
		}
		return "hbres/manage/stuReg";
	}

	/**
	 * 重报确认操作
	 */
	@RequestMapping(value="/manage/subOption")
	@ResponseBody
	public String subOption(Model model,String doctorFlow){
		ResDoctor doctor = new ResDoctor();
		doctor.setDoctorFlow(doctorFlow);
		doctor.setDoctorStatusId(UnSubmit.getId());
		doctor.setDoctorStatusName(UnSubmit.getName());
		this.resDoctorBiz.editDoctor(doctor);
		return GlobalConstant.OPERATE_SUCCESSED;
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping(value="/manage/userInfo")
	public String userInfo(String userFlow,Model model,String recordFlow/*报到记录号*/) throws DocumentException{
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			if(user!=null){
				model.addAttribute("user",user);
				
				ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
				model.addAttribute("doctor",doctor);
				PubUserResume pubUserResume = userResumeBiz.readPubUserResume(user.getUserFlow());
				if (pubUserResume != null) {
					String xmlContent = pubUserResume.getUserResume();
					if (StringUtil.isNotBlank(xmlContent)) {
						//xml转换成JavaBean
						model.addAttribute("extInfo", userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class));
					}
				}
			}
		}
		
		return "hbres/manage/infoAudit";
	}

	
	/**
	 * 用户退回
	 */
	@RequestMapping(value="/manage/withdrawUser")
	@ResponseBody
	public String withdrawUser(ResDoctor doctor,Model model){
		this.resDoctorBiz.withdrawDoctor(doctor);
		return GlobalConstant.OPERATE_SUCCESSED;
	}
	
	@RequestMapping(value="/hospital")
	public String hospital(Model model) throws Exception{
		SysOrgExample example = new SysOrgExample();
		example.createCriteria().andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		example.setOrderByClause("ordinal");
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList", orgList);
		return "hbres/manage/old_hospital";
	}
	
	@RequestMapping(value="/accounts")
	public String accounts(Model model) throws Exception{
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		SysLogExample example = new SysLogExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(user.getUserFlow());
		example.setOrderByClause("create_time desc");
		List<SysLog> logs = logMapper.selectByExample(example);
		if(logs!=null && logs.size()>0){
			model.addAttribute("log", logs.get(0));
		}
		return "hbres/accounts";
	}
	
	@RequestMapping("/noticemanage")
	public String noticeManage(Integer currentPage , Model model,HttpServletRequest request){
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,getPageSize(request));
		info.setColumnId(GlobalConstant.RES_NOTICE_TYPE_ID);
		info.setRoleFlow(GlobalConstant.ZL_NOTICE_SYS_ID);
		List<InxInfo> infos = this.noticeBiz.findNoticeWithBLOBsNotHaveRole(info);
		model.addAttribute("infos",infos);
		return "hbres/manage/notice";
	}
	
	@RequestMapping(value="/spe")
	public String spe(Model model,Integer currentPage,HttpServletRequest request) throws Exception{
		Map<String , Object> paramMap0 = new HashMap<String, Object>();
		paramMap0.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysDict> doctorTrainingSpeList = this.doctorRecruitBiz.searchTrainSpeList(paramMap0);
		model.addAttribute("doctorTrainingSpeList",doctorTrainingSpeList);
		return "hbres/manage/spe";
	}
	
	/**
	 * 基地维护
	 */
	@RequestMapping(value="/manage/orgCfg")
	public String orgCfg(String isCountry,Model model,Integer currentPage,HttpServletRequest request){
		SysOrgExample example = new SysOrgExample();
		SysOrgExample.Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(isCountry)){
			criteria.andOrgLevelIdEqualTo(OrgLevelEnum.CountryOrg.getId());
		}else {
			criteria.andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		}
		example.setOrderByClause("ORDINAL");
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList",orgList);
		return "hbres/manage/org";
	}
	
	/**
	 * 招录计划
	 */
	@RequestMapping(value="/manage/plan")
	public String plan(String assignYear , String source , String orgFlow , Model model,Integer currentPage,HttpServletRequest request,String fakeFlag){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("assignYear",assignYear);
		paramMap.put("orgFlow",orgFlow);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = speAssignBiz.getRecruitInfo(paramMap);
		List<Map<String,Object>> resultMapList2 = speAssignBiz.getRecruitInfo(paramMap);
		int totalNum = 0;
		if(resultMapList2!=null&&resultMapList2.size()>0){
			for(Map<String,Object> map:resultMapList2){
				String single = map.get("ASSIGN_PLAN_SUM").toString();
				totalNum+=Integer.parseInt(single);
			}
		}
		model.addAttribute("totalNum",totalNum);
		model.addAttribute("resultMapList",resultMapList);
		model.addAttribute("fakeFlag",fakeFlag);
		return "hbres/manage/plan";
	}

	@RequestMapping(value="/manage/planInfo")
	public String planInfo(String assignYear,String orgFlow,Model model){
		SysOrgExample example = new SysOrgExample();
		SysOrgExample.Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		example.setOrderByClause("ORDINAL");
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList",orgList);
		model.addAttribute("orgFlow",orgFlow);
		
		if(StringUtil.isNotBlank(assignYear)){
			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,assignYear);
			if(speAssignList!=null && speAssignList.size()>0){
				Map<String,ResOrgSpeAssign> speAssignMap = new HashMap<String,ResOrgSpeAssign>();
				for(ResOrgSpeAssign rosa : speAssignList){
					speAssignMap.put(rosa.getSpeId(),rosa);
				}
				model.addAttribute("speAssignMap",speAssignMap);
			}
		}
		
		return "hbres/manage/planInfo";
	}

	@RequestMapping(value="/manage/fakePlanInfo")
	public String fakePlanInfo(String assignYear,String orgFlow,Model model){
		model.addAttribute("orgFlow",orgFlow);
		if(StringUtil.isNotBlank(assignYear)){
			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,assignYear);
			if(speAssignList!=null && speAssignList.size()>0){
				Map<String,ResOrgSpeAssign> speAssignMap = new HashMap<String,ResOrgSpeAssign>();
				for(ResOrgSpeAssign rosa : speAssignList){
					speAssignMap.put(rosa.getSpeId(),rosa);
				}
				model.addAttribute("speAssignMap",speAssignMap);
			}
		}

		return "hbres/manage/fakePlanInfo";
	}

	/**
	 * 招录计划保存
	 */
	@RequestMapping(value="/manage/savePlan")
	@ResponseBody
	public String savePlan(ResOrgSpeAssign speAssign){
		if(speAssign!=null){
			if(StringUtil.isNotBlank(speAssign.getSpeId())){
				speAssign.setSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(speAssign.getSpeId()));
			}
			return speAssignBiz.queryOrgSpePlanFlow(speAssign);
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	@RequestMapping(value={"/modPasswd"})
	public String modPasswd(Model model){	
		return "hbres/manage/modPasswd";
	}
	
	/**
	 * 招录设置
	 */
	@RequestMapping(value="/manage/datecfg")
	public String dateCfg(Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		model.addAttribute("recruitCfg" , recruitCfg);
		
		String currDate = DateUtil.getCurrDate();
		DateCfgMsg regDateCfgMsg = new DateCfgMsg(recruitCfg);
		regDateCfgMsg.setRegDateMsg(currDate);
		
		DateCfgMsg printDateCfgMsg = new DateCfgMsg(recruitCfg);
		printDateCfgMsg.setPrintDateMsg(currDate);
		
		DateCfgMsg wishDateCfgMsg = new DateCfgMsg(recruitCfg);
		wishDateCfgMsg.setWishDateMsg(currDate);
		
		DateCfgMsg admitDateCfgMsg = new DateCfgMsg(recruitCfg);
		admitDateCfgMsg.setAdmitDateMsg(currDate);
		
		DateCfgMsg swapDateCfgMsg = new DateCfgMsg(recruitCfg);
		swapDateCfgMsg.setSwapDateMsg(currDate);

		DateCfgMsg registrationDateCfgMsg = new DateCfgMsg(recruitCfg);
		registrationDateCfgMsg.setRegistrationDateMsg(currDate);
		
		model.addAttribute("regDateCfgMsg" , regDateCfgMsg);
		model.addAttribute("printDateCfgMsg" , printDateCfgMsg);
		model.addAttribute("wishDateCfgMsg" , wishDateCfgMsg);
		model.addAttribute("admitDateCfgMsg" , admitDateCfgMsg);
		model.addAttribute("swapDateCfgMsg" , swapDateCfgMsg);
		model.addAttribute("registrationDateCfgMsg" , registrationDateCfgMsg);
		return "hbres/manage/dateCfg";
	}
	
	
	@RequestMapping("/manage/savedatecfg")
	@ResponseBody
	public String saveDateCfg(ResRecruitCfg recruitCfg){
		this.recruitCfgBiz.saveRecruitCfg(recruitCfg);
	    return GlobalConstant.OPERATE_SUCCESSED;	
	}
	
	@RequestMapping("/saveAdminMod")
	@ResponseBody
	public String saveAdminMod(SysUser user,ResDoctor doctor){
		//医师类型
		if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
			doctor.setDoctorTypeName(RecDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
		}
		//毕业院校
		String graduatedId = doctor.getGraduatedId();
		if(StringUtil.isNotBlank(graduatedId)){
			List<SysDict> graduatedNames = DictTypeEnum.sysListDictMap.get(DictTypeEnum.GraduateSchool.getId());
			for(SysDict dict:graduatedNames){
				String graduatedName = dict.getDictName();
				if(dict.getDictId().equals(graduatedId)){
					doctor.setGraduatedName(graduatedName);
				}
			}
		}
		//学历
		if(StringUtil.isNotBlank(user.getEducationId())) {
			user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
		} else {
			user.setEducationName("");
		}
		//学位
		if(StringUtil.isNotBlank(user.getDegreeId())){
			user.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
		}else{
			user.setDegreeName("");
		}

		if (StringUtil.isNotBlank(doctor.getDegreeCategoryId())) {
			doctor.setDegreeCategoryName(HbResDegreeCategoryEnum.getNameById(doctor.getDegreeCategoryId()));
		} else {
			doctor.setDegreeCategoryName("");
		}

		int result = resDoctorBiz.editDoctorUser(doctor, user);
		if(GlobalConstant.ZERO_LINE!=result){
			return GlobalConstant.SAVE_SUCCESSED;	
		}
	    return GlobalConstant.SAVE_FAIL;	
	}
	
	@RequestMapping("/resetDoctorRecruit")
	@ResponseBody
	public String resetDoctorRecruit(String doctorFlow){
		
		int result = resDoctorBiz.resetDoctorRecruit(doctorFlow);
		if(GlobalConstant.ZERO_LINE!=result){
			return GlobalConstant.OPRE_SUCCESSED;	
		}
	    return GlobalConstant.OPRE_FAIL;	
	}

	@RequestMapping("/adminSwapReport")
	public String adminSwapReport(Model model,Integer currentPage,HttpServletRequest request){
		String assignYear = InitConfig.getSysCfg("res_reg_year");
		//获取全部机构
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysOrg> orgList = orgBiz.searchHbresOrgList();
		model.addAttribute("orgList",orgList);

		//参数Map
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("assignYear",assignYear);

		getVacancies(paramMap,model);

		return "hbres/manage/swap/vacanciesSearchReport";
	}

	@RequestMapping("/orgSwapDocList")
	public String orgSwapDocList(String orgFlow,
								 /*String speId,*/
								 String userName,
								 String userPhone,
								 String userEmail,
								 String idNo,
								 String isSwap,
								 String isNotRecruitDoc,
								 Model model,
								 HttpServletRequest request,
								 Integer currentPage){
		String assignYear = InitConfig.getSysCfg("res_reg_year");
		//获取全部机构
		List<SysOrg> orgList = orgBiz.searchHbresOrgList();
		model.addAttribute("orgList",orgList);

		//参数Map
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("assignYear",assignYear);
		paramMap.put("orgFlow",orgFlow);

		getVacancies(paramMap,model);

//		paramMap.put("speId",speId);
		paramMap.put("userName",userName);
		paramMap.put("userPhone",userPhone);
		paramMap.put("userEmail",userEmail);
		paramMap.put("idNo",idNo);
		paramMap.put("isSwap",isSwap);
		paramMap.put("isNotRecruitDoc",isNotRecruitDoc);

		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> adminSwapDocList = doctorRecruitBiz.searchSwapDocList(paramMap);
		model.addAttribute("adminSwapDocList",adminSwapDocList);

		int notSwapNum=0;
		Map<String,Object> paramMap2 = new HashMap<String,Object>();
		paramMap2.put("assignYear",assignYear);
		paramMap2.put("orgFlow",orgFlow);
		getVacancies(paramMap2,model);
		List<Map<String,Object>> adminSwapDocList2 = doctorRecruitBiz.searchSwapDocList(paramMap2);
		paramMap2.put("isSwap",GlobalConstant.RECORD_STATUS_Y);
		List<Map<String,Object>> adminSwapDocList1 = doctorRecruitBiz.searchSwapDocList(paramMap2);
		if(adminSwapDocList2!=null){
			notSwapNum=adminSwapDocList2.size();
			if(adminSwapDocList1!=null){
				notSwapNum=notSwapNum-adminSwapDocList1.size();
			}
		}
		model.addAttribute("notSwapNum",notSwapNum);
		return "hbres/manage/swap/orgSwapDocList";
	}

	private void getVacancies(Map<String,Object> paramMap,Model model){
		//获取机构各专业下招录人数
		List<Map<String,Object>> docRecruitCounts = doctorRecruitBiz.getRecruitDocCount(paramMap);
		if(docRecruitCounts!=null && !docRecruitCounts.isEmpty()){
			Map<String,Object> recruitDocMap = new HashMap<String,Object>();
			for(Map<String,Object> map : docRecruitCounts){
				recruitDocMap.put((String)map.get("k"),map.get("v"));
			}
			model.addAttribute("recruitDocMap",recruitDocMap);
		}

		//获取各基地下各专业计划招录人数
		List<Map<String,Object>> speAssignCounts = doctorRecruitBiz.getSpeAssignCount(paramMap);
		if(speAssignCounts!=null && !speAssignCounts.isEmpty()){
			Map<String,Object> speAssignMap = new HashMap<String,Object>();
			for(Map<String,Object> map : speAssignCounts){
				speAssignMap.put((String)map.get("k"),map.get("v"));
			}
			model.addAttribute("speAssignMap",speAssignMap);
		}
	}

	@RequestMapping("/toSwap")
	public String toSwap(String doctorFlow,Model model){
		String assignYear = InitConfig.getSysCfg("res_reg_year");
		//获取全部机构
		List<SysOrg> orgList = orgBiz.searchHbresOrgList();
		model.addAttribute("orgList",orgList);
		//参数Map
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("assignYear",assignYear);
		getVacancies(paramMap,model);
		//将最新志愿面试成绩赋值到调剂后的志愿信息
		ResDoctorRecruit rdr = new ResDoctorRecruitExt();
		rdr.setDoctorFlow(doctorFlow);
		rdr.setRecruitYear(assignYear);
		ResDoctorRecruit recruit = resDoctorRecruitBiz.searchRecruitByResDoctor(rdr);
		model.addAttribute("recruit",recruit);
		return "hbres/manage/swap/operSwap";
	}

	@RequestMapping("/saveSwap")
	@ResponseBody
	public int saveSwap(ResDoctorRecruitWithBLOBs docRecruit,String msg){
		return doctorRecruitBiz.adminSwapRecruit(docRecruit,msg);
	}
	//招录统计
	@RequestMapping(value="/planStatistics")
	public String planStatistics(String itemId, String recruitYear, Model model,Integer currentPage,HttpServletRequest request){
		if(StringUtil.isNotBlank(recruitYear)){
			if("zltj".equals(itemId)){
				Map<String,Object> planMap = new HashMap<>();
				if(recruitYear.equals(DateUtil.getYear())){
					planMap = resDoctorRecruitBiz.queryPlanStatistics(recruitYear);
				}else{
					planMap = resDoctorRecruitBiz.queryPlanStatisticsBefore(recruitYear);
				}
				model.addAttribute("planMap",planMap);
			}else{
				PageHelper.startPage(currentPage,getPageSize(request));
				List<Map<String,Object>> orgMap = new ArrayList<>();
				if(recruitYear.equals(DateUtil.getYear())){
					orgMap = resDoctorRecruitBiz.queryOrgStatistics(recruitYear);
				}else{
					orgMap = resDoctorRecruitBiz.queryOrgStatisticsBefore(recruitYear);
				}
				model.addAttribute("orgMap",orgMap);
			}
		}
		return "hbres/manage/planStatistics";
	}
	//招录统计详情导出
	@RequestMapping("/exportStatistics")
	public void exportStatistics(String recruitYear,HttpServletResponse response) throws Exception {
//		List<Map<String,Object>> orgMap = resDoctorRecruitBiz.queryOrgStatistics(recruitYear);
		List<Map<String,Object>> orgMap = new ArrayList<>();
		if(recruitYear.equals(DateUtil.getYear())){
			orgMap = resDoctorRecruitBiz.queryOrgStatistics(recruitYear);
		}else{
			orgMap = resDoctorRecruitBiz.queryOrgStatisticsBefore(recruitYear);
		}
		String[] titles = new String[]{
				"ORG_CODE:培训基地代码",
				"ORG_NAME:培训基地名称",
				"ASSIGN_PLAN:计划招收人数",
				"TBZY_NUM:填报志愿人数",
				"TZFS_NUM:通知复试人数",
				"QRLQ_NUM:确认录取人数",
				"STTJ_NUM:省厅调剂人数"
		};
		String fileName = "招录信息详情.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, orgMap, response.getOutputStream());

	}
	//省厅导入招录计划
	@RequestMapping(value={"/importExcel"},method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> importExcel(MultipartFile file) throws Exception{
		if(file.getSize() > 0){
			return speAssignBiz.importExcel(file);
		}
		return null;
	}
	//基地导入复试成绩单
	@RequestMapping(value={"/impOperExcel"},method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> impOperExcel(MultipartFile file) throws Exception{
		if(file.getSize() > 0){
			return speAssignBiz.impOperExcel(file);
		}
		return null;
	}

	@RequestMapping("/exportSwapDocList")
	public void exportSwapDocList(String orgFlow,
								 String userName,
								 String userPhone,
								 String userEmail,
								 String idNo,
								 String isSwap,
								 String isNotRecruitDoc, HttpServletResponse response) throws Exception {
		String assignYear = InitConfig.getSysCfg("res_reg_year");

		//参数Map
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("assignYear",assignYear);
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("userName",userName);
		paramMap.put("userPhone",userPhone);
		paramMap.put("userEmail",userEmail);
		paramMap.put("idNo",idNo);
		paramMap.put("isSwap",isSwap);
		paramMap.put("isNotRecruitDoc",isNotRecruitDoc);

		List<Map<String,Object>> adminSwapDocList = doctorRecruitBiz.searchSwapDocList(paramMap);

		String[] titles = new String[]{
				"userName:姓名",
				"examResult:笔试成绩",
				"doctorTypeName:人员类型",
				"workOrgName:工作单位",
				"qualifiedNo:执业医师资格证号",
				"swapOrgName:调剂培训基地",
				"swapSpeName:调剂专业"
		};
		String fileName = "缺额调剂信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, adminSwapDocList, response.getOutputStream());
	}

//	/**
//	 * 省厅招录统计
//	 * @param sessionNumber
//	 * @return
//	 */
//	@RequestMapping(value={"/zlxytj"})
//	public String zlxytj(String sessionNumber,Model model){
//
//		List<Map<String,String>> citys=new ArrayList<>();
//		Map<String,String> city=new HashMap<>();
//		city.put("cityId", "320100");
//		city.put("cityName", "南京市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "320200");
//		city.put("cityName", "无锡市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "320300");
//		city.put("cityName", "徐州市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "320400");
//		city.put("cityName", "常州市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "320500");
//		city.put("cityName", "苏州市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "320600");
//		city.put("cityName", "南通市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "320700");
//		city.put("cityName", "连云港市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "320800");
//		city.put("cityName", "淮安市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "320900");
//		city.put("cityName", "盐城市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "321000");
//		city.put("cityName", "扬州市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "321100");
//		city.put("cityName", "镇江市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "321200");
//		city.put("cityName", "泰州市");
//		citys.add(city);
//		city = new HashMap<>();
//		city.put("cityId", "321300");
//		city.put("cityName", "宿迁市");
//		citys.add(city);
//		List<Map<String,Object>> list=jsResDoctorRecruitBiz.zlxytj(sessionNumber);
//		Map<String,List<String>> citySessionMap=new HashMap<>();
//		Map<String,Object> citySessionNumMap=new HashMap<>();
//		Map<String,Integer> cityNumMap=new HashMap<>();
//		Map<String,Integer> typeNumMap=new HashMap<>();
//		if(list!=null)
//		{
//			for(Map<String,Object> map:list)
//			{
//				//每个城市有多个届别
//				String cityId= (String) map.get("cityId");
//				String sessionNumber2= (String) map.get("sessionNumber");
//				String typeId= (String) map.get("typeId");
//				List<String> citySessionNumbers=citySessionMap.get(cityId);
//				if(citySessionNumbers==null)
//					citySessionNumbers=new ArrayList<>();
//				if(!citySessionNumbers.contains(sessionNumber2))
//				{
//					citySessionNumbers.add(sessionNumber2);
//				}
//				citySessionMap.put(cityId,citySessionNumbers);
//
//				citySessionNumMap.put(cityId+sessionNumber2+typeId,map.get("num"));
//
//				Integer sum=cityNumMap.get(cityId);
//				if(sum==null)
//					sum=0;
//				sum+=(Integer) map.get("num");
//				cityNumMap.put(cityId,sum);
//
//				Integer sum2=typeNumMap.get(typeId);
//				if(sum2==null)
//					sum2=0;
//				sum2+=(Integer) map.get("num");
//				typeNumMap.put(typeId,sum2);
//
//			}
//		}
//		model.addAttribute("citySessionMap",citySessionMap);
//		model.addAttribute("citySessionNumMap",citySessionNumMap);
//		model.addAttribute("cityNumMap",cityNumMap);
//		model.addAttribute("typeNumMap",typeNumMap);
//		model.addAttribute("citys",citys);
//		return "jsres/manage/zlxytj";
//	}

	/**
	 * 湖北结业管理系统 管理员主界面
	 */
	@RequestMapping(value="/graduate/manage/{role}")
	public String graduateIndex(@PathVariable String role, Integer currentPage, HttpServletRequest request, Model model){
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		if (GlobalConstant.USER_LIST_GLOBAL.equals(role)){
			//省级部门
			return "hbres/manage/jyIndex";
		}else if (GlobalConstant.USER_LIST_CHARGE.equals(role)){
			//市局
			return "hbres/city/jyIndex";
		}else if (GlobalConstant.USER_LIST_LOCAL.equals(role)){
			//医院管理员
			return "hbres/hospital/jyIndex";
		}
		return "/inx/hbres/login";
	}

	@RequestMapping(value="/doctorTheoryList")
	public String doctorTheoryList(Model model,String roleFlag){
		SysUser sysuser= GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			sysorg.setOrgCityId(org.getOrgCityId());
		}
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg>  provinceList=orgBiz.searchOrg(sysorg);

		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("countryList", countryList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		//查询库中最新成绩的年份
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("scoreTypeId","TheoryScore");
		String lastYear = resScoreBiz.findLastYearByScoreTypeId(paramMap);
		model.addAttribute("lastYear", lastYear);
		//如果是国家基地，基地和协同  如果不是国家基地，那只有本基地
		model.addAttribute("org",org);
		if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())){
			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
			model.addAttribute("jointOrgList",jointOrgList);
		}
		return  "hbres/graduate/doctorTheoryList";
	}
	@RequestMapping(value="/doctorSkillList")
	public String doctorSkillList(Model model,String roleFlag){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			sysorg.setOrgCityId(org.getOrgCityId());
		}
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg>  provinceList=orgBiz.searchOrg(sysorg);

		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("countryList", countryList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		//查询库中最新成绩的年份
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("scoreTypeId","SkillScore");
		String lastYear = resScoreBiz.findLastYearByScoreTypeId(paramMap);
		model.addAttribute("lastYear", lastYear);
		return  "hbres/graduate/doctorSkillList";
	}
	/**
	 * 根据条件查询
	 * @param model
	 * @param currentPage 分页
	 * @param roleFlag
	 * @param request
	 * @param doctor
	 * @param user
	 * @param baseId
	 * @param jointOrgFlag 协同基地
	 * @param derateFlag 显示减免
	 * @param orgLevel  基地类型
	 * @param datas  人员类型
	 * @param graduationYear 结业考核年份
	 * @param scoreYear 成绩年份
	 * @param isHege 是否合格
	 * @return
	 */
	@RequestMapping(value="/doctorTheoryListSun")
	public String doctorRecruitSun(Model model,Integer currentPage,String roleFlag,
								   HttpServletRequest request,ResDoctor doctor,SysUser user,
								   String baseId,String jointOrgFlag,
								   String derateFlag,String orgLevel,String[] datas,
								   String graduationYear,String scoreYear,String isHege,String skillIsHege){
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		//协同机构
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		//培训基地
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			//培训基地
			if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
			if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)||(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag))) {
				SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(sysuser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
					if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
						searchOrg.setOrgCityId(sysOrg.getOrgCityId());
					}
					//基地类型
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					org.setOrgProvId(sysOrg.getOrgProvId());
					if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
						org.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
				}
			}
		}else if("allOrgs".equals(doctor.getOrgFlow())){
			if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
				jointOrgFlowList=searchJointOrgList(GlobalConstant.FLAG_Y,sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			//是否勾选了协同机构
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}
		//证件号
		if(StringUtil.isNotBlank(user.getIdNo())){
			sysUser.setIdNo(user.getIdNo());
		}
		//姓名
		sysUser.setUserName(user.getUserName());
		//显示减免
		if(StringUtil.isNotBlank(derateFlag) && GlobalConstant.FLAG_Y.equals(derateFlag)){
			doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
			resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
		}else{
			derateFlag="";
		}
		//人员类型
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		//根据年份从res_pass_score_cfg取数据
		ResPassScoreCfg cfg = new ResPassScoreCfg();
		cfg.setCfgYear(scoreYear);
		ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
		String hegeScore="60";
		if(resPassScoreCfg!=null){
			hegeScore = resPassScoreCfg.getCfgPassScore();
			if(StringUtil.isBlank(hegeScore)){
				hegeScore="60";
			}
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<JsResDoctorRecruitExt> doctorList=null;
		if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
			ServletContext servletContext=request.getServletContext();
			if (servletContext!=null) {
				List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
				for (SysDict sysDict : sysDictList) {
					String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
					String orgFlow=InitConfig.getSysCfg(dictId);
					doctor.setWorkOrgId(sysDict.getDictId());
					if (sysuser.getOrgFlow().equals(orgFlow)) {
						doctorList=doctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore);
						break;
					}
					continue;
				}
			}
		}else if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			doctorList=doctorRecruitBiz.searchDoctorSkillAndTheoryScoreExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,skillIsHege,docTypeList,hegeScore);
		}else{
			doctorList = doctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore);
		}
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		model.addAttribute("scoreYear",scoreYear);
		return  "hbres/graduate/doctorListZi";
	}
	public List<String> searchJointOrgList(String flag,String Flow) {
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(GlobalConstant.FLAG_Y.equals(flag)){
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
	 * 理论成绩导入
	 */
	@RequestMapping(value="/importSchoolRollFromExcel")
	@ResponseBody
	public String importSchoolRollFromExcel(MultipartFile file,String scoreYear){
		if(StringUtil.isBlank(scoreYear))
		{
			return SCOREYEAR_NOT_FIND;
		}
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) resDoctorBiz.importCourseFromExcel(file, scoreYear, scoreYear);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
						return GlobalConstant.UPLOAD_FAIL+msg;
					}else{
						if(GlobalConstant.ZERO_LINE != count){
							return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
						}else{
							return GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	/**
	 * 根据条件查询
	 * @param model
	 * @param currentPage 分页
	 * @param roleFlag
	 * @param request
	 * @param doctor
	 * @param user
	 * @param cityId
	 * @param jointOrgFlag 协同基地
	 * @param derateFlag 显示减免
	 * @param orgLevel  基地类型
	 * @param datas  人员类型
	 * @param graduationYear 结业考核年份
	 * @param scoreYear 成绩年份
	 * @param isHege 是否合格
	 * @return
	 */
	@RequestMapping(value="/doctorSkillListSun")
	public String doctorSkillListSun(Model model,Integer currentPage,String roleFlag,
									 HttpServletRequest request,ResDoctor doctor,SysUser user,
									 String cityId,String jointOrgFlag,
									 String derateFlag,String orgLevel,String[] datas,
									 String graduationYear,String scoreYear,String isHege) throws DocumentException {
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		//协同机构
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		//培训基地
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			//培训基地
			if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
				jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
			if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag) ||GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag) ||GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
				SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(sysuser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
					if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
						searchOrg.setOrgCityId(sysOrg.getOrgCityId());
					}
					//基地类型
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					if(StringUtil.isNotEmpty(cityId)){
						org.setOrgCityId(cityId);
					}else{
						org.setOrgProvId(sysOrg.getOrgProvId());
						if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
							org.setOrgCityId(sysOrg.getOrgCityId());
						}
						if(StringUtil.isNotBlank(orgLevel)){
							org.setOrgLevelId(orgLevel);
							org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
						}
					}
				}
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			//是否勾选了协同机构
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}
		//证件号
		if(StringUtil.isNotBlank(user.getIdNo())){
			sysUser.setIdNo(user.getIdNo());
		}
		//姓名
		sysUser.setUserName(user.getUserName());
		//显示减免
		if(StringUtil.isNotBlank(derateFlag) && GlobalConstant.FLAG_Y.equals(derateFlag)){
			doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
			resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
		}else{
			derateFlag="";
		}
		//人员类型
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		//成绩类型为技能成绩
		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<JsResDoctorRecruitExt> doctorList=null;
		if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
			ServletContext servletContext=request.getServletContext();
			if (servletContext!=null) {
				List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
				for (SysDict sysDict : sysDictList) {
					String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
					String orgFlow=InitConfig.getSysCfg(dictId);
					doctor.setWorkOrgId(sysDict.getDictId());
					if (sysuser.getOrgFlow().equals(orgFlow)) {
						doctorList=doctorRecruitBiz.searchDoctorSkillScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList);
						break;
					}
					continue;
				}
			}
		}else{
			doctorList = doctorRecruitBiz.searchDoctorSkillScore(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList);
		}
		Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
		for(int i=0;i<doctorList.size();i++)
		{
			Map<String,String> extScore=new HashMap<String,String>();
			JsResDoctorRecruitExt ext=doctorList.get(i);
			ResScore resScore=ext.getResScore();
			String content = null==resScore ? "":resScore.getExtScore();
			if(StringUtil.isNotBlank(content)) {
				Document doc = DocumentHelper.parseText(content);
				Element root = doc.getRootElement();
				Element extScoreInfo = root.element("extScoreInfo");
				if (extScoreInfo != null) {
					List<Element> extInfoAttrEles = extScoreInfo.elements();
					if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
						for (Element attr : extInfoAttrEles) {
							String attrName = attr.getName();
							String attrValue = attr.getText();
							extScore.put(attrName, attrValue);
						}
					}
				}
			}
			extScoreMap.put(resScore.getScoreFlow(),extScore);
		}
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("extScoreMap",extScoreMap);
		model.addAttribute("datas",datas);
		model.addAttribute("scoreYear",scoreYear);
		return  "hbres/graduate/doctorSkillListZi";
	}
	@RequestMapping(value="/deleteScore")
	@ResponseBody
	public String deleteScore(String scoreFlow,String roleFlag){
		ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
		if(resScore!=null){
			resScore.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result=resScoreBiz.save(resScore);
			if(result==GlobalConstant.ONE_LINE){
				return GlobalConstant.FLAG_Y;
			}
		}
		return GlobalConstant.FLAG_N;
	}
	@RequestMapping(value="/deleteSkillScore")
	@ResponseBody
	public String deleteSkillScore(String scoreFlow,String roleFlag){

		ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
		if(resScore!=null){
			resScore.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result=resScoreBiz.save(resScore);
			if(result==GlobalConstant.ONE_LINE){
				return GlobalConstant.FLAG_Y;
			}
		}
		return GlobalConstant.FLAG_N;
	}
	@RequestMapping(value="/saveTheoryScore")
	@ResponseBody
	public String saveScore(ResScore score,String roleFlag){
		if(score==null){
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		String scoreFlow = score.getScoreFlow();
		if(StringUtil.isBlank(scoreFlow))
		{
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		int result = resScoreBiz.save(score);
		if(GlobalConstant.ZERO_LINE==result){
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	@RequestMapping(value="/saveSkillIsPass")
	@ResponseBody
	public String saveSkillIsPass(ResScore score,String roleFlag,String stationName,String isPass) throws DocumentException {
		if(score==null){
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		String scoreFlow = score.getScoreFlow();
		if(StringUtil.isBlank(scoreFlow))
		{
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		if(StringUtil.isBlank(isPass))
		{
			return GlobalConstant.OPRE_FAIL_FLAG;
		}

		if("1".equals(isPass))
		{
			score.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.PASS)));
		}else if("0".equals(isPass))
		{
			score.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.UNPASS)));
		}
		int result = resScoreBiz.save(score);
		if(GlobalConstant.ZERO_LINE==result){
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	/**
	 * 技能成绩导入
	 */
	@RequestMapping(value="/importSkillScoreFromExcel")
	@ResponseBody
	public String importSkillScoreFromExcel(MultipartFile file,String scoreYear){
		if(StringUtil.isBlank(scoreYear))
		{
			return SCOREYEAR_NOT_FIND;
		}
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) resDoctorBiz.importSkillScoreFromExcel(file, scoreYear, scoreYear);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
						return GlobalConstant.UPLOAD_FAIL+msg;
					}else{
						if(GlobalConstant.ZERO_LINE != count){
							return GlobalConstant.UPLOAD_SUCCESSED + "导入"+count+"条记录！";
						}else{
							return GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value="/scoreDetail")
	public String scoreDetail(ResScore score,String scoreType,String scoreYear,Model model) throws DocumentException {
		List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(score.getDoctorFlow());
		//技能成绩
		List<ResScore> skillList=new ArrayList<ResScore>();
		List<ResScore> theoryList=new ArrayList<ResScore>();
		List<ResScore> skillListByYear=new ArrayList<ResScore>();

		if(null!=scorelist&&scorelist.size()>0)
		{
			for(ResScore resScore:scorelist)
			{
				if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())&&StringUtil.isNotBlank(scoreYear)&&scoreYear.equals(resScore.getScorePhaseId()))
				{
					skillListByYear.add(resScore);
				}else if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
				{
					skillList.add(resScore);
				}else if(ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
				{
					theoryList.add(resScore);
				}
			}
		}
		if(skillListByYear.size()>0)
		{
			skillList=skillListByYear;
		}
		Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
		for(int i=0;i<skillList.size();i++)
		{
			Map<String,String> extScore=new HashMap<String,String>();
			ResScore resScore=skillList.get(i);
			String content = null==resScore ? "":resScore.getExtScore();
			if(StringUtil.isNotBlank(content)) {
				Document doc = DocumentHelper.parseText(content);
				Element root = doc.getRootElement();
				Element extScoreInfo = root.element("extScoreInfo");
				if (extScoreInfo != null) {
					List<Element> extInfoAttrEles = extScoreInfo.elements();
					if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
						for (Element attr : extInfoAttrEles) {
							String attrName = attr.getName();
							String attrValue = attr.getText();
							extScore.put(attrName, attrValue);
						}
					}
				}
			}
			extScoreMap.put(resScore.getScoreFlow(),extScore);
		}
		ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(score.getDoctorFlow());
		SysUser sysUser=userBiz.readSysUser(score.getDoctorFlow());
		model.addAttribute("user",sysUser);
		model.addAttribute("resDoctor",resDoctor);
		model.addAttribute("skillList",skillList);
		model.addAttribute("theoryList",theoryList);
		model.addAttribute("extScoreMap",extScoreMap);
		model.addAttribute("scoreType",scoreType);
		return "hbres/graduate/skillScoreDetail";
	}
	//根据条件打印结业成绩
	@RequestMapping(value="/doctorTheoryListSunExport")
	public void doctorTheoryListSunExport(String roleFlag,HttpServletResponse response,
										  HttpServletRequest request,ResDoctor doctor,SysUser user,
										  String baseId,String jointOrgFlag,
										  String derateFlag,String orgLevel,String[] datas,
										  String graduationYear,String scoreYear,String isHege,String skillIsHege) throws Exception {
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		//协同机构
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		//培训基地
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			//培训基地
			if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
			if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag) ||GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag) ||GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
				SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(sysuser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
					if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
						searchOrg.setOrgCityId(sysOrg.getOrgCityId());
					}
					//基地类型
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					org.setOrgProvId(sysOrg.getOrgProvId());
					if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
						org.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
				}
			}
		}else if("allOrgs".equals(doctor.getOrgFlow())){
			if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
				jointOrgFlowList=searchJointOrgList(GlobalConstant.FLAG_Y,sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			//是否勾选了协同机构
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}
		//证件号
		if(StringUtil.isNotBlank(user.getIdNo())){
			sysUser.setIdNo(user.getIdNo());
		}
		//姓名
		sysUser.setUserName(user.getUserName());
		//显示减免
		if(StringUtil.isNotBlank(derateFlag)&&GlobalConstant.FLAG_Y.equals(derateFlag)){
			doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
			resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
		}else{
			derateFlag="";
		}
		//人员类型
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		//根据年份从res_pass_score_cfg取数据
		ResPassScoreCfg cfg = new ResPassScoreCfg();
		cfg.setCfgYear(scoreYear);
		ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
		String hegeScore="60";
		if(resPassScoreCfg!=null){
			hegeScore = resPassScoreCfg.getCfgPassScore();
			if(StringUtil.isBlank(hegeScore)){
				hegeScore="60";
			}
		}

		resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<JsResDoctorRecruitExt> doctorList=null;
		if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
			ServletContext servletContext=request.getServletContext();
			if (servletContext!=null) {
				List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
				for (SysDict sysDict : sysDictList) {
					String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
					String orgFlow=InitConfig.getSysCfg(dictId);
					doctor.setWorkOrgId(sysDict.getDictId());
					if (sysuser.getOrgFlow().equals(orgFlow)) {
						doctorList=doctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore);
						break;
					}
					continue;
				}
			}
		}else if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			doctorList=doctorRecruitBiz.searchDoctorSkillAndTheoryScoreExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,skillIsHege,docTypeList,hegeScore);
		}else{
			doctorList = doctorRecruitBiz.searchDoctorScoreInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,scoreYear,isHege,docTypeList,hegeScore);
		}
		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(doctorList!=null&&doctorList.size()>0){
			for(JsResDoctorRecruitExt doctorRecruitExt:doctorList){
				String doctorName = doctorRecruitExt.getSysUser().getUserName();
				String idNo = doctorRecruitExt.getSysUser().getIdNo();
				String orgName = doctorRecruitExt.getOrgName();
				String sessionNumber = doctorRecruitExt.getSessionNumber();
				String catSpeName = doctorRecruitExt.getCatSpeName();
				String speName = doctorRecruitExt.getSpeName();
				String year = scoreYear;
				String theoryScore = null==doctorRecruitExt.getResScore().getTheoryScore()?"":doctorRecruitExt.getResScore().getTheoryScore()+"";
				String firstStationScore = "";
				String secondStationScore = "";
				String thirdStationScore = "";
				String fourthStationScore = "";
				String fifthStationScore = "";
				String sixthStationScore = "";
				String seventhStationScore = "";
				String eighthStationScore = "";
				String ninthStationScore = "";
				String all = "";
				String isPass = "";
				//技能成绩
				List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(doctorRecruitExt.getSysUser().getUserFlow());
				List<ResScore> skillList=new ArrayList<ResScore>();
				List<ResScore> theoryList=new ArrayList<ResScore>();
				List<ResScore> skillListByYear=new ArrayList<ResScore>();

				if(null!=scorelist&&scorelist.size()>0)
				{
					for(ResScore resScore:scorelist)
					{

						if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())&&StringUtil.isNotBlank(scoreYear)&&scoreYear.equals(resScore.getScorePhaseId()))
						{
							skillListByYear.add(resScore);
						}else if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
						{
							skillList.add(resScore);
						}else if(ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
						{
							theoryList.add(resScore);
						}
					}
				}
				if(skillListByYear.size()>0)
				{
					skillList=skillListByYear;
				}
				Map<String,Map<String,String>> extScoreMap=new HashMap<String,Map<String,String>>();
				for(int i=0;i<skillList.size();i++)
				{
					Map<String,String> extScore=new HashMap<String,String>();
					ResScore resScore=skillList.get(i);
					String content = null==resScore ? "":resScore.getExtScore();
					if(StringUtil.isNotBlank(content)) {
						Document doc = DocumentHelper.parseText(content);
						Element root = doc.getRootElement();
						Element extScoreInfo = root.element("extScoreInfo");
						if (extScoreInfo != null) {
							List<Element> extInfoAttrEles = extScoreInfo.elements();
							if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
								for (Element attr : extInfoAttrEles) {
									String attrName = attr.getName();
									String attrValue = attr.getText();
									extScore.put(attrName, attrValue);
								}
							}
						}
					}
					extScoreMap.put(resScore.getScoreFlow(),extScore);
				}
				for(ResScore score:skillList){
					Map<String,String> extScore = extScoreMap.get(score.getScoreFlow());
					firstStationScore = extScore.get("firstStationScore");
					secondStationScore = extScore.get("secondStationScore");
					thirdStationScore = extScore.get("thirdStationScore");
					fourthStationScore = extScore.get("fourthStationScore");
					fifthStationScore = extScore.get("fifthStationScore");
					sixthStationScore = extScore.get("sixthStationScore");
					seventhStationScore = extScore.get("seventhStationScore");
					eighthStationScore = extScore.get("eighthStationScore");
					ninthStationScore = extScore.get("ninthStationScore");
					double all0 = Double.parseDouble(StringUtil.defaultIfEmpty(firstStationScore,"0"))+
							Double.parseDouble(StringUtil.defaultIfEmpty(secondStationScore,"0"))+
							Double.parseDouble(StringUtil.defaultIfEmpty(thirdStationScore,"0"))+
							Double.parseDouble(StringUtil.defaultIfEmpty(fourthStationScore,"0"))+
							Double.parseDouble(StringUtil.defaultIfEmpty(fifthStationScore,"0"))+
							Double.parseDouble(StringUtil.defaultIfEmpty(sixthStationScore,"0"))+
							Double.parseDouble(StringUtil.defaultIfEmpty(seventhStationScore,"0"))+
							Double.parseDouble(StringUtil.defaultIfEmpty(eighthStationScore,"0"))+
							Double.parseDouble(StringUtil.defaultIfEmpty(ninthStationScore,"0"));
					DecimalFormat df = new DecimalFormat("0.0");
					all = df.format(all0);
					isPass = score.getSkillScore().intValue()==(GlobalConstant.PASS)?"是":(score.getSkillScore().intValue()==(GlobalConstant.UNPASS)?"否":"");
				}
				Map<String,Object> resultMap = new HashMap<>();
				resultMap.put("doctorName",doctorName);
				resultMap.put("idNo",idNo);
				resultMap.put("orgName",orgName);
				resultMap.put("sessionNumber",sessionNumber);
				resultMap.put("catSpeName",catSpeName);
				resultMap.put("speName",speName);
				resultMap.put("year",year);
				resultMap.put("theoryScore",theoryScore);
				resultMap.put("firstStationScore",firstStationScore);
				resultMap.put("secondStationScore",secondStationScore);
				resultMap.put("thirdStationScore",thirdStationScore);
				resultMap.put("fourthStationScore",fourthStationScore);
				resultMap.put("fifthStationScore",fifthStationScore);
				resultMap.put("sixthStationScore",sixthStationScore);
				resultMap.put("seventhStationScore",seventhStationScore);
				resultMap.put("eighthStationScore",eighthStationScore);
				resultMap.put("ninthStationScore",ninthStationScore);
				resultMap.put("all",all);
				resultMap.put("isPass",isPass);
				resultMapList.add(resultMap);
			}
		}
		String fileName = "学员成绩信息汇总表.xls";
		String titles[] = {
				"doctorName:姓名",
				"idNo:证件号码",
				"orgName:培训基地",
				"sessionNumber:年级",
				"catSpeName:培训类别",
				"speName:培训专业",
				"year:理论成绩年份",
				"theoryScore:理论成绩",
				"year:技能成绩年份",
				"firstStationScore:第一站",
				"secondStationScore:第二站",
				"thirdStationScore:第三站",
				"fourthStationScore:第四站",
				"fifthStationScore:第五站",
				"sixthStationScore:第六站",
				"seventhStationScore:第七站",
				"eighthStationScore:第八站",
				"ninthStationScore:第九站",
				"all:总分",
				"isPass:是否合格"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, resultMapList, response.getOutputStream());
	}

	@RequestMapping(value = {"/parameterConfigMain"})
	public String main() {
		return "hbres/graduate/configMain";
	}
	@RequestMapping(value = "/edit", method = {RequestMethod.GET})
	public ModelAndView edit() {
		String wsId = (String) getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
		ModelAndView mav = new ModelAndView("hbres/graduate/hbresCfg");
		SysCfg cfg = new SysCfg();
		cfg.setWsId(wsId);
		List<SysCfg> sysCfgList = cfgBiz.search(cfg);
		Map<String, String> sysCfgMap = new HashMap<String, String>();
		Map<String, String> sysCfgDescMap = new HashMap<String, String>();
		for (SysCfg sysCfg : sysCfgList) {
			if (StringUtil.isNotBlank(sysCfg.getCfgDesc())) {
				sysCfgDescMap.put(sysCfg.getCfgCode(), sysCfg.getCfgDesc());
			}
			sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
			if (StringUtil.isNotBlank(sysCfg.getCfgBigValue())) {
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());
			}
		}
		mav.addObject("sysCfgDescMap", sysCfgDescMap);
		return mav.addObject("sysCfgMap", sysCfgMap);
	}
	/**
	 * 保存年份合格线配置
	 */
	@RequestMapping("/saveCfg")
	@ResponseBody
	public String  saveCfg(ResPassScoreCfg resPassScoreCfg,Model model){
		int result =baseBiz.savePassScoreCfg(resPassScoreCfg);
		if(result>0){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	/**
	 * 查询分数配置信息
	 */
	@RequestMapping("/loadScoreConf")
	public String loadScoreConf(Integer currentPage,ResPassScoreCfg resPassScoreCfg, Model model, HttpServletRequest request){
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResPassScoreCfg> cfgList = baseBiz.readCfgs(resPassScoreCfg);
		model.addAttribute("cfgList",cfgList);
		return "hbres/graduate/scoreCfgList";
	}
	/**
	 * 编辑
	 */
	@RequestMapping("/setScoreConf")
	public String setScoreConf(String cfgYear,Model model){
		ResPassScoreCfg scoreCfg = new ResPassScoreCfg();
		if(StringUtil.isNotBlank(cfgYear)){
			scoreCfg.setCfgYear(cfgYear);
			ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(scoreCfg);
			model.addAttribute("resPassScoreCfg",resPassScoreCfg);
		}
		return "hbres/graduate/addScore";
	}
	/**
	 * 删除
	 */
	@RequestMapping("/delScoreConf")
	@ResponseBody
	public String delScoreConf(String cfgYear){
		int result = baseBiz.delScoreConf(cfgYear);
		if(result>GlobalConstant.ZERO_LINE){
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}
	@RequestMapping(value="/saveSkillScore")
	@ResponseBody
	public String saveSkillScore(ResScore score,String roleFlag,String stationName,String skilScore) throws DocumentException {
		if(score==null){
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		String scoreFlow = score.getScoreFlow();
		if(StringUtil.isBlank(scoreFlow))
		{
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("firstStationScore","");
		map.put("secondStationScore","");
		map.put("thirdStationScore","");
		map.put("fourthStationScore","");
		map.put("fifthStationScore","");
		map.put("sixthStationScore","");
		map.put("seventhStationScore","");
		map.put("eighthStationScore","");
		map.put("ninthStationScore","");
		map.put(stationName,skilScore);
		String extScore="";
		try {
			extScore =convertMapToXml(map,score);
		} catch (Exception e) {
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		score.setExtScore(extScore);
		int result = resScoreBiz.save(score);
		if(GlobalConstant.ZERO_LINE==result){
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	public String convertMapToXml(Map<String,String> map,ResScore resScore) throws Exception {
		String xmlBody = null;
		Document doc=null;
		Element root =null;
		ResScore old=resScoreBiz.searchByScoreFlow(resScore.getScoreFlow());
		String content = null==old ? "":old.getExtScore();
		if(null!=old&&StringUtil.isNotBlank(content)) {
			doc = DocumentHelper.parseText(content);
			root = doc.getRootElement();
			Element extScoreInfo = root.element("extScoreInfo");
			if (extScoreInfo != null) {
				List<Element> extInfoAttrEles = extScoreInfo.elements();
				if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
					for (Element attr : extInfoAttrEles) {
						String attrName = attr.getName();
						String newValue = map.get(attrName);
						if (StringUtil.isNotBlank(newValue)) {
							attr.setText(newValue);
						}
					}
				}else{
					if (map != null && map.size() > 0) {
						for (String key : map.keySet()) {
							Element item = extScoreInfo.element(key);
							if(item==null)	item=extScoreInfo.addElement(key);
							item.setText(map.get(key));
						}
					}
				}
			}else{
				extScoreInfo = root.addElement("extScoreInfo");
				if (map != null && map.size() > 0) {
					for (String key : map.keySet()) {
						Element item = extScoreInfo.addElement(key);
						item.setText(map.get(key));
					}
				}
			}
			xmlBody = doc.asXML();
		}else {
			doc = DocumentHelper.createDocument();
			root = doc.addElement("resExtScore");
			Element extScoreInfo = root.addElement("extScoreInfo");
			if (map != null && map.size() > 0) {
				for (String key : map.keySet()) {
					Element item = extScoreInfo.addElement(key);
					item.setText(map.get(key));
				}
			}
			xmlBody = doc.asXML();
		}
		return xmlBody;
	}
}
