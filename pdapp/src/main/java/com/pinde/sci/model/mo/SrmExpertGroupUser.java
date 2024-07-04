package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmExpertGroupUser extends MybatisObject {
    private String recordFlow;

    private String groupFlow;

    private String userFlow;

    private String expertStatusId;

    private String expertStatusName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String signFlag;

    private String emailNotifyFlag;

    private String phoneNotifyFlag;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getGroupFlow() {
        return groupFlow;
    }

    public void setGroupFlow(String groupFlow) {
        this.groupFlow = groupFlow;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getExpertStatusId() {
        return expertStatusId;
    }

    public void setExpertStatusId(String expertStatusId) {
        this.expertStatusId = expertStatusId;
    }

    public String getExpertStatusName() {
        return expertStatusName;
    }

    public void setExpertStatusName(String expertStatusName) {
        this.expertStatusName = expertStatusName;
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

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag;
    }

    public String getEmailNotifyFlag() {
        return emailNotifyFlag;
    }

    public void setEmailNotifyFlag(String emailNotifyFlag) {
        this.emailNotifyFlag = emailNotifyFlag;
    }

    public String getPhoneNotifyFlag() {
        return phoneNotifyFlag;
    }

    public void setPhoneNotifyFlag(String phoneNotifyFlag) {
        this.phoneNotifyFlag = phoneNotifyFlag;
    }
}