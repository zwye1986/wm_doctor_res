package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResAnnualAssessmentRecord;
import com.pinde.core.model.ResAnnualAssessmentRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResAnnualAssessmentRecordMapper {
    int countByExample(ResAnnualAssessmentRecordExample example);

    int deleteByExample(ResAnnualAssessmentRecordExample example);

    int deleteByPrimaryKey(String annualAssessmentFlow);

    int insert(ResAnnualAssessmentRecord record);

    int insertSelective(ResAnnualAssessmentRecord record);

    List<ResAnnualAssessmentRecord> selectByExample(ResAnnualAssessmentRecordExample example);

    ResAnnualAssessmentRecord selectByPrimaryKey(String annualAssessmentFlow);

    int updateByExampleSelective(@Param("record") ResAnnualAssessmentRecord record, @Param("example") ResAnnualAssessmentRecordExample example);

    int updateByExample(@Param("record") ResAnnualAssessmentRecord record, @Param("example") ResAnnualAssessmentRecordExample example);

    int updateByPrimaryKeySelective(ResAnnualAssessmentRecord record);

    int updateByPrimaryKey(ResAnnualAssessmentRecord record);
}