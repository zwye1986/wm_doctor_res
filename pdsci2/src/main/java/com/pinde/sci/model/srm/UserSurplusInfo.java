package com.pinde.sci.model.srm;

import java.math.BigDecimal;

/**
 * 个人经费结余情况
 * Created by www.0001.Ga on 2017-06-20.
 */
public class UserSurplusInfo {

    private String userFlow;
    private String userName;//用户名
    private String deptName;//科室
    private String projCount;//已结题项目数
    private String accountNo;//个人科研账号
    private BigDecimal budgetAmount;//总预算
    private BigDecimal realityAmount;//实际到账
    private BigDecimal yetPaymentAmount;//实际报销
    private BigDecimal realityBalance;//剩余总经费

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProjCount() {
        return projCount;
    }

    public void setProjCount(String projCount) {
        this.projCount = projCount;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public BigDecimal getRealityAmount() {
        return realityAmount;
    }

    public void setRealityAmount(BigDecimal realityAmount) {
        this.realityAmount = realityAmount;
    }

    public BigDecimal getYetPaymentAmount() {
        return yetPaymentAmount;
    }

    public void setYetPaymentAmount(BigDecimal yetPaymentAmount) {
        this.yetPaymentAmount = yetPaymentAmount;
    }

    public BigDecimal getRealityBalance() {
        return realityBalance;
    }

    public void setRealityBalance(BigDecimal realityBalance) {
        this.realityBalance = realityBalance;
    }
}
