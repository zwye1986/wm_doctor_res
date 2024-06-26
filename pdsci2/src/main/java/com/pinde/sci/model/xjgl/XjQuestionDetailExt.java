package com.pinde.sci.model.xjgl;

import com.pinde.sci.model.mo.NywjAnswerDetail;
import com.pinde.sci.model.mo.NywjQuestionDetail;

import java.util.List;

/**
 * @Copyright njpdxx.com
 * @since 2018/3/7
 */
public class XjQuestionDetailExt{
    private NywjQuestionDetail questionDetail;
    private List<NywjAnswerDetail> answerDetailList;

    public NywjQuestionDetail getQuestionDetail() {
        return questionDetail;
    }

    public void setQuestionDetail(NywjQuestionDetail questionDetail) {
        this.questionDetail = questionDetail;
    }

    public List<NywjAnswerDetail> getAnswerDetailList() {
        return answerDetailList;
    }

    public void setAnswerDetailList(List<NywjAnswerDetail> answerDetailList) {
        this.answerDetailList = answerDetailList;
    }
}
