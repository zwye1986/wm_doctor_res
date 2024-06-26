package com.pinde.sci.dao.njmuedu;

import com.pinde.sci.form.njmuedu.CourseInfoForm;
import com.pinde.sci.form.njmuedu.SysOrgExt;
import com.pinde.sci.model.mo.EduCourseSchedule;
import com.pinde.sci.model.njmuedu.EduCourseScheduleExt;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface NjmuEduCourseScheduleExtMapper {
	/**
	 * 查询课程
	 * @param  课程流水号
	 * @return
	 */
	BigDecimal selectAvgScore(EduCourseSchedule schedule);
	/**
	 * 查询某老师教授章节各类信息
	 * @param paramMap
	 * @return
	 */
	CourseInfoForm countInfoOfTeacher(Map<String,Object> paramMap);
	
	List<EduCourseScheduleExt> searchChapterScheduleMap(Map<String, Object> paramMap);
	
	/**
	 * 查询该老师所授课程的所有学校
	 * @param paramMap
	 * @return
	 */
	List<SysOrgExt> selectOrgOfSchedule(Map<String, Object> paramMap);
	
	List<EduCourseScheduleExt> searchCourseSchedule(Map<String, Object> paramMap);
}
