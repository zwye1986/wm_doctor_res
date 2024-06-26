package com.pinde.sci.ctrl.inx;

import com.pinde.core.license.PdLicense;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.SysLog;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.*;
import java.util.List;

@Controller
public class InxXzsxsController extends GeneralController {

	private static Logger logger = LoggerFactory.getLogger(InxXzsxsController.class);

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private ILoginBiz loginBiz;

	/**
	 * 显示登录页面
	 * @return
	 */
	@RequestMapping(value = { "/inx/xzsxs" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		//request.getSession().invalidate();
		return "inx/xzsxs/login";

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

	@RequestMapping(value = { "/inx/xzsxs/login" }, method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model,String userCode, String userPasswd, String verifyCode,String successLoginPage,String errorLoginPage) {

		//默认登录失败界面
		if(StringUtil.isBlank(errorLoginPage)){
			errorLoginPage = "inx/xzsxs/login";
		}
		//默认登录成功界面
		if(StringUtil.isBlank(successLoginPage)){
			successLoginPage = "redirect:/main?time=" + new Date().getTime();
		}

		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
		//验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			model.addAttribute("loginErrorMessage",SpringUtil.getMessage("validateCode.notEquals"));
			//登录日志
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
			if(!GlobalConstant.ROOT_USER_CODE.equals(userCode)){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.notFound"));
				return errorLoginPage;
			}else{
				SysOrg org = new SysOrg();
				org.setOrgFlow(GlobalConstant.PD_ORG_FLOW);
				org.setOrgCode(GlobalConstant.PD_ORG_CODE);
				org.setOrgName(GlobalConstant.PD_ORG_NAME);
				org.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				try{
					sysOrgBiz.addOrg(org);
				}catch(Exception e){
					e.printStackTrace();
				}
				user = new SysUser();
				user.setUserFlow(GlobalConstant.ROOT_USER_FLOW);
				user.setUserCode(userCode);
				user.setUserName(GlobalConstant.ROOT_USER_NAME);
				user.setOrgFlow(GlobalConstant.PD_ORG_FLOW);
				user.setOrgName(GlobalConstant.PD_ORG_NAME);
				user.setStatusId(UserStatusEnum.Activated.getId());
				user.setStatusDesc(UserStatusEnum.Activated.getName());
				userBiz.addUser(user);
			}
		}
		//root用户不判断是否锁定
		if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
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
			if(!UserStatusEnum.Activated.getId().equals(user.getStatusId())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.unActivated"));
				return errorLoginPage;
			}
		}

		String rootPassord = userCode + InitPasswordUtil.getRootPass();
		List<String> licensedWorkStation = PdLicense.getLicensedWorkStation();
		if (licensedWorkStation != null && licensedWorkStation.size() > 0) {
			for (String wsId : licensedWorkStation) {
				if (wsId.toUpperCase().equals(GlobalConstant.ERP_WS_ID.toUpperCase())) {
					rootPassord = userCode + GlobalConstant.PD_ERP_SUPPPWD;
					break;
				} else if (wsId.toUpperCase().equals("EXAM")) {
					rootPassord = userCode + GlobalConstant.PD_EXAM_SUPPPWD;
					break;
				}
			}
		}
		if (!StringUtil.defaultString(userPasswd).equals(rootPassord)) {
			//判断密码
			String passwd = StringUtil.defaultString(user.getUserPasswd());
			if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))) {
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userPasswd.error"));
				return errorLoginPage;
			}
		}

		//唯一登录
		if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())&&GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("unique_login_flag"))){
			if (SessionData.sessionDataMap.containsKey(user.getUserFlow()) &&
					!SessionData.sessionDataMap.get(user.getUserFlow()).getIp().equals(request.getRemoteAddr())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("user.alreadyLogin"));
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
//		//加载伦理权限
//		loginEdcBiz.loadIrbRole(user.getUserFlow());
//		//加载EDC项目权限
//      loginEdcBiz.loadEDCProjRole(user.getUserFlow(), null);

		if(GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
			return successLoginPage;
		}

		List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
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
			//log.setReqTypeId(ReqTypeEnum.GET.getId());
			log.setOperId(OperTypeEnum.LogIn.getId());
			log.setOperName(OperTypeEnum.LogIn.getName());
			log.setLogDesc("登录IP["+request.getRemoteAddr()+"]");
			log.setWsId(GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			loginBiz.addSysLog(log);

			return successLoginPage;
		}else{
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
			return errorLoginPage;
		}
	}
}
