package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractProduct;
import com.pinde.sci.model.mo.ErpCrmContractProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractProductMapper {
    int countByExample(ErpCrmContractProductExample example);

    int deleteByExample(ErpCrmContractProductExample example);

    int deleteByPrimaryKey(String productFlow);

    int insert(ErpCrmContractProduct record);

    int insertSelective(ErpCrmContractProduct record);

    List<ErpCrmContractProduct> selectByExample(ErpCrmContractProductExample example);

    ErpCrmContractProduct selectByPrimaryKey(String productFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractProduct record, @Param("example") ErpCrmContractProductExample example);

    int updateByExample(@Param("record") ErpCrmContractProduct record, @Param("example") ErpCrmContractProductExample example);

    int updateByPrimaryKeySelective(ErpCrmContractProduct record);

    int updateByPrimaryKey(ErpCrmContractProduct record);
}