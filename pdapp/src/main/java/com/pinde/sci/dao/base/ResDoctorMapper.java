package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorMapper {
    int countByExample(ResDoctorExample example);

    int deleteByExample(ResDoctorExample example);

    int deleteByPrimaryKey(String doctorFlow);

    int insert(ResDoctor record);

    int insertSelective(ResDoctor record);

    List<ResDoctor> selectByExample(ResDoctorExample example);

    ResDoctor selectByPrimaryKey(String doctorFlow);

    int updateByExampleSelective(@Param("record") ResDoctor record, @Param("example") ResDoctorExample example);

    int updateByExample(@Param("record") ResDoctor record, @Param("example") ResDoctorExample example);

    int updateByPrimaryKeySelective(ResDoctor record);

    int updateByPrimaryKey(ResDoctor record);
}