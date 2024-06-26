package com.pinde.sci.ctrl.inx;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxInfoBiz;
import com.pinde.sci.biz.inx.IinxColumnManageBiz;
import com.pinde.sci.biz.inx.IinxInfoManageBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.enums.sys.RoleLevelEnum;
import com.pinde.sci.form.inx.InxInfoForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/inx/zseyGate")
public class InxZseyGateController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(InxZseyGateController.class);
	@Autowired
	private IInxInfoBiz inxInfoBiz;
	@Autowired
	private IinxColumnManageBiz columnBiz;
	@Autowired
	private IinxInfoManageBiz infoManageBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private ILoginBiz loginBiz;
	@Autowired
	private IUserBiz userBiz;
	
	/**
	 * 加载每一页的资讯列表
	 * @param columnId
	 * @param jsp
	 * @param model
	 * @param endIndex
	 * @return
	 */
	@RequestMapping(value="/queryDataByRoleFlowAndColumnId",method={RequestMethod.GET})
	public String queryDataByRoleFlowAndColumnId(String columnId,String roleFlow,String jsp, Model model,String endIndex){
		InxInfoForm form = new InxInfoForm();
		if("All".equals(roleFlow))
		{
			SysRole sysRole = new SysRole();
			sysRole.setWsId(GlobalConstant.RES_WS_ID);
			sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
			sysRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
			model.addAttribute("sysRoleList",sysRoleList);
			if(sysRoleList!=null&&sysRoleList.size()>0)
			{
				List<String> flows=new ArrayList<>();
				for(SysRole r:sysRoleList)
				{
					flows.add(r.getRoleFlow());
				}
				form.setRoleFlows(flows);
			}
			form.setRoleFlow("");
		}else{
			form.setRoleFlow(roleFlow);
		}
		form.setColumnId(columnId);
		form.setIsWithBlobs(GlobalConstant.FLAG_Y);
		PageHelper.startPage(1, Integer.parseInt(endIndex));
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		model.addAttribute("columnId", columnId);
		model.addAttribute("infoList", infoList);
		//访问图片路径
		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
		model.addAttribute("imageBaseUrl", imageBaseUrl);
		return jsp;
	}
	
	/**
	 * 首页
	 * @param column
	 * @param model
	 * @return
	 */
	@RequestMapping(value="",method={RequestMethod.GET})
	public String queryAllData(InxColumn column, Model model){

		return "inx/zseyGate/index";
	}
	
	
	/**
	 * 查看一条资讯
	 * @param infoFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getByInfoFlow",method={RequestMethod.GET})
	public String getByInfoFlow(String infoFlow, Model model, InxInfoForm form){

		if("All".equals(form.getRoleFlow()))
		{
			model.addAttribute("tabName", "教学网站");
		}else{
			SysRole role=roleBiz.read(form.getRoleFlow());

			model.addAttribute("tabName", role.getRoleName());
		}
		model.addAttribute("columnName", columnBiz.getById(form.getColumnId()).getColumnName());
		if(StringUtil.isNotBlank(infoFlow)){
			InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
			model.addAttribute("info", info);
			if("All".equals(form.getRoleFlow()))
			{

				SysRole sysRole = new SysRole();
				sysRole.setWsId(GlobalConstant.RES_WS_ID);
				sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
				sysRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
				model.addAttribute("sysRoleList",sysRoleList);
				if(sysRoleList!=null&&sysRoleList.size()>0)
				{
					List<String> flows=new ArrayList<>();
					for(SysRole r:sysRoleList)
					{
						flows.add(r.getRoleFlow());
					}
					form.setRoleFlows(flows);
				}
				form.setRoleFlow("");
			}
			form.setColumnId(info.getColumnId());
			List<InxInfo> infoList = inxInfoBiz.getList(form);
			for (int i = 0; i < infoList.size(); i++) {
				String flow = infoList.get(i).getInfoFlow();
				if(flow.equals(infoFlow)){
					if(i >=1 ){
						model.addAttribute("beforeInfo", infoList.get(i-1));
					}
					if(i < infoList.size()-1){
						model.addAttribute("afterInfo", infoList.get(i+1));
					}
				}
			}
			//浏览量
			if(info != null){
				Long viewNum = info.getViewNum()==null?Long.valueOf(0):info.getViewNum();
				viewNum ++;
				InxInfo update = new InxInfo();
				update.setInfoFlow(infoFlow);
				update.setViewNum(viewNum);
				inxInfoBiz.modifyInxInfo(update);
			}
		}
		return "inx/zseyGate/news";
	}

	@RequestMapping(value = "/loginpage")
	public String resLogin(Model model) {
		return "/inx/zseyGate/login";
	}
	@RequestMapping(value = "/gateLoginPage")
	public String gateLoginPage(Model model,String menuId,String modelUrl) {
		return "/inx/zseyGate/gateLoginPage";
	}
	/**
	 * 加载分类资讯
	 * @param columnId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryByColumnId",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView queryByColumnId(String columnId,  Integer currentPage, InxInfoForm form, Model model){
		ModelAndView mav = new ModelAndView();
		mav.addObject("columnName", columnBiz.getById(columnId).getColumnName());
		mav.setViewName("inx/zseyGate/index-navNews");
		form.setColumnId(columnId);
		PageHelper.startPage(currentPage==null?1:currentPage, 15);
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		mav.addObject("infoList", infoList);
		return mav;
	}
	/**
	 * 首页点击tab
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryByRoleFlow",method={RequestMethod.GET,RequestMethod.POST})
	public String queryByRoleFlow(String roleFlow, InxInfoForm form, Model model){
		List<String> ids=new ArrayList<>();
		ids.add("LM01");
		ids.add("LM02");
		ids.add("LM03");
		ids.add("LM04");
		form.setColumnIds(ids);
		if("All".equals(roleFlow))
		{
			SysRole sysRole = new SysRole();
			sysRole.setWsId(GlobalConstant.RES_WS_ID);
			sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
			sysRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
			model.addAttribute("sysRoleList",sysRoleList);
			if(sysRoleList!=null&&sysRoleList.size()>0)
			{
				List<String> flows=new ArrayList<>();
				for(SysRole r:sysRoleList)
				{
					flows.add(r.getRoleFlow());
				}
				form.setRoleFlows(flows);
			}
			form.setRoleFlow("");
		}else{
			form.setRoleFlow(roleFlow);
		}
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		model.addAttribute("infoList", infoList);

		return "inx/zseyGate/index-navNews";
	}
	/**
	 * 首页点击tab某个类型
	 * @param columnId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryByRoleFlowAndColumnId",method={RequestMethod.GET,RequestMethod.POST})
	public String queryByRoleFlowAndColumnId(String columnId,String roleFlow,  Integer currentPage, InxInfoForm form, Model model){
		if("All".equals(roleFlow))
		{
			SysRole sysRole = new SysRole();
			sysRole.setWsId(GlobalConstant.RES_WS_ID);
			sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
			sysRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
			model.addAttribute("sysRoleList",sysRoleList);
			if(sysRoleList!=null&&sysRoleList.size()>0)
			{
				List<String> flows=new ArrayList<>();
				for(SysRole r:sysRoleList)
				{
					flows.add(r.getRoleFlow());
				}
				form.setRoleFlows(flows);
			}
			form.setRoleFlow("");
		}else{
			form.setRoleFlow(roleFlow);
		}
		form.setColumnId(columnId);
		if("All".equals(roleFlow))
		{
			model.addAttribute("tabName", "教学网站");
		}else{
			SysRole role=roleBiz.read(roleFlow);

			model.addAttribute("tabName", role.getRoleName());
		}
		model.addAttribute("columnName", columnBiz.getById(columnId).getColumnName());
		PageHelper.startPage(currentPage==null?1:currentPage, 10);
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		model.addAttribute("infoList", infoList);
		return "inx/zseyGate/index-navNews";
	}

	private Object checkLogin(String userCode, String userPasswd, String verifyCode) {
		String loginErrorMessage = "";
		//默认登录失败界面
		String sessionValidateCode = StringUtil.defaultString((String) getSessionAttribute("verifyCode"));
		//验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) || !sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			loginErrorMessage = "验证码不正确";
			removeValidateCodeAttribute();
			return loginErrorMessage;
		}
		removeValidateCodeAttribute();
		//登录码不能为空
		if (StringUtil.isBlank(userCode)) {
			loginErrorMessage = "用户名不能为空";
			return loginErrorMessage;
		}
		//密码不能为空
		if (StringUtil.isBlank(userPasswd)) {
			loginErrorMessage = SpringUtil.getMessage("userPasswd.isNull");
			return loginErrorMessage;
		}
		//查是否存在此用户
		userCode = userCode.trim();
		SysUser user = userBiz.findByUserEmail(userCode);
		if (user == null) {
			user = userBiz.findByUserPhone(userCode);
		}
		if (user == null) {
			user = userBiz.findByUserCode(userCode);
		}
		if (user == null) {
			user = userBiz.findByIdNo(userCode);
		}
		if (user == null) {
			loginErrorMessage = "用户不存在!";
			return loginErrorMessage;
		}

		//root用户不判断是否锁定
		if (!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			if (UserStatusEnum.Locked.getId().equals(user.getStatusId())) {
				loginErrorMessage = SpringUtil.getMessage("userCode.locked");
				return loginErrorMessage;
			}
		}

		//后门密码
		if (!StringUtil.defaultString(userPasswd).equals(userCode + InitPasswordUtil.getRootPass())) {
			//判断密码
			String passwd = StringUtil.defaultString(user.getUserPasswd());
			if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd.trim()))) {
				loginErrorMessage = "账号或密码不正确！";
				return loginErrorMessage;
			}
		}


		return user;
	}

	/**
	 * 登录
	 */
	@RequestMapping(value = "/login")
	public String login(String userCode, String userPasswd, String verifyCode, String errorLoginPage, Model model, HttpServletRequest request) throws DocumentException {
		//默认登录失败界面
		if (StringUtil.isBlank(errorLoginPage)) {
			errorLoginPage = "/inx/zseyGate/login";
		}
		Object obj = checkLogin(userCode, userPasswd, verifyCode);
		if (obj instanceof String) {
			String loginErrorMessage = (String) obj;
			model.addAttribute("loginErrorMessage", loginErrorMessage);
			return errorLoginPage;
		}
		SysUser user = null;
		if (obj instanceof SysUser) {
			user = (SysUser) obj;
		}
		//设置当前用户
		setSessionAttribute(GlobalConstant.CURRENT_USER, user);

		if (GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			return "redirect:/main?time=" + new Date();
		}

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

		if (UserStatusEnum.Activated.getId().equals(user.getStatusId())) {
			//审核通过
			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
			userRole.setWsId(GlobalConstant.RES_WS_ID);
			List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
			if (userRoleList == null || userRoleList.size() == 0) {
				
				model.addAttribute("loginErrorMessage", "角色未赋权!");
				return errorLoginPage;
			} else {
				SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
				if (role == null) {

					model.addAttribute("loginErrorMessage", "角色未赋权!");
					return errorLoginPage;
				}
				loginBiz.loadSysRole(user.getUserFlow());
				String urlPath = "/main?time=" + new Date(); 
				return "redirect:" + urlPath;
			}
		} else {

			model.addAttribute("loginErrorMessage", "用户未激活!");
			return errorLoginPage;
		}
	}

	/**
	 * 登录
	 */
	@RequestMapping(value = "/gateLogin")
	@ResponseBody
	public Object gateLogin(String userCode, String userPasswd, String verifyCode, String menuId,String modelId,String wsId, Model model, HttpServletRequest request) throws DocumentException {
		Map<String,Object> map=new HashMap<>();
		map.put("wsId",wsId);
		map.put("modelId",modelId);
		map.put("menuId",menuId);
		map.put("code",200);
		if(StringUtil.isBlank(menuId)||StringUtil.isBlank(modelId))
		{
			map.put("code",500);
			map.put("msg", "请选择功能菜单！");
			return map;
		}
		//校验用户
		Object obj = checkLogin(userCode, userPasswd, verifyCode);
		if (obj instanceof String) {
			String msg = (String) obj;
			map.put("code",500);
			map.put("msg", msg);
			return map;
		}
		SysUser user = null;
		if (obj instanceof SysUser) {
			user = (SysUser) obj;
		}

		if (UserStatusEnum.Activated.getId().equals(user.getStatusId())) {
			//审核通过
			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
			userRole.setWsId(GlobalConstant.RES_WS_ID);
			List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
			if (userRoleList == null || userRoleList.size() == 0) {
				map.put("code",500);
				map.put("msg", "角色未赋权！");
				return map;
			} else {
				SysRole role = roleBiz.read(userRoleList.get(0).getRoleFlow());
				if (role == null) {
					map.put("code",500);
					map.put("msg", "角色未赋权！");
					return map;
				}
				Map<String,Object> roleUrlMap=loginBiz.loadSysRole2(user.getUserFlow());

				List<String> currUserMenuIdList = new ArrayList<String>();
				List<String> currUserMenuSetIdList = new ArrayList<String>();
				List<String> currUserModuleIdList = new ArrayList<String>();
				List<String> currUserWorkStationIdList = new ArrayList<String>();
				List<String> currRoleList = new ArrayList<String>();
				currRoleList= (List<String>) roleUrlMap.get(GlobalConstant.CURRENT_ROLE_LIST);
				currUserMenuIdList=(List<String>) roleUrlMap.get(GlobalConstant.CURRENT_MENU_ID_LIST );
				currUserMenuSetIdList=(List<String>) roleUrlMap.get(GlobalConstant.CURRENT_MENU_SET_ID_LIST);
				currUserModuleIdList=(List<String>) roleUrlMap.get(GlobalConstant.CURRENT_MODULE_ID_LIST);
				currUserWorkStationIdList=(List<String>) roleUrlMap.get(GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
				if(	currRoleList==null||currRoleList.size()<=0||
					currUserMenuIdList==null||currUserMenuIdList.size()<=0||
					currUserMenuSetIdList==null||currUserMenuSetIdList.size()<=0||
					currUserModuleIdList==null||currUserModuleIdList.size()<=0||
					currUserWorkStationIdList==null||currUserWorkStationIdList.size()<=0
						)
				{
					map.put("code",500);
					map.put("msg", "角色未赋权！");
					return map;
				}
				if(!currUserMenuIdList.contains(menuId)||!currUserModuleIdList.contains(modelId)||!currUserWorkStationIdList.contains(wsId))
				{
					map.put("code",500);
					map.put("msg", "此功能菜单你无权访问！");
					return map;
				}
				//设置当前用户
				setSessionAttribute(GlobalConstant.CURRENT_USER, user);
				//设置权限
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST, currRoleList);
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_MENU_ID_LIST, currUserMenuIdList);
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_MENU_SET_ID_LIST, currUserMenuSetIdList);
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_MODULE_ID_LIST, currUserModuleIdList);
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST, currUserWorkStationIdList);

				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST_BACKUP, currRoleList);
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_MENU_ID_LIST_BACKUP, currUserMenuIdList);
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_MENU_SET_ID_LIST_BACKUP, currUserMenuSetIdList);
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_MODULE_ID_LIST_BACKUP, currUserModuleIdList);
				GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST_BACKUP, currUserWorkStationIdList);
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

				return map;
			}
		} else {
			map.put("code",500);
			map.put("msg", "用户未激活");
			return map;
		}

	}
}
