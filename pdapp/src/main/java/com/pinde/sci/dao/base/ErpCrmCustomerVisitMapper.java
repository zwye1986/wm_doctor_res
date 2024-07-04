package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmCustomerVisit;
import com.pinde.sci.model.mo.ErpCrmCustomerVisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmCustomerVisitMapper {
    int countByExample(ErpCrmCustomerVisitExample example);

    int deleteByExample(ErpCrmCustomerVisitExample example);

    int deleteByPrimaryKey(String visitFlow);

    int insert(ErpCrmCustomerVisit record);

    int insertSelective(ErpCrmCustomerVisit record);

    List<ErpCrmCustomerVisit> selectByExample(ErpCrmCustomerVisitExample example);

    ErpCrmCustomerVisit selectByPrimaryKey(String visitFlow);

    int updateByExampleSelective(@Param("record") ErpCrmCustomerVisit record, @Param("example") ErpCrmCustomerVisitExample example);

    int updateByExample(@Param("record") ErpCrmCustomerVisit record, @Param("example") ErpCrmCustomerVisitExample example);

    int updateByPrimaryKeySelective(ErpCrmCustomerVisit record);

    int updateByPrimaryKey(ErpCrmCustomerVisit record);
}