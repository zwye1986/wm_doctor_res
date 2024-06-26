package com.pinde.sci.form.zseyjxres;

import java.io.Serializable;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2017/7/7
 */
public class EducationForm implements Serializable {
    /**
     * 学历起止时间
     */
    private String eduRoundDate;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 专业名称
     */
    private String speName;

    /**
     * 学制
     */
    private String length;

    /**
     * 学历
     */
    private String education;

    /**
     * 学位
     */
    private String degree;

    public String getEduRoundDate() {
        return eduRoundDate;
    }

    public void setEduRoundDate(String eduRoundDate) {
        this.eduRoundDate = eduRoundDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
