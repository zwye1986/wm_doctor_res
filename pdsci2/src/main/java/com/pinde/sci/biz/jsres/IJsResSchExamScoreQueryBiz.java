package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.SchExamDoctorArrangement;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IJsResSchExamScoreQueryBiz {

    List<Map<String,Object>> userList(Map<String, Object> param);

    List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param);

    int clearScore(List<String> userFlows, String sessionNumber, String assessmentYear, String orgFlow);

    SchExamDoctorArrangement readDoctorArrangementByFlow(String recordFlow);

    void exportInfo(List<Map<String, Object>> list, List<String> years, Map<String, SchExamDoctorArrangement> doctorArrangementMap, HttpServletResponse response) throws IOException;
}
