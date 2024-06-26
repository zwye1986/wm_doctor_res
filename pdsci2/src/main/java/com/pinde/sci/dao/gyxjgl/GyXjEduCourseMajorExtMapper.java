package com.pinde.sci.dao.gyxjgl;

import com.pinde.sci.form.gyxjgl.XjMajorCourseForm;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.gyxjgl.XjEduCourseMajorExt;

import java.util.List;
import java.util.Map;

public interface GyXjEduCourseMajorExtMapper {
    List<XjMajorCourseForm> searchIncludeCourseMajor(Map<String, Object> paramMap);

    List<EduCourse> selectCourseNotIncludeMajor(Map<String, Object> paramMap);

    List<XjEduCourseMajorExt> selectCourseByMajor(Map<String, Object> paramMap);

    List<String> searchRecommendYear(Map<String, String> paramMap);

    List<XjEduCourseMajorExt> searchCourseMajorExtList(XjEduCourseMajorExt courseMajorExt);

    List<XjEduCourseMajorExt> searchCourseExtList(XjEduCourseMajorExt courseMajorExt);

    List<XjEduCourseMajorExt> searchReplenishCourseMajorExtList(Map<String, Object> paramMap);

    List<EduCourse> selectCourseAllMajor(Map<String, Object> paramMap);
}
