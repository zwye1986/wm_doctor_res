package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ResScheduleScore;

import java.util.List;

public interface IResScheduleScoreBiz {
    /**
     * 保存分数
     *
     * @param scheduleScore
     * @return
     */
    int saveSchedule(ResScheduleScore scheduleScore);

    /**
     * 保存扣分原因
     *
     * @param scheduleScore
     * @return
     */
    int saveScheduleDetailed(ResScheduleScore scheduleScore);

    List<ResScheduleScore> queryScheduleList(ResScheduleScore scheduleScore);

    List<ResScheduleScore> queryScheduleListNotItemName(ResScheduleScore scheduleScore);

    List<ResScheduleScore> queryScheduleListTotalled(ResScheduleScore scheduleScore);

    Integer deleteScheduleList(ResScheduleScore resScheduleScore);

}
