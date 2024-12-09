package com.pinde.sci.ctrl.portal;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IinxColumnManageBiz;
import com.pinde.sci.biz.portal.IPortalUserManageBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/portal/user")
public class PortalUserManageController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(PortalUserManageController.class);
	@Autowired
	private IinxColumnManageBiz columnBiz;
	@Autowired
	private IPortalUserManageBiz gateUserManageBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;

	@RequestMapping(value="/main")
	public String main(){
		return "portal/user/main";
	}
	@RequestMapping(value="/szmain")
	public String szmain(){
		return "portal/user/szmain";
	}
	@RequestMapping(value="/edit")
	public String edit(String userFlow,Model model){
		if(StringUtil.isNotBlank(userFlow))
		{
			SysUser user=userBiz.readSysUser(userFlow);
			model.addAttribute("user",user);
		}
		return "portal/user/edit";
	}
	@RequestMapping(value="/szEdit")
	public String szEdit(String userFlow,Model model){
		if(StringUtil.isNotBlank(userFlow))
		{
			SysUser user=userBiz.readSysUser(userFlow);
			model.addAttribute("user",user);
		}
		return "portal/user/szEdit";
	}
	@RequestMapping(value={"/saveUser"},method= RequestMethod.POST)
	public @ResponseBody String save(SysUser user,String[] roleFlows){
		//新增用户是判断
		if(StringUtil.isBlank(user.getUserFlow())){
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCode(user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}
		}else{
			String userFlow = user.getUserFlow();
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}
		}
		try {
			gateUserManageBiz.saveUser(user, roleFlows);
		}catch (Exception e)
		{
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	@RequestMapping(value = {"/userList" })
	public String userList (Model model, Integer currentPage, HttpServletRequest request, String userCode, String userName) throws Exception{
		String roleFlow0 = InitConfig.getSysCfg("portals_user_role_flow");
		String roleFlow1 = InitConfig.getSysCfg("portals_cityCharge_role_flow");
		List<String> roleFlowList = new ArrayList<>();
		roleFlowList.add(roleFlow0);
		roleFlowList.add(roleFlow1);
		Map<String,Object> params=new HashMap<>();
		params.put("userCode",userCode);
		params.put("userName",userName);
		params.put("roleFlowList",roleFlowList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> list0 = gateUserManageBiz.userList(params);
		model.addAttribute("list", list0);
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByOrgFlow(null, wsId);
		Map<String, List<String>> sysUserRoleMap = new HashMap<String, List<String>>();
		for (SysUserRole sysUserRole : sysUserRoleList) {
			String userFlow = sysUserRole.getUserFlow();
			if (sysUserRoleMap.containsKey(userFlow)) {
				List<String> list = sysUserRoleMap.get(userFlow);
				list.add(sysUserRole.getRoleFlow());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(sysUserRole.getRoleFlow());
				sysUserRoleMap.put(userFlow, list);
			}
		}
		model.addAttribute("sysUserRoleMap", sysUserRoleMap);
		return "portal/user/userList";
	}
	@RequestMapping(value = {"/szUserList" })
	public String szUserList (Model model, Integer currentPage, HttpServletRequest request, String userCode, String userName) throws Exception{
		String roleFlow0 = InitConfig.getSysCfg("portals_patient_role_flow");
		String roleFlow1 = InitConfig.getSysCfg("portals_doctor_role_flow");
		List<String> roleFlowList = new ArrayList<>();
		roleFlowList.add(roleFlow0);
		roleFlowList.add(roleFlow1);
		Map<String,Object> params=new HashMap<>();
		params.put("userCode",userCode);
		params.put("userName",userName);
		params.put("roleFlowList",roleFlowList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> list0 = gateUserManageBiz.userList(params);
		model.addAttribute("list", list0);
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByOrgFlow(null, wsId);
		Map<String, List<String>> sysUserRoleMap = new HashMap<String, List<String>>();
		for (SysUserRole sysUserRole : sysUserRoleList) {
			String userFlow = sysUserRole.getUserFlow();
			if (sysUserRoleMap.containsKey(userFlow)) {
				List<String> list = sysUserRoleMap.get(userFlow);
				list.add(sysUserRole.getRoleFlow());
			} else {
				List<String> list = new ArrayList<String>();
				list.add(sysUserRole.getRoleFlow());
				sysUserRoleMap.put(userFlow, list);
			}
		}
		model.addAttribute("sysUserRoleMap", sysUserRoleMap);
		return "portal/user/szUserList";
	}
}
