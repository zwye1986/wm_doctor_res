package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaSubjectStationFrom;
import com.pinde.core.model.OscaSubjectStationFromExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSubjectStationFromMapper {
    int countByExample(OscaSubjectStationFromExample example);

    int deleteByExample(OscaSubjectStationFromExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaSubjectStationFrom record);

    int insertSelective(OscaSubjectStationFrom record);

    List<OscaSubjectStationFrom> selectByExample(OscaSubjectStationFromExample example);

    OscaSubjectStationFrom selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaSubjectStationFrom record, @Param("example") OscaSubjectStationFromExample example);

    int updateByExample(@Param("record") OscaSubjectStationFrom record, @Param("example") OscaSubjectStationFromExample example);

    int updateByPrimaryKeySelective(OscaSubjectStationFrom record);

    int updateByPrimaryKey(OscaSubjectStationFrom record);
}