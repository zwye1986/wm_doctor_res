package com.pinde.sci.model.mo;

import com.pinde.core.model.TeachingActivitySpeakerExample;

public class OscaFrom extends TeachingActivitySpeakerExample.MybatisObject {
    private String fromFlow;

    private String fromName;

    private String fromTypeId;

    private String fromTypeName;

    private String fromUrl;

    private String isReleased;

    private String fromScore;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String orgFlow;

    private String orgName;

    private String rromContent;

    public String getFromFlow() {
        return fromFlow;
    }

    public void setFromFlow(String fromFlow) {
        this.fromFlow = fromFlow;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromTypeId() {
        return fromTypeId;
    }

    public void setFromTypeId(String fromTypeId) {
        this.fromTypeId = fromTypeId;
    }

    public String getFromTypeName() {
        return fromTypeName;
    }

    public void setFromTypeName(String fromTypeName) {
        this.fromTypeName = fromTypeName;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public String getIsReleased() {
        return isReleased;
    }

    public void setIsReleased(String isReleased) {
        this.isReleased = isReleased;
    }

    public String getFromScore() {
        return fromScore;
    }

    public void setFromScore(String fromScore) {
        this.fromScore = fromScore;
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

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRromContent() {
        return rromContent;
    }

    public void setRromContent(String rromContent) {
        this.rromContent = rromContent;
    }
}