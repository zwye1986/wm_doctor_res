package com.pinde.sci.biz.sch;

import com.pinde.sci.form.sch.DoctorSearchForm;
import com.pinde.sci.model.mo.ResDoctor;

import java.util.List;
import java.util.Map;


public interface ISchDoctorBiz {
	
	List<ResDoctor> searchResDoctor(String orgFlow);
	
	ResDoctor readResDoctor(String doctorFlow);
	
	int saveResDoctor(ResDoctor doctor);

	List<ResDoctor> searchResDoctor(String orgFlow,
			DoctorSearchForm doctorSearchForm);

	List<ResDoctor> searchTerminatResDoctor(String orgFlow,
			DoctorSearchForm doctorSearchForm);

	List<ResDoctor> searchNotTerminatResDoctor(String orgFlow);

//	List<ResDoctor> searchResDoctorByFlows(String orgFlow,
//			List<String> doctorFlowList);

	List<ResDoctor> searchResDoctorAll(String orgFlow,
			DoctorSearchForm doctorSearchForm);

	List<ResDoctor> searchResDoctor2(String orgFlow, DoctorSearchForm doctorSearchForm, String medicineTypeId);

	List<ResDoctor> searchResDoctor2(Map<String,Object> map);

//    List<ResDoctorExt> searchResDoctor(ResDoctorExt doctor);
}
