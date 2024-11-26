package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnCourseEvaluateDetail;
import com.pinde.core.model.LcjnCourseEvaluateDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LcjnCourseEvaluateDetailMapper {
    int countByExample(LcjnCourseEvaluateDetailExample example);

    int deleteByExample(LcjnCourseEvaluateDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnCourseEvaluateDetail record);

    int insertSelective(LcjnCourseEvaluateDetail record);

    List<LcjnCourseEvaluateDetail> selectByExample(LcjnCourseEvaluateDetailExample example);

    LcjnCourseEvaluateDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnCourseEvaluateDetail record, @Param("example") LcjnCourseEvaluateDetailExample example);

    int updateByExample(@Param("record") LcjnCourseEvaluateDetail record, @Param("example") LcjnCourseEvaluateDetailExample example);

    int updateByPrimaryKeySelective(LcjnCourseEvaluateDetail record);

    int updateByPrimaryKey(LcjnCourseEvaluateDetail record);
}