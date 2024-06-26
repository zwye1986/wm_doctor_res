package com.pinde.sci.biz.recruit;

import com.pinde.sci.model.mo.RecruitInterviewInfo;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRecruitInterviewInfoBiz {
    Boolean isQualifyInterview(String recruitFlow);

    Boolean sendInterview(RecruitInterviewInfo recruitInterviewInfo);
    Boolean sendAllInterview(List<RecruitInfoExt> recruitInfoExts);
    Integer importInterviewFromExcel(MultipartFile file, String type);

    RecruitInterviewInfo searchByExample(String recruitFlow, String orgFlow);

    RecruitInterviewInfo readByFlow(String recruitFlow);
}
