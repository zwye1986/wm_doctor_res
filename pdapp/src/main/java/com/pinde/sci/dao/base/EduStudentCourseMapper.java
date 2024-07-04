package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.mo.EduStudentCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduStudentCourseMapper {
    int countByExample(EduStudentCourseExample example);

    int deleteByExample(EduStudentCourseExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduStudentCourse record);

    int insertSelective(EduStudentCourse record);

    List<EduStudentCourse> selectByExample(EduStudentCourseExample example);

    EduStudentCourse selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduStudentCourse record, @Param("example") EduStudentCourseExample example);

    int updateByExample(@Param("record") EduStudentCourse record, @Param("example") EduStudentCourseExample example);

    int updateByPrimaryKeySelective(EduStudentCourse record);

    int updateByPrimaryKey(EduStudentCourse record);
}