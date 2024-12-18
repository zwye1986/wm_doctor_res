package com.pinde.sci.ctrl.inx;


import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserSexEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.OperTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.osca.IOscaBaseBiz;
import com.pinde.sci.biz.osca.IOscaDoctorRegistBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inx/osce")
public class InxOscaController extends GeneralController{

    private static Logger logger = LoggerFactory.getLogger(InxOscaController.class);


	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private ILoginBiz loginBiz;
	@Autowired
	private IOscaBaseBiz oscaBaseBiz;
	@Autowired
	private IInxBiz inxBiz;
	@Autowired
	private IOscaDoctorRegistBiz oscaDoctorRegistBiz;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(){
		return "inx/osce/index";
	}
	@RequestMapping(value="logout")
	public String logout( HttpServletRequest request){
        String wsId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		request.getSession().invalidate();
        GlobalContext.getSession().setAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID, wsId);
		return "inx/osce/index";
	}
	@RequestMapping(value="initNotice")
	public String initNotice(Model model){
		InxInfo info = new InxInfo();
		info.setColumnId("jinengkaohe");
		PageHelper.startPage(1,2);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "inx/osce/notice";
	}

	@RequestMapping(value="/noticeView")
	public String message(Model model,String infoFlow) throws Exception{
		model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
		return "inx/osce/message";
	}
	@RequestMapping("/noticelist")
	public String noticeList(Integer currentPage , Model model){
		InxInfo info = new InxInfo();
		info.setColumnId("jinengkaohe");
		PageHelper.startPage(currentPage,10);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "inx/osce/morenotice";
	}
	@RequestMapping(value="/allNotice",method={RequestMethod.GET})
	public String allNotice(Integer currentPage ,Model model,String flag){
		InxInfo info = new InxInfo();
		info.setColumnId("jinengkaohe");
		PageHelper.startPage(currentPage,10);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		if(StringUtil.isBlank(flag)){
			return "/inx/osce/morenotice";
		}else{
			return "/inx/osce/message";
		}
	}
	//登录
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, String userCode, String userPasswd, String verifyCode, String successLoginPage, String errorLoginPage) {
		//默认登录失败界面
		if(StringUtil.isBlank(errorLoginPage)){
			errorLoginPage = "/inx/osce/index";
		}
		//默认登录成功界面
		if(StringUtil.isBlank(successLoginPage)){
			successLoginPage = "redirect:/main?time=" + new Date().getTime();
		}
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
		//验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("validateCode.notEquals"));
			removeValidateCodeAttribute();
			return errorLoginPage;
		}
		removeValidateCodeAttribute();
		//登录码不能为空
		if (StringUtil.isBlank(userCode)){
			model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.isNull"));
			return errorLoginPage;
		}
		//密码不能为空
		if (StringUtil.isBlank(userPasswd)){
			model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userPasswd.isNull"));
			return errorLoginPage;
		}
		//查是否存在此用户
		SysUser user = userBiz.findByUserCode(userCode);
		if(user==null){
			user = userBiz.findByUserPhone(userCode);
		}
		if(user==null){
			user = userBiz.findByUserEmail(userCode);
		}
		if(user==null){
			user = userBiz.findByIdNo(userCode);
		}
		if(user==null){
            if (!com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(userCode)) {
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.notFound"));
				return errorLoginPage;
			}else{
				SysOrg org = new SysOrg();
                org.setOrgFlow(com.pinde.core.common.GlobalConstant.PD_ORG_FLOW);
                org.setOrgCode(com.pinde.core.common.GlobalConstant.PD_ORG_CODE);
                org.setOrgName(com.pinde.core.common.GlobalConstant.PD_ORG_NAME);
                org.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				try{
					sysOrgBiz.addOrg(org);
				}catch(Exception e){
                    logger.error("", e);
				}
				user = new SysUser();
                user.setUserFlow(com.pinde.core.common.GlobalConstant.ROOT_USER_FLOW);
				user.setUserCode(userCode);
                user.setUserName(com.pinde.core.common.GlobalConstant.ROOT_USER_NAME);
                user.setOrgFlow(com.pinde.core.common.GlobalConstant.PD_ORG_FLOW);
                user.setOrgName(com.pinde.core.common.GlobalConstant.PD_ORG_NAME);
				user.setStatusId(UserStatusEnum.Activated.getId());
				user.setStatusDesc(UserStatusEnum.Activated.getName());
				userBiz.addUser(user);
			}
		}
		if (!InitPasswordUtil.isRootPass(userPasswd)) {
			//判断密码
			String passwd = StringUtil.defaultString(user.getUserPasswd());
			if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userPasswd.error"));
				return errorLoginPage;
			}
		}
		if (StringUtil.isNotBlank(InitConfig.weekPasswordMap.get(userPasswd))) {
			model.addAttribute("userCode",userCode);
			return "/inx/osce/setStrongPasswd";
		}
		//root用户不判断是否锁定
        if (!com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {

			if(UserStatusEnum.Locked.getId().equals(user.getStatusId())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.locked"));
				return errorLoginPage;
			}
			if(UserStatusEnum.Reged.getId().equals(user.getStatusId())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.unActivated"));
				return errorLoginPage;
			}
			if(UserStatusEnum.Added.getId().equals(user.getStatusId())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.unReged"));
				return errorLoginPage;
			}
			//如果是注册未审核通过的学员，进入个人信息填写页面
			if("OSCE_NEW".equals(user.getStatusId())){
                setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
				model.addAttribute("user",user);
				ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
				model.addAttribute("doctor",doctor);
				OscaDoctorRegist search = new OscaDoctorRegist();
				search.setDoctorFlow(user.getUserFlow());
				List<OscaDoctorRegist> regists = oscaDoctorRegistBiz.searchRegist(search);
				if(regists!=null&&regists.size()>0){
					model.addAttribute("regist",regists.get(0));
				}
				SysOrg searchOrg = new SysOrg();
				searchOrg.setOrgTypeId("Hospital");
				List<SysOrg> orgList = sysOrgBiz.queryAllSysOrg(searchOrg);
				model.addAttribute("orgList",orgList);
				return "inx/osce/completeInfo";
			}
			if(!UserStatusEnum.Activated.getId().equals(user.getStatusId())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.unActivated"));
				return errorLoginPage;
			}
		}

		//唯一登录
        if (!com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("unique_login_flag"))) {
			if (SessionData.sessionDataMap.containsKey(user.getUserFlow()) &&
					!SessionData.sessionDataMap.get(user.getUserFlow()).getIp().equals(request.getRemoteAddr())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("user.alreadyLogin"));
				return errorLoginPage;
			}
		}
		//设置当前用户
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER_NAME, user.getUserName());
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ORG, sysOrgBiz.readSysOrg(user.getOrgFlow()));
		//设置当前用户部门列表
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(user));
		//招录学员绑定临床技能考核学员角色flow
		oscaBaseBiz.bindDoctorRole(user.getUserFlow());
		//加载系统权限
		loginBiz.loadSysRoleOsce(user.getUserFlow());
        if (com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			return successLoginPage;
		}
        List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
		if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() > 0) {
			//在线用户功能使用
			SessionData sessionData = new SessionData();
			sessionData.setSysUser(user);
			sessionData.setIp(request.getRemoteAddr());
			long now = System.currentTimeMillis();
			String loginTime = new java.sql.Date(now)+"&nbsp;"+new Time(now);
			sessionData.setLoginTime(loginTime);
			setSessionAttribute(SessionData.SESSIONDATAID,sessionData);
			//记录日志
			SysLog log = new SysLog();
			log.setOperId(OperTypeEnum.LogIn.getId());
			log.setOperName(OperTypeEnum.LogIn.getName());
			log.setLogDesc("登录IP["+request.getRemoteAddr()+"]");
            log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			loginBiz.addSysLog(log);
			return successLoginPage;
		}else{
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
			return errorLoginPage;
		}
	}

	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("/saveRegister")
	public String saveRegister(SysUser registerUser , String verifyCode, Model model){
		//注册信息校验
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));

		String errorMsg = "";
		if (StringUtil.isBlank(verifyCode) || StringUtil.isBlank(sessionValidateCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			errorMsg = SpringUtil.getMessage("validateCode.notEquals");
			model.addAttribute("errorMsg", errorMsg);
			return "inx/osce/register";
		}
		//是否已注册
		String userEmail = registerUser.getUserEmail().trim();
		SysUser user = userBiz.findByUserEmail(userEmail);
		if(user != null){
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE);
			return "inx/osce/register";
		}
		user = userBiz.findByUserCode(userEmail);
		if(user != null){
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE);
			return "inx/osce/register";
		}
		//判断用户身份证号是否重复
		user = userBiz.findByIdNo(registerUser.getIdNo());
		if (user != null) {
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE);
			return "inx/osce/register";
		}
		//判断用户手机号是否重复
		user = userBiz.findByUserPhone(registerUser.getUserPhone());
		if (user != null) {
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE);
			return "inx/osce/register";
		}
		this.inxBiz.saveOsceRegistUser(registerUser);
		model.addAttribute("activationCode", registerUser.getUserFlow());
		return "inx/osce/sendEmail";
	}

	/**
	 * 完善信息
	 * @return
	 */
	@RequestMapping("/complete")
	@ResponseBody
	public String complete(SysUser registerUser, ResDoctor doctor, Model model){
		SysOrg searchOrg = new SysOrg();
		searchOrg.setOrgTypeId("Hospital");
		List<SysOrg> orgList = sysOrgBiz.queryAllSysOrg(searchOrg);
		model.addAttribute("orgList",orgList);
		String userFlow = registerUser.getUserFlow();
		//是否已注册
		String userEmail = registerUser.getUserEmail().trim();
		SysUser user = userBiz.findByUserEmailNotSelf(userFlow,userEmail);
		if(user != null){
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE);
			return "inx/osce/completeInfo";
		}
		//判断用户身份证号是否重复
		user = userBiz.findByIdNoNotSelf(userFlow,registerUser.getIdNo());
		if (user != null) {
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE);
			return "inx/osce/completeInfo";
		}
		//判断用户手机号是否重复
		user = userBiz.findByUserPhoneNotSelf(userFlow,registerUser.getUserPhone());
		if (user != null) {
            model.addAttribute("errorMsg", com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE);
			return "inx/osce/completeInfo";
		}
		String sexName = UserSexEnum.getNameById(registerUser.getSexId());
		registerUser.setSexName(sexName);
		this.inxBiz.completeOsceRegistUser(registerUser,doctor);
		SysUser sysUser = userBiz.readSysUser(userFlow);
		model.addAttribute("user",sysUser);
		ResDoctor resDoctor = doctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor",resDoctor);
		OscaDoctorRegist search = new OscaDoctorRegist();
		search.setDoctorFlow(userFlow);
		List<OscaDoctorRegist> regists = oscaDoctorRegistBiz.searchRegist(search);
		if(regists!=null&&regists.size()>0){
			model.addAttribute("regist",regists.get(0));
		}
		return "1";
	}
}
