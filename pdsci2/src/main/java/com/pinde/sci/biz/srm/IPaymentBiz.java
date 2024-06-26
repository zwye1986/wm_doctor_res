package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.FundInfoExt;
import com.pinde.sci.model.srm.ProjFundDetailExt;
import com.pinde.sci.model.srm.ProjFundFormExt;
import com.pinde.sci.model.srm.PubProjExt;

import java.util.List;


public interface IPaymentBiz {
	/**
	 * 查询报销列表
	 * @return
	 */
	List<FundInfoExt> queryPaymentList(PubProj proj);
	
	/**
	 * 根据经费流水号查找报销明细
	 * @param fundFlow
	 * @return
	 */
	List<SrmProjFundDetail> getDetailByFundFlow(String fundFlow);

	
	/**
	 * 保存报销明细（多个）
	 * @param fundInfo
	 * @param dList
	 */
	void saveDetailList(String fundFlow,List<SrmProjFundDetail> detailList,SrmFundProcess fundProcess);
	
	/**
	 * 保存报销明细
	 * @param fundDetail
	 */
	void reimburse(SrmProjFundDetail fundDetail,PubFile file);
	
	/**
	 * 根据schemeFlow查找FundSchemeDetail
	 * @param schemeFlow
	 * @return
	 */
	List<SrmFundSchemeDetail> getSchemeDetailBySchemeFlow(String schemeFlow);


	/**
	 * 查询审核列表
	 * @return
	 */
	List<PubProjExt> queryProjFundInfoList(PubProjExt projExt);
	
	/**
	 * 
	 * @param fundFlow
	 * @return
	 */
	SrmProjFundInfo getFundInfoByFundFlow(String fundFlow);
	
	/**
	 * 查找报销明细
	 * @param fundDetailFlow
	 * @return
	 */
	SrmProjFundDetail getDetailByDetailFlow(String fundDetailFlow);
	
	/**
	 * 
	 * @param fundDetail
	 * @param fundProcess
	 */
	void updateDetailStatus(SrmProjFundDetail fundDetail,SrmFundProcess fundProcess);
	
	/**
	 * 在显示报销列表的的时候查询预算金额
	 * @param fundFlow
	 * @param itemFlow
	 * @return
	 */
	SrmProjFundDetail searchBudgetDetail(String fundFlow , String itemFlow);
	
	/**
	 * 查询没有被通过的经费项个数
	 * @param fundDetail
	 * @return
	 */
	int searchFundDetailNoApproveCount(SrmProjFundDetail fundDetail);

    /**
     * 按报销单号查询经费表单
     * @param formFlow
     * @return
     */
    List<SrmProjFundForm> getFormByFundFlow(String fundFlow);

    List<SrmProjFundDetail> getDetailByGroup(String formFlow,String fundFlow);

    SrmProjFundForm getFormByFormFlow(String formFlow);

    void submitReimburseForm(SrmProjFundForm fundForm);

	List<SrmProjFundDetail> getReimburseDetail(SrmProjFundDetail fundDetail,List<String> operStatusIds);
    /**
     * 查询徐州审核列表
     * @return
     */
    List<ProjFundFormExt> queryFundFormAuditList(ProjFundFormExt fundFormExt);

    ProjFundFormExt selectFundFormExtByFormFlow(String fundFormFlow);

    void updateFormStatus(List<SrmProjFundDetail> fundDetailList,SrmProjFundForm fundForm,SrmFundProcess process);

	List<ProjFundDetailExt> selectProjFundDetailList(ProjFundDetailExt projFundDetailExt);

	int deleteReimburse(String detailFlow);
}
