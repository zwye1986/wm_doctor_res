package com.pinde.sci.model.jsres;

import com.pinde.sci.model.mo.ResDoctorRecruit;

import java.io.Serializable;
import java.util.List;

/**
 * @创建人 zsq
 * @创建时间 2021/6/3
 * 描述
 */
public class DoctorRecruitVo implements Serializable {
    private ResDoctorRecruit doctorRecruit;
    private String addRecord;
    private String speId;
    private String speName;
    private String orgFlow;
    private String jointOrgFlow;
    private String jointOrgName;
    private String inJointOrgTrain;
    private String orgName;
    private String placeId;
    private String placeName;
    private String assignYear;
    private List<ResDoctorRecruit> passedRecruitList;
    private Boolean firstRecIsWMSecond;
    private Boolean isWMSecondRecPassed;
    private List<String> catSpeIdList;
    private List<ResDoctorRecruit> prevPassedList;
    private ResDoctorRecruit latestPrevPassed;

    public ResDoctorRecruit getDoctorRecruit() {
        return doctorRecruit;
    }

    public void setDoctorRecruit(ResDoctorRecruit doctorRecruit) {
        this.doctorRecruit = doctorRecruit;
    }

    public String getAddRecord() {
        return addRecord;
    }

    public void setAddRecord(String addRecord) {
        this.addRecord = addRecord;
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

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
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

    public String getInJointOrgTrain() {
        return inJointOrgTrain;
    }

    public void setInJointOrgTrain(String inJointOrgTrain) {
        this.inJointOrgTrain = inJointOrgTrain;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAssignYear() {
        return assignYear;
    }

    public void setAssignYear(String assignYear) {
        this.assignYear = assignYear;
    }

    public List<ResDoctorRecruit> getPassedRecruitList() {
        return passedRecruitList;
    }

    public void setPassedRecruitList(List<ResDoctorRecruit> passedRecruitList) {
        this.passedRecruitList = passedRecruitList;
    }

    public Boolean getFirstRecIsWMSecond() {
        return firstRecIsWMSecond;
    }

    public void setFirstRecIsWMSecond(Boolean firstRecIsWMSecond) {
        this.firstRecIsWMSecond = firstRecIsWMSecond;
    }

    public Boolean getWMSecondRecPassed() {
        return isWMSecondRecPassed;
    }

    public void setWMSecondRecPassed(Boolean WMSecondRecPassed) {
        isWMSecondRecPassed = WMSecondRecPassed;
    }

    public List<String> getCatSpeIdList() {
        return catSpeIdList;
    }

    public void setCatSpeIdList(List<String> catSpeIdList) {
        this.catSpeIdList = catSpeIdList;
    }

    public List<ResDoctorRecruit> getPrevPassedList() {
        return prevPassedList;
    }

    public void setPrevPassedList(List<ResDoctorRecruit> prevPassedList) {
        this.prevPassedList = prevPassedList;
    }

    public ResDoctorRecruit getLatestPrevPassed() {
        return latestPrevPassed;
    }

    public void setLatestPrevPassed(ResDoctorRecruit latestPrevPassed) {
        this.latestPrevPassed = latestPrevPassed;
    }
}
