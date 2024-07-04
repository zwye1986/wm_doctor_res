package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ResAnnualAssessmentRecord extends MybatisObject {
    private String annualAssessmentFlow;

    private String userName;

    private String userFlow;

    private String dailyFinishScore;

    private String trainingManualScore;

    private String professionalTheoryScore;

    private String skillName;

    private String skillScore;

    private String publicSkillsScore;

    private String approvedTotalScore;

    private String memo;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    private String materialUrl;

    private String materialName;

    private String assessmentYear;

    public String getAnnualAssessmentFlow() {
        return annualAssessmentFlow;
    }

    public void setAnnualAssessmentFlow(String annualAssessmentFlow) {
        this.annualAssessmentFlow = annualAssessmentFlow;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getDailyFinishScore() {
        return dailyFinishScore;
    }

    public void setDailyFinishScore(String dailyFinishScore) {
        this.dailyFinishScore = dailyFinishScore;
    }

    public String getTrainingManualScore() {
        return trainingManualScore;
    }

    public void setTrainingManualScore(String trainingManualScore) {
        this.trainingManualScore = trainingManualScore;
    }

    public String getProfessionalTheoryScore() {
        return professionalTheoryScore;
    }

    public void setProfessionalTheoryScore(String professionalTheoryScore) {
        this.professionalTheoryScore = professionalTheoryScore;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(String skillScore) {
        this.skillScore = skillScore;
    }

    public String getPublicSkillsScore() {
        return publicSkillsScore;
    }

    public void setPublicSkillsScore(String publicSkillsScore) {
        this.publicSkillsScore = publicSkillsScore;
    }

    public String getApprovedTotalScore() {
        return approvedTotalScore;
    }

    public void setApprovedTotalScore(String approvedTotalScore) {
        this.approvedTotalScore = approvedTotalScore;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }
}