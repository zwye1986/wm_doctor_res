package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.jsres.JsResDoctorOrgHistoryExt;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorOrgHistory;

import java.util.List;

public interface IJsResDoctorOrgHistoryBiz {

    /**
     * 获取一条记录
     *
     * @param recordFlow
     * @return
     */
    ResDoctorOrgHistory readDocOrgHistory(String recordFlow);

    /**
     * 保存
     *
     * @param docOrgHistory
     * @return
     */
    int saveDocOrgHistory(ResDoctorOrgHistory docOrgHistory);

    /**
     * 查询
     *
     * @param doctorOrgHistory
     * @return
     */
    List<ResDoctorOrgHistory> searchDoctorOrgHistoryList(ResDoctorOrgHistory doctorOrgHistory);

    /**
     * 查询
     *
     * @param docOrgHistory
     * @param resDoctor
     * @return
     */
    List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,
                                                                 ResDoctor resDoctor, List<String> orgFlowList, List<String> docTypeList);
    List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList1(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,
                                                                  ResDoctor resDoctor, List<String> orgFlowList,List<String> docTypeList,List<String> sessionNumbers);
    List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList1New(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList,
          ResDoctor resDoctor, List<String> orgFlowList,List<String> docTypeList,List<String> sessionNumbers,List<String> jointOrgFlowList);

    /**
     * 查询byorgFlowList
     *
     * @param docOrgHistory
     * @param resDoctor
     * @return
     */
    List<JsResDoctorOrgHistoryExt> seearchInfoByFlow(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList
            , List<String> docTypeList, ResDoctor resDoctor, List<String> orgFlowList);
    List<JsResDoctorOrgHistoryExt> seearchInfoByFlow1(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList
            , List<String> docTypeList, ResDoctor resDoctor, List<String> orgFlowList,List<String> sessionNumbers);

    /**
     * 变更培训基地
     *
     * @param recordFlow
     * @param changeStatusId
     * @param doctorFlow
     * @return
     */
    int auditTurnInOrg(String recordFlow, String changeStatusId, String doctorFlow, String time, String chooseFlag);
    int auditTurnInOrgGlobal(ResDoctorOrgHistory docOrgHistory);

    /**
     * 查询待转出、待转入
     *
     * @param docOrgHistory
     * @return
     */
    List<ResDoctorOrgHistory> searchWaitingChangeOrgHistoryList(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList);

    /**
     * 转出审核
     *
     * @param history
     * @param time
     * @return
     */
    int auditTurnOutOrg(ResDoctorOrgHistory history, String time);

    /**
     * 改变状态
     */
    int changeStatus(ResDoctorOrgHistory history, com.pinde.core.model.ResDoctorRecruit recruit);

    /****************************高******校******管******理******员******角******色************************************************/

    List<JsResDoctorOrgHistoryExt> seearchInfoByFlowForUni(ResDoctorOrgHistory orgHistory, List<String> changeStatusIdList,
                                                           ResDoctor doctor, List<String> docTypeList);

    List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtListForUni(ResDoctorOrgHistory orgHistory, List<String> changeStatusIdList, ResDoctor doctor);
}
