package com.pinde.sci.form.gzykdx;

import com.pinde.sci.model.mo.PubUserResume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 导师信息简历
 * @since 2017/3/8
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class GzykdxTeacherExtInfoForm extends PubUserResume implements Serializable {

    /**
     * 年龄
     */
    private String age;
    /**
     * 所在领域工作年限
     */
    private String workAreaYear;
    /**
     * 参加工作时间
     */
    private String workDate;
    /**
     * 最高学历毕业学校
     */
    private String educationSchool;
    /**
     * 最高学历毕业时间
     */
    private String educationDate;
    /**
     * 最高学位获得单位
     */
    private String degreeOrg;
    /**
     * 最高学位获得时间
     */
    private String degreeDate;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWorkAreaYear() {
        return workAreaYear;
    }

    public void setWorkAreaYear(String workAreaYear) {
        this.workAreaYear = workAreaYear;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getEducationSchool() {
        return educationSchool;
    }

    public void setEducationSchool(String educationSchool) {
        this.educationSchool = educationSchool;
    }

    public String getEducationDate() {
        return educationDate;
    }

    public void setEducationDate(String educationDate) {
        this.educationDate = educationDate;
    }

    public String getDegreeOrg() {
        return degreeOrg;
    }

    public void setDegreeOrg(String degreeOrg) {
        this.degreeOrg = degreeOrg;
    }

    public String getDegreeDate() {
        return degreeDate;
    }

    public void setDegreeDate(String degreeDate) {
        this.degreeDate = degreeDate;
    }
}
