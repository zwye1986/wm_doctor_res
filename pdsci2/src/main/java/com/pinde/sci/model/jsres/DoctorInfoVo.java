package com.pinde.sci.model.jsres;

import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.core.model.SysUser;

import java.io.Serializable;

/**
 * @创建人 zsq
 * @创建时间 2021/5/28
 * 描述
 */
public class DoctorInfoVo implements java.io.Serializable {

    private SysUser user;

    private ResDoctor doctor;

    private UserResumeExtInfoForm userResumeExt;

    private boolean isPassed;

    private String canSave;

    private String isDoctor;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public ResDoctor getDoctor() {
        return doctor;
    }

    public void setDoctor(ResDoctor doctor) {
        this.doctor = doctor;
    }

    public UserResumeExtInfoForm getUserResumeExt() {
        return userResumeExt;
    }

    public void setUserResumeExt(UserResumeExtInfoForm userResumeExt) {
        this.userResumeExt = userResumeExt;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public String getCanSave() {
        return canSave;
    }

    public void setCanSave(String canSave) {
        this.canSave = canSave;
    }

    public String getIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(String isDoctor) {
        this.isDoctor = isDoctor;
    }
}
