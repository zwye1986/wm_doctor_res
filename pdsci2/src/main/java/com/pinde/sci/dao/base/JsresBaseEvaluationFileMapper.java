package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresBaseEvaluationFile;
import com.pinde.sci.model.mo.JsresBaseEvaluationFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresBaseEvaluationFileMapper {
    int countByExample(JsresBaseEvaluationFileExample example);

    int deleteByExample(JsresBaseEvaluationFileExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresBaseEvaluationFile record);

    int insertSelective(JsresBaseEvaluationFile record);

    List<JsresBaseEvaluationFile> selectByExample(JsresBaseEvaluationFileExample example);

    JsresBaseEvaluationFile selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresBaseEvaluationFile record, @Param("example") JsresBaseEvaluationFileExample example);

    int updateByExample(@Param("record") JsresBaseEvaluationFile record, @Param("example") JsresBaseEvaluationFileExample example);

    int updateByPrimaryKeySelective(JsresBaseEvaluationFile record);

    int updateByPrimaryKey(JsresBaseEvaluationFile record);
}