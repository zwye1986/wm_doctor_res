package com.pinde.sci.biz.gzykdx;

import com.pinde.sci.form.gzykdx.GzykdxTeacherExtInfoForm;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.TeacherTargetApply;
import com.pinde.sci.model.mo.TeacherThesisDetail;

import java.util.List;
import java.util.Map;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是实现 XXX 功能
 * @since 2017/3/7
 */
public interface IGzykdxAuditManageBiz {

    /**
     * 导师查询考生录取情况
     * @param map
     * @return
     */
    List<Map<String, Object>> selectDoctorsRecruitList(Map<String, String> map);

    /**
     * 导师指标审核/导师申请审核列表
     * @param map
     * @return
     */
    List<Map<String,Object>> selectTeacherTargetApplyList(Map<String, String> map);
    List<Map<String,Object>> selectTeacherApplyListOrg(Map<String, String> map);

    /**
     * 查询招生指标
     * @param map
     * @return
     */
    List<Map<String,String>> selectRecruitNum(Map<String, String> map);

    /**
     * 查询已使用指标
     * @param map
     * @return
     */
    int selectAcademicRecruitSum(Map<String, String> map);
    int selectSpecializedRecruitSum(Map<String, String> map);

    /**
     * 一键提交导师申请
     * @param applyFlows
     * @return
     */
    int commitAllApply(List<String> applyFlows);

    List<TeacherTargetApply> selectApplyList(String orgFlow,String auditStatusId);
    List<TeacherTargetApply> teacherApplyList(TeacherTargetApply apply);

    /**
     * 批量修改申请信息
     * @param applyFlows
     * @return
     */
    int editApplies(List<String> applyFlows,TeacherTargetApply apply);

    /**
     * 录取缺额信息查询（二级单位）
     */
    List<Map<String,Object>> selectVacanciesOrg(Map<String,String> map);

    /**
     * 导师录取详情（二级单位）
     */
    List<Map<String,String>> teacherRecruitInfo(Map<String,String> map);
}
