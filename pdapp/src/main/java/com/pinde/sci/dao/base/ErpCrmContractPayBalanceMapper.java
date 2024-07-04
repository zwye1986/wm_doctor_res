package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractPayBalance;
import com.pinde.sci.model.mo.ErpCrmContractPayBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractPayBalanceMapper {
    int countByExample(ErpCrmContractPayBalanceExample example);

    int deleteByExample(ErpCrmContractPayBalanceExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ErpCrmContractPayBalance record);

    int insertSelective(ErpCrmContractPayBalance record);

    List<ErpCrmContractPayBalance> selectByExample(ErpCrmContractPayBalanceExample example);

    ErpCrmContractPayBalance selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractPayBalance record, @Param("example") ErpCrmContractPayBalanceExample example);

    int updateByExample(@Param("record") ErpCrmContractPayBalance record, @Param("example") ErpCrmContractPayBalanceExample example);

    int updateByPrimaryKeySelective(ErpCrmContractPayBalance record);

    int updateByPrimaryKey(ErpCrmContractPayBalance record);
}