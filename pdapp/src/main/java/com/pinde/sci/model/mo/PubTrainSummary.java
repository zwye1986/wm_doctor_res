package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class PubTrainSummary extends MybatisObject {
    private String recordFlow;

    private String summaryTitle;

    private String summaryTypeId;

    private String summaryTypeName;

    private String operUserFlow;

    private String operUserName;

    private String operTime;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String summaryContent;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getSummaryTitle() {
        return summaryTitle;
    }

    public void setSummaryTitle(String summaryTitle) {
        this.summaryTitle = summaryTitle;
    }

    public String getSummaryTypeId() {
        return summaryTypeId;
    }

    public void setSummaryTypeId(String summaryTypeId) {
        this.summaryTypeId = summaryTypeId;
    }

    public String getSummaryTypeName() {
        return summaryTypeName;
    }

    public void setSummaryTypeName(String summaryTypeName) {
        this.summaryTypeName = summaryTypeName;
    }

    public String getOperUserFlow() {
        return operUserFlow;
    }

    public void setOperUserFlow(String operUserFlow) {
        this.operUserFlow = operUserFlow;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
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

    public String getSummaryContent() {
        return summaryContent;
    }

    public void setSummaryContent(String summaryContent) {
        this.summaryContent = summaryContent;
    }
}