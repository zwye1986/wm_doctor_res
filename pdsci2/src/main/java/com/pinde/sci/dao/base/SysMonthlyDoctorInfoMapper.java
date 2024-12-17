package com.pinde.sci.dao.base;

import com.pinde.core.model.SysMonthlyDoctorInfo;
import com.pinde.core.model.SysMonthlyDoctorInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyDoctorInfoMapper {
    int countByExample(SysMonthlyDoctorInfoExample example);

    int deleteByExample(SysMonthlyDoctorInfoExample example);

    int deleteByPrimaryKey(String smdiFlow);

    int insert(SysMonthlyDoctorInfo record);

    int insertSelective(SysMonthlyDoctorInfo record);

    List<SysMonthlyDoctorInfo> selectByExample(SysMonthlyDoctorInfoExample example);

    List<SysMonthlyDoctorInfo> getMonthlyDoctorInfo2(SysMonthlyDoctorInfo sysMonthlyDoctorInfo);

    SysMonthlyDoctorInfo selectByPrimaryKey(String smdiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyDoctorInfo record, @Param("example") SysMonthlyDoctorInfoExample example);

    int updateByExample(@Param("record") SysMonthlyDoctorInfo record, @Param("example") SysMonthlyDoctorInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyDoctorInfo record);

    int updateByPrimaryKey(SysMonthlyDoctorInfo record);
}