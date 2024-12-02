package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class RecruitCfgInfo extends TeachingActivitySpeakerExample.MybatisObject {
    private String cfgFlow;

    private String orgFlow;

    private String recruitYear;

    private String recruitStartDate;

    private String recruitEndDate;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String isRecruit;

    public String getCfgFlow() {
        return cfgFlow;
    }

    public void setCfgFlow(String cfgFlow) {
        this.cfgFlow = cfgFlow;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getRecruitYear() {
        return recruitYear;
    }

    public void setRecruitYear(String recruitYear) {
        this.recruitYear = recruitYear;
    }

    public String getRecruitStartDate() {
        return recruitStartDate;
    }

    public void setRecruitStartDate(String recruitStartDate) {
        this.recruitStartDate = recruitStartDate;
    }

    public String getRecruitEndDate() {
        return recruitEndDate;
    }

    public void setRecruitEndDate(String recruitEndDate) {
        this.recruitEndDate = recruitEndDate;
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

    public String getIsRecruit() {
        return isRecruit;
    }

    public void setIsRecruit(String isRecruit) {
        this.isRecruit = isRecruit;
    }
}