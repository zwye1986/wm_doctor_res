package com.pinde.sci.biz.recruit;

import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.RecruitInfoLog;

public interface IRecruitInfoLogBiz {
    Integer updateRecruitInfoLog(RecruitInfoLog recruitInfoLog,String auditContent);
    RecruitInfoLog checkSavedRecruitInfo(String recruitFlow);

    int saveRecruitLog(RecruitInfoLog log);
}
