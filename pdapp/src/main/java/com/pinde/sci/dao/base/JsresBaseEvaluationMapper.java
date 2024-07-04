package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresBaseEvaluation;
import com.pinde.sci.model.mo.JsresBaseEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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