package com.pinde.sci.biz.jszy;

import com.pinde.sci.model.mo.ResDoctorReduction;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/11/28.
 */
public interface IJszyDoctorReductionBiz {

    ResDoctorReduction findReductionByRecruitFlow(String recruitFlow);

    List<ResDoctorReduction> findReductionByMap(Map<String, Object> paramMap);

    int edit(ResDoctorReduction resDoctorReduction);

    ResDoctorReduction findReductionByPK(String recordFlow);

    List<Map<String,String>> findReductionExtByMap(Map<String, Object> paramMap);
    List<Map<String,String>> findReductionExtByMapAcc(Map<String, Object> paramMap);

    int updateRecruitAndDoctorInfo(ResDoctorReduction reduction);

    ResDoctorReduction findReductionByRecruitFlowandStatusId(String recruitFlow,String auditStatusId);
}
