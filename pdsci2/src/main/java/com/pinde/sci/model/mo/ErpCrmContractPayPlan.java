package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class ErpCrmContractPayPlan extends MybatisObject {
    private String planFlow;

    private String contractFlow;

    private String planDate;

    private BigDecimal payFund;

    private String planTypeId;

    private String planTypeName;

    private String planStatusId;

    private String planStatusName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private BigDecimal balanceFund;

    public String getPlanFlow() {
        return planFlow;
    }

    public void setPlanFlow(String planFlow) {
        this.planFlow = planFlow;
    }

    public String getContractFlow() {
        return contractFlow;
    }

    public void setContractFlow(String contractFlow) {
        this.contractFlow = contractFlow;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public BigDecimal getPayFund() {
        return payFund;
    }

    public void setPayFund(BigDecimal payFund) {
        this.payFund = payFund;
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

    public String getPlanStatusId() {
        return planStatusId;
    }

    public void setPlanStatusId(String planStatusId) {
        this.planStatusId = planStatusId;
    }

    public String getPlanStatusName() {
        return planStatusName;
    }

    public void setPlanStatusName(String planStatusName) {
        this.planStatusName = planStatusName;
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

    public BigDecimal getBalanceFund() {
        return balanceFund;
    }

    public void setBalanceFund(BigDecimal balanceFund) {
        this.balanceFund = balanceFund;
    }
}