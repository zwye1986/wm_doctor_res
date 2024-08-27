package com.pinde.sci.model.jsres;

public class ParticipateInfoExt {

    /**
     * 教学活动编码
     */
    private String lectureFlow ;

    /**
     * 已扫码学员
     */
    private String scanCodeStudent;

    /**
     * 已报名学员
     */
    private String signUpStudent ;

    /**
     * 已扫码教师
     */
    private String scanCodeTeacher;

    /**
     * 已报名教师
     */
    private String signUpTeacher ;

    public String getLectureFlow() {
        return lectureFlow;
    }

    public void setLectureFlow(String lectureFlow) {
        this.lectureFlow = lectureFlow;
    }

    public String getScanCodeStudent() {
        return scanCodeStudent;
    }

    public void setScanCodeStudent(String scanCodeStudent) {
        this.scanCodeStudent = scanCodeStudent;
    }

    public String getSignUpStudent() {
        return signUpStudent;
    }

    public void setSignUpStudent(String signUpStudent) {
        this.signUpStudent = signUpStudent;
    }

    public String getScanCodeTeacher() {
        return scanCodeTeacher;
    }

    public void setScanCodeTeacher(String scanCodeTeacher) {
        this.scanCodeTeacher = scanCodeTeacher;
    }

    public String getSignUpTeacher() {
        return signUpTeacher;
    }

    public void setSignUpTeacher(String signUpTeacher) {
        this.signUpTeacher = signUpTeacher;
    }




}
