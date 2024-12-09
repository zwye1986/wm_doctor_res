package com.pinde.sci.dao.base;

import com.pinde.core.model.ResResponsibleteacherDoctor;
import com.pinde.core.model.ResResponsibleteacherDoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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