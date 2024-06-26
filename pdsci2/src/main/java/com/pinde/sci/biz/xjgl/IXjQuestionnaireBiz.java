package com.pinde.sci.biz.xjgl;

import com.pinde.sci.model.mo.NywjAnswerDetail;
import com.pinde.sci.model.mo.NywjCourseQuestion;
import com.pinde.sci.model.mo.NywjQuestionDetail;
import com.pinde.sci.model.mo.NywjStudentEvaluate;
import com.pinde.sci.model.xjgl.XjQuestionDetailExt;

import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/3/7
 */
public interface IXjQuestionnaireBiz {
    /**
     * 查询课程评价问卷
     */
    List<NywjCourseQuestion> searchQuestionList(NywjCourseQuestion courseQuestion);

    /**
     * 保存评价问卷维护
     */
    int saveCourseQuestion(NywjCourseQuestion courseQuestion,List<XjQuestionDetailExt> detailExts);

    /**
     * 删除问卷
     */
    int delCourseQuestion(String recordFlow);

    List<Map<String,String>> searchQuestions(Map<String ,String> map);

    List<NywjQuestionDetail> searchQuestionDetailList(NywjQuestionDetail questionDetail);

    List<NywjAnswerDetail> searchAnswerDetailList(NywjAnswerDetail answerDetail);

    List<Map<String,String>> searchStudentCourseInfos(Map<String ,String> map);

    List<NywjStudentEvaluate> searchStudentEvaluateList(NywjStudentEvaluate studentEvaluate);

    int saveStuEvaluate(NywjStudentEvaluate studentEvaluate);

    List<Map<String,String>> searchEvaluateDetails(Map<String ,String> map);
}
