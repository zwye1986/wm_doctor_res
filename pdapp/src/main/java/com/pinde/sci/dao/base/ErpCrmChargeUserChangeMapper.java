package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmChargeUserChange;
import com.pinde.sci.model.mo.ErpCrmChargeUserChangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmChargeUserChangeMapper {
    int countByExample(ErpCrmChargeUserChangeExample example);

    int deleteByExample(ErpCrmChargeUserChangeExample example);

    int deleteByPrimaryKey(String changeFlow);

    int insert(ErpCrmChargeUserChange record);

    int insertSelective(ErpCrmChargeUserChange record);

    List<ErpCrmChargeUserChange> selectByExample(ErpCrmChargeUserChangeExample example);

    ErpCrmChargeUserChange selectByPrimaryKey(String changeFlow);

    int updateByExampleSelective(@Param("record") ErpCrmChargeUserChange record, @Param("example") ErpCrmChargeUserChangeExample example);

    int updateByExample(@Param("record") ErpCrmChargeUserChange record, @Param("example") ErpCrmChargeUserChangeExample example);

    int updateByPrimaryKeySelective(ErpCrmChargeUserChange record);

    int updateByPrimaryKey(ErpCrmChargeUserChange record);
}