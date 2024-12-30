package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresGraduationApply;
import com.pinde.core.model.JsresGraduationApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresGraduationApplyMapper {
    int countByExample(JsresGraduationApplyExample example);

    int deleteByExample(JsresGraduationApplyExample example);

    int deleteByPrimaryKey(String applyFlow);

    int insert(JsresGraduationApply record);

    int insertSelective(JsresGraduationApply record);

    List<JsresGraduationApply> selectByExample(JsresGraduationApplyExample example);

    JsresGraduationApply selectByPrimaryKey(String applyFlow);

    int updateByExampleSelective(@Param("record") JsresGraduationApply record, @Param("example") JsresGraduationApplyExample example);

    int updateByExample(@Param("record") JsresGraduationApply record, @Param("example") JsresGraduationApplyExample example);

    int updateByPrimaryKeySelective(JsresGraduationApply record);

    int updateByPrimaryKey(JsresGraduationApply record);
}