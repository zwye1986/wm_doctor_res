package com.pinde.res.dao.xnres.ext;

import com.pinde.res.model.xnres.mo.AnnualAssessmentExt;
import com.pinde.sci.model.mo.ResAnnualAssessment;

import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface XnresAnnualAssessmentExtMapper {

    AnnualAssessmentExt initAnnualAssessmentExt(ResAnnualAssessment assessment);

    int checkAnnualDate(Map<String, Object> paramMap);
}
