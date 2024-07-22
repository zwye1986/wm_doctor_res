package com.pinde.sci.model.osca;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;

/**
 * @author Administrator
 */
public class OscaExaminerExt extends SysUser{

    private List<OscaTypeSpeExt> typeSpeList;
    private ResDoctor resDoctor;

    private String trainingTypeName;
    private String trainingSpeName;

    public String getTrainingTypeName() {
        return trainingTypeName;
    }

    public void setTrainingTypeName(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }

    public String getTrainingSpeName() {
        return trainingSpeName;
    }

    public void setTrainingSpeName(String trainingSpeName) {
        this.trainingSpeName = trainingSpeName;
    }

    public ResDoctor getResDoctor() {
        return resDoctor;
    }
    public void setResDoctor(ResDoctor resDoctor) {
        this.resDoctor = resDoctor;
    }



    public List<OscaTypeSpeExt> getTypeSpeList() {
        return typeSpeList;
    }

    public void setTypeSpeList(List<OscaTypeSpeExt> typeSpeList) {
        this.typeSpeList = typeSpeList;
    }
}
