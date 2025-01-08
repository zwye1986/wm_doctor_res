package com.pinde.core.model;

/**
 *  @author: yuh
 *  @Date: 2020/3/4 16:32
 *  @Description: 基地-----学员轮转数据月报
 */
public class DoctorLunZhuanDataMonthReport {
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
    private Integer trainSum;   //在培学员总数
    private Integer writeSum; //数据填写量
    private Integer dataAuditSum;      //数据审核量
    private String  auditScale; //审核比例
    private Double aveWriteSum;        //平均填写量
    private String aveAuditScale; //平均审核比例

    private String isContain ;
    private String monthDate ;
    private String createTime;
    private String recordFlow;
    private String graduate;
    private String notGraduate;


    public String getIsContain() {
        return isContain;
    }

    public void setIsContain(String isContain) {
        this.isContain = isContain;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getNotGraduate() {
        return notGraduate;
    }

    public void setNotGraduate(String notGraduate) {
        this.notGraduate = notGraduate;
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

    public Integer getTrainSum() {
        return trainSum;
    }

    public void setTrainSum(Integer trainSum) {
        this.trainSum = trainSum;
    }

    public Integer getWriteSum() {
        return writeSum;
    }

    public void setWriteSum(Integer writeSum) {
        this.writeSum = writeSum;
    }

    public Integer getDataAuditSum() {
        return dataAuditSum;
    }

    public void setDataAuditSum(Integer dataAuditSum) {
        this.dataAuditSum = dataAuditSum;
    }

    public String getAuditScale() {
        return auditScale;
    }

    public void setAuditScale(String auditScale) {
        this.auditScale = auditScale;
    }

    public Double getAveWriteSum() {
        return aveWriteSum;
    }

    public void setAveWriteSum(Double aveWriteSum) {
        this.aveWriteSum = aveWriteSum;
    }

    public String getAveAuditScale() {
        return aveAuditScale;
    }

    public void setAveAuditScale(String aveAuditScale) {
        this.aveAuditScale = aveAuditScale;
    }
}
