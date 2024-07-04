package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamBookSubject;
import com.pinde.sci.model.mo.ExamBookSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamBookSubjectMapper {
    int countByExample(ExamBookSubjectExample example);

    int deleteByExample(ExamBookSubjectExample example);

    int deleteByPrimaryKey(String bookSubjectFlow);

    int insert(ExamBookSubject record);

    int insertSelective(ExamBookSubject record);

    List<ExamBookSubject> selectByExample(ExamBookSubjectExample example);

    ExamBookSubject selectByPrimaryKey(String bookSubjectFlow);

    int updateByExampleSelective(@Param("record") ExamBookSubject record, @Param("example") ExamBookSubjectExample example);

    int updateByExample(@Param("record") ExamBookSubject record, @Param("example") ExamBookSubjectExample example);

    int updateByPrimaryKeySelective(ExamBookSubject record);

    int updateByPrimaryKey(ExamBookSubject record);
}