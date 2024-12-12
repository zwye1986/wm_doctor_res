package com.pinde.sci.model.jsres;


import com.pinde.core.model.SysOrg;
import com.pinde.sci.model.mo.JsresBaseEvaluation;

public class JsresBaseEvaluationExt extends JsresBaseEvaluation{
    private SysOrg sysOrg;
    private JsresBaseEvaluation jsresBaseEvaluation;

    public SysOrg getSysOrg() {
        return sysOrg;
    }

    public JsresBaseEvaluation getJsresBaseEvaluation() {
        return jsresBaseEvaluation;
    }

    public void setSysOrg(SysOrg sysOrg) {
        this.sysOrg = sysOrg;
    }

    public void setJsresBaseEvaluation(JsresBaseEvaluation jsresBaseEvaluation) {
        this.jsresBaseEvaluation = jsresBaseEvaluation;
    }
}
