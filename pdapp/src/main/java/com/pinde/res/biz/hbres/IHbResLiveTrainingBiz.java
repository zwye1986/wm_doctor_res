package com.pinde.res.biz.hbres;


import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResTarinNotice;
import com.pinde.sci.model.mo.ResTrainingOpinion;

import java.util.List;

public interface IHbResLiveTrainingBiz {
    /**
     * 根据主键读一条信息
     */
    ResTrainingOpinion read(String trainingOpinionFlow);
    /**
     * 增加/修改一条信息
     */
    int edit(ResTrainingOpinion trainingOpinion, String userFlow);
    /**
     * 读取一个学生的所有反馈意见
     */
    List<ResTrainingOpinion> readByOpinionUserFlow(String opinionUserFlow);
    /**
     * 根据反馈内容和是否回复查询当前机构下所有反馈意见
     */
    List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatus(String opinionUserContent, String replayStatus, String orgFlow);
    /**
     * 根据主键读取一条住培通知指南
     */
    ResTarinNotice readNotice(String recordFlow);
    /**
     * 增加/修改一条住培通知
     */
    int edit(ResTarinNotice tarinNotice, String userFlow);
    /**
     * 根据标题关键字查询当前机构下所有通知
     */
    List<ResTarinNotice> searchByTitleOrgFlow(String resNoticeTitle, String orgFlow);

    List<ResTarinNotice> searchByTitleOrgFlowAndWorkOrgId(String noticeTitle, String orgFlow, ResDoctor doctor);
}

