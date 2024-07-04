package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractBillPlan;
import com.pinde.sci.model.mo.ErpCrmContractBillPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractBillPlanMapper {
    int countByExample(ErpCrmContractBillPlanExample example);

    int deleteByExample(ErpCrmContractBillPlanExample example);

    int deleteByPrimaryKey(String billPlanFlow);

    int insert(ErpCrmContractBillPlan record);

    int insertSelective(ErpCrmContractBillPlan record);

    List<ErpCrmContractBillPlan> selectByExample(ErpCrmContractBillPlanExample example);

    ErpCrmContractBillPlan selectByPrimaryKey(String billPlanFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractBillPlan record, @Param("example") ErpCrmContractBillPlanExample example);

    int updateByExample(@Param("record") ErpCrmContractBillPlan record, @Param("example") ErpCrmContractBillPlanExample example);

    int updateByPrimaryKeySelective(ErpCrmContractBillPlan record);

    int updateByPrimaryKey(ErpCrmContractBillPlan record);
}