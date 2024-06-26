package com.pinde.sci.dao.osca;

import com.pinde.sci.model.mo.OscaSubjectStationFrom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaFromExtMapper {
    List<OscaSubjectStationFrom> selectSubjectStationFromByFlow(@Param(value = "flow") String flow);
}
