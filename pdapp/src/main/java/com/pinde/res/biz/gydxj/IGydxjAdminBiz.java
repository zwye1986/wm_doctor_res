package com.pinde.res.biz.gydxj;

import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.mo.EduTerm;
import com.pinde.sci.model.mo.NydsOfficialDoctor;

import java.util.List;
import java.util.Map;

public interface IGydxjAdminBiz {

    List<Map<String, Object>> searchEduUser(Map<String, Object> map);

    int backConfirm(String userFlow, String partId);

    List<Map<String, Object>> getGradeAuditStus(Map<String, Object> map);

    int auditGrade(String recordFlow,String auditStatusId);

    List<EduCourse> searchCourseList(Map<String, Object> map);

    List<EduTerm> searchTermList(Map<String, Object> map);

    List<NydsOfficialDoctor> searchTutorList(Map<String, Object> map);

    NydsOfficialDoctor searchTutorByFlow(String doctorFlow);

    int saveTutor(NydsOfficialDoctor tutor);

    EduCourse searchCourseByFlow(String courseFlow);

    int saveCourse(EduCourse course);

    List<EduStudentCourse> searchStuCourseListByFlow(String userFlow);

    int courseByYear(String courseSession);

    int termByYear(String sessionNumber);
}
