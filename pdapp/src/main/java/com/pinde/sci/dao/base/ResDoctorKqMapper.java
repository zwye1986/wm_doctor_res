package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorKq;
import com.pinde.sci.model.mo.ResDoctorKqExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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