package com.pinde.sci.ctrl.hbres;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.DocumentVo;
import com.pinde.core.pdf.PdfDocumentGenerator;
import com.pinde.core.pdf.utils.ResourceLoader;
import com.pinde.core.util.*;
import com.pinde.sci.biz.hbres.ExamManageBiz;
import com.pinde.sci.biz.hbres.GradeManageBiz;
import com.pinde.sci.biz.hbres.IHbResDoctorRecruitBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationGroupBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.ResExamDoctorMapper;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.enums.jsres.BaseStatusEnum;
import com.pinde.sci.enums.jsres.CertificateStatusEnum;
import com.pinde.sci.enums.jsres.JsResTrainYearEnum;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.enums.sys.UserEmailStatusEnum;
import com.pinde.sci.enums.sys.UserPhoneStatusEnum;
import com.pinde.sci.form.hbres.ExtInfoForm;
import com.pinde.sci.form.hbres.ReplenishInfoForm;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.DateCfgMsg;
import com.pinde.sci.model.res.OrgRecruitInfo;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

@Controller
@RequestMapping("/hbres/singup")
public class HbDoctorController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(HbDoctorController.class);

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IResOrgSpeAssignBiz speAssignBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private GradeManageBiz gradeManage;
	@Autowired
	private IRecruitCfgBiz recruitCfgBiz;
	@Autowired
	private ExamManageBiz examManageBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IResRegBiz resRegBiz;
	@Autowired
	private ResExamDoctorMapper examDoctorMapper;
	@Autowired
	private IHbResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private IResDoctorRecruitBiz resDoctorRecruitBiz;
	@Autowired
	private ILoginBiz loginBiz;
	@Autowired
	private ISchRotationBiz schRotationBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IJszyResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private GradeManageBiz gradeManageBiz;
	@Autowired
	private ICfgBiz cfgBiz;

	/**
	 * 住院医师主界面
	 */
	@RequestMapping(value="/doctor")
	public String index(Model model){
		SysUser user = GlobalContext.getCurrentUser();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
		ResReg reg = resRegBiz.searchResReg(user.getUserFlow(),regYear);
		ResReg recentReg = resRegBiz.searchRecentYearResReg(user.getUserFlow());

		//判断是否在开放注册期间
		boolean isRegistrationDate = false;
		String currDate = DateUtil.getCurrDate();
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		DateCfgMsg registrationDateCfgMsg = new DateCfgMsg(recruitCfg);
		registrationDateCfgMsg.setRegistrationDateMsg(currDate);
		if (registrationDateCfgMsg.getAllowReg()) {
			isRegistrationDate = true;
		}
		model.addAttribute("isRegistrationDate",isRegistrationDate);

		if(UserStatusEnum.Activated.getId().equals(user.getStatusId())){//用户激活
			if (doctor != null && !RegStatusEnum.UnSubmit.getId().equals(doctor.getDoctorStatusId())
					&& !RegStatusEnum.Passing.getId().equals(doctor.getDoctorStatusId())
					&& !RegStatusEnum.UnPassed.getId().equals(doctor.getDoctorStatusId())) {//报名审核成功，则跳转个人页面
				if(this.doctorRecruitBiz.doctorIsConfirmAdmit(user.getUserFlow())
						|| (recentReg !=null && regYear.equals(recentReg.getRegYear()))){//学员已确认录取，进入填报志愿流程
					if(StringUtil.isBlank(user.getNationId())){//医师信息中的"民族"为必填项 空则需要补填相关信息
						model.addAttribute("confirmAdmitFlag", "Y");
					}
					return "hbres/doctor/home";
				}else {
					model.addAttribute("doctor",doctor);
					model.addAttribute("user",user);
					return "inx/hbres/typeSelect1";
				}
			} else if (reg == null || (reg != null && RegStatusEnum.UnSubmit.getId().equals(doctor.getDoctorStatusId()))){//非首次报名，继续操作：选择类型-信息登记
				model.addAttribute("doctor",doctor);
				model.addAttribute("user",user);
				return "inx/hbres/typeSelect1";
			} else if (RegStatusEnum.Passing.getId().equals(doctor.getDoctorStatusId())) {//状态为待审核，则显示待审核界面
				model.addAttribute("recruitCfg" , recruitCfg);
				model.addAttribute("userEmail", user.getUserEmail());
				model.addAttribute("userIdno", user.getIdNo());
				model.addAttribute("userPhone", user.getUserPhone());
				model.addAttribute("userFlow",user.getUserFlow());
				return "inx/hbres/auditResult";
			} else if (RegStatusEnum.UnPassed.getId().equals(doctor.getDoctorStatusId())) {//状态为报名审核不通过，则显示审核不通过提示界面
				model.addAttribute("doctor",doctor);
				return "inx/hbres/notPass";
			}
		}
		return "/inx/hbres/login";
	}

	/**
	 * 补填信息弹框展示
	 */
	@RequestMapping(value="/fillDocInfo")
	public String fillInfo(Model model){
		SysUser user = GlobalContext.getCurrentUser();
		model.addAttribute("sysUser", user);
		ResDoctor doctor = this.resDoctorBiz.searchByUserFlow(user.getUserFlow());
		model.addAttribute("doctor", doctor);
		PubUserResume resume = userResumeBiz.readPubUserResume(user.getUserFlow());
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			ExtInfoForm extInfo = this.resDoctorBiz.parseExtInfoXml(resumeXml);
			model.addAttribute("extInfo" ,extInfo);
		}
		return "/hbres/doctor/replenishInfo";
	}

	/**
	 * 补填信息保存
	 */
	@RequestMapping(value="/saveInfo")
	public String saveInfo(SysUser user,ExtInfoForm extInfo,ResDoctor doc, Model model){
		SysUser su = GlobalContext.getCurrentUser();
		su.setNationId(user.getNationId());
		su.setNationName(UserNationEnum.getNameById(user.getNationId()));
		doc.setDoctorFlow(su.getUserFlow());
		ReplenishInfoForm form = new ReplenishInfoForm();
		form.setUser(su);
		form.setDoctor(doc);
		form.setExtInfo(extInfo);
		this.resDoctorBiz.saveInfo(form);
		model.addAttribute("operFlag",GlobalConstant.OPRE_SUCCESSED_FLAG);
		return "/hbres/doctor/replenishInfo";
	}

	private boolean getNoTestFlag(ResDoctor doctor,String currYear,String recentYear)throws DocumentException{
		boolean result = false;
		if(doctor!=null && StringUtil.isNotBlank(currYear) && StringUtil.isNotBlank(recentYear)){
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctor.getDoctorFlow());
			if (pubUserResume != null) {
				String xmlContent = pubUserResume.getUserResume();
				if (StringUtil.isNotBlank(xmlContent)) {
					//xml转换成JavaBean
					BaseUserResumeExtInfoForm extInfo = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
					//研究生考试成绩>=过分线且不参加全省住培考试 则不要 参加笔试
					Double masterScore = StringUtil.isBlank(extInfo.getMasterExamResult())?0:Double.valueOf(extInfo.getMasterExamResult());//研究生考试成绩
					Double lineScore = StringUtil.isBlank(InitConfig.getSysCfg("res_master_score_on"))?0:Double.valueOf(InitConfig.getSysCfg("res_master_score_on"));//后台过分线
					result = masterScore>=lineScore && GlobalConstant.FLAG_N.equals(extInfo.getIsJoinTest()) && currYear.equals(recentYear);
				}
			}
		}
		return result;
	}

	@RequestMapping(value="/doctorMain")
	public String doctorMain(Model model)throws DocumentException{
		SysUser currUser = GlobalContext.getCurrentUser();
		ResReg recentReg = resRegBiz.searchRecentYearResReg(currUser.getUserFlow());
		String regYear = recentReg.getRegYear();
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		String printEndDate = recruitCfg.getPrintEndDate();
		model.addAttribute("recruitCfg" , recruitCfg);
		String currDate = DateUtil.getCurrDate();
		String sysRegYear = InitConfig.getSysCfg("res_reg_year");

		ResDoctor doctor = this.resDoctorBiz.readDoctor(currUser.getUserFlow());

		if(doctor!=null){
			boolean noTest = getNoTestFlag(doctor,sysRegYear,regYear);
			model.addAttribute("condition",noTest);
			if(noTest|| "Y".equals(doctor.getIsUnderLine())){
				return "redirect:/hbres/singup/doctor/scoreSearch";
			}
		}

		ResExamDoctor examDoctor = resDoctorBiz.searchExamDoctor(currUser.getUserFlow(), ExamTypeEnum.Register.getId(), regYear);
		if(examDoctor != null){
			//打印准考证页面：增加考点名称和地址信息
			ResExamSite examSite = examManageBiz.findExamSiteByRecordFlow(examDoctor.getSiteFlow());
			model.addAttribute("examDoctor", examDoctor);
			model.addAttribute("examSite" , examSite);
			ResGradeBorderline gradeBorderline = this.gradeManage.findResGradeBorderlineByExamFlowAndSpeId(examDoctor.getExamFlow() , doctor.getSpecialized());
			if(gradeBorderline!=null && GlobalConstant.FLAG_Y.equals(gradeBorderline.getPublishFlag())){
				return "redirect:/hbres/singup/doctor/scoreSearch";
			}

	       if(currDate.compareTo(printEndDate)>0){
	    	   if(gradeBorderline==null || !GlobalConstant.FLAG_Y.equals(gradeBorderline.getPublishFlag())){
	   			model.addAttribute("scoreSearchMsg" , "分数未公布,请耐心等待");
	   		   }
			}
		} else {
			model.addAttribute("examDoctor", examDoctor);
			model.addAttribute("doctor", doctor);
			model.addAttribute("currYear", regYear);
			model.addAttribute("sysRegYear", sysRegYear);

			if (StringUtil.isNotBlank(sysRegYear)) {
				ResExam exam = examManageBiz.findExamByCfgYearAndTypeId(sysRegYear, ExamTypeEnum.Register.getId());
				if (exam != null) {
					String examFlow = exam.getExamFlow();
					if (StringUtil.isNotBlank(examFlow)) {
						List<ResExamSite> examSites = examManageBiz.findAllUsablelExamSite(examFlow);
						model.addAttribute("examSites", examSites);
					}
				}
			}
		}
		return "hbres/doctor/doctorMain";
	}

	@RequestMapping(value = "/doctor/selExamSites")
	@ResponseBody
	public String selExamSites(String doctorFlow, String examSiteFlow) {
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			if (doctor != null && StringUtil.isNotBlank(examSiteFlow)) {
				ResExamSite examSite = examManageBiz.readExamSite(examSiteFlow);
				ResExamDoctor examDoctor = new ResExamDoctor();
				examDoctor.setDoctorFlow(doctor.getDoctorFlow());
				examDoctor.setDoctorName(doctor.getDoctorName());
				examDoctor.setExamFlow(examSite.getExamFlow());
				examDoctor.setSiteCode(examSite.getSiteCode());
				examDoctor.setSiteFlow(examSite.getRecordFlow());
				examDoctor.setSiteName(examSite.getSiteName());
				int result = examManageBiz.editExamDoctor(examDoctor);
				if (result != GlobalConstant.ZERO_LINE) {
					return GlobalConstant.OPRE_SUCCESSED_FLAG;
				}
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value="/doctor/userInfo")
	public String userInfo(Model model) throws DocumentException{
		SysUser currUser = GlobalContext.getCurrentUser();
		model.addAttribute("user",currUser);

		ResDoctor doctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
		model.addAttribute("doctor",doctor);
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(currUser.getUserFlow());
		if (pubUserResume != null) {
			String xmlContent = pubUserResume.getUserResume();
			if (StringUtil.isNotBlank(xmlContent)) {
				//xml转换成JavaBean
				model.addAttribute("extInfo", userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class));
			}
		}
		return "hbres/doctor/user";
	}

	@RequestMapping("/doctor/noticelist")
	public String noticeList(Integer currentPage , Model model){
		InxInfo info = new InxInfo();
		info.setRoleFlow(GlobalConstant.ZL_NOTICE_SYS_ID);
		PageHelper.startPage(currentPage,10);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "hbres/doctor/noticelist";
	}

	@RequestMapping("/doctor/printexamticket")
	@ResponseBody
	public Object printExamTicket(){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		String currDate = DateUtil.getCurrDate();
		DateCfgMsg printDateCfgMsg = new DateCfgMsg(recruitCfg);
		printDateCfgMsg.setPrintDateMsg(currDate);
		return printDateCfgMsg;
	}

	//报到未通过学员 打开修改信息界面
	@RequestMapping("/changeInfo")
	public String changInfo(String doctorFlow,Model model) throws DocumentException{
		if(StringUtil.isNotBlank(doctorFlow)){
			SysUser user = userBiz.readSysUser(doctorFlow);
			if(user!=null){
				model.addAttribute("user",user);
				ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
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
		return "hbres/manage/changeInfoAudit";
	}



	@RequestMapping(value="/doctor/scoreSearch")
	public String scoreSearch(String msg , Model model,String recruitFlow) throws DocumentException{
		SysUser currUser = GlobalContext.getCurrentUser();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResReg recentReg = resRegBiz.searchRecentYearResReg(currUser.getUserFlow());
		String recentYear = recentReg.getRegYear();

		ResDoctor doctor = this.resDoctorBiz.readDoctor(currUser.getUserFlow());
		boolean noTest = getNoTestFlag(doctor,regYear,recentYear);
		model.addAttribute("noTestFlag" , noTest);
		model.addAttribute("isUnderLine",doctor.getIsUnderLine());
		if("03".equals(currUser.getEducationId()) && "Y".equals(doctor.getDoctorLicenseFlag())){
			if("01".equals(doctor.getSpecialized())){
				model.addAttribute("speLimitedFlag1",true);
			}
			if("02".equals(doctor.getSpecialized())){
				model.addAttribute("speLimitedFlag2",true);
			}
		}
		SysOrgExample orgExample = new SysOrgExample();//所有医院
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);

		//查询该学员所有报到记录
		ResRecruitRegister searchRegister = new ResRecruitRegister();
		searchRegister.setDoctorFlow(doctor.getDoctorFlow());
		searchRegister.setRecruitYear(regYear);
		List<ResRecruitRegister> registerList = doctorRecruitBiz.searchRecruitRegister(searchRegister);
		model.addAttribute("registerList",registerList);
		boolean canRegist = true;//判断是否有待审核的记录，又则不能申请新的
		if(registerList!=null&&registerList.size()>0){
			for(ResRecruitRegister recruitRegister:registerList){
				String auditStatusId = recruitRegister.getAuditStatusId();
				if(auditStatusId.equals(BaseStatusEnum.Auditing.getId())||auditStatusId.equals(BaseStatusEnum.Passed.getId())){
					canRegist = false;
					break;
				}
			}
		}
		//判断是否在开放注册期间
		boolean isRegistrationDate = false;
		String currDate2 = DateUtil.getCurrDate();
		ResRecruitCfg recruitCfg2 = recruitCfgBiz.findRecruitCfgByYear(regYear);
		DateCfgMsg registrationDateCfgMsg = new DateCfgMsg(recruitCfg2);
		registrationDateCfgMsg.setRegistrationDateMsg(currDate2);
		if (registrationDateCfgMsg.getAllowReg()) {
			isRegistrationDate = true;
		}
		model.addAttribute("isRegistrationDate",isRegistrationDate);
		model.addAttribute("canRegist",canRegist);

		ResExamDoctor examDoctor = resDoctorBiz.searchExamDoctor(currUser.getUserFlow(), ExamTypeEnum.Register.getId(), regYear);
		if((examDoctor==null || examDoctor.getExamResult()==null) && !noTest && !"Y".equals(doctor.getIsUnderLine())){
			return "hbres/doctor/noresult";
		}

		BigDecimal gradeBorderline = null;
		if(examDoctor!=null){
			model.addAttribute("examDoctor" , examDoctor);
			String examFlow = examDoctor.getExamFlow();
			gradeBorderline = this.gradeManage.findGradeBorderlineByExamFlowAndSpe(examFlow, doctor.getSpecialized());
		}

		//设置学员录取确认过期标记
		this.doctorRecruitBiz.setDoctorConfirmFlagForOutOfDate(regYear, currUser.getUserFlow());
		List<ResDoctorRecruit> doctorRecruits = resDoctorRecruitBiz.findResDoctorRecruits(regYear, currUser.getUserFlow());
		//最新的一条招录记录
		ResDoctorRecruitWithBLOBs doctorRecruit = null;
		if(doctorRecruits!=null && doctorRecruits.size()>0){
			doctorRecruit = (ResDoctorRecruitWithBLOBs) resDoctorRecruitBiz.readResDoctorRecruit(doctorRecruits.get(0).getRecruitFlow());
			model.addAttribute("doctorRecruit" , doctorRecruit);
			//复试成绩是否可见标识
			String orgFlow = doctorRecruit.getOrgFlow();
			SysCfg cfg = cfgBiz.read("hbShowScore_"+orgFlow);
			model.addAttribute("cfg" , cfg);

			Collections.reverse(doctorRecruits);
			model.addAttribute("doctorRecruits" , doctorRecruits);

			//复试成绩是否可见标识
			Map<String,String> showScoreMap = new HashMap<>();
			for(ResDoctorRecruit resDoctorRecruit:doctorRecruits){
				String cfgCode = "hbShowScore_"+resDoctorRecruit.getOrgFlow();
				SysCfg sc = cfgBiz.read(cfgCode);
				if(sc!=null){
					showScoreMap.put(resDoctorRecruit.getOrgFlow(),sc.getCfgValue());
				}
			}
			model.addAttribute("showScoreMap" , showScoreMap);
		}

		boolean guoxianFlag = false;
		boolean isShowRecruits = true;
		//首先判断是否发布分数线和是否过线
		if(noTest){
			guoxianFlag = true;
		}else{
			if(gradeBorderline==null){
				model.addAttribute("fillMsg" , "暂未发布分数线");
				isShowRecruits = false;
			}else if(examDoctor.getExamResult().compareTo(gradeBorderline)<0){
				model.addAttribute("fillMsg" , "未达志愿填报分数线,无法填报志愿");
				model.addAttribute("showCfgDate" , GlobalConstant.FLAG_N);
				isShowRecruits = false;
			}else if(examDoctor.getExamResult().compareTo(gradeBorderline)>=0){
				guoxianFlag = true;
			}
		}
		//查询招录设置
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		model.addAttribute("recruitCfg" , recruitCfg);
		if(guoxianFlag){
			//默认不可以填报志愿
			String isCanFill = GlobalConstant.FLAG_N;
			//默认不显示确认录取/拒绝
			String isConfirm = GlobalConstant.FLAG_N;//N:请等待确认 Y:显示确认 F：确认过期
			//当学员不被录取或者拒绝录取时 是否可以再填 默认不可以
			String isSwapFill = GlobalConstant.FLAG_N;
			String wishBeginDate = recruitCfg.getWishBeginDate();
			String wishEndDate = recruitCfg.getWishEndDate();
			String admitBeginDate = recruitCfg.getAdmitBeginDate();
			String admitEndDate = recruitCfg.getAdmitEndDate();
			String swapBeginDate = recruitCfg.getSwapBeginDate();
			String swapEndDate = recruitCfg.getSwapEndDate();
			//获取当前日期
			String currDate = DateUtil.getCurrDate();

			if(StringUtil.isNotBlank(wishBeginDate) && StringUtil.isNotBlank(wishEndDate)){
				//是否是志愿填报开放日期之间
				if(currDate.compareTo(wishBeginDate)>=0 && currDate.compareTo(wishEndDate)<=0){
					if(doctorRecruit==null){
						isCanFill = GlobalConstant.FLAG_Y;
					}else if(GlobalConstant.FLAG_N.equals(doctorRecruit.getReturnBackFlag())){//被退回可以重报
						isCanFill = GlobalConstant.FLAG_Y;
					}
				}else if(currDate.compareTo(wishBeginDate)<0){
					model.addAttribute("fillMsg" , "填报志愿暂未开放,请耐心等待");
					isShowRecruits = false;
				}else{
					model.addAttribute("fillMsg" , "填报志愿时间已经截止");
				}
			}else{
				model.addAttribute("fillMsg" , "暂无填报志愿开放时间信息,请耐心等待");
				isShowRecruits = false;
			}

			if(StringUtil.isNotBlank(admitEndDate)){
				//可以显示确认录取/拒绝
				if(currDate.compareTo(admitEndDate)<=0){
					isConfirm = GlobalConstant.FLAG_Y;
				}else if(currDate.compareTo(admitEndDate)>0){
					isConfirm = GlobalConstant.FLAG_F;
					model.addAttribute("fillMsg" , "确认录取结果时间已经截止");
				}
			}else{
				isConfirm = GlobalConstant.FLAG_F;
				model.addAttribute("fillMsg" , "暂无确认录取时间信息");
			}

			if(StringUtil.isNotBlank(swapBeginDate) && StringUtil.isNotBlank(swapEndDate)){
				//可以再次填报 再次期间 确认录取没有时间限制
				if(currDate.compareTo(swapBeginDate)>=0 && currDate.compareTo(swapEndDate)<=0){
					isConfirm = GlobalConstant.FLAG_Y;
					if(doctorRecruit==null){
						isSwapFill = GlobalConstant.FLAG_Y;
					}
					if(doctorRecruit!=null && (GlobalConstant.FLAG_N.equals(doctorRecruit.getRecruitFlag()) || GlobalConstant.FLAG_N.equals(doctorRecruit.getConfirmFlag()))){
						isSwapFill = GlobalConstant.FLAG_Y;
					}
				}else if(currDate.compareTo(swapEndDate)>0){
					isSwapFill = GlobalConstant.FLAG_F;
					isConfirm = GlobalConstant.FLAG_Y;
//					model.addAttribute("fillMsg" , "学员调剂日期已经截止");
				}
			}else{
				isSwapFill = GlobalConstant.FLAG_F;
				isConfirm = GlobalConstant.FLAG_Y;
				model.addAttribute("fillMsg" , "暂无学员调剂日期信息");
			}

//			if(isCanFill.equals(GlobalConstant.FLAG_Y) || isSwapFill.equals(GlobalConstant.FLAG_Y)){
//				SysOrgExample orgExample = new SysOrgExample();
//				orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
//				List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
//				model.addAttribute("hospitals", orgs);
//			}
			//确认录取完善医师轮转信息
			ResDoctorRecruit  rdr = this.resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
			if(null != rdr){
				List<SchRotation> schRotlst = this.schRotationBiz.searchSchRotation(rdr.getSpeId());
				if(null != schRotlst && schRotlst.size() > 0){
					schRotlst.get(0);
					ResDoctor doc = new ResDoctor();
					doc.setDoctorFlow(rdr.getDoctorFlow());
					doc.setRotationFlow(schRotlst.get(0).getRotationFlow());
					doc.setRotationName(schRotlst.get(0).getRotationName());
					this.resDoctorBiz.editDoctor(doc);

					SysUser user=new SysUser();
					user.setUserFlow(rdr.getDoctorFlow());
					user.setMedicineTypeId(schRotlst.get(0).getRotationTypeId());
					user.setMedicineTypeName(schRotlst.get(0).getRotationTypeName());
					userBiz.updateUser(user);
				}
			}
			model.addAttribute("isShowRecruits" , isShowRecruits);
			model.addAttribute("isCanFill" , isCanFill);
			model.addAttribute("isConfirm" , isConfirm);
			model.addAttribute("isSwapFill" , isSwapFill);
		}

		return "hbres/doctor/cjcx";
	}

	//查询是否有填写新增信息字段
	@RequestMapping("/doctor/checkInfo")
	@ResponseBody
	public String checkInfo(String doctorFlow) throws DocumentException {
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
		if (pubUserResume != null) {
			String xmlContent = pubUserResume.getUserResume();
			if (StringUtil.isNotBlank(xmlContent)) {
				//xml转换成JavaBean
				BaseUserResumeExtInfoForm extInfoForm = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
				if(StringUtil.isBlank(extInfoForm.getIsAssistance())){
					return "1";
				}
			}
		}
		return "";
	}

	//打开补全信息页面
	@RequestMapping("/doctor/comleteInfo")
	public String comleteInfo(String doctorFlow){
		return "/hbres/doctor/completeInfo";
	}

	//保存补全的信息
	@RequestMapping("/doctor/completeNewInfo")
	@ResponseBody
	public void completeNewInfo(ResDoctor doctor,	BaseUserResumeExtInfoForm extInfo) throws DocumentException {
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctor.getDoctorFlow());
		if (pubUserResume != null) {
			String xmlContent = pubUserResume.getUserResume();
			if (StringUtil.isNotBlank(xmlContent)) {
				//xml转换成JavaBean
				BaseUserResumeExtInfoForm oldExtInfo = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
				oldExtInfo.setIsAssistance(extInfo.getIsAssistance());
				oldExtInfo.setAssistanceProvince(extInfo.getAssistanceProvince());
				oldExtInfo.setAssistanceCode(extInfo.getAssistanceCode());
				oldExtInfo.setPersonnelAttributeId(extInfo.getPersonnelAttributeId());
				oldExtInfo.setPersonnelAttributeName(PersonnelAttributeEnum.getNameById(extInfo.getPersonnelAttributeId()));
				oldExtInfo.setQualifiedNoFlag(extInfo.getQualifiedNoFlag());
				//JavaBean转XML
				String xml = JaxbUtil.convertToXml(oldExtInfo);
				pubUserResume.setUserResume(xml);
				SysUser user = GlobalContext.getCurrentUser();
				userResumeBiz.savePubUserResume(user,pubUserResume);
			}
		}
		resDoctorBiz.editDoctor(doctor);
	}


	@RequestMapping("/doctor/delrecruit")
	@ResponseBody
	public String delRecruit(String recruitFlow){
		ResDoctorRecruit exitRecruit = resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		if(exitRecruit!=null && GlobalConstant.FLAG_N.equals(exitRecruit.getRetestFlag())){
			ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
			recruit.setRecruitFlow(recruitFlow);
			recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			resDoctorRecruitBiz.editDoctorRecruit(recruit);
			//撤销志愿同时清空用户/医师表基地信息
			SysUser user = new SysUser();
			user.setUserFlow(exitRecruit.getDoctorFlow());
			user.setOrgFlow("");
			user.setOrgName("");
			userBiz.updateUser(user);
			ResDoctor doctor = new ResDoctor();
			doctor.setDoctorFlow(exitRecruit.getDoctorFlow());
			doctor.setOrgFlow("");
			doctor.setOrgName("");
			resDoctorBiz.editDoctor(doctor);
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}else{
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
	}

	@RequestMapping("/doctor/findspe")
	@ResponseBody
	public Object findSpe(String orgFlow){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		List<ResOrgSpeAssign> resultSpes = new ArrayList<ResOrgSpeAssign>();
		List<ResOrgSpeAssign> spes = this.speAssignBiz.searchSpeAssign(orgFlow, regYear);
		List<ResOrgSpeAssign> spe0700 = new ArrayList<ResOrgSpeAssign>();
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctor resDoctor = resDoctorBiz.findByFlow(currUser.getUserFlow());
		if(!"03".equals(currUser.getEducationId()) || "Y".equals(resDoctor.getDoctorLicenseFlag())){//非大专学历或者大专学历但取得执业医师资格证
			for(ResOrgSpeAssign spe:spes){
				if(spe.getAssignPlan()!=null && spe.getAssignPlan().compareTo(new BigDecimal(0))>0){
					resultSpes.add(spe);
				}
			}
			return resultSpes;
		}else{//大专且未取得执业医师资格证
			for(ResOrgSpeAssign spe:spes){
				if("0700".equals(spe.getSpeId())){
					spe0700.add(spe);
				}
			}
			return spe0700;
		}
	}

	@RequestMapping("/doctor/findorg")
	@ResponseBody
	public Object findorg(String speId){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		List<Map<String,Object>> resultMapList = new ArrayList<>();
		List<ResOrgSpeAssign> spes = this.speAssignBiz.searchSpeAssignBySpeIdAndYear(speId, regYear);
		for(ResOrgSpeAssign spe:spes){
			if(spe.getAssignPlan()!=null && spe.getAssignPlan().compareTo(new BigDecimal(0))>0){
				SysOrg org = orgBiz.readSysOrg(spe.getOrgFlow());
				Map<String,Object> map= new HashMap<>();
				map.put("recordFlow",spe.getRecordFlow());
				map.put("orgFlow",spe.getOrgFlow());
				map.put("orgName",org.getOrgName());
				resultMapList.add(map);
			}
		}
		return resultMapList;
	}

	@RequestMapping("/doctor/submitrecruit")
	public String submitRecruit(ResDoctorRecruitWithBLOBs doctorRecruit , Model model) throws DocumentException {
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctor currDoctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
		doctorRecruit.setDoctorFlow(currUser.getUserFlow());
		if(StringUtil.isNotBlank(doctorRecruit.getOrgFlow())){
			SysOrg org = this.orgBiz.readSysOrg(doctorRecruit.getOrgFlow());
			doctorRecruit.setOrgName(org.getOrgName());
		}
		if(StringUtil.isNotBlank(doctorRecruit.getSpeId())){
			ResOrgSpeAssign spe = this.speAssignBiz.findSpeAssignByFlow(doctorRecruit.getSpeId());
			doctorRecruit.setSpeId(spe.getSpeId());
			doctorRecruit.setSpeName(spe.getSpeName());
		}
		String regYear = InitConfig.getSysCfg("res_reg_year");
		//查看基地的独立分数线
		ResExamDoctor examDoctor = resDoctorBiz.searchExamDoctor(currUser.getUserFlow(), ExamTypeEnum.Register.getId(), regYear);
		if(examDoctor!=null){
			String examFlow = examDoctor.getExamFlow();
			ResGradeBorderline resGradeBorderline = this.gradeManage.findResGradeBorderlineByExamFlowAndSpeId(examFlow, currDoctor.getSpecialized());
			if(resGradeBorderline!=null){
                String borderlineFlow = resGradeBorderline.getBorderlineFlow();
                ResGradeBorderlineOrg search = new ResGradeBorderlineOrg();
                search.setBorderlineFlow(borderlineFlow);
                search.setOrgFlow(doctorRecruit.getOrgFlow());
                search.setSpeId(currDoctor.getSpecialized());
                List<ResGradeBorderlineOrg> resGradeBorderlineOrgs = gradeManage.searchBorderlineOrg(search);
                if(resGradeBorderlineOrgs!=null&&resGradeBorderlineOrgs.size()>0){
                    ResGradeBorderlineOrg resGradeBorderlineOrg = resGradeBorderlineOrgs.get(0);
                    String gradeBorderline = resGradeBorderlineOrg.getGradeBorderlineOrg();
                    if(Double.parseDouble(gradeBorderline)>examDoctor.getExamResult().doubleValue()){
                        model.addAttribute("msg" , "-2");
                        model.addAttribute("gradeBorderlineOrg" , gradeBorderline);
                        model.addAttribute("gradeBorderlineOrgName" , resGradeBorderlineOrg.getOrgName());
                        return scoreSearch(null,model,null);
                    }
                }
            }
		}
		//查询招录计划
		ResOrgSpeAssign speAssign =this.speAssignBiz.searchSpeAssign(doctorRecruit.getOrgFlow() , regYear , doctorRecruit.getSpeId());
		if(speAssign!=null && speAssign.getAssignPlan()!=null && speAssign.getAssignPlan().compareTo(new BigDecimal(0))>0){
			//查询已确认招录人数
			ResDoctorRecruit searchRecruit = new ResDoctorRecruit();
			searchRecruit.setOrgFlow(doctorRecruit.getOrgFlow());
			searchRecruit.setSpeId(speAssign.getSpeId());
			searchRecruit.setRecruitYear(regYear);
			searchRecruit.setConfirmFlag(GlobalConstant.FLAG_Y);
			Integer confirmCount = doctorRecruitBiz.searchDoctorNum(searchRecruit);
			if(speAssign.getAssignPlan().compareTo(new BigDecimal(confirmCount))>0){
				this.gradeManage.submitRecruit(doctorRecruit);
				SysUser user = new SysUser();
				user.setUserFlow(doctorRecruit.getDoctorFlow());
				user.setOrgFlow(doctorRecruit.getOrgFlow());
				user.setOrgName(doctorRecruit.getOrgName());
				this.userBiz.updateUser(user);//填报志愿同步用户表机构信息
				ResDoctor doctor = new ResDoctor();
				doctor.setDoctorFlow(doctorRecruit.getDoctorFlow());
				doctor.setOrgFlow(doctorRecruit.getOrgFlow());
				doctor.setOrgName(doctorRecruit.getOrgName());
				doctor.setTrainingSpeId(doctorRecruit.getSpeId());
				doctor.setTrainingSpeName(doctorRecruit.getSpeName());
				this.resDoctorBiz.editDoctor(doctor);//填报志愿同步医师表机构信息
				model.addAttribute("msg" , "0");
			}else{
				model.addAttribute("msg" , "1");
			}
		}else{
			model.addAttribute("msg" , "-1");
		}

		return "redirect:/hbres/singup/doctor/scoreSearch";
	}

	@RequestMapping("/submitRegist")
	public String submitRegister(ResRecruitRegister resRecruitRegister, String orgFlow2, String speId2,String speName2){
		SysUser user = GlobalContext.getCurrentUser();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		if(StringUtil.isNotBlank(resRecruitRegister.getOrgFlow())){
			SysOrg org = this.orgBiz.readSysOrg(resRecruitRegister.getOrgFlow());
			resRecruitRegister.setOrgName(org.getOrgName());
		}
		if(StringUtil.isNotBlank(resRecruitRegister.getSpeId())){//此speId实际上是主键recordFlow
			ResOrgSpeAssign spe = this.speAssignBiz.findSpeAssignByFlow(resRecruitRegister.getSpeId());
			resRecruitRegister.setSpeId(spe.getSpeId());
			resRecruitRegister.setSpeName(spe.getSpeName());
		}
		if(StringUtil.isNotBlank(orgFlow2)&&StringUtil.isNotBlank(speId2)){//如果是线上录取学员 ，直接用传过来的值
			SysOrg org = this.orgBiz.readSysOrg(orgFlow2);
			resRecruitRegister.setOrgFlow(orgFlow2);
			resRecruitRegister.setOrgName(org.getOrgName());
			resRecruitRegister.setSpeId(speId2);
			resRecruitRegister.setSpeName(speName2);
		}
		resRecruitRegister.setDoctorFlow(user.getUserFlow());
		resRecruitRegister.setRecruitYear(regYear);
		resRecruitRegister.setAuditStatusId(BaseStatusEnum.Auditing.getId());
		resRecruitRegister.setAuditStatusName(BaseStatusEnum.Auditing.getName());
		//开通web端权限 开始
		ResPowerCfg sysCfg = resPowerCfgBiz.read("res_doctor_web_"+user.getUserFlow());
		if(null != sysCfg){
			sysCfg.setCfgValue(GlobalConstant.FLAG_Y);
			resPowerCfgBiz.updateDate(sysCfg);
		}else{
			sysCfg = new ResPowerCfg();
			sysCfg.setCfgCode("res_doctor_web_"+user.getUserFlow());
			sysCfg.setCfgValue(GlobalConstant.FLAG_Y);
			sysCfg.setCfgDesc("是否开放学员web登录权限");
			GeneralMethod.setRecordInfo(sysCfg,true);
			resPowerCfgBiz.add(sysCfg);
		}
		//开通web端权限 结束
		doctorRecruitBiz.editRegister(resRecruitRegister);
		return "redirect:/hbres/singup/doctor/scoreSearch";
	}

	@RequestMapping("/delRegist")
	@ResponseBody
	public int delRegist(ResRecruitRegister resRecruitRegister){
		resRecruitRegister.setRecordStatus("N");
		int result = doctorRecruitBiz.editRegister(resRecruitRegister);
		return result;
	}

	@RequestMapping("/doctor/confirmrecruit")
	@ResponseBody
	public String confirmRecruit(ResDoctorRecruitWithBLOBs doctorRecruit){
		resDoctorRecruitBiz.editDoctorRecruit(doctorRecruit);
		if(StringUtil.isNotBlank(doctorRecruit.getConfirmFlag())){
			ResDoctorRecruit recruit = resDoctorRecruitBiz.readResDoctorRecruit(doctorRecruit.getRecruitFlow());
			ResDoctor doctor = new ResDoctor();
			doctor.setDoctorFlow(recruit.getDoctorFlow());
			SysUser user = new SysUser();
			user.setUserFlow(doctorRecruit.getDoctorFlow());
			if(doctorRecruit.getConfirmFlag().equals(GlobalConstant.FLAG_Y)){
				/*//确认录取后，同步医师培训专业，培养年限，医师状态到过程
				doctor.setOrgFlow(recruit.getOrgFlow());
				doctor.setOrgName(recruit.getOrgName());
				doctor.setTrainingYears(recruit.getTrainYear());
				doctor.setTrainingSpeId(recruit.getSpeId());
				doctor.setTrainingSpeName(recruit.getSpeName());
				doctor.setDoctorStatusId("Training");//过程初始状态
				doctor.setDoctorStatusName("在培");
				user.setOrgFlow(recruit.getOrgFlow());
				user.setOrgName(recruit.getOrgName());*/
				/*//新需求：确认录取后不进过程，改为报到后进过程*/
			}else{//放弃录取，清空填报基地相关信息
				doctor.setOrgFlow("");
				doctor.setOrgName("");
				doctor.setTrainingYears("");
				doctor.setTrainingSpeId("");
				doctor.setTrainingSpeName("");
				user.setOrgFlow("");
				user.setOrgName("");
			}
			userBiz.updateUser(user);
			resDoctorBiz.editDoctor(doctor);
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping("/doctor/showjidirecruitinfo")
	public String showJidiRecruitInfo(Model model){
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);

		Map<String , Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
		List<SysDict> doctorTrainingSpeList = this.doctorRecruitBiz.searchTrainSpeList(paramMap);
		model.addAttribute("spes", doctorTrainingSpeList);

		return "hbres/doctor/jidirecruitinfo";
	}

	@RequestMapping("/doctor/getorgrecruitinfotable")
	public String getOrgRecruitInfoTable(String orgFlow , String speId , Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		List<OrgRecruitInfo> orgRecruitInfos = new ArrayList<OrgRecruitInfo>();
		if(StringUtil.isNotBlank(orgFlow) && StringUtil.isBlank(speId)){
			//查询该医院下所有专业的招录情况
			SysOrg org = this.orgBiz.readSysOrg(orgFlow);
			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,regYear);
			OrgRecruitInfo orgRecruitInfo = null;
			for(ResOrgSpeAssign speAssign:speAssignList){
				Integer planCount = null;
				Integer planCountOrg = null;
				String speName = speAssign.getSpeName();
				BigDecimal assignPlan = speAssign.getAssignPlan();
				if(assignPlan!=null){
					planCount = assignPlan.intValue();
				}
				BigDecimal assignPlanOrg = speAssign.getAssignPlanOrg();
				if(assignPlanOrg!=null){
					planCountOrg = assignPlanOrg.intValue();
				}else {
					planCountOrg = planCount;
				}
				ResDoctorRecruit searchRecruit = new ResDoctorRecruit();
				searchRecruit.setOrgFlow(orgFlow);
				searchRecruit.setSpeId(speAssign.getSpeId());
				searchRecruit.setRecruitYear(regYear);
				searchRecruit.setConfirmFlag(GlobalConstant.FLAG_Y);
				Integer confirmCount = doctorRecruitBiz.searchDoctorNum(searchRecruit);
				orgRecruitInfo = new OrgRecruitInfo(org.getOrgName() , speName , planCount , confirmCount,planCountOrg);
				orgRecruitInfos.add(orgRecruitInfo);
			}
		}
		if(StringUtil.isBlank(orgFlow) && StringUtil.isNotBlank(speId)){
			//查询所有医院该专业的招录情况
			Integer planCount = null;
			Integer planCountOrg = null;
			OrgRecruitInfo orgRecruitInfo = null;
			List<ResOrgSpeAssign> orgSpeAssigns = this.speAssignBiz.searchSpeAssignBySpeIdAndYear(speId , regYear);
			for(ResOrgSpeAssign speAssign:orgSpeAssigns){
				String speAssignOrgFlow = speAssign.getOrgFlow();
				SysOrg org = this.orgBiz.readSysOrg(speAssignOrgFlow);
				BigDecimal assignPlan = speAssign.getAssignPlan();
				if(assignPlan!=null){
					planCount = assignPlan.intValue();
				}
				BigDecimal assignPlanOrg = speAssign.getAssignPlanOrg();
				if(assignPlanOrg!=null){
					planCountOrg = assignPlanOrg.intValue();
				}else {
					planCountOrg = planCount;
				}
				ResDoctorRecruit searchRecruit = new ResDoctorRecruit();
				searchRecruit.setOrgFlow(speAssignOrgFlow);
				searchRecruit.setSpeId(speAssign.getSpeId());
				searchRecruit.setRecruitYear(regYear);
				searchRecruit.setConfirmFlag(GlobalConstant.FLAG_Y);
				Integer confirmCount = doctorRecruitBiz.searchDoctorNum(searchRecruit);
				orgRecruitInfo = new OrgRecruitInfo(org.getOrgName() , speAssign.getSpeName() , planCount , confirmCount,planCountOrg);
				orgRecruitInfos.add(orgRecruitInfo);
			}
		}
		if(StringUtil.isNotBlank(orgFlow) && StringUtil.isNotBlank(speId)){
			//查询该医院该专业的招录情况
			Integer planCount = null;
			Integer planCountOrg = null;
			OrgRecruitInfo orgRecruitInfo = null;
			SysOrg org = this.orgBiz.readSysOrg(orgFlow);
			ResOrgSpeAssign speAssign =this.speAssignBiz.searchSpeAssign(orgFlow , regYear , speId);
			if(speAssign!=null){
				ResDoctorRecruit searchRecruit = new ResDoctorRecruit();
				searchRecruit.setOrgFlow(orgFlow);
				searchRecruit.setSpeId(speAssign.getSpeId());
				searchRecruit.setRecruitYear(regYear);
				searchRecruit.setConfirmFlag(GlobalConstant.FLAG_Y);
				BigDecimal assignPlan = speAssign.getAssignPlan();
				if(assignPlan!=null){
					planCount = assignPlan.intValue();
				}
				BigDecimal assignPlanOrg = speAssign.getAssignPlanOrg();
				if(assignPlanOrg!=null){
					planCountOrg = assignPlanOrg.intValue();
				}else {
					planCountOrg = planCount;
				}
				Integer confirmCount = doctorRecruitBiz.searchDoctorNum(searchRecruit);
				orgRecruitInfo = new OrgRecruitInfo(org.getOrgName() , speAssign.getSpeName() , planCount , confirmCount,planCountOrg);
				orgRecruitInfos.add(orgRecruitInfo);
			}

		}

		model.addAttribute("orgRecruitInfos" , orgRecruitInfos);

		return "hbres/doctor/orgRecruitInfoTable";
	}

	@RequestMapping("/doctor/showJidiLine")
	public String showJidiLine(Model model){
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);
		return "hbres/doctor/jidiLine";
	}

	@RequestMapping("/doctor/jidiLineTable")
	public String jidiLineTable(String orgFlow , Model model,Integer currentPage,HttpServletRequest request){
		List<ResExam> exams = examManageBiz.findALLExam();
		List<ResExam> finishedExams = new ArrayList<ResExam>();
		for(ResExam exam : exams){
			if(ExamStatusEnum.Finished.getId().equals(exam.getExamStatusId())){
				finishedExams.add(exam);
			}
		}
		model.addAttribute("exams" , finishedExams);
		ResExam currExam = null;
		if(finishedExams.size()>0){
			currExam = finishedExams.get(0);
		}

		Map<String,Object> map01 = new HashMap<>();
		Map<String,Object> map02 = new HashMap<>();

		if(currExam!=null){
			List<ResGradeBorderline> gradeBorderlines = gradeManageBiz.findGradeBorderlineByExamFlow(currExam.getExamFlow());
			if(gradeBorderlines!=null && gradeBorderlines.size()>0){
				model.addAttribute("gradeBorderlines",gradeBorderlines);
				for(ResGradeBorderline gradeBorderline:gradeBorderlines){
					String borderlineFlow = gradeBorderline.getBorderlineFlow();
					ResGradeBorderlineOrg search = new ResGradeBorderlineOrg();
					search.setBorderlineFlow(borderlineFlow);
					List<ResGradeBorderlineOrg> gradeBorderlineOrgList = gradeManageBiz.searchBorderlineOrg(search);
					if(gradeBorderlineOrgList!=null && gradeBorderlineOrgList.size()>0){
						for(ResGradeBorderlineOrg gradeBorderlineOrg:gradeBorderlineOrgList){
							if("01".equals(gradeBorderlineOrg.getSpeId())){
								map01.put(gradeBorderlineOrg.getOrgFlow(),gradeBorderlineOrg);
							}
							if("02".equals(gradeBorderlineOrg.getSpeId())){
								map02.put(gradeBorderlineOrg.getOrgFlow(),gradeBorderlineOrg);
							}
						}
					}
				}
			}
		}
		model.addAttribute("map01",map01);
		model.addAttribute("map02",map02);

		SysOrgExample example = new SysOrgExample();
		SysOrgExample.Criteria criteria = example.createCriteria();
		criteria.andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId()).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		example.setOrderByClause("ordinal");
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList", orgList);
		return "hbres/doctor/jidiLineTable";
	}


	@RequestMapping("/doctor/showexamcard")
	public String showExamCard(Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		model.addAttribute("user" , currUser);
		ResDoctor doctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
		model.addAttribute("doctor" , doctor);
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResExamDoctor examDoctor = resDoctorBiz.searchExamDoctor(currUser.getUserFlow(), ExamTypeEnum.Register.getId(), regYear);
		if (examDoctor != null) {
			model.addAttribute("examDoctor" , examDoctor);
			String examFlow = examDoctor.getExamFlow();
			ResExam exam = examManageBiz.findExamByFlow(examFlow);
			model.addAttribute("exam" , exam);
			ResExamSite examSite = examManageBiz.findExamSiteByRecordFlow(examDoctor.getSiteFlow());
			model.addAttribute("examSite" , examSite);
		}
		return "hbres/doctor/examcard";
	}

	/**
	 * 打印
	 * @param recTypeId
	 * @param watermarkFlag
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/doctor/print")
	public void print(String recTypeId,String watermarkFlag,String printType, HttpServletRequest request,HttpServletResponse response)throws Exception{
		String templ = "";
		String fileName = "";
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ResExamDoctor examDoctor = null;
		if ("ExamCard".equals(recTypeId)) {
			fileName = "准考证";
			SysUser currUser = GlobalContext.getCurrentUser();
			dataMap.put("userName", currUser.getUserName());
			dataMap.put("sexName", StringUtil.defaultString(currUser.getSexName()));
			dataMap.put("idNo", StringUtil.defaultString(currUser.getIdNo()));

			String regYear = InitConfig.getSysCfg("res_reg_year");

			String ticketNum = "";
			String siteName = "";
			String siteAddress = "";
			String rootCode = "";
			String examDate = "";
			String examTime = "";
			examDoctor = resDoctorBiz.searchExamDoctor(currUser.getUserFlow(), ExamTypeEnum.Register.getId(), regYear);
			if (examDoctor != null) {
				ticketNum = StringUtil.defaultString(examDoctor.getTicketNum());
				siteName = StringUtil.defaultString(examDoctor.getSiteName());
				rootCode = StringUtil.defaultString(examDoctor.getRoomCode());
				String examFlow = examDoctor.getExamFlow();
				ResExam exam = examManageBiz.findExamByFlow(examFlow);
				if (exam != null) {
					examDate = StringUtil.defaultString(exam.getExamDate());
					examTime = StringUtil.defaultString(exam.getExamTime());
				}
				ResExamSite examSite = examManageBiz.findExamSiteByRecordFlow(examDoctor.getSiteFlow());
				if (examSite != null) {
					siteAddress = StringUtil.defaultString(examSite.getSiteAddress());
				}
			}
			dataMap.put("ticketNum", ticketNum);
			dataMap.put("siteName", siteName);
			dataMap.put("siteAddress", siteAddress);
			dataMap.put("siteCode", rootCode);   //考场号，非考点号
			dataMap.put("examDate", examDate);
			dataMap.put("examTime", examTime);

			//插入头像图片
			String value = "";
			ResDoctor doctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
			if (doctor != null) {
				String doctorHeadImg = StringUtil.defaultString(doctor.getDoctorHeadImg());
				String cfgUrl = InitConfig.getSysCfg("upload_base_url");
				doctorHeadImg = cfgUrl+"/"+doctorHeadImg;
				if (StringUtil.isBlank(doctorHeadImg)) {
					value = "";
				} else {
					value = "<img src='"+doctorHeadImg+"' width='80' height='150'  alt='证件照'/>";
				}
			}
			dataMap.put("headImg", value);
		}

		templ = recTypeId +"Template.docx";//模板
		String path = "/jsp/hbres/print/"+templ;
		ServletContext context =  request.getServletContext();
		String watermark = GeneralMethod.getWatermark(watermarkFlag);
		temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
		if(temeplete!=null){
			ServletOutputStream out = response.getOutputStream();
			if ("pdf".equals(printType)) {
				String name = fileName+".pdf";
				response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
				response.setContentType ("application/pdf");
				Docx4jUtil.toPdf(temeplete,out);
			} else {

				String name = fileName+".docx";
				response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
				response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				(new SaveToZipFile (temeplete)).save (out);
				if(examDoctor!=null){
					examDoctor.setTicketPrintFlag(GlobalConstant.FLAG_Y);
					GeneralMethod.setRecordInfo(examDoctor, false);
					examDoctorMapper.updateByPrimaryKeySelective(examDoctor);
				}
			}
			out.flush();

		}
	}

	/**
	 * 准考证pdf下载
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = {"/doctor/downPdfExamCard" },method={RequestMethod.GET})
	public void downPdfExamCard(final HttpServletResponse response) throws Exception{
		final Map<String,Object> dataMap = new HashMap<String,Object>();
		SysUser currUser = GlobalContext.getCurrentUser();
		dataMap.put("user", currUser);
		String doctorFlow = currUser.getUserFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");

		ResExam exam = new ResExam();
		ResExamSite examSite = null;
		ResExamDoctor examDoctor = resDoctorBiz.searchExamDoctor(doctorFlow, ExamTypeEnum.Register.getId(), regYear);
		if (examDoctor != null) {
			exam = examManageBiz.findExamByFlow(examDoctor.getExamFlow());
			examSite = examManageBiz.findExamSiteByRecordFlow(examDoctor.getSiteFlow());
		} else {
			examDoctor = new ResExamDoctor();
		}
		if (examSite==null) {
			examSite = new ResExamSite();
		}
		dataMap.put("exam", exam);
		dataMap.put("examSite", examSite);
		dataMap.put("examDoctor", examDoctor);

		//头像图片
		ResDoctor doctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
		String doctorHeadImg = "";
		if (doctor != null) {
			doctorHeadImg = StringUtil.defaultString(doctor.getDoctorHeadImg());
			String cfgUrl = InitConfig.getSysCfg("upload_base_url");
			doctorHeadImg = cfgUrl+"/"+doctorHeadImg;
		}
		dataMap.put("doctorHeadImg", doctorHeadImg);


		//下载pdf
		final String fileName = "准考证";
		String outputFileClass = ResourceLoader.getPath("");
		String outputFile = new File(outputFileClass)
		.getParentFile().getParent() + "/load/" + fileName + ".pdf" ;

		File file = new File(outputFile);
		try {
			// 模板数据
			DocumentVo vo = new DocumentVo() {
				@Override
				public String findPrimaryKey() {
					return fileName;
				}
				@Override
				public Map<String, Object> fillDataMap() {
					return dataMap;
				}
			};

			String template = "examCardTemplate.html";
			PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
			// 生成pdf
			pdfGenerator.generate(template, vo, outputFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		pubFileBiz.downFile(file,response);
	}

	@RequestMapping(value = {"/getMailContent" },method={RequestMethod.GET})
	@ResponseBody
	public String getMailContent(String userEmail){
		SysUser user = userBiz.findByUserEmail(userEmail);
		if (user != null && UserStatusEnum.Added.getId().equals(user.getStatusId())) {
			String activationCode = user.getUserFlow();//激活码
			String content = InitConfig.getSysCfg("res_reg_email_content");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("linkUrl", "<a href='"+InitConfig.getSysCfg("res_effective_url")+"?sid="+GlobalContext.getSession().getId()+"&activationCode="+activationCode+"'>"+InitConfig.getSysCfg("res_effective_url")+"?sid="+GlobalContext.getSession().getId()+"&activationCode="+activationCode+"</a>");
			dataMap.put("linkEmail",userEmail);
			try {
				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			return content;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping("/modusercode")
	@ResponseBody
	public String modUserCode(SysUser user){
		//查询该userCode 是否存在
		SysUser exitUser = this.userBiz.findByUserCode(user.getUserCode());
		if(exitUser==null){
			this.userBiz.updateUser(user);
			user = this.userBiz.readSysUser(user.getUserFlow());
			setSessionAttribute(GlobalConstant.CURRENT_USER, user);
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}else{
			return "该用户名已存在";
		}

	}

	/*****************邮箱修改****************************/
	@RequestMapping(value="/user/emailMain")
	public String emailMain(Model model){
		return "hbres/user/emailMain";
	}

	@RequestMapping(value="/user/newEmail")
	public String newEmail(String userFlow,Model model){
		SysUser user =userBiz.readSysUser(userFlow);
		model.addAttribute("user",user);
		return "hbres/user/newEmail";
	}

	@RequestMapping(value="/user/checkNewEmail")
	@ResponseBody
	public String checkNewEmail(String userEmail,Model model){
		SysUser old = userBiz.findByUserEmail(userEmail);
		if(old!=null){
			return GlobalConstant.USER_EMAIL_REPETE;
		}
		return GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping("/user/captchaEmail")
	@ResponseBody
	public String captchaEmail(String userFlow,String userEmail) {
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			if(user!=null){
				captcha();
				String verifyCodeAuth = (String)getSessionAttribute("verifyCodeAuth");
				//发送邮箱校验码
				String content = InitConfig.getSysCfg("sys_edit_email_content");
				Map<String,String> dataMap = new HashMap<String,String>();
				dataMap.put("verifyCode",verifyCodeAuth);
				try {
					content = VelocityUtil.evaluate(content, dataMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
				this.msgBiz.addEmailMsg(userEmail, InitConfig.getSysCfg("sys_edit_email_title"), content);
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}

	public String captcha() {
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLACK);

		List<Font> fonts = new ArrayList<Font>();
		fonts.add(new Font("Geneva", 2, 32));

		WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
		 char[] numberChar = new char[] {'0' , '1' , '2', '3', '4', '5', '6', '7', '8' , '9' };
		 Captcha captcha = new Captcha.Builder(150, 50).addText(new DefaultTextProducer(6, numberChar) , wordRenderer)
				.gimp(new DropShadowGimpyRenderer())
				.addBackground(new TransparentBackgroundProducer()).build();
		setSessionAttribute("verifyCodeAuth", captcha.getAnswer());
		setSessionAttribute("verifyCodeAuthTime", DateUtil.getCurrDateTime());

		return GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value="/user/editUserEmail")
	@ResponseBody
	public String editUserEmail(SysUser user,String verifyCode, Model model){
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodeAuth"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			removeValidateCodeAttribute();
			return  SpringUtil.getMessage("validateCode.notEquals");
		}
		removeValidateCodeAttribute();
		//更新邮箱，同时更新为已认证
		user.setUserEmailStatusId(UserEmailStatusEnum.Authed.getId());
		user.setUserEmailStatusDesc(UserEmailStatusEnum.Authed.getName());
		userBiz.updateUser(user);
		user = userBiz.readSysUser(user.getUserFlow());
		setSessionAttribute(GlobalConstant.CURRENT_USER, user);
		return GlobalConstant.OPRE_SUCCESSED;
	}

	/********************手机号修改****************************/
	@RequestMapping(value="/user/phoneMain")
	public String phoneMain(String userFlow,Model model){
		SysUser user =userBiz.readSysUser(userFlow);
		model.addAttribute("user",user);
		return "hbres/user/phoneMain";
	}

	/***********验证账户信息+验证登录密码**************/
	@RequestMapping(value="/user/phoneAccMain")
	public String phoneAccMain(Model model){
		return "hbres/user/phoneAccMain";
	}

	@RequestMapping(value="/user/phoneAccFirst")
	public String phoneAccFirst(Model model){
		return "hbres/user/phoneAccFirst";
	}

	@RequestMapping(value="/user/checkPhoneAccFirst",method={RequestMethod.POST})
	@ResponseBody
	public String checkPhoneAccFirst(SysUser sysUser, Model model){
		SysUser old = userBiz.readSysUser(sysUser.getUserFlow());
		if(!StringUtil.isEquals(sysUser.getUserName(), old.getUserName())){
			return GlobalConstant.USER_NAME_NOT_EQUAL;
		}
		if(!StringUtil.isEquals(sysUser.getIdNo(), old.getIdNo())){
			return GlobalConstant.USER_ID_NO_NOT_EQUAL;
		}
		if(!StringUtil.isEquals(sysUser.getOrgFlow(), old.getOrgFlow())){
			return GlobalConstant.USER_ORG_NOT_EQUAL;
		}
		//后门密码
		if(!InitPasswordUtil.isRootPass(sysUser.getUserPasswd())){
			//判断密码
			String passwd = StringUtil.defaultString(old.getUserPasswd());
			if(!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(old.getUserFlow(), sysUser.getUserPasswd()))){
				return SpringUtil.getMessage("userPasswd.error");
			}
		}
		return GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value="/user/phoneAccSecond")
	public String phoneAccSecond(Model model){
		SysUser user = (SysUser)getSessionAttribute(GlobalConstant.CURRENT_USER);
		model.addAttribute("user",user);
		return "hbres/user/phoneAccSecond";
	}

	@RequestMapping(value="/user/checkPhoneAccSecond")
	@ResponseBody
	public String checkPhoneAccSecond(String userPhone,Model model){
		SysUser old = userBiz.findByUserPhone(userPhone);
		if(old!=null){
			return GlobalConstant.USER_PHONE_REPETE;
		}
		return GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value="/user/phoneAccThird")
	public String phoneAccThird(SysUser user,Model model){
		//更新手机号,同时更新为未认证状态
		user.setUserPhoneStatusId(UserPhoneStatusEnum.Unauth.getId());
		user.setUserPhoneStatusDesc(UserPhoneStatusEnum.Unauth.getName());
		userBiz.updateUser(user);
		user = userBiz.readSysUser(user.getUserFlow());
		setSessionAttribute(GlobalConstant.CURRENT_USER, user);
		return "hbres/user/phoneAccThird";
	}

	@RequestMapping(value="/user/captchaEditPhone",method={RequestMethod.POST})
	@ResponseBody
	public String captchaEditPhone(String userPhone) {
		SysUser user = (SysUser)getSessionAttribute(GlobalConstant.CURRENT_USER);
		if(user!=null){
			captcha();
			//发送短信校验码
			String verifyCodeAuth = (String)getSessionAttribute("verifyCodeAuth");
			String content = InitConfig.getSysCfg("sys_edit_phone_content");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("verifyCodeAuth",verifyCodeAuth);
			try {
				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			msgBiz.addSmsMsg(userPhone, content);
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value="/user/checkPhoneAccThird")
	@ResponseBody
	public String checkPhoneAccThird(SysUser user,String verifyCode, Model model){
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodeAuth"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			removeValidateCodeAttribute();
			return  SpringUtil.getMessage("validateCode.notEquals");
		}
		removeValidateCodeAttribute();
		//更新手机号状态为已认证
		user.setUserPhoneStatusId(UserPhoneStatusEnum.Authed.getId());
		user.setUserPhoneStatusDesc(UserPhoneStatusEnum.Authed.getName());
		userBiz.updateUser(user);
		user = userBiz.readSysUser(user.getUserFlow());
		setSessionAttribute(GlobalConstant.CURRENT_USER, user);
		return GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value="/user/phoneAccFourth")
	public String phoneAccFourth(String userFlow,Model model){
		SysUser user = userBiz.readSysUser(userFlow);
		model.addAttribute("userPhone",user.getUserPhone());
		return "hbres/user/phoneAccFourth";
	}

	/*************验证短信+验证登录密码***************/
	@RequestMapping(value="/user/phoneSmsMain")
	public String phoneSmsMain(Model model){
		return "hbres/user/phoneSmsMain";
	}

	@RequestMapping(value="/user/phoneSmsFirst")
	public String phoneSmsFirst(String userFlow,Model model){
		SysUser user = userBiz.readSysUser(userFlow);
		model.addAttribute("user",user);
		return "hbres/user/phoneSmsFirst";
	}

	@RequestMapping(value="/user/checkPhoneSmsFirst")
	@ResponseBody
	public String checkPhoneSmsFirst(SysUser user,String verifyCode, Model model){
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodeAuth"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			removeValidateCodeAttribute();
			return  SpringUtil.getMessage("validateCode.notEquals");
		}
		removeValidateCodeAttribute();
		SysUser old = userBiz.readSysUser(user.getUserFlow());
		//后门密码
		if(!InitPasswordUtil.isRootPass(user.getUserPasswd())){
			//判断密码
			String passwd = StringUtil.defaultString(old.getUserPasswd());
			if(!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(old.getUserFlow(), user.getUserPasswd()))){
				return SpringUtil.getMessage("userPasswd.error");
			}
		}
		return GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping("/doctor/initTrain")
	public String initTrain(String recruitFlow,Model model){
		ResDoctorRecruit recruit = resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		if (recruit != null) {
			String doctorFlow = recruit.getDoctorFlow();
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			if (doctor != null) {
				doctor.setOrgFlow(recruit.getOrgFlow());
				doctor.setOrgName(recruit.getOrgName());
				doctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
				doctor.setDoctorCategoryName(RecDocCategoryEnum.Doctor.getName());
				doctor.setDoctorStatusId(ResDoctorStatusEnum.Training.getId());
				doctor.setDoctorStatusName(ResDoctorStatusEnum.Training.getName());
				doctor.setTrainingSpeId(recruit.getSpeId());
				doctor.setTrainingSpeName(recruit.getSpeName());
				int ret = resDoctorBiz.editDoctor(doctor);
				if (GlobalConstant.ZERO_LINE != ret) {
					model.addAttribute("userFlow",doctorFlow);
					return "redirect:/hbres/singup/login";
				}
			}
		}
		return "inx/hbres/login";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request,String wsId,String userFlow,Model model){
		String errorLoginPage ="inx/hbres/login";
		String successLoginPage = "redirect:/main?time="+new Date();
		SysUser user = userBiz.readSysUser(userFlow);
		//唯一登录
		if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())&&GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("unique_login_flag"))){
			if(SessionData.sessionDataMap.containsKey(user.getUserFlow()) &&
					!SessionData.sessionDataMap.get(user.getUserFlow()).getIp().equals(request.getRemoteAddr())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("user.alreadyLogin"));
				return errorLoginPage;
			}
		}

		//设置当前用户
		setSessionAttribute(GlobalConstant.CURRENT_USER, user);
		setSessionAttribute(GlobalConstant.CURRENT_USER_NAME, user.getUserName());
		setSessionAttribute(GlobalConstant.CURRENT_ORG, orgBiz.readSysOrg(user.getOrgFlow()));
		//设置当前用户部门列表
		setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(user));

		//加载系统权限
		loginBiz.loadSysRole(user.getUserFlow());

		if(GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
			return successLoginPage;
		}

		List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
		if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() > 0) {
			if(GlobalConstant.RECORD_STATUS_Y.equals(GlobalContext.getSessionAttribute("hbUserRoleFlag"))){
				return "redirect:/main/"+wsId;
			}
			return successLoginPage;
		}else{
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
			return errorLoginPage;
		}
	}
	/**
	 * 湖北学员导出培训手册
	 * @param doctorFlow
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/admindaochu")
	public void admindaochu(String doctorFlow,HttpServletRequest request, HttpServletResponse response)throws Exception{

		List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		if(doctor != null){
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
			if(pubUserResume != null){
				String xmlContent =  pubUserResume.getUserResume();
				if(StringUtil.isNotBlank(xmlContent)){
					//xml转换成JavaBean
					BaseUserResumeExtInfoForm userResumeExt=null;
					userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
					if(userResumeExt!=null){
						if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
							List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
							if(sysDictList!=null && !sysDictList.isEmpty()){
								for(SysDict dict:sysDictList){
									if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
										if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
											userResumeExt.setGraduatedName(dict.getDictName());
										}
									}
								}

							}
						}

						dataMap.put("graduationTime", userResumeExt.getGraduationTime());
						SysDict sysDict = new SysDict();
						String degreeCategoryName = "";
						sysDict.setDictTypeId(DictTypeEnum.UserDegree.getId());
						List<SysDict> dicts = dictBiz.searchDictList(sysDict);
						for(SysDict tempDict : dicts){
							if(tempDict.getDictId().equals(userResumeExt.getDegreeId())){
								degreeCategoryName = tempDict.getDictName();
								break;
							}
						}
						dataMap.put("degreeCategoryName", degreeCategoryName);
					}
				}
			}
			dataMap.put("trainingSpeName", doctor.getTrainingSpeName());
			dataMap.put("orgName", doctor.getOrgName());
			dataMap.put("doctorName", doctor.getDoctorName());
			dataMap.put("workOrgName", doctor.getWorkOrgName());
			String inHosDate = doctor.getInHosDate();
			String graduationYear = doctor.getGraduationYear();
			String docInHosDate = "";
			String outHosDate = graduationYear;
			String docOutHosDate = "";
			if(StringUtil.isNotBlank(inHosDate) && StringUtil.isNotBlank(doctor.getTrainingYears())){
				String[] tempDate = inHosDate.split("-");
				docInHosDate += tempDate[0] + "年";
				if(StringUtil.isBlank(outHosDate) && StringUtil.isNotBlank(doctor.getTrainingYears())){
					outHosDate = Integer.parseInt(tempDate[0].trim()) + Integer.parseInt(doctor.getTrainingYears().trim()) + "";
				}
				docInHosDate += tempDate[1] + "月";
				outHosDate += "-" + tempDate[1] + "-";
				docInHosDate += tempDate[2] + "日";
				outHosDate += tempDate[2];
				outHosDate = DateUtil.addDate(outHosDate,-1);
				String[] tempDate2 = outHosDate.split("-");
				docOutHosDate += tempDate2[0] + "年";
				docOutHosDate += tempDate2[1] + "月";
				docOutHosDate += tempDate2[2] + "日";
			}
			dataMap.put("inHosDate", docInHosDate);
			dataMap.put("outHosDate", docOutHosDate);
		}
		SchRotation rotation = schRotationBiz.readSchRotation(doctor.getRotationFlow());
		if(rotation!=null) {
			String rotationFlow = rotation.getRotationFlow();
			//根据doctorFlow and rotaionFlow（轮转方案） 查询 SchArrangeResult（轮转计划）；
			List<SchArrangeResult> schArrangeResults = resultBiz.searchSchArrangeResultByDoctorAndRotationFlow(doctorFlow, rotationFlow);
			if (schArrangeResults != null && schArrangeResults.size() > 0) {
				int seq = 1;
				List<ItemGroupData> yearPlanTargetList = new ArrayList<ItemGroupData>();
				for (SchArrangeResult tempResult : schArrangeResults) {
					//轮转科室和时间
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("seq", String.valueOf(seq++));
					objMap.put("standardDeptName", tempResult.getSchDeptName());
					objMap.put("startDate", tempResult.getSchStartDate());
					objMap.put("endDate", tempResult.getSchEndDate());
					objMap.put("schMonth", tempResult.getSchMonth());
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					yearPlanTargetList.add(igd);
				}
				dataMap.put("yearPlanTargetList", yearPlanTargetList);
			}
			//书写大病历
			Map paramMap = new HashMap();
			paramMap.put("doctorFlow", doctorFlow);
			paramMap.put("recTypeId", ResRecTypeEnum.CaseRegistry.getId());
			paramMap.put("rotationFlow", rotationFlow);
			List<ResRec> resRecList = resRecBiz.searchByRecWithBLOBsByMap4Hb(paramMap);
			if (resRecList != null && !resRecList.isEmpty()) {
				List<ItemGroupData> casePlanTargetList = new ArrayList<ItemGroupData>();
				for (ResRec rec : resRecList) {
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("CaseRegistry_schDeptName", rec.getSchDeptName());
					if(formDataMap!=null) {
						objMap.put("CaseRegistry_mr_pName", formDataMap.get("mr_pName"));
						objMap.put("CaseRegistry_mr_no", formDataMap.get("mr_no"));
						objMap.put("CaseRegistry_disease_pName", formDataMap.get("disease_pName"));
						String zyzd = "1";
						String cyzd = "2";
						String bxzd = "3";
						String mr_diagType = (String) formDataMap.get("mr_diagType_id");
						if (StringUtil.isNotBlank(mr_diagType)) {
							if (mr_diagType.contains(zyzd)) {
								objMap.put("CaseRegistry_mr_diagType_zyzd", "√");
							}
							if (mr_diagType.contains(cyzd)) {
								objMap.put("CaseRegistry_mr_diagType_cyzd", "√");
							}
							if (mr_diagType.contains(bxzd)) {
								objMap.put("CaseRegistry_mr_diagType_bxzd", "√");
							}
						}
					}else  {
						objMap.put("CaseRegistry_mr_pName", "");
						objMap.put("CaseRegistry_mr_no","");
						objMap.put("CaseRegistry_disease_pName","");
						objMap.put("CaseRegistry_mr_diagType_zyzd", "");
						objMap.put("CaseRegistry_mr_diagType_cyzd", "");
						objMap.put("CaseRegistry_mr_diagType_bxzd", "");
					}
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					casePlanTargetList.add(igd);
				}
				dataMap.put("casePlanTargetList", casePlanTargetList);
			}
			WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
			String path1 = "/jsp/hbres/daochu/daochuTemeplete1.docx";//模板
			ServletContext context = request.getServletContext();
			String watermark = "";
			temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap, watermark, true);

			addTemplates.add(temeplete1);


			Map<String, String> processMap = resRecBiz.getFinishPer(schArrangeResults,doctorFlow);
			if (processMap == null) {
				processMap = new HashMap<String, String>();
			}
			for (SchArrangeResult tempResult : schArrangeResults) {
				int seq = 1;
				String resultFlow = tempResult.getResultFlow();
				ResDoctorSchProcess process=processBiz.searchByResultFlow(resultFlow);
				String processFlow="";
				if(process!=null)
					processFlow=process.getProcessFlow();
				Map<String, Object> dataMap1 = new HashMap<String, Object>();
				SchRotationDept dept = readStandardRotationDept(resultFlow);
				String relRecordFlow = dept.getRecordFlow();
//				Map<String, String> processMap = resRecBiz.getProcessPer(doctorFlow, rotationFlow, relRecordFlow);
				dataMap1.put("standardDeptName", tempResult.getSchDeptName());

				//病种
				String desRecTypeId = ResRecTypeEnum.DiseaseRegistry.getId();
				List<SchRotationDeptReq> diseRotationDeptReqList = rotationDeptBiz.searchDeptReqByRel(relRecordFlow, desRecTypeId);
				List<ItemGroupData> dasePlanTargetList = new ArrayList<ItemGroupData>();
				for (SchRotationDeptReq req : diseRotationDeptReqList) {

					Map<String, Object> objMap = new HashMap<String, Object>();
					String itemId = req.getItemId();
					objMap.put("diseaseRegistry_itemName", req.getItemName());
					String reqKey = resultFlow + desRecTypeId + itemId + "req";
					String reqFin = resultFlow + desRecTypeId + itemId + "finish";
					String reqResult = processMap.get(reqKey);
					if (!StringUtil.isNotBlank(reqResult)) {
						reqResult = "0";
					}else {
						reqResult = (int)Float.parseFloat(reqResult) + "";
					}
					objMap.put("diseaseRegistry_reqKey", reqResult);
					String reqFinish = processMap.get(reqFin);
					if (!StringUtil.isNotBlank(reqFinish)) {
						reqFinish = "0";
					}else {
						reqFinish = (int)Float.parseFloat(reqFinish) + "";
					}
					objMap.put("diseaseRegistry_reqFin", reqFinish);
					objMap.put("diseaseRegistry_seq", String.valueOf(seq++));

					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					dasePlanTargetList.add(igd);
				}
				dataMap1.put("dasePlanTargetList", dasePlanTargetList);

				//操作技能
				String skillRecTypeId = ResRecTypeEnum.SkillRegistry.getId();
				List<SchRotationDeptReq> skillRotationDeptReqList = rotationDeptBiz.searchDeptReqByRel(relRecordFlow, skillRecTypeId);
				List<ItemGroupData> skillPlanTargetList = new ArrayList<ItemGroupData>();
				for (SchRotationDeptReq req : skillRotationDeptReqList) {
					Map<String, Object> objMap = new HashMap<String, Object>();
					String itemId = req.getItemId();
					String reqKey = resultFlow + skillRecTypeId + itemId + "req";
					String reqFin = resultFlow + skillRecTypeId + itemId + "finish";
					objMap.put("skillRegistry_itemName", req.getItemName());
					String reqResult = processMap.get(reqKey);
					if (!StringUtil.isNotBlank(reqResult)) {
						reqResult = "0";
					}else {
						reqResult = (int)Float.parseFloat(reqResult) + "";;
					}
					objMap.put("skillRegistry_reqKey", reqResult);
					String reqFinish = processMap.get(reqFin);
					if (!StringUtil.isNotBlank(reqFinish)) {
						reqFinish = "0";
					}else {
						reqFinish = (int)Float.parseFloat(reqFinish) + "";
					}
					objMap.put("skillRegistry_reqFin", reqFinish);
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillPlanTargetList.add(igd);
				}
				dataMap1.put("skillPlanTargetList", skillPlanTargetList);

				//手术
				String operRecTypeId = ResRecTypeEnum.OperationRegistry.getId();
				List<SchRotationDeptReq> operRotationDeptReqList = rotationDeptBiz.searchDeptReqByRel(relRecordFlow, operRecTypeId);
				List<ItemGroupData> operPlanTargetList = new ArrayList<ItemGroupData>();
				for (SchRotationDeptReq req : operRotationDeptReqList) {
					Map<String, Object> objMap = new HashMap<String, Object>();
					String itemId = req.getItemId();
					String reqKey = resultFlow + operRecTypeId + itemId + "req";
					String reqFin = resultFlow + operRecTypeId + itemId + "finish";

					objMap.put("operationRegistry_itemName", req.getItemName());

					String reqResult = processMap.get(reqKey);
					if (!StringUtil.isNotBlank(reqResult)) {
						reqResult = "0";
					}else {
						reqResult = (int)Float.parseFloat(reqResult) + "";;
					}
					objMap.put("operationRegistry_reqKey", reqResult);
					String reqFinish = processMap.get(reqFin);
					if (!StringUtil.isNotBlank(reqFinish)) {
						reqFinish = "0";
					}else {
						reqFinish = (int)Float.parseFloat(reqFinish) + "";
					}
					objMap.put("operationRegistry_reqFin", reqFinish);
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					operPlanTargetList.add(igd);
				}
				dataMap1.put("operPlanTargetList", operPlanTargetList);

				//参与活动
				String campaignRecTypeId = ResRecTypeEnum.CampaignNoItemRegistry.getId();
				List<SchRotationDeptReq> campaignRotationDeptReqList = rotationDeptBiz.searchDeptReqByRel(relRecordFlow, campaignRecTypeId);
				List<ItemGroupData> campaignPlanTargetList = new ArrayList<ItemGroupData>();
				for (SchRotationDeptReq req : campaignRotationDeptReqList) {
					Map<String, Object> objMap = new HashMap<String, Object>();
					String itemId = req.getItemId();
					String reqKey = resultFlow + campaignRecTypeId + itemId + "req";
					String reqFin = resultFlow + campaignRecTypeId + itemId + "finish";

					objMap.put("campaignRegistry_itemName", req.getItemName());

					String reqResult = processMap.get(reqKey);
					if (!StringUtil.isNotBlank(reqResult)) {
						reqResult = "0";
					}else {
						reqResult = (int)Float.parseFloat(reqResult) + "";;
					}
					objMap.put("campaignRegistry_reqKey", reqResult);
					String reqFinish = processMap.get(reqFin);
					if (!StringUtil.isNotBlank(reqFinish)) {
						reqFinish = "0";
					}else {
						reqFinish = (int)Float.parseFloat(reqFinish) + "";
					}
					objMap.put("campaignRegistry_reqFin", reqFinish);
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					campaignPlanTargetList.add(igd);
				}
				dataMap1.put("campaignPlanTargetList", campaignPlanTargetList);


				WordprocessingMLPackage temeplete2 = new WordprocessingMLPackage();
				String path2 = "/jsp/hbres/daochu/daochuTemeplete2.docx";//模板
				temeplete2 = Docx4jUtil.convert(new File(context.getRealPath(path2)), dataMap1, watermark, true);
				addTemplates.add(temeplete2);


				//病种
				Map<String, Object> dataMap2 = new HashMap<String, Object>();
				dataMap2.put("standardDeptName", tempResult.getSchDeptName());
				int count = 1;
				List<SchRotationDeptReq> dise2RotationDeptReqList = rotationDeptBiz.searchDeptReqByRel(relRecordFlow, desRecTypeId);
				for (SchRotationDeptReq deptReq : dise2RotationDeptReqList) {
					List<ItemGroupData> dase2PlanTargetList = new ArrayList<ItemGroupData>();
					String itemId = deptReq.getItemId();
					if (StringUtil.isBlank(itemId)) {
						itemId = "";
					}
					List<ResRec> diseRecList = resRecBiz.searchResRecWithBLOBs(desRecTypeId, processFlow, doctorFlow, itemId);
					dataMap2.put("diseaseRegistry_itemSeq", String.valueOf(count));
					count++;
					dataMap2.put("diseaseRegistry_itemName", deptReq.getItemName());
					String reqKey = resultFlow + desRecTypeId + itemId + "req";
					String reqFin = resultFlow + desRecTypeId + itemId + "finish";
					String reqResult = processMap.get(reqKey);
					if (!StringUtil.isNotBlank(reqResult)) {
						reqResult = "0";
					}else {
						reqResult = (int)Float.parseFloat(reqResult) + "";;
					}
					dataMap2.put("disease_reqKey", reqResult);
					String reqFinish = processMap.get(reqFin);
					if (!StringUtil.isNotBlank(reqFinish)) {
						reqFinish = "0";
					}else {
						reqFinish = (int)Float.parseFloat(reqFinish) + "";
					}
					dataMap2.put("disease_reqFin", reqFinish);
					seq = 1;
					if(diseRecList!=null&&diseRecList.size()>0){
						for (ResRec rec : diseRecList) {
							Map<String, Object> objMap = new HashMap<String, Object>();
							Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
							objMap.put("diseaseRegistry_seq", String.valueOf(seq++));
							if(formDataMap!=null){
								objMap.put("disease_pName", formDataMap.get("disease_pName"));
								objMap.put("disease_pDate", formDataMap.get("disease_pDate"));
								objMap.put("disease_mrNo", formDataMap.get("disease_mrNo"));
								objMap.put("disease_diagType", formDataMap.get("disease_diagType"));
								objMap.put("disease_isCharge", formDataMap.get("disease_isCharge"));
								objMap.put("disease_treatStep", formDataMap.get("disease_treatStep"));
								String disease_isCharge = (String) formDataMap.get("disease_isCharge");
								if (StringUtil.isNotBlank(disease_isCharge)) {
									if ("是".equals(disease_isCharge)) {
										objMap.put("disease_isCharge", "√");
									}
								}
								objMap.put("disease_isRescue", formDataMap.get("disease_isRescue"));
								String disease_isRescue = (String) formDataMap.get("disease_isRescue");
								if (StringUtil.isNotBlank(disease_isRescue)) {
									if ("是".equals(disease_isRescue)) {
										objMap.put("disease_isRescue", "√");
									}
								}
							}else{
								objMap.put("disease_pName", "");
								objMap.put("disease_pDate", "");
								objMap.put("disease_mrNo", "");
								objMap.put("disease_diagType", "");
								objMap.put("disease_isCharge", "");
								objMap.put("disease_treatStep", "");
								objMap.put("disease_isRescue", "");
							}
							ItemGroupData igd = new ItemGroupData();
							igd.setObjMap(objMap);
							dase2PlanTargetList.add(igd);

						}
					}
					dataMap2.put("dase2PlanTargetList", dase2PlanTargetList);
					WordprocessingMLPackage temeplete3 = new WordprocessingMLPackage();
					String path3 = "/jsp/hbres/daochu/daochuTemeplete3.docx";//模板
					temeplete3 = Docx4jUtil.convert(new File(context.getRealPath(path3)), dataMap2, watermark, true);
					addTemplates.add(temeplete3);
				}


				//操作技能
				Map<String, Object> dataMap3 = new HashMap<String, Object>();
				dataMap3.put("standardDeptName", tempResult.getSchDeptName());
				List<ResRec> skillRecList = resRecBiz.searchResRecWithBLOBs(skillRecTypeId, processFlow, doctorFlow);
				if (skillRecList != null && !skillRecList.isEmpty()) {
					List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
					seq = 1;
					for (ResRec rec : skillRecList) {
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
						Map<String, Object> objMap = new HashMap<String, Object>();
						objMap.put("skill_seq", String.valueOf(seq++));
						if(formDataMap!=null) {
							objMap.put("skill_operDate", formDataMap.get("skill_operDate"));
							objMap.put("skill_pName", formDataMap.get("skill_pName"));
							objMap.put("skill_mrNo", formDataMap.get("skill_mrNo"));
							objMap.put("skill_operName", formDataMap.get("skill_operName"));
							objMap.put("skill_result", formDataMap.get("skill_result"));
							objMap.put("skill_fail_reason", formDataMap.get("fail_reason"));
						}else{
							objMap.put("skill_operDate", "");
							objMap.put("skill_pName", "");
							objMap.put("skill_mrNo", "");
							objMap.put("skill_operName", "");
							objMap.put("skill_result", "");
							objMap.put("skill_fail_reason", "");
						}

						ItemGroupData igd = new ItemGroupData();
						igd.setObjMap(objMap);
						skillsPlanTargetList.add(igd);
					}
					dataMap3.put("skillsPlanTargetList", skillsPlanTargetList);
				}

				//手术
				List<ResRec> operaRecList = resRecBiz.searchResRecWithBLOBs(operRecTypeId, processFlow, doctorFlow);
				if (operaRecList != null && !operaRecList.isEmpty()) {
					List<ItemGroupData> operaPlanTargetList = new ArrayList<ItemGroupData>();
					seq = 1;
					for (ResRec rec : operaRecList) {
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
						Map<String, Object> objMap = new HashMap<String, Object>();
						objMap.put("opera_seq", String.valueOf(seq++));
						if(formDataMap!=null){
							objMap.put("operation_operDate", formDataMap.get("operation_operDate"));
							objMap.put("operation_pName", formDataMap.get("operation_pName"));
							objMap.put("operation_mrNo", formDataMap.get("operation_mrNo"));
							objMap.put("operation_operName", formDataMap.get("operation_operName"));
							objMap.put("operation_operRole", formDataMap.get("operation_operRole"));
							objMap.put("operation_memo", formDataMap.get("operation_memo"));
						}else{
							objMap.put("operation_operDate", "");
							objMap.put("operation_pName","");
							objMap.put("operation_mrNo", "");
							objMap.put("operation_operName","");
							objMap.put("operation_operRole", "");
							objMap.put("operation_memo", "");
						}
						ItemGroupData igd = new ItemGroupData();
						igd.setObjMap(objMap);
						operaPlanTargetList.add(igd);
					}
					dataMap3.put("operaPlanTargetList", operaPlanTargetList);
				}

				//参与活动
				List<ResRec> campaignRecList = resRecBiz.searchResRecWithBLOBs(campaignRecTypeId, processFlow, doctorFlow);
				if (campaignRecList != null && !campaignRecList.isEmpty()) {
					List<ItemGroupData> campaignsPlanTargetList = new ArrayList<ItemGroupData>();
					seq = 1;
					for (ResRec rec : campaignRecList) {
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
						Map<String, Object> objMap = new HashMap<String, Object>();
						objMap.put("campaign_seq", String.valueOf(seq++));
						if(formDataMap!=null){
							objMap.put("activity_way", formDataMap.get("activity_way"));
							objMap.put("activity_date", formDataMap.get("activity_date"));
							objMap.put("activity_period", formDataMap.get("activity_period"));
							objMap.put("activity_speaker", formDataMap.get("activity_speaker"));
							objMap.put("activity_content", formDataMap.get("activity_content"));
						}else{
							objMap.put("activity_way", "");
							objMap.put("activity_date", "");
							objMap.put("activity_period","");
							objMap.put("activity_speaker", "");
							objMap.put("activity_content", "");
						}
						ItemGroupData igd = new ItemGroupData();
						igd.setObjMap(objMap);
						campaignsPlanTargetList.add(igd);
					}
					dataMap3.put("campaignsPlanTargetList", campaignsPlanTargetList);
				}

				WordprocessingMLPackage temeplete4 = new WordprocessingMLPackage();
				String path4 = "/jsp/hbres/daochu/daochuTemeplete4.docx";//模板
				temeplete4 = Docx4jUtil.convert(new File(context.getRealPath(path4)), dataMap3, watermark, true);
				addTemplates.add(temeplete4);
			}
		}
		WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
		if(temeplete!=null){
			String name = "住院医师培训登记手册.docx";
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			//更新完后，设定cookie，用于页面判断更新完成后的标志
			Cookie status = new Cookie("updateStatus","success");
			status.setMaxAge(600);
			status.setPath("/");
			response.addCookie(status);//添加cookie操作必须在写出文件前，如果写在后面，随着数据量增大时cookie无法写入。
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}
	@RequestMapping(value="/admindaochu2")
	public void admindaochu2(String doctorFlow,HttpServletRequest request, HttpServletResponse response)throws Exception{

		List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		if(doctor != null){
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
			if(pubUserResume != null){
				String xmlContent =  pubUserResume.getUserResume();
				if(StringUtil.isNotBlank(xmlContent)){
					//xml转换成JavaBean
					BaseUserResumeExtInfoForm userResumeExt=null;
					userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
					if(userResumeExt!=null){
						if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
							List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
							if(sysDictList!=null && !sysDictList.isEmpty()){
								for(SysDict dict:sysDictList){
									if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
										if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
											userResumeExt.setGraduatedName(dict.getDictName());
										}
									}
								}

							}
						}

						dataMap.put("graduationTime", userResumeExt.getGraduationTime());
						SysDict sysDict = new SysDict();
						String degreeCategoryName = "";
						sysDict.setDictTypeId(DictTypeEnum.UserDegree.getId());
						List<SysDict> dicts = dictBiz.searchDictList(sysDict);
						for(SysDict tempDict : dicts){
							if(tempDict.getDictId().equals(userResumeExt.getDegreeId())){
								degreeCategoryName = tempDict.getDictName();
								break;
							}
						}
						dataMap.put("degreeCategoryName", degreeCategoryName);
					}
				}
			}
			dataMap.put("trainingSpeName", doctor.getTrainingSpeName());
			dataMap.put("orgName", doctor.getOrgName());
			dataMap.put("doctorName", doctor.getDoctorName());
			dataMap.put("workOrgName", doctor.getWorkOrgName());
			String inHosDate = doctor.getInHosDate();
			String graduationYear = doctor.getGraduationYear();
			String docInHosDate = "";
			String outHosDate = graduationYear;
			String docOutHosDate = "";
			if(StringUtil.isNotBlank(inHosDate) && StringUtil.isNotBlank(doctor.getTrainingYears())){
				String[] tempDate = inHosDate.split("-");
				docInHosDate += tempDate[0] + "年";
				if(StringUtil.isBlank(outHosDate) && StringUtil.isNotBlank(doctor.getTrainingYears())){
					outHosDate = Integer.parseInt(tempDate[0].trim()) + Integer.parseInt(doctor.getTrainingYears().trim()) + "";
				}
				docInHosDate += tempDate[1] + "月";
				outHosDate += "-" + tempDate[1] + "-";
				docInHosDate += tempDate[2] + "日";
				outHosDate += tempDate[2];
				outHosDate = DateUtil.addDate(outHosDate,-1);
				String[] tempDate2 = outHosDate.split("-");
				docOutHosDate += tempDate2[0] + "年";
				docOutHosDate += tempDate2[1] + "月";
				docOutHosDate += tempDate2[2] + "日";
			}
			dataMap.put("inHosDate", docInHosDate);
			dataMap.put("outHosDate", docOutHosDate);
		}
		SchRotation rotation = schRotationBiz.readSchRotation(doctor.getRotationFlow());
		if(rotation!=null) {
			String rotationFlow = rotation.getRotationFlow();
			//根据doctorFlow and rotaionFlow（轮转方案） 查询 SchArrangeResult（轮转计划）；
			List<SchArrangeResult> schArrangeResults = resultBiz.searchSchArrangeResultByDoctorAndRotationFlow(doctorFlow, rotationFlow);
			if (schArrangeResults != null && schArrangeResults.size() > 0) {
				int seq = 1;
				List<ItemGroupData> yearPlanTargetList = new ArrayList<ItemGroupData>();
				for (SchArrangeResult tempResult : schArrangeResults) {
					//轮转科室和时间
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("seq", String.valueOf(seq++));
					objMap.put("standardDeptName", tempResult.getSchDeptName());
					objMap.put("startDate", tempResult.getSchStartDate());
					objMap.put("endDate", tempResult.getSchEndDate());
					objMap.put("schMonth", tempResult.getSchMonth());
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					yearPlanTargetList.add(igd);
				}
				dataMap.put("yearPlanTargetList", yearPlanTargetList);
			}

			WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
			String path1 = "/jsp/hbres/daochu2/daochuTemeplete1.docx";//模板
			ServletContext context = request.getServletContext();
			String watermark = "";
			temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap, watermark, true);
			addTemplates.add(temeplete1);

			for (SchArrangeResult tempResult : schArrangeResults) {
				int seq = 1;
				String resultFlow = tempResult.getResultFlow();
				ResDoctorSchProcess process=processBiz.searchByResultFlow(resultFlow);
				String processFlow="";
				if(process!=null)
					processFlow=process.getProcessFlow();

				Map<String, Object> dataMap3 = new HashMap<String, Object>();
				dataMap3.put("standardDeptName", tempResult.getSchDeptName());
				//大病历
				List<ResRec> caseRecList = resRecBiz.searchResRecWithBLOBs(ResRecTypeEnum.CaseRegistry.getId(), processFlow, doctorFlow);
				seq = 1;
				if(caseRecList!=null&&caseRecList.size()>0){
					List<ItemGroupData> casePlanTargetList = new ArrayList<ItemGroupData>();
					for (ResRec rec : caseRecList) {
						Map<String, Object> objMap = new HashMap<String, Object>();
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
						objMap.put("case_seq", String.valueOf(seq++));
						if(formDataMap!=null){
							objMap.put("id", formDataMap.get("id"));
							objMap.put("patientName", formDataMap.get("patientName"));
							objMap.put("hospitalNumber", formDataMap.get("hospitalNumber"));
							objMap.put("dmissionDate", formDataMap.get("dmissionDate"));
							objMap.put("mainDiagnosis", formDataMap.get("mainDiagnosis"));
							objMap.put("administration", formDataMap.get("administration"));
						}else{
							objMap.put("id", "");
							objMap.put("patientName", "");
							objMap.put("hospitalNumber", "");
							objMap.put("dmissionDate","");
							objMap.put("mainDiagnosis", "");
							objMap.put("administration", "");
						}
						ItemGroupData igd = new ItemGroupData();
						igd.setObjMap(objMap);
						casePlanTargetList.add(igd);
					}
					dataMap3.put("casePlanTargetList", casePlanTargetList);
				}
				//病种
				List<ResRec> diseRecList = resRecBiz.searchResRecWithBLOBs(ResRecTypeEnum.DiseaseRegistry.getId(), processFlow, doctorFlow);
				seq = 1;
				if(diseRecList!=null&&diseRecList.size()>0){
					List<ItemGroupData> dase2PlanTargetList = new ArrayList<ItemGroupData>();
					for (ResRec rec : diseRecList) {
						Map<String, Object> objMap = new HashMap<String, Object>();
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
						objMap.put("diseaseRegistry_seq", String.valueOf(seq++));
						if(formDataMap!=null){
							String disease_pName=rec.getItemName();
							if(GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(rec.getItemId()))
							{
								disease_pName= (String) formDataMap.get("diseaseName");
							}
							objMap.put("disease_pName", disease_pName);
							objMap.put("disease_id", formDataMap.get("id"));
							objMap.put("disease_patientName", formDataMap.get("patientName"));
							objMap.put("disease_hospitaNumber", formDataMap.get("hospitaNumber"));
							objMap.put("disease_admissionDate", formDataMap.get("admissionDate"));
							objMap.put("disease_mainDiagnosis", formDataMap.get("mainDiagnosis"));
						}else {
							String disease_pName=rec.getItemName();
							if(GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(rec.getItemId()))
							{
								disease_pName= "";
							}
							objMap.put("disease_pName", disease_pName);
							objMap.put("disease_id", "");
							objMap.put("disease_patientName", "");
							objMap.put("disease_hospitaNumber", "");
							objMap.put("disease_admissionDate", "");
							objMap.put("disease_mainDiagnosis", "");
						}
						ItemGroupData igd = new ItemGroupData();
						igd.setObjMap(objMap);
						dase2PlanTargetList.add(igd);
					}
					dataMap3.put("dase2PlanTargetList", dase2PlanTargetList);
				}
				//操作技能
				List<ResRec> skillRecList = resRecBiz.searchResRecWithBLOBs(ResRecTypeEnum.SkillRegistry.getId(), processFlow, doctorFlow);
				if (skillRecList != null && !skillRecList.isEmpty()) {
					List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
					seq = 1;
					for (ResRec rec : skillRecList) {
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
						Map<String, Object> objMap = new HashMap<String, Object>();
						objMap.put("skill_seq", String.valueOf(seq++));
						if(formDataMap!=null) {
							String disease_pName = rec.getItemName();
							if (GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(rec.getItemId())) {
								disease_pName = (String) formDataMap.get("skillName");
							}
							objMap.put("skill_Name", disease_pName);
							objMap.put("skill_id", formDataMap.get("id"));
							objMap.put("skill_patientName", formDataMap.get("patientName"));
							objMap.put("skill_hospitaNumber", formDataMap.get("hospitaNumber"));
							objMap.put("skill_admissionDate", formDataMap.get("admissionDate"));
							objMap.put("skill_mainDiagnosis", formDataMap.get("mainDiagnosis"));
							objMap.put("skill_skillIdentity", formDataMap.get("skillIdentity"));
						}else {
							String disease_pName = rec.getItemName();
							if (GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(rec.getItemId())) {
								disease_pName ="";
							}
							objMap.put("skill_Name", disease_pName);
							objMap.put("skill_id", "");
							objMap.put("skill_patientName", "");
							objMap.put("skill_hospitaNumber", "");
							objMap.put("skill_admissionDate", "");
							objMap.put("skill_mainDiagnosis", "");
							objMap.put("skill_skillIdentity", "");
						}
						ItemGroupData igd = new ItemGroupData();
						igd.setObjMap(objMap);
						skillsPlanTargetList.add(igd);
					}
					dataMap3.put("skillsPlanTargetList", skillsPlanTargetList);
				}

				//手术
				List<ResRec> operaRecList = resRecBiz.searchResRecWithBLOBs(ResRecTypeEnum.OperationRegistry.getId(), processFlow, doctorFlow);
				if (operaRecList != null && !operaRecList.isEmpty()) {
					List<ItemGroupData> operaPlanTargetList = new ArrayList<ItemGroupData>();
					seq = 1;
					for (ResRec rec : operaRecList) {
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
						Map<String, Object> objMap = new HashMap<String, Object>();
						objMap.put("opera_seq", String.valueOf(seq++));
						if(formDataMap!=null){
							String disease_pName=rec.getItemName();
							if(GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(rec.getItemId()))
							{
								disease_pName= (String) formDataMap.get("operationName");
							}
							objMap.put("operation_Name", disease_pName);
							objMap.put("operation_id", formDataMap.get("id"));
							objMap.put("operation_patientName", formDataMap.get("patientName"));
							objMap.put("operation_hospitaNumber", formDataMap.get("hospitaNumber"));
							objMap.put("operation_admissionDate", formDataMap.get("admissionDate"));
							objMap.put("operation_mainDiagnosis", formDataMap.get("mainDiagnosis"));
							objMap.put("operation_operatorIdentity", formDataMap.get("operatorIdentity"));
						}else{
							String disease_pName=rec.getItemName();
							if(GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(rec.getItemId()))
							{
								disease_pName= "";
							}
							objMap.put("operation_Name", disease_pName);
							objMap.put("operation_id", "");
							objMap.put("operation_patientName", "");
							objMap.put("operation_hospitaNumber","");
							objMap.put("operation_admissionDate", "");
							objMap.put("operation_mainDiagnosis","");
							objMap.put("operation_operatorIdentity", "");
						}
						ItemGroupData igd = new ItemGroupData();
						igd.setObjMap(objMap);
						operaPlanTargetList.add(igd);
					}
					dataMap3.put("operaPlanTargetList", operaPlanTargetList);
				}
				WordprocessingMLPackage temeplete4 = new WordprocessingMLPackage();
				String path4 = "/jsp/hbres/daochu2/daochuTemeplete4.docx";//模板
				temeplete4 = Docx4jUtil.convert(new File(context.getRealPath(path4)), dataMap3, watermark, true);
				addTemplates.add(temeplete4);
			}
		}
		WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
		if(temeplete!=null){
			String name = "住院医师培训登记手册.docx";
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			//更新完后，设定cookie，用于页面判断更新完成后的标志
			Cookie status = new Cookie("updateStatus","success");
			status.setMaxAge(600);
			status.setPath("/");
			response.addCookie(status);//添加cookie操作必须在写出文件前，如果写在后面，随着数据量增大时cookie无法写入。
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}

	@RequestMapping(value="/admindaochu3")
	public void admindaochu3(String doctorFlow,HttpServletRequest request, HttpServletResponse response)throws Exception{
		List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String titleTop = StringUtil.defaultString(InitConfig.getSysCfg("res_admindaochu_top"));
		String titleButtom = StringUtil.defaultString(InitConfig.getSysCfg("res_admindaochu_buttom"));
		dataMap.put("titleTop", titleTop);
		dataMap.put("titleButtom", titleButtom);
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		SysUser user = userBiz.readSysUser(doctorFlow);
		if(doctor != null){
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
			if(pubUserResume != null){
				String xmlContent =  pubUserResume.getUserResume();
				if(StringUtil.isNotBlank(xmlContent)){
					//xml转换成JavaBean
					BaseUserResumeExtInfoForm userResumeExt=null;
					userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
					if(userResumeExt!=null){
						if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
							List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
							if(sysDictList!=null && !sysDictList.isEmpty()){
								for(SysDict dict:sysDictList){
									if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
										if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
											userResumeExt.setGraduatedName(dict.getDictName());
										}
									}
								}

							}
						}

						dataMap.put("graduationTime", userResumeExt.getGraduationTime());
						SysDict sysDict = new SysDict();
						String degreeCategoryName = "";
						sysDict.setDictTypeId(DictTypeEnum.UserDegree.getId());
						List<SysDict> dicts = dictBiz.searchDictList(sysDict);
						for(SysDict tempDict : dicts){
							if(tempDict.getDictId().equals(userResumeExt.getDegreeId())){
								degreeCategoryName = tempDict.getDictName();
								break;
							}
						}
						dataMap.put("degreeCategoryName", degreeCategoryName);
					}
				}
			}
			dataMap.put("trainingSpeName", StringUtil.isBlank(doctor.getTrainingTypeName())?doctor.getTrainingSpeName():doctor.getTrainingTypeName());
			dataMap.put("orgName", doctor.getOrgName());
			dataMap.put("doctorName", doctor.getDoctorName());
			dataMap.put("workOrgName", doctor.getWorkOrgName());
			String inHosDate = doctor.getInHosDate();
			String graduationYear = doctor.getGraduationYear();
			String docInHosDate = "";
			String outHosDate = graduationYear;
			String docOutHosDate = "";
			if(StringUtil.isNotBlank(inHosDate) && StringUtil.isNotBlank(doctor.getTrainingYears())){
				String[] tempDate = inHosDate.split("-");
				docInHosDate += tempDate[0] + "年";
				if(StringUtil.isBlank(outHosDate) && StringUtil.isNotBlank(doctor.getTrainingYears())){
					outHosDate = Integer.parseInt(tempDate[0].trim()) + Integer.parseInt(doctor.getTrainingYears().trim()) + "";
				}
				docInHosDate += tempDate[1] + "月";
				outHosDate += "-" + tempDate[1] + "-";
				docInHosDate += tempDate[2] + "日";
				outHosDate += tempDate[2];
				outHosDate = DateUtil.addDate(outHosDate,-1);
				String[] tempDate2 = outHosDate.split("-");
				docOutHosDate += tempDate2[0] + "年";
				docOutHosDate += tempDate2[1] + "月";
				docOutHosDate += tempDate2[2] + "日";
			}
			dataMap.put("inHosDate", docInHosDate);
			dataMap.put("outHosDate", docOutHosDate);
		}
		SchRotation rotation = schRotationBiz.readSchRotation(doctor.getRotationFlow());
		//第二套轮转方案
		SchRotation rotation2 = null;
		if(StringUtil.isNotBlank(doctor.getSecondRotationFlow()))
		{
			rotation2 = schRotationBiz.readSchRotation(doctor.getSecondRotationFlow());
		}
		if(rotation!=null) {
			String rotationFlow = rotation.getRotationFlow();
			//根据doctorFlow and rotaionFlow（轮转方案） 查询 SchArrangeResult（轮转计划）；
			List<SchArrangeResult> schArrangeResults = resultBiz.searchSchArrangeResultByDoctorAndRotationFlow(doctorFlow, rotationFlow);
			if(rotation2!=null){
				List<SchArrangeResult> schArrangeResults2 = resultBiz.searchSchArrangeResultByDoctorAndRotationFlow(doctorFlow, rotation2.getRotationFlow());
				schArrangeResults.addAll(schArrangeResults2);
			}
			if (schArrangeResults != null && schArrangeResults.size() > 0) {
				int seq = 1;
				List<ItemGroupData> yearPlanTargetList = new ArrayList<ItemGroupData>();
				for (SchArrangeResult tempResult : schArrangeResults) {
					//轮转科室和时间
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("seq", String.valueOf(seq++));
					objMap.put("standardDeptName", tempResult.getSchDeptName());
					objMap.put("startDate", tempResult.getSchStartDate());
					objMap.put("endDate", tempResult.getSchEndDate());
					objMap.put("schMonth", tempResult.getSchMonth());
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					yearPlanTargetList.add(igd);
				}
				dataMap.put("yearPlanTargetList", yearPlanTargetList);
			}

			WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
			String path1 = "/jsp/res/shouceDocx/daochuTemeplete1.docx";//模板
			ServletContext context = request.getServletContext();
			String watermark = "";
			temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap, watermark, true);
			addTemplates.add(temeplete1);

			String medicineTypeId=user.getMedicineTypeId();
			for (SchArrangeResult tempResult : schArrangeResults) {
				int seq = 1;
				String resultFlow = tempResult.getResultFlow();
				ResDoctorSchProcess process=processBiz.searchByResultFlow(resultFlow);
				String processFlow="";
				Map<String, Object> dataMap2 = new HashMap<String, Object>();
				Map<String, Object> dataMap3 = new HashMap<String, Object>();
				//科室名称模板
				dataMap2.put("standardDeptName", tempResult.getSchDeptName());
				dataMap2.put("startDate", tempResult.getSchStartDate());
				dataMap2.put("endDate", tempResult.getSchEndDate());
				String tempPath2 = "/jsp/res/shouceDocx/daochuTemeplete2.docx";
				WordprocessingMLPackage temeplete2 = new WordprocessingMLPackage();
				temeplete2 = Docx4jUtil.convert(new File(context.getRealPath(tempPath2)), dataMap2, watermark, true);
				addTemplates.add(temeplete2);
				String tempPath="";
					//获取轮转记录所在标准科室的类型
					SchRotationDept dept=rotationDeptBiz.readStandardRotationDept(resultFlow);
					String rotationFlow2=dept.getRotationFlow();
					String typeId =dept.getPracticOrTheory();
					String recordFlow =dept.getRecordFlow();
						if (JszyTCMPracticEnum.N.getId().equals(typeId)) {
							for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
								//根据后台配置显示的数据类型
								if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
									//查询resrec表数据
									List<ResRec> resRecList = null;
									if(process!=null) {
										processFlow = process.getProcessFlow();
										resRecList = resRecBiz.searchResRecWithBLOBs(regType.getId(), processFlow, doctorFlow);
									}
									//获取模版 取到值为 res/form/ChineseMedicine/hbszyysgfhpxglpt/after_1.0
									String path = resRecBiz.getFormPath(rotationFlow2, regType.getId(), "", "", "", medicineTypeId,recordFlow);
									String[] pathes = path.split("/");
									String path2 = pathes[2]+"/"+pathes[3]+"/"+pathes[4];
									String ver=pathes[4].split("_")[1];

									IrbSingleForm singleForm = findTheRecForm(pathes[3],regType.getId(),ver);
									seq=1;
									if (resRecList != null && !resRecList.isEmpty()&&singleForm!=null) {
										List<ItemGroupData> operaPlanTargetList = new ArrayList<ItemGroupData>();
										//具体再根据模版组装数据
										for (ResRec rec : resRecList) {
											Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
											Map<String, Object> objMap = new HashMap<String, Object>();
											objMap.put("case_seq", String.valueOf(seq++));
											if(formDataMap!=null){
												for(Element element:singleForm.getItemList())
												{
													String isExport=element.attributeValue("isExport");
													//注意类型区分 下拉 单选 多选
													String name=element.attributeValue("name");
//													if("Y".equals(isExport))
//													{
														objMap.put(name, formDataMap.get(name));
//													}
												}
												ItemGroupData igd = new ItemGroupData();
												igd.setObjMap(objMap);
												operaPlanTargetList.add(igd);
											}
										}
										dataMap3.put("operaPlanTargetList",operaPlanTargetList);
										//把path 转到模板的路径
										tempPath = "/jsp/res/shouceDocx/" + path2 + ".docx";
										WordprocessingMLPackage temeplete4 = new WordprocessingMLPackage();
										temeplete4 = Docx4jUtil.convert(new File(context.getRealPath(tempPath)), dataMap3, watermark, true);
										addTemplates.add(temeplete4);
									}
								}
							}
						} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(typeId)) {
							for (PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()) {
								//根据后台配置显示的数据类型
								if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + regType.getId()))) {
									//查询resrec表数据
									List<ResRec> resRecList = null;
									if(process!=null) {
										processFlow = process.getProcessFlow();
										resRecList = resRecBiz.searchResRecWithBLOBs(regType.getId(), processFlow, doctorFlow);
									}
									//获取模版 取到值为 res/form/ChineseMedicine/hbszyysgfhpxglpt/after_1.0
									String path = resRecBiz.getFormPath(rotationFlow2, regType.getId(), "", "", "", medicineTypeId,recordFlow);
									String[] pathes = path.split("/");
									String path2 = pathes[2]+"/"+pathes[3]+"/"+pathes[4];
									String ver=pathes[4].split("_")[1];

									IrbSingleForm singleForm = findTheRecForm(pathes[3],regType.getId(),ver);
									seq=1;
									if (resRecList != null && !resRecList.isEmpty()&&singleForm!=null) {
										List<ItemGroupData> operaPlanTargetList = new ArrayList<ItemGroupData>();
										//具体再根据模版组装数据
										for (ResRec rec : resRecList) {
											Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
											Map<String, Object> objMap = new HashMap<String, Object>();
											objMap.put("case_seq", String.valueOf(seq++));
											if(formDataMap!=null){
												for(Element element:singleForm.getItemList())
												{
													String isExport=element.attributeValue("isExport");
													//注意类型区分 下拉 单选 多选
													String name=element.attributeValue("name");
//													if("Y".equals(isExport))
//													{
														objMap.put(name, formDataMap.get(name));
//													}
												}
												ItemGroupData igd = new ItemGroupData();
												igd.setObjMap(objMap);
												operaPlanTargetList.add(igd);
											}
										}
										dataMap3.put("operaPlanTargetList",operaPlanTargetList);
										//把path 转到模板的路径
										tempPath = "/jsp/res/shouceDocx/" + path2 + ".docx";
										WordprocessingMLPackage temeplete4 = new WordprocessingMLPackage();
										temeplete4 = Docx4jUtil.convert(new File(context.getRealPath(tempPath)), dataMap3, watermark, true);
										addTemplates.add(temeplete4);
									}
								}
							}
						} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(typeId)) {
							for (TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()) {
								//根据后台配置显示的数据类型
								if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + regType.getId()))) {
									//查询resrec表数据
									List<ResRec> resRecList = null;
									if(process!=null) {
										processFlow = process.getProcessFlow();
										resRecList = resRecBiz.searchResRecWithBLOBs(regType.getId(), processFlow, doctorFlow);
									}
									//获取模版 取到值为 res/form/ChineseMedicine/hbszyysgfhpxglpt/after_1.0
									String path = resRecBiz.getFormPath(rotationFlow2, regType.getId(), "", "", "", medicineTypeId,recordFlow);
									String[] pathes = path.split("/");
									String path2 = pathes[2]+"/"+pathes[3]+"/"+pathes[4];
									String ver=pathes[4].split("_")[1];

									IrbSingleForm singleForm = findTheRecForm(pathes[3],regType.getId(),ver);
									seq=1;
									if (resRecList != null && !resRecList.isEmpty()&&singleForm!=null) {
										List<ItemGroupData> operaPlanTargetList = new ArrayList<ItemGroupData>();
										//具体再根据模版组装数据
										for (ResRec rec : resRecList) {
											Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
											Map<String, Object> objMap = new HashMap<String, Object>();
											objMap.put("case_seq", String.valueOf(seq++));
											if(formDataMap!=null){
												for(Element element:singleForm.getItemList())
												{
													String isExport=element.attributeValue("isExport");
													//注意类型区分 下拉 单选 多选
													String name=element.attributeValue("name");
//													if("Y".equals(isExport))
//													{
														objMap.put(name, formDataMap.get(name));
//													}
												}
												ItemGroupData igd = new ItemGroupData();
												igd.setObjMap(objMap);
												operaPlanTargetList.add(igd);
											}
										}
										dataMap3.put("operaPlanTargetList",operaPlanTargetList);
										//把path 转到模板的路径
										tempPath = "/jsp/res/shouceDocx/" + path2 + ".docx";
										WordprocessingMLPackage temeplete4 = new WordprocessingMLPackage();
										temeplete4 = Docx4jUtil.convert(new File(context.getRealPath(tempPath)), dataMap3, watermark, true);
										addTemplates.add(temeplete4);
									}
								}
							}
						}
			}
		}
		WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
		if(temeplete!=null){
			String name = "住院医师培训登记手册.docx";
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			//更新完后，设定cookie，用于页面判断更新完成后的标志
			Cookie status = new Cookie("updateStatus","success");
			status.setMaxAge(600);
			status.setPath("/");
			response.addCookie(status);//添加cookie操作必须在写出文件前，如果写在后面，随着数据量增大时cookie无法写入。
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}

	private IrbSingleForm findTheRecForm(String recForm, String recType, String recVer) {

		Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(recType);
		if(singleFormMap!=null){
			IrbSingleForm singleForm = singleFormMap.get(recForm+"_"+recVer);
			return singleForm;
		}
		return null;
	}

	public SchRotationDept readStandardRotationDept(String resultFlow){
		SchRotationDept rotationDept = null;
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
			if(result!=null){
				String rotationFlow = result.getRotationFlow();
				String standardDeptId = result.getStandardDeptId();
				SchRotationGroup group = groupBiz.readSchRotationGroup(result.getStandardGroupFlow());
				if(group!=null){
					if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)){
						SchRotationDeptExample example = new SchRotationDeptExample();
						SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
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

	/**
	 * 湖北结业管理系统 住院医师主界面
	 */
	@RequestMapping(value="/graduate/doctor")
	public String graduateIndex(Model model){
		return "hbres/doctor/jyHome";
	}

	/**
	 * 成绩查询
	 * @return
	 */
	@RequestMapping(value="/owenScore")
	public String owenScore(Model model,String hideApprove,String doctorFlow,String roleFlag) throws DocumentException {
		SysUser currUser = GlobalContext.getCurrentUser();
		String operUserFlow = currUser.getUserFlow();
		List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(operUserFlow);
		//理论成绩
		ResScore theoryScore=new ResScore();
		//技能成绩
		ResScore skillScore=new ResScore();
		//公共成绩
		ResScore publicScore=new ResScore();
		if(null!=scorelist&&scorelist.size()>0){
			int theoryYear=0;
			int skillYear=0;
			for(ResScore resScore:scorelist){
				int socreYear=Integer.valueOf(resScore.getScorePhaseId()==null? "-1":resScore.getScorePhaseId());
				if(ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())) {
					if(StringUtil.isNotBlank(String.valueOf(socreYear))) {
						if(socreYear>theoryYear) {
							theoryYear=socreYear;
							theoryScore=resScore;
						}
					}
				}
				if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())) {
					if(StringUtil.isNotBlank(String.valueOf(socreYear))) {
						if(socreYear>skillYear) {
							skillYear=socreYear;
							skillScore=resScore;
						}
					}
				}
				if(ResScoreTypeEnum.PublicScore.getId().equals(resScore.getScoreTypeId())) {
					publicScore=resScore;
				}
			}
		}
		//当前年份
		model.addAttribute("thisYear",DateUtil.getYear());
		model.addAttribute("theoryScore",theoryScore);
		model.addAttribute("skillScore",skillScore);
		model.addAttribute("publicScore",publicScore);
		//技能成绩
		List<ResScore> skillList=new ArrayList<ResScore>();
		List<ResScore> theoryList=new ArrayList<ResScore>();
		if(null!=scorelist&&scorelist.size()>0) {
			for(ResScore resScore:scorelist) {
				if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())) {
					//只取最新年份成绩
					if(skillList.size() == 0 ){
						skillList.add(resScore);
					}else{
						int oldYear = Integer.parseInt(skillList.get(0).getScorePhaseId());
						int newYear =  Integer.parseInt(resScore.getScorePhaseId());
						if(newYear > oldYear){
							skillList.remove(0);
							skillList.add(resScore);
						}
					}

				}else if(ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())) {
					//只取最新年份成绩
					if(theoryList.size() == 0 ){
						theoryList.add(resScore);
					}else{
						int oldYear = Integer.parseInt(theoryList.get(0).getScorePhaseId());
						int newYear =  Integer.parseInt(resScore.getScorePhaseId());
						if(newYear > oldYear){
							theoryList.remove(0);
							theoryList.add(resScore);
						}
					}
				}
			}
		}
		//所有技能科目详情
		Map<String,Map<String,String>> skillExtScoreMap=new HashMap<String,Map<String,String>>();
		for(int i=0;i<skillList.size();i++) {
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
			skillExtScoreMap.put(resScore.getScoreFlow(),extScore);
		}
		model.addAttribute("skillList",skillList);
		model.addAttribute("theoryList",theoryList);
		model.addAttribute("skillExtScoreMap",skillExtScoreMap);
		//公共科目成绩详情
		String content = null==publicScore ? "":publicScore.getExtScore();
		Map<String,String> extScoreMap=new HashMap<String,String>();
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
						extScoreMap.put(attrName, attrValue);
					}
				}
			}
		}
		model.addAttribute("extScore",extScoreMap);
		//个人信息
		ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(operUserFlow);
		SysUser sysUser=userBiz.readSysUser(operUserFlow);
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
		if(pubUserResume != null){
			String xmlContent =  pubUserResume.getUserResume();
			if(StringUtil.isNotBlank(xmlContent)){
				//xml转换成JavaBean
				BaseUserResumeExtInfoForm userResumeExt=null;
				userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
				if(userResumeExt!=null){
					if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
						List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
						if(sysDictList!=null && !sysDictList.isEmpty()){
							for(SysDict dict:sysDictList){
								if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
									if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
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
		ResDoctorRecruit recruit =new ResDoctorRecruit();
		recruit.setDoctorFlow(operUserFlow);
		recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		recruit.setAuditStatusId(ResDoctorAuditStatusEnum.Passed.getId());
		List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		ResDoctorRecruit resDoctorRecruit=null;
		if(recruitList != null && !recruitList.isEmpty()){
			resDoctorRecruit=recruitList.get(0);
			model.addAttribute("recruitList", recruitList);
			model.addAttribute("doctorRecruit",recruitList.get(0));
		}
		//保存医师培训时间
		if(resDoctorRecruit!=null) {
			String endTime="";String startTime="";
			//开始时间
			String recruitDate=resDoctorRecruit.getRecruitDate();
			//培养年限
			String trianYear=resDoctorRecruit.getTrainYear();
			String graudationYear=resDoctorRecruit.getGraduationYear();
			if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(graudationYear)) {
				try {
					int year=0;
					year=Integer.valueOf(graudationYear)-Integer.valueOf(recruitDate.substring(0,4));
					if(year!=0) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(recruitDate);
						startTime = recruitDate;
						//然后使用Calendar操作日期
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
						calendar.add(Calendar.DATE, -1);
						//把得到的日期格式化成字符串类型的时间
						endTime = sdf.format(calendar.getTime());
					}
				} catch (Exception e) {
					endTime="";
				}
			}
			//如果没有结业考核年份，按照届别与培养年限计算
			if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(trianYear)&&StringUtil.isBlank(endTime)) {
				int year=0;
				if(trianYear.equals(JsResTrainYearEnum.OneYear.getId())) {
					year=1;
				}
				if(trianYear.equals(JsResTrainYearEnum.TwoYear.getId())) {
					year=2;
				}
				if(trianYear.equals(JsResTrainYearEnum.ThreeYear.getId())) {
					year=3;
				}
				if(year!=0) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(recruitDate);
						startTime = recruitDate;
						//然后使用Calendar操作日期
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
						calendar.add(Calendar.DATE,-1);

						//把得到的日期格式化成字符串类型的时间
						endTime = sdf.format(calendar.getTime());
					} catch (Exception e) {}
				}
			}
			if (!startTime.equals(resDoctor.getCompleteStartDate()) || !endTime.equals(resDoctor.getCompleteEndDate())) {
				resDoctor.setCompleteStartDate(startTime);
				resDoctor.setCompleteEndDate(endTime);
				resDoctorBiz.editDoctor(resDoctor);
			}
		}
		model.addAttribute("resDoctor",resDoctor);
		model.addAttribute("user",sysUser);
		return "hbres/graduate/owenScore";
	}
	@RequestMapping(value="/doctorSubmit")
	@ResponseBody
	public String doctorSubmit(String doctorFlow) throws DocumentException {

		if(doctorFlow==null){
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		ResDoctor resDoctor=resDoctorBiz.findByFlow(doctorFlow);
		if(null==resDoctor)
		{
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		resDoctor.setGraduationStatusId(CertificateStatusEnum.Submit.getId());
		resDoctor.setGraduationStatusName(CertificateStatusEnum.Submit.getName());
		resDoctor.setDisagreeReason("");
		int result = resDoctorBiz.editDoctor(resDoctor);
		if(GlobalConstant.ZERO_LINE==result){
			return GlobalConstant.OPRE_FAIL_FLAG;
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
}
