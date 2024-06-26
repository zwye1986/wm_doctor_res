package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jszy.IJszyResDoctorBiz;
import com.pinde.sci.biz.jszy.JszyInxBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/inx/jszy")
public class InxJszyController extends GeneralController{

	@Autowired
	private JszyInxBiz inxBiz;
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

	@Autowired
	private IJszyResDoctorBiz jszyDoctorBiz;
	/**
	 * 显示登陆界面
	 * @return
	 */
	@RequestMapping("")
	public String showLogin(Model model){
		return "inx/jszy/login2";
	}

	/**
	 * 验证黑名单
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "/checkUserCodeInBlack")
	@ResponseBody
	public String checkUserCodeInBlack(String userCode)
	{
		String checkErrorMessage="";
		Map<String,Object> userInfoMap = new HashMap<String,Object>();
		userInfoMap.put("userCode",userCode);
		List<JsresUserBalcklist> userBalcklist = jszyDoctorBiz.checkBlackList(userInfoMap);
		if(userBalcklist.size()>0)
		{
			checkErrorMessage = userBalcklist.get(0).getReason()+"<br>"+userBalcklist.get(0).getReasonYj();
			return checkErrorMessage;
		}
		return checkErrorMessage;
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
			return "inx/jszy/login2";
		}
		removeValidateCodeAttribute();
		try{
			SysUser user = inxBiz.login(userCode, userPasswd);
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
			userRole.setWsId(GlobalConstant.RES_WS_ID);
			List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
			if(!userRoleList.isEmpty()){
				userRole = userRoleList.get(0);
			}
			SysRole role = roleBiz.read(userRole.getRoleFlow()); 
			if(role!=null) {
				if (role.getRoleFlow().equals(InitConfig.getSysCfg("res_doctor_role_flow"))){
					//判断若医师已有培训基地，则直接进入住院医师规培系统
					ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
					if(doctor!=null){
						user.setOrgFlow(doctor.getOrgFlow());
						user.setOrgName(doctor.getOrgName());
						//设置当前用户
						setSessionAttribute(GlobalConstant.CURRENT_USER, user);
						setSessionAttribute(GlobalConstant.CURRENT_ORG, sysOrgBiz.readSysOrg(user.getOrgFlow()));
					}
				}else if (role.getRoleFlow().equals(InitConfig.getSysCfg("res_head_role_flow"))) {//科主任
					String cfgCode="jswjw_"+user.getOrgFlow()+"_P001";
					SysCfg cfg=cfgBiz.read(cfgCode);
					if(cfg==null||GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue())){
						loginErrorMessage = "你暂无权限使用,请联系培训基地管理员！";
						model.addAttribute("loginErrorMessage" , loginErrorMessage);
						return "inx/jszy/login2";
					}
				} else if (role.getRoleFlow().equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {//带教老师
					String cfgCode="jswjw_"+user.getOrgFlow()+"_P001";
					SysCfg cfg=cfgBiz.read(cfgCode);
					if(cfg==null||GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue())){
						loginErrorMessage = "你暂无权限使用,请联系培训基地管理员！";
						model.addAttribute("loginErrorMessage" , loginErrorMessage);
						return "inx/jszy/login2";
					}
				} else if (role.getRoleFlow().equals(InitConfig.getSysCfg("res_disciple_role_flow"))) {//师承老师
					String cfgCode="jswjw_"+user.getOrgFlow()+"_P001";
					SysCfg cfg=cfgBiz.read(cfgCode);
					if(cfg==null||GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue())){
						loginErrorMessage = "你暂无权限使用,请联系培训基地管理员！";
						model.addAttribute("loginErrorMessage" , loginErrorMessage);
						return "inx/jszy/login2";
					}
				} else if (role.getRoleFlow().equals(InitConfig.getSysCfg("res_responsible_teacher_role_flow"))) {//带教老师
					String cfgCode="jswjw_"+user.getOrgFlow()+"_P001";
					SysCfg cfg=cfgBiz.read(cfgCode);
					if(cfg==null||GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue())){
						loginErrorMessage = "你暂无权限使用,请联系培训基地管理员！";
						model.addAttribute("loginErrorMessage" , loginErrorMessage);
						return "inx/jszy/login2";
					}
				}

				setSessionAttribute(GlobalConstant.CURRENT_WS_ID, GlobalConstant.RES_WS_ID);

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
				return "redirect:"+getRoleUrl(role.getRoleFlow(),user.getUserCode());
			}
			loginErrorMessage = "未赋权";
		}catch(RuntimeException re){
			loginErrorMessage = re.getMessage();
		}
		model.addAttribute("loginErrorMessage" , loginErrorMessage);
		return "inx/jszy/login2";
	}
	
	public String getRoleUrl(String roleFlow,String userCode){
		if (StringUtil.isNotBlank(roleFlow)){
			if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
				return "/jszy/manage/global";
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))||roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow_free"))) {//医院管理员//免费医院管理员
				return "/jszy/manage/local";
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow"))) {//科主任
				return "/main?time="+new Date();
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_secretary_role_flow"))) {//教秘
				return "/main?time="+new Date();
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {//带教老师
				return "/main?time="+new Date();
			}  else if (roleFlow.equals(InitConfig.getSysCfg("res_disciple_role_flow"))) {//师承老师
				return "/main?time="+new Date();
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				return "/jszy/doctor/index";
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))) {//主管部门（市局）
				return "/jszy/manage/charge";
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_responsible_teacher_role_flow"))) {//责任导师
				return "/main?time="+new Date();
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_university_manager_role_flow"))) {//高校
				return "/main?time="+new Date();
			}
		}
		return "";
	}
	
	@RequestMapping(value = { "/loginWithParam" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model,String userCode,String successLoginPage) {
		//默认登录成功界面
		if(StringUtil.isBlank(successLoginPage)){
			successLoginPage = "redirect:/main?time="+new Date();
		}
		//查是否存在此用户
		SysUser user = userBiz.findByUserCode(userCode);
		//设置当前用户
		setSessionAttribute(GlobalConstant.CURRENT_USER, user);
		setSessionAttribute(GlobalConstant.CURRENT_USER_NAME, user.getUserName());
		setSessionAttribute(GlobalConstant.CURRENT_ORG, sysOrgBiz.readSysOrg(user.getOrgFlow()));	
		//设置当前用户部门列表
		setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(user));
		//加载系统权限
		loginBiz.loadSysRole(user.getUserFlow());
		return successLoginPage;
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
			return "inx/jszy/register";
		}
		//是否已注册
		String userEmail = registerUser.getUserEmail();
		SysUser user = userBiz.findByUserEmail(userEmail.trim());
		if(user != null){
			model.addAttribute("errorMsg" , GlobalConstant.USER_EMAIL_REPETE);
			return "inx/jszy/register";
		}
		user = userBiz.findByUserCode(userEmail.trim());
		if(user != null){
			model.addAttribute("errorMsg" , GlobalConstant.USER_EMAIL_REPETE);
			return "inx/jszy/register";
		}
		this.inxBiz.registSczy(registerUser);
		model.addAttribute("activationCode" , registerUser.getUserFlow());
		return "redirect:/inx/jszy/sendEmail";
	}
	
	@RequestMapping("/sendEmail")
	public String sendEmail(String activationCode , Model model){
		SysUser user = userBiz.readSysUser(activationCode);
		model.addAttribute("activationCode" , user.getUserFlow());
		model.addAttribute("userEmail" , user.getUserEmail());
		return "inx/jszy/sendEmail";
	}
	
	/**
	 * 邮箱连接激活用户
	 * @return
	 */
	@RequestMapping("/activateuser")
	public String activateUser(String activationCode, Model model){
		this.inxBiz.activateUser(activationCode);
		return "redirect:/inx/jszy/activatesucc";
	}
	@RequestMapping("/activatesucc")
	public String activatesucc(HttpServletRequest request) {
		return "inx/jszy/activatesucc";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/inx/jszy";
	}
	
