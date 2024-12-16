package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaSubjectStationScore;
import com.pinde.core.model.OscaSubjectStationScoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaSubjectStationScoreMapper {
    int countByExample(OscaSubjectStationScoreExample example);

    int deleteByExample(OscaSubjectStationScoreExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSubjectStationScore record);

    int insertSelective(OscaSubjectStationScore record);

    List<OscaSubjectStationScore> selectByExample(OscaSubjectStationScoreExample example);

    OscaSubjectStationScore selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSubjectStationScore record, @Param("example") OscaSubjectStationScoreExample example);

    int updateByExample(@Param("record") OscaSubjectStationScore record, @Param("example") OscaSubjectStationScoreExample example);

    int updateByPrimaryKeySelective(OscaSubjectStationScore record);

    int updateByPrimaryKey(OscaSubjectStationScore record);
}