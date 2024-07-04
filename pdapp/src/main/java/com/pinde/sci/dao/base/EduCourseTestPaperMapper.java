package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseTestPaper;
import com.pinde.sci.model.mo.EduCourseTestPaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseTestPaperMapper {
    int countByExample(EduCourseTestPaperExample example);

    int deleteByExample(EduCourseTestPaperExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseTestPaper record);

    int insertSelective(EduCourseTestPaper record);

    List<EduCourseTestPaper> selectByExample(EduCourseTestPaperExample example);

    EduCourseTestPaper selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseTestPaper record, @Param("example") EduCourseTestPaperExample example);

    int updateByExample(@Param("record") EduCourseTestPaper record, @Param("example") EduCourseTestPaperExample example);

    int updateByPrimaryKeySelective(EduCourseTestPaper record);

    int updateByPrimaryKey(EduCourseTestPaper record);
}