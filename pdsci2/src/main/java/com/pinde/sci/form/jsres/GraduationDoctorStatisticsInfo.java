package com.pinde.sci.form.jsres;

public class GraduationDoctorStatisticsInfo {
    // 专业id
    private String speId;
    // 专业名称
    private String speName;
    // 基地flow
    private String orgFlow;
    // 基地编码
    private String orgCode;
    // 基地名称
    private String orgName;
    // 应结业人数
    private Integer graduationNum;
    // 已申请人数
    private Integer applyNum;
    // 基地审核通过人数
    private Integer localAuditNum;
    // 市局审核通过人数
    private Integer cityAuditNum;
    // 省厅审核通过人数
    private Integer globalAuditNum;

    public String getSpeId() {
        return speId;
    }

    public void setSpeId(String speId) {
        this.speId = speId;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getGraduationNum() {
        return graduationNum;
    }

    public void setGraduationNum(Integer graduationNum) {
        this.graduationNum = graduationNum;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getLocalAuditNum() {
        return localAuditNum;
    }

    public void setLocalAuditNum(Integer localAuditNum) {
        this.localAuditNum = localAuditNum;
    }

    public Integer getCityAuditNum() {
        return cityAuditNum;
    }

    public void setCityAuditNum(Integer cityAuditNum) {
        this.cityAuditNum = cityAuditNum;
    }

    public Integer getGlobalAuditNum() {
        return globalAuditNum;
    }

    public void setGlobalAuditNum(Integer globalAuditNum) {
        this.globalAuditNum = globalAuditNum;
    }
}
