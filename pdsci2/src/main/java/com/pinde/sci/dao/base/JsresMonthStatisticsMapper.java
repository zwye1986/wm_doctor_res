package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresMonthStatistics;
import com.pinde.sci.model.mo.JsresMonthStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresMonthStatisticsMapper {
    int countByExample(JsresMonthStatisticsExample example);

    int deleteByExample(JsresMonthStatisticsExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresMonthStatistics record);

    int insertSelective(JsresMonthStatistics record);

    List<JsresMonthStatistics> selectByExample(JsresMonthStatisticsExample example);

    JsresMonthStatistics selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresMonthStatistics record, @Param("example") JsresMonthStatisticsExample example);

    int updateByExample(@Param("record") JsresMonthStatistics record, @Param("example") JsresMonthStatisticsExample example);

    int updateByPrimaryKeySelective(JsresMonthStatistics record);

    int updateByPrimaryKey(JsresMonthStatistics record);
}