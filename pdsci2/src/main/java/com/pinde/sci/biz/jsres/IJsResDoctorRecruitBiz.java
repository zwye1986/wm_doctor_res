package com.pinde.sci.biz.jsres;

import com.pinde.core.model.*;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.form.jsres.JykhInfoForm;
import com.pinde.core.model.JsDoctorInfoExt;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJsResDoctorRecruitBiz {
    /**
     * 根据doctorFlow查询
     */
    ResDoctorRecruit readResDoctorRecruit(String recruitFlow);

    /**
     * 查询当前机构下的医师
     */
    List<JsResDoctorRecruitExt> resDoctorRecruitExtList(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> org, List<String> docTypeList);
    List<JsResDoctorRecruitExt> resDoctorRecruitExtList1(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> org,List<String> docTypeList,List<String> sessionNumbers);
    List<JsResDoctorRecruitExt> resDoctorRecruitExtNew(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> jointOrgList,
                                                       List<String> docTypeList,List<String> sessionNumbers,String joinOrgFlow,String isJointOrg,String isArmy);
    List<JsResDoctorRecruitExt> resDoctorRecruitExtList2(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> org,List<String> docTypeList,List<String> sessionNumbers,String sortType,String scoreType);
    List<JsResDoctorRecruitExt> resDoctorRecruitExtList3(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> org,List<String> docTypeList,List<String> sessionNumbers,String sortType,String scoreType);
    List<JsResDoctorRecruitExt> resDoctorRecruitExtList3New(ResDoctorRecruit resDoctorRecruit);
    List<JsResDoctorRecruitExt> resDoctorRecruitExtNew2(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> jointOrgList,
                                                       List<String> docTypeList,List<String> sessionNumbers,String joinOrgFlow,String isJointOrg,String isArmy);
    /**
     * 编辑
     *
     * @param recruitWithBLOBs
     * @param prevDocRec
     * @return
     */
    int editResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, ResDoctorRecruitWithBLOBs prevDocRec);

    /**
     * 保存
     *
     * @param doctorRecruitWithBLOBs
     * @return
     */
    int saveDoctorRecruit(ResDoctorRecruitWithBLOBs doctorRecruitWithBLOBs);

    int queryCountByDoctFlow(String doctorFlow);

    int resDoctorRecruitRefresh(com.pinde.core.model.ResDoctorRecruit recruit);

    /**
     * 查询
     *
     * @param recruit
     * @return
     */
    List<com.pinde.core.model.ResDoctorRecruit> searchResDoctorRecruitList(com.pinde.core.model.ResDoctorRecruit recruit, String orderByClause);

    /**
     * 查询审核通过人数
     *
     * @param recruit
     * @return
     */
    int searchBasePassCount(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlowList, String year);

    /**
     * 保存学员审核成绩
     * @param recruitWithBLOBs
     * @return
     */
    int saveAuditResult(ResDoctorRecruitWithBLOBs recruitWithBLOBs);

    /**
     * 修改学员成绩
     * @param recruitWithBLOBs
     * @return
     */
    int modifyResult(ResDoctorRecruitWithBLOBs recruitWithBLOBs);

    /**
     * 保存报考信息审核
     * @param recruitWithBLOBs
     * @return
     */
    int saveAuditRecruitInfo(ResDoctorRecruitWithBLOBs recruitWithBLOBs);
    /**
     * 保存审核
     *
     * @param recruitWithBLOBs
     * @return
     */
    int saveAuditRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs);

    List<JsDoctorInfoExt> searchDoctorInfoResume(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList);
    List<JsDoctorInfoExt> searchDoctorInfoResume1(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList, List<String>trainYearList,List<String> sessionNumbers,String baseFlag);
    List<JsDoctorInfoExt> searchDoctorInfoResume3(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList, List<String>trainYearList,List<String> sessionNumbers,String baseFlag,String isPostpone,String isArmy, String workOrgId,String workOrgName);


    int searchDoctorNum(com.pinde.core.model.ResDoctorRecruit recruit);

    /**
     * 更新医师走向
     *
     * @param recruitWithBLOBs
     * @return
     */
    int updateDoctorTrend(ResDoctorRecruitWithBLOBs recruitWithBLOBs);

    int searchCountByCondition(ResDoctorRecruitWithBLOBs recruitWithBLOBs, String flag);

    /**
     * 查询培训记录信息
     *
     * @param jointOrgFlowList
     * @param resDoctorRecruit
     * @param user
     * @param flag
     * @return
     */
    List<JsResDoctorRecruitExt> searchTrainInfoList(List<String> jointOrgFlowList, ResDoctorRecruit resDoctorRecruit, SysUser user, String flag);

    ResDoctorRecruitWithBLOBs readRecruit(String recruitFlow);

    List<JsResDoctorRecruitExt> searchDoctorInfoExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList);
    List<JsResDoctorRecruitExt> searchDoctorInfoExts1(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg,
                                                      List<String> jointOrgFlowList, String flag, List<String> docTypeList,List<String> trainYearList,
                                                      List<String> sessionNumbers, String baseFlag,String userOrgFlow, String newFlag,String isJointOrg);
    List<JsResDoctorRecruitExt> searchDoctorInfoExts2(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg,
                                                      List<String> jointOrgFlowList, String flag, List<String> docTypeList,List<String> trainYearList,
                                                      List<String> sessionNumbers, String baseFlag,String userOrgFlow, String newFlag,String isJointOrg,String isPostpone,String isArmy,String workOrgId,String workOrgName);

    /**
     * 理论成绩查询
     * @return
     */
    List<JsResDoctorRecruitExt>  searchDoctorScoreInfoExts( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  sysUser,SysOrg  org,List<String>  jointOrgFlowList,  String flag , String  scoreYear, String  isHege,List<String> docTypeList,String hegeScore,String testId);
    List<JsResDoctorRecruitExt>  searchDoctorSkillAndTheoryScoreExts( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  sysUser,SysOrg  org,List<String>  jointOrgFlowList,  String flag , String  scoreYear, String  isHege,String  skillIsHege,List<String> docTypeList,String hegeScore);
    List<JsResDoctorRecruitExt>  searchDoctorSkillAndTheoryScoreExts1( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  sysUser,SysOrg  org,List<String>  jointOrgFlowList,  String flag , String  theroyScoreYear, String  skillScoreYear,String  isHege,String  skillIsHege,List<String> docTypeList,
                                                                       String hegeScore,String testId,String isMakeUp,String trainingTypeId,String scoreTestId,String roleFlag);
