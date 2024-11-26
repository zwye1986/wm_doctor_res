package com.pinde.sci.ctrl.jsres;

import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResAssessCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.*;
import com.pinde.core.common.enums.ResAssessTypeEnum;
import com.pinde.core.common.enums.ResRecTypeEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jsres.UserInfoExtForm;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.jsres.JsDoctorInfoExt;
import com.pinde.sci.model.jsres.JsResDoctorOrgHistoryExt;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/jsres/institute")
public class InstituteController extends GeneralController {
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IJsResRecBiz jsResRecBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private IJsResDoctorOrgHistoryBiz jsDocOrgHistoryBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IJsResStatisticBiz resStatisticBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;

	//医师信息管理模块
	/**
	 * 医师信息查询
	 * @param model
	 * @param roleFlag
     * @return
     */
	@RequestMapping(value="/provinceDoctorList")
	public String provinceDoctorList(Model model,String roleFlag){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
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
		return  "jsres/institute/doctorinfo/doctorList";
	}

	@RequestMapping(value="/doctorTrendListSun")
	public String doctorRecruitSun(Model model,Integer currentPage,String roleFlag, HttpServletRequest request,ResDoctor doctor,SysUser user,String baseId,String jointOrgFlag,String derateFlag,String orgLevel,String[] datas,String graduationYear){
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				jointOrgFlowList.add(sysuser.getOrgFlow());
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId(sysOrg.getOrgProvId());
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
				if(StringUtil.isNotBlank(orgLevel)){
					org.setOrgLevelId(orgLevel);
					org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				}
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
		}
		if(StringUtil.isNotBlank(user.getIdNo())){
			sysUser.setIdNo(user.getIdNo());
		}
		sysUser.setUserName(user.getUserName());
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
				resDoctorRecruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<JsResDoctorRecruitExt> doctorList = jsResDoctorRecruitBiz.searchDoctorInfoExts(resDoctorRecruit,doctor, sysUser, org, jointOrgFlowList, derateFlag,docTypeList);
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		return  "jsres/institute/doctorinfo/doctorListZi";
	}

	/**
	 * 加载基地专业
	 * @return
	 */
	@RequestMapping(value="/searchResOrgSpeList", method={RequestMethod.GET})
	@ResponseBody
	public List<ResOrgSpe> searchResOrgSpeList(String sessionNumber,ResOrgSpe resOrgSpe, Model model){
		List<ResOrgSpe> speList=null;
		resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(sessionNumber)){
			int year=Integer.parseInt(sessionNumber);
			if(year>=2015){
				speList = resOrgSpeBiz.searchResOrgSpeList(resOrgSpe,null);
			}else{
				String speTypeId = resOrgSpe.getSpeTypeId();
				if(StringUtil.isNotBlank(speTypeId)){
					Map<String,String> speMap=InitConfig.getDictNameMap(speTypeId);
					speList=new ArrayList<ResOrgSpe>();
					ResOrgSpe orgSpe=null;
					for(Map.Entry<String, String> map:speMap.entrySet()){
						orgSpe=new ResOrgSpe();
						orgSpe.setSpeId(map.getValue());
						orgSpe.setSpeName(map.getKey());
						speList.add(orgSpe);
					}
				}
			}
		}
		return speList;
	}

	/**
	 * 导出excel
	 * @param
	 * @return
	 */
	@RequestMapping(value="/exportDoctor")
	public void exportDoctor(HttpServletRequest request, HttpServletResponse response,String sessionNumber,ResDoctor doctor,SysUser user,String baseId,String jointOrgFlag,String derateFlag,String orgLevel,String[] datas,String graduationYear)throws Exception{
		String[] headLines = null;
		headLines = new String[]{
				"住院医师规范化培训"+sessionNumber+"级招收对象花名册",
				"省（区、市）卫生计生行政部门（盖章）：江苏省卫生和计划生育委员会        填报人：顾丰      联系方式：025-83620704",
		};
		String[] titles = new String[]{
				":编号",
				"sysUser.userName:姓名",
				"sysUser.sexName:性别",
				"sysUser.userBirthday:出生年月",
				"sysUser.nationName:民族",
				"sysUser.idNo:身份证号码（若为其他证件，需注明）",
				"sysUser.userPhone:联系方式（手机）",
				"sysUser.userEmail:联系方式（邮箱）",
				"userResumeExt.graduatedName:本科毕业院校及专业",
				"userResumeExt.specialized:本科毕业院校及专业",
				"userResumeExt.graduationTime:毕业时间",
				"sysUser.educationName:最高学历",
				"doctor.graduatedName:最高学历毕业院校及专业",
				"doctor.specialized:最高学历毕业院校及专业",
				"doctor.graduationTime:获得最高学历时间",
				"doctor.doctorTypeName:身份类型（单位人/社会人）",
				"doctor.workOrgName:派出单位（限“单位人”填写）",
				"doctor.orgName:培训基地（若在协同单位，需注明）",
				"doctor.trainingSpeName:培训专业",
				"recruit.recruitDate:参训时间",
				"recruit.trainYear:计划参训时限",
		};
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser exSysUser=GlobalContext.getCurrentUser();
		List<String>jointOrgFlowList=new ArrayList<String>();
		List<UserInfoExtForm> userExtForms=new ArrayList<UserInfoExtForm>();
		ResDoctorRecruit  recruit=new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			recruit.setGraduationYear(graduationYear);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
				if(resJointOrgsList!=null&&!resJointOrgsList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				for(ResJointOrg jointOrg:resJointOrgsList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
			jointOrgFlowList.add(doctor.getOrgFlow());
		}else{
			SysOrg sysOrg=orgBiz.readSysOrg(exSysUser.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				jointOrgFlowList.add(exSysUser.getOrgFlow());
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId(sysOrg.getOrgProvId());
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
				if(StringUtil.isNotBlank(orgLevel)){
					org.setOrgLevelId(orgLevel);
					org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				}
			}
		}
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
				recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		ResRec resRec =new ResRec();
		resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<JsDoctorInfoExt> doctorInfoExts=jsResDoctorRecruitBiz.searchDoctorInfoResume(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList);
		List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
		Map<Object, Object> orgAndJointNameMap=new HashMap<Object, Object>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(Map<String,Object> en:jointOrgs){
				Object key=en.get("key");
				Object value=en.get("value");
				orgAndJointNameMap.put(key, value);
			}
		}
		if(doctorInfoExts!=null&&!doctorInfoExts.isEmpty()){
			for(JsDoctorInfoExt d:doctorInfoExts){
				UserInfoExtForm userInfoExtForm=new UserInfoExtForm();
				if(orgAndJointNameMap.containsKey(d.getOrgFlow())){
					d.getResDoctor().setOrgName(orgAndJointNameMap.get(d.getOrgFlow())+"("+d.getOrgName()+")");
				}
				String content=d.getUserResume().getUserResume();
				if(StringUtil.isNotBlank(content)){
					UserResumeExtInfoForm form= JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
					userInfoExtForm.setUserResumeExt(form);
				}
				SysUser su = d.getSysUser();
				String cretTypeId = su.getCretTypeId();
				if(StringUtil.isNotBlank(cretTypeId)){
					if(!CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId) ){
						su.setIdNo(su.getIdNo() +"(" + CertificateTypeEnum.getNameById(cretTypeId) +")");
					}
				}
				userInfoExtForm.setSysUser(su);
				userInfoExtForm.setDoctor(d.getResDoctor());
				ResDoctorRecruit recruit3 = d;
				userInfoExtForm.setRecruit(recruit3);
				if(StringUtil.isNotBlank(d.getTrainYear())){
					d.setTrainYear(JsResTrainYearEnum.getNameById(d.getTrainYear()));
				}
				userExtForms.add(userInfoExtForm);
			}
		}
		String fileName = "住院医师规范化培训"+sessionNumber+"级招收对象花名册.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, userExtForms, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @param sessionNumber
	 * @param doctor
	 * @param user
	 * @param baseId
	 * @param jointOrgFlag
	 * @param derateFlag
	 * @param orgLevel
	 * @param datas
	 * @param graduationYear
	 * @throws Exception
	 */
	@RequestMapping(value="/exportForDetail")
	public void exportForDetail(HttpServletRequest request, HttpServletResponse response,String sessionNumber,ResDoctor doctor,SysUser user,String baseId,String jointOrgFlag,String derateFlag,String orgLevel,String[] datas,String graduationYear)throws Exception{
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser exSysUser=GlobalContext.getCurrentUser();
		List<String>jointOrgFlowList=new ArrayList<String>();
		ResDoctorRecruit  recruit=new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			recruit.setGraduationYear(graduationYear);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
				if(resJointOrgsList!=null&&!resJointOrgsList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}
		}

		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				for(ResJointOrg jointOrg:resJointOrgsList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
			jointOrgFlowList.add(doctor.getOrgFlow());
		}else{
			SysOrg sysOrg=orgBiz.readSysOrg(exSysUser.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				jointOrgFlowList.add(exSysUser.getOrgFlow());
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId(sysOrg.getOrgProvId());
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
				if(StringUtil.isNotBlank(orgLevel)){
					org.setOrgLevelId(orgLevel);
					org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				}
			}
		}
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				doctor.setTrainingTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
				recruit.setTrainYear(JsResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<JsDoctorInfoExt> doctorInfoExts=jsResDoctorRecruitBiz.searchDoctorInfoResume(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList);
		jsResDoctorBiz.exportForDetail(doctorInfoExts, response);
	}

	@RequestMapping(value={"/doctorPassedList"})
	public String doctorPassedList(Model model, String doctorFlow, String studyFlag){
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());//  页面过滤 方便判断是否允许退回
		recruit.setDoctorFlow(doctorFlow);
		recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		model.addAttribute("recruitList", recruitList);
		if(StringUtil.isNotBlank(studyFlag)){
			if(GlobalConstant.FLAG_Y.equals(studyFlag)){
				model.addAttribute("studyFlag", studyFlag);
			}
		}
		ResRec resRec =new ResRec();
		resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<String> operUserFlowList =new ArrayList<String>();
		operUserFlowList.add(doctorFlow);
		List<ResRec> recList = resRecBiz.searchRecInfo(resRec, operUserFlowList);
		model.addAttribute("recList", recList);
		List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.Delay.getId(), doctorFlow);
		model.addAttribute("delayList", delayList);
		return "/jsres/province/doctor/doctorMain";
	}

	@RequestMapping(value={"/checkDoctorAuth"})
	@ResponseBody
	public String checkDoctorAuth(String doctorFlow,String roleFlag){
		String recTypeId = ResRecTypeEnum.DoctorAuth.getId();
		List<ResRec> resRecList = resRecBiz.searchByUserFlowAndTypeId(doctorFlow, recTypeId);
		if(resRecList != null && !resRecList.isEmpty()){
			return GlobalConstant.FLAG_Y;
		}
		return GlobalConstant.FLAG_N;
	}

	/**
	 * 培训登记
	 * @return
	 */
	@RequestMapping(value="/trainRegister")
	public String trainRegister(String hideApprove,String doctorFlow,String roleFlag){
		SysUser currUser = GlobalContext.getCurrentUser();
		String operUserFlow = currUser.getUserFlow();
		String recTypeId = ResRecTypeEnum.DoctorAuth.getId();
		List<ResRec> resRecList = resRecBiz.searchByUserFlowAndTypeId(operUserFlow, recTypeId);
		if(StringUtil.isNotBlank(doctorFlow)){
			return "redirect:/jsres/institute/process?hideApprove="+hideApprove+"&doctorFlow="+doctorFlow+"&roleFlag="+roleFlag;
		}
		if(resRecList != null && !resRecList.isEmpty()){
			return "redirect:/jsres/institute/process?hideApprove="+hideApprove+"&roleFlag="+roleFlag;
		}else{
			return "jsres/doctor/recordHandbook";
		}
	}

	/**
	 * 培训登记
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/process",method={RequestMethod.GET})
	public String process( Model model,String hideApprove,String doctorFlow,String roleFlag){
		model.addAttribute("hideApprove",hideApprove);
		ResDoctor doctor=new ResDoctor();
		SysUser sysUser=new SysUser();
		if (StringUtil.isNotBlank(doctorFlow)) {
			sysUser=userBiz.readSysUser(doctorFlow);
			doctor= resDoctorBiz.searchByUserFlow(doctorFlow);
		}else{
			sysUser = GlobalContext.getCurrentUser();
			doctor= resDoctorBiz.searchByUserFlow(sysUser.getUserFlow());
		}
		String resRecType=ResRecTypeEnum.DoctorAuth.getId();
		List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,sysUser.getUserFlow());
		if (resRec!=null && !resRec.isEmpty()) {
			model.addAttribute("resRec",resRec.get(0));
		}
		model.addAttribute("doctor", doctor);
		if(doctor!=null && StringUtil.isNotBlank(doctor.getRotationFlow())){

			List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus(doctor.getDoctorFlow(),doctor.getRotationFlow());
			Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
			if(doctorDeptList!=null && !doctorDeptList.isEmpty()){
				for(SchDoctorDept sdd : doctorDeptList){
					String key = sdd.getGroupFlow()+sdd.getStandardDeptId();
					doctorDeptMap.put(key,sdd);
				}
			}

			SchRotation rotation = rotationBiz.readSchRotation(doctor.getRotationFlow());
			model.addAttribute("rotation", rotation);

			List<SchRotationGroup> groupList =  groupBiz.searchSchRotationGroup(doctor.getRotationFlow());
			model.addAttribute("groupList", groupList);

			List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotation.getRotationFlow());

			Map<String,List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();

			//计算轮转时间内登记进度
			List<ResRec> recList = new ArrayList<ResRec>();
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",sysUser.getUserFlow());

			for(SchRotationDept dept :deptList){
				List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
				if(temp == null){
					temp = new ArrayList<SchRotationDept>();
				}
				rotationDeptMap.put(dept.getGroupFlow(), temp);

				String reductionKey = dept.getGroupFlow()+dept.getStandardDeptId();
				SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
				if(reductionDept!=null){
					if(!GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())){
						continue;
					}
					String reductionSchMonth = reductionDept.getSchMonth();
					dept.setSchMonth(reductionSchMonth);
				}
				temp.add(dept);

				String groupFlow = dept.getGroupFlow();
				String standardDeptId = dept.getStandardDeptId();
				paramMap.put("standardGroupFlow",groupFlow);
				paramMap.put("standardDeptId",standardDeptId);
				paramMap.put("processFlow",dept.getRecordFlow());
				List<ResRec> recs = resRecBiz.searchProssingRec(paramMap);
				if(recs!=null && !recs.isEmpty()){
					recList.addAll(recs);
				}
			}
			model.addAttribute("rotationDeptMap", rotationDeptMap);

			List<SchArrangeResult> resultList = resultBiz.searchSchArrangeResultByDoctor(doctor.getDoctorFlow());
			Map<String,List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
			Map<String,Float> realMonthMap = new HashMap<String,Float>();
			for(SchArrangeResult result : resultList){
				String key = result.getStandardGroupFlow()+result.getStandardDeptId();

				List<SchArrangeResult> temp = resultMap.get(key);
				if(temp == null){
					temp = new ArrayList<SchArrangeResult>();
					resultMap.put(key, temp);
				}
				temp.add(result);

				Float month = realMonthMap.get(key);
				if(month == null){
					month = 0f;
				}
				String realMonth = result.getSchMonth();
				if(StringUtil.isNotBlank(realMonth)){
					Float realMonthF = Float.parseFloat(realMonth);
					month+=realMonthF;
				}

				realMonthMap.put(key,month);
			}
			model.addAttribute("resultMap", resultMap);
			model.addAttribute("realMonthMap", realMonthMap);

			//计算登记进度
			Map<String, String> processPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow());
			model.addAttribute("processPerMap", processPerMap);

			//计算轮转时间内登记进度
			Map<String, String> processingPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow(), null, 0, recList);
			model.addAttribute("processingPerMap", processingPerMap);
			model.addAttribute("sysUser", sysUser);
		}
		return "jsres/institute/doctorinfo/process";
	}

	/**
	 * 获取退培信息
	 * @param model
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value="/backTrainInfo")
	public String backTrainInfo(ResRec resRec, Model model, String userFlow, Integer currentPage, HttpServletRequest request, String sessionNumber, String speName, String reasonId,String policyId, String seeFlag) throws DocumentException{
		Map<Object, Object> backInfoMap =null;
		List<String> userFlowList = new ArrayList<String>();
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(userFlow)){
			userFlowList.add(userFlow);
		}
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		List<String> orgFlowList=new ArrayList<String>();
		resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<ResRec> resRecList = resRecBiz.searchInfo(resRec, userFlowList, orgFlowList);
		if(resRecList!=null&&!resRecList.isEmpty()){
			backInfoMap = jsResRecBiz.paraseBackTrain(resRecList, sessionNumber, speName, reasonId, policyId);
		}
		//退培比例
		if(StringUtil.isNotBlank(resRec.getOrgFlow())&&StringUtil.isNotBlank(sessionNumber)){
			DecimalFormat df = new DecimalFormat("0.00%");
			ResDoctor doctor =new  ResDoctor();
			if(StringUtil.isNotBlank(resRec.getOrgFlow())){
				doctor.setOrgFlow(resRec.getOrgFlow());
			}
			doctor.setSessionNumber(sessionNumber);
			int count=resDoctorBiz.findDoctorCountInOrg(doctor);
			Map<Object, Object> countMap=null;
			if(backInfoMap!=null&&!backInfoMap.isEmpty()&&backInfoMap.size()>0){
				countMap=(Map<Object, Object>) backInfoMap.get(GlobalConstant.FLAG_F);
			}
			float percent=0;
			if(resRecList!=null&&!resRecList.isEmpty()&&count!=0){
				percent=(float)countMap.size()/(float)count;
				model.addAttribute("percent", df.format(percent));
			}else{
				model.addAttribute("percent", 0+"%");
			}
		}
		model.addAttribute("backInfoMap", backInfoMap);
		return "jsres/institute/doctorinfo/backTrainInfo";
	}

	@RequestMapping(value="/exportForBack")
	public void exportForBack(HttpServletRequest request, HttpServletResponse response, ResRec resRec, Model model, String userFlow, String sessionNumber, String speName, String reasonId,String policyId)throws Exception{
		Map<Object, Object> backInfoMap =new HashMap<Object,Object>();
		List<String> userFlowList = new ArrayList<String>();
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(userFlow)){
			userFlowList.add(userFlow);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			resRec.setOrgFlow(user.getOrgFlow());
		}
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<ResRec> resRecList = resRecBiz.searchRecInfo(resRec, userFlowList);
		if(resRecList!=null&&!resRecList.isEmpty()){
			backInfoMap = jsResRecBiz.paraseBackTrain(resRecList, sessionNumber, speName, reasonId, policyId);
		}
		DecimalFormat df = new DecimalFormat("0.00%");
		ResDoctor doctor =new  ResDoctor();
		if(StringUtil.isNotBlank(resRec.getOrgName())){
			doctor.setOrgName(resRec.getOrgName());
		}
		if(StringUtil.isNotBlank(resRec.getOrgFlow())){
			doctor.setOrgFlow(resRec.getOrgFlow());
		}
		doctor.setSessionNumber(sessionNumber);
		int count=resDoctorBiz.findDoctorCountInOrg(doctor);
		Map<Object, Object> countMap = (Map<Object, Object>) backInfoMap.get(GlobalConstant.FLAG_F);
		float percent=0;String per="";
		if(resRecList!=null&&!resRecList.isEmpty()&&count!=0){
			percent=(float)countMap.size()/(float)count;
			per= df.format(percent);
		}else{
			per= 0+"%";
		}
		String flag=GlobalConstant.FLAG_Y;
		if(StringUtil.isBlank(resRec.getOrgFlow())||StringUtil.isBlank(sessionNumber)){
			flag=GlobalConstant.FLAG_N;
		}
		backInfoMap.put(GlobalConstant.FLAG_N, per);
		jsResDoctorBiz.exportForBack(backInfoMap,response,flag);
	}

	/**
	 * 基地双向评分
	 * @param gradeRole
	 * @param deptFlow
	 * @param userName
	 * @param operStartDate
	 * @param operEndDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/gradeSearch"})
	public String gradeSearch(
			String gradeRole,
			String deptFlow,
			String recOrgFlow,
			String userName,
			String operStartDate,
			String operEndDate,
			String sessionNumber,
			Model model
	){

		if(StringUtil.isNotBlank(operStartDate)){
			operStartDate = DateUtil.getDate(operStartDate)+"000000";
		}
		if(StringUtil.isNotBlank(operEndDate)){
			operEndDate = DateUtil.getDate(operEndDate)+"235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		//省厅下基地及协同基地

		String orgFlowRec = GlobalContext.getCurrentUser().getOrgFlow();
		SysOrg org=orgBiz.readSysOrg(orgFlowRec);
		org.setOrgCityId("");
		List<SysOrg> sysOrgList=orgBiz.searOrgTeacherRoleCheckUser(org);
		model.addAttribute("sysOrgList", sysOrgList);
		if (StringUtil.isNotBlank(recOrgFlow)) {
			orgFlow=recOrgFlow;
		}else{
			String date=DateUtil.getCurrDateTime("yyyy");
			model.addAttribute("currDate", date);
			int d=Integer.valueOf(date);
			d=d-1;
			date=String.valueOf(d);
			model.addAttribute("PreviouYearDate", date);
			int s=Integer.valueOf(date);
			s=s-1;
			date=String.valueOf(s);
			model.addAttribute("FirstTwoYearsDate", date);
			return "jsres/institute/doctorinfo/gradeSearch";
		}

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("operStartDate",operStartDate);
		paramMap.put("operEndDate",operEndDate);
		paramMap.put("userName",userName);
		paramMap.put("deptFlow",deptFlow);

		//查出当前机构的所有科室
		List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
		model.addAttribute("depts",deptList);

		List<String> keys = new ArrayList<String>();
		Object waitSort = null;
		Map<String, Object> scoreMap=new HashMap<String, Object>();
		if("teacher".equals(gradeRole)){
			//带教flow
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			paramMap.put("roleFlow",teacherRoleFlow);

			recType = ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList",assessCfgList);
			//查出当前机构的所有带教老师
			List<SysUser> userList = userBiz.getUserByRec(paramMap);
			model.addAttribute("datas",userList);
			waitSort = userList;
			if(userList!=null && !userList.isEmpty()){
				for(SysUser su : userList){
					keys.add(su.getUserFlow());
					int score=0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if (resAssessCfgTitleForm.getItemList()!=null&&!resAssessCfgTitleForm.getItemList().isEmpty()) {
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s=Integer.parseInt(resAssessCfgItemForm.getScore());
								score+=s;
							}
						}
					}
					scoreMap.put(su.getUserFlow(), score);
				}
			}
			paramMap.put("teacherFlows",keys);
		}else if("head".equals(gradeRole)){
			recType = ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList",assessCfgList);
			List<SysDept> depts = deptBiz.getDeptByRec(paramMap);
			model.addAttribute("datas",depts);
			waitSort = depts;
			if(depts!=null && !depts.isEmpty()){
				for(SysDept sd : depts){
					keys.add(sd.getDeptFlow());
					int score=0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
							int s=Integer.parseInt(resAssessCfgItemForm.getScore());
							score+=s;
						}
					}
					scoreMap.put(sd.getDeptFlow(), score);
				}
			}
			paramMap.put("deptFlows",keys);
		}

		//获取评分数据
		List<Map<String, String>> recList = resRecBiz.getRecContentByProcess(paramMap);

		if (StringUtil.isNotBlank(sessionNumber)) {
			paramMap.put("sessionNumber", sessionNumber);
			List<Map<String, String>> recDateList = resRecBiz.getRecContentByProcess(paramMap);
			if(recDateList!=null && !recDateList.isEmpty()){
				Map<String,Float> recDateAvgMap=avg(recDateList);
				model.addAttribute("recDateAvgMap", recDateAvgMap);
			}
		}else{
			String date=DateUtil.getCurrDateTime("yyyy");
			model.addAttribute("currDate", date);
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recCurrDateList = resRecBiz.getRecContentByProcess(paramMap);
			if(recCurrDateList!=null && !recCurrDateList.isEmpty()){
				Map<String,Float> recCurrDateAvgMap=avg(recCurrDateList);
				model.addAttribute("recCurrDateAvgMap", recCurrDateAvgMap);
			}
			int d=Integer.valueOf(date);
			d=d-1;
			date=String.valueOf(d);
			model.addAttribute("PreviouYearDate", date);
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recpreviouYearList = resRecBiz.getRecContentByProcess(paramMap);
			if(recpreviouYearList!=null && !recpreviouYearList.isEmpty()){
				Map<String,Float> recpreviouYearAvgMap=avg(recpreviouYearList);
				model.addAttribute("recpreviouYearAvgMap", recpreviouYearAvgMap);
			}
			int s=Integer.valueOf(date);
			s=s-1;
			date=String.valueOf(s);
			model.addAttribute("FirstTwoYearsDate", date);
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recFirstTwoYearsList = resRecBiz.getRecContentByProcess(paramMap);
			if(recFirstTwoYearsList!=null && !recFirstTwoYearsList.isEmpty()){
				Map<String,Float> recFirstTwoYearsAvgMap=avg(recFirstTwoYearsList);
				model.addAttribute("recFirstTwoYearsAvgMap", recFirstTwoYearsAvgMap);
			}
		}

		if(recList!=null && !recList.isEmpty()){
			Map<String,Float> avgMap=avg(recList);
			if(waitSort!=null){
				final Map<String,Float> scoreMapTemp = avgMap;
				if("teacher".equals(gradeRole)){
					List<SysUser> userList = (List<SysUser>) waitSort;
					Collections.sort(userList,new Comparator<SysUser>() {
						@Override
						public int compare(SysUser u1,SysUser u2) {
							String k1 = u1.getUserFlow();
							String k2 = u2.getUserFlow();
							Float s1 = scoreMapTemp.get(k1+"_Total");
							Float s2 = scoreMapTemp.get(k2+"_Total");
							if(s1==null){
								s1=0f;
							}
							if(s2==null){
								s2=0f;
							}
							Float result = s2-s1;

							return result>0?1:result==0?0:-1;
						}

					});
				}else if("head".equals(gradeRole)){
					List<SysDept> depts = (List<SysDept>) waitSort;
					Collections.sort(depts,new Comparator<SysDept>() {
						@Override
						public int compare(SysDept d1,SysDept d2) {
							String k1 = d1.getDeptFlow();
							String k2 = d2.getDeptFlow();
							Float s1 = scoreMapTemp.get(k1+"_Total");
							Float s2 = scoreMapTemp.get(k2+"_Total");
							if(s1==null){
								s1=0f;
							}
							if(s2==null){
								s2=0f;
							}
							Float result = s2-s1;

							return result>0?1:result==0?0:-1;
						}

					});
				}
			}
			model.addAttribute("avgMap", avgMap);
		}
		model.addAttribute("scoreMap", scoreMap);
		return "jsres/institute/doctorinfo/gradeSearch";
	}

	/**
	 * 基地双向评分详情
	 * @param gradeRole
	 * @param keyCode
	 * @param operStartDate
	 * @param operEndDate
	 * @param date
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/gradeSearchDoc"})
	public String gradeSearchDoc(
			String gradeRole,
			String keyCode,
			String operStartDate,
			String operEndDate,
			String date,
			Model model
	){
		if(StringUtil.isNotBlank(operStartDate)){
			operStartDate = DateUtil.getDate(operStartDate)+"000000";
		}
		if(StringUtil.isNotBlank(operEndDate)){
			operEndDate = DateUtil.getDate(operEndDate)+"235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String,Object> scoreSumMap = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("sessionNumber",date);
		paramMap.put("operStartDate",operStartDate);
		paramMap.put("operEndDate",operEndDate);

		if("teacher".equals(gradeRole)){
			recType = ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();

			paramMap.put("teacherFlow",keyCode);
		}else if("head".equals(gradeRole)){
			recType = ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();

			paramMap.put("deptFlow",keyCode);
		}
		List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
		//获取评分数据
		List<Map<String, String>> recList = resRecBiz.getRecContentByProcess(paramMap);
		if(recList!=null && !recList.isEmpty()){
			model.addAttribute("recList",recList);

			Map<String,Float> scoreMap = new HashMap<String, Float>();

			for(Map<String,String> map : recList){
				String operUserFlow = map.get("operUserFlow");
				int scoreSum=0;
				for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
					for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
						int s=Integer.parseInt(resAssessCfgItemForm.getScore());
						scoreSum+=s;
					}
				}
				scoreSumMap.put(operUserFlow, scoreSum);
				String content = map.get("content");

				Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
				if(gradeMap!=null && !gradeMap.isEmpty()){
					for(String gk : gradeMap.keySet()){
						Object o = gradeMap.get(gk);
						Float score = 0f;
						if(o instanceof Map){
							Map<String,String> dataMap = (Map<String, String>) o;
							if(dataMap!=null){
								try {
									String scoreS = dataMap.get("score");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
									e.printStackTrace();
								}

								putMapVal(scoreMap,operUserFlow+gk,score);
							}
						}else{
							try {
								String scoreS = (String) gradeMap.get("totalScore");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
								e.printStackTrace();
							}

							putMapVal(scoreMap,operUserFlow,score);
						}
					}

				}
			}

			model.addAttribute("scoreMap",scoreMap);

			final Map<String,Float> scoreMapTemp = scoreMap;
			Collections.sort(recList,new Comparator<Map<String,String>>() {
				@Override
				public int compare(Map<String, String> m1,Map<String, String> m2) {
					String k1 = m1.get("operUserFlow")+m1.get("recFlow");
					String k2 = m2.get("operUserFlow")+m2.get("recFlow");
					Float s1 = scoreMapTemp.get(k1);
					Float s2 = scoreMapTemp.get(k2);
					Float result = s2-s1;

					return result>0?1:result==0?0:-1;
				}

			});
		}

		model.addAttribute("scoreSumMap",scoreSumMap);
		model.addAttribute("assessCfgList",assessCfgList);

		return "jsres/institute/doctorinfo/gradeSearchDoc";
	}

	//培训变更管理模块
	/**
	 * 基地变更查询
	 * @param currentPage
	 * @param doctor
	 * @param request
	 * @param model
	 * @param statusFlag
	 * @return
	 */
	@RequestMapping(value={"/changeBase"})
	public String changeBase(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model, String statusFlag){
		List<String> changeStatusIdList  =new ArrayList<String>();
		if(StringUtil.isNotBlank(statusFlag)){
			changeStatusIdList.add(JsResChangeApplyStatusEnum.OutApplyUnPass.getId());
			changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyUnPass.getId());
		}else{
			changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();
		ResDoctorOrgHistory orgHistory =new  ResDoctorOrgHistory();
		orgHistory.setChangeTypeId(JsResChangeTypeEnum.BaseChange.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts=jsDocOrgHistoryBiz.seearchInfoByFlow(orgHistory, changeStatusIdList,null, doctor,orgFlowList);
		model.addAttribute("historyExts", historyExts);
		return "jsres/institute/trainchange/findBaseChange";
	}

	/**
	 * 专业变更查询
	 * @param currentPage
	 * @param doctor
	 * @param request
	 * @param model
	 * @param passFlag
	 * @return
	 */
	@RequestMapping(value={"/searchChangeSpe"})
	public String searchChangeSpe(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model,String passFlag){
		ResDoctorOrgHistory orgHistory =new ResDoctorOrgHistory();
		orgHistory.setChangeTypeId(JsResChangeTypeEnum.SpeChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
		List<String> orgFlowList = new ArrayList<String>();
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		if(StringUtil.isNotBlank(passFlag)){
			if(GlobalConstant.FLAG_Y.equals(passFlag)){
				changeStatusIdList.add(JsResChangeApplySpeEnum.GlobalAuditPass.getId());
			}else{
				changeStatusIdList.add(JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
			}
		}else{
			changeStatusIdList.add(JsResChangeApplySpeEnum.GlobalAuditPass.getId());
			changeStatusIdList.add(JsResChangeApplySpeEnum.GlobalAuditunPass.getId());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsResDoctorOrgHistoryExt> historyExts=jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(orgHistory, changeStatusIdList, doctor,orgFlowList,null);
		model.addAttribute("historyExts", historyExts);
		return "jsres/institute/trainchange/changeSpeMain";
	}

	/**
	 * 医师延期查询
	 * @param model
	 * @param resRec
	 * @param currentPage
	 * @param request
	 * @param userFlow
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value="/delay")
	public String delay(Model model, ResRec resRec, Integer currentPage, HttpServletRequest request, String userFlow) throws DocumentException{
		List<String> orgFlowList = new ArrayList<String>();
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		List<String> operUserFlowList = new ArrayList<String>();
		if(StringUtil.isNotBlank(userFlow)){
			operUserFlowList.add(userFlow);
		}
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);

		PageHelper.startPage(currentPage,getPageSize(request));
		resRec.setRecTypeId(ResRecTypeEnum.Delay.getId());
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			resRec.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		List<ResRec> resRecList = resRecBiz.searchInfo(resRec, operUserFlowList, orgFlowList);
		Map<String, Object> dataMap = new HashMap<String,Object>();
		if(resRecList!=null&&!resRecList.isEmpty()){
			dataMap = jsResRecBiz.paraseDelayInfo(resRecList);
		}
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("resRecList", resRecList);
		return "jsres/institute/trainchange/delayInfo";
	}

	/**
	 * 基地变更详情
	 * @param doctorFlow
	 * @param recordFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/getChangeOrgDetail"})
	public String getChangeOrgDetail(String doctorFlow, String recordFlow, Model model){
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setDoctorFlow(doctorFlow);
		recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<ResDoctorRecruit> recruitsList=jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		model.addAttribute("recruitsList", recruitsList);
		ResRec resRec =new ResRec();
		List<String> operUserFlowList =new ArrayList<String>();
		operUserFlowList.add(doctorFlow);
		resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<ResRec> recList = resRecBiz.searchRecInfo(resRec, operUserFlowList);
		model.addAttribute("recList", recList);
		List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.Delay.getId(), doctorFlow);
		model.addAttribute("delayList", delayList);
		return "jsres/institute/trainchange/changOrgInfo";
	}

	/**
	 * 查看培训信息
	 * @return
	 */
	@RequestMapping("/getDoctorRecruit")
	public String getDoctorRecruit(String recruitFlow, String doctorFlow, Model model){
		boolean isLatest = false;//是否最新记录
		if(StringUtil.isNotBlank(recruitFlow)){
			ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
			if(doctorRecruit!=null){
				model.addAttribute("doctorRecruit", doctorRecruit);
				if(StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())){
					ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
					if(doctor!=null){
						model.addAttribute("doctor",doctor);

						String degreeType = doctor.getDegreeCategoryId();
						if(JsResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JsResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)){
							PubUserResume resume = userResumeBiz.readPubUserResume(doctorRecruit.getDoctorFlow());
							String content = resume.getUserResume();
							if(StringUtil.isNotBlank(content)){
								UserResumeExtInfoForm userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
								model.addAttribute("userResumeExt", userResumeExt);

								doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());
							}
						}
					}
				}
			}
			ResDoctorRecruit lastRecruit = new ResDoctorRecruit();
			lastRecruit.setDoctorFlow(doctorFlow);
			lastRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(lastRecruit, "CREATE_TIME DESC");
			if(recruitList != null && !recruitList.isEmpty()){
				lastRecruit = recruitList.get(0);
				if(lastRecruit.getRecruitFlow().equals(recruitFlow)){
					isLatest = true;
				}
			}
			String resRecType=ResRecTypeEnum.DoctorAuth.getId();
			List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,doctorFlow);
			if (resRec!=null&!resRec.isEmpty()) {
				model.addAttribute("resRec", resRec.get(0));
			}
			//最新记录 && 审核通过
			if(JsResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId()) ){
				ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
				docOrgHistory.setDoctorFlow(doctorFlow);
				docOrgHistory.setChangeTypeId(JsResChangeTypeEnum.BaseChange.getId());
				docOrgHistory.setRecruitFlow(recruitFlow);
				List<ResDoctorOrgHistory> docOrgHistoryList = jsDocOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
				if(docOrgHistoryList != null && !docOrgHistoryList.isEmpty()){
					model.addAttribute("docOrgHistoryList", docOrgHistoryList);
					model.addAttribute("latestDocOrgHistory", docOrgHistoryList.get(0));//最新变更记录
				}
				docOrgHistory.setChangeTypeId(JsResChangeTypeEnum.SpeChange.getId());
				List<ResDoctorOrgHistory> changeSpeList = jsDocOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
				if(changeSpeList != null && !changeSpeList.isEmpty()){
					model.addAttribute("changeSpeList", changeSpeList);
					model.addAttribute("lastChangeSpe",changeSpeList.get(0) );
				}
			}
		}
		model.addAttribute("isLatest", isLatest);
		return "jsres/institute/trainchange/trainInfo";
	}

	/**
	 * 个人基本信息
	 * @param model
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value="/doctorInfo")
	public String doctorInfo(String userFlow, Model model) throws DocumentException {
		SysUser sysUser = userBiz.readSysUser(userFlow);
		ResDoctor resDoctor = resDoctorBiz.readDoctor(userFlow);
		if (resDoctor != null) {
			if (StringUtil.isNotBlank(resDoctor.getGraduatedId())) {
				List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
				if (sysDictList != null && !sysDictList.isEmpty()) {
					for (SysDict dict : sysDictList) {
						if (dict.getDictId().equals(resDoctor.getGraduatedId())) {
							resDoctor.setGraduatedName(dict.getDictName());
						}
					}
				}
			}
			if (StringUtil.isNotBlank(resDoctor.getDoctorTypeId()) && JsResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())) {
				if (StringUtil.isNotBlank(resDoctor.getWorkOrgId())) {
					List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
					if (sysDictList != null && !sysDictList.isEmpty()) {
						for (SysDict dict : sysDictList) {
							if (dict.getDictId().equals(resDoctor.getWorkOrgId())) {
								resDoctor.setWorkOrgName(dict.getDictName());
							}
						}
					}
				}
			}
		}
		String resRecType = ResRecTypeEnum.DoctorAuth.getId();
		List<ResRec> resRec = resRecBiz.searchByRecWithBLOBs(resRecType, userFlow);
		if (resRec != null & !resRec.isEmpty()) {
			model.addAttribute("resRec", resRec.get(0));
		}
		model.addAttribute("user", sysUser);
		model.addAttribute("doctor", resDoctor);
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
		if (pubUserResume != null) {
			String xmlContent = pubUserResume.getUserResume();
			if (StringUtil.isNotBlank(xmlContent)) {
				//xml转换成JavaBean
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
				if (userResumeExt != null) {
					if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
						List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
						if (sysDictList != null && !sysDictList.isEmpty()) {
							for (SysDict dict : sysDictList) {
								if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
									if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
										userResumeExt.setGraduatedName(dict.getDictName());
									}
								}
							}

						}
					}
					model.addAttribute("userResumeExt", userResumeExt);
				}
			}
		}
		return "jsres/institute/trainchange/doctorInfo";
	}

	//统计分析模块
	@RequestMapping("/statisticCountryOrg")
	public String statisticCountryOrg(Model model, String sessionNumber, String trainTypeId, String orgLevel, String[] datas){
		Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
		Map<Object, Object> joingCountMap=new HashMap<Object,Object>();//保存每家基地的协同基地的每个专业的总数
		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		List<String> orgFlowList=null;
		List<String>docTypeList=new ArrayList<String>();
		List<String>jointOrgFlowList=null;
		ResDoctor doctor=new ResDoctor();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		//查询所有国家基地
		List<SysOrg> sysOrgList=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		if(StringUtil.isNotBlank(orgLevel)){
			org.setOrgLevelId(orgLevel);
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			sysOrgList=orgBiz.searchAllSysOrg(org);
		}else{
			sysOrgList=jointOrgBiz.searchCouAndProList(org);
		}
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg o:sysOrgList){
				orgFlowList=new ArrayList<String>();
				jointOrgFlowList=new ArrayList<String>();
				ResOrgSpe resOrgSpe=new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
				resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
					for(ResOrgSpe r:orgSpeList){
						orgSpeFlagMap.put(o.getOrgFlow()+r.getSpeTypeId()+r.getSpeId(),GlobalConstant.FLAG_Y);
					}
				}
				orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
				List<ResJointOrg>jointOrgList=jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					for(ResJointOrg resJointOrg:jointOrgList){
						jointOrgFlowList.add(resJointOrg.getJointOrgFlow());
						orgFlowList.add(resJointOrg.getJointOrgFlow());
					}
					List<Map<String, Object>>jointCountList=resStatisticBiz.statisticJointCount(recruit, jointOrgFlowList,doctor,docTypeList);
					if(jointCountList!=null&&!jointCountList.isEmpty()){
						for(Map<String,Object> en:jointCountList){
							Object key=o.getOrgFlow()+en.get("key");
							Object value= en.get("value");
							joingCountMap.put(key, value);
						}
					}
				}
				//每家基地每个专业的医师培训记录总数
				List<Map<String,Object>> doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
				if(doctorCountList!=null&&!doctorCountList.isEmpty()){
					for(Map<String,Object> en:doctorCountList){
						Object key=o.getOrgFlow()+en.get("key");
						Object value= en.get("value");
						totalCountMap.put(key, value);
					}
				}
			}
		}
		model.addAttribute("joingCountMap", joingCountMap);
		model.addAttribute("datas", datas);
		model.addAttribute("totalCountMap", totalCountMap);
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);
		model.addAttribute("sysOrgList", sysOrgList);
		return "/jsres/institute/statistics/statisticCountryOrg";
	}

	@RequestMapping("/exportExcel")
	public void exportExcel(String sessionNumber,String trainTypeId,String orgLevel,String[] datas,HttpServletResponse response)throws Exception{
		List<SysOrg> sysOrgList=new ArrayList<SysOrg>();//存放所有的符合条件的sysOrg
		List<SysDict> typeSpeList=new ArrayList<SysDict>();//存放每个培训类别的专业
		Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
		Map<Object, Object> joingCountMap=new HashMap<Object,Object>();//保存每家基地的协同基地的每个专业的总数
		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		Map<Object, Object> zongjiMap=new HashMap<Object, Object>();//统计小计
		List<String>orgFlowList=null;
		List<String>docTypeList=new ArrayList<String>();
		List<String>jointOrgFlowList=null;
		ResDoctor doctor=new ResDoctor();
		if(StringUtil.isNotBlank(trainTypeId)){
			typeSpeList=dictBiz.searchDictListByDictTypeId(trainTypeId);//每个培训类别对应的专业
		}
		SysOrg org=new SysOrg();
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(ss.getOrgProvId());
		if(StringUtil.isBlank(orgLevel)){
			sysOrgList=jointOrgBiz.searchCouAndProList(org);//查询符合要求的基地
		}else{
			org.setOrgLevelId(orgLevel);
			sysOrgList=orgBiz.searchAllSysOrg(org);//查询符合要求的 基地
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg o:sysOrgList){
				orgFlowList=new ArrayList<String>();
				jointOrgFlowList=new ArrayList<String>();
				ResOrgSpe resOrgSpe=new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
				resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
					for(ResOrgSpe r:orgSpeList){
						orgSpeFlagMap.put(o.getOrgFlow()+r.getSpeTypeId()+r.getSpeId(),GlobalConstant.FLAG_Y);
					}
				}
				orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
				List<ResJointOrg>jointOrgList=jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					for(ResJointOrg resJointOrg:jointOrgList){
						jointOrgFlowList.add(resJointOrg.getJointOrgFlow());
						orgFlowList.add(resJointOrg.getJointOrgFlow());
					}
					List<Map<String, Object>>jointCountList=resStatisticBiz.statisticJointCount(recruit, jointOrgFlowList,doctor,docTypeList);
					if(jointCountList!=null&&!jointCountList.isEmpty()){
						for(Map<String,Object> en:jointCountList){
							Object key=o.getOrgFlow()+en.get("key");
							Object value= en.get("value");
							joingCountMap.put(key, value);
						}
					}
				}
				//每家基地每个专业的医师培训记录总数
				List<Map<String,Object>> doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
				if(doctorCountList!=null&&!doctorCountList.isEmpty()){
					for(Map<String,Object> en:doctorCountList){
						Object key=o.getOrgFlow()+en.get("key");
						Object value= en.get("value");
						totalCountMap.put(key, value);
					}
				}
			}
		}
		resStatisticBiz.export(sysOrgList, typeSpeList, trainTypeId, totalCountMap, zongjiMap, orgSpeFlagMap, joingCountMap, response);
	}

	@RequestMapping("/statisticJointOrg")
	public String statisticJointOrg(Model model,ResDoctorRecruit recruit ,ResDoctor doctor,String orgLevel,String trainTypeId,String[] datas){
		Map<Object, Object> jointOrgListMap= new HashMap<Object, Object>();//每个符合添加的org的协同基地的map
		Map<Object, Object> jointOrgSpeMap = new HashMap<Object,Object>();//协同基地的spe
		Map<Object, Object> jointOrgDocCountMap = new HashMap<Object,Object>();//协同基地每个soe的总数
		List<SysOrg> sysOrgList=new ArrayList<SysOrg>();//存放所有的符合条件的sysOrg
		List<String> jointOrgFlowList=new ArrayList<String>();//存放所有的协同基地的orglist
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(ss.getOrgProvId());
		if(StringUtil.isBlank(orgLevel)){
			sysOrgList=jointOrgBiz.searchCouAndProList(org);
		}else{
			org.setOrgLevelId(orgLevel);
			sysOrgList=orgBiz.searchAllSysOrg(org);
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		recruit.setCatSpeId(trainTypeId);
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList ){//国家基地和省级基地的list
				List<ResJointOrg> jointOrgList=jointOrgBiz.searchResJointByOrgFlow(s.getOrgFlow());//分别查出每个基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					int c=0;
					for(ResJointOrg re:jointOrgList){//循环协同基地
						String flag="";
						if(c==0){
							flag=GlobalConstant.FLAG_Y;
						}else if(c==1){
							flag=GlobalConstant.FLAG_N;
						}else if(c==2){
							flag=GlobalConstant.FLAG_F;
						}//标记
						jointOrgListMap.put(s.getOrgFlow()+flag, re);//以当前的国家基地标记key   Map
						jointOrgFlowList.add(re.getJointOrgFlow());
						c++;
					}
				}
			}
		}
		if(jointOrgFlowList!=null &&!jointOrgFlowList.isEmpty()){
			ResOrgSpe resOrgSpe=new ResOrgSpe();
			for(String s:jointOrgFlowList){
				resOrgSpe.setOrgFlow(s);//添加条件查询每个协同基地的专业
				String type="";
				if(StringUtil.isNotBlank(trainTypeId)){
					resOrgSpe.setSpeTypeId(trainTypeId);
				}else if(StringUtil.isNotBlank(recruit.getSessionNumber())){
					type=GlobalConstant.FLAG_Y;
					if(Integer.parseInt(recruit.getSessionNumber())<2015){
						resOrgSpe.setSpeTypeId(TrainCategoryEnum.DoctorTrainingSpe.getId());
					}else{
						resOrgSpe.setSpeTypeId(TrainCategoryEnum.WMFirst.getId());
					}
				}
				List<ResOrgSpe> speList=resOrgSpeBiz.searchSpeByCondition(resOrgSpe,type);
				jointOrgSpeMap.put(s, speList);

			}
			recruit.setCatSpeId(trainTypeId);
			List<Map<String,Object>> doctorSpeList=resStatisticBiz.statisticDocCouByType(recruit, jointOrgFlowList, doctor,docTypeList);
			for(Map<String,Object> en:doctorSpeList){
				Object key= en.get("key");
				Object value= en.get("value");
				jointOrgDocCountMap.put(key, value);// Map 已协同基地的flow家伙是哪个spe作为key
			}
		}
		model.addAttribute("datas", datas);
		model.addAttribute("jointOrgDocCountMap", jointOrgDocCountMap);
		model.addAttribute("jointOrgListMap", jointOrgListMap);
		model.addAttribute("jointOrgSpeMap", jointOrgSpeMap);
		model.addAttribute("sysOrgList", sysOrgList);
		return "/jsres/institute/statistics/statisticJointOrgInfo";
	}

	@RequestMapping("/statisticsAppUserForOrg")
	public String statisticsAppUserForOrg(Model model,String startTime,String endTime,String catSpeId){
		List<SysOrg> sysOrgList =new ArrayList<SysOrg>();//所有国家基地和省级基地
		List<String> jointOrgFlowList=new ArrayList<String>();//存放所有的协同基地的orglist
		List<String> orgFlowList=new ArrayList<String>();//主基地的orglist
		Map<Object, Object> percentMap=new HashMap<Object,Object>();//存放百分比的map
		Map<Object, Object> jointPercentMap=new HashMap<Object,Object>();//xietong存放百分比的map
		Map<Object, Object> jointOrgListMap= new HashMap<Object, Object>();//每个符合添加的org的协同基地的map
		SysOrg org=new SysOrg();
		SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(ss.getOrgProvId());
		sysOrgList=jointOrgBiz.searchCouAndProList(org);//省级基地和国家基地
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList ){//国家基地和省级基地的list
				List<ResJointOrg> jointOrgList=jointOrgBiz.searchResJointByOrgFlow(s.getOrgFlow());//分别查出每个基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					jointOrgListMap.put(s.getOrgFlow(), jointOrgList);//以当前的国家基地标记key存放一个list   Map
					for(ResJointOrg re:jointOrgList){//循环协同基地
						jointOrgFlowList.add(re.getJointOrgFlow());
					}
				}
				orgFlowList.add(s.getOrgFlow());//存放主基地的orgFlow
			}
		}
		String month = DateUtil.getCurrDate2().substring(0, 6);
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
			recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
		}
		if(StringUtil.isNotBlank(catSpeId)){
			recruit.setCatSpeId(catSpeId);
		}
		List<String> delTypeList = new ArrayList<String>();
		delTypeList.add(ResRecTypeEnum.DoctorAuth.getId());
		delTypeList.add(ResRecTypeEnum.Delay.getId());
		delTypeList.add(ResRecTypeEnum.ReturnTraining.getId());
		delTypeList.add(ResRecTypeEnum.AfterSummary.getId());
		delTypeList.add(ResRecTypeEnum.AfterEvaluation.getId());
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<Map<String, Object>> docCountByOrg = resStatisticBiz.statisticDocCountByOrgForTime(null, orgFlowList, startTime, endTime,recruit);//主基地每家医院的人数总数
		List<Map<String, Object>> appCountByOrg = resStatisticBiz.statisticRealAppCount(delTypeList,recruit,endTime, startTime, orgFlowList, null,month);//统计主基地每家医院实际使用app的人数
		DecimalFormat df = new DecimalFormat("0.00%");
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList){
				double total=0;double real=0;
				for(Map<String, Object> en:docCountByOrg){
					if(s.getOrgFlow().equals(en.get("key"))){
						total=Double.parseDouble(en.get("value")+"");
					}
				}
				for(Map<String, Object> en:appCountByOrg){
					if(s.getOrgFlow().equals(en.get("key"))){
						real=Double.parseDouble(en.get("value")+"");
					}
				}
				double average=real/total;
				if(total==0||average==0||real==0){
					percentMap.put(s.getOrgFlow(),0+"%");
				}else{
					percentMap.put(s.getOrgFlow(),df.format(average));
				}
			}
		}
		if(jointOrgFlowList!=null &&!jointOrgFlowList.isEmpty()){//所有的协同基地
			List<Map<String, Object>> jointDocCountByOrg = resStatisticBiz.statisticDocCountByOrgForTime(GlobalConstant.FLAG_Y, orgFlowList, startTime, endTime,recruit);//协同基地基地每家医院的人数总数
			List<Map<String, Object>> JointAppCountByOrg = resStatisticBiz.statisticRealAppCount(delTypeList,recruit,endTime, startTime, orgFlowList, GlobalConstant.FLAG_Y,month);//统计协同基地每家医院实际使用app的人数
			for(String s:jointOrgFlowList){//循环list
				double t=0;double r=0;
				for(Map<String, Object> en:jointDocCountByOrg){
					if(s.equals(en.get("key"))){
						t=Double.parseDouble(en.get("value")+"");
					}
				}
				for(Map<String, Object> en:JointAppCountByOrg){
					if(s.equals(en.get("key"))){
						r=Double.parseDouble(en.get("value")+"");
					}
				}
				double a=r/t;
				if(t==0||a==0){
					jointPercentMap.put(s,0+"%");
				}else{
					jointPercentMap.put(s,df.format(a));
				}
			}
		}
		model.addAttribute("sysOrgList", sysOrgList);
		model.addAttribute("percentMap", percentMap);
		model.addAttribute("jointPercentMap", jointPercentMap);
		model.addAttribute("jointOrgListMap", jointOrgListMap);
		return "/jsres/institute/statistics/statisticsAppUserForOrg";
	}

	@RequestMapping("/statisticsAppUser")
	public String statisticsAppUser(Model model,String orgFlow,String startTime,String endTime,String catSpeId){
		Map<Object, Object> percentMap=new HashMap<Object, Object>(); //存有百分比的map
		Map<Object, Object> countMap=new HashMap<Object, Object>(); //存有具体数值的map
		List<String> timeGapMon=new ArrayList<String>();//存放横坐标
		SysUser currUser=GlobalContext.getCurrentUser();
		ResRec resRec=new ResRec();
		ResDoctorRecruit recruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(orgFlow)){
			recruit.setOrgFlow(orgFlow);
			resRec.setOrgFlow(orgFlow);
		}else{
			resRec.setOrgFlow(currUser.getOrgFlow());
			recruit.setOrgFlow(currUser.getOrgFlow());
		}
		if(StringUtil.isBlank(startTime)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_reg_date"))&&StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				String endDate = Integer.parseInt(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))-1+"-"+InitConfig.getSysCfg("res_reg_date");
				String[] date=DateUtil.addDate(endDate, 1).split("-");
				startTime = date[0]+date[1]+date[2];
			}else{
				startTime="201509";//开始时间
			}
		}
		if(StringUtil.isBlank(endTime)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_reg_date"))&&StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				endTime= DateUtil.getCurrDate2().substring(0, 8);//结束时间
			}
		}
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
			recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
		}
		if(StringUtil.isNotBlank(catSpeId)){
			recruit.setCatSpeId(catSpeId);
		}
		List<String> delTypeList = new ArrayList<String>();
		delTypeList.add(ResRecTypeEnum.Delay.getId());
		delTypeList.add(ResRecTypeEnum.ReturnTraining.getId());
		delTypeList.add(ResRecTypeEnum.DoctorAuth.getId());
		delTypeList.add(ResRecTypeEnum.AfterSummary.getId());
		delTypeList.add(ResRecTypeEnum.AfterEvaluation.getId());
		List<Map<String, Object>> docCountByOrg = resStatisticBiz.statisticDocCountByOrg(recruit,null,null);//每家医院的人数总数
		List<Map<String, Object>> appCountByOrg = resStatisticBiz.statisticAppCountByOrg(recruit,resRec,endTime,startTime,delTypeList,null);//统计每家医院实际使用app的人数
		Object orgTotal=0;//每家基地的总数
		if(docCountByOrg!=null&&!docCountByOrg.isEmpty()){
			orgTotal=docCountByOrg.get(0).get("value");
		}
		int year1=Integer.parseInt(startTime.substring(0, 4));
		int year2=Integer.parseInt(endTime.substring(0, 4));
		int  month1=Integer.parseInt(startTime.substring(4, 6));
		int  month2=Integer.parseInt(endTime.substring(4, 6));
		if((year2>year1)||(year2==year1&&month2>=month1)){//算出两个时间相差的月份
			for (int i = year1; i <= year2; i++) {
				int monthCount = 12;
				int monthStart = 1;
				if (i == year1) {
					monthStart = month1;
					if(year1==year2){
						if(month1!=month2){
							monthCount = 12-monthStart;
						}else{
							monthCount=1;
						}
					}else{
						monthCount=12-monthStart+1;
					}
				} else if (i == year2) {
					monthCount = month2;
				}
				for(int j = 0; j < monthCount; j++){
					int temp = monthStart+j;
					String mark="";
					String flag="";
					if(temp >=10){
						mark=i+""+(monthStart+j);
					}else{
						mark=i+""+("0"+(monthStart+j));
					}
					timeGapMon.add(mark);//存放横坐标
					for(Map<String, Object> en :appCountByOrg){
						if(en.get("key").equals(mark)){
							Object key=en.get("key");
							Object value=en.get("value");
							percentMap.put(key, (Double.parseDouble(value+"")/Double.parseDouble(orgTotal+"")));
							countMap.put(key, value);
							flag=GlobalConstant.FLAG_Y;
						}
					}
					if(StringUtil.isBlank(flag)){//查询中没有这条记录
						Object key= mark;
						Object value=0;
						percentMap.put(key, Double.parseDouble(value+""));
						countMap.put(key, value);
					}
				}

			}
		}
		model.addAttribute("timeGapMon", timeGapMon);
		model.addAttribute("percentMap", percentMap);
		model.addAttribute("countMap", countMap);
		return "/jsres/institute/statistics/statisticsAppUser";
	}

	@RequestMapping("/statisticsNoAppUser")
	public String statisticsNoAppUser(Model model, String orgFlow, String catSpeId, Integer currentPage, HttpServletRequest request, String startDate, String endDate){
		SysUser currUser = GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(orgFlow)){
			orgFlow = currUser.getOrgFlow();
		}
		if(StringUtil.isNotBlank(startDate)){
			String[] time = startDate.split("-");
			startDate=time[0]+time[1];
		}
		if(StringUtil.isNotBlank(endDate)){
			String[] time = endDate.split("-");
			endDate = time[0]+time[1];
		}
		ResRec resRec = new ResRec();
		resRec.setOrgFlow(orgFlow);
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setOrgFlow(orgFlow);
		if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
			recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
		}
		if(StringUtil.isNotBlank(catSpeId)){
			recruit.setCatSpeId(catSpeId);
		}
		recruit.setAuditStatusId(JsResDoctorAuditStatusEnum.Passed.getId());
		List<String> delTypeList = new ArrayList<String>();
		delTypeList.add(ResRecTypeEnum.Delay.getId());
		delTypeList.add(ResRecTypeEnum.ReturnTraining.getId());
		delTypeList.add(ResRecTypeEnum.DoctorAuth.getId());
		delTypeList.add(ResRecTypeEnum.AfterSummary.getId());
		delTypeList.add(ResRecTypeEnum.AfterEvaluation.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsDoctorInfoExt> docInfoExtsList =resStatisticBiz.statisticNoAppUser(recruit, resRec, delTypeList,startDate,endDate,null);
		model.addAttribute("docInfoExtsList", docInfoExtsList);
		return "/jsres/institute/statistics/noAppUsers";
	}

	@RequestMapping("/searchTeachTrain")
	public String searchTeachTrain(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage){
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> teacherTrainingList =resStatisticBiz.searchTeacherInfo(teacherTraining);
		model.addAttribute("teacherTrainingList", teacherTrainingList);
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		orgs=orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		return "/jsres/institute/statistics/searchTeachTrain";
	}

	private Map<String, Float> avg(List<Map<String,String>> recList){
		//均分Map
		Map<String,Float> avgMap = new HashMap<String, Float>();
		String count = "Count";
		String total = "Total";
		for(Map<String,String> map : recList){
			String key = map.get("key");

			putMapVal(avgMap,key+count,1f);

			String content = map.get("content");

			Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
			if(gradeMap!=null && !gradeMap.isEmpty()){
				for(String gk : gradeMap.keySet()){
					Object o = gradeMap.get(gk);
					if(o instanceof Map){
						Map<String,String> dataMap = (Map<String, String>) o;
						if(dataMap!=null){
							Float score = 0f;
							try {
								String scoreS = dataMap.get("score");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
								e.printStackTrace();
							}

							putMapVal(avgMap,key+"_"+gk,score);
						}
					}else{
						Float score = 0f;
						try {
							String scoreS = (String)o;
							score = Float.valueOf(scoreS);
						} catch (Exception e) {
							e.printStackTrace();
						}

						putMapVal(avgMap,key+"_"+total,score);
					}
				}

			}
		}

		if(!avgMap.isEmpty()){
			Set<String> keySet = avgMap.keySet();
			for(String k : keySet){
				if(k!=null){
					int ki = k.indexOf("_");
					if(!(ki<0)){
						String dataKey = k.substring(0,ki);

						Float itemTotal = avgMap.get(k);
						if(itemTotal!=null){
							Float countUser = avgMap.get(dataKey+count);
							if(itemTotal!=0 && countUser!=0){
								Float result = itemTotal/countUser;
								result = new BigDecimal(result).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
								avgMap.put(k,result);
							}
						}
					}
				}
			}
		}
		return  avgMap;
	}

	private void putMapVal(Map<String,Float> map,String key,Float val){
		if(map!=null){
			Float v = map.get(key);
			if(v==null){
				v = val;
			}else{
				v+=val;
			}
			v = new BigDecimal(v).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
			map.put(key,v);
		}
	}
}
