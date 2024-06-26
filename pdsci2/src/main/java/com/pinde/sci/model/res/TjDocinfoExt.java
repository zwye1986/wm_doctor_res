package com.pinde.sci.model.res;

import com.pinde.sci.model.mo.TjDocinfo;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
public class TjDocinfoExt extends TjDocinfo {
    private String docroleName;//角色
    private String siteOneName;//一站考点
    private String siteTwoName;//二站考点
    private String siteThreeName;//三站考点
    private String siteOneStateName;//一站通过
    private String siteTwoStateName;//二站通过
    private String siteThreeStateName;//三站通过
    private String totalScoreStateName;//总成绩通过

    public String getDocroleName() {
        return docroleName;
    }

    public void setDocroleName(String docroleName) {
        this.docroleName = docroleName;
    }

    public String getSiteOneName() {
        return siteOneName;
    }

    public void setSiteOneName(String siteOneName) {
        this.siteOneName = siteOneName;
    }

    public String getSiteTwoName() {
        return siteTwoName;
    }

    public void setSiteTwoName(String siteTwoName) {
        this.siteTwoName = siteTwoName;
    }

    public String getSiteThreeName() {
        return siteThreeName;
    }

    public void setSiteThreeName(String siteThreeName) {
        this.siteThreeName = siteThreeName;
    }

    public String getSiteOneStateName() {
        return siteOneStateName;
    }

    public void setSiteOneStateName(String siteOneStateName) {
        this.siteOneStateName = siteOneStateName;
    }

    public String getSiteTwoStateName() {
        return siteTwoStateName;
    }

    public void setSiteTwoStateName(String siteTwoStateName) {
        this.siteTwoStateName = siteTwoStateName;
    }

    public String getSiteThreeStateName() {
        return siteThreeStateName;
    }

    public void setSiteThreeStateName(String siteThreeStateName) {
        this.siteThreeStateName = siteThreeStateName;
    }

    public String getTotalScoreStateName() {
        return totalScoreStateName;
    }

    public void setTotalScoreStateName(String totalScoreStateName) {
        this.totalScoreStateName = totalScoreStateName;
    }
}
