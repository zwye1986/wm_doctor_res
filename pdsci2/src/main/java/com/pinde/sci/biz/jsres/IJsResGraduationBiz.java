package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.jsres.DoctorExamStatisticsExt;
import com.pinde.sci.model.mo.GraduationExamResults;
import com.pinde.sci.model.mo.SchExamDoctorArrangement;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IJsResGraduationBiz {

    List<Map<String,Object>> userList(Map<String, Object> param);

    List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param);

//    int clearScore(List<String> userFlows, String sessionNumber, String assessmentYear, String orgFlow);

//    SchExamDoctorArrangement readDoctorArrangementByFlow(String recordFlow);

    void exportInfo(List<Map<String, Object>> list, HttpServletResponse response) throws IOException;

    List<Map<String,Object>> findExamBydoctorFlow(String doctorFlow);

    GraduationExamResults getResultByFlow(String resultId);

    List<DoctorExamStatisticsExt> searchExamStatisticsList(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsList2(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsListBySpe(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsListByCity(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsListByCity2(Map<String, Object> param);

    List<Map<String, Object>> searchDoctorRecruit(Map<String, Object> params);

    List<Map<String, Object>> searchJointOrgDoctorRecruit(Map<String, Object> params);
}
