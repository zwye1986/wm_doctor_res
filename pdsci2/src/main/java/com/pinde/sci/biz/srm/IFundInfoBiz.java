package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmFundProcess;
import com.pinde.sci.model.mo.SrmProjFundDetail;
import com.pinde.sci.model.mo.SrmProjFundInfo;
import com.pinde.sci.model.srm.PubProjExt;

import java.util.List;

public interface IFundInfoBiz {

	public List<SrmProjFundInfo> searchFundInfo(SrmProjFundInfo fundInfo);
	
	public void saveFundInfo(SrmProjFundInfo fundInfo,List<SrmProjFundDetail> fundDtlList,SrmFundProcess process);

	public void saveSurplusInfo(SrmProjFundInfo fundInfo,SrmProjFundDetail fundDtl,SrmFundProcess process);
	/**
	 * 查询预算审核列表
	 * @param projExt
	 * @return
	 */
	public List<PubProjExt> searchBudgetAuditList(PubProjExt projExt);
	public List<PubProjExt> searchPubProjExtList(PubProjExt projExt);

	public void updateFundInfoStatus(SrmProjFundInfo fundInfo,SrmFundProcess process);

	public SrmProjFundInfo getFundInfoByFlow(String fundFlow);
	
	/**
	 * 预算审核
	 */
	public void budgetAudit(SrmProjFundInfo fundInfo,SrmFundProcess process);

    void updateFundInfo(SrmProjFundInfo fundInfo);
}
