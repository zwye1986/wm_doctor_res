package com.pinde.res.biz.eval.impl;


import com.pinde.app.common.GlobalUtil;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.model.SysCfg;
import com.pinde.core.model.SysUser;
import com.pinde.core.model.SysUserRole;
import com.pinde.core.model.SysUserRoleExample;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.eval.IEvalAppBiz;
import com.pinde.sci.dao.base.SysCfgMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.base.SysUserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class EvalAppBizImpl implements IEvalAppBiz {
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	//根据用户名获取用户信息

	@Override
	public SysUser readSysUser(String userFlow){
		return userMapper.selectByPrimaryKey(userFlow);
	}

	//获取用户拥有的角色列表
	@Override
	public List<SysUserRole> getSysUserRole(String userFlow){
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andUserFlowEqualTo(userFlow);
		return userRoleMapper.selectByExample(example);
	}

	//获取系统配置信息
	@Override
	public String getCfgByCode(String code){
		if(StringUtil.isNotBlank(code)){
			String v= GlobalUtil.getLocalCfgMap().get(code);
			if(StringUtil.isNotBlank(v))
				return v;
			SysCfg cfg = cfgMapper.selectByPrimaryKey(code);
			if(cfg!=null){
				String val = cfg.getCfgValue();
				if(!StringUtil.isNotBlank(val)){
					val = cfg.getCfgBigValue();
				}
				return val;
			}
		}
		return null;
	}

}
  