package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractBillPlanBiz;
import com.pinde.sci.biz.erp.IErpCrmContractBillBalanceBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ErpCrmContractBillBalanceMapper;
import com.pinde.sci.dao.erp.ErpCrmContractBillBalanceExtMapper;
import com.pinde.sci.enums.erp.PayPlanStatusEnum;
import com.pinde.sci.enums.erp.PlanBalanceStatusEnum;
import com.pinde.sci.model.erp.ErpCrmContractBillBalanceExt;
import com.pinde.sci.model.mo.ErpCrmContractBillBalance;
import com.pinde.sci.model.mo.ErpCrmContractBillBalanceExample;
import com.pinde.sci.model.mo.ErpCrmContractBillPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
public class ErpCrmContractBillBalanceBizImpl implements IErpCrmContractBillBalanceBiz {

    @Autowired
    private ErpCrmContractBillBalanceMapper contractBillBalanceMapper;

    @Autowired
    private ErpCrmContractBillBalanceExtMapper crmContractBillBalanceExtMapper;

    @Autowired
    private IErpContractBillPlanBiz billPlanBiz;

    @Autowired
    private ErpCrmContractBillBalanceExtMapper contractBillBalanceExtMapper;
    @Override
    public List<ErpCrmContractBillBalanceExt> searchBalanceList(ErpCrmContractBillBalance billBalance) {
        Map<String, Object> map = new HashMap<>();
        map.put("contractFlow", billBalance.getContractFlow());
        map.put("billPlanFlow", billBalance.getBillPlanFlow());
        return crmContractBillBalanceExtMapper.getBillBalanceExtByContractFlow(map);
    }
    @Override
    public List<ErpCrmContractBillBalanceExt> searchAuditPassBalanceList(ErpCrmContractBillBalance billBalance) {
        Map<String, Object> map = new HashMap<>();
        map.put("contractFlow", billBalance.getContractFlow());
        map.put("billPlanFlow", billBalance.getBillPlanFlow());
        map.put("statusId", PlanBalanceStatusEnum.AuditPass.getId());
        return crmContractBillBalanceExtMapper.getBillBalanceExtByContractFlow(map);
    }

    @Override
    public ErpCrmContractBillBalance readBillBalance(String record_flow) {
        return contractBillBalanceMapper.selectByPrimaryKey(record_flow);
    }

    @Override
    public int saveBillBalance(ErpCrmContractBillBalance balance) {
            save(balance);
            String billPlanFlow =  balance.getBillPlanFlow();
            BigDecimal balanceFund = contractBillBalanceExtMapper.getBalanceFund(balance);
            ErpCrmContractBillPlan payPlan = billPlanBiz.readPayPlan(billPlanFlow);
            //到账/付款金额
            payPlan.setBillBalanceFund(balanceFund);
            //状态
            BigDecimal payFund = payPlan.getBillPayFund();
            int result = payFund.compareTo(balanceFund);
            if(result == 1){
                payPlan.setBillPlanStatusId(PayPlanStatusEnum.InProgress.getId());
                payPlan.setBillPlanStatusName(PayPlanStatusEnum.InProgress.getName());
            }else if(result == 0 || result == -1){
                payPlan.setBillPlanStatusId(PayPlanStatusEnum.Complete.getId());
                payPlan.setBillPlanStatusName(PayPlanStatusEnum.Complete.getName());
            }else {
                payPlan.setBillPlanStatusId(PayPlanStatusEnum.NotStart.getId());
                payPlan.setBillPlanStatusName(PayPlanStatusEnum.NotStart.getName());
            }
            return billPlanBiz.save(payPlan);
    }
    @Override
    public int save(ErpCrmContractBillBalance balance) {
        if(StringUtil.isBlank(balance.getRecordFlow())){
            balance.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(balance, true);
            return contractBillBalanceMapper.insert(balance);
        }else{
            GeneralMethod.setRecordInfo(balance, false);
            return contractBillBalanceMapper.updateByPrimaryKeySelective(balance);
        }
    }

    @Override
    public BigDecimal getAllBillMoneyByContractFlow(String contractFlow) {
        return contractBillBalanceExtMapper.getAllBillMoneyByContractFlow(contractFlow);
    }

    @Override
    public Integer sumBalancePayFundByFlow(String contractFlow) {
        return contractBillBalanceExtMapper.sumBalancePayFundByFlow(contractFlow);
    }
}
