package com.pinde.sci.ctrl.edc;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IEdcUserRegBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.RoleLevelEnum;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reg")
@SessionAttributes({"form" , "noRegOrgList"})
public class EdcRegisterController extends GeneralController {

	private static Logger logger = LoggerFactory.getLogger(EdcRegisterController.class);
	@Autowired
	private IEdcUserRegBiz userRegBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IOrgBiz orgBiz;

	@ModelAttribute("sysRoleList")
	public List<SysRole> initInterests() {
		SysRole sysRole = new SysRole();
		sysRole.setWsId((String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID));
		sysRole.setRoleLevelId(RoleLevelEnum.SysLevel.getId());
		sysRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysRole.setAllowRegFlag(GlobalConstant.FLAG_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		return sysRoleList;
	}

	/*************************EDC 注册开始**************************************************/
	@RequestMapping(value="/edc/go",method={RequestMethod.GET})
	public String edcReg(String userFlow,Model model){
		if(StringUtil.isBlank(userFlow)){
			return "sys/reg/edc/fail";
		}else{
			SysUser sysUser = userBiz.readSysUser(userFlow);
			if(sysUser == null || StringUtil.isNotEquals(sysUser.getStatusId(), UserStatusEnum.Added.toString())){
				//没有注册 
				//model.addAttribute("message", "没有注册！");
				return "sys/reg/edc/fail";
			}else{
				model.addAttribute("sysUser", sysUser);
				//进入注册页面
				return "sys/reg/edc/reg";
			}
		}
	}
	
	@RequestMapping(value="/edc/saveReg",method={RequestMethod.POST})
	@ResponseBody
	public String edcSaveReg(SysUser sysUser){
		//判断用户id是否重复
		SysUser old = userBiz.findByUserCode(sysUser.getUserCode());
		if(old!=null){
			return GlobalConstant.USER_CODE_REPETE;
		}
		old = userBiz.readSysUser(sysUser.getUserFlow());
		if(!StringUtil.isEquals(sysUser.getUserName(), old.getUserName())){
			return GlobalConstant.USER_NAME_NOT_EQUAL;
		}
		if(!StringUtil.isEquals(sysUser.getIdNo(), old.getIdNo())){
			return GlobalConstant.USER_ID_NO_NOT_EQUAL;
		}
		if(!StringUtil.isEquals(sysUser.getUserPhone(), old.getUserPhone())){
			return GlobalConstant.USER_PHONE_NOT_EQUAL;
		}
		if(!StringUtil.isEquals(sysUser.getUserEmail(), old.getUserEmail())){
			return GlobalConstant.USER_EMAIL_NOT_EQUAL;
		}
		
		if(StringUtil.isNotBlank(sysUser.getUserFlow())){	
			userRegBiz.activatSysUser(sysUser);
		}
		return GlobalConstant.USER_REG_SUCCESSED;
	}
	
	@RequestMapping(value="/edc/success",method={RequestMethod.GET})
	public String edcSuccess(){
		return "sys/reg/edc/success";
	}

}
