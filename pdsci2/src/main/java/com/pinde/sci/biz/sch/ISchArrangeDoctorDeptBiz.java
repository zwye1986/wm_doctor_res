package com.pinde.sci.biz.sch;

import com.pinde.core.model.SchArrangeDoctorDept;

import java.util.List;



public interface ISchArrangeDoctorDeptBiz {
	List<SchArrangeDoctorDept> searchSchArrangeDoctorDept();
	
	SchArrangeDoctorDept readySchArrangeDoctorDept(String arrDocDeptFlow);
	
	int saveSchArrangeDoctorDept(SchArrangeDoctorDept arrangeDoctorDept);
}
