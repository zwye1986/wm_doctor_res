package com.pinde.res.biz.osca;

import com.pinde.res.model.jswjw.mo.OscaSkillRoomExt;
import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface IOscaStudentBiz {


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

}
  