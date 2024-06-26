package com.pinde.sci.form.sch;


import com.pinde.sci.model.mo.DeptTeacherGradeInfo;
import com.pinde.sci.model.mo.SchArrangeResult;

import java.io.Serializable;

public class SchGradeFrom extends SchArrangeResult implements Serializable {

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
