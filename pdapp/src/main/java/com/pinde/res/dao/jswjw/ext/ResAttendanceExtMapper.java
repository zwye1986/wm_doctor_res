package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.form.JsResAttendanceExt;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/8.
 */
public interface ResAttendanceExtMapper {

    //江苏西医
    List<JsResAttendanceExt> searchAttendanceList(Map<String, String> paramMap);
    //江苏西医
    List<JsResAttendanceExt> searchAttendanceList2(Map<String, Object> paramMap);
    //江苏西医
    List<Map<String,String>> attendanceList2(Map<String, Object> paramMap);
    List<Map<String,String>> attendanceList3(Map<String, Object> paramMap);

//    //江苏中医
//    List<JszyResAttendanceExt> searchJszyAttendanceList(Map<String, String> paramMap);
//
//    //湖北过程
//    List<ResAttendanceExt> searchResAttendanceList(Map<String, Object> paramMap);
//    //湖北过程
//    List<Map<String,String>> searchResAttendanceList2(Map<String, Object> paramMap);

    /****************************高******校******管******理******员******角******色************************************************/

    List<JsResAttendanceExt> searchAttendanceListForUni(Map<String, String> map);

    List<Map<String,String>> exportaAttendanceList2(Map<String, Object> map);

    List<Map<String,String>> exportaAttendanceList3(Map<String, Object> map);
    List<Map<String,String>> exportaAttendanceList4(Map<String, Object> map);

    List<Map<String,String>> exportResAttendanceList2(Map<String, Object> map);

    List<Map<String,String>> exportResAttendanceList3(Map<String, Object> map);

    List<Map<String,String>> signinlist(Map<String, Object> map);
}
