package com.pinde.sci.dao.base;

import com.pinde.core.model.OscaSkillsAssessment;
import com.pinde.core.model.OscaSkillsAssessmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSkillsAssessmentMapper {
    int countByExample(OscaSkillsAssessmentExample example);

    int deleteByExample(OscaSkillsAssessmentExample example);

    int deleteByPrimaryKey(String clinicalFlow);

    int insert(OscaSkillsAssessment record);

    int insertSelective(OscaSkillsAssessment record);

    List<OscaSkillsAssessment> selectByExample(OscaSkillsAssessmentExample example);

    OscaSkillsAssessment selectByPrimaryKey(String clinicalFlow);

    int updateByExampleSelective(@Param("record") OscaSkillsAssessment record, @Param("example") OscaSkillsAssessmentExample example);

    int updateByExample(@Param("record") OscaSkillsAssessment record, @Param("example") OscaSkillsAssessmentExample example);

    int updateByPrimaryKeySelective(OscaSkillsAssessment record);

    int updateByPrimaryKey(OscaSkillsAssessment record);
}