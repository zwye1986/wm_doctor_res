package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchDoctorSelectDept;
import com.pinde.sci.model.mo.SchDoctorSelectDeptExample;
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