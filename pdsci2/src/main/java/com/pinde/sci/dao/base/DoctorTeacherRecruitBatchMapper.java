package com.pinde.sci.dao.base;

import com.pinde.core.model.DoctorTeacherRecruitBatch;
import com.pinde.core.model.DoctorTeacherRecruitBatchExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorTeacherRecruitBatchMapper {
    int countByExample(DoctorTeacherRecruitBatchExample example);

    int deleteByExample(DoctorTeacherRecruitBatchExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(DoctorTeacherRecruitBatch record);

    int insertSelective(DoctorTeacherRecruitBatch record);

    List<DoctorTeacherRecruitBatch> selectByExample(DoctorTeacherRecruitBatchExample example);

    DoctorTeacherRecruitBatch selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") DoctorTeacherRecruitBatch record, @Param("example") DoctorTeacherRecruitBatchExample example);

    int updateByExample(@Param("record") DoctorTeacherRecruitBatch record, @Param("example") DoctorTeacherRecruitBatchExample example);

    int updateByPrimaryKeySelective(DoctorTeacherRecruitBatch record);

    int updateByPrimaryKey(DoctorTeacherRecruitBatch record);
}