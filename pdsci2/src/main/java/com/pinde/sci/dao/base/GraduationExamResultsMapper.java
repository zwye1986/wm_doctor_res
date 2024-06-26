package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GraduationExamResults;
import com.pinde.sci.model.mo.GraduationExamResultsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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