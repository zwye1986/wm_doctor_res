package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnCourseSkill;
import com.pinde.sci.model.mo.LcjnCourseSkillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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