package com.pinde.sci.model.mo;

import java.util.List;

/**
 * 学员出科情况pojo
 */
public class DoctorOutOfficeParamPO extends SysOrg  {
    private Integer monthOutOfficeSum; //本月应出科人数

    private Integer monthActualOutOfficeSum; //本月实际出科人数

    private Integer monthNotOutOfficeSum; //本月未出科人数

    private String outOfficeExceptionRate; //出科异常比例

    private String outOfficeDataFinishRate; //出科学员数据平均完成率

    private List<String> doctorFlows; //暂存实际出科人的doctorFlow

    private List<String> doctorFlowsInOrg;//当前学校在该基地的学生doctorFlow

    public List<String> getDoctorFlowsInOrg() {
        return doctorFlowsInOrg;
    }

    public void setDoctorFlowsInOrg(List<String> doctorFlowsInOrg) {
        this.doctorFlowsInOrg = doctorFlowsInOrg;
    }

    public List<String> getDoctorFlows() {
        return doctorFlows;
    }

    public void setDoctorFlows(List<String> doctorFlows) {
        this.doctorFlows = doctorFlows;
    }

    public Integer getMonthOutOfficeSum() {
        return monthOutOfficeSum;
    }

    public void setMonthOutOfficeSum(Integer monthOutOfficeSum) {
        this.monthOutOfficeSum = monthOutOfficeSum;
    }

    public Integer getMonthActualOutOfficeSum() {
        return monthActualOutOfficeSum;
    }

    public void setMonthActualOutOfficeSum(Integer monthActualOutOfficeSum) {
        this.monthActualOutOfficeSum = monthActualOutOfficeSum;
    }

    public Integer getMonthNotOutOfficeSum() {
        return monthNotOutOfficeSum;
    }

    public void setMonthNotOutOfficeSum(Integer monthNotOutOfficeSum) {
        this.monthNotOutOfficeSum = monthNotOutOfficeSum;
    }

    public String getOutOfficeExceptionRate() {
        return outOfficeExceptionRate;
    }

    public void setOutOfficeExceptionRate(String outOfficeExceptionRate) {
        this.outOfficeExceptionRate = outOfficeExceptionRate;
    }

    public String getOutOfficeDataFinishRate() {
        return outOfficeDataFinishRate;
    }

    public void setOutOfficeDataFinishRate(String outOfficeDataFinishRate) {
        this.outOfficeDataFinishRate = outOfficeDataFinishRate;
    }
}
