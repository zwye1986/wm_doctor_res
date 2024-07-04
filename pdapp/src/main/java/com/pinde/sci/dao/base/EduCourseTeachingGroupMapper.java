package com.pinde.sci.dao.base;

import java.util.List;

import com.pinde.sci.model.mo.EduCourseTeachingGroup;
import com.pinde.sci.model.mo.EduCourseTeachingGroupExample;
import org.apache.ibatis.annotations.Param;

public interface EduCourseTeachingGroupMapper {
    int countByExample(EduCourseTeachingGroupExample example);

    int deleteByExample(EduCourseTeachingGroupExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseTeachingGroup record);

    int insertSelective(EduCourseTeachingGroup record);

    List<EduCourseTeachingGroup> selectByExample(EduCourseTeachingGroupExample example);

    EduCourseTeachingGroup selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseTeachingGroup record, @Param("example") EduCourseTeachingGroupExample example);

    int updateByExample(@Param("record") EduCourseTeachingGroup record, @Param("example") EduCourseTeachingGroupExample example);

    int updateByPrimaryKeySelective(EduCourseTeachingGroup record);

    int updateByPrimaryKey(EduCourseTeachingGroup record);
}