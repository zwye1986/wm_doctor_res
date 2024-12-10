package com.pinde.sci.model.jsres;

import com.pinde.core.model.TeachingActivitySpeakerExample;
import com.pinde.sci.model.mo.TeachingActivityResult;

import java.util.List;

/**
 * 基地---教学活动和教学结果
 */
public class JiaoxueActiveAndResultParam implements java.io.Serializable {
    private String activityFlow;

    private String activityName;

    private String deptFlow;

    private String orgFlow;

    private String speakerFlow;

    private String speakerPhone;

    private String activityTypeId;

    private String activityTypeName;

    private String startTime;

    private String endTime;

    private String activityAddress;

    private String activityRemark;

    private String fileFlow;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String isEffective;

    private String realitySpeaker;

    private String imageUrl;

    private String activityStatus;

    private String submitRole;

    private String auditRole;

    private String opinion;

    private String userName;
    private String deptName;
    private String evalScore;
    private String fileName;
    private String scanNum;
    private String regiestNum;

    private List<TeachingActivityResult> resultList;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEvalScore() {
        return evalScore;
    }

    public void setEvalScore(String evalScore) {
        this.evalScore = evalScore;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getScanNum() {
        return scanNum;
    }

    public void setScanNum(String scanNum) {
        this.scanNum = scanNum;
    }

    public String getRegiestNum() {
        return regiestNum;
    }

    public void setRegiestNum(String regiestNum) {
        this.regiestNum = regiestNum;
    }

    public List<TeachingActivityResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<TeachingActivityResult> resultList) {
        this.resultList = resultList;
    }

    public String getActivityFlow() {
        return activityFlow;
    }

    public void setActivityFlow(String activityFlow) {
        this.activityFlow = activityFlow;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDeptFlow() {
        return deptFlow;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getSpeakerFlow() {
        return speakerFlow;
    }

    public void setSpeakerFlow(String speakerFlow) {
        this.speakerFlow = speakerFlow;
    }

    public String getSpeakerPhone() {
        return speakerPhone;
    }

    public void setSpeakerPhone(String speakerPhone) {
        this.speakerPhone = speakerPhone;
    }

    public String getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(String activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public String getActivityTypeName() {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityRemark() {
        return activityRemark;
    }

    public void setActivityRemark(String activityRemark) {
        this.activityRemark = activityRemark;
    }

    public String getFileFlow() {
        return fileFlow;
    }

    public void setFileFlow(String fileFlow) {
        this.fileFlow = fileFlow;
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

    public String getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(String isEffective) {
        this.isEffective = isEffective;
    }

    public String getRealitySpeaker() {
        return realitySpeaker;
    }

    public void setRealitySpeaker(String realitySpeaker) {
        this.realitySpeaker = realitySpeaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getSubmitRole() {
        return submitRole;
    }

    public void setSubmitRole(String submitRole) {
        this.submitRole = submitRole;
    }

    public String getAuditRole() {
        return auditRole;
    }

    public void setAuditRole(String auditRole) {
        this.auditRole = auditRole;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
