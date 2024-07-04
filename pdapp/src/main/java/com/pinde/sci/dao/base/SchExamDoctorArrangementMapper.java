package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchExamDoctorArrangement;
import com.pinde.sci.model.mo.SchExamDoctorArrangementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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