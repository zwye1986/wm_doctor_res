package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.czyyjxres.CertificateTypesEnum;
import com.pinde.sci.enums.czyyjxres.StuRoleEnum;
import com.pinde.sci.enums.sys.OperTypeEnum;
import com.pinde.sci.model.mo.*;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;
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
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

@Controller
@RequestMapping("/inx/czyyjxrecruit")
public class InxCzyyjxRecruitController extends GeneralController{

	@Autowired
	private ILoginBiz loginBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchRotationBiz schRotationBiz;
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


			return "inx/czyyjxres/englishLogin";

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
			loginErrorMessage = "The verification code is incorrect!";
			model.addAttribute("loginErrorMessage" , loginErrorMessage);
			InxInfo info = new InxInfo();
    		PageHelper.startPage(1,2);
    		List<InxInfo> infos = this.noticeBiz.findNotice(info);
    		model.addAttribute("infos",infos);
			removeValidateCodeAttribute();
			return "inx/czyyjxres/englishLogin";
		}
		removeValidateCodeAttribute();
		try{
			SysUser user = inxBiz.gzzyjxresLogin(userCode, userPasswd);
			//设置当前用户

			setSessionAttribute(GlobalConstant.CURRENT_USER, user);
			setSessionAttribute(GlobalConstant.CURRENT_USER_NAME, user.getUserName());
			setSessionAttribute(GlobalConstant.CURRENT_ORG, orgBiz.readSysOrg(user.getOrgFlow()));
			//设置当前用户部门列表
			setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(user));
			//加载系统权限
			loginBiz.loadSysRole(user.getUserFlow());

			if(GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
				return "redirect:/main?time="+new Date();
			}

