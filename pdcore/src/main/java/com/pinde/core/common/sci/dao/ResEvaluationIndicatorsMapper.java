package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResEvaluationIndicators;
import com.pinde.core.model.ResEvaluationIndicatorsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResEvaluationIndicatorsMapper {
    int countByExample(ResEvaluationIndicatorsExample example);

    int deleteByExample(ResEvaluationIndicatorsExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResEvaluationIndicators record);

    int insertSelective(ResEvaluationIndicators record);

    List<ResEvaluationIndicators> selectByExample(ResEvaluationIndicatorsExample example);

    ResEvaluationIndicators selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResEvaluationIndicators record, @Param("example") ResEvaluationIndicatorsExample example);

    int updateByExample(@Param("record") ResEvaluationIndicators record, @Param("example") ResEvaluationIndicatorsExample example);

    int updateByPrimaryKeySelective(ResEvaluationIndicators record);

    int updateByPrimaryKey(ResEvaluationIndicators record);
}