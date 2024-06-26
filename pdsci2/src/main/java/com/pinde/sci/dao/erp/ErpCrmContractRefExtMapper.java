package com.pinde.sci.dao.erp;

import com.pinde.sci.model.erp.ErpCrmContractRefExt;

import java.util.List;
import java.util.Map;

public interface ErpCrmContractRefExtMapper {

    /**
     * 查询已合同关联的主合同
     *
     * @param paramMap
     * @return
     */
    List<ErpCrmContractRefExt> searchContractListByRef(Map<String, Object> paramMap);
}
