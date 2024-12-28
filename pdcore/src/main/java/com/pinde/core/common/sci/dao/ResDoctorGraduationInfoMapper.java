package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDoctorGraduationInfo;
import com.pinde.core.model.ResDoctorGraduationInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorGraduationInfoMapper {
    int countByExample(ResDoctorGraduationInfoExample example);

    int deleteByExample(ResDoctorGraduationInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorGraduationInfo record);

    int insertSelective(ResDoctorGraduationInfo record);

    List<ResDoctorGraduationInfo> selectByExample(ResDoctorGraduationInfoExample example);

    ResDoctorGraduationInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorGraduationInfo record, @Param("example") ResDoctorGraduationInfoExample example);

    int updateByExample(@Param("record") ResDoctorGraduationInfo record, @Param("example") ResDoctorGraduationInfoExample example);

    int updateByPrimaryKeySelective(ResDoctorGraduationInfo record);

    int updateByPrimaryKey(ResDoctorGraduationInfo record);
}