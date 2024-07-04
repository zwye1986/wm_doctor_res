package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamQuestionBook;
import com.pinde.sci.model.mo.ExamQuestionBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamQuestionBookMapper {
    int countByExample(ExamQuestionBookExample example);

    int deleteByExample(ExamQuestionBookExample example);

    int deleteByPrimaryKey(String questionBookFlow);

    int insert(ExamQuestionBook record);

    int insertSelective(ExamQuestionBook record);

    List<ExamQuestionBook> selectByExample(ExamQuestionBookExample example);

    ExamQuestionBook selectByPrimaryKey(String questionBookFlow);

    int updateByExampleSelective(@Param("record") ExamQuestionBook record, @Param("example") ExamQuestionBookExample example);

    int updateByExample(@Param("record") ExamQuestionBook record, @Param("example") ExamQuestionBookExample example);

    int updateByPrimaryKeySelective(ExamQuestionBook record);

    int updateByPrimaryKey(ExamQuestionBook record);
}