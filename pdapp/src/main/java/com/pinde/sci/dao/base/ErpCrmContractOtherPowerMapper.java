package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractOtherPower;
import com.pinde.sci.model.mo.ErpCrmContractOtherPowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractOtherPowerMapper {
    int countByExample(ErpCrmContractOtherPowerExample example);

    int deleteByExample(ErpCrmContractOtherPowerExample example);

    int deleteByPrimaryKey(String otherPowerFlow);

    int insert(ErpCrmContractOtherPower record);

    int insertSelective(ErpCrmContractOtherPower record);

    List<ErpCrmContractOtherPower> selectByExample(ErpCrmContractOtherPowerExample example);

    ErpCrmContractOtherPower selectByPrimaryKey(String otherPowerFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractOtherPower record, @Param("example") ErpCrmContractOtherPowerExample example);

    int updateByExample(@Param("record") ErpCrmContractOtherPower record, @Param("example") ErpCrmContractOtherPowerExample example);

    int updateByPrimaryKeySelective(ErpCrmContractOtherPower record);

    int updateByPrimaryKey(ErpCrmContractOtherPower record);
}