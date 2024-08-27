package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.StuDeptOfStaff;
import com.pinde.sci.model.mo.StuDeptOfStaffExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuDeptOfStaffMapper {
    int countByExample(StuDeptOfStaffExample example);

    int deleteByExample(StuDeptOfStaffExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(StuDeptOfStaff record);

    int insertSelective(StuDeptOfStaff record);

    List<StuDeptOfStaff> selectByExample(StuDeptOfStaffExample example);

    StuDeptOfStaff selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") StuDeptOfStaff record, @Param("example") StuDeptOfStaffExample example);

    int updateByExample(@Param("record") StuDeptOfStaff record, @Param("example") StuDeptOfStaffExample example);

    int updateByPrimaryKeySelective(StuDeptOfStaff record);

    int updateByPrimaryKey(StuDeptOfStaff record);
}