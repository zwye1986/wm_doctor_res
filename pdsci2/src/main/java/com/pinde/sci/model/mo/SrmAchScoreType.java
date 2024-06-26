package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class SrmAchScoreType extends MybatisObject {
    private String typeFlow;

    private String parentTypeFlow;

    private String scoreTypeName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getTypeFlow() {
        return typeFlow;
    }

    public void setTypeFlow(String typeFlow) {
        this.typeFlow = typeFlow;
    }

    public String getParentTypeFlow() {
        return parentTypeFlow;
    }

    public void setParentTypeFlow(String parentTypeFlow) {
        this.parentTypeFlow = parentTypeFlow;
    }

    public String getScoreTypeName() {
        return scoreTypeName;
    }

    public void setScoreTypeName(String scoreTypeName) {
        this.scoreTypeName = scoreTypeName;
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