package com.pinde.sci.dao.hbzy;

import java.util.List;
import java.util.Map;

public interface HbzyGraduationApplyExtMapper {


    List<Map<String,Object>> chargeQueryListForExport(Map<String, Object> param);

    List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param);
}
