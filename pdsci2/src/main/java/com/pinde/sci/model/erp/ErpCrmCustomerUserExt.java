package com.pinde.sci.model.erp;

import com.pinde.sci.model.mo.ErpCrmContractPower;
import com.pinde.sci.model.mo.ErpCrmCustomerUser;

public class ErpCrmCustomerUserExt extends ErpCrmCustomerUser {
    private String recordFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }
}