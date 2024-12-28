package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SchDoctorSelectDept;
import com.pinde.core.model.SchDoctorSelectDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchDoctorSelectDeptMapper {
    int countByExample(SchDoctorSelectDeptExample example);

    int deleteByExample(SchDoctorSelectDeptExample example);

    int deleteByPrimaryKey(String selectFlow);

    int insert(SchDoctorSelectDept record);

    int insertSelective(SchDoctorSelectDept record);

    List<SchDoctorSelectDept> selectByExample(SchDoctorSelectDeptExample example);

    SchDoctorSelectDept selectByPrimaryKey(String selectFlow);

    int updateByExampleSelective(@Param("record") SchDoctorSelectDept record, @Param("example") SchDoctorSelectDeptExample example);

    int updateByExample(@Param("record") SchDoctorSelectDept record, @Param("example") SchDoctorSelectDeptExample example);

    int updateByPrimaryKeySelective(SchDoctorSelectDept record);

    int updateByPrimaryKey(SchDoctorSelectDept record);
}