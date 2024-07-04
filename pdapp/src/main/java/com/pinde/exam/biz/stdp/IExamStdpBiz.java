package com.pinde.exam.biz.stdp;

import java.util.List;
import java.util.Map;

public interface IExamStdpBiz {

	Map<String, Object> login(String userCode, String userPasswd);

	List<Map<String, Object>> infoList(String userFlow,int pageIndex,int pageSize);

	List<Map<String, Object>> examList(String userFlow, String examType, Integer pageIndex, Integer pageSize);

	Map<String, Object> startExam(String userFlow, String examFlow);

//	List<Map<String, Object>> examDetails(String userFlow, String examFlow);

	Map<String, Object> endExam(String userFlow, String examFlow, String answerFlow);

	List<Map<String, Object>> answerDetails(String userFlow, String examFlow, String answerFlow);

	Map<String, Object> questionInfo(String questionFlow);

	List<Map<String, Object>> questionDetails(String questionFlow);

}  
  