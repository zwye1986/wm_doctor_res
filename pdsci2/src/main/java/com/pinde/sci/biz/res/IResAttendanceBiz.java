package com.pinde.sci.biz.res;

import com.pinde.sci.model.jsres.JsResAttendanceExt;
import com.pinde.sci.model.jszy.JszyResAttendanceExt;
import com.pinde.sci.model.mo.JsresAttendance;
import com.pinde.sci.model.mo.JsresAttendanceDetail;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.ResAttendanceExt;

import java.util.List;
import java.util.Map;

/**
 * Created by czz on 2016/10/8.
 */
public interface IResAttendanceBiz {

    /**
     * 江苏西医
     */
    List<JsResAttendanceExt> searchAttendanceList(Map<String, String> map);
    List<JsResAttendanceExt> searchAttendanceList2(Map<String, Object> map);

    /*
     * 江苏中医
     */
    List<JszyResAttendanceExt> searchJszyAttendanceList(Map<String, String> map);
    /*
    * 湖北过程
    */
    List<ResAttendanceExt> searchResAttendanceList(Map<String, Object> map);
    /**
     *
     * @param jsresAttendance
     */
    void saveJsresAttendance(JsresAttendance jsresAttendance);

    /**
     * 根据AttendanceFlow查找AttendanceDetail
     * @param jsResAttendanceFlow
     * @return
     */
    List<JsresAttendanceDetail> searchJsresAttendanceDetailByAttendanceFlow(String jsResAttendanceFlow);

    /**
     * 根据AttendanceFlows查找AttendanceDetail
     * @param jsResAttendanceFlows
     * @return
     */
    List<JsresAttendanceDetail> searchJsresAttendanceDetailByAttendanceFlows(List<String> jsResAttendanceFlows);

    /**
     * 根据主键查询Attendance
     * @param attendanceFlow
     * @return
     */
    JsresAttendance searchJsresAttendanceByAttendanceFlow(String attendanceFlow);

    /**
     * 根据主键查询SysUser
     * @param doctorFlow
     * @return
     */
    SysUser searchSysUserByUserFlow(String doctorFlow);

    /****************************高******校******管******理******员******角******色************************************************/

    List<JsResAttendanceExt> searchAttendanceListForUni(Map<String, String> beMap);

    List<Map<String,String>> attendanceList2(Map<String, Object> beMap);
    List<Map<String,String>> attendanceList3(Map<String, Object> beMap);

    List<Map<String,String>> exportaAttendanceList2(Map<String, Object> beMap);

    List<Map<String,String>> exportaAttendanceList3(Map<String, Object> beMap);
    List<Map<String,String>> exportaAttendanceList4(Map<String, Object> beMap);

    JsresAttendance getJsresAttendance(String attendDate, String doctorFlow);

    List<Map<String,String>> signinlist(Map<String, Object> beMap);

    List<Map<String,String>> searchResAttendanceList2(Map<String, Object> beMap);
    List<Map<String,String>> exportResAttendanceList2(Map<String, Object> beMap);
    List<Map<String,String>> exportResAttendanceList3(Map<String, Object> beMap);
}
