package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseFile;
import com.pinde.sci.model.mo.EduCourseFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseFileMapper {
    int countByExample(EduCourseFileExample example);

    int deleteByExample(EduCourseFileExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseFile record);

    int insertSelective(EduCourseFile record);

    List<EduCourseFile> selectByExample(EduCourseFileExample example);

    EduCourseFile selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseFile record, @Param("example") EduCourseFileExample example);

    int updateByExample(@Param("record") EduCourseFile record, @Param("example") EduCourseFileExample example);

    int updateByPrimaryKeySelective(EduCourseFile record);

    int updateByPrimaryKey(EduCourseFile record);
}