package com.pinde.sci.form.sch;

import com.pinde.core.model.SchArrangeDoctor;
import com.pinde.core.model.SchArrangeDoctorDept;
import com.pinde.core.model.SchArrangeResult;

import java.util.List;

public class SchArrangeResultForm implements java.io.Serializable {
	private static final long serialVersionUID = 8256273931760685022L;
	
	private SchArrangeDoctor arrDoc;
	private List<SchArrangeDoctorDept> arrDocDeptList;
	private List<SchArrangeResult> resultList;
	
	public SchArrangeDoctor getArrDoc() {
		return arrDoc;
	}
	public void setArrDoc(SchArrangeDoctor arrDoc) {
		this.arrDoc = arrDoc;
	}
	public List<SchArrangeResult> getResultList() {
		return resultList;
	}
	public void setResultList(List<SchArrangeResult> resultList) {
		this.resultList = resultList;
	}
	public List<SchArrangeDoctorDept> getArrDocDeptList() {
		return arrDocDeptList;
	}
	public void setArrDocDeptList(List<SchArrangeDoctorDept> arrDocDeptList) {
		this.arrDocDeptList = arrDocDeptList;
	}
	
}