/**
     * 技能成绩查询
     * @return
     */
    List<JsResDoctorRecruitExt>  searchDoctorSkillScore( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  sysUser,SysOrg  org,List<String>  jointOrgFlowList,  String flag , String  scoreYear, String  isHege,List<String> docTypeList,String testId);
    /**
     * 理论成绩查询
     * @return
     */
    List<JsResDoctorRecruitExt>  searchDoctorTheoryScore( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  sysUser,SysOrg  org,List<String>  jointOrgFlowList,  String flag , String  scoreYear, String  isHege,List<String> docTypeList,String testId);

    List<JsResDoctorRecruitExt> searchDoctorPublicScore1(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String flag, String scoreYear, String notAllQualified, String allQualified, List<String> docTypeList, List<String> sessionNumbers);


    List<com.pinde.core.model.ResDoctorRecruit> readDoctorRecruits(com.pinde.core.model.ResDoctorRecruit recruit);

    List<ResDoctorRecruitWithBLOBs> searchRecruitWithBLOBs(com.pinde.core.model.ResDoctorRecruit recruit);

    int updateRecruit(ResDoctorRecruitWithBLOBs resDoctorRecruitWithBLOBs);

    /**
     * 获取该学员的培训信息审核状态
     *
     * @param doctorFlow
     * @return
     */
    boolean getRecruitStatus(String doctorFlow);

    List<Map<String, Object>> searchJointOrgList();

    List<Map<String,Object>> doctorScoreQuery(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String derateFlag, List<String> docTypeList, List<String> cerStatusList);


    List<String> searchDoctorNum(List<String> doctorTypeId,String doctorStatusId,String catSpeId);
    List<String> searchDoctorNum2(List<String> doctorTypeId,String catSpeId);
    String searchDoctorNumYear(String doctorStatusId,String catSpeId);
    String searchDoctorNumYear2(String catSpeId);

    List<Map<String,Object>> searchDoctorTrainingNum(String sessionNumber,String statisticsType,String catSpeId);
    List<Map<String,Object>> searchDoctorTrainingNumWithNotJoin(String sessionNumber,String statisticsType,String catSpeId);
    List<Map<String,Object>> searchSpeDoctorTrainingNum(String sessionNumber,String statisticsType,String catSpeId);

    List<Map<String,Object>> searchDoctorRecruitNum(String sessionNumber,String catSpeId);
    List<Map<String,Object>> searchDoctorRecruitNumWithNotJoin(String sessionNumber,String catSpeId);
    List<Map<String,Object>> searchSpeDoctorRecruitNum(String sessionNumber,String catSpeId);

    /****************************高******校******管******理******员******角******色************************************************/

    List<JsResDoctorRecruitExt> searchDoctorInfoExtsForUni(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, String derateFlag, List<String> docTypeList,SysUser currUser);

    List<Map<String,Object>> doctorScoreQueryForUni(Map<String, Object> param);

    List<JsResDoctorRecruitExt> searchDoctorSkillScoreForUni(Map<String, Object> param);
    List<JsResDoctorRecruitExt> searchDoctorSkillScoreForUni1(Map<String, Object> param);

    List<JsDoctorInfoExt> searchDoctorInfoResumeForUni(com.pinde.core.model.ResDoctorRecruit recruit, ResDoctor doctor, SysUser sysUser, SysOrg org, String derateFlag, List<String> docTypeList, SysUser exSysUser);

    List<Map<String,String>> searchOrgNotUseAppDoc(Map<String, Object> param);
    List<Map<String,String>> findSchArrengResultByDoctorAndYearMonth(Map<String, Object> param);

    /**
     * 学员手册查询
     * @param resDoctorRecruit
     * @param doctor
     * @param docTypeList
     * @return
     */
    List<JsResDoctorRecruitExt> searchDoctorManualForUni(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, List<String> docTypeList,SysUser currUser);

    /**
     * 最早与最晚开始培训时间
     * @param doctorFlow
     * @param rotationFlow
     * @return
     */
    Map<String,Object> getDocProcessArea(String doctorFlow, String rotationFlow);
    //2017结业考核人员导出所需信息
    List<JykhInfoForm> graduateExtList(Map<String, Object> parMap) throws Exception;

    void createCertificateNo(List<com.pinde.core.model.ResDoctorRecruit> resDoctorRecruits);

    void createNotCertificateNo(List<com.pinde.core.model.ResDoctorRecruit> resDoctorRecruits);

    /**
     * 证书查询
     * @param param
     * @return
     */
    List<JsResDoctorRecruitExt> searchDoctorCertificateList(Map<String, Object> param);
    List<JsResDoctorRecruitExt> searchDoctorCertificateList2(Map<String, Object> param);
    /**
     * 导入发证学员
     * @param file
     * @return
     */
    ExcelUtile importDoctorCertificateNoFromExcel(MultipartFile file);
    /**
     * 导入发证学员
     * @param file
     * @return
     */
    ExcelUtile importDoctorCertificateNoFromExcel2(MultipartFile file);

    List<Map<String,Object>> zlxytj(String sessionNumber);

    List<Map<String,Object>> zlxytj2(Map<String, Object> param);

    List<Map<String,Object>> zlxytjJoint(Map<String, Object> param);

    HSSFWorkbook createCycleResultsByDoc(String doctorFlow, String roleId, String schStartDate, String schEndDate);
    void exportForDetailByOrg(List<JsDoctorInfoExt> doctorInfoExts,String titleYsar, HttpServletResponse response) throws Exception;

    void exportRecruitList(List<JsResDoctorRecruitExt> recruitList, HttpServletResponse response) throws Exception;



    List<JsResDoctorRecruitExt> searchResDoctorRecruitExtList1(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, List<String> orgFlowList, List<String> docTypeList, List<String> sessionNumbers, String isJointOrg);

    List<JsDoctorInfoExt> resDoctorInfoExtListNew(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, List<String> orgFlowList, List<String> docTypeList, List<String> sessionNumbers, String isJointOrg);

    List<JsResDoctorRecruitExt> searchNotCertificateList(Map<String, Object> param);

    int createCertificate(ResDoctorRecruit old,String tabTag);

    List<com.pinde.core.model.ResDoctorRecruit> searchResDoctorRecruitListByFlows(List<String> recruitFlows);

    int findSignupCount(String doctorFlow, String year);

    List<ResDoctorRecruitWithBLOBs> readDoctorRecruitBlobs(com.pinde.core.model.ResDoctorRecruit recruit);
}
