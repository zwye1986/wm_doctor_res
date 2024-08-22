package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResAnnualAssessment;
import com.pinde.sci.model.mo.ResAnnualAssessmentExample;
import com.pinde.sci.model.mo.ResAnnualAssessmentWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResAnnualAssessmentMapper {
    int countByExample(ResAnnualAssessmentExample example);

    int deleteByExample(ResAnnualAssessmentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResAnnualAssessmentWithBLOBs record);

    int insertSelective(ResAnnualAssessmentWithBLOBs record);

    List<ResAnnualAssessmentWithBLOBs> selectByExampleWithBLOBs(ResAnnualAssessmentExample example);

    List<ResAnnualAssessment> selectByExample(ResAnnualAssessmentExample example);

    ResAnnualAssessmentWithBLOBs selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResAnnualAssessmentWithBLOBs record, @Param("example") ResAnnualAssessmentExample example);

    int updateByExampleWithBLOBs(@Param("record") ResAnnualAssessmentWithBLOBs record, @Param("example") ResAnnualAssessmentExample example);

    int updateByExample(@Param("record") ResAnnualAssessment record, @Param("example") ResAnnualAssessmentExample example);

    int updateByPrimaryKeySelective(ResAnnualAssessmentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ResAnnualAssessmentWithBLOBs record);

    int updateByPrimaryKey(ResAnnualAssessment record);
}