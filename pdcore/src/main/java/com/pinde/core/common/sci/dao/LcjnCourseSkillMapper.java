package com.pinde.core.common.sci.dao;

import com.pinde.core.model.LcjnCourseSkill;
import com.pinde.core.model.LcjnCourseSkillExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnCourseSkillMapper {
    int countByExample(LcjnCourseSkillExample example);

    int deleteByExample(LcjnCourseSkillExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnCourseSkill record);

    int insertSelective(LcjnCourseSkill record);

    List<LcjnCourseSkill> selectByExample(LcjnCourseSkillExample example);

    LcjnCourseSkill selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnCourseSkill record, @Param("example") LcjnCourseSkillExample example);

    int updateByExample(@Param("record") LcjnCourseSkill record, @Param("example") LcjnCourseSkillExample example);

    int updateByPrimaryKeySelective(LcjnCourseSkill record);

    int updateByPrimaryKey(LcjnCourseSkill record);
}