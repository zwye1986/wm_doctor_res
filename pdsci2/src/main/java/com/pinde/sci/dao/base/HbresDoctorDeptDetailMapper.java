package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.HbresDoctorDeptDetail;
import com.pinde.sci.model.mo.HbresDoctorDeptDetailExample;
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