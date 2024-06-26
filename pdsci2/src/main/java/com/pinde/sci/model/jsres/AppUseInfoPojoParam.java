package com.pinde.sci.model.jsres;

/**
 *  基地 ---app使用情况统计
 */
public class AppUseInfoPojoParam {
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
    private String doctorRateTrainSum;   //住院医师 在培人数
    private String doctorRateUseSum;      //住院医师 使用人数
    private String doctorRateRate;        //住院医师 使用率
    private String masterRateTrainSum;   //在校专硕 在培人数
    private String masterRateUseSum;  //在校专硕 使用人数
    private String masterRateRate;   //在校专硕 使用率
    private String avgRateTrainSum;    //平均使用率 在培人数
    private String avgRateUseSum; //平均使用率 使用人数
    private String avgRateRate;  //平均使用率 使用率


    private String isContain ;
    private String monthDate ;
    private String createTime;
    private String recordFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

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

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getDoctorRateTrainSum() {
        return doctorRateTrainSum;
    }

    public void setDoctorRateTrainSum(String doctorRateTrainSum) {
        this.doctorRateTrainSum = doctorRateTrainSum;
    }

    public String getDoctorRateUseSum() {
        return doctorRateUseSum;
    }

    public void setDoctorRateUseSum(String doctorRateUseSum) {
        this.doctorRateUseSum = doctorRateUseSum;
    }

    public String getDoctorRateRate() {
        return doctorRateRate;
    }

    public void setDoctorRateRate(String doctorRateRate) {
        this.doctorRateRate = doctorRateRate;
    }

    public String getMasterRateTrainSum() {
        return masterRateTrainSum;
    }

    public void setMasterRateTrainSum(String masterRateTrainSum) {
        this.masterRateTrainSum = masterRateTrainSum;
    }

    public String getMasterRateUseSum() {
        return masterRateUseSum;
    }

    public void setMasterRateUseSum(String masterRateUseSum) {
        this.masterRateUseSum = masterRateUseSum;
    }

    public String getMasterRateRate() {
        return masterRateRate;
    }

    public void setMasterRateRate(String masterRateRate) {
        this.masterRateRate = masterRateRate;
    }

    public String getAvgRateTrainSum() {
        return avgRateTrainSum;
    }

    public void setAvgRateTrainSum(String avgRateTrainSum) {
        this.avgRateTrainSum = avgRateTrainSum;
    }

    public String getAvgRateUseSum() {
        return avgRateUseSum;
    }

    public void setAvgRateUseSum(String avgRateUseSum) {
        this.avgRateUseSum = avgRateUseSum;
    }

    public String getAvgRateRate() {
        return avgRateRate;
    }

    public void setAvgRateRate(String avgRateRate) {
        this.avgRateRate = avgRateRate;
    }
}

