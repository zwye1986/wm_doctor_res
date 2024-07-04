package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaExamDifferScore;
import com.pinde.sci.model.mo.OscaExamDifferScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaExamDifferScoreMapper {
    int countByExample(OscaExamDifferScoreExample example);

    int deleteByExample(OscaExamDifferScoreExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(OscaExamDifferScore record);

    int insertSelective(OscaExamDifferScore record);

    List<OscaExamDifferScore> selectByExample(OscaExamDifferScoreExample example);

    OscaExamDifferScore selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") OscaExamDifferScore record, @Param("example") OscaExamDifferScoreExample example);

    int updateByExample(@Param("record") OscaExamDifferScore record, @Param("example") OscaExamDifferScoreExample example);

    int updateByPrimaryKeySelective(OscaExamDifferScore record);

    int updateByPrimaryKey(OscaExamDifferScore record);
}