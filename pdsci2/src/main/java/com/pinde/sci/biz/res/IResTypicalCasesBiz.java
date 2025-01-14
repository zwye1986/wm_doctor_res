package com.pinde.sci.biz.res;

import com.pinde.core.model.ResTypicalCases;
import com.pinde.core.model.ResDoctorExt;

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
