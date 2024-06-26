package com.pinde.sci.ctrl.srm;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRegBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.enums.srm.RegPageEnum;
import com.pinde.sci.enums.sys.RoleLevelEnum;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.srm.UserRegForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/reg")
@SessionAttributes({"form" , "noRegOrgList"})
public class SrmRegisterController extends GeneralController {

	private static Logger logger = LoggerFactory.getLogger(SrmRegisterController.class);
	@Autowired
	private IUserRegBiz userRegBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IOrgBiz orgBiz;

	/*************************SRM 注册开始**************************************************/
	@ModelAttribute("form")
	public UserRegForm initForm() {
		logger.debug("initForm()");
		return new UserRegForm();
	}
	/*************************SRM 注册开始**************************************************/
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


	@RequestMapping(value="/srm/go",method={RequestMethod.GET})
	public String srmReg(Model model){
		return "sys/reg/srm/reg";
	}

	@RequestMapping(value = "/srm/regist", method = {RequestMethod.POST}, params = {"_next", "!_prev", "!_finish"})
	public String processNext( @ModelAttribute("form") UserRegForm form , Model model) {
		String roleFlow = form.getRoleFlow();
		SysRole role = this.roleBiz.read(roleFlow);
		if(RegPageEnum.orgRegPage.getId().equals(role.getRegPageId())){
			//机构管理员注册角色
			//没有被机构管理员角色注册过的机构
			List<SysOrg> noRegOrgList = this.orgBiz.searchOrgNoRegByRoleFlow(roleFlow);
			model.addAttribute("noRegOrgList" , noRegOrgList);
		}
		return role.getRegPageId();
	}

	@RequestMapping(value = "/srm/regist", method = {RequestMethod.POST}, params = {"!_next", "_prev", "!_finish"})
	public String processPrev( @ModelAttribute("form") UserRegForm form , Model model) {
		return "sys/reg/srm/reg";
	}

	@RequestMapping(value = "/srm/regist", method = {RequestMethod.POST}, params = {"!_next", "!_prev", "_finish"})
	public String processFinish(@ModelAttribute("form") UserRegForm form, BindingResult bindingResult, SessionStatus sessionStatus , Model model) {
		String roleFlow = form.getRoleFlow();
		SysRole role = this.roleBiz.read(roleFlow);
		//srm 注册校验
		String eroMsg = this.userRegBiz.srmRegValidate(form , role);
		if(StringUtil.isNotBlank(eroMsg)){
			model.addAttribute("eroMsg" , eroMsg);
			return role.getRegPageId();
		}else{
			this.userRegBiz.regUser(form , role);
			sessionStatus.setComplete();
			return "redirect:/reg/success";
		}

	}

	@RequestMapping("/success")
	public String success(){
		return "sys/reg/srm/success";
	}

	@RequestMapping(value="/srm/success",method={RequestMethod.GET})
	public String srmSuccess(){
		return "sys/reg/srm/success";
	}
	/*************************SRM 注册结束**************************************************/

}
