package com.pinde.core.model;

public class ResDoctorSchProcessEvalWithBLOBs extends ResDoctorSchProcessEval {
    private String formCfg;

    private String evalResult;

    public String getFormCfg() {
        return formCfg;
    }

    public void setFormCfg(String formCfg) {
        this.formCfg = formCfg;
    }

    public String getEvalResult() {
        return evalResult;
    }

    public void setEvalResult(String evalResult) {
        this.evalResult = evalResult;
    }
}