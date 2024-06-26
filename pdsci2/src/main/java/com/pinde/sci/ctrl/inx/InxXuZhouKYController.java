package com.pinde.sci.ctrl.inx;


import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
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
@RequestMapping("/inx/xzky")
public class InxXuZhouKYController extends GeneralController{
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

	/**
	 * 显示登陆界面
	 * @return
	 */
	@RequestMapping(value = "", method = {RequestMethod.GET})
	public String showLogin(Model model){
		return "inx/xzky/login";
	}

	/**
	 * 登陆
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String successLoginPage, String errorLoginPage, String userCode, String userPasswd,
						String verifyCode, Model model, HttpServletRequest request){
		//默认登录失败界面
		if (StringUtil.isBlank(errorLoginPage)) {
			errorLoginPage = "inx/xzky/login";
		}
		//默认登录成功界面
		if (StringUtil.isBlank(successLoginPage)) {
			successLoginPage = "redirect:/main?time=" + new Date();
		}

		String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));
		//验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("validateCode.notEquals"));
			//登录日志
			removeValidateCodeAttribute();
			return errorLoginPage;
		}
		removeValidateCodeAttribute();
		//登录名不能为空
		if (StringUtil.isBlank(userCode)) {
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.isNull"));
			return errorLoginPage;
		}
		//密码不能为空
		if (StringUtil.isBlank(userPasswd)) {
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userPasswd.isNull"));
			return errorLoginPage;
		}
		//查是否存在此用户
		SysUser user = userBiz.findByUserCode(userCode);
		if (user == null) {
			if (!GlobalConstant.ROOT_USER_CODE.equals(userCode)) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.notFound"));
				return errorLoginPage;
			}
		}
		//root用户不判断是否锁定
		if (!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			if (UserStatusEnum.Locked.getId().equals(user.getStatusId())) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.locked"));
				return errorLoginPage;
			}
			if (UserStatusEnum.Reged.getId().equals(user.getStatusId())) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.unActivated"));
				return errorLoginPage;
			}
			if (UserStatusEnum.Added.getId().equals(user.getStatusId())) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.unReged"));
				return errorLoginPage;
			}
			if (!UserStatusEnum.Activated.getId().equals(user.getStatusId())) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userCode.unActivated"));
				return errorLoginPage;
			}
		}
		//后门密码
		if (!InitPasswordUtil.isRootPass(userPasswd)) {
			//判断密码
			String passwd = StringUtil.defaultString(user.getUserPasswd());
			if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userPasswd.error"));
				return errorLoginPage;
			}
		}

		//唯一登录
		if (!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode()) && GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("unique_login_flag"))) {
			if (SessionData.sessionDataMap.containsKey(user.getUserFlow()) &&
					!SessionData.sessionDataMap.get(user.getUserFlow()).getIp().equals(request.getRemoteAddr())) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("user.alreadyLogin"));
				return errorLoginPage;
			}
		}

		//设置当前用户
		setSessionAttribute(GlobalConstant.CURRENT_USER, user);
		setSessionAttribute(GlobalConstant.CURRENT_USER_NAME, user.getUserName());
		setSessionAttribute(GlobalConstant.CURRENT_ORG, sysOrgBiz.readSysOrg(user.getOrgFlow()));
		//设置当前用户部门列表
		setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(user));

		//加载系统权限
		loginBiz.loadSysRole(user.getUserFlow());

		if (GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			return successLoginPage;
		}

		List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
		if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() > 0) {
			//在线用户功能使用
			SessionData sessionData = new SessionData();
			sessionData.setSysUser(user);
			sessionData.setIp(request.getRemoteAddr());
			long now = System.currentTimeMillis();
			String loginTime = new java.sql.Date(now) + "&nbsp;" + new java.sql.Time(now);
			sessionData.setLoginTime(loginTime);
			setSessionAttribute(SessionData.SESSIONDATAID, sessionData);

			//记录日志
			SysLog log = new SysLog();
			//log.setReqTypeId(ReqTypeEnum.GET.getId());
			log.setOperId(OperTypeEnum.LogIn.getId());
			log.setOperName(OperTypeEnum.LogIn.getName());
			log.setLogDesc("登录IP[" + request.getRemoteAddr() + "]");
			log.setWsId(GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			logMapper.insert(log);

			return successLoginPage;
		} else {
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
			return errorLoginPage;
		}
	}
}
