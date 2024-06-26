package com.pinde.sci.biz.erp;

import com.pinde.sci.model.erp.ErpCrmContractPowerExt;
import com.pinde.sci.model.mo.ErpCrmContractBillPlan;
import com.pinde.sci.model.mo.ErpCrmContractPower;

import java.util.List;

public interface IErpContractPowerBiz {

    List<ErpCrmContractPower> searchContractPowerList(
            ErpCrmContractPower billPlan);

    String saveContractPower(List<ErpCrmContractPower> powerList, String contractFlow);

    int save(ErpCrmContractPower power);

    ErpCrmContractPower readpower(String billPlanFlow);

    String saveContractPowerFiles(List<ErpCrmContractPowerExt> powerList, String contractFlow);
}
