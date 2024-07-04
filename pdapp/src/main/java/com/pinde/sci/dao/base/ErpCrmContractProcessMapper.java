package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpCrmContractProcess;
import com.pinde.sci.model.mo.ErpCrmContractProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpCrmContractProcessMapper {
    int countByExample(ErpCrmContractProcessExample example);

    int deleteByExample(ErpCrmContractProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(ErpCrmContractProcess record);

    int insertSelective(ErpCrmContractProcess record);

    List<ErpCrmContractProcess> selectByExample(ErpCrmContractProcessExample example);

    ErpCrmContractProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") ErpCrmContractProcess record, @Param("example") ErpCrmContractProcessExample example);

    int updateByExample(@Param("record") ErpCrmContractProcess record, @Param("example") ErpCrmContractProcessExample example);

    int updateByPrimaryKeySelective(ErpCrmContractProcess record);

    int updateByPrimaryKey(ErpCrmContractProcess record);
}