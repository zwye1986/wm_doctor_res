package com.pinde.res.biz.jswjw;

import com.pinde.core.model.SchExamDoctorArrangement;

import java.util.List;
import java.util.Map;


public interface ISchExamScoreQueryBiz {

    List<Map<String,Object>> userList(Map<String, Object> param);

    List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param);
}
