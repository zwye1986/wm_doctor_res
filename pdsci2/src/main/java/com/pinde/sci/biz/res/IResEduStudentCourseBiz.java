package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.EduStudentCourseExt;

import java.util.List;
import java.util.Map;

public interface IResEduStudentCourseBiz {
    /**
     * 修改学生选课情况
     *
     * @param eduStudentCourse
     * @return
     */
    int save(EduStudentCourse eduStudentCourse);

    /**
     * 查询选修了某节课的学生的学分
     *
     * @param courseFlow
     * @return
     */
    Map<String, Object> searchCourseCreditForm(String courseFlow);

    /**
     * 查询学生选课记录
     *
     * @param studentCourse
     * @return
     */
    List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse);

    List<EduStudentCourse> searchStudentCourseList(List<String> courseFlowList);

    List<EduStudentCourseExt> searchStudentCourse(SysUser sysUser,
                                                  EduUser eduUser, ResDoctor doctor, EduStudentCourse studentCourse);

}
