package com.pinde.sci.dao.erp;

import com.pinde.sci.form.erp.InputReportForm;
import com.pinde.sci.model.mo.ErpCrmCustomer;
import com.pinde.sci.model.mo.ErpCrmCustomerUser;

import java.util.List;
import java.util.Map;

public interface ErpCrmCustomerExtMapper {
    /**
     * 查询客户
     *
     * @param paramMap
     * @return
     */
    List<ErpCrmCustomer> searchCustomerList(Map<String, Object> paramMap);

    List<InputReportForm> searchCrmInput(Map<String, Object> paramMap);

    /**
     * 根据一批流水号查询客户联系人
     *
     * @param userFlowList
     * @return
     */
    List<ErpCrmCustomerUser> searchCustomerUserList(List<String> userFlowList);

    int countCustomerHasContract(Map<String, Object> paramMap);

    List<Map<String,Object>> crmInputDetailCustomer(Map<String, Object> paramMap);

    List<Map<String,Object>> crmInputDetailCustomerUser(Map<String, Object> paramMap);

    List<ErpCrmCustomerUser> searchCustomerUserNumber();

    List<ErpCrmCustomer> searchCustomerListByContract(Map<String, Object> paramMap);
}
