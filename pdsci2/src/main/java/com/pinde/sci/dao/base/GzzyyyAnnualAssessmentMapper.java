package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GzzyyyAnnualAssessment;
import com.pinde.sci.model.mo.GzzyyyAnnualAssessmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GzzyyyAnnualAssessmentMapper {
    int countByExample(GzzyyyAnnualAssessmentExample example);

    int deleteByExample(GzzyyyAnnualAssessmentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(GzzyyyAnnualAssessment record);

    int insertSelective(GzzyyyAnnualAssessment record);

    List<GzzyyyAnnualAssessment> selectByExample(GzzyyyAnnualAssessmentExample example);

    GzzyyyAnnualAssessment selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") GzzyyyAnnualAssessment record, @Param("example") GzzyyyAnnualAssessmentExample example);

    int updateByExample(@Param("record") GzzyyyAnnualAssessment record, @Param("example") GzzyyyAnnualAssessmentExample example);

    int updateByPrimaryKeySelective(GzzyyyAnnualAssessment record);

    int updateByPrimaryKey(GzzyyyAnnualAssessment record);
}