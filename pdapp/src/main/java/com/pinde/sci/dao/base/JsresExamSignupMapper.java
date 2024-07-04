package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresExamSignup;
import com.pinde.sci.model.mo.JsresExamSignupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresExamSignupMapper {
    int countByExample(JsresExamSignupExample example);

    int deleteByExample(JsresExamSignupExample example);

    int deleteByPrimaryKey(String signupFlow);

    int insert(JsresExamSignup record);

    int insertSelective(JsresExamSignup record);

    List<JsresExamSignup> selectByExample(JsresExamSignupExample example);

    JsresExamSignup selectByPrimaryKey(String signupFlow);

    int updateByExampleSelective(@Param("record") JsresExamSignup record, @Param("example") JsresExamSignupExample example);

    int updateByExample(@Param("record") JsresExamSignup record, @Param("example") JsresExamSignupExample example);

    int updateByPrimaryKeySelective(JsresExamSignup record);

    int updateByPrimaryKey(JsresExamSignup record);
}