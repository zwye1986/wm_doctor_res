package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class ExamSubject extends TeachingActivitySpeakerExample.MybatisObject {
    private String subjectFlow;

    private String subjectCode;

    private String subjectName;

    private String subjectParentFlow;

    private String subjectTypeId;

    private String subjectTypeName;

    private String bankTypeId;

    private String bankTypeName;

    private Integer ordinal;

    private String depth;

    private String leafFlag;

    private String memo;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String sourceSubjectFlow;

    private String subjectIsenabled;

    public String getSubjectFlow() {
        return subjectFlow;
    }

    public void setSubjectFlow(String subjectFlow) {
        this.subjectFlow = subjectFlow;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectParentFlow() {
        return subjectParentFlow;
    }

    public void setSubjectParentFlow(String subjectParentFlow) {
        this.subjectParentFlow = subjectParentFlow;
    }

    public String getSubjectTypeId() {
        return subjectTypeId;
    }

    public void setSubjectTypeId(String subjectTypeId) {
        this.subjectTypeId = subjectTypeId;
    }

    public String getSubjectTypeName() {
        return subjectTypeName;
    }

    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }

    public String getBankTypeId() {
        return bankTypeId;
    }

    public void setBankTypeId(String bankTypeId) {
        this.bankTypeId = bankTypeId;
    }

    public String getBankTypeName() {
        return bankTypeName;
    }

    public void setBankTypeName(String bankTypeName) {
        this.bankTypeName = bankTypeName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getLeafFlag() {
        return leafFlag;
    }

    public void setLeafFlag(String leafFlag) {
        this.leafFlag = leafFlag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getSourceSubjectFlow() {
        return sourceSubjectFlow;
    }

    public void setSourceSubjectFlow(String sourceSubjectFlow) {
        this.sourceSubjectFlow = sourceSubjectFlow;
    }

    public String getSubjectIsenabled() {
        return subjectIsenabled;
    }

    public void setSubjectIsenabled(String subjectIsenabled) {
        this.subjectIsenabled = subjectIsenabled;
    }
}