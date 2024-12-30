package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresBaseEvaluationScore;
import com.pinde.core.model.JsresBaseEvaluationScoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresBaseEvaluationScoreMapper {
    int countByExample(JsresBaseEvaluationScoreExample example);

    int deleteByExample(JsresBaseEvaluationScoreExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresBaseEvaluationScore record);

    int insertSelective(JsresBaseEvaluationScore record);

    List<JsresBaseEvaluationScore> selectByExample(JsresBaseEvaluationScoreExample example);

    JsresBaseEvaluationScore selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresBaseEvaluationScore record, @Param("example") JsresBaseEvaluationScoreExample example);

    int updateByExample(@Param("record") JsresBaseEvaluationScore record, @Param("example") JsresBaseEvaluationScoreExample example);

    int updateByPrimaryKeySelective(JsresBaseEvaluationScore record);

    int updateByPrimaryKey(JsresBaseEvaluationScore record);
}