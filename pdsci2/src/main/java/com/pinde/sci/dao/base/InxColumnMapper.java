package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.InxColumn;
import com.pinde.sci.model.mo.InxColumnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InxColumnMapper {
    int countByExample(InxColumnExample example);

    int deleteByExample(InxColumnExample example);

    int deleteByPrimaryKey(String columnFlow);

    int insert(InxColumn record);

    int insertSelective(InxColumn record);

    List<InxColumn> selectByExample(InxColumnExample example);

    InxColumn selectByPrimaryKey(String columnFlow);

    int updateByExampleSelective(@Param("record") InxColumn record, @Param("example") InxColumnExample example);

    int updateByExample(@Param("record") InxColumn record, @Param("example") InxColumnExample example);

    int updateByPrimaryKeySelective(InxColumn record);

    int updateByPrimaryKey(InxColumn record);
}