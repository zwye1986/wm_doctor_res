package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitExamTeacher;
import com.pinde.sci.model.mo.RecruitExamTeacherExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitExamTeacherMapper {
    int countByExample(RecruitExamTeacherExample example);

    int deleteByExample(RecruitExamTeacherExample example);

    int deleteByPrimaryKey(String teacherFlow);

    int insert(RecruitExamTeacher record);

    int insertSelective(RecruitExamTeacher record);

    List<RecruitExamTeacher> selectByExample(RecruitExamTeacherExample example);

    RecruitExamTeacher selectByPrimaryKey(String teacherFlow);

    int updateByExampleSelective(@Param("record") RecruitExamTeacher record, @Param("example") RecruitExamTeacherExample example);

    int updateByExample(@Param("record") RecruitExamTeacher record, @Param("example") RecruitExamTeacherExample example);

    int updateByPrimaryKeySelective(RecruitExamTeacher record);

    int updateByPrimaryKey(RecruitExamTeacher record);
}