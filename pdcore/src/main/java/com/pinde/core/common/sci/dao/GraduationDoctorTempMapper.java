package com.pinde.core.common.sci.dao;

import java.util.List;

import com.pinde.core.model.GraduationDoctorTemp;
import com.pinde.core.model.GraduationDoctorTempExample;
import org.apache.ibatis.annotations.Param;

public interface GraduationDoctorTempMapper {
    int countByExample(GraduationDoctorTempExample example);

    int deleteByExample(GraduationDoctorTempExample example);

    int deleteByPrimaryKey(String doctorFlow);

    int insert(GraduationDoctorTemp record);

    int insertSelective(GraduationDoctorTemp record);

    List<GraduationDoctorTemp> selectByExample(GraduationDoctorTempExample example);

    GraduationDoctorTemp selectByPrimaryKey(String doctorFlow);

    int updateByExampleSelective(@Param("record") GraduationDoctorTemp record, @Param("example") GraduationDoctorTempExample example);

    int updateByExample(@Param("record") GraduationDoctorTemp record, @Param("example") GraduationDoctorTempExample example);

    int updateByPrimaryKeySelective(GraduationDoctorTemp record);

    int updateByPrimaryKey(GraduationDoctorTemp record);
}