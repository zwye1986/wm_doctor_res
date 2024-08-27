package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnCourseEvaluate;
import com.pinde.sci.model.mo.LcjnCourseEvaluateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnCourseEvaluateMapper {
    int countByExample(LcjnCourseEvaluateExample example);

    int deleteByExample(LcjnCourseEvaluateExample example);

    int deleteByPrimaryKey(String evaluateFlow);

    int insert(LcjnCourseEvaluate record);

    int insertSelective(LcjnCourseEvaluate record);

    List<LcjnCourseEvaluate> selectByExample(LcjnCourseEvaluateExample example);

    LcjnCourseEvaluate selectByPrimaryKey(String evaluateFlow);

    int updateByExampleSelective(@Param("record") LcjnCourseEvaluate record, @Param("example") LcjnCourseEvaluateExample example);

    int updateByExample(@Param("record") LcjnCourseEvaluate record, @Param("example") LcjnCourseEvaluateExample example);

    int updateByPrimaryKeySelective(LcjnCourseEvaluate record);

    int updateByPrimaryKey(LcjnCourseEvaluate record);
}