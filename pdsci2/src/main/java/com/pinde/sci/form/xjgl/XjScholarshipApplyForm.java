package com.pinde.sci.form.xjgl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class XjScholarshipApplyForm implements Serializable {

    /********************助管岗位/国家助学金**********************/
    private String userName;
    private String userBirthday;
    private String nativePlace;
    private String politicsStatusName;
    private String pydwOrgName;
    private String majorName;
    private String tutorName;
    private String sid;
    private String className;
    private String graduateSchool;
    private String userPhone;
    private String userQq;
    private String userEmail;
    private String specialty;
    //助管岗位
    private String jzfdyDesc;
    private String yjsggDesc;
    private String xszlDesc;
    //助管岗位
    private String resumeDesc;
    private String awardDesc;
    private String memo;
    /********************助管岗位/国家助学金**********************/
    private String sexName;
    private String nationName;
    private String periodYear;
    private String mainDeeds;
    private String awardExperience;
    private String backbonePosition;
    private String baseUnit;
    private String studyDegree;
    private String schoolSystem;
    private String studyPeriod;
    private String cardId;
    private String applyReason;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPoliticsStatusName() {
        return politicsStatusName;
    }

    public void setPoliticsStatusName(String politicsStatusName) {
        this.politicsStatusName = politicsStatusName;
    }

    public String getPydwOrgName() {
        return pydwOrgName;
    }

    public void setPydwOrgName(String pydwOrgName) {
        this.pydwOrgName = pydwOrgName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getJzfdyDesc() {
        return jzfdyDesc;
    }

    public void setJzfdyDesc(String jzfdyDesc) {
        this.jzfdyDesc = jzfdyDesc;
    }

    public String getYjsggDesc() {
        return yjsggDesc;
    }

    public void setYjsggDesc(String yjsggDesc) {
        this.yjsggDesc = yjsggDesc;
    }

    public String getXszlDesc() {
        return xszlDesc;
    }

    public void setXszlDesc(String xszlDesc) {
        this.xszlDesc = xszlDesc;
    }

    public String getResumeDesc() {
        return resumeDesc;
    }

    public void setResumeDesc(String resumeDesc) {
        this.resumeDesc = resumeDesc;
    }

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getPeriodYear() {
        return periodYear;
    }

    public void setPeriodYear(String periodYear) {
        this.periodYear = periodYear;
    }

    public String getMainDeeds() {
        return mainDeeds;
    }

    public void setMainDeeds(String mainDeeds) {
        this.mainDeeds = mainDeeds;
    }

    public String getAwardExperience() {
        return awardExperience;
    }

    public void setAwardExperience(String awardExperience) {
        this.awardExperience = awardExperience;
    }

    public String getBackbonePosition() {
        return backbonePosition;
    }

    public void setBackbonePosition(String backbonePosition) {
        this.backbonePosition = backbonePosition;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public String getStudyDegree() {
        return studyDegree;
    }

    public void setStudyDegree(String studyDegree) {
        this.studyDegree = studyDegree;
    }

    public String getSchoolSystem() {
        return schoolSystem;
    }

    public void setSchoolSystem(String schoolSystem) {
        this.schoolSystem = schoolSystem;
    }

    public String getStudyPeriod() {
        return studyPeriod;
    }

    public void setStudyPeriod(String studyPeriod) {
        this.studyPeriod = studyPeriod;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }
}
