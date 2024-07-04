package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;
import java.math.BigDecimal;

public class SrmProjFundForm extends MybatisObject {
    private String fundFormFlow;

    private String fundOperator;

    private String fundFlow;

    private BigDecimal money;

    private BigDecimal realmoney;

    private String operStatusId;

    private String operStatusName;

    private String provideDateTime;

    private BigDecimal orgFund;

    private BigDecimal goveFund;

    private BigDecimal realityGoveAmount;

    private BigDecimal realityOrgAmount;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getFundFormFlow() {
        return fundFormFlow;
    }

    public void setFundFormFlow(String fundFormFlow) {
        this.fundFormFlow = fundFormFlow;
    }

    public String getFundOperator() {
        return fundOperator;
    }

    public void setFundOperator(String fundOperator) {
        this.fundOperator = fundOperator;
    }

    public String getFundFlow() {
        return fundFlow;
    }

    public void setFundFlow(String fundFlow) {
        this.fundFlow = fundFlow;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getRealmoney() {
        return realmoney;
    }

    public void setRealmoney(BigDecimal realmoney) {
        this.realmoney = realmoney;
    }

    public String getOperStatusId() {
        return operStatusId;
    }

    public void setOperStatusId(String operStatusId) {
        this.operStatusId = operStatusId;
    }

    public String getOperStatusName() {
        return operStatusName;
    }

    public void setOperStatusName(String operStatusName) {
        this.operStatusName = operStatusName;
    }

    public String getProvideDateTime() {
        return provideDateTime;
    }

    public void setProvideDateTime(String provideDateTime) {
        this.provideDateTime = provideDateTime;
    }

    public BigDecimal getOrgFund() {
        return orgFund;
    }

    public void setOrgFund(BigDecimal orgFund) {
        this.orgFund = orgFund;
    }

    public BigDecimal getGoveFund() {
        return goveFund;
    }

    public void setGoveFund(BigDecimal goveFund) {
        this.goveFund = goveFund;
    }

    public BigDecimal getRealityGoveAmount() {
        return realityGoveAmount;
    }

    public void setRealityGoveAmount(BigDecimal realityGoveAmount) {
        this.realityGoveAmount = realityGoveAmount;
    }

    public BigDecimal getRealityOrgAmount() {
        return realityOrgAmount;
    }

    public void setRealityOrgAmount(BigDecimal realityOrgAmount) {
        this.realityOrgAmount = realityOrgAmount;
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