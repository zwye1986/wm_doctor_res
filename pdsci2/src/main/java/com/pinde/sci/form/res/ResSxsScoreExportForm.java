package com.pinde.sci.form.res;

import com.pinde.sci.model.mo.ResDoctorSchProcess;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-11-16.
 */
public class ResSxsScoreExportForm implements java.io.Serializable {
    private static final long serialVersionUID = 2697698680458967809L;

    private String userName;
    private String userFlow;
    private Map<String,ResDoctorSchProcess> map;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public Map<String, ResDoctorSchProcess> getMap() {
        return map;
    }

    public void setMap(Map<String, ResDoctorSchProcess> map) {
        this.map = map;
    }
}
