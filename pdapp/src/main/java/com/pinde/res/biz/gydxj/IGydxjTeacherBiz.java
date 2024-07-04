package com.pinde.res.biz.gydxj;

import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface IGydxjTeacherBiz {

    List<Map<String,Object>> searchCourse(String userFlow);

    List<EduCourse> qryCourseList(String courseCode);

    List<EduScheduleClass> scheduleCourseList(String courseFlow);

    int saveStuCourseGrade(EduStudentCourse course);

    List<Map<String,Object>> getApplyUserList(Map<String,Object> map);

    int auditApplyInfo(EduUserChangeApply apply);

    EduUser searEduUser(String barCode);

    List<Map<String,Object>> scheduleCourseList2(String courseFlow,String recordFlow);

    List<Map<String,Object>> qryCourseList2(Map<String,Object> map);

    List<Map<String,Object>> searchSignUserList(Map<String,Object> map);

    int signOutOpt(String userFlow,String recordFlow,String sign);
}
