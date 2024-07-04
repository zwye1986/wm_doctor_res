package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NywjCourseQuestion;
import com.pinde.sci.model.mo.NywjCourseQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NywjCourseQuestionMapper {
    int countByExample(NywjCourseQuestionExample example);

    int deleteByExample(NywjCourseQuestionExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NywjCourseQuestion record);

    int insertSelective(NywjCourseQuestion record);

    List<NywjCourseQuestion> selectByExample(NywjCourseQuestionExample example);

    NywjCourseQuestion selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NywjCourseQuestion record, @Param("example") NywjCourseQuestionExample example);

    int updateByExample(@Param("record") NywjCourseQuestion record, @Param("example") NywjCourseQuestionExample example);

    int updateByPrimaryKeySelective(NywjCourseQuestion record);

    int updateByPrimaryKey(NywjCourseQuestion record);
}