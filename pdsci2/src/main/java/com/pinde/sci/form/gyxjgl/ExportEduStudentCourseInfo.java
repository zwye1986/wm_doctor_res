package com.pinde.sci.form.gyxjgl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ExportEduStudentCourseInfo implements Serializable {

    private static final long serialVersionUID = 4467721827892963176L;
    private String gainYear;//获得学年
    private String gainTerm;//获得学期
    private String sid;//学号
    private String userName;//姓名
    private String courseCode;//课程代码
    private String courseName;//课程名称
    private String courseType;//课程类型
    private String coursePeriod;//学时
    private String courseCredit;//学分
    private String styUnit;//修读性质
    private String testType;//考核方式
    private String normalGrade;//平时成绩
    private String termGrade;//期末成绩
    private String courseGrade;//成绩

    public String getGainYear() {
        return gainYear;
    }

    public void setGainYear(String gainYear) {
        this.gainYear = gainYear;
    }

    public String getGainTerm() {
        return gainTerm;
    }

    public void setGainTerm(String gainTerm) {
        this.gainTerm = gainTerm;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(String coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getStyUnit() {
        return styUnit;
    }

    public void setStyUnit(String styUnit) {
        this.styUnit = styUnit;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getNormalGrade() {
        return normalGrade;
    }

    public void setNormalGrade(String normalGrade) {
        this.normalGrade = normalGrade;
    }

    public String getTermGrade() {
        return termGrade;
    }

    public void setTermGrade(String termGrade) {
        this.termGrade = termGrade;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }
}
