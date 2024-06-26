package com.pinde.sci.ctrl.sczyres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResRegBiz;
import com.pinde.sci.biz.sczyres.DoctorGraduationBiz;
import com.pinde.sci.biz.sczyres.DoctorRecruitBiz;
import com.pinde.sci.biz.sczyres.DoctorSingupBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.res.RecruitTypeEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.sczyres.SczyResOrgLevelEnum;
import com.pinde.sci.enums.sczyres.SpeCatEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.sczyres.ExportDoctorInfo;
import com.pinde.sci.form.sczyres.ExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/sczyres/hosptial")
@Controller
public class HosptialController extends GeneralController{

	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private DoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResJointOrgBiz resJointBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IResRegBiz regBiz;
	@Autowired
	private DoctorSingupBiz docSinupBiz;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private DoctorGraduationBiz doctorGraduationBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IFileBiz fileBiz;

	@RequestMapping("/home")
	public String home(Integer currentPage , Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		SysUser user = GlobalContext.getCurrentUser();
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,10);
		List<InxInfo> messages = this.noticeBiz.findNotice(info);
		model.addAttribute("messages",messages);
		ResDoctor doctor = new ResDoctor();
		doctor.setOrgFlow(user.getOrgFlow());
		doctor.setSessionNumber(regYear);
		//统计待审核
		doctor.setDoctorStatusId(RegStatusEnum.Passing.getId());
		int passingCount = resDoctorBiz.findDoctorCountInOrg(doctor);
		model.addAttribute("passingCount" , passingCount);
		//统计审核通过
		doctor.setDoctorStatusId(RegStatusEnum.Passed.getId());
		int passedCount = resDoctorBiz.findDoctorCountInOrg(doctor);
		model.addAttribute("passedCount" , passedCount);
		//统计审核不通过
		doctor.setDoctorStatusId(RegStatusEnum.UnPassed.getId());
		int notSingupCount = resDoctorBiz.findDoctorCountInOrg(doctor);
		//统计已报名
		int singupCount =passingCount+passedCount+notSingupCount;
		model.addAttribute("singupCount" , singupCount);

