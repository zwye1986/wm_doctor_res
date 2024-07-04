package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ScresGraduationApply;
import com.pinde.sci.model.mo.ScresGraduationApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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