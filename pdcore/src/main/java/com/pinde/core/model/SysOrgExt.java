package com.pinde.core.model;

/**
 * @ClassName SysOrgExt
 * @Description 基地扩展类
 * @Author fengxf
 * @Date 2020/9/2
 */
public class SysOrgExt extends SysOrg {
    // 基地级别
    private String orgBaseGradeName;
    // 协同基地flow
    private String jointOrgFlow;
    // 协同基地名称
    private String jointOrgName;
    // 协同基地级别
    private String jointBaseGradeName;
    //基地代码
    private String baseCode;
    //年份
    private String sessionYear;

    public String getSessionYear() {
        return sessionYear;
    }

    public void setSessionYear(String sessionYear) {
        this.sessionYear = sessionYear;
    }

    public String getOrgBaseGradeName() {
        return orgBaseGradeName;
    }

    public void setOrgBaseGradeName(String orgBaseGradeName) {
        this.orgBaseGradeName = orgBaseGradeName;
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

    public String getJointBaseGradeName() {
        return jointBaseGradeName;
    }

    public void setJointBaseGradeName(String jointBaseGradeName) {
        this.jointBaseGradeName = jointBaseGradeName;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
}
