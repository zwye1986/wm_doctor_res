package com.pinde.core.common.form;


import com.pinde.core.model.SchArrangeResult;

public class SchGradeFrom extends SchArrangeResult implements java.io.Serializable {

    private String isHead;
    private String isTeacher;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIsHead() {
        return isHead;
    }

    public void setIsHead(String isHead) {
        this.isHead = isHead;
    }

    public String getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(String isTeacher) {
        this.isTeacher = isTeacher;
    }
}
