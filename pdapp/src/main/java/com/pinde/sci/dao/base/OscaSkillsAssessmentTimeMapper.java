package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSkillsAssessmentTime;
import com.pinde.sci.model.mo.OscaSkillsAssessmentTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OscaSkillsAssessmentTimeMapper {
    int countByExample(OscaSkillsAssessmentTimeExample example);

    int deleteByExample(OscaSkillsAssessmentTimeExample example);

    int deleteByPrimaryKey(String recrodFlow);

    int insert(OscaSkillsAssessmentTime record);

    int insertSelective(OscaSkillsAssessmentTime record);

    List<OscaSkillsAssessmentTime> selectByExample(OscaSkillsAssessmentTimeExample example);

    OscaSkillsAssessmentTime selectByPrimaryKey(String recrodFlow);

    int updateByExampleSelective(@Param("record") OscaSkillsAssessmentTime record, @Param("example") OscaSkillsAssessmentTimeExample example);

    int updateByExample(@Param("record") OscaSkillsAssessmentTime record, @Param("example") OscaSkillsAssessmentTimeExample example);

    int updateByPrimaryKeySelective(OscaSkillsAssessmentTime record);

    int updateByPrimaryKey(OscaSkillsAssessmentTime record);
}