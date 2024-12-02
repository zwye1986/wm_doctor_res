package com.pinde.res.model.osca.mo;

import com.pinde.core.model.OscaDoctorAssessment;
import com.pinde.core.model.OscaSkillsAssessment;

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
