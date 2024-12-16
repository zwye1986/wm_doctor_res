package com.pinde.sci.dao.base;

import com.pinde.core.model.TjDocinfo;
import com.pinde.core.model.TjDocinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TjDocinfoMapper {
    int countByExample(TjDocinfoExample example);

    int deleteByExample(TjDocinfoExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(TjDocinfo record);

    int insertSelective(TjDocinfo record);

    List<TjDocinfo> selectByExample(TjDocinfoExample example);

    TjDocinfo selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") TjDocinfo record, @Param("example") TjDocinfoExample example);

    int updateByExample(@Param("record") TjDocinfo record, @Param("example") TjDocinfoExample example);

    int updateByPrimaryKeySelective(TjDocinfo record);

    int updateByPrimaryKey(TjDocinfo record);
}