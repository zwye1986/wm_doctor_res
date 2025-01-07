package com.pinde.sci.biz.jszy;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorOrgHistory;
import com.pinde.core.model.JszyResDoctorOrgHistoryExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IJszyResDoctorOrgHistoryBiz {

    /**
     * 获取一条记录
     */
    ResDoctorOrgHistory readDocOrgHistory(String recordFlow);

    /**
     * 保存
     */
    int saveDocOrgHistory(ResDoctorOrgHistory docOrgHistory);

    /**
     * 查询
     */
    List<ResDoctorOrgHistory> searchDoctorOrgHistoryList(ResDoctorOrgHistory doctorOrgHistory);

    /**
     * 查询
     */
    List<JszyResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList, ResDoctor resDoctor, List<String> orgFlowList);

    /**
     * 变更培训基地
     */
    int auditTurnInOrg(String recordFlow, String changeStatusId, String doctorFlow, String time, String chooseFlag);

    /**
     * 查询待转出、待转入
     */
    List<ResDoctorOrgHistory> searchWaitingChangeOrgHistoryList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList);

    /**
     * 转出审核
     */
    int auditTurnOutOrg(ResDoctorOrgHistory history, String time);

    /**
     * 改变状态
     */
    int changeStatus(ResDoctorOrgHistory history, com.pinde.core.model.ResDoctorRecruit recruit);


    String checkFile(MultipartFile file);
}
