package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresBaseEvaluation;
import com.pinde.core.model.JsresBaseEvaluationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresBaseEvaluationMapper {
    int countByExample(JsresBaseEvaluationExample example);

    int deleteByExample(JsresBaseEvaluationExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresBaseEvaluation record);

    int insertSelective(JsresBaseEvaluation record);

    List<JsresBaseEvaluation> selectByExample(JsresBaseEvaluationExample example);

    JsresBaseEvaluation selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresBaseEvaluation record, @Param("example") JsresBaseEvaluationExample example);

    int updateByExample(@Param("record") JsresBaseEvaluation record, @Param("example") JsresBaseEvaluationExample example);

    int updateByPrimaryKeySelective(JsresBaseEvaluation record);

    int updateByPrimaryKey(JsresBaseEvaluation record);
}