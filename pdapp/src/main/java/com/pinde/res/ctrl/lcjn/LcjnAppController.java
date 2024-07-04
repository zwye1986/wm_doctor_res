package com.pinde.res.ctrl.lcjn;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.lcjn.IInfoBiz;
import com.pinde.res.biz.lcjn.ILcjnBiz;
import com.pinde.res.enums.lcjn.LcjnAuditStatusEnum;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.res.enums.lcjn.LcjnDoctorScoreEnum;
import com.pinde.res.enums.stdp.UserSexEnum;
import com.pinde.res.enums.stdp.UserStatusEnum;
import com.pinde.res.model.lcjn.mo.JsonData;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


@Controller
@RequestMapping("/res/lcjn")
public class LcjnAppController {
	
    private static Logger logger = LoggerFactory.getLogger(LcjnAppController.class);
    
    private boolean alert = true;
    @Autowired
	private ILcjnBiz lcjnBiz;
	@Autowired
	private IInfoBiz infoBiz;
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/lcjn/500";
    }

	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/lcjn/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String courseFlow,String recordFlow,String funcTypeId ,
						   String funcFlow , String dataFlow , HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("courseFlow", courseFlow);
		session.setAttribute("recordFlow", recordFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/lcjn/test";
	}
	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功");
		return "res/lcjn/version";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd , Model model){
		String result = "res/lcjn/login";

		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userCode))
		{
			model.addAttribute("resultId","1201");
			model.addAttribute("resultType", "用户名为空！");
			return result;
		}
		if(StringUtil.isBlank(userPasswd))
		{
			model.addAttribute("resultId","1202");
			model.addAttribute("resultType", "密码为空！");
			return result;
		}
		SysUser user=lcjnBiz.readUserByCode(userCode);
		if(user==null){
			model.addAttribute("resultId", "1203");
			model.addAttribute("resultType", "用户不存在");
			return result;
		}else {
			//超级密码
			if(PasswordUtil.isRootPass(userPasswd)){
				model.addAttribute("userinfo", user);
			}else if(user.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))){
				model.addAttribute("userinfo", user);
			}else{
				model.addAttribute("resultId", "1204");
				model.addAttribute("resultType", "用户名或密码错误");
				return result;
			}
			boolean isDoctor = false;

			String userFlow = user.getUserFlow();
			//验证用户是否有角色
			List<SysUserRole> userRoles = lcjnBiz.getSysUserRole(userFlow);
			if(userRoles==null || userRoles.isEmpty()){
				model.addAttribute("resultId", "1205");
				model.addAttribute("resultType", "用户未赋权");
				return result;
			}
			//获取当前配置的医师角色
			String doctorRole = lcjnBiz.getCfgCode("lcjn_doctor_role_flow");
			//获取最先匹配到的角色,认定该用户的角色为匹配到的角色
			for(SysUserRole sur : userRoles){
				String ur = sur.getRoleFlow();
				if(StringUtil.isNotBlank(doctorRole)&&doctorRole.equals(ur)){
					isDoctor = true;
					model.addAttribute("roleId","Student");
					model.addAttribute("roleName","医师");
					break;
				}
			}
			model.addAttribute("isDoctor", isDoctor);
			if(!isDoctor)
			{
				model.addAttribute("resultId", "1206");
				model.addAttribute("resultType", "抱歉，您无权限登录客户端");
				return result;
			}
			boolean f=false;
			if(StringUtil.isBlank(user.getUserName()))
			{
				f=true;
			}
			if(StringUtil.isBlank(user.getSexId()))
			{
				f=true;
			}
			if(StringUtil.isBlank(user.getIdNo()))
			{
				f=true;
			}
			if(StringUtil.isBlank(user.getUserPhone()))
			{
				f=true;
			}
			if(StringUtil.isBlank(user.getUserEmail()))
			{
				f=true;
			}
			if(f){
				model.addAttribute("resultId", "200");
				model.addAttribute("resultType", "完善个人资料后，方可预约！");
				model.addAttribute("compelete", "N");
				return result;
			}
			model.addAttribute("compelete", "Y");

		}
		return result;
	}
	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
	 * 此方法中前三位格式有：
	 * 13+任意数
	 * 15+除4的任意数
	 * 18+除1和4的任意数
	 * 17+除9的任意数
	 * 147
	 */
	public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	public static boolean checkPass(String str) throws PatternSyntaxException {
		String regExp = "^[A-Za-z0-9]{6,12}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	public static boolean checkEmail(String str) throws PatternSyntaxException {
		String regExp = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	public static boolean checkIdNo(String str) throws PatternSyntaxException {
		String regExp = "^[1-9]\\d{5}[1-9]\\d{3}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[0-1]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[12]\\d)))(\\d{4}|\\d{3}[xX])$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	@RequestMapping(value={"/register"},method={RequestMethod.POST})
	public String register(String userCode,String userPasswd ,String reUserPasswd , Model model){
		String result = "res/lcjn/register";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "注册成功，请完善资料！");
		if(StringUtil.isBlank(userCode))
		{
			model.addAttribute("resultId","1101");
			model.addAttribute("resultType", "用户名不能为空！");
			return result;
		}
		if(StringUtil.isBlank(userPasswd))
		{
			model.addAttribute("resultId","1102");
			model.addAttribute("resultType", "密码不能为空！");
			return result;
		}
		if(StringUtil.isBlank(reUserPasswd))
		{
			model.addAttribute("resultId","1103");
			model.addAttribute("resultType", "确认密码不能为空！");
			return result;
		}
		if(!isChinaPhoneLegal(userCode))
		{
			model.addAttribute("resultId","1104");
			model.addAttribute("resultType", "请输入正确的手机号！");
			return result;
		}
		if(!checkPass(userPasswd))
		{
			model.addAttribute("resultId","1105");
			model.addAttribute("resultType", "密码填写有误，请输入6~12位数字或密码！");
			return result;
		}
		if(!userPasswd.equals(reUserPasswd))
		{
			model.addAttribute("resultId","1106");
			model.addAttribute("resultType", "两次密码输入不一致，请重新输入！");
			return result;
		}
		SysUser user=lcjnBiz.readUserByCode(userCode);
		if(user!=null){
			model.addAttribute("resultId","1107");
			model.addAttribute("resultType", "该用户名已被注册，请重新填写！");
			return result;
		}
		user=new SysUser();
		user.setUserCode(userCode);
		user.setUserPhone(userCode);
		user.setUserPasswd(userPasswd);
		user.setStatusId(UserStatusEnum.Activated.getId());
		user.setStatusDesc(UserStatusEnum.Activated.getName());
		int count=lcjnBiz.register(user);
		if(count==0)
		{
			model.addAttribute("resultId","1108");
			model.addAttribute("resultType", "注册失败！");
			return result;
		}
		model.addAttribute("user",user);
		SysUserRole userRole=new SysUserRole();
		userRole.setRecordFlow(PkUtil.getUUID());
		userRole.setWsId("lcjn");
		userRole.setUserFlow(user.getUserFlow());
		userRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		userRole.setAuthTime(DateUtil.getCurrDateTime());
		userRole.setAuthUserFlow(user.getUserFlow());
		userRole.setCreateTime(DateUtil.getCurrDateTime());
		userRole.setCreateUserFlow(user.getUserFlow());
		userRole.setModifyTime(DateUtil.getCurrDateTime());
		userRole.setModifyUserFlow(user.getUserFlow());
		String doctorRole = lcjnBiz.getCfgCode("lcjn_doctor_role_flow");
		if(StringUtil.isNotBlank(doctorRole))
		{
			userRole.setRoleFlow(doctorRole);
		}
		lcjnBiz.addUserRole(userRole);
		return result;
	}
	@RequestMapping(value={"/changePass"},method={RequestMethod.POST})
	public String changePass(String userFlow,String userPasswd ,String newUserPasswd,String reNewUserPasswd , Model model){
		String result = "res/lcjn/success";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "修改成功，请重新登录！");
		if(StringUtil.isBlank(userFlow))
		{
			model.addAttribute("resultId","1501");
			model.addAttribute("resultType", "用户标识符为空！");
			return result;
		}
		if(StringUtil.isBlank(userPasswd))
		{
			model.addAttribute("resultId","1502");
			model.addAttribute("resultType", "密码不能为空！");
			return result;
		}
		if(StringUtil.isBlank(newUserPasswd))
		{
			model.addAttribute("resultId","1503");
			model.addAttribute("resultType", "新密码不能为空！");
			return result;
		}
		if(StringUtil.isBlank(reNewUserPasswd))
		{
			model.addAttribute("resultId","1504");
			model.addAttribute("resultType", "确认密码不能为空！");
			return result;
		}
		if(!checkPass(newUserPasswd))
		{
			model.addAttribute("resultId","1505");
			model.addAttribute("resultType", "密码填写有误，请输入6~12位数字或密码！");
			return result;
		}
		if(!newUserPasswd.equals(reNewUserPasswd))
		{
			model.addAttribute("resultId","1506");
			model.addAttribute("resultType", "两次密码输入不一致，请重新输入！");
			return result;
		}
		SysUser user=lcjnBiz.readUserByFlow(userFlow);
		if(user==null){
			model.addAttribute("resultId","1507");
			model.addAttribute("resultType", "用户不存在！");
			return result;
		}
		if(!user.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))){
			model.addAttribute("resultId", "1508");
			model.addAttribute("resultType", "密码错误，请重新输入！");
			return result;
		}
		user.setUserPasswd(PasswordHelper.encryptPassword(userFlow,newUserPasswd));
		int count=lcjnBiz.save(user);
		if(count==0)
		{
			model.addAttribute("resultId","1509");
			model.addAttribute("resultType", "修改失败！");
			return result;
		}
		return result;
	}

	@RequestMapping(value={"/ownerInfo"},method={RequestMethod.POST})
	public String ownerInfo(String userFlow , Model model){
		String result = "res/lcjn/ownerInfo";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isBlank(userFlow))
		{
			model.addAttribute("resultId","1301");
			model.addAttribute("resultType", "用户标识符为空！");
			return result;
		}
		SysUser user=lcjnBiz.readUserByFlow(userFlow);
		if(user==null){
			model.addAttribute("resultId","1302");
			model.addAttribute("resultType", "用户不存在！");
			return result;
		}
		model.addAttribute("user",user);
		//职称
		List<SysDict> titleList=lcjnBiz.getDictListByDictId(DictTypeEnum.LcjnUserTitle.getId());
		model.addAttribute("titleList",titleList);
		//专业
		List<SysDict> speList=lcjnBiz.getDictListByDictId(DictTypeEnum.LcjnSpe.getId());
		model.addAttribute("speList",speList);
		return result;
	}
	@RequestMapping(value={"/saveOwnerInfo"},method={RequestMethod.POST})
	public String saveOwnerInfo(SysUser user, Model model){
		String result = "res/lcjn/success";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isBlank(user.getUserFlow()))
		{
			model.addAttribute("resultId","1401");
			model.addAttribute("resultType", "用户标识符为空！");
			return result;
		}
		if(StringUtil.isBlank(user.getUserName()))
		{
			model.addAttribute("resultId","1402");
			model.addAttribute("resultType", "姓名为空！");
			return result;
		}
		if(StringUtil.isBlank(user.getSexId()))
		{
			model.addAttribute("resultId","1403");
			model.addAttribute("resultType", "性别为空！");
			return result;
		}
		if(StringUtil.isBlank(user.getIdNo()))
		{
			model.addAttribute("resultId","1404");
			model.addAttribute("resultType", "身份证为空！");
			return result;
		}
		if(StringUtil.isBlank(user.getUserPhone()))
		{
			model.addAttribute("resultId","1405");
			model.addAttribute("resultType", "手机号为空！");
			return result;
		}
		if(StringUtil.isBlank(user.getUserEmail()))
		{
			model.addAttribute("resultId","1406");
			model.addAttribute("resultType", "邮箱为空！");
			return result;
		}
		if(!UserSexEnum.Man.getId().equals(user.getSexId())&&!UserSexEnum.Woman.getId().equals(user.getSexId()))
		{
			model.addAttribute("resultId","1407");
			model.addAttribute("resultType", "请选择正确的性别！");
			return result;
		}else{
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		}
		if(!checkIdNo(user.getIdNo()))
		{
			model.addAttribute("resultId","1408");
			model.addAttribute("resultType", "请输入正确的身份证号！");
			return result;
		}
		if(!isChinaPhoneLegal(user.getUserPhone()))
		{
			model.addAttribute("resultId","1409");
			model.addAttribute("resultType", "请输入正确的手机号！");
			return result;
		}
		if(!checkEmail(user.getUserEmail()))
		{
			model.addAttribute("resultId","1410");
			model.addAttribute("resultType", "请输入正确的邮箱！");
			return result;
		}
		SysUser old=lcjnBiz.readUserByIdNo(user.getIdNo());
		if(old!=null&&!old.getUserFlow().equals(user.getUserFlow())){
			model.addAttribute("resultId","1411");
			model.addAttribute("resultType", "该身份证已被使用，请重新填写！");
			return result;
		}
		 old=lcjnBiz.readUserByPhone(user.getUserPhone());
		if(old!=null&&!old.getUserFlow().equals(user.getUserFlow())){
			model.addAttribute("resultId","1412");
			model.addAttribute("resultType", "该手机号已被使用，请重新填写！");
			return result;
		}
		 old=lcjnBiz.readUserByEmail(user.getUserEmail());
		if(old!=null&&!old.getUserFlow().equals(user.getUserFlow())){
			model.addAttribute("resultId","1413");
			model.addAttribute("resultType", "该邮箱已被使用，请重新填写！");
			return result;
		}
		if(StringUtil.isNotBlank(user.getTitleId()))
		{
			//职称
			List<SysDict> titleList=lcjnBiz.getDictListByDictId(DictTypeEnum.LcjnUserTitle.getId());
			if(titleList!=null)
			{
				for(SysDict dict:titleList)
				{
					if(user.getTitleId().equals(dict.getDictId()))
					{
						user.setTitleName(dict.getDictName());
					}
				}
			}
		}
		if(StringUtil.isNotBlank(user.getLcjnSpeId()))
		{
			//专业
			List<SysDict> speList=lcjnBiz.getDictListByDictId(DictTypeEnum.LcjnSpe.getId());
			if(speList!=null)
			{
				for(SysDict dict:speList)
				{
					if(user.getLcjnSpeId().equals(dict.getDictId()))
					{
						user.setLcjnSpeName(dict.getDictName());
					}
				}
			}
		}
		int count=lcjnBiz.save(user);
		if(count==0)
		{
			model.addAttribute("resultId","1414");
			model.addAttribute("resultType", "保存失败！");
			return result;
		}
		return result;
	}

	@RequestMapping(value={"/updateOwnerInfo"},method={RequestMethod.POST})
	public String updateOwnerInfo(SysUser user,String flag, Model model){
		String result = "res/lcjn/success";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isBlank(user.getUserFlow()))
		{
			model.addAttribute("resultId","1401");
			model.addAttribute("resultType", "用户标识符为空！");
			return result;
		}
		if("1".equals(flag))
		{
			if(StringUtil.isBlank(user.getUserName()))
			{
				model.addAttribute("resultId","1402");
				model.addAttribute("resultType", "姓名为空！");
				return result;
			}
		}
		if("2".equals(flag))
		{
			if(StringUtil.isBlank(user.getSexId()))
			{
				model.addAttribute("resultId","1403");
				model.addAttribute("resultType", "性别为空！");
				return result;
			}
			if(!UserSexEnum.Man.getId().equals(user.getSexId())&&!UserSexEnum.Woman.getId().equals(user.getSexId()))
			{
				model.addAttribute("resultId","1407");
				model.addAttribute("resultType", "请选择正确的性别！");
				return result;
			}else{
				user.setSexName(UserSexEnum.getNameById(user.getSexId()));
			}
		}
		if("3".equals(flag))
		{
			if(StringUtil.isBlank(user.getIdNo()))
			{
				model.addAttribute("resultId","1404");
				model.addAttribute("resultType", "身份证为空！");
				return result;
			}
			if(!checkIdNo(user.getIdNo()))
			{
				model.addAttribute("resultId","1408");
				model.addAttribute("resultType", "请输入正确的身份证号！");
				return result;
			}
			SysUser old=lcjnBiz.readUserByIdNo(user.getIdNo());
			if(old!=null&&!old.getUserFlow().equals(user.getUserFlow())){
				model.addAttribute("resultId","1411");
				model.addAttribute("resultType", "该身份证已被使用，请重新填写！");
				return result;
			}
		}
		if("4".equals(flag))
		{
			if(StringUtil.isBlank(user.getUserPhone()))
			{
				model.addAttribute("resultId","1405");
				model.addAttribute("resultType", "手机号为空！");
				return result;
			}
			if(!isChinaPhoneLegal(user.getUserPhone()))
			{
				model.addAttribute("resultId","1409");
				model.addAttribute("resultType", "请输入正确的手机号！");
				return result;
			}
			SysUser old=lcjnBiz.readUserByPhone(user.getUserPhone());
			if(old!=null&&!old.getUserFlow().equals(user.getUserFlow())){
				model.addAttribute("resultId","1412");
				model.addAttribute("resultType", "该手机号已被使用，请重新填写！");
				return result;
			}
			old=lcjnBiz.readUserByCode(user.getUserPhone());
			if(old!=null&&!old.getUserFlow().equals(user.getUserFlow())){
				model.addAttribute("resultId","1412");
				model.addAttribute("resultType", "该手机号已被注册，请重新填写！");
				return result;
			}
		}
		if("5".equals(flag))
		{
			if(StringUtil.isBlank(user.getUserEmail()))
			{
				model.addAttribute("resultId","1406");
				model.addAttribute("resultType", "邮箱为空！");
				return result;
			}
			if(!checkEmail(user.getUserEmail()))
			{
				model.addAttribute("resultId","1410");
				model.addAttribute("resultType", "请输入正确的邮箱！");
				return result;
			}
			SysUser old=lcjnBiz.readUserByEmail(user.getUserEmail());
			if(old!=null&&!old.getUserFlow().equals(user.getUserFlow())){
				model.addAttribute("resultId","1413");
				model.addAttribute("resultType", "该邮箱已被使用，请重新填写！");
				return result;
			}
		}
		if(StringUtil.isNotBlank(user.getTitleId()))
		{
			//职称
			List<SysDict> titleList=lcjnBiz.getDictListByDictId(DictTypeEnum.LcjnUserTitle.getId());
			if(titleList!=null)
			{
				for(SysDict dict:titleList)
				{
					if(user.getTitleId().equals(dict.getDictId()))
					{
						user.setTitleName(dict.getDictName());
					}
				}
			}
		}
		if(StringUtil.isNotBlank(user.getLcjnSpeId()))
		{
			//专业
			List<SysDict> speList=lcjnBiz.getDictListByDictId(DictTypeEnum.LcjnSpe.getId());
			if(speList!=null)
			{
				for(SysDict dict:speList)
				{
					if(user.getLcjnSpeId().equals(dict.getDictId()))
					{
						user.setLcjnSpeName(dict.getDictName());
					}
				}
			}
		}
		int count=lcjnBiz.save(user);
		if(count==0)
		{
			model.addAttribute("resultId","1414");
			model.addAttribute("resultType", "保存失败！");
			return result;
		}
		return result;
	}

	@RequestMapping(value={"/notice"},method={RequestMethod.POST})
	public String notice(String userFlow,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/lcjn/notice";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "1701");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "1702");
			model.addAttribute("resultType", "当前页码为空");
			return result;
		}
		if(pageSize==null){
			model.addAttribute("resultId", "1703");
			model.addAttribute("resultType", "每页条数为空");
			return result;
		}
		infoBiz.saveDocNoReadInfos(userFlow);

		PageHelper.startPage(pageIndex,pageSize);
		List<InxInfo> infos =infoBiz.getInfos(userFlow);
		model.addAttribute("infos",infos);
		model.addAttribute("dataCount", PageHelper.total);
		return result;
	}

	@RequestMapping(value={"/courseInfoList"},method={RequestMethod.POST})
	public String courseInfoList(String userFlow,String courseName,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/lcjn/courseInfoList";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "1901");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "1902");
			model.addAttribute("resultType", "当前页码为空");
			return result;
		}
		if(pageSize==null){
			model.addAttribute("resultId", "1903");
			model.addAttribute("resultType", "每页条数为空");
			return result;
		}
		PageHelper.startPage(pageIndex,pageSize);
		Map<String,String> params=new HashMap<>();
		params.put("userFlow",userFlow);
		params.put("courseName",courseName);
		List<Map<String,Object>> infos =lcjnBiz.getLcjnCourseInfo(params);
		model.addAttribute("infos",infos);
		model.addAttribute("dataCount", PageHelper.total);

		String thisDate=DateUtil.getCurrDate();
		String nowTime=thisDate+" "+DateUtil.transDateTime(DateUtil.getCurrentTime().substring(8,14), "HHmmss","HH:mm");
		model.addAttribute("nowTime",nowTime);
		List<InxInfo> notices=infoBiz.getDocNoReadInfos(userFlow);
		if(notices!=null)
		{
			model.addAttribute("noticesCount",notices.size());
		}
		return result;
	}
	@RequestMapping(value={"/courseInfo"},method={RequestMethod.POST})
	public String courseInfo(String userFlow,String courseFlow,String recordFlow,Model model){
		String result = "res/lcjn/courseInfo";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "2001");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(courseFlow)){
			model.addAttribute("resultId", "2002");
			model.addAttribute("resultType", "课程标识符为空");
			return result;
		}
		LcjnDoctorCourse doctorCourse=lcjnBiz.getDoctorCourseByFlow(recordFlow);
		if(doctorCourse==null)
		{
			doctorCourse=lcjnBiz.getDoctorCourseByCourseAndDocFlow(userFlow,courseFlow);
		}
		if(doctorCourse!=null&&GlobalConstant.RECORD_STATUS_Y.equals(doctorCourse.getRecordStatus()))
			model.addAttribute("doctorCourse",doctorCourse);
		LcjnCourseInfo info=lcjnBiz.getLcjnCourseInfoByFlow(courseFlow);
		model.addAttribute("info",info);
		String thisDate=DateUtil.getCurrDate();
		String nowTime=thisDate+" "+DateUtil.transDateTime(DateUtil.getCurrentTime().substring(8,14), "HHmmss","HH:mm");
		model.addAttribute("nowTime",nowTime);
		int lastNum=lcjnBiz.getCourseLastNumByFlow(courseFlow);
		model.addAttribute("lastNum",lastNum);
		List<LcjnCourseTime> times=lcjnBiz.getCourseTimeByFlow(courseFlow);
		model.addAttribute("times",times);
		List<LcjnCourseTea> teas=lcjnBiz.getCourseTeaByFlow(courseFlow);
		model.addAttribute("teas",teas);
		List<LcjnCourseSkill> skills=lcjnBiz.getCourseSkillByFlow(courseFlow);
		model.addAttribute("skills",skills);
		return result;
	}

	@RequestMapping(value={"/appoint"},method={RequestMethod.POST})
	public  String appoint(String userFlow,String courseFlow,String recordFlow,Model model){
		String result = "res/lcjn/success";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "2101");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(courseFlow)){
			model.addAttribute("resultId", "2102");
			model.addAttribute("resultType", "课程标识符为空");
			return result;
		}
		SysUser user=lcjnBiz.readUserByFlow(userFlow);
		if(user==null){
			model.addAttribute("resultId", "2103");
			model.addAttribute("resultType", "个人信息不存在");
			return result;
		}
		boolean f=false;
		if(StringUtil.isBlank(user.getUserName()))
		{
			f=true;
		}
		if(StringUtil.isBlank(user.getSexId()))
		{
			f=true;
		}
		if(StringUtil.isBlank(user.getIdNo()))
		{
			f=true;
		}
		if(StringUtil.isBlank(user.getUserPhone()))
		{
			f=true;
		}
		if(StringUtil.isBlank(user.getUserEmail()))
		{
			f=true;
		}
		if(f){
			model.addAttribute("resultId", "2104");
			model.addAttribute("resultType", "完善个人资料后，方可预约！");
			return result;
		}
		LcjnCourseInfo info=lcjnBiz.getLcjnCourseInfoByFlow(courseFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "2102");
			model.addAttribute("resultType", "课程信息不存在");
			return result;
		}
		String startTime=info.getAppointStartTime();
		String endTime=info.getAppointEndTime();

		String thisDate=DateUtil.getCurrDate();
		String nowTime=thisDate+" "+DateUtil.transDateTime(DateUtil.getCurrentTime().substring(8,14), "HHmmss","HH:mm");
		if(nowTime.compareTo(startTime)<0)
		{
			model.addAttribute("resultId", "2103");
			model.addAttribute("resultType", "预约时间为:"+startTime+"~"+endTime);
			return result;
		}else if (nowTime.compareTo(endTime)>0){
			model.addAttribute("resultId", "2104");
			model.addAttribute("resultType", "对不起,预约已经结束！");
			return result;
		}
		int lastNum=lcjnBiz.getCourseLastNumByFlow(courseFlow);
		if(lastNum<=0){
			model.addAttribute("resultId", "2105");
			model.addAttribute("resultType", "对不起,预约人数已满，看看其他培训课程吧！");
			return result;
		}
		int count=lcjnBiz.checkTrainTime(userFlow,courseFlow);
		if(count>0){
			model.addAttribute("resultId", "2106");
			model.addAttribute("resultType", "抱歉，该课程与您预约的其他课程时间有冲突，看看其他培训课程吧！");
			return result;
		}
		LcjnDoctorCourse doctorCourse=lcjnBiz.getDoctorCourseByFlow(recordFlow);
		if(doctorCourse==null)
		{
			doctorCourse=lcjnBiz.getDoctorCourseByCourseAndDocFlow(userFlow,courseFlow);
		}
		if(doctorCourse==null)
			doctorCourse=new LcjnDoctorCourse();
		else if(GlobalConstant.RECORD_STATUS_Y.equals(doctorCourse.getRecordStatus())&&LcjnAuditStatusEnum.Passed.getId().equals(doctorCourse.getAuditStatusId())){
			model.addAttribute("resultId", "2106");
			model.addAttribute("resultType", "你已预约过该课程信息，不得重复预约！");
			return result;
		}
		doctorCourse.setDoctorFlow(userFlow);
		doctorCourse.setDoctorName(user.getUserName());
		doctorCourse.setCourseFlow(courseFlow);
		doctorCourse.setCourseName(info.getCourseName());
		doctorCourse.setAppointTime(nowTime);
		doctorCourse.setAuditStatusId(LcjnAuditStatusEnum.Passing.getId());
		doctorCourse.setAuditStatusName(LcjnAuditStatusEnum.Passing.getName());
		if(StringUtil.isBlank(doctorCourse.getEnteringStatusId())){
			doctorCourse.setEnteringStatusId(LcjnDoctorScoreEnum.NotEntered.getId());
			doctorCourse.setEnteringStatusName(LcjnDoctorScoreEnum.NotEntered.getName());
		}
		count=lcjnBiz.saveDoctorCourse(doctorCourse);
		if(count==0)
		{
			model.addAttribute("resultId", "2107");
			model.addAttribute("resultType", "预约失败！");
			return result;
		}
		return result;
	}
	@RequestMapping(value={"/cancellations"},method={RequestMethod.POST})
	public  String cancellations(String userFlow,String courseFlow,String recordFlow,Model model){
		String result = "res/lcjn/success";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "2201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(courseFlow)){
			model.addAttribute("resultId", "2202");
			model.addAttribute("resultType", "课程标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(recordFlow)){
			model.addAttribute("resultId", "2203");
			model.addAttribute("resultType", "预约信息标识符为空");
			return result;
		}
		SysUser user=lcjnBiz.readUserByFlow(userFlow);
		if(user==null){
			model.addAttribute("resultId", "2204");
			model.addAttribute("resultType", "个人信息不存在");
			return result;
		}
		LcjnCourseInfo info=lcjnBiz.getLcjnCourseInfoByFlow(courseFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "2205");
			model.addAttribute("resultType", "课程信息不存在");
			return result;
		}
		String startTime=info.getAppointStartTime();
		String endTime=info.getAppointEndTime();
		String thisDate=DateUtil.getCurrDate();
		String nowTime=thisDate+" "+DateUtil.transDateTime(DateUtil.getCurrentTime().substring(8,14), "HHmmss","HH:mm");
		if(!(nowTime.compareTo(startTime)>=0&&nowTime.compareTo(endTime)<=0))
		{
			model.addAttribute("resultId", "2206");
			model.addAttribute("resultType", "当前时间不在预约时间内，不得取消预约！");
			return result;
		}
		LcjnDoctorCourse doctorCourse=lcjnBiz.getDoctorCourseByFlow(recordFlow);
		if(doctorCourse==null)
		{
			doctorCourse=lcjnBiz.getDoctorCourseByCourseAndDocFlow(userFlow,courseFlow);
		}
		if(doctorCourse==null||GlobalConstant.RECORD_STATUS_N.equals(doctorCourse.getRecordStatus()))
		{
			model.addAttribute("resultId", "2207");
			model.addAttribute("resultType", "预约信息不存在");
			return result;
		}
		if(LcjnAuditStatusEnum.Passed.getId().equals(doctorCourse.getAuditStatusId()))
		{
			model.addAttribute("resultId", "2208");
			model.addAttribute("resultType", "预约信息已审核通过，不得取消预约");
			return result;
		}
		if(LcjnAuditStatusEnum.Invalid.getId().equals(doctorCourse.getAuditStatusId()))
		{
			model.addAttribute("resultId", "2209");
			model.addAttribute("resultType", "预约信息已失效，无法取消预约");
			return result;
		}
		doctorCourse.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		int	count=lcjnBiz.saveDoctorCourse(doctorCourse);
		if(count==0)
		{
			model.addAttribute("resultId", "2210");
			model.addAttribute("resultType", "取消预约失败！");
			return result;
		}
		return result;
	}

	@RequestMapping(value={"/noticeDetail"},method={RequestMethod.POST})
	public String noticeDetail(String userFlow,String infoFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		String result="res/lcjn/noticeDetail";
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "1801");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "1802");
			model.addAttribute("resultType", "通知公告标识符为空");
			return result;
		}
		InxInfo info = infoBiz.readNoticeByFlow(infoFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "1803");
			model.addAttribute("resultType", "通知公告不存在，请刷新列表页面");
			return result;
		}
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/lcjn/showNotice/showNoticeDetail.jsp?infoFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/lcjn/showNotice/showNoticeDetail2.jsp?infoFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return result;
	}
	@RequestMapping(value={"/showNoticeDetail"},method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String showNoticeDetail(String infoFlow,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<>();
		InxInfo info = infoBiz.readNoticeByFlow(infoFlow);
		if(info!=null)
		{
			map.put("title","通知公告详情——【"+info.getInfoTitle()+"】");
			map.put("content",info.getInfoContent());
		}else{
			map.put("title","无详细信息");
			map.put("content","通知公告详情");
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value={"/getNoticeCount"},method={RequestMethod.GET})
	public String getNoticeCount(String userFlow,HttpServletRequest request,Model model){
		String result = "res/lcjn/noticeCount";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "1601");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		List<InxInfo> infos=infoBiz.getDocNoReadInfos(userFlow);
		if(infos!=null)
		{
			model.addAttribute("dataCount",infos.size());
		}
		return result;
	}


	@RequestMapping(value={"/myCourseList"},method={RequestMethod.POST})
	public String myCourseList(String userFlow,String courseName,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/lcjn/myCourseList";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "2301");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "2302");
			model.addAttribute("resultType", "当前页码为空");
			return result;
		}
		if(pageSize==null){
			model.addAttribute("resultId", "2303");
			model.addAttribute("resultType", "每页条数为空");
			return result;
		}
		PageHelper.startPage(pageIndex,pageSize);
		Map<String,String> params=new HashMap<>();
		params.put("userFlow",userFlow);
		params.put("courseName",courseName);
		List<Map<String,Object>> infos =lcjnBiz.getLcjnDocCourseInfo(params);
		Map<String,String> startDateMap=new HashMap<>();
		Map<String,Object> evalMap=new HashMap<>();
		Map<String,Object> siginMap=new HashMap<>();
		Map<String,Object> timeMap=new HashMap<>();

		if(infos!=null)
		{
			for(Map<String,Object> map:infos)
			{
				String courseFlow= (String) map.get("courseFlow");
				//获取最小开始时间
				String minStartDate=lcjnBiz.getCourseMinTrainStartDate(courseFlow);
				startDateMap.put(courseFlow,minStartDate);
				//获取签到信息
				List<LcjnDoctorSigin> sigins=lcjnBiz.getSiginListByFlow(userFlow,courseFlow);
				siginMap.put(courseFlow,sigins);
				//获取是否已评价
				boolean f=lcjnBiz.checkIsEval(userFlow,courseFlow);
				evalMap.put(courseFlow,f);

				List<LcjnCourseTime> times=lcjnBiz.getCourseTimeByFlow(courseFlow);
				timeMap.put(courseFlow,times);
			}
		}
		String thisDate=DateUtil.getCurrDate();
		String nowTime=thisDate+" "+DateUtil.transDateTime(DateUtil.getCurrentTime().substring(8,14), "HHmmss","HH:mm");
		model.addAttribute("startDateMap",startDateMap);
		model.addAttribute("nowTime",nowTime);
		model.addAttribute("timeMap",timeMap);
		model.addAttribute("evalMap",evalMap);
		model.addAttribute("siginMap",siginMap);
		model.addAttribute("infos",infos);
		model.addAttribute("dataCount", PageHelper.total);
		return result;
	}

	@RequestMapping(value={"/evaluate"},method={RequestMethod.POST})
	public String evaluate(String userFlow,String courseFlow,String recordFlow,Model model){
		String result = "res/lcjn/evaluate";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "2401");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(courseFlow)){
			model.addAttribute("resultId", "2402");
			model.addAttribute("resultType", "课程标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(recordFlow)){
			model.addAttribute("resultId", "2403");
			model.addAttribute("resultType", "预约信息标识符为空");
			return result;
		}
		SysUser user=lcjnBiz.readUserByFlow(userFlow);
		if(user==null){
			model.addAttribute("resultId", "2404");
			model.addAttribute("resultType", "个人信息不存在");
			return result;
		}
		LcjnCourseInfo info=lcjnBiz.getLcjnCourseInfoByFlow(courseFlow);
		model.addAttribute("info",info);
		if(info==null)
		{
			model.addAttribute("resultId", "2405");
			model.addAttribute("resultType", "课程信息不存在");
			return result;
		}
		LcjnDoctorCourse doctorCourse=lcjnBiz.getDoctorCourseByFlow(recordFlow);
		if(doctorCourse==null)
		{
			doctorCourse=lcjnBiz.getDoctorCourseByCourseAndDocFlow(userFlow,courseFlow);
		}
		if(doctorCourse==null||GlobalConstant.RECORD_STATUS_N.equals(doctorCourse.getRecordStatus()))
		{
			model.addAttribute("resultId", "2406");
			model.addAttribute("resultType", "预约信息不存在,不得评价");
			return result;
		}
		if(!LcjnAuditStatusEnum.Passed.getId().equals(doctorCourse.getAuditStatusId()))
		{
			model.addAttribute("resultId", "2407");
			model.addAttribute("resultType", "该课程未预约成功，不得评价");
			return result;
		}
		//获取最小开始时间
		String minStartDate=lcjnBiz.getCourseMinTrainStartDate(courseFlow);
		String thisDate=DateUtil.getCurrDate();
		String nowTime=thisDate+" "+DateUtil.transDateTime(DateUtil.getCurrentTime().substring(8,14), "HHmmss","HH:mm");
		if(StringUtil.isNotBlank(minStartDate)&&nowTime.compareTo(minStartDate)<0)
		{
			model.addAttribute("resultId", "2408");
			model.addAttribute("resultType", "培训暂未开始不得评价！");
			return result;
		}
		//获取签到信息
//		List<LcjnDoctorSigin> sigins=lcjnBiz.getSiginListByFlow(userFlow,courseFlow);
//		if(sigins==null||sigins.size()<=0)
//		{
//			model.addAttribute("resultId", "2409");
//			model.addAttribute("resultType", "从未签到过，不得评价！");
//			return result;
//		}
		//获取是否已评价
		boolean f=lcjnBiz.checkIsEval(userFlow,courseFlow);
		if(f){
			model.addAttribute("resultId", "2410");
			model.addAttribute("resultType", "该课程已被评价过，请刷新列表！");
			return result;
		}

		//课程评价指标
		List<SysDict> courseEvaList=lcjnBiz.getDictListByDictId(DictTypeEnum.CourseEvaluationItem.getId(),info.getOrgFlow());
		model.addAttribute("courseEvaList",courseEvaList);
		//教师评价指标
		List<SysDict> teacherEvaList=lcjnBiz.getDictListByDictId(DictTypeEnum.TeacherEvaluationItem.getId(),info.getOrgFlow());
		model.addAttribute("teacherEvaList",teacherEvaList);
		List<LcjnCourseTea> teas=lcjnBiz.getCourseTeaByFlow(courseFlow);
		model.addAttribute("teas",teas);

		return result;
	}

	@RequestMapping(value={"/showEvaluate"},method={RequestMethod.POST})
	public String showEvaluate(String userFlow,String courseFlow,Model model){
		String result = "res/lcjn/showEvaluate";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "2501");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(courseFlow)){
			model.addAttribute("resultId", "2502");
			model.addAttribute("resultType", "课程标识符为空");
			return result;
		}
		LcjnCourseInfo info=lcjnBiz.getLcjnCourseInfoByFlow(courseFlow);
		model.addAttribute("info",info);

		//课程评价
		LcjnCourseEvaluate courseEvaluate=lcjnBiz.getCourseEvaluate(userFlow,courseFlow);
		model.addAttribute("courseEvaluate",courseEvaluate);
		//课程评价详情
		if(courseEvaluate!=null) {
			List<LcjnCourseEvaluateDetail> details = lcjnBiz.getCourseEvaluateDetail(courseEvaluate.getEvaluateFlow());
			Map<String,Object> courseDetailMap=new HashMap<>();
			if(details!=null)
			{
				for(LcjnCourseEvaluateDetail d:details)
				{
					courseDetailMap.put(d.getDictId(),d);
				}
			}
			model.addAttribute("courseDetailMap",courseDetailMap);
		}
		//教师评价
		List<LcjnTeaEvaluate> teaEvaluates=lcjnBiz.getCourseTeaEvalByFlow(userFlow,courseFlow);
		Map<String,Object> teaDetailMap=new HashMap<>();
		Map<String,Object> teaEvalMap=new HashMap<>();
		if(teaEvaluates!=null)
		{
			for(LcjnTeaEvaluate teaEvaluate:teaEvaluates)
			{
				String teaFlow=teaEvaluate.getUserFlow();
				teaEvalMap.put(teaFlow,teaEvaluate);
				List<LcjnTeaEvaluateDetail> details=lcjnBiz.getCourseTeaEvalDeatil(teaEvaluate.getTeaEvaluateFlow());
				if(details!=null)
				{
					for(LcjnTeaEvaluateDetail d:details)
					{
						teaDetailMap.put(teaFlow+d.getDictId(),d);
					}
				}
			}
		}
		model.addAttribute("teaEvalMap",teaEvalMap);
		model.addAttribute("teaDetailMap",teaDetailMap);
		//课程评价指标
		List<SysDict> courseEvaList=lcjnBiz.getDictListByDictId(DictTypeEnum.CourseEvaluationItem.getId(),info.getOrgFlow());
		model.addAttribute("courseEvaList",courseEvaList);
		//教师评价指标
		List<SysDict> teacherEvaList=lcjnBiz.getDictListByDictId(DictTypeEnum.TeacherEvaluationItem.getId(),info.getOrgFlow());
		model.addAttribute("teacherEvaList",teacherEvaList);
		List<LcjnCourseTea> teas=lcjnBiz.getCourseTeaByFlow(courseFlow);
		model.addAttribute("teas",teas);

		return result;
	}

	@RequestMapping(value={"/saveEvaluate"},method={RequestMethod.POST})
	public String saveEvaluate(String jsonData, Model model){
		String result = "res/lcjn/success";
		model.addAttribute("resultId","200");
		model.addAttribute("resultType", "交易成功！");
		JsonData data =null;
		try {
			data= JSON.parseObject(jsonData, JsonData.class);
		}catch (Exception e)
		{
			model.addAttribute("resultId", "2601");
			model.addAttribute("resultType", "数据格式错误！");
			return result;
		}
		if(data==null)
		{
			model.addAttribute("resultId", "2602");
			model.addAttribute("resultType", "评价信息不能为空！");
			return result;
		}
			String userFlow=data.getUserFlow();
			if(StringUtil.isEmpty(userFlow)){
				model.addAttribute("resultId", "2603");
				model.addAttribute("resultType", "用户标识符为空");
				return result;
			}
			if(data.getCourse()==null){
				model.addAttribute("resultId", "2604");
				model.addAttribute("resultType", "课程评价信息不存在");
				return result;
			}
			String courseFlow=data.getCourse().getCourseFlow();
			if(StringUtil.isEmpty(courseFlow)){
				model.addAttribute("resultId", "2605");
				model.addAttribute("resultType", "课程标识符为空");
				return result;
			}
			LcjnCourseInfo info=lcjnBiz.getLcjnCourseInfoByFlow(courseFlow);
			if(info==null){
				model.addAttribute("resultId", "2606");
				model.addAttribute("resultType", "课程信息不存在");
				return result;
			}
			SysUser user=lcjnBiz.readUserByFlow(userFlow);
			if(user==null){
				model.addAttribute("resultId", "2607");
				model.addAttribute("resultType", "个人信息不存在");
				return result;
			}
			//获取最小开始时间
			String minStartDate=lcjnBiz.getCourseMinTrainStartDate(courseFlow);
			String thisDate=DateUtil.getCurrDate();
			String nowTime=thisDate+" "+DateUtil.transDateTime(DateUtil.getCurrentTime().substring(8,14), "HHmmss","HH:mm");
			if(StringUtil.isNotBlank(minStartDate)&&nowTime.compareTo(minStartDate)<0)
			{
				model.addAttribute("resultId", "2608");
				model.addAttribute("resultType", "培训暂未开始，无法进行评价！");
				return result;
			}
			//获取是否已评价
			boolean f=lcjnBiz.checkIsEval(userFlow,courseFlow);
			if(f){
				model.addAttribute("resultId", "2609");
				model.addAttribute("resultType", "该课程已被评价过，无法再次评价！");
				return result;
			}
			lcjnBiz.saveEvalData(data,userFlow,courseFlow);
		return result;
	}

	//解析二维码字符串为map
	private void transCodeInfo(Map<String,String> paramMap,String codeInfo){
		String[] params = StringUtil.split(codeInfo, "&");
		for(String paramStr : params){
			if(paramStr.indexOf("=")>0){
				String key = paramStr.substring(0, paramStr.indexOf("="));
				String value = paramStr.substring(paramStr.indexOf("=")+1, paramStr.length());
				paramMap.put(key, value);
			}
		}
	}
	@RequestMapping(value={"/qrCode"},method={RequestMethod.POST})
	public String qrCode(String userFlow,
						 String codeInfo,
						 HttpServletRequest request,
						 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "2701");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/lcjn/success";
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		transCodeInfo(paramMap, codeInfo);
		String funcFlow=paramMap.get("funcFlow");
		if(StringUtil.isEquals(funcFlow, "signin")){
			String courseFlow=paramMap.get("courseFlow");
			if(StringUtil.isEmpty(courseFlow)){
				model.addAttribute("resultId", "2702");
				model.addAttribute("resultType", "课程标识符为空");
				return "res/lcjn/success";
			}
			LcjnCourseInfo info=lcjnBiz.getLcjnCourseInfoByFlow(courseFlow);
			if(info==null){
				model.addAttribute("resultId", "2703");
				model.addAttribute("resultType", "课程信息不存在");
				return "res/lcjn/success";
			}
			SysUser user=lcjnBiz.readUserByFlow(userFlow);
			if(user==null){
				model.addAttribute("resultId", "2704");
				model.addAttribute("resultType", "个人信息不存在");
				return "res/lcjn/success";
			}
			LcjnDoctorCourse doctorCourse=lcjnBiz.getDoctorCourseByCourseAndDocFlow(userFlow,courseFlow);
			if(doctorCourse==null)
			{
				model.addAttribute("resultId", "2705");
				model.addAttribute("resultType", "您未预约该课程信息，无法签到！");
				return "res/lcjn/success";
			}else if(!LcjnAuditStatusEnum.Passed.getId().equals(doctorCourse.getAuditStatusId()))
			{
				model.addAttribute("resultId", "2706");
				model.addAttribute("resultType", "您预约的该课程信息，管理员未审核通过，无法签到！");
				return "res/lcjn/success";
			}
//			//获取最小开始时间
//			String minStartDate=lcjnBiz.getCourseMinTrainStartDate(courseFlow);
			String thisDate=DateUtil.getCurrDate();
			String nowTime=thisDate+" "+DateUtil.transDateTime(DateUtil.getCurrentTime().substring(8,14), "HHmmss","HH:mm");
//			if(StringUtil.isNotBlank(minStartDate)&&nowTime.compareTo(minStartDate)<0)
//			{
//				model.addAttribute("resultId", "2705");
//				model.addAttribute("resultType", "培训暂未开始，无法进行签到！");
//				return "res/lcjn/success";
//			}
//			int count=lcjnBiz.checkThisTimeIsInTrain(nowTime,courseFlow);
//			if(count<=0)
//			{
//				model.addAttribute("resultId", "2706");
//				model.addAttribute("resultType", "当前时间不在培训时间内，无法进行签到！");
//				return "res/lcjn/success";
//			}
			LcjnDoctorSigin sigin=new LcjnDoctorSigin();
			sigin.setCourseName(info.getCourseName());
			sigin.setCourseFlow(info.getCourseFlow());
			sigin.setSiginTime(nowTime);
			sigin.setDoctorName(user.getUserName());
			sigin.setDoctorFlow(user.getUserFlow());
			int count=lcjnBiz.saveSigin(sigin);
			if(count==0)
			{
				model.addAttribute("resultId", "2707");
				model.addAttribute("resultType", "签到失败！");
				return "res/lcjn/success";
			}
		}else {
			model.addAttribute("resultId", "2708");
			model.addAttribute("resultType", "无效二维码");
			return "res/lcjn/success";
		}
		return "res/lcjn/success";
	}
	public static void main(String args[]) throws ParseException {
		List<Map<String,String>> times=new ArrayList<>();
		Map<String,String>time=new HashMap<>();
		time.put("startDate","2017-02-14");
		time.put("endDate","2017-02-15");
		times.add(time);
		time=new HashMap<>();
		time.put("startDate","2017-02-19");
		time.put("endDate","2017-02-22");
		times.add(time);
		time=new HashMap<>();
		time.put("startDate","2017-02-16");
		time.put("endDate","2017-02-17");
		times.add(time);
		time=new HashMap<>();
		time.put("startDate","2017-02-23");
		time.put("endDate","2017-02-28");
		times.add(time);
		time=new HashMap<>();
		time.put("startDate","2017-03-01");
		time.put("endDate","2017-03-28");
		times.add(time);
		List<Map<String,String>> newTimes=getNewTimes(times);
		System.err.println(JSON.toJSON(newTimes));



		time=new HashMap<>();
		time.put("startDate","2017-02-15");
		time.put("endDate","2017-04-15");
		times.add(time);
		List<Map<String,String>> times1= null;
		try {
			times1 = getTimes(time);
			System.err.println(JSON.toJSON(times1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int months=getMonths(time);
		System.err.println(months);

	}

	private static int getMonths(Map<String, String> time) throws ParseException {
		int count = 0;
		if (time != null) {
			String startDate = time.get("startDate");
			String endDate = time.get("endDate");
			String NextEndDate ="";
			if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
				while (true) {
					//开始时间加1个自然月
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(startDate);
					c.setTime(date);
					c.add(Calendar.MONTH, 1);
					NextEndDate = sdf.format(c.getTime());
					NextEndDate = DateUtil.addDate(NextEndDate, -1);
					if (NextEndDate.compareTo(endDate) >= 0 ) {
						count++;
						break;
					}else{
						startDate = DateUtil.addDate(NextEndDate, 1);
					}
					count++;
				}
				if (NextEndDate.compareTo(endDate) == 0 )
				{
					return count;
				}else {
					return -1;
				}
			}
		}
		return count;
	}

	private static List<Map<String, String>> getTimes(Map<String, String> time) throws ParseException {
		List<Map<String, String>> list = null;
		if (time != null) {
			String startDate = time.get("startDate");
			String endDate = time.get("endDate");
			if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
				list = new ArrayList<>();
				while (startDate.compareTo(endDate) <= 0) {
					//开始时间加1个自然月
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(startDate);
					c.setTime(date);
					c.add(Calendar.MONTH, 1);
					String NextEndDate = sdf.format(c.getTime());
					NextEndDate = DateUtil.addDate(NextEndDate, -1);
					if (NextEndDate.compareTo(endDate) > 0) {
						NextEndDate = endDate;
					}
					Map<String, String> newTime = new HashMap<>();
					newTime.put("startDate", startDate);
					newTime.put("endDate", NextEndDate);
					list.add(newTime);
					startDate = DateUtil.addDate(NextEndDate, 1);
				}

				Collections.sort(list, new Comparator<Map<String, String>>() {
					@Override
					public int compare(Map<String, String> f1, Map<String, String> f2) {
						String order1 = f1.get("startDate");
						String order2 = f2.get("startDate");
						if (order1 == null) {
							return -1;
						} else if (order2 == null) {
							return 1;
						} else if (order1 != null && order2 != null) {
							return order1.compareTo(order2);
						}
						return 0;
					}
				});
			}
		}
		return list;
	}

	private static List<Map<String, String>> getNewTimes(List<Map<String, String>> times) {
		if (times != null && times.size() > 0) {
			Collections.sort(times, new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> f1, Map<String, String> f2) {
					String order1 = f1.get("startDate");
					String order2 = f2.get("startDate");
					if (order1 == null) {
						return -1;
					} else if (order2 == null) {
						return 1;
					} else if (order1 != null && order2 != null) {
						return order1.compareTo(order2);
					}
					return 0;
				}
			});
			for (int i = 0; i < times.size(); i++) {
				Map<String, String> map = times.get(i);
				String startDate = map.get("startDate");
				String endDate = map.get("endDate");
				String endDateNextDay = DateUtil.addDate(endDate, 1);
				int count = 0;
				for (int j = i + 1; j < times.size(); j++) {
					Map<String, String> otherMap = times.get(j);
					String otherStartDate = otherMap.get("startDate");
					String otherEndDate = otherMap.get("endDate");
					if (otherStartDate.equals(endDateNextDay)) {
						map.put("endDate", otherEndDate);
						times.remove(otherMap);
						j--;
						count++;
					}
				}
				i = i - count;
			}
		}
		return times;
	}
}
