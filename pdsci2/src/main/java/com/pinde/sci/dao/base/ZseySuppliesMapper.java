package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ZseySupplies;
import com.pinde.sci.model.mo.ZseySuppliesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZseySuppliesMapper {
    int countByExample(ZseySuppliesExample example);

    int deleteByExample(ZseySuppliesExample example);

    int deleteByPrimaryKey(String suppliesFlow);

    int insert(ZseySupplies record);

    int insertSelective(ZseySupplies record);

    List<ZseySupplies> selectByExample(ZseySuppliesExample example);

    ZseySupplies selectByPrimaryKey(String suppliesFlow);

    int updateByExampleSelective(@Param("record") ZseySupplies record, @Param("example") ZseySuppliesExample example);

    int updateByExample(@Param("record") ZseySupplies record, @Param("example") ZseySuppliesExample example);

    int updateByPrimaryKeySelective(ZseySupplies record);

    int updateByPrimaryKey(ZseySupplies record);
}