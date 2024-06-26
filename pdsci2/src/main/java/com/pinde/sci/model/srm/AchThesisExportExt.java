package com.pinde.sci.model.srm;

import com.pinde.sci.model.mo.SrmAchThesis;

/**
 * Created by www.0001.Ga on 2016-12-14.
 */
public class AchThesisExportExt extends SrmAchThesis{
    private String authorDeptFlow;
    private String authorDeptName;
    private String userFlow;
    private String authorName;
    public String getAuthorDeptFlow() {
        return authorDeptFlow;
    }

    public void setAuthorDeptFlow(String authorDeptFlow) {
        this.authorDeptFlow = authorDeptFlow;
    }

    public String getAuthorDeptName() {
        return authorDeptName;
    }

    public void setAuthorDeptName(String authorDeptName) {
        this.authorDeptName = authorDeptName;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
