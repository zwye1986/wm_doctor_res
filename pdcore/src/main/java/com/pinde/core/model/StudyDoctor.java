package com.pinde.core.model;

/**
 * @author xieshihai
 */
public class StudyDoctor implements java.io.Serializable {
    private String doctorFlow;
    private String doctorCode;
    private String doctorName;
    private String doctorHeadImg;
    private String sexName;
    private String cretTypeId;
    private String cretTypeNo;
    private String userMail;
    private String userPhone;
    /**
     * 培训类型
     */
    private String trainingTypeId;
    private String trainingTypeName;
    /**
     * 培训专业
     */
    private String trainingSpeId;
    private String trainingSpeName;

    private String sessionNumber;

    /**
     * 培训基地名称
     */
    private String orgFlow;
    private String orgName;
    /**
     * 单位名称
     */
    private String workOrgName;
    private String recordStatus;

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorHeadImg() {
        return doctorHeadImg;
    }

    public void setDoctorHeadImg(String doctorHeadImg) {
        this.doctorHeadImg = doctorHeadImg;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getCretTypeId() {
        return cretTypeId;
    }

    public void setCretTypeId(String cretTypeId) {
        this.cretTypeId = cretTypeId;
    }

    public String getCretTypeNo() {
        return cretTypeNo;
    }

    public void setCretTypeNo(String cretTypeNo) {
        this.cretTypeNo = cretTypeNo;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getTrainingTypeId() {
        return trainingTypeId;
    }

    public void setTrainingTypeId(String trainingTypeId) {
        this.trainingTypeId = trainingTypeId;
    }

    public String getTrainingTypeName() {
        return trainingTypeName;
    }

    public void setTrainingTypeName(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }

    public String getTrainingSpeId() {
        return trainingSpeId;
    }

    public void setTrainingSpeId(String trainingSpeId) {
        this.trainingSpeId = trainingSpeId;
    }

    public String getTrainingSpeName() {
        return trainingSpeName;
    }

    public void setTrainingSpeName(String trainingSpeName) {
        this.trainingSpeName = trainingSpeName;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
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

    public String getWorkOrgName() {
        return workOrgName;
    }

    public void setWorkOrgName(String workOrgName) {
        this.workOrgName = workOrgName;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }
}
