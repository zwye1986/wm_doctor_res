package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresOutDeptStatistics;
import com.pinde.core.model.JsresOutDeptStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresOutDeptStatisticsMapper {
    int countByExample(JsresOutDeptStatisticsExample example);

    int deleteByExample(JsresOutDeptStatisticsExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresOutDeptStatistics record);

    int insertSelective(JsresOutDeptStatistics record);

    List<JsresOutDeptStatistics> selectByExample(JsresOutDeptStatisticsExample example);

    JsresOutDeptStatistics selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresOutDeptStatistics record, @Param("example") JsresOutDeptStatisticsExample example);

    int updateByExample(@Param("record") JsresOutDeptStatistics record, @Param("example") JsresOutDeptStatisticsExample example);

    int updateByPrimaryKeySelective(JsresOutDeptStatistics record);

    int updateByPrimaryKey(JsresOutDeptStatistics record);
}