package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IFundSchemeBiz;
import com.pinde.sci.biz.srm.IFundSchemeDetailBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.SrmFundSchemeMapper;
import com.pinde.sci.dao.base.SrmProjFundInfoMapper;
import com.pinde.sci.dao.base.SrmProjSourceSchemeMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SrmFundSchemeExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class FundSchemeBizImpl implements IFundSchemeBiz {

	@Autowired
	private SrmFundSchemeMapper srmFundSchemeMapper;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IFundSchemeDetailBiz schemeDetailBiz;
	@Autowired
	private SrmProjFundInfoMapper srmProjFundInfoMapper;
	@Autowired
	private SrmProjSourceSchemeMapper sourceSchemeMapper;
	@Override
	public SrmFundScheme readFundScheme(String schemeFlow) {
		return srmFundSchemeMapper.selectByPrimaryKey(schemeFlow);
	}

	@Override
	public int saveFundScheme(SrmFundScheme scheme) {
		if(StringUtil.isNotBlank(scheme.getSchemeFlow())){
			GeneralMethod.setRecordInfo(scheme, false);
			scheme.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			return srmFundSchemeMapper.updateByPrimaryKeySelective(scheme);
		}else{
			scheme.setSchemeFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(scheme, true);
			scheme.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			//schemeDetailBiz.saveFundSchemeDetail(scheme);
			return srmFundSchemeMapper.insert(scheme);
		}
	}

	@Override
	public List<SrmFundScheme> searchFundScheme(SrmFundScheme scheme) {
		SrmFundSchemeExample example=new SrmFundSchemeExample();
		Criteria criteria=example.createCriteria();
		_condition(scheme,criteria);
		return srmFundSchemeMapper.selectByExample(example);
	}

	@Override
	public List<SrmFundScheme> searchFundSchemeByFlows(SrmFundScheme scheme, List<String> schemeFlows) {
		SrmFundSchemeExample example=new SrmFundSchemeExample();
		Criteria criteria=example.createCriteria();
		if(schemeFlows != null && schemeFlows.size() > 0) {
			criteria.andSchemeFlowIn(schemeFlows);
		}
		if(null != scheme) {
			_condition(scheme, criteria);
		}
		return srmFundSchemeMapper.selectByExample(example);
	}
	private void _condition(SrmFundScheme scheme,Criteria criteria){
		if(StringUtil.isNotBlank(scheme.getRecordStatus())){
			criteria.andRecordStatusEqualTo(scheme.getRecordStatus());
		}
		if(StringUtil.isNotBlank(scheme.getProjFirstSourceId())){
			criteria.andProjFirstSourceIdEqualTo(scheme.getProjFirstSourceId());
		}
		if(StringUtil.isNotBlank(scheme.getProjSecondSourceId())){
			criteria.andProjSecondSourceIdEqualTo(scheme.getProjSecondSourceId());
		}
		if(StringUtil.isNotBlank(scheme.getSchemeName())){
			criteria.andSchemeNameLike("%"+scheme.getSchemeName()+"%");
		}
		if(StringUtil.isNotBlank(scheme.getProjTypeId())){
			criteria.andProjTypeIdEqualTo(scheme.getProjTypeId());
		}
		if(StringUtil.isNotBlank(scheme.getProjFirstSourceId())){
			criteria.andProjFirstSourceIdEqualTo(scheme.getProjFirstSourceId());
		}
		if(StringUtil.isNotBlank(scheme.getProjSecondSourceId())){
			criteria.andProjSecondSourceIdEqualTo(scheme.getProjSecondSourceId());
		}
	}
	@Override
	public int deleteFundScheme(SrmFundScheme scheme) {
		if(StringUtil.isNotBlank(scheme.getSchemeFlow())){
			GeneralMethod.setRecordInfo(scheme, false);
		}
	 return srmFundSchemeMapper.updateByPrimaryKeySelective(scheme);
		
	}

	@Override
	public List<SrmFundScheme> searchStartScheme(String projTypeId) {
		SrmFundSchemeExample example=new SrmFundSchemeExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andProjTypeIdEqualTo(projTypeId);
	
		return srmFundSchemeMapper.selectByExample(example);
	}

	@Override
	public List<SrmProjFundInfo> searchProjFundInfo(SrmProjFundInfo projFundInfo) {
		SrmProjFundInfoExample example=new SrmProjFundInfoExample();
		com.pinde.sci.model.mo.SrmProjFundInfoExample.Criteria criteria=example.createCriteria()
			.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(projFundInfo.getProjSourceId())){
			criteria.andProjSourceIdEqualTo(projFundInfo.getProjSourceId());
		}
		if(StringUtil.isNotBlank(projFundInfo.getProjName())){
			criteria.andProjNameLike(projFundInfo.getProjName());
		}
		
		return srmProjFundInfoMapper.selectByExample(example);
	}

	@Override
	public List<SrmProjSourceScheme> searchSourceScheme(SrmProjSourceScheme sourceScheme) {
		SrmProjSourceSchemeExample example = new SrmProjSourceSchemeExample();
		SrmProjSourceSchemeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(sourceScheme.getSchemeFlow())){
			criteria.andSchemeFlowEqualTo(sourceScheme.getSchemeFlow());
		}
		if(StringUtil.isNotBlank(sourceScheme.getProjFirstSourceId())){
			criteria.andProjFirstSourceIdEqualTo(sourceScheme.getProjFirstSourceId());
		}
		if(StringUtil.isNotBlank(sourceScheme.getProjSecondSourceId())){
			criteria.andProjSecondSourceIdEqualTo(sourceScheme.getProjSecondSourceId());
		}

		return sourceSchemeMapper.selectByExample(example);
	}

	@Override
	public int saveSourceScheme(List<SrmProjSourceScheme> sourceSchemeList,String schemeFlow) {
		SrmProjSourceScheme scheme = new SrmProjSourceScheme();
		SrmProjSourceSchemeExample example = new SrmProjSourceSchemeExample();
		example.createCriteria().andSchemeFlowEqualTo(schemeFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		scheme.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		sourceSchemeMapper.updateByExampleSelective(scheme,example);
		if(sourceSchemeList != null && sourceSchemeList.size() > 0){
			for(SrmProjSourceScheme sourceScheme : sourceSchemeList){
				if(StringUtil.isNotBlank(sourceScheme.getSourceFlow())){
					sourceScheme.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					GeneralMethod.setRecordInfo(sourceScheme, false);
					sourceSchemeMapper.updateByPrimaryKeySelective(sourceScheme);
				}else{
					sourceScheme.setSourceFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(sourceScheme, true);
					sourceSchemeMapper.insertSelective(sourceScheme);
				}
			}
		}
		return 1;
	}
}
