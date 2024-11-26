package com.pinde.sci.biz.sys.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysRoleMapper;
import com.pinde.sci.dao.base.SysUserRoleMapper;
import com.pinde.sci.dao.jsres.SysRoleExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SysUserRoleExample.Criteria;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor=Exception.class)
public class UserRoleBizImpl implements IUserRoleBiz{
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysRoleExtMapper roleExtMapper;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;

	@Override
	public List<SysUserRole> getByUserFlow(String userFlow) {
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserFlowEqualTo(userFlow);
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return sysUserRoleMapper.selectByExample(example);
	}

	@Override
	public List<SysUserRole> searchUserRole(SysUserRole userRole){
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(userRole.getOrgFlow())){
			criteria.andOrgFlowEqualTo(userRole.getOrgFlow());
		}
		if(StringUtil.isNotBlank(userRole.getRoleFlow())){
			criteria.andRoleFlowEqualTo(userRole.getRoleFlow());
		}
		if(StringUtil.isNotBlank(userRole.getWsId())){
			criteria.andWsIdEqualTo(userRole.getWsId());
		}
		if(StringUtil.isNotBlank(userRole.getUserFlow())){
			criteria.andUserFlowEqualTo(userRole.getUserFlow());
		}
		return sysUserRoleMapper.selectByExample(example);
	}

	@Override
	public List<SysUserRole> getByOrgFlow(String orgFlow,String wsId) {
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		criteria.andWsIdEqualTo(wsId);
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return sysUserRoleMapper.selectByExample(example);
	}

	@Override
	public List<SysUserRole> getByUserFlow(List<String> userFlows, String wsid) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userFlows",userFlows);
		paramMap.put("wsid",wsid);

		return roleExtMapper.getByUserFlow(paramMap);
	}



	@Override
	public void saveAllot(String userFlow,String orgFlow,String workStationId, String[] roleFlows) {
		//将原先该用户的角色都删除
		SysUserRole update = new SysUserRole();
//		update.setUserFlow(userFlow);
		update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		GeneralMethod.setRecordInfo(update, false);
		SysUserRoleExample example = new SysUserRoleExample();
		com.pinde.sci.model.mo.SysUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserFlowEqualTo(userFlow)
		.andWsIdEqualTo(workStationId)
		.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		sysUserRoleMapper.updateByExampleSelective(update, example);

		//重新添加角色
		if(roleFlows!=null){
			for(String rolwf : roleFlows){
				SysUserRole insert = new SysUserRole();
				insert.setUserFlow(userFlow);
				insert.setOrgFlow(orgFlow);
				insert.setWsId(workStationId);
				insert.setRoleFlow(rolwf);
				insert.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				insert.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(insert, true);
				sysUserRoleMapper.insert(insert);

				String teacherRole = InitConfig.getSysCfg("res_teacher_role_flow");
				if(teacherRole.equals(rolwf)){
					// 基地是否开通过程
					String orgCfgCode = "jsres_" + orgFlow + "_guocheng";
					JsresPowerCfg jsresPowerCfg = jsResPowerCfgBiz.read(orgCfgCode);
					if(null != jsresPowerCfg && GlobalConstant.FLAG_Y.equals(jsresPowerCfg.getCfgValue()) && "Passed".equals(jsresPowerCfg.getCheckStatusId())){
						// 带教默认APP登录权限
						String cfgCode = "jsres_teacher_app_login_"+userFlow;
						JsresPowerCfg jsresCfg = new JsresPowerCfg();
						jsresCfg.setCfgCode(cfgCode);
						jsresCfg.setCfgValue("Y");
						jsresCfg.setCfgDesc("是否开放带教app权限");
						jsresCfg.setCheckStatusId("Passed");
						jsresCfg.setCheckStatusName("审核通过");
						jsresCfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
						jsResPowerCfgBiz.save(jsresCfg);
					}
				}
			}
		}

	}

	@Override
	public int saveSysUserRole(SysUserRole userRole){
		if(userRole != null){
			if(StringUtil.isNotBlank(userRole.getRecordFlow())){
				GeneralMethod.setRecordInfo(userRole, false);
				return sysUserRoleMapper.updateByPrimaryKeySelective(userRole);
			}else{
				userRole.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(userRole, true);
				return sysUserRoleMapper.insertSelective(userRole);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int addSysUserRole(SysUserRole userRole){
		if(userRole != null){
			SysUserRoleExample example = new SysUserRoleExample();
			example.createCriteria().andRoleFlowEqualTo(userRole.getRoleFlow()).andUserFlowEqualTo(userRole.getUserFlow());
			List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
			if(userRoleList==null || userRoleList.isEmpty()){
				userRole.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(userRole, true);
				return sysUserRoleMapper.insertSelective(userRole);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<SysUserRole> getByUserFlowAndWsid(String userFlow, String wsid) {
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		if(StringUtil.isNotBlank(wsid)){
			criteria.andWsIdEqualTo(wsid);
		}
		return sysUserRoleMapper.selectByExample(example);
	}

	@Override
	public SysRole read(String roleFlow) {
		if(StringUtil.isNotBlank(roleFlow)){
			return roleMapper.selectByPrimaryKey(roleFlow);
		}else {
			return null;
		}
	}

	@Override
	public List<SysRole> getByWisd(String wisd) {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(wisd)){
			criteria.andWsIdEqualTo(wisd);
		}
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return roleMapper.selectByExample(example);
	}


//	@Override
//	public List<SysUserRole> searchUserByRoles(List<String> roleList,String orgFlow){
//		SysUserRoleExample example = new SysUserRoleExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andRoleFlowIn(roleList).andOrgFlowEqualTo(orgFlow);
//		return sysUserRoleMapper.selectByExample(example);
//	}

	@Override
	public int saveSysUserRole(String userFlow, String roleFlow, String wsId) {
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andRoleFlowEqualTo(roleFlow).andUserFlowEqualTo(userFlow);
		List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
		if(userRoleList.size()==0){
			SysUserRole userRole = new SysUserRole();
			userRole.setRecordFlow(PkUtil.getUUID());
			userRole.setUserFlow(userFlow);
			userRole.setRoleFlow(roleFlow);
			userRole.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			if(StringUtil.isBlank(wsId)){
				wsId = GlobalContext.getCurrentWsId();
			}
			userRole.setWsId(wsId);
			userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			userRole.setAuthTime(DateUtil.getCurrDateTime());
			GeneralMethod.setRecordInfo(userRole, true);
			return sysUserRoleMapper.insert(userRole);
		}else if(userRoleList.size()>=1){
			SysUserRole userRole = userRoleList.get(0);
			GeneralMethod.setRecordInfo(userRole, false);
			if(userRole.getRecordStatus().equals(GlobalConstant.RECORD_STATUS_Y)){
				userRole.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			}else {
				userRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			}
			return sysUserRoleMapper.updateByPrimaryKeySelective(userRole);
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public SysUserRole readUserRole(String userFlow, String roleFlow){
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andRoleFlowEqualTo(roleFlow).andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		SysUserRole role = null;
		List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
		if(userRoleList!=null && userRoleList.size()>0){
			role = userRoleList.get(0);
		}
		return role;
	}

	@Override
	public SysUserRole readUserRoleNoReoordStatus(String userFlow, String roleFlow){
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andRoleFlowEqualTo(roleFlow).andUserFlowEqualTo(userFlow);
		SysUserRole role = null;
		List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
		if(userRoleList!=null && userRoleList.size()>0){
			role = userRoleList.get(0);
		}
		return role;
	}

	@Override
	public void saveSysUserRoleNoDel(String userFlow, String roleFlow) {
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andRoleFlowEqualTo(roleFlow).andUserFlowEqualTo(userFlow);
		List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
		if(userRoleList.size()==0){
			SysUserRole userRole = new SysUserRole();
			userRole.setRecordFlow(PkUtil.getUUID());
			userRole.setUserFlow(userFlow);
			userRole.setRoleFlow(roleFlow);
			userRole.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			userRole.setWsId(GlobalContext.getCurrentWsId());
			userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			userRole.setAuthTime(DateUtil.getCurrDateTime());
			GeneralMethod.setRecordInfo(userRole, true);
			sysUserRoleMapper.insert(userRole);
		}
	}

	@Override
	public void saveSysUserRoleNoDel(String userFlow, String roleFlow, String docRole) {
		SysUserRole userRole = new SysUserRole();
		userRole.setRecordFlow(PkUtil.getUUID());
		userRole.setUserFlow(userFlow);
		userRole.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		userRole.setAuthTime(DateUtil.getCurrDateTime());
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRoleFlowEqualTo(roleFlow).andWsIdEqualTo(GlobalContext.getCurrentWsId());
		List<SysUserRole> userRoleList = sysUserRoleMapper.selectByExample(example);
		if(userRoleList.size()==0){//学籍学生角色未绑定
			userRole.setRoleFlow(roleFlow);
			userRole.setWsId(GlobalContext.getCurrentWsId());
			GeneralMethod.setRecordInfo(userRole, true);
			sysUserRoleMapper.insert(userRole);
		}
		if(StringUtil.isNotBlank(docRole)){
			example.clear();
			example.createCriteria().andUserFlowEqualTo(userFlow).andRoleFlowEqualTo(docRole)
					.andWsIdEqualTo(GlobalConstant.RES_WS_ID).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<SysUserRole> docRoleList = sysUserRoleMapper.selectByExample(example);
			if(docRoleList.size() == 0){
				userRole.setRecordFlow(PkUtil.getUUID());
				userRole.setRoleFlow(docRole);
				userRole.setWsId(GlobalConstant.RES_WS_ID);
				GeneralMethod.setRecordInfo(userRole, true);
				sysUserRoleMapper.insert(userRole);
			}
		}
	}
	@Override
	public boolean userExistRole(SysUser sysUser, SysRole sysRole) {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria sysRoleCriteria = example.createCriteria();
		sysRoleCriteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(sysRole.getRoleFlow())){
			sysRoleCriteria.andRoleFlowEqualTo(sysRole.getRoleFlow());
		}
		if(StringUtil.isNotBlank(sysRole.getWsId())){
			sysRoleCriteria.andWsIdEqualTo(sysRole.getWsId());
		}
		if(StringUtil.isNotBlank(sysRole.getRoleName())){
			sysRoleCriteria.andRoleNameEqualTo(sysRole.getRoleName());
		}
		List<SysRole> roleList = sysRoleMapper.selectByExample(example);
		if(roleList != null && roleList.size()>0 && StringUtil.isNotBlank(sysUser.getUserFlow())){
			List<String> roleFlowList = new ArrayList<>();
			for(SysRole role : roleList){
				roleFlowList.add(role.getRoleFlow());
			}
			SysUserRoleExample userRoleExample = new SysUserRoleExample();
			SysUserRoleExample.Criteria criteria = userRoleExample.createCriteria();
			criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			criteria.andRoleFlowIn(roleFlowList);
			criteria.andUserFlowEqualTo(sysUser.getUserFlow());
			int count = sysUserRoleMapper.countByExample(userRoleExample);
			if(count > 0){
				return true;
			}
		}

		return false;
	}

	@Override
	public SysRole getByRoleName(String roleName) {
		SysRoleExample example = new SysRoleExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRoleNameEqualTo(roleName).andWsIdEqualTo("res");
		List<SysRole> roleList = roleMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(roleList)) {
			return roleList.get(0);
		}
		return null;
	}

	/**
	 * 范围更新，范围内的，先删后改，范围外的不变
	 * @param userFlow
	 * @param wsId
	 * @param userRoles
	 */
	@Override
	public void batchUpdateUserRoles(String userFlow, String wsId, List<String> userRoles, List<String> roleRanges) {
		List<SysUserRole> currUserRoles = getByUserFlowAndWsid(userFlow, wsId);
		currUserRoles= currUserRoles.stream().filter(vo -> roleRanges.contains(vo.getRoleFlow())).collect(Collectors.toList());
		Map<String, SysUserRole> flowToUserRoleMap = currUserRoles.stream().collect(Collectors.toMap(vo -> vo.getUserFlow() + vo.getRoleFlow(), vo -> vo, (vo1, vo2) -> vo1));
		// 将原来的失效掉
		currUserRoles.stream().forEach(vo -> vo.setRecordStatus(GlobalConstant.RECORD_STATUS_N));
		// 使用新的role
		List<SysUserRole> updateUserRoleList = new ArrayList<>();
		List<SysUserRole> insertUserRoleList = new ArrayList<>();
		for (String userRole : userRoles) {
			SysUserRole sysUserRole = null;
			if(flowToUserRoleMap.get(userFlow + userRole) != null) {
				sysUserRole = flowToUserRoleMap.get(userFlow + userRole);
				sysUserRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				GeneralMethod.setRecordInfo(sysUserRole, false);
				updateUserRoleList.add(sysUserRole); // 已存在，本次更新
			}else {
				sysUserRole = new SysUserRole();
				GeneralMethod.setRecordInfo(sysUserRole, true);
				sysUserRole.setUserFlow(userFlow);
				sysUserRole.setRoleFlow(userRole);
				sysUserRole.setWsId(wsId);
				sysUserRole.setRecordFlow(PkUtil.getUUID());
				sysUserRole.setAuthTime(DateUtil.getCurrDate());
				sysUserRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				sysUserRole.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
				insertUserRoleList.add(sysUserRole); // 不存在，本次新增
			}
		}

		for (SysUserRole currUserRole : currUserRoles) {
			if(!userRoles.contains(currUserRole.getRoleFlow())) {
				GeneralMethod.setRecordInfo(currUserRole, false);
				updateUserRoleList.add(currUserRole); // 已存在，本次不涉及，删除
			}
		}

		if(CollectionUtils.isNotEmpty(updateUserRoleList)) {
			sysUserRoleMapper.batchUpdateSelective(updateUserRoleList);
		}

		if(CollectionUtils.isNotEmpty(insertUserRoleList)) {
			sysUserRoleMapper.batchInsert(insertUserRoleList);
		}

	}
}
