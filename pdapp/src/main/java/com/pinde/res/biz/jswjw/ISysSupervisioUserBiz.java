package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ISysSupervisioUserBiz {

    SysSupervisioUser findBySuUserCode(String userCode);

    SysSupervisioUser findBySuUserCodeNew(String userCode);

    int editSupervisioUser(SysSupervisioUser user);

    List<SysSupervisioUser> findByUserPhoneAndNotSelf(String userFlow, String userPhone);

    int addSupervisioUser(SysSupervisioUser user);

    SysSupervisioUser findBySuUserFlow(String userFlow);

    SysSupervisioUser findByUserCode(String userCode);

    SysSupervisioUser findByUserPhone(String userPhone);

    List<Map<String,Object>> selectSubjectListByParam(Map<String, Object> param);
    List<Map<String,Object>> selectSubjectListByParamNew(Map<String, Object> param);

    ResSupervisioSubjectUser searchSubjectUser(String userFlow, String subjectFlow);

    String saveFileToDirs(String oldFolderName, MultipartFile uploadFile, String supersivioSign);

    int updateSubjectFeedback(Map<String, Object> param);

    List<ResSupervisioSubjectUser> selectSupervisioUserListByFlow(String subjectFlow);

    ResSupervisioSubject selectSubjectByFlow(String subjectFlow);

    ResSupervisioSubjectUser selectSubjectUserByFlow(String userFlow, String subjectFlow);

    List<ResEvaluationScore> searchEvaluationScore(ResEvaluationScore searchScore);

    ResEvaluationScore searchEvaluationOwnerScoreByItemId(ResEvaluationScore evaluationScore);

    int saveScore(ResEvaluationScore evaluationScore,String userFlow);

    String checkImg(MultipartFile uploadFile);

    String saveFileToDirs(String oldFolderName, MultipartFile uploadFile, String folderName, String orgFlow, String planYear, String itemId);

    int saveJsresSupervisioFile(JsresSupervisioFile jsresSupervisioFile,String userFlow);

    int deleteFileByPrimaryKey(String recordFlow);

    int saveSubjectUser(ResSupervisioSubjectUser subjectUser,String userFlow);

    int saveSubjectUserTwo(ResSupervisioSubjectUser subjectUser,String userFlow);

    int saveSubject(ResSupervisioSubject subject,String userFlow);
}
