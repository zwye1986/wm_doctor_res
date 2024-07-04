package com.pinde.res.model.osca.mo;

import com.pinde.sci.model.mo.OscaDoctorAssessment;
import com.pinde.sci.model.mo.OscaSkillsAssessment;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;


public class OscaDoctorSkillsExt extends OscaSkillsAssessment {
    private List<OscaDoctorAssessment> doctorAssessments;

    public List<OscaDoctorAssessment> getDoctorAssessments() {
        return doctorAssessments;
    }

    public void setDoctorAssessments(List<OscaDoctorAssessment> doctorAssessments) {
        this.doctorAssessments = doctorAssessments;
    }
}
