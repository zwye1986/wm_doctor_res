package com.pinde.core.common.sci.dao;

import com.pinde.core.model.HbresDoctorDeptDetail;
import com.pinde.core.model.HbresDoctorDeptDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HbresDoctorDeptDetailMapper {
    int countByExample(HbresDoctorDeptDetailExample example);

    int deleteByExample(HbresDoctorDeptDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(HbresDoctorDeptDetail record);

    int insertSelective(HbresDoctorDeptDetail record);

    List<HbresDoctorDeptDetail> selectByExample(HbresDoctorDeptDetailExample example);

    HbresDoctorDeptDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") HbresDoctorDeptDetail record, @Param("example") HbresDoctorDeptDetailExample example);

    int updateByExample(@Param("record") HbresDoctorDeptDetail record, @Param("example") HbresDoctorDeptDetailExample example);

    int updateByPrimaryKeySelective(HbresDoctorDeptDetail record);

    int updateByPrimaryKey(HbresDoctorDeptDetail record);
}