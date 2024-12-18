package com.pinde.sci.dao.base;

import com.pinde.core.model.ResRecSkillRegistry;
import com.pinde.core.model.ResRecSkillRegistryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRecSkillRegistryMapper {
    int countByExample(ResRecSkillRegistryExample example);

    int deleteByExample(ResRecSkillRegistryExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResRecSkillRegistry record);

    int insertSelective(ResRecSkillRegistry record);

    List<ResRecSkillRegistry> selectByExampleWithBLOBs(ResRecSkillRegistryExample example);

    List<ResRecSkillRegistry> selectByExample(ResRecSkillRegistryExample example);

    ResRecSkillRegistry selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") ResRecSkillRegistry record, @Param("example") ResRecSkillRegistryExample example);

    int updateByExampleWithBLOBs(@Param("record") ResRecSkillRegistry record, @Param("example") ResRecSkillRegistryExample example);

    int updateByExample(@Param("record") ResRecSkillRegistry record, @Param("example") ResRecSkillRegistryExample example);

    int updateByPrimaryKeySelective(ResRecSkillRegistry record);

    int updateByPrimaryKeyWithBLOBs(ResRecSkillRegistry record);

    int updateByPrimaryKey(ResRecSkillRegistry record);
}