package com.pinde.sci.dao.erp;

import com.pinde.sci.model.erp.ErpCrmContractExt;
import com.pinde.sci.model.erp.ErpCrmContractLicExt;
import com.pinde.sci.model.mo.ErpCrmContract;
import com.pinde.sci.model.mo.ErpCrmProductBuildInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ErpCrmContractExtMapper {
    /**
     * 查询合同及其客户信息
     *
     * @param paramMap
     * @return
     */
    List<ErpCrmContractExt> searchContractList(Map<String, Object> paramMap);

    List<ErpCrmContractExt> searchContracts(Map<String, Object> paramMap);

    List<ErpCrmContractLicExt> searchContractLics(Map<String, Object> paramMap);

    BigDecimal countContractFundNumber(Map<String, Object> paramMap);

    BigDecimal searchAllMoney(Map<String, Object> paramMap);

    List<ErpCrmContract> searchCustomerList(Map<String, Object> paramMap);

    List<ErpCrmContractExt> searchErpContractListNew(Map<String, Object> paramMap);
    List<ErpCrmContractExt> searchErpContractListBillNew(Map<String, Object> paramMap);

    List<Map<String,Object>> yszcx(Map<String, Object> paramMap);
    List<Map<String,Object>> zxcx(Map<String, Object> paramMap);
    List<Map<String,Object>> cwyscx(Map<String, Object> paramMap);
    List<Map<String,Object>> dkcx(Map<String, Object> paramMap);
    List<Map<String,Object>> wh(Map<String, Object> paramMap);

    ErpCrmProductBuildInfo getEmptyBuildInfoByFlow(String contractFlow);

    Map<String,Object> cwysFund(Map<String, Object> paramMap);

    Map<String,Object> searchErpContractListNewMap(Map<String, Object> paramMap);

    Map<String,Object> waitPayAuditMap(Map<String, Object> paramMap);

    Map<String,Object> waitBillAuditMap(Map<String, Object> paramMap);

    List<ErpCrmContract> searchErpContractList2(Map<String, Object> param);
}
