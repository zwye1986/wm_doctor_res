package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.OscaDoctorAssessment;
import com.pinde.sci.model.mo.OscaSkillsAssessment;
import com.pinde.sci.model.mo.OscaSubjectStation;
import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OscaSubjectMainExtMapper {

    List<OscaSubjectStation> searchSubjectsForDoctor(@Param("clinicalFlow") String clinicalFlow);

    List<Map<String,String>> getSubjectParts(@Param("subjectFlow") String subjectFlow);

    void delSubjectStationScore(@Param("subjectFlow") String subjectFlow);

    void delSubjectPartScore(@Param("subjectFlow") String subjectFlow);
}
