package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorKqLog;
import com.pinde.sci.model.mo.ResDoctorKqLogExample;
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