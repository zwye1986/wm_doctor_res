package com.pinde.core.common.sci.dao;

import com.pinde.core.model.StudyCourse;
import com.pinde.core.model.StudyCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyCourseMapper {
    int countByExample(StudyCourseExample example);

    int deleteByExample(StudyCourseExample example);

    int deleteByPrimaryKey(String courseFlow);

    int insert(StudyCourse record);

    int insertSelective(StudyCourse record);

    List<StudyCourse> selectByExample(StudyCourseExample example);

    StudyCourse selectByPrimaryKey(String courseFlow);

    int updateByExampleSelective(@Param("record") StudyCourse record, @Param("example") StudyCourseExample example);

    int updateByExample(@Param("record") StudyCourse record, @Param("example") StudyCourseExample example);

    int updateByPrimaryKeySelective(StudyCourse record);

    int updateByPrimaryKey(StudyCourse record);
}