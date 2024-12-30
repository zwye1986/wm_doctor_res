package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ScresGraduationApply;
import com.pinde.core.model.ScresGraduationApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScresGraduationApplyMapper {
    int countByExample(ScresGraduationApplyExample example);

    int deleteByExample(ScresGraduationApplyExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ScresGraduationApply record);

    int insertSelective(ScresGraduationApply record);

    List<ScresGraduationApply> selectByExample(ScresGraduationApplyExample example);

    ScresGraduationApply selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ScresGraduationApply record, @Param("example") ScresGraduationApplyExample example);

    int updateByExample(@Param("record") ScresGraduationApply record, @Param("example") ScresGraduationApplyExample example);

    int updateByPrimaryKeySelective(ScresGraduationApply record);

    int updateByPrimaryKey(ScresGraduationApply record);
}