package com.pinde.sci.biz.sch;

import com.pinde.sci.form.sch.SchGradeFrom;
import com.pinde.core.model.ResDoctor;
import com.pinde.sci.model.mo.SchArrange;

import java.util.List;


public interface ISchArrangeBiz {

	List<SchArrange> searchSchArrange(String orgFLow);

	void rostering(String beginDate,boolean exact);

	List<ResDoctor> searchCouldSchDoctor(String orgFLow);

	SchArrange readArrange(String arrangeFlow);

	void modifyArrange(SchArrange arrange);

	int saveArrange(SchArrange arrange);

	List<ResDoctor> searchUnSchDoctor(String orgFLow);

	List<ResDoctor> searchFinishSchDoctor(String arrangeFlow);

	void abort(String arrangeFlow);

	List<SchGradeFrom> searchGradeByDoctorFlow(String schDeptFlow,String userFlow,String processFlow,String doctorFlow,String schStartDate,String schEndDate,String resultFlow);

	List<SchGradeFrom> searchGradeByOrgFlow(String doctorName,String processFlow,String orgFlow,String schStartDate,String schEndDate);


}
