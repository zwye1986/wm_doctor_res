package com.pinde.sci.dao.recruit;

import org.apache.ibatis.annotations.Param;

public interface RecruitExamInfoExtMapper {
    void delExamRoomInfo(@Param("examFlow") String examFlow);
}
