package com.pinde.res.dao.jszy.ext;

import com.pinde.core.model.ResAnnualAssessment;
import com.pinde.res.model.jszy.mo.AnnualAssessmentExt;

import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface AnnualAssessmentExtMapper {

    AnnualAssessmentExt initAnnualAssessmentExt(ResAnnualAssessment assessment);

    int checkAnnualDate(Map<String, Object> paramMap);
}
