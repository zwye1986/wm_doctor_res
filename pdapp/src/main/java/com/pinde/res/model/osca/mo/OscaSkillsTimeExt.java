package com.pinde.res.model.osca.mo;

import com.pinde.core.model.OscaSkillsAssessment;
import com.pinde.core.model.OscaSkillsAssessmentTime;

import java.util.List;


public class OscaSkillsTimeExt extends OscaSkillsAssessment {
    private List<OscaSkillsAssessmentTime> times;

    public List<OscaSkillsAssessmentTime> getTimes() {
        return times;
    }

    public void setTimes(List<OscaSkillsAssessmentTime> times) {
        this.times = times;
    }
}