	@RequestMapping(value="/noticeView")
	public String message(Model model,String infoFlow) throws Exception{
		SysUser user = GlobalContext.getCurrentUser();
		if(user!=null)
		{
			noticeBiz.saveReadInfo(user.getUserFlow(),infoFlow);
		}
		model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
		return "jszy/message";
	}
	
	@RequestMapping("/queryNoticeByRoleFlowAndColumnId")
	public String queryNoticeByRoleFlowAndColumnId(String sysId,String typeId ,String flagId, Model model){
       // SysUser user = GlobalContext.getCurrentUser();
		PageHelper.startPage(1,7);
        List<InxInfo> infos = null;
        if(flagId == null || flagId.equals("Y")) {
            infos = this.noticeBiz.searchInfoByOrgNoRoleFlow(null, typeId, sysId);
        }else if(flagId.equals("N")){
            infos = this.noticeBiz.searchInfoByOrg("N", typeId, sysId);
        }
        model.addAttribute("infos",infos);
		return "inx/jszy/loadNotices";
	}
	@RequestMapping("/noticelist")
	public String noticeList(Integer currentPage ,String sysId,String typeId ,String flagId, Model model){
        SysUser user = GlobalContext.getCurrentUser();
		PageHelper.startPage(currentPage,10);
        List<InxInfo> infos = null;
        if(flagId == null || flagId.equals("Y")) {
            infos = this.noticeBiz.searchInfoByOrgNoRoleFlow(null, typeId, sysId);
        }else if(flagId.equals("N")){
            infos = this.noticeBiz.searchInfoByOrg("N", typeId, sysId);
        }
		model.addAttribute("infos",infos);
		return "inx/jszy/morenotice";
	}
	
