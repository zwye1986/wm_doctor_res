package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJsResActivityBiz {

    List<Map<String,Object>> findActivityList(Map<String, String> param);

    List<Map<String,Object>> activityStatisticsList(Map<String, String> param);

    List<Map<String,Object>> activityStatisticsExportList(Map<String, String> param);

    List<Map<String,Object>> queryJoin(String activityFlow,String orgName);

    List<Map<String,Object>> supervisioFindActivityList(Map<String, Object> param);

    List<Map<String,Object>> getTeacherActivityStatistics(Map<String, Object> param);

    List<Map<String,Object>> getTeacherActivityStatistics2(Map<String, Object> param);

    Map<String,Object> readActivity(String activityFlow);

    List<Map<String,Object>> readActivityResults(String activityFlow);

    List<TeachingActivityEval> readActivityResultEvals(String resultFlow);

    TeachingActivityInfo readActivityInfo(String activityFlow);

    int saveActivity(TeachingActivityInfo info);

    String editActivity(TeachingActivityInfo activity, MultipartFile file, String isRe, String isRes,String data,String role);

    Map<String,Object> getDeptActivityStatisticsMap(String deptFlow, String startTime, String endTime);

    List<Map<String,Object>> getDeptCountActivityStatisticsList(String orgFlow,String deptFlow, String startTime, String endTime,String notNull);

    Map<String,Object> getTeacherActivityStatisticsMap(String deptFlow,String teacherFlow, String startTime, String endTime);

    List<Map<String,String>> getTeacherActivityStatisticsReport(Map<String, Object> param);

    String getRealitSpeaker(String userFlow,String deptFlow,String orgFlow);

    String getRealitSpeaker2(String userFlow,String deptFlow,String orgFlow, String startTime, String endTime);

    Map<String,Object> getDoctorActivityStatisticsMap(String doctorFlow, String startTime, String endTime, String isDept, String userFlow);

    TeachingActivityResult readRegistInfo(String activityFlow, String userFlow);

    int saveRegist(TeachingActivityResult info);

    int saveResult(TeachingActivityResult info);

    TeachingActivityResult readResult(String resultFlow);

    int countByActivity (String activityFlow);

    int checkJoin(String activityFlow, String userFlow);

    int saveEvalInfo(List<TeachingActivityEval> evals, String resultFlow);

    int saveResultEval(TeachingActivityEval eval);

    List<Map<String, String>> parseImageXml(String content) throws DocumentException;

    Map<String,String> activityImg(String activityFlow, MultipartFile checkFile, String fileAddress);

    List<Map<String,Object>> readActivityRegists(String activityFlow);

    String saveActivityFile(String fileFlow, MultipartFile file);

    List<Map<String,Object>> getDoctorActivityStatistics(Map<String, Object> parMp);

    List<Map<String,Object>> getResDoctorActivityStatistics(Map<String, Object> parMp);

    List<TeachingActivityInfo> checkJoinList(String activityFlow, String userFlow);

    int editActivityFormValue(TeachingActivityFormValue formValue);

    List<Map<String,Object>> findActivityList2(Map<String, String> param);

    List<Map<String, Object>> getActivitys(Map<String, Object> param);

    List<Map<String,Object>> getDeptActivityCountMap(Map<String,Object> param);

    List<Map<String, Object>> readActivityResults2(Map<String, Object> param2);

    Map<String,Object> getDeptActivityMap(String trainingSpeId,String startTime,String endTime);

    List<Map<String,String>> searchSpeDept(String orgFlow,String trainingSpeId);

    List<Map<String, Object>> getActivityCountMap(Map<String, Object> param);

    List<Map<String, Object>> readResults(Map<String, Object> param);

    List<TeachActivityCfg> searchActivityCfgs(String roleFlow, String orgFlow);

    String editActivityNew(TeachingActivityInfo activity, String isRes, List<MultipartFile> fileList, String data, String[] fileFlow) throws ParseException;

    String editActivityNew2(TeachingActivityInfo activity, String isRes, Map<String,List<MultipartFile>> fileMap, String data, String[] fileFlow) throws ParseException;

    String editActivityFiles(String activityFlow, List<MultipartFile> fileList, String[] fileFlow);

    String editActivityFiles(String activityFlow, Map<String,List<MultipartFile>> fileMap, String[] fileFlow);
}
