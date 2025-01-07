package com.pinde.sci.biz.jszy;

import com.pinde.core.model.*;
import com.pinde.core.model.JszyDoctorInfoExt;
import com.pinde.core.model.JszyResDoctorRecruitExt;

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
    List<com.pinde.core.model.ResDoctorRecruit> searchResDoctorRecruitList(com.pinde.core.model.ResDoctorRecruit recruit, String orderByClause);


    /**
     * 查询审核通过人数
     */
    int searchBasePassCount(com.pinde.core.model.ResDoctorRecruit recruit, List<String> orgFlowList);

    /**
     * 保存审核
     */
    int saveAuditRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs);

    List<JszyDoctorInfoExt> searchDoctorInfoResume(Map<String,Object> paramMap);

    int searchDoctorNum(com.pinde.core.model.ResDoctorRecruit recruit);

    /**
     * 更新医师走向
     *
     * @param recruitWithBLOBs
     * @return
     */
    int updateDoctorTrend(ResDoctorRecruitWithBLOBs recruitWithBLOBs);


    ResDoctorRecruitWithBLOBs readRecruit(String recruitFlow);

    List<JszyResDoctorRecruitExt> searchDoctorInfoExts(Map<String, Object> doctorRecruitMap);

    List<JszyResDoctorRecruitExt>  searchDoctorSkillAndTheoryScoreExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, String skillIsHege, List<String> docTypeList);

    /**
     * 技能成绩查询
     */
    List<JszyResDoctorRecruitExt>  searchDoctorSkillScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList);

    List<com.pinde.core.model.ResDoctorRecruit> readDoctorRecruits(com.pinde.core.model.ResDoctorRecruit recruit);

    List<ResDoctorRecruitWithBLOBs> searchRecruitWithBLOBs(com.pinde.core.model.ResDoctorRecruit recruit);

    List<ResDoctorRecruitWithBLOBs> readDoctorRecruitBlobs(com.pinde.core.model.ResDoctorRecruit recruit);

    int updateRecruit(ResDoctorRecruitWithBLOBs resDoctorRecruitWithBLOBs);

    /**
     * 获取该学员的培训信息审核状态
     */
    boolean getRecruitStatus(String doctorFlow);

    List<Map<String, Object>> searchJointOrgList();

    List<Map<String,Object>> doctorScoreQuery(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, List<String> jointOrgFlowList, String derateFlag, List<String> docTypeList, List<String> cerStatusList);


    List<Map<String,Object>> zlxytj(Map<String,Object> param);

    List<Map<String,Object>> zlxytj2(Map<String, Object> param);

    List<Map<String,Object>> zlxytjJoint(Map<String, Object> param);



    List<JszyResDoctorRecruitExt> searchDoctorCertificateList(Map<String, Object> param);


    /**
     * 在培学员统计
     * @param param
     * @return
     */
    List<Map<String,Object>> zpxytj(Map<String, Object> param);
}
