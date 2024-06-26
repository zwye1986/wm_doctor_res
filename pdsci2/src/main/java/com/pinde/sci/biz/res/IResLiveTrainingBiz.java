package com.pinde.sci.biz.res;


import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResTarinNotice;
import com.pinde.sci.model.mo.ResTrainingOpinion;
import com.pinde.sci.model.mo.SysOrg;

import java.util.List;
import java.util.Map;

public interface IResLiveTrainingBiz {
    /**
     * 根据主键读一条信息
     */
    ResTrainingOpinion read(String trainingOpinionFlow);
    /**
     * 增加/修改一条信息
     */
    int edit(ResTrainingOpinion trainingOpinion);
    /**
     * 读取一个学生的所有反馈意见
     */
    List<ResTrainingOpinion> readByOpinionUserFlow(String opinionUserFlow);
    /**
     * 根据反馈内容和是否回复查询当前机构下所有反馈意见
     */
    List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatus(String opinionUserContent,String replayStatus,String orgFlow);
    /**
     * 根据反馈内容和是否回复查询当前高校下所有反馈意见（湖北）
     */
    List<ResTrainingOpinion> searchOpinionByUni4hb(Map<String,Object> paramMap);
    /**
     * 根据主键读取一条住培通知指南
     */
    ResTarinNotice readNotice(String recordFlow);
    /**
     * 增加/修改一条住培通知
     */
    int edit(ResTarinNotice tarinNotice);
    /**
     * 根据标题关键字查询当前机构下所有通知
     */
    List<ResTarinNotice> searchByTitleOrgFlow(String resNoticeTitle, String orgFlow, String roleFlag);
    /**
     * 根据标题关键字查询当前高校下所有通知
     */
    List<ResTarinNotice> searchByTitleOrgFlow4University(Map<String,Object> paramMap);


    List<ResTrainingOpinion> searchOpinionByUni(String opinionUserContent, String orgFlow, SysOrg org);

    List<ResTarinNotice> searchByTitleOrgFlowAndSchool(String resNoticeTitle, String orgFlow, ResDoctor doctor);

    List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatusNew(String opinionUserContent, String replayStatus, String orgFlow, String resTrainingSpeId);
}

