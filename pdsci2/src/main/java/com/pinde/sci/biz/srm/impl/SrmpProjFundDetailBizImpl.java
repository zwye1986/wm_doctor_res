package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IFundInfoDetailBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.SrmProjFundDetailMapper;
import com.pinde.sci.dao.srm.ProjFundExtMapper;
import com.pinde.sci.model.mo.SrmProjFundDetail;
import com.pinde.sci.model.mo.SrmProjFundDetailExample;
import com.pinde.sci.model.mo.SrmProjFundDetailExample.Criteria;
import com.pinde.sci.model.srm.FundDetailAndSchemeExt;
import com.pinde.sci.model.srm.FundDetailExt;
import com.pinde.sci.model.srm.ProjFundDetailExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class SrmpProjFundDetailBizImpl implements IFundInfoDetailBiz{

@Autowired
private SrmProjFundDetailMapper fundDetailMapper;
@Autowired
private ProjFundExtMapper fundExtMapper;
	
	@Override
	public List<SrmProjFundDetail> searchFundDetail(SrmProjFundDetail fundDtl) {
		SrmProjFundDetailExample example=new SrmProjFundDetailExample();
		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteriaDertail(criteria,fundDtl);
		example.setOrderByClause("item_id desc");
		return fundDetailMapper.selectByExample(example);
	}
	private void criteriaDertail(Criteria criteria,SrmProjFundDetail fundDtl){
		if(StringUtil.isNotBlank(fundDtl.getFundFlow())){
			criteria.andFundFlowEqualTo(fundDtl.getFundFlow());
		}
		if(StringUtil.isNotBlank(fundDtl.getFundTypeId())){
			criteria.andFundTypeIdEqualTo(fundDtl.getFundTypeId());
		}
		if(StringUtil.isNotBlank(fundDtl.getOperStatusId())){
			criteria.andOperStatusIdEqualTo(fundDtl.getOperStatusId());
		}
		if(StringUtil.isNotBlank(fundDtl.getItemFlow())){
			criteria.andItemFlowEqualTo(fundDtl.getItemFlow());
		}
		if(StringUtil.isNotBlank(fundDtl.getItemId())){
			criteria.andItemIdEqualTo(fundDtl.getItemId());
		}
		if(StringUtil.isNotBlank(fundDtl.getRealityTypeId())){
			criteria.andRealityTypeIdEqualTo(fundDtl.getRealityTypeId());
		}
		if(StringUtil.isNotBlank(fundDtl.getReimburseId())){
			criteria.andReimburseIdEqualTo(fundDtl.getReimburseId());
		}
	}
	@Override
	public List<SrmProjFundDetail> searchFundDetail(SrmProjFundDetail fundDtl, List<String> fundTypeId,List<String> fundFlowList,String startTime,String endTime,String startMoney,String endMoney) {
		SrmProjFundDetailExample example=new SrmProjFundDetailExample();
		Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

		if(StringUtil.isNotBlank(fundDtl.getFundTypeId())){
			criteria.andFundTypeIdEqualTo(fundDtl.getFundTypeId());
		}
		if(StringUtil.isNotBlank(fundDtl.getOperStatusId())){
			criteria.andOperStatusIdEqualTo(fundDtl.getOperStatusId());
		}
		if(fundTypeId != null && fundTypeId.size()>0){
			criteria.andFundTypeIdIn(fundTypeId);
		}
		if(fundFlowList != null && fundFlowList.size()>0){
			criteria.andFundFlowIn(fundFlowList);
		}
		if(StringUtil.isNotBlank(startMoney)){
			BigDecimal startMo = new BigDecimal(startMoney);
			criteria.andRealmoneyGreaterThanOrEqualTo(startMo);
		}
		if(StringUtil.isNotBlank(endMoney)){
			BigDecimal endMo = new BigDecimal(endMoney);
			criteria.andRealmoneyLessThanOrEqualTo(endMo);
		}
		if(StringUtil.isNotBlank(startTime)){
			criteria.andProvideDateTimeGreaterThanOrEqualTo(DateUtil.getDateTime(startTime));
		}
		if(StringUtil.isNotBlank(endTime)){
			criteria.andProvideDateTimeLessThanOrEqualTo(DateUtil.getDateTime(endTime));
		}
		example.setOrderByClause("PROVIDE_DATE_TIME asc,FUND_FLOW desc");
		return fundDetailMapper.selectByExample(example);
	}

	@Override
	public List<SrmProjFundDetail> searchFundDetailByProjAndFund(Map<String, Object> map) {
		return fundExtMapper.selectFundDetailList(map);
	}

	@Override
	public void saveFundDetail(SrmProjFundDetail fundDtl) {
		fundDetailMapper.insert(fundDtl);
		
	}

	@Override
	public void updateFundDetail(SrmProjFundDetail fundDtl) {
		fundDetailMapper.updateByPrimaryKeySelective(fundDtl);
		
	}

	@Override
	public int deleteFundDetail(String fundDetailFlow) {
		SrmProjFundDetail detail = new SrmProjFundDetail();
		detail.setFundDetailFlow(fundDetailFlow);
		detail.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		return this.fundDetailMapper.updateByPrimaryKeySelective(detail);
	}

	@Override
	public int updateRecordStatusByFundFlow(String fundFlow,String recordStatus) {
		SrmProjFundDetailExample example = new SrmProjFundDetailExample();
		example.createCriteria().andFundFlowEqualTo(fundFlow);
		SrmProjFundDetail detail = new SrmProjFundDetail();
		detail.setRecordStatus(recordStatus);
		return this.fundDetailMapper.updateByExampleSelective(detail, example);
	}

	@Override
	public ProjFundDetailExt selectProjFundDetailExt(String fundDetailFlow) {
		return this.fundExtMapper.selectProjFundDetailExt(fundDetailFlow);
	}

	@Override
	public List<FundDetailAndSchemeExt> selectFundDetailExt(Map<String,Object> map) {
		return fundExtMapper.selectFundDetailExtList(map);
	}

}
