package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResGraduationAssessment;
import com.pinde.core.model.ResGraduationAssessmentExample;
import com.pinde.core.model.ResGraduationAssessmentWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResGraduationAssessmentMapper {
    int countByExample(ResGraduationAssessmentExample example);

    int deleteByExample(ResGraduationAssessmentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResGraduationAssessmentWithBLOBs record);

    int insertSelective(ResGraduationAssessmentWithBLOBs record);

    List<ResGraduationAssessmentWithBLOBs> selectByExampleWithBLOBs(ResGraduationAssessmentExample example);

    List<ResGraduationAssessment> selectByExample(ResGraduationAssessmentExample example);

    ResGraduationAssessmentWithBLOBs selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResGraduationAssessmentWithBLOBs record, @Param("example") ResGraduationAssessmentExample example);

    int updateByExampleWithBLOBs(@Param("record") ResGraduationAssessmentWithBLOBs record, @Param("example") ResGraduationAssessmentExample example);

    int updateByExample(@Param("record") ResGraduationAssessment record, @Param("example") ResGraduationAssessmentExample example);

    int updateByPrimaryKeySelective(ResGraduationAssessmentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ResGraduationAssessmentWithBLOBs record);

    int updateByPrimaryKey(ResGraduationAssessment record);
}