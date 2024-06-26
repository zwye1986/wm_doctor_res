package com.pinde.sci.biz.sch;

import com.pinde.sci.model.mo.ResScore;
import com.pinde.sci.model.mo.SchExamDoctorArrangement;
import com.pinde.sci.model.res.ResDoctorExt;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface ISchExamScoreQueryBiz {

    List<Map<String,Object>> userList(Map<String, Object> param);

    List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param);

    SchExamDoctorArrangement readDoctorArrangementByFlow(String recordFlow);

    void exportInfo(List<ResDoctorExt> list, List<String> years, Map<String, SchExamDoctorArrangement> doctorArrangementMap, HttpServletResponse response) throws IOException;

    List<ResScore> getDoctorSkillScore(Map<String, Object> param);

    void exportSkillScoreInfo(List<ResDoctorExt> list, List<String> years, Map<String, ResScore> doctorArrangementMap, HttpServletResponse response) throws IOException;
}
