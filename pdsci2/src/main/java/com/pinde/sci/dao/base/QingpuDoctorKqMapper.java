package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.QingpuDoctorKq;
import com.pinde.sci.model.mo.QingpuDoctorKqExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QingpuDoctorKqMapper {
    int countByExample(QingpuDoctorKqExample example);

    int deleteByExample(QingpuDoctorKqExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(QingpuDoctorKq record);

    int insertSelective(QingpuDoctorKq record);

    List<QingpuDoctorKq> selectByExample(QingpuDoctorKqExample example);

    QingpuDoctorKq selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") QingpuDoctorKq record, @Param("example") QingpuDoctorKqExample example);

    int updateByExample(@Param("record") QingpuDoctorKq record, @Param("example") QingpuDoctorKqExample example);

    int updateByPrimaryKeySelective(QingpuDoctorKq record);

    int updateByPrimaryKey(QingpuDoctorKq record);
}