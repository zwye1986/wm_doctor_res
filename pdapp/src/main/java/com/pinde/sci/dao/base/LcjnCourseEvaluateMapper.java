package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnCourseEvaluate;
import com.pinde.core.model.LcjnCourseEvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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