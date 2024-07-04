package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresBaseEvaluationScore;
import com.pinde.sci.model.mo.JsresBaseEvaluationScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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