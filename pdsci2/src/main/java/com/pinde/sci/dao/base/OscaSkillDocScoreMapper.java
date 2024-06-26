package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSkillDocScore;
import com.pinde.sci.model.mo.OscaSkillDocScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSkillDocScoreMapper {
    int countByExample(OscaSkillDocScoreExample example);

    int deleteByExample(OscaSkillDocScoreExample example);

    int deleteByPrimaryKey(String scoreFlow);

    int insert(OscaSkillDocScore record);

    int insertSelective(OscaSkillDocScore record);

    List<OscaSkillDocScore> selectByExampleWithBLOBs(OscaSkillDocScoreExample example);

    List<OscaSkillDocScore> selectByExample(OscaSkillDocScoreExample example);

    OscaSkillDocScore selectByPrimaryKey(String scoreFlow);

    int updateByExampleSelective(@Param("record") OscaSkillDocScore record, @Param("example") OscaSkillDocScoreExample example);

    int updateByExampleWithBLOBs(@Param("record") OscaSkillDocScore record, @Param("example") OscaSkillDocScoreExample example);

    int updateByExample(@Param("record") OscaSkillDocScore record, @Param("example") OscaSkillDocScoreExample example);

    int updateByPrimaryKeySelective(OscaSkillDocScore record);

    int updateByPrimaryKeyWithBLOBs(OscaSkillDocScore record);

    int updateByPrimaryKey(OscaSkillDocScore record);
}