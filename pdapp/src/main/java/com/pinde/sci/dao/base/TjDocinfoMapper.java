package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TjDocinfo;
import com.pinde.sci.model.mo.TjDocinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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