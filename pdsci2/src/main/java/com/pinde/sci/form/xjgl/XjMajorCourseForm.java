package com.pinde.sci.form.xjgl;


import com.pinde.sci.model.xjgl.XjEduCourseMajorExt;

import java.util.List;

public class XjMajorCourseForm {

    private String MajorId;

    private String MajorName;

    private List<XjEduCourseMajorExt> courseMajorExtList;


    public String getMajorId() {
        return MajorId;
    }

    public void setMajorId(String majorId) {
        MajorId = majorId;
    }

    public String getMajorName() {
        return MajorName;
    }

    public void setMajorName(String majorName) {
        MajorName = majorName;
    }

    public List<XjEduCourseMajorExt> getCourseMajorExtList() {
        return courseMajorExtList;
    }

    public void setCourseMajorExtList(List<XjEduCourseMajorExt> courseMajorExtList) {
        this.courseMajorExtList = courseMajorExtList;
    }


}
