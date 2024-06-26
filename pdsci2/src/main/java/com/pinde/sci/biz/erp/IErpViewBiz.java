package com.pinde.sci.biz.erp;

import com.pinde.sci.model.mo.ErpCrmContract;
import com.pinde.sci.model.mo.ErpCrmContractPayPlan;
import com.pinde.sci.model.mo.ErpCrmCustomer;
import com.pinde.sci.model.mo.ErpOaContactOrder;

import java.math.BigDecimal;
import java.util.List;

public interface IErpViewBiz {

    /**
     * 查询客户数量
     *
     * @param customer
     * @return
     */
    int countCustomer(ErpCrmCustomer customer);

    /**
     * 查询有合同的客户数量
     *
     * @return
     */
    int countCustomerHasContract();

    /**
     * 查询合同数量
     *
     * @param contract
     * @return
     */
    int countContract(ErpCrmContract contract);

    /**
     * 查询合同资金情况
     *
     * @param contract
     * @return
     */
    BigDecimal countContractFund(ErpCrmContract contract);

    /**
     * 查询合同回款情况
     *
     * @param contract
     * @param plan
     * @return
     */
    BigDecimal countPayPlanFund(ErpCrmContract contract, ErpCrmContractPayPlan plan);

    /**
     * 查询联系单数量
     *
     * @param statusList
     * @return
     */
    int countContactOrderByStatus(List<String> statusList);

    /**
     * 查询派工单数量
     *
     * @param statusList
     * @return
     */
    int countWorkOrderByStatus(List<String> statusList);

    int countContactOrder(ErpOaContactOrder contactOrder);

    int searchCustomerUserNumber();

//	public int countWorkOrder(ErpOaWorkOrder workOrder);
}
