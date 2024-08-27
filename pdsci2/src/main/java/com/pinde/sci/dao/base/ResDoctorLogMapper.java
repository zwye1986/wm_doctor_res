package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorLog;
import com.pinde.sci.model.mo.ResDoctorLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorLogMapper {
    int countByExample(ResDoctorLogExample example);

    int deleteByExample(ResDoctorLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorLog record);

    int insertSelective(ResDoctorLog record);

    List<ResDoctorLog> selectByExample(ResDoctorLogExample example);

    ResDoctorLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorLog record, @Param("example") ResDoctorLogExample example);

    int updateByExample(@Param("record") ResDoctorLog record, @Param("example") ResDoctorLogExample example);

    int updateByPrimaryKeySelective(ResDoctorLog record);

    int updateByPrimaryKey(ResDoctorLog record);
}