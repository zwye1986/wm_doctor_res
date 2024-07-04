package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseTeacher;
import com.pinde.sci.model.mo.EduCourseTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseTeacherMapper {
    int countByExample(EduCourseTeacherExample example);

    int deleteByExample(EduCourseTeacherExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseTeacher record);

    int insertSelective(EduCourseTeacher record);

    List<EduCourseTeacher> selectByExample(EduCourseTeacherExample example);

    EduCourseTeacher selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseTeacher record, @Param("example") EduCourseTeacherExample example);

    int updateByExample(@Param("record") EduCourseTeacher record, @Param("example") EduCourseTeacherExample example);

    int updateByPrimaryKeySelective(EduCourseTeacher record);

    int updateByPrimaryKey(EduCourseTeacher record);
}