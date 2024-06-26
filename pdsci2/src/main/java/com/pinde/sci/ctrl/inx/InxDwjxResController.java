package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.dwjxres.StuRoleEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/inx/dwjxres")
public class InxDwjxResController extends GeneralController{

	@Autowired
	private IInxBiz inxBiz;
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
	private RedirectAttributes attributes;

	/**
	 * 显示登陆界面
	 * @return
	 */
	@RequestMapping("")
	public String showLogin(Model model,@ModelAttribute("flag") String flag) {
		model.addAttribute("flag",flag);
		InxInfo info = new InxInfo();
		PageHelper.startPage(1, 2);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos", infos);

		if ("xz".equals(flag))
			return "inx/xzjx/login";

			return "inx/dwjxres/login";

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
			InxInfo info = new InxInfo();
    		PageHelper.startPage(1,2);
    		List<InxInfo> infos = this.noticeBiz.findNotice(info);
    		model.addAttribute("infos",infos);
			removeValidateCodeAttribute();
			return "inx/dwjxres/login";
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
		InxInfo info = new InxInfo();
		PageHelper.startPage(1,2);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "inx/dwjxres/login";
	}

	public String getRoleUrl(String roleFlow){
		if (StringUtil.isNotBlank(roleFlow)){
			if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
				return "/dwjxres/hospital/home";
//			} else if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//系统管理员即省级管理员
//				return "/dwjxres/manage/home";
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				return "/dwjxres/doctor/home";
			}else if (roleFlow.equals(InitConfig.getSysCfg("res_nursing_role_flow"))) {//护理管理员
				return "/dwjxres/nursing/home";
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
			return "inx/dwjxres/register";
		}
		//是否已注册
//		String userEmail = registerUser.getUserEmail();
//		SysUser user = userBiz.findByUserEmail(userEmail.trim());
//		if(user != null){
//			model.addAttribute("errorMsg" , GlobalConstant.USER_EMAIL_REPETE);
//			return "inx/dwjxres/register";
//		}
//		SysUser user = userBiz.findByUserCode(userEmail.trim());
//		if(user != null){
//			model.addAttribute("errorMsg" , GlobalConstant.USER_EMAIL_REPETE);
//			return "inx/dwjxres/register";
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
		return "redirect:/inx/dwjxres/activateuser";
	}

	@RequestMapping("/sendEmail")
	public String sendEmail(String activationCode, String userEmail, Model model){
//		if(StringUtil.isNotBlank(userEmail)){
//			inxBiz.sendEmail(activationCode,userEmail,"");
//			model.addAttribute("userEmail", userEmail);
//		}
		SysUser user = userBiz.readSysUser(activationCode);
		model.addAttribute("activationCode" , user.getUserFlow());
//		model.addAttribute("userEmail" , user.getUserEmail());
		return "inx/dwjxres/sendEmail";
	}

	/**
	 * 邮箱连接激活用户
	 * @return
	 */
	@RequestMapping("/activateuser")
	public String activateUser(String activationCode, Model model){
		this.inxBiz.activateUser(activationCode);
		return "redirect:/inx/dwjxres/activatesucc";
	}
	@RequestMapping("/activatesucc")
	public String activatesucc(HttpServletRequest request) {
		return "inx/dwjxres/activatesucc";
	}
	@RequestMapping("/logout")
	public String logout(String flag,HttpServletRequest request,RedirectAttributes attributes) {
		request.getSession().invalidate();
		attributes.addAttribute("flag",flag);
		return "redirect:/inx/dwjxres";
	}

	@RequestMapping(value="/noticeView")
	public String message(Model model,String infoFlow) throws Exception{
		model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
		return "dwjxres/message";
	}

	@RequestMapping("/noticelist")
	public String noticeList(Integer currentPage , Model model){
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,10);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "inx/dwjxres/morenotice";
	}

	@RequestMapping("/forgetpasswd")
	public String forgetpasswd(){
		return "inx/dwjxres/forgetpasswd";
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
		return "inx/dwjxres/resetpasswd";
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
		return "inx/dwjxres/uploadFile";
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
			return "inx/dwjxres/uploadFile";
		} else {
			model.addAttribute("result" , GlobalConstant.FLAG_Y);
			String resultPath = "";
			if(uploadFile!=null){
				if(!uploadFile.isEmpty()){
					resultPath = this.resDoctorBiz.saveImg("",uploadFile, "cdresImages");
				}
			}
			if(GlobalConstant.FLAG_N.equals(resultPath)){
				resultPath = "";
			}
			model.addAttribute("filePath" , resultPath);
			return "inx/dwjxres/uploadFile";
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
}
