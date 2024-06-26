package com.pinde.sci.dao.res;

import com.pinde.sci.model.mo.ResAnnualAssessment;
import com.pinde.sci.model.res.AnnualAssessmentExt;

import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface AnnualAssessmentExtMapper {

    AnnualAssessmentExt initAnnualAssessmentExt(Map<String,Object> paramMap);
    AnnualAssessmentExt initAssessmentExtByRecordFlow(Map<String,Object> paramMap);
}
