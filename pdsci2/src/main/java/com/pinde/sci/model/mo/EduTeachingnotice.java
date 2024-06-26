package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EduTeachingnotice extends MybatisObject {
    private String infoFlow;

    private String infoTitle;

    private String infoTargetName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String infoTargetFlow;

    private String infoTypeId;

    private String infoTypeName;

    private String infoContent;

    public String getInfoFlow() {
        return infoFlow;
    }

    public void setInfoFlow(String infoFlow) {
        this.infoFlow = infoFlow;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoTargetName() {
        return infoTargetName;
    }

    public void setInfoTargetName(String infoTargetName) {
        this.infoTargetName = infoTargetName;
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

    public String getInfoTargetFlow() {
        return infoTargetFlow;
    }

    public void setInfoTargetFlow(String infoTargetFlow) {
        this.infoTargetFlow = infoTargetFlow;
    }

    public String getInfoTypeId() {
        return infoTypeId;
    }

    public void setInfoTypeId(String infoTypeId) {
        this.infoTypeId = infoTypeId;
    }

    public String getInfoTypeName() {
        return infoTypeName;
    }

    public void setInfoTypeName(String infoTypeName) {
        this.infoTypeName = infoTypeName;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }
}