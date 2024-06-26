package com.pinde.sci.dao.exam;

import com.pinde.sci.model.mo.ExamSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExamSubjectExtMapper {

	List<ExamSubject> searchSubjectByQuestion(String questionFlow);

//	public List<ExamSubject> searchSubjectBySubject(String subjectFlow);

	List<Map<String, Integer>> countQuestNumByQuestionTypeOfSubject(String subjectFlow);

//	public List<Map<String,Integer>> countQuestNumBySubjectOfSubject(String subjectFlow);

	List<Map<String, Integer>> countQuestNumByBookOfSubject(String subjectFlow);

	int setenabled(@Param("enabledtype") String enabledtype, @Param("subjectFlow") String subjectFlow);
}
