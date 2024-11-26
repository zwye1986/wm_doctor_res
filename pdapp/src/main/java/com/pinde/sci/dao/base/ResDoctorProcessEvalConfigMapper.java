package com.pinde.sci.dao.base;

import com.pinde.core.model.ResDoctorProcessEvalConfig;
import com.pinde.core.model.ResDoctorProcessEvalConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResDoctorProcessEvalConfigMapper {
    int countByExample(ResDoctorProcessEvalConfigExample example);

    int deleteByExample(ResDoctorProcessEvalConfigExample example);

    int deleteByPrimaryKey(String configFlow);

    int insert(ResDoctorProcessEvalConfig record);

    int insertSelective(ResDoctorProcessEvalConfig record);

    List<ResDoctorProcessEvalConfig> selectByExampleWithBLOBs(ResDoctorProcessEvalConfigExample example);

    List<ResDoctorProcessEvalConfig> selectByExample(ResDoctorProcessEvalConfigExample example);

    ResDoctorProcessEvalConfig selectByPrimaryKey(String configFlow);

    int updateByExampleSelective(@Param("record") ResDoctorProcessEvalConfig record, @Param("example") ResDoctorProcessEvalConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDoctorProcessEvalConfig record, @Param("example") ResDoctorProcessEvalConfigExample example);

    int updateByExample(@Param("record") ResDoctorProcessEvalConfig record, @Param("example") ResDoctorProcessEvalConfigExample example);

    int updateByPrimaryKeySelective(ResDoctorProcessEvalConfig record);

    int updateByPrimaryKeyWithBLOBs(ResDoctorProcessEvalConfig record);

    int updateByPrimaryKey(ResDoctorProcessEvalConfig record);
}