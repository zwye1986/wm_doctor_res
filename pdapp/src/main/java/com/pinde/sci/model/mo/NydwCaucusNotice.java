package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class NydwCaucusNotice extends MybatisObject {
    private String recordFlow;

    private String noticeName;

    private String partyBranchId;

    private String partyBranchName;

    private String noticeTypeId;

    private String noticeTypeName;

    private String publishFlag;

    private String publishTime;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String clicks;

    private String content;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(String partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public String getPartyBranchName() {
        return partyBranchName;
    }

    public void setPartyBranchName(String partyBranchName) {
        this.partyBranchName = partyBranchName;
    }

    public String getNoticeTypeId() {
        return noticeTypeId;
    }

    public void setNoticeTypeId(String noticeTypeId) {
        this.noticeTypeId = noticeTypeId;
    }

    public String getNoticeTypeName() {
        return noticeTypeName;
    }

    public void setNoticeTypeName(String noticeTypeName) {
        this.noticeTypeName = noticeTypeName;
    }

    public String getPublishFlag() {
        return publishFlag;
    }

    public void setPublishFlag(String publishFlag) {
        this.publishFlag = publishFlag;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
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

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}