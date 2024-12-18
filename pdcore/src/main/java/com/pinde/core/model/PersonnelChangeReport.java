package com.pinde.core.model;

public class PersonnelChangeReport implements java.io.Serializable {
    /**
     * 专业基地名称
     */
    private String professionalBase;

    /**
     * 科室
     */
    private String trainingSpeName;

    /**
     * 年级
     */
    private String sessionNumber;

    /**
     * 协同基地
     */
    private String orgName;

    /**
     * 延培人数
     */
    private Integer changeTimeNum;

    /**
     * 变更基地人数
     */
    private Integer speChangeNum;

    /**
     * id
     */
    private Integer id;

    /**
     * 父id
     */
    private Integer pid;

    public String getProfessionalBase() {
        return professionalBase;
    }

    public void setProfessionalBase(String professionalBase) {
        this.professionalBase = professionalBase;
    }

    public String getTrainingSpeName() {
        return trainingSpeName;
    }

    public void setTrainingSpeName(String trainingSpeName) {
        this.trainingSpeName = trainingSpeName;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getChangeTimeNum() {
        return changeTimeNum;
    }

    public void setChangeTimeNum(Integer changeTimeNum) {
        this.changeTimeNum = changeTimeNum;
    }

    public Integer getSpeChangeNum() {
        return speChangeNum;
    }

    public void setSpeChangeNum(Integer speChangeNum) {
        this.speChangeNum = speChangeNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
