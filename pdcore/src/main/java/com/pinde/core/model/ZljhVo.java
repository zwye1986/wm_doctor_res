package com.pinde.core.model;

/**
 * @创建人 zsq
 * @创建时间 2021/5/31
 * 描述
 */
public class ZljhVo implements java.io.Serializable {
    private String assignYear;
    private String orgType;
    private String orgName;
    private String orgFlow;
    private String assignPlanSum;

    public String getAssignYear() {
        return assignYear;
    }

    public void setAssignYear(String assignYear) {
        this.assignYear = assignYear;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getAssignPlanSum() {
        return assignPlanSum;
    }

    public void setAssignPlanSum(String assignPlanSum) {
        this.assignPlanSum = assignPlanSum;
    }
}
