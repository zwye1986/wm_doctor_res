package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcNormalValue;
import com.pinde.sci.model.mo.EdcNormalValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcNormalValueMapper {
    int countByExample(EdcNormalValueExample example);

    int deleteByExample(EdcNormalValueExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcNormalValue record);

    int insertSelective(EdcNormalValue record);

    List<EdcNormalValue> selectByExample(EdcNormalValueExample example);

    EdcNormalValue selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcNormalValue record, @Param("example") EdcNormalValueExample example);

    int updateByExample(@Param("record") EdcNormalValue record, @Param("example") EdcNormalValueExample example);

    int updateByPrimaryKeySelective(EdcNormalValue record);

    int updateByPrimaryKey(EdcNormalValue record);
}