package com.pinde.sci.biz.sch;

import com.pinde.sci.model.mo.SchArrangeDoctorDept;

import java.util.List;



public interface ISchArrangeDoctorDeptBiz {
	List<SchArrangeDoctorDept> searchSchArrangeDoctorDept();
	
	SchArrangeDoctorDept readySchArrangeDoctorDept(String arrDocDeptFlow);
	
	int saveSchArrangeDoctorDept(SchArrangeDoctorDept arrangeDoctorDept);
}
