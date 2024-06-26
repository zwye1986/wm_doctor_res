package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class ErpCrmContractBillPlan extends MybatisObject {
    private String billPlanFlow;

    private String contractFlow;

    private String billPlanDate;

    private BigDecimal billPayFund;

    private String billPlanStatusId;

    private String billPlanStatusName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private BigDecimal billBalanceFund;

    public String getBillPlanFlow() {
        return billPlanFlow;
    }

    public void setBillPlanFlow(String billPlanFlow) {
        this.billPlanFlow = billPlanFlow;
    }

    public String getContractFlow() {
        return contractFlow;
    }

    public void setContractFlow(String contractFlow) {
        this.contractFlow = contractFlow;
    }

    public String getBillPlanDate() {
        return billPlanDate;
    }

    public void setBillPlanDate(String billPlanDate) {
        this.billPlanDate = billPlanDate;
    }

    public BigDecimal getBillPayFund() {
        return billPayFund;
    }

    public void setBillPayFund(BigDecimal billPayFund) {
        this.billPayFund = billPayFund;
    }

    public String getBillPlanStatusId() {
        return billPlanStatusId;
    }

    public void setBillPlanStatusId(String billPlanStatusId) {
        this.billPlanStatusId = billPlanStatusId;
    }

    public String getBillPlanStatusName() {
        return billPlanStatusName;
    }

    public void setBillPlanStatusName(String billPlanStatusName) {
        this.billPlanStatusName = billPlanStatusName;
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

    public BigDecimal getBillBalanceFund() {
        return billBalanceFund;
    }

    public void setBillBalanceFund(BigDecimal billBalanceFund) {
        this.billBalanceFund = billBalanceFund;
    }
}