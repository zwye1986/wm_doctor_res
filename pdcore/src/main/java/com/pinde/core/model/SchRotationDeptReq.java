package com.pinde.core.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
@TableName("sch_rotation_dept_req")
public class SchRotationDeptReq implements java.io.Serializable {
    @TableId
    private String reqFlow;

    private String rotationFlow;

    private String standardDeptId;

    private String standardDeptName;

    private String relRecordFlow;

    private String recTypeId;

    private String recTypeName;

    private String itemName;

    private BigDecimal reqNum;

    private String reqNote;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String orgFlow;

    private String orgName;

    private String itemId;

    private String reqStandard;

    public String getReqFlow() {
        return reqFlow;
    }

    public void setReqFlow(String reqFlow) {
        this.reqFlow = reqFlow;
    }

    public String getRotationFlow() {
        return rotationFlow;
    }

    public void setRotationFlow(String rotationFlow) {
        this.rotationFlow = rotationFlow;
    }

    public String getStandardDeptId() {
        return standardDeptId;
    }

    public void setStandardDeptId(String standardDeptId) {
        this.standardDeptId = standardDeptId;
    }

    public String getStandardDeptName() {
        return standardDeptName;
    }

    public void setStandardDeptName(String standardDeptName) {
        this.standardDeptName = standardDeptName;
    }

    public String getRelRecordFlow() {
        return relRecordFlow;
    }

    public void setRelRecordFlow(String relRecordFlow) {
        this.relRecordFlow = relRecordFlow;
    }

    public String getRecTypeId() {
        return recTypeId;
    }

    public void setRecTypeId(String recTypeId) {
        this.recTypeId = recTypeId;
    }

    public String getRecTypeName() {
        return recTypeName;
    }

    public void setRecTypeName(String recTypeName) {
        this.recTypeName = recTypeName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getReqNum() {
        return reqNum;
    }

    public void setReqNum(BigDecimal reqNum) {
        this.reqNum = reqNum;
    }

    public String getReqNote() {
        return reqNote;
    }

    public void setReqNote(String reqNote) {
        this.reqNote = reqNote;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getReqStandard() {
        return reqStandard;
    }

    public void setReqStandard(String reqStandard) {
        this.reqStandard = reqStandard;
    }
}