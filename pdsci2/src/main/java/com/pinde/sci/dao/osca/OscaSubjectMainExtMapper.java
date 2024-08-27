package com.pinde.sci.dao.osca;

import com.pinde.sci.model.mo.OscaSubjectStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OscaSubjectMainExtMapper {

    List<OscaSubjectStation> searchSubjectsForDoctor(@Param("clinicalFlow") String clinicalFlow);

    List<Map<String,String>> getSubjectParts(@Param("subjectFlow") String subjectFlow);

    void delSubjectStationScore(@Param("subjectFlow") String subjectFlow);

    void delSubjectPartScore(@Param("subjectFlow") String subjectFlow);
}
