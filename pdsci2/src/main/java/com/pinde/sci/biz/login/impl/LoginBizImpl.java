package com.pinde.sci.biz.login.impl;

import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.model.OscaOrgMenu;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.model.SysUserRole;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.osca.IOscaOrgMenuBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.dao.base.SysLoginAbilityMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class LoginBizImpl implements ILoginBiz{

	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOscaOrgMenuBiz oscaOrgMenuBiz;
	@Autowired
	private SysLoginAbilityMapper abilityMapper;

	@Override
	public void addSysLog(SysLog sysLog){
		logMapper.insert(sysLog);
	}

	@Override
	public List<SysLog> getCurrWsSysLog(String startDate,String endDate,SysLog log) {
		List<String> wsIds = new ArrayList<String>();
        wsIds.add(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
		wsIds.add(GlobalContext.getCurrentWsId());
		SysLogExample example = new SysLogExample();
        SysLogExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andWsIdIn(wsIds);
		if(StringUtil.isNotBlank(log.getUserName())){
			criteria.andUserNameEqualTo(log.getUserName());
		}
		if(StringUtil.isNotBlank(startDate)){
			criteria.andLogTimeGreaterThanOrEqualTo(DateUtil.getDate(startDate)+"000000");
		}
		if(StringUtil.isNotBlank(endDate)){
			criteria.andLogTimeLessThanOrEqualTo(DateUtil.getDate(endDate)+"235959");
		}

		example.setOrderByClause("LOG_TIME DESC");
		return logMapper.selectByExample(example);
	}

	@Override
	public void loadSysRole(String userFlow){
		//加载所拥有的权限
		List<String> currUserMenuIdList = new ArrayList<String>();
		List<String> currUserMenuSetIdList = new ArrayList<String>();
		List<String> currUserModuleIdList = new ArrayList<String>();
		List<String> currUserWorkStationIdList = new ArrayList<String>();
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(userFlow);
		List<String> currRoleList = new ArrayList<String>();
		for(SysUserRole userRole : sysUserRoleList){
			if(StringUtil.isNotBlank(userRole.getRoleFlow())) {
				currRoleList.add(userRole.getRoleFlow());
				List<String> menuIds = roleBiz.getPopedom(userRole.getRoleFlow());
				currUserMenuIdList.addAll(menuIds);
				for (String menuId : currUserMenuIdList) {
					String setId = InitConfig.getMenuSetIdByMenuId(menuId);
					if (!currUserMenuSetIdList.contains(setId) && setId != null) {
						currUserMenuSetIdList.add(setId);
					}
					String moduleId = InitConfig.getModuleIdByMenuId(menuId);
					if (!currUserModuleIdList.contains(moduleId) && moduleId != null) {
						currUserModuleIdList.add(moduleId);
					}
					String wsid = InitConfig.getWorkStationIdByMenuId(menuId);
					if (!currUserWorkStationIdList.contains(wsid) && wsid != null) {
						currUserWorkStationIdList.add(wsid);
					}
				}
			}
		}

        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST, currRoleList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_ID_LIST, currUserMenuIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_SET_ID_LIST, currUserMenuSetIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MODULE_ID_LIST, currUserModuleIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WORKSTATION_ID_LIST, currUserWorkStationIdList);

        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST_BACKUP, currRoleList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_ID_LIST_BACKUP, currUserMenuIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_SET_ID_LIST_BACKUP, currUserMenuSetIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MODULE_ID_LIST_BACKUP, currUserModuleIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WORKSTATION_ID_LIST_BACKUP, currUserWorkStationIdList);
	}
	@Override
	public Map<String,Object> loadSysRole2(String userFlow){
		Map<String,Object> roleUrl=new HashMap<>();
		//加载所拥有的权限
		List<String> currUserMenuIdList = new ArrayList<String>();
		List<String> currUserMenuSetIdList = new ArrayList<String>();
		List<String> currUserModuleIdList = new ArrayList<String>();
		List<String> currUserWorkStationIdList = new ArrayList<String>();
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(userFlow);
		List<String> currRoleList = new ArrayList<String>();
		for(SysUserRole userRole : sysUserRoleList){
			currRoleList.add(userRole.getRoleFlow());
			List<String> menuIds = roleBiz.getPopedom(userRole.getRoleFlow());
			currUserMenuIdList.addAll(menuIds);
			for(String menuId : currUserMenuIdList){
				String setId = InitConfig.getMenuSetIdByMenuId(menuId);
				if(!currUserMenuSetIdList.contains(setId)&&setId!=null){
					currUserMenuSetIdList.add(setId);
				}
				String moduleId = InitConfig.getModuleIdByMenuId(menuId);
				if(!currUserModuleIdList.contains(moduleId)&&moduleId!=null){
					currUserModuleIdList.add(moduleId);
				}
				String wsid = InitConfig.getWorkStationIdByMenuId(menuId);
				if(!currUserWorkStationIdList.contains(wsid)&&wsid!=null){
					currUserWorkStationIdList.add(wsid);
				}
			}

		}


        roleUrl.put(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST, currRoleList);
        roleUrl.put(com.pinde.core.common.GlobalConstant.CURRENT_MENU_ID_LIST, currUserMenuIdList);
        roleUrl.put(com.pinde.core.common.GlobalConstant.CURRENT_MENU_SET_ID_LIST, currUserMenuSetIdList);
        roleUrl.put(com.pinde.core.common.GlobalConstant.CURRENT_MODULE_ID_LIST, currUserModuleIdList);
        roleUrl.put(com.pinde.core.common.GlobalConstant.CURRENT_WORKSTATION_ID_LIST, currUserWorkStationIdList);
		return roleUrl;
	}

	@Override
	public void loadSysRole(String userFlow,String workStation){
		//加载所拥有的权限
		List<String> currUserMenuIdList = new ArrayList<String>();
		List<String> currUserMenuSetIdList = new ArrayList<String>();
		List<String> currUserModuleIdList = new ArrayList<String>();
		List<String> currUserWorkStationIdList = new ArrayList<String>();
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(userFlow);
		List<String> currRoleList = new ArrayList<String>();
		for(SysUserRole userRole : sysUserRoleList){
			if(workStation.equals(userRole.getWsId())){
				currRoleList.add(userRole.getRoleFlow());
				List<String> menuIds = roleBiz.getPopedom(userRole.getRoleFlow());
				currUserMenuIdList.addAll(menuIds);
				for(String menuId : currUserMenuIdList){
					String setId = InitConfig.getMenuSetIdByMenuId(menuId);
					if(!currUserMenuSetIdList.contains(setId)&&setId!=null){
						currUserMenuSetIdList.add(setId);
					}
					String moduleId = InitConfig.getModuleIdByMenuId(menuId);
					if(!currUserModuleIdList.contains(moduleId)&&moduleId!=null){
						currUserModuleIdList.add(moduleId);
					}
					String wsid = InitConfig.getWorkStationIdByMenuId(menuId);
					if(!currUserWorkStationIdList.contains(wsid)&&wsid!=null){
						currUserWorkStationIdList.add(wsid);
					}
				}
			}
		}
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST, currRoleList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_ID_LIST, currUserMenuIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_SET_ID_LIST, currUserMenuSetIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MODULE_ID_LIST, currUserModuleIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WORKSTATION_ID_LIST, currUserWorkStationIdList);

        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST_BACKUP, currRoleList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_ID_LIST_BACKUP, currUserMenuIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_SET_ID_LIST_BACKUP, currUserMenuSetIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MODULE_ID_LIST_BACKUP, currUserModuleIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WORKSTATION_ID_LIST_BACKUP, currUserWorkStationIdList);
	}

	@Override
	public void loadSysRoleOsce(String userFlow) {
		//加载所拥有的权限
		List<String> currUserMenuIdList = new ArrayList<String>();
		List<String> currUserMenuSetIdList = new ArrayList<String>();
		List<String> currUserModuleIdList = new ArrayList<String>();
		List<String> currUserWorkStationIdList = new ArrayList<String>();
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(userFlow);
		List<String> currRoleList = new ArrayList<String>();
		for(SysUserRole userRole : sysUserRoleList){
			currRoleList.add(userRole.getRoleFlow());
			List<String> menuIds = roleBiz.getPopedom(userRole.getRoleFlow());
			currUserMenuIdList.addAll(menuIds);
			/********  osca二级权限控制  ********/
			SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			SysUser user=userBiz.findByFlow(userFlow);
			String isAdmin="";
            if (sysOrg != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(sysOrg.getIsExamOrg())) {
				for(int i=0;i<currUserMenuIdList.size();i++){
                    if (!com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
						if("osca-xy".equals(InitConfig.getModuleIdByMenuId(currUserMenuIdList.get(i)))||
								"osca-sjbmgl".equals(InitConfig.getModuleIdByMenuId(currUserMenuIdList.get(i)))){
                            isAdmin = com.pinde.core.common.GlobalConstant.FLAG_N;
							break;
						}
						if("osca-gly".equals(InitConfig.getModuleIdByMenuId(currUserMenuIdList.get(i)))){
                            isAdmin = com.pinde.core.common.GlobalConstant.FLAG_Y;
						}
					}
					if("osca-gly-jcxxgl-khxm".equals(currUserMenuIdList.get(i))){
						currUserMenuIdList.remove(i);
					}
					if("osca-gly-jcxxgl-pfbd".equals(currUserMenuIdList.get(i))){
						currUserMenuIdList.remove(i);
					}
					if("osca-gly-jcxxgl-kgxx".equals(currUserMenuIdList.get(i))){
						currUserMenuIdList.remove(i);
					}
				}
                if (isAdmin.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) && StringUtil.isNotBlank(GlobalContext.getCurrentUser().getOrgFlow())) {
					OscaOrgMenu oscaOrgMenu=new OscaOrgMenu();
					oscaOrgMenu.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
					List<OscaOrgMenu> oscaOrgMenus=oscaOrgMenuBiz.searchAllOrgMenu(oscaOrgMenu);
					if(oscaOrgMenus!=null&&oscaOrgMenus.size()>0){
						for (OscaOrgMenu oscaOM:oscaOrgMenus) {
							currUserMenuIdList.add(oscaOM.getMenuId());
						}
					}
				}
			}
			/********  osca二级权限控制  ********/
			for(String menuId : currUserMenuIdList){
				String setId = InitConfig.getMenuSetIdByMenuId(menuId);
				if(!currUserMenuSetIdList.contains(setId)&&setId!=null){
					currUserMenuSetIdList.add(setId);
				}
				String moduleId = InitConfig.getModuleIdByMenuId(menuId);
				if(!currUserModuleIdList.contains(moduleId)&&moduleId!=null){
					currUserModuleIdList.add(moduleId);
				}
				String wsid = InitConfig.getWorkStationIdByMenuId(menuId);
				if(!currUserWorkStationIdList.contains(wsid)&&wsid!=null&&!wsid.equals("res")){
					currUserWorkStationIdList.add(wsid);
				}
			}

			/*****  osca非考点的基地管理员登录拦截 ****/
			if(sysOrg!=null){
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(sysOrg.getIsExamOrg()) || StringUtil.isBlank(sysOrg.getIsExamOrg())) {
                    if (!com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode()) &&
							currUserModuleIdList!=null&&currUserModuleIdList.size()>0){
						for(int i=0;i<currUserModuleIdList.size();i++){
							if("osca-gly".equals(currUserModuleIdList.get(i))){
								if(currUserWorkStationIdList!=null&&currUserWorkStationIdList.size()>0){
									for(int j=0;j<currUserWorkStationIdList.size();j++){
										if("osca".equals(currUserWorkStationIdList.get(j))){
											currUserWorkStationIdList.remove(j);
										}
									}
								}
							}
						}
					}
				}
			}
			/*****  osca非考点的基地管理员登录拦截 ****/

		}

        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST, currRoleList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_ID_LIST, currUserMenuIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_SET_ID_LIST, currUserMenuSetIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MODULE_ID_LIST, currUserModuleIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WORKSTATION_ID_LIST, currUserWorkStationIdList);

        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST_BACKUP, currRoleList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_ID_LIST_BACKUP, currUserMenuIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MENU_SET_ID_LIST_BACKUP, currUserMenuSetIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_MODULE_ID_LIST_BACKUP, currUserModuleIdList);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WORKSTATION_ID_LIST_BACKUP, currUserWorkStationIdList);
	}

	@Override
	public SysLoginAbility readAbility(String userCode) {
		return abilityMapper.selectByPrimaryKey(userCode);
	}

	@Override
	public void updateLoginAbility(String userCode) {
		SysLoginAbility record = new SysLoginAbility();
		record.setUserCode(userCode);
        record.setIsLock(com.pinde.core.common.GlobalConstant.FLAG_N);
		record.setLoginTime(DateUtil.getCurrDateTime());
		SysLoginAbility ability = abilityMapper.selectByPrimaryKey(userCode);
		if(null == ability){
			//初次错误
			record.setLoginNum("1");
			GeneralMethod.setRecordInfo(record,true);
			abilityMapper.insertSelective(record);
		}else if(Integer.valueOf(ability.getLoginNum()) < 5){
			//初次错误
			record.setLoginNum(Integer.valueOf(ability.getLoginNum())+1+"");
			if(Integer.valueOf(ability.getLoginNum()) == 4){
                record.setIsLock(com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
			GeneralMethod.setRecordInfo(record,false);
			abilityMapper.updateByPrimaryKeySelective(record);
		}else{
			//10分钟解锁后，重新计算错误次数
			record.setLoginNum("1");
			GeneralMethod.setRecordInfo(record,false);
			abilityMapper.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public void deleteLoginAbility(String userCode) {
		abilityMapper.deleteByPrimaryKey(userCode);
	}

	@Override
	public void registRecruitUser(SysUser user) {
		//将注册信息保存下来  用户状态为新增状态
		user.setUserFlow(PkUtil.getUUID());
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Activated.getId());
		user.setStatusDesc(UserStatusEnum.Activated.getName());
		GeneralMethod.setRecordInfo(user, true);
		userBiz.insertUser(user);
		if(StringUtil.isNotBlank(InitConfig.getSysCfg("recruit_doctor_role_flow"))){
			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
            String currWsId = com.pinde.core.common.GlobalConstant.RECRUIT_WS_ID;
			userRole.setWsId(currWsId);
			userRole.setRoleFlow(InitConfig.getSysCfg("recruit_doctor_role_flow"));
			userRole.setAuthTime(DateUtil.getCurrDate());
			userRoleBiz.saveSysUserRole(userRole);
		}
	}
}
