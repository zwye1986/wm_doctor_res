package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResBaseevalForm;
import com.pinde.core.model.ResBaseevalFormExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseevalFormMapper {
    int countByExample(ResBaseevalFormExample example);

    int deleteByExample(ResBaseevalFormExample example);

    int deleteByPrimaryKey(String formFlow);

    int insert(ResBaseevalForm record);

    int insertSelective(ResBaseevalForm record);

    List<ResBaseevalForm> selectByExample(ResBaseevalFormExample example);

    ResBaseevalForm selectByPrimaryKey(String formFlow);

    int updateByExampleSelective(@Param("record") ResBaseevalForm record, @Param("example") ResBaseevalFormExample example);

    int updateByExample(@Param("record") ResBaseevalForm record, @Param("example") ResBaseevalFormExample example);

    int updateByPrimaryKeySelective(ResBaseevalForm record);

    int updateByPrimaryKey(ResBaseevalForm record);
}