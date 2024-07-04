package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpOaWorkOrder;
import com.pinde.sci.model.mo.ErpOaWorkOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpOaWorkOrderMapper {
    int countByExample(ErpOaWorkOrderExample example);

    int deleteByExample(ErpOaWorkOrderExample example);

    int deleteByPrimaryKey(String workFlow);

    int insert(ErpOaWorkOrder record);

    int insertSelective(ErpOaWorkOrder record);

    List<ErpOaWorkOrder> selectByExampleWithBLOBs(ErpOaWorkOrderExample example);

    List<ErpOaWorkOrder> selectByExample(ErpOaWorkOrderExample example);

    ErpOaWorkOrder selectByPrimaryKey(String workFlow);

    int updateByExampleSelective(@Param("record") ErpOaWorkOrder record, @Param("example") ErpOaWorkOrderExample example);

    int updateByExampleWithBLOBs(@Param("record") ErpOaWorkOrder record, @Param("example") ErpOaWorkOrderExample example);

    int updateByExample(@Param("record") ErpOaWorkOrder record, @Param("example") ErpOaWorkOrderExample example);

    int updateByPrimaryKeySelective(ErpOaWorkOrder record);

    int updateByPrimaryKeyWithBLOBs(ErpOaWorkOrder record);

    int updateByPrimaryKey(ErpOaWorkOrder record);
}