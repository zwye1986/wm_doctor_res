package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmCustomerUser;
import com.pinde.sci.model.mo.ErpCrmCustomerUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmCustomerUserMapper {
    int countByExample(ErpCrmCustomerUserExample example);

    int deleteByExample(ErpCrmCustomerUserExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(ErpCrmCustomerUser record);

    int insertSelective(ErpCrmCustomerUser record);

    List<ErpCrmCustomerUser> selectByExample(ErpCrmCustomerUserExample example);

    ErpCrmCustomerUser selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") ErpCrmCustomerUser record, @Param("example") ErpCrmCustomerUserExample example);

    int updateByExample(@Param("record") ErpCrmCustomerUser record, @Param("example") ErpCrmCustomerUserExample example);

    int updateByPrimaryKeySelective(ErpCrmCustomerUser record);

    int updateByPrimaryKey(ErpCrmCustomerUser record);
}