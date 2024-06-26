package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresDataStatistics;
import com.pinde.sci.model.mo.JsresDataStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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