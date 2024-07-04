package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubModule;
import com.pinde.sci.model.mo.PubModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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