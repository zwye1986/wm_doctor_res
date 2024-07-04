package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSubjectStationScore;
import com.pinde.sci.model.mo.OscaSubjectStationScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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