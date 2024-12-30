package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.ResDoctorRecruitExample;
import com.pinde.core.model.ResDoctorRecruitWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorRecruitMapper {
    int countByExample(ResDoctorRecruitExample example);

    int deleteByExample(ResDoctorRecruitExample example);

    int deleteByPrimaryKey(String recruitFlow);

    int insert(ResDoctorRecruitWithBLOBs record);

    int insertSelective(ResDoctorRecruitWithBLOBs record);

    List<ResDoctorRecruitWithBLOBs> selectByExampleWithBLOBs(ResDoctorRecruitExample example);

    List<com.pinde.core.model.ResDoctorRecruit> selectByExample(ResDoctorRecruitExample example);

    ResDoctorRecruitWithBLOBs selectByPrimaryKey(String recruitFlow);

    int updateByExampleSelective(@Param("record") ResDoctorRecruitWithBLOBs record, @Param("example") ResDoctorRecruitExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDoctorRecruitWithBLOBs record, @Param("example") ResDoctorRecruitExample example);

    int updateByExample(@Param("record") ResDoctorRecruit record, @Param("example") ResDoctorRecruitExample example);

    int updateByPrimaryKeySelective(ResDoctorRecruitWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ResDoctorRecruitWithBLOBs record);

    int updateByPrimaryKey(ResDoctorRecruit record);

    List<com.pinde.core.model.ResDoctorRecruit> selectByDoctorFlow(@Param("docotrFlows") List<String> docotrFlows);

    int updateCertificateNo(@Param("no") String no, @Param("recordFlow") String recordFlo, @Param("CERTIFICATE_ISSUING_STATUS") String CERTIFICATE_ISSUING_STATUS);

    int updateNotCertificateNo(@Param("recruitFlow") String recruitFlow);
}