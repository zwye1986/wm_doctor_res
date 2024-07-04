package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EdcVisit extends MybatisObject {
    private String visitFlow;

    private String visitName;

    private String visitTypeId;

    private String visitTypeName;

    private Integer ordinal;

    private String projFlow;

    private String visitWindow;

    private String isVisit;

    private String isBaseline;

    private String groupFlow;

    private String groupName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getVisitFlow() {
        return visitFlow;
    }

    public void setVisitFlow(String visitFlow) {
        this.visitFlow = visitFlow;
    }

    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName;
    }

    public String getVisitTypeId() {
        return visitTypeId;
    }

    public void setVisitTypeId(String visitTypeId) {
        this.visitTypeId = visitTypeId;
    }

    public String getVisitTypeName() {
        return visitTypeName;
    }

    public void setVisitTypeName(String visitTypeName) {
        this.visitTypeName = visitTypeName;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getProjFlow() {
        return projFlow;
    }

    public void setProjFlow(String projFlow) {
        this.projFlow = projFlow;
    }

    public String getVisitWindow() {
        return visitWindow;
    }

    public void setVisitWindow(String visitWindow) {
        this.visitWindow = visitWindow;
    }

    public String getIsVisit() {
        return isVisit;
    }

    public void setIsVisit(String isVisit) {
        this.isVisit = isVisit;
    }

    public String getIsBaseline() {
        return isBaseline;
    }

    public void setIsBaseline(String isBaseline) {
        this.isBaseline = isBaseline;
    }

    public String getGroupFlow() {
        return groupFlow;
    }

    public void setGroupFlow(String groupFlow) {
        this.groupFlow = groupFlow;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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