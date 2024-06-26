package com.pinde.sci.ctrl.sczyres;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IRecruitCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sczyres.DoctorGraduationBiz;
import com.pinde.sci.biz.sczyres.DoctorRecruitBiz;
import com.pinde.sci.biz.sczyres.DoctorSingupBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.sczyres.SczyRecDocTypeEnum;
import com.pinde.sci.enums.sczyres.SpeCatEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.MsgTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.sczyres.ExtInfoForm;
import com.pinde.sci.form.sczyres.SingUpForm;
import com.pinde.sci.form.sczyres.WorkResumeForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.DateCfgMsg;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/sczyres/doctor")
public class DoctorController extends GeneralController{
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private DoctorSingupBiz doctorSingupBiz;
	@Autowired
	private DoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IRecruitCfgBiz recruitCfgBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private DoctorGraduationBiz doctorGraduationBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	
	@RequestMapping("/home")
	public String home(Integer currentPage , Model model){
		SysUser user = GlobalContext.getCurrentUser();
		ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
		model.addAttribute("doctor" , doctor);
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		recruit.setDoctorFlow(user.getUserFlow());
		recruit.setRecruitYear(regYear);
		List<ResDoctorRecruit> recruits = this.doctorRecruitBiz.findDoctorRecruit(recruit , "CREATE_TIME" , null);
		if(!recruits.isEmpty()){
			recruit = recruits.get(recruits.size()-1);
			model.addAttribute("recruit" , recruit);
		}
		
		//读取系统消息
		PageHelper.startPage(currentPage,10);
		PubMsgExample example = new PubMsgExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andReceiverEqualTo(user.getUserFlow()).andMsgTypeIdEqualTo(MsgTypeEnum.Sys.getId());
		example.setOrderByClause("MSG_TIME desc");
		List<PubMsg> msgList = msgBiz.searchMessageWithBLOBs(example);
		if(msgList!=null && msgList.size()>0){
			model.addAttribute("msgList",msgList);
			
			int newMsg = 0;
			for(PubMsg msg : msgList){
				if(!GlobalConstant.FLAG_Y.equals(msg.getReceiveFlag())){
					newMsg++;
				}
			}
			model.addAttribute("newMsg",newMsg);
		}

		//招录时间节点
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		model.addAttribute("recruitCfg" , recruitCfg);

		String currDate = DateUtil.getCurrDate();
		DateCfgMsg regDateCfgMsg = new DateCfgMsg(recruitCfg);
		regDateCfgMsg.setRegDateMsg(currDate);

//		DateCfgMsg wishDateCfgMsg = new DateCfgMsg(recruitCfg);
//		wishDateCfgMsg.setWishDateMsg(currDate);

		DateCfgMsg admitDateCfgMsg = new DateCfgMsg(recruitCfg);
		admitDateCfgMsg.setAdmitDateMsg(currDate);

		DateCfgMsg graduationDateCfgMsg = new DateCfgMsg(recruitCfg);
		graduationDateCfgMsg.setGraduationDateMsg(currDate);

		model.addAttribute("regDateCfgMsg" , regDateCfgMsg);
//		model.addAttribute("wishDateCfgMsg" , wishDateCfgMsg);
		model.addAttribute("admitDateCfgMsg" , admitDateCfgMsg);
		model.addAttribute("admitFlag",admitDateCfgMsg.getAllowAdmit());
		model.addAttribute("graduationDateCfgMsg" , graduationDateCfgMsg);
		return "sczyres/doctor/index";
	}
	
