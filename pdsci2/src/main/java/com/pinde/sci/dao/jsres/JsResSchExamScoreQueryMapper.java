package com.pinde.sci.dao.jsres;

import com.pinde.sci.model.mo.SchExamDoctorArrangement;

import java.util.List;
import java.util.Map;

public interface JsResSchExamScoreQueryMapper {

	List<Map<String,Object>> userList(Map<String, Object> param);

	List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param);
}
