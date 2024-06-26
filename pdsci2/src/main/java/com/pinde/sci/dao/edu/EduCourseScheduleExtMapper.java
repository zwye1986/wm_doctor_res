package com.pinde.sci.dao.edu;

import com.pinde.sci.form.edu.CourseInfoForm;
import com.pinde.sci.form.edu.SysOrgExt;
import com.pinde.sci.model.edu.EduCourseScheduleExt;
import com.pinde.sci.model.mo.EduCourseSchedule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface EduCourseScheduleExtMapper {
    /**
     * 查询课程
     *
     * @param schedule
     * @return
     */
    BigDecimal selectAvgScore(EduCourseSchedule schedule);

    /**
     * 查询某老师教授章节各类信息
     *
     * @param paramMap
     * @return
     */
    CourseInfoForm countInfoOfTeacher(Map<String, Object> paramMap);

//	List<EduCourseScheduleExt> searchChapterScheduleMap(Map<String, Object> paramMap);

    /**
     * 查询该老师所授课程的所有学校
     *
     * @param paramMap
     * @return
     */
    List<SysOrgExt> selectOrgOfSchedule(Map<String, Object> paramMap);

    List<EduCourseScheduleExt> searchCourseSchedule(Map<String, Object> paramMap);

    /**
     * 查询
     * @param paramMap
     * @return
     */
//	List<EduCourseSchedule> searchEduCourseScheduleList(Map<String, Object> paramMap);
}
