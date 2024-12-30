package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysMonthlyAppStatistics;
import com.pinde.core.model.SysMonthlyAppStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyAppStatisticsMapper {
    int countByExample(SysMonthlyAppStatisticsExample example);

    int deleteByExample(SysMonthlyAppStatisticsExample example);

    int deleteByPrimaryKey(String appFlow);

    int insert(SysMonthlyAppStatistics record);

    int insertSelective(SysMonthlyAppStatistics record);

    List<SysMonthlyAppStatistics> selectByExample(SysMonthlyAppStatisticsExample example);

    SysMonthlyAppStatistics selectByPrimaryKey(String appFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyAppStatistics record, @Param("example") SysMonthlyAppStatisticsExample example);

    int updateByExample(@Param("record") SysMonthlyAppStatistics record, @Param("example") SysMonthlyAppStatisticsExample example);

    int updateByPrimaryKeySelective(SysMonthlyAppStatistics record);

    int updateByPrimaryKey(SysMonthlyAppStatistics record);
}