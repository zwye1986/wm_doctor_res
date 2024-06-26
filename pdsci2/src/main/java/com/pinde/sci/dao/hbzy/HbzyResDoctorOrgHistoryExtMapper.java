package com.pinde.sci.dao.hbzy;

import com.pinde.sci.model.hbzy.JszyResDoctorOrgHistoryExt;

import java.util.List;
import java.util.Map;

public interface HbzyResDoctorOrgHistoryExtMapper {

	List<JszyResDoctorOrgHistoryExt> searchDoctorOrgHistoryExtList(Map<String, Object> paramMap);

	List<Map<String,String>> searchChangeOrgInfoByParamMap(Map<String, Object> paramMap);

	List<Map<String,String>> searchDelayInfoByParamMap(Map<String, Object> paramMap);

	List<Map<String,String>> searchChangeSpeInfoByParamMap(Map<String, Object> paramMap);
}
