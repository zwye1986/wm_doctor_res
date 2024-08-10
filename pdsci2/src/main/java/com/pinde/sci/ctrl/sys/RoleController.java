package com.pinde.sci.ctrl.sys;


import com.pinde.core.entyties.SysDict;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.portal.IPortalColumnManageBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sys.RoleLevelEnum;
import com.pinde.sci.model.mo.PortalColumn;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysRole;
import com.pinde.sci.model.mo.SysRoleAuthGx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller  
@RequestMapping("/sys/role")
public class RoleController extends GeneralController{

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IPortalColumnManageBiz columnBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IDictBiz dictBiz;


	/**
	 * 显示角色列表
	 * @param sysRole
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "/list",method={RequestMethod.POST,RequestMethod.GET})
	public String list(String currWsId,String roleName,Model model,HttpServletRequest request) {
		SysRole sysRole = new SysRole();
		if(StringUtil.isBlank(currWsId)){ 
			currWsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
		}
		sysRole.setWsId(currWsId);
		
		if (StringUtil.isNotBlank(roleName)) {	//按角色名称检索
			sysRole.setRoleName(roleName);
		}
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		model.addAttribute("sysRoleList", sysRoleList);
		return "sys/role/list";
	}
	
	/**
	 * 编辑角色
	 * @param roleFlow
	 * @param model
	 * @param request
	 * @return
	 */	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(@RequestParam(value="roleFlow",required=false) String roleFlow,Model model,HttpServletRequest request) {
		if(StringUtil.isNotBlank(roleFlow)){
			SysRole sysRole = roleBiz.read(roleFlow);
			model.addAttribute("sysRole", sysRole);
		}
		SysRole sysRole = new SysRole();
		sysRole.setWsId((String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID));
		sysRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		model.addAttribute("sysRoleList", sysRoleList);
		return "sys/role/edit";
	}
	
