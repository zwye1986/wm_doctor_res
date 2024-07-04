package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class OscaSubjectPartScore extends MybatisObject {
    private String recordFlow;

    private String subjectFlow;

    private String partFlow;

    private Integer partScore;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getSubjectFlow() {
        return subjectFlow;
    }

    public void setSubjectFlow(String subjectFlow) {
        this.subjectFlow = subjectFlow;
    }

    public String getPartFlow() {
        return partFlow;
    }

    public void setPartFlow(String partFlow) {
        this.partFlow = partFlow;
    }

    public Integer getPartScore() {
        return partScore;
    }

    public void setPartScore(Integer partScore) {
        this.partScore = partScore;
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