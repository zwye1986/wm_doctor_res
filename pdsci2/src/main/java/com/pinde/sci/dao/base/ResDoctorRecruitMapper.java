package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResDoctorRecruitExample;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResDoctorRecruitMapper {
    int countByExample(ResDoctorRecruitExample example);

    int deleteByExample(ResDoctorRecruitExample example);

    int deleteByPrimaryKey(String recruitFlow);

    int insert(ResDoctorRecruitWithBLOBs record);

    int insertSelective(ResDoctorRecruitWithBLOBs record);

    List<ResDoctorRecruitWithBLOBs> selectByExampleWithBLOBs(ResDoctorRecruitExample example);

    List<ResDoctorRecruit> selectByExample(ResDoctorRecruitExample example);

    ResDoctorRecruitWithBLOBs selectByPrimaryKey(String recruitFlow);

    int updateByExampleSelective(@Param("record") ResDoctorRecruitWithBLOBs record, @Param("example") ResDoctorRecruitExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDoctorRecruitWithBLOBs record, @Param("example") ResDoctorRecruitExample example);

    int updateByExample(@Param("record") ResDoctorRecruit record, @Param("example") ResDoctorRecruitExample example);

    int updateByPrimaryKeySelective(ResDoctorRecruitWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ResDoctorRecruitWithBLOBs record);

    int updateByPrimaryKey(ResDoctorRecruit record);

    List<ResDoctorRecruit> selectByDoctorFlow(@Param("docotrFlows") List<String> docotrFlows);

    int updateCertificateNo(@Param("no") String no, @Param("recordFlow") String recordFlo,@Param("CERTIFICATE_ISSUING_STATUS") String CERTIFICATE_ISSUING_STATUS);

    int updateNotCertificateNo(@Param("recruitFlow") String recruitFlow);
}