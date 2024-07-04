package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class ErpCrmContractPayBalance extends MybatisObject {
    private String recordFlow;

    private String contractFlow;

    private String planFlow;

    private String billFlow;

    private String payDate;

    private BigDecimal payFund;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String billApplyUserFlow;

    private String billApplyUserName;

    private String auditUserFlow;

    private String auditUserName;

    private String statusId;

    private String statusName;

    private String auditReason;

    private String auditTime;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getContractFlow() {
        return contractFlow;
    }

    public void setContractFlow(String contractFlow) {
        this.contractFlow = contractFlow;
    }

    public String getPlanFlow() {
        return planFlow;
    }

    public void setPlanFlow(String planFlow) {
        this.planFlow = planFlow;
    }

    public String getBillFlow() {
        return billFlow;
    }

    public void setBillFlow(String billFlow) {
        this.billFlow = billFlow;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayFund() {
        return payFund;
    }

    public void setPayFund(BigDecimal payFund) {
        this.payFund = payFund;
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

    public String getBillApplyUserFlow() {
        return billApplyUserFlow;
    }

    public void setBillApplyUserFlow(String billApplyUserFlow) {
        this.billApplyUserFlow = billApplyUserFlow;
    }

    public String getBillApplyUserName() {
        return billApplyUserName;
    }

    public void setBillApplyUserName(String billApplyUserName) {
        this.billApplyUserName = billApplyUserName;
    }

    public String getAuditUserFlow() {
        return auditUserFlow;
    }

    public void setAuditUserFlow(String auditUserFlow) {
        this.auditUserFlow = auditUserFlow;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
}