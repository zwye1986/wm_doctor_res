package com.pinde.sci.dao.res;

import com.pinde.sci.form.res.OneCourseCreditForm;
import com.pinde.sci.model.res.EduStudentCourseExt;

import java.util.List;
import java.util.Map;

public interface ResEduStudentCourseExtMapper {
	/**
	 * 查询选修了某节课的学生的学分
	 * @param paramMap
     * @return
     */
	List<OneCourseCreditForm> searchCourseCreditForm(Map<String, Object> paramMap);

	List<EduStudentCourseExt> searchStudentCourse(Map<String, Object> paramMap);
}
