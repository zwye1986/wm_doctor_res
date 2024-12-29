package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PubWorkpaper;
import com.pinde.core.model.PubWorkpaperExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubWorkpaperMapper {
    int countByExample(PubWorkpaperExample example);

    int deleteByExample(PubWorkpaperExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubWorkpaper record);

    int insertSelective(PubWorkpaper record);

    List<PubWorkpaper> selectByExampleWithBLOBs(PubWorkpaperExample example);

    List<PubWorkpaper> selectByExample(PubWorkpaperExample example);

    PubWorkpaper selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubWorkpaper record, @Param("example") PubWorkpaperExample example);

    int updateByExampleWithBLOBs(@Param("record") PubWorkpaper record, @Param("example") PubWorkpaperExample example);

    int updateByExample(@Param("record") PubWorkpaper record, @Param("example") PubWorkpaperExample example);

    int updateByPrimaryKeySelective(PubWorkpaper record);

    int updateByPrimaryKeyWithBLOBs(PubWorkpaper record);

    int updateByPrimaryKey(PubWorkpaper record);
}