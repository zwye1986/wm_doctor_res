package com.pinde.exam.dao.stdp.ext;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ExamStdpMapper {
	
	List<Map<String,Object>> login(@Param("userCode")String userCode);

	List<Map<String, Object>> infoList(@Param("userFlow")String userFlow,@Param("start")int start, @Param("end")int end);

	List<Map<String, Object>> examList(@Param("userFlow")String userFlow, @Param("examType")String examType,@Param("start")int start, @Param("end")int end);

	Map<String, Object> examInfo(@Param("examFlow")String examFlow);

	List<Map<String, Object>> examDetails(@Param("userFlow")String userFlow, @Param("examFlow")String examFlow);
	
	int addAnswerInfo(Map<String,Object> param);

	int endExam(Map<String, Object> param);

	Map<String, Object> answerInfo(@Param("answerFlow")String answerFlow);

	List<Map<String, Object>> answerDetails(String userFlow, String examFlow, @Param("answerFlow")String answerFlow);

	void addAnswerDetail(Map<String, Object> detailParam);

	Map<String, Object> questionInfo(@Param("questionFlow")String questionFlow);

	List<Map<String, Object>> questionDetails(@Param("questionFlow")String questionFlow);
}
