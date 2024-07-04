package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractUser;
import com.pinde.sci.model.mo.ErpCrmContractUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractUserMapper {
    int countByExample(ErpCrmContractUserExample example);

    int deleteByExample(ErpCrmContractUserExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ErpCrmContractUser record);

    int insertSelective(ErpCrmContractUser record);

    List<ErpCrmContractUser> selectByExample(ErpCrmContractUserExample example);

    ErpCrmContractUser selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractUser record, @Param("example") ErpCrmContractUserExample example);

    int updateByExample(@Param("record") ErpCrmContractUser record, @Param("example") ErpCrmContractUserExample example);

    int updateByPrimaryKeySelective(ErpCrmContractUser record);

    int updateByPrimaryKey(ErpCrmContractUser record);
}