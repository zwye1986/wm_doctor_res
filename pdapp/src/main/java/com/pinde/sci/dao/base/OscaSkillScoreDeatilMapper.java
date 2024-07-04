package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSkillScoreDeatil;
import com.pinde.sci.model.mo.OscaSkillScoreDeatilExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaSkillScoreDeatilMapper {
    int countByExample(OscaSkillScoreDeatilExample example);

    int deleteByExample(OscaSkillScoreDeatilExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSkillScoreDeatil record);

    int insertSelective(OscaSkillScoreDeatil record);

    List<OscaSkillScoreDeatil> selectByExample(OscaSkillScoreDeatilExample example);

    OscaSkillScoreDeatil selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSkillScoreDeatil record, @Param("example") OscaSkillScoreDeatilExample example);

    int updateByExample(@Param("record") OscaSkillScoreDeatil record, @Param("example") OscaSkillScoreDeatilExample example);

    int updateByPrimaryKeySelective(OscaSkillScoreDeatil record);

    int updateByPrimaryKey(OscaSkillScoreDeatil record);
}