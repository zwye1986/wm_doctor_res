package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresActivityStatistics;
import com.pinde.sci.model.mo.JsresActivityStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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