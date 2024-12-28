package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDoctorSchProcessEval;
import com.pinde.core.model.ResDoctorSchProcessEvalExample;
import com.pinde.core.model.ResDoctorSchProcessEvalWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorSchProcessEvalMapper {
    int countByExample(ResDoctorSchProcessEvalExample example);

    int deleteByExample(ResDoctorSchProcessEvalExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorSchProcessEvalWithBLOBs record);

    int insertSelective(ResDoctorSchProcessEvalWithBLOBs record);

    List<ResDoctorSchProcessEvalWithBLOBs> selectByExampleWithBLOBs(ResDoctorSchProcessEvalExample example);

    List<ResDoctorSchProcessEval> selectByExample(ResDoctorSchProcessEvalExample example);

    ResDoctorSchProcessEvalWithBLOBs selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorSchProcessEvalWithBLOBs record, @Param("example") ResDoctorSchProcessEvalExample example);

    int updateByExampleWithBLOBs(@Param("record") ResDoctorSchProcessEvalWithBLOBs record, @Param("example") ResDoctorSchProcessEvalExample example);

    int updateByExample(@Param("record") ResDoctorSchProcessEval record, @Param("example") ResDoctorSchProcessEvalExample example);

    int updateByPrimaryKeySelective(ResDoctorSchProcessEvalWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ResDoctorSchProcessEvalWithBLOBs record);

    int updateByPrimaryKey(ResDoctorSchProcessEval record);
}