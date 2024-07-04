package com.pinde.sci.model.mo;

public class ResGraduationAssessmentWithBLOBs extends ResGraduationAssessment {
    private String experienceContent;

    private String auditContent;

    private String adminContent;

    public String getExperienceContent() {
        return experienceContent;
    }

    public void setExperienceContent(String experienceContent) {
        this.experienceContent = experienceContent;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    public String getAdminContent() {
        return adminContent;
    }

    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent;
    }
}