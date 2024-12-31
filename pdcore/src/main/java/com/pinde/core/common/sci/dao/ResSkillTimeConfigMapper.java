package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResSkillTimeConfig;
import com.pinde.core.model.ResSkillTimeConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSkillTimeConfigMapper {
    int countByExample(ResSkillTimeConfigExample example);

    int deleteByExample(ResSkillTimeConfigExample example);

    int deleteByPrimaryKey(String skillTimeFlow);

    int insert(ResSkillTimeConfig record);

    int insertSelective(ResSkillTimeConfig record);

    List<ResSkillTimeConfig> selectByExample(ResSkillTimeConfigExample example);

    ResSkillTimeConfig selectByPrimaryKey(String skillTimeFlow);

    int updateByExampleSelective(@Param("record") ResSkillTimeConfig record, @Param("example") ResSkillTimeConfigExample example);

    int updateByExample(@Param("record") ResSkillTimeConfig record, @Param("example") ResSkillTimeConfigExample example);

    int updateByPrimaryKeySelective(ResSkillTimeConfig record);

    int updateByPrimaryKey(ResSkillTimeConfig record);
}