package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResKgCfg;
import com.pinde.core.model.ResKgCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResKgCfgMapper {
    int countByExample(ResKgCfgExample example);

    int deleteByExample(ResKgCfgExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(ResKgCfg record);

    int insertSelective(ResKgCfg record);

    List<ResKgCfg> selectByExample(ResKgCfgExample example);

    ResKgCfg selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") ResKgCfg record, @Param("example") ResKgCfgExample example);

    int updateByExample(@Param("record") ResKgCfg record, @Param("example") ResKgCfgExample example);

    int updateByPrimaryKeySelective(ResKgCfg record);

    int updateByPrimaryKey(ResKgCfg record);
}