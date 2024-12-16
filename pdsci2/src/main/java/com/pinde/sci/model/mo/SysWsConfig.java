package com.pinde.sci.model.mo;

import lombok.Data;
import lombok.Getter;

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

    public void setWsId(String wsId) {
        this.wsId = wsId;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public void setSysLoginImg(String sysLoginImg) {
        this.sysLoginImg = sysLoginImg;
    }

    public void setSysHeadImg(String sysHeadImg) {
        this.sysHeadImg = sysHeadImg;
    }

    public void setSysTitleName(String sysTitleName) {
        this.sysTitleName = sysTitleName;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}