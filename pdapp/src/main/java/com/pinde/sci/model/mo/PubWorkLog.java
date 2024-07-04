package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class PubWorkLog extends MybatisObject {
    private String logFlow;

    private String fillDate;

    private String endDate;

    private Short weekNum;

    private Short logMonth;

    private String logTypeId;

    private String logTypeName;

    private String userFlow;

    private String userName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String logContent;

    public String getLogFlow() {
        return logFlow;
    }

    public void setLogFlow(String logFlow) {
        this.logFlow = logFlow;
    }

    public String getFillDate() {
        return fillDate;
    }

    public void setFillDate(String fillDate) {
        this.fillDate = fillDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Short getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Short weekNum) {
        this.weekNum = weekNum;
    }

    public Short getLogMonth() {
        return logMonth;
    }

    public void setLogMonth(Short logMonth) {
        this.logMonth = logMonth;
    }

    public String getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(String logTypeId) {
        this.logTypeId = logTypeId;
    }

    public String getLogTypeName() {
        return logTypeName;
    }

    public void setLogTypeName(String logTypeName) {
        this.logTypeName = logTypeName;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}