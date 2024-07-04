package com.pinde.res.biz.gzzy;


import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResTarinNotice;
import com.pinde.sci.model.mo.ResTrainingOpinion;

import java.util.List;
import java.util.Map;

public interface IGzzyResLiveTrainingBiz {
    /**
     * 根据主键读一条信息
     */
    Map<String,String> read(String trainingOpinionFlow);
    /**
     * 根据主键删除一条反馈信息
     */
    void delOpinion(String trainingOpinionFlow);
    /**
     * 增加/修改一条信息
     */
    void edit(Map<String,Object> paramMap);
    /**
     * 读取一个学生的所有反馈意见
     */
    List<Map<String,String>> readByOpinionUserFlow(Map<String,Object> paramMap);
    /**
     * 根据反馈内容和是否回复查询当前机构下所有反馈意见
     */
    List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatus(String opinionUserContent, String replayStatus, String orgFlow);
    /**
     * 根据主键读取一条住培通知指南
     */
    Map<String,String> readNotice(String recordFlow);
    /**
     * 增加/修改一条住培通知
     */
    int edit(ResTarinNotice tarinNotice, String userFlow);
    /**
     * 根据标题关键字查询当前机构下所有通知
     */
    List<Map<String,String>> searchByTitleOrgFlow(Map<String,Object> paramMap);

    List<ResTarinNotice> searchByTitleOrgFlowAndWorkOrgId(String noticeTitle, String orgFlow, ResDoctor doctor);
}

