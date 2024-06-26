package com.pinde.sci.model.mo;

public class EduCourseMaterialWithBLOBs extends EduCourseMaterial {
    private String outlineContent;

    private String planContent;

    public String getOutlineContent() {
        return outlineContent;
    }

    public void setOutlineContent(String outlineContent) {
        this.outlineContent = outlineContent;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }
}