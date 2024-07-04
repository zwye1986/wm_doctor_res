package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseMapper {
    int countByExample(EduCourseExample example);

    int deleteByExample(EduCourseExample example);

    int deleteByPrimaryKey(String courseFlow);

    int insert(EduCourse record);

    int insertSelective(EduCourse record);

    List<EduCourse> selectByExampleWithBLOBs(EduCourseExample example);

    List<EduCourse> selectByExample(EduCourseExample example);

    EduCourse selectByPrimaryKey(String courseFlow);

    int updateByExampleSelective(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByExampleWithBLOBs(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByExample(@Param("record") EduCourse record, @Param("example") EduCourseExample example);

    int updateByPrimaryKeySelective(EduCourse record);

    int updateByPrimaryKeyWithBLOBs(EduCourse record);

    int updateByPrimaryKey(EduCourse record);
}