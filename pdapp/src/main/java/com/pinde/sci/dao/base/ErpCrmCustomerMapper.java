package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmCustomer;
import com.pinde.sci.model.mo.ErpCrmCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmCustomerMapper {
    int countByExample(ErpCrmCustomerExample example);

    int deleteByExample(ErpCrmCustomerExample example);

    int deleteByPrimaryKey(String customerFlow);

    int insert(ErpCrmCustomer record);

    int insertSelective(ErpCrmCustomer record);

    List<ErpCrmCustomer> selectByExample(ErpCrmCustomerExample example);

    ErpCrmCustomer selectByPrimaryKey(String customerFlow);

    int updateByExampleSelective(@Param("record") ErpCrmCustomer record, @Param("example") ErpCrmCustomerExample example);

    int updateByExample(@Param("record") ErpCrmCustomer record, @Param("example") ErpCrmCustomerExample example);

    int updateByPrimaryKeySelective(ErpCrmCustomer record);

    int updateByPrimaryKey(ErpCrmCustomer record);
}