package com.pinde.sci.model.xjgl;

import com.pinde.sci.model.mo.*;

public class XjEduStudentCourseExt extends EduStudentCourse {

    private static final long serialVersionUID = 400539611091936315L;
    private EduCourse course;
    private EduCourseMajor courseMajor;
    private SysUser sysUser;
    private EduUser eduUser;
    private ResDoctor resDoctor;


    public EduCourseMajor getCourseMajor() {
        return courseMajor;
    }

    public void setCourseMajor(EduCourseMajor courseMajor) {
        this.courseMajor = courseMajor;
    }

    public EduCourse getCourse() {
        return course;
    }

    public void setCourse(EduCourse course) {
        this.course = course;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public EduUser getEduUser() {
        return eduUser;
    }

    public void setEduUser(EduUser eduUser) {
        this.eduUser = eduUser;
    }

    public ResDoctor getResDoctor() {
        return resDoctor;
    }

    public void setResDoctor(ResDoctor resDoctor) {
        this.resDoctor = resDoctor;
    }

}
