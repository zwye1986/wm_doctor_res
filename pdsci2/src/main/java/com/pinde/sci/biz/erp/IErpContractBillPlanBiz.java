package com.pinde.sci.biz.erp;

import com.pinde.sci.model.mo.ErpCrmContractBillPlan;
import com.pinde.sci.model.mo.ErpCrmContractPayPlan;

import java.util.List;

public interface IErpContractBillPlanBiz {

    /**
     * 查询合同开票计划
     * @param billPlan
     * @return
     */
    List<ErpCrmContractBillPlan> searchContractBillPlanList(ErpCrmContractBillPlan billPlan);

    /**
     * 保存合同开票计划
     * @param billPlanList
     * @param contractFlow
     * @return
     */
    String saveContractBillPlan(List<ErpCrmContractBillPlan> billPlanList, String contractFlow);

    int save(ErpCrmContractBillPlan billPlan);

    ErpCrmContractBillPlan readPayPlan(String billPlanFlow);

    Integer sumBillPayFundByFlow(String contractFlow);
}
