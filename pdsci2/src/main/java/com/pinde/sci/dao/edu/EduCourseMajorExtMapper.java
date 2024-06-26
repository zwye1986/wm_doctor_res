package com.pinde.sci.dao.edu;

import com.pinde.sci.form.edu.MajorCourseForm;
import com.pinde.sci.model.edu.EduCourseMajorExt;
import com.pinde.sci.model.mo.EduCourse;

import java.util.List;
import java.util.Map;

public interface EduCourseMajorExtMapper {
    List<MajorCourseForm> searchIncludeCourseMajor(Map<String, Object> paramMap);

    List<EduCourse> selectCourseNotIncludeMajor(Map<String, Object> paramMap);

    List<EduCourseMajorExt> selectCourseByMajor(Map<String, Object> paramMap);

    List<String> searchRecommendYear(Map<String, String> paramMap);

    List<EduCourseMajorExt> searchCourseMajorExtList(EduCourseMajorExt courseMajorExt);

    List<EduCourseMajorExt> searchReplenishCourseMajorExtList(Map<String, Object> paramMap);
}
