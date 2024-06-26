package com.pinde.sci.dao.erp;

import com.pinde.sci.model.erp.ErpCrmContractUserExt;

import java.util.List;
import java.util.Map;

public interface ErpCrmContractUserExtMapper {

    List<ErpCrmContractUserExt> searchContractUserExtList(Map<String, Object> paramMap);
}
