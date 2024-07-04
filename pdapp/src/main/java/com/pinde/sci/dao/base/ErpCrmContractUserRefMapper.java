package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractUserRef;
import com.pinde.sci.model.mo.ErpCrmContractUserRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractUserRefMapper {
    int countByExample(ErpCrmContractUserRefExample example);

    int deleteByExample(ErpCrmContractUserRefExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ErpCrmContractUserRef record);

    int insertSelective(ErpCrmContractUserRef record);

    List<ErpCrmContractUserRef> selectByExample(ErpCrmContractUserRefExample example);

    ErpCrmContractUserRef selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractUserRef record, @Param("example") ErpCrmContractUserRefExample example);

    int updateByExample(@Param("record") ErpCrmContractUserRef record, @Param("example") ErpCrmContractUserRefExample example);

    int updateByPrimaryKeySelective(ErpCrmContractUserRef record);

    int updateByPrimaryKey(ErpCrmContractUserRef record);
}