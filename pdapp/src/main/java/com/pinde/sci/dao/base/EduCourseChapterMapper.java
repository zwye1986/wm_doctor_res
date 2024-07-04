package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseChapter;
import com.pinde.sci.model.mo.EduCourseChapterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseChapterMapper {
    int countByExample(EduCourseChapterExample example);

    int deleteByExample(EduCourseChapterExample example);

    int deleteByPrimaryKey(String chapterFlow);

    int insert(EduCourseChapter record);

    int insertSelective(EduCourseChapter record);

    List<EduCourseChapter> selectByExample(EduCourseChapterExample example);

    EduCourseChapter selectByPrimaryKey(String chapterFlow);

    int updateByExampleSelective(@Param("record") EduCourseChapter record, @Param("example") EduCourseChapterExample example);

    int updateByExample(@Param("record") EduCourseChapter record, @Param("example") EduCourseChapterExample example);

    int updateByPrimaryKeySelective(EduCourseChapter record);

    int updateByPrimaryKey(EduCourseChapter record);
}