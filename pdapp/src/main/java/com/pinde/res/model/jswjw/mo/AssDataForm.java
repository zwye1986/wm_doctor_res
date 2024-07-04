package com.pinde.res.model.jswjw.mo;


import java.util.List;

public class AssDataForm {

    private String rotationType;
    private String standardDeptName;
    private String schMonth1;
    private String schMonth2;
    private String schDeptName;
    private String schDate1;
    private String schDate2;
    private String imageUrl;
    private String completeBi;
    private String outCompleteBi;
    private String auditBi;
    List<AssDataForm> childLists;

    public List<AssDataForm> getChildLists() {
        return childLists;
    }

    public void setChildLists(List<AssDataForm> childLists) {
        this.childLists = childLists;
    }

    public String getRotationType() {
        return rotationType;
    }

    public void setRotationType(String rotationType) {
        this.rotationType = rotationType;
    }

    public String getStandardDeptName() {
        return standardDeptName;
    }

    public void setStandardDeptName(String standardDeptName) {
        this.standardDeptName = standardDeptName;
    }

    public String getSchMonth1() {
        return schMonth1;
    }

    public void setSchMonth1(String schMonth1) {
        this.schMonth1 = schMonth1;
    }

    public String getSchMonth2() {
        return schMonth2;
    }

    public void setSchMonth2(String schMonth2) {
        this.schMonth2 = schMonth2;
    }

    public String getSchDeptName() {
        return schDeptName;
    }

    public void setSchDeptName(String schDeptName) {
        this.schDeptName = schDeptName;
    }

    public String getSchDate1() {
        return schDate1;
    }

    public void setSchDate1(String schDate1) {
        this.schDate1 = schDate1;
    }

    public String getSchDate2() {
        return schDate2;
    }

    public void setSchDate2(String schDate2) {
        this.schDate2 = schDate2;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCompleteBi() {
        return completeBi;
    }

    public void setCompleteBi(String completeBi) {
        this.completeBi = completeBi;
    }

    public String getOutCompleteBi() {
        return outCompleteBi;
    }

    public void setOutCompleteBi(String outCompleteBi) {
        this.outCompleteBi = outCompleteBi;
    }

    public String getAuditBi() {
        return auditBi;
    }

    public void setAuditBi(String auditBi) {
        this.auditBi = auditBi;
    }
}