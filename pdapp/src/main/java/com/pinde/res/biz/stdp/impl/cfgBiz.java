package com.pinde.res.biz.stdp.impl;

import com.pinde.app.common.GlobalUtil;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.model.SysCfg;
import com.pinde.core.model.SysCfgExample;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.ICfgBiz;
import com.pinde.sci.dao.base.SysCfgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class cfgBiz implements ICfgBiz {
	@Resource
	private SysCfgMapper cfgMapper;
	@Resource
	private SysCfgMapper sysCfgMapper;
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
	public List<SysCfg> search(SysCfg cfg) {
		List<String> wsIdList = new ArrayList<String>();
		wsIdList.add(GlobalConstant.SYS_WS_ID);
		SysCfgExample example = new SysCfgExample();
		SysCfgExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(cfg.getWsId())) {
			wsIdList.add(cfg.getWsId());
			criteria.andWsIdIn(wsIdList);
		}
		return sysCfgMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public SysCfg read(String cfgCode) {
		return sysCfgMapper.selectByPrimaryKey(cfgCode);
	}
}
