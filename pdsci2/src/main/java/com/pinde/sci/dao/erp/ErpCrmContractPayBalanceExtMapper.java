package com.pinde.sci.dao.erp;

import com.pinde.sci.model.erp.ErpCrmContractPayBalanceExt;
import com.pinde.sci.model.mo.ErpCrmContractPayBalance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ErpCrmContractPayBalanceExtMapper {

    BigDecimal getBalanceFund(ErpCrmContractPayBalance payBalance);

    BigDecimal countBalanceFund(Map<String, Object> paramMap);

    BigDecimal getContractBalanceMoney(String contractFlow);

    BigDecimal getContractPlanMoney(String contractFlow);

    List<ErpCrmContractPayBalanceExt> getContractPayBalanceExtByContractFlow(Map<String, Object> paramMap);

    Integer sumBalancePayFundByFlow(String contractFlow);
}
