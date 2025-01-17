package com.pinde.sci.biz.recruit;

import com.pinde.core.model.RecruitAdmitInfo;
import com.pinde.core.model.RecruitInfoExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRecruitAdmitInfoBiz {
    Boolean isQualifyAdmit(String recruitFlow);

    Boolean sendAdmit(RecruitAdmitInfo recruitAdmitInfo);

    Integer importAdmitFromExcel(MultipartFile file, String type);

    RecruitAdmitInfo searchByExample(String recruitFlow, String orgFlow);

    RecruitAdmitInfo readByFlow(String recruitFlow);

    Boolean sendAdmitAll(List<RecruitInfoExt> recruitInfoExts, RecruitAdmitInfo recruitAdmitInfo);
}
