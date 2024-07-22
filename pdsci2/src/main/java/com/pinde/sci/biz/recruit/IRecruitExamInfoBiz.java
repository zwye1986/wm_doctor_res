package com.pinde.sci.biz.recruit;

import com.pinde.sci.model.mo.RecruitExamInfo;

import java.util.List;

public interface IRecruitExamInfoBiz {
    List<RecruitExamInfo> searchExamInfoByMainFlow(String mainFlow);

    RecruitExamInfo readByFlow(String examFlow);

    int saveExamInfo(RecruitExamInfo examInfo);

    void delExamDetail(String examFlow);

}
