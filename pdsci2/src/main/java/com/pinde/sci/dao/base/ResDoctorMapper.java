package com.pinde.sci.dao.base;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResDoctorMapper {
    int countByExample(ResDoctorExample example);

    int deleteByExample(ResDoctorExample example);

    int deleteByPrimaryKey(String doctorFlow);

    int insert(ResDoctor record);

    int insertSelective(ResDoctor record);

    List<ResDoctor> selectByExample(ResDoctorExample example);

    List<Map<String, Object>> readDoctorCountBySpe(ResDoctor resDoctor);

    ResDoctor selectByPrimaryKey(String doctorFlow);

    int updateByExampleSelective(@Param("record") ResDoctor record, @Param("example") ResDoctorExample example);

    int updateByExample(@Param("record") ResDoctor record, @Param("example") ResDoctorExample example);

    int updateByPrimaryKeySelective(ResDoctor record);

    int updateByPrimaryKey(ResDoctor record);
}