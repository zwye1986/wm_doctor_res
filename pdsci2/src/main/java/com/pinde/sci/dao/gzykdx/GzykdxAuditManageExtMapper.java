package com.pinde.sci.dao.gzykdx;

import java.util.List;
import java.util.Map;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是实现 XXX 功能
 * @since 2017/3/7
 */
public interface GzykdxAuditManageExtMapper {
    /**
     * 导师查询考生录取情况
     * @param map
     * @return
     */
    List<Map<String,Object>> queryDoctorsRecruitList(Map<String, String> map);

    /**
     * 导师指标审核/导师申请审核列表
     * @param map
     * @return
     */
    List<Map<String,Object>> qureyTeacherTargetApplyList(Map<String, String> map);
    List<Map<String,Object>> qureyTeacherApplyListOrg(Map<String, String> map);

    /**
     * 查询招生指标
     * @param map
     * @return
     */
    List<Map<String,String>> queryRecruitNum(Map<String, String> map);

    /**
     * 查询已使用指标
     * @param map
     * @return
     */
    int queryAcademicRecruitSum(Map<String, String> map);
    int querySpecializedRecruitSum(Map<String, String> map);

    /**
     * 录取缺额信息查询（二级单位）
     */
    List<Map<String,Object>> queryVacanciesOrg(Map<String,String> map);
    List<Map<String,Object>> queryVacanciesOrgNew(Map<String,String> map);

    List<Map<String,String>> teacherRecruitInfo(Map<String,String> map);
}
