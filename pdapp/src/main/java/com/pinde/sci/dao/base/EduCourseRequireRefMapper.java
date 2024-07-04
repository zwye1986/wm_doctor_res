package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseRequireRef;
import com.pinde.sci.model.mo.EduCourseRequireRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseRequireRefMapper {
    int countByExample(EduCourseRequireRefExample example);

    int deleteByExample(EduCourseRequireRefExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseRequireRef record);

    int insertSelective(EduCourseRequireRef record);

    List<EduCourseRequireRef> selectByExample(EduCourseRequireRefExample example);

    EduCourseRequireRef selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseRequireRef record, @Param("example") EduCourseRequireRefExample example);

    int updateByExample(@Param("record") EduCourseRequireRef record, @Param("example") EduCourseRequireRefExample example);

    int updateByPrimaryKeySelective(EduCourseRequireRef record);

    int updateByPrimaryKey(EduCourseRequireRef record);
}