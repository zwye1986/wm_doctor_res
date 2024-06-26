package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorSchProcessEval;
import com.pinde.sci.model.mo.ResDoctorSchProcessEvalExample;
import com.pinde.sci.model.mo.ResDoctorSchProcessEvalWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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