package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResTestConfig;
import com.pinde.sci.model.mo.ResTestConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResTestConfigMapper {
    int countByExample(ResTestConfigExample example);

    int deleteByExample(ResTestConfigExample example);

    int insert(ResTestConfig record);

    int insertSelective(ResTestConfig record);

    List<ResTestConfig> selectByExample(ResTestConfigExample example);

    int updateByExampleSelective(@Param("record") ResTestConfig record, @Param("example") ResTestConfigExample example);

    int updateByExample(@Param("record") ResTestConfig record, @Param("example") ResTestConfigExample example);
}