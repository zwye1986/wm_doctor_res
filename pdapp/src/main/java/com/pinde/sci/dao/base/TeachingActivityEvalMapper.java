package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ActivityCfgExt;
import com.pinde.sci.model.mo.TeachingActivityEval;
import com.pinde.sci.model.mo.TeachingActivityEvalExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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

    List<ActivityCfgExt> searchActvity(Map<String,String> param);
}