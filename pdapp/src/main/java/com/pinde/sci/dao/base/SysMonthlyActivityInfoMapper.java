package com.pinde.sci.dao.base;

import com.pinde.core.model.SysMonthlyActivityInfo;
import com.pinde.core.model.SysMonthlyActivityInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyActivityInfoMapper {
    int countByExample(SysMonthlyActivityInfoExample example);

    int deleteByExample(SysMonthlyActivityInfoExample example);

    int deleteByPrimaryKey(String smrdiFlow);

    int insert(SysMonthlyActivityInfo record);

    int insertSelective(SysMonthlyActivityInfo record);

    List<SysMonthlyActivityInfo> selectByExample(SysMonthlyActivityInfoExample example);

    SysMonthlyActivityInfo selectByPrimaryKey(String smrdiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyActivityInfo record, @Param("example") SysMonthlyActivityInfoExample example);

    int updateByExample(@Param("record") SysMonthlyActivityInfo record, @Param("example") SysMonthlyActivityInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyActivityInfo record);

    int updateByPrimaryKey(SysMonthlyActivityInfo record);
}