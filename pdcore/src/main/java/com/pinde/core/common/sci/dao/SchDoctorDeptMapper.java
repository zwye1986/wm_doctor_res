package com.pinde.core.common.sci.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pinde.core.model.SchDoctorDept;
import com.pinde.core.model.SchDoctorDeptExample;
import liquibase.pro.packaged.B;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchDoctorDeptMapper extends BaseMapper<SchDoctorDept> {
    int countByExample(SchDoctorDeptExample example);

    int deleteByExample(SchDoctorDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchDoctorDept record);

    int insertSelective(SchDoctorDept record);

    List<SchDoctorDept> selectByExample(SchDoctorDeptExample example);

    List<SchDoctorDept> selectListByRotationFLowListList(@Param("doctorFlowList") List<String> doctorFlowList, @Param("rotationFlowListList") List<List<String>> rotationFlowListList);

    SchDoctorDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchDoctorDept record, @Param("example") SchDoctorDeptExample example);

    int updateByExample(@Param("record") SchDoctorDept record, @Param("example") SchDoctorDeptExample example);

    int updateByPrimaryKeySelective(SchDoctorDept record);

    int updateByPrimaryKey(SchDoctorDept record);
}