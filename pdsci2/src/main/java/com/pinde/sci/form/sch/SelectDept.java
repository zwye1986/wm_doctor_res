package com.pinde.sci.form.sch;

import com.pinde.sci.model.mo.SchRotationDept;

import java.io.Serializable;
import java.util.List;

public class SelectDept implements Serializable{

	private static final long serialVersionUID = -7689352547548165240L;

	private String schDeptFlow;
	private String schDeptName;
	private String recordFlow;
	private String standardDeptName;
	private String schMonth;
	private String groupFlow;
	private String groupName;
	private String schDate;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupFlow() {
		return groupFlow;
	}

	public void setGroupFlow(String groupFlow) {
		this.groupFlow = groupFlow;
	}

	public String getSchDate() {
		return schDate;
	}

	public void setSchDate(String schDate) {
		this.schDate = schDate;
	}

	public String getStandardDeptName() {
		return standardDeptName;
	}

	public void setStandardDeptName(String standardDeptName) {
		this.standardDeptName = standardDeptName;
	}

	public String getSchDeptName() {
		return schDeptName;
	}

	public void setSchDeptName(String schDeptName) {
		this.schDeptName = schDeptName;
	}

	public String getSchDeptFlow() {
		return schDeptFlow;
	}

	public void setSchDeptFlow(String schDeptFlow) {
		this.schDeptFlow = schDeptFlow;
	}

	public String getRecordFlow() {
		return recordFlow;
	}

	public void setRecordFlow(String recordFlow) {
		this.recordFlow = recordFlow;
	}

	public String getSchMonth() {
		return schMonth;
	}

	public void setSchMonth(String schMonth) {
		this.schMonth = schMonth;
	}
}
