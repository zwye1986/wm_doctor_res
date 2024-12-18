package com.pinde.sci.dao.base;

import com.pinde.core.model.GraduationExamResults;
import com.pinde.core.model.GraduationExamResultsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GraduationExamResultsMapper {
    int countByExample(GraduationExamResultsExample example);

    int deleteByExample(GraduationExamResultsExample example);

    int deleteByPrimaryKey(String resultsId);

    int insert(GraduationExamResults record);

    int insertSelective(GraduationExamResults record);

    List<GraduationExamResults> selectByExample(GraduationExamResultsExample example);

    GraduationExamResults selectByPrimaryKey(String resultsId);

    int updateByExampleSelective(@Param("record") GraduationExamResults record, @Param("example") GraduationExamResultsExample example);

    int updateByExample(@Param("record") GraduationExamResults record, @Param("example") GraduationExamResultsExample example);

    int updateByPrimaryKeySelective(GraduationExamResults record);

    int updateByPrimaryKey(GraduationExamResults record);
}