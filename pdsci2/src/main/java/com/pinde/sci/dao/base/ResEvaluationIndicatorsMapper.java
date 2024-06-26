package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResEvaluationIndicators;
import com.pinde.sci.model.mo.ResEvaluationIndicatorsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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