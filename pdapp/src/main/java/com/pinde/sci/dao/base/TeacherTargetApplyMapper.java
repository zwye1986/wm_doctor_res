package com.pinde.sci.dao.base;

import com.pinde.core.model.TeacherTargetApply;
import com.pinde.core.model.TeacherTargetApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherTargetApplyMapper {
    int countByExample(TeacherTargetApplyExample example);

    int deleteByExample(TeacherTargetApplyExample example);

    int deleteByPrimaryKey(String applyFlow);

    int insert(TeacherTargetApply record);

    int insertSelective(TeacherTargetApply record);

    List<TeacherTargetApply> selectByExample(TeacherTargetApplyExample example);

    TeacherTargetApply selectByPrimaryKey(String applyFlow);

    int updateByExampleSelective(@Param("record") TeacherTargetApply record, @Param("example") TeacherTargetApplyExample example);

    int updateByExample(@Param("record") TeacherTargetApply record, @Param("example") TeacherTargetApplyExample example);

    int updateByPrimaryKeySelective(TeacherTargetApply record);

    int updateByPrimaryKey(TeacherTargetApply record);
}