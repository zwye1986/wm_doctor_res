package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresActivityStatistics;
import com.pinde.core.model.JsresActivityStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresActivityStatisticsMapper {
    int countByExample(JsresActivityStatisticsExample example);

    int deleteByExample(JsresActivityStatisticsExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresActivityStatistics record);

    int insertSelective(JsresActivityStatistics record);

    List<JsresActivityStatistics> selectByExample(JsresActivityStatisticsExample example);

    JsresActivityStatistics selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresActivityStatistics record, @Param("example") JsresActivityStatisticsExample example);

    int updateByExample(@Param("record") JsresActivityStatistics record, @Param("example") JsresActivityStatisticsExample example);

    int updateByPrimaryKeySelective(JsresActivityStatistics record);

    int updateByPrimaryKey(JsresActivityStatistics record);
}