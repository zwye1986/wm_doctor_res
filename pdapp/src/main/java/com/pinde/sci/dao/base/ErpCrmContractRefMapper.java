package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractRef;
import com.pinde.sci.model.mo.ErpCrmContractRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractRefMapper {
    int countByExample(ErpCrmContractRefExample example);

    int deleteByExample(ErpCrmContractRefExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ErpCrmContractRef record);

    int insertSelective(ErpCrmContractRef record);

    List<ErpCrmContractRef> selectByExample(ErpCrmContractRefExample example);

    ErpCrmContractRef selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractRef record, @Param("example") ErpCrmContractRefExample example);

    int updateByExample(@Param("record") ErpCrmContractRef record, @Param("example") ErpCrmContractRefExample example);

    int updateByPrimaryKeySelective(ErpCrmContractRef record);

    int updateByPrimaryKey(ErpCrmContractRef record);
}