	@RequestMapping("/readMsg")
	@ResponseBody
	public String readMsg(PubMsg msg){
		int result = msgBiz.updateMsg(msg);
		if(GlobalConstant.ZERO_LINE!=result){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	@RequestMapping("/singup")
	public String singup(Model model){
		SysUser user = GlobalContext.getCurrentUser();
		String userFlow = user.getUserFlow();
		user = this.userBiz.readSysUser(userFlow);
		model.addAttribute("user" , user);
		ResDoctor doctor = doctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor" , doctor);
		PubUserResume resume = userResumeBiz.readPubUserResume(userFlow);
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = doctorSingupBiz.parseExtInfoXml(resumeXml);
			model.addAttribute("extInfo" ,extInfo);
		}

		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		String currDate = DateUtil.getCurrDate();
		DateCfgMsg regDateCfgMsg = new DateCfgMsg(recruitCfg);
		regDateCfgMsg.setRegDateMsg(currDate);
//		DateCfgMsg wishDateCfgMsg = new DateCfgMsg(recruitCfg);
//		wishDateCfgMsg.setWishDateMsg(currDate);

		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(user.getUserFlow());
		recruit.setRecruitYear(regYear);
		List<ResDoctorRecruit> recruits = this.doctorRecruitBiz.findDoctorRecruit(recruit , "CREATE_TIME" , null);
		model.addAttribute("recruits" , recruits);

		if(recruits!=null&&recruits.size()>0){
			Map<String,Object> moreSpeMap = new HashMap<>();
			for(ResDoctorRecruit doctorRecruit:recruits){
				if("Y".equals(doctorRecruit.getSwapFlag())){
					String recruitFlow = doctorRecruit.getRecruitFlow();
					ScresRecuritMoreSpe search = new ScresRecuritMoreSpe();
					search.setRecruitFlow(recruitFlow);
					List<ScresRecuritMoreSpe> moreSpeList = doctorRecruitBiz.searchMoreSpe(search);
					moreSpeMap.put(recruitFlow,moreSpeList);
				}
			}
			model.addAttribute("moreSpeMap",moreSpeMap);
		}

		if(regDateCfgMsg.getAllowReg()){
			if(doctor!=null && (RegStatusEnum.Passing.getId().equals(doctor.getDoctorStatusId()) ||
				RegStatusEnum.Passed.getId().equals(doctor.getDoctorStatusId()) ||
					RegStatusEnum.UnPassed.getId().equals(doctor.getDoctorStatusId()))){
				//资料待审核/审核通过/审核不通过 不能修改
				return "sczyres/doctor/singupinfofordoctor";
			}
			if(recruits!=null&&recruits.size()>0){
				ResDoctorRecruit recruit1 = recruits.get(0);
				//录取过(包括过与不过)的不能修改
				if(StringUtil.isNotBlank(recruit1.getRecruitFlag())){
					return "sczyres/doctor/singupinfofordoctor";
				}
			}
			return "sczyres/doctor/singup";
		}
//		if(wishDateCfgMsg.getAllowWish()) {//第二次报名
//			if(recruits==null||recruits.size()==0){
//				//没提交过的不能修改
//				return "sczyres/doctor/singupinfofordoctor";
//			}else{
//				//第一次已录取的不能报名
//				ResDoctorRecruit recruit1 = recruits.get(0);
//				if("Y".equals(recruit1.getConfirmFlag())){
//					return "sczyres/doctor/singupinfofordoctor";
//				}
//			}
//			if(doctor!=null && !(RegStatusEnum.Passed.getId().equals(doctor.getDoctorStatusId()))){//首次报名没有通过的不能再报名
//				return "sczyres/doctor/singupinfofordoctor";
//			}
//			if(doctor!=null && (RegStatusEnum.Passing.getId().equals(doctor.getDoctorStatusId()) ||
//					RegStatusEnum.Passed.getId().equals(doctor.getDoctorStatusId())) &&"2".equals(doctor.getSczyRecriutBanch())
//					){
//				//资料待审核或者审核通过 不能修改
//				return "sczyres/doctor/singupinfofordoctor";
//			}
//			if(recruits!=null&&recruits.size()>0 &&"2".equals(doctor.getSczyRecriutBanch())){
//				ResDoctorRecruit recruit1 = recruits.get(0);
//				//录取过(包括过与不过)的不能修改
//				if(StringUtil.isNotBlank(recruit1.getRecruitFlag())){
//					return "sczyres/doctor/singupinfofordoctor";
//				}
//			}
//			return "sczyres/doctor/singup";
//		}
		return "sczyres/doctor/singupinfofordoctor";
	}
	
	/**
	 * 学员调剂
	 * @return
	 */
	@RequestMapping("/swap")
	public String swap(Model model){
		SysUser user = GlobalContext.getCurrentUser();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		//是否确认录取过
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(user.getUserFlow());
		recruit.setRecruitYear(regYear);
		List<ResDoctorRecruit> recruits = this.doctorRecruitBiz.findDoctorRecruit(recruit , "CREATE_TIME" , null);
		for(ResDoctorRecruit rdr:recruits){
			if(GlobalConstant.FLAG_Y.equals(rdr.getRecruitFlag())){//目前是这个限制 ， 如果需要判断是否确认录取 是否强制学生点击拒绝录取
				model.addAttribute("msg" , "已经被录取不可调剂") ;
				return "sczyres/doctor/notallowswap";
			}
		}
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		String currDate = DateUtil.getCurrDate();
		DateCfgMsg regDateCfgMsg = new DateCfgMsg(recruitCfg);
		regDateCfgMsg.setSwapDateMsg(currDate);
		if(regDateCfgMsg.getAllowSwap()){
			SysOrgExample orgExample = new SysOrgExample();
			orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
			orgExample.setOrderByClause("ORDINAL");
			List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
			model.addAttribute("hospitals", orgs);
			return "sczyres/doctor/swap";
		}else{
			model.addAttribute("msg" , "当前调剂"+regDateCfgMsg.getMsg()) ;
			return "sczyres/doctor/notallowswap";
		}
		
		//是否在调剂日期中
	}
	
	@RequestMapping("/getrecruit")
	public String getRecruit(Model model){
		SysUser user = GlobalContext.getCurrentUser();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).
				andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId()).andOrgLevelIdIsNotNull();
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);
		ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
		model.addAttribute("doctor" , doctor);
		PubUserResume resume = userResumeBiz.readPubUserResume(user.getUserFlow());
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = doctorSingupBiz.parseExtInfoXml(resumeXml);
			model.addAttribute("extInfo" ,extInfo);
		}
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(user.getUserFlow());
		recruit.setRecruitYear(regYear);
		List<ResDoctorRecruit> recruits = this.doctorRecruitBiz.findDoctorRecruit(recruit , "CREATE_TIME" , null);
		if(!recruits.isEmpty()){
			recruit = recruits.get(0);
			model.addAttribute("recruit" , recruit);
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
			//第二、三志愿
			String recruitFlow = recruit.getRecruitFlow();
			ScresRecuritMoreSpe search = new ScresRecuritMoreSpe();
			search.setRecruitFlow(recruitFlow);
			List<ScresRecuritMoreSpe> moreSpeList = doctorRecruitBiz.searchMoreSpe(search);
			if(moreSpeList!=null&&moreSpeList.size()>0){
				for(ScresRecuritMoreSpe moreSpe:moreSpeList){
					if("2".equals(moreSpe.getOrderNum())){
						model.addAttribute("moreSpe2",moreSpe);
					}
					if("3".equals(moreSpe.getOrderNum())){
						model.addAttribute("moreSpe3",moreSpe);
					}
				}
			}
		}
		return "sczyres/doctor/recruit";
	}
	
	@RequestMapping("/saveRecruit")
	@ResponseBody
	public String saveRecruit(ResDoctorRecruitWithBLOBs recruit,String jsonData){
		SysUser user = GlobalContext.getCurrentUser();
		recruit.setDoctorFlow(user.getUserFlow());
		this.doctorSingupBiz.saveRecruit(recruit,jsonData);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	@RequestMapping("/findcatspe")
	@ResponseBody
	public Object findCatSpe(String orgFlow){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		//可选专业
		SysCfg cfg = cfgBiz.read("scres_spe_allow");
		List<String> allowSpes = null;
		if(cfg!=null){
			String spes2 = cfg.getCfgValue();
			if(StringUtil.isNotBlank(spes2)){
				String[] speList = spes2.split(",");
				allowSpes = Arrays.asList(speList);
			}
		}
		List<ResOrgSpeAssign> resultSpes = new ArrayList<ResOrgSpeAssign>();
		List<ResOrgSpeAssign> spes = this.doctorRecruitBiz.findSpeAssign(orgFlow, regYear);
		if(allowSpes!=null&&allowSpes.size()>0){
			for(ResOrgSpeAssign spe:spes){
				if(spe.getAssignPlan()!=null && spe.getAssignPlan().compareTo(spe.getAssignReal()==null?new BigDecimal(0):spe.getAssignReal())>0&&allowSpes.contains(spe.getSpeId())){
					resultSpes.add(spe);
				}
			}
		}
		return resultSpes;
		
	}
	
	@RequestMapping("/findspe")
	@ResponseBody
	public Object findSpe(String catSpeId){
		return this.doctorSingupBiz.findSpe(catSpeId);
	}
	
	@RequestMapping("/submitSingup")
	@ResponseBody
	public String submitSingup(SingUpForm form){
		SysUser user = form.getUser();
		if(StringUtil.isNotBlank(user.getIdNo())){
			SysUser existUser = this.userBiz.findByIdNoNotSelf(user.getUserFlow(), user.getIdNo());
			if(existUser!=null){
				return "证件号码已存在";
			}
		}
		ResDoctor doctor = form.getDoctor();
		if(StringUtil.isNotBlank(user.getSexId())){
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));	
		}
		if(StringUtil.isNotBlank(user.getCretTypeId())){
			user.setCretTypeName(CertificateTypeEnum.getNameById(user.getCretTypeId()));	
		}
		if(StringUtil.isNotBlank(user.getEducationId())){
			user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));	
		}
		if(StringUtil.isNotBlank(user.getDegreeId())){
			user.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));	
		}
		if (StringUtil.isNotBlank(doctor.getDoctorTypeId())) {
			doctor.setDoctorTypeName(SczyRecDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
		}
		this.doctorSingupBiz.submitSingup(form);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	@RequestMapping("/submitRecruit")
	@ResponseBody
	public String submitRecruit(ResDoctorRecruitWithBLOBs recruit,String jsonData){
		try{
			SysUser user = GlobalContext.getCurrentUser();
			recruit.setDoctorFlow(user.getUserFlow());
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
			recruit.setAdmitFlag("");
			recruit.setRecruitFlag("");
			recruit.setConfirmFlag("");
			recruit.setDoctorStatusName("在培");
			this.doctorSingupBiz.submitRecruit(recruit,jsonData);
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}catch(RuntimeException re){
			return re.getMessage();
		}
	}
	@RequestMapping("/msg")
	public String msg(Integer currentPage , Model model){
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,10);
		List<InxInfo> messages = this.noticeBiz.findNotice(info);
		model.addAttribute("messages",messages);
		return "sczyres/doctor/msg";
	}

	@RequestMapping("/msg_en")
	public String msg_en(Integer currentPage , Model model){
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,10);
		List<InxInfo> messages = this.noticeBiz.findNotice(info);
		model.addAttribute("messages",messages);
		return "sczyres/doctor/msg_en";
	}


	@RequestMapping("/confirmRecruit")
	@ResponseBody
	public String confirmRecruit(ResDoctorRecruitWithBLOBs recruit,Model model){
		if("Y".equals(recruit.getConfirmFlag())){
			doctorRecruitBiz.editDoctorRecruit(recruit);
		}
		if("N".equals(recruit.getConfirmFlag())){
			doctorRecruitBiz.giveUpDoctorRecruit(recruit);
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	@RequestMapping("/getsingupinfo")
	public String getSingUpInfo(String userFlow , Model model){
		showSingUpInfo(userFlow , model);
		return "sczyres/doctor/singupinfo";
	}
	
	@RequestMapping("/getsingupinfofordialog")
	public String getSingUpInfoForDialog(String userFlow , Model model){
		showSingUpInfo(userFlow , model);
		return "sczyres/doctor/singupinfofordialog";
	}
	
	@RequestMapping("/getsingupinfoaudit")
	public String getSingUpInfoForAudit(String userFlow , Model model){
		showSingUpInfo(userFlow , model);
		return "sczyres/doctor/singupinfoforaudit";
	}
	
	private void showSingUpInfo(String userFlow , Model model){
		SysUser user = this.userBiz.readSysUser(userFlow);
		model.addAttribute("user" , user);
		ResDoctor doctor = doctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor" , doctor);
		PubUserResume resume = userResumeBiz.readPubUserResume(userFlow);
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = doctorSingupBiz.parseExtInfoXml(resumeXml);
			model.addAttribute("extInfo" ,extInfo);
		}
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(user.getUserFlow());
		recruit.setRecruitYear(regYear);
		List<ResDoctorRecruit> recruits = this.doctorRecruitBiz.findDoctorRecruit(recruit , "CREATE_TIME" , null);
		model.addAttribute("recruits" , recruits);
		//第二三志愿
		if(recruits!=null&&recruits.size()>0){
			Map<String,Object> moreSpeMap = new HashMap<>();
			for(ResDoctorRecruit doctorRecruit:recruits){
				if("Y".equals(doctorRecruit.getSwapFlag())){
					String recruitFlow = doctorRecruit.getRecruitFlow();
					ScresRecuritMoreSpe search = new ScresRecuritMoreSpe();
					search.setRecruitFlow(recruitFlow);
					List<ScresRecuritMoreSpe> moreSpeList = doctorRecruitBiz.searchMoreSpe(search);
					moreSpeMap.put(recruitFlow,moreSpeList);
				}
			}
			model.addAttribute("moreSpeMap",moreSpeMap);
		}
	}
	
	
	/**
	 * 打印
	 * @param userFlow
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/print")
	public void print(String userFlow, HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String resRegYear=InitConfig.getSysCfg("res_reg_year");
		dataMap.put("resRegYear", resRegYear);
		SysUser sysUser = this.userBiz.readSysUser(userFlow);
		if(sysUser != null){
			dataMap.put("userName", sysUser.getUserName());
			dataMap.put("userBirthday", sysUser.getUserBirthday());
			dataMap.put("sexName", sysUser.getSexName());
			dataMap.put("nationName", sysUser.getNationName());
			dataMap.put("educationName", sysUser.getEducationName());
			dataMap.put("degreeName", sysUser.getDegreeName());
			dataMap.put("idNo", sysUser.getIdNo());
			dataMap.put("userPhone", sysUser.getUserPhone());
			dataMap.put("userAddress", sysUser.getUserAddress());
			dataMap.put("userEmail", sysUser.getUserEmail());
		}
		ResDoctor doctor = doctorBiz.readDoctor(userFlow);
		if(doctor != null){
			dataMap.put("foreignSkills", doctor.getForeignSkills());
			dataMap.put("specialized", doctor.getSpecialized());
			String doctorLicenseFlag =  doctor.getDoctorLicenseFlag();
			if(GlobalConstant.FLAG_Y.equals(doctorLicenseFlag)){
				doctorLicenseFlag = "有";
			}else if(GlobalConstant.FLAG_N.equals(doctorLicenseFlag)){
				doctorLicenseFlag = "无";
			}
			dataMap.put("doctorLicenseFlag", doctorLicenseFlag);
			dataMap.put("graduatedName", doctor.getGraduatedName());
			dataMap.put("graduationTime", doctor.getGraduationTime());
			dataMap.put("certificateNo", doctor.getCertificateNo());
			dataMap.put("degreeNo", doctor.getDegreeNo());
			dataMap.put("qualifiedNo", doctor.getQualifiedNo());
			dataMap.put("regNo", doctor.getRegNo());
			dataMap.put("workOrgName", doctor.getWorkOrgName());
			dataMap.put("doctorTypeName", doctor.getDoctorTypeName());
		}
		PubUserResume resume = userResumeBiz.readPubUserResume(userFlow);
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = doctorSingupBiz.parseExtInfoXml(resumeXml);
			if(extInfo != null){
				dataMap.put("nativePlace", extInfo.getNativePlace());
				dataMap.put("healthStatus", extInfo.getHealthStatus());
				dataMap.put("political", extInfo.getPolitical());
				dataMap.put("maritalStatus", extInfo.getMaritalStatus());
				dataMap.put("beforeCase", extInfo.getBeforeCase());
				dataMap.put("societyWork", extInfo.getSocietyWork());
				String yearGraduateFlag =  extInfo.getYearGraduateFlag();
				if(GlobalConstant.FLAG_Y.equals(yearGraduateFlag)){
					yearGraduateFlag = "是";
				}else if(GlobalConstant.FLAG_N.equals(yearGraduateFlag)){
					yearGraduateFlag = "否";
				}
				dataMap.put("yearGraduateFlag", yearGraduateFlag);
				dataMap.put("birthProvName", extInfo.getBirthProvName());
				dataMap.put("birthCityName", extInfo.getBirthCityName());
				dataMap.put("birthAreaName", extInfo.getBirthAreaName());
				dataMap.put("homeAddress", extInfo.getHomeAddress());
				dataMap.put("homePhome", extInfo.getHomePhome());

				dataMap.put("zipCode", extInfo.getZipCode());
				dataMap.put("qqCode", extInfo.getQqCode());
				dataMap.put("otherWay", extInfo.getOtherWay());
				dataMap.put("maSchool", extInfo.getMaSchool());
				dataMap.put("maDate", extInfo.getMaDate());
				dataMap.put("maMajor", extInfo.getMaMajor());
				dataMap.put("phdSchool", extInfo.getPhdSchool());
				dataMap.put("phdDate", extInfo.getPhdDate());
				dataMap.put("phdMajor", extInfo.getPhdMajor());
//				dataMap.put("medicalHeaithOrg", DictTypeEnum.getDictName(DictTypeEnum.MedicalHeaithOrg, extInfo.getMedicalHeaithOrg()));
//				dataMap.put("hospitalAttr", DictTypeEnum.getDictName(DictTypeEnum.HospitalAttr, extInfo.getHospitalAttr()));
//				dataMap.put("hospitalCategory", DictTypeEnum.getDictName(DictTypeEnum.HospitalCategory, extInfo.getHospitalCategory()));
//				dataMap.put("baseAttribute", DictTypeEnum.getDictName(DictTypeEnum.BaseAttribute, extInfo.getBaseAttribute()));
//				dataMap.put("basicHealthOrg", DictTypeEnum.getDictName(DictTypeEnum.BasicHealthOrg, extInfo.getBasicHealthOrg()));
				List<WorkResumeForm> workResumeList = extInfo.getWorkResumeList();
				if(workResumeList != null && !workResumeList.isEmpty()){
					List<ItemGroupData> yearPlanTargetList = new ArrayList<ItemGroupData>();
					for(WorkResumeForm wrf :workResumeList){
						Map<String , Object> objMap = new HashMap<String, Object>();
						objMap.put("clinicalRoundDate", wrf.getClinicalRoundDate());
						objMap.put("dateLength", wrf.getDateLength());
						objMap.put("hospitalName", wrf.getHospitalName());
						objMap.put("hospitalLevel", wrf.getHospitalLevel());
						objMap.put("deptName", wrf.getDeptName());
						objMap.put("postName", wrf.getPostName());
						objMap.put("witness", wrf.getWitness());
						objMap.put("witnessPost", wrf.getWitnessPost());
						objMap.put("witnessPhone", wrf.getWitnessPhone());
						
						ItemGroupData  igd = new ItemGroupData();
						igd.setObjMap(objMap);
						
						yearPlanTargetList.add(igd);
					}
					dataMap.put("yearPlanTargetList", yearPlanTargetList);
				}
			}
		}

		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(userFlow);
		recruit.setRecruitYear(resRegYear);
		List<ResDoctorRecruit> recruits = this.doctorRecruitBiz.findDoctorRecruit(recruit , "CREATE_TIME" , null);
		if(!recruits.isEmpty()){
			recruit = recruits.get(0);
			dataMap.put("recruitOrgName", recruit.getOrgName());
			dataMap.put("recruit", recruit.getCatSpeName());
		}
		
		//插入头像图片
		String value = "";
		if (doctor != null) {
			String doctorHeadImg = StringUtil.defaultString(sysUser.getUserHeadImg());
			String cfgUrl = InitConfig.getSysCfg("upload_base_url");
			doctorHeadImg = cfgUrl+"/"+doctorHeadImg;
			if (StringUtil.isBlank(doctorHeadImg)) {
				value = "";
			} else {
				value = "<img src='"+doctorHeadImg+"' width='80' height='150'  alt='证件照'/>";
			}
		}
		dataMap.put("headImg", value);

		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
		String path = "/jsp/sczyres/print/printTemeplete.docx";//模板
		ServletContext context =  request.getServletContext();
		String watermark = GeneralMethod.getWatermark(GlobalConstant.FLAG_Y);
		temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
		if(temeplete!=null){
			String name = "培训学员注册申请表.docx"; 
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +""); 
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}
	
	@RequestMapping("/index")
	public String index(){
		SysUser user = GlobalContext.getCurrentUser();
		SysUserRole userRole = new SysUserRole();
		userRole.setUserFlow(user.getUserFlow());
		userRole.setWsId(GlobalConstant.RES_WS_ID);
		List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
		if(!userRoleList.isEmpty()){
			userRole = userRoleList.get(0);
		}
//		SysRole role = roleBiz.read(userRole.getRoleFlow()); 
//		if(role!=null){
//			setSessionAttribute(GlobalConstant.CURRENT_USER, user);	
//			return "redirect:"+getRoleUrl(role.getRoleFlow());
//		}
//		return null;
		return "redirect:"+this.getRoleUrl(userRole.getRoleFlow());
		
	}
	public String getRoleUrl(String roleFlow){
		if (StringUtil.isNotBlank(roleFlow)){
			if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
				return "/sczyres/manage/home";
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
				return "/sczyres/hosptial/home";
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				return "/sczyres/doctor/home";
			}
		}
		return "";
	}
	
	@RequestMapping("/patch")
	public String patch(Model model){
		try{
			ResDoctor doctor = new ResDoctor();
			doctor.setRecordStatus(GlobalConstant.FLAG_Y);
			List<ResDoctor> doctors = this.doctorBiz.searchByDoc(doctor);
			model.addAttribute("count" , doctors.size());
			int patchCount = 0;
			for(ResDoctor doc : doctors){
				String doctorFlow = doc.getDoctorFlow();
				PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
				String resumeInfo = resume.getUserResume();
				ExtInfoForm extInfo = doctorSingupBiz.parseExtInfoXml(resumeInfo);
				if(extInfo==null){
					continue;
				}
				String workOrgName = extInfo.getSocietyWork();
				if(StringUtil.isNotBlank(workOrgName)){
					ResDoctor patch = new ResDoctor();
					patch.setDoctorFlow(doctorFlow);
					patch.setWorkOrgName(workOrgName);
					this.doctorSingupBiz.modDoctorByDoctorFlow(doctor);
					patchCount++;
				}
				
			}
			model.addAttribute("successCount" , patchCount);
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("error" , e.getMessage());
		}
		
		return "sczyres/doctor/patch";
	}
	
	/**
	 * 打印准考证
	 * @param userFlow
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/printAdmissionCard")
	public void printAdmissionCard(String userFlow, HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String resRegYear=InitConfig.getSysCfg("res_reg_year");
		dataMap.put("resRegYear", resRegYear);
		SysUser sysUser = this.userBiz.readSysUser(userFlow);
		if(sysUser != null){
			dataMap.put("userName", sysUser.getUserName());
			dataMap.put("sexName", sysUser.getSexName());
			dataMap.put("idNo", sysUser.getIdNo());
		}
		
		//插入头像图片
		String value = "";
		ResDoctor doctor = doctorBiz.readDoctor(userFlow);
		if (doctor != null) {
			String doctorHeadImg = StringUtil.defaultString(sysUser.getUserHeadImg());
			String cfgUrl = InitConfig.getSysCfg("upload_base_url");
			doctorHeadImg = cfgUrl+"/"+doctorHeadImg;
			if (StringUtil.isBlank(doctorHeadImg)) {
				value = "";
			} else {
				value = "<img src='"+doctorHeadImg+"' width='80' height='150'  alt='证件照'/>";
			}
			dataMap.put("","");
		}
		dataMap.put("headImg", value);

		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
		String path = "/jsp/sczyres/print/admissionCardTemeplete.docx";//准考证模板
		ServletContext context =  request.getServletContext();
		//String watermark = GeneralMethod.getWatermark(GlobalConstant.FLAG_N);
		temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,null,true);
		if(temeplete!=null){
			String name = resRegYear+"招生准考证.docx"; 
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +""); 
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}

	//结业申请tab页
	@RequestMapping("/graduation")
	public String graduation(Model model,String firstFlag){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String userFlow = currentUser.getUserFlow();
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setDoctorFlow(userFlow);
		recruit.setConfirmFlag("Y");
		//临时注释掉 所有注册学员均可进入结业
//		List<ResDoctorRecruit> recruits = this.doctorRecruitBiz.findDoctorRecruit(recruit , "CREATE_TIME" , null);
//		if(recruits==null||recruits.size()==0){
//			return "sczyres/doctor/notallowgraduation";
//		}
		String regYear = InitConfig.getSysCfg("res_graduation_year");
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		String currDate = DateUtil.getCurrDate();
		DateCfgMsg graduationDateCfgMsg = new DateCfgMsg(recruitCfg);
		graduationDateCfgMsg.setGraduationDateMsg(currDate);
		//查询申请记录
		ScresGraduationApply searchApply = new ScresGraduationApply();
		searchApply.setDoctorFlow(userFlow);
		searchApply.setGraduationYear(regYear);
		List<ScresGraduationApply> applyList = doctorGraduationBiz.searchApply(searchApply,null);
		if(applyList!=null&&applyList.size()>0){
			model.addAttribute("apply",applyList.get(0));
			//准考证信息
			String applyFlow = applyList.get(0).getRecordFlow();
			ScresGraduationTicket searh = new ScresGraduationTicket();
			searh.setApplyFlow(applyFlow);
			List<ScresGraduationTicket> ticketList = doctorGraduationBiz.searchTicket(searh);
			if(ticketList!=null&&ticketList.size()>0){
				model.addAttribute("ticket",ticketList.get(0));
			}
		}
		//查询打印准考证开关
		SysCfg cfg = cfgBiz.read("scres_graduation_printSwith");
		model.addAttribute("cfg",cfg);

//		if(graduationDateCfgMsg.getAllowReg()){
		model.addAttribute("allowFlag",graduationDateCfgMsg.getAllowReg());
		model.addAttribute("regCfgMsg" ,graduationDateCfgMsg);
		return "sczyres/doctor/graduation";
//		}else{
//			return "sczyres/doctor/notallowgraduation";
//		}
	}

	//资格审核情况表
	@RequestMapping("/zgshqkb")
	public String zgshqkb(String printFlag,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		currentUser = userBiz.findByFlow(currentUser.getUserFlow());
		ResDoctor currentDoctor = doctorBiz.findByFlow(currentUser.getUserFlow());
		model.addAttribute("user",currentUser);
		model.addAttribute("doctor",currentDoctor);
		PubUserResume resume = userResumeBiz.readPubUserResume(currentUser.getUserFlow());
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = doctorSingupBiz.parseExtInfoXml(resumeXml);
			model.addAttribute("extInfo" ,extInfo);
		}
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");
		PubFile searchFile = new PubFile();
		searchFile.setProductFlow(currentUser.getUserFlow());
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
		ScresGraduationApply searchAppy = new ScresGraduationApply();
		searchAppy.setDoctorFlow(currentUser.getUserFlow());
		List<ScresGraduationApply> applyList = doctorGraduationBiz.searchApply(searchAppy,null);
		String year = DateUtil.getYear();
		if(applyList!=null&&applyList.size()>0){
			ScresGraduationApply apply = applyList.get(0);
			if(StringUtil.isNotBlank(apply.getGraduationYear())){
				year =apply.getGraduationYear();
			}
		}
		model.addAttribute("year",year);
		if(GlobalConstant.FLAG_Y.equals(printFlag)){
			return "sczyres/doctor/zgshqkbTemp";
		}
		return "sczyres/doctor/zgshqkb";
	}

	//轮转情况审核表
	@RequestMapping("/lzqkshb")
	public String lzqkshb(String printFlag,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		currentUser = userBiz.findByFlow(currentUser.getUserFlow());
		ResDoctor currentDoctor = doctorBiz.findByFlow(currentUser.getUserFlow());
		model.addAttribute("user",currentUser);
		model.addAttribute("doctor",currentDoctor);
		PubUserResume resume = userResumeBiz.readPubUserResume(currentUser.getUserFlow());
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = doctorSingupBiz.parseExtInfoXml(resumeXml);
			model.addAttribute("extInfo" ,extInfo);
		}
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);
		//轮转情况
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");
		model.addAttribute("graduationYear",graduationYear);
		ScresSchInfo search = new ScresSchInfo();
		search.setDoctorFlow(currentUser.getUserFlow());
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
		if(GlobalConstant.FLAG_Y.equals(printFlag)){
			return "sczyres/doctor/lzqkshbTemp";
		}
		return "sczyres/doctor/lzqkshb";
	}

	//保存结业申请基础信息
	@RequestMapping("/saveBaseInfo")
	@ResponseBody
	public String saveBaseInfo(SysUser user,ResDoctor doctor,ExtInfoForm extInfoForm,String workOrgName1,
							   String workOrgName2,String jsonData){
		if(StringUtil.isNotBlank(user.getIdNo())){
			SysUser existUser = this.userBiz.findByIdNoAndCretTypeNotSelf(user.getUserFlow(), user.getIdNo(), user.getCretTypeId());
			if(existUser!=null){
				return "证件号码已存在";
			}
		}
		if(doctor.getDoctorTypeId().equals(SczyRecDocTypeEnum.Agency.getId())||doctor.getDoctorTypeId().equals(SczyRecDocTypeEnum.Entrust.getId())){
			doctor.setWorkOrgName(workOrgName1);
		}else if(doctor.getDoctorTypeId().equals(SczyRecDocTypeEnum.Graduate.getId())){
			doctor.setWorkOrgName(workOrgName2);
		}
		int result = doctorGraduationBiz.saveBaseInfo(user,doctor,extInfoForm,jsonData);
		if(result==1){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}else {
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
	}

	//保存结业申请轮转信息
	@RequestMapping("/saveSchInfo")
	@ResponseBody
	public String saveSchInfo(String jsonData){
		int result = doctorGraduationBiz.saveSchInfo(jsonData);
		if(result==1){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}else if(result==-1){
			return "证件号码已存在";
		}else{
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
	}

	//删除单条轮转信息
	@RequestMapping("/delSchInfo")
	@ResponseBody
	public int delSchInfo(ScresSchInfo scresSchInfo){
		scresSchInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		return doctorGraduationBiz.saveSingleSchInfo(scresSchInfo);
	}

	@RequestMapping("/uploadFile")
	public String uploadFile(String operType, Model model) {
		model.addAttribute("operType", operType);
		return "sczyres/doctor/uploadFile";
	}

	@RequestMapping(value = "/checkUploadFile", method = {RequestMethod.POST})
	public String checkUploadFile(String operType, MultipartFile uploadFile, Model model) {
		if(uploadFile.getSize()>5*1024*1024){
			model.addAttribute("tooBig","文件不得大于5M");
			return "sczyres/doctor/uploadFile";
		}
		model.addAttribute("operType", operType);
		if(uploadFile!=null && (!uploadFile.isEmpty())){
			String originalFileName = uploadFile.getOriginalFilename();
			//定义上传路径
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "sczyGraduation" + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//重命名上传后的文件名
			originalFileName = PkUtil.getUUID();
			File newFile = new File(fileDir, originalFileName);
			try {
				uploadFile.transferTo(newFile);
			} catch (Exception e) {
				throw new RuntimeException("文件上传异常");
			}
			String filePath = "/sczyGraduation/" + dateString + "/" + originalFileName;
			model.addAttribute("filePath",filePath);
			model.addAttribute("result","Y");
		}
		return "sczyres/doctor/uploadFile";
	}

	@RequestMapping("/submitApply")
	@ResponseBody
	public String submitApply(ScresGraduationApply apply){
		SysUser user = GlobalContext.getCurrentUser();
		user = userBiz.readSysUser(user.getUserFlow());
		String doctorFlow = user.getUserFlow();
		String doctorName = user.getUserName();
		String idNo = user.getIdNo();
		String educationId = user.getEducationId();
		String educationName = user.getEducationName();
		String orgFlow = user.getOrgFlow();
		String orgName = user.getOrgName();
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		if(doctor==null){
			return "操作失败，请检查信息是否填写完整";
		}
		String trainingSpeId = doctor.getTrainingSpeId();
		String trainingSpeName = doctor.getTrainingSpeName();
		String doctorLicenseNo = doctor.getDoctorLicenseNo();

		apply.setDoctorFlow(doctorFlow);
		apply.setDoctorName(doctorName);
		apply.setIdNo(idNo);
		apply.setEducationId(educationId);
		apply.setEducationName(educationName);
		apply.setOrgFlow(orgFlow);
		apply.setOrgName(orgName);
		apply.setTrainingSpeId(trainingSpeId);
		apply.setTrainingSpeName(trainingSpeName);
		apply.setDoctorLicenseNo(doctorLicenseNo);

		PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = doctorSingupBiz.parseExtInfoXml(resumeXml);
			String trainingStartDate = extInfo.getTrainingStartDate();
			String trainingEndDate = extInfo.getTrainingEndDate();
			apply.setTrainingStartDate(trainingStartDate);
			apply.setTrainingEndDate(trainingEndDate);
		}else{
			return "操作失败，请检查信息是否填写完整";
		}
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");
		apply.setGraduationYear(graduationYear);
		apply.setDoctorStatusId("1");
		apply.setOrgStatusId("-1");
		apply.setXtOrgStatusId("-1");
		apply.setChargeStatusId("-1");
		doctorGraduationBiz.saveApply(apply);
		return "1";
	}

	@RequestMapping(value="/userHeadImgUpload")
	@ResponseBody
	public String userHeadImgUpload(String userFlow,MultipartFile headImg){
		if(headImg.getSize()>45*1024 || headImg.getSize()<20*1024){
			return "头像图片大小需为20-45kb之间";
		}
		String fileName = headImg.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf(".")).toUpperCase();//后缀名
		if(!suffix.equals(".JPG")){
			return "头像格式必须为JPG";
		}
		if(headImg!=null && headImg.getSize() > 0){
			return userBiz.uploadImg(userFlow,headImg);
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
}
