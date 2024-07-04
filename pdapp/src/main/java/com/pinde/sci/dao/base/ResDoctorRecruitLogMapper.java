package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorRecruitLog;
import com.pinde.sci.model.mo.ResDoctorRecruitLogExample;
import com.pinde.sci.model.mo.ResDoctorRecruitLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResDoctorRecruitLogMapper {
    int countByExample(ResDoctorRecruitLogExample example);

    int deleteByExample(ResDoctorRecruitLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorRecruitLogWithBLOBs record);

    int insertSelective(ResDoctorRecruitLogWithBLOBs record);

    List<ResDoctorRecruitLogWithBLOBs> selectByExampleWithBLOBs(ResDoctorRecruitLogExample example);

    List<ResDoctorRecruitLog> selectByExample(ResDoctorRecruitLogExample example);

    ResDoctorRecruitLogWithBLOBs selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorRecruitLogWithBLOBs record, @Param("example") ResDoctorRecruitLogExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDoctorRecruitLogWithBLOBs record, @Param("example") ResDoctorRecruitLogExample example);

    int updateByExample(@Param("record") ResDoctorRecruitLog record, @Param("example") ResDoctorRecruitLogExample example);

    int updateByPrimaryKeySelective(ResDoctorRecruitLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ResDoctorRecruitLogWithBLOBs record);

    int updateByPrimaryKey(ResDoctorRecruitLog record);
}