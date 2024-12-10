package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

import java.math.BigDecimal;

public class PubProjFundPlan implements java.io.Serializable {
    private String fundPlanFlow;

    private String projFlow;

    private String planTypeId;

    private String planTypeName;

    private String year;

    private BigDecimal amount;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getFundPlanFlow() {
        return fundPlanFlow;
    }

    public void setFundPlanFlow(String fundPlanFlow) {
        this.fundPlanFlow = fundPlanFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getPlanTypeId() {
        return planTypeId;
    }

    public void setPlanTypeId(String planTypeId) {
        this.planTypeId = planTypeId;
    }

    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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