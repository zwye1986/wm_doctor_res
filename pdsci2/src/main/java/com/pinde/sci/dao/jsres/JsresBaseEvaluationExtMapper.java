package com.pinde.sci.dao.jsres;


import com.pinde.core.model.JsresBaseEvaluation;

import java.util.List;
import java.util.Map;

public interface JsresBaseEvaluationExtMapper {
    List<JsresBaseEvaluation> searchEvaluationExtList(Map<String, Object> paramMap);

    List<JsresBaseEvaluation> searchJointEvaluationExtList(List<String> jointOrgFlowList);
}
