package com.pinde.sci.form.gzykdx;

import com.pinde.sci.model.mo.DoctorTeacherRecruit;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.TeacherTargetApply;

public class GzykdxTeacherTargetApplyExportForm {

    private TeacherTargetApply teacherTargetApply;
    private String courseList;
    private String degreeTypeName;

    public String getDegreeTypeName() {
        return degreeTypeName;
    }

    public void setDegreeTypeName(String degreeTypeName) {
        this.degreeTypeName = degreeTypeName;
    }

    public TeacherTargetApply getTeacherTargetApply() {
        return teacherTargetApply;
    }

    public void setTeacherTargetApply(TeacherTargetApply teacherTargetApply) {
        this.teacherTargetApply = teacherTargetApply;
    }

    public String getCourseList() {
        return courseList;
    }

    public void setCourseList(String courseList) {
        this.courseList = courseList;
    }
}