	@RequestMapping("/forgetpasswd")
	public String forgetpasswd(){
		return "inx/jszy/forgetpasswd";
	}
	
	/**
	 * 忘记密码发送邮件
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendResetPassEmail")
	@ResponseBody
	public Map<String,String> sendResetPassEmail(String userEmail,String verifyCode,Model model){
		Map<String,String> respMap = new HashMap<String,String>();
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodeComplex"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			respMap.put("errorMessage", SpringUtil.getMessage("validateCode.notEquals"));
			respMap.put("result", GlobalConstant.FLAG_F);
			removeValidateCodeAttribute();
			return respMap;
		}
		removeValidateCodeAttribute();
		if(StringUtil.isNotBlank(userEmail)){
			userEmail = userEmail.trim();
			SysUser user = userBiz.findByUserEmail(userEmail);
			if(user==null){
				user = userBiz.findByUserPhone(userEmail);
			}
			if(user==null){
				user = userBiz.findByIdNo(userEmail);
			}
			if(user!=null){
				userEmail = user.getUserEmail();
				inxBiz.sendResetPassEmail(userEmail, user.getUserFlow());
				respMap.put("userEmail", userEmail);
				respMap.put("result", GlobalConstant.FLAG_Y);
				return respMap;
			}
		}
		respMap.put("result", GlobalConstant.FLAG_N);
		return respMap;
	}
	
	@RequestMapping("/resetpasswd")
	public String resetpasswd(String actionId,Model model){
		SysUser user = userBiz.readSysUser(actionId);
		if (user != null) {
			model.addAttribute("userCode", user.getUserCode());
			model.addAttribute("actionId", actionId);
		}
		return "inx/jszy/resetpasswd";
	}
	
	@RequestMapping(value={"/savePasswd"})
	@ResponseBody
	public String savePasswd(String actionId,String userPasswd,HttpServletRequest request,Model model){
		SysUser sysUser = userBiz.readSysUser(actionId);
		if(sysUser != null){
			//更新
			sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswd));
			userBiz.updateUser(sysUser);
			setSessionAttribute(GlobalConstant.CURRENT_USER, sysUser);
			return GlobalConstant.RESET_SUCCESSED;
		}else{
			//给出错误提示
			return GlobalConstant.SAVE_FAIL;
		}
	}
	
	@RequestMapping("/uploadFile")
	public String uploadFile(String operType,Model model){
		model.addAttribute("operType" , operType);
		return "inx/jszy/uploadFile";
	}
	
	/**
	 * 检查上传文件
	 * @param operType
	 * @param uploadFile
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/checkUploadFile",method={RequestMethod.POST})
	public String checkUploadFile(String operType, MultipartFile uploadFile, Model model){
		Map<String , MultipartFile> fileMap = new LinkedHashMap<String , MultipartFile>();
		fileMap.put(operType, uploadFile);
		String[] fileCheckInfo = testFileMap(fileMap);
		model.addAttribute("operType" , operType);
		if(fileCheckInfo!=null){
			model.addAttribute("result" , GlobalConstant.FLAG_N);
			model.addAttribute("fileErrorMsg" , fileCheckInfo);
			return "inx/jszy/uploadFile";
		} else {
			model.addAttribute("result" , GlobalConstant.FLAG_Y);
			String resultPath = "";
			if(uploadFile!=null){
				if(!uploadFile.isEmpty()){
					resultPath = this.resDoctorBiz.saveImg("",uploadFile, "jszyImages");
				}
			}
			if(GlobalConstant.FLAG_N.equals(resultPath)){
				resultPath = "";
			}
			model.addAttribute("filePath" , resultPath);
			return "inx/jszy/uploadFile";
		}
	}
	
	private String[] testFileMap(Map<String , MultipartFile> fileMap){
		 Set<Entry<String, MultipartFile>>  fileEntrySet = fileMap.entrySet();
		 Iterator<Entry<String, MultipartFile>> iterator = fileEntrySet.iterator();
		 while(iterator.hasNext()){
			 Entry<String, MultipartFile> entry = iterator.next();
			 String name = entry.getKey();
			 MultipartFile file = entry.getValue();
			 if(file!=null && !file.isEmpty()){
				if(file!=null && !file.isEmpty()){
					String fileResult = resDoctorBiz.checkFile(file);
					if(!GlobalConstant.FLAG_Y.equals(fileResult)){
						return new String[]{name,fileResult};
					}
				}
			}
		 }
		return null;
	}
	/**
	 * 江苏中医注册 身份证验证
	 */
	@RequestMapping(value="/checkIdNo")
	@ResponseBody
	public String checkIdNo(String idNo){
		if(StringUtil.isNotBlank(idNo)) {
			SysUser user = userBiz.findByNotBlackIdNo(idNo.trim());
			if(user != null){
				return "该身份证号已被注册或者已进入黑名单！";
			}
		}
		return "";
	}
}
