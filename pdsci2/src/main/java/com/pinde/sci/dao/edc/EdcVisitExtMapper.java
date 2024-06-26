package com.pinde.sci.dao.edc;

import com.pinde.sci.form.edc.EdcVisitForm;
import com.pinde.sci.model.mo.EdcVisit;

import java.util.List;
import java.util.Map;



public interface EdcVisitExtMapper {
	List<EdcVisit> searchVisitsByGroupFlow(Map<String,Object> paramMap);

	List<EdcVisitForm> searchVisitsByModuleCode(Map<String, Object> paramMap);

	List<EdcVisitForm> searchVisitsByElementCode(Map<String, Object> paramMap); 
}