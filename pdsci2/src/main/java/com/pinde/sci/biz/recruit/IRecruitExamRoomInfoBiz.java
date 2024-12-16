package com.pinde.sci.biz.recruit;

import com.pinde.core.model.RecruitExamRoomInfo;

import java.util.List;

public interface IRecruitExamRoomInfoBiz {
    String IsExamRoomUsed(String roomFlow,String orgFlow);

    List<RecruitExamRoomInfo> readRoomInfosByExamFlow(String examFlow);

    int saveRoomInfo(RecruitExamRoomInfo roomInfo);
}
