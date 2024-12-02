package com.pinde.sci.dao.base;

import com.pinde.core.model.SysMonthlyStatistics;
import com.pinde.core.model.SysMonthlyStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMonthlyStatisticsMapper {
    int countByExample(SysMonthlyStatisticsExample example);

    int deleteByExample(SysMonthlyStatisticsExample example);

    int deleteByPrimaryKey(String statisticFlow);

    int insert(SysMonthlyStatistics record);

    int insertSelective(SysMonthlyStatistics record);

    List<SysMonthlyStatistics> selectByExample(SysMonthlyStatisticsExample example);

    SysMonthlyStatistics selectByPrimaryKey(String statisticFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyStatistics record, @Param("example") SysMonthlyStatisticsExample example);

    int updateByExample(@Param("record") SysMonthlyStatistics record, @Param("example") SysMonthlyStatisticsExample example);

    int updateByPrimaryKeySelective(SysMonthlyStatistics record);

    int updateByPrimaryKey(SysMonthlyStatistics record);
}