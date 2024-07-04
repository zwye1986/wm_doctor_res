package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnCourseTea;
import com.pinde.sci.model.mo.LcjnCourseTeaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LcjnCourseTeaMapper {
    int countByExample(LcjnCourseTeaExample example);

    int deleteByExample(LcjnCourseTeaExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnCourseTea record);

    int insertSelective(LcjnCourseTea record);

    List<LcjnCourseTea> selectByExample(LcjnCourseTeaExample example);

    LcjnCourseTea selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnCourseTea record, @Param("example") LcjnCourseTeaExample example);

    int updateByExample(@Param("record") LcjnCourseTea record, @Param("example") LcjnCourseTeaExample example);

    int updateByPrimaryKeySelective(LcjnCourseTea record);

    int updateByPrimaryKey(LcjnCourseTea record);
}