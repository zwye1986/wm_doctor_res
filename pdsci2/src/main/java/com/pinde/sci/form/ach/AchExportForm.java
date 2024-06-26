package com.pinde.sci.form.ach;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 论文（奖项）导出信息(徐州中心，无锡二院)
 */
public class AchExportForm implements Serializable {
    private static final long serialVersionUID = 4313531234321L;
    private String year;
    private BigDecimal num;
    private String authorName;
    private String userFlow;
    private String deptFlow;
    private String deptName;
    private String branchId;
    private String branchName;
    private BigDecimal amount;
    private Map<String,BigDecimal> typeMap;

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Map<String, BigDecimal> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map<String, BigDecimal> typeMap) {
        this.typeMap = typeMap;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getDeptFlow() {
        return deptFlow;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
