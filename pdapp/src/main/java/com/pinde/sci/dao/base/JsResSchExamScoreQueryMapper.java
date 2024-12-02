package com.pinde.sci.dao.base;


import com.pinde.core.model.SchExamDoctorArrangement;

import java.util.List;
import java.util.Map;

public interface JsResSchExamScoreQueryMapper {

	List<Map<String,Object>> userList(Map<String, Object> param);

	List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param);
}
