package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDoctorRecruitInfo;
import com.pinde.core.model.ResDoctorRecruitInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorRecruitInfoMapper {
    int countByExample(ResDoctorRecruitInfoExample example);

    int deleteByExample(ResDoctorRecruitInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorRecruitInfo record);

    int insertSelective(ResDoctorRecruitInfo record);

    List<ResDoctorRecruitInfo> selectByExample(ResDoctorRecruitInfoExample example);

    ResDoctorRecruitInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorRecruitInfo record, @Param("example") ResDoctorRecruitInfoExample example);

    int updateByExample(@Param("record") ResDoctorRecruitInfo record, @Param("example") ResDoctorRecruitInfoExample example);

    int updateByPrimaryKeySelective(ResDoctorRecruitInfo record);

    int updateByPrimaryKey(ResDoctorRecruitInfo record);
}