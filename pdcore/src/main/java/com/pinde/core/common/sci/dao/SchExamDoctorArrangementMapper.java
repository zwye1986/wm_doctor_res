package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SchExamDoctorArrangement;
import com.pinde.core.model.SchExamDoctorArrangementExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchExamDoctorArrangementMapper {
    int countByExample(SchExamDoctorArrangementExample example);

    int deleteByExample(SchExamDoctorArrangementExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchExamDoctorArrangement record);

    int insertSelective(SchExamDoctorArrangement record);

    List<SchExamDoctorArrangement> selectByExample(SchExamDoctorArrangementExample example);

    SchExamDoctorArrangement selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchExamDoctorArrangement record, @Param("example") SchExamDoctorArrangementExample example);

    int updateByExample(@Param("record") SchExamDoctorArrangement record, @Param("example") SchExamDoctorArrangementExample example);

    int updateByPrimaryKeySelective(SchExamDoctorArrangement record);

    int updateByPrimaryKey(SchExamDoctorArrangement record);
}