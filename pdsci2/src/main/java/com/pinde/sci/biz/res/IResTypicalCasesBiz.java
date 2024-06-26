package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResStudentDiscipleTeacher;
import com.pinde.sci.model.mo.ResTypicalCases;
import com.pinde.sci.model.res.ResDoctorExt;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/14.
 */
public interface IResTypicalCasesBiz {
    List<ResTypicalCases> searchTypicalCases(ResTypicalCases resTypicalCases);

    int saveResTypicalCases(ResTypicalCases resTypicalCases);

    ResTypicalCases findResTypicalCases(String caseFlow);

    List<ResDoctorExt> searchCasePendingAudit(Map<String, Object> beMap);

    int agreeRecordBatch(Map<String, String> paramMap);
}
