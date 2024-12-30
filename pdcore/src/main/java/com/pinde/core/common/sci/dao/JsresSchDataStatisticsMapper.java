package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresSchDataStatistics;
import com.pinde.core.model.JsresSchDataStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresSchDataStatisticsMapper {
    int countByExample(JsresSchDataStatisticsExample example);

    int deleteByExample(JsresSchDataStatisticsExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresSchDataStatistics record);

    int insertSelective(JsresSchDataStatistics record);

    List<JsresSchDataStatistics> selectByExample(JsresSchDataStatisticsExample example);

    JsresSchDataStatistics selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresSchDataStatistics record, @Param("example") JsresSchDataStatisticsExample example);

    int updateByExample(@Param("record") JsresSchDataStatistics record, @Param("example") JsresSchDataStatisticsExample example);

    int updateByPrimaryKeySelective(JsresSchDataStatistics record);

    int updateByPrimaryKey(JsresSchDataStatistics record);
}