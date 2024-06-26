package com.pinde.sci.dao.erp;

import com.pinde.sci.model.erp.ErpOaWorkOrderExt;

import java.util.List;
import java.util.Map;

public interface ErpOaWorkOrderExtMapper {

    List<ErpOaWorkOrderExt> searchWorkOrderList(Map<String, Object> paramMap);

    List<ErpOaWorkOrderExt> applyWorkOrderList(Map<String, Object> paramMap);

    int countWorkOrderList(Map<String, Object> paramMap);
}
