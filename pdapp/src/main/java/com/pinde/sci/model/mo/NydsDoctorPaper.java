package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class NydsDoctorPaper extends MybatisObject {
    private String recordFlow;

    private String doctorFlow;

    private String paperTitle;

    private String periodicalName;

    private String publishTime;

    private String influenceFactor;

    private String jcrPartitionId;

    private String jcrPartitionName;

    private String periodicalPicUrl;

    private String paperPicUrl;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String authorRank;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getInfluenceFactor() {
        return influenceFactor;
    }

    public void setInfluenceFactor(String influenceFactor) {
        this.influenceFactor = influenceFactor;
    }

    public String getJcrPartitionId() {
        return jcrPartitionId;
    }

    public void setJcrPartitionId(String jcrPartitionId) {
        this.jcrPartitionId = jcrPartitionId;
    }

    public String getJcrPartitionName() {
        return jcrPartitionName;
    }

    public void setJcrPartitionName(String jcrPartitionName) {
        this.jcrPartitionName = jcrPartitionName;
    }

    public String getPeriodicalPicUrl() {
        return periodicalPicUrl;
    }

    public void setPeriodicalPicUrl(String periodicalPicUrl) {
        this.periodicalPicUrl = periodicalPicUrl;
    }

    public String getPaperPicUrl() {
        return paperPicUrl;
    }

    public void setPaperPicUrl(String paperPicUrl) {
        this.paperPicUrl = paperPicUrl;
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

    public String getAuthorRank() {
        return authorRank;
    }

    public void setAuthorRank(String authorRank) {
        this.authorRank = authorRank;
    }
}