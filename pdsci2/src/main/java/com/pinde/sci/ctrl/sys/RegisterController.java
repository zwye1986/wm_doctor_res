package com.pinde.sci.ctrl.sys;

import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.sys.RoleLevelEnum;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/reg")
@SessionAttributes({"form" , "noRegOrgList"})
public class RegisterController extends GeneralController {

	private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IOrgBiz orgBiz;
	
	@ModelAttribute("sysRoleList")
	public List<SysRole> initInterests() {
		SysRole sysRole = new SysRole();
        sysRole.setWsId((String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID));
		sysRole.setRoleLevelId(RoleLevelEnum.SysLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysRole.setAllowRegFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		return sysRoleList;
	}

    /*************************忘记密码开始**************************************************/
	@RequestMapping(value="/forget/first",method={RequestMethod.GET})
	public String forgetFirst(){
		return "sys/reg/forget/first";
	}
	
	@RequestMapping(value="/forget/checkFirst",method={RequestMethod.POST})
	public String checkFirst(String forgetType,String forgetName,String verifyCode, Model model){
		SysUser old = null;
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			model.addAttribute("forgetErrorMessage",SpringUtil.getMessage("validateCode.notEquals"));
			removeValidateCodeAttribute();
			return "sys/reg/forget/first";
		}
		removeValidateCodeAttribute();
		if("userCode".equals(forgetType)){
			old = userBiz.findByUserCode(forgetName);
			if(old==null){
                model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.USER_CODE_NOT_EXIST);
				return "sys/reg/forget/first";
			}
		}
		if("userPhone".equals(forgetType)){
			old = userBiz.findByUserPhone(forgetName);
			if(old==null){
                model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.USER_PHONE_NOT_EXIST);
				return "sys/reg/forget/first";
			}		
		}
		if("userEmail".equals(forgetType)){
			old = userBiz.findByUserEmail(forgetName);
			if(old==null){
                model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.USER_EMAIL_NOT_EXIST);
				return "sys/reg/forget/first";
			}		
		}
		if("idNo".equals(forgetType)){
			old = userBiz.findByIdNo(forgetName);
			if(old==null){
                model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.ID_NO_NOT_EXIST);
				return "sys/reg/forget/first";
			}		
		}
		if(null==old){
			return "sys/reg/forget/first";			
		}
		model.addAttribute("forgetType",forgetType);
		return "redirect:/reg/forget/second?userFlow="+old.getUserFlow();
	}
	
	@RequestMapping(value="/forget/second",method={RequestMethod.GET})
	public String forgetSecond(String userFlow, Model model){
//		SysUser old = userBiz.readSysUser(userFlow);
		return "sys/reg/forget/second";
	}
	
	@RequestMapping(value="/forget/checkSecond",method={RequestMethod.POST})
	public String checkSecond(SysUser sysUser,String forgetType, Model model){

		SysUser old = userBiz.readSysUser(sysUser.getUserFlow());
		if(!StringUtil.isEquals(sysUser.getUserName(), old.getUserName())){
            model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.USER_NAME_NOT_EQUAL);
			return "sys/reg/forget/second";
		}
		if(!"idNo".equals(forgetType) && !StringUtil.isEquals(sysUser.getIdNo(), old.getIdNo())){
            model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.USER_ID_NO_NOT_EQUAL);
			return "sys/reg/forget/second";
		}
		if(!StringUtil.isEquals(sysUser.getOrgFlow(), old.getOrgFlow())){
            model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.USER_ORG_NOT_EQUAL);
			return "sys/reg/forget/second";
		}
		return "redirect:/reg/forget/thrid?userFlow="+old.getUserFlow();
	}
	
	@RequestMapping(value="/forget/thrid",method={RequestMethod.GET})
	public String forgetThrid(String userFlow, Model model){
//		SysUser old = userBiz.readSysUser(userFlow);
		return "sys/reg/forget/thrid";
	}
	
	@RequestMapping(value="/forget/checkThrid",method={RequestMethod.POST})
	public String checkThrid(String userFlow,String userPasswdNew,String userPasswdNew2, Model model){

		if(!StringUtil.isEquals(userPasswdNew, userPasswdNew2)){
            model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.USER_PASSWD_NOT_EQUAL);
			return "sys/reg/forget/thrid";
		}
		SysUser sysUser = new SysUser();
		sysUser.setUserFlow(userFlow);
		String userPwd = PasswordHelper.encryptPassword(userFlow,userPasswdNew);
		sysUser.setUserPasswd(userPwd);
		userBiz.updateUser(sysUser);
		return "sys/reg/forget/success";
	}

}
