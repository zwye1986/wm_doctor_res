package com.pinde.sci.biz.jszy;

import com.pinde.sci.model.jszy.JszyResDoctorOrgHistoryExt;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorOrgHistory;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
     * 查询byorgFlowList
     */
    List<JszyResDoctorOrgHistoryExt> seearchInfoByFlow(ResDoctorOrgHistory docOrgHistory, List<String> changeStatusIdList, ResDoctor resDoctor, List<String> orgFlowList);

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
    int changeStatus(ResDoctorOrgHistory history, ResDoctorRecruit recruit);

    /**
     * 根据条件查询已送申请记录
     * @param paramMap
     * @return
     */
    List<Map<String,String>> searchChangeOrgInfoByParamMap(Map<String, Object> paramMap);

    String checkFile(MultipartFile file);

    String saveCheckFileToDirs(MultipartFile file, String changeRecruitFile, String changeTypeId);


    /**
     * 省厅审核基地变更如果同意则同步res_doctor 和 res_doctor_recruit
     * @param history
     * @return
     */
    int auditChangeOrg(ResDoctorOrgHistory history);

    List<Map<String,String>> searchChangeSpeInfoByParamMap(Map<String, Object> paramMap);

    int auditChangeSpe(ResDoctorOrgHistory history,ResDoctorRecruit doctorRecruit);
}
