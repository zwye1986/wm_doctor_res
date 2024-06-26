package com.pinde.sci.biz.srm;

import com.pinde.sci.form.srm.BalanceFundCollectForm;
import com.pinde.sci.form.srm.FundDetailCalculateForm;
import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SrmFundProcess;
import com.pinde.sci.model.mo.SrmProjFundDetail;
import com.pinde.sci.model.mo.SrmProjFundInfo;
import com.pinde.sci.model.srm.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 *项目经费
 */
public interface IFundBiz {
	/**
	 * 经费列表
	 * @param proj
	 * @return
	 */
	public List<FundInfoExt> getList(PubProj proj);

	SrmProjFundDetail readSrmProjFundDetail(String detailFlow);

	int editFundDetail(SrmProjFundDetail srmProjFundDetail, SrmFundProcess pro);

	/**
	 * 经费明细
	 * @param fundFlow
	 * @return
	 */
	public List<SrmProjFundDetail> getDetails(String fundFlow);

	/**
	 * 报销明细反查
	 * @param detail
	 * @return
     */
	public List<SrmProjFundDetail> paymentDetails(SrmProjFundDetail detail);
	/**
	 * 保存经费明细
	 * @param detail
	 * @return
	 */
	public void saveDetail(SrmProjFundDetail detail );
	/**
	 * 获取经费
	 * @param fundFlow 经费流水号
	 * @return
	 */
	public SrmProjFundInfo getFund(String fundFlow);
	/**
	 * 计算经费总计
	 * @param list 经费列表
	 * @return
	 */
	public FundSum getFundSum(List<PubProjExt> list);
	/**
	 * 获取经费扩展
	 * @param fundFlow 经费流水号
	 * @return
	 */
	public FundInfoExt getFundExt(String fundFlow);
	
	/**
	 * 根据经费明细流水号删除该经费明细，
	 * 同时更新实际到账金额， 到账余额 ，根据到账类型决定更新下拨实际到账总额或配套实际到账总额
	 * @param fundDetailFlow
	 */
	public void delDetailByFundDetailFlow(String fundDetailFlow);

	/**
	 * 经费操作过程更新
	 * @param srmFundProcess
     */
	int editProcess(SrmFundProcess srmFundProcess);
	/**
	 * 获取经费操作过程信息
	 */
	List<SrmFundProcess> getSrmFundProcess(String fundProcessFlow);

	/**
	 * 财务到账经费审核,更新操作id
	 */
	public int editFundDetail(SrmProjFundDetail srmProjFundDetail );
	/**
	 *获取管理费类型,金额,扣除时间,审核状态
	 */
	public List<SrmProjFundDetail> getFeeDetail(String fundFlow);

	List<FundDetailCalculateForm> calculateFundDetail(String fundFlow,String startTime,String endTime);

	List<UserSurplusInfo> surplusInfoList(Map<String,String> map);

	List<PubProjExt> surplusProjInfoList(PubProjExt pubProjExt);

	List<PersonalFundInfoExt> personalFundList(Map<String,String> map);

	int importIncomesFromExcel(MultipartFile file);

	BalanceFundCollectForm getFundCollectForm(List<String> fundFlowList);
}

