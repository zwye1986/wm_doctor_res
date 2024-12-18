package com.pinde.sci.dao.base;

import com.pinde.core.model.SysMonthlyDoctorDetailInfo;
import com.pinde.core.model.SysMonthlyDoctorDetailInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyDoctorDetailInfoMapper {
    int countByExample(SysMonthlyDoctorDetailInfoExample example);

    int deleteByExample(SysMonthlyDoctorDetailInfoExample example);

    int deleteByPrimaryKey(String smddiFlow);

    int insert(SysMonthlyDoctorDetailInfo record);

    int insertSelective(SysMonthlyDoctorDetailInfo record);

    List<SysMonthlyDoctorDetailInfo> selectByExampleWithBLOBs(SysMonthlyDoctorDetailInfoExample example);

    List<SysMonthlyDoctorDetailInfo> selectByExample(SysMonthlyDoctorDetailInfoExample example);

    SysMonthlyDoctorDetailInfo selectByPrimaryKey(String smddiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyDoctorDetailInfo record, @Param("example") SysMonthlyDoctorDetailInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") SysMonthlyDoctorDetailInfo record, @Param("example") SysMonthlyDoctorDetailInfoExample example);

    int updateByExample(@Param("record") SysMonthlyDoctorDetailInfo record, @Param("example") SysMonthlyDoctorDetailInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyDoctorDetailInfo record);

    int updateByPrimaryKeyWithBLOBs(SysMonthlyDoctorDetailInfo record);

    int updateByPrimaryKey(SysMonthlyDoctorDetailInfo record);
}