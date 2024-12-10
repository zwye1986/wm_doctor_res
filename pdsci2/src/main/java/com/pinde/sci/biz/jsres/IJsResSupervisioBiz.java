package com.pinde.sci.biz.jsres;

import com.pinde.core.model.SysDept;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IJsResSupervisioBiz {


    ResEvaluationScore searchEvaluationOwnerScoreByItemId(ResEvaluationScore evaluationScore);

    int saveScore(ResEvaluationScore evaluationScore);

    List<ResEvaluationScore> searchEvaluationScore(ResEvaluationScore evaluationScore);

    String checkImg(MultipartFile uploadFile);

    String saveFileToDirs(String oldFolderName, MultipartFile uploadFile, String folderName, String orgFlow, String planYear, String itemId);

    int saveJsresSupervisioFile(JsresSupervisioFile jsresSupervisioFile);

    int deleteFileByPrimaryKey(String recordFlow);

    List<JsresSupervisioFile> searchSupervisioFile(String planYear, String orgFlow, String speId);

    JsresSupervisioFile searchJsresSupervisioFileByRecordFlow(String recordFlow);

    ResScheduleScore queryScheduleOne(ResScheduleScore resScheduleScore);

    List<ResScheduleScore> queryScheduleList(ResScheduleScore resScheduleScore);

    int saveSchedule(ResScheduleScore resScheduleScore) throws UnsupportedEncodingException;

    int delSchedule(ResScheduleScore resScheduleScore);

    int saveScheduleDetailed(ResScheduleScore resScheduleScore);

    List<ResScheduleScore> queryScheduleListNotItemName(ResScheduleScore resScheduleScore);

    List<ResScheduleScore> queryScheduleListTotalled(ResScheduleScore resScheduleScore);

    List<ResOrgSpeAssign> searchSpeAll();

    List<SysDept> selectDeptByOrgFlow(String orgFlow);

    List<ResHospScoreTable> chooseTable(String speId,String inspectionType);

    List<ResEvaluationIndicators> searchAll();

    ResEvaluationScore findHospSelfAssessmentScore(ResEvaluationScore score);

    /*
       下面是基地自评接口
     */

    List<HospSelfAssessmentCfg> findAllCfg(HospSelfAssessmentCfg cfg);

    HospSelfAssessmentCfg findCfgBySessionNumber(String sessionNumber);

    String saveAssessment(HospSelfAssessmentCfg cfg,String type);

    List<ResEvaluationScore> findHospSelfAssessmentAllScore(ResEvaluationScore score,String subjectType);

    HospSelfAssessment findHospSelfAssessment(String sessionNumber,String orgFlow,String speId,String subjectType);

    List<HospSelfAssessment> findAllAssessmentBySpeAndYear(HospSelfAssessment assessment);

    List<JsresSupervisioFile> findSupervisioFile(JsresSupervisioFile supervisioFile,String subjectType);

    int findCoreIndicatorsNum(String cfgFlow,String orgFlow,String speId,String subjectType);

    int saveAssessmentAllScore(HospSelfAssessment assessment);

    int saveHospSelfAssessmentScore(ResEvaluationScore score) throws UnsupportedEncodingException;
}