		List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(user.getOrgFlow());
		//是否有协同基地
		if(!jointOrgs.isEmpty()){
			model.addAttribute("showJointOrg",true);	
		}
		return "sczyres/hosptial/index";
	}

	@RequestMapping("/homeGraduationOnly")
	public String homeGraduationOnly(Integer currentPage , Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		SysUser user = GlobalContext.getCurrentUser();
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,10);
		List<InxInfo> messages = this.noticeBiz.findNotice(info);
		model.addAttribute("messages",messages);
		ResDoctor doctor = new ResDoctor();
		doctor.setOrgFlow(user.getOrgFlow());
		doctor.setSessionNumber(regYear);
		//统计待审核
		doctor.setDoctorStatusId(RegStatusEnum.Passing.getId());
		int passingCount = resDoctorBiz.findDoctorCountInOrg(doctor);
		model.addAttribute("passingCount" , passingCount);
		//统计审核通过
		doctor.setDoctorStatusId(RegStatusEnum.Passed.getId());
		int passedCount = resDoctorBiz.findDoctorCountInOrg(doctor);
		model.addAttribute("passedCount" , passedCount);
		//统计审核不通过
		doctor.setDoctorStatusId(RegStatusEnum.UnPassed.getId());
		int notSingupCount = resDoctorBiz.findDoctorCountInOrg(doctor);
		//统计已报名
		int singupCount =passingCount+passedCount+notSingupCount;
		model.addAttribute("singupCount" , singupCount);

		List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(user.getOrgFlow());
		//是否有协同基地
		if(!jointOrgs.isEmpty()){
			model.addAttribute("showJointOrg",true);
		}
		return "sczyres/hosptial/indexGraduationOnly";
	}
	

	
	@RequestMapping(value="/returnInfo")
	@ResponseBody
	public String returnInfo(String userFlow,String recruitFlow){
		int result = docSinupBiz.returnInfo(userFlow,recruitFlow);
		
		if(result!=GlobalConstant.ZERO_LINE){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	@RequestMapping("/recruitDoctor")
	public String recruitDoctor(String status , String speId , String key , Model model ,String planFlag , String waitStuCfm){
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("recruitYear", regYear);
		paramMap.put("doctorStatusId", RegStatusEnum.Passed.getId());
		if(StringUtil.isNotBlank(status)){
			if(GlobalConstant.FLAG_Y.equals(status)){
				if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){
					paramMap.put("stuType", "8");
				}else{
					paramMap.put("stuType", "7");
				}
			}else {
				paramMap.put("stuType", "9");
			}
		}
		if(StringUtil.isNotBlank(speId)){
			paramMap.put("speId", speId);
		}
		if(StringUtil.isNotBlank(key)){
			paramMap.put("key", key);
		}
		if(StringUtil.isNotBlank(planFlag)){
			paramMap.put("planFlag", planFlag);
		}
		if(StringUtil.isNotBlank(waitStuCfm)){
			paramMap.put("waitStuCfm", waitStuCfm);
		}
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		List<ResDoctorRecruitExt> resultDoctorRecruitExts = new ArrayList<ResDoctorRecruitExt>();
		if(StringUtil.isBlank(status)){
			for(ResDoctorRecruitExt rdre : doctorRecruitExts){
				if(SczyResOrgLevelEnum.Main.getId().equals(org.getOrgLevelId())){
					if(StringUtil.isBlank(rdre.getRecruitFlag())){
						resultDoctorRecruitExts.add(rdre);
					}
				}
				if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){
					if(StringUtil.isBlank(rdre.getAdmitFlag())){
						resultDoctorRecruitExts.add(rdre);
					}
				}
			}
		}else if(GlobalConstant.FLAG_Y.equals(status)){
			if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){
				for(ResDoctorRecruitExt rdre : doctorRecruitExts){
					if( (GlobalConstant.FLAG_Y.equals(rdre.getAdmitFlag()) && GlobalConstant.FLAG_Y.equals(rdre.getRecruitFlag())) || 
						(GlobalConstant.FLAG_Y.equals(rdre.getAdmitFlag()) && StringUtil.isBlank(rdre.getRecruitFlag()))	
							){
						resultDoctorRecruitExts.add(rdre);
					}
				}
			}else{
				resultDoctorRecruitExts = doctorRecruitExts;
			}
		}else{
			resultDoctorRecruitExts = doctorRecruitExts;
		}
		model.addAttribute("doctorRecruitExts", resultDoctorRecruitExts);	
		return "sczyres/hosptial/doctorrecruit";
	}
	
	@RequestMapping("/recruitDoctorForJointOrg")
	public String recruitDoctorForJointOrg(String status , String key , Model model ,String planFlag){
		
		if(GlobalConstant.FLAG_F.equals(status)){
			return singupForJointOrg(status , key , model);
		}
		
		if(StringUtil.isBlank(status)){
			status = GlobalConstant.FLAG_Y;
		}
		model.addAttribute("status",status);
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("recruitYear", regYear);
		paramMap.put("doctorStatusId", RegStatusEnum.Passed.getId());
		if(GlobalConstant.FLAG_Y.equals(status)){
			paramMap.put("admitFlag", GlobalConstant.FLAG_Y);
		}
		if(GlobalConstant.FLAG_N.equals(status)){
			paramMap.put("recruitFlag", GlobalConstant.FLAG_N);
		}
		List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(orgFlow);
		List<String> jointOrgFlows = new ArrayList<String>();
		for(ResJointOrg jointOrg:jointOrgs){
			jointOrgFlows.add(jointOrg.getJointOrgFlow());
		}
		if(!jointOrgFlows.isEmpty()){
			paramMap.put("jointOrgs", jointOrgFlows);	
		}
		if(StringUtil.isNotBlank(key)){
			paramMap.put("key", key);
		}
		if(StringUtil.isNotBlank(planFlag)){
			paramMap.put("planFlag", planFlag);
		}
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		List<ResDoctorRecruitExt> resultDoctorRecruitExts = new ArrayList<ResDoctorRecruitExt>();
		for(ResDoctorRecruitExt rdre : doctorRecruitExts){
//			if(!RecruitTypeEnum.Swap.getId().equals(rdre.getRecruitTypeId())){
				if(GlobalConstant.FLAG_Y.equals(status)){
					if(!GlobalConstant.FLAG_N.equals(rdre.getRecruitFlag())){
						resultDoctorRecruitExts.add(rdre);
					}
				}else{
					resultDoctorRecruitExts.add(rdre);
				}
//			}
		}
		model.addAttribute("doctorRecruitExts", resultDoctorRecruitExts);	
		return "sczyres/hosptial/doctorrecruitinfoforjointorg";
	}
	
	/**
	 * 协同基地-报名名单
	 * @param status
	 * @param key
	 * @param model
	 * @return
	 */
	public String singupForJointOrg(String status , String key , Model model){
		SysUser user = GlobalContext.getCurrentUser();
		List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(user.getOrgFlow());
		List<String> jointOrgFlows = new ArrayList<String>();
		for(ResJointOrg jointOrg:jointOrgs){
			jointOrgFlows.add(jointOrg.getJointOrgFlow());
		}
		List<ResDoctorExt> userList = null;
		Map<String , Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sessionNumber" , InitConfig.getSysCfg("res_reg_year"));
		paramMap.put("key", key);
		paramMap.put("sczy", "Y");
		if(!jointOrgFlows.isEmpty()){
			paramMap.put("jointOrgs", jointOrgFlows);
		}
		userList = resDoctorBiz.searchRegUser(paramMap);
		model.addAttribute("userList",userList);
		return "sczyres/hosptial/singupforjointorg";
	}
	
	/**
	 * 查询被上级部门调剂到本基地和下属协同基地的学员
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping("/recruitDoctorForSwap")
	public String recruitDoctorForSwap(String status,String key , Model model){
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		//是否有协同基地
		List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(orgFlow);
		if(!jointOrgs.isEmpty()){
			model.addAttribute("showJointOrg" , true);
		}
		List<String> jointOrgFlows=new ArrayList<String>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("recruitTypeId",RecruitTypeEnum.Swap.getId() );
		paramMap.put("recruitYear", regYear);
		if(StringUtil.isBlank(status)){
			status=GlobalConstant.FLAG_Y;
		}
		if (StringUtil.isEquals(status, GlobalConstant.FLAG_Y)) {
			paramMap.put("orgFlow", orgFlow);
		}
		if (StringUtil.isEquals(status, GlobalConstant.FLAG_N)){
			for(ResJointOrg res:jointOrgs){
				jointOrgFlows.add(res.getJointOrgFlow());
			}
		}
		if (StringUtil.isNotBlank(key)) {
			paramMap.put("key", key);
		}
		if (!jointOrgFlows.isEmpty()) {
			paramMap.put("jointOrgs", jointOrgFlows);
		}
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		model.addAttribute("doctorRecruitExts", doctorRecruitExts);	
		List<String> swapRecruitDoctors = new ArrayList<String>();
		for(ResDoctorRecruitExt rdre:doctorRecruitExts){
			swapRecruitDoctors.add(rdre.getDoctorFlow());
		}
		if(!swapRecruitDoctors.isEmpty()){
			Map<String, ResDoctorRecruit> noRecruitMap = this.doctorRecruitBiz.findNoRecruitDoctorRecruit(swapRecruitDoctors);
			model.addAttribute("noRecruitMap" , noRecruitMap);
		}
		
		return "sczyres/hosptial/doctorrecruitinfoforswap";
	}
	@RequestMapping("/orgRecruitInfo")
	public String orgRecruitInfo(Model model ,String id) {
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String regYear = InitConfig.getSysCfg("res_reg_year");
		String orgFlow = user.getOrgFlow();
		String orgId=null;  
		List<SysDict>dictList=null;
		if (StringUtil.isBlank(id)||StringUtil.isEquals(id, SpeCatEnum.ChineseMedicine.getId()) ) {
			id=SpeCatEnum.ChineseMedicine.getId();
			dictList=DictTypeEnum.sysListDictMap.get(DictTypeEnum.ZyCoSpe.getId());
			orgId=SpeCatEnum.ChineseMedicine.getId();
		}
		if (StringUtil.isEquals(id,SpeCatEnum.TCMGeneral.getId())) {
		   dictList=DictTypeEnum.sysListDictMap.get(DictTypeEnum.ZyqkCoSpe.getId());
			orgId=SpeCatEnum.TCMGeneral.getId();
		}
		//录取总数
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setOrgFlow(orgFlow);
		recruit.setCatSpeId(orgId);
		recruit.setRecruitFlag(GlobalConstant.FLAG_Y);
		recruit.setRecruitYear(regYear);
		Map<String,Integer> recruitCountMap = new HashMap<String,Integer>();
		for(SysDict rdr : dictList){
			recruit.setSpeId(rdr.getDictId());
			int recruitCount=doctorRecruitBiz.findCountByRecruit(recruit);
			recruitCountMap.put(rdr.getDictId(), recruitCount);
		}
		model.addAttribute("recruitCountMap", recruitCountMap);
		return "sczyres/hosptial/orgRecruitInfo";
	}
	@RequestMapping("/gradeedit")
	public String gradeEdit(String recruitFlow , Model model){
		ResDoctorRecruit recruit = this.doctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		model.addAttribute("recruit" , recruit);
		return "sczyres/hosptial/gradeedit";
	}
	
	/**
	 * 成绩保存
	 * @param recruit
	 * @return
	 */
	@RequestMapping("/savegrade")
	@ResponseBody
	public String saveGrade(ResDoctorRecruitWithBLOBs recruit){
		if(StringUtil.isNotBlank(recruit.getRecruitFlow())){
		    this.doctorRecruitBiz.editDoctorRecruit(recruit);
		    return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	/**
	 * 不录取的操作
	 * @return
	 */
	@RequestMapping("/notrecruit")
	@ResponseBody
	public String notrecruit(String recruitFlow){
		ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
		recruit.setRecruitFlow(recruitFlow);
		recruit.setRecruitFlag(GlobalConstant.FLAG_N);
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		String orgLevel = "";
		if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){
			recruit.setAdmitFlag(GlobalConstant.FLAG_N);
			orgLevel = SczyResOrgLevelEnum.Joint.getId();
		}
		if(SczyResOrgLevelEnum.Main.getId().equals(org.getOrgLevelId())){
			orgLevel = SczyResOrgLevelEnum.Main.getId();
		}
		this.doctorRecruitBiz.recruit(recruit,null,orgLevel);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	@RequestMapping("/openAdmitPage")
	public String openAdmitPage(String recruitFlow , Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResDoctorRecruit recruit = this.doctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		model.addAttribute("recruit" , recruit);
		SysUser user = this.userBiz.readSysUser(recruit.getDoctorFlow());
		model.addAttribute("user" , user);
		ResDoctor doctor = doctorBiz.readDoctor(recruit.getDoctorFlow());
		model.addAttribute("doctor",doctor);
		//可选专业
		SysCfg cfg = cfgBiz.read("scres_spe_allow");
		List<String> allowSpes = null;
		if(cfg!=null){
			String spes = cfg.getCfgValue();
			if(StringUtil.isNotBlank(spes)){
				String[] speList = spes.split(",");
				allowSpes = Arrays.asList(speList);
			}
		}
		List<ResOrgSpeAssign> allCatspes = this.doctorRecruitBiz.findSpeAssign(recruit.getOrgFlow(), regYear);
		List<ResOrgSpeAssign> catspes = new ArrayList<>();
		if(allCatspes!=null&&allCatspes.size()>0){
			for(ResOrgSpeAssign assign:allCatspes){
				if(allowSpes!=null&&allowSpes.contains(assign.getSpeId())){
					catspes.add(assign);
				}
			}
		}
		model.addAttribute("catspes" , catspes);
		return "sczyres/hosptial/noticeRecruitMain";
	}
	/**
	 *显示结构信息 
	 */
	@RequestMapping("/orgInfo")
	public String showOrgInfo(Model model) {
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg org = this.orgBiz.readSysOrg(user.getOrgFlow());
		model.addAttribute("org" , org);
		return "sczyres/hosptial/orgInfo";
	}
	/**
	 * 更新结构简介
	 */
	@RequestMapping("/updateInfo")
	@ResponseBody
	public String updateInfo(String orgInfo){
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg org = new SysOrg();
		org.setOrgFlow(user.getOrgFlow());
		org.setOrgInfo(orgInfo);
		orgBiz.update(org);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	/**
	 * 录取
	 * @param recruit
	 * @return
	 */
	@RequestMapping("/recruit")
	@ResponseBody
	public String recruit(ResDoctorRecruitWithBLOBs recruit,String planFlag){
		//是主基地还是协同基地的操作
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		String orgLevel = "";
		if(SczyResOrgLevelEnum.Main.getId().equals(org.getOrgLevelId())){
			recruit.setRecruitFlag(GlobalConstant.FLAG_Y);
			orgLevel = SczyResOrgLevelEnum.Main.getId();
		}
		if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){
			recruit.setAdmitFlag(GlobalConstant.FLAG_Y);
			orgLevel = SczyResOrgLevelEnum.Joint.getId();
		}
		if(SpeCatEnum.TCMAssiGeneral.getId().equals(recruit.getCatSpeId())){
			recruit.setSpeId("");
			recruit.setSpeName("");
			recruit.setSecondSpeId("");
			recruit.setSecondSpeName("");
			recruit.setTrainYear("2");
		}
		if(SpeCatEnum.TCMGeneral.getId().equals(recruit.getCatSpeId())){
			recruit.setSecondSpeId("");
			recruit.setSecondSpeName("");
			recruit.setTrainYear("3");
		}
		if(SpeCatEnum.ChineseMedicine.getId().equals(recruit.getCatSpeId())){
			recruit.setTrainYear("3");
		}
		int result = this.doctorRecruitBiz.recruit(recruit,planFlag,orgLevel);
		if(result==0){
			return "0";
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	/**
	 * 导出报名审核通过的学员
	 * @param orgFlow
	 * @param response
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/exportpasseddoctor")
	public void exportPassedDoctor(String orgFlow, HttpServletResponse response) throws Exception {
		String regYear = InitConfig.getSysCfg("res_reg_year");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isNotBlank(orgFlow)){
			paramMap.put("orgFlow", orgFlow);	
		}
		paramMap.put("recruitYear", regYear);
		paramMap.put("doctorStatusId", RegStatusEnum.Passed.getId());
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
	    String fileName = "报名审核通过学员名单.xls";
	    createExcle(response, fileName, doctorRecruitExts, GlobalConstant.FLAG_Y);
		
	}
	
	/**
	 * 导出报名审核通过的学员
	 * @param orgFlow
	 * @param response
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/exportrecruitdoctor")
	@ResponseBody
	public void exportRecruitDoctor(String status , String speId , String key , String planFlag , String waitStuCfm,
									 HttpServletResponse response) throws Exception {
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		SysOrg org = orgBiz.readSysOrg(orgFlow);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("recruitYear", regYear);
		paramMap.put("doctorStatusId", RegStatusEnum.Passed.getId());
		if(StringUtil.isNotBlank(status)){
			if(GlobalConstant.FLAG_Y.equals(status)){
				if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){
					paramMap.put("stuType", "8");
				}else{
					paramMap.put("stuType", "7");
				}
			}else {
				paramMap.put("stuType", "9");
			}
		}
		if(StringUtil.isNotBlank(speId)){
			paramMap.put("speId", speId);
		}
		if(StringUtil.isNotBlank(key)){
			paramMap.put("key", key);
		}
		if(StringUtil.isNotBlank(planFlag)){
			paramMap.put("planFlag", planFlag);
		}
		if(StringUtil.isNotBlank(waitStuCfm)){
			paramMap.put("waitStuCfm", waitStuCfm);
		}
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
	    String fileName = "录取学员名单.xls";
	    createExcle(response, fileName, doctorRecruitExts, null);
		
	}
	
	private String[] getExportTitle(){
		String[] titles = new String[]{
				"orgName:基地名称",
				"doctorName:姓名",
				"sex:性别",
				"age:年龄",
				"idNo:身份证号",
				"nationName:民族",
				"userPhone:手机号",
				"email:邮箱",
				"qqCode:QQ",
				"catSpeName:专业名称",
				"speName:对应专业名称",
				"secondSpeName:二级专业名称",
				"doctorLicenseFlag:是否执业医师",
				"qualifiedNo:执业医师资格证号",
				"doctorType:人员类型",
				"graduatedName:毕业院校（本科）",
				"graduationTime:毕业年份（本科）",
				"specialized:毕业专业（本科）",
				"degreeName:学位",
				"educationName:最高学历",
				"maSchool:毕业院校（硕士）",
				"maDate:毕业年份（硕士）",
				"maMajor:毕业专业（硕士）",
				"maDegreeName:学位（硕士）",
				"maDegreeTypeName:学位类型（硕士）",
				"workOrgName:工作单位"
//				"medicalHeaithOrgName:医疗卫生机构",
//				"hospitalAttrName:医院属性",
//				"hospitalCategoryName:医院类别",
//				"baseAttributeName:单位性质",
//				"basicHealthOrgName:基层医疗卫生机构"
		};
		
		return titles;
	}

	private void createExcle(HttpServletResponse response, String fileName, List<ResDoctorRecruitExt> doctorRecruitExts, String opertype) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = formatDate.format(calendar.getTime());
		Date today = formatDate.parse(currentTime);
		List<ExportDoctorInfo> dataList = new ArrayList<ExportDoctorInfo>();
		ExportDoctorInfo hdei = null;
		for(ResDoctorRecruitExt rdre:doctorRecruitExts){
			boolean exportFlag = false;
			if(GlobalConstant.FLAG_Y.equals(opertype)){
                if(RecruitTypeEnum.Fill.getId().equals(rdre.getRecruitTypeId())){
                	exportFlag = true;
				}
			}else{
				exportFlag = true;
			}
			if(exportFlag){
				hdei = new ExportDoctorInfo();
				hdei.setOrgName(rdre.getDoctor().getOrgName());
				hdei.setDoctorName(rdre.getSysUser().getUserName());
				hdei.setSex(rdre.getSysUser().getSexName());
				if(null != rdre.getSysUser().getUserBirthday())
				{
					Date birthDay = formatDate.parse(rdre.getSysUser().getUserBirthday());
					hdei.setAge(today.getYear()-birthDay.getYear()+"");
				}
				hdei.setIdNo(rdre.getSysUser().getIdNo());
				hdei.setNationName(rdre.getSysUser().getNationName());
				hdei.setUserPhone(rdre.getSysUser().getUserPhone());
				hdei.setEmail(rdre.getSysUser().getUserEmail());
				PubUserResume resume = userResumeBiz.readPubUserResume(rdre.getDoctorFlow());
				if(resume!=null){
					String resumeXml = resume.getUserResume();
					if(StringUtil.isNotBlank(resumeXml)) {
						ExtInfoForm extInfo = docSinupBiz.parseExtInfoXml(resumeXml);
						hdei.setQqCode(extInfo.getQqCode());
						hdei.setMaSchool(extInfo.getMaSchool());
						hdei.setMaDate(extInfo.getMaDate());
						hdei.setMaMajor(extInfo.getMaMajor());
						hdei.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(extInfo.getDegreeId()));
//						hdei.setMedicalHeaithOrgName(DictTypeEnum.getDictName(DictTypeEnum.MedicalHeaithOrg, extInfo.getMedicalHeaithOrg()));
//						hdei.setHospitalAttrName(DictTypeEnum.getDictName(DictTypeEnum.HospitalAttr, extInfo.getHospitalAttr()));
//						hdei.setHospitalCategoryName(DictTypeEnum.getDictName(DictTypeEnum.HospitalCategory, extInfo.getHospitalCategory()));
//						hdei.setBaseAttributeName(DictTypeEnum.getDictName(DictTypeEnum.BaseAttribute, extInfo.getBaseAttribute()));
//						hdei.setBasicHealthOrgName(DictTypeEnum.getDictName(DictTypeEnum.BasicHealthOrg, extInfo.getBasicHealthOrg()));
					}
				}
				hdei.setCatSpeName(rdre.getCatSpeName());
				hdei.setSpeName(rdre.getSpeName());
				hdei.setSecondSpeName(rdre.getSecondSpeName());
				hdei.setDoctorLicenseFlag(GlobalConstant.FLAG_Y.equals(rdre.getDoctor().getDoctorLicenseFlag())?"是":"否");
				hdei.setQualifiedNo(rdre.getDoctor().getQualifiedNo());
				hdei.setEducationName(rdre.getSysUser().getEducationName());
				hdei.setDoctorType(rdre.getDoctor().getDoctorTypeName());
				hdei.setGraduatedName(rdre.getDoctor().getGraduatedName());
				hdei.setGraduationTime(rdre.getDoctor().getGraduationTime());
				hdei.setSpecialized(rdre.getDoctor().getSpecialized());
				hdei.setWorkOrgName(rdre.getDoctor().getWorkOrgName());
				dataList.add(hdei);
			}
				
		}
		String[] titles = getExportTitle();
		fileName = URLEncoder.encode(fileName, "UTF-8");
	    ExcleUtile.exportSimpleExcle(titles, dataList, ExportDoctorInfo.class, response.getOutputStream());
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(@RequestBody SysUser user){
		System.out.println(user.getUserName()+"--"+user.getOrgFlow());
		return "1";
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
		return "sczyres/accounts";
	}

	@RequestMapping(value={"/modPasswd"})
	public String modPasswd(Model model){
		return "sczyres/modPasswd";
	}

	@RequestMapping("/graduationList")
	public String graduationList(Model model,ScresGraduationApply searchApply,String role,String statusId,String catSpeId,String isMakeUp){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");
		List<String> orgFlows = new ArrayList<>();
		List<ScresGraduationApply> graduationApplies = new ArrayList<>();
		if("hospital".equals(role)){
			SysOrg org = orgBiz.readSysOrg(orgFlow);
			model.addAttribute("level",org.getOrgLevelId());
			if(SczyResOrgLevelEnum.Main.getId().equals(org.getOrgLevelId())){//主基地
				Map<String,Object> paramMap = new HashMap<>();
				List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(orgFlow);
				if(jointOrgs!=null&&jointOrgs.size()>0){
					for(ResJointOrg jointOrg:jointOrgs){
						String jointOrgFlow = jointOrg.getJointOrgFlow();
						orgFlows.add(jointOrgFlow);
					}
				}
				orgFlows.add(orgFlow);
				paramMap.put("orgFlow",orgFlow);
				paramMap.put("orgFlows",orgFlows);
				paramMap.put("graduationYear",graduationYear);
				paramMap.put("statusId",statusId);
				paramMap.put("catSpeId",catSpeId);
				paramMap.put("isMakeUp",isMakeUp);
				paramMap.put("doctorName",searchApply.getDoctorName());
				graduationApplies = doctorGraduationBiz.searchAppliesMain(paramMap);
			}
			if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){//协同基地
				searchApply.setGraduationYear(graduationYear);
				searchApply.setDoctorStatusId("1");
				searchApply.setOrgFlow(orgFlow);
				searchApply.setTrainingSpeId(catSpeId);

				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("searchApply",searchApply);
				paramMap.put("isMakeUp",isMakeUp);
				paramMap.put("orgFlows",null);
				graduationApplies = doctorGraduationBiz.searchApplyMap(paramMap);
				model.addAttribute("isJoint","Y");
			}
		}else{//中管局
			searchApply.setGraduationYear(graduationYear);
			searchApply.setXtOrgStatusId("1");
			searchApply.setTrainingSpeId(catSpeId);
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("searchApply",searchApply);
			paramMap.put("isMakeUp",isMakeUp);
			paramMap.put("orgFlows",null);
			graduationApplies = doctorGraduationBiz.searchApplyMap(paramMap);
			SysOrgExample orgExample = new SysOrgExample();
			orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
			orgExample.setOrderByClause("ORDINAL");
			List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
			model.addAttribute("hospitals", orgs);
		}
		model.addAttribute("graduationApplies",graduationApplies);
		return "sczyres/hosptial/graduationList";
	}

	@RequestMapping("/detail")
	public String detail(Model model,String doctorFlow,String recordFlow){
		SysUser user = userBiz.findByFlow(doctorFlow);
		ResDoctor doctor = doctorBiz.findByFlow(doctorFlow);
		model.addAttribute("user",user);
		model.addAttribute("doctor",doctor);
		PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = docSinupBiz.parseExtInfoXml(resumeXml);
			model.addAttribute("extInfo" ,extInfo);
		}
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");
		model.addAttribute("graduationYear",graduationYear);
		PubFile searchFile = new PubFile();
		searchFile.setProductFlow(doctorFlow);
		searchFile.setProductType(graduationYear);
		List<PubFile> pubFileList = fileBiz.searchFiles(searchFile);
		Map<Integer,Object> fileMap = new HashMap<>();
		int i = 1;
		if(pubFileList!=null&&pubFileList.size()>0){
			for(PubFile file:pubFileList){
				fileMap.put(i,file);
				i++;
			}
		}
		model.addAttribute("fileMap",fileMap);
		//轮转情况
		ScresSchInfo search = new ScresSchInfo();
		search.setDoctorFlow(doctorFlow);
		search.setGraduationYear(graduationYear);
		List<ScresSchInfo> scresSchInfoList = doctorGraduationBiz.searchSchInfo(search);
		Map<String,List<ScresSchInfo>> resultMap = new HashMap<>();
		if(scresSchInfoList!=null&&scresSchInfoList.size()>0){
			for(ScresSchInfo scresSchInfo:scresSchInfoList){
				String speId = scresSchInfo.getSpeId();
				if(null==resultMap.get(speId)){
					List<ScresSchInfo> scresSchInfos = new ArrayList<>();
					scresSchInfos.add(scresSchInfo);
					resultMap.put(speId,scresSchInfos);
				}else {
					resultMap.get(speId).add(scresSchInfo);
				}
			}
		}
		model.addAttribute("resultMap",resultMap);
		ScresGraduationApply apply = doctorGraduationBiz.readApply(recordFlow);
		model.addAttribute("apply",apply);
		return "sczyres/hosptial/detail";
	}

	@RequestMapping("/auditApply")
	@ResponseBody
	public String auditApply(String flag,ScresGraduationApply apply,String level,String remarkContent){
		SysUser user = GlobalContext.getCurrentUser();
		if("Main".equals(level)){
			apply.setOrgStatusId(flag);
			apply.setXtOrgStatusId(flag);
			apply.setXtOrgRemark(remarkContent);
			apply.setOrgAuditorName(user.getUserName());
			if("0".equals(flag)){
				apply.setDoctorStatusId("0");
				apply.setXtOrgStatusId("0");
				apply.setOrgStatusId("-1");
			}
		}
		if("Joint".equals(level)){
			apply.setOrgStatusId(flag);
			apply.setOrgRemark(remarkContent);
			if("0".equals(flag)){
				apply.setDoctorStatusId("0");
				apply.setOrgStatusId("0");
			}
		}

		doctorGraduationBiz.saveApply(apply);
		return "1";
	}

	@RequestMapping("/changeReturnBackFlag")//修改指标情况
	@ResponseBody
	public int changeReturnBackFlag(String recruitFlow,String flag){
		return doctorRecruitBiz.changeReturnBackFlag(recruitFlow,flag);
	}

	@RequestMapping("/noPass")//打开审核不通过页面
	public String noPass(){
		return "sczyres/hosptial/noPassRemark";
	}

	@RequestMapping("/auditApplyNoPass")
	@ResponseBody
	public String auditApplyNoPass(ScresGraduationApply apply,String remarkInfo,String role,String level){
		if("hospital".equals(role)){
			if("Main".equals(level)){
				apply.setDoctorStatusId("0");
				apply.setXtOrgStatusId("0");
				apply.setOrgStatusId("-1");
				apply.setXtOrgRemark(remarkInfo);
			}
			if("Joint".equals(level)){
				apply.setDoctorStatusId("0");
				apply.setOrgStatusId("0");
				apply.setOrgRemark(remarkInfo);
			}
		}else if("charge".equals(role)){
			apply.setDoctorStatusId("0");
			apply.setOrgStatusId("-1");
			apply.setXtOrgStatusId("-1");
			apply.setChargeStatusId("0");
			apply.setChargeRemark(remarkInfo);
		}
		doctorGraduationBiz.saveApply(apply);
		return "1";
	}
	//准考证导入
	@RequestMapping(value="/importGrades")
	@ResponseBody
	public String importGrades(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = doctorRecruitBiz.importGrades(file);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
}
