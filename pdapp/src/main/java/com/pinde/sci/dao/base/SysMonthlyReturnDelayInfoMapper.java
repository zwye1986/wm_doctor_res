package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysMonthlyReturnDelayInfo;
import com.pinde.sci.model.mo.SysMonthlyReturnDelayInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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