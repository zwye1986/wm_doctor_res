package com.pinde.sci.dao.base;

import com.pinde.core.model.ResDoctorGraduationExam;
import com.pinde.core.model.ResDoctorGraduationExamExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorGraduationExamMapper {
    int countByExample(ResDoctorGraduationExamExample example);

    int deleteByExample(ResDoctorGraduationExamExample example);

    int deleteByPrimaryKey(String examFlow);

    int insert(ResDoctorGraduationExam record);

    int insertSelective(ResDoctorGraduationExam record);

    List<ResDoctorGraduationExam> selectByExample(ResDoctorGraduationExamExample example);

    ResDoctorGraduationExam selectByPrimaryKey(String examFlow);

    int updateByExampleSelective(@Param("record") ResDoctorGraduationExam record, @Param("example") ResDoctorGraduationExamExample example);

    int updateByExample(@Param("record") ResDoctorGraduationExam record, @Param("example") ResDoctorGraduationExamExample example);

    int updateByPrimaryKeySelective(ResDoctorGraduationExam record);

    int updateByPrimaryKey(ResDoctorGraduationExam record);
}