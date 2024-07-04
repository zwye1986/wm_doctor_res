package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.StudyCourse;
import com.pinde.sci.model.mo.StudyCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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