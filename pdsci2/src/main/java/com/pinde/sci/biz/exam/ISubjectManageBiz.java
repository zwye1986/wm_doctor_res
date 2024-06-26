package com.pinde.sci.biz.exam;

import com.pinde.sci.model.mo.ExamBook;
import com.pinde.sci.model.mo.ExamSubject;

import java.util.List;
import java.util.Map;

public interface ISubjectManageBiz {

    List<ExamSubject> search(ExamSubject subject);

    int countQuestNumOfSubject(String subjectFlow);

    List<Map<String, Integer>> countQuestNumByQuestionTypeOfSubject(String subjectFlow);

//	public List<Map<String,Integer>> countQuestNumBySubjectOfSubject(String subjectFlow);

    List<Map<String, Integer>> countQuestNumByBookOfSubject(String subjectFlow);

    void saveOrder(String[] subjectFlows);

    List<ExamBook> searchBookBySubject(String subjectFlow);

    List<ExamBook> searchBookByQuestion(String questionFlow);

    List<ExamSubject> searchSubjectByQuestion(String questionFlow);

//	public List<ExamSubject> searchSubjectBySubject(String subjectFlow);

    void delQuestionSubject(String subjectFlow, String questionFlow);

    void unRel(String subjectFlow);

    ExamSubject read(String subjectFlow);

    void add(ExamSubject subject, ExamSubject subjectParent);

    void mod(ExamSubject subject);

    void del(String subjectFlow);

    void setenabled(String enabledtype, String subjectFlow);

    void merge(String[] subjectFlows);

    int countForTree(String subjectFlow);

    void copy(String examBankType, String targetSubjectFlow, String sourceSubjectFlow);

    void extract(String examBankType, String targetSubjectFlow, String sourceSubjectFlow);

    /**
     * 科目编号在题库类型中的数量 (是否唯一)
     *
     * @param subjectCode
     * @param bankTypeId
     * @return
     */
    int countSubjectCodeInBankType(String subjectFlow, String subjectCode, String bankTypeId);

}
