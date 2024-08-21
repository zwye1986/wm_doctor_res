package com.pinde.sci.form.sch;

import java.io.Serializable;
import java.util.List;

public class SelectDeptForm implements Serializable{
	
	private static final long serialVersionUID = 3862460508039897169L;
	
	private List<SelectDept> depts;

	public List<SelectDept> getDepts() {
		return depts;
	}

	public void setDepts(List<SelectDept> depts) {
		this.depts = depts;
	}
}
