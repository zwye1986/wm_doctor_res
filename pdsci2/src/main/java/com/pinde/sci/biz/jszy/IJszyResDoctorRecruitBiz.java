package com.pinde.sci.biz.jszy;

import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
import com.pinde.sci.model.jszy.JszyDoctorInfoLogExt;
import com.pinde.sci.model.jszy.JszyResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJszyResDoctorRecruitBiz {
    /**
     * 根据doctorFlow查询
     */
    ResDoctorRecruit readResDoctorRecruit(String recruitFlow);
    /**
     * 根据doctorFlow查询
     */
    public JsresRecruitDocInfoWithBLOBs readResDoctorRecruitInfo(String recruitFlow);

    /**
     * 查询当前机构下的医师
     */
    List<JszyResDoctorRecruitExt> resDoctorRecruitExtList(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> org);

    //增加人员类型筛选
    List<JszyResDoctorRecruitExt> resDoctorRecruitExtList(Map<String,Object> param);

    /**
     * 编辑
     */
    int editResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, ResDoctorRecruitWithBLOBs prevDocRec);

    /**
     * 保存
     */
    int saveDoctorRecruit(ResDoctorRecruitWithBLOBs doctorRecruitWithBLOBs);

    /**
     * 查询
     */
    List<ResDoctorRecruit> searchResDoctorRecruitList(ResDoctorRecruit recruit, String orderByClause);

    List<JsresRecruitDocInfo> searchResDoctorRecruitInfoList(ResDoctorRecruit recruit, String orderByClause);

    /**
     * 查询审核通过人数
     */
    int searchBasePassCount(ResDoctorRecruit recruit, List<String> orgFlowList);

    /**
     * 保存审核
     */
    int saveAuditRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs);

    List<JszyDoctorInfoExt> searchDoctorInfoResume(Map<String,Object> paramMap);

    int searchDoctorNum(ResDoctorRecruit recruit);

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
     */
    List<JszyResDoctorRecruitExt> searchTrainInfoList(List<String> jointOrgFlowList, ResDoctorRecruit resDoctorRecruit, SysUser user, String flag);

    ResDoctorRecruitWithBLOBs readRecruit(String recruitFlow);

    List<JszyResDoctorRecruitExt> searchDoctorInfoExts(Map<String, Object> doctorRecruitMap);

    /**
     * 理论成绩查询
     */
    List<JszyResDoctorRecruitExt>  searchDoctorScoreInfoExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList);
    List<JszyResDoctorRecruitExt>  searchDoctorSkillAndTheoryScoreExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, String skillIsHege, List<String> docTypeList);

    /**
     * 技能成绩查询
     */
    List<JszyResDoctorRecruitExt>  searchDoctorSkillScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList);

    /**
     * 公共科目成绩查询
     */
    List<JszyResDoctorRecruitExt>  searchDoctorPublicScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String flag, String scoreYear, String notAllQualified, String allQualified, List<String> docTypeList);

    List<ResDoctorRecruit> readDoctorRecruits(ResDoctorRecruit recruit);

    List<ResDoctorRecruitWithBLOBs> searchRecruitWithBLOBs(ResDoctorRecruit recruit);
    List<ResDoctorRecruitWithBLOBs> readDoctorRecruitBlobs(ResDoctorRecruit recruit);

    int updateRecruit(ResDoctorRecruitWithBLOBs resDoctorRecruitWithBLOBs);

    /**
     * 获取该学员的培训信息审核状态
     */
    boolean getRecruitStatus(String doctorFlow);

    List<Map<String, Object>> searchJointOrgList();

    List<Map<String,Object>> doctorScoreQuery(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String derateFlag, List<String> docTypeList, List<String> cerStatusList);

    int saveSecondSpe(ResDoctorRecruitWithBLOBs dBloBs);

    List<Map<String,Object>> zlxytj(Map<String,Object> param);

    List<Map<String,Object>> zlxytj2(Map<String, Object> param);

    List<Map<String,Object>> zlxytjJoint(Map<String, Object> param);

    List<Map<String,Object>> zlxytj3(Map<String, Object> param);

    void exportForDetailLog(List<JszyDoctorInfoLogExt> doctorInfoExts, HttpServletResponse response) throws Exception;

    List<JszyResDoctorRecruitExt> searchDoctorCertificateList(Map<String, Object> param);

    /**
     * 导入发证学员
     * @param file
     * @return
     */
    ExcelUtile importDoctorCertificateNoFromExcel2(MultipartFile file);

    List<Map<String,Object>> zlxytj4(Map<String, Object> param);

    List<Map<String,Object>> zlxytj5(Map<String, Object> param);

    ExcelUtile importGraduationPeople(MultipartFile file);

    /**
     * 在培学员统计
     * @param param
     * @return
     */
    List<Map<String,Object>> zpxytj(Map<String, Object> param);
    /**
     * 在培学员异动统计
     * @param param
     * @return
     */
    List<Map<String,Object>> zpxytjChanges(Map<String, Object> param);
    /**
     * 在培学员异动详情
     * @param param
     * @return
     */
    List<Map<String,Object>> zpxytjChangesDetail(Map<String, Object> param);
}
