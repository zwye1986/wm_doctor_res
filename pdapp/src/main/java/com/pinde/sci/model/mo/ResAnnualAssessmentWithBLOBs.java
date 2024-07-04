package com.pinde.sci.model.mo;

public class ResAnnualAssessmentWithBLOBs extends ResAnnualAssessment {
    private String compleleContent;

    private String experienceContent;

    private String auditContent;

    private String adminContent;

    public String getCompleleContent() {
        return compleleContent;
    }

    public void setCompleleContent(String compleleContent) {
        this.compleleContent = compleleContent;
    }

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