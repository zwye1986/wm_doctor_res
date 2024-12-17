package com.pinde.sci.form.res;

import com.pinde.core.model.ResEvaluationDept;

public class ResEvaluationDeptExt extends ResEvaluationDept implements java.io.Serializable {

	private static final long serialVersionUID = -5631934699990814967L;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    private String deptName;

}
