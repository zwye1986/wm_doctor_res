package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDoctorRecruitLog;
import com.pinde.core.model.ResDoctorRecruitLogExample;
import com.pinde.core.model.ResDoctorRecruitLogWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorRecruitLogMapper {
    int countByExample(ResDoctorRecruitLogExample example);

    int deleteByExample(ResDoctorRecruitLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorRecruitLogWithBLOBs record);

    int insertSelective(ResDoctorRecruitLogWithBLOBs record);

    List<ResDoctorRecruitLogWithBLOBs> selectByExampleWithBLOBs(ResDoctorRecruitLogExample example);

    List<ResDoctorRecruitLog> selectByExample(ResDoctorRecruitLogExample example);

    ResDoctorRecruitLogWithBLOBs selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorRecruitLogWithBLOBs record, @Param("example") ResDoctorRecruitLogExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDoctorRecruitLogWithBLOBs record, @Param("example") ResDoctorRecruitLogExample example);

    int updateByExample(@Param("record") ResDoctorRecruitLog record, @Param("example") ResDoctorRecruitLogExample example);

    int updateByPrimaryKeySelective(ResDoctorRecruitLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ResDoctorRecruitLogWithBLOBs record);

    int updateByPrimaryKey(ResDoctorRecruitLog record);
}