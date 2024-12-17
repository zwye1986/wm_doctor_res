package com.pinde.sci.biz.recruit;

import com.pinde.core.model.RecruitInfoLog;

public interface IRecruitInfoLogBiz {
    Integer updateRecruitInfoLog(RecruitInfoLog recruitInfoLog,String auditContent);
    RecruitInfoLog checkSavedRecruitInfo(String recruitFlow);

    int saveRecruitLog(RecruitInfoLog log);
}
