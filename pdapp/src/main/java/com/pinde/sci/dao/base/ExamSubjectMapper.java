package com.pinde.sci.dao.base;

import com.pinde.core.model.ExamSubject;
import com.pinde.core.model.ExamSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamSubjectMapper {
    int countByExample(ExamSubjectExample example);

    int deleteByExample(ExamSubjectExample example);

    int deleteByPrimaryKey(String subjectFlow);

    int insert(ExamSubject record);

    int insertSelective(ExamSubject record);

    List<ExamSubject> selectByExample(ExamSubjectExample example);

    ExamSubject selectByPrimaryKey(String subjectFlow);

    int updateByExampleSelective(@Param("record") ExamSubject record, @Param("example") ExamSubjectExample example);

    int updateByExample(@Param("record") ExamSubject record, @Param("example") ExamSubjectExample example);

    int updateByPrimaryKeySelective(ExamSubject record);

    int updateByPrimaryKey(ExamSubject record);
}