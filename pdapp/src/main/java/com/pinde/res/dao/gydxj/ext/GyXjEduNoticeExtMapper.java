package com.pinde.res.dao.gydxj.ext;

import com.pinde.sci.model.mo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GyXjEduNoticeExtMapper {

    List<EduTeachingnotice> searchByRoles(@Param(value = "userFlow") String userFlow, @Param(value = "infoTypeId") String infoTypeId);

    List<Map<String, Object>> searchEduUser(Map<String, Object> map);

    int backConfirm(@Param(value = "userFlow") String userFlow, @Param(value = "partId") String partId);

    List<Map<String, Object>> searchApplyList(Map<String, Object> map);

    List<Map<String, Object>> classScheduleList(@Param(value = "classTime") String classTime,@Param(value = "userFlow") String userFlow,@Param(value = "termFlow") String termFlow);

    List<Map<String,Object>> getGradeAuditStus(Map<String, Object> parMp);

    int auditGrade(@Param(value = "recordFlow") String recordFlow,@Param(value = "auditStatusId") String auditStatusId);

    List<EduCourse> searchCourseList(Map<String, Object> map);

    List<EduTerm> searchTermList(Map<String, Object> map);

    List<NydsOfficialDoctor> searchTutorList(Map<String, Object> map);

    List<Map<String,Object>> searchEduCourse(Map<String, Object> map);

    List<Map<String, Object>> searchChooseCourse(Map<String, Object> map);

//    Map<String,Object> searchNeedData();

    List<Map<String,Object>> searchStuCourse(Map<String, Object> map);

    List<EduStudentCourse> searchStuCourseListByFlow(@Param(value = "userFlow") String userFlow);

    List<Map<String, Object>> searchCourse(@Param("userFlow") String userFlow);

    List<EduCourse> qryCourseList(@Param("courseCode") String courseCode);

    List<EduScheduleClass> scheduleCourseList(@Param("courseFlow") String courseFlow);

    List<EduCourse> searchStuCourseList(@Param("userFlow") String userFlow);

    List<Map<String, Object>> getApplyUserList(Map<String, Object> map);

    List<String> queryExitCourseFlow(Map<String, Object> map);

    List<EduUser> searEduUser(@Param("barCode") String barCode);

    int delStuCourse(Map<String, Object> parMp);

    List<Map<String, Object>> scheduleCourseList2(@Param("courseFlow") String courseFlow,@Param("recordFlow") String recordFlow);

    List<Map<String, Object>> qryCourseList2(Map<String, Object> map);

    List<Map<String, Object>> searchSignUserList(Map<String, Object> map);

    int courseByYear(@Param("courseSession") String courseSession);

    int termByYear(@Param("sessionNumber") String sessionNumber);
}
