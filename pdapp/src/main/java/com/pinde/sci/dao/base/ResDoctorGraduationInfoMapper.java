package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorGraduationInfo;
import com.pinde.sci.model.mo.ResDoctorGraduationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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