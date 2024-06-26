package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class EduMajorCredit extends MybatisObject {
    private String recordFlow;

    private String majorId;

    private String majorName;

    private String credit;

    private String degreeCredit;

    private String trainTypeId;

    private String trainTypeName;

    private String trainCategoryId;

    private String trainCategoryName;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDegreeCredit() {
        return degreeCredit;
    }

    public void setDegreeCredit(String degreeCredit) {
        this.degreeCredit = degreeCredit;
    }

    public String getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(String trainTypeId) {
        this.trainTypeId = trainTypeId;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }

    public String getTrainCategoryId() {
        return trainCategoryId;
    }

    public void setTrainCategoryId(String trainCategoryId) {
        this.trainCategoryId = trainCategoryId;
    }

    public String getTrainCategoryName() {
        return trainCategoryName;
    }

    public void setTrainCategoryName(String trainCategoryName) {
        this.trainCategoryName = trainCategoryName;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }
}