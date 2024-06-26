package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresGraduationApply;
import com.pinde.sci.model.mo.JsresGraduationApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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