package com.pinde.sci.dao.base;

import com.pinde.core.model.SchDoctorSelectDept;
import com.pinde.core.model.SchDoctorSelectDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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