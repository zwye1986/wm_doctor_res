package com.pinde.res.biz.jswjw;

import com.pinde.core.model.*;
import com.pinde.res.model.jswjw.mo.OscaSkillRoomExt;

import java.util.List;
import java.util.Map;

public interface IJswjwStudentBiz {


    OscaSkillsAssessment getAuditOscaInfo(String userFlow);
    OscaDoctorAssessment getAuditOscaDocInfo(String userFlow);

    OscaSubjectMain getOscaSubjectMain(String subjectFlow);

    List<OscaSubjectStation> getOscaSubjectStations(String subjectFlow);

    List<OscaSkillRoom> getRooms(String stationFlow, String clinicalFlow);

    OscaSkillRoomExt getDocRoom(String userFlow, String stationFlow, String clinicalFlow);

    OscaSkillRoomExt getStationBestRoom(String stationFlow, String clinicalFlow);

    /**
     * 校验学员除了当前站点
     * @param param
     * @return
     */
    int checkIsWait(Map<String, Object> param);

    int checkIsAssess(Map<String, Object> param);

    /**
     * 
     * @param roomRecordFlow
     * @return
     */
    OscaSkillRoom getRoomByFlow(String roomRecordFlow);

    OscaSkillsAssessment getOscaSkillsAssessmentByFlow(String clinicalFlow);

    OscaSubjectStation getOscaSubjectStationsByFlow(String stationFlow);

    OscaSkillRoomDoc getOscaSkillRoomDocByDoc(Map<String, Object> param);

    int updateOscaSkillRoomDoc(OscaSkillRoomDoc docStation, SysUser user);

    List<OscaSkillRoomExt> getStationAllRoom(Map<String, Object> param);

    List<OscaSkillRoomDoc> getDocAllStation(Map<String, Object> param);

    OscaDoctorAssessment getOscaDoctorAssessment(String userFlow, String clinicalFlow);

    int editOscaDoctorAssessment(OscaDoctorAssessment oscaDoctorAssessment, SysUser user);

    int editJsresDoctorPaper(JsresDoctorPaper paper, SysUser user);

    int editJsresDoctorPart(JsresDoctorParticipation part, SysUser user);

    JsresDoctorPaper readJsresDoctorPaperByFlow(String recordFlow);

    JsresDoctorParticipation readJsresDoctorJsresDoctorParticipationByFlow(String recordFlow);

    List<JsresDoctorPaper> readJsresDoctorPaperByDoctorFlow(String userFlow);

    List<JsresDoctorParticipation> readJsresDoctorJsresDoctorParticipationByDoctorFlow(String userFlow);

    void deleteJsresDoctorPaperByFlow(String recordFlow);

    void deleteJsresDoctorParticipationByFlow(String recordFlow);

    List<ResErrorSchNotice> getResErrorNotices(Map<String, Object> param);

    ResErrorSchNotice readErrorSchNotice(String recordFlow);

    int editErrorNotice(ResErrorSchNotice notice, SysUser user);

    List<ResDoctorRecruit> findCertificates(String userFlow);

    ResDoctorRecruit readRecruit(String recordFlow);

    /**
     * @Department：研发部
     * @Description 查询消息条数
     * @Author fengxf
     * @Date 2022/3/3
     */
    int countResErrorNotices(String userFlow, String statusId);
}
  