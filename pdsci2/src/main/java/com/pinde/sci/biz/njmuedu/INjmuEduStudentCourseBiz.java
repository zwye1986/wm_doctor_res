package com.pinde.sci.biz.njmuedu;

import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.njmuedu.EduUserExt;

import java.util.List;
import java.util.Map;

public interface INjmuEduStudentCourseBiz {

    /**
     * 修改学生选课情况
     *
     * @param eduStudentCourse
     * @return
     */
    int edit(EduStudentCourse eduStudentCourse);

    /**
     * 查询学生选择的课程的详细信息
     *
     * @param eduUserExtList
     * @return
     */
    Map<String, Map<String, Object>> searchStudentChooseCourses(List<EduUserExt> eduUserExtList);

    /**
     * 查询选修了某节课的学生的学分
     *
     * @param courseFlow
     * @return
     */
    Map<String, Object> searchCourseCreditForm(String courseFlow);

    /**
     * 插入必修课
     *
     * @return
     */
    int insertRequireCourse(String userFlow);


    /**
     * 查询
     *
     * @param studentCourse
     * @return
     */
    List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse);

    /**
     * 计算该课程总成绩
     *
     * @param courseFlow
     * @param chapterFlow
     * @param userFlow
     * @return
     */
    double calculateCourseGrade(String courseFlow, String chapterFlow, String userFlow);

    List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse, List<String> courseFlowList);

    /**
     * 计数
     *
     * @param courseFlow
     * @param studyStatusIdList
     * @return
     */
    int getStudentCourseCount(String courseFlow, List<String> studyStatusIdList);
}