//			//根据当前userFlow查询res_doctor表是否有数据，若无，则插入一条数据
//			ResDoctor doctor = doctorBiz.searchByUserFlow(user.getUserFlow());
//			if(doctor==null){
//				doctor = new ResDoctor();
//				doctor.setDoctorFlow(user.getUserFlow());
//				doctor.setDoctorCategoryId(RecDocCategoryEnum.Scholar.getId());
//				doctor.setDoctorCategoryName(RecDocCategoryEnum.Scholar.getName());
//
//				doctorBiz.onlySaveResDoctor(doctor);
//			}
//			doctor.setDoctorName(user.getUserName());
//			doctor.setOrgFlow(user.getOrgFlow());
//			if(StringUtil.isNotBlank(user.getOrgFlow())){
//				SysOrg so = orgBiz.readSysOrg(user.getOrgFlow());
//				doctor.setOrgName(so.getOrgName());
//			}
//			SchRotation rotation = new SchRotation();
//			rotation.setDoctorCategoryId(RecDocCategoryEnum.Scholar.getId());//进修生
//			rotation.setPublishFlag(GlobalConstant.FLAG_Y);//发布状态
//			List<SchRotation> rotationList= schRotationBiz.searchOrgStandardRotation(rotation);//进修生只维护一个方案
//			if(rotationList!=null && rotationList.size()>0){
//				doctor.setRotationFlow(rotationList.get(0).getRotationFlow());
//				doctor.setRotationName(rotationList.get(0).getRotationName());
//			}else{
//				model.addAttribute("result","请联系系统管理员维护培训轮转方案！");
//				return "/czyyjxres/doctor/loginError";
//			}
//
//			doctorBiz.editDoctor(doctor);

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
					return "redirect:/czyyjxres/secretaries/home";
				}
				if(StringUtil.isNotBlank(user.getIsOrgAdmin()) && user.getIsOrgAdmin().equals(StuRoleEnum.Teacher.getId()))
				{
					return "redirect:/czyyjxres/teacher/home";
				}
			}
			loginErrorMessage = "Not weighted!";
		}catch(RuntimeException re){
			loginErrorMessage = re.getMessage();
		}
		if("用户名或密码不正确".equals(loginErrorMessage)){
			loginErrorMessage = "Username or password incorrect!";
		}else if("用户未激活".equals(loginErrorMessage)){
			loginErrorMessage ="User not activated!";
		}else if("验证码不正确".equals(loginErrorMessage)){
			loginErrorMessage = "The verification code is incorrect!";
		}

		model.addAttribute("loginErrorMessage" , loginErrorMessage);
		InxInfo info = new InxInfo();
		PageHelper.startPage(1,2);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "inx/czyyjxres/englishLogin";
	}

	public String getRoleUrl(String roleFlow){
		if (StringUtil.isNotBlank(roleFlow)){
			String successLoginPage = "/main?time="+new Date();
			if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				return "/czyyjxres/doctor/home_en";
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
			if("验证码不正确！".equals(errorMsg)){
				errorMsg="The verification code is incorrect!";
			}
			model.addAttribute("errorMsg" , errorMsg);
			return "inx/czyyjxres/register_en";
		}
		//是否已注册
//		String userEmail = registerUser.getUserEmail();
//		SysUser user = userBiz.findByUserEmail(userEmail.trim());
//		if(user != null){
//			model.addAttribute("errorMsg" , GlobalConstant.USER_EMAIL_REPETE);
//			return "inx/czyyjxres/register";
//		}
//		SysUser user = userBiz.findByUserCode(userEmail.trim());
//		if(user != null){
//			model.addAttribute("errorMsg" , GlobalConstant.USER_EMAIL_REPETE);
//			return "inx/czyyjxres/register";
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
		String cretTypeId = registerUser.getCretTypeId();
		String cretTypeName = CertificateTypesEnum.getNameById(cretTypeId);
		registerUser.setCretTypeName(cretTypeName);
		registerUser.setIsOwnerStu("P");
		this.inxBiz.saveJXRegistUserEN(registerUser);
		model.addAttribute("activationCode" , registerUser.getUserFlow());
//		model.addAttribute("userEmail",userEmail);
		return "redirect:/inx/czyyjxrecruit/sendEmail";
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
		return "inx/czyyjxres/sendEmail_en";
	}

	/**
	 * 邮箱连接激活用户
	 * @return
	 */
	@RequestMapping("/activateuser")
	public String activateUser(String activationCode, Model model){
		this.inxBiz.activateUser(activationCode);
		return "redirect:/inx/czyyjxrecruit/activatesucc";
	}
	@RequestMapping("/activatesucc")
	public String activatesucc(HttpServletRequest request) {
		return "inx/czyyjxres/activatesucc_en";
	}
	@RequestMapping("/logout")
	public String logout(String flag,HttpServletRequest request,RedirectAttributes attributes) {
		request.getSession().invalidate();
		attributes.addAttribute("flag",flag);
		return "redirect:/inx/czyyjxrecruit";
	}

	@RequestMapping(value="/noticeView")
	public String message(Model model,String infoFlow) throws Exception{
		model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
		return "czyyjxres/message";
	}

	@RequestMapping("/noticelist")
	public String noticeList(Integer currentPage , Model model){
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,10);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "inx/czyyjxres/morenotice";
	}

	@RequestMapping("/forgetpasswd")
	public String forgetpasswd(){
		return "inx/czyyjxres/forgetpasswd";
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
		return "inx/czyyjxres/resetpasswd";
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
		return "inx/czyyjxres/uploadFile_en";
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
			return "inx/czyyjxres/uploadFile_en";
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
			return "inx/czyyjxres/uploadFile_en";
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
	@RequestMapping("/captcha")
	public void captcha(HttpServletResponse response) {
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLUE);
		colors.add(Color.BLACK);
		colors.add(Color.ORANGE);
		colors.add(Color.RED);
		List<Font> fonts = new ArrayList<Font>();
		fonts.add(new Font("Geneva", 2, 32));
		fonts.add(new Font("Courier", 3, 32));
		fonts.add(new Font("Arial", 1, 32));
		WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
		char[] numberChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		Captcha captcha = new Captcha.Builder(70, 35).addText(new DefaultTextProducer(4, numberChar) , wordRenderer)
				.gimp(new DropShadowGimpyRenderer())
				.addBackground(new TransparentBackgroundProducer()).build();
		setSessionAttribute("verifyCode", captcha.getAnswer());
		CaptchaServletUtil.writeImage(response, captcha.getImage());

	}
}
