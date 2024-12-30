package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PubModule;
import com.pinde.core.model.PubModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubModuleMapper {
    int countByExample(PubModuleExample example);

    int deleteByExample(PubModuleExample example);

    int deleteByPrimaryKey(String moduleFlow);

    int insert(PubModule record);

    int insertSelective(PubModule record);

    List<PubModule> selectByExample(PubModuleExample example);

    PubModule selectByPrimaryKey(String moduleFlow);

    int updateByExampleSelective(@Param("record") PubModule record, @Param("example") PubModuleExample example);

    int updateByExample(@Param("record") PubModule record, @Param("example") PubModuleExample example);

    int updateByPrimaryKeySelective(PubModule record);

    int updateByPrimaryKey(PubModule record);
}