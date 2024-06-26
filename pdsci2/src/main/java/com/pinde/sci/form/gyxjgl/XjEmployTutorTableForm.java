package com.pinde.sci.form.gyxjgl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 同等学力聘请导师申请表
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class XjEmployTutorTableForm {

    private String studentId;//学号
    private String userName;//姓名
    private String sexName;//性别
    private String idNumber;//身份证号
    private String speName;//申请硕士学位专业
    private String workUnitAndContact;//工作单位及联系电话
    private String paper;//科研、发表论文等
    private String tutorName;//导师姓名
    private String tutorSexName;//导师性别
    private String tutorAge;//导师年龄
    private String tutorTitle;//导师职称职务
    private String tutorWorkUnitAndContact;//工作单位及联系电话
    private String tutorDirection;//导师研究方向
    private String tutorOpinion;//导师意见
    private String orgOpinion;//导师单位意见
    private String schoolDirection;//研究生院意见

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getWorkUnitAndContact() {
        return workUnitAndContact;
    }

    public void setWorkUnitAndContact(String workUnitAndContact) {
        this.workUnitAndContact = workUnitAndContact;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorSexName() {
        return tutorSexName;
    }

    public void setTutorSexName(String tutorSexName) {
        this.tutorSexName = tutorSexName;
    }

    public String getTutorAge() {
        return tutorAge;
    }

    public void setTutorAge(String tutorAge) {
        this.tutorAge = tutorAge;
    }

    public String getTutorTitle() {
        return tutorTitle;
    }

    public void setTutorTitle(String tutorTitle) {
        this.tutorTitle = tutorTitle;
    }

    public String getTutorWorkUnitAndContact() {
        return tutorWorkUnitAndContact;
    }

    public void setTutorWorkUnitAndContact(String tutorWorkUnitAndContact) {
        this.tutorWorkUnitAndContact = tutorWorkUnitAndContact;
    }

    public String getTutorDirection() {
        return tutorDirection;
    }

    public void setTutorDirection(String tutorDirection) {
        this.tutorDirection = tutorDirection;
    }

    public String getTutorOpinion() {
        return tutorOpinion;
    }

    public void setTutorOpinion(String tutorOpinion) {
        this.tutorOpinion = tutorOpinion;
    }

    public String getOrgOpinion() {
        return orgOpinion;
    }

    public void setOrgOpinion(String orgOpinion) {
        this.orgOpinion = orgOpinion;
    }

    public String getSchoolDirection() {
        return schoolDirection;
    }

    public void setSchoolDirection(String schoolDirection) {
        this.schoolDirection = schoolDirection;
    }
}

