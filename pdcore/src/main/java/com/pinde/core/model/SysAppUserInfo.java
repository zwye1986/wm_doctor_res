package com.pinde.core.model;

public class SysAppUserInfo implements java.io.Serializable {
    private String infoFlow;

    private String docotrFlow;

    private String useMonth;

    private String useYear;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getInfoFlow() {
        return infoFlow;
    }

    public void setInfoFlow(String infoFlow) {
        this.infoFlow = infoFlow;
    }

    public String getDocotrFlow() {
        return docotrFlow;
    }

    public void setDocotrFlow(String docotrFlow) {
        this.docotrFlow = docotrFlow;
    }

    public String getUseMonth() {
        return useMonth;
    }

    public void setUseMonth(String useMonth) {
        this.useMonth = useMonth;
    }

    public String getUseYear() {
        return useYear;
    }

    public void setUseYear(String useYear) {
        this.useYear = useYear;
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
}