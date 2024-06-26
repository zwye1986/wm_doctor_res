package com.pinde.sci.dao.xjgl;

import com.pinde.sci.form.xjgl.XjMajorCourseForm;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.xjgl.XjEduCourseMajorExt;

import java.util.List;
import java.util.Map;

public interface XjEduCourseMajorExtMapper {
    List<XjMajorCourseForm> searchIncludeCourseMajor(Map<String, Object> paramMap);

    List<EduCourse> selectCourseNotIncludeMajor(Map<String, Object> paramMap);

    List<XjEduCourseMajorExt> selectCourseByMajor(Map<String, Object> paramMap);

    List<String> searchRecommendYear(Map<String, String> paramMap);

    List<XjEduCourseMajorExt> searchCourseMajorExtList(XjEduCourseMajorExt courseMajorExt);

    List<XjEduCourseMajorExt> searchCourseExtList(XjEduCourseMajorExt courseMajorExt);

    List<XjEduCourseMajorExt> searchReplenishCourseMajorExtList(Map<String, Object> paramMap);

    List<EduCourse> selectCourseAllMajor(Map<String, Object> paramMap);
}
