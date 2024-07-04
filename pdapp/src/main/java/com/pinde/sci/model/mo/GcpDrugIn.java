package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class GcpDrugIn extends MybatisObject {
    private String recordFlow;

    private String projFlow;

    private String orgFlow;

    private String drugFlow;

    private String drugPack;

    private String drugAmount;

    private String lotNo;

    private String inDate;

    private String inOperator;

    private String recordStatus;

    private String createUserFlow;

    private String createTime;

    private String modifyUserFlow;

    private String modifyTime;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getDrugFlow() {
        return drugFlow;
    }

    public void setDrugFlow(String drugFlow) {
        this.drugFlow = drugFlow;
    }

    public String getDrugPack() {
        return drugPack;
    }

    public void setDrugPack(String drugPack) {
        this.drugPack = drugPack;
    }

    public String getDrugAmount() {
        return drugAmount;
    }

    public void setDrugAmount(String drugAmount) {
        this.drugAmount = drugAmount;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getInOperator() {
        return inOperator;
    }

    public void setInOperator(String inOperator) {
        this.inOperator = inOperator;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}