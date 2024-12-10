package com.pinde.core.vo;


import com.pinde.core.model.ResOrgSpe;

import java.util.List;

/**
 * 专业基地清单页面数据：统计汇总数据
  */
public class ResOrgSepVO extends ResOrgSpe {
    /**
     * 当年住院医师
     */
    private String curInHospitalDoctors;

    /**
     * 当年在校专硕
     */
    private String curInCollegeMasters;

    /**
     * 所有在培住院医师
     */
    private String inHospitalDoctors;

    /**
     * 所有在培在校专硕
     */
    private String inCollegeMasters;

    /**
     * 在培人员总数
     */
    private String inTrains;

    /**
     * 培训总容量
     */
    private String trainingCapacity;

    /**
     * 增训容量使用率
     */
    private String trainingCapacityUsePer;

    /**
     * 开放专业基地的医院数量
     */
    private String openSpeBases;

    /**
     *
     */
    private List<String> orgFlowList;

    /**
     * 基地编码
     */
    private String baseCode;

    private List<ResOrgSepVO> jointOrgList;

    public String getCurInHospitalDoctors() {
        return curInHospitalDoctors;
    }

    public void setCurInHospitalDoctors(String curInHospitalDoctors) {
        this.curInHospitalDoctors = curInHospitalDoctors;
    }

    public String getCurInCollegeMasters() {
        return curInCollegeMasters;
    }

    public void setCurInCollegeMasters(String curInCollegeMasters) {
        this.curInCollegeMasters = curInCollegeMasters;
    }

    public String getInHospitalDoctors() {
        return inHospitalDoctors;
    }

    public void setInHospitalDoctors(String inHospitalDoctors) {
        this.inHospitalDoctors = inHospitalDoctors;
    }

    public String getInCollegeMasters() {
        return inCollegeMasters;
    }

    public void setInCollegeMasters(String inCollegeMasters) {
        this.inCollegeMasters = inCollegeMasters;
    }

    public String getInTrains() {
        return inTrains;
    }

    public void setInTrains(String inTrains) {
        this.inTrains = inTrains;
    }

    public String getTrainingCapacity() {
        return trainingCapacity;
    }

    public void setTrainingCapacity(String trainingCapacity) {
        this.trainingCapacity = trainingCapacity;
    }

    public String getTrainingCapacityUsePer() {
        return trainingCapacityUsePer;
    }

    public void setTrainingCapacityUsePer(String trainingCapacityUsePer) {
        this.trainingCapacityUsePer = trainingCapacityUsePer;
    }

    public String getOpenSpeBases() {
        return openSpeBases;
    }

    public void setOpenSpeBases(String openSpeBases) {
        this.openSpeBases = openSpeBases;
    }

    public List<String> getOrgFlowList() {
        return orgFlowList;
    }

    public void setOrgFlowList(List<String> orgFlowList) {
        this.orgFlowList = orgFlowList;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public List<ResOrgSepVO> getJointOrgList() {
        return jointOrgList;
    }

    public void setJointOrgList(List<ResOrgSepVO> jointOrgList) {
        this.jointOrgList = jointOrgList;
    }
}
