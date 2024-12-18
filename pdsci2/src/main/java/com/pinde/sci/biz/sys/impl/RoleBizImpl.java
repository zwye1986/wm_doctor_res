package com.pinde.sci.biz.sys.impl;

import com.pinde.core.common.sci.dao.SysRoleMapper;
import com.pinde.core.model.*;
import com.pinde.core.model.SysRoleExample.Criteria;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class RoleBizImpl implements IRoleBiz {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysRolePopedomMapper sysRolePopedomMapper;
	@Autowired
	private SysRoleColumnMapper roleColumnMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysCfgMapper sysCfgMapper;
	@Autowired
	private SysRoleAuthGxMapper sysRoleAuthGxMapper;

	@Override
	public List<SysRole> search(SysRole sysRole, List<String> levelIds) {
		SysRoleExample example = new SysRoleExample();
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(sysRole.getRoleName())){
			criteria.andRoleNameLike("%"+sysRole.getRoleName()+"%");			
		}
		if(StringUtil.isNotBlank(sysRole.getWsId())){
			criteria.andWsIdEqualTo(sysRole.getWsId());			
		}
		if(StringUtil.isNotBlank(sysRole.getRecordStatus())){
			criteria.andRecordStatusEqualTo(sysRole.getRecordStatus());	
		}
		if(StringUtil.isNotBlank(sysRole.getRoleLevelId())){
			criteria.andRoleLevelIdEqualTo(sysRole.getRoleLevelId());	
		}
		if(levelIds!=null&&levelIds.size()>0)
		{
			criteria.andRoleLevelIdIn(levelIds);
		}
		if(StringUtil.isNotBlank(sysRole.getAllowRegFlag())){
			criteria.andAllowRegFlagEqualTo(sysRole.getAllowRegFlag());	
		}
		example.setOrderByClause("ORDINAL");
		return sysRoleMapper.selectByExample(example);
	}

	@Override
	public boolean save(SysRole sysRole){
		if(StringUtil.isNotBlank(sysRole.getRoleFlow())){
			GeneralMethod.setRecordInfo(sysRole, false);
			sysRoleMapper.updateByPrimaryKeySelective(sysRole);				
		}else{
			sysRole.setRoleFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(sysRole, true);
			sysRoleMapper.insert(sysRole);	
		}
		return true;
	}

	@Override
	public SysRole read(String roleFlow) {
		return sysRoleMapper.selectByPrimaryKey(roleFlow);
	}

	@Override
	public boolean delete(String roleFlow,String recordStatus) {
		SysRole sysRole = new SysRole();
		sysRole.setRoleFlow(roleFlow);
		sysRole.setRecordStatus(recordStatus);
		GeneralMethod.setRecordInfo(sysRole, false);
		sysRoleMapper.updateByPrimaryKeySelective(sysRole);
		return true;
	}

	@Override
	public boolean realDelete(String roleFlow) {
		//查询是否有用户已使用此角色，如果有则不允许删除
		SysUserRoleExample example = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria().andRoleFlowEqualTo(roleFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysUserRole> sysUserRole = sysUserRoleMapper.selectByExample(example);
		if(null != sysUserRole && sysUserRole.size() > 0){
			return false;
		}
		sysRoleMapper.deleteByPrimaryKey(roleFlow);
		return true;
	}

	@Override
	public List<String> getPopedom(String roleFlow) {
		SysRolePopedomExample example = new SysRolePopedomExample();
		SysRolePopedomExample.Criteria criteria = example.createCriteria();
        criteria.andRoleFlowEqualTo(roleFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRolePopedom> sysRolePopedomList = sysRolePopedomMapper.selectByExample(example);
		List<String> menuIdList = new ArrayList<String>();
		for(SysRolePopedom sysRolePopedom : sysRolePopedomList){
			menuIdList.add(sysRolePopedom.getMenuId());
		}
		return menuIdList;
	}

	@Override
	public boolean savePop(SysRole sysRole, String[] menuIds) {
		//将原先该角色的功能都删除
		SysRolePopedom update = new SysRolePopedom();
		update.setRoleFlow(sysRole.getRoleFlow());
        update.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		SysRolePopedomExample example = new SysRolePopedomExample();
		SysRolePopedomExample.Criteria criteria = example.createCriteria();
        criteria.andRoleFlowEqualTo(sysRole.getRoleFlow()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		sysRolePopedomMapper.updateByExampleSelective(update, example);

		for(String menuId : menuIds){
			SysRolePopedom insert = new SysRolePopedom();
			insert.setRoleFlow(sysRole.getRoleFlow());
			insert.setMenuId(menuId);
            insert.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			insert.setRecordFlow(PkUtil.getUUID());
			sysRolePopedomMapper.insert(insert);
		}
		return false;
	}
	@Override
	public Integer saveRoleAuth(SysRole sysRole,String orgFlow, String[] schoolNames) {
		int result = 0;
		SysRoleAuthGxExample example = new SysRoleAuthGxExample();
		SysRoleAuthGxExample.Criteria criteria = example.createCriteria();
        criteria.andRoleFlowEqualTo(sysRole.getRoleFlow()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		SysRoleAuthGx sysRoleAuthGx = new SysRoleAuthGx();
		List<SysRoleAuthGx> sysRoleAuthGxes = sysRoleAuthGxMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(sysRoleAuthGxes)){
			sysRoleAuthGx = sysRoleAuthGxes.get(0);
			sysRoleAuthGx.setSchools(Arrays.toString(schoolNames).replace("[","").replace("]",""));
			sysRoleAuthGx.setOrgFlow(orgFlow);
			result = sysRoleAuthGxMapper.updateByPrimaryKey(sysRoleAuthGx);
		}else{
			SysRoleAuthGx insert = new SysRoleAuthGx();
			insert.setRoleFlow(sysRole.getRoleFlow());
			insert.setOrgFlow(orgFlow);
			insert.setSchools(schoolNames.toString());
            insert.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			insert.setRecordFlow(PkUtil.getUUID());
			result =  sysRoleAuthGxMapper.insert(insert);
		}
		return result;
	}

	@Override
	public SysRoleAuthGx readRoleAuth(String roleFlow){
		SysRoleAuthGx sysRoleAuthGx = new SysRoleAuthGx();
		SysRoleAuthGxExample example = new SysRoleAuthGxExample();
		SysRoleAuthGxExample.Criteria criteria = example.createCriteria();
        criteria.andRoleFlowEqualTo(roleFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRoleAuthGx> sysRoleAuthGxes = sysRoleAuthGxMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(sysRoleAuthGxes)){
			sysRoleAuthGx = sysRoleAuthGxes.get(0);
		}
		return sysRoleAuthGx;
	}

	@Override
	public void saveOrder(String[] roleFlow) {
		if(roleFlow==null) return;
		int i=1;
		for(String flow : roleFlow){
			SysRole role = new SysRole();
			role.setRoleFlow(flow);
			role.setOrdinal((i++));
			sysRoleMapper.updateByPrimaryKeySelective(role);		}	
		
	}

	@Override
	public List<SysRole> search(String userFlow, String wsId) {
		List<SysRole> roleList = null;
		if(StringUtil.isNotBlank(userFlow)&&StringUtil.isNotBlank(wsId)){
			SysUserRoleExample example = new SysUserRoleExample();
            example.createCriteria().andUserFlowEqualTo(userFlow).andWsIdEqualTo(wsId).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysUserRole> userRoleList = this.sysUserRoleMapper.selectByExample(example);
			List<String> roleFlowList = new ArrayList<String>();
			if(userRoleList!=null&&!userRoleList.isEmpty()){
				for (SysUserRole userRole : userRoleList) {
                    if(StringUtil.isNotBlank(userRole.getRoleFlow())){
                        roleFlowList.add(userRole.getRoleFlow());
                    }
				}
			}
            if(roleFlowList!=null&&!roleFlowList.isEmpty()) {
                SysRoleExample example2 = new SysRoleExample();
                example2.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRoleFlowIn(roleFlowList);
                roleList = this.sysRoleMapper.selectByExample(example2);
            }
		}
		return roleList;
	}

	@Override
	public List<String> getColumn(String roleFlow) {
		SysRoleColumnExample example = new SysRoleColumnExample();
		SysRoleColumnExample.Criteria criteria = example.createCriteria();
        criteria.andRoleFlowEqualTo(roleFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRoleColumn> list = roleColumnMapper.selectByExample(example);
		List<String> menuIdList = new ArrayList<String>();
		for(SysRoleColumn sysRolePopedom : list){
			menuIdList.add(sysRolePopedom.getColumnId());
		}
		return menuIdList;
	}

	@Override
	public boolean saveCol(SysRole sysRole, String[] columnIds) {
		//将原先该角色的栏目都删除
		SysRoleColumn update = new SysRoleColumn();
		update.setRoleFlow(sysRole.getRoleFlow());
        update.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		SysRoleColumnExample example = new SysRoleColumnExample();
		SysRoleColumnExample.Criteria criteria = example.createCriteria();
        criteria.andRoleFlowEqualTo(sysRole.getRoleFlow()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		roleColumnMapper.updateByExampleSelective(update, example);

		for(String menuId : columnIds){
			SysRoleColumn insert = new SysRoleColumn();
			insert.setRoleFlow(sysRole.getRoleFlow());
			insert.setColumnId(menuId);
            insert.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			insert.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(insert,true);
			roleColumnMapper.insert(insert);
		}
		return false;
	}

	@Override
	public SysRole searchByRoleName(String roleName) {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(roleName).andWsIdEqualTo("njmuedu").andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> roleList = sysRoleMapper.selectByExample(example);
		return roleList.get(0);
	}

	@Override
	public SysRole selectByRoleName(String roleName) {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(roleName).andWsIdEqualTo("res").andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> roleList = sysRoleMapper.selectByExample(example);
		return roleList.get(0);
	}

    @Override
    public int addActivityCfg(Map<String,String> param) {
        return sysRoleMapper.addActivityCfg(param);
    }

    @Override
    public int delActivityCfg(String recordFlow) {
        return sysRoleMapper.delActivityCfg(recordFlow);
    }

    @Override
    public int updActivityCfg(Map<String, String> param) {
        return sysRoleMapper.updActivityCfg(param);
    }

    @Override
    public List<ActivityCfgExt> searchActvity(Map<String, String> param) {
        return sysRoleMapper.searchActvity(param);
    }
	@Override
	public List<SysCfg> searchRoleList() {
		SysCfgExample example = new SysCfgExample();
		SysCfgExample.Criteria criteria = example.createCriteria();
		List<String> list = new ArrayList();
		list.add("res_head_role_flow");
		list.add("res_teacher_role_flow");
		list.add("res_secretary_role_flow");
		list.add("res_teaching_head_role_flow");
		list.add("res_teaching_secretary_role_flow");
		criteria.andCfgCodeIn(list);
		return sysCfgMapper.selectByExample(example);
	}
}
