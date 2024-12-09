package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnSupplies;
import com.pinde.core.model.LcjnSuppliesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LcjnSuppliesMapper {
    int countByExample(LcjnSuppliesExample example);

    int deleteByExample(LcjnSuppliesExample example);

    int deleteByPrimaryKey(String suppliesFlow);

    int insert(LcjnSupplies record);

    int insertSelective(LcjnSupplies record);

    List<LcjnSupplies> selectByExample(LcjnSuppliesExample example);

    LcjnSupplies selectByPrimaryKey(String suppliesFlow);

    int updateByExampleSelective(@Param("record") LcjnSupplies record, @Param("example") LcjnSuppliesExample example);

    int updateByExample(@Param("record") LcjnSupplies record, @Param("example") LcjnSuppliesExample example);

    int updateByPrimaryKeySelective(LcjnSupplies record);

    int updateByPrimaryKey(LcjnSupplies record);
}