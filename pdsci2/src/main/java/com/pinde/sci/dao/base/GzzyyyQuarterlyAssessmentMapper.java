package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GzzyyyQuarterlyAssessment;
import com.pinde.sci.model.mo.GzzyyyQuarterlyAssessmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GzzyyyQuarterlyAssessmentMapper {
    int countByExample(GzzyyyQuarterlyAssessmentExample example);

    int deleteByExample(GzzyyyQuarterlyAssessmentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(GzzyyyQuarterlyAssessment record);

    int insertSelective(GzzyyyQuarterlyAssessment record);

    List<GzzyyyQuarterlyAssessment> selectByExample(GzzyyyQuarterlyAssessmentExample example);

    GzzyyyQuarterlyAssessment selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") GzzyyyQuarterlyAssessment record, @Param("example") GzzyyyQuarterlyAssessmentExample example);

    int updateByExample(@Param("record") GzzyyyQuarterlyAssessment record, @Param("example") GzzyyyQuarterlyAssessmentExample example);

    int updateByPrimaryKeySelective(GzzyyyQuarterlyAssessment record);

    int updateByPrimaryKey(GzzyyyQuarterlyAssessment record);
}