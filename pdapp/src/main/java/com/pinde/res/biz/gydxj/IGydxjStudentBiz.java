package com.pinde.res.biz.gydxj;

import com.pinde.res.model.gydxj.mo.SubmitApplyForm;
import com.pinde.res.model.gydxj.mo.XjEduUserExtInfoForm;
import com.pinde.res.model.gydxj.mo.XjEduUserForm;
import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface IGydxjStudentBiz {

    XjEduUserExtInfoForm parseExtInfoXml(String extInfoXml);

    int save(XjEduUserForm form) throws Exception;

    int updateUser(SysUser user);

    int editDoctor(ResDoctor doctor);

    int saveEduUser(EduUser eduUser);

    List<Map<String, Object>> searchApplyList(Map<String, Object> map);

    List<Map<String, Object>> classScheduleList(String classTime,String userFlow,String termFlow);

    List<Map<String,Object>> searchEduCourse(Map<String, Object> map);

    List<Map<String, Object>> searchChooseCourse(Map<String, Object> map);

    Map<String,Object> searchNeedData();

    List<Map<String,Object>> searchStuCourse(Map<String, Object> map);

    EduUserChangeApply readEduUserChangeApply(String recordFlow);

    List<EduCourse> searchStuCourseList(String userFlow);

    int saveApply(SubmitApplyForm form, EduUserChangeApply apply);

    List<String> queryExitCourseFlow(Map<String, Object> map);

    int saveStuCourse(EduCourse course, String userFlow, String studentPeriod, String roleId);

    int delStuCourse(Map<String, Object> map);

    List<EduScheduleClass> searchClassList(Map<String, Object> map);

    int signClass(Map<String, Object> map);

    int submitApply(String recordFlow);

    List<EduScheduleTeacher> searchTeacherByClass(String recordFlow);

    int confirmStatus(String userFlow, String partId);

    int searchEduStuCourse(String userFlow, String courseFlow);
}
