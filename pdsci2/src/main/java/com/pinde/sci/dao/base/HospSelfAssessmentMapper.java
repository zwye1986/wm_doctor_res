package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.HospSelfAssessment;
import com.pinde.sci.model.mo.HospSelfAssessmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HospSelfAssessmentMapper {
    int countByExample(HospSelfAssessmentExample example);

    int deleteByExample(HospSelfAssessmentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(HospSelfAssessment record);

    int insertSelective(HospSelfAssessment record);

    List<HospSelfAssessment> selectByExample(HospSelfAssessmentExample example);

    HospSelfAssessment selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") HospSelfAssessment record, @Param("example") HospSelfAssessmentExample example);

    int updateByExample(@Param("record") HospSelfAssessment record, @Param("example") HospSelfAssessmentExample example);

    int updateByPrimaryKeySelective(HospSelfAssessment record);

    int updateByPrimaryKey(HospSelfAssessment record);
}