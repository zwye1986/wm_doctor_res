package com.pinde.sci.model.xjgl;

import com.pinde.sci.model.mo.*;

import java.util.List;

public class XjEduScheduleClassExt extends EduScheduleClass {

    private static final long serialVersionUID = 400539611091936315L;
    private EduCourse course;
    private EduScheduleStudent scheduleStudent;
    private List<EduScheduleTeacher> scheduleTeacherList;

    public EduCourse getCourse() {
        return course;
    }

    public void setCourse(EduCourse course) {
        this.course = course;
    }

    public EduScheduleStudent getScheduleStudent() {
        return scheduleStudent;
    }

    public void setScheduleStudent(EduScheduleStudent scheduleStudent) {
        this.scheduleStudent = scheduleStudent;
    }

    public List<EduScheduleTeacher> getScheduleTeacherList() {
        return scheduleTeacherList;
    }

    public void setScheduleTeacherList(List<EduScheduleTeacher> scheduleTeacherList) {
        this.scheduleTeacherList = scheduleTeacherList;
    }
}
