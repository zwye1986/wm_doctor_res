package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EdcInspect extends MybatisObject {
    private String projFlow;

    private String inspectTypeId;

    private String inspectTypeName;

    private String recordStatus;

    private String createUserFlow;

    private String createTime;

    private String modifyUserFlow;

    private String modifyTime;

    private String inspectInfo;

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getInspectTypeId() {
        return inspectTypeId;
    }

    public void setInspectTypeId(String inspectTypeId) {
        this.inspectTypeId = inspectTypeId;
    }

    public String getInspectTypeName() {
        return inspectTypeName;
    }

    public void setInspectTypeName(String inspectTypeName) {
        this.inspectTypeName = inspectTypeName;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getInspectInfo() {
        return inspectInfo;
    }

    public void setInspectInfo(String inspectInfo) {
        this.inspectInfo = inspectInfo;
    }
}