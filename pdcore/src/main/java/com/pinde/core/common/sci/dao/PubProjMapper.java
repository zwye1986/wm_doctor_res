package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PubProj;
import com.pinde.core.model.PubProjExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubProjMapper {
    int countByExample(PubProjExample example);

    int deleteByExample(PubProjExample example);

    int deleteByPrimaryKey(String projFlow);

    int insert(PubProj record);

    int insertSelective(PubProj record);

    List<PubProj> selectByExampleWithBLOBs(PubProjExample example);

    List<PubProj> selectByExample(PubProjExample example);

    PubProj selectByPrimaryKey(String projFlow);

    int updateByExampleSelective(@Param("record") PubProj record, @Param("example") PubProjExample example);

    int updateByExampleWithBLOBs(@Param("record") PubProj record, @Param("example") PubProjExample example);

    int updateByExample(@Param("record") PubProj record, @Param("example") PubProjExample example);

    int updateByPrimaryKeySelective(PubProj record);

    int updateByPrimaryKeyWithBLOBs(PubProj record);

    int updateByPrimaryKey(PubProj record);
}