	/**
	 * 保存角色
	 * @param sysRole
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(SysRole sysRole,Model model,HttpServletRequest request) throws Exception{		
		sysRole.setWsName(InitConfig.getWorkStationName(sysRole.getWsId()));
		if(StringUtil.isNotBlank(sysRole.getParentRoleFlow())){
			String parentRoleName = null;
			SysRole parentRole = InitConfig.getSysRole(sysRole.getParentRoleFlow());
			if(parentRole!=null){
				parentRoleName = parentRole.getRoleName();
			}
			sysRole.setParentRoleName(parentRoleName);
		}
		sysRole.setRoleLevelName(RoleLevelEnum.getNameById(sysRole.getRoleLevelId()));
		if(GlobalConstant.FLAG_N.equals(sysRole.getAllowRegFlag())){
			sysRole.setRegPageId("");
			sysRole.setRegPageName("");
		}
		roleBiz.save(sysRole);
		InitConfig._loadRole(request.getServletContext());
		return GlobalConstant.SAVE_SUCCESSED;
		
	}

	
	/**
	 * 保存角色排序码
	 * @param sysRole
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrder",method=RequestMethod.POST)
	@ResponseBody
	public String saveOrder(String [] roleFlow,Model model,HttpServletRequest request) throws Exception{
		roleBiz.saveOrder(roleFlow);
		InitConfig._loadRole(request.getServletContext());
		return GlobalConstant.SAVE_SUCCESSED;
		
	}
	
	/**
	 * 删除角色
	 * @param roleFlow
	 * @return
	 */	
	@RequestMapping(value="/delete",method=RequestMethod.GET) 
	@ResponseBody
	public String delete(@RequestParam(value="roleFlow",required=true) String roleFlow,@RequestParam(value="recordStatus",required=true) String recordStatus) {
		roleBiz.delete(roleFlow,recordStatus);
		return GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 删除角色，物理删除
	 * @param roleFlow
	 * @return
     */
	@RequestMapping(value="/realDelete",method=RequestMethod.GET)
	@ResponseBody
	public String realDelete(@RequestParam(value="roleFlow",required=true) String roleFlow) {
		boolean flag = roleBiz.realDelete(roleFlow);
		return flag ? GlobalConstant.DELETE_SUCCESSED : "删除失败！已使用的角色不能删除！";
	}
	
	/**
	 * 显示角色所拥有的功能
	 * @param roleFlow
	 * @param wsId
	 * @param model
	 * @return
	 */	
	@RequestMapping(value="/getpop",method=RequestMethod.GET)
	public String getpop(@RequestParam(value="roleFlow",required=true) String roleFlow,
						 @RequestParam(value="wsId",required=true) String wsId,Model model) {
		SysRole sysRole = roleBiz.read(roleFlow);
		model.addAttribute("sysRole", sysRole);
		model.addAttribute("workStation", InitConfig.getWorkStation(wsId));
		List<String> menuIds = roleBiz.getPopedom(roleFlow);
		model.addAttribute("menuIds", menuIds);	
		return "sys/role/popedom";
	}
	/**
	 * 高校子角色数据权限
	 * @param roleFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getDataAuth",method=RequestMethod.GET)
	public String getDataAuth(@RequestParam(value="roleFlow",required=true) String roleFlow,
						 @RequestParam(value="wsId",required=true) String wsId,Model model) {
		List<String> schoolList = new ArrayList<>();
		SysRoleAuthGx sysRoleAuthGx = roleBiz.readRoleAuth(roleFlow);
		if(null != sysRoleAuthGx && StringUtil.isNotEmpty(sysRoleAuthGx.getSchools())){
			model.addAttribute("sysRoleAuthGx", sysRoleAuthGx);
			String schools = sysRoleAuthGx.getSchools();
			String[] schoolsSplit = schools.split(",");
			if(null != schoolsSplit){
				for (int i = 0; i < schoolsSplit.length; i++) {
					schoolList.add(schoolsSplit[i]);
				}
			}
		}
		model.addAttribute("schools", schoolList);
		SysRole sysRole = roleBiz.read(roleFlow);
		model.addAttribute("sysRole", sysRole);
		model.addAttribute("workStation", InitConfig.getWorkStation(wsId));
		List<SysOrg> sysOrgs = orgBiz.searchAllSysOrg(new SysOrg());
		model.addAttribute("sysOrgs", sysOrgs);
		List<SysDict> sysDicts = dictBiz.searchDictListByDictTypeId("SendSchool");
		List<SysDict> arrays = sysDicts.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
				-> new TreeSet<>(Comparator.comparing(SysDict :: getDictName))), ArrayList::new));
		model.addAttribute("sysDicts", arrays);
		return "sys/role/dataAuth";
	}

	/**
	 * 保存角色对应的权限
	 * @param sysRole
	 * @param orgFlow
	 * @param dictNames
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveRoleAuth",method=RequestMethod.POST)
	@ResponseBody
	public String saveRoleAuth(SysRole sysRole,String orgFlow,@RequestParam(value="dictName",required=false) String [] dictNames,Model model) {
		roleBiz.saveRoleAuth(sysRole, orgFlow,dictNames);
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 显示角色所有可以维护的栏目信息
	 * @param roleFlow
	 * @param wsId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getcol",method=RequestMethod.GET)
	public String getcol(@RequestParam(value="roleFlow",required=true) String roleFlow,
						 @RequestParam(value="wsId",required=true) String wsId,Model model) {
		SysRole sysRole = roleBiz.read(roleFlow);
		model.addAttribute("sysRole", sysRole);
		model.addAttribute("workStation", InitConfig.getWorkStation(wsId));
		List<String> columnIds = roleBiz.getColumn(roleFlow);
		model.addAttribute("columnIds", columnIds);
		List<PortalColumn> colList = columnBiz.getAll(GlobalConstant.RECORD_STATUS_Y,null);//获取所有已启用栏目
		model.addAttribute("colList", colList);
		return "sys/role/column";
	}
	
	/**
	 * 保存角色对应的功能
	 * @param sysRole
	 * @param menuIds
	 * @param model
	 * @return
	 */	
	@RequestMapping(value="/savepop",method=RequestMethod.POST)
	@ResponseBody
	public String savepop(SysRole sysRole,@RequestParam(value="menuId",required=false) String [] menuIds,Model model) {
		roleBiz.savePop(sysRole, menuIds);
		return GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/savecol",method=RequestMethod.POST)
	@ResponseBody
	public String savecol(SysRole sysRole,@RequestParam(value="columnId",required=false) String [] columnIds,Model model) {
		roleBiz.saveCol(sysRole, columnIds);
		return GlobalConstant.SAVE_SUCCESSED;
	}

}
