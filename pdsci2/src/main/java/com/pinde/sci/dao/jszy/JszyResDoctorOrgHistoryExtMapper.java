package com.pinde.sci.dao.jszy;

import com.pinde.core.model.JszyResDoctorOrgHistoryExt;

import java.util.List;
import java.util.Map;

public interface JszyResDoctorOrgHistoryExtMapper {

	List<JszyResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList(Map<String, Object> paramMap);

	List<Map<String,String>> searchChangeOrgInfoByParamMap(Map<String, Object> paramMap);

	List<Map<String,String>> searchDelayInfoByParamMap(Map<String, Object> paramMap);

	List<Map<String,String>> searchChangeSpeInfoByParamMap(Map<String, Object> paramMap);
}
