package com.pinde.sci.biz.exam;

import com.pinde.sci.model.exam.ExamQuestionExt;
import com.pinde.sci.model.mo.ExamBookImp;
import com.pinde.sci.model.mo.ExamQuestionWithBLOBs;

import java.util.List;
import java.util.Map;

public interface IQuestionManageBiz {

//	public List<ExamQuestionWithBLOBs> searchExamQuestionBySubjectQuestionType(String subjectFlow,String questionTypeId);

    List<ExamQuestionWithBLOBs> searchExamQuestionBySubjectSubject(String subjectFlow, String subjectFlow2);

    List<ExamQuestionWithBLOBs> searchExamQuestionBySubjectBook(String subjectFlow, String bookFlow);

//	public List<ExamQuestionWithBLOBs> searchExamQuestionByBookQuestionType(String bookFlow,String questionTypeId);

    /**
     * 根据书号和题目类型 查询该书目下该题型的所有题目和对应的子题
     *
     * @param bookFlow
     * @param questionTypeId
     * @return
     */
    List<ExamQuestionExt> searchExamQuestionAndSubQuestionByBookQuestionType(String bookFlow, String questionTypeId);

    /**
     * 根据科目流水号和题目类型 查询该书目下该题型的所有题目和对应的子题
     *
     * @param subjectFlow
     * @param questionTypeId
     * @return
     */
    List<ExamQuestionExt> searchExamQuestionAndSubQuestionBySubjectQuestionType(String subjectFlow, String questionTypeId);

    List<ExamQuestionWithBLOBs> searchExamQuestionByBookSubject(String bookFlow, String subjectFlow);

    List<ExamQuestionWithBLOBs> searchExamQuestionByBookBook(String bookFlow, String bookFlow2);

    void del(String questionFlow);

    ExamQuestionExt createQuestion(String typeId, ExamBookImp bookImp, Map<Integer, String> questionData);

    /**
     * 替换掉题目选项的ABCE...更改为@@
     *
     * @param data
     * @return
     */
    String replaceQuestionStr(String data);

}
