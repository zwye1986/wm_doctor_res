package com.pinde.sci.dao.hbres;

import com.pinde.sci.form.hbres.ResDoctorTrainingSpeForm;
import com.pinde.sci.model.mo.ResExamDoctor;

import java.util.List;
import java.util.Map;

public interface HbresResOrgSpeAssignExtMapper {
	List<Map<String,Object>> getSpeAssignCount(Map<String,Object> paramMap);
}
