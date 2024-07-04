package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractPayPlan;
import com.pinde.sci.model.mo.ErpCrmContractPayPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractPayPlanMapper {
    int countByExample(ErpCrmContractPayPlanExample example);

    int deleteByExample(ErpCrmContractPayPlanExample example);

    int deleteByPrimaryKey(String planFlow);

    int insert(ErpCrmContractPayPlan record);

    int insertSelective(ErpCrmContractPayPlan record);

    List<ErpCrmContractPayPlan> selectByExample(ErpCrmContractPayPlanExample example);

    ErpCrmContractPayPlan selectByPrimaryKey(String planFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractPayPlan record, @Param("example") ErpCrmContractPayPlanExample example);

    int updateByExample(@Param("record") ErpCrmContractPayPlan record, @Param("example") ErpCrmContractPayPlanExample example);

    int updateByPrimaryKeySelective(ErpCrmContractPayPlan record);

    int updateByPrimaryKey(ErpCrmContractPayPlan record);
}