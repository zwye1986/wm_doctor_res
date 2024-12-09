package com.pinde.res.biz.stdp;

import com.pinde.core.model.*;
import com.pinde.res.ctrl.hbres.ActivityImageFileForm;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IResActivityBiz {

    List<Map<String,Object>> findActivityList(Map<String, String> param);

    List<Map<String,Object>> getTeacherActivityStatistics(Map<String, Object> param);

    Map<String,Object> readActivity(String activityFlow);

    List<Map<String,Object>> readActivityResults(String activityFlow, String searchStr);

    List<TeachingActivityEval> readActivityResultEvals(String resultFlow);

    TeachingActivityInfo readActivityInfo(String activityFlow);

    Map<String,Object> getDeptActivityStatisticsMap(String deptFlow, String startTime, String endTime);

    Map<String,Object> getTeacherActivityStatisticsMap(String deptFlow, String teacherFlow, String startTime, String endTime);

    TeachingActivityResult readRegistInfo(String activityFlow, String userFlow);

    int countByActivity (String activityFlow);

    int saveResult(TeachingActivityResult info,String userFlow);

    TeachingActivityResult readResult(String resultFlow);

    int checkJoin(String activityFlow, String userFlow);

    int checkJoin2(String activityFlow, String userFlow);

    int saveEvalInfo(List<TeachingActivityEval> evals, String resultFlow,String userFlow);

    int saveResultEval(TeachingActivityEval eval,String userFlow);

    List<Map<String, String>> parseImageXml(String content) throws DocumentException;

    List<Map<String,Object>> readActivityRegists(String activityFlow);

    List<Map<String,Object>> getDoctorActivityStatistics(Map<String, Object> parMp);

    int saveActivityInfo(TeachingActivityInfo activity, SysUser userinfo);
    int saveActivityInfo2(TeachingActivityInfo activity, SysUser userinfo);

    String jsresPowerCfgMap (String cfgCode);

    int saveActivityInfo2(TeachingActivityInfo activity, SysUser userinfo,String recordFlow);

    int checkTime(TeachingActivityInfo activity);

    int addActivityInfo(TeachingActivityInfo activity, SysUser userinfo, List<TeachingActivityTarget> targets, List<TeachingActivityFormValue> formValues);

    int addActivityInfo2(TeachingActivityInfo activity, SysUser userinfo, List<TeachingActivityTarget> targets, List<TeachingActivityFormValue> formValues,String recordFlow);

    int editActivityFormValue(TeachingActivityFormValue formValue, SysUser userinfo);

    Map<String,Object> findActivityTypeMap(Map<String, String> param);

    void deleteActivityImage(SysUser userinfo, String activityFlow, String imageFlow) throws DocumentException;

    void addActivityImage(ActivityImageFileForm form, SysUser userinfo);

    List<TeachingActivityInfo> checkJoinList(String activityFlow, String userFlow);

    List<Map<String,Object>> searchDeptByDoctor(String userFlow, String orgFlow);

    List<Map<String,Object>> readActivityResultsByType(String activityFlow, String searchStr, String typeId);

    List<Map<String,Object>> readActivityTargetEvals(String activityFlow);

    List<TeachingActivityTarget> readByOrg(String activityTypeId, String orgFlow);

    List<TeachingActivityInfo> getActivityListByRole(Map<String, Object> param);
}
