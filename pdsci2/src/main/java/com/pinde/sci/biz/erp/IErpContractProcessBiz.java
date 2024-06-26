package com.pinde.sci.biz.erp;

import com.pinde.sci.model.mo.ErpCrmContract;
import com.pinde.sci.model.mo.ErpCrmContractOtherPower;
import com.pinde.sci.model.mo.ErpCrmContractProcess;

import java.util.List;

public interface IErpContractProcessBiz {

    int saveProcess(ErpCrmContract contract, ErpCrmContractProcess process);

    int auditContract(ErpCrmContract old, ErpCrmContractProcess process);

}
