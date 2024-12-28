package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysMonthlyReturnDelayInfo;
import com.pinde.core.model.SysMonthlyReturnDelayInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyReturnDelayInfoMapper {
    int countByExample(SysMonthlyReturnDelayInfoExample example);

    int deleteByExample(SysMonthlyReturnDelayInfoExample example);

    int deleteByPrimaryKey(String smrdiFlow);

    int insert(SysMonthlyReturnDelayInfo record);

    int insertSelective(SysMonthlyReturnDelayInfo record);

    List<SysMonthlyReturnDelayInfo> selectByExample(SysMonthlyReturnDelayInfoExample example);

    SysMonthlyReturnDelayInfo selectByPrimaryKey(String smrdiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyReturnDelayInfo record, @Param("example") SysMonthlyReturnDelayInfoExample example);

    int updateByExample(@Param("record") SysMonthlyReturnDelayInfo record, @Param("example") SysMonthlyReturnDelayInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyReturnDelayInfo record);

    int updateByPrimaryKey(SysMonthlyReturnDelayInfo record);
}