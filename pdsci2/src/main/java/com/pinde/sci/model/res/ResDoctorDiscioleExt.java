package com.pinde.sci.model.res;

import com.pinde.core.model.ResDoctor;

/**
 * Created by www.0001.Ga on 2016-10-24.
 */
public class ResDoctorDiscioleExt extends ResDoctor {
    private String discipleStartDate;
    private String discipleEndDate;

    public String getDiscipleStartDate() {
        return discipleStartDate;
    }

    public void setDiscipleStartDate(String discipleStartDate) {
        this.discipleStartDate = discipleStartDate;
    }

    public String getDiscipleEndDate() {
        return discipleEndDate;
    }

    public void setDiscipleEndDate(String discipleEndDate) {
        this.discipleEndDate = discipleEndDate;
    }
}
