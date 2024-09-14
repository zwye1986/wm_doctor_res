package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ResTeacherTraining extends MybatisObject {
    private String recordFlow;

    private String doctorName;

    private String sexName;

    private String technicalTitle;

    private String orgFlow;

    private String orgName;

    private String certificateNo;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String speId;

    private String speName;

    private String deptFlow;

    private String deptName;

    private String trainingYear;

    private String certificateUrl;

    private String meetingName;

    private String doctorAge;

    private String doctorEdu;

    private String officeYear;

    private String workYear;

    private String internYear;

    private String threeInternYear;

    private String hosYear;

    private String threeHosYear;

    private String isTrain;

    private String achievementUrl;

    private String teacherLevelId;

    private String teacherLevelName;

    private String userPhone;

    private String applicationTeacherLevelId;

    private String applicationAuditStatus;

    private String applicationAuditMessage;

    private String applicationMessage;

    private String applicationProvalUrl;

    private String graduationSchool;

    private String graduationTime;

    private String workingTime;

    private String certificateLevel;

    private String certificateTime;

    private String isResponsibleTutor;

    private String idNo;

    private List<String> userDeptNames;

    private String allUserDeptNames;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getTechnicalTitle() {
        return technicalTitle;
    }

    public void setTechnicalTitle(String technicalTitle) {
        this.technicalTitle = technicalTitle;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }

    public String getSpeId() {
        return speId;
    }

    public void setSpeId(String speId) {
        this.speId = speId;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getDeptFlow() {
        return deptFlow;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTrainingYear() {
        return trainingYear;
    }

    public void setTrainingYear(String trainingYear) {
        this.trainingYear = trainingYear;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(String doctorAge) {
        this.doctorAge = doctorAge;
    }

    public String getDoctorEdu() {
        return doctorEdu;
    }

    public void setDoctorEdu(String doctorEdu) {
        this.doctorEdu = doctorEdu;
    }

    public String getOfficeYear() {
        return officeYear;
    }

    public void setOfficeYear(String officeYear) {
        this.officeYear = officeYear;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getInternYear() {
        return internYear;
    }

    public void setInternYear(String internYear) {
        this.internYear = internYear;
    }

    public String getThreeInternYear() {
        return threeInternYear;
    }

    public void setThreeInternYear(String threeInternYear) {
        this.threeInternYear = threeInternYear;
    }

    public String getHosYear() {
        return hosYear;
    }

    public void setHosYear(String hosYear) {
        this.hosYear = hosYear;
    }

    public String getThreeHosYear() {
        return threeHosYear;
    }

    public void setThreeHosYear(String threeHosYear) {
        this.threeHosYear = threeHosYear;
    }

    public String getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(String isTrain) {
        this.isTrain = isTrain;
    }

    public String getAchievementUrl() {
        return achievementUrl;
    }

    public void setAchievementUrl(String achievementUrl) {
        this.achievementUrl = achievementUrl;
    }

    public String getTeacherLevelId() {
        return teacherLevelId;
    }

    public void setTeacherLevelId(String teacherLevelId) {
        this.teacherLevelId = teacherLevelId;
    }

    public String getTeacherLevelName() {
        return teacherLevelName;
    }

    public void setTeacherLevelName(String teacherLevelName) {
        this.teacherLevelName = teacherLevelName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getApplicationTeacherLevelId() {
        return applicationTeacherLevelId;
    }

    public void setApplicationTeacherLevelId(String applicationTeacherLevelId) {
        this.applicationTeacherLevelId = applicationTeacherLevelId;
    }

    public String getApplicationAuditStatus() {
        return applicationAuditStatus;
    }

    public void setApplicationAuditStatus(String applicationAuditStatus) {
        this.applicationAuditStatus = applicationAuditStatus;
    }

    public String getApplicationAuditMessage() {
        return applicationAuditMessage;
    }

    public void setApplicationAuditMessage(String applicationAuditMessage) {
        this.applicationAuditMessage = applicationAuditMessage;
    }

    public String getApplicationMessage() {
        return applicationMessage;
    }

    public void setApplicationMessage(String applicationMessage) {
        this.applicationMessage = applicationMessage;
    }

    public String getApplicationProvalUrl() {
        return applicationProvalUrl;
    }

    public void setApplicationProvalUrl(String applicationProvalUrl) {
        this.applicationProvalUrl = applicationProvalUrl;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getCertificateLevel() {
        return certificateLevel;
    }

    public void setCertificateLevel(String certificateLevel) {
        this.certificateLevel = certificateLevel;
    }

    public String getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(String certificateTime) {
        this.certificateTime = certificateTime;
    }

    public String getIsResponsibleTutor() {
        return isResponsibleTutor;
    }

    public void setIsResponsibleTutor(String isResponsibleTutor) {
        this.isResponsibleTutor = isResponsibleTutor;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public List<String> getUserDeptNames() {
        return userDeptNames;
    }

    public void setUserDeptNames(List<String> userDeptNames) {
        this.userDeptNames = userDeptNames;
    }

    public String getAllUserDeptNames() {
        if(CollectionUtils.isEmpty(userDeptNames)){
            return "";
        }
        return StringUtils.join(userDeptNames,",");
    }

    public void setAllUserDeptNames(String allUserDeptNames) {
        this.allUserDeptNames = allUserDeptNames;
    }
}