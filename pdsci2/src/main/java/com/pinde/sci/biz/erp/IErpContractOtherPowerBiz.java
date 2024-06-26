package com.pinde.sci.biz.erp;

import com.pinde.sci.model.erp.ErpCrmContractPowerExt;
import com.pinde.sci.model.mo.ErpCrmContractOtherPower;
import com.pinde.sci.model.mo.ErpCrmContractPower;

import java.util.List;

public interface IErpContractOtherPowerBiz {

    List<ErpCrmContractOtherPower> searchContractPowerList(   ErpCrmContractOtherPower billPlan);

    String saveContractPower(List<ErpCrmContractOtherPower> powerList, String contractFlow);

    int save(ErpCrmContractOtherPower power);

    ErpCrmContractOtherPower readpower(String billPlanFlow);

    int deleteOtherPowerByContractFlow(String contractFlow);
}
