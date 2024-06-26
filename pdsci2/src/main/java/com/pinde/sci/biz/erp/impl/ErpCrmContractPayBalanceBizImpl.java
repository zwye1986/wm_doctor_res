package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractPayPlanBiz;
import com.pinde.sci.biz.erp.IErpCrmContractPayBalanceBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ErpCrmContractPayBalanceMapper;
import com.pinde.sci.dao.erp.ErpCrmContractPayBalanceExtMapper;
import com.pinde.sci.enums.erp.PayPlanStatusEnum;
import com.pinde.sci.enums.erp.PlanBalanceStatusEnum;
import com.pinde.sci.model.erp.ErpCrmContractPayBalanceExt;
import com.pinde.sci.model.mo.ErpCrmContractPayBalance;
import com.pinde.sci.model.mo.ErpCrmContractPayBalanceExample;
import com.pinde.sci.model.mo.ErpCrmContractPayPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpCrmContractPayBalanceBizImpl implements IErpCrmContractPayBalanceBiz {
	@Autowired
	private ErpCrmContractPayBalanceMapper contractPayBalanceMapper;
	@Autowired
	private ErpCrmContractPayBalanceExtMapper contractPayBalanceExtMapper;
	@Autowired
	private IErpContractPayPlanBiz payPlanBiz;

	@Override
	public int save(ErpCrmContractPayBalance balance) {
		if(StringUtil.isBlank(balance.getRecordFlow())){
			balance.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(balance, true);
			return contractPayBalanceMapper.insert(balance);
		}else{
			GeneralMethod.setRecordInfo(balance, false);
			return contractPayBalanceMapper.updateByPrimaryKeySelective(balance);
		}
	}

	@Override
	public List<ErpCrmContractPayBalanceExt> searchBalanceList(ErpCrmContractPayBalance balance) {
		Map<String, Object> map = new HashMap<>();
		map.put("contractFlow", balance.getContractFlow());
		map.put("planFlow", balance.getPlanFlow());
		return contractPayBalanceExtMapper.getContractPayBalanceExtByContractFlow(map);
	}
	@Override
	public List<ErpCrmContractPayBalanceExt> searchAuditPassBalanceList(ErpCrmContractPayBalance balance) {
		Map<String, Object> map = new HashMap<>();
		map.put("contractFlow", balance.getContractFlow());
		map.put("planFlow", balance.getPlanFlow());
		map.put("statusId", PlanBalanceStatusEnum.AuditPass.getId());
		return contractPayBalanceExtMapper.getContractPayBalanceExtByContractFlow(map);
	}

	@Override
	public int saveBalance(ErpCrmContractPayBalance balance) {
		save(balance);
		String planFlow =  balance.getPlanFlow();
		BigDecimal balanceFund = contractPayBalanceExtMapper.getBalanceFund(balance);
		ErpCrmContractPayPlan payPlan = payPlanBiz.readPayPlan(planFlow);
		//到账/付款金额
		payPlan.setBalanceFund(balanceFund);
		//状态
		BigDecimal payFund = payPlan.getPayFund();
		int result = payFund.compareTo(balanceFund);
		if(result == 1){
			payPlan.setPlanStatusId(PayPlanStatusEnum.InProgress.getId());
			payPlan.setPlanStatusName(PayPlanStatusEnum.InProgress.getName());
		}else if(result == 0 || result == -1){
			payPlan.setPlanStatusId(PayPlanStatusEnum.Complete.getId());
			payPlan.setPlanStatusName(PayPlanStatusEnum.Complete.getName());
		}else {
			payPlan.setPlanStatusId(PayPlanStatusEnum.NotStart.getId());
			payPlan.setPlanStatusName(PayPlanStatusEnum.NotStart.getName());
		}
		return payPlanBiz.save(payPlan);
	}

	@Override
	public BigDecimal getContractBalanceMoney(String contractFlow) {
		return contractPayBalanceExtMapper.getContractBalanceMoney(contractFlow);
	}

	@Override
	public BigDecimal getContractPlanMoney(String contractFlow) {
		return contractPayBalanceExtMapper.getContractPlanMoney(contractFlow);
	}
	@Override
	public Integer sumBalancePayFundByFlow(String contractFlow) {
		return contractPayBalanceExtMapper.sumBalancePayFundByFlow(contractFlow);
	}

	@Override
	public ErpCrmContractPayBalance readBalanceByFlow(String recordFlow) {
		return contractPayBalanceMapper.selectByPrimaryKey(recordFlow);
	}

}
