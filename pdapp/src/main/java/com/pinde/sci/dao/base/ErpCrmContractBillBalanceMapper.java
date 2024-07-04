package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractBillBalance;
import com.pinde.sci.model.mo.ErpCrmContractBillBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractBillBalanceMapper {
    int countByExample(ErpCrmContractBillBalanceExample example);

    int deleteByExample(ErpCrmContractBillBalanceExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ErpCrmContractBillBalance record);

    int insertSelective(ErpCrmContractBillBalance record);

    List<ErpCrmContractBillBalance> selectByExample(ErpCrmContractBillBalanceExample example);

    ErpCrmContractBillBalance selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractBillBalance record, @Param("example") ErpCrmContractBillBalanceExample example);

    int updateByExample(@Param("record") ErpCrmContractBillBalance record, @Param("example") ErpCrmContractBillBalanceExample example);

    int updateByPrimaryKeySelective(ErpCrmContractBillBalance record);

    int updateByPrimaryKey(ErpCrmContractBillBalance record);
}