package com.pinde.sci.dao.erp;

import com.pinde.sci.model.erp.ErpCrmContractBillBalanceExt;
import com.pinde.sci.model.mo.ErpCrmContractBillBalance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ErpCrmContractBillBalanceExtMapper {

    BigDecimal getBalanceFund(ErpCrmContractBillBalance payBalance);

    BigDecimal getAllBillMoneyByContractFlow(String contractFlow);

    List<ErpCrmContractBillBalanceExt> getBillBalanceExtByContractFlow(Map<String, Object> map);

    Integer sumBalancePayFundByFlow(String contractFlow);
}
