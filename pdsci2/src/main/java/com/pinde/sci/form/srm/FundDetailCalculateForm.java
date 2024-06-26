package com.pinde.sci.form.srm;

import com.pinde.sci.model.mo.SrmProjFundDetail;

import java.math.BigDecimal;

/**
 * Created by www.0001.Ga on 2017-06-15.
 */
public class FundDetailCalculateForm extends SrmProjFundDetail {
//    SrmProjFundDetail projFundDetail;
    //该项已报销总计
    private BigDecimal yetPaymentAmount;
    //该项下拨已报销
    private BigDecimal yetPaymentAllocate;
    //该项配套已报销
    private BigDecimal yetPaymentMatching;
    //该项剩余金额
    private BigDecimal balanceAllocate;
    //该项下拨剩余
    private BigDecimal balanceAmount;
    //该项配套剩余
    private BigDecimal balanceMatching;

//    public SrmProjFundDetail getProjFundDetail() {
//        return projFundDetail;
//    }
//
//    public void setProjFundDetail(SrmProjFundDetail projFundDetail) {
//        this.projFundDetail = projFundDetail;
//    }

    public BigDecimal getYetPaymentAmount() {
        return yetPaymentAmount;
    }

    public void setYetPaymentAmount(BigDecimal yetPaymentAmount) {
        this.yetPaymentAmount = yetPaymentAmount;
    }

    public BigDecimal getYetPaymentAllocate() {
        return yetPaymentAllocate;
    }

    public void setYetPaymentAllocate(BigDecimal yetPaymentAllocate) {
        this.yetPaymentAllocate = yetPaymentAllocate;
    }

    public BigDecimal getYetPaymentMatching() {
        return yetPaymentMatching;
    }

    public void setYetPaymentMatching(BigDecimal yetPaymentMatching) {
        this.yetPaymentMatching = yetPaymentMatching;
    }

    public BigDecimal getBalanceAllocate() {
        return balanceAllocate;
    }

    public void setBalanceAllocate(BigDecimal balanceAllocate) {
        this.balanceAllocate = balanceAllocate;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getBalanceMatching() {
        return balanceMatching;
    }

    public void setBalanceMatching(BigDecimal balanceMatching) {
        this.balanceMatching = balanceMatching;
    }
}
