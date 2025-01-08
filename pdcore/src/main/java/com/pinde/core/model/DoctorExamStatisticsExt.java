package com.pinde.core.model;

/**
 * 基地--------学员轮转异常统计
 */
public class DoctorExamStatisticsExt {

    private String id;
    private String pid;
    private String orgFlow;
    private String orgName;
    private String speId;
    private String speName;
    private String orgCityId;
    private String orgCityName;
    private String examTotal;   //应参考人数
    private String realTotal;   //实际参考人数
    private String fristExamTotal; //首考人数
    private String secondExamTotal; //补考人数
    private String missExamTotal; //缺考人数

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

    public String getOrgCityId() {
        return orgCityId;
    }

    public void setOrgCityId(String orgCityId) {
        this.orgCityId = orgCityId;
    }

    public String getOrgCityName() {
        return orgCityName;
    }

    public void setOrgCityName(String orgCityName) {
        this.orgCityName = orgCityName;
    }

    public String getExamTotal() {
        return examTotal;
    }

    public void setExamTotal(String examTotal) {
        this.examTotal = examTotal;
    }

    public String getRealTotal() {
        return realTotal;
    }

    public void setRealTotal(String realTotal) {
        this.realTotal = realTotal;
    }

    public String getFristExamTotal() {
        return fristExamTotal;
    }

    public void setFristExamTotal(String fristExamTotal) {
        this.fristExamTotal = fristExamTotal;
    }

    public String getSecondExamTotal() {
        return secondExamTotal;
    }

    public void setSecondExamTotal(String secondExamTotal) {
        this.secondExamTotal = secondExamTotal;
    }

    public String getMissExamTotal() {
        return missExamTotal;
    }

    public void setMissExamTotal(String missExamTotal) {
        this.missExamTotal = missExamTotal;
    }
}
