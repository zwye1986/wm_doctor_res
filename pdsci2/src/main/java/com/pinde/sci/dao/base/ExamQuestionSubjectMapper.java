package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamQuestionSubject;
import com.pinde.sci.model.mo.ExamQuestionSubjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamQuestionSubjectMapper {
    int countByExample(ExamQuestionSubjectExample example);

    int deleteByExample(ExamQuestionSubjectExample example);

    int deleteByPrimaryKey(String questionSubjectFlow);

    int insert(ExamQuestionSubject record);

    int insertSelective(ExamQuestionSubject record);

    List<ExamQuestionSubject> selectByExample(ExamQuestionSubjectExample example);

    ExamQuestionSubject selectByPrimaryKey(String questionSubjectFlow);

    int updateByExampleSelective(@Param("record") ExamQuestionSubject record, @Param("example") ExamQuestionSubjectExample example);

    int updateByExample(@Param("record") ExamQuestionSubject record, @Param("example") ExamQuestionSubjectExample example);

    int updateByPrimaryKeySelective(ExamQuestionSubject record);

    int updateByPrimaryKey(ExamQuestionSubject record);
}