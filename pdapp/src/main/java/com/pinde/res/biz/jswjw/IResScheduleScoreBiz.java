package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.ResScheduleScore;

import java.util.List;

public interface IResScheduleScoreBiz {
    /**
     * 保存分数
     *
     * @param scheduleScore
     * @return
     */
    int saveSchedule(ResScheduleScore scheduleScore,String userFlow);

    /**
     * 保存扣分原因
     *
     * @param scheduleScore
     * @return
     */
    int saveScheduleDetailed(ResScheduleScore scheduleScore,String userFlow);

    List<ResScheduleScore> queryScheduleList(ResScheduleScore scheduleScore);

    List<ResScheduleScore> queryScheduleListNotItemName(ResScheduleScore scheduleScore);

    List<ResScheduleScore> queryScheduleListTotalled(ResScheduleScore scheduleScore);

    Integer deleteScheduleList(ResScheduleScore resScheduleScore);
}
