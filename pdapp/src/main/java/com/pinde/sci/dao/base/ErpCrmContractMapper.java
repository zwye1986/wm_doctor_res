package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContract;
import com.pinde.sci.model.mo.ErpCrmContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractMapper {
    int countByExample(ErpCrmContractExample example);

    int deleteByExample(ErpCrmContractExample example);

    int deleteByPrimaryKey(String contractFlow);

    int insert(ErpCrmContract record);

    int insertSelective(ErpCrmContract record);

    List<ErpCrmContract> selectByExample(ErpCrmContractExample example);

    ErpCrmContract selectByPrimaryKey(String contractFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContract record, @Param("example") ErpCrmContractExample example);

    int updateByExample(@Param("record") ErpCrmContract record, @Param("example") ErpCrmContractExample example);

    int updateByPrimaryKeySelective(ErpCrmContract record);

    int updateByPrimaryKey(ErpCrmContract record);
}