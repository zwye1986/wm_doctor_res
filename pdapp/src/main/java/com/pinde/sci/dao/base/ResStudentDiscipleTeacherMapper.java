package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResStudentDiscipleTeacher;
import com.pinde.sci.model.mo.ResStudentDiscipleTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResStudentDiscipleTeacherMapper {
    int countByExample(ResStudentDiscipleTeacherExample example);

    int deleteByExample(ResStudentDiscipleTeacherExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResStudentDiscipleTeacher record);

    int insertSelective(ResStudentDiscipleTeacher record);

    List<ResStudentDiscipleTeacher> selectByExample(ResStudentDiscipleTeacherExample example);

    ResStudentDiscipleTeacher selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResStudentDiscipleTeacher record, @Param("example") ResStudentDiscipleTeacherExample example);

    int updateByExample(@Param("record") ResStudentDiscipleTeacher record, @Param("example") ResStudentDiscipleTeacherExample example);

    int updateByPrimaryKeySelective(ResStudentDiscipleTeacher record);

    int updateByPrimaryKey(ResStudentDiscipleTeacher record);
}