package com.pinde.sci.model.vo;

import com.pinde.sci.model.mo.ResSpeBaseStdDept;

import java.util.ArrayList;
import java.util.List;

public class ResSpeBaseStdDeptVO extends ResSpeBaseStdDept {
    private String standardDeptName;

    private String standardDeptCode;

    private List<String> requireStatusIn = new ArrayList<>();

    private List<String> standardDeptFlowIn = new ArrayList<>();

    public List<String> getRequireStatusIn() {
        return requireStatusIn;
    }

    public void setRequireStatusIn(List<String> requireStatusIn) {
        this.requireStatusIn = requireStatusIn;
    }

    public List<String> getStandardDeptFlowIn() {
        return standardDeptFlowIn;
    }

    public void setStandardDeptFlowIn(List<String> standardDeptFlowIn) {
        this.standardDeptFlowIn = standardDeptFlowIn;
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
}
