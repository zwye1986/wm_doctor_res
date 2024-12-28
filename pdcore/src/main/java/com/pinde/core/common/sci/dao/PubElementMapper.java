package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PubElement;
import com.pinde.core.model.PubElementExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubElementMapper {
    int countByExample(PubElementExample example);

    int deleteByExample(PubElementExample example);

    int deleteByPrimaryKey(String elementFlow);

    int insert(PubElement record);

    int insertSelective(PubElement record);

    List<PubElement> selectByExample(PubElementExample example);

    PubElement selectByPrimaryKey(String elementFlow);

    int updateByExampleSelective(@Param("record") PubElement record, @Param("example") PubElementExample example);

    int updateByExample(@Param("record") PubElement record, @Param("example") PubElementExample example);

    int updateByPrimaryKeySelective(PubElement record);

    int updateByPrimaryKey(PubElement record);
}