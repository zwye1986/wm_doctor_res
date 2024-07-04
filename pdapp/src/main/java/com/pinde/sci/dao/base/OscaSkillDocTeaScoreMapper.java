package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSkillDocTeaScore;
import com.pinde.sci.model.mo.OscaSkillDocTeaScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSkillDocTeaScoreMapper {
    int countByExample(OscaSkillDocTeaScoreExample example);

    int deleteByExample(OscaSkillDocTeaScoreExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSkillDocTeaScore record);

    int insertSelective(OscaSkillDocTeaScore record);

    List<OscaSkillDocTeaScore> selectByExample(OscaSkillDocTeaScoreExample example);

    OscaSkillDocTeaScore selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSkillDocTeaScore record, @Param("example") OscaSkillDocTeaScoreExample example);

    int updateByExample(@Param("record") OscaSkillDocTeaScore record, @Param("example") OscaSkillDocTeaScoreExample example);

    int updateByPrimaryKeySelective(OscaSkillDocTeaScore record);

    int updateByPrimaryKey(OscaSkillDocTeaScore record);
}