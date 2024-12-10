package com.pinde.core.vo;


import com.pinde.core.model.ResDeptRelStdDept;

import java.util.List;

public class ResDeptRelStdDeptVO extends ResDeptRelStdDept {
    private String deptNameFuzzy;

    private String relStdDeptFlag;

    private String standardDeptName;

    private String operType;

    private Integer ordinal;

    private List<String> deptNameCodeOrPair;

    public String getDeptNameFuzzy() {
        return deptNameFuzzy;
    }

    public void setDeptNameFuzzy(String deptNameFuzzy) {
        this.deptNameFuzzy = deptNameFuzzy;
    }

    public String getRelStdDeptFlag() {
        return relStdDeptFlag;
    }

    public void setRelStdDeptFlag(String relStdDeptFlag) {
        this.relStdDeptFlag = relStdDeptFlag;
    }

    public String getStandardDeptName() {
        return standardDeptName;
    }

    public void setStandardDeptName(String standardDeptName) {
        this.standardDeptName = standardDeptName;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public List<String> getDeptNameCodeOrPair() {
        return deptNameCodeOrPair;
    }

    public void setDeptNameCodeOrPair(List<String> deptNameCodeOrPair) {
        this.deptNameCodeOrPair = deptNameCodeOrPair;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }
}
