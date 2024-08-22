package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchArrangeDoctorDept;
import com.pinde.sci.model.mo.SchArrangeDoctorDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchArrangeDoctorDeptMapper {
    int countByExample(SchArrangeDoctorDeptExample example);

    int deleteByExample(SchArrangeDoctorDeptExample example);

    int deleteByPrimaryKey(String arrDocDeptFlow);

    int insert(SchArrangeDoctorDept record);

    int insertSelective(SchArrangeDoctorDept record);

    List<SchArrangeDoctorDept> selectByExample(SchArrangeDoctorDeptExample example);

    SchArrangeDoctorDept selectByPrimaryKey(String arrDocDeptFlow);

    int updateByExampleSelective(@Param("record") SchArrangeDoctorDept record, @Param("example") SchArrangeDoctorDeptExample example);

    int updateByExample(@Param("record") SchArrangeDoctorDept record, @Param("example") SchArrangeDoctorDeptExample example);

    int updateByPrimaryKeySelective(SchArrangeDoctorDept record);

    int updateByPrimaryKey(SchArrangeDoctorDept record);
}