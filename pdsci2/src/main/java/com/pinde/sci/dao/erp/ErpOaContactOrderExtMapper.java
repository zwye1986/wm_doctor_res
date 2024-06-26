package com.pinde.sci.dao.erp;

import com.pinde.sci.model.erp.ErpOrderCustomerExt;
import com.pinde.sci.model.mo.ErpOaContactOrder;

import java.util.List;
import java.util.Map;

public interface ErpOaContactOrderExtMapper {

    List<ErpOrderCustomerExt> searchContactOrderList(Map<String, Object> paramMap);

    int countContactOrderList(Map<String, Object> paramMap);
}
