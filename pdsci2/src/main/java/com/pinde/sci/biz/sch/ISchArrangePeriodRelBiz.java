package com.pinde.sci.biz.sch;



import com.pinde.sci.model.mo.SchArrangePeriodRel;

import java.util.List;


public interface ISchArrangePeriodRelBiz {

	List<SchArrangePeriodRel> searchPeriodRelByDoctorFlow(String doctorFlow);

	SchArrangePeriodRel searchPeriodRel(String recordFlow);

	int insert(SchArrangePeriodRel periodRel,String doctorFlow);

	int delete(String recordFlow);

	int deleteByDoctorFlow(String doctorFlow);

}
