package com.pinde.res.biz.stdp.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.ISchAndStandardDeptCfgBiz;
import com.pinde.sci.dao.base.SchAndStandardDeptCfgMapper;
import com.pinde.sci.model.mo.SchAndStandardDeptCfg;
import com.pinde.sci.model.mo.SchAndStandardDeptCfgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class SchAndStandardDeptCfgBizImpl implements ISchAndStandardDeptCfgBiz {

	@Autowired
	private SchAndStandardDeptCfgMapper cfgMapper;


	@Override
	public SchAndStandardDeptCfg readByFlow(String recordFlow) {
		return cfgMapper.selectByPrimaryKey(recordFlow);
	}


	@Override
	public List<SchAndStandardDeptCfg> getListByOrgFlow(String orgFlow) {
		if(StringUtil.isNotBlank(orgFlow))
		{
			SchAndStandardDeptCfgExample example=new SchAndStandardDeptCfgExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andOrgFlowEqualTo(orgFlow);
			return cfgMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public SchAndStandardDeptCfg readBySchDeptFlow(String schDeptFlow) {
		if(StringUtil.isNotBlank(schDeptFlow))
		{
			SchAndStandardDeptCfgExample example=new SchAndStandardDeptCfgExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andSchDeptFlowEqualTo(schDeptFlow);
			List<SchAndStandardDeptCfg> cfgs= cfgMapper.selectByExample(example);
			if(cfgs!=null&&cfgs.size()>0)
			{
				return cfgs.get(0);
			}
		}
		return null;
	}
}
