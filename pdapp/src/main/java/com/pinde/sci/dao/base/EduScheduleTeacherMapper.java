package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduScheduleTeacher;
import com.pinde.sci.model.mo.EduScheduleTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduScheduleTeacherMapper {
    int countByExample(EduScheduleTeacherExample example);

    int deleteByExample(EduScheduleTeacherExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduScheduleTeacher record);

    int insertSelective(EduScheduleTeacher record);

    List<EduScheduleTeacher> selectByExample(EduScheduleTeacherExample example);

    EduScheduleTeacher selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduScheduleTeacher record, @Param("example") EduScheduleTeacherExample example);

    int updateByExample(@Param("record") EduScheduleTeacher record, @Param("example") EduScheduleTeacherExample example);

    int updateByPrimaryKeySelective(EduScheduleTeacher record);

    int updateByPrimaryKey(EduScheduleTeacher record);
}