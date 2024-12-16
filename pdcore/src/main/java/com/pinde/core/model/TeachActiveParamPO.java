package com.pinde.core.model;

import java.util.List;

/**
 * 教学活动情况pojo
 */
public class TeachActiveParamPO extends SysOrg {
    private Integer trainerSum;

    private Integer teachActiveSessionSum;

    private Integer allJoinSum;
    private Double averJoinSum;

    private Double averDureTime;

    private String averTeachActiveSessionSum;//教学活动举办场次和最高值比例

    private Double dureTime;//总时长

    private List<String> doctorFlowsInOrg;//当前学校在该基地的学生doctorFlow

    public List<String> getDoctorFlowsInOrg() {
        return doctorFlowsInOrg;
    }

    public void setDoctorFlowsInOrg(List<String> doctorFlowsInOrg) {
        this.doctorFlowsInOrg = doctorFlowsInOrg;
    }

    public Double getDureTime() {
        return dureTime;
    }

    public void setDureTime(Double dureTime) {
        this.dureTime = dureTime;
    }

    public String getAverTeachActiveSessionSum() {
        return averTeachActiveSessionSum;
    }

    public void setAverTeachActiveSessionSum(String averTeachActiveSessionSum) {
        this.averTeachActiveSessionSum = averTeachActiveSessionSum;
    }

    public Integer getTrainerSum() {
        return trainerSum;
    }

    public void setTrainerSum(Integer trainerSum) {
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
}
