package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresDataStatistics;
import com.pinde.core.model.JsresDataStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresDataStatisticsMapper {
    int countByExample(JsresDataStatisticsExample example);

    int deleteByExample(JsresDataStatisticsExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresDataStatistics record);

    int insertSelective(JsresDataStatistics record);

    List<JsresDataStatistics> selectByExample(JsresDataStatisticsExample example);

    JsresDataStatistics selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresDataStatistics record, @Param("example") JsresDataStatisticsExample example);

    int updateByExample(@Param("record") JsresDataStatistics record, @Param("example") JsresDataStatisticsExample example);

    int updateByPrimaryKeySelective(JsresDataStatistics record);

    int updateByPrimaryKey(JsresDataStatistics record);
}