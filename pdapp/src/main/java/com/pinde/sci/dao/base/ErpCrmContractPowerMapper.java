package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractPower;
import com.pinde.sci.model.mo.ErpCrmContractPowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractPowerMapper {
    int countByExample(ErpCrmContractPowerExample example);

    int deleteByExample(ErpCrmContractPowerExample example);

    int deleteByPrimaryKey(String powerFlow);

    int insert(ErpCrmContractPower record);

    int insertSelective(ErpCrmContractPower record);

    List<ErpCrmContractPower> selectByExample(ErpCrmContractPowerExample example);

    ErpCrmContractPower selectByPrimaryKey(String powerFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractPower record, @Param("example") ErpCrmContractPowerExample example);

    int updateByExample(@Param("record") ErpCrmContractPower record, @Param("example") ErpCrmContractPowerExample example);

    int updateByPrimaryKeySelective(ErpCrmContractPower record);

    int updateByPrimaryKey(ErpCrmContractPower record);
}