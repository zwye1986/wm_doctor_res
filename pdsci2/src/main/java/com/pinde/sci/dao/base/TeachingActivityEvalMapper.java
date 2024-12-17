package com.pinde.sci.dao.base;

import com.pinde.core.model.TeachingActivityEval;
import com.pinde.core.model.TeachingActivityEvalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingActivityEvalMapper {
    int countByExample(TeachingActivityEvalExample example);

    int deleteByExample(TeachingActivityEvalExample example);

    int deleteByPrimaryKey(String evalFlow);

    int insert(TeachingActivityEval record);

    int insertSelective(TeachingActivityEval record);

    List<TeachingActivityEval> selectByExample(TeachingActivityEvalExample example);

    TeachingActivityEval selectByPrimaryKey(String evalFlow);

    int updateByExampleSelective(@Param("record") TeachingActivityEval record, @Param("example") TeachingActivityEvalExample example);

    int updateByExample(@Param("record") TeachingActivityEval record, @Param("example") TeachingActivityEvalExample example);

    int updateByPrimaryKeySelective(TeachingActivityEval record);

    int updateByPrimaryKey(TeachingActivityEval record);
}