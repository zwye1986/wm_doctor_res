package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcElement;
import com.pinde.sci.model.mo.EdcElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcElementMapper {
    int countByExample(EdcElementExample example);

    int deleteByExample(EdcElementExample example);

    int deleteByPrimaryKey(String elementFlow);

    int insert(EdcElement record);

    int insertSelective(EdcElement record);

    List<EdcElement> selectByExample(EdcElementExample example);

    EdcElement selectByPrimaryKey(String elementFlow);

    int updateByExampleSelective(@Param("record") EdcElement record, @Param("example") EdcElementExample example);

    int updateByExample(@Param("record") EdcElement record, @Param("example") EdcElementExample example);

    int updateByPrimaryKeySelective(EdcElement record);

    int updateByPrimaryKey(EdcElement record);
}