package com.pinde.sci.biz.study;

import com.pinde.sci.model.mo.StudyDoctor;
import com.pinde.sci.model.mo.StudySubject;
import com.pinde.sci.model.mo.StudySubjectDetail;

import java.util.List;

public interface IStudySubjectBiz {
    /**
     * 查询发布课程
     */
    List<StudySubject> findAllSubject(String postStatus) throws Exception;

    /**
     * 查询预约人数未满的课程
     */
    List<StudySubject> findSubject(String postStatus) throws Exception;

    /**
     * 查询一条发布课程记录
     */
    StudySubject findBySubjectFlow(String subjectFlow) throws Exception;

    /**
     * 保存预约课程
     */
    int saveSubject(StudySubject studySubject) throws Exception;

   /* *//**
     * 更新
     *//*
    int updateStudySubject(StudySubject studySubject) throws Exception;*/

    int findAppiontNum(String subjectFlow);

    StudySubjectDetail getSubjectDetail(String subjectFlow, String userFlow);
}
