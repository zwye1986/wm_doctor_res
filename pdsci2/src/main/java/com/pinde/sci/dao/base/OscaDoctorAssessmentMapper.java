package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaDoctorAssessment;
import com.pinde.core.model.OscaDoctorAssessmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaDoctorAssessmentMapper {
    int countByExample(OscaDoctorAssessmentExample example);

    int deleteByExample(OscaDoctorAssessmentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaDoctorAssessment record);

    int insertSelective(OscaDoctorAssessment record);

    List<OscaDoctorAssessment> selectByExample(OscaDoctorAssessmentExample example);

    OscaDoctorAssessment selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaDoctorAssessment record, @Param("example") OscaDoctorAssessmentExample example);

    int updateByExample(@Param("record") OscaDoctorAssessment record, @Param("example") OscaDoctorAssessmentExample example);

    int updateByPrimaryKeySelective(OscaDoctorAssessment record);

    int updateByPrimaryKey(OscaDoctorAssessment record);
}