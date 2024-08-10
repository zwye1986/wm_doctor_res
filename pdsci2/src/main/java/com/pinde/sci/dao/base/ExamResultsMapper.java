package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamResults;
import com.pinde.sci.model.mo.ExamResultsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamResultsMapper {
    int countByExample(ExamResultsExample example);

    int deleteByExample(ExamResultsExample example);

    int deleteByPrimaryKey(String resultsId);

    int insert(ExamResults record);

    int insertSelective(ExamResults record);

    List<ExamResults> selectByExample(ExamResultsExample example);

    ExamResults selectByPrimaryKey(String resultsId);

    int updateByExampleSelective(@Param("record") ExamResults record, @Param("example") ExamResultsExample example);

    int updateByExample(@Param("record") ExamResults record, @Param("example") ExamResultsExample example);

    int updateByPrimaryKeySelective(ExamResults record);

    int updateByPrimaryKey(ExamResults record);
}