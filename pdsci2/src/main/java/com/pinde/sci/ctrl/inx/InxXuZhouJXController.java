package com.pinde.sci.ctrl.inx;


import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.dwjxres.StuRoleEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inx/xzjx")
public class InxXuZhouJXController extends GeneralController{
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IInxBiz inxBiz;
	@Autowired
	private ILoginBiz loginBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IRoleBiz roleBiz;

	/**
	 * 显示登陆界面
	 * @return
	 */
	@RequestMapping(value = "", method = {RequestMethod.GET})
	public String showLogin(Model model){
		return "inx/xzjx/login";
	}

	/**
	 * 登陆
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String userCode, String userPasswd,String verifyCode , Model model , HttpServletRequest request){
		//验证码输入不能为空，不区分大小写
		String loginErrorMessage = "";
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			loginErrorMessage = "验证码不正确";
			model.addAttribute("loginErrorMessage" , loginErrorMessage);
			removeValidateCodeAttribute();
			return "inx/xzjx/login";
		}
		removeValidateCodeAttribute();
		try{
			SysUser user = inxBiz.login(userCode, userPasswd);
			//设置当前用户
			setSessionAttribute(GlobalConstant.CURRENT_USER, user);
			if(GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
				return "redirect:/main?time="+new Date();
			}

			//在线用户功能使用
			SessionData sessionData = new SessionData();
			sessionData.setSysUser(user);
			sessionData.setIp(request.getRemoteAddr());
			long now = System.currentTimeMillis();
			String loginTime = new java.sql.Date(now)+"&nbsp;"+new java.sql.Time(now);
			sessionData.setLoginTime(loginTime);
			setSessionAttribute(SessionData.SESSIONDATAID,sessionData);

			//记录日志
			SysLog log = new SysLog();
			log.setOperId(OperTypeEnum.LogIn.getId());
			log.setOperName(OperTypeEnum.LogIn.getName());
			log.setLogDesc("登录IP[" + request.getRemoteAddr() + "]");
			log.setWsId(GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			logMapper.insert(log);

			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
			userRole.setWsId(GlobalConstant.RES_WS_ID);
			List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
			if(!userRoleList.isEmpty()){
				userRole = userRoleList.get(0);
			}
			SysRole role = roleBiz.read(userRole.getRoleFlow());
			if(role!=null){
				return "redirect:"+getRoleUrl(role.getRoleFlow());
			}else{
				if(StringUtil.isNotBlank(user.getIsOrgAdmin()) && user.getIsOrgAdmin().equals(StuRoleEnum.Secretary.getId()))
				{
					return "redirect:/dwjxres/secretaries/home";
				}
				if(StringUtil.isNotBlank(user.getIsOrgAdmin()) && user.getIsOrgAdmin().equals(StuRoleEnum.Teacher.getId()))
				{
					return "redirect:/dwjxres/teacher/home";
				}
			}
			loginErrorMessage = "未赋权";
		}catch(RuntimeException re){
			loginErrorMessage = re.getMessage();
		}
		model.addAttribute("loginErrorMessage" , loginErrorMessage);
		return "inx/xzjx/login";
	}

	public String getRoleUrl(String roleFlow){
		if (StringUtil.isNotBlank(roleFlow)){
			if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
				return "/dwjxres/hospital/home";
//			} else if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//系统管理员即省级管理员
//				return "/dwjxres/manage/home";
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				return "/dwjxres/doctor/home";
			}
		}
		return "";
	}

	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("/regist")
	public String regist(SysUser registerUser , String verifyCode, Model model){
		//注册信息校验
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));

		String errorMsg = "";
		if (StringUtil.isBlank(verifyCode) || StringUtil.isBlank(sessionValidateCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			errorMsg = SpringUtil.getMessage("validateCode.notEquals");
			model.addAttribute("errorMsg" , errorMsg);
			return "inx/xzjx/register";
		}
//		//是否已注册
//		String userEmail = registerUser.getUserEmail();
//		SysUser user = userBiz.findByUserEmail(userEmail.trim());
//		if(user != null){
//			model.addAttribute("errorMsg" , GlobalConstant.USER_EMAIL_REPETE);
//			return "inx/xzjx/register";
//		}
//		user = userBiz.findByUserCode(userEmail.trim());
//		if(user != null){
//			model.addAttribute("errorMsg" , GlobalConstant.USER_EMAIL_REPETE);
//			return "inx/xzjx/register";
//		}
		String idNo=registerUser.getIdNo();
		SysUser user = userBiz.findByIdNo(idNo.trim());
		if(user != null){
			return GlobalConstant.USER_ID_NO_REPETE;
		}
		user = userBiz.findByUserCode(idNo.trim());
		if(user != null){
			return GlobalConstant.USER_ID_NO_REPETE;
		}
		registerUser.setIsOwnerStu("P");
		this.inxBiz.saveJXRegistUser(registerUser);
		model.addAttribute("activationCode" , registerUser.getUserFlow());
//		model.addAttribute("userEmail",userEmail);
		return "redirect:/inx/dwjxres/sendEmail";
	}
}
