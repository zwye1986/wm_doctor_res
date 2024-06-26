package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmProjFundDetail;
import com.pinde.sci.model.srm.FundDetailAndSchemeExt;
import com.pinde.sci.model.srm.FundDetailExt;
import com.pinde.sci.model.srm.ProjFundDetailExt;

import java.util.List;
import java.util.Map;

public interface IFundInfoDetailBiz {

	
	public List<SrmProjFundDetail> searchFundDetail(SrmProjFundDetail fundDtl);
	public List<SrmProjFundDetail> searchFundDetail(SrmProjFundDetail fundDtl,List<String> fundTypeId,List<String> fundFlowList,String startTime,String endTime,String startMoney,String endMoney);
	public List<SrmProjFundDetail> searchFundDetailByProjAndFund(Map<String,Object> map);
	public void saveFundDetail(SrmProjFundDetail fundDtl);
	
	public void updateFundDetail(SrmProjFundDetail fundDtl);
	
	public int deleteFundDetail(String fundDetailFlow);
	public int updateRecordStatusByFundFlow(String fundFlow,String recordStatus);
	/**
	 * 获取经费明细扩展
	 * @param fundDetailFlow
	 * @return
	 */
	public ProjFundDetailExt selectProjFundDetailExt(String fundDetailFlow);

	List<FundDetailAndSchemeExt> selectFundDetailExt(Map<String,Object> map);
}
