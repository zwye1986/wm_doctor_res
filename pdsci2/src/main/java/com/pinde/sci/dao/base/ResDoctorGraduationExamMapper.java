package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorGraduationExam;
import com.pinde.sci.model.mo.ResDoctorGraduationExamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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