package com.pinde.sci.ctrl.inx;


import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.OperTypeEnum;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.model.mo.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inx/lcjn")
public class InxLcjnController extends GeneralController{

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private ILoginBiz loginBiz;
	@Autowired
	private INoticeBiz noticeBiz;

	//临床技能培训中心管理系统登录页
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(){
		return "inx/lcjn/index";
	}
	@RequestMapping(value="logout")
	public String logout( HttpServletRequest request){
        String wsId = (String) GlobalContext.getSession().getAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		request.getSession().invalidate();
        GlobalContext.getSession().setAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID, wsId);
		return "inx/osce/index";
	}

	/**
	 * 登录动作
	 * @param request
	 * @param model
	 * @param userCode 用户名
	 * @param userPasswd 密码
	 * @param verifyCode 验证码
	 * @return
	 */

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, String userCode, String userPasswd, String verifyCode, String successLoginPage, String errorLoginPage) {

		//默认登录失败界面
		if(StringUtil.isBlank(errorLoginPage)){
			errorLoginPage = "/inx/lcjn/index";
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
			}else{//初次用root超级密码登录时，新增root用户和组织机构表
				SysOrg org = new SysOrg();
                org.setOrgFlow(com.pinde.core.common.GlobalConstant.PD_ORG_FLOW);
                org.setOrgCode(com.pinde.core.common.GlobalConstant.PD_ORG_CODE);
                org.setOrgName(com.pinde.core.common.GlobalConstant.PD_ORG_NAME);
                org.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				sysOrgBiz.addOrg(org);
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
		//root用户不判断是否激活
        if (!com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			if(!UserStatusEnum.Activated.getId().equals(user.getStatusId())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.unActivated"));
				return errorLoginPage;
			}
		}
		//超级密码
		if (!InitPasswordUtil.isRootPass(userPasswd)) {
			//判断密码
			String passwd = StringUtil.defaultString(user.getUserPasswd());
			if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userPasswd.error"));
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
		//加载系统权限
		loginBiz.loadSysRole(user.getUserFlow());
        if (com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			return successLoginPage;
		}
		//一个用户可以配置多个工作站
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
}
