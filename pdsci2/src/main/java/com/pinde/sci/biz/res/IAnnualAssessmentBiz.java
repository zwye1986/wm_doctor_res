package com.pinde.sci.biz.res;

import com.pinde.core.model.ResAnnualAssessment;
import com.pinde.core.model.ResAnnualAssessmentWithBLOBs;
import com.pinde.sci.model.res.AnnualAssessmentExt;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-19.
 */
public interface IAnnualAssessmentBiz {

    AnnualAssessmentExt initAnnualAssessmentExt(Map<String,Object> paramMap);

    List<ResAnnualAssessment> findAnnualAssessmentList(ResAnnualAssessment assessment,List<String> statusIdList);

    int editAnnualAssessment(ResAnnualAssessmentWithBLOBs assessmentWithBLOBs);

    int delAnnualAssessment(String recordFlow);

    int  oneKeyAudit(String teacherFlow);


    List<ResAnnualAssessment> findAnnualAssessmentByDocFlow(String doctorFlow);
}
