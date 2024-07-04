package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResExamDoctor;
import com.pinde.sci.model.mo.ResExamDoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResExamDoctorMapper {
    int countByExample(ResExamDoctorExample example);

    int deleteByExample(ResExamDoctorExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResExamDoctor record);

    int insertSelective(ResExamDoctor record);

    List<ResExamDoctor> selectByExample(ResExamDoctorExample example);

    ResExamDoctor selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResExamDoctor record, @Param("example") ResExamDoctorExample example);

    int updateByExample(@Param("record") ResExamDoctor record, @Param("example") ResExamDoctorExample example);

    int updateByPrimaryKeySelective(ResExamDoctor record);

    int updateByPrimaryKey(ResExamDoctor record);
}