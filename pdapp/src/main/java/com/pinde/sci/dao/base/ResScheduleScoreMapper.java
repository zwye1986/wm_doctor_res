package com.pinde.sci.dao.base;

import com.pinde.core.model.ResScheduleScore;
import com.pinde.core.model.ResScheduleScoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResScheduleScoreMapper {
    int countByExample(ResScheduleScoreExample example);

    int deleteByExample(ResScheduleScoreExample example);

    int deleteByPrimaryKey(String scheduleFlow);

    int insert(ResScheduleScore record);

    int insertSelective(ResScheduleScore record);

    List<ResScheduleScore> selectByExample(ResScheduleScoreExample example);

    ResScheduleScore selectByPrimaryKey(String scheduleFlow);

    int updateByExampleSelective(@Param("record") ResScheduleScore record, @Param("example") ResScheduleScoreExample example);

    int updateByExample(@Param("record") ResScheduleScore record, @Param("example") ResScheduleScoreExample example);

    int updateByPrimaryKeySelective(ResScheduleScore record);

    int updateByPrimaryKey(ResScheduleScore record);
}