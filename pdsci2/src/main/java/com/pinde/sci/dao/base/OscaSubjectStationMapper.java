package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSubjectStation;
import com.pinde.sci.model.mo.OscaSubjectStationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaSubjectStationMapper {
    int countByExample(OscaSubjectStationExample example);

    int deleteByExample(OscaSubjectStationExample example);

    int deleteByPrimaryKey(String stationFlow);

    int insert(OscaSubjectStation record);

    int insertSelective(OscaSubjectStation record);

    List<OscaSubjectStation> selectByExample(OscaSubjectStationExample example);

    OscaSubjectStation selectByPrimaryKey(String stationFlow);

    int updateByExampleSelective(@Param("record") OscaSubjectStation record, @Param("example") OscaSubjectStationExample example);

    int updateByExample(@Param("record") OscaSubjectStation record, @Param("example") OscaSubjectStationExample example);

    int updateByPrimaryKeySelective(OscaSubjectStation record);

    int updateByPrimaryKey(OscaSubjectStation record);
}