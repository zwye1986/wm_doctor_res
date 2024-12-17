package com.pinde.sci.dao.recruit;

import com.pinde.core.model.RecruitInfo;
import com.pinde.sci.form.recruit.ExamInfoFlowForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitExamMainExtMapper {
    int getExamNum(@Param("mainFlow") String mainFlow);

    void delExamInfo(@Param("mainFlow") String mainFlow);

    void delExamRoomInfo(@Param("mainFlow") String mainFlow);

    int checkExamTime(@Param("mainFlow") String mainFlow, @Param("examFlow") String examFlow, @Param("examStartTime") String examStartTime, @Param("examEndTime") String examEndTime);

    int checkInterviewTime(@Param("mainFlow") String mainFlow, @Param("examFlow") String examFlow, @Param("interviewStartTime") String interviewStartTime, @Param("interviewEndTime") String interviewEndTime);

    List<RecruitInfo> readPassedRecruitInfos(@Param("orgFlow") String orgFlow, @Param("recruitYear") String recruitYear);

    List<ExamInfoFlowForm> readExamInfoFlowFormsByFlow(@Param("mainFlow") String mainFlow);
}
