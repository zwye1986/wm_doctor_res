package com.pinde.sci.biz.erp;

import com.pinde.sci.model.erp.ErpCrmContractBillBalanceExt;
import com.pinde.sci.model.mo.ErpCrmContractBillBalance;

import java.math.BigDecimal;
import java.util.List;

public interface IErpCrmContractBillBalanceBiz {

	/**
	 * 实际开票
	 * @param billBalance
	 * @return
     */
	List<ErpCrmContractBillBalanceExt> searchBalanceList(ErpCrmContractBillBalance billBalance);

	/**
	 * 获取单条记录
	 * @param record_flow
	 * @return
     */
	ErpCrmContractBillBalance readBillBalance(String record_flow);

	/**
	 * 保存实际开票信息，同时回写主表中的金额
	 * @param balance
	 * @return
     */
	int saveBillBalance(ErpCrmContractBillBalance balance);

	/**
	 * 保存实际开票信息
	 * @param balance
	 * @return
     */
	int save(ErpCrmContractBillBalance balance);

	/**
	 * 合同已开票金额
	 * @param contractFlow
	 * @return
     */
	BigDecimal getAllBillMoneyByContractFlow(String contractFlow);

	Integer sumBalancePayFundByFlow(String contractFlow);

	/**
	 * 查询审核通过的开票信息
	 * @param billBalance
	 * @return
     */
	List<ErpCrmContractBillBalanceExt> searchAuditPassBalanceList(ErpCrmContractBillBalance billBalance);
}
