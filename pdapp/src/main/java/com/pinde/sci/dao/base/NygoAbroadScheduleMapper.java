package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NygoAbroadSchedule;
import com.pinde.sci.model.mo.NygoAbroadScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NygoAbroadScheduleMapper {
    int countByExample(NygoAbroadScheduleExample example);

    int deleteByExample(NygoAbroadScheduleExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NygoAbroadSchedule record);

    int insertSelective(NygoAbroadSchedule record);

    List<NygoAbroadSchedule> selectByExample(NygoAbroadScheduleExample example);

    NygoAbroadSchedule selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NygoAbroadSchedule record, @Param("example") NygoAbroadScheduleExample example);

    int updateByExample(@Param("record") NygoAbroadSchedule record, @Param("example") NygoAbroadScheduleExample example);

    int updateByPrimaryKeySelective(NygoAbroadSchedule record);

    int updateByPrimaryKey(NygoAbroadSchedule record);
}