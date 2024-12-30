package com.pinde.core.common.sci.dao;

import com.pinde.core.model.LcjnSkillCfg;
import com.pinde.core.model.LcjnSkillCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnSkillCfgMapper {
    int countByExample(LcjnSkillCfgExample example);

    int deleteByExample(LcjnSkillCfgExample example);

    int deleteByPrimaryKey(String skillFlow);

    int insert(LcjnSkillCfg record);

    int insertSelective(LcjnSkillCfg record);

    List<LcjnSkillCfg> selectByExample(LcjnSkillCfgExample example);

    LcjnSkillCfg selectByPrimaryKey(String skillFlow);

    int updateByExampleSelective(@Param("record") LcjnSkillCfg record, @Param("example") LcjnSkillCfgExample example);

    int updateByExample(@Param("record") LcjnSkillCfg record, @Param("example") LcjnSkillCfgExample example);

    int updateByPrimaryKeySelective(LcjnSkillCfg record);

    int updateByPrimaryKey(LcjnSkillCfg record);
}