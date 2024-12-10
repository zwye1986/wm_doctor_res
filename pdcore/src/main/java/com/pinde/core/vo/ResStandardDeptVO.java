package com.pinde.core.vo;


import com.pinde.core.model.ResStandardDept;

import java.util.ArrayList;
import java.util.List;

public class ResStandardDeptVO extends ResStandardDept {
    private String standardDeptNameFuzzy;

    private String oldStandardDeptCode;

    private String withSpeBaseFlag;

    private String withBaseDeptFlag;

    private String speBaseId;

    private String rotateRequireStatus;

    private String speBaseStdDeptFlow;

    private String deptFlow;

    private String curRelStandardDeptFlag;

    private List<ResStandardDeptVO> subStandardDeptList = new ArrayList<>();

    private List<String> deptNameCodeOrPair = new ArrayList<>();

    private List<String> standardDeptFlowList = new ArrayList<>();

    public String getStandardDeptNameFuzzy() {
        return standardDeptNameFuzzy;
    }

    public void setStandardDeptNameFuzzy(String standardDeptNameFuzzy) {
        this.standardDeptNameFuzzy = standardDeptNameFuzzy;
    }

    public List<ResStandardDeptVO> getSubStandardDeptList() {
        return subStandardDeptList;
    }

    public void setSubStandardDeptList(List<ResStandardDeptVO> subStandardDeptList) {
        this.subStandardDeptList = subStandardDeptList;
    }

    public List<String> getDeptNameCodeOrPair() {
        return deptNameCodeOrPair;
    }

    public void setDeptNameCodeOrPair(List<String> deptNameCodeOrPair) {
        this.deptNameCodeOrPair = deptNameCodeOrPair;
    }

    public String getOldStandardDeptCode() {
        return oldStandardDeptCode;
    }

    public void setOldStandardDeptCode(String oldStandardDeptCode) {
        this.oldStandardDeptCode = oldStandardDeptCode;
    }

    public String getWithSpeBaseFlag() {
        return withSpeBaseFlag;
    }

    public void setWithSpeBaseFlag(String withSpeBaseFlag) {
        this.withSpeBaseFlag = withSpeBaseFlag;
    }

    public String getSpeBaseId() {
        return speBaseId;
    }

    public void setSpeBaseId(String speBaseId) {
        this.speBaseId = speBaseId;
    }

    public String getRotateRequireStatus() {
        return rotateRequireStatus;
    }

    public void setRotateRequireStatus(String rotateRequireStatus) {
        this.rotateRequireStatus = rotateRequireStatus;
    }

    public String getSpeBaseStdDeptFlow() {
        return speBaseStdDeptFlow;
    }

    public void setSpeBaseStdDeptFlow(String speBaseStdDeptFlow) {
        this.speBaseStdDeptFlow = speBaseStdDeptFlow;
    }

    public List<String> getStandardDeptFlowList() {
        return standardDeptFlowList;
    }

    public void setStandardDeptFlowList(List<String> standardDeptFlowList) {
        this.standardDeptFlowList = standardDeptFlowList;
    }

    public String getWithBaseDeptFlag() {
        return withBaseDeptFlag;
    }

    public void setWithBaseDeptFlag(String withBaseDeptFlag) {
        this.withBaseDeptFlag = withBaseDeptFlag;
    }

    public String getDeptFlow() {
        return deptFlow;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public String getCurRelStandardDeptFlag() {
        return curRelStandardDeptFlag;
    }

    public void setCurRelStandardDeptFlag(String curRelStandardDeptFlag) {
        this.curRelStandardDeptFlag = curRelStandardDeptFlag;
    }
}
