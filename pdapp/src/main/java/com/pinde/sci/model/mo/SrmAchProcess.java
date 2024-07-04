package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmAchProcess extends MybatisObject {
    private String processFlow;

    private String achFlow;

    private String typeId;

    private String typeName;

    private String operateUserFlow;

    private String operateUserName;

    private String operateTime;

    private String operStatusId;

    private String operStatusName;

    private String content;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getProcessFlow() {
        return processFlow;
    }

    public void setProcessFlow(String processFlow) {
        this.processFlow = processFlow;
    }

    public String getAchFlow() {
        return achFlow;
    }

    public void setAchFlow(String achFlow) {
        this.achFlow = achFlow;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOperateUserFlow() {
        return operateUserFlow;
    }

    public void setOperateUserFlow(String operateUserFlow) {
        this.operateUserFlow = operateUserFlow;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperStatusId() {
        return operStatusId;
    }

    public void setOperStatusId(String operStatusId) {
        this.operStatusId = operStatusId;
    }

    public String getOperStatusName() {
        return operStatusName;
    }

    public void setOperStatusName(String operStatusName) {
        this.operStatusName = operStatusName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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