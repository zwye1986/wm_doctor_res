package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmChargeUserLog;
import com.pinde.sci.model.mo.ErpCrmChargeUserLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmChargeUserLogMapper {
    int countByExample(ErpCrmChargeUserLogExample example);

    int deleteByExample(ErpCrmChargeUserLogExample example);

    int deleteByPrimaryKey(String logFlow);

    int insert(ErpCrmChargeUserLog record);

    int insertSelective(ErpCrmChargeUserLog record);

    List<ErpCrmChargeUserLog> selectByExample(ErpCrmChargeUserLogExample example);

    ErpCrmChargeUserLog selectByPrimaryKey(String logFlow);

    int updateByExampleSelective(@Param("record") ErpCrmChargeUserLog record, @Param("example") ErpCrmChargeUserLogExample example);

    int updateByExample(@Param("record") ErpCrmChargeUserLog record, @Param("example") ErpCrmChargeUserLogExample example);

    int updateByPrimaryKeySelective(ErpCrmChargeUserLog record);

    int updateByPrimaryKey(ErpCrmChargeUserLog record);
}