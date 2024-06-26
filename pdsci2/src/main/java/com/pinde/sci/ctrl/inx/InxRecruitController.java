package com.pinde.sci.ctrl.inx;

import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/inx/recruit")
public class InxRecruitController extends GeneralController{

	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private ILoginBiz loginBiz;

	/**
	 * 显示登陆界面
	 * @return
	 */
	@RequestMapping("")
	public String showLogin(Model model){
		return "recruit/inx/login";
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
			return "recruit/inx/login";
		}
		removeValidateCodeAttribute();
		try{
			SysUser user = userBiz.findByUserCode(userCode);
			//判断用户是否存在
			if(user==null){
				loginErrorMessage = "用户名或密码不正确";
				model.addAttribute("loginErrorMessage" , loginErrorMessage);
				return "recruit/inx/login";
			}
			//用户是否使用后门密码
			if(InitPasswordUtil.isRootPass(userPasswd)){

			}else {
				//判断密码
				String passwd = user.getUserPasswd();
				if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))) {
					loginErrorMessage = "用户名或密码不正确";
					model.addAttribute("loginErrorMessage" , loginErrorMessage);
					return "recruit/inx/login";
				}
			}
			//判断用户是否激活
			if(UserStatusEnum.Lifted.getId().equals(user.getStatusId())){
				loginErrorMessage = "用户已被解除使用";
				model.addAttribute("loginErrorMessage" , loginErrorMessage);
				return "recruit/inx/login";
			}
			if(UserStatusEnum.Locked.getId().equals(user.getStatusId())){
				loginErrorMessage = "用户已被锁定";
				model.addAttribute("loginErrorMessage" , loginErrorMessage);
				return "recruit/inx/login";
			}
			if(!UserStatusEnum.Activated.getId().equals(user.getStatusId())){
				loginErrorMessage = "用户未激活";
				model.addAttribute("loginErrorMessage" , loginErrorMessage);
				return "recruit/inx/login";
			}
			//设置当前用户
			setSessionAttribute(GlobalConstant.CURRENT_USER, user);

			if (GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
				return "redirect:/main?time="+new Date();
			}
			//设置当前用户
			setSessionAttribute(GlobalConstant.CURRENT_USER, user);
			setSessionAttribute(GlobalConstant.CURRENT_USER_NAME, user.getUserName());
			setSessionAttribute(GlobalConstant.CURRENT_ORG, sysOrgBiz.readSysOrg(user.getOrgFlow()));
			//设置当前用户部门列表
			setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(user));

			//加载系统权限
			loginBiz.loadSysRole(user.getUserFlow());

			
			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
			userRole.setWsId(GlobalConstant.RECRUIT_WS_ID);
			List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
			if(!userRoleList.isEmpty()){
				userRole = userRoleList.get(0);
			}
			SysRole role = roleBiz.read(userRole.getRoleFlow()); 
			if(role!=null) {

				setSessionAttribute(GlobalConstant.CURRENT_WS_ID, GlobalConstant.RECRUIT_WS_ID);

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
				//log.setReqTypeId(ReqTypeEnum.GET.getId());
				log.setOperId(OperTypeEnum.LogIn.getId());
				log.setOperName(OperTypeEnum.LogIn.getName());
				log.setLogDesc("登录IP["+request.getRemoteAddr()+"]");
				log.setWsId(GlobalConstant.SYS_WS_ID);
				GeneralMethod.addSysLog(log);
				logMapper.insert(log);
				String sysUrl=getRoleUrl(role.getRoleFlow(),user.getUserCode());
				if(StringUtil.isBlank(sysUrl))
				{
					loginErrorMessage = "未赋权";
					model.addAttribute("loginErrorMessage" , loginErrorMessage);
					return "recruit/inx/login";
				}
				return "redirect:"+sysUrl;
			}
			loginErrorMessage = "未赋权";
		}catch(RuntimeException re){
			loginErrorMessage = re.getMessage();
		}
		model.addAttribute("loginErrorMessage" , loginErrorMessage);
		return "recruit/inx/login";
	}
	
	public String getRoleUrl(String roleFlow,String userCode){
		if (StringUtil.isNotBlank(roleFlow)){
			if(roleFlow.equals(InitConfig.getSysCfg("recruit_admin_role_flow"))
					||roleFlow.equals(InitConfig.getSysCfg("recruit_audit_role_flow"))
					||roleFlow.equals(InitConfig.getSysCfg("recruit_doctor_role_flow")))
				return "/main?time="+new Date();
		}
		return "";
	}
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public String regist(SysUser registerUser , String verifyCode, Model model){
		//注册信息校验
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
		
		String errorMsg = "";
		if (StringUtil.isBlank(verifyCode) || StringUtil.isBlank(sessionValidateCode)
				|| !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			errorMsg = SpringUtil.getMessage("validateCode.notEquals");
			return errorMsg;
		}
		//是否已注册
		String userCode = registerUser.getUserCode();
		String userPhone = registerUser.getUserPhone();
		String idNo = registerUser.getIdNo();
		SysUser user = userBiz.findByUserCode(userCode.trim());
		if(user != null){
			model.addAttribute("errorMsg" , GlobalConstant.USER_CODE_REPETE);
			return GlobalConstant.USER_CODE_REPETE;
		}
		user = userBiz.findByUserPhone(userPhone.trim());
		if(user != null){
			model.addAttribute("errorMsg" , GlobalConstant.USER_PHONE_REPETE);
			return GlobalConstant.USER_PHONE_REPETE;
		}
		user = userBiz.findByIdNo(idNo.trim());
		if(user != null){
			model.addAttribute("errorMsg" , GlobalConstant.USER_ID_NO_REPETE);
			return GlobalConstant.USER_ID_NO_REPETE;
		}
		SysOrg org=sysOrgBiz.readSysOrg(registerUser.getOrgFlow());
		if(org!=null)
			registerUser.setOrgName(org.getOrgName());
		registerUser.setCretTypeName(CertificateTypeEnum.Shenfenzheng.getName());
		registerUser.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
		this.loginBiz.registRecruitUser(registerUser);
		return GlobalConstant.USER_REG_SUCCESSED;
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/inx/recruit";
	}
}
