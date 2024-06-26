package com.pinde.sci.dao.exam;

import com.pinde.sci.model.mo.ExamBook;

import java.util.List;
import java.util.Map;

public interface ExamBookExtMapper {

    List<ExamBook> searchBookBySubject(String subjectFlow);

    List<ExamBook> searchBookByQuestion(String questionFlow);

    List<Map<String, Integer>> countQuestNumByQuestionTypeOfBook(String bookFlow);

//	public List<ExamBook> searchBookByBook(String bookFlow);

//	public List<Map<String, Integer>> countQuestNumByBookOfBook(String bookFlow);

//	public List<Map<String, Integer>> countQuestNumBySubjectOfBook(String bookFlow);

//	public List<ExamSubject> searchSubjectByBook(String bookFlow);

}
