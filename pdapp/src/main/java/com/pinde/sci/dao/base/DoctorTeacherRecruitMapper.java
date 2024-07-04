package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.DoctorTeacherRecruit;
import com.pinde.sci.model.mo.DoctorTeacherRecruitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorTeacherRecruitMapper {
    int countByExample(DoctorTeacherRecruitExample example);

    int deleteByExample(DoctorTeacherRecruitExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(DoctorTeacherRecruit record);

    int insertSelective(DoctorTeacherRecruit record);

    List<DoctorTeacherRecruit> selectByExample(DoctorTeacherRecruitExample example);

    DoctorTeacherRecruit selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") DoctorTeacherRecruit record, @Param("example") DoctorTeacherRecruitExample example);

    int updateByExample(@Param("record") DoctorTeacherRecruit record, @Param("example") DoctorTeacherRecruitExample example);

    int updateByPrimaryKeySelective(DoctorTeacherRecruit record);

    int updateByPrimaryKey(DoctorTeacherRecruit record);
}