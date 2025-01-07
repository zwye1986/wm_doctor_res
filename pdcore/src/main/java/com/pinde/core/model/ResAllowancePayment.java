package com.pinde.core.model;

public class ResAllowancePayment extends SchArrangeResult {

    private String teacherUserFlow;

    private String doctorFlow;

    private String teaCost;

    private String kmCost;

    private String headCost;

    private String speCost;

    private String schMonth;

    private String numberCount;

    private String trainingSpeId;

    private String trainingSpeName;

    public String getTeacherUserFlow() {
        return teacherUserFlow;
    }

    public void setTeacherUserFlow(String teacherUserFlow) {
        this.teacherUserFlow = teacherUserFlow;
    }

    @Override
    public String getDoctorFlow() {
        return doctorFlow;
    }

    @Override
    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getTeaCost() {
        return teaCost;
    }

    public void setTeaCost(String teaCost) {
        this.teaCost = teaCost;
    }

    public String getKmCost() {
        return kmCost;
    }

    public void setKmCost(String kmCost) {
        this.kmCost = kmCost;
    }

    public String getHeadCost() {
        return headCost;
    }

    public void setHeadCost(String headCost) {
        this.headCost = headCost;
    }

    public String getSpeCost() {
        return speCost;
    }

    public void setSpeCost(String speCost) {
        this.speCost = speCost;
    }

    @Override
    public String getSchMonth() {
        return schMonth;
    }

    @Override
    public void setSchMonth(String schMonth) {
        this.schMonth = schMonth;
    }

    public String getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(String numberCount) {
        this.numberCount = numberCount;
    }

    public String getTrainingSpeId() {
        return trainingSpeId;
    }

    public void setTrainingSpeId(String trainingSpeId) {
        this.trainingSpeId = trainingSpeId;
    }

    public String getTrainingSpeName() {
        return trainingSpeName;
    }

    public void setTrainingSpeName(String trainingSpeName) {
        this.trainingSpeName = trainingSpeName;
    }
}
