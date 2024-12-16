package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaExamDifferScore;
import com.pinde.core.model.OscaExamDifferScoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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