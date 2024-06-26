package com.pinde.sci.dao.res;

import com.pinde.sci.model.mo.ResAnnualAssessment;
import com.pinde.sci.model.res.AnnualAssessmentExt;

import java.util.List;
import java.util.Map;

public interface ResIdCtrlExtMapper {
    List<Map<String,Object>> doctorIdList(Map<String,Object> paramMap);
}
