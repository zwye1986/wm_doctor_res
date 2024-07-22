package com.pinde.sci.ctrl.login;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ClientIPUtils;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.DESUtil;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.SysLog;
import com.pinde.sci.model.mo.SysLoginAbility;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

@Controller
public class LoginController extends GeneralController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

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
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		//request.getSession().invalidate();
		return "login";
	}
    /**
     * 显示登录页面
     * @return
     */
    @RequestMapping(value = { "/loginPortal" }, method = RequestMethod.GET)
    public String portalLogin(HttpServletRequest request) {
        //request.getSession().invalidate();
        return "inx/portal/login";
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
	public String login(HttpServletRequest request, Model model,String userCode, String userPasswd, String verifyCode,String successLoginPage,String errorLoginPage) {
		//默认登录失败界面
		if(StringUtil.isBlank(errorLoginPage)){
			errorLoginPage = "login";
		}
		//默认登录成功界面
		if(StringUtil.isBlank(successLoginPage)){
			successLoginPage = "redirect:/main?time=" + new Date().getTime();
		}

		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
		//验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			model.addAttribute("loginErrorMessage",SpringUtil.getMessage("validateCode.notEquals"));
			removeValidateCodeAttribute();
			return errorLoginPage;
		}
		removeValidateCodeAttribute();
		//根据链值（动态令牌）解密
		String token = request.getParameter("csrftoken");
		String token2 = (String) request.getSession().getAttribute("csrftoken");
		String newCode = DESUtil.decrypt(userCode,token2);
		String newPwd = DESUtil.decrypt(userPasswd,token2);
		if(StringUtil.isNotBlank(newCode) && StringUtil.isNotBlank(newPwd)){
			userCode = newCode;
			userPasswd = newPwd;
		}
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
		//令牌
		if (token == null || !token.equals(token2)) {
			model.addAttribute("loginErrorMessage", "请求不正确！");
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
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("userCode.stoped"));
				return errorLoginPage;
			}
			if(UserStatusEnum.SysLocked.getId().equals(user.getStatusId())){
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
		boolean gzFlag = "gzykdx".equals(InitConfig.getSysCfg("xjgl_customer"));
		if(gzFlag){
			SysLoginAbility ability = loginBiz.readAbility(userCode);
			//输入密码超过5次错误，锁定10分钟
			if(null != ability && GlobalConstant.FLAG_Y.equals(ability.getIsLock())){
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					Long diff = System.currentTimeMillis()-sdf.parse(ability.getLoginTime()).getTime();
					Long day = diff / (24*60*60*1000);
					Long hour = diff / (60*60*1000) - day * 24;
					Long min = diff / (60*1000) - day * 24 * 60 - hour * 60;
					if(min < 10){
						model.addAttribute("loginErrorMessage", "密码错误次数过多，请十分钟再试！");
						return errorLoginPage;
					}
				} catch (ParseException e) {
					e.getMessage();
				}
			}
		}
		if (!InitPasswordUtil.isRootPass(userPasswd)) {
			//判断密码
			boolean pwdFlag = true;
			String passwd = StringUtil.defaultString(user.getUserPasswd());
			if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))){
				pwdFlag = false;
			}
			//医院超级密码判断
			if(!pwdFlag && StringUtil.isNotBlank(user.getOrgFlow())){
				pwdFlag = true;
				SysOrg sysOrg = sysOrgBiz.readSysOrg(user.getOrgFlow());
				sysOrg.setHospitalPassword(userPasswd);
				int hospitalPwd = sysOrgBiz.confirmRole(user.getUserFlow(),sysOrg);
				if (!(StringUtil.isNotBlank(sysOrg.getHospitalPassword()) && hospitalPwd >0)) {
					pwdFlag = false;
				}
			}
			if(!pwdFlag){
				model.addAttribute("loginErrorMessage", SpringUtil.getMessage("userPasswd.error"));
				if(gzFlag){
					loginBiz.updateLoginAbility(userCode);
				}
				return errorLoginPage;
			}
		}
		if(gzFlag){
			//登录成功，则删除记录，重新计算
			loginBiz.deleteLoginAbility(userCode);
		}

		String clientIp = ClientIPUtils.getClientIp(request);

		//唯一登录
		if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())&&GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("unique_login_flag"))){
			if (SessionData.sessionDataMap.containsKey(user.getUserFlow()) && SessionData.sessionDataMap.get(user.getUserFlow()).getIp().equals(clientIp)){
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
			sessionData.setIp(clientIp);
			long now = System.currentTimeMillis();
			String loginTime = new java.sql.Date(now)+"&nbsp;"+new Time(now);
			sessionData.setLoginTime(loginTime);
			setSessionAttribute(SessionData.SESSIONDATAID,sessionData);
			SessionData.sessionDataMap.put(user.getUserFlow(),sessionData);
			//记录日志
			SysLog log = new SysLog();
			//log.setReqTypeId(ReqTypeEnum.GET.getId());
			log.setOperId(OperTypeEnum.LogIn.getId());
			log.setOperName(OperTypeEnum.LogIn.getName());
			log.setLogDesc("登录IP["+clientIp+"]");
			log.setWsId(GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			loginBiz.addSysLog(log);

			return successLoginPage;
		}else{
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
			return errorLoginPage;
		}
	}

	@RequestMapping("/logout")
	public String logout(String wsId, HttpServletRequest request) {
		//记录日志
		if(GlobalContext.getCurrentUser()!=null && !GlobalConstant.ROOT_USER_CODE.equals(GlobalContext.getCurrentUser().getUserCode())){
			SysLog log = new SysLog();
			//log.setReqTypeId(ReqTypeEnum.GET.getId());
			log.setOperId(OperTypeEnum.LogOut.getId());
			log.setOperName(OperTypeEnum.LogOut.getName());
			log.setWsId(GlobalConstant.SYS_WS_ID);
			GeneralMethod.addSysLog(log);
			loginBiz.addSysLog(log);
		}
		request.getSession().invalidate();

		return "logout";
	}

	@RequestMapping("/online")
	public String online(HttpServletRequest request,Model model) {

		List<SessionData> onlineUsers = SessionData.getOnlineUsers();
		Map<String, HttpSession> sessionMap = SessionData.sessionMap;
		Map<String, SessionData> sessionDataMap = SessionData.sessionDataMap;
		for(int i=0;i<onlineUsers.size();i++){
			SessionData sessionData = (SessionData)onlineUsers.get(i);
			String uniqueid = sessionData.getSysUser().getUserFlow();

			HttpSession s = (HttpSession)sessionMap.get(uniqueid);
			boolean isActive = true;
			try{
				s.getCreationTime();
			}catch(Exception e){
				isActive = false;
			}
			String lastAccessTime = null;
			String gapTime = null;
			long lastAccessedTime = s.getLastAccessedTime();
			if(s!=null&&isActive){
				lastAccessTime = new java.sql.Date(lastAccessedTime)+"&nbsp;"+new java.sql.Time(lastAccessedTime);
				gapTime = new java.sql.Time(new java.util.Date().getTime()-s.getLastAccessedTime()-8*60*60*1000)+"";
				sessionData.setLastAccessTime(lastAccessTime);
				sessionData.setGapTime(gapTime);
			}
		}
		Collections.sort(onlineUsers, new Comparator<SessionData>() {
			public int compare(SessionData a, SessionData b) {
				int one = Integer.parseInt(StringUtil.defaultIfEmpty(a.getGapTime(),"0").replaceAll(":", ""));
				int two = Integer.parseInt(StringUtil.defaultIfEmpty(b.getGapTime(),"0").replaceAll(":", ""));
				return one - two;
			}
		});

		model.addAttribute("sessionDataMap",sessionDataMap);
		model.addAttribute("sessionMap", sessionMap);
		model.addAttribute("sessionDataList", onlineUsers);
		return "online";
	}

	@RequestMapping("/resetUserLogin")
	@ResponseBody
	public String resetUserLogin(String userFlow) {
		SessionData.sessionDataMap.remove(userFlow);
		HttpSession userSession = SessionData.sessionMap.get(userFlow);
		if(userSession!=null){
			userSession.invalidate();
		}
		SessionData.sessionMap.remove(userFlow);
		return GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping("/timeout")
	public String timeout(HttpServletRequest request, Model model) {
		return "timeout";
	}
	@RequestMapping("/path")
	public String path(HttpServletRequest request, Model model) {
		System.err.println("============path===============");
		return "path";
	}

	/**
	 * 验证码图片
	 * @param model
	 * @param request
	 * @param response
	 */

	@RequestMapping("/captcha")
	public void captcha(Model model, HttpServletRequest request,
						HttpServletResponse response) {

		List<Color> colors = new ArrayList<Color>();
//		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.BLACK);
//		colors.add(Color.GRAY);
//		colors.add(Color.LIGHT_GRAY);
//		colors.add(Color.DARK_GRAY);
		colors.add(Color.ORANGE);
		colors.add(Color.RED);

		List<Font> fonts = new ArrayList<Font>();
		fonts.add(new Font("Geneva", 2, 32));
		fonts.add(new Font("Courier", 3, 32));
		fonts.add(new Font("Arial", 1, 32));

//		WordRenderer wordRenderer = new ColoredEdgesWordRenderer(colors, fonts);
		WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
		char[] numberChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A','B','C','D','E','F','G','H','J','K','L','M'};
		Captcha captcha = new Captcha.Builder(150, 50).addText(new DefaultTextProducer(4, numberChar) , wordRenderer)
				.gimp(new DropShadowGimpyRenderer())
				.addBackground(new TransparentBackgroundProducer()).build();

		setSessionAttribute("verifyCode", captcha.getAnswer());

		CaptchaServletUtil.writeImage(response, captcha.getImage());

	}

	/**
	 * 验证码图片(督导)
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping("/supervisioCaptcha")
	public void supervisioCaptcha(Model model, HttpServletRequest request,
								  HttpServletResponse response) {

		List<Color> colors = new ArrayList<Color>();
//		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.BLACK);
//		colors.add(Color.GRAY);
//		colors.add(Color.LIGHT_GRAY);
//		colors.add(Color.DARK_GRAY);
		colors.add(Color.ORANGE);
		colors.add(Color.RED);

		List<Font> fonts = new ArrayList<Font>();
		fonts.add(new Font("Geneva", 2, 32));
		fonts.add(new Font("Courier", 3, 32));
		fonts.add(new Font("Arial", 1, 32));

//		WordRenderer wordRenderer = new ColoredEdgesWordRenderer(colors, fonts);
		WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
		char[] numberChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','P','Q','R','S','T','U','V','X','Y','Z'};
		Captcha captcha = new Captcha.Builder(150, 50).addText(new DefaultTextProducer(4, numberChar) , wordRenderer)
				.gimp(new DropShadowGimpyRenderer())
				.addBackground(new TransparentBackgroundProducer()).build();

		setSessionAttribute("verifyCode", captcha.getAnswer());

		CaptchaServletUtil.writeImage(response, captcha.getImage());

	}

	@RequestMapping("/sys/log/list")
	public String loginLogList(Integer currentPage, Model model,String startDate,String endDate,SysLog log,HttpServletRequest request) {
		PageHelper.startPage(currentPage, getPageSize(request));
		model.addAttribute("logList",loginBiz.getCurrWsSysLog(startDate,endDate,log));
		return "sys/log/list";
	}

	/**
	 * 验证码图片
	 * @param model
	 * @param request
	 * @param response
	 */

	@RequestMapping("/captchaComplex")
	public void captchaComplex(Model model, HttpServletRequest request,
							   HttpServletResponse response) {

		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.GREEN);
		colors.add(Color.BLUE);
		colors.add(Color.BLACK);
		colors.add(Color.GRAY);
		colors.add(Color.LIGHT_GRAY);
		colors.add(Color.DARK_GRAY);
		colors.add(Color.ORANGE);
		colors.add(Color.RED);

		List<Font> fonts = new ArrayList<Font>();
		fonts.add(new Font("Geneva", 2, 32));
		fonts.add(new Font("Courier", 3, 32));
		fonts.add(new Font("Arial", 1, 32));

//		WordRenderer wordRenderer = new ColoredEdgesWordRenderer(colors, fonts);
		WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
		char[] numberChar = new char[36];
		int t = 0;
		for(char m='0' ; m<='9'; m++){
			numberChar[t] = m;
			t++;
		}

		for(char i='A';i<='Z';i++){
			numberChar[t] = i;
			t++;
		}
		Captcha captcha = new Captcha.Builder(150, 50).addText(new DefaultTextProducer(6, numberChar) , wordRenderer)
				.gimp(new DropShadowGimpyRenderer())
				.addBackground(new TransparentBackgroundProducer()).build();

		setSessionAttribute("verifyCodeComplex", captcha.getAnswer());

		CaptchaServletUtil.writeImage(response, captcha.getImage());

	}

	/**
	 * @Department：研发部
	 * @Description 算式验证码
	 * @Author fengxf
	 * @Date 2024/2/4
	 */
	@RequestMapping("/captchaMath")
	public void captchaMath(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 算术类型
		ArithmeticCaptcha captcha = checkCapchaResult();
		CaptchaUtil.out(captcha, request, response);
	}

	/**
	 * @Department：研发部
	 * @Description 获取验证码 校验结果保证不能为负数
	 * @Author fengxf
	 * @Date 2024/2/5
	 */
	private ArithmeticCaptcha checkCapchaResult(){
		//生成算术验证码
		ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
		captcha.setLen(2);  // 几位数运算，默认是两位
		//计算结果
		int sum = Integer.parseInt(captcha.text());
		if (sum < 0) {
			//算术验证码
			captcha = new ArithmeticCaptcha(130, 48);
			// 几位数运算，默认是两位
			captcha.setLen(2);
			return checkCapchaResult();
		}
		return captcha;
	}
}
