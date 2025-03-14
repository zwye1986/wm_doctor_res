package com.pinde.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
@TableName("RES_JOINT_ORG")
public class ResJointOrg implements java.io.Serializable {
    @TableId
    private String jointFlow;

    private String orgFlow;

    private String orgName;

    private String jointOrgFlow;

    private String jointOrgName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String sessionNumber;

    private String speId;

    private String speName;

    @TableField(exist = false)
    private List<String> jointOrgFlowList;

    public List<String> getJointOrgFlowList() {
        return jointOrgFlowList;
    }

    public void setJointOrgFlowList(List<String> jointOrgFlowList) {
        this.jointOrgFlowList = jointOrgFlowList;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getSpeId() {
        return speId;
    }

    public void setSpeId(String speId) {
        this.speId = speId;
    }

    public String getJointFlow() {
        return jointFlow;
    }

    public void setJointFlow(String jointFlow) {
        this.jointFlow = jointFlow;
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

    public String getJointOrgFlow() {
        return jointOrgFlow;
    }

    public void setJointOrgFlow(String jointOrgFlow) {
        this.jointOrgFlow = jointOrgFlow;
    }

    public String getJointOrgName() {
        return jointOrgName;
    }

    public void setJointOrgName(String jointOrgName) {
        this.jointOrgName = jointOrgName;
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

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }
}