package com.pinde.core.model;
/**
 *  基地 ---教学活动情况统计
 */
public class JiaoxueActivePojoParam {
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
    private String trainerSum;   //在培人数总数
    private Integer teachActiveSessionSum;      //活动举办场次
    private Integer allJoinSum;        //总参加人次
    private Double averJoinSum;   //场均参加人次
    private Double averDureTime;  //场均时长
    private Double dureTime;//总时长

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

    public String getTrainerSum() {
        return trainerSum;
    }

    public void setTrainerSum(String trainerSum) {
        this.trainerSum = trainerSum;
    }

    public Integer getTeachActiveSessionSum() {
        return teachActiveSessionSum;
    }

    public void setTeachActiveSessionSum(Integer teachActiveSessionSum) {
        this.teachActiveSessionSum = teachActiveSessionSum;
    }

    public Integer getAllJoinSum() {
        return allJoinSum;
    }

    public void setAllJoinSum(Integer allJoinSum) {
        this.allJoinSum = allJoinSum;
    }

    public Double getAverJoinSum() {
        return averJoinSum;
    }

    public void setAverJoinSum(Double averJoinSum) {
        this.averJoinSum = averJoinSum;
    }

    public Double getAverDureTime() {
        return averDureTime;
    }

    public void setAverDureTime(Double averDureTime) {
        this.averDureTime = averDureTime;
    }

    public Double getDureTime() {
        return dureTime;
    }

    public void setDureTime(Double dureTime) {
        this.dureTime = dureTime;
    }
}

