package com.pinde.sci.form.gyxjgl;




public class XjCourseOutlineForm {

    //课程简介
    private String  courseSynopsis;
    //课程目标
    private String  courseTarget;
    //课程进度
    private String  courseTerm;

    //教学方式与要求
    private String  teachingMethod;

    //考核方式
    private String  assessType;

    //教材及主要参考书目
    private String  teachingMaterial;

    public String getCourseSynopsis() {
        return courseSynopsis;
    }

    public void setCourseSynopsis(String courseSynopsis) {
        this.courseSynopsis = courseSynopsis;
    }

    public String getCourseTarget() {
        return courseTarget;
    }

    public void setCourseTarget(String courseTarget) {
        this.courseTarget = courseTarget;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    public String getAssessType() {
        return assessType;
    }

    public void setAssessType(String assessType) {
        this.assessType = assessType;
    }

    public String getTeachingMaterial() {
        return teachingMaterial;
    }

    public void setTeachingMaterial(String teachingMaterial) {
        this.teachingMaterial = teachingMaterial;
    }
}
