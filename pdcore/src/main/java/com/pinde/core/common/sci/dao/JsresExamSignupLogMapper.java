package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresExamSignupLog;
import com.pinde.core.model.JsresExamSignupLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresExamSignupLogMapper {
    int countByExample(JsresExamSignupLogExample example);

    int deleteByExample(JsresExamSignupLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresExamSignupLog record);

    int insertSelective(JsresExamSignupLog record);

    List<JsresExamSignupLog> selectByExample(JsresExamSignupLogExample example);

    JsresExamSignupLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresExamSignupLog record, @Param("example") JsresExamSignupLogExample example);

    int updateByExample(@Param("record") JsresExamSignupLog record, @Param("example") JsresExamSignupLogExample example);

    int updateByPrimaryKeySelective(JsresExamSignupLog record);

    int updateByPrimaryKey(JsresExamSignupLog record);
}