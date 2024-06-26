package com.pinde.sci.biz.erp;

import com.pinde.sci.model.mo.ErpCrmChargeUserChange;
import com.pinde.sci.model.mo.ErpCrmContractOtherPower;

import java.util.List;

public interface IErpCrmChargeUserChangeBiz {

    List<ErpCrmChargeUserChange> searchByChange(ErpCrmChargeUserChange change);

    ErpCrmChargeUserChange readByFlow(String changeFlow);

    int save(ErpCrmChargeUserChange change);

    int audit(ErpCrmChargeUserChange change);

    int checkHave(ErpCrmChargeUserChange change);
}
