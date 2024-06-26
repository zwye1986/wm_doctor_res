package com.pinde.sci.dao.hbres;

import java.util.List;
import java.util.Map;

public interface HbresGraduationApplyExtMapper {

    List<Map<String,Object>> chargeQueryListForExport(Map<String, Object> param);

    List<Map<String,Object>> chargeQueryApplyList(Map<String, Object> param);
}
