package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresGraduationApplyLog;
import com.pinde.core.model.JsresGraduationApplyLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JsresGraduationApplyLogMapper {
    int countByExample(JsresGraduationApplyLogExample example);

    int deleteByExample(JsresGraduationApplyLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresGraduationApplyLog record);

    int insertSelective(JsresGraduationApplyLog record);

    List<JsresGraduationApplyLog> selectByExample(JsresGraduationApplyLogExample example);

    JsresGraduationApplyLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresGraduationApplyLog record, @Param("example") JsresGraduationApplyLogExample example);

    int updateByExample(@Param("record") JsresGraduationApplyLog record, @Param("example") JsresGraduationApplyLogExample example);

    int updateByPrimaryKeySelective(JsresGraduationApplyLog record);

    int updateByPrimaryKey(JsresGraduationApplyLog record);
}