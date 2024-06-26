package com.pinde.sci.form.jsres.BaseSpeDept;

import java.io.Serializable;

public class DocSchlAndSpeForm implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 学校
     */
    private String school;
    /**
     * 专业
     */
    private String spe;


    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSpe() {
        return spe;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }
}
