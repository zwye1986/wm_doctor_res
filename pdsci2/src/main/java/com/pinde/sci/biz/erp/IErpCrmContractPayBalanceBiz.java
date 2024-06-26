package com.pinde.sci.biz.erp;

import com.pinde.sci.model.erp.ErpCrmContractPayBalanceExt;
import com.pinde.sci.model.mo.ErpCrmContractPayBalance;

import java.math.BigDecimal;
import java.util.List;

public interface IErpCrmContractPayBalanceBiz {
	 
	/**
	 * 保存
	 * @param balance
	 * @return
	 */
	int save(ErpCrmContractPayBalance balance);
	
	/**
	 * 查询
	 * @param balance
	 * @return
	 */
	List<ErpCrmContractPayBalanceExt> searchBalanceList(ErpCrmContractPayBalance balance);

	/**
	 * 保存到账
	 * @param balance
	 * @return
	 */
	int saveBalance(ErpCrmContractPayBalance balance);

	/**
	 * 合同实际到款金额
	 * @param contractFlow
	 * @return
     */
	BigDecimal getContractBalanceMoney(String contractFlow);

	/**
	 * 合同计划到款金额
	 * @param contractFlow
	 * @return
     */
	BigDecimal getContractPlanMoney(String contractFlow);

	/**
	 * 合同到款总金额
	 * @param contractFlow
	 * @return
     */
	Integer sumBalancePayFundByFlow(String contractFlow);

	/**
	 * 根据主键查询
	 * @param recordFlow
	 * @return
     */
	ErpCrmContractPayBalance readBalanceByFlow(String recordFlow);

	/**
	 * 查询申述通过的到帐信息
	 * @param balance
	 * @return
     */
	List<ErpCrmContractPayBalanceExt> searchAuditPassBalanceList(ErpCrmContractPayBalance balance);
}
