package com.pinde.sci.dao.base;

import com.pinde.core.model.SysMonthlyDeptCycleInfo;
import com.pinde.core.model.SysMonthlyDeptCycleInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyDeptCycleInfoMapper {
    int countByExample(SysMonthlyDeptCycleInfoExample example);

    int deleteByExample(SysMonthlyDeptCycleInfoExample example);

    int deleteByPrimaryKey(String smdciFlow);

    int insert(SysMonthlyDeptCycleInfo record);

    int insertSelective(SysMonthlyDeptCycleInfo record);

    List<SysMonthlyDeptCycleInfo> selectByExample(SysMonthlyDeptCycleInfoExample example);

    SysMonthlyDeptCycleInfo selectByPrimaryKey(String smdciFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyDeptCycleInfo record, @Param("example") SysMonthlyDeptCycleInfoExample example);

    int updateByExample(@Param("record") SysMonthlyDeptCycleInfo record, @Param("example") SysMonthlyDeptCycleInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyDeptCycleInfo record);

    int updateByPrimaryKey(SysMonthlyDeptCycleInfo record);
}