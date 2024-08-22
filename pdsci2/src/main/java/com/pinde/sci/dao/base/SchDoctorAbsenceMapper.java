package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchDoctorAbsence;
import com.pinde.sci.model.mo.SchDoctorAbsenceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchDoctorAbsenceMapper {
    int countByExample(SchDoctorAbsenceExample example);

    int deleteByExample(SchDoctorAbsenceExample example);

    int deleteByPrimaryKey(String absenceFlow);

    int insert(SchDoctorAbsence record);

    int insertSelective(SchDoctorAbsence record);

    List<SchDoctorAbsence> selectByExample(SchDoctorAbsenceExample example);

    SchDoctorAbsence selectByPrimaryKey(String absenceFlow);

    int updateByExampleSelective(@Param("record") SchDoctorAbsence record, @Param("example") SchDoctorAbsenceExample example);

    int updateByExample(@Param("record") SchDoctorAbsence record, @Param("example") SchDoctorAbsenceExample example);

    int updateByPrimaryKeySelective(SchDoctorAbsence record);

    int updateByPrimaryKey(SchDoctorAbsence record);
}