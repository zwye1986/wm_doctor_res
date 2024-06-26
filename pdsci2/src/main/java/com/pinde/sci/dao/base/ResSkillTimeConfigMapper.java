package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSkillTimeConfig;
import com.pinde.sci.model.mo.ResSkillTimeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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