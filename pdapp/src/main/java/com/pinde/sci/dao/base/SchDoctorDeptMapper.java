package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchDoctorDept;
import com.pinde.sci.model.mo.SchDoctorDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchDoctorDeptMapper {
    int countByExample(SchDoctorDeptExample example);

    int deleteByExample(SchDoctorDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchDoctorDept record);

    int insertSelective(SchDoctorDept record);

    List<SchDoctorDept> selectByExample(SchDoctorDeptExample example);

    SchDoctorDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchDoctorDept record, @Param("example") SchDoctorDeptExample example);

    int updateByExample(@Param("record") SchDoctorDept record, @Param("example") SchDoctorDeptExample example);

    int updateByPrimaryKeySelective(SchDoctorDept record);

    int updateByPrimaryKey(SchDoctorDept record);
}