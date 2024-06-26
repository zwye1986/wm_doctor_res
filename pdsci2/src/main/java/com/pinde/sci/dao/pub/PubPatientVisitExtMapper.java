package com.pinde.sci.dao.pub;

import com.pinde.sci.model.mo.PubPatientVisit;

import java.util.List;
import java.util.Map;

public interface PubPatientVisitExtMapper {
	List<PubPatientVisit> selectByModuleCode(Map<String,String> map);

	List<PubPatientVisit> searchPatientVisit(Map<String, String> paramMap);
}
