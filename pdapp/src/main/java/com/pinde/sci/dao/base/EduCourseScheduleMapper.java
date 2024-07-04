package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseSchedule;
import com.pinde.sci.model.mo.EduCourseScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseScheduleMapper {
    int countByExample(EduCourseScheduleExample example);

    int deleteByExample(EduCourseScheduleExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseSchedule record);

    int insertSelective(EduCourseSchedule record);

    List<EduCourseSchedule> selectByExample(EduCourseScheduleExample example);

    EduCourseSchedule selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseSchedule record, @Param("example") EduCourseScheduleExample example);

    int updateByExample(@Param("record") EduCourseSchedule record, @Param("example") EduCourseScheduleExample example);

    int updateByPrimaryKeySelective(EduCourseSchedule record);

    int updateByPrimaryKey(EduCourseSchedule record);
}