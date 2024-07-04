package com.pinde.sci.model.mo;

public class EduCourseWithBLOBs extends EduCourse {
    private String courseIntro;

    private String outlineContent;

    private String teachingContent;

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public String getOutlineContent() {
        return outlineContent;
    }

    public void setOutlineContent(String outlineContent) {
        this.outlineContent = outlineContent;
    }

    public String getTeachingContent() {
        return teachingContent;
    }

    public void setTeachingContent(String teachingContent) {
        this.teachingContent = teachingContent;
    }
}