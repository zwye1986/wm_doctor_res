package com.pinde.res.biz.pushExam.impl;


import com.pinde.app.common.GlobalUtil;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.SysCfg;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.pushExam.IPushExamBiz;
import com.pinde.core.common.sci.dao.SysCfgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
//@Transactional(rollbackFor=Exception.class)
public class PushExamBizImpl implements IPushExamBiz {

	@Resource
	private SysCfgMapper cfgMapper;

	@Resource
	private SysUserMapper userMapper;

	//获取系统配置信息
	@Override
	public String getCfgCode(String code){
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

	@Override
	public SysUser readUserByFlow(String userFlow) {
		return userMapper.selectByPrimaryKey(userFlow);
	}

	@Override
	public void updateUser(SysUser user) {
		userMapper.updateByPrimaryKeySelective(user);
	}
}  
  