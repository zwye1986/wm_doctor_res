package com.pinde.sci.model.njmuedu;

import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.SysUser;

public class EduUserCourseExt extends SysUser {

    private EduUser eduUser;
    private EduStudentCourse studentCourse;

    public EduUser getEduUser() {
        return eduUser;
    }

    public void setEduUser(EduUser eduUser) {
        this.eduUser = eduUser;
    }

    public EduStudentCourse getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(EduStudentCourse studentCourse) {
        this.studentCourse = studentCourse;
    }
}
