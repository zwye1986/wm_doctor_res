package com.pinde.sci.model.vo;

import com.pinde.sci.model.mo.SysDept;

public class SysDeptVO extends SysDept {
    private String relDeptFlow;

    private String standardDeptFlow;

    private String standardDeptName;

    private String standardDeptCode;

    private String deptNameFuzzy;

    private String relStdDeptFlag;

    private String deptStatus;

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

    public String getStandardDeptFlow() {
        return standardDeptFlow;
    }

    public void setStandardDeptFlow(String standardDeptFlow) {
        this.standardDeptFlow = standardDeptFlow;
    }

    public String getStandardDeptName() {
        return standardDeptName;
    }

    public void setStandardDeptName(String standardDeptName) {
        this.standardDeptName = standardDeptName;
    }

    public String getStandardDeptCode() {
        return standardDeptCode;
    }

    public void setStandardDeptCode(String standardDeptCode) {
        this.standardDeptCode = standardDeptCode;
    }

    public String getRelDeptFlow() {
        return relDeptFlow;
    }

    public void setRelDeptFlow(String relDeptFlow) {
        this.relDeptFlow = relDeptFlow;
    }

    public String getDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(String deptStatus) {
        this.deptStatus = deptStatus;
    }
}
