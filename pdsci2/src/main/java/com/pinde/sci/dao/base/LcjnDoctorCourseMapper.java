package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnDoctorCourse;
import com.pinde.sci.model.mo.LcjnDoctorCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnDoctorCourseMapper {
    int countByExample(LcjnDoctorCourseExample example);

    int deleteByExample(LcjnDoctorCourseExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnDoctorCourse record);

    int insertSelective(LcjnDoctorCourse record);

    List<LcjnDoctorCourse> selectByExample(LcjnDoctorCourseExample example);

    LcjnDoctorCourse selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnDoctorCourse record, @Param("example") LcjnDoctorCourseExample example);

    int updateByExample(@Param("record") LcjnDoctorCourse record, @Param("example") LcjnDoctorCourseExample example);

    int updateByPrimaryKeySelective(LcjnDoctorCourse record);

    int updateByPrimaryKey(LcjnDoctorCourse record);
}