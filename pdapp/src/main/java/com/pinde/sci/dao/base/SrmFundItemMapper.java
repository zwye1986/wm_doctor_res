package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmFundItem;
import com.pinde.sci.model.mo.SrmFundItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmFundItemMapper {
    int countByExample(SrmFundItemExample example);

    int deleteByExample(SrmFundItemExample example);

    int deleteByPrimaryKey(String itemFlow);

    int insert(SrmFundItem record);

    int insertSelective(SrmFundItem record);

    List<SrmFundItem> selectByExample(SrmFundItemExample example);

    SrmFundItem selectByPrimaryKey(String itemFlow);

    int updateByExampleSelective(@Param("record") SrmFundItem record, @Param("example") SrmFundItemExample example);

    int updateByExample(@Param("record") SrmFundItem record, @Param("example") SrmFundItemExample example);

    int updateByPrimaryKeySelective(SrmFundItem record);

    int updateByPrimaryKey(SrmFundItem record);
}