package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISysSupervisioUserBiz {
    List<SysUser> selectUserBysubjectActivitiFlows(String subjectActivitiFlows);

    ResSupervisioReport expertMajorByrecordFlow(String recordFlow);

    ResSupervisioReport expertMajorBySubject(String subjectFlow,String subjectActivitiFlows,String partofFlow);

    ResSupervisioReport selectReportContextMas(ResSupervisioReport resSupervisioReport);

    int addReport(ResSupervisioReport resSupervisioReport);

    int saveReport(ResSupervisioReport resSupervisioReport);

    List<ResSupervisioReport>reportBySubjectList(String subjectFlow,String subjectActivitiFlows,String partof);

    List<JsresSupervisioFile> searchSubjectFile(String subjectYear, String subjectFlow, String speId);

    //督导 查看提交记录
    List<ResSupervisioSubjectRecords> selectRecordBySubjectFlowAndRoleFlag(String subjectFlow,String roleFlag);

    int insertRecores(String subjectFlow,String userFlow,String roleFlag);

    ResSupervisioSubjectRecords  selectRecords(String subjectFlow,String userFlow);

    List<ResSupervisioSubjectUser> selectSubjectUserBySubjectActivitiFlows(String subjectActivitiFlows);

    List<ResSupervisioSubjectUser> searchSubjectAndUserLevedId(String subjectFlow,String userLevedId);

    List<Map<String,String>> searchBaseExpertSupervisio(Map<String,Object> param);

    List<ResSupervisioSubject> selectSubjectActiveListByParam(Map<String, Object> param);

    List<ResSupervisioSubject> selectBaseSubjectList(Map<String, Object> param);

    List<ResSupervisioSubject> selectLocalSubjectListByParam(Map<String, Object> param);

    SysUser findUserByUserCode(String userCode);

    SysSupervisioUser findByUserCode(String userCode);

    SysSupervisioUser findByUserPhone(String userPhone);

    List<SysUser> findByUserPhoneForLeader(String userFlow,String userPhone);

    List<SysUser> selectSupervisioUserList(Map<String, Object> param);

    List<SysUser> selectSysUserList(Map<String, Object> param);

    SysSupervisioUser findByUserFlow(String userFlow);

    int addSupervisioUser(SysUser user);

    int editSupervisioUser(SysUser user);

    int importSupervisioUser(MultipartFile file) throws Exception ;

    int importHospitalLeader(MultipartFile file) throws Exception ;

    List<Map<String,String>> searchManegeSupervisioList(Map<String,Object> param);

    List<SysUser> findByUserPhoneAndNotSelf(String userFlow,String userPhone);


    String saveFileToDirs(String oldFolderName, MultipartFile uploadFile, String supersivioSign);

    String eidtPassword(String userCode, String oldUserPasswd, String ideentityCheck, String userPasswd);

    List<ResSupervisioSubject> selectSubjectList(Map<String, Object> param);

    List<ResSupervisioSubject> selectGrobalSubjectList(Map<String, Object> param);

    ResSupervisioSubject selectSubjectByFlow(String subjectFlow);

    List<ResSupervisioSubjectUser> selectSupervisioUserListByFlow(String subjectFlow);

    List<SysSupervisioUser> queryUserList(String userName);

    int saveSubject(ResSupervisioSubject subject);

    int delSubjectUserBySubjectFlow(String subjectFlow);

    int saveSubjectUser(ResSupervisioSubjectUser subjectUser);

    List<String> selectSupervisioUserFlowListByFlow(String subjectFlow);

    List<ResSupervisioSubject> selectSubjectListByParam(Map<String, Object> param);


    ResEvaluationScore searchEvaluationOwnerScoreByItemId(ResEvaluationScore evaluationScore);

    int saveScore(ResEvaluationScore evaluationScore);

    List<ResEvaluationScore> searchEvaluationScore(ResEvaluationScore searchScore);


    ResSupervisioSubjectUser selectSubjectUserByFlow(String userFlow, String subjectFlow);

    List<ResSupervisioSubject> selectSubjectListByOrgFlow(String orgFlow, String currYear);

    int insertSubject(ResSupervisioSubject subject);

    List<ResSupervisioSubject> selectSubjectListBySpeId(String speId, String currYear);

    List<Map<String,Object>> searchSpeAssignBySpeIdAndYear(String speId, String currYear);

    int updateSubject(ResSupervisioSubject subject);

    List<ResSupervisioSubjectUser> selectSupervisioUserListByFlowAndUserLevelID(String subjectFlow);

    List<ResSupervisioSubject> selectBySubjectFlow(String subjectFlow);

    List<ResSupervisioSubjectUser> selectSubjectUserBysubjectActivitiFlows(String userFlow, String subjectActivitiFlows);

    ResSupervisioSubjectUser selectSysSupervisioUserByUserFlowAndSubjectFlow(String userFlow,String subjectFlow);

    List<ResSupervisioSubject> selectBySubjectActivitiFlows(String subjectActivitiFlows);

    ResSupervisioSubjectUser searchSubjectUser(String userFlow,String subjectFlow);

    int updateSubjectFeedback(Map<String, Object> param);

    Map<String, Object> selectAvgScoreBySubjectActivitiFlows(String subjectActivitiFlows);

    String searchSubjectNameNum(String subjectName);

    String searchSubExpertNum(String subjectFlow);

    List<ResSupervisioReport> selectAllName(String name);

    List<ResSupervisioReport>expertMajorBySubjectList(String subjectFlow,String subjectActivitiFlows);

    List<ResHospSupervSubject> readHospSuperBySubjectName(String subjectName);

    List<ResHospSupervSubject> selectHospSuperList(HashMap<String, Object> map);

    ResHospSupervSubject selectHospSupervisioBySubjectFlow(String subjectFlow);

    int delHospSupervisioBySubjectFlow(String subjectFlow);

    int updateHospSupervisioBySubjectFlow(ResHospSupervSubject resHospSupervSubject);

    List<ResHospSupervSubject> hospitalLeaderScoreList(Map<String, Object> map);

    List<ResSupervisioSubject> selectBySubject(ResSupervisioSubject subject);

    ResHospSupervSubject selectHospByActivityFlow(String activityFlow);

    List<Map<String,String>> searchEvaluationIndicators(ArrayList<String> list);


}
