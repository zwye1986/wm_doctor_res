package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ErpDocShare extends MybatisObject {
    private String recordFlow;

    private String docFlow;

    private String shareTypeId;

    private String shareTypeName;

    private String shareRecordFlow;

    private String shareRecordName;

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

    public String getDocFlow() {
        return docFlow;
    }

    public void setDocFlow(String docFlow) {
        this.docFlow = docFlow;
    }

    public String getShareTypeId() {
        return shareTypeId;
    }

    public void setShareTypeId(String shareTypeId) {
        this.shareTypeId = shareTypeId;
    }

    public String getShareTypeName() {
        return shareTypeName;
    }

    public void setShareTypeName(String shareTypeName) {
        this.shareTypeName = shareTypeName;
    }

    public String getShareRecordFlow() {
        return shareRecordFlow;
    }

    public void setShareRecordFlow(String shareRecordFlow) {
        this.shareRecordFlow = shareRecordFlow;
    }

    public String getShareRecordName() {
        return shareRecordName;
    }

    public void setShareRecordName(String shareRecordName) {
        this.shareRecordName = shareRecordName;
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