package com.pinde.sci.dao.gyxjgl;


import com.pinde.sci.model.mo.EduScheduleStudent;
import com.pinde.sci.model.gyxjgl.XjEduScheduleClassExt;

import java.util.List;
import java.util.Map;

public interface GyXjEduTermExtMapper {

    List<XjEduScheduleClassExt> seachClassByMap(Map<String, Object> param);

    List<Map<String,String>> searchTeachersClassSchedule(Map<String,String> paramMap);

    List<Map<String,String>> getFirstClassByFlow(String recordFlow);

    List<Map<String,String>> getFirstClassOfGzByFlow(String recordFlow);

    //学生只能选本专业且已选过课程下的课
    List<XjEduScheduleClassExt> seachStudentClassByMap(Map<String ,Object> param);

    //存储学生选的课程
    int insertScheduleCourse(EduScheduleStudent scheduleStudent);

    //排课冲突检测：同一时间是否有相同老师的课
    List<Map<String,Object>> checkConflict(Map<String,Object> param);
    //排课冲突检测：同一时间是否已经有必选课
    List<Map<String,Object>> checkNeedCourse(Map<String,Object> param);
}
