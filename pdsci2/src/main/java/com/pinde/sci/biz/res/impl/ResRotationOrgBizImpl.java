package com.pinde.sci.biz.res.impl;


import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResRotationOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResRotationOrgMapper;
import com.pinde.sci.model.mo.ResRotationOrg;
import com.pinde.sci.model.mo.ResRotationOrgExample;
import com.pinde.sci.model.mo.ResRotationOrgExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResRotationOrgBizImpl implements IResRotationOrgBiz{
	@Autowired
	private  ResRotationOrgMapper rotationOrgMapper;

	@Override
	public int save(ResRotationOrg rotationOrg) {
		if(rotationOrg!=null){
			if(StringUtil.isNotBlank(rotationOrg.getRecordFlow())){
				GeneralMethod.setRecordInfo(rotationOrg, false);
				return rotationOrgMapper.updateByPrimaryKeySelective(rotationOrg);
			}else{
				rotationOrg.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(rotationOrg,true);
				return rotationOrgMapper.insert(rotationOrg);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public ResRotationOrg searchByScoreFlow(String recordFlow) {
		return rotationOrgMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<ResRotationOrg> searchByRotationFlow(String rotationFlow) {
		ResRotationOrgExample example=new ResRotationOrgExample();
		Criteria criteria= example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(rotationFlow)) {
			criteria.andRotationFlowEqualTo(rotationFlow);
		}
		return rotationOrgMapper.selectByExample(example);
	}
	
	@Override
	public List<ResRotationOrg> searchByRotationFlowY(String rotationFlow) {
		ResRotationOrgExample example=new ResRotationOrgExample();
		Criteria criteria= example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(rotationFlow)) {
			criteria.andRotationFlowEqualTo(rotationFlow);
		}
		return rotationOrgMapper.selectByExample(example);
	}

	@Override
	public List<ResRotationOrg> searchByRotationFlowOrg(String rotationFlow,
			String orgFlow) {
		ResRotationOrgExample example=new ResRotationOrgExample();
		Criteria criteria= example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(rotationFlow)) {
			criteria.andRotationFlowEqualTo(rotationFlow);
		}
		if (StringUtil.isNotBlank(orgFlow)) {
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		return rotationOrgMapper.selectByExample(example);
	}

	@Override
	public List<ResRotationOrg> ResRotationOrgAll() {
		ResRotationOrgExample example=new ResRotationOrgExample();
		Criteria criteria= example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return rotationOrgMapper.selectByExample(example);
	}
	

}
