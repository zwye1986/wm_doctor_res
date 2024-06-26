package com.pinde.sci.model.xjgl;

import com.pinde.sci.model.mo.EduCourseMajor;

import java.util.List;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2017/8/31
 */
public class XjEduCourseMajorListExt extends EduCourseMajor {
    private String majorId;
    private String planYear;
    private List<EduCourseMajor> delDatas;
    private List<EduCourseMajor> addDatas;

    @Override
    public String getMajorId() {
        return majorId;
    }

    @Override
    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @Override
    public String getPlanYear() {
        return planYear;
    }

    @Override
    public void setPlanYear(String planYear) {
        this.planYear = planYear;
    }

    public List<EduCourseMajor> getDelDatas() {
        return delDatas;
    }

    public void setDelDatas(List<EduCourseMajor> delDatas) {
        this.delDatas = delDatas;
    }

    public List<EduCourseMajor> getAddDatas() {
        return addDatas;
    }

    public void setAddDatas(List<EduCourseMajor> addDatas) {
        this.addDatas = addDatas;
    }
}
