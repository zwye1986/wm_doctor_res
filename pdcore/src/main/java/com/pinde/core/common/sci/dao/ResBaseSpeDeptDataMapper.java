package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResBaseSpeDeptData;
import com.pinde.core.model.ResBaseSpeDeptDataExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseSpeDeptDataMapper {
    int countByExample(ResBaseSpeDeptDataExample example);

    int deleteByExample(ResBaseSpeDeptDataExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseSpeDeptData record);

    int insertSelective(ResBaseSpeDeptData record);

    List<ResBaseSpeDeptData> selectByExample(ResBaseSpeDeptDataExample example);

    ResBaseSpeDeptData selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseSpeDeptData record, @Param("example") ResBaseSpeDeptDataExample example);

    int updateByExample(@Param("record") ResBaseSpeDeptData record, @Param("example") ResBaseSpeDeptDataExample example);

    int updateByPrimaryKeySelective(ResBaseSpeDeptData record);

    int updateByPrimaryKey(ResBaseSpeDeptData record);
}