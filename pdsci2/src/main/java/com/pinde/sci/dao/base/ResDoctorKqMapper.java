package com.pinde.sci.dao.base;

import com.pinde.core.model.ResDoctorKq;
import com.pinde.core.model.ResDoctorKqExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorKqMapper {
    int countByExample(ResDoctorKqExample example);

    int deleteByExample(ResDoctorKqExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorKq record);

    int insertSelective(ResDoctorKq record);

    List<ResDoctorKq> selectByExample(ResDoctorKqExample example);

    ResDoctorKq selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorKq record, @Param("example") ResDoctorKqExample example);

    int updateByExample(@Param("record") ResDoctorKq record, @Param("example") ResDoctorKqExample example);

    int updateByPrimaryKeySelective(ResDoctorKq record);

    int updateByPrimaryKey(ResDoctorKq record);
}