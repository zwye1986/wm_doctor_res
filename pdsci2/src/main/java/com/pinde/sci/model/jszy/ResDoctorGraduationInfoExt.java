package com.pinde.sci.model.jszy;

import com.pinde.sci.model.mo.ResDoctorGraduationInfo;
import com.pinde.core.model.ResDoctorRecruitWithBLOBs;

/**
 * Created by Administrator on 2017/12/28.
 */
public class ResDoctorGraduationInfoExt extends ResDoctorGraduationInfo {
    private ResDoctorRecruitWithBLOBs recruit;

    public ResDoctorRecruitWithBLOBs getRecruit() {
        return recruit;
    }

    public void setRecruit(ResDoctorRecruitWithBLOBs recruit) {
        this.recruit = recruit;
    }
}
