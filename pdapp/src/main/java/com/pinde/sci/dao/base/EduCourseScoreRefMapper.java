package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseScoreRef;
import com.pinde.sci.model.mo.EduCourseScoreRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseScoreRefMapper {
    int countByExample(EduCourseScoreRefExample example);

    int deleteByExample(EduCourseScoreRefExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseScoreRef record);

    int insertSelective(EduCourseScoreRef record);

    List<EduCourseScoreRef> selectByExample(EduCourseScoreRefExample example);

    EduCourseScoreRef selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseScoreRef record, @Param("example") EduCourseScoreRefExample example);

    int updateByExample(@Param("record") EduCourseScoreRef record, @Param("example") EduCourseScoreRefExample example);

    int updateByPrimaryKeySelective(EduCourseScoreRef record);

    int updateByPrimaryKey(EduCourseScoreRef record);
}