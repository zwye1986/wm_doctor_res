package com.pinde.sci.form.xjgl;

public class XjPollingTableEducationForm {

    private String beginAndEndDate;//起止年月
    private String schoolAndUnit;//学校或单位
    private String postName;//职务
    private String unitAddress;//单位地址

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getSchoolAndUnit() {
        return schoolAndUnit;
    }

    public void setSchoolAndUnit(String schoolAndUnit) {
        this.schoolAndUnit = schoolAndUnit;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getBeginAndEndDate() {
        return beginAndEndDate;
    }

    public void setBeginAndEndDate(String beginAndEndDate) {
        this.beginAndEndDate = beginAndEndDate;
    }
}
