package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchAndStandardDeptCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchAndStandardDeptCfgMapper;
import com.pinde.sci.dao.sch.SchAndStandardDeptCfgExtMapper;
import com.pinde.sci.model.mo.SchAndStandardDeptCfg;
import com.pinde.sci.model.mo.SchAndStandardDeptCfgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchAndStandardDeptCfgBizImpl implements ISchAndStandardDeptCfgBiz {

	@Autowired
	private SchAndStandardDeptCfgMapper cfgMapper;
	@Autowired
	private SchAndStandardDeptCfgExtMapper schAndStandardDeptCfgExtMapper;


	@Override
	public SchAndStandardDeptCfg readByFlow(String recordFlow) {
		return cfgMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int save(SchAndStandardDeptCfg cfg) {
		if(StringUtil.isNotBlank(cfg.getRecordFlow()))
		{
			GeneralMethod.setRecordInfo(cfg,false);
			return  cfgMapper.updateByPrimaryKeySelective(cfg);
		}else {
			cfg.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(cfg,true);
			return cfgMapper.insertSelective(cfg);
		}
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

	@Override
	public SchAndStandardDeptCfg searchByItems(Map<String, String> paramMap) {
		SchAndStandardDeptCfgExample example=new SchAndStandardDeptCfgExample();
		SchAndStandardDeptCfgExample.Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(paramMap.get("schDeptFlow"))){
			criteria.andSchDeptFlowEqualTo(paramMap.get("schDeptFlow"));
		}
		if(StringUtil.isNotBlank(paramMap.get("orgFlow"))){
			criteria.andOrgFlowEqualTo(paramMap.get("orgFlow"));
		}
		if(StringUtil.isNotBlank(paramMap.get("standardDeptId"))){
			criteria.andStandardDeptIdEqualTo(paramMap.get("standardDeptId"));
		}

		List<SchAndStandardDeptCfg> temps = cfgMapper.selectByExample(example);
		if(temps != null && temps.size() > 0){
			return cfgMapper.selectByExample(example).get(0);
		}
		return null;
	}

	@Override
	public List<Map<String,String>>searchByRequired(String rotationFlow,String isRequired) {
		return schAndStandardDeptCfgExtMapper.searchByRequired(rotationFlow,isRequired);
	}

	@Override
	public List<Map<String,String>> searchByLastRotation(String orgFlow) {
		return schAndStandardDeptCfgExtMapper.searchByLastRotation(orgFlow);
	}

	@Override
	public List<Map<String, String>> searchByLastRotationBySpe(String orgFlow, String speId,String isRequired) {
		return schAndStandardDeptCfgExtMapper.searchByLastRotationBySpe(orgFlow,speId,isRequired);

	}

	@Override
	public SchAndStandardDeptCfg readBySchDeptFlowAndOrgFlow(String schDeptFlow, String orgFlow) {
		SchAndStandardDeptCfgExample example=new SchAndStandardDeptCfgExample();
		SchAndStandardDeptCfgExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo("Y");
		if (StringUtil.isNotBlank(schDeptFlow)){
			criteria.andSchDeptFlowEqualTo(schDeptFlow);
		}
		if (StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		List<SchAndStandardDeptCfg> list = cfgMapper.selectByExample(example);
		if (null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SchAndStandardDeptCfg> selectByStandardDeptId(String orgFlow, String standardDeptId) {
		SchAndStandardDeptCfgExample example=new SchAndStandardDeptCfgExample();
		SchAndStandardDeptCfgExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo("Y");
		if (StringUtil.isNotBlank(standardDeptId)){
			criteria.andStandardDeptIdEqualTo(standardDeptId);
		}
		if (StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		return cfgMapper.selectByExample(example);
	}

	@Override
	public List<SchAndStandardDeptCfg> searchSchAndStandardDeptByExample(SchAndStandardDeptCfgExample example) {
		return cfgMapper.selectByExample(example);
	}

	public List<SchAndStandardDeptCfg> getListByOrgFlow(String orgFlow, List<String> schDeptFlowList) {
		SchAndStandardDeptCfgExample example = new SchAndStandardDeptCfgExample();
		SchAndStandardDeptCfgExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo("Y");
		if (StringUtil.isNotBlank(orgFlow)) {
			criteria.andOrgFlowEqualTo(orgFlow);
		}

		if (null != schDeptFlowList && schDeptFlowList.size() > 0) {
			criteria.andSchDeptFlowIn(schDeptFlowList);
		}

		return this.cfgMapper.selectByExample(example);
	}
}
