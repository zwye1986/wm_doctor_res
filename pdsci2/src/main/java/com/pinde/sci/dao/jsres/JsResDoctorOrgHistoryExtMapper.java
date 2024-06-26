package com.pinde.sci.dao.jsres;

import com.pinde.sci.model.jsres.JsResDoctorOrgHistoryExt;

import java.util.List;
import java.util.Map;

public interface JsResDoctorOrgHistoryExtMapper {

	List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList(Map<String, Object> paramMap);

	List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtListNew(Map<String, Object> paramMap);

	/****************************高******校******管******理******员******角******色************************************************/

	List<JsResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtListForUni(Map<String, Object> paramMap);
}
