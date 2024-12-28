package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDoctorKqLog;
import com.pinde.core.model.ResDoctorKqLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorKqLogMapper {
    int countByExample(ResDoctorKqLogExample example);

    int deleteByExample(ResDoctorKqLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorKqLog record);

    int insertSelective(ResDoctorKqLog record);

    List<ResDoctorKqLog> selectByExample(ResDoctorKqLogExample example);

    ResDoctorKqLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorKqLog record, @Param("example") ResDoctorKqLogExample example);

    int updateByExample(@Param("record") ResDoctorKqLog record, @Param("example") ResDoctorKqLogExample example);

    int updateByPrimaryKeySelective(ResDoctorKqLog record);

    int updateByPrimaryKey(ResDoctorKqLog record);
}