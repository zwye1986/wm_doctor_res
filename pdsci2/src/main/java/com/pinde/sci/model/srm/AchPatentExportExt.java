package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.SrmAchPatent;

import java.math.BigDecimal;

/**
 * Created by www.0001.Ga on 2016-12-14.
 */
public class AchPatentExportExt extends SrmAchPatent{
    private String deptFlow;
    private String deptName;
    private BigDecimal num;
    private String userFlow;
    private String authorName;
   // private BigDecimal amount;

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    @Override
    public String getDeptFlow() {
        return deptFlow;
    }

    @Override
    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    @Override
    public String getDeptName() {
        return deptName;
    }

    @Override
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
