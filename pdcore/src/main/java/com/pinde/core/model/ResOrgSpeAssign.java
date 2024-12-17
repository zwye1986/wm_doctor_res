package com.pinde.core.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResOrgSpeAssign implements java.io.Serializable {
    private String recordFlow;

    private String orgFlow;

    private String speId;

    private String speName;

    private BigDecimal assignPlan;

    private BigDecimal assignReal;

    private String assignYear;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private BigDecimal assignPlanOrg;

    private String isSend;

    private String graduateSpe;

    private String education;

    private String auditStatusId;

    private String auditStatusName;

    private String speDesc;

    private String startTime;

    private String endTime;

    private String isShown;

    private String baseCapacity;

    private String sendStartTime;

    private String sendEndTime;

    private String sendPlan;
}