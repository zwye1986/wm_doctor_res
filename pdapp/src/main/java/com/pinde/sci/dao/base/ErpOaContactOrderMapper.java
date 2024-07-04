package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ErpOaContactOrder;
import com.pinde.sci.model.mo.ErpOaContactOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErpOaContactOrderMapper {
    int countByExample(ErpOaContactOrderExample example);

    int deleteByExample(ErpOaContactOrderExample example);

    int deleteByPrimaryKey(String contactFlow);

    int insert(ErpOaContactOrder record);

    int insertSelective(ErpOaContactOrder record);

    List<ErpOaContactOrder> selectByExampleWithBLOBs(ErpOaContactOrderExample example);

    List<ErpOaContactOrder> selectByExample(ErpOaContactOrderExample example);

    ErpOaContactOrder selectByPrimaryKey(String contactFlow);

    int updateByExampleSelective(@Param("record") ErpOaContactOrder record, @Param("example") ErpOaContactOrderExample example);

    int updateByExampleWithBLOBs(@Param("record") ErpOaContactOrder record, @Param("example") ErpOaContactOrderExample example);

    int updateByExample(@Param("record") ErpOaContactOrder record, @Param("example") ErpOaContactOrderExample example);

    int updateByPrimaryKeySelective(ErpOaContactOrder record);

    int updateByPrimaryKeyWithBLOBs(ErpOaContactOrder record);

    int updateByPrimaryKey(ErpOaContactOrder record);
}