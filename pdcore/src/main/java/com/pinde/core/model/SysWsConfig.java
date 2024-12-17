package com.pinde.core.model;

import lombok.Data;

@Data
public class SysWsConfig implements java.io.Serializable {
    private String wsId;

    private String wsName;

    private String loginUrl;

    private String logoutUrl;

    private String sysLoginImg;

    private String sysHeadImg;

    private String sysTitleName;

    private String admissionTime;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String isDefault;

    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getSysLoginImg() {
        return sysLoginImg;
    }

    public void setSysLoginImg(String sysLoginImg) {
        this.sysLoginImg = sysLoginImg;
    }

    public String getSysHeadImg() {
        return sysHeadImg;
    }

    public void setSysHeadImg(String sysHeadImg) {
        this.sysHeadImg = sysHeadImg;
    }

    public String getSysTitleName() {
        return sysTitleName;
    }

    public void setSysTitleName(String sysTitleName) {
        this.sysTitleName = sysTitleName;
    }

    public String getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime;
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

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}