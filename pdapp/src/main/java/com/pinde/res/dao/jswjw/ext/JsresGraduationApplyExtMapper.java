package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.JsresGraduationInfo;

import java.util.List;
import java.util.Map;

public interface JsresGraduationApplyExtMapper {

    List<Map<String,Object>> chargeQueryList(Map<String, Object> param);

    List<Map<String,Object>> chargeQueryListForExport(Map<String, Object> param);

    List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param);

    List<JsresGraduationInfo> queryGraduationInfoList(Map<String, Object> param);

    List<JsresGraduationInfo> queryGraduationInfoListNew(Map<String, Object> param);

    List<Map<String,Object>> queryGraduationInfoListExport(Map<String, Object> param);

    List<Map<String,Object>> chargeQueryApplyListNew(Map<String, Object> param);

    List<Map<String,Object>> chargeQueryListForExport2(Map<String, Object> param);

    List<Map<String,Object>> chargeQueryApplyList2(Map<String, Object> param);

}
