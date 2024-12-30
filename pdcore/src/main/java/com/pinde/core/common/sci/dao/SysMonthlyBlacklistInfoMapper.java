package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysMonthlyBlacklistInfo;
import com.pinde.core.model.SysMonthlyBlacklistInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyBlacklistInfoMapper {
    int countByExample(SysMonthlyBlacklistInfoExample example);

    int deleteByExample(SysMonthlyBlacklistInfoExample example);

    int deleteByPrimaryKey(String smrdiFlow);

    int insert(SysMonthlyBlacklistInfo record);

    int insertSelective(SysMonthlyBlacklistInfo record);

    List<SysMonthlyBlacklistInfo> selectByExample(SysMonthlyBlacklistInfoExample example);

    SysMonthlyBlacklistInfo selectByPrimaryKey(String smrdiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyBlacklistInfo record, @Param("example") SysMonthlyBlacklistInfoExample example);

    int updateByExample(@Param("record") SysMonthlyBlacklistInfo record, @Param("example") SysMonthlyBlacklistInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyBlacklistInfo record);

    int updateByPrimaryKey(SysMonthlyBlacklistInfo record);
}