package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSubjectPartScore;
import com.pinde.sci.model.mo.OscaSubjectPartScoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaSubjectPartScoreMapper {
    int countByExample(OscaSubjectPartScoreExample example);

    int deleteByExample(OscaSubjectPartScoreExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSubjectPartScore record);

    int insertSelective(OscaSubjectPartScore record);

    List<OscaSubjectPartScore> selectByExample(OscaSubjectPartScoreExample example);

    OscaSubjectPartScore selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSubjectPartScore record, @Param("example") OscaSubjectPartScoreExample example);

    int updateByExample(@Param("record") OscaSubjectPartScore record, @Param("example") OscaSubjectPartScoreExample example);

    int updateByPrimaryKeySelective(OscaSubjectPartScore record);

    int updateByPrimaryKey(OscaSubjectPartScore record);
}