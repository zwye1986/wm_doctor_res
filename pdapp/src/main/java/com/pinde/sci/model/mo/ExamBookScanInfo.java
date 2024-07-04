package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ExamBookScanInfo extends MybatisObject {
    private String scanFlow;

    private String bookFlow;

    private String scanUserFlow;

    private String scanUserName;

    private String scanTime;

    private String signUserFlow;

    private String signUserName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getScanFlow() {
        return scanFlow;
    }

    public void setScanFlow(String scanFlow) {
        this.scanFlow = scanFlow;
    }

    public String getBookFlow() {
        return bookFlow;
    }

    public void setBookFlow(String bookFlow) {
        this.bookFlow = bookFlow;
    }

    public String getScanUserFlow() {
        return scanUserFlow;
    }

    public void setScanUserFlow(String scanUserFlow) {
        this.scanUserFlow = scanUserFlow;
    }

    public String getScanUserName() {
        return scanUserName;
    }

    public void setScanUserName(String scanUserName) {
        this.scanUserName = scanUserName;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

    public String getSignUserFlow() {
        return signUserFlow;
    }

    public void setSignUserFlow(String signUserFlow) {
        this.signUserFlow = signUserFlow;
    }

    public String getSignUserName() {
        return signUserName;
    }

    public void setSignUserName(String signUserName) {
        this.signUserName = signUserName;
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