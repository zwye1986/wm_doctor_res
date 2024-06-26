package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduScheduleStudent;
import com.pinde.sci.model.mo.EduScheduleStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduScheduleStudentMapper {
    int countByExample(EduScheduleStudentExample example);

    int deleteByExample(EduScheduleStudentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduScheduleStudent record);

    int insertSelective(EduScheduleStudent record);

    List<EduScheduleStudent> selectByExample(EduScheduleStudentExample example);

    EduScheduleStudent selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduScheduleStudent record, @Param("example") EduScheduleStudentExample example);

    int updateByExample(@Param("record") EduScheduleStudent record, @Param("example") EduScheduleStudentExample example);

    int updateByPrimaryKeySelective(EduScheduleStudent record);

    int updateByPrimaryKey(EduScheduleStudent record);
}