package com.pinde.sci.dao.erp;

import com.pinde.sci.model.erp.ErpCrmContractBillBalanceExt;
import com.pinde.sci.model.erp.ErpProductManageUserExt;
import com.pinde.sci.model.mo.ErpCrmContractBillBalance;
import com.pinde.sci.model.mo.ErpProductManage;
import com.pinde.sci.model.mo.ErpProductManageUser;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ErpProductManageExtMapper {

    List<ErpProductManage> ownerProducts(Map<String, Object> paramMap);

    int deleteJoinUserByManageFlow(@Param("manageFlow") String manageFlow);

    List<ErpProductManage> process(Map<String, Object> paramMap);

    List<ErpProductManage> query(Map<String, Object> paramMap);

    List<ErpProductManageUserExt> selectProductUserInfo(Map<String, Object> paramMap);
}
