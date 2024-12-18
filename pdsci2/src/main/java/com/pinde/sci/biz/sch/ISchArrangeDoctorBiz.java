package com.pinde.sci.biz.sch;

import com.pinde.core.model.SchArrangeDoctor;

import java.util.List;


public interface ISchArrangeDoctorBiz {
	List<SchArrangeDoctor> searchSchArrangeDoctor();
	
	SchArrangeDoctor readSchArrangeDoctor(String arrDocFlow);
	
	int saveSchArrangeDoctor(SchArrangeDoctor arrangeDoctor);
}
