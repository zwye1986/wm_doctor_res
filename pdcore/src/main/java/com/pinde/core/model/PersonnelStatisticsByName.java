package com.pinde.core.model;

public class PersonnelStatisticsByName implements java.io.Serializable {

    private String orgFlow;

    private String trainingSpeName;

    private String sessionNumber;

    private String orgName;

    private Integer lastResidentNum;

    private Integer residentNum;

    private Integer lastInSchoolNum;

    private Integer inSchoolNum;

    private Integer residentRecruitNum;

    private Integer inSchoolRecruitNum;

    private Integer residentGraduatNum;

    private Integer inSchoolGraduatNum;

    private Integer residentExaminedNum;

    private Integer inSchoolExaminedNum;

    private Integer residentReturnNum;

    private Integer inSchoolReturnNum;

    private Integer residentOutNum;

    private Integer inSchoolOutNum;

    private Integer residentInNum;

    private Integer inSchoolInNum;

    private Integer pid;//父id

    private Integer id;

    private Integer lastSum;//上月在培人数总计

    private Integer momentSum;//本月在培人数合计

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public Integer getLastSum() {
        return lastSum;
    }

    public void setLastSum(Integer lastSum) {
        this.lastSum = lastSum;
    }

    public Integer getMomentSum() {
        return momentSum;
    }

    public void setMomentSum(Integer momentSum) {
        this.momentSum = momentSum;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLastResidentNum() {
        return lastResidentNum;
    }

    public void setLastResidentNum(Integer lastResidentNum) {
        this.lastResidentNum = lastResidentNum;
    }

    public Integer getResidentNum() {
        return residentNum;
    }

    public void setResidentNum(Integer residentNum) {
        this.residentNum = residentNum;
    }

    public Integer getLastInSchoolNum() {
        return lastInSchoolNum;
    }

    public void setLastInSchoolNum(Integer lastInSchoolNum) {
        this.lastInSchoolNum = lastInSchoolNum;
    }

    public Integer getInSchoolNum() {
        return inSchoolNum;
    }

    public void setInSchoolNum(Integer inSchoolNum) {
        this.inSchoolNum = inSchoolNum;
    }

    public Integer getResidentRecruitNum() {
        return residentRecruitNum;
    }

    public void setResidentRecruitNum(Integer residentRecruitNum) {
        this.residentRecruitNum = residentRecruitNum;
    }

    public Integer getInSchoolRecruitNum() {
        return inSchoolRecruitNum;
    }

    public void setInSchoolRecruitNum(Integer inSchoolRecruitNum) {
        this.inSchoolRecruitNum = inSchoolRecruitNum;
    }

    public Integer getResidentGraduatNum() {
        return residentGraduatNum;
    }

    public void setResidentGraduatNum(Integer residentGraduatNum) {
        this.residentGraduatNum = residentGraduatNum;
    }

    public Integer getInSchoolGraduatNum() {
        return inSchoolGraduatNum;
    }

    public void setInSchoolGraduatNum(Integer inSchoolGraduatNum) {
        this.inSchoolGraduatNum = inSchoolGraduatNum;
    }

    public Integer getResidentExaminedNum() {
        return residentExaminedNum;
    }

    public void setResidentExaminedNum(Integer residentExaminedNum) {
        this.residentExaminedNum = residentExaminedNum;
    }

    public Integer getInSchoolExaminedNum() {
        return inSchoolExaminedNum;
    }

    public void setInSchoolExaminedNum(Integer inSchoolExaminedNum) {
        this.inSchoolExaminedNum = inSchoolExaminedNum;
    }

    public Integer getResidentReturnNum() {
        return residentReturnNum;
    }

    public void setResidentReturnNum(Integer residentReturnNum) {
        this.residentReturnNum = residentReturnNum;
    }

    public Integer getInSchoolReturnNum() {
        return inSchoolReturnNum;
    }

    public void setInSchoolReturnNum(Integer inSchoolReturnNum) {
        this.inSchoolReturnNum = inSchoolReturnNum;
    }

    public Integer getResidentOutNum() {
        return residentOutNum;
    }

    public void setResidentOutNum(Integer residentOutNum) {
        this.residentOutNum = residentOutNum;
    }

    public Integer getInSchoolOutNum() {
        return inSchoolOutNum;
    }

    public void setInSchoolOutNum(Integer inSchoolOutNum) {
        this.inSchoolOutNum = inSchoolOutNum;
    }

    public Integer getResidentInNum() {
        return residentInNum;
    }

    public void setResidentInNum(Integer residentInNum) {
        this.residentInNum = residentInNum;
    }

    public Integer getInSchoolInNum() {
        return inSchoolInNum;
    }

    public void setInSchoolInNum(Integer inSchoolInNum) {
        this.inSchoolInNum = inSchoolInNum;
    }

    @Override
    public String toString() {
        return "PersonnelStatistics{" +
                "trainingSpeName='" + trainingSpeName + '\'' +
                ", sessionNumber='" + sessionNumber + '\'' +
                ", orgName='" + orgName + '\'' +
                ", lastResidentNum=" + lastResidentNum +
                ", residentNum=" + residentNum +
                ", lastInSchoolNum=" + lastInSchoolNum +
                ", inSchoolNum=" + inSchoolNum +
                ", residentRecruitNum=" + residentRecruitNum +
                ", inSchoolRecruitNum=" + inSchoolRecruitNum +
                ", residentGraduatNum=" + residentGraduatNum +
                ", inSchoolGraduatNum=" + inSchoolGraduatNum +
                ", residentExaminedNum=" + residentExaminedNum +
                ", inSchoolExaminedNum=" + inSchoolExaminedNum +
                ", residentReturnNum=" + residentReturnNum +
                ", inSchoolReturnNum=" + inSchoolReturnNum +
                ", residentOutNum=" + residentOutNum +
                ", inSchoolOutNum=" + inSchoolOutNum +
                ", residentInNum=" + residentInNum +
                ", inSchoolInNum=" + inSchoolInNum +
                ", pid='" + pid + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
