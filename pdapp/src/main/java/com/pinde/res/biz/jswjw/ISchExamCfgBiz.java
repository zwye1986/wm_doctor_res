package com.pinde.res.biz.jswjw;

import com.pinde.core.model.ResDoctorGraduationExam;
import com.pinde.core.model.SchExamArrangement;
import com.pinde.core.model.SchExamDoctorArrangement;

import java.util.List;
import java.util.Map;


public interface ISchExamCfgBiz {
    List<SchExamArrangement> searchList(SchExamArrangement schExamArrangement);

    SchExamArrangement readByFlow(String arrangeFlow);
	/**
	 * 根据arrangeFlow和doctorFlow查询医师考试的记录
	 * @param paramMap
	 * @return
     */
	List<Map<String,String>> searchExamLogByItems(Map<String, String> paramMap);


    int findDocExamCount(String userFlow, String arrangeFlow);

    Map<String,Object> getSuiJiConfig(SchExamArrangement ment, String userFlow);

    Map<String,Object> getGuDingConfig(SchExamArrangement ment);

    int save(SchExamDoctorArrangement schExamDoctorArrangement);

    int saveGraduationExam(ResDoctorGraduationExam result);
}