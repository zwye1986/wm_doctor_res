package com.pinde.sci.dao.base;

import com.pinde.core.model.ResSkillConfig;
import com.pinde.core.model.ResSkillConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSkillConfigMapper {
    int countByExample(ResSkillConfigExample example);

    int deleteByExample(ResSkillConfigExample example);

    int deleteByPrimaryKey(String skillFlow);

    int insert(ResSkillConfig record);

    int insertSelective(ResSkillConfig record);

    List<ResSkillConfig> selectByExample(ResSkillConfigExample example);

    ResSkillConfig selectByPrimaryKey(String skillFlow);

    int updateByExampleSelective(@Param("record") ResSkillConfig record, @Param("example") ResSkillConfigExample example);

    int updateByExample(@Param("record") ResSkillConfig record, @Param("example") ResSkillConfigExample example);

    int updateByPrimaryKeySelective(ResSkillConfig record);

    int updateByPrimaryKey(ResSkillConfig record);
}