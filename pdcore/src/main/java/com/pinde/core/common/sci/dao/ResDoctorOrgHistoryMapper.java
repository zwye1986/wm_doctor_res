package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDoctorOrgHistory;
import com.pinde.core.model.ResDoctorOrgHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorOrgHistoryMapper {
    int countByExample(ResDoctorOrgHistoryExample example);

    int deleteByExample(ResDoctorOrgHistoryExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorOrgHistory record);

    int insertSelective(ResDoctorOrgHistory record);

    List<ResDoctorOrgHistory> selectByExampleWithBLOBs(ResDoctorOrgHistoryExample example);

    List<ResDoctorOrgHistory> selectByExample(ResDoctorOrgHistoryExample example);

    ResDoctorOrgHistory selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorOrgHistory record, @Param("example") ResDoctorOrgHistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDoctorOrgHistory record, @Param("example") ResDoctorOrgHistoryExample example);

    int updateByExample(@Param("record") ResDoctorOrgHistory record, @Param("example") ResDoctorOrgHistoryExample example);

    int updateByPrimaryKeySelective(ResDoctorOrgHistory record);

    int updateByPrimaryKeyWithBLOBs(ResDoctorOrgHistory record);

    int updateByPrimaryKey(ResDoctorOrgHistory record);
}