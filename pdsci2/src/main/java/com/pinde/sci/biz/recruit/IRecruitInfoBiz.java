package com.pinde.sci.biz.recruit;

import com.pinde.core.model.RecruitInfo;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.core.model.RecruitInfoExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRecruitInfoBiz {
    String auditWriteExam(String recordStatus, String recruitFlow);
    String auditInterview(String recordStatus, String recruitFlow);
    String auditAdmit(String recordStatus, String recruitFlow);



    Integer updateRecruitInfo(RecruitInfo recruitInfo);

    RecruitInfo searchRecruitInfoByUserFlowAndRecruitYear(String userFlow, String recruitYear);

    RecruitInfo searchByRecruitFlow(String recruitFlow);

    Integer approve(String recruitFlow,String auditContent);

    Integer disapprove(String recruitFlow,String auditContent);

    List<RecruitInfoExt> selectRecruitInfoAboutAudit(HashMap<String, String> map);
    int saveRecruitInfo(RecruitInfo recruitInfo);

    List<RecruitInfoExt> searchCanExamRecruitInfo(Map<String, String> param);

    List<RecruitInfoExt> selectRecruitByUserFlow(String userFlow, String orgFlow);

    RecruitInfoExt searchRecruitInfoByFlow(String recruitFlow);

    ExcelUtile importExamScore(MultipartFile file, String recruitYear, List<RecruitInfoExt> recruitInfoExts);

    ExcelUtile saveImportInterviewScore(MultipartFile file, String recruitYear, List<RecruitInfoExt> recruitInfoExts);
    ExcelUtile saveImportAdmitResult(MultipartFile file, String recruitYear, List<RecruitInfoExt> recruitInfoExts);
}
