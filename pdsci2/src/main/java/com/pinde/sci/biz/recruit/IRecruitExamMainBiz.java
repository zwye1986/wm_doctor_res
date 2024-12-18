package com.pinde.sci.biz.recruit;

import com.pinde.core.model.RecruitCfgInfo;
import com.pinde.core.model.RecruitExamMain;
import com.pinde.core.model.RecruitInfo;
import com.pinde.sci.form.recruit.ExamInfoFlowForm;
import com.pinde.sci.form.recruit.ExamInfoForm;

import java.util.List;

public interface IRecruitExamMainBiz {
    List<RecruitExamMain> searchAll(RecruitExamMain recruitExamMain);

    RecruitExamMain searchExamMainByRecruitYear(String recruitYear);

    Integer addRecExamMainByCfgInfo(RecruitCfgInfo searchResult);

    Integer updateExamMainByMainFlow(RecruitExamMain recruitExamMain);

    RecruitExamMain readByFlow(String mainFlow);

    int saveExamMain(RecruitExamMain recruitExamMain);

    int checkPassedNum(String recruitYear, String orgFlow);

    RecruitExamMain readByCfgFlow(String cfgFlow, String orgFlow);

    int checkAuditingNum(String recruitYear, String orgFlow);

    int getExamNum(String mainFlow);

    void delExamDetail(String mainFlow);

    int checkExamTime(String mainFlow, String examFlow, String examStartTime, String examEndTime);

    int checkInterviewTime(String mainFlow, String examFlow, String interviewStartTime, String interviewEndTime);

    String saveExamInfo(ExamInfoForm form);

    List<RecruitInfo> readPassedRecruitInfos(String orgFlow, String recruitYear);

    List<ExamInfoFlowForm> readExamInfoFlowFormsByFlow(String mainFlow);

    void fenpei(List<RecruitInfo> recruitInfos, List<ExamInfoFlowForm> flowForms);

    List<RecruitInfo> readMainRecruitInfos(String mainFlow);
}
