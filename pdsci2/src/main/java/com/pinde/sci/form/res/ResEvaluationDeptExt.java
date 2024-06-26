package com.pinde.sci.form.res;

import com.pinde.sci.model.mo.ResEvaluationDept;

import java.io.Serializable;
import java.util.List;

public class ResEvaluationDeptExt extends ResEvaluationDept implements Serializable{

	private static final long serialVersionUID = -5631934699990814967L;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    private String deptName;

}
