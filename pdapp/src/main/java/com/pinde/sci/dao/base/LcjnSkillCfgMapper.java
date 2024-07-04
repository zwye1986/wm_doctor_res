package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnSkillCfg;
import com.pinde.sci.model.mo.LcjnSkillCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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