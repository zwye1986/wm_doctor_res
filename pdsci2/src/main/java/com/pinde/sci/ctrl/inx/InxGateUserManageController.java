package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IGateUserManageBiz;
import com.pinde.sci.biz.inx.IinxColumnManageBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.enums.sys.RoleLevelEnum;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/gateuser")
public class InxGateUserManageController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(InxGateUserManageController.class);
	@Autowired
	private IinxColumnManageBiz columnBiz;
	@Autowired
	private IGateUserManageBiz gateUserManageBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserBiz userBiz;
	
	@RequestMapping(value="/main")
	public String main(){
		return "sys/gateuser/main";
	}
	@RequestMapping(value="/edit")
	public String edit(String userFlow,Model model){

		SysRole sysRole = new SysRole();
		sysRole.setWsId((String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID));
		sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
		sysRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		model.addAttribute("sysRoleList",sysRoleList);
		if(StringUtil.isNotBlank(userFlow))
		{
			SysUser user=userBiz.readSysUser(userFlow);
			model.addAttribute("user",user);
			Map<String,Object> roleFlows=gateUserManageBiz.getUserRoles(userFlow);
			model.addAttribute("roleFlows",roleFlows);
		}
		return "sys/gateuser/edit";
	}
	@RequestMapping(value={"/saveUser"},method= RequestMethod.POST)
	public @ResponseBody String save(SysUser user,String[] roleFlows){
		//新增用户是判断
		if(StringUtil.isBlank(user.getUserFlow())){
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCode(user.getUserCode());
			if(old!=null){
				return GlobalConstant.USER_CODE_REPETE;
			}
		}else{
			String userFlow = user.getUserFlow();
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
			if(old!=null){
				return GlobalConstant.USER_CODE_REPETE;
			}
		}
		try {
			gateUserManageBiz.saveUser(user, roleFlows);
		}catch (Exception e)
		{
			return GlobalConstant.SAVE_FAIL;
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	@RequestMapping(value = {"/userList" })
	public String userList (Model model, Integer currentPage, HttpServletRequest request, String userCode) throws Exception{
		Map<String,Object> params=new HashMap<>();
		params.put("userCode",userCode);
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list = gateUserManageBiz.userList(params);
		model.addAttribute("list",list);
		return "sys/gateuser/userList";
	}
}
