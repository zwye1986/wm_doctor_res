package com.pinde.sci.model.jsres;

import java.util.List;

/**
 * 基地--------学员轮转异常统计
 */
public class DoctorLunZhuanExceptionParam {

    private String id;
    private String pid;
    private String orgFlow;
    private String orgName;
    private String joinOrgFlow;
    private String joinOrgName;
    private String sessionNumber;
    private String level;
    private String speId;
    private String speName;
    private Integer inException;   //入科异常
    private List<String> inExceptionDoctorFlowList;
    private Integer outException;      //出科异常
    private List<String> outExceptionDoctorFlowList;
    private Integer outExamException;        //出科考核异常
    private List<String> outExamExceptionDoctorFlowList;

    public List<String> getInExceptionDoctorFlowList() {
        return inExceptionDoctorFlowList;
    }

    public void setInExceptionDoctorFlowList(List<String> inExceptionDoctorFlowList) {
        this.inExceptionDoctorFlowList = inExceptionDoctorFlowList;
    }

    public List<String> getOutExceptionDoctorFlowList() {
        return outExceptionDoctorFlowList;
    }

    public void setOutExceptionDoctorFlowList(List<String> outExceptionDoctorFlowList) {
        this.outExceptionDoctorFlowList = outExceptionDoctorFlowList;
    }

    public List<String> getOutExamExceptionDoctorFlowList() {
        return outExamExceptionDoctorFlowList;
    }

    public void setOutExamExceptionDoctorFlowList(List<String> outExamExceptionDoctorFlowList) {
        this.outExamExceptionDoctorFlowList = outExamExceptionDoctorFlowList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getJoinOrgFlow() {
        return joinOrgFlow;
    }

    public void setJoinOrgFlow(String joinOrgFlow) {
        this.joinOrgFlow = joinOrgFlow;
    }

    public String getJoinOrgName() {
        return joinOrgName;
    }

    public void setJoinOrgName(String joinOrgName) {
        this.joinOrgName = joinOrgName;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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

    public Integer getInException() {
        return inException;
    }

    public void setInException(Integer inException) {
        this.inException = inException;
    }

    public Integer getOutException() {
        return outException;
    }

    public void setOutException(Integer outException) {
        this.outException = outException;
    }

    public Integer getOutExamException() {
        return outExamException;
    }

    public void setOutExamException(Integer outExamException) {
        this.outExamException = outExamException;
    }
}
