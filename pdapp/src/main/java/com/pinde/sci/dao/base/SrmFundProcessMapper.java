package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmFundProcess;
import com.pinde.sci.model.mo.SrmFundProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmFundProcessMapper {
    int countByExample(SrmFundProcessExample example);

    int deleteByExample(SrmFundProcessExample example);

    int deleteByPrimaryKey(String fundProcessFlow);

    int insert(SrmFundProcess record);

    int insertSelective(SrmFundProcess record);

    List<SrmFundProcess> selectByExample(SrmFundProcessExample example);

    SrmFundProcess selectByPrimaryKey(String fundProcessFlow);

    int updateByExampleSelective(@Param("record") SrmFundProcess record, @Param("example") SrmFundProcessExample example);

    int updateByExample(@Param("record") SrmFundProcess record, @Param("example") SrmFundProcessExample example);

    int updateByPrimaryKeySelective(SrmFundProcess record);

    int updateByPrimaryKey(SrmFundProcess record);
}