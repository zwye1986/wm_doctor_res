package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresSchDataStatistics;
import com.pinde.sci.model.mo.JsresSchDataStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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