package com.pinde.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("res_rec")
public class ResRec implements java.io.Serializable {
    @TableId
    private String recFlow;

    private String orgFlow;

    private String orgName;

    private String deptFlow;

    private String deptName;

    private String schDeptFlow;

    private String schDeptName;

    private String recTypeId;

    private String recTypeName;

    private String itemName;

    private String recVersion;

    private String operTime;

    private String operUserFlow;

    private String operUserName;

    private String statusId;

    private String statusName;

    private String auditTime;

    private String auditUserFlow;

    private String auditUserName;

    private String auditStatusId;

    private String auditStatusName;

    private String headAuditTime;

    private String headAuditUserFlow;

    private String headAuditUserName;

    private String headAuditStatusId;

    private String headAuditStatusName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String managerAuditTime;

    private String managerAuditUserFlow;

    private String managerAuditUserName;

    private String managerAuditStatusId;

    private String managerAuditStatusName;

    private String adminAuditTime;

    private String adminAuditUserFlow;

    private String adminAuditUserName;

    private String adminAuditStatusId;

    private String adminAuditStatusName;

    private String itemId;

    private String processFlow;

    private String recForm;

    private String schRotationDeptFlow;

    private String medicineType;

    private String recContent;
    @TableField(exist = false)
    private  String checkItemName;
    @TableField(exist = false)
    private String invalidContent;
    @TableField(exist = false)
    private String startDate;
    @TableField(exist = false)
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCheckItemName() {
        return checkItemName;
    }

    public void setCheckItemName(String checkItemName) {
        this.checkItemName = checkItemName;
    }

    public String getInvalidContent() {
        return invalidContent;
    }

    public void setInvalidContent(String invalidContent) {
        this.invalidContent = invalidContent;
    }

    /**
     * 查询类型 ：2 教学活动
     */
    @TableField(exist = false)
    private String queryType;

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getRecFlow() {
        return recFlow;
    }

    public void setRecFlow(String recFlow) {
        this.recFlow = recFlow;
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

    public String getDeptFlow() {
        return deptFlow;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSchDeptFlow() {
        return schDeptFlow;
    }

    public void setSchDeptFlow(String schDeptFlow) {
        this.schDeptFlow = schDeptFlow;
    }

    public String getSchDeptName() {
        return schDeptName;
    }

    public void setSchDeptName(String schDeptName) {
        this.schDeptName = schDeptName;
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

    public String getRecVersion() {
        return recVersion;
    }

    public void setRecVersion(String recVersion) {
        this.recVersion = recVersion;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getOperUserFlow() {
        return operUserFlow;
    }

    public void setOperUserFlow(String operUserFlow) {
        this.operUserFlow = operUserFlow;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditUserFlow() {
        return auditUserFlow;
    }

    public void setAuditUserFlow(String auditUserFlow) {
        this.auditUserFlow = auditUserFlow;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public String getAuditStatusId() {
        return auditStatusId;
    }

    public void setAuditStatusId(String auditStatusId) {
        this.auditStatusId = auditStatusId;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    public String getHeadAuditTime() {
        return headAuditTime;
    }

    public void setHeadAuditTime(String headAuditTime) {
        this.headAuditTime = headAuditTime;
    }

    public String getHeadAuditUserFlow() {
        return headAuditUserFlow;
    }

    public void setHeadAuditUserFlow(String headAuditUserFlow) {
        this.headAuditUserFlow = headAuditUserFlow;
    }

    public String getHeadAuditUserName() {
        return headAuditUserName;
    }

    public void setHeadAuditUserName(String headAuditUserName) {
        this.headAuditUserName = headAuditUserName;
    }

    public String getHeadAuditStatusId() {
        return headAuditStatusId;
    }

    public void setHeadAuditStatusId(String headAuditStatusId) {
        this.headAuditStatusId = headAuditStatusId;
    }

    public String getHeadAuditStatusName() {
        return headAuditStatusName;
    }

    public void setHeadAuditStatusName(String headAuditStatusName) {
        this.headAuditStatusName = headAuditStatusName;
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

    public String getManagerAuditTime() {
        return managerAuditTime;
    }

    public void setManagerAuditTime(String managerAuditTime) {
        this.managerAuditTime = managerAuditTime;
    }

    public String getManagerAuditUserFlow() {
        return managerAuditUserFlow;
    }

    public void setManagerAuditUserFlow(String managerAuditUserFlow) {
        this.managerAuditUserFlow = managerAuditUserFlow;
    }

    public String getManagerAuditUserName() {
        return managerAuditUserName;
    }

    public void setManagerAuditUserName(String managerAuditUserName) {
        this.managerAuditUserName = managerAuditUserName;
    }

    public String getManagerAuditStatusId() {
        return managerAuditStatusId;
    }

    public void setManagerAuditStatusId(String managerAuditStatusId) {
        this.managerAuditStatusId = managerAuditStatusId;
    }

    public String getManagerAuditStatusName() {
        return managerAuditStatusName;
    }

    public void setManagerAuditStatusName(String managerAuditStatusName) {
        this.managerAuditStatusName = managerAuditStatusName;
    }

    public String getAdminAuditTime() {
        return adminAuditTime;
    }

    public void setAdminAuditTime(String adminAuditTime) {
        this.adminAuditTime = adminAuditTime;
    }

    public String getAdminAuditUserFlow() {
        return adminAuditUserFlow;
    }

    public void setAdminAuditUserFlow(String adminAuditUserFlow) {
        this.adminAuditUserFlow = adminAuditUserFlow;
    }

    public String getAdminAuditUserName() {
        return adminAuditUserName;
    }

    public void setAdminAuditUserName(String adminAuditUserName) {
        this.adminAuditUserName = adminAuditUserName;
    }

    public String getAdminAuditStatusId() {
        return adminAuditStatusId;
    }

    public void setAdminAuditStatusId(String adminAuditStatusId) {
        this.adminAuditStatusId = adminAuditStatusId;
    }

    public String getAdminAuditStatusName() {
        return adminAuditStatusName;
    }

    public void setAdminAuditStatusName(String adminAuditStatusName) {
        this.adminAuditStatusName = adminAuditStatusName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProcessFlow() {
        return processFlow;
    }

    public void setProcessFlow(String processFlow) {
        this.processFlow = processFlow;
    }

    public String getRecForm() {
        return recForm;
    }

    public void setRecForm(String recForm) {
        this.recForm = recForm;
    }

    public String getSchRotationDeptFlow() {
        return schRotationDeptFlow;
    }

    public void setSchRotationDeptFlow(String schRotationDeptFlow) {
        this.schRotationDeptFlow = schRotationDeptFlow;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getRecContent() {
        return recContent;
    }

    public void setRecContent(String recContent) {
        this.recContent = recContent;
    }
}