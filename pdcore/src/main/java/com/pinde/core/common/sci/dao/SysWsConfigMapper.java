package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysWsConfig;
import com.pinde.core.model.SysWsConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysWsConfigMapper {
    int countByExample(SysWsConfigExample example);

    int deleteByExample(SysWsConfigExample example);

    int deleteByPrimaryKey(String wsId);

    int insert(SysWsConfig record);

    int insertSelective(SysWsConfig record);

    List<SysWsConfig> selectByExample(SysWsConfigExample example);

    SysWsConfig selectByPrimaryKey(String wsId);

    int updateByExampleSelective(@Param("record") SysWsConfig record, @Param("example") SysWsConfigExample example);

    int updateByExample(@Param("record") SysWsConfig record, @Param("example") SysWsConfigExample example);

    int updateByPrimaryKeySelective(SysWsConfig record);

    int updateByPrimaryKey(SysWsConfig record);
}