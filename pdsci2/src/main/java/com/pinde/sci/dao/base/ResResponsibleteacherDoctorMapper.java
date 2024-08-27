package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResResponsibleteacherDoctor;
import com.pinde.sci.model.mo.ResResponsibleteacherDoctorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResResponsibleteacherDoctorMapper {
    int countByExample(ResResponsibleteacherDoctorExample example);

    int deleteByExample(ResResponsibleteacherDoctorExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResResponsibleteacherDoctor record);

    int insertSelective(ResResponsibleteacherDoctor record);

    List<ResResponsibleteacherDoctor> selectByExample(ResResponsibleteacherDoctorExample example);

    ResResponsibleteacherDoctor selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResResponsibleteacherDoctor record, @Param("example") ResResponsibleteacherDoctorExample example);

    int updateByExample(@Param("record") ResResponsibleteacherDoctor record, @Param("example") ResResponsibleteacherDoctorExample example);

    int updateByPrimaryKeySelective(ResResponsibleteacherDoctor record);

    int updateByPrimaryKey(ResResponsibleteacherDoctor record);
}