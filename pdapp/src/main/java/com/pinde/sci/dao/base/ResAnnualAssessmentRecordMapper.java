package com.pinde.sci.dao.base;

import com.pinde.core.model.ResAnnualAssessmentRecord;
import com.pinde.core.model.ResAnnualAssessmentRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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